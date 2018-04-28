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
import service.Usuario.UserService;
import service.Usuario.UserUpdateResult;


import model.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
	
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
		Usuario newUser = createUser(req);
		if(newUser != null && req.getParameter("cantidadDinero") != null){
			UserUpdateResult result = new UserService().addNewUser(newUser, Double.valueOf(req.getParameter("cantidadDinero")));
			handleAddUserResult(result, resp);
		}
		//req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
	}

	
}
