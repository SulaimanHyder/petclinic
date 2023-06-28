package com.app.pet.treatment.exception;

public class ControllerException extends RuntimeException {
	
	public String message;
	public String exception;
	
	public ControllerException(String message) {
		this.message = message;
	}
	
	public ControllerException(String message, String exception) {
		this.message = message;
		this.exception = exception;
	} 
}
