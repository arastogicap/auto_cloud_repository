package com.ledify.batch.notification.batchProcessor.step;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ledify.batch.notification.batchProcessor.exception.BatchException;
import com.ledify.batch.notification.batchProcessor.models.MetaData;
import com.ledify.batch.notification.batchProcessor.models.NotificationEvent;
import com.ledify.batch.notification.batchProcessor.models.Payload;
import com.ledify.batch.notification.batchProcessor.repository.PayloadRepository;

@Component
public class Processor implements ItemProcessor<List<Payload>, List<NotificationEvent>> {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PayloadRepository payloadRepository;

	Set<String> uniqueSano = new HashSet<String>();

	List<NotificationEvent> fsPayloadList = null;

	@Override
	public List<NotificationEvent> process(List<Payload> arg0) throws Exception {
		fsPayloadList = new ArrayList<NotificationEvent>();
		int compare = 0;
		Payload payloadEx = null;
		try {

			for (Payload payloadTemp : arg0) {
				payloadEx = payloadTemp;
				List<Payload> list = payloadRepository.findBySanoAndSTransactionType(payloadTemp.getSano(), "JIT");
				if (list.size() == 0) {
					NotificationEvent ne = new NotificationEvent();
					List<MetaData> metadataList = payloadTemp.getMetaData();
					for (MetaData md : metadataList) {
						setNotificationEvent(ne, md);
						Date schDate = null;
						Date currDate = new Date();
						if (StringUtils.equals(md.getKey(), "SchDate")) {
							schDate = convertToDate(md.getValue());
							compare = currDate.compareTo(schDate);
							if (compare > 0) {

								long variance = calculateDaysDifference(currDate, schDate);
								if (variance > 0) {
									ne.setVariance(String.valueOf(variance));
									fsPayloadList.add(ne);
								}

							}
						}

					}
				}

			}

		} catch (Exception e) {
			BatchException be = BatchException.wrap(e);
			logger.error("Batch terminated while processing :-" + payloadEx);
			throw be;
		}

		return fsPayloadList;
	}

	private long calculateDaysDifference(Date dt1, Date dt2) {
		long diff = 0;
		diff = dt1.getTime() - dt2.getTime();
		diff = diff / (24 * 60 * 60 * 1000);
		return diff;
	}

	private void setNotificationEvent(NotificationEvent ne, MetaData md) {
		if (StringUtils.equals(md.getKey(), "sa#")) {
			ne.setSano(md.getValue());
		}
		if (StringUtils.equals(md.getKey(), "itemNo#")) {
			ne.setLineItem(Long.valueOf(md.getValue()));
		}
		if (StringUtils.equals(md.getKey(), "SchDate")) {
			ne.setFieldKey(md.getKey());
			ne.setFieldVal(md.getValue());
		}

	}

	private Date convertToDate(String dateStr) {
		/*
		 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy",
		 * Locale.ENGLISH); LocalDate date = LocalDate.parse(dateStr, formatter)
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/*
	 * @Override public String process(String content) throws Exception { return
	 * content.toUpperCase(); }
	 */

}