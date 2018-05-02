package servlets.client;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import model.beans.*;
import model.services.AccountService;
import model.services.OperationService;
import model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/client/nuevaoperacion")
public class NuevaOpServlet extends HttpServlet{
	
	
	private Transferencia CreateTransaction(HttpServletRequest req){
		
		String Origen = req.getParameter("origen");
		String Destino = req.getParameter("destino");
		Double cantidad =  Double.parseDouble(req.getParameter("cantidad"));
		
		Transferencia transfer = null;
		
		if(Origen != null && Destino != null &&  cantidad != null){
			transfer.setCantidad(cantidad);
			transfer.setNumeroCuentaDestino(Destino);
			transfer.setNumeroCuentaOrigen(Origen);
		}
		
		return transfer;
	}
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);

		
		req.setAttribute("userBean", user);
		req.setAttribute("cuentas", cuentas);

		req.getRequestDispatcher("/WEB-INF/views/nuevaOperacion.jsp").forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Double cantidad;
		String numeroCuentaDestino;
		String numeroCuentaOrigen;
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);
		
		
		if(req.getParameter("destino") != null && req.getParameter("origen") != null && req.getParameter("cantidad") != null){
			cantidad = Double.valueOf(req.getParameter("cantidad").replace(",","."));
			numeroCuentaDestino = req.getParameter("destino");
			numeroCuentaOrigen = req.getParameter("origen");
			new OperationService().doTransference(numeroCuentaOrigen, numeroCuentaDestino, cantidad);
		}
		
		doGet(req, resp);


	}
	
}
