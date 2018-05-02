package servlets.client;

import java.io.IOException;
import java.util.List;
import model.beans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Cuenta.AccountService;
import service.Operacion.OperationService;


@WebServlet("/client")
public class ClientServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		req.setAttribute("userBean", user);
		req.getRequestDispatcher("/WEB-INF/views/homeClient.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	
}
