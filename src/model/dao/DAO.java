package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {

	Connection conn;
	
	public DAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	abstract void processRow(Object bean, ResultSet result) throws SQLException;
	
	boolean checkPrimaryKey(String key, String getQuery) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(getQuery);
	    ps.setString(1,key);
	    ResultSet rs = ps.executeQuery();
	    return !rs.next();
	}
	
}
