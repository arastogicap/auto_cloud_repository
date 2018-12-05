package com.ledify.batch.notification.batchProcessor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sap.cloud.sdk", "com.ledify.batch.notification.batchProcessor"})
@ServletComponentScan({"com.sap.cloud.sdk", "com.ledify.batch.notification.batchProcessor"})
@EnableBatchProcessing
public class SpringBatchApplication extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure( final SpringApplicationBuilder application )
    {
        return application.sources(SpringBatchApplication.class);
    }

    public static void main( final String[] args )
    {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}
