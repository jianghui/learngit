package com.bsoft.ckb.model;

import java.util.ArrayList;
import java.util.List;

public class InspectionSorts extends BaseDO {

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = -2929057943219834558L;
	
	private String sort;
	private String sortName;
	private String parentSort;
	private List<InspectionSorts> childs = new ArrayList<>();
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
	public List<InspectionSorts> getChilds() {
		return childs;
	}
	public void setChilds(List<InspectionSorts> childs) {
		this.childs = childs;
	}
	
}
