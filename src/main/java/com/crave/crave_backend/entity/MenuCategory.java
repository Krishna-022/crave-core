package com.crave.crave_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MenuCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long restaurantId;
	
	@Column(nullable = false)
	private String name;

	public Long getId() {
		return id;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public MenuCategory(Long id, Long restaurantId, String name) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.name = name;
	}

	public MenuCategory() {}

	@Override
	public String toString() {
		return "MenuCategory [id=" + id + ", restaurantId=" + restaurantId + ", name=" + name + "]";
	}
}
