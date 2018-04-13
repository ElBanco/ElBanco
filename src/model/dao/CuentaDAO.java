package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.DatesFactory;
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
	
	private final String UPDATE_SALDO = "UPDATE Cuenta SET Saldo=?, FechaModificacion=NOW() WHERE NumeroCuenta=?;";
	
	private final String GET = "SELECT * FROM Cuenta WHERE NumeroCuenta=?;"; 
	
	private final RandomStringGenerator stringGenerator = new RandomStringGenerator(9, RandomStringGenerator.StringType.NUMERIC);
	
	
	@Override
	void processRow(Object bean, ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

	public void addCuenta(Cuenta newAccount) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		DatesFactory datesFactory = new DatesFactory();
		String accountNumber = "";
		boolean validAccountNumber = false;
		
		while(!validAccountNumber){
			accountNumber = stringGenerator.newString();
			validAccountNumber = checkPrimaryKey(accountNumber, GET);
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
	
	public List<Cuenta> listByUser(Usuario user) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(LIST_BY_USER);
		List<Cuenta> accounts = new ArrayList<Cuenta>();
		
		stmt.setString(1, user.getNombreUsuario());
		ResultSet rs = stmt.executeQuery();
		
		Cuenta account;
		while(rs.next()){
			account = new Cuenta();
			account.setNumeroCuenta(rs.getString("NumeroCuenta"));
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
		stmt.setDouble(1, balance);
		stmt.setString(2, account.getNumeroCuenta());
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
			account.setNumeroCuenta(rs.getString("NumeroCuenta"));
			account.setNombreUsuario(rs.getString("NombreUsuario"));
			account.setSaldo(rs.getDouble("Saldo"));
			account.setFechaCreacion(rs.getDate("FechaCreacion"));
			account.setFechaModificacion(rs.getDate("FechaModificacion"));
			account.setFechaBaja(rs.getDate("FechaBaja"));
		}
		
		return account;
		
	}
	

	
}
