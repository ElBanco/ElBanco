package model.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import model.beans.*;
import model.dao.*;

public class AccountService extends Service{
	
	public List<Cuenta> getAccounts(final Usuario user){
		
		MultipleResultHandler<Cuenta>  handler = new MultipleResultHandler<Cuenta>() {
			
			@Override
			public List<Cuenta> handle(Connection conn) throws SQLException {
				CuentaDAO accountDAO = new CuentaDAO(conn);
				return accountDAO.listByUser(user);
			}
		};
		
		
		return doSelect(handler);

	}

}
