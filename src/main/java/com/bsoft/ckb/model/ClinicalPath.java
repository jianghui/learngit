package com.bsoft.ckb.model;

public class ClinicalPath extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String clinicalPathId;
	private String clinicalPathName;
	public String getClinicalPathId() {
		return clinicalPathId;
	}
	public void setClinicalPathId(String clinicalPathId) {
		this.clinicalPathId = clinicalPathId;
	}
	public String getClinicalPathName() {
		return clinicalPathName;
	}
	public void setClinicalPathName(String clinicalPathName) {
		this.clinicalPathName = clinicalPathName;
	}
	
	
}
