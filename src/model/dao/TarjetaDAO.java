package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.DatesHelper;
import utils.RandomStringGenerator;
import model.beans.*;

public class TarjetaDAO extends DAO{

	public TarjetaDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private final String INSERT_MONEDERO = "INSERT INTO Tarjeta (" +
			"NumeroTarjeta, " +
			"PIN, " +
			"NombreUsuario, " +
			"Saldo, " +
			"FechaCreacion, " +
			"FechaModificacion, " +
			"Discriminador) " +
			"VALUES (?, ?, ?, ?, ?, ?,'Monedero');";
	
	private final String INSERT_TARJ_DEBITO= "INSERT INTO Tarjeta (" +
					"NumeroTarjeta, " +
					"PIN, " +
					"FechaCaducidad, " +
					"Titular, " +
					"FechaCreacion, " +
					"FechaModificacion, " +
					"NumeroCuenta, " +
					"Discriminador) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, 'TarjetaDebito');";
	
	private final String GET = "SELECT * FROM Tarjeta WHERE NumeroTarjeta=?;";
	
	private final String GET_MONEDERO = "SELECT * FROM Tarjeta WHERE NombreUsuario=?;";
	
	private final String GET_TARJETA = "SELECT * FROM Tarjeta WHERE NumeroTarjeta=?;";
	
	private final String GET_DEBIT_CARD = "SELECT NumeroTarjeta FROM Tarjeta WHERE NumeroCuenta=?;";
	
	private final String DAR_BAJA_TARJETA = "UPDATE Tarjeta SET FechaBaja=? WHERE NumeroTarjeta=?;";
	
	private final String CAMBIAR_LIMITE_MONEDERO = "UPDATE Tarjeta " +
			"SET LimiteSuperior=? " +
			"WHERE NombreUsuario=? AND Discriminador='Monedero;";

	private final String CAMBIAR_lIMITE_SUPERIOR_TARJETA = "UPDATE Tarjeta " +
			"SET LimiteSuperior=? " +
			"WHERE NumeroTarjeta=?;";

	private final String CAMBIAR_lIMITE_DIARIO_TARJETA = "UPDATE Tarjeta " +
			"SET LimiteDiario=? " +
			"WHERE NumeroTarjeta=?;";
	
	
	
	private final String UPDATE_SALDO_MONEDERO = "UPDATE Tarjeta SET Saldo=? WHERE NombreUsuario=?;";
	
	private final RandomStringGenerator cardNumberGenerator = new RandomStringGenerator(16, RandomStringGenerator.StringType.NUMERIC);
	
	private final RandomStringGenerator pinGenerator = new RandomStringGenerator(3, RandomStringGenerator.StringType.NUMERIC);
	
	
	@Override
	void processRow(Object bean, ResultSet rs) throws SQLException {
		
		if(bean instanceof Monedero){
			
			Monedero monedero = (Monedero) bean;
			monedero.setNumeroTarjeta(rs.getString("NumeroTarjeta"));
		    monedero.setNombreUsuario(rs.getString("NombreUsuario"));
		    monedero.setPin(rs.getString("PIN"));
		    monedero.setSaldo(rs.getDouble("Saldo"));
		    monedero.setFechaCreacion(rs.getDate("FechaCreacion"));
		    monedero.setFechaCancelacion(rs.getDate("FechaCancelacion"));
		    monedero.setFechaBaja(rs.getDate("FechaBaja"));
		    monedero.setLimiteSuperior(rs.getDouble("LimiteSuperior"));
		    
		}else if(bean instanceof TarjetaDebito){
			
			TarjetaDebito debitCard = (TarjetaDebito) bean;
			debitCard.setNumeroTarjeta(rs.getString("NumeroTarjeta"));
			debitCard.setPin(rs.getString("PIN"));
			debitCard.setFechaCreacion(rs.getDate("FechaCreacion"));
			debitCard.setFechaCancelacion(rs.getDate("FechaCancelacion"));
			debitCard.setFechaBaja(rs.getDate("FechaBaja"));
			debitCard.setFechaCaducidad(rs.getDate("FechaCaducidad"));
			debitCard.setLimiteDiario(rs.getDouble("LimiteDiario"));
			debitCard.setLimiteSuperior(rs.getDouble("LimiteSuperior"));
			debitCard.setNumeroCuenta(rs.getString("NumeroCuenta"));
			debitCard.setTitular(rs.getString("Titular"));
			
		}
		
	}
	
	
	public void addTarjeta(Tarjeta newCard) throws SQLException{
		
		PreparedStatement stmt;
		
		String cardNumber = "";
		boolean validCardNumber = false;
		
		while(!validCardNumber){
			cardNumber = cardNumberGenerator.newString();
			validCardNumber = checkUnique(cardNumber, GET);
		}
		System.out.println(cardNumber);
		
		String pin = pinGenerator.newString();
		System.out.println(pin);
		
		DatesHelper datesHelper = new DatesHelper();
		
		newCard.setNumeroTarjeta(cardNumber);
		newCard.setPin(pin);
		newCard.setFechaCreacion(datesHelper.getUtilDate());
		newCard.setFechaModificacion(datesHelper.getUtilDate());
		
		if(newCard instanceof Monedero){
			
			stmt = conn.prepareStatement(INSERT_MONEDERO, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cardNumber);
			stmt.setString(2, pin);
			stmt.setString(3, ((Monedero) newCard).getNombreUsuario());
			stmt.setDouble(4, ((Monedero) newCard).getSaldo());
			stmt.setDate(5, datesHelper.getSqlDate());
			stmt.setDate(6, datesHelper.getSqlDate());
			
		}else if(newCard instanceof TarjetaDebito){
			
			TarjetaDebito newDebitCard = (TarjetaDebito) newCard;
		    java.sql.Date fechaCaducidad = datesHelper.obtenerFechaCaducidadSql(2);
								
			stmt = conn.prepareStatement(INSERT_TARJ_DEBITO, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cardNumber);
			stmt.setString(2, pin);
			stmt.setDate(3, fechaCaducidad);
			stmt.setString(4, newDebitCard.getTitular());
			stmt.setDate(5, datesHelper.getSqlDate());
			stmt.setDate(6, datesHelper.getSqlDate());
			stmt.setString(7, newDebitCard.getNumeroCuenta());
			
		}else{
			return;
		}
		
		stmt.execute();
		stmt.close();
		
	}
	
