package com.uat.hbc.common.service;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.dao.RegistrationDao;
import com.uat.hbc.common.model.RegistrationModel;
import com.uat.hbc.common.model.Result;


@Service
public class RegistrationService {
	@Autowired
	RegistrationDao  registrationDao;
	RegistrationModel registrationModel = new RegistrationModel();
	
	public Result insertUpdateRecord(String procName,String opflag, String posMstId,
			String titleID, String firstName, String middleName, String lastName,
			String firstNameOl, String middleNameOl, String lastNameOl, String fatherName,
			String motherName,String gender, String maritalStatus, String birthDate,String aadharEnroll,String aadharNo,String panNo,
			String religionId,String categoryId,String educationId,String mobNo,String phoneNo,String emailId,String adrType,String areaId,String house,String houseId,String street,String streetId,String landmark,String landmarkId,String uploadName,String docPath,String docType,String ftpId,String strId, String branchid, String userid, String userdesc,String status) {
		
		String[] idArr = posMstId.split(",");
		String srNo = "";
		srNo=srNo.trim();
		int srno = 1;
		for (int i = 0; i < idArr.length; i++) {
			System.out.println("Value of i::" + i);
			srNo += srno+"~" ;
			srno++;
		}
		System.out.println("srNo::" + srNo);
		
//		registrationModel.setAdrType(Arrays.stream(adrType.split("~"))
//				.map(s -> {
//					try {
//						return new BigDecimal(s);
//					} catch (NumberFormatException e) {
//						return BigDecimal.ZERO;
//					}
//				}).toArray(String[]::new));
		registrationModel.setAadharEnroll(aadharEnroll);
		registrationModel.setAadharNo(aadharNo);
		registrationModel.setPanNo(panNo);
		registrationModel.setPosMstID(posMstId);
		registrationModel.setTitleId(titleID);
		registrationModel.setFirstName(firstName);
		registrationModel.setMiddleName(middleName);
		registrationModel.setLastName(lastName);
		registrationModel.setFirstNameOl(firstNameOl);
		registrationModel.setMiddleNameOl(middleNameOl);
		registrationModel.setLastNameOl(lastNameOl);
		registrationModel.setFatherName(fatherName);
		registrationModel.setMotherName(motherName);
		registrationModel.setGender(gender);
		registrationModel.setMaritalStatis(maritalStatus);
		registrationModel.setBirthDate(birthDate); 
		registrationModel.setReligionId(religionId);
		registrationModel.setCategoryId(categoryId);
		registrationModel.setEducationId(educationId);
		registrationModel.setMobNo(mobNo);
		registrationModel.setPhoneNo(phoneNo);
		registrationModel.setEmailId(emailId);
		registrationModel.setUploadName(uploadName.split("~"));
		registrationModel.setDocPath(docPath.split("~"));
		registrationModel.setDocType(docType.split("~"));
		registrationModel.setStrID(strId);
		registrationModel.setStatus(status);
		registrationModel.setAdrType(adrType.split("~"));
		
		registrationModel.setAreaId(Arrays.stream(areaId.split("~"))
				.map(s -> {
					try {
						return new BigDecimal(s);
					} catch (NumberFormatException e) {
						return BigDecimal.ZERO;
					}
				}).toArray(BigDecimal[]::new));
		
		
		registrationModel.setFtpId((Arrays.stream(ftpId.split("~")))
				.map(s -> {
					try {
						return new BigDecimal(s);
					} catch (NumberFormatException e) {
						return BigDecimal.ZERO;
					}
				}).toArray(BigDecimal[]::new));
		
		
		registrationModel.setHouse(house.split("~"));
				
		registrationModel.setHouseId(Arrays.stream(houseId.split("~"))
				.map(s -> {
					try {
						return new BigDecimal(s);
					} catch (NumberFormatException e) {
						return BigDecimal.ZERO;
					}
				}).toArray(BigDecimal[]::new));
		
		registrationModel.setStreet(street.split("~"));
				
		registrationModel.setStreetId(Arrays.stream(streetId.split("~"))
				.map(s -> {
					try {
						return new BigDecimal(s);
					} catch (NumberFormatException e) {
						return BigDecimal.ZERO;
					}
				}).toArray(BigDecimal[]::new));
		
		registrationModel.setLandmark(landmark.split("~"));
		
		registrationModel.setLandmarkId(Arrays.stream(landmarkId.split("~"))
				.map(s -> {
					try {
						return new BigDecimal(s);
					} catch (NumberFormatException e) {
						return BigDecimal.ZERO;
					}
				}).toArray(BigDecimal[]::new));
		
		
		System.out.println("registrationModel"+registrationModel);
		
		Result resObj;
		resObj = registrationDao.insertUpdateRecord(
				registrationModel, procName, opflag, userid,
				branchid, userdesc);
		return resObj;
	}
}
