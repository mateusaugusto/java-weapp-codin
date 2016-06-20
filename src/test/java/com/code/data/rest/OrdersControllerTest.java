package com.code.data.rest;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.code.data.AbstractControllerTest;
import com.code.data.application.OrdersApplication;
import com.code.data.dto.OrdersDTO;
import com.code.data.dto.ProductsDTO;

@Transactional
public class OrdersControllerTest extends AbstractControllerTest{
	
	 @Autowired
	 private OrdersApplication service;
	 
	 @Before
	    public void setUp() {
	        super.setUp();
	        service.evictCache();
	    }
	
	    @Test
	    public void testfindOrderById() throws Exception {
	        String uri = "/order/{id}";
	        Long id = new Long(1);
	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	    }
	    
	    @Test
	    public void testfindAllOrders() throws Exception {
	        String uri = "/order";
	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	    }
	    
	    
	    @Test
	    public void testfindOrderByIdNotFound() throws Exception {
	        String uri = "/order/{id}";
	        Long id = Long.MAX_VALUE;
	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	        Assert.assertTrue("failure - expected HTTP response body to be empty",
	                content.trim().length() == 0);
	    }
	    
	    @Test
	    public void testCreateOrder() throws Exception {

	        String uri = "/order/";
	        
	    	List<ProductsDTO> listProducts = new ArrayList<>();
	    	ProductsDTO products = new ProductsDTO();
	        products.setId(1);
	        products.setName("item 1");
	        listProducts.add(products);
	        
	        OrdersDTO entity = new OrdersDTO();
	        entity.setOrder("Order Test");
	        entity.setProducts(listProducts);
	        
	        String inputJson = super.mapToJson(entity);

	        MvcResult result = mvc
	                .perform(MockMvcRequestBuilders.post(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	                .andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0 );

	        OrdersDTO createdOrder = super.mapFromJson(content, OrdersDTO.class);

	        Assert.assertNotNull("failure - expected order not null",
	        		createdOrder);
	        Assert.assertNotNull("failure - expected order.id not null",
	        		createdOrder.getOrder());
	        Assert.assertEquals("failure - expected oredr match", "test",
	        		createdOrder.getProducts());
	    }
	    
	    

}
