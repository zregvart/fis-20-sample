package com.github.zregvart.fis;

import java.net.UnknownHostException;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends RouteBuilder {

    public static void main(final String[] args) throws UnknownHostException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configure() throws Exception {
        from("timer://sampleTimer?period=10000").setBody().constant("Hello World").to("jms:queue:sample.queue");
    }

    @Bean
    ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://activemq.fis-20-sample.svc.cluster.local:61616");
    }
}