	public Monedero getMonedero(String nombreUsuario) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET_MONEDERO);
		
		stmt.setString(1, nombreUsuario);
		ResultSet rs = stmt.executeQuery();
		
		Monedero monedero = null;
		
		if(rs.next()){
			monedero = new Monedero();
			processRow(monedero, rs);
		}
		
		return monedero;
		
	}
	
	public List<TarjetaDebito> listDebitCardByAccount(String numeroCuenta) throws SQLException{
				
		PreparedStatement stmt = conn.prepareStatement(GET_DEBIT_CARD);
		List<TarjetaDebito> debitCards = new ArrayList<TarjetaDebito>();
			 		
		stmt.setString(1, numeroCuenta);
		ResultSet rs = stmt.executeQuery();
 			 		
 		TarjetaDebito debitCard;		
		while(rs.next()){
			debitCard = new TarjetaDebito();
			processRow(debitCard, rs);
			debitCards.add(debitCard);
		}
		
		return debitCards;	
	}
	
	public void updateMonedero(String nombreUsuario, double saldo) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_SALDO_MONEDERO);
		stmt.setDouble(1, saldo);
		stmt.setString(2, nombreUsuario);
		stmt.executeUpdate();
		
	}
	
	
	public boolean darBajaTarjeta(final String numeroTarjeta) throws SQLException {
			
		DatesHelper datesHelper = new DatesHelper();
		PreparedStatement stmt = conn.prepareStatement(DAR_BAJA_TARJETA);
		stmt.setDate(1, datesHelper.getSqlDate());
		stmt.setString(2, numeroTarjeta);
		stmt.executeUpdate();
		stmt.close();
		
		return true;
	}
		
	public boolean cambiarLimiteMonederoSuperior(String nombreUsuario, Double limiteSuperior) throws SQLException {
	
		PreparedStatement stmt = conn.prepareStatement(CAMBIAR_LIMITE_MONEDERO);
		stmt.setDouble(1, limiteSuperior);
		stmt.setString(2, nombreUsuario);
		stmt.executeUpdate();
		stmt.close();
	
		return true;
	}
	
	public boolean cambiarLimiteDebitoSuperior(String numeroTarjeta,Double limiteSuperior) throws SQLException {
			
		if(checkUnique(numeroTarjeta, GET_TARJETA)){
		
			PreparedStatement stmt = conn.prepareStatement(CAMBIAR_lIMITE_SUPERIOR_TARJETA);
			stmt.setDouble(1, limiteSuperior);
			stmt.setString(2, numeroTarjeta);
			stmt.executeUpdate();
			stmt.close();
			
		}else{
			return false;
		}
		
		return true;
	}
				
	public boolean cambiarLimiteDebitoDiario(String numeroTarjeta, Double limiteDiario) throws SQLException {
			
		if(checkUnique(numeroTarjeta, GET_TARJETA)){
			
			PreparedStatement stmt = conn.prepareStatement(CAMBIAR_lIMITE_DIARIO_TARJETA);
			stmt.setDouble(1, limiteDiario);
			stmt.setString(2, numeroTarjeta);
			stmt.executeUpdate();
			stmt.close();
			
		}else{
			return false;
		}
		
		return true;
	}
	
	
}
