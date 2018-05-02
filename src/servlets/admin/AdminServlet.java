package servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import model.beans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import service.Cuenta.AccountService;
import service.Tarjeta.CardService;
import service.Usuario.UserService;
import service.Usuario.UserUpdateResult;


import model.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
	
	
		private Cuenta createAccount(HttpServletRequest req){
				
				String nombreUsuario= req.getParameter("nombreUsuario");
				Double saldo = Double.parseDouble(req.getParameter("saldo"));
				
				Cuenta account = null;
				
				if(nombreUsuario != null &&  
					saldo != null )
				{
		
					account = new Cuenta();
					account.setNombreUsuario(nombreUsuario);
					account.setSaldo(saldo);
					
				}
				
				return account;		
			}
			
			
			private TarjetaDebito createDebitCard(HttpServletRequest req){
				
				String titular = req.getParameter("titular");
				String numeroCuenta = req.getParameter("numeroCuenta");
				TarjetaDebito debitCard = null;
				
				if(titular != null &&
					numeroCuenta != null)
				{
					
					debitCard = new TarjetaDebito();
					debitCard.setTitular(titular);
					debitCard.setNumeroCuenta(numeroCuenta);
					
				}
				
				return debitCard;
			}
	
	private String handleAddUserResult(UserUpdateResult result, HttpServletResponse resp) throws IOException{
		
		String message = "";
		
		if(result.isSuccessfulUpdate()){
			message = "Usuario añadido con exito";
			return message;
		}
		
		
		switch (result.getError()) {
		
			case DUPLICATED_USER:
				message = "El usuario ya existe";
				break;
	
			case DUPLICATED_EMAIL:
				message = "Dirección de correo no disponible";
				break;
				
			default:
				message = "Error desconocido";
				break;
			
		}
		
		System.out.println(message);
		
		return message;
	}
	
	private Usuario createUser(HttpServletRequest req){
		
		String nombreUsuario = req.getParameter("nombreUsuario");
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String telefono = req.getParameter("telefono");
		String direccion = req.getParameter("direccion");
		
		Usuario user = null;
		
		if(nombreUsuario != null && 
		   nombre != null &&  
		   apellidos != null && 
		   email != null && 
		   telefono != null && 
		   direccion != null) 
		{
			user = new Usuario();
			user.setNombreUsuario(nombreUsuario);
			user.setNombre(nombre);
			user.setApellidos(apellidos);
			user.setEmail(email);
			user.setTelefono(telefono);
			user.setDireccion(direccion);
			user.setRolID("Cliente");
			user.setFechaCreacion(new Date());
			user.setFechaModificacion(new Date());
			
		}
		
		return user;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		req.setAttribute("message", session.getAttribute("message"));
		session.setAttribute("message", null);
		req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario newUser = createUser(req);
		String message = "Parametros incompletos";
		
		if(newUser != null && req.getParameter("cantidadDinero") != ""){
			UserUpdateResult result = new UserService().addNewUser(newUser, Double.valueOf(req.getParameter("cantidadDinero")));
			message = handleAddUserResult(result, resp);
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("message", message);
		resp.sendRedirect("/ElBanco/admin");
		
				
	}

	
}
