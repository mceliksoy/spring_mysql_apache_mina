package com.example;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public NioSocketAcceptor nioSocketAcceptor() {
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        return acceptor;
    }
}
