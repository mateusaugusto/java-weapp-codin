package com.avenuecode.data.rest;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.avenuecode.data.AbstractControllerTest;
import com.avenuecode.data.application.ProductsApplication;

@Transactional
public class ProductsControllerTest extends AbstractControllerTest { 
	
	 @Autowired
	 private ProductsApplication service;
	 
	 @Before
	    public void setUp() {
	        super.setUp();
	        service.evictCache();
	    }
	 
	    @Test
	    public void testfindProductById() throws Exception {
	        String uri = "/products/{idProduct}";
	        Long idProduct = new Long(1);
	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, idProduct)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	    }
	    
	    @Test
	    public void testfindAllProducts() throws Exception {
	        String uri = "/products";
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
	    public void testfindProductByIdNotFound() throws Exception {
	        String uri = "/products/{idProduct}";
	        Long idProduct = Long.MAX_VALUE;
	        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, idProduct)
	                .accept(MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
	        Assert.assertTrue("failure - expected HTTP response body to be empty",
	                content.trim().length() == 0);
	    }
	    
	    
	    
	    

}
