package com.ewolff.sockjs;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SockJSSender {

	public static void main(String[] args) {
		CachingConnectionFactory conFactory = new CachingConnectionFactory(
				"localhost");
		RabbitTemplate template = new RabbitTemplate(conFactory);
		template.convertAndSend("amq.topic","test", "Hello from Java!");
	}

}
