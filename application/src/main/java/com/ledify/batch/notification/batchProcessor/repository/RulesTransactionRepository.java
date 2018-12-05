package com.ledify.batch.notification.batchProcessor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ledify.batch.notification.batchProcessor.models.LedifyRulesTransaction;

public interface RulesTransactionRepository extends CrudRepository<LedifyRulesTransaction,Long>{
	
	List<LedifyRulesTransaction> findBySano(String sano);
 
}