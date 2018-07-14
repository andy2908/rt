package com.uat.hbc.insurance.model;

import java.math.BigDecimal;

import javax.xml.ws.Holder;

import com.bajajAllianz.WeoRecStrings40User;

public class BajajPayBean {

	String userId, password;
	Holder<BigDecimal> pTransactionIdInout;
	Holder<WeoRecStrings40User> pPaymentTransObjInout;
	Holder<String> policyNo;
	Holder<String> pErrorMsgOut;
	Holder<BigDecimal> pErrorCodeOut;
	String hbUserId="", userDesc="",branchId="", motorGroupResponseGicName="",motorGroupResponseGroupId="",motorGroupResponseSessionId="",motorGroupResponseGicId="", IPAddress="";
	String procedureName="";
	
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getMotorGroupResponseGicName() {
		return motorGroupResponseGicName;
	}
	public void setMotorGroupResponseGicName(String motorGroupResponseGicName) {
		this.motorGroupResponseGicName = motorGroupResponseGicName;
	}
	public String getHbUserId() {
		return hbUserId;
	}
	public void setHbUserId(String hbUserId) {
		this.hbUserId = hbUserId;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getMotorGroupResponseGroupId() {
		return motorGroupResponseGroupId;
	}
	public void setMotorGroupResponseGroupId(String motorGroupResponseGroupId) {
		this.motorGroupResponseGroupId = motorGroupResponseGroupId;
	}
	public String getMotorGroupResponseSessionId() {
		return motorGroupResponseSessionId;
	}
	public void setMotorGroupResponseSessionId(String motorGroupResponseSessionId) {
		this.motorGroupResponseSessionId = motorGroupResponseSessionId;
	}
	public String getMotorGroupResponseGicId() {
		return motorGroupResponseGicId;
	}
	public void setMotorGroupResponseGicId(String motorGroupResponseGicId) {
		this.motorGroupResponseGicId = motorGroupResponseGicId;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Holder<BigDecimal> getpTransactionIdInout() {
		return pTransactionIdInout;
	}
	public void setpTransactionIdInout(Holder<BigDecimal> pTransactionIdInout) {
		this.pTransactionIdInout = pTransactionIdInout;
	}
	public Holder<WeoRecStrings40User> getpPaymentTransObjInout() {
		return pPaymentTransObjInout;
	}
	public void setpPaymentTransObjInout(Holder<WeoRecStrings40User> pPaymentTransObjInout) {
		this.pPaymentTransObjInout = pPaymentTransObjInout;
	}
	public Holder<String> getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(Holder<String> policyNo) {
		this.policyNo = policyNo;
	}
	public Holder<String> getpErrorMsgOut() {
		return pErrorMsgOut;
	}
	public void setpErrorMsgOut(Holder<String> pErrorMsgOut) {
		this.pErrorMsgOut = pErrorMsgOut;
	}
	public Holder<BigDecimal> getpErrorCodeOut() {
		return pErrorCodeOut;
	}
	public void setpErrorCodeOut(Holder<BigDecimal> pErrorCodeOut) {
		this.pErrorCodeOut = pErrorCodeOut;
	}
	
}
