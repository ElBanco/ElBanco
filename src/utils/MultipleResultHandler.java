package utils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MultipleResultHandler<T> {
	public abstract List<T> handle(Connection conn) throws SQLException;
	
}
