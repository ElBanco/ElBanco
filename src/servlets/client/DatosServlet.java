
package servlets.client;

import java.io.IOException;
import java.util.List;
import model.beans.*;
import model.services.AccountService;
import model.services.OperationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/client/cuentas")
public class DatosServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);
		req.setAttribute("userBean", user);
		req.getRequestDispatcher("/WEB-INF/views/datos.jsp").forward(req, resp);
	}
	
	
}
