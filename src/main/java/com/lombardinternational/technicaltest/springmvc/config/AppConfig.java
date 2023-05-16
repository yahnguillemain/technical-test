package com.lombardinternational.technicaltest.springmvc.config;

import com.lombardinternational.technicaltest.springmvc.controllers.CounterService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
//@EnableWebSecurity(debug = true)
public class AppConfig {

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CounterService counterService() {
        return new CounterService();
    }

}
