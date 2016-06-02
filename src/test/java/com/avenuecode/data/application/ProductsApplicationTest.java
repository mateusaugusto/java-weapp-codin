package com.avenuecode.data.application;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.avenuecode.data.AbstractTest;
import com.avenuecode.data.domain.Products;

@Transactional  
public class ProductsApplicationTest extends AbstractTest {
	
	    @Autowired
        private ProductsApplication service;
	    
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
	        List<Products> list = service.findAllProducts();
	        Assert.assertNotNull("failure - expected not null", list);
	        Assert.assertEquals("failure - expected list size", 4,list.size());
	    }
	    
	    @Test
	    public void testFindOne() {
	        Long id = new Long(1);
	        Products entity = service.findProductById(id);
	        Assert.assertNotNull("failure - expected not null", entity);
	        Assert.assertEquals("failure - expected id attribute match", 1, entity.getId());
	    }
	    
	    @Test
	    public void testFindOneNotFound() {
	        Long id = Long.MAX_VALUE;
	        Products entity = service.findProductById(id);
	        Assert.assertNull("failure - expected null", entity);

	    }
	
	
}
