package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.DatesHelper;
import utils.RandomStringGenerator;

import model.beans.*;

public class CuentaDAO extends DAO{
	
	public CuentaDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}


	private final String INSERT = "INSERT INTO Cuenta (" +
											"NumeroCuenta, " +
											"NombreUsuario, " +
											"Saldo, " + 
											"FechaCreacion, " +
											"FechaModificacion) " +
											"VALUES (?, ?, ?, ?, ?);";
	
	private final String LIST_BY_USER = "SELECT * FROM Cuenta WHERE NombreUsuario=?;";
	
	private final String UPDATE_SALDO = "UPDATE Cuenta SET Saldo=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final String GET = "SELECT * FROM Cuenta WHERE NumeroCuenta=?;"; 
	
	private final String UPDATE_BAJA = "UPDATE Cuenta SET FechaBaja=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final String UPDATE_LIMITE_INFERIOR = "UPDATE Cuenta SET LimiteInferior=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final String UPDATE_LIMITE_DIARIO = "UPDATE Cuenta SET LimiteDiario=?, FechaModificacion=? WHERE NumeroCuenta=?;";
	
	private final RandomStringGenerator stringGenerator = new RandomStringGenerator(9, RandomStringGenerator.StringType.NUMERIC);
	
	
	@Override
	void processRow(Object bean, ResultSet rs) throws SQLException {
		
		if(bean instanceof Cuenta){
			Cuenta account = (Cuenta) bean;
			account.setNumeroCuenta(rs.getString("NumeroCuenta"));
			account.setNombreUsuario(rs.getString("NombreUsuario"));
			account.setSaldo(rs.getDouble("Saldo"));
			account.setFechaCreacion(rs.getDate("FechaCreacion"));
			account.setFechaModificacion(rs.getDate("FechaModificacion"));
			account.setFechaBaja(rs.getDate("FechaBaja"));
			account.setLimiteDiario(rs.getDouble("LimiteDiario"));
			account.setLimiteInferior(rs.getDouble("LimiteInferior"));
		}
		
	}
	

	public void addCuenta(Cuenta newAccount) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		DatesHelper datesFactory = new DatesHelper();
		String accountNumber = "";
		boolean validAccountNumber = false;
		
		while(!validAccountNumber){
			accountNumber = stringGenerator.newString();
			validAccountNumber = checkUnique(accountNumber, GET);
		}
		
		newAccount.setNumeroCuenta(accountNumber);
		System.out.println(accountNumber);
		newAccount.setFechaCreacion(datesFactory.getUtilDate());
		newAccount.setFechaModificacion(datesFactory.getUtilDate());
		
		stmt.setString(1, accountNumber);
		stmt.setString(2, newAccount.getNombreUsuario());
		stmt.setString(3, String.valueOf(newAccount.getSaldo()));
		stmt.setDate(4, datesFactory.getSqlDate());
		stmt.setDate(5, datesFactory.getSqlDate());
		
		stmt.execute();
		stmt.close();
		
	}
	
	public List<Cuenta> listByUser(String nombreUsuario) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(LIST_BY_USER);
		List<Cuenta> accounts = new ArrayList<Cuenta>();
		
		stmt.setString(1, nombreUsuario);
		ResultSet rs = stmt.executeQuery();
		
		Cuenta account;
		while(rs.next()){
			account = new Cuenta();
			processRow(account, rs);
			accounts.add(account);
		}
		
	    stmt.close();
		
		return accounts;
		
	}
	
	public void updateBalance(String numeroCuenta, double balance) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_SALDO);
		DatesHelper datesHelper = new DatesHelper();
		
		stmt.setDouble(1, balance);
		stmt.setDate(2, datesHelper.getSqlDate());
		stmt.setString(3, numeroCuenta);
		
		stmt.executeUpdate();
		stmt.close();
		
		
	}
	
	
	public Cuenta getCuenta(String id) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(GET);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		Cuenta account = null;
		
		if(rs.next()){
			account = new Cuenta();
			processRow(account, rs);
		}
		
		return account;
		
	}
	
	public void darBaja(String numeroCuenta) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(UPDATE_BAJA);
		DatesHelper datesHelper = new DatesHelper();
		
		stmt.setDate(1, datesHelper.getSqlDate());
		stmt.setDate(2, datesHelper.getSqlDate());
		stmt.setString(3, numeroCuenta);
		
		stmt.executeUpdate();
		stmt.close();
		
	}
	
	
	public void cambiarLimiteInferior(String numeroCuenta, Double limiteInferior) throws SQLException{
				
	    PreparedStatement stmt = conn.prepareStatement(UPDATE_LIMITE_INFERIOR);
	    DatesHelper datesHelper = new DatesHelper();
		
		stmt.setDouble(1, limiteInferior);
	    stmt.setDate(2, datesHelper.getSqlDate());
		stmt.setString(3, numeroCuenta);
		
		stmt.executeUpdate();
		stmt.close();
		
	}
	
	
	public void cambiarLimiteDiario(String numeroCuenta, Double limiteDiario) throws SQLException{
				
		PreparedStatement stmt = conn.prepareStatement(UPDATE_LIMITE_DIARIO);
		DatesHelper datesHelper = new DatesHelper();

		stmt.setDouble(1, limiteDiario);
		stmt.setDate(2, datesHelper.getSqlDate());
		stmt.setString(3, numeroCuenta);
	
		stmt.executeUpdate();
		stmt.close();
		
	}
	
	
}
