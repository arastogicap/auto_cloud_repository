package com.ledify.batch.notification.batchProcessor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ledify.batch.notification.batchProcessor.models.Payload;




@Service
public interface PayloadRepository extends CrudRepository<Payload, Long>{
	
	List<Payload> findAll();
	List<Payload> findBySTransactionType(String sTransactionType);
	List<Payload> findBySanoAndSTransactionType(String sano,String sTransactionType);
	
	

}
