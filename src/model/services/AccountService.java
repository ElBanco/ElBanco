package model.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import utils.MultipleResultHandler;
import utils.SingleResultHandler;
import utils.UpdateHandler;

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

	//Enlaza un usuario a una cuenta
	public boolean bindAccount(final String nombreUsuario, final int numeroCuenta){
		
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				CuentaDAO accountDAO = new CuentaDAO(conn);
				
				Usuario user = userDAO.getUser(nombreUsuario);
				Cuenta account = accountDAO.getCuenta(numeroCuenta);
				
				if (user != null && account != null){ //Existe el usuario y la cuenta
					
					accountDAO.updateUser(user, account);
				}else{
					return false;
				}
				return true;
			}
		};
		return doTransaction(handler);
	}
	
	public boolean addNewAccount(final Cuenta newAccount){
		
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				
				UsuarioDAO userDAO = new UsuarioDAO(conn);	
				
				if (userDAO.getUser(newAccount.getNombreUsuario()) != null){ //Existe el usuario
					
					CuentaDAO accountDAO = new CuentaDAO(conn);
					TarjetaDAO cardDAO = new TarjetaDAO(conn);
					
					accountDAO.addCuenta(newAccount);
				
					TarjetaDebito debitCard = new TarjetaDebito();
					debitCard.setPin("6666"); // cambiar a número aleatorio
					debitCard.setLimiteDiario(newAccount.getLimiteDiario());
					debitCard.setNumeroCuenta(newAccount.getNumeroCuenta());
					debitCard.setTitular(newAccount.getNombreUsuario());
					
					cardDAO.addTarjeta(debitCard);
				}else{
					return false;
				}
				return true;
			}
		};
		return doTransaction(handler);
	}

	public boolean darBajaCuenta(final int numeroCuenta) {
		/// TODO Auto-generated method stub
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				CuentaDAO accountDAO = new CuentaDAO(conn);
				Cuenta account = accountDAO.getCuenta(numeroCuenta);
				
				if (account != null) {
					accountDAO.darBaja(account);
				}else{
					return false;
				}
				return true;
			}
		};
		return doTransaction(handler);
		
	}

}
