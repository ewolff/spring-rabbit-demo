Spring Rabbit Demo
==================

This is a very simple demo to show what Spring AMQP and RabbitMQ can do.
It contains quite a few JUnit tests to show the main features.
The tests assume a running RabbitMQ on localhost. com.ewolff.basicamqp.BasicAMQPTest shows trivial handling of the different exchanges and queues.

Also there is a Consumer while can be started with the class com.ewolff.amqp.consumer.ConsumerStarter and a Sender in the class com.ewolff.amqp.sender.Sender. The test com.ewolff.basicamqp.RequestResponseTest executes this schema and tests whether responses are send by the sender.

Lastly the package com.ewolff.sockjs contain examples how Java can interact with SockJS as detailed in http://www.rabbitmq.com/blog/2012/05/14/introducing-rabbitmq-web-stomp/ .
 
 