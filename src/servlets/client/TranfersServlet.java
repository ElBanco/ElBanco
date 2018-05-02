package servlets.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Operacion.OperationService;

import model.beans.Transferencia;

@WebServlet("/client/transferencias")
public class TranfersServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String numeroCuenta = req.getParameter("numeroCuenta");
		List<Transferencia> transfers = new ArrayList<Transferencia>();
		
		if(numeroCuenta != null){
			transfers = new OperationService().listTransfers(numeroCuenta);
			req.setAttribute("transfers", transfers);
			req.getRequestDispatcher("/WEB-INF/views/transfers.jsp").forward(req, resp);
		}
		
	}
	
	

}
