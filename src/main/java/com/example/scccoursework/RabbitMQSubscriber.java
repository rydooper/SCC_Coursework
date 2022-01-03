package com.example.scccoursework;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitMQSubscriber {
    private static enum EXCHANGE_TYPE {DIRECT, FANOUT, TOPIC, HEADERS};

    // queue and exchange names are defined as constants for easy reuse
    private final static String EXCHANGE_NAME = "hello";
    private final static String QUEUE_NAME = "hello";

    // Set this for topic or direct exchanges. Leave empty for fanout.
    private final static String TOPIC_KEY_NAME = ""; // For direct use full name.
    // For topic use * to match one word or # to match multiple: *.blue, red.#, etc.

    public static void main(String[] args) throws Exception {

        // Connect to the RabbitMQ server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("");
        factory.setUsername("");
        factory.setPassword("");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declare the exchange you want to connect your queue to
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE.FANOUT.toString().toLowerCase()); // 2nd parameter: fanout, direct, topic, headers
        // Get an existing server-declared queue to connect to the exchange
        // IMPORTANT: This only works if the queue is already defined on the RabbitMQ server (through the web-UI for instance)
        // Try uncommenting line 44 and commenting out line 46 and see what happens (replace on line 46 constant QUEUE_NAME with queueName to fix the compilation error
        //String queueName = channel.queueDeclare().getQueue();
        // Declare a subscriber-defined queue
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // Link the queue to the exchange
        /*
            Special characters can be used in routing keys:
            - using "#" means the queue will receive all messages (like  fanout).
            - using * can replace one word in the routing key / topic.
        */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, TOPIC_KEY_NAME);
        // the routing key usually used for direct or topic queues

        System.out.println(" [*] Waiting for " + TOPIC_KEY_NAME + " messages. To exit press CTRL+C");

        // This code block indicates a callback which is like an event triggered ONLY when a message is received
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };

        // Consume messages from the queue by using the callback
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}
