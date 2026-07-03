package br.com.adriano.orders;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class OrdersConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersConsumerApplication.class, args);
	}

    @Bean
    ApplicationRunner runner(Environment env) {
        return args -> {
            System.out.println("--------------------------------");
            System.out.println(env.getProperty("spring.kafka.bootstrap-servers"));
            System.out.println("--------------------------------");
        };
    }
}
