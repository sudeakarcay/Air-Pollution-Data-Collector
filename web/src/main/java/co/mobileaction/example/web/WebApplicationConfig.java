package co.mobileaction.example.web;

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

/**
 * @author sa
 * @date 17.05.2021
 * @time 13:53
 */
@SpringBootApplication
@ComponentScan("co.mobileaction.example.common")
public class WebApplicationConfig
{
    @Value("${messaging.coordinate.queue.request}")
    private String MESSAGING_COORDINATE_REQUEST_QUEUE;

    @Bean
    public AmqpTemplate coordinateRequestQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                             MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_COORDINATE_REQUEST_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(WebApplicationConfig.class, args);
    }
}