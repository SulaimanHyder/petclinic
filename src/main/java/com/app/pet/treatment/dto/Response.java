package com.app.pet.treatment.dto;

public class Response<T> {
	
	private T response;
	private String status;
	
	public Response(T response, String status) {
		super();
		this.response = response;
		this.status = status;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
