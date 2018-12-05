package com.ledify.batch.notification.batchProcessor.step;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ledify.batch.notification.batchProcessor.exception.BatchException;
import com.ledify.batch.notification.batchProcessor.models.LedifyProcessRecords;
import com.ledify.batch.notification.batchProcessor.models.LedifyRulesTransaction;
import com.ledify.batch.notification.batchProcessor.models.NotificationEvent;
import com.ledify.batch.notification.batchProcessor.repository.LedifyProcessRepository;
import com.ledify.batch.notification.batchProcessor.repository.RulesTransactionRepository;

@Component
public class Writer implements ItemWriter<List<NotificationEvent>> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LedifyProcessRepository ledifyProcessRepository;

	@Autowired
	private RulesTransactionRepository rulesTransactionRepo;

	@Override
	public void write(List<? extends List<NotificationEvent>> arg0) throws Exception {
		// for (List<NotificationEvent> payloadList : arg0) {
		List<NotificationEvent> payloadList = arg0.get(0);
		try {
			for (NotificationEvent payload : payloadList) {
				List<LedifyProcessRecords> ledifyProcessRecordList = ledifyProcessRepository
						.findBySanoAndReference(payload.getSano(), "DLVSCH");
				if (ledifyProcessRecordList != null && ledifyProcessRecordList.size() > 0) {
					for (LedifyProcessRecords ledifyProcessRecord : ledifyProcessRecordList) {
						ledifyProcessRecord.setVariance(payload.getVariance());
						updateLedifyProcess(ledifyProcessRecord);
					}
				} else {
					persistLedifyProcessRecordsForForecast(payload);
				}
			}

		} catch (Exception e) {
			BatchException be = BatchException.wrap(e);

			throw be;
		}

	}

	/**
	 * @param payload
	 */
	private void persistLedifyProcessRecordsForForecast(NotificationEvent payload) {

		persistLedifyProcess(payload);

	}

	/**
	 * Method for storing Ledify Process Record
	 * 
	 * @param md
	 * @param lineItemNo
	 */
	private void persistLedifyProcess(NotificationEvent ne) {

		String varianceResult = null;
		String unit = null;
		LedifyProcessRecords ledifyProcessRecord = new LedifyProcessRecords();
		ledifyProcessRecord.setLineItem(String.valueOf(ne.getLineItem()));
		ledifyProcessRecord.setSano(ne.getSano());
		ledifyProcessRecord.setForcastkey(ne.getFieldKey());
		ledifyProcessRecord.setForcastDetail(ne.getFieldVal());
		ledifyProcessRecord.setCreatedOn(new Date());
		ledifyProcessRecord.setReference("DLVSCH");
		varianceResult = "Overdue";
		unit = "Day";

		ledifyProcessRecord.setVariance(ne.getVariance());
		ledifyProcessRecord.setVarianceResult(varianceResult);
		ledifyProcessRecord.setUnit(unit);

		ledifyProcessRepository.save(ledifyProcessRecord);
		updateRulesActiveFlag(ne.getSano());

	}

	/**
	 * @param sano
	 */
	private void updateRulesActiveFlag(String sano) {
		List<LedifyRulesTransaction> rulesTransaction = rulesTransactionRepo.findBySano(sano);
		for (LedifyRulesTransaction rule : rulesTransaction) {

			if (rule.getRuleId() == 4) {
				rule.setActiveFlag(1);
				rulesTransactionRepo.save(rule);
			}

		}
	}

	private void updateLedifyProcess(LedifyProcessRecords ledifyProcessRecord) {
		ledifyProcessRepository.save(ledifyProcessRecord);
		updateRulesActiveFlag(ledifyProcessRecord.getSano());
	}

	/*
	 * @Override public void write(List<? extends String> messages) throws Exception
	 * { for(String msg : messages){ System.out.println("#Writer Step: " + msg); } }
	 */

}