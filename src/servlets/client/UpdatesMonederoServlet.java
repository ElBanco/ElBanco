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

import service.Operacion.OperationService;

import model.beans.Transferencia;
import model.beans.UpdateMonedero;
import model.beans.Usuario;

@WebServlet("/client/updates_monedero")
public class UpdatesMonederoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		List<UpdateMonedero> updates = new OperationService().listUpdates(user.getNombreUsuario());
		req.setAttribute("updates", updates);
		req.getRequestDispatcher("/WEB-INF/views/updates_monedero.jsp").forward(req, resp);
		
	}
	
	

}
