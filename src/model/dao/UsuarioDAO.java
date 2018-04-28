package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DatesHelper;
import utils.RandomStringGenerator;
import model.beans.*;

public class UsuarioDAO extends DAO{

	public UsuarioDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private final String AUTH = "SELECT * FROM Usuario WHERE NombreUsuario=? AND HashContrasena=SHA2(?, 256);";
	
	private final String GET_BY_USERNAME = "SELECT * FROM Usuario WHERE NombreUsuario=?;";
	
	private final String GET_BY_EMAIL = "SELECT * FROM Usuario WHERE Email=?;";
	
	private final String INSERT = "INSERT INTO Usuario (" +
			"NombreUsuario, RolID, Nombre, Apellidos, Email, Telefono, Direccion, HashContrasena, FechaCreacion, FechaModificacion) " +
			"VALUES (?,?,?,?,?,?,?,SHA2(?, 256),?,?);";
	
	
	
	@Override
	void processRow(Object bean, ResultSet rs) throws SQLException {
		
		Usuario user = (Usuario) bean;
		
		user.setNombreUsuario(rs.getString("NombreUsuario"));
	    user.setRolID(rs.getString("RolID"));
	    user.setNombre(rs.getString("Nombre"));
	    user.setApellidos(rs.getString("Apellidos"));
	    user.setDireccion(rs.getString("Direccion"));
	    user.setEmail(rs.getString("Email"));
	    user.setFechaCreacion(rs.getDate("FechaCreacion"));
	    user.setFechaModificacion(rs.getDate("FechaModificacion"));
	    user.setFechaBaja(rs.getDate("FechaBaja"));
	    user.setTelefono(rs.getString("Telefono"));
		
	}

	
	public Usuario getUserByUsername(String username) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET_BY_USERNAME);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		Usuario user = null;
		
		if(rs.next()){
			user = new Usuario();
		    processRow(user, rs);
		}
		
		rs.close();
	    stmt.close();
		
		return user;
		
	}
	
	public Usuario getUserByEmail(String email) throws SQLException{
			
			PreparedStatement stmt = conn.prepareStatement(GET_BY_EMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			Usuario user = null;
			
			if(rs.next()){
				user = new Usuario();
			    processRow(user, rs);
			}
			
			rs.close();
		    stmt.close();
		    
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
	       processRow(user, rs);
	     }
	     
	     rs.close();
	     stmt.close();
	    
	     return user;


	}

	public void addUser(Usuario newUser, String password) throws SQLException {
		
		PreparedStatement stmt = conn.prepareStatement(INSERT);
		DatesHelper datesFactory = new DatesHelper();
		newUser.setFechaCreacion(datesFactory.getUtilDate());
		newUser.setFechaModificacion(datesFactory.getUtilDate());
		
		stmt.setString(1, newUser.getNombreUsuario());
		stmt.setString(2, newUser.getRolID());
		stmt.setString(3, newUser.getNombre());
		stmt.setString(4, newUser.getApellidos());
		stmt.setString(5, newUser.getEmail());
		stmt.setString(6, newUser.getTelefono());
		stmt.setString(7, newUser.getDireccion());
		stmt.setString(8, password);
		stmt.setDate(9, datesFactory.getSqlDate());
		stmt.setDate(10, datesFactory.getSqlDate());
		
		stmt.executeUpdate();
		stmt.close();
		
		
	}

	
}
