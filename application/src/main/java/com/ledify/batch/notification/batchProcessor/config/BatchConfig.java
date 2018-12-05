package com.ledify.batch.notification.batchProcessor.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ledify.batch.notification.batchProcessor.listner.BatchExecutionListner;
import com.ledify.batch.notification.batchProcessor.listner.BatchStepListner;
import com.ledify.batch.notification.batchProcessor.models.NotificationEvent;
import com.ledify.batch.notification.batchProcessor.models.Payload;
import com.ledify.batch.notification.batchProcessor.step.Processor;
import com.ledify.batch.notification.batchProcessor.step.Reader;
import com.ledify.batch.notification.batchProcessor.step.Writer;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public Reader reader;

	@Autowired
	public Processor processor;

	@Autowired
	public Writer writer;

	@Autowired
	BatchExecutionListner listener;
	
	@Autowired
	BatchStepListner stepListner;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).listener(listener).flow(step1()).end()
				.build();
	}

	@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<List<Payload>, List<NotificationEvent>> chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer).listener(stepListner)
                .build();
    }

}
