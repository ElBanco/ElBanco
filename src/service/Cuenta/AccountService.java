package service.Cuenta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import model.beans.*;
import model.dao.*;
import service.*;

public class AccountService extends Service{
	
	public List<Cuenta> getAccounts(final String nombreUsuario){
		
		MultipleResultRetriever<Cuenta>  retriever = new MultipleResultRetriever<Cuenta>() {
			
			@Override
			public List<Cuenta> retrieve(Connection conn) throws SQLException {
				CuentaDAO accountDAO = new CuentaDAO(conn);
				return accountDAO.listByUser(nombreUsuario);
			}
		};
		
		
		return doSelect(retriever);
	}
	
		public UpdateResult addNewAccount(final Cuenta newAccount){
				
			Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				// TODO Auto-generated method stub
				
				CuentaDAO accountDAO = new CuentaDAO(conn);
				accountDAO.addCuenta(newAccount);
				result.setSuccessfulUpdate(true);
				
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
		
	public UpdateResult darBajaCuenta(final String numeroCuenta) {
				/// TODO Auto-generated method stub
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				// TODO Auto-generated method stub
				CuentaDAO accountDAO = new CuentaDAO(conn);
				accountDAO.darBaja(numeroCuenta);
				result.setSuccessfulUpdate(true);
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
		
	public UpdateResult cambiarLimiteInferior(final String numeroCuenta, final Double limiteInferior) {
		
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException  {
				
				CuentaDAO accountDAO = new CuentaDAO(conn);
				accountDAO.cambiarLimiteInferior(numeroCuenta, limiteInferior);
				result.setSuccessfulUpdate(true);
				
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
		
	}
		
	public UpdateResult cambiarLimiteDiario(final String numeroCuenta, final Double limiteDiario){
				
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				CuentaDAO accountDAO = new CuentaDAO(conn);
				accountDAO.cambiarLimiteDiario(numeroCuenta, limiteDiario);
				result.setSuccessfulUpdate(true);
				
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}

}
