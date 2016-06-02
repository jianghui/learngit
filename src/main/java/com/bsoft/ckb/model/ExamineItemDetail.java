package com.bsoft.ckb.model;

public class ExamineItemDetail extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String examineName;
	private String ref;
	private String clinicalValue;
	private Integer type;
	public String getExamineName() {
		return examineName;
	}
	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getClinicalValue() {
		return clinicalValue;
	}
	public void setClinicalValue(String clinicalValue) {
		this.clinicalValue = clinicalValue;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	

}
