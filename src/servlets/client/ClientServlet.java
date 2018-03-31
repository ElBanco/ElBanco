package servlets.client;

import java.io.IOException;
import java.util.List;
import model.beans.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBInteractions;


@WebServlet("/client")
public class ClientServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new DBInteractions().getAccounts(user);
		req.setAttribute("userBean", user);
		req.setAttribute("cuentas", cuentas);
		req.getRequestDispatcher("/WEB-INF/views/client.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Double cantidad;
		int numeroCuentaDestino;
		
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new DBInteractions().getAccounts(user);
		int numeroCuentaOrigen = cuentas.get(0).getNumeroCuenta();
		
		if(req.getParameter("numeroCuentaDestino") != null && req.getParameter("cantidad") != null){
			cantidad = Double.valueOf(req.getParameter("cantidad"));
			numeroCuentaDestino = Integer.valueOf(req.getParameter("numeroCuentaDestino"));
			if (new DBInteractions().doTransference(numeroCuentaOrigen, numeroCuentaDestino, cantidad)) System.out.println("aaa");;
		}
		
		doGet(req, resp);
	}

	
}
