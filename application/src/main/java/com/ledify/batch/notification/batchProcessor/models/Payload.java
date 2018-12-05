package com.ledify.batch.notification.batchProcessor.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "PAYLOAD")
public class Payload {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "trans_type")
	String sTransactionType;
	
	@Column(name = "pl_data")
	String PayloadData;
	
	@Column(name = "sano")
	String sano;
	
	//int compare;
	
	
	//cascade = CascadeType.ALL
	@OneToMany(mappedBy="payload", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	List<MetaData> metaData = new ArrayList<>();
	
	/*@OneToOne(cascade = CascadeType.ALL)
	CustomerDetails custDetail=new CustomerDetails();*/
	
	
/*	public CustomerDetails getCustDetail() {
		return custDetail;
	}
	public void setCustDetail(CustomerDetails custDetail) {
		this.custDetail = custDetail;
	}*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getsTransactionType() {
		return sTransactionType;
	}
	public void setsTransactionType(String sTransactionType) {
		this.sTransactionType = sTransactionType;
	}

		
	public String getSano() {
		return sano;
	}
	public void setSano(String sano) {
		this.sano = sano;
	}


	
	/*public List<MetaData> getMetaData() {
		return metaData;
	}
	public void setMetaData(List<MetaData> metaData) {
		//this.metaData = metaData;
		this.metaData.clear();

	    for (MetaData md: metaData) {
	      md.setPayload(this);
	      this.metaData.add(md);
	    }
	}
	public void addMetadata(MetaData md) {
        this.metaData.add(md);
        md.setPayload(this);
    }
	
	public void SaveMetaData() {
		List<MetaData> mds = this.getMetaData();
		for (MetaData md : mds ) {
			md.setPayload(this);
		}
		//return payload;
	 }*/
	

	public String getPayloadData() {
		return PayloadData;
	}
	public void setPayloadData(String payloadData) {
		PayloadData = payloadData;
	}
	public List<MetaData> getMetaData() {
		return metaData;
	}
	public void setMetaData(List<MetaData> metaData) {
		this.metaData = metaData;
	}
	
	
	
	/*public int getCompare() {
		return compare;
	}
	public void setCompare(int compare) {
		this.compare = compare;
	}
	public Payload() {
		super();
		
	}*/
	
}
