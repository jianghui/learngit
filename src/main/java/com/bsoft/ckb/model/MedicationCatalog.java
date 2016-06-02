package com.bsoft.ckb.model;

public class MedicationCatalog extends BaseDO{
	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 399730068640943317L;
	private String medicationCatalogId; 
	private String medicationName; 
	private String barcode;
	private String originName;
	private String commodityName;
	private String specifications;
	public String getMedicationCatalogId() {
		return medicationCatalogId;
	}
	public void setMedicationCatalogId(String medicationCatalogId) {
		this.medicationCatalogId = medicationCatalogId;
	}
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	
	
}
