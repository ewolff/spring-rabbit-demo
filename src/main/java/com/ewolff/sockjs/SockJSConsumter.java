package com.ewolff.sockjs;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SockJSConsumter {


	
	public static void main(String[] args) {
		CachingConnectionFactory conFactory = new CachingConnectionFactory(
				"localhost");
		RabbitTemplate template = new RabbitTemplate(conFactory);
		RabbitAdmin admin = new RabbitAdmin(conFactory);
		 Queue testQueue = new Queue("test");
		admin.declareQueue(testQueue);
		TopicExchange topicExchange = new TopicExchange("amq.topic");
		admin.declareBinding(BindingBuilder.bind(testQueue).to(topicExchange).with("test"));
		while (true) {
			Message msg = template.receive("test");
			if (msg != null)
				System.out.println(new String(msg.getBody()));
		}
	}

}
