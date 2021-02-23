package com.tushaar.assignment.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tushaar.assignment.user.VO.OrderRequestTemplate;
import com.tushaar.assignment.user.VO.OrderResponseTemplate;
import com.tushaar.assignment.user.model.User;
import com.tushaar.assignment.user.reporsitory.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository _repository;
	
	@Autowired
	private RestTemplate _restTemplate;

	public User newUser(Map<String, String> body) {
		User user = new User();
		user.setAddress(body.get("address"));
		user.setName(body.get("name"));
		return _repository.save(user);
	}

	public User getUser(Long userId) {
		return _repository.findById(userId).orElse(null);
	}

	public Object newOrder(Long userId, Map<String, String> body) {
		User user = _repository.findById(userId).orElse(new User());
		OrderRequestTemplate orderRequest = new OrderRequestTemplate();
		orderRequest.setName(user.getName());
		orderRequest.setAddress(body.get("address"));
		orderRequest.setTime(body.get("time"));
		orderRequest.setType(body.get("type"));
		orderRequest.setUserId(userId);
		OrderResponseTemplate response = 
				_restTemplate.postForObject("http://localhot:8081/orders/new", orderRequest, OrderResponseTemplate.class);
		return response;
	}

}
