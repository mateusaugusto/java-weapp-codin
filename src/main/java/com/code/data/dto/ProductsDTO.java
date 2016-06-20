package com.code.data.dto;

public class ProductsDTO {
   
	private String name;
	
	private long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProductsDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ProductsDTO(){}
	
}
