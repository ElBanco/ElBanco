package service.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

import utils.RandomStringGenerator;
import utils.TLSEmail;

import model.beans.*;
import model.dao.*;
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
				
				if(userDAO.getUserByUsername(newUser.getNombreUsuario()) != null){
					result.setSuccessfulUpdate(false);
					((UserUpdateResult)result).setError(UserError.DUPLICATED_USER);
					return;
				}else if(userDAO.getUserByEmail(newUser.getEmail()) != null){
					result.setSuccessfulUpdate(false);
					((UserUpdateResult)result).setError(UserError.DUPLICATED_EMAIL);
					return;
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
				
				result.setSuccessfulUpdate(true);
				
				return;
			}
		};
		
		UserUpdateResult result = new UserUpdateResult();
		doTransaction(updater, result);
		
		if(result.isSuccessfulUpdate()){
			TLSEmail.sendEmailNewUser(newUser.email, newUser.getNombreUsuario(), password);
		}
		
		return result;

	}
	
	

}
