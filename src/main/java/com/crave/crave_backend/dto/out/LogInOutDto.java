package com.crave.crave_backend.dto.out;

public class LogInOutDto {
	
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public LogInOutDto(String accessToken) {
		this.accessToken = accessToken;
	}
}
