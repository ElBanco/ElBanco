package model.dao;

import java.sql.Connection;

public abstract class DAO {

	Connection conn;
	
	public DAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
}
