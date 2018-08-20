package com.hcl.transactions.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.hcl.transactions.exception.ApiError;
import com.hcl.transactions.exception.TechnicalException;
import com.hcl.transactions.response.HclOpenBankResponse;
import com.hcl.transactions.service.HclTransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionServiceController { 
	
	private static final Logger logger = Logger.getLogger(TransactionServiceController.class);
	

	@Autowired
	private HclTransactionService hclTransactionService;
	 
	 
	 @RequestMapping(value = "/getTransactions", method = RequestMethod.GET)
	public ResponseEntity<List<HclOpenBankResponse>> getTransactions() throws TechnicalException {

		List<HclOpenBankResponse> transactionResponse = null;

		transactionResponse = hclTransactionService.getTransactionsList();
		
		logger.info(transactionResponse != null ? "Total no of Transactions:: " + transactionResponse.size() : "Total no of Transactions:: 0");

		return new ResponseEntity<List<HclOpenBankResponse>>(transactionResponse, HttpStatus.OK);

	}
	 
	 @RequestMapping(value = "/getTxType", method = RequestMethod.GET)
	public ResponseEntity<List<HclOpenBankResponse>> getTransactionType(@RequestParam("type") String type)
			throws TechnicalException {

		List<HclOpenBankResponse> transactionResponse = hclTransactionService.getTransactionsList();

		List<HclOpenBankResponse> filteredResponse = null;

		if (type != null) {

			filteredResponse = transactionResponse.stream().filter(p -> type.equalsIgnoreCase(p.getTransactionType()))
					.collect(Collectors.toList());

			return new ResponseEntity<List<HclOpenBankResponse>>(filteredResponse, HttpStatus.OK);

		} else {

			return new ResponseEntity<List<HclOpenBankResponse>>(filteredResponse, HttpStatus.BAD_REQUEST);

		}

	}
	 
	 @RequestMapping(value = "/getAmountByTxType", method = RequestMethod.GET)
	public ResponseEntity<Double> getAmountByTransactionType(@RequestParam("type") String type)
			throws TechnicalException {

		List<HclOpenBankResponse> transactionResponse = hclTransactionService.getTransactionsList();

		Double sum = null;

		if (type != null) {

			sum = transactionResponse.stream().filter(p -> type.equalsIgnoreCase(p.getTransactionType()))
					.mapToDouble(p -> new Double(p.getTransactionAmount())).sum();

			return new ResponseEntity<Double>(sum, HttpStatus.OK);

		} else {

			return new ResponseEntity<Double>(sum, HttpStatus.BAD_REQUEST);
		}

	}
	 
	 @ExceptionHandler({ Exception.class })
	 public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
	     ApiError apiError = new ApiError(
	       HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
	     return new ResponseEntity<Object>(
	       apiError, new HttpHeaders(), apiError.getStatus());
	 }

}
