package com.bsoft.ckb.model;

public class DiseaseEntity extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String diseaseEntityId;
	private String diseaseEntityName;
	public String getDiseaseEntityId() {
		return diseaseEntityId;
	}
	public void setDiseaseEntityId(String diseaseEntityId) {
		this.diseaseEntityId = diseaseEntityId;
	}
	public String getDiseaseEntityName() {
		return diseaseEntityName;
	}
	public void setDiseaseEntityName(String diseaseEntityName) {
		this.diseaseEntityName = diseaseEntityName;
	}
	
}
