package com.exam.eticket.services.execptions;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}

	
	
}
