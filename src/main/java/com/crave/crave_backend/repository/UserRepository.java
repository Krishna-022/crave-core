package com.crave.crave_backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crave.crave_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByContactNumber(String contactNumber);
	Optional<User> findByEmail(String Email);
}
