package utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface UpdateHandler {
	public abstract boolean handle(Connection conn) throws SQLException;
}
