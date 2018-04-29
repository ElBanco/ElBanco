package utils;

import java.util.GregorianCalendar;

public class DatesHelper {
	
	private java.util.Date utilDate;
	private java.sql.Date sqlDate;
	
	public DatesHelper() {
		GregorianCalendar calendar = new GregorianCalendar();
		utilDate = calendar.getTime();
		sqlDate = new java.sql.Date(calendar.getTimeInMillis());
		
	}

	public java.util.Date getUtilDate() {
		return utilDate;
	}


	public java.sql.Date getSqlDate() {
		return sqlDate;
	}
	
	public java.util.Date obtenerFechaCaducidadUtil(int numYears){
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.YEAR, numYears);
		return calendar.getTime();
		
	}
	
	public java.sql.Date obtenerFechaCaducidadSql(int numYears){
		return new java.sql.Date(obtenerFechaCaducidadUtil(numYears).getTime());
		
	}	

}
