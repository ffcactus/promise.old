package com.promise.employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.promise.employee")
@EnableWebMvc
@EnableAspectJAutoProxy
public class AppConfig
{
    @Bean
    CommonAnnotationBeanPostProcessor getCommonAnnotationBeanPostProcessor()
    {
        return new CommonAnnotationBeanPostProcessor();
    }
}
