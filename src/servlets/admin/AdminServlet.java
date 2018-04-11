package servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.beans.*;
import model.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario createUser(HttpServletRequest req){
		
		String nombreUsuario = req.getParameter("nombreUsuario");
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String telefono = req.getParameter("telefono");
		String direccion = req.getParameter("direccion");
		String hashContrasena = req.getParameter("hashContrasena");
		
		
		Usuario user = null;
		
		if(nombreUsuario != null && 
		   nombre != null &&  
		   apellidos != null && 
		   email != null && 
		   telefono != null && 
		   direccion != null && 
		   hashContrasena != null)
		{
			user = new Usuario();
			user.setNombreUsuario(nombreUsuario);
			user.setNombre(nombre);
			user.setApellidos(apellidos);
			user.setEmail(email);
			user.setTelefono(telefono);
			user.setDireccion(direccion);
			user.setHashContrasena(hashContrasena);
			user.setRolID("Cliente");
		}
		
		return user;
	}
	
	private Cuenta createAccount(HttpServletRequest req){
		
		String nombreUsuario= req.getParameter("nombreUsuario");
		Double saldo = Double.parseDouble(req.getParameter("saldo"));
		Double limiteDiario = Double.parseDouble(req.getParameter("limiteDiario"));
		Double limiteInferior = Double.parseDouble(req.getParameter("limiteInferior"));
		
		Cuenta account = null;
		
		if(nombreUsuario != null &&  
			saldo != null && 
			limiteDiario != null && 
			limiteInferior != null)
		{

			account = new Cuenta();
			account.setNombreUsuario(nombreUsuario);
			account.setSaldo(saldo);
			account.setLimiteDiario(limiteDiario);
			account.setLimiteInferior(limiteInferior);
			
		}
		
		return account;		
	}
	
	private Monedero createMonedero(HttpServletRequest req){
		
		String nombreUsuario = req.getParameter("nombreUsuario");
		Double saldo = Double.parseDouble(req.getParameter("saldo"));
		Double limiteSuperior = Double.parseDouble(req.getParameter("limiteSuperior"));
		
		Monedero monedero = null;
		
		if(nombreUsuario != null &&
			saldo != null && 
			limiteSuperior != null)
		{
			
			monedero = new Monedero();
			monedero.setNombreUsuario(nombreUsuario);
			monedero.setSaldo(saldo);
			monedero.setLimiteSuperior(limiteSuperior);
			
		}
		
		return monedero;
	}
	
	private TarjetaDebito createDebitCard(HttpServletRequest req){
		
		String titular = req.getParameter("titular");
		Integer numeroCuenta = Integer.parseInt(req.getParameter("numeroCuenta"));
		Double limiteDiario = Double.parseDouble(req.getParameter("limiteDiario"));
		Double limiteSuperior = Double.parseDouble(req.getParameter("limiteSuperior"));
		TarjetaDebito debitCard = null;
		
		if(titular != null &&
			numeroCuenta != null &&
			limiteDiario != null && 
			limiteSuperior != null)
		{
			
			debitCard = new TarjetaDebito();
			debitCard.setTitular(titular);
			debitCard.setNumeroCuenta(numeroCuenta);
			debitCard.setLimiteDiario(limiteDiario);
			debitCard.setLimiteSuperior(limiteSuperior);
			
		}
		
		return debitCard;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String actionValue = req.getParameter("action");
		
		if (actionValue.equals("addUser")){
			
			Usuario newUser = createUser(req);
			if(newUser != null && req.getParameter("cantidadDinero") != null){
				new UserService().addNewUser(newUser, Double.valueOf(req.getParameter("cantidadDinero")));
			}
		}
		//Este lo hice pero no vale para mucho
		else if(actionValue.equals("bindAccount")){
			
			String nombreUsuario = req.getParameter("nombreUsuario");
			int numeroCuenta = Integer.parseInt(req.getParameter("numeroCuenta"));
			new AccountService().bindAccount(nombreUsuario, numeroCuenta);
			
		}
		else if(actionValue.equals("addNewAccount")){
			
			Cuenta newAccount = createAccount(req);
			new AccountService().addNewAccount(newAccount);
			
		}
		else if(actionValue.equals("addMonedero")){
			
			Monedero monedero = createMonedero(req);
			new CardService().addNewMonedero(monedero);
			
		}
		else if(actionValue.equals("addDebitCard")){
			
			TarjetaDebito debitCard = createDebitCard(req);
			new CardService().addNewDebitCard(debitCard);
			
		}
		else if(actionValue.equals("darBajaUser")){
			
			new UserService().darBajaUser(req.getParameter("nombreUsuario"));

		}
		else if(actionValue.equals("darBajaCuenta")){
			
			new AccountService().darBajaCuenta(Integer.parseInt(req.getParameter("numeroCuenta")));
		
		}
		else if(actionValue.equals("darBajaTarjeta")){
			
			new CardService().darBajaTarjeta(Integer.parseInt(req.getParameter("numeroTarjeta")));
			
		}
		
		req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
		
	}
}
