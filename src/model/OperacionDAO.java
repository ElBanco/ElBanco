package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.beans.*;

public class OperacionDAO {
	
	Connection conn;

	OperacionDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	private final String INSERT_TRANSFERENCIA = "INSERT INTO Operacion (" +
			"Cantidad, " + 
			"NumeroCuentaOrigen, " +
			"NumeroCuentaDestino, " +
			"Fecha) " +
			"VALUES (?, ?, ?, NOW());";
	
	private final String DAILY_TRANSFERENCES_SUM = "SELECT SUM(Cantidad) FROM Operacion WHERE NumeroCuentaOrigen=? AND DATE(Fecha)=CURDATE();";

	
	void addOp(Operacion op) throws SQLException{
		
		PreparedStatement stmt;
		
		if (op instanceof Transferencia){
			stmt = conn.prepareStatement(INSERT_TRANSFERENCIA, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, op.getCantidad());
			stmt.setInt(2, ((Transferencia) op).getNumeroCuentaOrigen());
			stmt.setInt(3, ((Transferencia) op).getNumeroCuentaDestino());
		}else{
			// TODO
			return;
		}
		
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
		    op.setOperacionID(rs.getInt(1));
		}
		
		stmt.close();
		
	}
	
	double getDailyTranferenceSum(Cuenta account) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(DAILY_TRANSFERENCES_SUM);
		stmt.setInt(1, account.getNumeroCuenta());
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()){
			return rs.getDouble(1);
		}
		
		return 0;
	}
	
}
