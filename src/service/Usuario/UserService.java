package service.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

import javax.mail.MessagingException;

import utils.RandomStringGenerator;
import utils.TLSEmail;

import model.beans.*;
import model.dao.*;
import model.dao.UsuarioDAO.AddUserCode;
import service.*;
import service.Usuario.UserUpdateResult.UserError;

public class UserService extends Service{
	
	private final RandomStringGenerator passGenerator = new RandomStringGenerator(10, RandomStringGenerator.StringType.ALPHANUMERIC);
	
	
	public Usuario authenticate(final String username, final String password){
		
		
		SingleResultRetriever<Usuario> retriever = new SingleResultRetriever<Usuario>() {
			@Override
			public Usuario retrieve(Connection conn) throws SQLException {
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				return userDAO.authenticate(username, password);
			}
		};
		
		return doSelect(retriever);
		
	}
	
	public Usuario getUser(final String username){
		
		SingleResultRetriever<Usuario> retriever = new SingleResultRetriever<Usuario>() {
			@Override
			public Usuario retrieve(Connection conn) throws SQLException {
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				return userDAO.getUserByUsername(username);
			}
		};
		
		return doSelect(retriever);

	}
	
	public UserUpdateResult addNewUser(final Usuario newUser, final Double initialAmount){
		
		final String password = passGenerator.newString();
		System.out.println(password);
	
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				CuentaDAO accountDAO = new CuentaDAO(conn);
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				
				
				AddUserCode code = userDAO.addUser(newUser, password);
				switch (code) {
					case DUPLICATE_USER:
						result.setSuccessfulUpdate(false);
						((UserUpdateResult)result).setError(UserError.DUPLICATED_USER);
						return;
					case DUPLICATE_EMAIL:
						result.setSuccessfulUpdate(false);
						((UserUpdateResult)result).setError(UserError.DUPLICATED_EMAIL);
						return;
	
					default:
						break;
				};
				
				Cuenta originalAccount = new Cuenta();
				originalAccount.setNombreUsuario(newUser.getNombreUsuario());
				originalAccount.setSaldo(initialAmount);
				accountDAO.addCuenta(originalAccount);
				
				Monedero monedero = new Monedero();
				monedero.setNombreUsuario(newUser.getNombreUsuario());
				monedero.setSaldo(0);
				cardDAO.addTarjeta(monedero);
				
				try {
					TLSEmail.sendEmailNewUser(newUser.email, newUser.getNombreUsuario(), password);
				} catch (MessagingException e) {
					result.setSuccessfulUpdate(false);
					((UserUpdateResult)result).setError(UserError.INVALID_EMAIL);
				return;
				}
				
				result.setSuccessfulUpdate(true);
				
			}
		};
		
		UserUpdateResult result = new UserUpdateResult();
		doTransaction(updater, result);
		
		return result;

	}
	
		public UserUpdateResult darBajaUser(final String nombreUsuario) {
				// TODO Auto-generated method stub
			Updater updater = new Updater() {
				
				@Override
				public void update(Connection conn, UpdateResult result) throws SQLException {
					// TODO Auto-generated method stub
					UsuarioDAO userDAO = new UsuarioDAO(conn);
	
					if (userDAO.darBaja(nombreUsuario)) {
						result.setSuccessfulUpdate(true);
					}else{
						result.setSuccessfulUpdate(false);
					}
					
				}
			};
			
			UserUpdateResult result = new UserUpdateResult();
			doTransaction(updater, result);
			
			return result;
		}
	
		
}
