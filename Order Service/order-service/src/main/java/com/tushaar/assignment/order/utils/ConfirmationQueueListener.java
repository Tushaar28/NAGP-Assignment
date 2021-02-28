package com.tushaar.assignment.order.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.tushaar.assignment.order.service.OrderService;

@Component
public class ConfirmationQueueListener {

	@Autowired
	OrderService _service;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@JmsListener(destination = "confirmation-queue")
	public void updateOrder(Map<String, String> body) {
		try {
			Object response = _service.updateOrder(body);
			if(!response.equals(HttpStatus.OK))
				throw new Exception("An error occured");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
