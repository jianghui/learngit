package com.bsoft.ckb.model;

public class MedicationInstruction extends BaseDO{
	
	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 399730068640943317L;
	private String ingredient; //药品成分
	private String character; //形状
	private String indication;//
	private String adverseReactions;
	private String contraindication;
	private String specialDrugs;
	private String medicationForChildren;
	private String medicationForElderly;
	private String interaction;
	private String overdose;
	private String pharma_toxicology;
	private String pharmacokinetic;
	private String periodOfValidity;
	private String approvalNumber;
	private String commodityName;
	private String originName;
	private String medicationName;
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getAdverseReactions() {
		return adverseReactions;
	}
	public void setAdverseReactions(String adverseReactions) {
		this.adverseReactions = adverseReactions;
	}
	public String getContraindication() {
		return contraindication;
	}
	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}
	public String getSpecialDrugs() {
		return specialDrugs;
	}
	public void setSpecialDrugs(String specialDrugs) {
		this.specialDrugs = specialDrugs;
	}
	public String getMedicationForChildren() {
		return medicationForChildren;
	}
	public void setMedicationForChildren(String medicationForChildren) {
		this.medicationForChildren = medicationForChildren;
	}
	public String getMedicationForElderly() {
		return medicationForElderly;
	}
	public void setMedicationForElderly(String medicationForElderly) {
		this.medicationForElderly = medicationForElderly;
	}
	public String getInteraction() {
		return interaction;
	}
	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}
	public String getOverdose() {
		return overdose;
	}
	public void setOverdose(String overdose) {
		this.overdose = overdose;
	}
	public String getPharma_toxicology() {
		return pharma_toxicology;
	}
	public void setPharma_toxicology(String pharma_toxicology) {
		this.pharma_toxicology = pharma_toxicology;
	}
	public String getPharmacokinetic() {
		return pharmacokinetic;
	}
	public void setPharmacokinetic(String pharmacokinetic) {
		this.pharmacokinetic = pharmacokinetic;
	}
	public String getPeriodOfValidity() {
		return periodOfValidity;
	}
	public void setPeriodOfValidity(String periodOfValidity) {
		this.periodOfValidity = periodOfValidity;
	}
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	
	
}
