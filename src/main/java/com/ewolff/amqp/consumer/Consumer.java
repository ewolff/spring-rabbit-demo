package com.ewolff.amqp.consumer;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@Autowired
	private AmqpAdmin amqpAdmin;

	private AtomicInteger counter = new AtomicInteger(0);

	public String consume(String message) {
		int count = counter.incrementAndGet();
		if ((count % 100) == 0) {
			System.out.println(count);
		}
		return "response " + message;
	}

}
