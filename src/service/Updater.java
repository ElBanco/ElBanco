package service;

import java.sql.Connection;
import java.sql.SQLException;

public interface Updater {
	public abstract void update(Connection conn, UpdateResult result) throws SQLException;
}
