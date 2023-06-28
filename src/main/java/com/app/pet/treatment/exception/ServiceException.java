package com.app.pet.treatment.exception;

public class ServiceException extends RuntimeException {
	
	public String message;
	public String exception;
	
	public ServiceException(String message) {
		this.message = message;
	}
	
	public ServiceException(String message, String exception) {
		this.message = message;
		this.exception = exception;
	}
}
