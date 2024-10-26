package mvc.command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 要請を処理するハンドラーを探せなかった場合４０４エラーを返却するハンドラー。
public class NullHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
