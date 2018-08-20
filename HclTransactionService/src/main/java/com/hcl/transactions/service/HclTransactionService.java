package com.hcl.transactions.service;

import java.util.List;

import com.hcl.transactions.response.HclOpenBankResponse;

public interface HclTransactionService {
	
	public List<HclOpenBankResponse> getTransactionsList();
	
	

}
