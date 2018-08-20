package com.hcl.transactions.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcl.transactions.exception.TechnicalException;
import com.hcl.transactions.openBank.client.OpenBankClient;
import com.hcl.transactions.response.HclOpenBankResponse;
import com.hcl.transactions.rest.Transaction;
import com.hcl.transactions.rest.Transactions;
import com.hcl.transactions.service.HclTransactionService;

@Component
public class HclTransactionServiceImpl implements HclTransactionService{ 
	
	private static final Logger logger = Logger.getLogger(HclTransactionServiceImpl.class);
	
	@Autowired
	private OpenBankClient openBankClient;

	/* 
	 * invokes the open bank api to retrieve the tx list and maps it to hcl response bean
	 * 
	 * (non-Javadoc)
	 * @see com.hcl.transactions.service.HclTransactionService#getTransactionsList()
	 */
	public List<HclOpenBankResponse> getTransactionsList()  throws TechnicalException{ 

		List<HclOpenBankResponse> hclOpenBankResponseList = null;
		
		logger.debug("invoking open bank api to retrieve transactions list");

		Transactions openBankResponse = openBankClient.getOpenBankTransactions();

		if (openBankResponse != null) {
			hclOpenBankResponseList = new ArrayList<HclOpenBankResponse>();

			for (Transaction transactions : openBankResponse.getTransactions()) {

				HclOpenBankResponse hclOpenBankResponse = new HclOpenBankResponse();

				hclOpenBankResponse.setId(transactions.getId());
				hclOpenBankResponse.setAccountId(
						transactions.getThisAccount() != null ? transactions.getThisAccount().getId() : null);
				hclOpenBankResponse.setCounterpartyAccount(
						transactions.getOtherAccount() != null ? transactions.getOtherAccount().getNumber() : null);
				if (transactions.getOtherAccount() != null && transactions.getOtherAccount().getHolder() != null) {
					hclOpenBankResponse.setCounterpartyName(transactions.getOtherAccount().getHolder().getName());
				}
				// if (transactions.getOtherAccount() != null &&
				// transactions.getOtherAccount().getMetadata() != null) {
				// hclOpenBankResponse.setCounterPartyLogoPath(transactions.getOtherAccount().getMetadata().getImageURL());
				// }
				if (transactions.getDetails() != null && transactions.getDetails().getValue() != null) {
					hclOpenBankResponse.setInstructedAmount(transactions.getDetails().getValue().getAmount());
					hclOpenBankResponse.setInstructedCurrency(transactions.getDetails().getValue().getCurrency());
					hclOpenBankResponse.setTransactionAmount(transactions.getDetails().getValue().getAmount());
					hclOpenBankResponse.setTransactionCurrency(transactions.getDetails().getValue().getCurrency());
				}
				if (transactions.getDetails() != null) {
					hclOpenBankResponse.setTransactionType(transactions.getDetails().getType());
					hclOpenBankResponse.setDescription(transactions.getDetails().getDescription());
				}
				hclOpenBankResponseList.add(hclOpenBankResponse);

			}

		}

		return hclOpenBankResponseList;

	}

	

}
