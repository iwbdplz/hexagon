package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//　リクエストのエンコーディング設定がパラメータで設定したエンコーディングパラメータと違う場合パラメータで設定した値に変更するフィルター。
// 리퀘스트의 인코딩 설정이 파라미터로 설정된 인코딩 메서드와 다른 경우 파라미터로 설정된 값을 변환하는 필터
public class CharacterEncodingFilter implements Filter {

	private String encoding;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}
}
