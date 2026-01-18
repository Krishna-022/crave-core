package com.crave.crave_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private Long restaurantId;

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Cart() {}

	public Cart(Long id, Long userId, Long restaurantId) {
		super();
		this.id = id;
		this.userId = userId;
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", restaurantId=" + restaurantId + "]";
	}
}
