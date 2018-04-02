package model.services;

import java.sql.Connection;
import java.sql.SQLException;

import utils.SingleResultHandler;

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
	

}
