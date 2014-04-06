import javax.jws.WebService;

@WebService
public class CustomerWebService {
	public String getPassword(String nameCustomer) {
		Customer customer = CustomerDAO.read(nameCustomer);
		if (customer != null) {
			return customer.getPassword();
		}
		else {
			return "Not found";
		}
	}
}
