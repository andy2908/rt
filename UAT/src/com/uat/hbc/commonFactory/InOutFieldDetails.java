package com.uat.hbc.commonFactory;

public class InOutFieldDetails {
	private String inputFieldId;
	private String inputFieldDataType;
	private String inputFieldValue;
	private Integer order;
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getInputFieldId() {
		return inputFieldId;
	}
	public void setInputFieldId(String inputFieldId) {
		this.inputFieldId = inputFieldId;
	}
	public String getInputFieldDataType() {
		return inputFieldDataType;
	}
	public void setInputFieldDataType(String inputFieldDataType) {
		this.inputFieldDataType = inputFieldDataType;
	}
	public String getInputFieldValue() {
		return inputFieldValue;
	}
	public void setInputFieldValue(String inputFieldValue) {
		this.inputFieldValue = inputFieldValue;
	}
	@Override
	public String toString() {
		return "InOutFieldDetails [inputFieldId=" + inputFieldId
				+ ", inputFieldDataType=" + inputFieldDataType
				+ ", inputFieldValue=" + inputFieldValue + "]";
	}
	public InOutFieldDetails setParameter(String fieldId, String fieldDataType,String fieldValue,String fieldSize,int order)
	{

		InOutFieldDetails inputObj = new InOutFieldDetails();
		inputObj.setInputFieldId(fieldId);
		inputObj.setInputFieldDataType(fieldDataType);
		inputObj.setInputFieldValue(fieldValue);
		inputObj.setOrder(order);
		return inputObj;
	}
}
