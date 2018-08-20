package com.hcl.transactions.openBank.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hcl.transactions.exception.TechnicalException;
import com.hcl.transactions.rest.Transactions;

@Component
@PropertySource("classpath:application.properties")
public class OpenBankClient {
	
	@Autowired
	private RestTemplate restTemplate;
	

	 
	 public @Value("${open.bank.url}") String openBankUrl;
	

	
	/**
	 * 
	 * client to retrieve transaction details from OpenBank api
	 * 
	 * @return
	 */
	public Transactions getOpenBankTransactions() {

		Transactions transactionsResponse = null;

		try {
			
			transactionsResponse = restTemplate.getForObject(openBankUrl, Transactions.class);

		} catch (Exception e) {
			//log the error
			throw new TechnicalException(e.getMessage(), e);
		}
		return transactionsResponse;
	}

}
