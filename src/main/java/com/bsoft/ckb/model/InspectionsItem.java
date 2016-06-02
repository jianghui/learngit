package com.bsoft.ckb.model;

public class InspectionsItem extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String inspectionName;
	private String indication;
	private String contraindication;
	private String effect;
	private String notes;
	private String clinicalValue;
	public String getInspectionName() {
		return inspectionName;
	}
	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getContraindication() {
		return contraindication;
	}
	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getClinicalValue() {
		return clinicalValue;
	}
	public void setClinicalValue(String clinicalValue) {
		this.clinicalValue = clinicalValue;
	}
	
	
	
	

}
