package com.crave.crave_backend.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;

@Entity
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long menuCategoryId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	@Digits(integer=6, fraction=2)
	private BigDecimal price;
	
	public Long getId() {
        return id;
    }

    public Long getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(Long menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MenuItem(Long id, Long menuCategoryId, String name, BigDecimal price) {
        super();
        this.id = id;
        this.menuCategoryId = menuCategoryId;
        this.name = name;
        this.price = price;
    }

    public MenuItem() {}

    @Override
    public String toString() {
        return "MenuItem [id=" + id 
                + ", menuCategoryId=" + menuCategoryId 
                + ", name=" + name 
                + ", price=" + price + "]";
    }
}
