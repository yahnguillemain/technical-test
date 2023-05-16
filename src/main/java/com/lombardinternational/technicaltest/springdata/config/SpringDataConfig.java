package com.lombardinternational.technicaltest.springdata.config;

import com.lombardinternational.technicaltest.springdata.service.DepartmentRepositoryService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.lombardinternational.technicaltest.springdata.repository")
@EnableTransactionManagement
@ComponentScan(basePackageClasses = DepartmentRepositoryService.class)
public class SpringDataConfig {

}
