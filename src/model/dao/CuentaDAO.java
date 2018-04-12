package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import model.beans.*;

public class CuentaDAO extends DAO{
	
	public CuentaDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}


	private final String INSERT_CUENTA = "INSERT INTO Cuenta (" +
											"NombreUsuario, " +
											"Saldo, " + 
											"LimiteDiario, " +
											"LimiteInferior, " +
											"FechaCreacion, " +
											"FechaModificacion) " +
											"VALUES (?, ?, ?, ?, ?, ?);";
	
	private final String LIST_BY_USER = "SELECT * FROM Cuenta WHERE NombreUsuario=?;";
	
	private final String UPDATE_SALDO = "UPDATE Cuenta SET Saldo=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final String GET = "SELECT * FROM Cuenta WHERE NumeroCuenta=?;";
	
	private final String UPDATE_USER = "UPDATE Cuenta SET NombreUsuario=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final String UPDATE_BAJA = "UPDATE Cuenta SET FechaBaja=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final String UPDATE_LIMITE_INFERIOR = "UPDATE Cuenta SET LimiteInferior=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final String UPDATE_LIMITE_DIARIO = "UPDATE Cuenta SET LimiteDiario=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	

	public void addCuenta(Cuenta newAccount) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(INSERT_CUENTA, Statement.RETURN_GENERATED_KEYS);
		GregorianCalendar calendar = new GregorianCalendar();
		java.sql.Date currentDate = new java.sql.Date(calendar.getTimeInMillis());
		
		stmt.setString(1, newAccount.getNombreUsuario());
		stmt.setString(2, String.valueOf(newAccount.getSaldo())); 
		stmt.setDouble(3, newAccount.getLimiteDiario());
		stmt.setDouble(4, newAccount.getLimiteInferior());
		stmt.setDate(5, currentDate);
		stmt.setDate(6, currentDate);
		
		newAccount.setFechaCreacion(currentDate);
		newAccount.setFechaModificacion(currentDate);
		
		stmt.executeUpdate();
		
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
		    newAccount.setNumeroCuenta(rs.getInt(1));
		}
		
		stmt.close();
		
	}
	
	
	public List<Cuenta> listByUser(Usuario user) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(LIST_BY_USER);
		List<Cuenta> accounts = new ArrayList<Cuenta>();
		
		stmt.setString(1, user.getNombreUsuario());
		ResultSet rs = stmt.executeQuery();
		
		Cuenta account;
		while(rs.next()){
			account = new Cuenta();
			account.setNumeroCuenta(rs.getInt("NumeroCuenta"));
			account.setNombreUsuario(rs.getString("NombreUsuario"));
			account.setSaldo(rs.getDouble("Saldo"));
			account.setFechaCreacion(rs.getDate("FechaCreacion"));
			account.setFechaModificacion(rs.getDate("FechaModificacion"));
			account.setFechaBaja(rs.getDate("FechaBaja"));
			accounts.add(account);
		}
		
	    stmt.close();
		
		return accounts;
		
	}
	
	public void updateBalance(Cuenta account, double balance) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_SALDO);
		GregorianCalendar calendar = new GregorianCalendar();
		java.sql.Date currentDate = new java.sql.Date(calendar.getTimeInMillis());
		
		stmt.setDouble(1, balance);
		stmt.setInt(2, account.getNumeroCuenta());
		stmt.setDate(3, currentDate);
		
		account.setSaldo(balance);
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	
	public Cuenta getCuenta(int id) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Cuenta account = null;
		
		if(rs.next()){
			account = new Cuenta();
			account.setNumeroCuenta(rs.getInt("NumeroCuenta"));
			account.setNombreUsuario(rs.getString("NombreUsuario"));
			account.setSaldo(rs.getDouble("Saldo"));
			account.setFechaCreacion(rs.getDate("FechaCreacion"));
			account.setFechaModificacion(rs.getDate("FechaModificacion"));
			account.setFechaBaja(rs.getDate("FechaBaja"));
		}
		
		return account;
		
	}
	
	public void updateUser(Usuario user, Cuenta account) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_USER);
		GregorianCalendar calendar = new GregorianCalendar();
		java.sql.Date currentDate = new java.sql.Date(calendar.getTimeInMillis());

		stmt.setString(1, user.getNombreUsuario());
		stmt.setDate(2, currentDate);
		stmt.setInt(3, account.getNumeroCuenta());
		
		stmt.executeUpdate();
		stmt.close();
	}

	public void darBaja(Cuenta account) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_BAJA);
		GregorianCalendar calendar = new GregorianCalendar();
		java.sql.Date currentDate = new java.sql.Date(calendar.getTimeInMillis());
		
		stmt.setDate(1, currentDate);
		stmt.setDate(2, currentDate); //Deberia cambiarse esto?
		stmt.setInt(3, account.getNumeroCuenta());
		
		stmt.executeUpdate();
		stmt.close();
	}


	public void cambiarLimiteInferior(Cuenta account, Double limiteInferior) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_LIMITE_INFERIOR);
		GregorianCalendar calendar = new GregorianCalendar();
		java.sql.Date currentDate = new java.sql.Date(calendar.getTimeInMillis());
		
		stmt.setDouble(1, limiteInferior);
		stmt.setDate(2, currentDate);
		stmt.setInt(3, account.getNumeroCuenta());
		
		stmt.executeUpdate();
		stmt.close();
	}


	public void cambiarLimiteDiario(Cuenta account, Double limiteDiario) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_LIMITE_DIARIO);
		GregorianCalendar calendar = new GregorianCalendar();
		java.sql.Date currentDate = new java.sql.Date(calendar.getTimeInMillis());
		
		stmt.setDouble(1, limiteDiario);
		stmt.setDate(2, currentDate);
		stmt.setInt(3, account.getNumeroCuenta());
		
		stmt.executeUpdate();
		stmt.close();
	}
	
}
