package com.crave.crave_backend.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long orderId;
	
	@Column(nullable = false)
	private Long menuItemId;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	@Digits(integer = 6, fraction = 2)
	private BigDecimal unitPrice;
	
	public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrder(Long orderId) {
        this.orderId = orderId;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderItem(Long id, Long orderId, Long menuItemId, Integer quantity, BigDecimal unitPrice) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem() {}

    @Override
    public String toString() {
        return "OrderItem [id=" + id 
                + ", orderId=" + orderId
                + ", menuItemId=" + menuItemId 
                + ", quantity=" + quantity 
                + ", unitPrice=" + unitPrice + "]";
    }
}
