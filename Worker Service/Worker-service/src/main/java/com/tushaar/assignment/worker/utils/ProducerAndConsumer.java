package com.tushaar.assignment.worker.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.tushaar.assignment.worker.VO.OrderVO;
import com.tushaar.assignment.worker.service.WorkerService;

@Component
public class ProducerAndConsumer {
	
	@Autowired
	WorkerService _service;
	
	@JmsListener(destination = "approval-queue")
	public void approveOrder(Map<String, String> body) {
		//System.out.println(body);
		try {
		OrderVO order = _service.approveOrder(body);
		sendConfirmation(order);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	private void sendConfirmation(OrderVO order) {
		try {
			_service.sendConfirmation(order);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}
}
