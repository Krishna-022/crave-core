package com.crave.crave_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true, length = 10)
	private String contactNumber;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private Long buildingNumber;
	
	@Column(nullable = false)
	private String cityName;
	
	@Column(nullable = false, length = 10)
	private String pinCode;
	
	@Column(nullable = false)
	private String stateName;
	
	@Column(nullable = false)
	private String countryName;
	
	public Restaurant() {}

    public Restaurant(Long userId, String name, String contactNumber, String email,
                      Long buildingNumber, String cityName, String pinCode,
                      String stateName, String countryName) {
        this.userId = userId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.buildingNumber = buildingNumber;
        this.cityName = cityName;
        this.pinCode = pinCode;
        this.stateName = stateName;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public Long getBuildingNumber() {
        return buildingNumber;
    }
    public void setBuildingNumber(Long buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPinCode() {
        return pinCode;
    }
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", cityName='" + cityName + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", stateName='" + stateName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
