package model.services;

import java.sql.Connection;
import java.sql.SQLException;

import utils.RandomStringGenerator;
import utils.TLSEmail;

import model.beans.*;
import model.dao.*;
import model.services.UserUpdateResult.Error;

public class UserService extends Service{
	
	private final RandomStringGenerator passGenerator = new RandomStringGenerator(10, RandomStringGenerator.StringType.ALPHANUMERIC);
	
	
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
				return userDAO.getUserByUsername(username);
			}
		};
		
		return doSelect(handler);

	}
	
	public UpdateResult addNewUser(final Usuario newUser, final Double initialAmount){
		
		final String password = passGenerator.newString();
	
		Updater updater = new Updater() {
			
			@Override
			public UpdateResult update(Connection conn) throws SQLException {
				
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				CuentaDAO accountDAO = new CuentaDAO(conn);
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				
				if(userDAO.getUserByUsername(newUser.getNombreUsuario()) != null){
					return new UserUpdateResult(false, Error.DUPLICATED_USER);
				}else if(newUser.getEmail() != null){
					return new UserUpdateResult(false, Error.DUPLICATED_EMAIL);
				}
				
				userDAO.addUser(newUser, password);
				
				Cuenta originalAccount = new Cuenta();
				originalAccount.setNombreUsuario(newUser.getNombreUsuario());
				originalAccount.setSaldo(initialAmount);
				accountDAO.addCuenta(originalAccount);
				
				Monedero monedero = new Monedero();
				monedero.setNombreUsuario(newUser.getNombreUsuario());
				monedero.setSaldo(0);
				cardDAO.addTarjeta(monedero);
				
				return new UpdateResult(true);
			}
		};
		
		if(doTransaction(handler)){
			TLSEmail.sendEmailNewUser(newUser.email, newUser.getNombreUsuario(), password);
			return true;
		}else{
			return false;
		}

	}
	
	

}
