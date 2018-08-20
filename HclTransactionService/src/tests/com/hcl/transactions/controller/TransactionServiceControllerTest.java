package com.hcl.transactions.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.transactions.response.HclOpenBankResponse;
import com.hcl.transactions.service.HclTransactionService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceControllerTest {

	@InjectMocks
	TransactionServiceController underTest;
	@Mock
	private HclTransactionService hclTransactionService;

	@Test
	public void testGetTransactionsSuccess() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
		ResponseEntity<List<HclOpenBankResponse>> response = underTest.getTransactions();
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	@Test(expected = RuntimeException.class)
	public void testGetTransactionsError() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenThrow(new RuntimeException());
		underTest.getTransactions();

	}
	
	
	@Test
	public void testGetTransactionTypeSuccess() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
		ResponseEntity<List<HclOpenBankResponse>> response = underTest.getTransactionType("SANDBOX_TAN");
		Assert.assertEquals(2, response.getBody().size());

	}
	
	@Test
	public void testGetTransactionTypeNotFound() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
		ResponseEntity<List<HclOpenBankResponse>> response = underTest.getTransactionType("SOME_TYPE");
		Assert.assertEquals(0, response.getBody().size());

	}
	
	@Test(expected = RuntimeException.class)
	public void testGetTransactionTypeError() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenThrow(new RuntimeException());
		underTest.getTransactionType("SOME_TYPE");
		

	}
	
	@Test
	public void testGetTransactionTypeInputNull() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
		ResponseEntity<List<HclOpenBankResponse>> response = underTest.getTransactionType(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());

	}
	
	
	
	@Test
	public void testGetAmountByTransactionTypeSuccess() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
		ResponseEntity<Double> response = underTest.getAmountByTransactionType("SANDBOX_TAN");
		Assert.assertEquals(-180.0, response.getBody());

	}
	
	@Test(expected = RuntimeException.class)
	public void testGetAmountByTransactionTypeError() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenThrow(new RuntimeException());
		underTest.getAmountByTransactionType("SOME_TYPE");
		

	}
	
	@Test
	public void testGetAmountByTransactionTypeNotFound() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
		ResponseEntity<Double> response = underTest.getAmountByTransactionType("SOME_TYPE");
		Assert.assertEquals(0.0, response.getBody());

	}
	
	@Test
	public void testGetAmountByTransactionTypeNull() {
		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
		ResponseEntity<Double> response = underTest.getAmountByTransactionType(null);
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());

	}
//	
//	@Test(expected = RuntimeException.class)
//	public void testGetTransactionTypeError() {
//		Mockito.when(hclTransactionService.getTransactionsList()).thenThrow(new RuntimeException());
//		underTest.getTransactionType("SOME_TYPE");
//		
//
//	}
//	
//	@Test
//	public void testGetTransactionTypeInputNull() {
//		Mockito.when(hclTransactionService.getTransactionsList()).thenReturn(getHclBankResponseWithTx());
//		ResponseEntity<List<HclOpenBankResponse>> response = underTest.getTransactionType(null);
//		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
//
//	}

	private List<HclOpenBankResponse> getHclBankResponseWithTx() {

		List<HclOpenBankResponse> list = new ArrayList<HclOpenBankResponse>();

		HclOpenBankResponse response1 = new HclOpenBankResponse();
		response1.setAccountId("12345");
		response1.setId("897706c1-dcc6-4e70-9d85-8a537c7cbf3e");
		response1.setTransactionType("SANDBOX_TAN");
		response1.setCounterpartyAccount("savings-kids-john");
		response1.setCounterPartyLogoPath(null);
		response1.setCounterpartyName("ALIAS_49532E");
		response1.setDescription("Gift");
		response1.setTransactionAmount("-90.00");
		response1.setTransactionCurrency("GBP");

		list.add(response1);

		HclOpenBankResponse response2 = new HclOpenBankResponse();
		response2.setAccountId("6789");
		response2.setId("897706c1-dcc6-4e70-9d85-8a537c7cbf3e");
		response2.setTransactionType("SANDBOX_TAN");
		response2.setCounterpartyAccount("savings-kids-john");
		response2.setCounterPartyLogoPath(null);
		response2.setCounterpartyName("ALIAS_49532E");
		response2.setDescription("Gift");
		response2.setTransactionAmount("-90.00");
		response2.setTransactionCurrency("GBP");

		list.add(response2);

		return list;

	}

}
