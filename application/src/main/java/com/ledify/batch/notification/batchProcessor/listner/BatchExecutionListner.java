package com.ledify.batch.notification.batchProcessor.listner;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class BatchExecutionListner implements JobExecutionListener{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void beforeJob(org.springframework.batch.core.JobExecution jobExecution) {
		logger.info("Job starts at :" + new Date());
		logger.info("Job starts at milliseconds :------ "
	                + System.currentTimeMillis());
		jobExecution.setStartTime(now());
		
		
	}

	@Override
	public void afterJob(org.springframework.batch.core.JobExecution jobExecution) {
		if(jobExecution.getStatus()==BatchStatus.FAILED) {
			logger.info("Job failed with following exceptions");
			List<Throwable> exceptionList = jobExecution
                    .getAllFailureExceptions();
            for (Throwable th : exceptionList) {
                logger.error("exception :" + th.getLocalizedMessage());
            }
		}
		/*if (jobExecution.getStatus() == BatchStatus.FAILED) {
            LOGGER.info("Job failed with following exceptions ");
            List<Throwable> exceptionList = jobExecution
                    .getAllFailureExceptions();
            for (Throwable th : exceptionList) {
                LOGGER.error("exception :" + th.getLocalizedMessage());
            }
        }*/
		
	}
	
	 public static Date now() {
	        return new Date();
	    }

	

}
