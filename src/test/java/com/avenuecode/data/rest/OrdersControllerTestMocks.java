package com.avenuecode.data.rest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.avenuecode.data.AbstractControllerTest;
import com.avenuecode.data.application.OrdersApplication;
import com.avenuecode.data.dto.OrdersDTO;
import com.avenuecode.data.dto.ProductsDTO;

@Transactional
public class OrdersControllerTestMocks extends AbstractControllerTest{
	
	   @Mock
	    private OrdersApplication service;

	    @InjectMocks
	    private OrdersController controller;

	    @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        setUp(controller);
	    }
	    
	    @Test
	    public void testfindAllOrders() throws Exception {

	        List<OrdersDTO> list = getEntityListStubData();

	        when(service.findAllOrders()).thenReturn(list);

	        String uri = "/order/";

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        verify(service, times(1)).findAllOrders();

	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);

	    }
	    
	    @Test
	    public void testfindOrdersById() throws Exception {

	        Long id = new Long(8);
	        OrdersDTO entity = getEntityStubData();
 
	        when(service.findAllOrderById(id)).thenReturn(entity);
	        String uri = "/order/{id}";
	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        verify(service, times(1)).findAllOrderById(id);
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	    }
	    
	    @Test
	    public void testfindOrderNotFound() throws Exception {

	        Long id = Long.MAX_VALUE;
	        when(service.findAllOrderById(id)).thenReturn(null);
	        String uri = "/order/{id}";

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        verify(service, times(1)).findAllOrderById(id);

	        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	        Assert.assertTrue("failure - expected HTTP response body to be empty",
	                content.trim().length() == 0);
	    }
	    
	    
	    @Test
	    public void testCreateOrders() throws Exception {

	    	OrdersDTO entity = getEntityStubData();

	        when(service.create(any(OrdersDTO.class))).thenReturn(entity);

	        String uri = "/order/";
	        String inputJson = super.mapToJson(entity);

	        MvcResult result = mvc
	                .perform(MockMvcRequestBuilders.post(uri)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
	                .andReturn();

	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();

	        verify(service, times(1)).create(any(OrdersDTO.class));

	        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);

	        OrdersDTO createdOrder = super.mapFromJson(content, OrdersDTO.class);

	        Assert.assertNotNull("failure - expected entity not null",
	        		createdOrder);
	        Assert.assertNotNull("failure - expected id attribute not null",
	        		createdOrder.getOrder());
	        Assert.assertEquals("failure - expected text attribute match",
	                entity.getOrder(), createdOrder.getOrder());
	    }
	    
	    
	    private OrdersDTO getEntityStubData() {
	    	List<ProductsDTO> listProducts = new ArrayList<>();
	    	ProductsDTO products = new ProductsDTO();
	        products.setId(1);
	        products.setName("item 1");
	        listProducts.add(products);
	        
	        OrdersDTO entity = new OrdersDTO();
	        entity.setOrder("Order Test");
	        entity.setProducts(listProducts);
	        return entity;
	    }
	    
	    private List<OrdersDTO> getEntityListStubData() {
	        List<OrdersDTO> list = new ArrayList<OrdersDTO>();
	        list.add(getEntityStubData());
	        return list;
	    }
	    

}
