package com.ledify.batch.notification.batchProcessor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ledify_rules_transaction")
public class LedifyRulesTransaction {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "sano")
	private String sano;
	@Column(name = "rule_id")
	private int ruleId;
	@Column(name = "ui_display_flag")
	private int uiDisplayFlag;
	@Column(name = "active_flag")
	private int activeFlag;
	@Column(name = "read_flag")
	private int readFlag;
	
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
	public int getUiDisplayFlag() {
		return uiDisplayFlag;
	}
	public void setUiDisplayFlag(int uiDisplayFlag) {
		this.uiDisplayFlag = uiDisplayFlag;
	}
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	public int getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(int readFlag) {
		this.readFlag = readFlag;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	
	
	
}
