package com.crave.crave_backend.dto.out;

public class UserLogInOutDto {
	
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public UserLogInOutDto(String accessToken) {
		this.accessToken = accessToken;
	}
}
