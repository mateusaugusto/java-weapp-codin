package com.avenuecode.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avenuecode.data.domain.Orders;
import com.avenuecode.data.domain.OrdersProducts;
import com.avenuecode.data.dto.ProductsDTO;

@Repository
public interface OrdersProductsRepository extends JpaRepository<OrdersProducts, Long>  {
	
	void deleteByOrder(Orders orders);
	
	@Query("SELECT new com.avenuecode.data.dto.ProductsDTO(p.id, p.name)"
			+ "FROM OrdersProducts op "
			+ "INNER JOIN op.product p "
			+ "WHERE op.order.id = :idOrder ")
	List<ProductsDTO> getProductsOrder(@Param("idOrder") long idOrder);

}
