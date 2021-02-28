package com.tushaar.assignment.order.utils;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.tushaar.assignment.order.model.Order;

public class PendingQueueProducer {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	Queue queue;
	
	public void publish(Order order) throws Exception {
		try {
			jmsTemplate.convertAndSend(queue, order);
		}
		catch(Exception e) {
			throw new Exception("Unable to publish message");
		}
	}

}
