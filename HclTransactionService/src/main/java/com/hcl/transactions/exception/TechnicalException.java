package com.hcl.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Technical exception.
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND,reason = "OpenBank api not responding")	
public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 7497572870971418898L;

	String errorMsg;
	Throwable t;
	

	

	public TechnicalException(String errorMsg, Throwable t) {
		super(errorMsg, t);
		this.errorMsg = errorMsg;
		this.t = t;
	}

	

}
