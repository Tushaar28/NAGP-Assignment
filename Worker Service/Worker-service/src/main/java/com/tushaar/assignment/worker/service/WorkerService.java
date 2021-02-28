package com.tushaar.assignment.worker.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tushaar.assignment.worker.VO.OrderVO;
import com.tushaar.assignment.worker.utils.status;

@Service
public class WorkerService {

	@Autowired
	RestTemplate _restTemplate;

	public OrderVO approveOrder(Map<String, String> body) {
		try {
			Long orderId = Long.parseLong(body.get("orderId"));
			Long workerId = Long.parseLong(body.get("workerId"));
			OrderVO order = _restTemplate.getForObject("http://localhost:8081/orders/" + orderId, OrderVO.class);
			if (order == null)
				throw new Exception("No such order found");
			if (orderId % 2 == 0) {
				order.setOrderStatus(status.APPROVED);
				order.setWorkerId(workerId);
			} else {
				order.setOrderStatus(status.REJECTED);
				order.setWorkerId(null);
			}
			return order;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void sendConfirmation(OrderVO order) {
		try {
			String statusCode = "-1";
			if(order.getOrderStatus() == status.APPROVED)
				statusCode = "1";
			else if(order.getOrderStatus() == status.REJECTED)
				statusCode = "0";
			if(statusCode == "-1")
				throw new Exception("Order status has not been updated");
			Map<String, String> body = new HashMap<>();
			body.put("orderId", order.getOrderId().toString());
			body.put("status", statusCode);
			body.put("workerId", order.getWorkerId().toString());
			Object response = _restTemplate.postForObject("http://localhost:8080/publish/confirm/order", body, Object.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}

}
