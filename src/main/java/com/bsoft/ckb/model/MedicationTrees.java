package com.bsoft.ckb.model;

public class MedicationTrees extends BaseDO{
	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 399730068640943317L;
	private String medicationTreeId; 
	private String medicationTreeName; 
	private String encodingId;
	private Integer type;
	public String getMedicationTreeId() {
		return medicationTreeId;
	}
	public void setMedicationTreeId(String medicationTreeId) {
		this.medicationTreeId = medicationTreeId;
	}
	public String getMedicationTreeName() {
		return medicationTreeName;
	}
	public void setMedicationTreeName(String medicationTreeName) {
		this.medicationTreeName = medicationTreeName;
	}
	public String getEncodingId() {
		return encodingId;
	}
	public void setEncodingId(String encodingId) {
		this.encodingId = encodingId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
