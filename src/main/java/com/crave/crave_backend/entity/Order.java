package com.crave.crave_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.crave.crave_backend.enums.OrderState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;

@Entity
@Table(name = "orders") // because order is reserved keyword in SQL
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Long restaurantId;
	
	@Column(nullable = false)
	private Long userAddressId;
	
	@Column(nullable = false)
	@Digits(integer=7, fraction=2)
	private BigDecimal totalPrice;  //Floating point (float/double) introduces rounding errors
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderState orderState;
	
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }
    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Order(Long id, Long userId, Long restaurantId, BigDecimal totalPrice, LocalDate date, OrderState orderState) {
        super();
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.totalPrice = totalPrice;
        this.orderState = orderState;
    }

    public Order() {}

    @Override
    public String toString() {
        return "Order [id=" + id 
                + ", userId=" + userId 
                + ", restaurantId=" + restaurantId 
                + ", totalPrice=" + totalPrice 
                + ", orderState=" + orderState + "]";
    }
}
