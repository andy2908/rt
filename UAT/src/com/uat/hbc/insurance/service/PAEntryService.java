package com.uat.hbc.insurance.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;


import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.insurance.dao.PAEntryDao;
import com.uat.hbc.insurance.model.PAEntryModel;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

@Service
public class PAEntryService {
	@Autowired
	private PAEntryDao paEntryDao;
	PAEntryModel paEntryModel = new PAEntryModel();

	public Result insertUpdatePARecordPKGwithArray(String appId, String applicantId, String gicId, String proposalId,
			String productId, String period, String startDate, String endDate, String policyNo, String cityId,
			String zoneId, String planId, String totalSI, String totalPrem, String cgst, String sgst, String igst,
			String ugst, String tax, String netPrem, String serviceCharge, String totalPayment, String payMode,
			String payId, String initialId, String firstName, String middleName, String lastName, String dob,
			String gender, String aadharNo, String panNo, String contactNo, String emailId, String disease,
			String occupationId, String relationId, String annualIncome, String sumInsA, String premA, String sumInsB,
			String premB, String sumInsC, String premC, String sumInsD, String premD, String sumInsABCD, String addrId,
			String areaId, String house, String houseId, String street, String streetId, String landmark,
			String landmarkId, String prevMemberNo, String prevGicId, String prevProdId, String prevPolicyType,
			String prevPeriod, String prevStartDate, String prevEndDate, String prevTotalSumIns, String prevInsType,
			String prevPolicyNo, String prevNcb, String nomId, String nomInitialId, String nomFirstName,
			String nomMiddleName, String nomLastName, String nomDob, String nomAadharNo, String nomGender,
			String nomRelId, String nomContactNo, String nomEmailId, String nomAddrId, String nomAreaId,
			String nomHouse, String nomHouseId, String nomStreet, String nomStreetId, String nomLandmark,
			String nomLandmarkId, String memberNo, String addOn, String addOnSumIns, String addOnPrem, String userId,
			String branchId, String userDesc, String statusVal) {

		paEntryModel.setAppId(appId);
		paEntryModel.setApplicantId(applicantId);
		paEntryModel.setGicId(gicId);
		paEntryModel.setProposalId(proposalId);
		paEntryModel.setProductId(productId);
		paEntryModel.setPeriod(period);
		paEntryModel.setStartDate(startDate);
		paEntryModel.setEndDate(endDate);
		paEntryModel.setPolicyNo(policyNo);
		paEntryModel.setCityId(cityId);
		paEntryModel.setZoneId(zoneId);
		paEntryModel.setPlanId(planId);
		paEntryModel.setTotalSI(totalSI);
		paEntryModel.setTotalPrem(totalPrem);
		paEntryModel.setCgst(cgst);
		paEntryModel.setSgst(sgst);
		paEntryModel.setIgst(igst);
		paEntryModel.setIgst(igst);
		paEntryModel.setUgst(ugst);
		paEntryModel.setTax(tax);
		paEntryModel.setNetPrem(netPrem);
		paEntryModel.setServiceCharge(serviceCharge);
		paEntryModel.setTotalPayment(totalPayment);
		paEntryModel.setPayMode(payMode);
		paEntryModel.setPayId(payId);
		paEntryModel.setInitialId(Arrays.stream(initialId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setFirstName(firstName.split("~"));
		paEntryModel.setMiddleName(middleName.split("~"));
		paEntryModel.setLastName(lastName.split("~"));
		paEntryModel.setDob(Arrays.stream(dob.split("~")).map(s -> {
			return AllUtils.getFormattedDateOracle(s);
		}).toArray(String[]::new));
		paEntryModel.setGender(Arrays.stream(gender.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setAadharNo(aadharNo.split("~"));
		paEntryModel.setPanNo(panNo.split("~"));
		paEntryModel.setContactNo(contactNo.split("~"));
		paEntryModel.setEmailId(emailId.split("~"));
		paEntryModel.setDisease(disease.split("~"));
		paEntryModel.setOccupationId(Arrays.stream(occupationId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));

		paEntryModel.setRelationId(Arrays.stream(relationId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));

		paEntryModel.setAnnualIncome(Arrays.stream(annualIncome.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));

		paEntryModel.setSumInsA(Arrays.stream(sumInsA.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));

		paEntryModel.setPremA(Arrays.stream(premA.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setSumInsB(Arrays.stream(sumInsB.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setPremB(Arrays.stream(premB.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setSumInsC(Arrays.stream(sumInsC.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setPremC(Arrays.stream(premC.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setSumInsD(Arrays.stream(sumInsD.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setPremD(Arrays.stream(premD.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setSumInsABCD(Arrays.stream(sumInsABCD.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setAddrId(addrId);
		paEntryModel.setAreaId(areaId);
		paEntryModel.setHouse(houseId);
		paEntryModel.setHouseId(houseId);
		paEntryModel.setStreet(streetId);
		paEntryModel.setStreetId(streetId);
		paEntryModel.setLandmark(landmark);
		paEntryModel.setLandmarkId(landmarkId);
		paEntryModel.setPrevMemberNo(prevMemberNo.equals("") ? null : prevMemberNo.split("~"));
		paEntryModel.setPrevGicId(prevGicId.equals("") ? null : prevGicId.split("~"));
		paEntryModel.setPrevProdId(prevProdId.equals("") ? null :prevProdId.split("~"));
		paEntryModel.setPrevPolicyType(prevPolicyType.equals("") ? null :prevPolicyType.split("~"));
		paEntryModel.setPrevPeriod(prevPeriod.equals("") ? null :prevPeriod.split("~"));
		paEntryModel.setPrevStartDtae(Arrays.stream(prevStartDate.split("~")).map(s -> {
			return AllUtils.getFormattedDateOracle(s);
		}).toArray(String[]::new));
		paEntryModel.setPrevEndDate(Arrays.stream(prevEndDate.split("~")).map(s -> {
			return AllUtils.getFormattedDateOracle(s);
		}).toArray(String[]::new));
		paEntryModel.setPrevTotalSumIns(Arrays.stream(prevTotalSumIns.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paEntryModel.setPrevInsType(prevInsType.equals("") ? null :prevInsType.split("~"));
		paEntryModel.setPrevPolicyNo(prevPolicyNo.equals("") ? null :prevPolicyNo.split("~"));
		paEntryModel.setPrevNcb(prevNcb.equals("") ? null :prevNcb.split("~"));
		paEntryModel.setNomId(nomId);
		paEntryModel.setNomInitialId(nomInitialId);
		paEntryModel.setNomFirstName(nomFirstName);
		paEntryModel.setNomMiddleName(nomMiddleName);
		paEntryModel.setNomLastName(nomLastName);
		paEntryModel.setNomDob(nomDob);
		paEntryModel.setNomAadharNo(nomAadharNo);
		paEntryModel.setNomGender(nomGender);
		paEntryModel.setNomRelId(nomRelId);
		paEntryModel.setNomContactNo(nomContactNo);
		paEntryModel.setNomEmailId(nomEmailId);
		paEntryModel.setNomAddrId(nomAddrId);
		paEntryModel.setNomAreaId(nomAreaId);
		paEntryModel.setNomHouse(nomHouse);
		paEntryModel.setNomHouseId(nomHouseId);
		paEntryModel.setNomStreet(nomStreet);
		paEntryModel.setNomStreetId(nomStreetId);
		paEntryModel.setNomLandmark(nomLandmark);
		paEntryModel.setNomLandmarkId(nomLandmarkId);
		paEntryModel.setMemberNo(memberNo.equals("") ? null :memberNo.split("~"));
		paEntryModel.setAddOn(addOn.equals("") ? null :addOn.split("~"));
		paEntryModel.setAddOnSumIns(addOnSumIns.equals("") ? null :addOnSumIns.split("~"));
		paEntryModel.setAddOnPrem(addOnPrem.equals("") ? null :addOnPrem.split("~"));

		return paEntryDao.insertUpdatePARecordPKGwithArray(paEntryModel, userId, branchId, userDesc, statusVal);
	}
}
