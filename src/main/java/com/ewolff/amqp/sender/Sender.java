package com.ewolff.amqp.sender;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;

public class Sender {

	public static void main(String[] args) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange("my.fanout");
		rabbitTemplate.setMessageConverter(new JsonMessageConverter());
		for (int i=0; i<1000; i++) {
			rabbitTemplate.convertAndSend("Hello");
		}
	}

}
