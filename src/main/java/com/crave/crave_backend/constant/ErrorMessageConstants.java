package com.crave.crave_backend.constant;

public interface ErrorMessageConstants {
	
	String ENTITY_CONFLICT = "%s with %s '%s' already exists";

	String PERSISTENCE_UNKNOWN_EXCEPTION = "Server busy, Please try again";
	
	String DATA_INTEGRITY_VIOLATION = "Please fill all the fields properly";
	
	String BAD_CREDENTIALS = "Invalid credentials";
}
