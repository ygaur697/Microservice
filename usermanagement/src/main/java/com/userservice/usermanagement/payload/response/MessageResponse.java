package com.userservice.usermanagement.payload.response;

public class MessageResponse {
	/**
	 * Author-Yash
	 * This message response definition
	 */
	private String message;

	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
