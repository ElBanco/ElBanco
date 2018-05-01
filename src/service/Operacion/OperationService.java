package service.Operacion;

import java.sql.Connection;
import java.sql.SQLException;


import model.beans.*;
import model.dao.*;
import service.*;
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
				Double balance = sourceAccount.getSaldo();
				Double dailyLimit = sourceAccount.getLimiteDiario();
				
				
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
	
	public OperationUpdateResult updateMonedero(final String nombreUsuario, final double amount){
		
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				OperacionDAO opDAO = new OperacionDAO(conn);
				
				Monedero monedero = cardDAO.getMonedero(nombreUsuario);
				cardDAO.updateMonedero(nombreUsuario, monedero.getSaldo() + amount);
				
				UpdateMonedero updateMonedero = new UpdateMonedero();
				updateMonedero.setCantidad(amount);
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
}
