package com.promise.setting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.promise")
@EnableWebMvc
@EnableAspectJAutoProxy
public class AppConfig
{
    @Bean
    CommonAnnotationBeanPostProcessor getCommonAnnotationBeanPostProcessor()
    {
        return new CommonAnnotationBeanPostProcessor();
    }

    //    @Bean
    //    MultipartConfigElement getMultipartConfigElement()
    //    {
    //
    //        return new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
    //    }
    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver resolver()
    {
        return new StandardServletMultipartResolver();
    }

    //    private static final String LOCATION = "/tmp/"; // Temporary location where files will be stored
    //
    //    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
    //                                                       // Beyond that size spring will throw exception.
    //    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
    //
    //    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
}
