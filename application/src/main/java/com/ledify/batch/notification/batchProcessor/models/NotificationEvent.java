package com.ledify.batch.notification.batchProcessor.models;

/**
 * @author amrastog
 *
 */
public class NotificationEvent {
	private String sano;
	private String fieldKey;
	private String variance;
	Long lineItem;
	private String fieldVal;
	private Payload payload;

	public String getSano() {
		return sano;
	}

	public void setSano(String sano) {
		this.sano = sano;
	}

	public String getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}

	public String getVariance() {
		return variance;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}

	public Long getLineItem() {
		return lineItem;
	}

	public void setLineItem(Long lineItem) {
		this.lineItem = lineItem;
	}

	public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

}
