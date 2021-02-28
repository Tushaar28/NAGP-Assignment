package com.tushaar.assignment.order.utils;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class PendingQueueConfig {
	
	@Bean
	public Queue pendingQueue() {
		return new ActiveMQQueue("pending_queue");
	}
}
