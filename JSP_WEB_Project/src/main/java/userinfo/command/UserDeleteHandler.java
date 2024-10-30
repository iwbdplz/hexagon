package userinfo.command;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import userinfo.service.UserInfoDeleteService;

// ユーザー削除リクエストを担当するハンドラー。
// 유저 삭제 리퀘스트를 담당하는 메서드
public class UserDeleteHandler implements CommandHandler {

	private UserInfoDeleteService userInfoDeleteService = new UserInfoDeleteService();
	
	//　POST以外のリクエストは許可しない。
	// POST 이외의 리퀘스트는 허가하지 않는다.
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			proceed(req, res);
			return "/user/list.do";
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return "/user/list.do";
		}
	}

	//　ユーザーIDを使ってユーザーのデータを削除する。
	// 유저 ID를 사용하여 유저의 데이터를 검색한다.
	private void proceed(HttpServletRequest req, HttpServletResponse res) {
		String[] userIds = req.getParameterValues("userId");
		//　ユーザーIDが入力されなかった時はエラーを発生する。
		// 유저 ID가 입력되지 않은 경우 에러를 발생시킨다.
		if (userIds == null) {
			Map<String, Boolean> errors = new HashMap<>();
			errors.put("noSelectedError", Boolean.TRUE);
			req.setAttribute("errors", errors);
		} else {
			userInfoDeleteService.deleteUserInfo(userIds);
		}
	}

}

