package integration;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qa.service.repository.AccountRepository;

//@Produces annotation specifies the type of output this method (or web service) will produce. Need to return responses via json!
//@Path annotation specify the URL path on which this method will be invoked.

//@Path()
public class AccountEndPoint {
	
	@Inject
	private AccountRepository accountRepository;

	//@Path()
	@GET
	@Produces()
	public String getAllAccounts() {
		return accountRepository.findAllAccounts();
	}

	//@Path()
	@POST
	@Produces()
	public String addAccount(String account) {
		return accountRepository.createAnAccount(account);
	}

}
