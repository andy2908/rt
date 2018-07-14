package com.uat.hbc.insurance.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.model.Result;
import com.uat.hbc.insurance.service.PAEntryService;



@Controller
public class PAEntryController {
	@Autowired
	PAEntryService paEntryService;

	@RequestMapping("/PAEntry")
	public ModelAndView callJSP(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		String appId = req.getParameter("appId");
		model.addObject("appId", appId);
		model.setViewName("common/PAEntry");
		return model;
	}

	@RequestMapping(value = "/paEntryController", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result UploadAttLog(HttpServletRequest request, HttpServletResponse res) {
		String appId = request.getParameter("appId");
		String applicantId = request.getParameter("applicantId");
		String gicId = request.getParameter("gicId");
		String proposalId = request.getParameter("proposalId");
		String productId = request.getParameter("productId");
		String period = request.getParameter("period");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String policyNo = request.getParameter("policyNo");
		String cityId = request.getParameter("cityId");
		String zoneId = request.getParameter("zoneId");
		String planId = request.getParameter("planId");
		String totalSI = request.getParameter("totalSI");
		String totalPrem = request.getParameter("totalPrem");
		String cgst = request.getParameter("cgst");
		String sgst = request.getParameter("sgst");
		String igst = request.getParameter("igst");
		String ugst = request.getParameter("ugst");
		String tax = request.getParameter("tax");
		String netPrem = request.getParameter("netPrem");
		String serviceCharge = request.getParameter("serviceCharge");
		String totalPayment = request.getParameter("totalPayment");
		String payMode = request.getParameter("payMode");
		String payId = request.getParameter("payId");
		String initialId = request.getParameter("initialId");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String aadharNo = request.getParameter("aadharNo");
		String panNo = request.getParameter("panNo");
		String contactNo = request.getParameter("contactNo");
		String emailId = request.getParameter("emailId");
		String disease = request.getParameter("disease");
		String occupationId = request.getParameter("occupationId");
		String relationId = request.getParameter("relationId");
		String annualIncome = request.getParameter("annualIncome");
		String sumInsA = request.getParameter("sumInsA");
		String premA = request.getParameter("premA");
		String sumInsB = request.getParameter("sumInsB");
		String premB = request.getParameter("premB");
		String sumInsC = request.getParameter("sumInsC");
		String premC = request.getParameter("premC");
		String sumInsD = request.getParameter("sumInsD");
		String premD = request.getParameter("premD");
		String sumInsABCD = request.getParameter("sumInsABCD");
		String addrId = request.getParameter("addrId");
		String areaId = request.getParameter("areaId");
		String house = request.getParameter("house");
		String houseId = request.getParameter("houseId");
		String street = request.getParameter("street");
		String streetId = request.getParameter("streetId");
		String landmark = request.getParameter("landmark");
		String landmarkId = request.getParameter("landmarkId");
		String prevMemberNo = request.getParameter("prevMemberNo");
		String prevGicId = request.getParameter("prevGicId");
		String prevProdId = request.getParameter("prevProdId");
		String prevPolicyType = request.getParameter("prevPolicyType");
		String prevPeriod = request.getParameter("prevPeriod");
		String prevStartDate = request.getParameter("prevStartDate");
		String prevEndDate = request.getParameter("prevEndDate");
		String prevTotalSumIns = request.getParameter("prevTotalSumIns");
		String prevInsType = request.getParameter("prevInsType");
		String prevPolicyNo = request.getParameter("prevPolicyNo");
		String prevNcb = request.getParameter("prevNcb");
		String nomId = request.getParameter("nomId");
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
		String nomAddrId = request.getParameter("nomAddrId");
		String nomAreaId = request.getParameter("nomAreaId");
		String nomHouse = request.getParameter("nomHouse");
		String nomHouseId = request.getParameter("nomHouseId");
		String nomStreet = request.getParameter("nomStreet");
		String nomStreetId = request.getParameter("nomStreetId");
		String nomLandmark = request.getParameter("nomLandmark");
		String nomLandmarkId = request.getParameter("nomLandmarkId");
		String memberNo = request.getParameter("memberNo");
		String addOn = request.getParameter("addOn");
		String addOnSumIns = request.getParameter("addOnSumIns");
		String addOnPrem = request.getParameter("addOnPrem");
		String userId = request.getParameter("userId");
		String branchId = request.getParameter("branchId");
		String userDesc = request.getParameter("userDesc");
		String statusVal = request.getParameter("statusVal");
		Result resObj = paEntryService.insertUpdatePARecordPKGwithArray(appId, applicantId, gicId, proposalId, productId,
				period, startDate, endDate, policyNo, cityId, zoneId, planId, totalSI, totalPrem, cgst, sgst, igst,
				ugst, tax, netPrem, serviceCharge, totalPayment, payMode, payId, initialId, firstName, middleName,
				lastName, dob, gender, aadharNo, panNo, contactNo, emailId, disease, occupationId, relationId,
				annualIncome, sumInsA, premA, sumInsB, premB, sumInsC, premC, sumInsD, premD, sumInsABCD, addrId,
				areaId, house, houseId, street, streetId, landmark, landmarkId, prevMemberNo, prevGicId, prevProdId,
				prevPolicyType, prevPeriod, prevStartDate, prevEndDate, prevTotalSumIns, prevInsType, prevPolicyNo,
				prevNcb, nomId, nomInitialId, nomFirstName, nomMiddleName, nomLastName, nomDob, nomAadharNo,nomGender, nomRelId,
				nomContactNo, nomEmailId, nomAddrId, nomAreaId, nomHouse, nomHouseId, nomStreet, nomStreetId,
				nomLandmark, nomLandmarkId, memberNo,addOn, addOnSumIns, addOnPrem, userId, branchId, userDesc,statusVal);
		return resObj;
	}

}
