package servlets.client;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import model.beans.*;
import model.services.AccountService;
import model.services.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/client/operaciones")
public class OpServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);
		
		List<Transferencia> ops2 = new OperationService().getOps(cuentas.get(0));
		
		//List<Operacion> ops2 = new ArrayList <Operacion>();
		for (int i = 1; i < cuentas.size(); i++) {
		    Cuenta element = cuentas.get(i);
		    ops2.addAll( new OperationService().getOps(element));
		}
		List<UpdateMonedero> opsMonedero = new OperationService().getOpsMonedero(user);
		
		
		req.setAttribute("userBean", user);
		req.setAttribute("cuentas", cuentas);
		req.setAttribute("transferencias", ops2);
		req.setAttribute("updatesMonedero", opsMonedero);
		req.getRequestDispatcher("/WEB-INF/views/operaciones.jsp").forward(req, resp);
	}
	
	
}
