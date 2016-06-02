package com.avenuecode.data.domain;

import java.io.Serializable;

public class OrdersProductsId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long order;
	private long product;
	
	public long getOrder() {
		return order;
	}
	public void setOrder(long order) {
		this.order = order;
	}
	public long getProduct() {
		return product;
	}
	public void setProduct(long product) {
		this.product = product;
	}
	
	
}
