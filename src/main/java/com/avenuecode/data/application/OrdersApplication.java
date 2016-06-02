package com.avenuecode.data.application;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import com.avenuecode.data.domain.Orders;
import com.avenuecode.data.domain.OrdersProducts;
import com.avenuecode.data.domain.Products;
import com.avenuecode.data.dto.OrdersDTO;
import com.avenuecode.data.dto.ProductsDTO;
import com.avenuecode.data.exception.NotFoundException;
import com.avenuecode.data.repository.OrdersProductsRepository;
import com.avenuecode.data.repository.OrdersRepository;
import com.avenuecode.data.repository.ProductsRepository;

@Component
public class OrdersApplication {

	@Autowired
	private OrdersRepository repository;

	@Autowired
	private ProductsRepository repositoryProducts;

	@Autowired
	private OrdersProductsRepository repositoryOP;

	@Transactional
	public OrdersDTO create(OrdersDTO orderDTO) {

		long idOrder = repository.saveAndFlush(buildOrders(orderDTO.getOrder())).getId();
		orderDTO.getProducts().forEach(p -> repositoryOP.save(buildOrdersProducts(idOrder, buildProducts(p.getId()))));
	   
		return orderDTO;
	}

	@Transactional
	public void updateOrder(long idOrder, OrdersDTO orderDTO) {
		Orders orders = buildUpdateOrders(idOrder, orderDTO);
		repository.saveAndFlush(orders);
		repositoryOP.deleteByOrder(orders);
		orderDTO.getProducts().forEach(p -> repositoryOP.save(buildOrdersProducts(idOrder, buildProducts(p.getId()))));
	}

	public List<OrdersDTO> findAllOrders() {
		List<Orders> listOrders = repository.findAll();
		if (listOrders == null) {
			throw new NotFoundException("order not exists");
		}
		List<OrdersDTO> listOrdersDTO = new ArrayList<>();

		listOrders.forEach(p -> {
			List<ProductsDTO> productsDTO = repositoryOP.getProductsOrder(p.getId());
			OrdersDTO ordersDTO = new OrdersDTO();
			ordersDTO.setOrder(p.getName());
			ordersDTO.setProducts(productsDTO);
			;
			listOrdersDTO.add(ordersDTO);
			ordersDTO = new OrdersDTO();
		});

		return listOrdersDTO;
	}

	public OrdersDTO findAllOrderById(long idOrder) {
		Orders order = repository.findById(idOrder);
		
		if (order == null) {
			throw new NotFoundException("Order not exists");
		}
		
		OrdersDTO ordersDTO = new OrdersDTO();
		List<ProductsDTO> productsDTO = repositoryOP.getProductsOrder(order.getId());
		
		ordersDTO.setOrder(order.getName());
		ordersDTO.setProducts(productsDTO);
		
		return ordersDTO;
	}

	private Products buildProducts(long idProduct) {
		Products product = new Products();
		product = repositoryProducts.findOne(idProduct);
		return product;
	}

	private Orders buildOrders(String name) {
		Orders orders = new Orders();
		orders.setName(name);
		return orders;
	}

	private OrdersProducts buildOrdersProducts(long idOrder, Products product) {
		OrdersProducts ordersProducts = new OrdersProducts();
		Orders order = new Orders();
		order = repository.findOne(idOrder);
		ordersProducts.setOrder(order);
		ordersProducts.setProduct(product);
		return ordersProducts;
	}

	private Orders buildUpdateOrders(long idOrder, OrdersDTO orderDTO) {
		Orders order = new Orders();
		order.setId(idOrder);
		order.setName(orderDTO.getOrder());
		return order;
	}
	
    @Transactional
    @CacheEvict(
            value = "products",
            key = "#id")
    public void delete(Long id) {
       // logger.info("> delete id:{}", id);
        repository.delete(id);
        //logger.info("< delete id:{}", id);
    }

    @CacheEvict(
            value = "greetings",
            allEntries = true)
    public void evictCache() {
        //logger.info("> evictCache");
        //logger.info("< evictCache");
    }

}
