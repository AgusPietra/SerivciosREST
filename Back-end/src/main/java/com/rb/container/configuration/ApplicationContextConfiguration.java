package com.rb.container.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"com.rb"})
@Configuration
@EnableScheduling
public class ApplicationContextConfiguration {

    void ApplicationContextConfiguration () {

    }
}



