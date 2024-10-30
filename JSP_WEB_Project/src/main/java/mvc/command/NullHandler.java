package mvc.command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 要請を処理するハンドラーを探せなかった場合４０４エラーを返却するハンドラー。
// 요청을 처리할 핸들러를 찾지 못했을 경우 404에러를 반환하는 핸들러
public class NullHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
