package servlets.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Usuario.UserService;


import model.beans.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if((username == null) || (password == null)){
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			return;
		}
		
		PrintWriter out = resp.getWriter();
		Usuario user = new UserService().authenticate(username, password);

		if(user != null){
			req.getSession().setAttribute("user", user);
			if(user.getRolID().equals("Administrador")){
				resp.sendRedirect("/ElBanco/admin");
			}else{
				resp.sendRedirect("/ElBanco/client");
			}
		}else{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Prueba</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Access Denied");
			out.println("</body>");
			out.println("</html>");
		}
		

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}

}
