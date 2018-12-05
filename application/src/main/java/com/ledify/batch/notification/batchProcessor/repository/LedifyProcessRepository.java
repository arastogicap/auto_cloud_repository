package com.ledify.batch.notification.batchProcessor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ledify.batch.notification.batchProcessor.models.LedifyProcessRecords;

@Service
public interface LedifyProcessRepository extends CrudRepository<LedifyProcessRecords, Long> {
	List<LedifyProcessRecords> findBySano(String sano);
	List<LedifyProcessRecords> findBySanoAndReference(String sano,String reference);
	//List<LedifyProcessRecords> findBySanolineItem(String sano,String lineItem);
	

}
