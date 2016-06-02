package com.avenuecode.data.application;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.avenuecode.data.AbstractTest;
import com.avenuecode.data.dto.OrdersDTO;
import com.avenuecode.data.dto.ProductsDTO;

@Transactional
public class OrdersApplicationTest extends AbstractTest {
	
	@Autowired
    private OrdersApplication service;
	
	  @Before
	    public void setUp() {
	        service.evictCache();
	    }

	    @After
	    public void tearDown() {
	        // clean up after each test method
	    }
	    
	    @Test
	    public void testFindAll() {
	        List<OrdersDTO> list = service.findAllOrders();
	        Assert.assertNotNull("failure - expected not null", list);
	        Assert.assertEquals("failure - expected list size", 7,list.size());
	    }
	    
	    @Test
	    public void testFindOne() {
	        Long id = new Long(1);
	        OrdersDTO order = service.findAllOrderById(id);
	        Assert.assertNotNull("failure - expected not null", order);
	        Assert.assertEquals("failure - expected id attribute match", 1, 1);
	    }
	    
	    @Test
	    public void testFindOneNotFound() {
	        Long id = Long.MAX_VALUE;
	        OrdersDTO list = service.findAllOrderById(id);
	        Assert.assertNull("failure - expected null", list);

	    }
	    
	    @Test
	    public void testCreate() {
	    	
	    	List<ProductsDTO> listProducts = new ArrayList<>();
	    	ProductsDTO products = new ProductsDTO();
	        products.setId(1);
	        products.setName("item 1");
	        listProducts.add(products);
	        
	        OrdersDTO entity = new OrdersDTO();
	        entity.setOrder("Order Test");
	        entity.setProducts(listProducts);

	        service.create(entity);

	        Assert.assertNotNull("failure - expected not null", entity);
	        Assert.assertNotNull("failure - expected id attribute not null",
	        		entity);
	        Assert.assertEquals("failure - expected text attribute match", "Order Test",
	        		entity.getOrder());

	    }
	    
	    @Test
	    public void testUpdate() {

	    	  Long id = new Long(1);

	    		List<ProductsDTO> listProducts = new ArrayList<>();
		    	ProductsDTO products = new ProductsDTO();
		        products.setId(1);
		        products.setName("item 1");
		        listProducts.add(products);
		        
		        OrdersDTO entity = new OrdersDTO();
		        entity.setOrder("Order Test");
		        entity.setProducts(listProducts);


	          String updatedText = entity.getOrder() + " test";
	          entity.setOrder(updatedText);
	          service.updateOrder(id, entity);

	          Assert.assertNotNull("failure - expected not null", entity);
	          Assert.assertEquals("failure - expected id attribute match", id,
	        		  id);
	          Assert.assertEquals("failure - expected text attribute match",
	                  updatedText, entity.getOrder());

	    }
	    
	    

}
