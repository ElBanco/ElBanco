package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import utils.DatesFactory;
import utils.RandomStringGenerator;
import model.beans.*;

public class UsuarioDAO extends DAO{

	public UsuarioDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private final String AUTH = "SELECT * FROM Usuario WHERE NombreUsuario=? AND HashContrasena=?;";
	
	private final String GET = "SELECT * FROM Usuario WHERE NombreUsuario=?;";
	
	private final String INSERT = "INSERT INTO Usuario (" +
			"NombreUsuario, RolID, Nombre, Apellidos, Email, Telefono, Direccion, HashContrasena, FechaCreacion, FechaModificacion) " +
			"VALUES (?,?,?,?,?,?,?,?,?,?);";
	
	private final RandomStringGenerator passGenerator = new RandomStringGenerator(10, RandomStringGenerator.StringType.ALPHANUMERIC);
	
	
	@Override
	void processRow(Object bean, ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
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
		DatesFactory datesFactory = new DatesFactory();
		String pass = passGenerator.newString();
		newUser.setHashContrasena(pass);
		System.out.println(pass);
		newUser.setFechaCreacion(datesFactory.getUtilDate());
		newUser.setFechaModificacion(datesFactory.getUtilDate());
		
		stmt.setString(1, newUser.getNombreUsuario());
		stmt.setString(2, newUser.getRolID());
		stmt.setString(3, newUser.getNombre());
		stmt.setString(4, newUser.getApellidos());
		stmt.setString(5, newUser.getEmail());
		stmt.setString(6, newUser.getTelefono());
		stmt.setString(7, newUser.getDireccion());
		stmt.setString(8, newUser.getHashContrasena());
		stmt.setDate(9, datesFactory.getSqlDate());
		stmt.setDate(10, datesFactory.getSqlDate());
		
		stmt.executeUpdate();
		stmt.close();
		
		
	}

	
}
