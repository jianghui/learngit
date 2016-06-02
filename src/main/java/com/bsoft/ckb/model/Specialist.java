package com.bsoft.ckb.model;

public class Specialist extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String specialistId;
	private String specialistName;
	private String specialistEncode;
	public String getSpecialistId() {
		return specialistId;
	}
	public void setSpecialistId(String specialistId) {
		this.specialistId = specialistId;
	}
	public String getSpecialistName() {
		return specialistName;
	}
	public void setSpecialistName(String specialistName) {
		this.specialistName = specialistName;
	}
	public String getSpecialistEncode() {
		return specialistEncode;
	}
	public void setSpecialistEncode(String specialistEncode) {
		this.specialistEncode = specialistEncode;
	}
	
	
}
