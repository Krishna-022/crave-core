package com.crave.crave_backend.constant;

import java.util.concurrent.TimeUnit;

public interface SecurityConstants {
	
	String APPLICATION_NAME = "Crave";
	
	String AUTHORIZATION_HEADER = "Authorization";
	
	String BEARER_PREFIX = "Bearer ";
	
	Integer BEARER_PREFIX_LENGTH = 7;
	
	Long ONE_HOUR_MS = TimeUnit.HOURS.toMillis(1);
	
}
