package com.bsoft.ckb.model;

public class ClinicalPathReport extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String pointName;
	private String treatmentWorks;
	private String focusAdvice;
	private String nursingWork;
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getTreatmentWorks() {
		return treatmentWorks;
	}
	public void setTreatmentWorks(String treatmentWorks) {
		this.treatmentWorks = treatmentWorks;
	}
	public String getFocusAdvice() {
		return focusAdvice;
	}
	public String getNursingWork() {
		return nursingWork;
	}
	public void setFocusAdvice(String focusAdvice) {
		this.focusAdvice = focusAdvice;
	}
	public void setNursingWork(String nursingWork) {
		this.nursingWork = nursingWork;
	}
	
	
}
