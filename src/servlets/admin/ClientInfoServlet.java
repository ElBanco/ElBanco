package servlets.admin;

import java.io.IOException;
import model.beans.*;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UpdateResult;
import service.Cuenta.AccountService;
import service.Tarjeta.CardService;
import service.Usuario.UserService;



@WebServlet("/admin/client_info")
public class ClientInfoServlet extends HttpServlet{
	
	private boolean addDebitCard(String titular, String numeroCuenta){
		
		CardService cardService = new CardService();
		TarjetaDebito newDebitCard = new TarjetaDebito();
		
		if((titular == null) || (numeroCuenta == null)) return false;
		
		newDebitCard.setTitular(titular);
		newDebitCard.setNumeroCuenta(numeroCuenta);
		
		UpdateResult result = cardService.addNewDebitCard(newDebitCard);
		return result.isSuccessfulUpdate();
		
	}
	
	private boolean addAccount(String nombreUsuario, Double saldo){
			
			AccountService accountService = new AccountService();
			Cuenta newAccount = new Cuenta();
			
			if((nombreUsuario == null) || (saldo == null)) return false;
			
			newAccount.setNombreUsuario(nombreUsuario);
			newAccount.setSaldo(saldo);
			
			UpdateResult result = accountService.addNewAccount(newAccount);
			return result.isSuccessfulUpdate();
			
	}
	
	private boolean darBajaCuenta(String numeroCuenta){
		AccountService accountService = new AccountService();
		if(numeroCuenta == null) return false;
		UpdateResult result = accountService.darBajaCuenta(numeroCuenta);
		return result.isSuccessfulUpdate();
	}
	
	private boolean darBajaMonedero(String numeroMonedero){
		CardService cardService = new CardService();
		if(numeroMonedero == null) return false;
		UpdateResult result = cardService.darBajaTarjeta(numeroMonedero);
		return result.isSuccessfulUpdate();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("nombreUsuario");
		Usuario client = new UserService().getUser(username);

		if(client != null){
			List<Cuenta> cuentas = new AccountService().getAccounts(username);
			Monedero monedero = new CardService().getMonedero(username);
			req.setAttribute("client", client);
			req.setAttribute("cuentas", cuentas);
			req.setAttribute("monedero", monedero);
			req.getRequestDispatcher("/WEB-INF/views/client_info.jsp").forward(req, resp);
		}else{
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Prueba</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("User Not Found");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		String numeroCuenta = req.getParameter("numeroCuenta");
		
		if(action.equals("addDebitCard")){
			String titular = req.getParameter("titular");
			addDebitCard(titular, numeroCuenta);
		}else if(action.equals("darBajaCuenta")){
			if(darBajaCuenta(numeroCuenta)){
				System.out.println("dar baja Cuenta");
			}
		}else if(action.equals("darBajaMonedero")){
			String monedero = req.getParameter("monedero");
			if(darBajaMonedero(monedero)){
				System.out.println("dar baja Cuenta");
			}
		}else if(action.equals("addAccount")){
			Double saldo = Double.valueOf(req.getParameter("saldo"));
			String nombreUsuario = req.getParameter("nombreUsuario");
			addAccount(nombreUsuario, saldo);
		}
		
		
	}
	
}
