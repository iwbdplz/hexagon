package mvc.command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 各リクエストに対してハンドラーを具現するための親インターフェース
// 각 요청에 대한 핸들러를 구현하기 위한 부모 인터페이스
public interface CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
