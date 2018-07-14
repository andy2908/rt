package com.uat.hbc.insurance.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.insurance.dao.MotorEntryDao;
import com.uat.hbc.insurance.model.MotorEntryModel;

@Service
public class MotorEntryService {
	@Autowired
	private MotorEntryDao motorEntryDao;
	MotorEntryModel motorEntryModel=new MotorEntryModel();

	public Result insertUpdatePARecordPKGwithArray(String appId, String opFlag, String applicantId, String gicId,
			String proposalId, String productId, String polPeriod, String startDate, String endDate, String idv,
			String odTotal, String tpTotal, String totalPrem, String cgst, String sgst, String igst, String ugst,
			String totalTax, String netPrem, String serviceCharge, String totalPay, String payMode, String rtoId,
			String zoneId, String varId, String mfrYear, String mfrMonth, String regDate, String fuelType, String regNo,
			String chassisNo, String engineNo, String passengers, String cc, String bodyType, String custType,
			String insType, String policyNo, String coverNoteNo, String salutationId, String firstName,
			String middleName, String lastName, String dob, String aadharNo, String panNo, String contactNo1,String contactNo2,
			String emailId, String gender, String bankId, String accountNo, String otherDet, String addType,
			String addName, String addId, String house, String houseId, String street, String streetId, String landmark,
			String landmarkId, String ratingType, String ratingId, String ratingVal, String valId, String valDate,
			String rate, String amount, String ratingDetails, String hbUserId, String commRate, String commIncentive, String crid,
			String commAmount, String nomType, String nomInitialId, String nomFirstName, String nomMiddleName,
			String nomLastName, String nomDob, String nomAadharNo, String nomGender, String nomRelId,
			String nomContactNo, String nomEmailId, String nomAreaId, String nomHouse,
			String nomHouseId, String nomStreet, String nomStreetId, String nomLandmark, String nomLandmarkId,
			String isPrevIns, String prevGicId, String prevProdId, String prevPolicyType, String prevPeriod,
			String prevStartDate, String prevEndDate, String prevIdv, String prevInsType, String prevPolicyNo,
			String prevCovernoteNo, String prevOwnerType, String rcBookNameTransfer, String rcBookNameTransferDate,
			String insNameTransfer, String insNameTransferDate, String prevNcb, String prevIsClaim, String prevClaimNo,
			String prevClaimDate, String prevClaimAmount, String finId, String finType, String finCityId, String isInsp,
			String contactPersonName, String inspContactNo, String inspAddId, String inspHouse, String inspHouseId,
			String inspStreet, String inspStreetId, String inspLandmark, String inspLandmarkId, String payBankId,
			String payChqUtrNo, String payChqTranDate, String remarks,String branchId,String userId,String userDesc) {
		
		motorEntryModel.setAppId(appId);
		motorEntryModel.setApplicantId(applicantId);
		motorEntryModel.setGicId(gicId);
		motorEntryModel.setProposalId(proposalId);
		motorEntryModel.setProductId(productId);
		motorEntryModel.setPolPeriod(polPeriod);
		motorEntryModel.setStartDate(startDate);
		motorEntryModel.setEndDate(endDate);
		motorEntryModel.setIdv(idv);
		motorEntryModel.setOdTotal(odTotal);
		motorEntryModel.setTpTotal(tpTotal);
		motorEntryModel.setTotalPrem(totalPrem);
		motorEntryModel.setCgst(cgst);
		motorEntryModel.setSgst(sgst);
		motorEntryModel.setIgst(igst);
		motorEntryModel.setUgst(ugst);
		motorEntryModel.setTotalTax(totalTax);
		motorEntryModel.setNetPrem(netPrem);
		motorEntryModel.setServiceCharge(serviceCharge);
		motorEntryModel.setTotalPay(totalPay);
		motorEntryModel.setPayMode(payMode);
		motorEntryModel.setRtoId(rtoId);
		motorEntryModel.setZoneId(zoneId);
		motorEntryModel.setVarId(varId);
		motorEntryModel.setMfrYear(mfrYear);
		motorEntryModel.setMfrMonth(mfrMonth);
		motorEntryModel.setRegDate(regDate);
		motorEntryModel.setFuelType(fuelType);
		motorEntryModel.setRegNo(regNo);
		motorEntryModel.setChassisNo(chassisNo);
		motorEntryModel.setEngineNo(engineNo);
		motorEntryModel.setPassengers(passengers);
		motorEntryModel.setCc(cc);
		motorEntryModel.setBodyType(bodyType);
		motorEntryModel.setCustType(custType);
		motorEntryModel.setInsType(insType);
		motorEntryModel.setPolicyNo(policyNo);
		motorEntryModel.setCoverNoteNo(coverNoteNo);
		motorEntryModel.setSalutationId(salutationId);
		motorEntryModel.setFirstName(firstName);
		motorEntryModel.setMiddleName(middleName);
		motorEntryModel.setLastName(lastName);
		motorEntryModel.setDob(dob);
		motorEntryModel.setAadharNo(aadharNo);
		motorEntryModel.setPanNo(panNo);
		motorEntryModel.setContactNo1(contactNo1);
		motorEntryModel.setContactNo2(contactNo2);
		motorEntryModel.setEmailId(emailId);
		motorEntryModel.setGender(gender);
		motorEntryModel.setBankId(bankId);
		motorEntryModel.setAccountNo(accountNo);
		motorEntryModel.setOtherDet(otherDet);
		motorEntryModel.setAddType(addType.split("~"));
		motorEntryModel.setAddName(addName.split("~"));
		motorEntryModel.setAddId(Arrays.stream(addId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setHouse(house.split("~"));
		motorEntryModel.setHouseId(Arrays.stream(houseId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setStreet(street.split("~"));
		motorEntryModel.setStreetId(Arrays.stream(streetId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setLandmark(landmark.split("~"));
		motorEntryModel.setLandmarkId(Arrays.stream(landmarkId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setRatingType(Arrays.stream(ratingType.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setRatingId(Arrays.stream(ratingId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setRatingVal(ratingVal.split("~"));
		motorEntryModel.setValId(Arrays.stream(valId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setValDate(Arrays.stream(valDate.split("~")).map(s -> {
			return AllUtils.getFormattedDateOracle(s);
		}).toArray(String[]::new));
		motorEntryModel.setRate(Arrays.stream(rate.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setAmount(Arrays.stream(amount.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setRatingDetails(ratingDetails.split("~"));
		motorEntryModel.setHbUserId(Arrays.stream(hbUserId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setCommRate(Arrays.stream(commRate.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setCommIncentive(Arrays.stream(commIncentive.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setCrid(Arrays.stream(crid.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setCommAmount(Arrays.stream(commAmount.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setNomType(Arrays.stream(nomType.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setNomInitialId(Arrays.stream(nomInitialId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setNomFirstName(nomFirstName.split("~"));
		motorEntryModel.setNomMiddleName(nomMiddleName.split("~"));
		motorEntryModel.setNomLastName(nomLastName.split("~"));
		motorEntryModel.setNomDob(Arrays.stream(nomDob.split("~")).map(s -> {
			return AllUtils.getFormattedDateOracle(s);
		}).toArray(String[]::new));
		motorEntryModel.setNomAadharNo(nomAadharNo.split("~"));
		motorEntryModel.setNomGender(nomGender.split("~"));
		motorEntryModel.setNomRelId(Arrays.stream(nomRelId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setNomContactNo(nomContactNo.split("~"));
		motorEntryModel.setNomEmailId(nomEmailId.split("~"));
		motorEntryModel.setNomAreaId(Arrays.stream(nomAreaId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setNomHouse(nomHouse.split("~"));
		motorEntryModel.setNomHouseId(Arrays.stream(nomHouseId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setNomStreet(nomStreet.split("~"));
		motorEntryModel.setNomStreetId(Arrays.stream(nomStreetId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setNomLandmark(nomLandmark.split("~"));
		motorEntryModel.setNomLandmarkId(Arrays.stream(nomLandmarkId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setIsPrevIns(isPrevIns);
		motorEntryModel.setPrevGicId(prevGicId);
		motorEntryModel.setPrevProdId(prevProdId);
		motorEntryModel.setPrevPolicyType(prevPolicyType);
		motorEntryModel.setPrevPeriod(prevPeriod);
		motorEntryModel.setPrevStartDate(prevStartDate);
		motorEntryModel.setPrevEndDate(prevEndDate);
		motorEntryModel.setPrevIdv(prevIdv);
		motorEntryModel.setPrevInsType(prevInsType);
		motorEntryModel.setPrevPolicyNo(prevPolicyNo);
		motorEntryModel.setPrevCovernoteNo(prevCovernoteNo);
		motorEntryModel.setPrevOwnerType(prevOwnerType);
		motorEntryModel.setRcBookNameTransfer(rcBookNameTransfer);
		motorEntryModel.setRcBookNameTransferDate(rcBookNameTransferDate);
		motorEntryModel.setInsNameTransfer(insNameTransferDate);
		motorEntryModel.setInsNameTransferDate(insNameTransferDate);
		motorEntryModel.setPrevNcb(prevNcb);
		motorEntryModel.setPrevIsClaim(prevIsClaim);
		motorEntryModel.setPrevClaimNo(Arrays.stream(prevClaimNo.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setPrevClaimDate(Arrays.stream(prevClaimDate.split("~")).map(s -> {
			return AllUtils.getFormattedDateOracle(s);
		}).toArray(String[]::new));
		motorEntryModel.setPrevClaimAmount(Arrays.stream(prevClaimAmount.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		motorEntryModel.setFinId(finId);
		motorEntryModel.setFinType(finType);
		motorEntryModel.setFinCityId(finCityId);
		motorEntryModel.setIsInsp(isInsp);
		motorEntryModel.setContactPersonName(contactPersonName);
		motorEntryModel.setInspContactNo(inspContactNo);
		motorEntryModel.setInspAddId(inspAddId);
		motorEntryModel.setInspHouse(inspHouse);
		motorEntryModel.setInspHouseId(inspHouseId);
		motorEntryModel.setInspStreet(inspStreet);
		motorEntryModel.setInspStreetId(inspStreetId);
		motorEntryModel.setInspLandmark(inspLandmark);
		motorEntryModel.setInspLandmarkId(inspLandmarkId);
		motorEntryModel.setPayBankId(payBankId);
		motorEntryModel.setPayChqUtrNo(payChqUtrNo);
		motorEntryModel.setPayChqTranDate(payChqTranDate);
		motorEntryModel.setRemarks(remarks);
		
		return motorEntryDao.insertUpdatePARecordPKGwithArray(motorEntryModel,opFlag, userId, branchId, userDesc);
	}
}
