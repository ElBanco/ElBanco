package servlets.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.Monedero;
import model.beans.Transferencia;
import model.beans.Usuario;
import service.Operacion.OperationService;
import service.Tarjeta.CardService;

@WebServlet("/client/monedero")
public class MonederoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		Monedero monedero = new CardService().getMonedero(user.getNombreUsuario());
		req.setAttribute("monedero", monedero);
		req.getRequestDispatcher("/WEB-INF/views/monedero.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Double limiteSuperior = Double.valueOf(req.getParameter("limiteSuperior"));
		HttpSession session = req.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		new CardService().cambiarLimiteMonederoSuperior(user.getNombreUsuario(), limiteSuperior);
		resp.sendRedirect("/ElBanco/client/monedero");
	}
	
	

}
