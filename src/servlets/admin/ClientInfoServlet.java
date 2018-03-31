package servlets.admin;

import java.io.IOException;
import model.beans.*;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBInteractions;


@WebServlet("/admin/client_info")
public class ClientInfoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("nombreUsuario");
		Usuario client = new DBInteractions().getUser(username);

		if(client != null){
			List<Cuenta> cuentas = new DBInteractions().getAccounts(client);
			Monedero monedero = new DBInteractions().getMonedero(client);
			req.setAttribute("client", client);
			req.setAttribute("cuentas", cuentas);
			req.setAttribute("monedero", monedero);
			req.getRequestDispatcher("/WEB-INF/views/client_info.jsp").forward(req, resp);
		}else{
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Prueba</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("User Not Found");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
