package com.avenuecode.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avenuecode.data.domain.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
	
	Orders findById(long idOrder);

}
