package com.code.data.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.code.data.application.ProductsApplication;
import com.code.data.domain.Products;

@RestController
@RequestMapping("/products")
public class ProductsController extends BaseController {
	
	@Autowired
	private ProductsApplication application;

	 /**
     * Web service  to fetch all products
     * 
     * If found, the order is returned as JSON with HTTP status 200.
     * 
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     * 
     * @return A ResponseEntity containing a list of products object, if found,
     *         and a HTTP status code as described in the method comment.
     */
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Products>> getAllProducts() {
		List<Products> listProducts = application.findAllProducts();
		if (listProducts.isEmpty())
			return new ResponseEntity<List<Products>>(listProducts, HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Products>>(listProducts, HttpStatus.OK);
	}
	
	 /**
     * Web service  to fetch a single product entity by primary key
     * identifier.
     * 
     * If found, the order is returned as JSON with HTTP status 200.
     * 
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     * 
     * @param id A Long URL path variable containing ID product
     * @return A ResponseEntity containing a single product object, if found,
     *         and a HTTP status code as described in the method comment.
     */
	
	@RequestMapping(value = "/{idProduct}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Products> getProductById(@PathVariable long idProduct) {
		Products product = application.findProductById(idProduct);
		if (product == null)
			return new ResponseEntity<Products>(product, HttpStatus.NO_CONTENT);
		return new ResponseEntity<Products>(product, HttpStatus.OK);
	}
	
	
}
