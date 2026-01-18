package com.crave.crave_backend.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
	
	@CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
	
	@UpdateTimestamp
    private LocalDateTime updatedAt;
    
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

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

    public User(String firstName, String middleName, String lastName,
                String contactNumber, String email, String passwordHash) {
        super();
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

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, createdAt, email, firstName, id, lastName, middleName, passwordHash,
				updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(middleName, other.middleName) && Objects.equals(passwordHash, other.passwordHash)
				&& Objects.equals(updatedAt, other.updatedAt);
	}
}
