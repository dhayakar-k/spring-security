package com.springsecurity.employeemanagement.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ConfigProperties {

    @Value("${digitaldots.rabbitmq.queue}")
    private String queueName;

    @Value("${digitaldots.rabbitmq.queue1}")
    private String tsQueue;

    @Value("${digitaldots.rabbitmq.queue2}")
    private String apQueue;

    @Value("${digitaldots.rabbitmq.queue3}")
    private String mhQueue;

    @Value("${digitaldots.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${digitaldots.rabbitmq.routingkey}")
    private String myRoutingKey;

    @Value("${digitaldots.rabbitmq.routingkey1}")
    private String adminRoutingKey;

    @Value("${digitaldots.rabbitmq.routingkey2}")
    private String marketingRoutingKey;


    @Value("${digitaldots.rabbitmq.routingkey2}")
    private String financeRoutingKey;

}
