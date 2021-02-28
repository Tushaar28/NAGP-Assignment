package com.tushaar.assignment.admin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tushaar.assignment.admin.VO.OrderVO;
import com.tushaar.assignment.admin.utils.Constants;

@Service
public class AdminService {
	
	@Autowired
	RestTemplate _restTemplate;

	public OrderVO assignOrder(String id) {
		try {
		Long orderId = Long.parseLong(id);
		OrderVO order = _restTemplate.getForObject("http://localhost:8081/orders/" + orderId, OrderVO.class);
		Long workerId = (order.getOrderId() % Constants.NUMBER_OF_WORKERS) + 1;
		order.setWorkerId(workerId);
		return order;
		}
		catch(Exception e) {
			return null;
		}
//		OrderVO assignOrder = new OrderVO();
//		assignOrder.setAddress(order.getAddress());
//		assignOrder.setName(order.getName());
//		assignOrder.setOrderId(order.getOrderId());
//		assignOrder.setOrderStatus(order.getOrderStatus());
//		assignOrder.setTime(order.getTime());
//		assignOrder.setType(order.getType());
//		assignOrder.setUserId(order.getUserId());
//		assignOrder.setWorkerId(workerId);
//		
//		return assignOrder;
	}

	public void publishOrder(OrderVO order) {
		try {
		Map<String, String> body = new HashMap<>();
		body.put("orderId", order.getOrderId().toString());
		body.put("workerId", order.getWorkerId().toString());
		Object response = _restTemplate.postForObject("http://localhost:8080/publish/assign/order", body, Object.class);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
