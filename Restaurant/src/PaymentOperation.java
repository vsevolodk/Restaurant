

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentOperation
 */
@WebServlet("/paymentoperation")
public class PaymentOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String idOrder = req.getParameter("idOrder");
			String costOrder = req.getParameter("costOrder");
			String customerName = req.getParameter("customerName");
			Customer customer = CustomerDAO.read(customerName);
			Payment payment = new Payment();
			payment.setCost(Integer.parseInt(costOrder));
			payment.setCustomer(customer);
			Order order = OrderDAO.read(Integer.parseInt(idOrder));
			payment.setOrder(order);
			PrintWriter out = resp.getWriter();
			PaymentDAO.create(payment);
			out.println("<h1>Payed</h1>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
