package com.ledify.batch.notification.batchProcessor.step;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ledify.batch.notification.batchProcessor.models.Payload;
import com.ledify.batch.notification.batchProcessor.repository.PayloadRepository;
import com.ledify.batch.notification.batchProcessor.util.ItemReaderThreadLocalContext;

 
 @Component
public class Reader implements ItemReader<List<Payload>>{

     
    private int count=0;
     
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
	PayloadRepository payloadRepository;

	@Override
	public List<Payload> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(count==0) {
			if(!ItemReaderThreadLocalContext.getContextFlag()) {
				List<Payload> payloadList=payloadRepository.findBySTransactionType("FS");
				logger.info("Size =="+payloadList.size());
			
				ItemReaderThreadLocalContext.setContextFlag(Boolean.TRUE);
				return payloadList;	
				
			}
			
		}
		return null;
		
	}
     
    /*@Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
         
        if(count < messages.length){
            return messages[count++];
        }else{
            count=0;
        }
        return null;
    }*/
    
    
     
}