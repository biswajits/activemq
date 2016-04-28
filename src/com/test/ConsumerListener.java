package com.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class ConsumerListener {

	public static void main(String[] args) throws JMSException {
		String url = "tcp://localhost:61616";
        if (args.length > 0) {
            url = args[0];
        }

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("smx","smx",url);
        Destination destination = new ActiveMQQueue("test");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(destination);

        for (;;) {
            System.out.println("Waiting for message.");
            Message message = consumer.receive();
            if (message == null) {
                break;
            }
            System.out.println("Got message: " + message);
            if (message instanceof TextMessage) {
            	TextMessage textMessage = (TextMessage) message;
            	System.out.println("Receivedage '" + textMessage.getText()
            	+ "'");
            }
        }

        connection.close();

	}

}
