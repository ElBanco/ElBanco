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
	
	private void handleAddUserResult(UserUpdateResult result, HttpServletResponse resp) throws IOException{
		
		PrintWriter out = resp.getWriter();
		
		if(result.isSuccessfulUpdate()){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Prueba</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Valid User");
			out.println("</body>");
			out.println("</html>");
			return;
		}
		
		
		switch (result.getError()) {
		
			case DUPLICATED_USER:
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Prueba</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("DUPLICATED_USER");
				out.println("</body>");
				out.println("</html>");
				break;
	
			case DUPLICATED_EMAIL:
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Prueba</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("DUPLICATED_EMAIL");
				out.println("</body>");
				out.println("</html>");
				break;
				
			default:
				break;
			
		}
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
		req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Usuario newUser = createUser(req);
		//if(newUser != null && req.getParameter("cantidadDinero") != null){
			//UserUpdateResult result = new UserService().addNewUser(newUser, Double.valueOf(req.getParameter("cantidadDinero")));
			//handleAddUserResult(result, resp);
		//}
		//req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
		
				String actionValue = req.getParameter("action");
				if (actionValue.equals("addUser")){
					
					Usuario newUser = createUser(req);
					if(newUser != null && req.getParameter("cantidadDinero") != null){
						new UserService().addNewUser(newUser, Double.valueOf(req.getParameter("cantidadDinero")));
					}
				}
				//Este lo hice pero no vale para mucho
				else if(actionValue.equals("bindAccount")){
					
					
				}
				else if(actionValue.equals("addNewAccount")){
					
					Cuenta newAccount = createAccount(req);
					new AccountService().addNewAccount(newAccount);
					
				}
				else if(actionValue.equals("addMonedero")){
					
					
				}
				else if(actionValue.equals("addDebitCard")){
					
					TarjetaDebito debitCard = createDebitCard(req);
					new CardService().addNewDebitCard(debitCard);
					
				}
				else if(actionValue.equals("darBajaUser")){
					
					new UserService().darBajaUser(req.getParameter("nombreUsuario"));
		
				}
				else if(actionValue.equals("darBajaCuenta")){
					
					new AccountService().darBajaCuenta(req.getParameter("numeroCuenta"));
				
		 		}	 		
				else if(actionValue.equals("darBajaTarjeta")){
					
					new CardService().darBajaTarjeta(req.getParameter("numeroTarjeta"));
					
				}
				
	}

	
}
