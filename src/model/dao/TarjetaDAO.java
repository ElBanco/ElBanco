package model.dao;
//Arreglar lo de añadir tarjeta.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import utils.UpdateHandler;

import model.beans.*;

public class TarjetaDAO extends DAO{

	public TarjetaDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	private final String GET_TARJETA = "SELECT NumeroTarjeta, pin, FechaCreacion, FechaBaja, FechaCancelacion FROM Tarjeta WHERE NumeroTarjeta=?;";
	
	private final String INSERT_MONEDERO = "INSERT INTO Tarjeta (" +
			"PIN, " +
			"NombreUsuario, " +
			"Saldo, " +
			"LimiteSuperior, " +
			"FechaCreacion, " +
			"Discriminador) " +
			"VALUES (?, ?, ?, ?, ?, 'Monedero');";
	
	private final String GET_MONEDERO = "SELECT * FROM Tarjeta WHERE NombreUsuario=?;";
	
	private final String INSERT_TARJ_DEBITO= "INSERT INTO Tarjeta (" +
			"PIN, " +
			"FechaCaducidad, " +
			"Titular, " +
			"LimiteDiario, " +
			"FechaCreacion, " +
			"Discriminador, " +
			"NumeroCuenta, " +
			"LimiteSuperior) " +
			"VALUES (?, ?, ?, ?, ?, 'Tarjeta_Credito', ?, ?);";
	
	private final String GET_DEBIT_CARD = "SELECT NumeroTarjeta FROM Tarjeta WHERE NumeroCuenta=?;";

	private final String DAR_BAJA_TARJETA = "UPDATE Tarjeta SET FechaBaja=? WHERE NumeroTarjeta=?";
	
	public boolean existeTarjeta(final int numeroTarjeta) throws SQLException{
		
		PreparedStatement stmt;
		
		stmt = conn.prepareStatement(GET_TARJETA);
		stmt.setInt(1, numeroTarjeta);
		ResultSet rs = stmt.executeQuery();

		return rs.next();
	}
	
	public void addTarjeta(Tarjeta newCard) throws SQLException{
			
		PreparedStatement stmt;
		GregorianCalendar calendar = new GregorianCalendar();
		
		if(newCard instanceof Monedero){
			
			Monedero newMonederoCard = (Monedero) newCard;
			
			stmt = conn.prepareStatement(INSERT_MONEDERO, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newMonederoCard.getPin());
			stmt.setString(2, newMonederoCard.getNombreUsuario());
			stmt.setDouble(3, newMonederoCard.getSaldo()); 
			stmt.setDouble(4, newMonederoCard.getLimiteSuperior());
			stmt.setDate(5, new java.sql.Date(calendar.getTime().getTime()));
		}
		
		else if(newCard instanceof TarjetaDebito){
			
			TarjetaDebito newDebitCard = (TarjetaDebito) newCard;
			java.sql.Date fechaCaducidad = obtenerFechaCaducidad(calendar);
					
			stmt = conn.prepareStatement(INSERT_TARJ_DEBITO, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newDebitCard.getPin());
			stmt.setDate(2, fechaCaducidad);
			stmt.setString(3, newDebitCard.getTitular());
			stmt.setDouble(4, newDebitCard.getLimiteDiario()); 
			stmt.setDate(5, new java.sql.Date(calendar.getTime().getTime()));
			stmt.setInt(6,  newDebitCard.getNumeroCuenta());
			stmt.setDouble(7, newDebitCard.getLimiteSuperior());
			
			
		}
		else{
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
	
	public TarjetaDebito getDebitCard(Cuenta account) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET_DEBIT_CARD);
		
		stmt.setInt(1, account.getNumeroCuenta());
		ResultSet rs = stmt.executeQuery();
		
		TarjetaDebito debitCard = null;
		
		if(rs.next()){
			debitCard = new TarjetaDebito();
			debitCard.setNumeroTarjeta(rs.getInt("NumeroTarjeta"));
			debitCard.setPin(rs.getString("PIN"));
			debitCard.setFechaCreacion(rs.getDate("FechaCreacion"));
			debitCard.setFechaCancelacion(rs.getDate("FechaCancelacion"));
			debitCard.setFechaBaja(rs.getDate("FechaBaja"));
			debitCard.setFechaCaducidad(rs.getDate("FechaCaducidad"));
			debitCard.setLimiteDiario(rs.getDouble("LimiteDiario"));
			debitCard.setLimiteSuperior(rs.getDouble("LimiteSuperior"));
			debitCard.setNumeroCuenta(rs.getInt("NumeroCuenta"));
			debitCard.setTitular(rs.getString("Titular"));
		}
		
		return debitCard;	
	}
	
	public boolean darBajaTarjeta(final int numeroTarjeta) throws SQLException {
		
		if (existeTarjeta(numeroTarjeta)){
			
			GregorianCalendar calendar = new GregorianCalendar();
				
			PreparedStatement stmt = conn.prepareStatement(DAR_BAJA_TARJETA);
			stmt.setDate(1, new java.sql.Date(calendar.getTime().getTime()));
			stmt.setInt(2, numeroTarjeta);
			
			stmt.executeUpdate();
			stmt.close();
		}
		else{
			return false;
		}
		return true;
	}
	
	private java.sql.Date obtenerFechaCaducidad(GregorianCalendar gregCalendar){
		
		java.util.Date fechaCaducidad;
		gregCalendar.add(1, 2); //Añade 2 años
		fechaCaducidad = gregCalendar.getTime();
		java.sql.Date sqlFechaCaducidad = new java.sql.Date(fechaCaducidad.getTime());

		return sqlFechaCaducidad;
	}
	
}
