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
import service.Tarjeta.CardService;
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
		List<Cuenta> cuentas = new AccountService().getAccounts(user.getNombreUsuario());
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
		List<Cuenta> cuentas = new AccountService().getAccounts(user.getNombreUsuario());
		String numeroCuentaOrigen = cuentas.get(0).getNumeroCuenta();
		
		//if(req.getParameter("numeroCuentaDestino") != null && req.getParameter("cantidad") != null){
			//cantidad = Double.valueOf(req.getParameter("cantidad"));
			//numeroCuentaDestino = req.getParameter("numeroCuentaDestino");
			//OperationUpdateResult result = new OperationService().doTransference(numeroCuentaOrigen, numeroCuentaDestino, cantidad);
			//handleTransference(result, resp);
		//}
		
		String actionValue = req.getParameter("action");
		
		if(actionValue.equals("cambiarLimiteCuentaInferior")){
						
			System.out.println("cambiarLimiteCuentaInferior");
			String numeroCuenta = req.getParameter("numeroCuenta");
			Double limiteInferior = Double.parseDouble(req.getParameter("limiteInferior"));
			
			new AccountService().cambiarLimiteInferior(numeroCuenta, limiteInferior);
			
		}else if(actionValue.equals("cambiarLimiteCuentaDiario")){
 			 		
			String numeroCuenta = req.getParameter("numeroCuenta");
			Double limiteDiario = Double.parseDouble(req.getParameter("limiteDiario"));
			
			new AccountService().cambiarLimiteDiario(numeroCuenta, limiteDiario);
			
		}else if(actionValue.equals("cambiarLimiteMonedero")){
			
			String nombreUsuario = req.getParameter("nombreUsuario");
			Double limiteSuperior = Double.parseDouble(req.getParameter("limiteSuperior"));
			
			new CardService().cambiarLimiteMonederoSuperior(nombreUsuario, limiteSuperior);
			
		}else if(actionValue.equals("cambiarLimiteDebitoSuperior")){
			
			String numeroTarjeta = req.getParameter("numeroTarjeta");
			Double limiteSuperior = Double.parseDouble(req.getParameter("limiteSuperior"));
			
			new CardService().cambiarLimiteDebitoSuperior(numeroTarjeta, limiteSuperior);
			
		}else if(actionValue.equals("cambiarLimiteDebitoDiario")){
			
			String numeroTarjeta = req.getParameter("numeroTarjeta");
			Double limiteDiario = Double.parseDouble(req.getParameter("limiteDiario"));
			
			new CardService().cambiarLimiteDebitoDiario(numeroTarjeta, limiteDiario);
		}

		
		
		doGet(req, resp);
	}

	
}
