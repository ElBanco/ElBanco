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

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	
	
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
			req.getRequestDispatcher("/WEB-INF/views/error.html").forward(req, resp);
			return;
		
		

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
