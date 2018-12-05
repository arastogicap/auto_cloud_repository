package com.ledify.batch.notification.batchProcessor.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class BatchStepListner implements StepExecutionListener{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
		int itemSkipped=stepExecution.getReadSkipCount();
		int itemRead=stepExecution.getReadCount();
		int itemProcess=stepExecution.getProcessSkipCount();
		int itemWritten=stepExecution.getWriteCount();
		
		logger.info("Item read::"+itemRead);
		logger.info("Item processed::"+itemProcess);
		logger.info("Item written::"+itemWritten);
		
		
		return stepExecution.getExitStatus();
	}

}
