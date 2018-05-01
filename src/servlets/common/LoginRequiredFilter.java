package servlets.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "*")
public class LoginRequiredFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		boolean isStaticResource = request.getRequestURI().startsWith("/resources/");
		boolean loggedIn = request.getSession().getAttribute("user") != null;

		if (loggedIn || isStaticResource) {
			chain.doFilter(servletRequest, servletResponse);
		} else {
			request.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}