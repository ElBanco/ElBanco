package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import model.beans.*;

public class UsuarioDAO extends DAO{

	public UsuarioDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private final String AUTH = "SELECT * FROM Usuario WHERE NombreUsuario=? AND HashContrasena=?;";
	
	private final String GET = "SELECT * FROM Usuario WHERE NombreUsuario=?;";
	
	private final String INSERT = "INSERT INTO Usuario (" +
			"NombreUsuario, " +
			"RolID, " +
			"Nombre, " +
			"Apellidos, " +
			"Email, " +
			"Telefono, " +
			"Direccion, " +
			"HashContrasena, " +
			"FechaCreacion, " +
			"FechaModificacion) " +
			"VALUES (?,?,?,?,?,?,?,?,?,?);";
	
	private final String UPDATE_BAJA = "UPDATE Usuario SET FechaBaja=?, FechaModificacion=? WHERE NombreUsuario=?;";
	

	
	public Usuario getUser(String username) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		Usuario user = null;
		
		if(rs.next()){
			user = new Usuario();
		    user.setNombreUsuario(rs.getString("NombreUsuario"));
		    user.setRolID(rs.getString("RolID"));
		    user.setNombre(rs.getString("Nombre"));
		    user.setApellidos(rs.getString("Apellidos"));
		    user.setDireccion(rs.getString("Direccion"));
		    user.setEmail(rs.getString("Email"));
		    user.setFechaCreacion(rs.getDate("FechaCreacion"));
		    user.setFechaModificacion(rs.getDate("FechaModificacion"));
		    user.setFechaBaja(rs.getDate("FechaBaja"));
		    user.setHashContrasena(rs.getString("HashContrasena"));
		    user.setTelefono(rs.getString("Telefono"));
		}
		
		return user;
	}
	
	public Usuario authenticate(String username, String password) throws SQLException{
	
		PreparedStatement stmt = conn.prepareStatement(AUTH);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		Usuario user = null;
		
		if (rs.next()) {
	       user = new Usuario();
	       user.setNombreUsuario(rs.getString("NombreUsuario"));
	       user.setRolID(rs.getString("RolID"));
	       user.setNombre(rs.getString("Nombre"));
	       user.setApellidos(rs.getString("Apellidos"));
	       user.setDireccion(rs.getString("Direccion"));
	       user.setEmail(rs.getString("Email"));
	       user.setFechaCreacion(rs.getDate("FechaCreacion"));
	       user.setFechaModificacion(rs.getDate("FechaModificacion"));
	       user.setFechaBaja(rs.getDate("FechaBaja"));
	       user.setHashContrasena(rs.getString("HashContrasena"));
	       user.setTelefono(rs.getString("Telefono"));
	     }
	     
	     rs.close();
	     stmt.close();
	    
	     return user;
	}

	public void addUser(Usuario newUser) throws SQLException {
		
		PreparedStatement stmt = conn.prepareStatement(INSERT);
		GregorianCalendar calendar = new GregorianCalendar();
		
		stmt.setString(1, newUser.getNombreUsuario());
		stmt.setString(2, newUser.getRolID());
		stmt.setString(3, newUser.getNombre());
		stmt.setString(4, newUser.getApellidos());
		stmt.setString(5, newUser.getEmail());
		stmt.setString(6, newUser.getTelefono());
		stmt.setString(7, newUser.getDireccion());
		stmt.setString(8, newUser.getHashContrasena());
		stmt.setDate(9, new java.sql.Date(calendar.getTime().getTime()));
		stmt.setDate(10, new java.sql.Date(calendar.getTime().getTime()));
		
		stmt.executeUpdate();
		stmt.close();
	}

	public boolean darBaja(Usuario user) throws SQLException{
		// TODO Auto-generated method stub
		PreparedStatement stmt = conn.prepareStatement(UPDATE_BAJA);
		GregorianCalendar calendar = new GregorianCalendar();
		
		stmt.setDate(1, new java.sql.Date(calendar.getTime().getTime()));
		stmt.setDate(2, new java.sql.Date(calendar.getTime().getTime()));
		stmt.setString(3, user.getNombreUsuario());
		
		stmt.executeUpdate();
		stmt.close();
		
		return true;
	}
	
}
