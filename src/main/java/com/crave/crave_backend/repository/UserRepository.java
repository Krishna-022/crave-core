package com.crave.crave_backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.crave.crave_backend.entity.User;

import jakarta.persistence.LockModeType;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<User> findByContactNumber(String contactNumber);
	
	Optional<User> findByEmail(String Email);
	
	List<User> findByContactNumberOrEmail(String contactNumber, String Email); 
}
