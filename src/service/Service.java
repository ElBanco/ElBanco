package service;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public abstract class Service {
	
	DataSource dataSource;
	final String JNDI_NAME = "jdbc/ElBancoDB";
	
	protected Service() {
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
	
	protected void doTransaction(Updater updater, UpdateResult result){
		
		Connection conn = null;
		
		try {
			
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			updater.update(conn, result);
			
			if(result.isSuccessfulUpdate()){
				conn.commit();
			}else{
				rollBack(conn);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			rollBack(conn);
			result.setSuccessfulUpdate(false);
			
		}finally{
			closeConn(conn);
		}
		
	}
	
	protected <T> T doSelect(SingleResultRetriever<T> retriever){
		
		Connection conn = null;
		T result = null;
		
		try {
			
			conn = dataSource.getConnection();
			result = retriever.retrieve(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return result;
	}
	
	protected <T> List<T> doSelect(MultipleResultRetriever<T> retriever){
		
		Connection conn = null;
		List<T> results = null;
		
		try {
			
			conn = dataSource.getConnection();
			results = retriever.retrieve(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConn(conn);
		}
		
		return results;
	}
	

}
