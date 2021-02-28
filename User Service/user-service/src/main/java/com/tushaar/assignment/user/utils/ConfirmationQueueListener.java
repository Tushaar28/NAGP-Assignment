package com.tushaar.assignment.user.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConfirmationQueueListener {
	
	@Autowired
	RestTemplate _restTemplate;
	
	@JmsListener(destination = "confirmation-queue")
	public void notify(Map<String, String> body) {
		try {
			Long orderId = Long.parseLong(body.get("orderId"));
			String statusCode = body.get("status");
			Long workerId = Long.parseLong(body.get("workerId"));
			if(statusCode.equalsIgnoreCase("1"))
				_restTemplate.postForObject("http://localhost:8082/user/approve/" + orderId, workerId, Object.class);
			else
				_restTemplate.getForObject("http://localhost:8082/user/reject/" + orderId, Object.class);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
