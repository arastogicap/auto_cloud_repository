package com.ledify.batch.notification.batchProcessor.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author amrastog
 *
 */
@Entity
@Table(name = "ledify_transaction_process")
public class LedifyProcessRecords {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sano")
	private String sano;

	@Column(name = "line_item")
	private String lineItem;

	@Column(name = "forecast_ref_key")
	private String forcastkey;

	@Column(name = "forecast_detail")
	private String forcastDetail;

	@Column(name = "jit_ref_key")
	private String jitRefKey;

	@Column(name = "jit_detail")
	private String jitDetail;

	@Column(name = "reference")
	private String reference;

	@Column(name = "variance")
	private String variance;

	@Column(name = "rule_id")
	private Long ruleid;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_on")
	private Date updatedOn;

	@Column(name = "posive_negative_variance")
	private String postiveNegativeVariance;

	@Column(name = "variance_result")
	private String varianceResult;

	@Column(name = "unit")
	private String unit;

	@Column(name = "tolerance_qty")
	private String toleranceQty;

	/*
	 * @Column(name = "forcast_Date") private LocalDate forcastDate;
	 * 
	 * @Column(name = "schedule_Date") private LocalDate scheduleDate;
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSano() {
		return sano;
	}

	public void setSano(String sano) {
		this.sano = sano;
	}

	public String getLineItem() {
		return lineItem;
	}

	public void setLineItem(String lineItem) {
		this.lineItem = lineItem;
	}

	public String getForcastkey() {
		return forcastkey;
	}

	public void setForcastkey(String forcastkey) {
		this.forcastkey = forcastkey;
	}

	public String getForcastDetail() {
		return forcastDetail;
	}

	public void setForcastDetail(String forcastDetail) {
		this.forcastDetail = forcastDetail;
	}

	public String getJitRefKey() {
		return jitRefKey;
	}

	public void setJitRefKey(String jitRefKey) {
		this.jitRefKey = jitRefKey;
	}

	public String getJitDetail() {
		return jitDetail;
	}

	public void setJitDetail(String jitDetail) {
		this.jitDetail = jitDetail;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getVariance() {
		return variance;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}

	public Long getRuleid() {
		return ruleid;
	}

	public void setRuleid(Long ruleid) {
		this.ruleid = ruleid;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getPostiveNegativeVariance() {
		return postiveNegativeVariance;
	}

	public void setPostiveNegativeVariance(String postiveNegativeVariance) {
		this.postiveNegativeVariance = postiveNegativeVariance;
	}

	public String getVarianceResult() {
		return varianceResult;
	}

	public void setVarianceResult(String varianceResult) {
		this.varianceResult = varianceResult;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getToleranceQty() {
		return toleranceQty;
	}

	public void setToleranceQty(String toleranceQty) {
		this.toleranceQty = toleranceQty;
	}

	/*
	 * public LocalDate getForcastDate() { return forcastDate; }
	 * 
	 * public void setForcastDate(LocalDate forcastDate) { this.forcastDate =
	 * forcastDate; }
	 * 
	 * public LocalDate getScheduleDate() { return scheduleDate; }
	 * 
	 * public void setScheduleDate(LocalDate scheduleDate) { this.scheduleDate =
	 * scheduleDate; }
	 */

}
