package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {

	Connection conn;
	
	public DAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	abstract void processRow(Object bean, ResultSet result) throws SQLException;
	
	
}
