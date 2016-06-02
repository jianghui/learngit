package com.bsoft.ckb.model;

import java.util.ArrayList;
import java.util.List;

public class ExamineSort extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 6462536205101933150L;
	private String sort;
	private String sortName;
	private String parentSort;
	private Integer type;
	private List<ExamineSort> childs = new ArrayList<>();
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getParentSort() {
		return parentSort;
	}
	public void setParentSort(String parentSort) {
		this.parentSort = parentSort;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<ExamineSort> getChilds() {
		return childs;
	}
	public void setChilds(List<ExamineSort> childs) {
		this.childs = childs;
	}
	
}
