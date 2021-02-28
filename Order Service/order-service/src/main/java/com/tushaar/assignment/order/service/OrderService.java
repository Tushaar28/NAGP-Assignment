package com.tushaar.assignment.order.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tushaar.assignment.order.model.Order;
import com.tushaar.assignment.order.repository.OrderRepository;
import com.tushaar.assignment.order.utils.OrderStatus.status;
import com.tushaar.assignment.order.utils.PendingQueueProducer;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository _repository;
	
	@Autowired
	private RestTemplate restTemplate;

	public Object newOrder(Map<String, String> body) {
		try {
		Order order = new Order();
		order.setUserId(Long.parseLong(body.get("userId")));
		order.setName(body.get("name"));
		order.setAddress(body.get("address"));
		order.setType(body.get("type"));
		order.setTime(body.get("time"));
		order.setOrderStatus(status.PENDING);
		order = _repository.save(order);
		Object response = restTemplate.postForObject("http://localhost:8080/publish/order", order, Object.class);
		return new ResponseEntity<Object>("Order has been created with order id " + order.getOrderId(), new HttpHeaders(), HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>("Unable to create order", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Order getOrder(Long id) {
		try {
			Order order = _repository.findById(id).orElse(null);
			return order;
		}
		catch(Exception e) {
			return null;
		}
	}

	public Object updateOrder(Map<String, String> body) {
		try {
		Long orderId = Long.parseLong(body.get("orderId"));
		String statusCode = body.get("status");
		status finalStatus = status.PENDING;
		Long workerId = Long.parseLong(body.get("workerId"));
		if(statusCode.equalsIgnoreCase("1"))
			finalStatus = status.APPROVED;
		else if(statusCode.equalsIgnoreCase("0"))
			finalStatus = status.REJECTED;
		
		Order order = _repository.findById(orderId).orElse(null);
		if(order == null)
			throw new Exception("No such order found");
		order.setOrderStatus(finalStatus);
		order.setWorkerId(workerId);
		Order updatedOrder = _repository.save(order);
		return HttpStatus.OK;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
			
	}
}	
