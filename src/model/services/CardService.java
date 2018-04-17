package model.services;

import java.sql.Connection;
import java.sql.SQLException;

import utils.SingleResultHandler;
import utils.UpdateHandler;

import model.beans.*;
import model.dao.*;

public class CardService extends Service{
	
	public Monedero getMonedero(final Usuario user){
		
		SingleResultHandler<Tarjeta> handler = new SingleResultHandler<Tarjeta>() {
			
			@Override
			public Tarjeta handle(Connection conn) throws SQLException {
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				return cardDAO.getMonedero(user);
			}
		};
		
		return (Monedero) doSelect(handler);
	}
	
	public TarjetaDebito getDebitCard(final Cuenta account){
		
		SingleResultHandler<Tarjeta> handler = new SingleResultHandler<Tarjeta>() {

			@Override
			public Tarjeta handle(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);

				return cardDAO.getDebitCard(account);
			}
		};
		
		return (TarjetaDebito) doSelect(handler);
	}
	
	public boolean addNewMonedero(final Monedero newMonedero){
		
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				
				UsuarioDAO userDAO = new UsuarioDAO(conn);
				
				if(userDAO.getUser(newMonedero.getNombreUsuario()) != null){
					
					TarjetaDAO cardDAO = new TarjetaDAO(conn);
					newMonedero.setPin("6666");
					cardDAO.addTarjeta(newMonedero);
				}

				return true;
			}
		};
		
		return doTransaction(handler);
	}
	
	public boolean addNewDebitCard(final TarjetaDebito newDebitCard){
		
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				
				CuentaDAO accountDAO = new CuentaDAO(conn);
				
				if(accountDAO.getCuenta(newDebitCard.getNumeroCuenta()) != null){
					
					TarjetaDAO cardDAO = new TarjetaDAO(conn);
					newDebitCard.setPin("6666");
					cardDAO.addTarjeta(newDebitCard);
				}

				return true;
			}
		};
		
		return doTransaction(handler);
	}

	public boolean darBajaTarjeta(final int numeroTarjeta) {
		
		
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);

				return cardDAO.darBajaTarjeta(numeroTarjeta);
			}
		};
		
		return doTransaction(handler);
	}

	public boolean cambiarLimiteMonederoSuperior(final String nombreUsuario,
			final Double limiteSuperior) {
		
		UpdateHandler handler = new UpdateHandler() {
			
			@Override
			public boolean handle(Connection conn) throws SQLException {
				
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				
				return cardDAO.cambiarLimiteMonederoSuperior(nombreUsuario, limiteSuperior);
			}
		};
		return doTransaction(handler);
	}

	public boolean cambiarLimiteDebitoSuperior(final int numeroTarjeta, 
			final Double limiteSuperior) {
		
		UpdateHandler handler = new UpdateHandler() {
		
		@Override
		public boolean handle(Connection conn) throws SQLException {
			
			TarjetaDAO cardDAO = new TarjetaDAO(conn);
			
			return cardDAO.cambiarLimiteDebitoSuperior(numeroTarjeta, limiteSuperior);
			}
		};
		return doTransaction(handler);
	}

	public boolean cambiarLimiteDebitoDiario(final int numeroTarjeta, final Double limiteDiario) {
		
		UpdateHandler handler = new UpdateHandler() {
		
		@Override
		public boolean handle(Connection conn) throws SQLException {
			
			TarjetaDAO cardDAO = new TarjetaDAO(conn);
			
			return cardDAO.cambiarLimiteDebitoDiario(numeroTarjeta, limiteDiario);
			}
		};
		return doTransaction(handler);
	}
}
