package com.springsecurity.employeemanagement.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@AllArgsConstructor
@ComponentScan(basePackages = "com.digitaldots")
public class RabbitMqConfiguration {

    private final ConfigProperties configProperties;


    /* @Bean
    public Queue queue() {
      return new Queue(configProperties.getQueueName(), false);
    }*/
    // Create three different queues

    @Bean
    public Queue tsQueue(){
        return new Queue(configProperties.getTsQueue(), false);
    }

    @Bean
    @Primary
    public Queue apQueue() {
        return new Queue(configProperties.getApQueue(), false);
    }

    @Bean
    public Queue mhQueue(){
        return new Queue(configProperties.getMhQueue(), false);
    }

    @Bean
    public DirectExchange directExchange(){
     return new DirectExchange(configProperties.getExchangeName());
    }
    // Bind the queues with the different topic exchanges
    @Bean
    public Binding tsBinding(Queue queue, DirectExchange directExchange) {
     return BindingBuilder.bind(queue).to(directExchange).with(configProperties.getAdminRoutingKey());
    }

    @Bean
    public Binding apBinding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(configProperties.getMarketingRoutingKey());
    }

    @Bean
    public Binding mhBinding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(configProperties.getFinanceRoutingKey());
    }

    /*@Bean
    public Binding binding(Queue queue, DirectExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(configProperties.getMyRoutingKey());
    }*/

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory)  {
     final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
     rabbitTemplate.setMessageConverter(messageConverter());
     return rabbitTemplate;
    }

    // creating beans for listeners

   /*@Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory
                                            , MessageListenerAdapter adapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue().getName());
        container.setMessageListener(adapter);
        return  container;
   }

   @Bean
   public MappingJackson2MessageConverter consumerJackson2MessageConverter(){
        return new MappingJackson2MessageConverter();
   }


    @Bean
   public MessageListenerAdapter messageListenerAdapter(RabbitMqListener listener){
        return new MessageListenerAdapter(listener, "listen");
    }*/


}
