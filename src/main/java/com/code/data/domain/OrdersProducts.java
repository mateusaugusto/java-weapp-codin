package com.code.data.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders_products")
@IdClass(OrdersProductsId.class)
public class OrdersProducts {
	
	@Id
    @ManyToOne
    @JoinColumn(name="id_orders")
    private Orders order;
 
    @Id
    @ManyToOne
    @JoinColumn(name="id_products")
    private Products product;

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
    

}
