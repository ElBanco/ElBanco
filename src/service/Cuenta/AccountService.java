package service.Cuenta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import model.beans.*;
import model.dao.*;
import service.*;

public class AccountService extends Service{
	
	public List<Cuenta> getAccounts(final Usuario user){
		
		MultipleResultRetriever<Cuenta>  retriever = new MultipleResultRetriever<Cuenta>() {
			
			@Override
			public List<Cuenta> retrieve(Connection conn) throws SQLException {
				CuentaDAO accountDAO = new CuentaDAO(conn);
				return accountDAO.listByUser(user);
			}
		};
		
		
		return doSelect(retriever);

	}

}
