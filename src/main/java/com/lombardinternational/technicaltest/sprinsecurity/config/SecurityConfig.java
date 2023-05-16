package com.lombardinternational.technicaltest.sprinsecurity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.lombardinternational.technicaltest.sprinsecurity.service")
@EnableJpaRepositories(basePackages = "com.lombardinternational.technicaltest.sprinsecurity.repository")
public class SecurityConfig {
    //TODO implement security!

}

