package com.hcl.transactions.openBank.client;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.transactions.rest.Transactions;

@RunWith(MockitoJUnitRunner.class)
public class OpenBankClientTest {
	
	@InjectMocks
	OpenBankClient underTest;
	@Mock
	private RestTemplate restTemplate;
	
	@Test
	public void testGetOpenBankTransactionsSuccess() {
		
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.anyObject())).thenReturn(getOpenBankTransactions());
		
		Transactions response = underTest.getOpenBankTransactions();
		
		Assert.assertNotNull(response);

	}
	
	@Test(expected = RuntimeException.class)
	public void testGetOpenBankTransactionsError() {
		
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.anyObject())).thenThrow(new RuntimeException());
		
		Transactions response = underTest.getOpenBankTransactions();
		
		Assert.assertNotNull(response);

	}
	
	
	private Transactions getOpenBankTransactions() {
		ObjectMapper mapper = new ObjectMapper();
		Transactions transactions = null;

		String json = "{\"transactions\":[{\"id\":\"897706c1-dcc6-4e70-9d85-8a537c7cbf3e\",\"this_account\":{\"id\":\"savings-kids-john\",\"holders\":[{\"name\":\"Savings - Kids John\",\"is_alias\":false}],\"number\":\"832425-00304050\",\"kind\":\"savings\",\"IBAN\":null,\"swift_bic\":null,\"bank\":{\"national_identifier\":\"rbs\",\"name\":\"The Royal Bank of Scotland\"}},\"other_account\":{\"id\":\"E806HT1hp-IfBH0DP1rCFrPAftEtpCAwU-XlMo_9bzM\",\"holder\":{\"name\":\"ALIAS_49532E\",\"is_alias\":true},\"number\":\"savings-kids-john\",\"kind\":null,\"IBAN\":null,\"swift_bic\":null,\"bank\":{\"national_identifier\":\"rbs\",\"name\":\"rbs\"},\"metadata\":{\"public_alias\":null,\"private_alias\":null,\"more_info\":null,\"URL\":null,\"image_URL\":null,\"open_corporates_URL\":null,\"corporate_location\":null,\"physical_location\":null}},\"details\":{\"type\":\"SANDBOX_TAN\",\"description\":\"Gift\",\"posted\":\"2017-10-15T14:22:28Z\",\"completed\":\"2017-10-15T14:22:28Z\",\"new_balance\":{\"currency\":\"GBP\",\"amount\":null},\"value\":{\"currency\":\"GBP\",\"amount\":\"-90.00\"}},\"metadata\":{\"narrative\":null,\"comments\":[],\"tags\":[],\"images\":[],\"where\":null}},{\"id\":\"a96603e1-7408-485f-8baf-0ff13fdb9655\",\"this_account\":{\"id\":\"savings-kids-john\",\"holders\":[{\"name\":\"Savings - Kids John\",\"is_alias\":false}],\"number\":\"832425-00304050\",\"kind\":\"savings\",\"IBAN\":null,\"swift_bic\":null,\"bank\":{\"national_identifier\":\"rbs\",\"name\":\"The Royal Bank of Scotland\"}},\"other_account\":{\"id\":\"E806HT1hp-IfBH0DP1rCFrPAftEtpCAwU-XlMo_9bzM\",\"holder\":{\"name\":\"ALIAS_49532E\",\"is_alias\":true},\"number\":\"savings-kids-john\",\"kind\":null,\"IBAN\":null,\"swift_bic\":null,\"bank\":{\"national_identifier\":\"rbs\",\"name\":\"rbs\"},\"metadata\":{\"public_alias\":null,\"private_alias\":null,\"more_info\":null,\"URL\":null,\"image_URL\":null,\"open_corporates_URL\":null,\"corporate_location\":null,\"physical_location\":null}},\"details\":{\"type\":\"SANDBOX_TAN\",\"description\":\"Birthday Gift\",\"posted\":\"2017-10-15T07:57:48Z\",\"completed\":\"2017-10-15T07:57:48Z\",\"new_balance\":{\"currency\":\"GBP\",\"amount\":null},\"value\":{\"currency\":\"GBP\",\"amount\":\"100.00\"}},\"metadata\":{\"narrative\":null,\"comments\":[],\"tags\":[],\"images\":[],\"where\":null}}]}";

		try {
			transactions = mapper.readValue(json, Transactions.class);
		} catch (IOException e) {
			
		}

		return transactions;

	}

}
