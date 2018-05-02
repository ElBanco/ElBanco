package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import model.beans.*;

public class OperacionDAO extends DAO{
	
	
	public OperacionDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private final String INSERT_TRANSFERENCIA = "INSERT INTO Operacion (" +
			"Cantidad, " + 
			"NumeroCuentaOrigen, " +
			"NumeroCuentaDestino, " +
			"Fecha, " +
			"Discriminador) " +
			"VALUES (?, ?, ?, ?, 'Transfer');";
	
	private final String INSERT_UPDATE_MONEDERO = "INSERT INTO Operacion (" +
			"Cantidad, " + 
			"NumeroTarjeta, " +
			"Fecha, " +
			"Discriminador) " +
			"VALUES (?, ?, ?, 'UpdateMonedero');";
	
	private final String DAILY_TRANSFERENCES_SUM = "SELECT SUM(Cantidad) FROM Operacion WHERE NumeroCuentaOrigen=? AND DATE(Fecha)=CURDATE();";
	
	private final String LIST_TRANSFERENCES = "SELECT * FROM Operacion WHERE (NumeroCuentaOrigen=? OR NumeroCuentaDestino=?);";
	
	private final String LIST_UPDATES_MONEDERO = "SELECT * FROM Operacion WHERE NumeroTarjeta=?;";
	
	
	@Override
	void processRow(Object bean, ResultSet result) throws SQLException {
		
		if(bean instanceof Transferencia){
			
			Transferencia t = (Transferencia) bean;
			t.setCantidad(result.getDouble("Cantidad"));
			t.setFecha(result.getDate("Fecha"));
			t.setNumeroCuentaDestino(result.getString("NumeroCuentaDestino"));
			t.setNumeroCuentaOrigen(result.getString("NumeroCuentaOrigen"));
			t.setOperacionID(result.getInt("OperacionID"));
			
		}else if(bean instanceof UpdateMonedero){
			
			UpdateMonedero u = (UpdateMonedero) bean;
			u.setCantidad(result.getDouble("Cantidad"));
			u.setFecha(result.getDate("Fecha"));
			u.setNumeroTarjeta(result.getString("numeroTarjeta"));
			
		}
		
	}

	public void addOp(Operacion op) throws SQLException{
		
		PreparedStatement stmt;
		GregorianCalendar calendar = new GregorianCalendar();
		java.util.Date utilDate = calendar.getTime();
		op.setFecha(utilDate);
		java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
		
		if (op instanceof Transferencia){
			stmt = conn.prepareStatement(INSERT_TRANSFERENCIA, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, op.getCantidad());
			stmt.setString(2, ((Transferencia) op).getNumeroCuentaOrigen());
			stmt.setString(3, ((Transferencia) op).getNumeroCuentaDestino());
			stmt.setDate(4, sqlDate);
		}else if(op instanceof UpdateMonedero){
			stmt = conn.prepareStatement(INSERT_UPDATE_MONEDERO, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, op.getCantidad());
			stmt.setString(2, ((UpdateMonedero) op).getNumeroTarjeta());
			stmt.setDate(3, sqlDate);
		}else{
			return;
		}
		
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
		    op.setOperacionID(rs.getInt(1));
		}
		
		stmt.close();
		
	}
	
	public double getDailyTranferenceSum(String numeroCuenta) throws SQLException{
		
		PreparedStatement stmt = conn.prepareStatement(DAILY_TRANSFERENCES_SUM);
		stmt.setString(1, numeroCuenta);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()){
			return rs.getDouble(1);
		}
		
		return 0;
	}
	
	public List<Transferencia> listTransferences(String numeroCuenta) throws SQLException{
		
		List<Transferencia> listTransfers = new ArrayList<Transferencia>();
		PreparedStatement stmt = conn.prepareStatement(LIST_TRANSFERENCES);
		stmt.setString(1, numeroCuenta);
		stmt.setString(2, numeroCuenta);
		ResultSet rs = stmt.executeQuery();
		
		Transferencia t;
		while(rs.next()){
			t = new Transferencia();
			processRow(t, rs);
			listTransfers.add(t);
		}
		
		return listTransfers;
		
	}
	
	public List<UpdateMonedero> listUpdatesMonedero(String numeroTarjeta) throws SQLException{
		
		List<UpdateMonedero> listUpdates = new ArrayList<UpdateMonedero>();
		PreparedStatement stmt = conn.prepareStatement(LIST_UPDATES_MONEDERO);
		stmt.setString(1, numeroTarjeta);
		ResultSet rs = stmt.executeQuery();
		
		UpdateMonedero u;
		while(rs.next()){
			u = new UpdateMonedero();
			processRow(u, rs);
			listUpdates.add(u);
		}
		
		return listUpdates;
		
	}
	
}
