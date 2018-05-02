package servlets.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.beans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UpdateResult;
import service.Cuenta.AccountService;
import service.Operacion.OperationService;
import service.Operacion.OperationUpdateResult;
import service.Tarjeta.CardService;
import service.Usuario.UserService;


@WebServlet("/client/cuentas")
public class CuentasServlet extends HttpServlet{
	
	private String doTransfer(String numeroCuenta, String numeroCuentaDestino, Double cantidad){
		
		OperationUpdateResult result = new OperationService().doTransference(numeroCuenta, numeroCuentaDestino, cantidad);
		
		String message = "";
		if(result.isSuccessfulUpdate()){
			message = "Transaccion exitosa";
			return message;
		}
		
		
		switch (result.getError()) {
		
			case BALANCE:
				message = "Dinero insuficiente";
				break;
	
			case DAYLY_LIMIT:
				message = "Limite diario excedido";
				break;
				
			case LOWER_LIMIT:
				message = "Limite inferio excedido";
				break;
				
			case UNKOWN_ACCOUNT:
				message = "Cuenta desconocida";
				break;
				
			default:
				break;
			
		}
		
		return message;
	}
		
	
	private boolean inMonedero(String numeroCuenta, String nombreUsuario, Double cantidad){
		UpdateResult result = new OperationService().updateMonedero(nombreUsuario, cantidad, numeroCuenta);
		return result.isSuccessfulUpdate();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreUsuario = null;
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		HttpSession session = req.getSession();
		
		if(req.getParameter("nombreUsuario") != null){
			cuentas = new AccountService().getAccounts(req.getParameter("nombreUsuario"));
			if(!cuentas.isEmpty()){
				nombreUsuario = req.getParameter("nombreUsuario");
				session.setAttribute("nombreUsuario", nombreUsuario);
			}
		}else if(session.getAttribute("nombreUsuario") != null){
			cuentas = new AccountService().getAccounts((String) session.getAttribute("nombreUsuario"));
			if(!cuentas.isEmpty()){
				nombreUsuario = (String) session.getAttribute("nombreUsuario");
				req.setAttribute("message", session.getAttribute("message"));
				session.setAttribute("message", null);
			}
		}
		
		if(!cuentas.isEmpty()){
			req.setAttribute("cuentas", cuentas);
			req.getRequestDispatcher("/WEB-INF/views/cuentas_client.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = req.getParameter("action");
		String numeroCuenta = req.getParameter("numeroCuenta");
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		String nombreUsuario = user.getNombreUsuario();
		Double cantidad = Double.valueOf(req.getParameter("cantidad"));
		String message = "";
		
		if(action.equals("doTransfer")){
			String numeroCuentaDestino = req.getParameter("numeroCuentaDestino");
			message = doTransfer(numeroCuenta, numeroCuentaDestino, cantidad);
		}else if(action.equals("inMonedero")){
			if(inMonedero(numeroCuenta, nombreUsuario, cantidad)){
				message = "Ingreso con exito";
			}else{
				message = "Error ingresando en monedero";
			}
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("message", message);
		resp.sendRedirect("/ElBanco/client/cuentas");
	}
	
	
	
}
	


