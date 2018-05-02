package service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MultipleResultRetriever<T> {
	public abstract List<T> retrieve(Connection conn) throws SQLException;
	
}
