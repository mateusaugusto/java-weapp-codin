package com.avenuecode.data.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import com.avenuecode.data.domain.Products;
import com.avenuecode.data.repository.ProductsRepository;

@Component
public class ProductsApplication {
	
	@Autowired
	private ProductsRepository repository;
	
	public List<Products> findAllProducts(){
		List<Products> listProducts = repository.findAll();
		return listProducts;		
	}
	
	public Products findProductById(long idProduct){
		return  repository.findById(idProduct);		
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
