package com.bsoft.ckb.model;

public class Examine extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String examineCode;
	private String examineName;
	private Integer type;
	public String getExamineCode() {
		return examineCode;
	}
	public void setExamineCode(String examineCode) {
		this.examineCode = examineCode;
	}
	public String getExamineName() {
		return examineName;
	}
	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	
	

}
