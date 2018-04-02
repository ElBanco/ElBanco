package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.beans.*;

public class TarjetaDAO extends DAO{

	public TarjetaDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private final String INSERT_MONEDERO = "INSERT INTO Tarjeta (" +
			"PIN, " +
			"NombreUsuario, " +
			"Saldo, " +
			"FechaCreacion, " +
			"Discriminador) " +
			"VALUES (?, ?, ?, NOW(), 'Monedero');";
	
	private final String GET_MONEDERO = "SELECT * FROM Tarjeta WHERE NombreUsuario=?;";
	
	

	public void addTarjeta(Tarjeta newCard) throws SQLException{
		
		
		PreparedStatement stmt;
		
		if(newCard instanceof Monedero){
			stmt = conn.prepareStatement(INSERT_MONEDERO, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newCard.getPin());
			stmt.setString(2, ((Monedero) newCard).getNombreUsuario());
			stmt.setDouble(3, ((Monedero) newCard).getSaldo()); 
		}else{
			return;
		}
		
		
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
		    newCard.setNumeroTarjeta(rs.getInt(1));
		}
		
		stmt.close();
		
	}
	
	public Monedero getMonedero(Usuario user) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET_MONEDERO);
		
		stmt.setString(1, user.getNombreUsuario());
		ResultSet rs = stmt.executeQuery();
		
		Monedero monedero = null;
		
		if(rs.next()){
			monedero = new Monedero();
		    monedero.setNombreUsuario(rs.getString("NombreUsuario"));
		    monedero.setPin(rs.getString("PIN"));
		    monedero.setSaldo(rs.getDouble("Saldo"));
		    monedero.setFechaCreacion(rs.getDate("FechaCreacion"));
		    monedero.setFechaCancelacion(rs.getDate("FechaCancelacion"));
		    monedero.setFechaBaja(rs.getDate("FechaBaja"));
		}
		
		return monedero;
		
		
		
	}
	
	
}
