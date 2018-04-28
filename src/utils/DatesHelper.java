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

}
