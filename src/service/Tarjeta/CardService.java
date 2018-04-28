package service.Tarjeta;

import java.sql.Connection;
import java.sql.SQLException;

import service.SingleResultRetriever;


import model.beans.*;
import model.dao.*;
import service.*;

public class CardService extends Service{
	
	public Monedero getMonedero(final Usuario user){
		
		SingleResultRetriever<Tarjeta> retriever = new SingleResultRetriever<Tarjeta>() {
			
			@Override
			public Tarjeta retrieve(Connection conn) throws SQLException {
				TarjetaDAO cardDAO = new TarjetaDAO(conn);
				return cardDAO.getMonedero(user);
			}
		};
		
		return (Monedero) doSelect(retriever);

	}
	

}
