package model.services;

import java.sql.Connection;
import java.sql.SQLException;

import utils.SingleResultHandler;
import utils.UpdateHandler;

import model.beans.*;
import model.dao.*;

public class UserService extends Service{
	
	
	public Usuario authenticate(final String username, final String password){
		
		
		SingleResultHandler<Usuario> handler = new SingleResultHandler<Usuario>() {
			@Override
			public Usuario handle(Connection conn) throws SQLException {
				conn = dataSource.getConnection();
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				return userDAO.authenticate(username, password);
			}
		};
		
		return doSelect(handler);
		
	}
	
	public Usuario getUser(final String username){
		
		SingleResultHandler<Usuario> handler = new SingleResultHandler<Usuario>() {
			@Override
			public Usuario handle(Connection conn) throws SQLException {
				conn = dataSource.getConnection();
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				return userDAO.getUser(username);
			}
		};
		
		return doSelect(handler);

	}
	
	public boolean addNewUser(final Usuario newUser, final Double initialAmount){
	
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				CuentaDAO accountDAO = new CuentaDAO(conn);
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				
				userDAO.addUser(newUser);
				
				Cuenta originalAccount = new Cuenta();
				originalAccount.setNombreUsuario(newUser.getNombreUsuario());
				originalAccount.setSaldo(initialAmount);
				accountDAO.addCuenta(originalAccount);
				
				Monedero monedero = new Monedero();
				monedero.setNombreUsuario(newUser.getNombreUsuario());
				monedero.setPin("6666"); // cambiar a número aleatorio
				monedero.setSaldo(0);
				cardDAO.addTarjeta(monedero);
				
				conn.commit();
				return true;
			}
		};
		
		return doTransaction(handler);

	}

}
