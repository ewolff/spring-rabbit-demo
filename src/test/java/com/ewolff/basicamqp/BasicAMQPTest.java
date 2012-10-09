package com.ewolff.basicamqp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.ewolff.amqp.domain.Customer;

public class BasicAMQPTest {

	private ConnectionFactory conFactory;
	private RabbitTemplate template;
	private RabbitAdmin admin;

	@Test
	public void testDefaults() {
		admin.declareQueue(new Queue("myQueue"));
		template.convertAndSend("myQueue", "Hi AMQP!");
		String receive = (String) template.receiveAndConvert("myQueue");
		Assert.assertEquals("Hi AMQP!", receive);
	}

	@Test
	public void testObject() {
		admin.declareQueue(new Queue("myQueue2"));
		template.convertAndSend("myQueue2",
				new Customer("Eberhard", "Wolff", 2));
		Object receive = template.receiveAndConvert("myQueue2");
		Customer customer = (Customer) receive;
		Assert.assertEquals("Eberhard", customer.getFirstname());
	}

	@Before
	public void setUp() {
		conFactory = new CachingConnectionFactory("localhost");
		template = new RabbitTemplate(conFactory);
		admin = new RabbitAdmin(conFactory);
	}

	@Test
	public void testFanout() {
		Queue fanoutQueue = new Queue("fanoutQueue");
		admin.declareQueue(fanoutQueue);
		FanoutExchange fanoutExchange = new FanoutExchange("myFanout");
		admin.declareExchange(fanoutExchange);
		admin.declareBinding(BindingBuilder.bind(fanoutQueue)
				.to(fanoutExchange));
		template.setExchange("myFanout");
		template.convertAndSend("Hi Fanout!");
		Assert.assertEquals("Hi Fanout!",
				template.receiveAndConvert("fanoutQueue"));
	}

	@Test
	public void testDirect() {
		Queue directQueue = new Queue("direct");
		admin.declareQueue(directQueue);
		admin.declareBinding(BindingBuilder.bind(directQueue)
				.to(new DirectExchange("amq.direct")).with("helloKey"));
		template.convertAndSend("amq.direct", "dropMe", "I will be dropped!");
		template.convertAndSend("amq.direct", "helloKey", "Hi Direct!");
		Assert.assertEquals("Hi Direct!", template.receiveAndConvert("direct"));
		Assert.assertNull(template.receiveAndConvert("direct"));
	}

}
