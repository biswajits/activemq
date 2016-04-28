package com.test;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	public static void main(String[] args){

		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("smx","smx","tcp://localhost:61616");
		Connection connection = null;
		try{
			connection = factory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("test");
			MessageProducer producer = session.createProducer(queue);
			TextMessage message = session.createTextMessage ("Hello, world!");
			producer.send(message);
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
