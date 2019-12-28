package com.bmworks;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart(ApplicationReadyEvent applicationReadyEvent) {

        RabbitTemplate rabbitTemplate = applicationReadyEvent.getApplicationContext().getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("initialization", "stock-management-up", "Stock Management is up");

    }
}
