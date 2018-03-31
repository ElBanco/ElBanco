package servlets.admin;

import java.io.IOException;
import java.util.Date;
import model.beans.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet{
	
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
			new DBInteractions().addNewUser(newUser, Double.valueOf(req.getParameter("cantidadDinero")));
		}
		req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
	}

	
}
