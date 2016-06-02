package com.avenuecode.data.dto;

import java.util.List;

public class OrdersDTO {
   
	private String order;
	
	private List<ProductsDTO> products;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<ProductsDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsDTO> products) {
		this.products = products;
	}
	
	
}
