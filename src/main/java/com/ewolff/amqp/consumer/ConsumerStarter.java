package com.ewolff.amqp.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerStarter {

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("consumer.xml");
	}

}
