package service.Operacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import model.beans.*;
import model.dao.*;
import service.*;
import service.Cuenta.AccountService;
import service.Operacion.OperationUpdateResult.OperationError;

public class OperationService extends Service{
	
	public OperationUpdateResult doTransference(final String sourceAccountNumber, final String destinationAccountNumber, final double amount){
	
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				CuentaDAO accountDAO = new CuentaDAO(conn);
				OperacionDAO opDAO = new OperacionDAO(conn);
				
				Cuenta sourceAccount = accountDAO.getCuenta(sourceAccountNumber);
				Cuenta destinationAccount = accountDAO.getCuenta(destinationAccountNumber);
				
				if(sourceAccount == null || destinationAccount == null){
					result.setSuccessfulUpdate(false);
					((OperationUpdateResult)result).setError(OperationError.UNKOWN_ACCOUNT);
					return;
				}
				
				Double lowerLimit = sourceAccount.getLimiteInferior();
				System.out.println(lowerLimit);
				Double balance = sourceAccount.getSaldo();
				Double dailyLimit = sourceAccount.getLimiteDiario();
				System.out.println(dailyLimit);
				
				
				if(lowerLimit != null){
					if(lowerLimit > (balance - amount)){
						result.setSuccessfulUpdate(false);
						((OperationUpdateResult)result).setError(OperationError.LOWER_LIMIT);
						return;
					}
				}else{
					if((balance - amount) < 0){
						result.setSuccessfulUpdate(false);
						((OperationUpdateResult)result).setError(OperationError.BALANCE);
						return;
					}
				}
				
				if(dailyLimit != null &&
						(opDAO.getDailyTranferenceSum(sourceAccountNumber) + amount) > dailyLimit){
					result.setSuccessfulUpdate(false);
					((OperationUpdateResult)result).setError(OperationError.DAYLY_LIMIT);
					return;
				}
				
					
				Transferencia transf = new Transferencia();
				transf.setCantidad(amount);
				transf.setNumeroCuentaOrigen(sourceAccountNumber);
				transf.setNumeroCuentaDestino(destinationAccountNumber);
				opDAO.addOp(transf);
				
				accountDAO.updateBalance(sourceAccountNumber, sourceAccount.getSaldo() - amount);
				accountDAO.updateBalance(destinationAccountNumber, destinationAccount.getSaldo() + amount);
					
				result.setSuccessfulUpdate(true);
				return;
				
				
			}
		};
		
		OperationUpdateResult result = new OperationUpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
	
	public OperationUpdateResult updateMonedero(final String nombreUsuario, final double amount, final String numeroCuenta){
		
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				CuentaDAO acccountDAO = new CuentaDAO(conn);
				OperacionDAO opDAO = new OperacionDAO(conn);
				
				Monedero monedero = cardDAO.getMonedero(nombreUsuario);
				Cuenta cuenta = acccountDAO.getCuenta(numeroCuenta);
				
				if((cuenta.getSaldo() - amount) < 0){
					((OperationUpdateResult)result).setError(OperationError.BALANCE);
					result.setSuccessfulUpdate(false);
					return;
				}
				
				cardDAO.updateMonedero(nombreUsuario, monedero.getSaldo() + amount);
				acccountDAO.updateBalance(numeroCuenta, cuenta.getSaldo() - amount);
				
				UpdateMonedero updateMonedero = new UpdateMonedero();
				updateMonedero.setCantidad(amount);
				updateMonedero.setNumeroTarjeta(monedero.getNumeroTarjeta());
				updateMonedero.setNumeroTarjeta(monedero.getNumeroTarjeta());
				opDAO.addOp(updateMonedero);
				
				result.setSuccessfulUpdate(true);
				return;
			}
		};
		
		OperationUpdateResult result = new OperationUpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
	
	public List<Transferencia> listTransfers(final String numeroCuenta){
		
		MultipleResultRetriever<Transferencia> retriever = new MultipleResultRetriever<Transferencia>() {
			
			@Override
			public List<Transferencia> retrieve(Connection conn) throws SQLException {
				OperacionDAO operacionDAO = new OperacionDAO(conn);
				List<Transferencia> transfers = operacionDAO.listTransferences(numeroCuenta);
				return transfers;
			}
		};
		
		return doSelect(retriever);
	}
	
	public List<UpdateMonedero> listUpdates(final String nombreUsuario){
		
		MultipleResultRetriever<UpdateMonedero> retriever = new MultipleResultRetriever<UpdateMonedero>() {
			
			@Override
			public List<UpdateMonedero> retrieve(Connection conn) throws SQLException {
				OperacionDAO operacionDAO = new OperacionDAO(conn);
				TarjetaDAO tarjetaDAO = new TarjetaDAO(conn);
				Monedero monedero = tarjetaDAO.getMonedero(nombreUsuario);
				List<UpdateMonedero> updates = operacionDAO.listUpdatesMonedero(monedero.getNumeroTarjeta());
				return updates;
			}
		};
		
		return doSelect(retriever);
	}
}
