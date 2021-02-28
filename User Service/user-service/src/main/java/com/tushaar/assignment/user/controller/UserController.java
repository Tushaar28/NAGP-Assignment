package com.tushaar.assignment.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.assignment.user.model.User;
import com.tushaar.assignment.user.service.UserService;

@RestController
@RequestMapping("/user")
@EnableDiscoveryClient
public class UserController {
	
	@Autowired
	private UserService _service;
	
	@PostMapping("/new")
	public User newUser(@RequestBody Map<String, String> body) {
		return _service.newUser(body);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long userId) {
		return _service.getUser(userId);
	}

	@PostMapping("/{id}/order/new")
	public Object newOrder(@PathVariable("id") Long userId, @RequestBody Map<String, String> body) {
		return _service.newOrder(userId, body);
	}
	
	@PostMapping("/approve/{id}")
	public Object notifyApprove(@PathVariable("id") Long orderId, @RequestBody String workerId) {
		return _service.notifyApprove(orderId, workerId);
	}
	
	@GetMapping("/reject/{id}")
	public Object notifyReject(@PathVariable("id") Long orderId) {
		//return "Your order with order id " + orderId + " has been rejected";
		return _service.notifyReject(orderId);
	}
}
