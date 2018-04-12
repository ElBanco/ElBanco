package servlets.client;

import java.io.IOException;
import java.util.List;
import model.beans.*;
import model.services.AccountService;
import model.services.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/client")
public class ClientServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);
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
		String actionValue = req.getParameter("action");
		
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);
		int numeroCuentaOrigen = cuentas.get(0).getNumeroCuenta();
		
		if(req.getParameter("numeroCuentaDestino") != null && req.getParameter("cantidad") != null){
			cantidad = Double.valueOf(req.getParameter("cantidad"));
			numeroCuentaDestino = Integer.valueOf(req.getParameter("numeroCuentaDestino"));
			new OperationService().doTransference(numeroCuentaOrigen, numeroCuentaDestino, cantidad);
		}
		else if(actionValue.equals("cambiarLimitesCuenta")){
			
			int numeroCuenta = Integer.parseInt(req.getParameter("numeroCuenta"));
			String limiteInferior = req.getParameter("limiteInferior");
			String limiteDiario = req.getParameter("limiteDiario");
			
			new AccountService().cambiarLimiteInferior(req.getParameter("limiteInferior"));
			
			new AccountService()
		}
		
		doGet(req, resp);
	}

	
}
