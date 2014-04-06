import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

/**
 * Servlet implementation class MainPage
 */
@WebServlet(name = "userwork", urlPatterns = {"/userwork"})
public class UserWork extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			Customer user = CustomerDAO.read(req.getParameter("customerName"));
			
			if (user != null && user.getPassword().equals(req.getParameter("password"))) {	
				Session ses = new Session();
				req.setAttribute("customerName", req.getAttribute("customerName"));
				ses.service(req, resp);			
			}
			else {
				out.println("<h1>Sorry, wrong login or password</h1>");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
