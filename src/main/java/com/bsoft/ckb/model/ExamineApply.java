package com.bsoft.ckb.model;

public class ExamineApply extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String examineAppCode;
	private String examineAppName;
	private String examineCode;
	private String examineName;
	public String getExamineAppCode() {
		return examineAppCode;
	}
	public String getExamineAppName() {
		return examineAppName;
	}
	public void setExamineAppCode(String examineAppCode) {
		this.examineAppCode = examineAppCode;
	}
	public void setExamineAppName(String examineAppName) {
		this.examineAppName = examineAppName;
	}
	public String getExamineCode() {
		return examineCode;
	}
	public String getExamineName() {
		return examineName;
	}
	public void setExamineCode(String examineCode) {
		this.examineCode = examineCode;
	}
	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

}
