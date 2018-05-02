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

import model.beans.Usuario;
import service.Usuario.UserService;


@WebFilter(urlPatterns = "*")
public class LoginRequiredFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		chain.doFilter(servletRequest, servletResponse);
		
//		boolean isStaticResource = request.getRequestURI().contains("resources/");
//		boolean isAdminRequest = request.getRequestURI().contains("/admin");
//		boolean loggedIn = request.getSession().getAttribute("user") != null;
//		boolean loggingOut = request.getRequestURI().contains("/logout");
//		boolean isAdmin = loggedIn && ((Usuario)(request.getSession().getAttribute("user"))).getRolID().equals("Administrador");
//		
//		
//		if (isStaticResource || loggingOut) {
//			chain.doFilter(servletRequest, servletResponse);
//		}else if (loggedIn) {
//			if(isAdminRequest && isAdmin){
//				chain.doFilter(servletRequest, servletResponse);
//			}else if(isAdminRequest && !isAdmin){
//				request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
//			}else if(!isAdminRequest && isAdmin){
//				request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
//			}else if(!isAdminRequest && !isAdmin){
//				chain.doFilter(servletRequest, servletResponse);
//			}	
//		}else{
//			System.out.println("login");
//			request.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
//		}

//		if (isStaticResource) {
//			chain.doFilter(servletRequest, servletResponse);
//			}
//		else if (loggedIn && !isAdminRequest) {
//			chain.doFilter(servletRequest, servletResponse);
//		}else if (isAdminRequest && isAdmin) {
//			chain.doFilter(servletRequest, servletResponse);
//		}else if (isAdminRequest && !isAdmin) {
//			request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
//		} else {
//			request.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}