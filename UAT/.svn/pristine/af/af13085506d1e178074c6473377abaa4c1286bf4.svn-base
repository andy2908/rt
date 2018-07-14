package com.uat.hbc.insurance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.model.Result;
import com.uat.hbc.insurance.service.MotorEntryService;



@Controller
public class MotorEntryController {
	@Autowired
	MotorEntryService motorEntryService;

	@RequestMapping("/user/motorEntry")
	public ModelAndView callJSP(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		String appId = req.getParameter("appId");
		model.addObject("appId", appId);
		model.setViewName("common/motorEntry");
		return model;
	}

	@RequestMapping(value = "/user/motorEntryController", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result motorEntryController(HttpServletRequest request, HttpServletResponse res) {
		String appId = request.getParameter("appId");
		String opFlag = request.getParameter("opFlag");
		String applicantId = request.getParameter("applicantId");
		String gicId = request.getParameter("gicId");
		String proposalId = request.getParameter("proposalId");
		String productId = request.getParameter("productId");
		String polPeriod = request.getParameter("polPeriod");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String idv = request.getParameter("idv");
		String odTotal = request.getParameter("odTotal");
		String tpTotal = request.getParameter("tpTotal");
		String totalPrem = request.getParameter("totalPrem");
		String cgst = request.getParameter("cgst");
		String sgst = request.getParameter("sgst");
		String igst = request.getParameter("igst");
		String ugst = request.getParameter("ugst");
		String totalTax = request.getParameter("totalTax");
		String netPrem = request.getParameter("netPrem");
		String serviceCharge = request.getParameter("serviceCharge");
		String totalPay = request.getParameter("totalPay");
		String payMode = request.getParameter("payMode");
		String rtoId = request.getParameter("rtoId");
		String zoneId = request.getParameter("zoneId");
		String varId = request.getParameter("varId");
		String mfrYear = request.getParameter("mfrYear");
		String mfrMonth = request.getParameter("mfrMonth");
		String regDate = request.getParameter("regDate");
		String fuelType = request.getParameter("fuelType");
		String regNo = request.getParameter("regNo");
		String chassisNo = request.getParameter("chassisNo");
		String engineNo = request.getParameter("engineNo");
		String passengers = request.getParameter("passengers");
		String cc = request.getParameter("cc");
		String bodyType = request.getParameter("bodyType");
		String custType = request.getParameter("custType");
		String insType = request.getParameter("insType");
		String policyNo = request.getParameter("policyNo");
		String coverNoteNo = request.getParameter("coverNoteNo");
		String salutationId = request.getParameter("salutationId");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");
		String aadharNo = request.getParameter("aadharNo");
		String panNo = request.getParameter("panNo");
		String contactNo1 = request.getParameter("contactNo1");
		String contactNo2 = request.getParameter("contactNo2");
		String emailId = request.getParameter("emailId");
		String gender = request.getParameter("gender");
		String bankId = request.getParameter("bankId");
		String accountNo = request.getParameter("accountNo");
		String otherDet = request.getParameter("otherDet");
		String addType = request.getParameter("addType");
		String addName = request.getParameter("addName");
		String addId = request.getParameter("addId");
		String house = request.getParameter("house");
		String houseId = request.getParameter("houseId");
		String street = request.getParameter("street");
		String streetId = request.getParameter("streetId");
		String landmark = request.getParameter("landmark");
		String landmarkId = request.getParameter("landmarkId");
		String ratingType = request.getParameter("ratingType");
		String ratingId = request.getParameter("ratingId");
		String ratingVal = request.getParameter("ratingVal");
		String valId = request.getParameter("valId");
		String valDate = request.getParameter("valDate");
		String rate = request.getParameter("rate");
		String amount = request.getParameter("amount");
		String ratingDetails = request.getParameter("ratingDetails");
		String hbUserId = request.getParameter("hbUserId");
		String commRate = request.getParameter("commRate");
		String commIncentive = request.getParameter("commIncentive");
		String crid = request.getParameter("crid");
		String commAmount = request.getParameter("commAmount");
		String nomType = request.getParameter("nomType");
		String nomInitialId = request.getParameter("nomInitialId");
		String nomFirstName = request.getParameter("nomFirstName");
		String nomMiddleName = request.getParameter("nomMiddleName");
		String nomLastName = request.getParameter("nomLastName");
		String nomDob = request.getParameter("nomDob");
		String nomAadharNo = request.getParameter("nomAadharNo");
		String nomGender = request.getParameter("nomGender");
		String nomRelId = request.getParameter("nomRelId");
		String nomContactNo = request.getParameter("nomContactNo");
		String nomEmailId = request.getParameter("nomEmailId");
		String nomAreaId = request.getParameter("nomAreaId");
		String nomHouse = request.getParameter("nomHouse");
		String nomHouseId = request.getParameter("nomHouseId");
		String nomStreet = request.getParameter("nomStreet");
		String nomStreetId = request.getParameter("nomStreetId");
		String nomLandmark = request.getParameter("nomLandmark");
		String nomLandmarkId = request.getParameter("nomLandmarkId");
		String isPrevIns = request.getParameter("isPrevIns");
		String prevGicId = request.getParameter("prevGicId");
		String prevProdId = request.getParameter("prevProdId");
		String prevPolicyType = request.getParameter("prevPolicyType");
		String prevPeriod = request.getParameter("prevPeriod");
		String prevStartDate = request.getParameter("prevStartDate");
		String prevEndDate = request.getParameter("prevEndDate");
		String prevIdv = request.getParameter("prevIdv");
		String prevInsType = request.getParameter("prevInsType");
		String prevPolicyNo = request.getParameter("prevPolicyNo");
		String prevCovernoteNo = request.getParameter("prevCovernoteNo");
		String prevOwnerType = request.getParameter("prevOwnerType");
		String rcBookNameTransfer = request.getParameter("rcBookNameTransfer");
		String rcBookNameTransferDate = request.getParameter("rcBookNameTransferDate");
		String insNameTransfer = request.getParameter("insNameTransfer");
		String insNameTransferDate = request.getParameter("insNameTransferDate");
		String prevNcb = request.getParameter("prevNcb");
		String prevIsClaim = request.getParameter("prevIsClaim");
		String prevClaimNo = request.getParameter("prevClaimNo");
		String prevClaimDate = request.getParameter("prevClaimDate");
		String prevClaimAmount = request.getParameter("prevClaimAmount");
		String finId = request.getParameter("finId");
		String finType = request.getParameter("finType");
		String finCityId = request.getParameter("finCityId");
		String isInsp = request.getParameter("isInsp");
		String contactPersonName = request.getParameter("contactPersonName");
		String inspContactNo = request.getParameter("inspContactNo");
		String inspAddId = request.getParameter("inspAddId");
		String inspHouse = request.getParameter("inspHouse");
		String inspHouseId = request.getParameter("inspHouseId");
		String inspStreet = request.getParameter("inspStreet");
		String inspStreetId = request.getParameter("inspStreetId");
		String inspLandmark = request.getParameter("inspLandmark");
		String inspLandmarkId = request.getParameter("inspLandmarkId");
		String payBankId = request.getParameter("payBankId");
		String payChqUtrNo = request.getParameter("payChqUtrNo");
		String payChqTranDate = request.getParameter("payChqTranDate");
		String remarks = request.getParameter("remarks");
		String branchId = request.getParameter("branchId");
		String userId = request.getParameter("userId");
		String userDesc = request.getParameter("userDesc");

		Result resObj = motorEntryService.insertUpdatePARecordPKGwithArray(appId, opFlag, applicantId, gicId, proposalId,
				productId, polPeriod, startDate, endDate, idv, odTotal, tpTotal, totalPrem, cgst, sgst, igst, ugst,
				totalTax, netPrem, serviceCharge, totalPay, payMode, rtoId, zoneId, varId, mfrYear, mfrMonth, regDate,
				fuelType, regNo, chassisNo, engineNo, passengers, cc, bodyType, custType, insType, policyNo,
				coverNoteNo, salutationId, firstName, middleName, lastName, dob, aadharNo, panNo, contactNo1,contactNo2, emailId,
				gender, bankId, accountNo, otherDet, addType, addName, addId, house, houseId, street, streetId,
				landmark, landmarkId, ratingType, ratingId, ratingVal, valId, valDate, rate, amount, ratingDetails,hbUserId,
				commRate, commIncentive, crid, commAmount, nomType, nomInitialId, nomFirstName, nomMiddleName,
				nomLastName, nomDob, nomAadharNo, nomGender, nomRelId, nomContactNo, nomEmailId, nomAreaId,
				nomHouse, nomHouseId, nomStreet, nomStreetId, nomLandmark, nomLandmarkId, isPrevIns, prevGicId,
				prevProdId, prevPolicyType, prevPeriod, prevStartDate, prevEndDate, prevIdv, prevInsType, prevPolicyNo,
				prevCovernoteNo, prevOwnerType, rcBookNameTransfer, rcBookNameTransferDate, insNameTransfer,
				insNameTransferDate, prevNcb, prevIsClaim, prevClaimNo, prevClaimDate, prevClaimAmount, finId, finType,
				finCityId, isInsp, contactPersonName, inspContactNo, inspAddId, inspHouse, inspHouseId, inspStreet,
				inspStreetId, inspLandmark, inspLandmarkId, payBankId, payChqUtrNo, payChqTranDate, remarks,branchId,userId,userDesc);

		return resObj;
	}
}
