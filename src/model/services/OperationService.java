package model.services;

import java.sql.Connection;
import java.sql.SQLException;

import utils.UpdateHandler;

import model.beans.*;
import model.dao.*;

public class OperationService extends Service{
	
	public boolean doTransference(final String sourceAccountNumber, final String destinationAccountNumber, final double amount){
	
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				
				CuentaDAO accountDAO = new CuentaDAO(conn);
				OperacionDAO opDAO = new OperacionDAO(conn);
				
				Cuenta sourceAccount = accountDAO.getCuenta(sourceAccountNumber);
				Cuenta destinationAccount = accountDAO.getCuenta(destinationAccountNumber);
				
				if(sourceAccount == null || destinationAccount == null) return false;
				
				Double lowerLimit = sourceAccount.getLimiteInferior();
				Double balance = sourceAccount.getSaldo();
				Double dailyLimit = sourceAccount.getLimiteDiario();
				
				boolean allowedTransference = true;
				
				if(lowerLimit != null){
					if(lowerLimit > (balance - amount)) allowedTransference = false;
				}else{
					if((balance - amount) < 0) allowedTransference = false;
				}
				
				if(dailyLimit != null &&
						(opDAO.getDailyTranferenceSum(sourceAccount) + amount) > dailyLimit){
					allowedTransference = false;
				}
				
				if(allowedTransference){
					
					Transferencia transf = new Transferencia();
					transf.setCantidad(amount);
					transf.setNumeroCuentaOrigen(sourceAccountNumber);
					transf.setNumeroCuentaDestino(destinationAccountNumber);
					opDAO.addOp(transf);
					
					accountDAO.updateBalance(sourceAccount, sourceAccount.getSaldo() - amount);
					accountDAO.updateBalance(destinationAccount, destinationAccount.getSaldo() + amount);
					
					return true;
				}
				
				return false;
			}
		};
		
		return doTransaction(handler);
	}
	
	public boolean updateMonedero(final Usuario user, final double amount){
		
		UpdateHandler updateHandler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				OperacionDAO opDAO = new OperacionDAO(conn);
				
				Monedero monedero = cardDAO.getMonedero(user);
				cardDAO.updateMonedero(monedero, monedero.getSaldo() + amount);
				
				UpdateMonedero updateMonedero = new UpdateMonedero();
				updateMonedero.setCantidad(amount);
				updateMonedero.setNumeroTarjeta(monedero.getNumeroTarjeta());
				opDAO.addOp(updateMonedero);
				
				return true;
			}
		};
		
		return doTransaction(updateHandler);
	}
}
