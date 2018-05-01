package service.Tarjeta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import service.SingleResultRetriever;
import service.UpdateResult;


import model.beans.*;
import model.dao.*;
import service.*;

public class CardService extends Service{
	
	public Monedero getMonedero(final String nombreUsuario){
		
		SingleResultRetriever<Tarjeta> retriever = new SingleResultRetriever<Tarjeta>() {
			
			@Override
			public Tarjeta retrieve(Connection conn) throws SQLException {
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				return cardDAO.getMonedero(nombreUsuario);
			}
		};
		
		return (Monedero) doSelect(retriever);

	}
	
	
	public List<TarjetaDebito> getDebitCards(final String numeroCuenta){
				
		MultipleResultRetriever<TarjetaDebito> retriever = new MultipleResultRetriever<TarjetaDebito>() {
	
			@Override
			public List<TarjetaDebito> retrieve(Connection conn) throws SQLException {
			// TODO Auto-generated method stub
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				return cardDAO.listDebitCardByAccount(numeroCuenta);
			}
		};
		
		return doSelect(retriever);
		
	}
	
	public UpdateResult addNewDebitCard(final TarjetaDebito newDebitCard){
				
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				// TODO Auto-generated method stub
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				cardDAO.addTarjeta(newDebitCard);
				result.setSuccessfulUpdate(true);

			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
		
	public UpdateResult darBajaTarjeta(final String numeroTarjeta) {
				
				
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
	
				if(cardDAO.darBajaTarjeta(numeroTarjeta)){
					result.setSuccessfulUpdate(true);
				}else{
					result.setSuccessfulUpdate(false);
				}
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
		
	public UpdateResult cambiarLimiteMonederoSuperior(final String nombreUsuario, final Double limiteSuperior) {
		
		Updater updater = new Updater() {
			
			@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				
				if(cardDAO.cambiarLimiteMonederoSuperior(nombreUsuario, limiteSuperior)){
					result.setSuccessfulUpdate(true);
				}else{
					result.setSuccessfulUpdate(false);
				}
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
		 	 
	public UpdateResult cambiarLimiteDebitoSuperior(final String numeroTarjeta, 
					final Double limiteSuperior) {
			
		Updater updater = new Updater() {
		
		@Override
		public void update(Connection conn, UpdateResult result) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				
				if(cardDAO.cambiarLimiteDebitoSuperior(numeroTarjeta, limiteSuperior)){
					result.setSuccessfulUpdate(true);
				}else{
					result.setSuccessfulUpdate(false);
				}
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
	
	public UpdateResult cambiarLimiteDebitoDiario(final String numeroTarjeta, final Double limiteDiario) {
				
		Updater updater = new Updater() {
		
		@Override
			public void update(Connection conn, UpdateResult result) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				
				if(cardDAO.cambiarLimiteDebitoDiario(numeroTarjeta, limiteDiario)){
					result.setSuccessfulUpdate(true);
				}else{
					result.setSuccessfulUpdate(false);
				}
			}
		};
		
		UpdateResult result = new UpdateResult();
		doTransaction(updater, result);
		
		return result;
	}
	

}
