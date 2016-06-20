package com.code.data.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code.data.application.OrdersApplication;
import com.code.data.dto.OrdersDTO;

@RestController
@RequestMapping("/order")
public class OrdersController extends BaseController {
  
	@Autowired
	private OrdersApplication application;
	
	/**
     * Web service Create a new Order with list or single products
     * 
     * If found, is returned as JSON with HTTP status 200.
     * 
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     */
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	void addUserToList(@RequestBody OrdersDTO order) {
		application.create(order);
	}
	
	/**
     * Web service update an Order 
     * 
     * If found, is returned as JSON with HTTP status ok.
     * 
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     * 
     * @param id order to be uptaded.
     */
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{idOrder}",method = RequestMethod.PUT)
	void saveSearch(@PathVariable int idOrder,@RequestBody OrdersDTO order) {
		application.updateOrder(idOrder, order);
	}
	
	 /**
     * Web service  to fetch a single order entity by primary key
     * identifier.
     * 
     * If found, the order is returned as JSON with HTTP status 200.
     * 
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     * 
     * @param id A Long URL path variable containing ID orde
     * @return A ResponseEntity containing a single Order object, if found,
     *         and a HTTP status code as described in the method comment.
     */
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrdersDTO>> getAllOrdes() {
		List<OrdersDTO> listOrders = application.findAllOrders();
		if (listOrders.isEmpty())
			return new ResponseEntity<List<OrdersDTO>>(listOrders, HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<OrdersDTO>>(listOrders, HttpStatus.OK);
	}
	
	 /**
     * Web service  to fetch all orders entity 
     * 
     * If found, the order is returned as JSON with HTTP status 200.
     * 
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     * 
     * @return A ResponseEntity containing a list of Order object and a list of products, if found,
     *         and a HTTP status code as described in the method comment.
     */
	@RequestMapping(value = "/{idOrder}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrdersDTO> getOrdesById(@PathVariable long idOrder) {
		OrdersDTO listOrders = application.findAllOrderById(idOrder);
		if (listOrders == null)
			return new ResponseEntity<OrdersDTO>(listOrders, HttpStatus.NO_CONTENT);
		return new ResponseEntity<OrdersDTO>(listOrders, HttpStatus.OK);
	}

}
