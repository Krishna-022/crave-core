package com.crave.crave_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long cartId;
	
	@Column(nullable = false)
	private Long menuItemId;
	
	@Column(nullable = false)
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CartItem(Long id, Long cartId, Long menuItemId, Integer quantity) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.menuItemId = menuItemId;
		this.quantity = quantity;
	}

	public CartItem() {}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", cartId=" + cartId + ", menuItemId=" + menuItemId + ", quantity=" + quantity
				+ "]";
	}
}
