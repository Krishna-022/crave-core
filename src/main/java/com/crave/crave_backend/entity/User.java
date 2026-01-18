package com.crave.crave_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName; 
	
	@Column(nullable = true)
	private String middleName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true, length = 10)
	private String contactNumber;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String passwordHash;
	
	public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword() {
        return passwordHash;
    }
    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User(Long id, String firstName, String middleName, String lastName,
                String contactNumber, String email, String passwordHash) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User() {}

    @Override
    public String toString() {
        return "User [id=" + id 
                + ", firstName=" + firstName 
                + ", middleName=" + middleName 
                + ", lastName=" + lastName 
                + ", contactNumber=" + contactNumber 
                + ", email=" + email 
                + ", passwordHash=" + passwordHash + "]";
    }
}
