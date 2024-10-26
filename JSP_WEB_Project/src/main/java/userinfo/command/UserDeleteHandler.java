package userinfo.command;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import userinfo.service.UserInfoDeleteService;

// ユーザー削除リクエストを担当するハンドラー。
public class UserDeleteHandler implements CommandHandler {

	private UserInfoDeleteService userInfoDeleteService = new UserInfoDeleteService();
	
	//　POST以外のリクエストは許可しない。
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
	private void proceed(HttpServletRequest req, HttpServletResponse res) {
		String[] userIds = req.getParameterValues("userId");
		//　ユーザーIDが入力されなかった時はエラーを発生する。
		if (userIds == null) {
			Map<String, Boolean> errors = new HashMap<>();
			errors.put("noSelectedError", Boolean.TRUE);
			req.setAttribute("errors", errors);
		} else {
			userInfoDeleteService.deleteUserInfo(userIds);
		}
	}

}

