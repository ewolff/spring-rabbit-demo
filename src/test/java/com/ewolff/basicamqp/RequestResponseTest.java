package com.ewolff.basicamqp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/consumer.xml")
public class RequestResponseTest {

	@Autowired
	private RabbitOperations rabbitOperations;


	@Test
	public void testRequestReply() {
		String response = (String) rabbitOperations.convertSendAndReceive(
				"my.fanout", "", "test");
		Assert.assertEquals("response test", response);
	}

}
