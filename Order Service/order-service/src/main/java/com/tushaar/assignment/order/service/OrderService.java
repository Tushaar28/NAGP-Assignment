package com.tushaar.assignment.order.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushaar.assignment.order.model.Order;
import com.tushaar.assignment.order.repository.OrderRepository;
import com.tushaar.assignment.order.utils.OrderStatus.status;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository _repository;

	public Object newOrder(Map<String, String> body) {
		Order order = new Order();
		order.setUserId(Long.parseLong(body.get("userId")));
		order.setName(body.get("name"));
		order.setAddress(body.get("address"));
		order.setType(body.get("type"));
		order.setTime(body.get("time"));
		order.setOrderStatus(status.PENDING);
		return _repository.save(order);
	}

}
