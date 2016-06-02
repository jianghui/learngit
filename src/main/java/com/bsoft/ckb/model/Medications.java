package com.bsoft.ckb.model;

public class Medications extends BaseDO{
	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 399730068640943317L;
	private String medicationId; 
	private String medicationName; 
	private String commodityName;
	private String originName;
	public String getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
	}
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	
	
	
}
