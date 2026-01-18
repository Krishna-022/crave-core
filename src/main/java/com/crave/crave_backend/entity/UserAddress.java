package com.crave.crave_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userId;
	
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
	
	public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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

    public UserAddress(Long id, Long userId, Long buildingNumber, String cityName,
                       String pinCode, String stateName, String countryName) {
        super();
        this.id = id;
        this.userId = userId;
        this.buildingNumber = buildingNumber;
        this.cityName = cityName;
        this.pinCode = pinCode;
        this.stateName = stateName;
        this.countryName = countryName;
    }

    public UserAddress() {}

    @Override
    public String toString() {
        return "UserAddress [id=" + id 
                + ", userId=" + userId 
                + ", buildingNumber=" + buildingNumber 
                + ", cityName=" + cityName 
                + ", pinCode=" + pinCode 
                + ", stateName=" + stateName 
                + ", countryName=" + countryName + "]";
    }
}
