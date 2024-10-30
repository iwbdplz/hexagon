package mvc.controller;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

// URIを通じて要請を区分して正しいサービロジックを実行するクラス。
// URI를 통해 요청을 구분하여 알맞는 서비스 로직을 실행하는 클래스
public class ControllerUsingURI extends HttpServlet{

	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	//　プログラムを実行した途端、プロパティファイルにあるパラメーターを呼び出してそれぞれ該当する値をcommandHandlerMapに保存する。
	// 프로그램을 실행한 순간, 프로퍼티 파일에 있는 파라미터를 불러내 각각 해당하는 값을 commandHandlerMap에 저장한다.
	// commandHandlerMapは＜URI, クラス＞の形式だ。
	// commandHandlerMap은 <URI, 클래스>의 형식이다.
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		try(FileReader fis = new FileReader(configFilePath)){
			prop.load(fis);
		} catch(IOException e) {
			throw new ServletException(e);
		}
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}
	
	// commandHandlerMapからリクエストのURIを通じて実行するクラスを呼び出す。
	// commandHandlerMap 으로부터 리퀘스트의 URI를 통해 실행할 클래스를 불러낸다.
	// 実行するクラスを呼び出す事を失敗した場合、NullHandlerを呼び出して４０４エラーを返却する。
	// 실행할 클래스를 불러내는 것을 실패한 경우, NullHandler를 불러내 404 에러를 반환한다.
	// 実行するクラスを呼び出す事を成功した場合、利用者に提供するVIEWページを返却する。
	// 실행할 클래스를 불러내는 것을 성공한 경우, 사용자에게 제공할 VIEW 페이지를 반환한다.
	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null) {
			handler = new NullHandler();
		}
		String viewPage = null;
		try {
			viewPage = handler.process(req, res);
		} catch(Exception e) {
			throw new ServletException(e);
		}
		if (viewPage != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res);
		}
	}
}
