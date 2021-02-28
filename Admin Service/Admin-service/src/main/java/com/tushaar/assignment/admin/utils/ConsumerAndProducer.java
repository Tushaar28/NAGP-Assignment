package com.tushaar.assignment.admin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.tushaar.assignment.admin.VO.OrderVO;
import com.tushaar.assignment.admin.service.AdminService;

@Component
public class ConsumerAndProducer {
	
	@Autowired
	AdminService _service;

	@JmsListener(destination = "pending-queue")
	public void consumePendingOrder(String id) {
		OrderVO order = _service.assignOrder(id);
		publishOrder(order);
	}

	public void publishOrder(OrderVO order) {
		_service.publishOrder(order);
	}
}
