package model.services;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import utils.MultipleResultHandler;
import utils.SingleResultHandler;
import utils.UpdateHandler;


public abstract class Service {
	
	DataSource dataSource;
	final String JNDI_NAME = "jdbc/ElBancoDB";
	//final String JNDI_NAME = "jdbc:mysql://yourserver:3306/yourdatabase?zeroDateTimeBehavior=convertToNull";
	
	Service() {
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
	
	boolean doTransaction(UpdateHandler handler){
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			if(handler.handle(conn)){
				conn.commit();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return rollBack(conn);
		}finally{
			closeConn(conn);
		}
		
		return rollBack(conn);
	}
	
	<T> T doSelect(SingleResultHandler<T> handler){
		
		Connection conn = null;
		T result = null;
		
		try {
			
			conn = dataSource.getConnection();
			result = handler.handle(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return result;
	}
	
	<T> List<T> doSelect(MultipleResultHandler<T> handler){
		
		Connection conn = null;
		List<T> results = null;
		
		try {
			
			conn = dataSource.getConnection();
			results = handler.handle(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return results;
	}
	

}
