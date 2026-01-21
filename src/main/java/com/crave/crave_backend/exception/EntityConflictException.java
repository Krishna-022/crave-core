package com.crave.crave_backend.exception;

import java.util.List;

public class EntityConflictException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private List<String> messageList;

	public EntityConflictException() {}
	
	public EntityConflictException(List<String> messageList) {
		this.messageList = messageList;
	}

	public List<String> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}
}
