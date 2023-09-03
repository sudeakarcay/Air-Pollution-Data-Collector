package co.mobileaction.example.worker;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.mobileaction.example.common")
public class WorkerAppConfig
{
    @Value("${messaging.consumer.initial-size}")
    private int CONSUMER_SIZE;

    @Value("${messaging.consumer.request.auto-start}")
    private boolean CONSUMER_REQUEST_AUTO_START;

    @Value("${messaging.consumer.request.max-size}")
    private int CONSUMER_REQUEST_MAX_SIZE;

    @Value("${messaging.coordinate.queue.request.problem}")
    private String MESSAGING_COORDINATE_REQUEST_PROBLEM_QUEUE;


    @Bean
    public AmqpTemplate coordinateRequestProblemQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                                    MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_COORDINATE_REQUEST_PROBLEM_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory coordinateQueueListener(ConnectionFactory connectionFactory,
                                                              MessageConverter messageConverter)
    {
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setConcurrentConsumers(CONSUMER_SIZE);
        container.setMaxConcurrentConsumers(CONSUMER_REQUEST_MAX_SIZE);
        container.setAutoStartup(CONSUMER_REQUEST_AUTO_START);
        container.setPrefetchCount(10);
        container.setMessageConverter(messageConverter);
        return container;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(WorkerAppConfig.class, args);
    }
}
