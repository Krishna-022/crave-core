package com.crave.crave_backend.dto.in;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRestaurantIndto {

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only English letters and spaces")
	private String name;

	@NotBlank(message = "Contact number is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact number must be exactly 10 digits starting with 6, 7, 8, or 9")
	private String contactNumber;

	@NotBlank(message = "Email is required")
	@Pattern(regexp = "^[a-z0-9]+(?:\\.[a-z0-9]+)*@gmail\\.com$", message = "Email must be a valid Gmail address (lowercase letters, numbers, periods only; no consecutive/leading/trailing dots)")
	@Size(min = 11, max = 100, message = "Email cannot exceed 100 characters")
	private String email;

	@NotNull(message = "Building number is required")
	private Long buildingNumber;

	@NotBlank(message = "PINCODE is required")
	@Pattern(regexp = "^\\d{6}$", message = "Pincode must be exactly 6 digits")
	private String pinCode;

	@NotBlank(message = "City name is required")
	@Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "City name must contain only English letters and spaces")
	private String cityName;

	@NotBlank(message = "State name is required")
	@Size(min = 2, max = 100, message = "State name must be between 2 and 100 characters")
	@Pattern(regexp = "^[A-Za-z ]+$", message = "State name must contain only English letters and spaces")
	private String stateName;
	
	@NotNull(message = "Image is required")
	private MultipartFile image;

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Long buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
