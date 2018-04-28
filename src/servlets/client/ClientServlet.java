package servlets.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.beans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Cuenta.AccountService;
import service.Operacion.OperationService;
import service.Operacion.OperationUpdateResult;
import service.Usuario.UserUpdateResult;



@WebServlet("/client")
public class ClientServlet extends HttpServlet{
	
	private void handleTransference(OperationUpdateResult result, HttpServletResponse resp) throws IOException{
		
		PrintWriter out = resp.getWriter();
		
		if(result.isSuccessfulUpdate()){
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Prueba</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Valid Transfer");
			out.println("</body>");
			out.println("</html>");
			return;
		}
		
		
		switch (result.getError()) {
		
			case BALANCE:
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Prueba</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("BALANCE");
				out.println("</body>");
				out.println("</html>");
				break;
	
			case DAYLY_LIMIT:
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Prueba</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("DAYLY_LIMIT:");
				out.println("</body>");
				out.println("</html>");
				break;
				
			case LOWER_LIMIT:
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Prueba</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("LOWER_LIMIT:");
				out.println("</body>");
				out.println("</html>");
				break;
				
			case UNKOWN_ACCOUNT:
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Prueba</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("UNKOWN_ACCOUNT:");
				out.println("</body>");
				out.println("</html>");
				break;
				
			default:
				break;
			
		}
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);
		req.setAttribute("userBean", user);
		req.setAttribute("cuentas", cuentas);
		req.getRequestDispatcher("/WEB-INF/views/client.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Double cantidad;
		String numeroCuentaDestino;
		
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		List<Cuenta> cuentas = new AccountService().getAccounts(user);
		String numeroCuentaOrigen = cuentas.get(0).getNumeroCuenta();
		
		if(req.getParameter("numeroCuentaDestino") != null && req.getParameter("cantidad") != null){
			cantidad = Double.valueOf(req.getParameter("cantidad"));
			numeroCuentaDestino = req.getParameter("numeroCuentaDestino");
			OperationUpdateResult result = new OperationService().doTransference(numeroCuentaOrigen, numeroCuentaDestino, cantidad);
			handleTransference(result, resp);
		}
		
		//doGet(req, resp);
	}

	
}
