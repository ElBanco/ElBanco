package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.beans.*;

public class DBInteractions {
	
	private DataSource dataSource;
	public static final String JNDI_NAME = "jdbc/ElBancoDB";
	
	public DBInteractions() {
	    try {
	        dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + JNDI_NAME);
	    } catch (NamingException e) {
	        // Handle error that it's not configured in JNDI.
	        throw new IllegalStateException(JNDI_NAME + " is missing in JNDI!", e);
	    }
	}
	
	private boolean rollBack(Connection conn){
		
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	
	private void closeConn(Connection conn){
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Usuario authenticate(String username, String password){
		
		
		Connection conn = null;
		Usuario user = null;
		
		try {
			
			conn = dataSource.getConnection();
			UsuarioDAO userDAO = new UsuarioDAO(conn);
			user = userDAO.authenticate(username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return user;
		
	}
	
	public boolean addNewUser(Usuario newUser, Double initialAmount){
		
		Connection conn = null;
		
		try {
			
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			UsuarioDAO userDAO = new UsuarioDAO(conn);
			CuentaDAO accountDAO = new CuentaDAO(conn);
			TarjetaDAO cardDAO = new TarjetaDAO(conn);
			
			userDAO.addUser(newUser);
			
			Cuenta originalAccount = new Cuenta();
			originalAccount.setNombreUsuario(newUser.getNombreUsuario());
			originalAccount.setSaldo(initialAmount);
			accountDAO.addCuenta(originalAccount);
			
			Monedero monedero = new Monedero();
			monedero.setNombreUsuario(newUser.getNombreUsuario());
			monedero.setPin("6666"); // cambiar a número aleatorio
			monedero.setSaldo(0);
			cardDAO.addTarjeta(monedero);
			
			conn.commit();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return rollBack(conn);
		}finally{
			closeConn(conn);
		}

	}
	
	public Usuario getUser(String username){
		
		Connection conn = null;
		Usuario user = null;
		
		try {
			
			conn = dataSource.getConnection();
			UsuarioDAO userDAO = new UsuarioDAO(conn);
			user = userDAO.getUser(username);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return user;

	}
	
	public List<Cuenta> getAccounts(Usuario user){
		
		Connection conn = null;
		List<Cuenta> accounts = new ArrayList<Cuenta>();
		
		try {
			
			conn = dataSource.getConnection();
			CuentaDAO accountDAO = new CuentaDAO(conn);
			accounts = accountDAO.listByUser(user);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return accounts;

	}

	public Monedero getMonedero(Usuario user){
		
		Connection conn = null;
		Monedero monedero = null;
		
		try {
			
			conn = dataSource.getConnection();
			TarjetaDAO cardDAO = new TarjetaDAO(conn);
			monedero = cardDAO.getMonedero(user);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return monedero;

	}
	
	
	public boolean doTransference(int sourceAccountNumber, int destinationAccountNumber, double amount){
		
		Connection conn = null;
		
		try {
			
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			CuentaDAO accountDAO = new CuentaDAO(conn);
			OperacionDAO opDAO = new OperacionDAO(conn);
			
			Cuenta sourceAccount = accountDAO.getCuenta(sourceAccountNumber);
			Cuenta destinationAccount = accountDAO.getCuenta(destinationAccountNumber);
			
			if(sourceAccount == null || destinationAccount == null) return false;
			
			Double lowerLimit = sourceAccount.getLimiteInferior();
			Double balance = sourceAccount.getSaldo();
			Double dailyLimit = sourceAccount.getLimiteDiario();
			
			boolean allowedTransference = true;
			
			if(lowerLimit != null){
				if(lowerLimit > (balance - amount)) allowedTransference = false;
			}else{
				if((balance - amount) < 0) allowedTransference = false;
			}
			
			if(dailyLimit != null &&
					(opDAO.getDailyTranferenceSum(sourceAccount) + amount) > dailyLimit){
				allowedTransference = false;
			}
			
			if(allowedTransference){
				
				Transferencia transf = new Transferencia();
				transf.setCantidad(amount);
				transf.setNumeroCuentaOrigen(sourceAccountNumber);
				transf.setNumeroCuentaDestino(destinationAccountNumber);
				opDAO.addOp(transf);
				
				accountDAO.updateBalance(sourceAccount, sourceAccount.getSaldo() - amount);
				accountDAO.updateBalance(destinationAccount, destinationAccount.getSaldo() + amount);
				
				
				conn.commit();
				return true;
			}
			
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return rollBack(conn);
		}finally{
			closeConn(conn);
		}
	}


}
