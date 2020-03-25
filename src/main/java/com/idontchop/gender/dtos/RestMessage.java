package com.idontchop.gender.dtos;

public class RestMessage {

	String message;

	public static RestMessage build (String message) {
		return new RestMessage(message);
	}
	public RestMessage () {}
	public RestMessage (String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
