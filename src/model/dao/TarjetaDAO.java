package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DatesFactory;
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
			"Discriminador) " +
			"VALUES (?, ?, ?, ?, ?,'Monedero');";
	
	private final String GET = "SELECT * FROM Tarjeta WHERE NumeroTarjeta=?;";
	
	private final String GET_MONEDERO = "SELECT * FROM Tarjeta WHERE NombreUsuario=?;";
	
	private final String UPDATE_SALDO_MONEDERO = "UPDATE Tarjeta SET Saldo=? WHERE NombreUsuario=?;";
	
	private final RandomStringGenerator cardNumberGenerator = new RandomStringGenerator(16, RandomStringGenerator.StringType.NUMERIC);
	
	private final RandomStringGenerator pinGenerator = new RandomStringGenerator(3, RandomStringGenerator.StringType.NUMERIC);
	
	
	@Override
	void processRow(Object bean, ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public void addTarjeta(Tarjeta newCard) throws SQLException{
		
		PreparedStatement stmt;
		
		String cardNumber = "";
		boolean validAccountNumber = false;
		
		while(!validAccountNumber){
			cardNumber = cardNumberGenerator.newString();
			validAccountNumber = checkPrimaryKey(cardNumber, GET);
		}
		System.out.println(cardNumber);
		
		String pin = pinGenerator.newString();
		System.out.println(pin);
		
		DatesFactory datesFactory = new DatesFactory();
		
		newCard.setNumeroTarjeta(cardNumber);
		newCard.setPin(pin);
		newCard.setFechaCreacion(datesFactory.getUtilDate());
		
		if(newCard instanceof Monedero){
			stmt = conn.prepareStatement(INSERT_MONEDERO, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cardNumber);
			stmt.setString(2, pin);
			stmt.setString(3, ((Monedero) newCard).getNombreUsuario());
			stmt.setDouble(4, ((Monedero) newCard).getSaldo());
			stmt.setDate(5, datesFactory.getSqlDate());
		}else{
			return;
		}
		
		stmt.execute();
		stmt.close();
		
	}
	
	public Monedero getMonedero(Usuario user) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET_MONEDERO);
		
		stmt.setString(1, user.getNombreUsuario());
		ResultSet rs = stmt.executeQuery();
		
		Monedero monedero = null;
		
		if(rs.next()){
			monedero = new Monedero();
			monedero.setNumeroTarjeta(rs.getString("NumeroTarjeta"));
		    monedero.setNombreUsuario(rs.getString("NombreUsuario"));
		    monedero.setPin(rs.getString("PIN"));
		    monedero.setSaldo(rs.getDouble("Saldo"));
		    monedero.setFechaCreacion(rs.getDate("FechaCreacion"));
		    monedero.setFechaCancelacion(rs.getDate("FechaCancelacion"));
		    monedero.setFechaBaja(rs.getDate("FechaBaja"));
		}
		
		return monedero;
		
	}
	
	public void updateMonedero(Monedero monedero, double saldo) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_SALDO_MONEDERO);
		
		stmt.setDouble(1, saldo);
		stmt.setString(2, monedero.getNombreUsuario());
		
		monedero.setSaldo(saldo);
		
		stmt.executeUpdate();
		
	}

	
}
