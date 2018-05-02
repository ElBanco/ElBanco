package utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface SingleResultHandler<T> {
	public abstract T handle(Connection conn) throws SQLException;
}
