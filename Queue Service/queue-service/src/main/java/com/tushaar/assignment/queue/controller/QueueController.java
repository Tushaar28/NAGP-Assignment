package com.tushaar.assignment.queue.controller;

import java.util.Map;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushaar.assignment.queue.VO.OrderVO;

@RestController
@RequestMapping("/publish")
@EnableDiscoveryClient
public class QueueController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	Queue pending_queue = new ActiveMQQueue("pending-queue");
	Queue approval_queue = new ActiveMQQueue("approval-queue");
	Queue confirmation_queue = new ActiveMQQueue("confirmation-queue");
	
	@PostMapping("/order")
	public Object postOrder(@RequestBody OrderVO order) {
		try {
		jmsTemplate.convertAndSend(pending_queue, order.getOrderId());
		return HttpStatus.OK;
		}
		catch(Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
		
	// Assigns order to worker
	@PostMapping("/assign/order")
	public Object assignOrder(@RequestBody Map<String, String> body) {
		try {
			jmsTemplate.convertAndSend(approval_queue, body);
			return HttpStatus.OK;
		}
		catch(Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	// Send confirmation back by worker
	@PostMapping("/confirm/order")
	public Object confirmOrder(@RequestBody Map<String, String> body) {
		try {
			jmsTemplate.convertAndSend(confirmation_queue, body);
			return HttpStatus.OK;
		}
		catch(Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
}
