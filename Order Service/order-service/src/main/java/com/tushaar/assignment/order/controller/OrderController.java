package com.tushaar.assignment.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.assignment.order.model.Order;
import com.tushaar.assignment.order.service.OrderService;

@RestController
@RequestMapping("/orders")
@EnableDiscoveryClient
public class OrderController {
	
	@Autowired
	private OrderService _service;
	
	@PostMapping("/new")
	public Object newOrder(@RequestBody Map<String, String> body) {
		return _service.newOrder(body);
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable("id") Long id) {
		return _service.getOrder(id);
	}
}
