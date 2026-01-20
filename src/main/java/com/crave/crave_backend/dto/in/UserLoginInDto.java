package com.crave.crave_backend.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserLoginInDto {
	@NotBlank(message = "Contact number is required")
	@Pattern(
	    regexp = "^[6-9]\\d{9}$",
	    message = "Contact number must be exactly 10 digits starting with 6, 7, 8, or 9"
	)
	private String contactNumber;
	
	@NotBlank(message = "Password is required")
	private String password;

	public UserLoginInDto() {}
	public UserLoginInDto(
			@NotBlank(message = "Contact number is required") @Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact number must be exactly 10 digits starting with 6, 7, 8, or 9") String contactNumber,
			@NotBlank(message = "Password is required") @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$", message = "Password must contain: uppercase, lowercase, number, and one of @$!%*?&") String password) {
		super();
		this.contactNumber = contactNumber;
		this.password = password;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
