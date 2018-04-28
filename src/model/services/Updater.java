package model.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface Updater {
	public abstract UpdateResult update(Connection conn) throws SQLException;
}
