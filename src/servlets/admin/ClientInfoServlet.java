package servlets.admin;

import java.io.IOException;
import model.beans.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UpdateResult;
import service.Cuenta.AccountService;
import service.Tarjeta.CardService;
import service.Usuario.UserService;
import service.Usuario.UserUpdateResult;



@WebServlet("/admin/client_info/*")
public class ClientInfoServlet extends HttpServlet{
	
	private void clientInfoDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String username = null;
		Usuario client = null;
		HttpSession session = req.getSession();
		
		
		if(req.getParameter("nombreUsuario") != null){
			client = new UserService().getUser(req.getParameter("nombreUsuario"));
			if(client != null){
				username = req.getParameter("nombreUsuario");
				session.setAttribute("nombreUsuario", username);
			}
		}else if(session.getAttribute("nombreUsuario") != null){
			client = new UserService().getUser((String) session.getAttribute("nombreUsuario"));
			if(client != null){
				username = (String) session.getAttribute("nombreUsuario");
				req.setAttribute("message", session.getAttribute("message"));
				session.setAttribute("message", null);
			}
		}

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
	
	private void tarjetasInfoDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String numeroCuenta = null;
		List<TarjetaDebito> tarjetas = new ArrayList<TarjetaDebito>();
		HttpSession session = req.getSession();
		
		if(req.getParameter("numeroCuenta") != null){
			tarjetas = new CardService().getDebitCards(req.getParameter("numeroCuenta"));
			if(!tarjetas.isEmpty()){
				numeroCuenta = req.getParameter("numeroCuenta");
				session.setAttribute("numeroCuenta", numeroCuenta);
			}
		}else if(session.getAttribute("numeroCuenta") != null){
			tarjetas = new CardService().getDebitCards((String) session.getAttribute("numeroCuenta"));
			if(!tarjetas.isEmpty()){
				numeroCuenta = (String) session.getAttribute("numeroCuenta");
				req.setAttribute("message", session.getAttribute("message"));
				session.setAttribute("message", null);
			}
		}
		
		if(!tarjetas.isEmpty()){
			req.setAttribute("tarjetas", tarjetas);
			req.getRequestDispatcher("/WEB-INF/views/ver_tarjetas.jsp").forward(req, resp);
		}else{
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Prueba</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("No hay tarjetas");
			out.println("</body>");
			out.println("</html>");
		}
		
		
	}
	
	private boolean darBajaTarjeta(String numeroTarjeta){
		CardService cardService = new CardService();
		if(numeroTarjeta == null) return false;
		UpdateResult result = cardService.darBajaTarjeta(numeroTarjeta);
		return result.isSuccessfulUpdate();	
	}
	
	
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

	private boolean darBajaUsuario(String nombreUsuario){
		UserService userService = new UserService();
		if(nombreUsuario == null) return false;
		UserUpdateResult result = userService.darBajaUser(nombreUsuario);
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
		String path = req.getRequestURI();
		if(path.equals("/ElBanco/admin/client_info")){
			clientInfoDispatch(req, resp);
		}else if(path.equals("/ElBanco/admin/client_info/tarjetas")){
			tarjetasInfoDispatch(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = req.getParameter("action");
		String numeroCuenta = req.getParameter("numeroCuenta");
		String nombreUsuario = req.getParameter("nombreUsuario");
		String message = "";
		
		if(action.equals("addDebitCard")){
			String titular = req.getParameter("titular");
			if(addDebitCard(titular, numeroCuenta)){
				message = "Tarjeta añadida con exito";
			}else{
				message = "Error añadiendo tarjeta";
			}
		}else if(action.equals("darBajaCuenta")){
			if(darBajaCuenta(numeroCuenta)){
				message = "Cuenta dada de baja con exito";
			}else{
				message = "Error dando de baja cuenta";
			}
		}else if(action.equals("darBajaMonedero")){
			String monedero = req.getParameter("monedero");
			System.out.println(monedero);
			if(darBajaMonedero(monedero)){
				message = "Monedero dado de baja con exito";
			}else{
				message = "Error dando de baja monedero";
			}
		}else if(action.equals("darBajaUsuario")){
			if(darBajaUsuario(nombreUsuario)){
				message = "Usuario dado de baja con exito";
			}else{
				message = "Error dando de baja usuario";
			}
		}else if(action.equals("addAccount")){
			Double saldo = Double.valueOf(req.getParameter("saldo"));
			if(addAccount(nombreUsuario, saldo)){
				message = "Cuenta añadida con exito";
			}else{
				message = "Error añadiendo cuenta";
			}
		}else if(action.equals("darBajaTarjeta")){
			String numeroTarjeta = req.getParameter("numeroTarjeta");
			if(darBajaTarjeta(numeroTarjeta)){
				message = "Tarjeta dada de baja con exito";
			}else{
				message = "Error dando de baja tarjeta";
			}
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("message", message);
		if(action.equals("darBajaTarjeta")){
			resp.sendRedirect("/ElBanco/admin/client_info/tarjetas");
		}else{
			resp.sendRedirect("/ElBanco/admin/client_info");
		}
		
		
	}
	
}
