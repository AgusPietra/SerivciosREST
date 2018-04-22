package com.rb.container;
import com.rb.container.configuration.ApplicationContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration(exclude = {JmxAutoConfiguration.class})
@Import({ApplicationContextConfiguration.class})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    }
}

