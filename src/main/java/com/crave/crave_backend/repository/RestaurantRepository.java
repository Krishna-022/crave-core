package com.crave.crave_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crave.crave_backend.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	List<Restaurant> findByUserId(Long UserId);

	List<Restaurant> findByContactNumberOrEmailOrName(String contactNumber, String email, String name);
}
