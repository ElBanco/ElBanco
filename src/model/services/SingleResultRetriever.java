package model.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface SingleResultRetriever<T> {
	public abstract T retrieve(Connection conn) throws SQLException;
}
