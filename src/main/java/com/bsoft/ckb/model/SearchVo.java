package com.bsoft.ckb.model;

import java.util.ArrayList;
import java.util.List;

public class SearchVo extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	
	private List<Medications> medications = new ArrayList<>();
	private List<DiseaseEntity> diseaseEntitys = new ArrayList<>();
	private List<Examine> examines = new ArrayList<>();
	private List<ExamineApply> examineApplys = new ArrayList<>();
	private List<Inspections> inspections = new ArrayList<>();
	public List<Medications> getMedications() {
		return medications;
	}
	public List<DiseaseEntity> getDiseaseEntitys() {
		return diseaseEntitys;
	}
	public List<Examine> getExamines() {
		return examines;
	}
	public List<ExamineApply> getExamineApplys() {
		return examineApplys;
	}
	public List<Inspections> getInspections() {
		return inspections;
	}
	public void setMedications(List<Medications> medications) {
		this.medications = medications;
	}
	public void setDiseaseEntitys(List<DiseaseEntity> diseaseEntitys) {
		this.diseaseEntitys = diseaseEntitys;
	}
	public void setExamines(List<Examine> examines) {
		this.examines = examines;
	}
	public void setExamineApplys(List<ExamineApply> examineApplys) {
		this.examineApplys = examineApplys;
	}
	public void setInspections(List<Inspections> inspections) {
		this.inspections = inspections;
	}
	
	
}
