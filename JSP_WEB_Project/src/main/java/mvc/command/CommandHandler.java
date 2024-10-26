package mvc.command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 各リクエストに対してハンドラーを具現するための親インターフェース
public interface CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
