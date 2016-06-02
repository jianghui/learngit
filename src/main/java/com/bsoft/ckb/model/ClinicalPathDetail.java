package com.bsoft.ckb.model;

import java.util.List;

public class ClinicalPathDetail extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String clinicalPathName;
	private String suitableUsers;
	private String diangnosisBasis;
	private String treatmentProtocols;
	private String standardDays;
	private String standardIn;
	private String clinicProject;
	private String standardLeaveHospital;
	private String variationAnalysis;
	private String costReference;
	private List<ClinicalPathReport> clinicalPathReports;
	private String treatmentWorks;
	private String focusAdvice;
	private String nursingWork;
	public String getClinicalPathName() {
		return clinicalPathName;
	}
	public String getSuitableUsers() {
		return suitableUsers;
	}
	public String getDiangnosisBasis() {
		return diangnosisBasis;
	}
	public String getTreatmentProtocols() {
		return treatmentProtocols;
	}
	public String getStandardDays() {
		return standardDays;
	}
	public String getStandardIn() {
		return standardIn;
	}
	public String getClinicProject() {
		return clinicProject;
	}
	public String getStandardLeaveHospital() {
		return standardLeaveHospital;
	}
	public String getVariationAnalysis() {
		return variationAnalysis;
	}
	public String getCostReference() {
		return costReference;
	}
	public String getTreatmentWorks() {
		return treatmentWorks;
	}
	public String getFocusAdvice() {
		return focusAdvice;
	}
	public String getNursingWork() {
		return nursingWork;
	}
	public void setClinicalPathName(String clinicalPathName) {
		this.clinicalPathName = clinicalPathName;
	}
	public void setSuitableUsers(String suitableUsers) {
		this.suitableUsers = suitableUsers;
	}
	public void setDiangnosisBasis(String diangnosisBasis) {
		this.diangnosisBasis = diangnosisBasis;
	}
	public void setTreatmentProtocols(String treatmentProtocols) {
		this.treatmentProtocols = treatmentProtocols;
	}
	public void setStandardDays(String standardDays) {
		this.standardDays = standardDays;
	}
	public void setStandardIn(String standardIn) {
		this.standardIn = standardIn;
	}
	public void setClinicProject(String clinicProject) {
		this.clinicProject = clinicProject;
	}
	public void setStandardLeaveHospital(String standardLeaveHospital) {
		this.standardLeaveHospital = standardLeaveHospital;
	}
	public void setVariationAnalysis(String variationAnalysis) {
		this.variationAnalysis = variationAnalysis;
	}
	public void setCostReference(String costReference) {
		this.costReference = costReference;
	}
	public void setTreatmentWorks(String treatmentWorks) {
		this.treatmentWorks = treatmentWorks;
	}
	public void setFocusAdvice(String focusAdvice) {
		this.focusAdvice = focusAdvice;
	}
	public void setNursingWork(String nursingWork) {
		this.nursingWork = nursingWork;
	}
	public List<ClinicalPathReport> getClinicalPathReports() {
		return clinicalPathReports;
	}
	public void setClinicalPathReports(List<ClinicalPathReport> clinicalPathReports) {
		this.clinicalPathReports = clinicalPathReports;
	}
	
	
	
	
}
