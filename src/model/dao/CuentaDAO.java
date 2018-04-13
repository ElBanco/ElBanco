package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.beans.*;

public class CuentaDAO extends DAO{
	
	public CuentaDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}


	private final String INSERT = "INSERT INTO Cuenta (" +
											"NombreUsuario, " +
											"Saldo, " + 
											"FechaCreacion, " +
											"FechaModificacion) " +
											"VALUES (?, ?, NOW(), NOW());";
	
	private final String LIST_BY_USER = "SELECT * FROM Cuenta WHERE NombreUsuario=?;";
	
	private final String UPDATE_SALDO = "UPDATE Cuenta SET Saldo=?, FechaModificacion=NOW() WHERE NumeroCuenta=?;";
	
	private final String GET = "SELECT * FROM Cuenta WHERE NumeroCuenta=?;"; 
	
	
	@Override
	void processRow(Object bean, ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

	public void addCuenta(Cuenta newAccount) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, newAccount.getNombreUsuario());
		stmt.setString(2, String.valueOf(newAccount.getSaldo())); 
		
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
		stmt.setDouble(1, balance);
		stmt.setInt(2, account.getNumeroCuenta());
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

	
}
