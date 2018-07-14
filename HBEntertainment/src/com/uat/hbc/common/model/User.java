package com.uat.hbc.common.model;

public class User {
	
	private String userId;
	private String userCode;
	private String userName;
	private String userNameOL;
	private String ipAddress;
	private String macAddress;
	private String workingDate;
	private String deptId;
	private String mobileNo_P;
	private String mobileNo_O;
	private String emailId_P;
	private String emailId_O;
	private String masterType;
	private String userDesc;
	private String branchId;
	
	public String getBranchId() { 
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getUserDesc() {
		return userName.substring(0, 3);
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNameOL() {
		return userNameOL;
	}
	public void setUserNameOL(String userNameOL) {
		this.userNameOL = userNameOL;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getWorkingDate() {
		return workingDate;
	}
	public void setWorkingDate(String workingDate) {
		this.workingDate = workingDate;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getMobileNo_P() {
		return mobileNo_P;
	}
	public void setMobileNo_P(String mobileNo_P) {
		this.mobileNo_P = mobileNo_P;
	}
	public String getMobileNo_O() {
		return mobileNo_O;
	}
	public void setMobileNo_O(String mobileNo_O) {
		this.mobileNo_O = mobileNo_O;
	}
	public String getEmailId_P() {
		return emailId_P;
	}
	public void setEmailId_P(String emailId_P) {
		this.emailId_P = emailId_P;
	}
	public String getEmailId_O() {
		return emailId_O;
	}
	public void setEmailId_O(String emailId_O) {
		this.emailId_O = emailId_O;
	}
	public String getMasterType() {
		return masterType;
	}
	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", userNameOL="
				+ userNameOL + ", ipAddress=" + ipAddress + ", macAddress=" + macAddress + ", workingDate="
				+ workingDate + ", deptId=" + deptId + ", mobileNo_P=" + mobileNo_P + ", mobileNo_O=" + mobileNo_O
				+ ", emailId_P=" + emailId_P + ", emailId_O=" + emailId_O + ", masterType=" + masterType + "]";
	}
}
