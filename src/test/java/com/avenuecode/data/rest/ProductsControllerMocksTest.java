package com.avenuecode.data.rest;

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
import com.avenuecode.data.application.ProductsApplication;
import com.avenuecode.data.domain.Products;

@Transactional
public class ProductsControllerMocksTest extends AbstractControllerTest {
		
    @Mock
    private ProductsApplication service;

    @InjectMocks
    private ProductsController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(controller);
    }
    
    @Test
    public void testfindAllProducts() throws Exception {

        // Create some test data
        List<Products> list = getEntityListStubData();

        // Stub the GreetingService.findAll method return value
        when(service.findAllProducts()).thenReturn(list);

        // Perform the behavior being tested
        String uri = "/products/";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the GreetingService.findAll method was invoked once
        verify(service, times(1)).findAllProducts();

        // Perform standard JUnit assertions on the response
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

    }
    
    @Test
    public void testfindProductsById() throws Exception {

        Long id = new Long(1);
        Products entity = getEntityStubData();

        when(service.findProductById(id)).thenReturn(entity);
        String uri = "/products/{id}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(service, times(1)).findProductById(id);
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
    
    @Test
    public void testfindProductsByIdNotFound() throws Exception {

        Long id = Long.MAX_VALUE;
        when(service.findProductById(id)).thenReturn(null);
        String uri = "/api/greetings/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(service, times(1)).findProductById(id);

        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
        Assert.assertTrue("failure - expected HTTP response body to be empty",
                content.trim().length() == 0);
    }
    
    
    private Products getEntityStubData() {
    	Products entity = new Products();
        entity.setId(1L);
        entity.setName("hello");
        return entity;
    }
    
    private List<Products> getEntityListStubData() {
        List<Products> list = new ArrayList<Products>();
        list.add(getEntityStubData());
        return list;
    }
    

}
