package com.avenuecode.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avenuecode.data.domain.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
	
	Products findById(long idProduct);

}
