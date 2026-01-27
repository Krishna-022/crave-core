package com.crave.crave_backend.exception;

public class UserUnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserUnauthorizedException(String message, Long userId) {
		super(message);
		this.message = message;
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
