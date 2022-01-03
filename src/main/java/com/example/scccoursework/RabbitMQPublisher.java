package com.example.scccoursework;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class RabbitMQPublisher {
    private static enum EXCHANGE_TYPE {DIRECT, FANOUT, TOPIC, HEADERS};

    private final static String EXCHANGE_NAME = "hello";

    // Set this for topic or direct exchanges. Leave empty for fanout.
    private final static String TOPIC_KEY_NAME = ""; // For topic the format is keyword1.keyword2.keyword3. and so on.

    public static void main(String[] argv) throws Exception {

        // Connect to the RabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(""); // the IP
        factory.setUsername(""); // the username
        factory.setPassword(""); // the password

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //channel.exchangeDelete(EXCHANGE_NAME); // sometimes you must delete an existing exchange
            // Declare the exchange you want to connect your queue to
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE.FANOUT.toString().toLowerCase()); // 2nd parameter: fanout, direct, topic, headers
            String message = "Hello World!";
            // Publish a message to the exchange
            // This message will remain there until a client consumes it ...
            channel.basicPublish(EXCHANGE_NAME,
                    TOPIC_KEY_NAME, // the routing key, usually used for direct or topic queues
                    new AMQP.BasicProperties.Builder()
                            .contentType("text/plain")
                            .deliveryMode(2)
                            .priority(1)
                            .userId("student")
                            //.expiration("60000")
                            .build(),
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + TOPIC_KEY_NAME + ":" + message + "'");
        }
    }
}
