package com.uat.hbc.insurance.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.dom.DOMSource;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.common.service.IntegrationSaveResponseService;
import com.google.gson.Gson;
import com.kotakGic.GetSignPolicyPDF;
import com.kotakGic.IPartnerIntegrationService;
import com.kotakGic.PartnerIntegrationService;
import com.uat.hbc.common.Utils;
import com.uat.hbc.insurance.dao.KotakDaoIntegration;
import com.uat.hbc.insurance.model.KotakCntrlrBean;
import com.uat.hbc.insurance.model.KotakParameterBean;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;
import com.uat.hbc.insurance.model.KotakSaveBean;

@Controller
public class KotakGicIntegrationController {

	@Autowired
	KotakDaoIntegration kotakDao;
	DOMSource domSource;
	// String path = System.getProperty("server.path");
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;

	HashMap data;
	HashMap inputParaList;
	HashMap<String, String> hashmapPartner;
	HashMap<String, String> payRequest;
	String jsonNames = "";
	org.jdom2.Element rootelement;
	org.jdom2.Element Block;
	org.jdom2.Element ProposalDetails;

	JSONObject jsonResult;
	// String quotationNumber , Email , customerID;
	// String
	// motorGroupResponseGroupId,motorGroupResponseSessionId,motorGroupResponseGicId,userId,userDesc,branchId;
	// String salt,hash, mihpayid;
	String Premium;
	String jsonN = "";
	String path = System.getProperty("user.home");
	// String txnid = "";

	KotakParameterBean pojo = new KotakParameterBean();
	KotakCntrlrBean kbean = new KotakCntrlrBean();
	KotakSaveBean savebn = new KotakSaveBean();

	
	
	@RequestMapping(value = "user/KotakGicIntegrationController", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String saveDetails2(HttpServletRequest request, HttpServletResponse res) {

		try {

			// KotakCntrlrBean kbean = new KotakCntrlrBean();

			String request_for = request.getParameter("request_for");
			String Proposal = "";
			String xmlParam = "";

			String userid = request.getParameter("userid");
			String pass = request.getParameter("pass");
			String businessType = request.getParameter("businessType");
			String rtoCode = request.getParameter("rtoCode");
			String vehicleMake = request.getParameter("vehicleMake");
			String makeCode = request.getParameter("makeCode");
			String vehicleModel = request.getParameter("vehicleModel");
			String VehicleModelCode = request.getParameter("VehicleModelCode");
			String VehicleVariant = request.getParameter("VehicleVariant");
			String VehicleVariantCode = request.getParameter("VehicleVariantCode");
			String DateofRegistration = request.getParameter("DateofRegistration");
			String idv = request.getParameter("idv");
			pojo.setIdvVehcl(idv);
			String returnToInvoice = request.getParameter("returnToInvoice");
			String EngineProtect = request.getParameter("EngineProtect");
			String RoadsideAssistance = request.getParameter("RoadsideAssistance");
			String DepreciationCover = request.getParameter("DepreciationCover");
			String ConsumableCover = request.getParameter("ConsumableCover");
			String PreviousClaim = request.getParameter("PreviousClaim");
			String PreviousYearNCBPercentage = request.getParameter("PreviousYearNCBPercentage");
			String NCBPercentage = request.getParameter("NCBPercentage");
			String near = request.getParameter("near");
			String EAR = request.getParameter("EAR");
			String cng = request.getParameter("cng");
			String NEARSumInsured = request.getParameter("NEARSumInsured");
			String EARSumInsured = request.getParameter("EARSumInsured");
			String CNGSumInsured = request.getParameter("CNGSumInsured");
			String accessKey = request.getParameter("accessKey");
			String typeOfPolicyHolder = request.getParameter("typeOfPolicyHolder");
			String CustomerType = request.getParameter("CustomerType");
			String PreviousPolicyTypeOrTypeofCover = request.getParameter("PreviousPolicyTypeOrTypeofCover");
			String TotalClaimCount = request.getParameter("TotalClaimCount");
			String PreviousPolicyExpiryDate = request.getParameter("PreviousPolicyExpiryDate");
			String VoluntaryDeductibleAmount = request.getParameter("VoluntaryDeductibleAmount");
			String IsPACoverForUnnamedPersons = request.getParameter("IsPACoverForUnnamedPersons");
			String NumberofPersonsUnnamed = request.getParameter("NumberofPersonsUnnamed");
			String CapitalSumInsuredPerPersonUnnamed = request.getParameter("CapitalSumInsuredPerPersonUnnamed");
			String IsPACoverForNamedPersons = request.getParameter("IsPACoverForNamedPersons");
			String NumberofPersonsNamed = request.getParameter("NumberofPersonsNamed");
			String CapitalSumInsuredPerPersonNamed = request.getParameter("CapitalSumInsuredPerPersonNamed");
			String IsPACoverForPaidDriver = request.getParameter("IsPACoverForPaidDriver");
			String NumberofPaidDrivers = request.getParameter("NumberofPaidDrivers");
			String SumInsuredForPaidDriver = request.getParameter("SumInsuredForPaidDriver");
			String WiderLegalLiabilityToPaidDriverCleanerConductorIMT28 = request.getParameter("WiderLegalLiabilityToPaidDriverCleanerConductorIMT28");
			String NoOfPersonWiderLegalLiability = request.getParameter("NoOfPersonWiderLegalLiability");
			String LegalLiabilityToEmployeesExcludingPaidDriverCleanerConductorIMT29 = request.getParameter("LegalLiabilityToEmployeesExcludingPaidDriverCleanerConductorIMT29");
			String NoofEmployees = request.getParameter("NoofEmployees");
			String MarketMovement = request.getParameter("MarketMovement");
			String BasicODDeviation = request.getParameter("BasicODDeviation");
			String AddOnDeviation = request.getParameter("AddOnDeviation");
			String InsuredProfession = request.getParameter("InsuredProfession");
			String InsuredCreditScore = request.getParameter("InsuredCreditScore");
			String InsureddrivingScore = request.getParameter("InsureddrivingScore");

			savebn.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
			savebn.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
			savebn.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
			savebn.setUserId(request.getParameter("userId"));
			savebn.setUserDesc(request.getParameter("userDesc"));
			savebn.setBranchId(request.getParameter("branchId"));
			savebn.setIPAddress(request.getParameter("IPAddress"));

			String procName = request.getParameter("procName");
			String tableName = request.getParameter("tableName");
			String rtoCity = request.getParameter("rtoCity");
			String zoneID = request.getParameter("zoneID");
			String policyType = request.getParameter("policyType");
			String varid = request.getParameter("varid");
			String customerType = request.getParameter("customerType");
			String vehicleClassCode = request.getParameter("vehicleClassCode");
			String manufacturerCode = request.getParameter("manufacturerCode");
			System.out.println("manufacturerCode = " + manufacturerCode);
			String occupationType = request.getParameter("occupationType");
			String financeId = request.getParameter("financeId");
			String prevGicId = request.getParameter("prevGicId");
			String carAreaId = request.getParameter("carAreaId");
			String corrsCarId = request.getParameter("corrsCarId");
			String OwnerDriverNomineeRelationship = request.getParameter("OwnerDriverNomineeRelationship");
			String OwnerDriverAppointeeRelationship = request.getParameter("OwnerDriverAppointeeRelationship");
			String titleId = request.getParameter("titleID");
			String marStatusId = request.getParameter("marStatusId");
			String finAgreeType = request.getParameter("finAgreeType");
			String covers = request.getParameter("covers");
			String covVal = request.getParameter("covVal");
			String covNo = request.getParameter("covNo");

			///////////// for policy number////////////////

			// DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			// Date date = new Date();
			// System.out.println("System Date ///////////// --------- > " +
			// dateFormat.format(date));
			// String sysDate = dateFormat.format(date);

			java.util.Date date = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sysDate = sdf.format(date);
			System.out.println("Current date in String Format: " + sysDate);

			DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
			Date time = new Date();
			System.out.println(timeFormat.format(time));
			String sysTime = timeFormat.format(time);

			String productType = request.getParameter("prdctType");
			System.out.println("productType = " + productType);
			String transactionType = request.getParameter("transactionType");
			System.out.println("transactionType = " + transactionType);
			String transactionDate = sysDate;
			System.out.println("transactionDate = " + transactionDate);
			String transactionAmount = request.getParameter("trnscAmt");
			System.out.println("transactionAmount = " + transactionAmount);
			String prefix = request.getParameter("prefix");
			System.out.println("prefix = " + prefix);
			// String ProductType = request.getParameter("productType");
			String proposalDate = sysDate;
			System.out.println("proposalDate = " + proposalDate);
			String proposalTime = sysTime;
			System.out.println("proposalTime = " + proposalTime);
			String proposerType = request.getParameter("proposerType");
			System.out.println("proposerType = " + proposerType);
			String customerName = request.getParameter("customerName");
			pojo.setName(customerName);
			System.out.println("customerName = " + customerName);
			
			String customerAddr = request.getParameter("customerAddr");
			pojo.setAddress(customerAddr);
			System.out.println("customerAddr = " + customerAddr);
			
			String phone = request.getParameter("phone");
			pojo.setPhone(phone);
			System.out.println("phone = " + phone);
			
			String mobile = request.getParameter("mobile");
			pojo.setPhone(mobile);
			System.out.println("mobile = " + mobile);
			
			String email = request.getParameter("email");
			pojo.setPhone(email);
			System.out.println("email = " + email);
			
			pojo.setPolIssOfc("Kotak Mahindra General Insurance Company Limited;"
					+ "8th Floor, Zone IV, Kotak Infinity,Building No.21, "
					+ "Infinity IT Park,Off Western Express Highway, "
					+ "General AK Vaidya Marg, Dindoshi,Malad (E),Mumbai - 400097");

			// String issueDate = request.getParameter("purchaseDt");
			// pojo.setPolIssOn(issueDate);
			//
			// System.out.println("issueDate = " + issueDate);
			String manufacturer = request.getParameter("manufacturer");
			pojo.setManufacturer(manufacturer);
			System.out.println("manufacturer = " + pojo.getManufacturer());

			String engineNos = request.getParameter("engineNos");
			pojo.setEngNo(engineNos);
			System.out.println("engineNos = " + pojo.getEngNo());
			
			String chassisNos = request.getParameter("chassisNos");
			pojo.setVehChassisNo(chassisNos);
			System.out.println("chassisNos = " + chassisNos);
			
			String vehicleRegistrationNumber = request.getParameter("vhclRegNo");
			System.out.println("vehicleRegistrationNumber = " + vehicleRegistrationNumber);
			pojo.setVehRegNo(vehicleRegistrationNumber);
			
			String vehicleRegistrationNumber1 = request.getParameter("vhclRegNo1");
			System.out.println("vehicleRegistrationNumber1 = " + vehicleRegistrationNumber1);
//			pojo.setVehRegNo1(vehicleRegistrationNumber);
			
			String vehicleRegistrationNumber2 = request.getParameter("vhclRegNo2");
			System.out.println("vehicleRegistrationNumber2 = " + vehicleRegistrationNumber2);
//			pojo.setVehRegNo2(vehicleRegistrationNumber2);
			
			String vehicleRegistrationNumber3 = request.getParameter("vhclRegNo3");
			System.out.println("vehicleRegistrationNumber3 = " + vehicleRegistrationNumber3);
//			pojo.setVehRegNo3(vehicleRegistrationNumber3);
			
			String vehicleRegistrationNumber4 = request.getParameter("vhclRegNo4");
			System.out.println("vehicleRegistrationNumber4 = " + vehicleRegistrationNumber4);
//			pojo.setVehRegNo4(vehicleRegistrationNumber4);

			String ttlValOfVeh = request.getParameter("ttlValOfVeh");
			System.out.println("ttlValOfVeh = " + ttlValOfVeh);
			pojo.setTotalValVhcl(ttlValOfVeh);

			String biFuelKit = request.getParameter("bifuelK");
			System.out.println("biFuelKit = " + biFuelKit);
			String biFuelKitIDV = request.getParameter("bifuelKIDV");
			System.out.println("biFuelKitIDV = " + biFuelKitIDV);
			// String zeroDepreciation = request.getParameter("zeroDep");
			String zeroDepreciation = "No";
			// System.out.println("zeroDepreciation = " + zeroDepreciation);
			String consumables_Addon = request.getParameter("cnsmblAddon");
			System.out.println("consumables_Addon = " + consumables_Addon);
			String IMTNos = request.getParameter("imtNo");
			System.out.println("IMTNos = " + IMTNos);

			//////////////// end//////////////

			// switch (request_for) {
			// case "premium":

			inputParaList = new HashMap<>();
			inputParaList.put("PI_TABLE_NAME", tableName);
			System.out.println("tableName--->>> " + tableName);

			System.out.println("ProcName--->>> " + procName);

			System.out.println("rtoCity-->>>" + rtoCity);
			inputParaList.put("PI_Rto_City", Integer.parseInt(rtoCity));
			System.out.println("rtoCity-->>>" + rtoCity);

			inputParaList.put("PI_ZONE_ID", (zoneID));
			System.out.println("ZoneId-->>>" + zoneID);

			inputParaList.put("PI_PolicyType", Integer.parseInt(policyType));
			System.out.println("policyType--->>>> " + policyType);

			inputParaList.put("PI_VarID", Integer.parseInt(varid));
			System.out.println("variance--->>>> " + varid);

			inputParaList.put("PI_P_BusinessType", Integer.parseInt(businessType));
			System.out.println("businessType--->>>> " + businessType);

			inputParaList.put("PI_ProductID", Integer.parseInt(vehicleClassCode));
			System.out.println("vehicleClassCode--->>>> " + vehicleClassCode);

			inputParaList.put("PI_VehID", Integer.parseInt(manufacturerCode));
			System.out.println("manufacturerCode--->>>> " + manufacturerCode);

			inputParaList.put("PI_ModelID", VehicleModelCode);
			System.out.println("vehicleModelCode--->>>> " + VehicleModelCode);

			inputParaList.put("PI_CUST_TYPE", customerType);
			System.out.println("customerType--->>>> " + customerType);

			inputParaList.put("PI_OccupationID", occupationType);
			System.out.println("occupationType--->>>> " + occupationType);

			inputParaList.put("PI_FIN_ID", financeId);
			System.out.println("financeId--->>>> " + financeId);

			inputParaList.put("PI_PREV_GIC_ID", prevGicId);
			System.out.println("prevGicId--->>>> " + prevGicId);

			inputParaList.put("PI_Car_AreaID", carAreaId);
			System.out.println("carAreaId--->>>> " + carAreaId);

			inputParaList.put("PI_Corrs_AreaID", corrsCarId);
			System.out.println("corrsCarId--->>>> " + corrsCarId);

			inputParaList.put("PI_Nom_Rel", OwnerDriverNomineeRelationship);

			System.out.println("OwnerDriverNomineeRelationship--->>>> " + OwnerDriverNomineeRelationship);

			inputParaList.put("PI_Own_Dri_Appointee_Rel", OwnerDriverAppointeeRelationship);

			System.out.println("OwnerDriverAppointeeRelationship--->>>> " + OwnerDriverAppointeeRelationship);

			inputParaList.put("PI_Title_ID", titleId);
			System.out.println("titleId--->>>> " + titleId);

			inputParaList.put("PI_Mar_Status_ID", marStatusId);
			System.out.println("marStatusId--->>>> " + marStatusId);

			inputParaList.put("PI_FIN_AgreementType", finAgreeType);
			System.out.println("finAgreeType--->>>> " + finAgreeType);

			inputParaList.put("PI_ClaimNo", TotalClaimCount);
			System.out.println("PI_ClaimNo--->>>> " + TotalClaimCount);

			inputParaList.put("PI_COVERS", covers);
			System.out.println("cover--->>>> " + covers);

			inputParaList.put("PI_COV_VAL", covVal);
			System.out.println("CovVal------>> " + covVal);

			inputParaList.put("PI_COV_NO", covNo);
			System.out.println("CoverNo---->>>> " + covNo);
			System.out.println("inputParaList::" +inputParaList);
			jsonNames = kotakDao.getKotakData("PKG_MOTOR_CALC", procName, inputParaList);

			String jsonNamesArr[] = jsonNames.split("\\}\\]\\[\\{");

			jsonNames = jsonNamesArr[0];
			String coverList = "";
			if (jsonNamesArr.length > 1) {
				coverList = jsonNamesArr[1];
				coverList = "[{" + coverList;
				jsonNames = jsonNames + "}]";
			}

			System.out.println("jsonName controller::: " + jsonNames);
			System.out.println("coverList Controller::: " + coverList);

			String depriciationCover = "No", liabilityToPaidDriver = "No", NoPassToLLPaiddriver = "0", paUnnamed = "No",
					noOfPerunnamed = "0", unnamedPaSi = "0", tppdDiscount = "No", antiTheftdevice = "No",
					legalliabilityToEmployee = "No", NoOfPassengerForLLToEmployee = "0", roadSideAsstCover = "No",
					fiberGlassTankCover = "No", fiberGlassSI = "", drivingtution = "No", AAIMembship = "No",
					limitedToOwnPremise = "No", vehicleForHandicap = "No";

			if (!coverList.equals("")) {
				String coverId = "", coverNo = "", coverVal = "", coverStatus = "";
				JSONArray coverJson;

				coverJson = new JSONArray(coverList);

				for (int i = 0; i < coverJson.length(); ++i) {
					org.json.JSONObject obj = (org.json.JSONObject) coverJson.get(i);
					String gicId = "", gicCompany = "";
					if (!obj.isNull("HB_COVER_ID")) {
						coverId = "" + obj.getInt("HB_COVER_ID");
						System.out.println("1111111111 coverId==>>" + coverId);
					}
					if (!obj.isNull("COV_NO")) {
						coverNo = "" + obj.getInt("COV_NO");
						System.out.println("1111111111 coverNo==>>" + coverNo);
					}
					if (!obj.isNull("COVER_VAL")) {
						coverVal = "" + obj.getInt("COVER_VAL");
						System.out.println("1111111111 coverVal==>>" + coverVal);
					}
					if (!obj.isNull("COVER_STATUS")) {
						coverStatus = "" + obj.getString("COVER_STATUS");
						System.out.println("1111111111 COVER_STATUS==>>" + coverStatus);
						if (coverStatus.equalsIgnoreCase("yes")) {
							coverStatus = "True";
						} else {
							coverStatus = "False";
						}
					}

					if (coverId.equals("8")) {
						// System.out.println("cover 8------------>>"+coverId);
						// depriciationCover="Yes";
						IsPACoverForUnnamedPersons = coverStatus;
						NumberofPersonsUnnamed = coverNo;
						CapitalSumInsuredPerPersonUnnamed = coverVal;
						zeroDepreciation = coverStatus;
					}
					//
					if (coverId.equals("55")) {
						// System.out.println("cover 56------------>>"+coverId);
						// liabilityToPaidDriver="Yes";
						// NoPassToLLPaiddriver=coverNo;
						IsPACoverForUnnamedPersons = coverStatus;
						NumberofPersonsUnnamed = coverNo;
						CapitalSumInsuredPerPersonUnnamed = coverVal;
					}
					//
					if (coverId.equals("54")) {
						System.out.println("cover 54------------>>" + coverId);
						IsPACoverForUnnamedPersons = coverStatus;
						NumberofPersonsUnnamed = coverNo;
						CapitalSumInsuredPerPersonUnnamed = coverVal;
					}
					//
					if (coverId.equals("52")) {
						// System.out.println("cover 52------------>>"+coverId);
						// tppdDiscount="Yes";
						IsPACoverForUnnamedPersons = coverStatus;
						NumberofPersonsUnnamed = coverNo;
						CapitalSumInsuredPerPersonUnnamed = coverVal;
						pojo.setTrailer("0");
					}
					pojo.setTrailer("0");
					//
					if (coverId.equals("21")) {
						System.out.println("cover 21------------>>" + coverId);
						antiTheftdevice = "Yes";
						// str_IsAntiTheftDeviceCertifiedByARAI="True";
						pojo.setCngLpgKit("0");
					}
					pojo.setCngLpgKit("0");
					//
					// if (coverId.equals("62")) {
					// legalliabilityToEmployee="Yes";
					// NoOfPassengerForLLToEmployee=coverNo;
					// }
					//
					// if (coverId.equals("25")) {
					// roadSideAsstCover="Yes";
					// }
					// if (coverId.equals("22")) {
					// fiberGlassTankCover="Yes";
					// fiberGlassSI=coverVal;
					// }
					// if (coverId.equals("15")) {
					// if(!str_P_BusinessType.equals("1")){
					// input.put("NoPreviousPolicyHistory", false);
					// }
					// }
					if (coverId.equals("9")) {
						// drivingtution="Yes";
						EAR = coverStatus;
						EARSumInsured = coverVal;
						pojo.setElecAccsrFitd(coverVal);
					}
					if (coverId.equals("104")) {
						// AAIMembship="Yes";
						VoluntaryDeductibleAmount = coverVal;
					}
					if (coverId.equals("18")) {
						// limitedToOwnPremise="Yes";
						IsPACoverForUnnamedPersons = coverStatus;
						NumberofPersonsUnnamed = coverNo;
						CapitalSumInsuredPerPersonUnnamed = coverVal;
					}
					if (coverId.equals("2")) {
						// vehicleForHandicap="Yes";
						near = coverStatus;
						NEARSumInsured = coverVal;
						pojo.setNeAccsrsFitd(coverVal);
					}
				}
			}

			JSONObject json = new JSONObject();
			// json.put("strBusinessType", businessType);
			// json.put("strRTOCode", rtoCode);
			// json.put("strVehicleMake", vehicleMake);
			// json.put("strVehicleMakeCode", makeCode);
			// json.put("strVehicleModel", vehicleModel);
			// json.put("strVehicleModelCode", VehicleModelCode);
			// json.put("strVehicleVariant", VehicleVariant);
			// json.put("strVehicleVariantCode", VehicleVariantCode);
			json.put("strDateofRegistration", DateofRegistration);
			json.put("strIDV", idv);
			System.out.println("[[[[[[[ idv ]]]]]]]]]" + idv);
			// json.put("strMarketMovement", MarketMovement);
			json.put("strReturnToInvoice", returnToInvoice);
			json.put("strEngineProtect", EngineProtect);
			json.put("strRoadsideAssistance", RoadsideAssistance);
			json.put("strDepreciationCover", DepreciationCover);
			json.put("strConsumableCover", ConsumableCover);
			json.put("strPreviousClaim", PreviousClaim);
			json.put("strPreviousYearNCBPercentage", PreviousYearNCBPercentage);

			json.put("strNCBPercentage", NCBPercentage);
			json.put("strNEAR", near);
			json.put("strEAR", EAR);
			json.put("strCNG", cng);
			json.put("strNEARSumInsured", NEARSumInsured);
			json.put("strEARSumInsured", EARSumInsured);
			json.put("strCNGSumInsured", CNGSumInsured);
			// json.put("strAccessKey", accessKey);
			// json.put("TypeOfPolicyHolder", typeOfPolicyHolder);
			// json.put("CustomerType", CustomerType);
			// json.put("PreviousPolicyTypeOrTypeofCover",
			// PreviousPolicyTypeOrTypeofCover);
			// json.put("TotalClaimCount", TotalClaimCount);
			json.put("PreviousPolicyExpiryDate", PreviousPolicyExpiryDate);
			json.put("VoluntaryDeductibleAmount", VoluntaryDeductibleAmount);
			json.put("IsPACoverForUnnamedPersons", IsPACoverForUnnamedPersons);
			json.put("NumberofPersonsUnnamed", NumberofPersonsUnnamed);
			json.put("CapitalSumInsuredPerPersonUnnamed", CapitalSumInsuredPerPersonUnnamed);
			json.put("IsPACoverForNamedPersons", IsPACoverForNamedPersons);
			json.put("NumberofPersonsNamed", NumberofPersonsNamed);
			json.put("CapitalSumInsuredPerPersonNamed", CapitalSumInsuredPerPersonNamed);
			json.put("IsPACoverForPaidDriver", IsPACoverForPaidDriver);
			json.put("NumberofPaidDrivers", NumberofPaidDrivers);
			json.put("SumInsuredForPaidDriver", SumInsuredForPaidDriver);
			json.put("WiderLegalLiabilityToPaidDriverCleanerConductorIMT28",
					WiderLegalLiabilityToPaidDriverCleanerConductorIMT28);
			json.put("NoOfPersonWiderLegalLiability", NoOfPersonWiderLegalLiability);
			json.put("LegalLiabilityToEmployeesExcludingPaidDriverCleanerConductorIMT29",
					LegalLiabilityToEmployeesExcludingPaidDriverCleanerConductorIMT29);
			json.put("NoofEmployees", NoofEmployees);

			json.put("BasicODDeviation", BasicODDeviation);
			json.put("AddOnDeviation", AddOnDeviation);
			json.put("InsuredProfession", InsuredProfession);
			json.put("InsuredCreditScore", InsuredCreditScore);
			json.put("InsureddrivingScore", InsureddrivingScore);
			System.out.println("JsonObject-->>>" + json);

			if (jsonNames.contains("}][{")) {
				jsonNames = jsonNames.replace("}][{", ",");
			}
			if (jsonNames.contains("[") || jsonNames.contains("]")) {
				jsonNames = jsonNames.replace("[", "");
				jsonNames = jsonNames.replace("]", "");
			}

			System.out.println("uuuuuuuuuuuuuu JSON uuuuuuuuuuuuu-->>>>" + jsonNames);

			jsonN = jsonNames + json;

			System.out.println("jsonN---1-->>>" + jsonN);
			// if (jsonN.contains(",}{")) {
			// jsonN = jsonN.replace(",}{", ",");
			// }
			// if (jsonN.contains("}{")) {
			// jsonN = jsonN.replace("}{", ",");
			// }
			// if (jsonN.contains("OCCUPATION")) {
			// jsonN = jsonN.replace("\"OCCUPATION\"", " ");
			// jsonN = jsonN.replace(", :\" \",", ",");
			// }
			// if (jsonN.contains("SOURCE_TYPE")) {
			// jsonN = jsonN.replace("\"SOURCE_TYPE\"", " ");
			// jsonN = jsonN.replace(", :\"Aggregator\",", ",");
			// }
			// if (jsonN.contains("IMD_FU_FLAG")) {
			// jsonN = jsonN.replace("\"IMD_FU_FLAG\"", " ");
			// jsonN = jsonN.replace(", :\"P\",", ",");
			// }
			// if (jsonN.contains("IMD_CODE")) {
			// jsonN = jsonN.replace("\"IMD_CODE\"", " ");
			// jsonN = jsonN.replace(", :\"5169680000\",", ",");
			// }
			// if (jsonN.contains("FU_CODE")) {
			// jsonN = jsonN.replace("\"FU_CODE\"", " ");
			// jsonN = jsonN.replace(", :\" \",", ",");
			// }
			// if (jsonN.contains("IMD_LOCATION_CODE")) {
			// jsonN = jsonN.replace("\"IMD_LOCATION_CODE\"", " ");
			// jsonN = jsonN.replace(", :\"0002\",", ",");
			// }
			// if (jsonN.contains("LEAD_GENERATOR")) {
			// jsonN = jsonN.replace("\"LEAD_GENERATOR\"", " ");
			// jsonN = jsonN.replace(", :\" \",", ",");
			// }
			// if (jsonN.contains("SERVICE_TAX_APPLICABLE")) {
			// jsonN = jsonN.replace("\"SERVICE_TAX_APPLICABLE\"", " ");
			// jsonN = jsonN.replace(", :\"No\",", ",");
			// }
			// if (jsonN.contains("ST_EXEMPTION_REASON")) {
			// jsonN = jsonN.replace("\"ST_EXEMPTION_REASON\"", " ");
			// jsonN = jsonN.replace(", :\" \",", ",");
			// }

			if (jsonN.contains("STRACCESSKEY")) {
				jsonN = jsonN.replace("\"STRACCESSKEY\"", "\"AccessKey\"");
			}
			if (jsonN.contains("STRBUSINESSTYPE")) {
				jsonN = jsonN.replace("\"STRBUSINESSTYPE\"", "\"strBusinessType\"");
			}
			if (jsonN.contains("STRRTOCODE")) {
				jsonN = jsonN.replace("\"STRRTOCODE\"", "\"strRTOCode\"");
			}
			if (jsonN.contains("STRVEHICLEMAKE")) {
				jsonN = jsonN.replace("\"STRVEHICLEMAKE\"", "\"strVehicleMake\"");
			}
			if (jsonN.contains("STRVEHICLEMAKECODE")) {
				jsonN = jsonN.replace("\"STRVEHICLEMAKECODE\"", "\"strVehicleMakeCode\"");
			}
			if (jsonN.contains("STRVEHICLEMODEL")) {
				jsonN = jsonN.replace("\"STRVEHICLEMODEL\"", "\"strVehicleModel\"");
			}
			if (jsonN.contains("STRVEHICLEMODELCODE")) {
				jsonN = jsonN.replace("\"STRVEHICLEMODELCODE\"", "\"strVehicleModelCode\"");
			}
			if (jsonN.contains("STRVEHICLEVARIANT")) {
				jsonN = jsonN.replace("\"STRVEHICLEVARIANT\"", "\"strVehicleVariant\"");
			}
			if (jsonN.contains("STRVEHICLEVARIANTCODE")) {
				jsonN = jsonN.replace("\"STRVEHICLEVARIANTCODE\"", "\"strVehicleVariantCode\"");
			}
			if (jsonN.contains("TYPEOFPOLICYHOLDER")) {
				jsonN = jsonN.replace("\"TYPEOFPOLICYHOLDER\"", "\"TypeOfPolicyHolder\"");
			}
			if (jsonN.contains("STRMARKETMOVEMENT")) {
				jsonN = jsonN.replace("\"STRMARKETMOVEMENT\"", "\"MarketMovement\"");
			}
			if (jsonN.contains("CUSTOMERTYPE")) {
				jsonN = jsonN.replace("\"CUSTOMERTYPE\"", "\"CustomerType\"");
			}
			if (jsonN.contains("CLAIM_NO")) {
				jsonN = jsonN.replace("\"CLAIM_NO\"", "\"TotalClaimCount\"");
			}

			System.out.println("jsonN-->>>" + jsonN);

			switch (request_for) {
			case "premium":

				// js.remove("OCCUPATION");
				// js.remove("SOURCE_TYPE");
				// js.remove("IMD_FU_FLAG");
				// js.remove("IMD_CODE");
				// js.remove("FU_CODE");
				// js.remove("IMD_LOCATION_CODE");
				// js.remove("SERVICE_TAX_APPLICABLE");
				// js.remove("ST_EXEMPTION_REASON");
				// js.remove("LEAD_GENERATOR");
				// js.remove("POLICY_STARTDATE");
				// js.remove("POLICY_ENDDATE");

				System.out.println("jsonNames-->>> " + jsonNames);
				// System.out.println("Premium-->>> " + Premium);
				Premium = PostInfoToAPI(jsonN, "CalculatePremiumPrivateCarGeneric");
				System.out.println("Premium-->>> " + Premium);

				System.out.println("JsonObject-->>>" + json);
				System.out.println("jsonN-->>>" + jsonN);
				JSONObject js = new JSONObject(jsonN);
				pojo.setFromDate(js.getString("POLICY_STARTDATE"));
				pojo.setToDate(js.getString("POLICY_ENDDATE"));

				JSONObject Jprem = new JSONObject(Premium);
				String od = Jprem.getString("TotalPremiumOwnDamage");
				String tp = Jprem.getString("BasicTPPremium");
				float o = Float.parseFloat(od);
				float t = Float.parseFloat(tp);
				float total = o + t;
				String elecSI = null;
				String engPrct = null;
				String extBiFuelSI = null;
				String libForBiFuel = null;
				String volunDedc = null;
				String prevPolicyExpDt = null;
				String legLibToPaidDvrNo = null;
				String nonElecSI = null;
				String paToPaidDvrSI = null;
				String volunDedcForDepWaiv = null;
				String LLEOPDCC = null;
				String regDt = null;
				String paForUnnmdPsngrSI = null;
				String pdfShortUrl = null;
				String retrnToInvc = null;
				String NCB = null;
				String paForNmedPsngrSI = null;

				String sTotal = String.valueOf(total);
				String bscTpPrem = Jprem.getString("BasicTPPremium");
				String cnsmblCvr = Jprem.getString("ConsumableCover");
				String cvrType = Jprem.getString("CoverType");
				String cc = Jprem.getString("CubicCapacity");
				String deprCvr = Jprem.getString("DepreciationCover");
				String elecAccIDV = Jprem.getString("ElectricalAccessoriesIDV");
				if (!(Jprem.getString("ElectronicSI") == null)) {
					elecSI = "";
					// System.out.println("elecSI +++++ blank if " + elecSI);
				} else {
					elecSI = Jprem.getString("ElectronicSI");
					// System.out.println("elecSI +++++ null else" + elecSI);
				}
				// System.out.println("elecSI +++++ " + elecSI);
				if (!(Jprem.getString("EngineProtect") == null)) {
					engPrct = "";
				} else {
					engPrct = Jprem.getString("EngineProtect");
				}
				// System.out.println("engPrct +++++ " + engPrct);
				String errMsg = Jprem.getString("ErrorMessage");
				if (!(Jprem.getString("ExternalBiFuelSI") == null)) {
					extBiFuelSI = "";
				} else {
					extBiFuelSI = Jprem.getString("ExternalBiFuelSI");
				}
				// System.out.println("extBiFuelSI +++++ " + extBiFuelSI);
				String finalIDV = Jprem.getString("FinalIDV");
				String fuelType = Jprem.getString("FuelType");
				if (!(Jprem.getString("LLEOPDCC") == null)) {
					LLEOPDCC = "";
				} else {
					LLEOPDCC = Jprem.getString("LLEOPDCC");
				}
				// System.out.println("LLEOPDCC +++++ " + LLEOPDCC);
				if (!(Jprem.getString("LegalLiabilityToPaidDriverNo") == null)) {
					legLibToPaidDvrNo = "";
				} else {
					legLibToPaidDvrNo = Jprem.getString("LegalLiabilityToPaidDriverNo");
				}
				// System.out.println("legLibToPaidDvrNo +++++ " +
				// legLibToPaidDvrNo);
				if (!(Jprem.getString("LiabilityForBiFuel") == null)) {
					libForBiFuel = "";
				} else {
					libForBiFuel = Jprem.getString("LiabilityForBiFuel");
				}
				// System.out.println("libForBiFuel +++++ " + libForBiFuel);
				String make = Jprem.getString("Make");
				String model = Jprem.getString("Model");
				if (!(Jprem.getString("NCB") == null)) {
					NCB = "";
				} else {
					NCB = Jprem.getString("NCB");
				}
				// System.out.println("NCB +++++ " + NCB);
				String ncbPerc = Jprem.getString("NCBPercentage");
				if (ncbPerc.contains("%")) {
					ncbPerc = ncbPerc.replace("%", "");
				}
				String netPrem = Jprem.getString("NetPremium");
				String nonElecAccIDV = Jprem.getString("NonElectricalAccessoriesIDV");
				if (!(Jprem.getString("NonElectronicSI") == null)) {
					nonElecSI = "";
				} else {
					nonElecSI = Jprem.getString("NonElectronicSI");
				}
				// System.out.println("nonElecSI +++++ " + nonElecSI);
				String ownDmgPrem = Jprem.getString("OwnDamagePremium");
				String ownrshpType = Jprem.getString("OwnershipType");
				String paCvrFrOwnrDrvr = Jprem.getString("PACoverForOwnerDriver");
				if (!(Jprem.getString("PAForNamedPassengerSI") == null)) {
					paForNmedPsngrSI = "";
				} else {
					paForNmedPsngrSI = Jprem.getString("PAForNamedPassengerSI");
				}
				// System.out.println("paForNmedPsngrSI +++++ " +
				// paForNmedPsngrSI);
				if (!(Jprem.getString("PAForUnnamedPassengerSI") == null)) {
					paForUnnmdPsngrSI = "";
				} else {
					paForUnnmdPsngrSI = Jprem.getString("PAForUnnamedPassengerSI");
				}
				// System.out.println("paForUnnmdPsngrSI +++++ " +
				// paForUnnmdPsngrSI);
				if (!(Jprem.getString("PAToPaidDriverSI") == null)) {
					paToPaidDvrSI = "";
				} else {
					paToPaidDvrSI = Jprem.getString("PAToPaidDriverSI");
				}
				// System.out.println("paToPaidDvrSI +++++ " + paToPaidDvrSI);
				if (!(Jprem.getString("PDFShortURL") == null)) {
					pdfShortUrl = "";
				} else {
					pdfShortUrl = Jprem.getString("PDFShortURL");
				}
				// System.out.println("pdfShortUrl +++++ " + pdfShortUrl);
				String policyStrtDate = Jprem.getString("PolicyStartDate");
				if (!(Jprem.getString("PreviousPolicyExpiryDate") == null)) {
					prevPolicyExpDt = "";
				} else {
					prevPolicyExpDt = Jprem.getString("PreviousPolicyExpiryDate");
				}
				// System.out.println("prevPolicyExpDt +++++ " +
				// prevPolicyExpDt);
				String purchsDt = Jprem.getString("PurchaseDate");
				String quoteNo = Jprem.getString("QuoteNumber");
				String RSA = Jprem.getString("RSA");
				String rto = Jprem.getString("RTO");
				if (!(Jprem.getString("RagistrationDate") == null)) {
					regDt = "";
				} else {
					regDt = Jprem.getString("RagistrationDate");
				}
				// System.out.println("regDt +++++ " + regDt);
				if (!(Jprem.getString("ReturnToInvoice") == null)) {
					retrnToInvc = "";
				} else {
					retrnToInvc = Jprem.getString("ReturnToInvoice");
				}
				// System.out.println("retrnToInvc +++++ " + retrnToInvc);
				String seatCap = Jprem.getString("SeatingCapacity");
				String srvcTx = Jprem.getString("ServiceTax");
				String sysIDV = Jprem.getString("SystemIDV");
				String ttlPrem = Jprem.getString("TotalPremium");
				String ttlPremLib = Jprem.getString("TotalPremiumLiability");
				String ttlPremOwnDmg = Jprem.getString("TotalPremiumOwnDamage");
				String variant = Jprem.getString("Variant");
				if (!(Jprem.getString("VoluntaryDeduction") == null)) {
					volunDedc = "";
				} else {
					volunDedc = Jprem.getString("VoluntaryDeduction");
				}
				// System.out.println("volunDedc +++++ " + volunDedc);
				if (!(Jprem.getString("VoluntaryDeductionforDepWaiver") == null)) {
					volunDedcForDepWaiv = "";
				} else {
					volunDedcForDepWaiv = Jprem.getString("VoluntaryDeductionforDepWaiver");
				}
				// System.out.println("volunDedcForDepWaiv +++++ " +
				// volunDedcForDepWaiv);

				pojo.setBasicOwnDmg(ownDmgPrem);
				pojo.setPolIssOn(purchsDt);
				pojo.setBscTPPrem(bscTpPrem);
				pojo.setTotalOwnDmgPrem(od);
				// String tl = Jprem.getString("TotalPremiumLiability");
				pojo.setTotalLibPrem(ttlPremLib);
				float itl = Float.parseFloat(ttlPremLib);
				float totalOL = o + itl;
				String sTotalOL = String.valueOf(totalOL);
				pojo.setTotalPkg(sTotalOL);

				// IntegrationSaveResponseService integrationSaveResponseService
				// = new IntegrationSaveResponseService();

				HashMap<String, String> hashmapKotak = new HashMap<String, String>();
				hashmapKotak.put("BasicTPPremium", bscTpPrem);
				hashmapKotak.put("ConsumableCover", cnsmblCvr);
				hashmapKotak.put("CoverType", cvrType);
				hashmapKotak.put("CubicCapacity", cc);
				hashmapKotak.put("DepreciationCover", deprCvr);
				hashmapKotak.put("ElectricalAccessoriesIDV", elecAccIDV);
				if (!(Jprem.get("ElectronicSI") == null)) {
					hashmapKotak.put("ElectronicSI", elecSI);
				}
				if (!(Jprem.get("EngineProtect") == null)) {
					hashmapKotak.put("EngineProtect", engPrct);
				}
				// hashmapKotak.put("ErrorMessage", errMsg);
				if (!(Jprem.getString("ExternalBiFuelSI") == null)) {
					hashmapKotak.put("ExternalBiFuelSI", extBiFuelSI);
				}
				hashmapKotak.put("FinalIDV", finalIDV);
				hashmapKotak.put("FuelType", fuelType);
				if (!(Jprem.getString("LLEOPDCC") == null)) {
					hashmapKotak.put("LLEOPDCC", LLEOPDCC);
				}
				if (!(Jprem.getString("LegalLiabilityToPaidDriverNo") == null)) {
					hashmapKotak.put("LegalLiabilityToPaidDriverNo", legLibToPaidDvrNo);
				}
				if (!(Jprem.getString("LiabilityForBiFuel") == null)) {
					hashmapKotak.put("LiabilityForBiFuel", libForBiFuel);
				}
				hashmapKotak.put("Make", make);
				hashmapKotak.put("Model", model);
				if (!(Jprem.getString("NCB") == null)) {
					hashmapKotak.put("NCB", NCB);
				}
				hashmapKotak.put("NCBPercentage", ncbPerc);
				hashmapKotak.put("NetPremium", netPrem);
				hashmapKotak.put("NonElectricalAccessoriesIDV", nonElecAccIDV);
				if (!(Jprem.getString("NonElectronicSI") == null)) {
					hashmapKotak.put("NonElectronicSI", nonElecSI);
				}
				hashmapKotak.put("OwnDamagePremium", ownDmgPrem);
				hashmapKotak.put("OwnershipType", ownrshpType);
				hashmapKotak.put("PACoverForOwnerDriver", paCvrFrOwnrDrvr);
				if (!(Jprem.getString("PAForNamedPassengerSI") == null)) {
					hashmapKotak.put("PAForNamedPassengerSI", paForNmedPsngrSI);
				}
				if (!(Jprem.getString("PAForUnnamedPassengerSI") == null)) {
					hashmapKotak.put("PAForUnnamedPassengerSI", paForUnnmdPsngrSI);
				}
				if (!(Jprem.getString("PAToPaidDriverSI") == null)) {
					hashmapKotak.put("PAToPaidDriverSI", paToPaidDvrSI);
				}
				if (!(Jprem.getString("PDFShortURL") == null)) {
					hashmapKotak.put("PDFShortURL", pdfShortUrl);
				}
				hashmapKotak.put("PolicyStartDate", policyStrtDate);
				if (!(Jprem.getString("PreviousPolicyExpiryDate") == null)) {
					hashmapKotak.put("PreviousPolicyExpiryDate", prevPolicyExpDt);
				}
				hashmapKotak.put("PurchaseDate", purchsDt);
				hashmapKotak.put("QuoteNumber", quoteNo);
				hashmapKotak.put("RSA", RSA);
				hashmapKotak.put("RTO", rto);
				if (!(Jprem.getString("RagistrationDate") == null)) {
					hashmapKotak.put("RagistrationDate", regDt);
				}
				if (!(Jprem.getString("ReturnToInvoice") == null)) {
					hashmapKotak.put("ReturnToInvoice", retrnToInvc);
				}
				hashmapKotak.put("SeatingCapacity", seatCap);
				hashmapKotak.put("ServiceTax", srvcTx);
				hashmapKotak.put("SystemIDV", sysIDV);
				hashmapKotak.put("TotalPremium", ttlPrem);
				hashmapKotak.put("TotalPremiumLiability", ttlPremLib);
				hashmapKotak.put("TotalPremiumOwnDamage", ttlPremOwnDmg);
				hashmapKotak.put("Variant", variant);
				if (!(Jprem.getString("VoluntaryDeductionforDepWaiver") == null)) {
					hashmapKotak.put("VoluntaryDeduction", volunDedc);
				}
				if (!(Jprem.getString("VoluntaryDeductionforDepWaiver") == null)) {
					hashmapKotak.put("VoluntaryDeductionforDepWaiver", volunDedcForDepWaiv);
				}

//				 String procedureName = "PR_PREMIUM";
//				 savebn.setMtrGrpRespGrpId(new Integer(savebn.getMtrGrpRespGrpId()));
//				 savebn.setMtrGrpRespGicId(new Integer(savebn.getMtrGrpRespGicId()));
//				 Integer value = new Integer(1);
				// System.out.println("valu ''''' " + value);
				// System.out.println("hashmap //////// " +
//				 hashmapKotak.toString());
//				 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
//				 integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(savebn.getMtrGrpRespGrpId(), savebn.getMtrGrpRespGicId(), savebn.getMotorGroupResponseSessionId(), value, hashmapKotak, savebn.getIPAddress(), savebn.getUserId(), savebn.getBranchId(), savebn.getUserDesc(), procedureName);

				break;

			case "Partnerintegration":

				System.out.println("Partner premium = = = = " + Premium);
				JSONObject prem = new JSONObject(Premium);
				String od1 = prem.getString("TotalPremiumOwnDamage");
				String tp1 = prem.getString("BasicTPPremium");
				float o1 = Float.parseFloat(od1);
				float t1 = Float.parseFloat(tp1);
				float total1 = o1 + t1;
				String sTotal1 = String.valueOf(total1);
				//
				// pojo.setBasicOwnDmg(prem.get("OwnDamagePremium").toString());
				// pojo.setPolIssOn(prem.get("PurchaseDate").toString());
				// pojo.setBscTPPrem(prem.get("BasicTPPremium").toString());
				// pojo.setTotalOwnDmgPrem(od);
				// String tl = prem.getString("TotalPremiumLiability");
				// pojo.setTotalLibPrem(tl);
				// float itl = Float.parseFloat(tl);
				// float totalOL = o + itl;
				// String sTotalOL = String.valueOf(totalOL);
				// pojo.setTotalPkg(sTotalOL);

				JSONObject jsonObj = new JSONObject();
				//
				jsonObj.put("ProductType", productType);
				jsonObj.put("ProposalDate", proposalDate);
				jsonObj.put("ProposalTime", proposalTime);
				jsonObj.put("ProposerType", proposerType);
				jsonObj.put("CustomerName", customerName);
				System.out.println("engineNos******* " + engineNos);
				System.out.println("chassisNos******* " + chassisNos);
				jsonObj.put("EngineNo", engineNos);
				jsonObj.put("ChasisNo", chassisNos);
				jsonObj.put("Make", prem.getString("Make"));
				jsonObj.put("Model", prem.getString("Model"));
				jsonObj.put("Variant", prem.getString("Variant"));
				jsonObj.put("IDV", idv);
				jsonObj.put("TotalA", od1);
				jsonObj.put("TotalB", tp1);
				jsonObj.put("GrossPremium", sTotal1);
				jsonObj.put("ServiceORSalesTax", prem.getString("ServiceTax"));
				jsonObj.put("NetPremium", prem.getString("NetPremium"));

				System.out.println("mihpayid ______ " + kbean.getMihpayid());
				jsonObj.put("TransactionReference", kbean.getMihpayid());
				jsonObj.put("TransactionDate", transactionDate);
				jsonObj.put("TransactionAmount", transactionAmount);
				jsonObj.put("TransactionType", transactionType);
				jsonObj.put("TransactionID", kbean.getTxnid());
				jsonObj.put("Prefix", prefix);
				jsonObj.put("VehicleRegistrationNumber", vehicleRegistrationNumber);
				jsonObj.put("VehicleRegistrationNumber1", vehicleRegistrationNumber1);
				jsonObj.put("VehicleRegistrationNumber2", vehicleRegistrationNumber2);
				jsonObj.put("VehicleRegistrationNumber3", vehicleRegistrationNumber3);
				jsonObj.put("VehicleRegistrationNumber4", vehicleRegistrationNumber4);
				
				jsonObj.put("BiFuelKit", biFuelKit);
				jsonObj.put("BiFuelKitIDV", biFuelKitIDV);

				String ncb = prem.getString("NCB");
				if (!ncb.equalsIgnoreCase("null")) {
					ncb = "0";
				}

				jsonObj.put("NCB", prem.getString("NCB"));
				jsonObj.put("ZeroDepreciation", zeroDepreciation);
				// jsonObj.put("PolicyNo", prem.getString("POLICYNO")); //from
				// database
				// jsonObj.put("PolicyStartDate",
				// prem.getString("POLICY_STARTDATE")); //from database
				// jsonObj.put("PolicyEndDate",
				// prem.getString("POLICY_ENDDATE")); //from database
				// jsonObj.put("LegalLiabilityPaidDriver",
				// legalLiabilityPaidDriver); //from database
				// X//WiderLegalLiabilityToPaidDriverCleanerConductorIMT28////X

				String llpd = prem.getString("LegalLiabilityToPaidDriverNo");
				if (!llpd.equalsIgnoreCase("null")) {
					llpd = "Yes";
				} else {
					llpd = "No";
				}

				jsonObj.put("LegalLiabilityPaidDriver", llpd);
				// jsonObj.put("UserID",userId ); //from database
				// jsonObj.put("Password",pass); //from database

				String engPro = prem.getString("EngineProtect");
				if (!engPro.equalsIgnoreCase("null")) {
					engPro = "Yes";
				} else {
					engPro = "No";
				}

				jsonObj.put("EngineProtector", engPro);

				String rti = prem.getString("ReturnToInvoice");
				if (!rti.equalsIgnoreCase("null")) {
					rti = "Yes";
				} else {
					rti = "No";
				}

				jsonObj.put("ReturnToInvoice", rti);
				jsonObj.put("PAUnnamedPassengerSI", CapitalSumInsuredPerPersonUnnamed);
				jsonObj.put("Consumables_Addon", consumables_Addon);

				String rsa = prem.getString("RSA");
				if (!rsa.equalsIgnoreCase("null")) {
					rsa = "Yes";
				} else {
					rsa = "No";
				}
				jsonObj.put("RoadsideAssistance", rsa);
				jsonObj.put("IMTNos", IMTNos);
				// jsonObj.put("Additional_Info", additional_Info);

				String strXml = jsonObj + jsonN;

				System.out.println("strXml jsonObj============= " + jsonObj);
				System.out.println("strXml jsonN============= " + jsonN);
				System.out.println("strXml ============= " + strXml);

				if (strXml.contains("}{")) {
					strXml = strXml.replace("}{", ",");
				}

				System.out.println("strXml2 ============= " + strXml);

				String xmlpartnerIntg = xmlFilePartner("[" + strXml + "]");
				// String xmlpartnerIntg = xmlFilePartner(jsonN);
				PartnerIntegrationService integrationService = new PartnerIntegrationService();
				IPartnerIntegrationService service = integrationService.getBasicHttpBindingIPartnerIntegrationService();
				Premium = service.savePartnerIntegration(xmlpartnerIntg);
				System.out.println("Premium-->>> " + Premium);

				if (Premium.contains("<?xml version=\"1.0\" encoding=\"utf-16\"?><")) {

					Premium = Premium.replace("<?xml version=\"1.0\" encoding=\"utf-16\"?><", "<");
					System.out.println("inside if Premium '''''''' " + Premium);
				}

				hashmapPartner = new HashMap<>();

				hashmapPartner = readResponse(Premium);
				System.out.println("hashmapPartner '''''''' " + hashmapPartner);
				Gson gson = new Gson();
				jsonNames = gson.toJson(hashmapPartner);
				Premium = jsonNames;
				if (Premium.contains("[{") || Premium.contains("}]")) {
					Premium = Premium.replace("[{", "{");
					Premium = Premium.replace("}]", "}");
				}
				Premium = Premium.toString();
				System.out.println("Premium '''''''' " + Premium);

				GetSignPolicyPDF policyPDF = new GetSignPolicyPDF();
				// JAXBElement<byte[]> jByte = policyPDF.getStrPolicyPDF();
				// byte [] policy = jByte.getValue();
				// String strPolicyPdf =
				// "JVBERi0xLjQKJcMaw4HDYcOPw30KCjEgMCBvYmogPDwKICAvVHlwZSAvQ2F0YWxvZwogIC9QYWdlcyAyIDAgUgo+PiBlbmRvYmoKCjIgMCBvYmogPDwKICAvVHlwZSAvUGFnZXMKICAvS2lkcyBbIDQgMCBSIDExIDAgUiAxNCAwIFIgMTcgMCBSIF0KICAvQ291bnQgNAo+PiBlbmRvYmoKCjMgMCBvYmogPDwKICAvTGVuZ3RoIDU2OTYKPj4gc3RyZWFtCjEgMCAwIC0xIDAgOTQ0LjIzMzc5NTIgY20KcQoxIDAgMCAxIC0xMTYuOTUwMTk1MyAwIGNtCnEKMSAwIDAgMSAxMDAwMCAxMDAwMCBjbQpxCnEKcQpxCnEKLTk4ODMuMDQ5ODA0NyAtMTAwMDAgMjEwNS4xMDAwOTc3IDk5LjEzMzMzMTMgcmUKVyBuCnEKMC45MjU0OTAyIDAuMjE1Njg2MyAwLjE5NjA3ODQgcmcKLTk4ODMuMDQ5ODA0NyAtMTAwMDAgMjEwNS4xMDAwOTc3IDk5LjEzMzMzMTMgcmUKZgpRClEKcQpxCnEKLTk4ODMuMDQ5ODA0NyAtOTk3OC45NTAxOTUzIDIwMCA1MiByZQpXIG4KMjAwIDAgMCAtNTIgLTk4ODMuMDQ5ODA0NyAtOTkyNi45NTAxOTUzIGNtCi9JMSBEbwpRClEKUQpxCnEKMSAxIDEgcmcKMSAwIDAgLTEgLTgwODEgLTk5MzguNDMzNTkzOCBjbQpCVAovRjIgMzAgVGYKMCBUcgooR2VuZXJhbCBJbnN1cmFuY2UpIFRqCkVUClEKUQpRCnEKcQpxCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQxLjA0OTgwNDcgLTk4ODIuODY2MjEwOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooUmVmIE5vLjogR0VOL1dFTC9TRy8wMDA4LjEvUFRGLzIwMTcwMzE1MTAwMTE5MykgVGoKRVQKUQpRClEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDEuMDQ5ODA0NyAtOTg1Mi44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihEYXRlOiAxNS1NYXItMjAxNykgVGoKRVQKUQpRClEKUQpRCnEKcQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0MS4wNDk4MDQ3IC05ODE2Ljg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFRvLCkgVGoKRVQKUQpRClEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDEuMDQ5ODA0NyAtOTgwMS44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihzYW5kZWVwIGphZGhhdikgVGoKRVQKUQpRClEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDEuMDQ5ODA0NyAtOTc4Ni44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihEYW5nZSBDaG93ayBLYWxld2FkaSBNYWluIFJvYWQgTWFuZ2FsIE5hZ2FyIFBpbXByaS1DaGluY2h3YWQgTWFoYXJhc2h0cmEgSW5kaWEpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0MS4wNDk4MDQ3IC05NzczLjg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKE1haGFyYXNodHJhIC0gNDExMDU3KSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDEuMDQ5ODA0NyAtOTc2MC44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihEaXN0cmljdDogUHVuZSkgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQxLjA0OTgwNDcgLTk3NDcuODY2MjEwOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooTWFoYXJhc2h0cmEsIEluZGlhKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDEuMDQ5ODA0NyAtOTczNC44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihDb250YWN0IERldGFpbHMgOTAyODMyNjcwNCkgVGoKRVQKUQpRClEKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0My4wNDk4MDQ3IC05NzAwLjg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFBvbGljeSBudW1iZXI6IFBURi8yMDE3MDMxNTEwMDExOTMpIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDMuMDQ5ODA0NyAtOTY2OC44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihTdWJqZWN0OiBXZWxjb21lIExldHRlciBmb3IgS290YWsgQ2FyIFNlY3VyZSBcKENvbXByZWhlbnNpdmUgUG9saWN5XCkpIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDMuMDQ5ODA0NyAtOTYzNi44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihEZWFyIHNhbmRlZXAgamFkaGF2LCkgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0My4wNDk4MDQ3IC05NjA0Ljg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFdlIHZhbHVlIHlvdXIgcmVsYXRpb25zaGlwIHdpdGggS290YWsgTWFoaW5kcmEgR2VuZXJhbCBJbnN1cmFuY2UgQ29tcGFueSBMaW1pdGVkIGFuZCB0aGFuayB5b3UgZm9yIGNob29zaW5nIHVzIGFzIHlvdXIgcHJlZmVycmVkIHNlcnZpY2UgcHJvdmlkZXIuKSBUagpFVApRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQzLjA0OTgwNDcgLTk1NzIuODY2MjEwOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooVGhpcyBpcyB3aXRoIHJlZmVyZW5jZSB0byB5b3VyIGFib3ZlIG1lbnRpb25lZCBQb2xpY3kgaXNzdWVkIHVuZGVyIEtvdGFrIENhciBTZWN1cmUgXChDb21wcmVoZW5zaXZlIFBvbGljeVwpLikgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0My4wNDk4MDQ3IC05NTQwLjg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKEVuY2xvc2VkIHBsZWFzZSBmaW5kIHRoZSBQb2xpY3kgU2NoZWR1bGUgYW5kIFBvbGljeSBXb3JkaW5ncyBjb250YWluaW5nIGRldGFpbGVkIHRlcm1zIGFuZCBjb25kaXRpb25zIG9mIHRoZSBjb3ZlcmFnZS4gV2Ugd2lzaCB0byBpbmZvcm0geW91IHRoYXQgdGhlIHBvbGljeSBpc3N1ZWQgaXMgYmFzZWQgb24gdGhlIGluZm9ybWF0aW9uIHN1Ym1pdHRlZCB0byB1cyBhcyB3ZWxsIGFzIGFjY2VwdGFuY2Ugb2YgdGhlIHRlcm1zIGFuZCBjb25kaXRpb25zLikgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0My4wNDk4MDQ3IC05NTA4Ljg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFdlIHJlcXVlc3QgeW91IHRvIGtpbmRseSBnbyB0aHJvdWdoIHRoZSBzYW1lIG9uY2UgYWdhaW4gYW5kIGluIGNhc2Ugb2YgYW55IGRpc2FncmVlbWVudCwgZGlzY3JlcGFuY3kgb3IgY2xhcmlmaWNhdGlvbnMsIHBsZWFzZSBjYWxsIHVzIG9uIG91ciB0b2xsIGZyZWUgbnVtYmVyIDE4MDAgMjY2IDQ1NDUgb3Igd3JpdGUgdG8gdXMgYXQgY2FyZUBrb3Rhay5jb20gd2l0aGluIDE1IGRheXMgZnJvbSB0aGUgZGF0ZSBvZiB0aGlzIGxldHRlci4gQWx0ZXJuYXRpdmVseSwgeW91IGNhbiBhbHNvIHdyaXRlIHRvIHVzIGF0IDh0aCBGbG9vciwgS290YWsgSW5maW5pdHksIEJ1aWxkaW5nIE5vLiAyMSBJbmZpbml0eSBQYXJrLCBPZmYgV2VzdGVybiBFeHByZXNzIEhpZ2h3YXkgR2VuZXJhbCBBSyBWYWlkeWEgTWFyZywgTWFsYWRcKEVcKSBNdW1iYWkgLSkgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQzLjA0OTgwNDcgLTk0OTUuODY2MjEwOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooNDAwIDAwOTcsIEluZGlhLikgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0My4wNDk4MDQ3IC05NDYzLjg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFBsZWFzZSBub3RlIHRoYXQgdGhlIGluZm9ybWF0aW9uIHByb3ZpZGVkIGJ5IHlvdSB3aWxsIGJlIHZlcmlmaWVkIGF0IHRoZSB0aW1lIG9mIGNsYWltIGFuZCB0aGUgY2FwdGlvbmVkIFBvbGljeSBzaGFsbCBiZSB0cmVhdGVkIGFzIHZvaWQgaWYgd2UgZGlzY292ZXIgYW55IHVudHJ1ZSBvciBpbmNvcnJlY3Qgc3RhdGVtZW50LCBtaXNyZXByZXNlbnRhdGlvbiwgbm9uLWRlc2NyaXB0aW9uIG9yIG5vbi1kaXNjbG9zdXJlIGluIGFueSBmb3JtIHdoYXRzb2V2ZXIgbWFkZSBieSB5b3Ugb3IgYnkgeW91ciBhZ2VudCwgb24geW91ciBiZWhhbGYsIGF0IGFueSBzdGFnZS4pIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDMuMDQ5ODA0NyAtOTQzMS44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihBcyBhIHZhbHVlZCBjdXN0b21lciwgd2Ugd291bGQgbGlrZSB0byBwcm92aWRlIHJlZ3VsYXIgdXBkYXRlcyBvbiB5b3VyIHBvbGljeSB0aHJvdWdoIGVtYWlsIGFuZCBTTVMuIFdlIHRoZXJlZm9yZSByZXF1ZXN0IHlvdSB0byBrZWVwIHVzIHVwZGF0ZWQgb2YgYW55IGNoYW5nZSBpbiB5b3VyIGNvbnRhY3QgZGV0YWlscy4pIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDMuMDQ5ODA0NyAtOTM5OS44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihBc3N1cmluZyB5b3Ugb2Ygb3VyIGJlc3Qgc2VydmljZXMgYXQgYWxsIHRpbWVzLikgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg0My4wNDk4MDQ3IC05MzY3Ljg2NjIxMDkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFRoYW5raW5nIHlvdSwpIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NDMuMDQ5ODA0NyAtOTMzNS44NjYyMTA5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihZb3VycyBzaW5jZXJlbHksKSBUagpFVApRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQzLjA0OTgwNDcgLTkzMDMuODY2MjEwOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooRm9yIEtvdGFrIE1haGluZHJhIEdlbmVyYWwgSW5zdXJhbmNlIENvbXBhbnkgTGltaXRlZCkgVGoKRVQKUQpRCnEKcQpxCi05ODQzLjA0OTgwNDcgLTkzMDEuODY2MjEwOSAyMTEgODQgcmUKVyBuCjIxMSAwIDAgLTg0IC05ODQzLjA0OTgwNDcgLTkyMTcuODY2MjEwOSBjbQovSTQgRG8KUQpRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQzLjA0OTgwNDcgLTkyMDYuODY2MjEwOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooQXV0aG9yaXNlZCBTaWduYXRvcnkpIFRqCkVUClEKUQpRCnEKcQotOTg4My4wNDk4MDQ3IC05MTg1Ljg2NjIxMDkgMjEwNS4wNjY2NTA0IDEzMC4xMDAwMDYxIHJlClcgbgpxCjAuOCAwLjggMC44IHJnCi05ODgzLjA0OTgwNDcgLTkxODUuODY2MjEwOSAyMTA1LjA2NjY1MDQgMTMwLjEwMDAwNjEgcmUKZgpRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MTk5LjUxNjYwMTYgLTkxMjcuMzE2NDA2MyBjbQpCVAovRjMgMTEgVGYKMCBUcgooQ0lOOiBVNjYwMDBNSDIwMTRQTEMyNjAyOTEsKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5MzYuNTE2NjAxNiAtOTEyNy4zMTY0MDYzIGNtCkJUCi9GMyAxMSBUZgowIFRyCigyNyBCS0MsIEMgMjcsIEcgQmxvY2ssIEJhbmRyYSBLdXJsYSBDb21wbGV4LCBCYW5kcmEgRWFzdCwgTXVtYmFpIDQwMDA1MSwgTWFoYXJhc2h0cmEsIEluZGlhLikgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MjQ1LjAxNjYwMTYgLTkxMDUuMzE2NDA2MyBjbQpCVAovRjMgMTEgVGYKMCBUcgooT2ZmaWNlOiA4dGggRmxvb3IsIFpvbmUgSVYsIEtvdGFrIEluZmluaXR5LCBCdWlsZGluZyBOby4yMSwgSW5maW5pdHkgSVQgUGFyaywgT2ZmIFdlc3Rlcm4gRXhwcmVzcyBIaWdod2F5LCBHZW5lcmFsIEFLIFZhaWR5YSBNYXJnLCBEaW5kb3NoaSwgTWFsYWRcKEVcKSwgTXVtYmFpIDQwMDA5Ny4gSW5kaWEuKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkxMDIuNTE2NjAxNiAtOTA4My4zMTY0MDYzIGNtCkJUCi9GMyAxMSBUZgowIFRyCihUb2xsIEZyZWU6IDE4MDAgMjY2IDQ1NDUgRW1haWw6Y2FyZUBrb3Rhay5jb20gV2Vic2l0ZTogd3d3LmtvdGFrZ2VuZXJhbGluc3VyYW5jZS5jb20gSVJEQUkgUmVnLiBOby4gMTUyKSBUagpFVApRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTExMy4wMTY2MDE2IC05MTQ5LjMxNjQwNjMgY20KQlQKL0Y1IDExIFRmCjAgVHIKKEtvdGFrIE1haGluZHJhIEdlbmVyYWwgSW5zdXJhbmNlIENvbXBhbnkgTGltaXRlZCBcKEZvcm1lcmx5IEtvdGFrIE1haGluZHJhIEdlbmVyYWwgSW5zdXJhbmNlIExpbWl0ZWRcKSkgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTAzMi41MTY2MDE2IC05MTI3LjMxNjQwNjMgY20KQlQKL0Y1IDExIFRmCjAgVHIKKFJlZ2lzdGVyZWQgT2ZmaWNlOikgVGoKRVQKUQpRClEKUQpRClEKUQpRClEKCmVuZHN0cmVhbSBlbmRvYmoKCjQgMCBvYmogPDwKICAvQ29udGVudHMgMyAwIFIKICAvUGFyZW50IDIgMCBSCiAgL01lZGlhQm94IFsgMCAwIDIxMDUuMTAwMDk3NyA5NDQuMjMzNzk1MiBdCiAgL1R5cGUgL1BhZ2UKICAvUHJvY1NldCBbIC9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUkgXQogIC9SZXNvdXJjZXMgPDwKICAgIC9Gb250IDw8CiAgICAgIC9GMiA2IDAgUgogICAgICAvRjMgNyAwIFIKICAgICAgL0Y1IDkgMCBSCiAgICA+PgogICAgL0V4dEdTdGF0ZSA8PD4+CiAgICAvWE9iamVjdCA8PAogICAgICAvSTEgNSAwIFIKICAgICAgL0k0IDggMCBSCiAgICA+PgogICAgL1BhdHRlcm4gPDw+PgogICAgL1NoYWRpbmcgPDw+PgogID4+CiAgL0Fubm90cyBbIF0KPj4gZW5kb2JqCgo1IDAgb2Jq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MCAwIC0xIC04MDgxIC04OTY4LjIwMDE5NTMgY20KQlQKL0YyIDMwIFRmCjAgVHIKKEdlbmVyYWwgSW5zdXJhbmNlKSBUagpFVApRClEKUQpxCnEKcQotOTQ2Mi4wMzMyMDMxIC04OTA0LjU4MzAwNzggNjY1IDY4MCByZQpXIG4KNjY1IDAgMCAtNjgwIC05NDYyLjAzMzIwMzEgLTgyMjQuNTgzMDA3OCBjbQovSTYgRG8KUQpRClEKcQpxCi05ODgzLjA0OTgwNDcgLTgyMDMuNTMzMjAzMSAyMTA1LjA2NjY1MDQgMTMwLjEwMDAwNjEgcmUKVyBuCnEKMC44IDAuOCAwLjggcmcKLTk4ODMuMDQ5ODA0NyAtODIwMy41MzMyMDMxIDIxMDUuMDY2NjUwNCAxMzAuMTAwMDA2MSByZQpmClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkxOTkuNTE2NjAxNiAtODE0NC45ODMzOTg0IGNtCkJUCi9GMyAxMSBUZgowIFRyCihDSU46IFU2NjAwME1IMjAxNFBMQzI2MDI5MSwpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODkzNi41MTY2MDE2IC04MTQ0Ljk4MzM5ODQgY20KQlQKL0YzIDExIFRmCjAgVHIKKDI3IEJLQywgQyAyNywgRyBCbG9jaywgQmFuZHJhIEt1cmxhIENvbXBsZXgsIEJhbmRyYSBFYXN0LCBNdW1iYWkgNDAwMDUxLCBNYWhhcmFzaHRyYSwgSW5kaWEuKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkyNDUuMDE2NjAxNiAtODEyMi45ODMzOTg0IGNtCkJUCi9GMyAxMSBUZgowIFRyCihPZmZpY2U6IDh0aCBGbG9vciwgWm9uZSBJViwgS290YWsgSW5maW5pdHksIEJ1aWxkaW5nIE5vLjIxLCBJbmZpbml0eSBJVCBQYXJrLCBPZmYgV2VzdGVybiBFeHByZXNzIEhpZ2h3YXksIEdlbmVyYWwgQUsgVmFpZHlhIE1hcmcsIERpbmRvc2hpLCBNYWxhZFwoRVwpLCBNdW1iYWkgNDAwMDk3LiBJbmRpYS4pIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTEwMi41MTY2MDE2IC04MTAwLjk4MzM5ODQgY20KQlQKL0YzIDExIFRmCjAgVHIKKFRvbGwgRnJlZTogMTgwMCAyNjYgNDU0NSBFbWFpbDpjYXJlQGtvdGFrLmNvbSBXZWJzaXRlOiB3d3cua290YWtnZW5lcmFsaW5zdXJhbmNlLmNvbSBJUkRBSSBSZWcuIE5vLiAxNTIpIFRqCkVUClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MTEzLjAxNjYwMTYgLTgxNjYuOTgzMzk4NCBjbQpCVAovRjUgMTEgVGYKMCBUcgooS290YWsgTWFoaW5kcmEgR2VuZXJhbCBJbnN1cmFuY2UgQ29tcGFueSBMaW1pdGVkIFwoRm9ybWVybHkgS290YWsgTWFoaW5kcmEgR2VuZXJhbCBJbnN1cmFuY2UgTGltaXRlZFwpKSBUagpFVApRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MDMyLjUxNjYwMTYgLTgxNDQuOTgzMzk4NCBjbQpCVAovRjUgMTEgVGYKMCBUcgooUmVnaXN0ZXJlZCBPZmZpY2U6KSBUagpFVApRClEKUQpRClEKUQpRClEKUQoKZW5kc3RyZWFtIGVuZG9iagoKMTEgMCBvYmogPDwKICAvQ29udGVudHMgMTAgMCBSCiAgL1BhcmVudCAyIDAgUgogIC9NZWRpYUJveCBbIDAgMCAyMTA1LjEwMDA5NzcgOTU2LjMzMzQwNDUgXQogIC9UeXBlIC9QYWdlCiAgL1Byb2NTZXQgWyAvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJIF0KICAvUmVzb3VyY2VzIDw8CiAgICAvRm9udCA8PAogICAgICAvRjIgNiAwIFIKICAgICAgL0YzIDcgMCBSCiAgICAgIC9GNSA5IDAgUgogICAgPj4KICAgIC9FeHRHU3RhdGUgPDw+PgogICAgL1hPYmplY3QgPDwKICAgICAgL0kxIDUgMCBSCiAgICAgIC9JNiAxMiAwIFIKICAgID4+CiAgICAvUGF0dGVybiA8PD4+CiAgICAvU2hhZGluZyA8PD4+CiAgPj4KICAvQW5ub3RzIFsgXQo+PiBlbmRvYmoKCjEyIDAgb2JqIDw8CiAgL1R5cGUgL1hPYmplY3QKICAvU3VidHlwZSAvSW1hZ2UKICAvV2lkdGggNjY1CiAgL0hlaWdodCA2ODAKICAvQml0c1BlckNvbXBvbmVudCA4CiAgL0NvbG9yU3BhY2UgL0RldmljZVJHQgogIC9GaWx0ZXIgL0RDVERlY29kZQogIC9MZW5ndGggMjQxMTcKPj4gc3RyZWFtCv/Y/+AAEEpGSUYAAQEBASwBLAAA/9sAQwACAQECAQECAgICAgICAgMFAwMDAwMGBAQDBQcGBwcHBgcHCAkLCQgICggHBwoNCgoLDAwMDAcJDg8NDA4LDAwM/9sAQwECAgIDAwMGAwMGDAgHCAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwM/8IAEQgCqAKZAwEiAAIRAQMRAf/EABwAAQACAwEBAQAAAAAAAAAAAAAGBwECBQMECP/EABQBAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhADEAAAAf38AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeJ7NdgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeBiscWeenoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA8jFWrPM/QjZJHG7IAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANDWrM2UbfW+M+mvov6FjSL889UvFB5wDjHZa7AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1MVasYz9zinarOOys+CDSf7zuy+q7cPD6HANY3ixTIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABgxV2Z8b9FgafnuRHJlE5qU7PAtSTnh7uIaxTWyTIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABgxWG83Nuq+E+5rsU715zQQltwjn9FyDWGa2aMgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMCtN5eZ7LknVh1b/WZuyGQIu+mdLKJDlzDED1tAzkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMVztJzPdfAff+fb41OFUPEv4puzYPc5xZM55rX2LSGwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYrzMgMyJ5HzUX1PnLs+qFzQ+Shf0LgiksfEYrjFpjcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMV/t1zeSsHxUvzrwOntT9wlJTia8Q7eXzGtaYtIz6AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMCC7faZlb5zSq+Fc58v50tqZmO9RV6GTwMVfm0DPsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACEb7m8ueR6+XCkRQnnfVFFwQjhTAmX158jFWrPNvcAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEN28zaZA0razCjLEltIF4Q6Byk7EteRmrMWaPrAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABEdvhN5tkMRSuDoW5+dbTJ9rsIpK2gqvaxzP2gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAiu/INp4GIlLqqLVVhZxrSk8lx8fQaird5+Z6IAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIztwTefZCGTOvSwc03cZV8Wsf6SJ2tgKw2nBv0wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR3eMG9h4Ga5meCnLDh8SLRr3exSOW4CtEzNuuAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADhbRA2scM4reOHnYvBgR62zGegT1nArbMrNu4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADj7QgzZbJiOQz6SR1F3OoSGOxi9ys7VDFdbSI374AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOVtATazsZM44lTnrd9Ublp0fZUBLc9twr92TeSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA521dGbRxuYrHqwklnbry6yh7D6EaPWwvQIFv95tKcZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHxZrQWo2M8/mdMqq44VDiZwa7I6SLOnoIPt7m0tAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB8uaxFp49TEd4vGONE/0VVx8UUt2eFY2frsIZtqZmWMgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD581eLRx7GeXHY2Qa4voocsC0vKmiY2N4/QIht8ZvNgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeOatFn4+kRiRU2fDi9YySaN1beh+fr7+sYim/LN50AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADzVYYs3H2DlbV4WPxKFmp53h+bpWWP1vswZjG3DNp9kAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANM1YYsjXoBpsQiGXXCDh+0flJYTT1MR7aNG9hAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1VeYsLXpiLyamD47A50FL93/Pn6AIjI/uDg7xI2sXIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYVkazzXrB4+xS83llEk+l0RiR8V58XvDi7Qk3spkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYVuaTTXtDjdP88Hdu7mVWXT5b5KauTYOVmAm1n42AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGM1yYleveHP9aTJ1ypHGiuP0dW8GJJbXIk45ua8M2i2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGFemJHiRB8XznB+Wb/AJ9LgjnegRrcXCk4+DatxaeNwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQA17vnKA+OmidVNZMKPSaxaQmZb4SQfJmsjNpPQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMQY06essHj7YPz/edTck9JZ2JiRCZxeUD5s1eZtLHsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACFGv04l48fSjiy5PXPeJRTlvepAZ/w+4PDarTFoY+kAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYQ8wxMx5OEdShfS7ilpLKo0fBcleWGPNVhizsfWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGImY8NJwGK0LEoW+aXLFqfW2j5/p+aaDRVhiy9fuAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABGjy52k+GmaVNrCp/8AQJ+X7++ynS94rMNhqq80sXXpAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADz9AB58jtjg09f/DOriGzowVkaz3TrAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADCtjEz17QAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAxmGnJlevfAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP/8QAKRAAAgICAgICAgMBAAMBAAAABAUDBgECAGAHEBESFRYTFCExICNwNv/aAAgBAQABBQL/AOPyz6wR67Y2x1EknQSHXE1+PjjxFH1AgjQWHP8AN5ANGH0Ei6hPNqPFtvNfzhRdA4OoTTawRTSy344QXQIflqefryVAZswT9NlkxDGQTLfDggo143Pvj7eUMfNSqf8A+c6ZvJiPUsue8ngARLReSHwxT3IBjX29ms0FnoaywWNeBr5WKF5VbnraNvTV6On5jP2x0XffEerAye7nrl8SsT1eqhu1xTbnq4jvdDkU8or0dujlEinxALGLryw2CJAHXa/KYX0XbbGmrE+a6nLVsSoTj9vhGsC8yRybCeTlRW1sSh2LlNu2GmCaDOBZMf8AOWCwQ18OvIJjzOi7bY1wzYzXI5YrhUB+pI9ZtLPVZamelWpLmEV4hC3228RkRHjxfwQcfvoUASFFK2N6LnP1w1ZzW05QphTBczwxi53twfk2VbKA6Dew2KtkU5jVrNDZl/p68hQhIkk7o3ouc/GHDea0nJ1EKQL35KX7Ak2GyL81tT43NLDrpNgHYL1cCqPjt3CiCSJZ353Rfn446czWM9MmhSB8MYwr8Y2+ccbr9Wi6hpov2nH+e3LiFIElTz2U3GPjoz1zO+NSpIUYfM5+OXCq6WlfUbfOjN12xtjffEerID81acemzeFMGnUT2g/GPjHRXzuZ0WjSQog+OXEKQGGJt5DnrZh9YtFtqcFpCqttnrZljsZFtPrdcgra/jZtCmDVKprcfjH1x0WwPZmhqFDCgC9eR4S4Xa98DogwOb5FfNBGXjw17WxrqorNYgrIPGjSFQGrWz3E7XX646LYX8rEyvoIUAfGLKFUKGZGeMaDGxGtdTkqxVYMCJT3Jpm5NF4uAQuM2cSkNevmuh+uv1x0WxWGUsyu16KvicYMYlYo8snlB4GJoCNwwOM8dlTGaJhVarDWQuMWESsQEKe7n6aYj16LZLDJOTXa7EgF5LNrDgpcde39lpk1YIqFvhson/gefEtFDEmvR8emI9Oi2WySbT1yuRoRuMGMSsUf+55GdRx4i0zj7Yt9PmSmU64xWYX0abGvGFGmvZ8ceItOi2axyaz1qt6IRvV6OmLswX9VaqHsxtrsuOZx9sLq6IqK4WXGCPBFNfToodYI+i2ey7wTVms6IYeGmxrxUlhYWyyWGvQWIFqOcgkri4ZYq9kk6Bwa6y382GHUeLotos2wklZrOqSLhROgcExRHkxwGGOhXLLnPYLM/QQWEGuzsKW99EEaiw4/lv5w4+gsPRbRZshbVis4TR+podZ431fKo7Il+V5IJSI4EINqu+45w2m28PJyNBos7zX88YbQSDotns347lYrGFGvJpdYIkVnEsOOSxazx2KuE0liz8k7NgKZTNa/ByabUeOWSa/Hii6BQdFs9m/F4rFZ/GY9b6431tFUIrJ1StsNnD4ztocDZNTgkhvJJNYY55pr8cGHGAP0WzWb8Tir1rK3Pp7Y5wblzbXG+LZUp0B5Xk/LBVSqZhNpyWXWGMsia+GBBRrxui2ezYTR1is5B39+UR8wFDSfyj821+2F9OBWNOb74j1LMmvRwAES0TotnsuqSGsVnYWT1akRD4X9UsSblhLsBCmg2SJym9774j1OOmux65dErE6LZbLojgrFa3hl9L7kGxbettcb4tFOIRsFc0pAHNtsaasWE1zPWrYVQnRbLZI0Q1ZrcmpHu7Ub+9tSLxhp6sdzHr88e/8ALHzOfrhoynt5ypXEnD6LZLHGgGrdck2J9fu4eH3q70f8nwXyhMOppdO31m5nPxhu0ntZylTCmD6LYrHEgFrlelnL93Wk6WGGmXWSAjlquUFZ1gQBMDfThtNZzU6eFIF0WxWGKvh12vTFGevJLQxWnEu7ZHAkvi93y50yOzjL/IZVfDp9RkLL5n/OOXM1iOSpoUQXRbA/hQB15BMwL9BOBmMrlbq3W+O2GIcOFI9qssglhpeVS2Oxx/Hx6evJnpyRJCiC6K+fQoQkCKZqZ6zj5xZqyTVmNWtMFmC8oJtgWlUTjKU/x88/56fvJnJqJFChC6K8eQoQkKOZ2b6vdznq/AfLv/rE8mqj8PRIkp2rQbyFV/HYxgaL1YX8zM1AghQBdFduoUYSRLNYDefPxw62r12+5a23CyaTeOmetaT2AMnxEv3xVvG36619WKwTHGV+vxIBOiuXEKQNMnmsZ/x8er1YJ9zVfi5ePBYPGX9HWpvtLiu1/t+MWoZWpo3qx2GUoquV2KvidFcN4UgKhVPaTcf57ilwP5W9MoMJvJpIsZemmmI9eWSxSSlVyuRV8XorVrCnDUq57adjH1xwsvQ/Wu2oqrNL/Wt2sVU8jjnxtbeApGpwk9ksPqzWTf8AsVutxoRuitGkKgRYtmuB+uuNccvF4wpxSKj+GjtFXgswNdshNMYtaSssWF/jBaDLFFrBHyzWTfSWtVvREP0VkyiVCLwJrqfppjTXl4sBCYKHxdsYnqNxmUGYz9sWasD2UGhLGaf3ZrLtBLWa1ojh6KwYRLBQQ5rydppiPXjTSaQCq3KUc3H+4uFOhs4lSt86MzXP2x6s1m2DkrNZ1SxdFPOjWiiCy3s6OPEWnD7UArMHaDlYuNPiswlRt8ykvGfnDyrCP9oo8Qx8s9l2AzWK1+I06KaZGvGGgmvp0UesMfGFoEWsfIdAyZtWasFZYv018n5ZVL02LxoYwnX+rPZvxnKxWPxPRii4woIo5r6fDDqPFy6XHSvQUunb6yTsxxybjS5BiKZdI7EPzGPj1Z7N+KxWKz+M6MSToJBppNfj4YNR4uFnafdPN+CuVntw9eXVCsTtTj2EKwcqkDMnGMfGOWezfiI6xWsg56KQRoJD8TeQDRx9BYeXC4RVkSk1eaUq01eCzgKxda5ai2o4C+KIryY2FG0DH5ZrPqkjq9Z2C26LPPqNFttLfzRhtA4OWx/+vKl1Ans49bs5FPYab4l0s9YgsoK2lM2BgIUa4Xlns2qOCs1rYeXos02o8csk1+OFEjCH5N9v4lVwJUuY9sb6WirwWYGvWImlMYpdZ4/VmsuiIes1reOfossusMc5E19ODDjAG4e6FWbwsxyeWesQWcGuWUmpMdN8SaWCtDWQYAHRcJyy2PRCLW61J/Y6LJJiLQoue9HggxrhuGtxl0nkKk5sEVVrgrnOafYF3LFC+MD8YHssberJYo0IlbrkkhPRd98R6nGT3g9eviWC+rnT9LOJT7hKqLutK/JcpV3/ACmP+8xrjHqw2GJAJXK9KUT0XbbGmrE6e6nLlsSoTm2+NOYz8+rjTYrKLUrhMlLslDieGQx/xQ8sNgir4ddr8pxnRdtvrqyZTXI5YshUCctdk0rS0SotLdHUK7PXB9N8SY5aacPaIlweF4XH76FCGgRTMzei5z9cNGk9uOVK4U4fPn/fJ3yM2Y+SVq8HTV5fpphj/HDOPf8Ak09PnsKEJCjmcG9Fzn4w3azWo5SphTB+rlEzr7hU1CvCgehKE+2po8Yz9h+92OPT+OPjx3CiCRJZ3hvRc5+MOm81lPTp4UgXN59I9+Sxazx2Cuk0hkGeJ5CQ48S7/dDXBq6Lxy6hRhpEs9hNxj46Ln/OPHM781KlhRh8ePIEIKAE27Of+epodZ4yaMWlsevz9eOG8KQJMoms52MfGOivXUzs1GkhRBcIkzFCKR+824suBEvTnH3awfPxj22bQpQlKqa2H4x9cdFfvZmpqFDCgD4xaQKhx/JCkqe6UnVzqGYVdG4QcSwSx2YmzslYmwIHGrSJOGrWTW87XH1x0WwPpWRqBBEgD4aXqCImUS+RGO1GV8ceSxVDi21KKzCxWNzYIaxWIa0DxozhUhrV81zO11xpjotisMphder8SAT1f495Kt44KjJq1tFNNUr/AB+GvQeJd5fvGFFFPxixiViLwZ7qfprjTXotksUpBNdr0SATh7CFYOudCtdJ4dSYcbleMnod3WmD2u65d5qdf1rijh50a0UIOe8MNNMR6dFstjl3IrldjQi+r+k/N16u1TD9Xi3uKfuvuim0w7+ME5O6ethoouGmxrxhhpr2fHHiLTotmskuCKzW40I3Djo1wxHkJm0kqF61se39zHjy4LVhHkFk78YAM+VhD+vLuGGRgDxQS342KLWGPotmskkU9ZrWiGDhE2B4ZDZfJr5eBEsEk10M8qeTkX5NLRXWrmv+iitAoIo5r+bDDrBH0W0WXYbesVnVHD6zj5xcaZKATP5FKfL6fUdK2LLHiaKtVeGtR8JJ0Eh1xNfj4INRoui2ezZC2rFZwlj5ORoNHFc1k82M/bHx88uVKkGJpV10skHogjQWHOZr+cMNoJD0Wz2b8fmsVnCjTmc/XBWxHkOxbeMFWNJLStW7wz6ER8DrogDHk02sEUkk1/PFEjCH6LZ7P+L5WKz+LxyabUeOIiNkIofb+PWRDpxfJQ/EwMYfjWeUJj6ll1gjIlmvx4gcYI/RbPZ/xOKvWvxvqabUePMgltVDzF+MGo0oj0U23rUrGx+Rx44PH9b3TA8lkxDGSRLfjQgowBui2ey4TR1ms5A29FDaGDnrS/GzSGYK8JNJC/GDUlCruQyelr0e/N98R6FlzXs4ACJaL0Wz2XVLDWazsJJ6dXzKKxxlxyj2F5Pd2RagzxmcKWFd01RqX6rpzffEepx013PXL4lYnRbNZdEcFYrW0MvNt8R6vbGVa2zesxO07T8lWOVNIKmVEDaFw12mQVszm22NNWTCe5nLVsSoTotlsGEIlYre+s3NtvriyWIi2MEDGXxw2gn0JifIB7CCoYm0Bxpt99ebbY1wzZzXA5WrhTh9G3i1kz630xJqor4qPWwV6CxAqHBXjtmMToXDMLGRnmc/HGzSe2GqlUScPpr5BBYQKTWya2PzP+ccNZ7KcnTwpAuof847bzWE5KmhRhdRevJnhyRHCiC6jYHkzUxEihQh9RtMx0nK+giQB/8Ax/8A/8QAFBEBAAAAAAAAAAAAAAAAAAAAoP/aAAgBAwEBPwFh3//EABQRAQAAAAAAAAAAAAAAAAAAAKD/2gAIAQIBAT8BYd//xAA/EAACAQICBwYEBQIDCQEAAAABAgMABBEhBRASEyIxQRUjMlFgYRQzQlIgQ1NxoXKxYoHBJDRjcHORk6Kjkv/aAAgBAQAGPwL/AJP7TsFXzNYjkfSTSSMFRcyTQY7UWi4Tl5zGgo5Ll6RaR2CqoxJNYYtFouE/+ehHGoVF5AekS7sFVeZrZXaj0ZE3Ef1qWONQqLkAPSJdyFVcya3ce1HouI8Tfq0scahUXIDVLc7O0V5CoJn8Uq7R9HFmOCjmaMEG1Ho6I94/6tLFEoRF5AasMRj5VN/lVn/0h6NJY4AU1tbkx6OjPeyfqewpIYlCogwA1LE0iiR+S4867VtJHlj+tPKppIiNsYbSdRUOxZ723VBs/tWF1o6RT7Cm2LaaMJzZuWtN8+DSHZUdTWPoYknACjaWjNHYRHvZfv8AYUsMKhUTWt5aOyXcGY96+BvwI7tOEhvrqS6sdo2z/MjHSoUjYbyBQrL1FccaN+4rCNFQew1bbcUjZIg5sa7S0jxTv8uM8oh6GJJwAo2dmxjsoz30v3ewpYYVCouqS4KM+x0Fd9aSKPNc6A3pjJ+4ULuwuYkvEzBB8VGwv8Eulyz5PSXejZRBE57xde8kzY5Ig5sa7S0lxTN8qPpEPQ2JyAprGyYpaJ86YdfYUsEKhUX+dZVgGU9KOkLFN5bn5kRzwrerbRLJ9ajmKxhlmh/Y1G6XhIBzbqKVMS2yMMT11GWTNvpUc2Ndp6SGMh+VF0jHobOmsLBilvHlPMP7ClghXZVf513EMVzhKmaRnwsK3OlrV4H+7DKu5ljlBGYrtLRmO6xxkjFCWM8QydfLWZpT+y9WoaS0kM+cEX6Y9Dto7R5whX583l7ClghXBV/n8FtpaHxW5wf9qiuJ40ma4XgTmSa+Ljk+DlfNIx0FLZ3kCywcmkPlRWCNYwxxOGozTH9h9xoaS0iMMPkw/aPQ50bo8kIPnzjkvtSwwrgBzPnqXfSLHtHAYnXLA3KRcKltr3F3tD3SNy/A00zYYch1ahpHSAwj/IgP0+/ofs3Rxy/Pm+wUsUQ/c9WOvZ2isqZxtXZeleFlySQ1iORoknACra/0U6tsPsz+2tppmwUfzQ0hpAERL8iH/U+hzo3Rp4vzphyjFCKIf1N1Y6nnnYKq/wA08omeysh4MPqpdG3kpnim8DGiMQs6eBx0rszSuK7PgkNdl6Mx3f5sopYYhn9bfcdTTTMFVf5oX9+pS3T5EH+p9D9maOPeNlLL0jFCKMZ/Ux5sdcM12DLo5T4V6ULmJlS2Rf8AtRuoGNtbw8Kv1qG4F1Jc2ztgwY1G5GxIwxV/KhFEMWPibq2pppm2UWlvr4FbRDjBCf7msBkPQ3ZujeKY/Mk6Rit3HmxzdjzY6mmncIi9TSyxMGR8waaKVQyOMCKHzJNGO+JA6VEbPZEKjl5VFouy41R8ZX6CooRyjXDU00zBUWhe3ilLJDjBEfq9zQA5D0N2bo7iuG+Y/SIVsJxSNm7nmx1NNM4REo7Um6sbf8vHxUsUahUQYAamilUOjjAg00Ojnb4W7y/orZXilbxueZ1NNMwVEoXd2pjsIz3MR+v3NAAYAehuzdH8d1J426RCtkccr5yOebHVmQMeVNHcK1vYWzcvvpdIaJxAi8aVz2bhPGn4WmmYIi0Lm4Bj0dEe6j/U96CqMAPQ3Z+ju8u5PEw5RCvvmfOSQ82OpppmCIvnQlxkt9GwNw/4qAHSsDmK7U0VirKcXQVh4blPGmtpZWCIgxJNfETho9GxHu4/1fegqgADkPQ3Z9hx3kvXpFWZ3lxJnJIeZOuO20htw6PDZYfVSmIolsi4gjlhSLYd3ZW542P165ZoIgjzHEnU0srBUXmaE0oaPRkJ4U/WoIgCquQHoYWNiBLey/8Az96LMd7cy5ySHmdTzSsFRBiSa3tt3WjoMs/rowzD9m6rSaKu5nSxd/H7VElps7rDxD6vwNJIwRFGJJrafaj0XGch+tQRAFVeQHoZbKzG8vZshh9HuaLyHe3cuckh56mkc4IgxNGGJjHo6E8X+KgiBY4ohW5s4tqyiyeQ00M64+R+2l0dMjz2sp7sjpraSRgqLmSa+qPRcLf+alSNQqLyA9DC0tBvb6bJVH0e5oyyne3c2cjnWUcYq3MV2ho7Frb8xPKo7O1BgtxnMaWCFcFHXzpLDRo312xz8lqNplXe4Z+2pndgqrmSawG1FoqI5n9Y0scahUUYAehltbYb69myVR9PuaaaZt9eTZu5/tqZ2OCrmaf4d8TGcCDz1FXGKtzBrtHRu0Yce8j8qig0dGxvLjJv8Fb2bvbyXN3PTUXchVXMk0Y02otFwnib9aljjUKiZAehhb26729myRB096NxcHe3s2buensNZBGINdp6KyAzkjFAqdmZfGmqPR7d7JNkQM8KluIY+OX/ANdRZjgo5mt1CTHoyI9436vtSxRKFRBgB6GWCAb28lyRBTXN029vZs2b7fYa7K1PDbyf+x1YHMGu09F4jA4vGKSG0iY6Qm4dn7aNzc97ezZsT9OosxCqvM0beAtHo2M8cg/M9qWKJQiJyHoZYohvbufKNBTXd2d7fTeJvt9h+DR14PypM6RvuXHVhT3cUQEj/wAaiTkBRtrYlNHRnCWQfme1LDCoWNOXoYJGN7dS5RxjrRvb0729mzP/AA/YaxFDcm2HXDrX+y3u+XyJp4L2z2057S9Kij2+/hGy69fwEk4AU1nakpYRnvZR9fsKWGFQiJ6GCqN7cy5RxjmaN9fHeXsvn+WPLXLZq2zLGcOLLa14EYg0NJaJxDY8US9aiedNiVl4h5asScAKNlZkpZxnvph9XsKWGFAqL6G5by4kyjjHNjRv7/vLyTkOkY/B8dY91dx55fVXwd33V5Hln9WqKI4yzSnDYWg33DHVnRsbElLVfnzf6ClhhUKi+hs+8mfKOMc2NdoaQ7y7k8I6RDW1g2KSDqeR1/GWXdXkeeX1U1vNC3aK8AHnR0jpHvLuXiAP0asaOj7BikCHv5x/YUsMK7Kr/PobabjlfJEHNjXaWkeK6k8CdIh+Dexd3dx5qw612bpPgnj4VY9dSAjeTSHAIOdRaRNvsz7PXW2jtHsVjGU8w6e1LDCuAXr1PobbbORskTqxrtLSWdy3y06RDWr2p2AWwdvtpJL23+JtnHDKlAJMEkP0tka3keCXSeFx1qSyvIma7j4Y/eu1NJYvcSZqp+jW2jdHnhHz5h9HtQghGAHM/d6GMkmbnJE6ua7S0jnM3y4zyiGuRIZVdojgwBqW3flItXOirvDG2J2dvyrcaKh3e7PezLyru3N5bL/nUF/e2axXC5rrOjdHH/rTDkgoRRD+purH0MZZDi30qObGu09IjvD8qLpGPwdqaMx2McZY6DocJR408qivYmaNZ+CQjpUQtsCHUMW+78HZujTxH50vSMUIohn9TdWPoYyyn+lerGu0tJDP8mHog1x7qDb2+bHlQNxZSAH6l5Vsu5jx6OK7T0NdRHHOSINzqWMYCbZzTqDQiuxhsHBPPDX2bo3OU/NlHKMVuo82ObP1Y+hmmlPLkOrUNJaRGX5MPRNezLcxhvLGng3kUwYcsc6Mc8YudGSnqMdmhIttEyP1FHdPNEfZsq+I+JZwOQ19m6Oznf5knSIVsJm5zdzzY+hmmmbADkPOhpHSCkIPkQnp764tFWJwnn8TfaKHxCm4mPiZjXxOiWeGWPPZx51JYaQQfERjBgetYcU2jZj/APmklTwuMRr7N0bxXLeN+kQrYXikbN3PNj6GaeZsFX+aXSF+uzCvyID09z+B999aYJrgaHL4nxCtmVFdfI1gBgBq7O0fx3UnibpEKwHHM+ckh5sfQzTTNsqv80L+/BW3Q4wQ/wCprDVPawXCrc7P+a0dH6VJ2WPBKaj0jY53EHFl9QoQXp+HukyO11oyPcIfIA4k1Jpi4QpFyhU6xo/R/eXknM9IhX3zyZySHmx9DNNMwVF/mhfXqlLVD3MJ6+5rAZAavhLUh7yXh5+CjdXD728nzZvKjHIMJB4H6iuzdJ4mHHCOQ1vTGu0+e2nWttlaYj7zQVAFUcgNQsLAb28l8uUQ86zO8uJM5JDzPoZppmCotC8uwUsYzjDD93uaAAwA1KlrE0k852VIGQp5biVu0ZOPax5V2XpXFZEyRzqMcq8Y8DdVqa3u2DW8Z7s9dYsbIby9m/8AT3ou53t1LnJIevoZppmCInnS3d0rR2ER7qL7/c0ABgBqkFuwWYrwk0dHaXGzOp4Xbrq+y4TwPXZmlcVIySQ1iOR1i0s1317LyH2e9GSQ727lzkkPoZppWComZoXM4ZNGxnu4j+Z70FUYAdNQgmuESQ9Caxjmjb9jWIwS5TwPXZelOGRTso566o2uI8WjOINBVyC5DUtpaDe3s2SgfT7mmmmO9vJs5HPoZpZWCInM1v5gY9GxN3afq0FUYKOQ1Q2sknfTHAAU9/ajaf60862Ybqa1uo/HGTX+x3+9HkxoC7tVlZOTpzpo7yNgkWSM3PWttbDfXs3gQdPemuJzvbyfN3PT0M0kjBUQYkmt5IGi0XEeFf1jQRAFVcgBq3UXeXkuSIK7R0j3l3LmA30UkEkqLJJyUnnXaejO7nTiZF+qt3J3V3H4kP4BBAN9eTZIg6e9G5uTvr2bN3PT29DNJIwVFGJJrabai0XCch1mNKiDZVcgNRt0lRbll4RTHTSF5XPBIfCK3mIkkk+Wo612tpLEynOJPtreTuETzNQ6RtpTDjm2x9esQwje3k2UaCjd3Z3t9Nmx+32HoZpJGCovMmvqi0XCf/PSxooVVGAA1dHuH8CV2tfuXuZc0GPhFGOQYOPA/UUkemAzomUTN4aNw7qIQMca232otGQnIffSxRjZRBgBqEcY3t3LlHGKa8vDvb2bMk/R7D0MXchVXMmtgbUei4jz/WpY41CIuQA1NMI2kfkoFS3ukZGFxPnGPsrszSeO6xwSQ0GU4g0Y5F4x4G6ihY3kjfA2zY/10sUShEQYADUFQby6lyjjHM0b69O9vpvP8v2HoYu5CqvMmtiPaj0XEeJv1qWKNQqJkBqbY8WGVPY6YGTtij4ZUCuYPKjHIMHHhbyoaN0ljuCeCTyoOpDK3IjWAO8uZMo4xzNG/vzvLyXP/pjy9DFmICrzNGCHaj0bEeN/1aWKJQqKMABqVZ544i3LaNcE0bfs1FHwEg8L+VdmaTx3eOEchoMpxBrdzry5N1FJDGMEjGA1ct5PJlHGOZNdoaQO8u5OQ6Rj0MWYgAdaNtbkx6OiPeSfqewpYolCInIakWaVY2kyXHrXxVue/jXl0YU0Bup7S/j6HrWFvpHeKOW1W5vbJZtnlIgzFNaXML7iMZM3NdeJ45nyjjHNjXaOkOO6k8K9Ih6GJJwAr4W1Jj0fGe9l+/2FLDCoREGvI7FxH4GrszSvBIuSO3WhfWPd3acXD9dfCXfdXseRB+rVy1bTcUrZJGObGu0dI8dy/gQ8oh6GxJwAprKzYx2UZ76X7/YUsMKhEXVmQNeI4LlPA9dl6UxVl4Uc1Fd27/DzqcSy/UKVccdkYY+erePm5yRBzY12lpHinb5cfSIehsTkKNjZMUs0PfzDr7ClhhUKq/zqMp4pGyRfM18Xe3clvt+BFOGFSrPdNOCeHHpWRB/bUNvu5F5OOdRwglhGMMTqMkh4j4V6saGktIjvfyojyjHobGmsLFilunzph/YUkMChUX+dWHWtG3Eo2rWNuKlaOQSsRwotbW01lZ/9qgf4l7i1mbZYGgfPWZZTn9K9WNDSWkRxH5MJ/LHodtH2BIgXKef/AEFLDCuCr/OsaTgdpoBkyeVZhXB8aH6aM+5XLPFjkK2xJGIgOeOVQWdpx29s21I/SgPLUZZT/SvVjXaWkR7wQ9EHoc6N0eSIx8+cfT7UsMIwA5nq2oKzKC3Ieeoq4DK3MGu0dHYmAnGSOim2U2hxqDmtbB0jN8N9lCO3TDzbqdTSzH9h1ahpLSIIX8mA8l9/Q50do45fnzdFFCKFcPM9W1NPM2AHIedLpK6Lw2sR7pBljWGoowBVuYqO50U+xFKeNTyFDHnqaaZsAP5oaR0gMIh8iHy9z6HOjdHHP86Yco6WKIf1N1Y6mYDaKjHDzorpCTcRwnggPWi7lYoYhQuo2a2sLc8P+Os/wNNO2yq/zS6Qv1KQJ8iE/wBz6HOjNGnvPzZekYoRRDP6m6sdRknkWNR50IxcZtkKF7Zd3dpxAr9dQ2GkZtykHiTlt0sUShI0FdmaKJ2ce9lHSooncyMi4Fj11NPM2yi/zS318CtomcEJ/uawGXobs3RpxmPzZOkQoRx5sc3Y82OqSZ/DGMae9vSws0bCOPzpT8LGNg4io7VBvIlykYfTS39gwFyvErL9VLokRskw4ZZPahHGMXPjbqdTTTOERaF7eqY7SM9xF93uawGQHobs3R3FcN8yTpEK2E4pGzdzzY67rd447NQCPDFMm/emjsXCSNkf2qSK4AkkcbTyHnV5Hts1tG2CY00iood+Zw56mmmYKiULy7DR2MZ7mE/X7mgAMAPQ3Z2juO5fxv0iFbI45WzdzzY6jLO4RB1NbUEySf500bZq4wNPwGTR8xxoSC5RcscD0rs7RQM0kuTOOQpIOb83PmdTTSsFROdC5uQY9HRHGKP9T3NBVGAHobs/R427qTxN0iFYDjmfOSQ82OuRVx24+JaW50fcPaXceUiA5Y0E0lDv4uQet1IyAt9Elbax5HybKtm2hVPfrqaWVgiLzJoXE+1Ho6I8Ef6vvQVRgB6G+A0fx3knM9Ix51z3lxJnJIeZOppZWCotO2jLMtBH9RHOmtp03NyozXzqbbxFndDa/wA6F5e4pYRnuo/uraiHw0v3LQhMrzHqzHU0srBUQYk0JZdqPRkR4E/VoKoCqOQHoYWFiN5eTf8Az96JJ3lzLnJIeZOpnbkgxr4cPurGDMr1akhiUIiClNrmI/mEVv0XGW24hULDAPGNlgOmtpJGCouZJreOGj0XEeFf1qCINlVyA9DCyshvb2by/LHmaLud7dS5ySHn+DtPReKSpmyL1qKytImW+l4HPlWLd5cyZyPTKeTDCpRESd620dTSSMFRRiSaxO1HouI5f8alRFCquQA9DCztBvb6bJR9nuaMsp3t3NnJIdReRgqjqa2Fu4tr96x1dp6M4J04mUda3cnd3cfjXW0kjBUXMk1hxR6LhPP9aljjUKqDAD0Mtrarvr2bJVH0+5ozTHfXk2buf7asakt0kaLR9scGw+qkwjZCmeOPOhA11EpXLxcqDowZT1GqS6iiVZZeZ1F3IVVzJNbCbUei4TxH9Y0sUahETIAehlt7dd9ezZIg6e5o3Fwd9ezZu56e2ou5CqvMmtqJw6OOYq7t7uCRo3baVlrdWaNaWp8TGiJy007Dx48jV7o1nMkducVOssxAVedbqItFoyE8bfrUsUShEXkB6GWCAb29myRBTXNyd7ezZux+n21M7nZVRiTUiJIJYX4Tsmt3JtS6OlOR8qSZRHMjcjhjQtJJFifDyyFGCwPxN1Lwrs9Kea4zurk7Te2osxwUczW4gLJoyM944/N9qSKJQiIMAB6GWKJd7dzZRoKN3dnfX0uZb7fbW0bjFHGBr4m12pLCQ8S+VfTJHIMx1WtltqbR0hy9qW4aNJdsZMOdBoIFDj6jnqLMcAKa1ty0ej4j3kv6nsKWGFAkacgPQwSPvbuXKOMdaN7eHe303PyT2GtLeeBktmHzK3quDGRjjXZmj/8Adwe9lpLm2LT2bfMFdJI5BmvValUTPIrnEA8hqJJwAo2lqSljGe+l+/2FJDCuyiehgqje3MuUcY5mjfXx3l7Nnn+X7DViTgBQ0fosssaHvJhQtrnjYLht4Z412XLMyWkrZSe1RrbbLhhiZPupo5FDI2RBqeWF3wm+nourE5AUbKzLR2cZ76b7vYUsMKhVX0NiEaSWThjUdTRv787y9k5eUY1YnkK7M0YTu+UstG0vYx8PMcpQKDoQytyNGGdcfI+VCxuQ81nIeA0D56sT0o2NixS1Q4TzefsKWGFdlF9DjFQcNZU5g04t4wm2cTTQzKPY9Vr4K+xezc8L+VLJGwZG5EUu2its5jEctZsLA7Nuvz5x/YUsMKgKv8+jmgnUHyP21LFNPvY8e7HkNbaO0e2zGvz5h09hSwQLgo/n0kdG6PbhGU832e1LDCMhzPVvSR0bo45/nTDklCKEf1N1Y+kuzdHZyH50vSMUIohn9THmx9JR2tkhBuMml/TFbtOJzm7nm5/5Qf/EACkQAQACAQQDAAEEAwEBAQAAAAEAESEQMUFRYGFxgZGhwdEgsfDx4XD/2gAIAQEAAT8h/wDx9sbbpQQGl5B78SCgvQTPQ0bA/iEvRUPEbATgoCKiyy7KOPkEwtAoPER1FaOCXj6cKOPkDsvQeIgpCxsEbPgHhZweoM5aBtpty9exm1PaPfhx3S2mxG+p+zfo9Q550DQfZucsz8j/ANp/wevDS6GtXYlKaw4UQoJsGm+5yyj8llw/OocVmTS3CQbhxmKnl66Gu0wq3WoZqsOU+SoGznwYux7V2JSQQfOAjWoDnXbwUv6YkKHDzzYD2f8AqXhBfglK+CsbP2aq0WL/AAm0fcryv7HgxhshXiV1oHME9aA596UpFg92VYBvsp8u4qnFM25LnJ+LLZQW2D0Q0dECv6cETvYP+RcqvBUaGQrxFqJX7qQhY/n2dLzDPjSjDARtslBz+JfWDApZ8/8Awww71CijrNBb3tooNmPmQQ9uA/xNd+DGqqDePUL+UIOsX5XbpesRaWfVenCFjVXNHxuORa9kSfNcHOOpTi94evoNFudE33uztwKdwKPBbhsK7i8WUe3sQTRMvL7f8D2Q9HMCwTeYKX4puKghRZEf1SuYCG7pVpNufoIh3ebbiX3Ao8FQFcBGpp0Do9wGH0D7dHwbaq2ASIjs6CuKMjZllmli98FAMVKzobQD2nREBhnYDsdwTRx4K4IrobB25A9zb75f8h0E2oE4JPHAzIVHGMBoOQYRYVq8S0LsBrDe5szvoTbanK6IgR8uwdHcEgKDB4KtE3OKsrkB7mVpun5DoMEtlz6EUttGwiDhnJuvc3OK3S6iGoVdeXEtxsq+zbB7Tfu0AeGC8voiXKtNpGAABt4KtRXwBfV+ZeKec7tLqQRlYD6ls3NYNu06v3OnqFzXYv1grgzGfSERyuc0BeW/b8mwVUVwAgAwB4NZDh/1Z7gsv3ZJdNxj8AGNQ5l15gi1vL8C9mIXRe33AvrCcE2SBpDet28+pt6LF8UExQUB4KsthxX/AIOpYMi9VarBi4XyGUTnA20YSQCblfadkEGZ+8NBEpavMukbgx9jUAUHgw/hA57C9z7NdaAC7Ys1b1KDflxX9GF4gKgSlwj61rQFJ2rLY0F3XaDwHQHHgxcNhkdhjUPfHSFZm1VR5PFRRrOAJV8sNwE3HmBejn16IF2Wm3vuGls+gVBVoDbrt6g8noCg8GEgYych2x49iTRXKVEdI7YrHRDg3j07Dsdpsy3DQQR3JU5otDcPaTKwLYV2+oVkKGweC8zBEHGQdownnyn9ayBwVo3FEB/f1iYaPRs37lsTIfuP8OuGIIeC37Sjn5DUlQMHgq1NhB3IMO6pCT0etBPXB0TnaO5nOOa7fli6knZF7IEUJt5XZKemBX9wcaBAWw2mX6U4WfxACJWAHg3o3YBDumqs9HrUFJqTZmX0bHNIPYizT7gKi55LthUSSuYE5HazbmVCbPwBKr1LYS/iAEUDg8G+o20HtLJj00pRCzolKuw19aAdJQMMzDdh2H9Q2wUfvl0uxCng0OyVhQT6d5X0eod5KBt4N7zrn29T2X9NYLYFI8zMp/Ee8dQKgsm46D24C0+4RAay/wBhoY4tpsTHR7ZX09Sq4weDH3j2T2+purKtQ3FTc5NFIGAjzMw/gX/yZ7QaXfuKD2L+DQV4WmwTi5ry+nqFPCg8GbTyGb7fU3I8TIdS7mIHC+skoJui340B0CO48z9Mwfzo7YK1eJt78l7CGJSgTbwXumMldvqbunTkGpa/tvdDdSD6v1hJLca2sDAyTgV/gBYFq8ShHrGtAFpUB4N3Ek69/JujLyOhotR2eGfsNWIMBEgAlvIqoQ23bRgDIV4lN+myiN8tUG/vwYAH/Lg2ih07T8I/we5+PxgNO7GB/wDUGBORGVCEMIBR30NFAGVilbouZDPCvb7fBjiHlnE6flOegSq0GXjTGnUGzTlHp/8A7i5T4X64gTzXzm0NkgG7KJQNhXLQD3I5Xb4NUhgZMqM1PkA7/wAMPybB8szy/jHg2XvEjGbugnlvhtfZ3DEWiNRPgj0e4RwWeT2+DX1u1oqib1c/2NU1UL3Eq0YgvE/J5GdS64N/tEDdNtb+LiX1l4gURAtajUjFshyHub5lci7fBlY2K+QCCuWZ3A/MqLU3ivADB0tB8ZmcMOU1znhRfUtvFuXXyYldjl+sAKNotESljH4iHuHvOU/IfBr0RjO4QQ3ayvn+1ArQGHZmdIUPJzDAFybufzJCd+kPHkwtN2IGS4FMYi0TOJsP4F9zuEX/ACnwbLP2D4ggXu0/KfcCjSp18tn9S/n/AFf+k4eS4ELkFR8IyG7GxmVDwf3NFqKG2eR9XcBDuF+U+DUMwY9zojkuT7HC+4FRouPgrcWSdNTCnuYKBq//AEh3tsvEjqi22EF2No8X90ZcsxC+j8wKXfDteDDW+4fRG3LeyPCO4DZovTZ28FQpvcGEu1LhZRQHPt9lfT7GLCKVxTr1YpZ7H2fQnbtX4MIomDl9ERkq9gOIAFGA0uc3wd703m0PYve8yuq6bIAMCgONBnGjz2H3GHvAeDIF4vyuiWPWPqRgAA4lw+0RhghbzR/xHEAUvLYejFE04ou2PUBeB6nvWnG1L8hYspefvgwA+t539DS2W2BgbAONNr7VYWl8AbDeXEMQSwMsyCvAg/qUNgpjYObHsgfiCFGgUGm14Sr6Edvu0P8AXgwS1tvn1LNVFi8ATAoDjTOxgxY6JjbT3qZkB3cGwRGGsAw7kxCIZtf/ADWnxqOj2nvAPF0epXgoCStVL6QGx6wNsOgONEnYAWDCrJ1++I6EbGbAA2G99MIXP2vRcE0HIPcrQd2EOQdp2S6L8PXgwy2sWW1kYijl6gQD0DY0AFtwDwLtSzkyA7y87xz9MwCp3g+KoYfkH6lR1rsW0DuGk/D14MM8rSHLfkws5fUEcWg2IymBSa6+xiVN9TsmzAdBnslq63b+5MnoKyf0nCBa36rV+IrI+3RAP9XsHrwa58gMQ5nS8PY+ockKCgNBNPMBPbEqPOyO/DcRc1Xsk4kgplL7JvBOAPmg9/rvp6n3aw+XrwbqWKCUK6Lf0IWEdDY0EoAu5uFyJReyGASp9r4/EPunZ2MYF+8LF4udEVZ1oH5j3dvqb6gvJ4NDoJaKCILfHcKP4lSJwUEZXBDqzN9s9PUE0EYawMsyztix0/JWl0th+SsfW6TU0A407DdhXt9TuI4K+DATysO0frnows/iHHmgoNEdk1F5YV5sr8INejohxnqDAzxNmH2Mg7swx+K/olM8gaJ05iJK7QtcgeDAWStGCIsQ9pXR6hKmpcaFFQuz2uCPLT/BEgBWjZIabZe7l+Kg2w/qG7C0WOoY90F/qbURBydTwYF6WmwRzK3s3OD1KxvgrTZxCouVWfyUQ8sgW7n425q4zDwGsRsYdtqsIN+pLQ+wsV2DLbPSIYPBS4PatiUGPlh/iQL4UE2jj5oUXivOwOiRFsfETRZIVXFXY0Sy+KxfR/g6YB9Tl/pg8GAsK1diKasi5wrcgANT6+Yf+plkiQpJ57D/ANQy9dnGVTIRVoF9Gn9Y7mlR/wCFCHfgzgQLV4laBQxFLGgOdP3YGCbETSjoSx3PTDYJ8M9SiyMIZtMLN9CW39KiJsTNt+w78GBkAyvUqOQ/dSGfesb+zoRQiatD8sMcSzO63bLYb2r0znXe1QXIbFrpa2wH+EEvLvP7DdwK8FNUgBasRldc6BlU6yu3Su8ekuFxvgbJ2ZXn5GhUaN2kZVzDdQB9jevQ8B+AE501sOJrvwYWVAN2K3WlsH+yAuJl5Xbr8P5P8lXEK3ajWAzWoALWAChENvalKj41pnX7BnoENvHcfAvuBXgoIuxPTRQDp7hr/pDt0wqXRp0GNCgWMSY+nYOfxLL6qgb+oi924cfrCi4ZP33Q6pX3OiEoDe0e3uUKMB4KqX1FbAaD8I9wZgM8x2ugOg+x9EsclZASttANpScwmGdn1nEMM1pxnucvoisE2+x/2QCCgPBVqZcbGwOQe5lKGU+hdEfsA7wLJwQuTHcibE2f8EuUgO4Nmpjdl2uiMlC9lAwAAMHgtzr4y/5Mt5LL57TpyJiVXAtjZCiO0A2KNvyEyZKcHgOIQJ9Cp7hVXbwtBtBvP7CNlTsvRBEAGweDcclP9x3Dq/fEl0ZCkuOVsJond1JjG0FKneyRQFaUffuKWbgJ7wM2Qu7oC4bt59Ev20X9hBIAKA8FcT03J/0Z/f4Nl1DsCyHJOOw5xGW9Y7/EeDfZD6YOy3csMzYS95aCktav+pYpZAhbAoDY8FWp0qYfQvuZQ8ttBuufgDEPWUN2wCXZskNv/sJANi0xspB2CG6kNIHbe0zgDTd/9EPAegNjwYFtuOeQvuP/AGMNclmYo5qZMLwsOWGTXAwsAYBpEuREuQB8BtfnQ992iHJl/ixy9QcB6AKDwYCYbs7SKrXIDRH+e1Y6Rs3Ky7/lmx9SlhAa4gKr0uPzls/hWj9Jld92Glxxi4meMews5fUBeNAoPBiVY1mQdog/S4rfrjr1HNzZFfilDMwEIcqcEazP9+OHJKsI88NTrLYUEJWiphRy+oDkFDg8FubVxBkQO2HpT181CpBHeZSNt31OkBiONxqxlndeoYdoU3nxnHrS1hoKAnCvpws/iH2egoPBtrXTkEPbZUX0etAYfalBDLSaO0MQ2ORiB9mZi/8AbSVWo0vNc6kQWwwErf40v+pQYYNjwZ+5GkHsPrj09aGq2C5ds873L4BKatO4MiStIzMvEsZVjebmyJeNB0lYUE4DNdy4PUIWVBt4N9LAP6k9vYL4etBsleAEdsGCYjsVhb5m3qWAp9mcQBL6EvUpLdetRHpaXaLOuHV0eoSE6B4LcDseNXtiPteHw0AhbDgl0wUyDLI9rCQIdaCkyhIopBs4KBYnmcuIO/poMQto4CNaKsKxw9eDncwd6CU/X1Ct5KzXpraPgOyffzGdnUBN2dO7P+5hCTgcbMUMg0PAe1diUEmli5AFCV4KLqD12cV2+pvKwnI6C0Zlb51+XuA6kN8VFg36xK4RHVEtYg25Dt4aYdALAtXiVRvhiAChqA8GUn2jP6n4fkDoMDGtXYgQ1u0xKMDBtO0yj7OuW5p03fcIgtBYx8BMN0HTgLV4lLsAxH9esG/t8GKseigOLND+AaA6VkWJBA0tj1H+P5AXDflYcwxoR7nK9QzLq+pQt0L0VoBkvUouCHCRjwfl9vg6A27LLrUSQdJ3LkZvyxuazbydkKb1fIYHBbRYxYfdtloFQBbgI61VdCBfEzjK7fDigie9dxFaaWVL6jsEVshyncPBvXl9viK0zK9WB7DkPcrKhk3O3xFaIgbhsHoe4S29/wBRPiK0S9+wD8D7L4Dz+cnxIz0oPm/MG2/uQi//AJB//9oADAMBAAIAAwAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACwQgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwDigAQgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACARxCCSQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwCgQCRwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwQjQwTCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATgAhziAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACCRyTiCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACSByiSRiAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACiRBiCBiAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASCBRQQTyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABQQBzBAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACBgDCSgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATByxCRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABATARCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAhBSRyxQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABiwDSRxgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABhwAzBTyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADggRiDgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAixwSTAyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACjBgQBigAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACQjAjByAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQDyRQhCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABTQyxDAyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABxATAQDCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAzhjiiRhAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABTgiCCDwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABjgwwjBBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADACxDAzgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABghSgwASgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABQBySigTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABQARQAACgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASATRCgRAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAzAAzihwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARAgQgBDgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABhARhggDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAyAxjyAygAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQhhyjADgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADAADwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABwAAAAAAAAAAAAAAAAAAAA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IDAgMTI2My4zMzI5MDEgY20KcQoxIDAgMCAxIC0xMTYuOTUwMTk1MyAtMjYgY20KcQoxIDAgMCAxIDEwMDAwIDgwNzMuNDMzMTA1NSBjbQpxCnEKcQpxCnEKLTk4ODMuMDQ5ODA0NyAtODA0Ny40MzMxMDU1IDIxMDUuMTAwMDk3NyA5OS4xMzMzMzEzIHJlClcgbgpxCjAuOTI1NDkwMiAwLjIxNTY4NjMgMC4xOTYwNzg0IHJnCi05ODgzLjA0OTgwNDcgLTgwNDcuNDMzMTA1NSAyMTA1LjEwMDA5NzcgOTkuMTMzMzMxMyByZQpmClEKUQpxCnEKcQotOTg4My4wNDk4MDQ3IC04MDI2LjM4MzMwMDggMjAwIDUyIHJlClcgbgoyMDAgMCAwIC01MiAtOTg4My4wNDk4MDQ3IC03OTc0LjM4MzMwMDggY20KL0kxIERvClEKUQpRCnEKcQoxIDEgMSByZwoxIDAgMCAtMSAtODA4MSAtNzk4NS44NjY2OTkyIGNtCkJUCi9GMiAzMCBUZgowIFRyCihHZW5lcmFsIEluc3VyYW5jZSkgVGoKRVQKUQpRClEKcQpxCnEKcQpxCnEKMC45Mjk0MTE4IDAuMTk2MDc4NCAwLjIxNTY4NjMgcmcKMSAwIDAgLTEgLTg5MTMuNSAtNzkxOC4yOTk4MDQ3IGNtCkJUCi9GNSAyMCBUZgowIFRyCihLb3RhayBDYXIgU2VjdXJlKSBUagpFVApRCnEKcQpxCnEKLTc5MjIuOTUwMTk1MyAtNzkzOC4yOTk4MDQ3IDEyMCAxMjAgcmUKVyBuCjEyMCAwIDAgLTEyMCAtNzkyMi45NTAxOTUzIC03ODE4LjI5OTgwNDcgY20KL0k3IERvClEKUQpRClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg4OTkuNSAtNzkwMS4yOTk4MDQ3IGNtCkJUCi9GMyAxMyBUZgowIFRyCihcKENvbXByZWhlbnNpdmUgUG9saWN5XCkpIFRqCkVUClEKUQpxCnEKMC45Mjk0MTE4IDAuMTk2MDc4NCAwLjIxNTY4NjMgcmcKMSAwIDAgLTEgLTg5ODMgLTc4NzkuMjk5ODA0NyBjbQpCVAovRjUgMjAgVGYKMCBUcgooQ2VydGlmaWNhdGUgY3VtIFBvbGljeSBTY2hlZHVsZSkgVGoKRVQKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04OTY4IC03ODU3LjI5OTgwNDcgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKFBvbGljeSAvIENlcnRpZmljYXRlIE5vOiBQVEYvMjAxNzAzMTUxMDAxMTkzKSBUagpFVApRClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5NjMuNSAtNzgzNy4yOTk4MDQ3IGNtCkJUCi9GMyAxMyBUZgowIFRyCihGb3IgYW55IGFzc2lzdGFuY2UgcGxlYXNlIGNhbGwpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODk1NC41IC03ODIyLjI5OTgwNDcgY20KQlQKL0YzIDEzIFRmCjAgVHIKKG9yIHZpc2l0KSBUagpFVApRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODc4Mi41IC03ODM3LjI5OTgwNDcgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKDE4MDAgMjY2IDQ1NDUpIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5MTEuNSAtNzgyMi4yOTk4MDQ3IGNtCkJUCi9GNSAxMyBUZgowIFRyCih3d3cua290YWtnZW5lcmFsaW5zdXJhbmNlLmNvbSkgVGoKRVQKUQpRClEKUQpRCnEKcQpxCnEKcQpxCnEKLTk4ODMuMDQ5ODA0NyAtNzc4My4yMDAxOTUzIDEwMTAuNDUwMDEyMiAzMCByZQpXIG4KcQowIDAuMiAwLjQgcmcKLTk4ODMuMDQ5ODA0NyAtNzc4My4yMDAxOTUzIDEwMTAuNDUwMDEyMiAzMCByZQpmClEKUQpxCjEgMSAxIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTc3NjMuMjAwMTk1MyBjbQpCVAovRjUgMTIgVGYKMCBUcgooSU5TVVJFRCBERVRBSUxTKSBUagpFVApRClEKcQpxCi04Nzg4LjQwMDM5MDYgLTc3ODMuMjAwMTk1MyAxMDEwLjQ1MDAxMjIgMzAgcmUKVyBuCnEKMCAwLjIgMC40IHJnCi04Nzg4LjQwMDM5MDYgLTc3ODMuMjAwMTk1MyAxMDEwLjQ1MDAxMjIgMzAgcmUKZgpRClEKcQoxIDEgMSByZwoxIDAgMCAtMSAtODc2My40MDAzOTA2IC03NzYzLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKFBPTElDWSBERVRBSUxTKSBUagpFVApRClEKUQpxCnEKcQpxCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTc3MzMuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooTmFtZTopIFRqCkVUClEKUQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTc0OS42NTAzOTA2IC03NzMzLjIwMDE5NTMgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKHNhbmRlZXAgamFkaGF2KSBUagpFVApRClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTc3MDkuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooQWRkcmVzczopIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk3NDkuNjUwMzkwNiAtNzcwOS4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihEYW5nZSBDaG93ayBLYWxld2FkaSBNYWluIFJvYWQgTWFuZ2FsIE5hZ2FyIFBpbXByaS1DaGluY2h3YWQgTWFoYXJhc2h0cmEgSW5kaWEgTWFoYXJhc2h0cmEgLSA0MTEwNTcgRGlzdHJpY3Q6IFB1bmUgTWFoYXJhc2h0cmEsIEluZGlhKSBUagpFVApRClEKUQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC03Njc0LjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKFBob25lOikgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTc0OS42NTAzOTA2IC03Njc0LjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKE5BKSBUagpFVApRClEKUQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC03NjUwLjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKE1vYmlsZTopIFRqCkVUClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk3NDkuNjUwMzkwNiAtNzY1MC4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCig5MDI4MzI2NzA0KSBUagpFVApRClEKUQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC03NjI2LjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKEVtYWlsOikgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTc0OS42NTAzOTA2IC03NjI2LjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKHNhbmRlZXAuamFkaGF2QHBvbGljaWVzMzY1LmNvbSkgVGoKRVQKUQpRClEKUQpRClEKcQpxCnEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg2MjYuNDAwMzkwNiAtNzczMy4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihLb3RhayBNYWhpbmRyYSBHZW5lcmFsIEluc3VyYW5jZSBDb21wYW55IExpbWl0ZWQ7IDh0aCBGbG9vciwgWm9uZSBJViwgS290YWsgSW5maW5pdHksQnVpbGRpbmcgTm8uMjEsIEluZmluaXR5IElUIFBhcmssT2ZmIFdlc3Rlcm4gRXhwcmVzcykgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NzYzLjQwMDM5MDYgLTc3MTguMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooSGlnaHdheSwgR2VuZXJhbCBBSyBWYWlkeWEgTWFyZywgRGluZG9zaGksTWFsYWQgXChFXCksIE11bWJhaSAtIDQwMDA5Ny4uKSBUagpFVApRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODc2My40MDAzOTA2IC03NzMzLjIwMDE5NTMgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKFBvbGljeSBJc3N1aW5nIE9mZmljZTopIFRqCkVUClEKUQpRClEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg3NjMuNDAwMzkwNiAtNzY5NC4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihQZXJpb2Qgb2YgSW5zdXJhbmNlOikgVGoKRVQKUQpRClEKcQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODc2My40MDAzOTA2IC03NjcwLjIwMDE5NTMgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKEZyb206IDAxLzA0LzIwMTcgMDA6MDApIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MzMxLjIwMDE5NTMgLTc2NzAuMjAwMTk1MyBjbQpCVAovRjUgMTMgVGYKMCBUcgoodG86IDMxLzAzLzIwMTgpIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MTM5LjExNjY5OTIgLTc2NzAuMjAwMTk1MyBjbQpCVAovRjUgMTMgVGYKMCBUcgooTWlkbmlnaHQpIFRqCkVUClEKUQpRClEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg3NjMuNDAwMzkwNiAtNzY0Ni4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihQb2xpY3kgaXNzdWVkIG9uOiAxNS8wMy8yMDE3KSBUagpFVApRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MzMxLjIwMDE5NTMgLTc2NDYuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooQ292ZXIgTm90ZSBObzogTkEpIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NzYzLjQwMDM5MDYgLTc2MjIuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooSHlwb3RoZWNhdGVkIHRvOiBLT1RBSyBNQUhJTkRSQSBQUklNRSBMVEQuLikgVGoKRVQKUQpRClEKUQpRClEKUQpRClEKUQpRCnEKcQpxCi05ODgzLjA0OTgwNDcgLTc2MDkuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKVyBuCnEKMCAwLjIgMC40IHJnCi05ODgzLjA0OTgwNDcgLTc2MDkuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKZgpRClEKcQoxIDEgMSByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC03NTg5LjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKFZFSElDTEUgREVUQUlMUykgVGoKRVQKUQpRClEKcQpxCnEKcQpxCnEKcQpxCnEKcQpxCi05ODQwLjk1MDE5NTMgLTc1NjguMjAwMTk1MyAyMDIuMDk5OTkwOCAzOSByZQpXIG4KcQowLjgyMzUyOTQgMC44Mjc0NTEgMC44MzUyOTQxIHJnCi05ODQwLjk1MDE5NTMgLTc1NjguMjAwMTk1MyAyMDIuMDk5OTkwOCAzOSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC45NTAxOTUzIC03NTY3LjcwMDE5NTMgbQotOTYzOC44NTAyMDQ1IC03NTY3LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzU2OC4yMDAxOTUzIG0KLTk4NDAuNDUwMTk1MyAtNzUyOS4yMDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05NjM5LjM1MDIwNDUgLTc1NjguMjAwMTk1MyBtCi05NjM5LjM1MDIwNDUgLTc1MjkuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NzcyLjkwMDM5MDYgLTc1NTAuMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooUmVnaXN0cmF0aW9uKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk3NjAuNDAwMzkwNiAtNzUzNi4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihOdW1iZXIpIFRqCkVUClEKUQpxCnEKLTk2MzguODQ5NjA5NCAtNzU2OC4yMDAxOTUzIDIwMi4wODMzNDM1IDM5IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTk2MzguODQ5NjA5NCAtNzU2OC4yMDAxOTUzIDIwMi4wODMzNDM1IDM5IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05NjM4Ljg0OTYwOTQgLTc1NjcuNzAwMTk1MyBtCi05NDM2Ljc2NjI2NTkgLTc1NjcuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTQzNy4yNjYyNjU5IC03NTY4LjIwMDE5NTMgbQotOTQzNy4yNjYyNjU5IC03NTI5LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTU3NC4zMTY0MDYzIC03NTUwLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKE1hbnVmYWN0dXJlcikgVGoKRVQKUQpRCnEKcQotOTQzNi43NjY2MDE2IC03NTY4LjIwMDE5NTMgMjAyLjA5OTk5MDggMzkgcmUKVyBuCnEKMC44MjM1Mjk0IDAuODI3NDUxIDAuODM1Mjk0MSByZwotOTQzNi43NjY2MDE2IC03NTY4LjIwMDE5NTMgMjAyLjA5OTk5MDggMzkgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk0MzYuNzY2NjAxNiAtNzU2Ny43MDAxOTUzIG0KLTkyMzQuNjY2NjEwNyAtNzU2Ny43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MjM1LjE2NjYxMDcgLTc1NjguMjAwMTk1MyBtCi05MjM1LjE2NjYxMDcgLTc1MjkuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MzUzLjIxNjc5NjkgLTc1NTAuMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooTW9kZWwpIFRqCkVUClEKUQpxCnEKLTkyMzQuNjY2OTkyMiAtNzU2OC4yMDAxOTUzIDI2Mi43MzMzMzc0IDM5IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTkyMzQuNjY2OTkyMiAtNzU2OC4yMDAxOTUzIDI2Mi43MzMzMzc0IDM5IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MjM0LjY2Njk5MjIgLTc1NjcuNzAwMTk1MyBtCi04OTcxLjkzMzY1NDggLTc1NjcuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODk3Mi40MzM2NTQ4IC03NTY4LjIwMDE5NTMgbQotODk3Mi40MzM2NTQ4IC03NTI5LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTEyMi43OTk4MDQ3IC03NTUwLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFZhcmlhbnQpIFRqCkVUClEKUQpxCnEKLTg5NzEuOTMzNTkzOCAtNzU2OC4yMDAxOTUzIDE0MS40NDk5OTY5IDM5IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTg5NzEuOTMzNTkzOCAtNzU2OC4yMDAxOTUzIDE0MS40NDk5OTY5IDM5IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04OTcxLjkzMzU5MzggLTc1NjcuNzAwMTk1MyBtCi04ODMwLjQ4MzU5NjggLTc1NjcuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMC45ODM1OTY4IC03NTY4LjIwMDE5NTMgbQotODgzMC45ODM1OTY4IC03NTI5LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODkyMC43MTY3OTY5IC03NTUwLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFllYXIgb2YpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODkzNS43MTY3OTY5IC03NTM2LjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKE1hbnVmYWN0dXJlKSBUagpFVApRClEKcQpxCi04ODMwLjQ4MzM5ODQgLTc1NjguMjAwMTk1MyAyODIuOTMzMzQ5NiAzOSByZQpXIG4KcQowLjgyMzUyOTQgMC44Mjc0NTEgMC44MzUyOTQxIHJnCi04ODMwLjQ4MzM5ODQgLTc1NjguMjAwMTk1MyAyODIuOTMzMzQ5NiAzOSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMC40ODMzOTg0IC03NTY3LjcwMDE5NTMgbQotODU0Ny41NTAwNDg4IC03NTY3LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg1NDguMDUwMDQ4OCAtNzU2OC4yMDAxOTUzIG0KLTg1NDguMDUwMDQ4OCAtNzUyOS4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg3MjcuMDE2NjAxNiAtNzU1MC4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihSVE8gTG9jYXRpb24pIFRqCkVUClEKUQpxCnEKLTg1NDcuNTQ5ODA0NyAtNzU2OC4yMDAxOTUzIDE0MS40NDk5OTY5IDM5IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTg1NDcuNTQ5ODA0NyAtNzU2OC4yMDAxOTUzIDE0MS40NDk5OTY5IDM5IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NTQ3LjU0OTgwNDcgLTc1NjcuNzAwMTk1MyBtCi04NDA2LjA5OTgwNzcgLTc1NjcuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODQwNi41OTk4MDc3IC03NTY4LjIwMDE5NTMgbQotODQwNi41OTk4MDc3IC03NTI5LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODQ5Ni44MzMwMDc4IC03NTUwLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKEVuZ2luZSkgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NDk5LjMzMzAwNzggLTc1MzYuMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooTnVtYmVyKSBUagpFVApRClEKcQpxCi04NDA2LjA5OTYwOTQgLTc1NjguMjAwMTk1MyAzMDMuMTUwMDI0NCAzOSByZQpXIG4KcQowLjgyMzUyOTQgMC44Mjc0NTEgMC44MzUyOTQxIHJnCi04NDA2LjA5OTYwOTQgLTc1NjguMjAwMTk1MyAzMDMuMTUwMDI0NCAzOSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODQwNi4wOTk2MDk0IC03NTY3LjcwMDE5NTMgbQotODEwMi45NDk1ODUgLTc1NjcuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODEwMy40NDk1ODUgLTc1NjguMjAwMTk1MyBtCi04MTAzLjQ0OTU4NSAtNzUyOS4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgzMDAuMDMzMjAzMSAtNzU1MC4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihWZWhpY2xlIENoYXNzaXMvKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgzMDcuMDMzMjAzMSAtNzUzNi4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihUcmFpbGVyIENoYXNzaXMgTm8uKSBUagpFVApRClEKcQpxCi04MTAyLjk1MDE5NTMgLTc1NjguMjAwMTk1MyAxNDEuNDQ5OTk2OSAzOSByZQpXIG4KcQowLjgyMzUyOTQgMC44Mjc0NTEgMC44MzUyOTQxIHJnCi04MTAyLjk1MDE5NTMgLTc1NjguMjAwMTk1MyAxNDEuNDQ5OTk2OSAzOSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODEwMi45NTAxOTUzIC03NTY3LjcwMDE5NTMgbQotNzk2MS41MDAxOTg0IC03NTY3LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc5NjIuMDAwMTk4NCAtNzU2OC4yMDAxOTUzIG0KLTc5NjIuMDAwMTk4NCAtNzUyOS4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgwNDguNzMzMzk4NCAtNzU1MC4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihDdWJpYykgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MDU2LjczMzM5ODQgLTc1MzYuMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooQ2FwYWNpdHkpIFRqCkVUClEKUQpxCnEKLTc5NjEuNSAtNzU2OC4yMDAxOTUzIDE0MS40NDk5OTY5IDM5IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTc5NjEuNSAtNzU2OC4yMDAxOTUzIDE0MS40NDk5OTY5IDM5IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03OTYxLjUgLTc1NjcuNzAwMTk1MyBtCi03ODIwLjA1MDAwMzEgLTc1NjcuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzgyMC41NTAwMDMxIC03NTY4LjIwMDE5NTMgbQotNzgyMC41NTAwMDMxIC03NTI5LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtNzkxMi4yODMyMDMxIC03NTUwLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFNlYXRpbmcpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtNzkxNS4yODMyMDMxIC03NTM2LjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKENhcGFjaXR5KSBUagpFVApRClEKUQpxCnEKcQotOTg0MC45NTAxOTUzIC03NTI5LjIwMDE5NTMgMjAyLjA5OTk5MDggMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTk4NDAuOTUwMTk1MyAtNzUyOS4yMDAxOTUzIDIwMi4wOTk5OTA4IDI1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjk1MDE5NTMgLTc1MDQuNzAwMTk1MyBtCi05NjM4Ljg1MDIwNDUgLTc1MDQuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC40NTAxOTUzIC03NTI5LjIwMDE5NTMgbQotOTg0MC40NTAxOTUzIC03NTA0LjIwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk2MzkuMzUwMjA0NSAtNzUyOS4yMDAxOTUzIG0KLTk2MzkuMzUwMjA0NSAtNzUwNC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTc3OC40MDAzOTA2IC03NTEyLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKE1IMTJTUzIyMjIpIFRqCkVUClEKUQpRCnEKcQotOTYzOC44NDk2MDk0IC03NTI5LjIwMDE5NTMgMjAyLjA4MzM0MzUgMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTk2MzguODQ5NjA5NCAtNzUyOS4yMDAxOTUzIDIwMi4wODMzNDM1IDI1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05NjM4Ljg0OTYwOTQgLTc1MDQuNzAwMTk1MyBtCi05NDM2Ljc2NjI2NTkgLTc1MDQuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTQzNy4yNjYyNjU5IC03NTI5LjIwMDE5NTMgbQotOTQzNy4yNjYyNjU5IC03NTA0LjIwMDE5NTMgbApTClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NTYxLjgxNjQwNjMgLTc1MTIuMjAwMTk1MyBjbQpCVAovRjUgMTIgVGYKMCBUcgooTUFSVVRJKSBUagpFVApRClEKUQpxCnEKLTk0MzYuNzY2NjAxNiAtNzUyOS4yMDAxOTUzIDIwMi4wOTk5OTA4IDI1IHJlClcgbgpxCjEgMSAxIHJnCi05NDM2Ljc2NjYwMTYgLTc1MjkuMjAwMTk1MyAyMDIuMDk5OTkwOCAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTQzNi43NjY2MDE2IC03NTA0LjcwMDE5NTMgbQotOTIzNC42NjY2MTA3IC03NTA0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkyMzUuMTY2NjEwNyAtNzUyOS4yMDAxOTUzIG0KLTkyMzUuMTY2NjEwNyAtNzUwNC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTM3My43MTY3OTY5IC03NTEyLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKFNXSUZUIERaSVJFKSBUagpFVApRClEKUQpxCnEKLTkyMzQuNjY2OTkyMiAtNzUyOS4yMDAxOTUzIDI2Mi43MzMzMzc0IDI1IHJlClcgbgpxCjEgMSAxIHJnCi05MjM0LjY2Njk5MjIgLTc1MjkuMjAwMTk1MyAyNjIuNzMzMzM3NCAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTIzNC42NjY5OTIyIC03NTA0LjcwMDE5NTMgbQotODk3MS45MzM2NTQ4IC03NTA0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg5NzIuNDMzNjU0OCAtNzUyOS4yMDAxOTUzIG0KLTg5NzIuNDMzNjU0OCAtNzUwNC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTEzMy4yOTk4MDQ3IC03NTEyLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKExYSSBCUyAtIElWKSBUagpFVApRClEKUQpxCnEKLTg5NzEuOTMzNTkzOCAtNzUyOS4yMDAxOTUzIDE0MS40NDk5OTY5IDI1IHJlClcgbgpxCjEgMSAxIHJnCi04OTcxLjkzMzU5MzggLTc1MjkuMjAwMTk1MyAxNDEuNDQ5OTk2OSAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODk3MS45MzM1OTM4IC03NTA0LjcwMDE5NTMgbQotODgzMC40ODM1OTY4IC03NTA0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MzAuOTgzNTk2OCAtNzUyOS4yMDAxOTUzIG0KLTg4MzAuOTgzNTk2OCAtNzUwNC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODkxNS43MTY3OTY5IC03NTEyLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKDIwMTMpIFRqCkVUClEKUQpRCnEKcQotODgzMC40ODMzOTg0IC03NTI5LjIwMDE5NTMgMjgyLjkzMzM0OTYgMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTg4MzAuNDgzMzk4NCAtNzUyOS4yMDAxOTUzIDI4Mi45MzMzNDk2IDI1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04ODMwLjQ4MzM5ODQgLTc1MDQuNzAwMTk1MyBtCi04NTQ3LjU1MDA0ODggLTc1MDQuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODU0OC4wNTAwNDg4IC03NTI5LjIwMDE5NTMgbQotODU0OC4wNTAwNDg4IC03NTA0LjIwMDE5NTMgbApTClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NzA2LjAxNjYwMTYgLTc1MTIuMjAwMTk1MyBjbQpCVAovRjUgMTIgVGYKMCBUcgooTUgxMikgVGoKRVQKUQpRClEKcQpxCi04NTQ3LjU0OTgwNDcgLTc1MjkuMjAwMTk1MyAxNDEuNDQ5OTk2OSAyNSByZQpXIG4KcQoxIDEgMSByZwotODU0Ny41NDk4MDQ3IC03NTI5LjIwMDE5NTMgMTQxLjQ0OTk5NjkgMjUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg1NDcuNTQ5ODA0NyAtNzUwNC43MDAxOTUzIG0KLTg0MDYuMDk5ODA3NyAtNzUwNC43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NDA2LjU5OTgwNzcgLTc1MjkuMjAwMTk1MyBtCi04NDA2LjU5OTgwNzcgLTc1MDQuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg1MTguMzMzMDA3OCAtNzUxMi4yMDAxOTUzIGNtCkJUCi9GNSAxMiBUZgowIFRyCihFV1JXRTQyMzQyKSBUagpFVApRClEKUQpxCnEKLTg0MDYuMDk5NjA5NCAtNzUyOS4yMDAxOTUzIDMwMy4xNTAwMjQ0IDI1IHJlClcgbgpxCjEgMSAxIHJnCi04NDA2LjA5OTYwOTQgLTc1MjkuMjAwMTk1MyAzMDMuMTUwMDI0NCAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODQwNi4wOTk2MDk0IC03NTA0LjcwMDE5NTMgbQotODEwMi45NDk1ODUgLTc1MDQuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODEwMy40NDk1ODUgLTc1MjkuMjAwMTk1MyBtCi04MTAzLjQ0OTU4NSAtNzUwNC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODMxMS4wMzMyMDMxIC03NTEyLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKEdERkdFVDQ1MzUzMjUzNSkgVGoKRVQKUQpRClEKcQpxCi04MTAyLjk1MDE5NTMgLTc1MjkuMjAwMTk1MyAxNDEuNDQ5OTk2OSAyNSByZQpXIG4KcQoxIDEgMSByZwotODEwMi45NTAxOTUzIC03NTI5LjIwMDE5NTMgMTQxLjQ0OTk5NjkgMjUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgxMDIuOTUwMTk1MyAtNzUwNC43MDAxOTUzIG0KLTc5NjEuNTAwMTk4NCAtNzUwNC43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03OTYyLjAwMDE5ODQgLTc1MjkuMjAwMTk1MyBtCi03OTYyLjAwMDE5ODQgLTc1MDQuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgwNDYuMjMzMzk4NCAtNzUxMi4yMDAxOTUzIGNtCkJUCi9GNSAxMiBUZgowIFRyCigxMTk3KSBUagpFVApRClEKUQpxCnEKLTc5NjEuNSAtNzUyOS4yMDAxOTUzIDE0MS40NDk5OTY5IDI1IHJlClcgbgpxCjEgMSAxIHJnCi03OTYxLjUgLTc1MjkuMjAwMTk1MyAxNDEuNDQ5OTk2OSAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzk2MS41IC03NTA0LjcwMDE5NTMgbQotNzgyMC4wNTAwMDMxIC03NTA0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc4MjAuNTUwMDAzMSAtNzUyOS4yMDAxOTUzIG0KLTc4MjAuNTUwMDAzMSAtNzUwNC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtNzg5NC43ODMyMDMxIC03NTEyLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKDUpIFRqCkVUClEKUQpRClEKUQpRClEKUQpRClEKcQpxCnEKcQpxCnEKcQpxCnEKLTk4NDAuOTUwMTk1MyAtNzQ5NC4yMDAxOTUzIDMzMy4zNDk5NzU2IDU1IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTk4NDAuOTUwMTk1MyAtNzQ5NC4yMDAxOTUzIDMzMy4zNDk5NzU2IDU1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjQ1MDE5NTMgLTc0OTMuNzAwMTk1MyAzMzIuMzQ5OTc1NiA1NCByZQpTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTczOC4yODMyMDMxIC03NDc2LjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKEluc3VyZWQgRGVjbGFyZWQgVmFsdWUpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTcyNi4yODMyMDMxIC03NDYyLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFwoSURWXCkgb2YgdGhlIFZlaGljbGUpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTY4Ny4yODMyMDMxIC03NDQ4LjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFwoaW4guVwpKSBUagpFVApRClEKcQpxCi05NTA3LjU5OTYwOTQgLTc0OTQuMjAwMTk1MyAzMzMuMzMzMzQzNSA1NSByZQpXIG4KcQowLjgyMzUyOTQgMC44Mjc0NTEgMC44MzUyOTQxIHJnCi05NTA3LjU5OTYwOTQgLTc0OTQuMjAwMTk1MyAzMzMuMzMzMzQzNSA1NSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTUwNy41OTk2MDk0IC03NDkzLjcwMDE5NTMgbQotOTE3NC4yNjYyNjU5IC03NDkzLjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk1MDcuNTk5NjA5NCAtNzQzOS43MDAxOTUzIG0KLTkxNzQuMjY2MjY1OSAtNzQzOS43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MTc0Ljc2NjI2NTkgLTc0OTQuMjAwMTk1MyBtCi05MTc0Ljc2NjI2NTkgLTc0MzkuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MzgyLjkzMzU5MzggLTc0NzYuMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooTm9uIC0gRWxlY3RyaWNhbCkgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NDA1LjQzMzU5MzggLTc0NjIuMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooQWNjZXNzb3JpZXMgZml0dGVkIHRvIHRoZSkgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05Mzc4LjkzMzU5MzggLTc0NDguMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooVmVoaWNsZSBcKGluILlcKSkgVGoKRVQKUQpRCnEKcQotOTE3NC4yNjY2MDE2IC03NDk0LjIwMDE5NTMgMzU0LjE4MzM0OTYgNTUgcmUKVyBuCnEKMC44MjM1Mjk0IDAuODI3NDUxIDAuODM1Mjk0MSByZwotOTE3NC4yNjY2MDE2IC03NDk0LjIwMDE5NTMgMzU0LjE4MzM0OTYgNTUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkxNzQuMjY2NjAxNiAtNzQ5My43MDAxOTUzIG0KLTg4MjAuMDgzMjUyIC03NDkzLjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkxNzQuMjY2NjAxNiAtNzQzOS43MDAxOTUzIG0KLTg4MjAuMDgzMjUyIC03NDM5LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MjAuNTgzMjUyIC03NDk0LjIwMDE5NTMgbQotODgyMC41ODMyNTIgLTc0MzkuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MDU2LjY4MzU5MzggLTc0NzYuMjAwMTk1MyBjbQpCVAovRjMgMTIgVGYKMCBUcgooRWxlY3RyaWNhbCAmIEVsZWN0cm9uaWMpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTA2MS42ODM1OTM4IC03NDYyLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKEFjY2Vzc29yaWVzIGZpdHRlZCB0byB0aGUpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTAzNS4xODM1OTM4IC03NDQ4LjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFZlaGljbGUgXChpbiC5XCkpIFRqCkVUClEKUQpxCnEKLTg4MjAuMDgzMDA3OCAtNzQ5NC4yMDAxOTUzIDMzMy4zNDk5NzU2IDU1IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTg4MjAuMDgzMDA3OCAtNzQ5NC4yMDAxOTUzIDMzMy4zNDk5NzU2IDU1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04ODIwLjA4MzAwNzggLTc0OTMuNzAwMTk1MyBtCi04NDg2LjczMzAzMjIgLTc0OTMuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgyMC4wODMwMDc4IC03NDM5LjcwMDE5NTMgbQotODQ4Ni43MzMwMzIyIC03NDM5LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg0ODcuMjMzMDMyMiAtNzQ5NC4yMDAxOTUzIG0KLTg0ODcuMjMzMDMyMiAtNzQzOS4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg2NzEuNDE2OTkyMiAtNzQ3Ni4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihUcmFpbGVyKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg2NjYuOTE2OTkyMiAtNzQ2Mi4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihcKGluILlcKSkgVGoKRVQKUQpRCnEKcQotODQ4Ni43MzMzOTg0IC03NDk0LjIwMDE5NTMgMzMzLjM1MDAwNjEgNTUgcmUKVyBuCnEKMC44MjM1Mjk0IDAuODI3NDUxIDAuODM1Mjk0MSByZwotODQ4Ni43MzMzOTg0IC03NDk0LjIwMDE5NTMgMzMzLjM1MDAwNjEgNTUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg0ODYuNzMzMzk4NCAtNzQ5My43MDAxOTUzIG0KLTgxNTMuMzgzMzkyMyAtNzQ5My43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NDg2LjczMzM5ODQgLTc0MzkuNzAwMTk1MyBtCi04MTUzLjM4MzM5MjMgLTc0MzkuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODE1My44ODMzOTIzIC03NDk0LjIwMDE5NTMgbQotODE1My44ODMzOTIzIC03NDM5LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODM1OS4wNjY0MDYzIC03NDc2LjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKENORyAvIExQRyBLaXQpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODMzMy41NjY0MDYzIC03NDYyLjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFwoaW4guVwpKSBUagpFVApRClEKcQpxCi04MTUzLjM4MzMwMDggLTc0OTQuMjAwMTk1MyAzMzMuMzMzMzEzIDU1IHJlClcgbgpxCjAuODIzNTI5NCAwLjgyNzQ1MSAwLjgzNTI5NDEgcmcKLTgxNTMuMzgzMzAwOCAtNzQ5NC4yMDAxOTUzIDMzMy4zMzMzMTMgNTUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgxNTMuMzgzMzAwOCAtNzQ5My43MDAxOTUzIG0KLTc4MjAuMDQ5OTg3OCAtNzQ5My43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MTUzLjM4MzMwMDggLTc0MzkuNzAwMTk1MyBtCi03ODIwLjA0OTk4NzggLTc0MzkuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzgyMC41NDk5ODc4IC03NDk0LjIwMDE5NTMgbQotNzgyMC41NDk5ODc4IC03NDM5LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODA1NS4yMTY3OTY5IC03NDc2LjIwMDE5NTMgY20KQlQKL0YzIDEyIFRmCjAgVHIKKFRvdGFsIFZhbHVlIG9mIHRoZSBWZWhpY2xlKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgwMDAuMjE2Nzk2OSAtNzQ2Mi4yMDAxOTUzIGNtCkJUCi9GMyAxMiBUZgowIFRyCihcKGluILlcKSkgVGoKRVQKUQpRClEKcQpxCnEKLTk4NDAuOTUwMTk1MyAtNzQzOS4yMDAxOTUzIDMzMy4zNDk5NzU2IDI1IHJlClcgbgpxCjEgMSAxIHJnCi05ODQwLjk1MDE5NTMgLTc0MzkuMjAwMTk1MyAzMzMuMzQ5OTc1NiAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC45NTAxOTUzIC03NDE0LjcwMDE5NTMgbQotOTUwNy42MDAyMTk3IC03NDE0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzQzOS4yMDAxOTUzIG0KLTk4NDAuNDUwMTk1MyAtNzQxNC4yMDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05NTA4LjEwMDIxOTcgLTc0MzkuMjAwMTk1MyBtCi05NTA4LjEwMDIxOTcgLTc0MTQuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk2OTUuMjgzMjAzMSAtNzQyMi4yMDAxOTUzIGNtCkJUCi9GNSAxMiBUZgowIFRyCigyNjg5NjEpIFRqCkVUClEKUQpRCnEKcQotOTUwNy41OTk2MDk0IC03NDM5LjIwMDE5NTMgMzMzLjMzMzM0MzUgMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTk1MDcuNTk5NjA5NCAtNzQzOS4yMDAxOTUzIDMzMy4zMzMzNDM1IDI1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05NTA3LjU5OTYwOTQgLTc0MTQuNzAwMTk1MyBtCi05MTc0LjI2NjI2NTkgLTc0MTQuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTE3NC43NjYyNjU5IC03NDM5LjIwMDE5NTMgbQotOTE3NC43NjYyNjU5IC03NDE0LjIwMDE5NTMgbApTClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MzQ0LjkzMzU5MzggLTc0MjIuMjAwMTk1MyBjbQpCVAovRjUgMTIgVGYKMCBUcgooMCkgVGoKRVQKUQpRClEKcQpxCi05MTc0LjI2NjYwMTYgLTc0MzkuMjAwMTk1MyAzNTQuMTgzMzQ5NiAyNSByZQpXIG4KcQoxIDEgMSByZwotOTE3NC4yNjY2MDE2IC03NDM5LjIwMDE5NTMgMzU0LjE4MzM0OTYgMjUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkxNzQuMjY2NjAxNiAtNzQxNC43MDAxOTUzIG0KLTg4MjAuMDgzMjUyIC03NDE0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MjAuNTgzMjUyIC03NDM5LjIwMDE5NTMgbQotODgyMC41ODMyNTIgLTc0MTQuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkwMDEuMTgzNTkzOCAtNzQyMi4yMDAxOTUzIGNtCkJUCi9GNSAxMiBUZgowIFRyCigwKSBUagpFVApRClEKUQpxCnEKLTg4MjAuMDgzMDA3OCAtNzQzOS4yMDAxOTUzIDMzMy4zNDk5NzU2IDI1IHJlClcgbgpxCjEgMSAxIHJnCi04ODIwLjA4MzAwNzggLTc0MzkuMjAwMTk1MyAzMzMuMzQ5OTc1NiAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgyMC4wODMwMDc4IC03NDE0LjcwMDE5NTMgbQotODQ4Ni43MzMwMzIyIC03NDE0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg0ODcuMjMzMDMyMiAtNzQzOS4yMDAxOTUzIG0KLTg0ODcuMjMzMDMyMiAtNzQxNC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODY1Ny40MTY5OTIyIC03NDIyLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKDApIFRqCkVUClEKUQpRCnEKcQotODQ4Ni43MzMzOTg0IC03NDM5LjIwMDE5NTMgMzMzLjM1MDAwNjEgMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTg0ODYuNzMzMzk4NCAtNzQzOS4yMDAxOTUzIDMzMy4zNTAwMDYxIDI1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NDg2LjczMzM5ODQgLTc0MTQuNzAwMTk1MyBtCi04MTUzLjM4MzM5MjMgLTc0MTQuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODE1My44ODMzOTIzIC03NDM5LjIwMDE5NTMgbQotODE1My44ODMzOTIzIC03NDE0LjIwMDE5NTMgbApTClEKUQpxCnEKLTgxNTMuMzgzMzAwOCAtNzQzOS4yMDAxOTUzIDMzMy4zMzMzMTMgMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTgxNTMuMzgzMzAwOCAtNzQzOS4yMDAxOTUzIDMzMy4zMzMzMTMgMjUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgxNTMuMzgzMzAwOCAtNzQxNC43MDAxOTUzIG0KLTc4MjAuMDQ5OTg3OCAtNzQxNC43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03ODIwLjU0OTk4NzggLTc0MzkuMjAwMTk1MyBtCi03ODIwLjU0OTk4NzggLTc0MTQuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgwMDguMjE2Nzk2OSAtNzQyMi4yMDAxOTUzIGNtCkJUCi9GNSAxMiBUZgowIFRyCigyNjg5NjEpIFRqCkVUClEKUQpRClEKUQpRClEKUQpRClEKUQpRCnEKcQpxCi05ODgzLjA0OTgwNDcgLTc0MDMuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKVyBuCnEKMCAwLjIgMC40IHJnCi05ODgzLjA0OTgwNDcgLTc0MDMuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKZgpRClEKcQoxIDEgMSByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC03MzgzLjcwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKFBSRU1JVU0gQ09NUFVUQVRJT04gVEFCTEUgXChJbiC5XCkpIFRqCkVUClEKUQpRCnEKcQpxCnEKcQpxCnEKcQpxCnEKcQotOTg0MC45NTAxOTUzIC03MzYyLjIwMDE5NTMgMTAxMC40NTAwMTIyIDE3IHJlClcgbgpxCjEgMSAxIHJnCi05ODQwLjk1MDE5NTMgLTczNjIuMjAwMTk1MyAxMDEwLjQ1MDAxMjIgMTcgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzM2MS43MDAxOTUzIDEwMDkuNDUwMDEyMiAxNiByZQpTClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MzYzLjIzMzM5ODQgLTczNDguMjAwMTk1MyBjbQpCVAovRjUgMTMgVGYKMCBUcgooU2VjdGlvbikgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MzEyLjIzMzM5ODQgLTczNDguMjAwMTk1MyBjbQpCVAovRjUgMTMgVGYKMCBUcgooSSkgVGoKRVQKUQpRClEKcQpxCi04ODMwLjUgLTczNjIuMjAwMTk1MyAxMDEwLjQ1MDAxMjIgMTcgcmUKVyBuCnEKMSAxIDEgcmcKLTg4MzAuNSAtNzM2Mi4yMDAxOTUzIDEwMTAuNDUwMDEyMiAxNyByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMC41IC03MzYxLjcwMDE5NTMgbQotNzgyMC4wNDk5ODc4IC03MzYxLjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MzAuNSAtNzM0NS43MDAxOTUzIG0KLTc4MjAuMDQ5OTg3OCAtNzM0NS43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03ODIwLjU0OTk4NzggLTczNjIuMjAwMTk1MyBtCi03ODIwLjU0OTk4NzggLTczNDUuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgzNTUuMjgzMjAzMSAtNzM0OC4yMDAxOTUzIGNtCkJUCi9GNSAxMyBUZgowIFRyCihTZWN0aW9uIEkpIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODMwMC4yODMyMDMxIC03MzQ4LjIwMDE5NTMgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKEkpIFRqCkVUClEKUQpRClEKcQpxCnEKcQpxCnEKcQotOTg0MC45NTAxOTUzIC03MzQ0LjIwMDE5NTMgNzg4LjE2NjYyNiAxNiByZQpXIG4KcQoxIDEgMSByZwotOTg0MC45NTAxOTUzIC03MzQ0LjIwMDE5NTMgNzg4LjE2NjYyNiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC45NTAxOTUzIC03MzI4LjcwMDE5NTMgbQotOTA1Mi43ODM1NjkzIC03MzI4LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzM0NC4yMDAxOTUzIG0KLTk4NDAuNDUwMTk1MyAtNzMyOC4yMDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MDUzLjI4MzU2OTMgLTczNDQuMjAwMTk1MyBtCi05MDUzLjI4MzU2OTMgLTczMjguMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4MTkuNzUgLTczMzEuMjAwMTk1MyBjbQpCVAovRjUgMTMgVGYKMCBUcgooT3duIERhbWFnZSkgVGoKRVQKUQpRClEKcQpxCi05MDUyLjc4MzIwMzEgLTczNDQuMjAwMTk1MyAyMjIuMjgzMzQwNSAxNiByZQpXIG4KcQoxIDEgMSByZwotOTA1Mi43ODMyMDMxIC03MzQ0LjIwMDE5NTMgMjIyLjI4MzM0MDUgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkwNTIuNzgzMjAzMSAtNzMyOC43MDAxOTUzIG0KLTg4MzAuNDk5ODYyNyAtNzMyOC43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04ODMwLjk5OTg2MjcgLTczNDQuMjAwMTk1MyBtCi04ODMwLjk5OTg2MjcgLTczMjguMjAwMTk1MyBsClMKUQpRClEKcQpxCnEKLTk4NDAuOTUwMTk1MyAtNzMyOC4yMDAxOTUzIDc4OC4xNjY2MjYgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTk4NDAuOTUwMTk1MyAtNzMyOC4yMDAxOTUzIDc4OC4xNjY2MjYgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuOTUwMTk1MyAtNzMxMi43MDAxOTUzIG0KLTkwNTIuNzgzNTY5MyAtNzMxMi43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjQ1MDE5NTMgLTczMjguMjAwMTk1MyBtCi05ODQwLjQ1MDE5NTMgLTczMTIuMjAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTA1My4yODM1NjkzIC03MzI4LjIwMDE5NTMgbQotOTA1My4yODM1NjkzIC03MzEyLjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTgxOS43NSAtNzMxNS4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihCYXNpYyBPd24gRGFtYWdlKSBUagpFVApRClEKcQpxCi05MDUyLjc4MzIwMzEgLTczMjguMjAwMTk1MyAyMjIuMjgzMzQwNSAxNiByZQpXIG4KcQoxIDEgMSByZwotOTA1Mi43ODMyMDMxIC03MzI4LjIwMDE5NTMgMjIyLjI4MzM0MDUgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkwNTIuNzgzMjAzMSAtNzMxMi43MDAxOTUzIG0KLTg4MzAuNDk5ODYyNyAtNzMxMi43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04ODMwLjk5OTg2MjcgLTczMjguMjAwMTk1MyBtCi04ODMwLjk5OTg2MjcgLTczMTIuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04OTI4LjAxNjYwMTYgLTczMTUuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooMzkzOS43NCkgVGoKRVQKUQpRClEKcQpxCnEKLTk4NDAuOTUwMTk1MyAtNzMxMi4yMDAxOTUzIDc4OC4xNjY2MjYgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTk4NDAuOTUwMTk1MyAtNzMxMi4yMDAxOTUzIDc4OC4xNjY2MjYgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuOTUwMTk1MyAtNzI5Ni43MDAxOTUzIG0KLTkwNTIuNzgzNTY5MyAtNzI5Ni43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjQ1MDE5NTMgLTczMTIuMjAwMTk1MyBtCi05ODQwLjQ1MDE5NTMgLTcyOTYuMjAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTA1My4yODM1NjkzIC03MzEyLjIwMDE5NTMgbQotOTA1My4yODM1NjkzIC03Mjk2LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTgxOS43NSAtNzI5OS4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihMZXNzOikgVGoKRVQKUQpRCnEKcQotOTA1Mi43ODMyMDMxIC03MzEyLjIwMDE5NTMgMjIyLjI4MzM0MDUgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTkwNTIuNzgzMjAzMSAtNzMxMi4yMDAxOTUzIDIyMi4yODMzNDA1IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MDUyLjc4MzIwMzEgLTcyOTYuNzAwMTk1MyBtCi04ODMwLjQ5OTg2MjcgLTcyOTYuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMC45OTk4NjI3IC03MzEyLjIwMDE5NTMgbQotODgzMC45OTk4NjI3IC03Mjk2LjIwMDE5NTMgbApTClEKUQpRCnEKcQpxCi05ODQwLjk1MDE5NTMgLTcyOTYuMjAwMTk1MyA3ODguMTY2NjI2IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05ODQwLjk1MDE5NTMgLTcyOTYuMjAwMTk1MyA3ODguMTY2NjI2IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjk1MDE5NTMgLTcyODAuNzAwMTk1MyBtCi05MDUyLjc4MzU2OTMgLTcyODAuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC40NTAxOTUzIC03Mjk2LjIwMDE5NTMgbQotOTg0MC40NTAxOTUzIC03MjgwLjIwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkwNTMuMjgzNTY5MyAtNzI5Ni4yMDAxOTUzIG0KLTkwNTMuMjgzNTY5MyAtNzI4MC4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4MTkuNzUgLTcyODMuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooQm9udXMgUGVyY2VudCBCb251cyBQZXJjZW50ICIzNSIpIFRqCkVUClEKUQpxCnEKLTkwNTIuNzgzMjAzMSAtNzI5Ni4yMDAxOTUzIDIyMi4yODMzNDA1IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05MDUyLjc4MzIwMzEgLTcyOTYuMjAwMTk1MyAyMjIuMjgzMzQwNSAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTA1Mi43ODMyMDMxIC03MjgwLjcwMDE5NTMgbQotODgzMC40OTk4NjI3IC03MjgwLjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MzAuOTk5ODYyNyAtNzI5Ni4yMDAxOTUzIG0KLTg4MzAuOTk5ODYyNyAtNzI4MC4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5MjguMDE2NjAxNiAtNzI4My4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCigxMDM0LjE4KSBUagpFVApRClEKUQpxCnEKcQotOTg0MC45NTAxOTUzIC03MjgwLjIwMDE5NTMgNzg4LjE2NjYyNiAxNiByZQpXIG4KcQoxIDEgMSByZwotOTg0MC45NTAxOTUzIC03MjgwLjIwMDE5NTMgNzg4LjE2NjYyNiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC45NTAxOTUzIC03MjY0LjcwMDE5NTMgbQotOTA1Mi43ODM1NjkzIC03MjY0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzI4MC4yMDAxOTUzIG0KLTk4NDAuNDUwMTk1MyAtNzI2NC4yMDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MDUzLjI4MzU2OTMgLTcyODAuMjAwMTk1MyBtCi05MDUzLjI4MzU2OTMgLTcyNjQuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODE5Ljc1IC03MjY3LjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKEJvbnVzIFBlcmNlbnQgVm9sdW50YXJ5IERlZHVjdGlibGUpIFRqCkVUClEKUQpxCnEKLTkwNTIuNzgzMjAzMSAtNzI4MC4yMDAxOTUzIDIyMi4yODMzNDA1IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05MDUyLjc4MzIwMzEgLTcyODAuMjAwMTk1MyAyMjIuMjgzMzQwNSAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTA1Mi43ODMyMDMxIC03MjY0LjcwMDE5NTMgbQotODgzMC40OTk4NjI3IC03MjY0LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MzAuOTk5ODYyNyAtNzI4MC4yMDAxOTUzIG0KLTg4MzAuOTk5ODYyNyAtNzI2NC4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5MjEuMDE2NjAxNiAtNzI2Ny4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCig5ODQuOTQpIFRqCkVUClEKUQpRCnEKcQpxCi05ODQwLjk1MDE5NTMgLTcyNjQuMjAwMTk1MyA3ODguMTY2NjI2IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05ODQwLjk1MDE5NTMgLTcyNjQuMjAwMTk1MyA3ODguMTY2NjI2IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjk1MDE5NTMgLTcyNDguNzAwMTk1MyBtCi05MDUyLjc4MzU2OTMgLTcyNDguNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC40NTAxOTUzIC03MjY0LjIwMDE5NTMgbQotOTg0MC40NTAxOTUzIC03MjQ4LjIwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkwNTMuMjgzNTY5MyAtNzI2NC4yMDAxOTUzIG0KLTkwNTMuMjgzNTY5MyAtNzI0OC4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTgxOS43NSAtNzI1MS4yMDAxOTUzIGNtCkJUCi9GNSAxMyBUZgowIFRyCihUb3RhbCBPd24gRGFtYWdlIFByZW1pdW0gXChBXCkpIFRqCkVUClEKUQpRCnEKcQotOTA1Mi43ODMyMDMxIC03MjY0LjIwMDE5NTMgMjIyLjI4MzM0MDUgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTkwNTIuNzgzMjAzMSAtNzI2NC4yMDAxOTUzIDIyMi4yODMzNDA1IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MDUyLjc4MzIwMzEgLTcyNDguNzAwMTk1MyBtCi04ODMwLjQ5OTg2MjcgLTcyNDguNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMC45OTk4NjI3IC03MjY0LjIwMDE5NTMgbQotODgzMC45OTk4NjI3IC03MjQ4LjIwMDE5NTMgbApTClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MDA1LjAxNjYwMTYgLTcyNTEuMjAwMTk1MyBjbQpCVAovRjUgMTMgVGYKMCBUcgooMTkyMC42MTk5OTk5OTk5OTk3KSBUagpFVApRClEKUQpRClEKUQpRCnEKcQpxCnEKcQpxCi04ODMwLjUgLTczNDUuMjAwMTk1MyA3ODguMTY2Njg3IDE2IHJlClcgbgpxCjEgMSAxIHJnCi04ODMwLjUgLTczNDUuMjAwMTk1MyA3ODguMTY2Njg3IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04ODMwLjUgLTczMjkuNzAwMTk1MyBtCi04MDQyLjMzMzMxMyAtNzMyOS43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MDQyLjgzMzMxMyAtNzM0NS4yMDAxOTUzIG0KLTgwNDIuODMzMzEzIC03MzI5LjIwMDE5NTMgbApTClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04ODEwLjI5OTgwNDcgLTczMzIuMjAwMTk1MyBjbQpCVAovRjUgMTMgVGYKMCBUcgooTGlhYmlsaXR5KSBUagpFVApRClEKUQpxCnEKLTgwNDIuMzMzNDk2MSAtNzM0NS4yMDAxOTUzIDIyMi4yODMzMjUyIDE2IHJlClcgbgpxCjEgMSAxIHJnCi04MDQyLjMzMzQ5NjEgLTczNDUuMjAwMTk1MyAyMjIuMjgzMzI1MiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODA0Mi4zMzM0OTYxIC03MzI5LjcwMDE5NTMgbQotNzgyMC4wNTAxNzA5IC03MzI5LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc4MjAuNTUwMTcwOSAtNzM0NS4yMDAxOTUzIG0KLTc4MjAuNTUwMTcwOSAtNzMyOS4yMDAxOTUzIGwKUwpRClEKUQpxCnEKcQotODgzMC41IC03MzI5LjIwMDE5NTMgNzg4LjE2NjY4NyAxNiByZQpXIG4KcQoxIDEgMSByZwotODgzMC41IC03MzI5LjIwMDE5NTMgNzg4LjE2NjY4NyAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMC41IC03MzEzLjcwMDE5NTMgbQotODA0Mi4zMzMzMTMgLTczMTMuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODA0Mi44MzMzMTMgLTczMjkuMjAwMTk1MyBtCi04MDQyLjgzMzMxMyAtNzMxMy4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg4MTAuMjk5ODA0NyAtNzMxNi4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihCYXNpYyBUUCBJbmNsdWRpbmcgVFBQRCBQcmVtaXVtKSBUagpFVApRClEKcQpxCi04MDQyLjMzMzQ5NjEgLTczMjkuMjAwMTk1MyAyMjIuMjgzMzI1MiAxNiByZQpXIG4KcQoxIDEgMSByZwotODA0Mi4zMzM0OTYxIC03MzI5LjIwMDE5NTMgMjIyLjI4MzMyNTIgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgwNDIuMzMzNDk2MSAtNzMxMy43MDAxOTUzIG0KLTc4MjAuMDUwMTcwOSAtNzMxMy43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03ODIwLjU1MDE3MDkgLTczMjkuMjAwMTk1MyBtCi03ODIwLjU1MDE3MDkgLTczMTMuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC03ODk5LjU2Njg5NDUgLTczMTYuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooMjIzNykgVGoKRVQKUQpRClEKcQpxCnEKLTg4MzAuNSAtNzMxMy4yMDAxOTUzIDc4OC4xNjY2ODcgMTggcmUKVyBuCnEKMSAxIDEgcmcKLTg4MzAuNSAtNzMxMy4yMDAxOTUzIDc4OC4xNjY2ODcgMTggcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MzAuNSAtNzI5NS43MDAxOTUzIG0KLTgwNDIuMzMzMzEzIC03Mjk1LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgwNDIuODMzMzEzIC03MzEzLjIwMDE5NTMgbQotODA0Mi44MzMzMTMgLTcyOTUuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04ODEwLjI5OTgwNDcgLTczMDAuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooUEEgQ292ZXIgZm9yIE93bmVyIERyaXZlciBvZiC5IDIwMDAwMCkgVGoKRVQKUQpRCnEKcQotODA0Mi4zMzM0OTYxIC03MzEzLjIwMDE5NTMgMjIyLjI4MzMyNTIgMTggcmUKVyBuCnEKMSAxIDEgcmcKLTgwNDIuMzMzNDk2MSAtNzMxMy4yMDAxOTUzIDIyMi4yODMzMjUyIDE4IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MDQyLjMzMzQ5NjEgLTcyOTUuNzAwMTk1MyBtCi03ODIwLjA1MDE3MDkgLTcyOTUuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzgyMC41NTAxNzA5IC03MzEzLjIwMDE5NTMgbQotNzgyMC41NTAxNzA5IC03Mjk1LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtNzg5Mi41NjY4OTQ1IC03Mjk5LjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKDEwMCkgVGoKRVQKUQpRClEKcQpxCnEKLTg4MzAuNSAtNzI5NS4yMDAxOTUzIDc4OC4xNjY2ODcgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTg4MzAuNSAtNzI5NS4yMDAxOTUzIDc4OC4xNjY2ODcgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg4MzAuNSAtNzI3OS43MDAxOTUzIG0KLTgwNDIuMzMzMzEzIC03Mjc5LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgwNDIuODMzMzEzIC03Mjk1LjIwMDE5NTMgbQotODA0Mi44MzMzMTMgLTcyNzkuMjAwMTk1MyBsClMKUQpRCnEKcQotODA0Mi4zMzM0OTYxIC03Mjk1LjIwMDE5NTMgMjIyLjI4MzMyNTIgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTgwNDIuMzMzNDk2MSAtNzI5NS4yMDAxOTUzIDIyMi4yODMzMjUyIDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MDQyLjMzMzQ5NjEgLTcyNzkuNzAwMTk1MyBtCi03ODIwLjA1MDE3MDkgLTcyNzkuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzgyMC41NTAxNzA5IC03Mjk1LjIwMDE5NTMgbQotNzgyMC41NTAxNzA5IC03Mjc5LjIwMDE5NTMgbApTClEKUQpRCnEKcQpxCi04ODMwLjUgLTcyNzkuMjAwMTk1MyA3ODguMTY2Njg3IDE2IHJlClcgbgpxCjEgMSAxIHJnCi04ODMwLjUgLTcyNzkuMjAwMTk1MyA3ODguMTY2Njg3IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04ODMwLjUgLTcyNjMuNzAwMTk1MyBtCi04MDQyLjMzMzMxMyAtNzI2My43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MDQyLjgzMzMxMyAtNzI3OS4yMDAxOTUzIG0KLTgwNDIuODMzMzEzIC03MjYzLjIwMDE5NTMgbApTClEKUQpxCnEKLTgwNDIuMzMzNDk2MSAtNzI3OS4yMDAxOTUzIDIyMi4yODMzMjUyIDE2IHJlClcgbgpxCjEgMSAxIHJnCi04MDQyLjMzMzQ5NjEgLTcyNzkuMjAwMTk1MyAyMjIuMjgzMzI1MiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODA0Mi4zMzM0OTYxIC03MjYzLjcwMDE5NTMgbQotNzgyMC4wNTAxNzA5IC03MjYzLjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc4MjAuNTUwMTcwOSAtNzI3OS4yMDAxOTUzIG0KLTc4MjAuNTUwMTcwOSAtNzI2My4yMDAxOTUzIGwKUwpRClEKUQpxCnEKcQotODgzMC41IC03MjYzLjIwMDE5NTMgNzg4LjE2NjY4NyAxNiByZQpXIG4KcQoxIDEgMSByZwotODgzMC41IC03MjYzLjIwMDE5NTMgNzg4LjE2NjY4NyAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMC41IC03MjQ3LjcwMDE5NTMgbQotODA0Mi4zMzMzMTMgLTcyNDcuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODA0Mi44MzMzMTMgLTcyNjMuMjAwMTk1MyBtCi04MDQyLjgzMzMxMyAtNzI0Ny4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODgxMC4yOTk4MDQ3IC03MjUwLjIwMDE5NTMgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKFRvdGFsIExpYWJpbGl0eSBQcmVtaXVtIFwoQlwpKSBUagpFVApRClEKUQpxCnEKLTgwNDIuMzMzNDk2MSAtNzI2My4yMDAxOTUzIDIyMi4yODMzMjUyIDE2IHJlClcgbgpxCjEgMSAxIHJnCi04MDQyLjMzMzQ5NjEgLTcyNjMuMjAwMTk1MyAyMjIuMjgzMzI1MiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODA0Mi4zMzM0OTYxIC03MjQ3LjcwMDE5NTMgbQotNzgyMC4wNTAxNzA5IC03MjQ3LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc4MjAuNTUwMTcwOSAtNzI2My4yMDAxOTUzIG0KLTc4MjAuNTUwMTcwOSAtNzI0Ny4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtNzg5OS41NjY4OTQ1IC03MjUwLjIwMDE5NTMgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKDIzMzcpIFRqCkVUClEKUQpRClEKUQpRClEKUQpRClEKUQpRCnEKcQpxCnEKcQpxCnEKLTk4NDAuOTUwMTk1MyAtNzI0Ny4yMDAxOTUzIDE3OTguNjE2Njk5MiAxNiByZQpXIG4KcQoxIDEgMSByZwotOTg0MC45NTAxOTUzIC03MjQ3LjIwMDE5NTMgMTc5OC42MTY2OTkyIDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjk1MDE5NTMgLTcyMzEuNzAwMTk1MyBtCi04MDQyLjMzMzQ5NjEgLTcyMzEuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC40NTAxOTUzIC03MjQ3LjIwMDE5NTMgbQotOTg0MC40NTAxOTUzIC03MjMxLjIwMDE5NTMgbApTClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODE5Ljc1IC03MjM0LjIwMDE5NTMgY20KQlQKL0Y1IDEzIFRmCjAgVHIKKFRvdGFsIFBhY2thZ2UgXChBK0JcKSkgVGoKRVQKUQpRClEKcQpxCi04MDQyLjMzMzQ5NjEgLTcyNDcuMjAwMTk1MyAyMjIuMjgzMzI1MiAxNiByZQpXIG4KcQoxIDEgMSByZwotODA0Mi4zMzM0OTYxIC03MjQ3LjIwMDE5NTMgMjIyLjI4MzMyNTIgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgwNDIuMzMzNDk2MSAtNzIzMS43MDAxOTUzIG0KLTc4MjAuMDUwMTcwOSAtNzIzMS43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03ODIwLjU1MDE3MDkgLTcyNDcuMjAwMTk1MyBtCi03ODIwLjU1MDE3MDkgLTcyMzEuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTc5MTcuNTY2ODk0NSAtNzIzNC4yMDAxOTUzIGNtCkJUCi9GNSAxMyBUZgowIFRyCig0MjU3LjYyKSBUagpFVApRClEKUQpRCnEKcQpxCnEKcQpxCnEKLTk4NDAuOTUwMTk1MyAtNzIzMS4yMDAxOTUzIDE2NDMuMTY2NjI2IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05ODQwLjk1MDE5NTMgLTcyMzEuMjAwMTk1MyAxNjQzLjE2NjYyNiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC45NTAxOTUzIC03MjE1LjcwMDE5NTMgbQotODE5Ny43ODM1NjkzIC03MjE1LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzIzMS4yMDAxOTUzIG0KLTk4NDAuNDUwMTk1MyAtNzIxNS4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4MTkuNzUgLTcyMTguMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooU2VydmljZSBUYXggQCAxNCUpIFRqCkVUClEKUQpxCnEKLTgxOTcuNzgzMjAzMSAtNzIzMS4yMDAxOTUzIDM3Ny43MzMzMzc0IDE2IHJlClcgbgpxCjEgMSAxIHJnCi04MTk3Ljc4MzIwMzEgLTcyMzEuMjAwMTk1MyAzNzcuNzMzMzM3NCAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODE5Ny43ODMyMDMxIC03MjE1LjcwMDE5NTMgbQotNzgyMC4wNDk4NjU3IC03MjE1LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc4MjAuNTQ5ODY1NyAtNzIzMS4yMDAxOTUzIG0KLTc4MjAuNTQ5ODY1NyAtNzIxNS4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTc5MTAuNTY2ODk0NSAtNzIxOC4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCig2ODUuNDQpIFRqCkVUClEKUQpRCnEKcQpxCi05ODQwLjk1MDE5NTMgLTcyMTUuMjAwMTk1MyAxNjQzLjE2NjYyNiAxNiByZQpXIG4KcQoxIDEgMSByZwotOTg0MC45NTAxOTUzIC03MjE1LjIwMDE5NTMgMTY0My4xNjY2MjYgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuOTUwMTk1MyAtNzE5OS43MDAxOTUzIG0KLTgxOTcuNzgzNTY5MyAtNzE5OS43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODQwLjQ1MDE5NTMgLTcyMTUuMjAwMTk1MyBtCi05ODQwLjQ1MDE5NTMgLTcxOTkuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODE5Ljc1IC03MjAyLjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKFN3YWNoaCBCaGFyYXQgQ2VzcyBAIDAuNSUpIFRqCkVUClEKUQpxCnEKLTgxOTcuNzgzMjAzMSAtNzIxNS4yMDAxOTUzIDM3Ny43MzMzMzc0IDE2IHJlClcgbgpxCjEgMSAxIHJnCi04MTk3Ljc4MzIwMzEgLTcyMTUuMjAwMTk1MyAzNzcuNzMzMzM3NCAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODE5Ny43ODMyMDMxIC03MTk5LjcwMDE5NTMgbQotNzgyMC4wNDk4NjU3IC03MTk5LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc4MjAuNTQ5ODY1NyAtNzIxNS4yMDAxOTUzIG0KLTc4MjAuNTQ5ODY1NyAtNzE5OS4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTc5MDMuNTY2ODk0NSAtNzIwMi4yMDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCigyNC40OCkgVGoKRVQKUQpRClEKcQpxCnEKLTk4NDAuOTUwMTk1MyAtNzE5OS4yMDAxOTUzIDE2NDMuMTY2NjI2IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05ODQwLjk1MDE5NTMgLTcxOTkuMjAwMTk1MyAxNjQzLjE2NjYyNiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC45NTAxOTUzIC03MTgzLjcwMDE5NTMgbQotODE5Ny43ODM1NjkzIC03MTgzLjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzE5OS4yMDAxOTUzIG0KLTk4NDAuNDUwMTk1MyAtNzE4My4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4MTkuNzUgLTcxODYuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooS3Jpc2hpIEthbHlhbiBDZXNzIEAgMC41JSkgVGoKRVQKUQpRCnEKcQotODE5Ny43ODMyMDMxIC03MTk5LjIwMDE5NTMgMzc3LjczMzMzNzQgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTgxOTcuNzgzMjAzMSAtNzE5OS4yMDAxOTUzIDM3Ny43MzMzMzc0IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MTk3Ljc4MzIwMzEgLTcxODMuNzAwMTk1MyBtCi03ODIwLjA0OTg2NTcgLTcxODMuNzAwMTk1MyBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzgyMC41NDk4NjU3IC03MTk5LjIwMDE5NTMgbQotNzgyMC41NDk4NjU3IC03MTgzLjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtNzkwMy41NjY4OTQ1IC03MTg2LjIwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKDI0LjQ4KSBUagpFVApRClEKUQpRClEKUQpRCnEKcQpxCi05ODQwLjk1MDE5NTMgLTcxODMuMjAwMTk1MyAxNzk4LjYxNjY5OTIgMTggcmUKVyBuCnEKMSAxIDEgcmcKLTk4NDAuOTUwMTk1MyAtNzE4My4yMDAxOTUzIDE3OTguNjE2Njk5MiAxOCByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg0MC45NTAxOTUzIC03MTY1LjcwMDE5NTMgbQotODA0Mi4zMzM0OTYxIC03MTY1LjcwMDE5NTMgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NDAuNDUwMTk1MyAtNzE4My4yMDAxOTUzIG0KLTk4NDAuNDUwMTk1MyAtNzE2NS4yMDAxOTUzIGwKUwpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTgxOS43NSAtNzE3MC4yMDAxOTUzIGNtCkJUCi9GNSAxMyBUZgowIFRyCihUb3RhbCBQcmVtaXVtIFwoaW4guSBcKSkgVGoKRVQKUQpRClEKcQpxCi04MDQyLjMzMzQ5NjEgLTcxODMuMjAwMTk1MyAyMjIuMjgzMzI1MiAxOCByZQpXIG4KcQoxIDEgMSByZwotODA0Mi4zMzM0OTYxIC03MTgzLjIwMDE5NTMgMjIyLjI4MzMyNTIgMTggcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgwNDIuMzMzNDk2MSAtNzE2NS43MDAxOTUzIG0KLTc4MjAuMDUwMTcwOSAtNzE2NS43MDAxOTUzIGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03ODIwLjU1MDE3MDkgLTcxODMuMjAwMTk1MyBtCi03ODIwLjU1MDE3MDkgLTcxNjUuMjAwMTk1MyBsClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTc4OTkuNTY2ODk0NSAtNzE3MC4yMDAxOTUzIGNtCkJUCi9GNSAxMyBUZgowIFRyCig0ODk2KSBUagpFVApRClEKUQpRClEKUQpRClEKUQpRClEKUQpxCnEKcQpxCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQwLjk1MDE5NTMgLTcxMzQuNzAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooR2VvZ3JhcGhpY2FsIEFyZWEpIFRqCkVUClEKUQpxCnEKcQowLjUwMTk2MDggMC41MDE5NjA4IDAuNTAxOTYwOCBSRwoxIHcKLTk0MTYuMDY2NDA2MyAtNzE0OC43MDAxOTUzIDIzOSAxNyByZQpTClEKcQpxCnEKcQotOTQxNS41NjY0MDYzIC03MTQ4LjIwMDE5NTMgMjM4IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05NDE1LjU2NjQwNjMgLTcxNDguMjAwMTk1MyAyMzggMTYgcmUKZgpRClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTQxNS41NjY0MDYzIC03MTM0LjcwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKElORElBKSBUagpFVApRClEKUQpRClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg4MzQuMjUgLTcxMzUuNzAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooQWRkaXRpb25hbCBFeGNlc3MguSkgVGoKRVQKUQpRCnEKcQpxCjAuNTAxOTYwOCAwLjUwMTk2MDggMC41MDE5NjA4IFJHCjEgdwotODcwOC43NSAtNzE0OC43MDAxOTUzIDg5IDE3IHJlClMKUQpxCnEKcQpxCi04NzA4LjI1IC03MTQ4LjIwMDE5NTMgODggMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTg3MDguMjUgLTcxNDguMjAwMTk1MyA4OCAxNiByZQpmClEKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NjI3LjI1IC03MTM0LjcwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKDApIFRqCkVUClEKUQpRClEKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODA4Mi4wODM0OTYxIC03MTM1LjcwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKENvbXB1bHNvcnkgRGVkdWN0aWJsZXMguSkgVGoKRVQKUQpRCnEKcQpxCjAuNTAxOTYwOCAwLjUwMTk2MDggMC41MDE5NjA4IFJHCjEgdwotNzkyMC41ODM0OTYxIC03MTQ4LjcwMDE5NTMgODkgMTcgcmUKUwpRCnEKcQpxCnEKLTc5MjAuMDgzNDk2MSAtNzE0OC4yMDAxOTUzIDg4IDE2IHJlClcgbgpxCjEgMSAxIHJnCi03OTIwLjA4MzQ5NjEgLTcxNDguMjAwMTk1MyA4OCAxNiByZQpmClEKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC03ODYwLjA4MzQ5NjEgLTcxMzQuNzAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooMTAwMCkgVGoKRVQKUQpRClEKUQpRClEKUQpxCnEKcQpxCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODQwLjk1MDE5NTMgLTcxMDcuNzAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooVm9sdW50YXJ5IERlZHVjdGlibGUguSkgVGoKRVQKUQpRCnEKcQpxCjAuNTAxOTYwOCAwLjUwMTk2MDggMC41MDE5NjA4IFJHCjEgdwotOTUzMS40NTAxOTUzIC03MTIwLjcwMDE5NTMgODkgMTcgcmUKUwpRCnEKcQpxCnEKLTk1MzAuOTUwMTk1MyAtNzEyMC4yMDAxOTUzIDg4IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05NTMwLjk1MDE5NTMgLTcxMjAuMjAwMTk1MyA4OCAxNiByZQpmClEKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NDcwLjk1MDE5NTMgLTcxMDYuNzAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooNTAwMCkgVGoKRVQKUQpRClEKUQpRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NzYzLjQzMzU5MzggLTcxMDcuNzAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooVm9sdW50YXJ5IERlZHVjdGlibGUgZm9yIERlcHJlY2lhdGlvbiBDb3ZlciC5KSBUagpFVApRClEKcQpxCnEKMC41MDE5NjA4IDAuNTAxOTYwOCAwLjUwMTk2MDggUkcKMSB3Ci04NDkwLjkzMzU5MzggLTcxMjAuNzAwMTk1MyA4OSAxNyByZQpTClEKcQpxCnEKcQotODQ5MC40MzM1OTM4IC03MTIwLjIwMDE5NTMgODggMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTg0OTAuNDMzNTkzOCAtNzEyMC4yMDAxOTUzIDg4IDE2IHJlCmYKUQpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg0MDkuNDMzNTkzOCAtNzEwNi43MDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCigwKSBUagpFVApRClEKUQpRClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgwMjMuMDQ5ODA0NyAtNzEwNy43MDAxOTUzIGNtCkJUCi9GMyAxMyBUZgowIFRyCihUb3RhbCBEZWR1Y3RpYmxlILkpIFRqCkVUClEKUQpxCnEKcQowLjUwMTk2MDggMC41MDE5NjA4IDAuNTAxOTYwOCBSRwoxIHcKLTc5MDkuNTQ5ODA0NyAtNzEyMC43MDAxOTUzIDg5IDE3IHJlClMKUQpxCnEKcQpxCi03OTA5LjA0OTgwNDcgLTcxMjAuMjAwMTk1MyA4OCAxNiByZQpXIG4KcQoxIDEgMSByZwotNzkwOS4wNDk4MDQ3IC03MTIwLjIwMDE5NTMgODggMTYgcmUKZgpRClEKcQowIDAgMCByZwoxIDAgMCAtMSAtNzg0OS4wNDk4MDQ3IC03MTA2LjcwMDE5NTMgY20KQlQKL0YzIDEzIFRmCjAgVHIKKDYwMDApIFRqCkVUClEKUQpRClEKUQpRClEKUQpRClEKUQpRClEKUQpRCnEKcQpxCi05ODgzLjA0OTgwNDcgLTcwOTMuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKVyBuCnEKMCAwLjIgMC40IHJnCi05ODgzLjA0OTgwNDcgLTcwOTMuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKZgpRClEKcQoxIDEgMSByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC03MDczLjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKElOVEVSTUVESUFSWSBERVRBSUxTKSBUagpFVApRClEKUQpxCnEKcQpxCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTcwMzMuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooSW50ZXJtZWRpYXJ5IENvZGUpIFRqCkVUClEKUQpxCnEKcQowLjUwMTk2MDggMC41MDE5NjA4IDAuNTAxOTYwOCBSRwoxIHcKLTk1NDkuMjk5ODA0NyAtNzA1MS43MDAxOTUzIDUxMi43ODMzMjUyIDIyIHJlClMKUQpxCnEKcQpxCi05NTQ4Ljc5OTgwNDcgLTcwNTEuMjAwMTk1MyA1MS4xODMzMzQ0IDIxIHJlClcgbgpxCjEgMSAxIHJnCi05NTQ4Ljc5OTgwNDcgLTcwNTEuMjAwMTk1MyA1MS4xODMzMzQ0IDIxIHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05NDk4LjExNjQ3MDMgLTcwNTEuMjAwMTk1MyBtCi05NDk4LjExNjQ3MDMgLTcwMzAuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NTI4LjIxNjc5NjkgLTcwMzMuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooMSkgVGoKRVQKUQpRCnEKcQotOTQ5Ny42MTYyMTA5IC03MDUxLjIwMDE5NTMgNTEuMTgzMzM0NCAyMSByZQpXIG4KcQoxIDEgMSByZwotOTQ5Ny42MTYyMTA5IC03MDUxLjIwMDE5NTMgNTEuMTgzMzM0NCAyMSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTQ0Ni45MzI4NzY2IC03MDUxLjIwMDE5NTMgbQotOTQ0Ni45MzI4NzY2IC03MDMwLjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTQ3Ny4wMzMyMDMxIC03MDMzLjIwMDE5NTMgY20KQlQKL0YyIDE4IFRmCjAgVHIKKDEpIFRqCkVUClEKUQpxCnEKLTk0NDYuNDMzNTkzOCAtNzA1MS4yMDAxOTUzIDUxLjE4MzMzNDQgMjEgcmUKVyBuCnEKMSAxIDEgcmcKLTk0NDYuNDMzNTkzOCAtNzA1MS4yMDAxOTUzIDUxLjE4MzMzNDQgMjEgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkzOTUuNzUwMjU5NCAtNzA1MS4yMDAxOTUzIG0KLTkzOTUuNzUwMjU5NCAtNzAzMC4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk0MjUuODQ5NjA5NCAtNzAzMy4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCig0KSBUagpFVApRClEKcQpxCi05Mzk1LjI1IC03MDUxLjIwMDE5NTMgNTEuMTgzMzM0NCAyMSByZQpXIG4KcQoxIDEgMSByZwotOTM5NS4yNSAtNzA1MS4yMDAxOTUzIDUxLjE4MzMzNDQgMjEgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkzNDQuNTY2NjY1NiAtNzA1MS4yMDAxOTUzIG0KLTkzNDQuNTY2NjY1NiAtNzAzMC4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkzNzQuNjY2OTkyMiAtNzAzMy4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCigwKSBUagpFVApRClEKcQpxCi05MzQ0LjA2NjQwNjMgLTcwNTEuMjAwMTk1MyA1MS4xODMzMzQ0IDIxIHJlClcgbgpxCjEgMSAxIHJnCi05MzQ0LjA2NjQwNjMgLTcwNTEuMjAwMTk1MyA1MS4xODMzMzQ0IDIxIHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MjkzLjM4MzA3MTkgLTcwNTEuMjAwMTk1MyBtCi05MjkzLjM4MzA3MTkgLTcwMzAuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MzIzLjQ4MzM5ODQgLTcwMzMuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooNCkgVGoKRVQKUQpRCnEKcQotOTI5Mi44ODM3ODkxIC03MDUxLjIwMDE5NTMgNTEuMTY2NjcxOCAyMSByZQpXIG4KcQoxIDEgMSByZwotOTI5Mi44ODM3ODkxIC03MDUxLjIwMDE5NTMgNTEuMTY2NjcxOCAyMSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTI0Mi4yMTcxMTczIC03MDUxLjIwMDE5NTMgbQotOTI0Mi4yMTcxMTczIC03MDMwLjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTI3Mi4yOTk4MDQ3IC03MDMzLjIwMDE5NTMgY20KQlQKL0YyIDE4IFRmCjAgVHIKKDgpIFRqCkVUClEKUQpxCnEKLTkyNDEuNzE2Nzk2OSAtNzA1MS4yMDAxOTUzIDUxLjE4MzMxOTEgMjEgcmUKVyBuCnEKMSAxIDEgcmcKLTkyNDEuNzE2Nzk2OSAtNzA1MS4yMDAxOTUzIDUxLjE4MzMxOTEgMjEgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkxOTEuMDMzNDc3OCAtNzA1MS4yMDAxOTUzIG0KLTkxOTEuMDMzNDc3OCAtNzAzMC4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkyMjEuMTMzNzg5MSAtNzAzMy4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCig1KSBUagpFVApRClEKcQpxCi05MTkwLjUzMzIwMzEgLTcwNTEuMjAwMTk1MyA1MS4xNjY2NzE4IDIxIHJlClcgbgpxCjEgMSAxIHJnCi05MTkwLjUzMzIwMzEgLTcwNTEuMjAwMTk1MyA1MS4xNjY2NzE4IDIxIHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MTM5Ljg2NjUzMTQgLTcwNTEuMjAwMTk1MyBtCi05MTM5Ljg2NjUzMTQgLTcwMzAuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MTY5Ljk1MDE5NTMgLTcwMzMuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooMikgVGoKRVQKUQpRCnEKcQotOTEzOS4zNjYyMTA5IC03MDUxLjIwMDE5NTMgNTEuMTgzMzM0NCAyMSByZQpXIG4KcQoxIDEgMSByZwotOTEzOS4zNjYyMTA5IC03MDUxLjIwMDE5NTMgNTEuMTgzMzM0NCAyMSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTA4OC42ODI4NzY2IC03MDUxLjIwMDE5NTMgbQotOTA4OC42ODI4NzY2IC03MDMwLjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTExOC43ODMyMDMxIC03MDMzLjIwMDE5NTMgY20KQlQKL0YyIDE4IFRmCjAgVHIKKDkpIFRqCkVUClEKUQpxCnEKLTkwODguMTgzNTkzOCAtNzA1MS4yMDAxOTUzIDUxLjE2NjY3MTggMjEgcmUKVyBuCnEKMSAxIDEgcmcKLTkwODguMTgzNTkzOCAtNzA1MS4yMDAxOTUzIDUxLjE2NjY3MTggMjEgcmUKZgpRClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTA2Ny4wOTk2MDk0IC03MDMzLjIwMDE5NTMgY20KQlQKL0YyIDE4IFRmCjAgVHIKKDIpIFRqCkVUClEKUQpRClEKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTAxNi4wMTY2MDE2IC03MDMzLjIwMDE5NTMgY20KQlQKL0YyIDE4IFRmCjAgVHIKKEludGVybWVkaWFyeSBOYW1lKSBUagpFVApRClEKcQpxCnEKMC41MDE5NjA4IDAuNTAxOTYwOCAwLjUwMTk2MDggUkcKMSB3Ci04NjI0LjUgLTcwNTEuNzAwMTk1MyA4MjEuMDQ5OTg3OCAyMiByZQpTClEKcQpxCnEKcQotODYyNCAtNzA1MS4yMDAxOTUzIDgyMC4wNDk5ODc4IDIxIHJlClcgbgpxCjEgMSAxIHJnCi04NjI0IC03MDUxLjIwMDE5NTMgODIwLjA0OTk4NzggMjEgcmUKZgpRClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODYyNCAtNzAzMy4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCihLTUJMLTUyOTItU0hBS0FSUFVSLUJSQU5DSCBCQU5LSU5HLTA1MSkgVGoKRVQKUQpRClEKUQpRClEKUQpRClEKUQpRCnEKcQpxCnEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NTguMDQ5ODA0NyAtNjk4OS4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCihJbnRlcm1lZGlhcnkncyBNb2JpbGUgTm8uKSBUagpFVApRClEKcQpxCnEKMC41MDE5NjA4IDAuNTAxOTYwOCAwLjUwMTk2MDggUkcKMSB3Ci05NDQ2LjUzMzIwMzEgLTcwMDUuMjAwMTk1MyA2MTUuNTMzMzI1MiAxNyByZQpTClEKcQpxCnEKcQotOTQ0Ni4wMzMyMDMxIC03MDA0LjcwMDE5NTMgNjEuNDUwMDEyMiAxNiByZQpXIG4KcQoxIDEgMSByZwotOTQ0Ni4wMzMyMDMxIC03MDA0LjcwMDE5NTMgNjEuNDUwMDEyMiAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTM4NS4wODMxOTA5IC03MDA0LjcwMDE5NTMgbQotOTM4NS4wODMxOTA5IC02OTg4LjcwMDE5NTMgbApTClEKUQpxCnEKLTkzODQuNTgzMDA3OCAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTkzODQuNTgzMDA3OCAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkzMjMuNjMzMDEwOSAtNzAwNC43MDAxOTUzIG0KLTkzMjMuNjMzMDEwOSAtNjk4OC43MDAxOTUzIGwKUwpRClEKcQpxCi05MzIzLjEzMzc4OTEgLTcwMDQuNzAwMTk1MyA2MS40NDk5OTY5IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05MzIzLjEzMzc4OTEgLTcwMDQuNzAwMTk1MyA2MS40NDk5OTY5IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MjYyLjE4Mzc5MjEgLTcwMDQuNzAwMTk1MyBtCi05MjYyLjE4Mzc5MjEgLTY5ODguNzAwMTk1MyBsClMKUQpRCnEKcQotOTI2MS42ODM1OTM4IC03MDA0LjcwMDE5NTMgNjEuNDQ5OTk2OSAxNiByZQpXIG4KcQoxIDEgMSByZwotOTI2MS42ODM1OTM4IC03MDA0LjcwMDE5NTMgNjEuNDQ5OTk2OSAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTIwMC43MzM1OTY4IC03MDA0LjcwMDE5NTMgbQotOTIwMC43MzM1OTY4IC02OTg4LjcwMDE5NTMgbApTClEKUQpxCnEKLTkyMDAuMjMzMzk4NCAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTkyMDAuMjMzMzk4NCAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkxMzkuMjgzNDAxNSAtNzAwNC43MDAxOTUzIG0KLTkxMzkuMjgzNDAxNSAtNjk4OC43MDAxOTUzIGwKUwpRClEKcQpxCi05MTM4Ljc4MzIwMzEgLTcwMDQuNzAwMTk1MyA2MS40NTAwMTIyIDE2IHJlClcgbgpxCjEgMSAxIHJnCi05MTM4Ljc4MzIwMzEgLTcwMDQuNzAwMTk1MyA2MS40NTAwMTIyIDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MDc3LjgzMzE5MDkgLTcwMDQuNzAwMTk1MyBtCi05MDc3LjgzMzE5MDkgLTY5ODguNzAwMTk1MyBsClMKUQpRCnEKcQotOTA3Ny4zMzMwMDc4IC03MDA0LjcwMDE5NTMgNjEuNDY2NjU5NSAxNiByZQpXIG4KcQoxIDEgMSByZwotOTA3Ny4zMzMwMDc4IC03MDA0LjcwMDE5NTMgNjEuNDY2NjU5NSAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTAxNi4zNjYzNDgzIC03MDA0LjcwMDE5NTMgbQotOTAxNi4zNjYzNDgzIC02OTg4LjcwMDE5NTMgbApTClEKUQpxCnEKLTkwMTUuODY2MjEwOSAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTkwMTUuODY2MjEwOSAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg5NTQuOTE2MjE0IC03MDA0LjcwMDE5NTMgbQotODk1NC45MTYyMTQgLTY5ODguNzAwMTk1MyBsClMKUQpRCnEKcQotODk1NC40MTY5OTIyIC03MDA0LjcwMDE5NTMgNjEuNDY2Njc0OCAxNiByZQpXIG4KcQoxIDEgMSByZwotODk1NC40MTY5OTIyIC03MDA0LjcwMDE5NTMgNjEuNDY2Njc0OCAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODg5My40NTAzMTc0IC03MDA0LjcwMDE5NTMgbQotODg5My40NTAzMTc0IC02OTg4LjcwMDE5NTMgbApTClEKUQpxCnEKLTg4OTIuOTUwMTk1MyAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTg4OTIuOTUwMTk1MyAtNzAwNC43MDAxOTUzIDYxLjQ0OTk5NjkgMTYgcmUKZgpRClEKUQpRClEKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtODgyMC41IC02OTg5LjIwMDE5NTMgY20KQlQKL0YyIDE4IFRmCjAgVHIKKEludGVybWVkaWFyeSdzIExhbmRsaW5lIE5vLikgVGoKRVQKUQpRCnEKcQpxCjAuNTAxOTYwOCAwLjUwMTk2MDggMC41MDE5NjA4IFJHCjEgdwotODM3Ny44ODM3ODkxIC03MDA3LjcwMDE5NTMgNTc0LjQzMzM0OTYgMjIgcmUKUwpRCnEKcQpxCnEKLTgzNzcuMzgzNzg5MSAtNzAwNy4yMDAxOTUzIDQ3Ljc4MzMyNTIgMjEgcmUKVyBuCnEKMSAxIDEgcmcKLTgzNzcuMzgzNzg5MSAtNzAwNy4yMDAxOTUzIDQ3Ljc4MzMyNTIgMjEgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgzMzAuMTAwNDYzOSAtNzAwNy4yMDAxOTUzIG0KLTgzMzAuMTAwNDYzOSAtNjk4Ni4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgzNTguNSAtNjk4OS4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCigxKSBUagpFVApRClEKcQpxCi04MzI5LjU5OTYwOTQgLTcwMDcuMjAwMTk1MyA0Ny43ODMzNDA1IDIxIHJlClcgbgpxCjEgMSAxIHJnCi04MzI5LjU5OTYwOTQgLTcwMDcuMjAwMTk1MyA0Ny43ODMzNDA1IDIxIHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MjgyLjMxNjI2ODkgLTcwMDcuMjAwMTk1MyBtCi04MjgyLjMxNjI2ODkgLTY5ODYuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MzEwLjcxNjc5NjkgLTY5ODkuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooOCkgVGoKRVQKUQpRCnEKcQotODI4MS44MTY0MDYzIC03MDA3LjIwMDE5NTMgNDcuNzgzMzI1MiAyMSByZQpXIG4KcQoxIDEgMSByZwotODI4MS44MTY0MDYzIC03MDA3LjIwMDE5NTMgNDcuNzgzMzI1MiAyMSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODIzNC41MzMwODExIC03MDA3LjIwMDE5NTMgbQotODIzNC41MzMwODExIC02OTg2LjIwMDE5NTMgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODI2Mi45MzM1OTM4IC02OTg5LjIwMDE5NTMgY20KQlQKL0YyIDE4IFRmCjAgVHIKKDYpIFRqCkVUClEKUQpxCnEKLTgyMzQuMDMzMjAzMSAtNzAwNy4yMDAxOTUzIDQ3Ljc4MzM0MDUgMjEgcmUKVyBuCnEKMSAxIDEgcmcKLTgyMzQuMDMzMjAzMSAtNzAwNy4yMDAxOTUzIDQ3Ljc4MzM0MDUgMjEgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgxODYuNzQ5ODYyNyAtNzAwNy4yMDAxOTUzIG0KLTgxODYuNzQ5ODYyNyAtNjk4Ni4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgyMTUuMTUwMzkwNiAtNjk4OS4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCigwKSBUagpFVApRClEKcQpxCi04MTg2LjI1IC03MDA3LjIwMDE5NTMgNDcuNzgzMzQwNSAyMSByZQpXIG4KcQoxIDEgMSByZwotODE4Ni4yNSAtNzAwNy4yMDAxOTUzIDQ3Ljc4MzM0MDUgMjEgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTgxMzguOTY2NjU5NSAtNzAwNy4yMDAxOTUzIG0KLTgxMzguOTY2NjU5NSAtNjk4Ni4yMDAxOTUzIGwKUwpRClEKcQpxCi04MTM4LjQ2Njc5NjkgLTcwMDcuMjAwMTk1MyA0Ny43ODMzMjUyIDIxIHJlClcgbgpxCjEgMSAxIHJnCi04MTM4LjQ2Njc5NjkgLTcwMDcuMjAwMTk1MyA0Ny43ODMzMjUyIDIxIHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04MDkxLjE4MzQ3MTcgLTcwMDcuMjAwMTk1MyBtCi04MDkxLjE4MzQ3MTcgLTY5ODYuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MTE5LjU4MzQ5NjEgLTY5ODkuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooMikgVGoKRVQKUQpRCnEKcQotODA5MC42ODMxMDU1IC03MDA3LjIwMDE5NTMgNDcuNzgzMzQwNSAyMSByZQpXIG4KcQoxIDEgMSByZwotODA5MC42ODMxMDU1IC03MDA3LjIwMDE5NTMgNDcuNzgzMzQwNSAyMSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODA0My4zOTk3NjUgLTcwMDcuMjAwMTk1MyBtCi04MDQzLjM5OTc2NSAtNjk4Ni4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgwNzEuNzk5ODA0NyAtNjk4OS4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCig2KSBUagpFVApRClEKcQpxCi04MDQyLjg5OTkwMjMgLTcwMDcuMjAwMTk1MyA0Ny43ODMzMjUyIDIxIHJlClcgbgpxCjEgMSAxIHJnCi04MDQyLjg5OTkwMjMgLTcwMDcuMjAwMTk1MyA0Ny43ODMzMjUyIDIxIHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03OTk1LjYxNjU3NzEgLTcwMDcuMjAwMTk1MyBtCi03OTk1LjYxNjU3NzEgLTY5ODYuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MDI0LjAxNjYwMTYgLTY5ODkuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooNikgVGoKRVQKUQpRCnEKcQotNzk5NS4xMTY2OTkyIC03MDA3LjIwMDE5NTMgNDcuODAwMDAzMSAyMSByZQpXIG4KcQoxIDEgMSByZwotNzk5NS4xMTY2OTkyIC03MDA3LjIwMDE5NTMgNDcuODAwMDAzMSAyMSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzk0Ny44MTY2OTYyIC03MDA3LjIwMDE5NTMgbQotNzk0Ny44MTY2OTYyIC02OTg2LjIwMDE5NTMgbApTClEKUQpxCnEKLTc5NDcuMzE2ODk0NSAtNzAwNy4yMDAxOTUzIDQ3Ljc4MzMyNTIgMjEgcmUKVyBuCnEKMSAxIDEgcmcKLTc5NDcuMzE2ODk0NSAtNzAwNy4yMDAxOTUzIDQ3Ljc4MzMyNTIgMjEgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc5MDAuMDMzNTY5MyAtNzAwNy4yMDAxOTUzIG0KLTc5MDAuMDMzNTY5MyAtNjk4Ni4yMDAxOTUzIGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTc5MjguNDMzMTA1NSAtNjk4OS4yMDAxOTUzIGNtCkJUCi9GMiAxOCBUZgowIFRyCigyKSBUagpFVApRClEKcQpxCi03ODk5LjUzMzIwMzEgLTcwMDcuMjAwMTk1MyA0Ny44MDAwMDMxIDIxIHJlClcgbgpxCjEgMSAxIHJnCi03ODk5LjUzMzIwMzEgLTcwMDcuMjAwMTk1MyA0Ny44MDAwMDMxIDIxIHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03ODUyLjIzMzIwMDEgLTcwMDcuMjAwMTk1MyBtCi03ODUyLjIzMzIwMDEgLTY5ODYuMjAwMTk1MyBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC03ODgwLjYzMzMwMDggLTY5ODkuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooNikgVGoKRVQKUQpRCnEKcQotNzg1MS43MzMzOTg0IC03MDA3LjIwMDE5NTMgNDcuNzgzMzQwNSAyMSByZQpXIG4KcQoxIDEgMSByZwotNzg1MS43MzMzOTg0IC03MDA3LjIwMDE5NTMgNDcuNzgzMzQwNSAyMSByZQpmClEKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC03ODMyLjM1MDA5NzcgLTY5ODkuMjAwMTk1MyBjbQpCVAovRjIgMTggVGYKMCBUcgooNikgVGoKRVQKUQpRClEKUQpRClEKUQpRClEKUQpRCnEKcQpxCi05ODgzLjA0OTgwNDcgLTY5NzUuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKVyBuCnEKMCAwLjIgMC40IHJnCi05ODgzLjA0OTgwNDcgLTY5NzUuMjAwMTk1MyAyMTA1LjEwMDA5NzcgMzAgcmUKZgpRClEKcQoxIDEgMSByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC02OTU1LjIwMDE5NTMgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKERJU0NMQUlNRVIpIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTY5MjMuMjAwMTk1MyBjbQpCVAovRjMgMTMgVGYKMCBUcgooRm9yIGNvbXBsZXRlIGRldGFpbHMgb24gdGVybXMgYW5kIGNvbmRpdGlvbnMgZ292ZXJuaW5nIHRoZSBjb3ZlcmFnZSBhbmQgTkNCIHBsZWFzZSByZWFkIHRoZSBQb2xpY3kgV29yZGluZ3MuIFRoaXMgZG9jdW1lbnQgaXMgdG8gYmUgcmVhZCB3aXRoIHRoZSBwb2xpY3kgd29yZGluZ3MuIFBsZWFzZSByZWZlciB0byB0aGUgY2xhaW0gZm9ybSBmb3IgbmVjZXNzYXJ5IGRvY3VtZW50cyB0byBiZSBzdWJtaXR0ZWQgZm9yIHByb2Nlc3NpbmcgdGhlIGNsYWltLikgVGoKRVQKUQpRClEKUQpRCnEKcQotOTg4My4wNDk4MDQ3IC02OTE0LjIwMDE5NTMgMjEwNS4wNjY2NTA0IDEzMC4wOTk5OTA4IHJlClcgbgpxCjAuOCAwLjggMC44IHJnCi05ODgzLjA0OTgwNDcgLTY5MTQuMjAwMTk1MyAyMTA1LjA2NjY1MDQgMTMwLjA5OTk5MDggcmUKZgpRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MTk5LjUxNjYwMTYgLTY4NTUuNjQ5OTAyMyBjbQpCVAovRjMgMTEgVGYKMCBUcgooQ0lOOiBVNjYwMDBNSDIwMTRQTEMyNjAyOTEsKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5MzYuNTE2NjAxNiAtNjg1NS42NDk5MDIzIGNtCkJUCi9GMyAxMSBUZgowIFRyCigyNyBCS0MsIEMgMjcsIEcgQmxvY2ssIEJhbmRyYSBLdXJsYSBDb21wbGV4LCBCYW5kcmEgRWFzdCwgTXVtYmFpIDQwMDA1MSwgTWFoYXJhc2h0cmEsIEluZGlhLikgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MjQ1LjAxNjYwMTYgLTY4MzMuNjQ5OTAyMyBjbQpCVAovRjMgMTEgVGYKMCBUcgooT2ZmaWNlOiA4dGggRmxvb3IsIFpvbmUgSVYsIEtvdGFrIEluZmluaXR5LCBCdWlsZGluZyBOby4yMSwgSW5maW5pdHkgSVQgUGFyaywgT2ZmIFdlc3Rlcm4gRXhwcmVzcyBIaWdod2F5LCBHZW5lcmFsIEFLIFZhaWR5YSBNYXJnLCBEaW5kb3NoaSwgTWFsYWRcKEVcKSwgTXVtYmFpIDQwMDA5Ny4gSW5kaWEuKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkxMDIuNTE2NjAxNiAtNjgxMS42NDk5MDIzIGNtCkJUCi9GMyAxMSBUZgowIFRyCihUb2xsIEZyZWU6IDE4MDAgMjY2IDQ1NDUgRW1haWw6Y2FyZUBrb3Rhay5jb20gV2Vic2l0ZTogd3d3LmtvdGFrZ2VuZXJhbGluc3VyYW5jZS5jb20gSVJEQUkgUmVnLiBOby4gMTUyKSBUagpFVApRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTExMy4wMTY2MDE2IC02ODc3LjY0OTkwMjMgY20KQlQKL0Y1IDExIFRmCjAgVHIKKEtvdGFrIE1haGluZHJhIEdlbmVyYWwgSW5zdXJhbmNlIENvbXBhbnkgTGltaXRlZCBcKEZvcm1lcmx5IEtvdGFrIE1haGluZHJhIEdlbmVyYWwgSW5zdXJhbmNlIExpbWl0ZWRcKSkgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTAzMi41MTY2MDE2IC02ODU1LjY0OTkwMjMgY20KQlQKL0Y1IDExIFRmCjAgVHIKKFJlZ2lzdGVyZWQgT2ZmaWNlOikgVGoKRVQKUQpRClEKUQpRClEKUQpRClEKCmVuZHN0cmVhbSBlbmRvYmoKCjE0IDAgb2JqIDw8CiAgL0NvbnRlbnRzIDEzIDAgUgogIC9QYXJlbnQgMiAwIFIKICAvTWVkaWFCb3ggWyAwIDAgMjEwNS4xMDAwOTc3IDEyNjMuMzMyOTAxIF0KICAvVHlwZSAvUGFnZQogIC9Qcm9jU2V0IFsg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NjUzLjk2Njc5NjkgMjEwNS4xMDAwOTc3IDMwIHJlCmYKUQpRCnEKMSAxIDEgcmcKMSAwIDAgLTEgLTk4NTguMDQ5ODA0NyAtNjYzMy45NjY3OTY5IGNtCkJUCi9GNSAxMiBUZgowIFRyCihMSU1JVFMgT0YgTElBQklMSVRZKSBUagpFVApRClEKUQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC02NjAzLjk2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFVuZGVyIFNlY3Rpb24gSUkgLSAxXChpXCkgb2YgdGhlIHBvbGljeSAtPiBEZWF0aCBvZiBvciBib2RpbHkgaW5qdXJ5OiBTdWNoIGFtb3VudHMgaXMgbmVjZXNzYXJ5IHRvIG1lZXQgdGhlaXIgcmVxdWlyZW1lbnRzIG9mIE1vdG9yIFZlaGljbGVzIEFjdCwgMTk4OC4pIFRqCkVUClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC02NTkwLjk2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKFVuZGVyIFNlY3Rpb24gSUkgLSAxXChpaVwpIG9mIHRoZSBwb2xpY3kgLT4gRGFtYWdlIHRvIFRoaXJkIFBhcnR5IFByb3BlcnR5ILkgNyw1MCwwMDA7IFBBIENvdmVyIHVuZGVyIFNlY3Rpb24gSUlJOiBmb3IgT3duZXIgRHJpdmVyIENTSSC5IDIsMDAsMDAwKSBUagpFVApRClEKUQpxCnEKcQotOTg4My4wNDk4MDQ3IC02NTczLjk2Njc5NjkgMjEwNS4xMDAwOTc3IDMwIHJlClcgbgpxCjAgMC4yIDAuNCByZwotOTg4My4wNDk4MDQ3IC02NTczLjk2Njc5NjkgMjEwNS4xMDAwOTc3IDMwIHJlCmYKUQpRCnEKMSAxIDEgcmcKMSAwIDAgLTEgLTk4NTguMDQ5ODA0NyAtNjU1My45NjY3OTY5IGNtCkJUCi9GNSAxMiBUZgowIFRyCihMSU1JVEFUSU9OUyBBUyBUTyBVU0UpIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTY1MjMuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooVGhlIHBvbGljeSBjb3ZlcnMgdXNlIG9mIHRoZSB2ZWhpY2xlIGZvciBhbnkgcHVycG9zZSBvdGhlciB0aGFuOyBIaXJlIG9yIHJld2FyZCwgQ2FycmlhZ2Ugb2YgZ29vZHMgXChvdGhlciB0aGFuIHNhbXBsZXMgb3IgcGVyc29uYWwgbHVnZ2FnZVwpLCBvcmdhbml6ZWQgcmFjaW5nLCBQYWNlIG1ha2luZywgc3BlZWQgdGVzdGluZywgcmVsaWFiaWxpdHkgdHJhaWxzIG9yIGFueSBwdXJwb3NlIGluIGNvbm5lY3Rpb24gd2l0aCBNb3RvciBUcmFkZS4pIFRqCkVUClEKUQpRCnEKcQpxCi05ODgzLjA0OTgwNDcgLTY1MDcuOTY2Nzk2OSAyMTA1LjEwMDA5NzcgMzAgcmUKVyBuCnEKMCAwLjIgMC40IHJnCi05ODgzLjA0OTgwNDcgLTY1MDcuOTY2Nzk2OSAyMTA1LjEwMDA5NzcgMzAgcmUKZgpRClEKcQoxIDEgMSByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC02NDg3Ljk2Njc5NjkgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKERSSVZFUidTIENMQVVTRVMpIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTY0NTcuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooQW55IHBlcnNvbiBpbmNsdWRpbmcgdGhlIGluc3VyZWQ6IFByb3ZpZGVkIHRoYXQgYSBwZXJzb24gZHJpdmluZyBob2xkIGFuIGVmZmVjdGl2ZSBEcml2aW5nIExpY2Vuc2UgYXQgdGhlIHRpbWUgb2YgYWNjaWRlbnQgYW5kIGlzIG5vdCBkaXNxdWFsaWZpZWQgZnJvbSBob2xkaW5nIG9yIG9idGFpbmluZyBzdWNoIGEgbGljZW5zZS4gUHJvdmlkZWQgYWxzbyB0aGF0IHRoZSBwZXJzb24gaG9sZGluZyBhbiBlZmZlY3RpdmUgTGVhcm5lcnPvv70gTGljZW5zZSBtYXkgYWxzbyBkcml2ZSB0aGUgVmVoaWNsZSBhbmQgdGhhdCBzdWNoIGEgcGVyc29uIHNhdGlzZmllcyB0aGUgcmVxdWlyZW1lbnRzIG9mIFJ1bGUgMyBvZiB0aGUgQ2VudHJhbCBNb3RvciB2ZWhpY2xlcyBSdWxlcyAxOTg5LikgVGoKRVQKUQpRClEKcQpxCnEKLTk4ODMuMDQ5ODA0NyAtNjQ0MS45NjY3OTY5IDIxMDUuMTAwMDk3NyAzMCByZQpXIG4KcQowIDAuMiAwLjQgcmcKLTk4ODMuMDQ5ODA0NyAtNjQ0MS45NjY3OTY5IDIxMDUuMTAwMDk3NyAzMCByZQpmClEKUQpxCjEgMSAxIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTY0MjEuOTY2Nzk2OSBjbQpCVAovRjUgMTIgVGYKMCBUcgooTm8gQ0xBSU0gQk9OVVMgU2NhbGUpIFRqCkVUClEKUQpRCnEKcQpxCnEKcQpxCnEKLTk4NTguMDQ5ODA0NyAtNjM4MS45NjY3OTY5IG0KLTk4NTguMDQ5ODA0NyAtNjM4NS44OTM3ODc3IC05ODU2LjQzMzIwODIgLTYzODkuNzk2NTk2OCAtOTg1My42NTY0MDY0IC02MzkyLjU3MzM5ODYgYwotOTg1MC44Nzk2MDQ2IC02Mzk1LjM1MDIwMDQgLTk4NDYuOTc2Nzk1NSAtNjM5Ni45NjY3OTY5IC05ODQzLjA0OTgwNDcgLTYzOTYuOTY2Nzk2OSBjCi04NjI0Ljk4MzE1NDMgLTYzOTYuOTY2Nzk2OSBsCi04NjI0Ljk4MzE1NDMgLTYzNzAuOTY2Nzk2OSBsCi05ODU4LjA0OTgwNDcgLTYzNzAuOTY2Nzk2OSBsCmgKVyBuCnEKMC44MjM1Mjk0IDAuODI3NDUxIDAuODM1Mjk0MSByZwotOTg1OC4wNDk4MDQ3IC02Mzk2Ljk2Njc5NjkgMTIzMy4wNjY2NTA0IDI2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODU3LjU0OTgwNDcgLTYzODEuOTY2Nzk2OSBtCi05ODU3LjU0OTgwNDcgLTYzODUuNzYyODg4IC05ODU1Ljk4NzA5NDggLTYzODkuNTM1NjAzNCAtOTg1My4zMDI4NTMgLTYzOTIuMjE5ODQ1MiBjCi05ODUwLjYxODYxMTIgLTYzOTQuOTA0MDg3IC05ODQ2Ljg0NTg5NTggLTYzOTYuNDY2Nzk2OSAtOTg0My4wNDk4MDQ3IC02Mzk2LjQ2Njc5NjkgYwotODYyNS40ODMxNTQzIC02Mzk2LjQ2Njc5NjkgbAotODYyNS40ODMxNTQzIC02MzcxLjQ2Njc5NjkgbAotOTg1Ny41NDk4MDQ3IC02MzcxLjQ2Njc5NjkgbApoClMKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4MzIuMDQ5ODA0NyAtNjM3OC45NjY3OTY5IGNtCkJUCi9GNSAxMiBUZgowIFRyCihBbGwgdHlwZXMgb2YgdmVoaWNsZXMpIFRqCkVUClEKUQpRCnEKcQotODYyNC45ODMzOTg0IC02Mzk2Ljk2Njc5NjkgbQotNzgxNy45NTAwNzMyIC02Mzk2Ljk2Njc5NjkgbAotNzgxNC4wMjMwODI0IC02Mzk2Ljk2Njc5NjkgLTc4MTAuMTIwMjczNCAtNjM5NS4zNTAyMDA0IC03ODA3LjM0MzQ3MTUgLTYzOTIuNTczMzk4NiBjCi03ODA0LjU2NjY2OTcgLTYzODkuNzk2NTk2OCAtNzgwMi45NTAwNzMyIC02Mzg1Ljg5Mzc4NzcgLTc4MDIuOTUwMDczMiAtNjM4MS45NjY3OTY5IGMKLTc4MDIuOTUwMDczMiAtNjM3MC45NjY3OTY5IGwKLTg2MjQuOTgzMzk4NCAtNjM3MC45NjY3OTY5IGwKaApXIG4KcQowLjgyMzUyOTQgMC44Mjc0NTEgMC44MzUyOTQxIHJnCi04NjI0Ljk4MzM5ODQgLTYzOTYuOTY2Nzk2OSA4MjIuMDMzMzI1MiAyNiByZQpmClEKUQpxCjEgMCAwIDEgLTg2MjQuOTgzMzk4NCAtNjM5Ni45NjY3OTY5IGNtCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgcmcKMCAwIDgwNy4wMzMzMjUyIDEgcmUKZgpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgcmcKMSAwIDAgMSA4MDcuMDMzMzI1MiAwIGNtCjAgMCBtCjMuOTI2OTkwOCAwIDcuODI5Nzk5OSAxLjYxNjU5NjQgMTAuNjA2NjAxNyA0LjM5MzM5ODMgYwo5Ljg5OTQ5NDkgNS4xMDA1MDUxIGwKNy4zMDc4MTMyIDIuNTA4ODIzMyAzLjY2NTE5MTQgMSAwIDEgYwpoCmYKUQpRCnEKLTEgMCAwIC0xIC03ODAyLjk1MDA3MzIgLTYzNzAuOTY2Nzk2OSBjbQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IHJnCjAgMCBtCjgyMi4wMzMzMjUyIDAgbAo4MjIuMDMzMzI1MiAxIGwKMSAxIGwKaApmClEKUQpxCjAgMSAtMSAwIC03ODAyLjk1MDA3MzIgLTYzOTYuOTY2Nzk2OSBjbQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IHJnCjE1IDAgbQoyNiAwIGwKMjUgMSBsCjE1IDEgbApoCmYKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IHJnCi0xIDAgMCAxIDE1IDAgY20KMCAwIG0KMy45MjY5OTA4IDAgNy44Mjk3OTk5IDEuNjE2NTk2NCAxMC42MDY2MDE3IDQuMzkzMzk4MyBjCjkuODk5NDk0OSA1LjEwMDUwNTEgbAo3LjMwNzgxMzIgMi41MDg4MjMzIDMuNjY1MTkxNCAxIDAgMSBjCmgKZgpRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MzI5LjQ2Njc5NjkgLTYzNzguOTY2Nzk2OSBjbQpCVAovRjUgMTIgVGYKMCBUcgooJSBvZiBEaXNjb3VudCBvbiBPd24gRGFtYWdlIFByZW1pdW0pIFRqCkVUClEKUQpRClEKcQpxCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODU4LjA0OTgwNDcgLTYzNDYuNDY2Nzk2OSBtCi04NjI0Ljk4MzE1NDMgLTYzNDYuNDY2Nzk2OSBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg1Ny41NDk4MDQ3IC02MzcwLjk2Njc5NjkgbQotOTg1Ny41NDk4MDQ3IC02MzQ1Ljk2Njc5NjkgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg2MjUuNDgzMTU0MyAtNjM3MC45NjY3OTY5IG0KLTg2MjUuNDgzMTU0MyAtNjM0NS45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4MzIuMDQ5ODA0NyAtNjM1My45NjY3OTY5IGNtCkJUCi9GMyAxMiBUZgowIFRyCihObyBjbGFpbSBtYWRlIG9yIHBlbmRpbmcgZHVyaW5nIHRoZSBwcmVjZWRpbmcgZnVsbCB5ZWFyIG9mIGluc3VyYW5jZSkgVGoKRVQKUQpRCnEKcQotODYyNC45ODMzOTg0IC02MzcwLjk2Njc5NjkgODIyLjAzMzMyNTIgMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTg2MjQuOTgzMzk4NCAtNjM3MC45NjY3OTY5IDgyMi4wMzMzMjUyIDI1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NjI0Ljk4MzM5ODQgLTYzNDYuNDY2Nzk2OSBtCi03ODAyLjk1MDA3MzIgLTYzNDYuNDY2Nzk2OSBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzgwMy40NTAwNzMyIC02MzcwLjk2Njc5NjkgbQotNzgwMy40NTAwNzMyIC02MzQ1Ljk2Njc5NjkgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODIyNy40NjY3OTY5IC02MzUzLjQ2Njc5NjkgY20KQlQKL0YzIDEzIFRmCjAgVHIKKDIwJSkgVGoKRVQKUQpRClEKcQpxCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODU4LjA0OTgwNDcgLTYzMjEuNDY2Nzk2OSBtCi04NjI0Ljk4MzE1NDMgLTYzMjEuNDY2Nzk2OSBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg1Ny41NDk4MDQ3IC02MzQ1Ljk2Njc5NjkgbQotOTg1Ny41NDk4MDQ3IC02MzIwLjk2Njc5NjkgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg2MjUuNDgzMTU0MyAtNjM0NS45NjY3OTY5IG0KLTg2MjUuNDgzMTU0MyAtNjMyMC45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4MzIuMDQ5ODA0NyAtNjMyOC45NjY3OTY5IGNtCkJUCi9GMyAxMiBUZgowIFRyCihObyBjbGFpbSBtYWRlIG9yIHBlbmRpbmcgZHVyaW5nIHRoZSBwcmVjZWRpbmcgMiBjb25zZWN1dGl2ZSB5ZWFycyBvZiBpbnN1cmFuY2UpIFRqCkVUClEKUQpxCnEKLTg2MjQuOTgzMzk4NCAtNjM0NS45NjY3OTY5IDgyMi4wMzMzMjUyIDI1IHJlClcgbgpxCjEgMSAxIHJnCi04NjI0Ljk4MzM5ODQgLTYzNDUuOTY2Nzk2OSA4MjIuMDMzMzI1MiAyNSByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODYyNC45ODMzOTg0IC02MzIxLjQ2Njc5NjkgbQotNzgwMi45NTAwNzMyIC02MzIxLjQ2Njc5NjkgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTc4MDMuNDUwMDczMiAtNjM0NS45NjY3OTY5IG0KLTc4MDMuNDUwMDczMiAtNjMyMC45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgyMjcuNDY2Nzk2OSAtNjMyOC40NjY3OTY5IGNtCkJUCi9GMyAxMyBUZgowIFRyCigyNSUpIFRqCkVUClEKUQpRCnEKcQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTg1OC4wNDk4MDQ3IC02Mjk2LjQ2Njc5NjkgbQotODYyNC45ODMxNTQzIC02Mjk2LjQ2Njc5NjkgbApTClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NTcuNTQ5ODA0NyAtNjMyMC45NjY3OTY5IG0KLTk4NTcuNTQ5ODA0NyAtNjI5NS45NjY3OTY5IGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NjI1LjQ4MzE1NDMgLTYzMjAuOTY2Nzk2OSBtCi04NjI1LjQ4MzE1NDMgLTYyOTUuOTY2Nzk2OSBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODMyLjA0OTgwNDcgLTYzMDMuOTY2Nzk2OSBjbQpCVAovRjMgMTIgVGYKMCBUcgooTm8gY2xhaW0gbWFkZSBvciBwZW5kaW5nIGR1cmluZyB0aGUgcHJlY2VkaW5nIDMgY29uc2VjdXRpdmUgeWVhcnMgb2YgaW5zdXJhbmNlKSBUagpFVApRClEKcQpxCi04NjI0Ljk4MzM5ODQgLTYzMjAuOTY2Nzk2OSA4MjIuMDMzMzI1MiAyNSByZQpXIG4KcQoxIDEgMSByZwotODYyNC45ODMzOTg0IC02MzIwLjk2Njc5NjkgODIyLjAzMzMyNTIgMjUgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg2MjQuOTgzMzk4NCAtNjI5Ni40NjY3OTY5IG0KLTc4MDIuOTUwMDczMiAtNjI5Ni40NjY3OTY5IGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci03ODAzLjQ1MDA3MzIgLTYzMjAuOTY2Nzk2OSBtCi03ODAzLjQ1MDA3MzIgLTYyOTUuOTY2Nzk2OSBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04MjI3LjQ2Njc5NjkgLTYzMDMuNDY2Nzk2OSBjbQpCVAovRjMgMTMgVGYKMCBUcgooMzUlKSBUagpFVApRClEKUQpxCnEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk4NTguMDQ5ODA0NyAtNjI3MS40NjY3OTY5IG0KLTg2MjQuOTgzMTU0MyAtNjI3MS40NjY3OTY5IGwKUwpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05ODU3LjU0OTgwNDcgLTYyOTUuOTY2Nzk2OSBtCi05ODU3LjU0OTgwNDcgLTYyNzAuOTY2Nzk2OSBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODYyNS40ODMxNTQzIC02Mjk1Ljk2Njc5NjkgbQotODYyNS40ODMxNTQzIC02MjcwLjk2Njc5NjkgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTgzMi4wNDk4MDQ3IC02Mjc4Ljk2Njc5NjkgY20KQlQKL0YzIDEyIFRmCjAgVHIKKE5vIGNsYWltIG1hZGUgb3IgcGVuZGluZyBkdXJpbmcgdGhlIHByZWNlZGluZyA0IGNvbnNlY3V0aXZlIHllYXJzIG9mIGluc3VyYW5jZSkgVGoKRVQKUQpRCnEKcQotODYyNC45ODMzOTg0IC02Mjk1Ljk2Njc5NjkgODIyLjAzMzMyNTIgMjUgcmUKVyBuCnEKMSAxIDEgcmcKLTg2MjQuOTgzMzk4NCAtNjI5NS45NjY3OTY5IDgyMi4wMzMzMjUyIDI1IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NjI0Ljk4MzM5ODQgLTYyNzEuNDY2Nzk2OSBtCi03ODAyLjk1MDA3MzIgLTYyNzEuNDY2Nzk2OSBsClMKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotNzgwMy40NTAwNzMyIC02Mjk1Ljk2Njc5NjkgbQotNzgwMy40NTAwNzMyIC02MjcwLjk2Njc5NjkgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODIyNy40NjY3OTY5IC02Mjc4LjQ2Njc5NjkgY20KQlQKL0YzIDEzIFRmCjAgVHIKKDQ1JSkgVGoKRVQKUQpRClEKcQpxCnEKLTEgMCAwIC0xIC04NjI0Ljk4MzE1NDMgLTYyNDUuOTY2Nzk2OSBjbQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IHJnCjAgMCBtCjEyMTguMDY2NjUwNCAwIGwKMTIxOC4wNjY2NTA0IDEgbAoxIDEgbApoCmYKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IHJnCjEgMCAwIDEgMTIxOC4wNjY2NTA0IDAgY20KMCAwIG0KMy45MjY5OTA4IDAgNy44Mjk3OTk5IDEuNjE2NTk2NCAxMC42MDY2MDE3IDQuMzkzMzk4MyBjCjkuODk5NDk0OSA1LjEwMDUwNTEgbAo3LjMwNzgxMzIgMi41MDg4MjMzIDMuNjY1MTkxNCAxIDAgMSBjCmgKZgpRClEKcQowIC0xIDEgMCAtOTg1OC4wNDk4MDQ3IC02MjQ1Ljk2Njc5NjkgY20KcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiByZwoxNSAwIDEwIDEgcmUKZgpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgcmcKLTEgMCAwIDEgMTUgMCBjbQowIDAgbQozLjkyNjk5MDggMCA3LjgyOTc5OTkgMS42MTY1OTY0IDEwLjYwNjYwMTcgNC4zOTMzOTgzIGMKOS44OTk0OTQ5IDUuMTAwNTA1MSBsCjcuMzA3ODEzMiAyLjUwODgyMzMgMy42NjUxOTE0IDEgMCAxIGMKaApmClEKUQpxCjAgMSAtMSAwIC04NjI0Ljk4MzE1NDMgLTYyNzAuOTY2Nzk2OSBjbQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IHJnCjAgMCBtCjI1IDAgbAoyNCAxIGwKMCAxIGwKaApmClEKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODMyLjA0OTgwNDcgLTYyNTMuOTY2Nzk2OSBjbQpCVAovRjMgMTIgVGYKMCBUcgooTm8gY2xhaW0gbWFkZSBvciBwZW5kaW5nIGR1cmluZyB0aGUgcHJlY2VkaW5nIDUgY29uc2VjdXRpdmUgeWVhcnMgb2YgaW5zdXJhbmNlKSBUagpFVApRClEKcQpxCi04NjI0Ljk4MzM5ODQgLTYyNzAuOTY2Nzk2OSBtCi03ODAyLjk1MDA3MzIgLTYyNzAuOTY2Nzk2OSBsCi03ODAyLjk1MDA3MzIgLTYyNjAuOTY2Nzk2OSBsCi03ODAyLjk1MDA3MzIgLTYyNTcuMDM5ODA2MSAtNzgwNC41NjY2Njk3IC02MjUzLjEzNjk5NyAtNzgwNy4zNDM0NzE1IC02MjUwLjM2MDE5NTIgYwotNzgxMC4xMjAyNzM0IC02MjQ3LjU4MzM5MzMgLTc4MTQuMDIzMDgyNCAtNjI0NS45NjY3OTY5IC03ODE3Ljk1MDA3MzIgLTYyNDUuOTY2Nzk2OSBjCi04NjI0Ljk4MzM5ODQgLTYyNDUuOTY2Nzk2OSBsCmgKVyBuCnEKMSAxIDEgcmcKLTg2MjQuOTgzMzk4NCAtNjI3MC45NjY3OTY5IDgyMi4wMzMzMjUyIDI1IHJlCmYKUQpRCnEKLTEgMCAwIC0xIC03ODAyLjk1MDA3MzIgLTYyNDUuOTY2Nzk2OSBjbQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IHJnCjE1IDAgODA3LjAzMzMyNTIgMSByZQpmClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiByZwotMSAwIDAgMSAxNSAwIGNtCjAgMCBtCjMuOTI2OTkwOCAwIDcuODI5Nzk5OSAxLjYxNjU5NjQgMTAuNjA2NjAxNyA0LjM5MzM5ODMgYwo5Ljg5OTQ5NDkgNS4xMDA1MDUxIGwKNy4zMDc4MTMyIDIuNTA4ODIzMyAzLjY2NTE5MTQgMSAwIDEgYwpoCmYKUQpRCnEKMCAxIC0xIDAgLTc4MDIuOTUwMDczMiAtNjI3MC45NjY3OTY5IGNtCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgcmcKMCAwIDEwIDEgcmUKZgpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgcmcKMSAwIDAgMSAxMCAwIGNtCjAgMCBtCjMuOTI2OTkwOCAwIDcuODI5Nzk5OSAxLjYxNjU5NjQgMTAuNjA2NjAxNyA0LjM5MzM5ODMgYwo5Ljg5OTQ5NDkgNS4xMDA1MDUxIGwKNy4zMDc4MTMyIDIuNTA4ODIzMyAzLjY2NTE5MTQgMSAwIDEgYwpoCmYKUQpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTgyMjcuNDY2Nzk2OSAtNjI1My40NjY3OTY5IGNtCkJUCi9GMyAxMyBUZgowIFRyCig1MCUpIFRqCkVUClEKUQpRClEKUQpRClEKcQpxCnEKLTk4ODMuMDQ5ODA0NyAtNjIzMC45NjY3OTY5IDIxMDUuMTAwMDk3NyAzMCByZQpXIG4KcQowIDAuMiAwLjQgcmcKLTk4ODMuMDQ5ODA0NyAtNjIzMC45NjY3OTY5IDIxMDUuMTAwMDk3NyAzMCByZQpmClEKUQpxCjEgMSAxIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTYyMTAuOTY2Nzk2OSBjbQpCVAovRjUgMTIgVGYKMCBUcgooSU1QT1JUQU5UIE5PVElDRSkgVGoKRVQKUQpRClEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NTguMDQ5ODA0NyAtNjE4MC45NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihUaGUgSW5zdXJlZCBpcyBub3QgaW5kZW1uaWZpZWQgaWYgdGhlIHZlaGljbGUgaXMgdXNlZCBvciBkcml2ZW4gb3RoZXJ3aXNlIHRoYW4gaW4gYWNjb3JkYW5jZSB3aXRoIHRoaXMgU2NoZWR1bGUuIEFueSBwYXltZW50IG1hZGUgYnkgdGhlIENvbXBhbnkgYnkgcmVhc29uIG9mIHdpZGVyIHRlcm1zIGFwcGVhcmluZyBpbiB0aGUgUG9saWN5IGluIG9yZGVyIHRvIGNvbXBseSB3aXRoIHRoZSBNb3RvciBWZWhpY2xlIEFjdCwgMTk4OCBpcyByZWNvdmVyYWJsZSBmcm9tIHRoZSBJbnN1cmVkLiBTZWUgdGhlIGNsYXVzZSBoZWFkZWQgIkFWT0lEQU5DRSBPRiBDRVJUQUlOIFRFUk1TIEFORCBSSUdIVCBPRiBSRUNPVkVSWSIuIEZvciBsZWdhbCBpbnRlcnByZXRhdGlvbiwgRW5nbGlzaCB2ZXJzaW9uIHdpbGwgaG9sZCkgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTYxNjcuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooZ29vZC4pIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTYxMzEuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooU3ViamVjdCB0byBJLk0uVC4gRW5kdC5Ob3MuICYgTWVtb3JhbmR1bSkgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NjQwLjA0OTgwNDcgLTYxMzEuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooUHJpbnRlZC9oZXJlaW4vYXR0YWNoZWQgaGVyZXRvIFVuZGVyIEhpcmUgUHVyY2hhc2UgQWdyZWVtZW50IHdpdGggX19fX18pIFRqCkVUClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NjQ2LjA0OTgwNDcgLTYxMzEuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooLikgVGoKRVQKUQpxCjAgMCAwIFJHCjAuOTE2NjY2NyB3Ci05NjQ2LjA0OTgwNDcgLTYxMzAuODgzNDYzNSBtCi05NjQzLjA0OTgwNDcgLTYxMzAuODgzNDYzNSBsClMKUQpRClEKUQpxCnEKcQotOTg4My4wNDk4MDQ3IC02MTE1Ljk2Njc5NjkgMjEwNS4xMDAwOTc3IDMwIHJlClcgbgpxCjAgMC4yIDAuNCByZwotOTg4My4wNDk4MDQ3IC02MTE1Ljk2Njc5NjkgMjEwNS4xMDAwOTc3IDMwIHJlCmYKUQpRCnEKMSAxIDEgcmcKMSAwIDAgLTEgLTk4NTguMDQ5ODA0NyAtNjA5NS45NjY3OTY5IGNtCkJUCi9GNSAxMiBUZgowIFRyCihTRVJWSUNFIFRBWCBERVRBSUxTKSBUagpFVApRClEKUQpxCnEKcQpxCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTYwNTAuNDY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooU2VydmljZSBUYXggUmVnaXN0cmF0aW9uIE5vLikgVGoKRVQKUQpRCnEKcQpxCjAuNTAxOTYwOCAwLjUwMTk2MDggMC41MDE5NjA4IFJHCjEgdwotOTQ4Ny42MzM3ODkxIC02MDYzLjQ2Njc5NjkgODIxLjA0OTk4NzggMTcgcmUKUwpRCnEKcQpxCnEKLTk0ODcuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTk0ODcuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTk0MzIuOTY3MTE3MyAtNjA2Mi45NjY3OTY5IG0KLTk0MzIuOTY3MTE3MyAtNjA0Ni45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk0NjMuNzk5ODA0NyAtNjA1MC40NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihBKSBUagpFVApRClEKcQpxCi05NDMyLjQ2Njc5NjkgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05NDMyLjQ2Njc5NjkgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05Mzc4LjMwMDE0MDQgLTYwNjIuOTY2Nzk2OSBtCi05Mzc4LjMwMDE0MDQgLTYwNDYuOTY2Nzk2OSBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05NDA5LjEzMzc4OTEgLTYwNTAuNDY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooQSkgVGoKRVQKUQpRCnEKcQotOTM3Ny43OTk4MDQ3IC02MDYyLjk2Njc5NjkgNTQuNjY2NjcxOCAxNiByZQpXIG4KcQoxIDEgMSByZwotOTM3Ny43OTk4MDQ3IC02MDYyLjk2Njc5NjkgNTQuNjY2NjcxOCAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTMyMy42MzMxMzI5IC02MDYyLjk2Njc5NjkgbQotOTMyMy42MzMxMzI5IC02MDQ2Ljk2Njc5NjkgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTM1NC40NjY3OTY5IC02MDUwLjQ2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKEYpIFRqCkVUClEKUQpxCnEKLTkzMjMuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTkzMjMuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkyNjguOTY3MTE3MyAtNjA2Mi45NjY3OTY5IG0KLTkyNjguOTY3MTE3MyAtNjA0Ni45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkzMDAuMjk5ODA0NyAtNjA1MC40NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihDKSBUagpFVApRClEKcQpxCi05MjY4LjQ2Njc5NjkgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05MjY4LjQ2Njc5NjkgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MjE0LjMwMDE0MDQgLTYwNjIuOTY2Nzk2OSBtCi05MjE0LjMwMDE0MDQgLTYwNDYuOTY2Nzk2OSBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MjQ1LjEzMzc4OTEgLTYwNTAuNDY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooSykgVGoKRVQKUQpRCnEKcQotOTIxMy43OTk4MDQ3IC02MDYyLjk2Njc5NjkgNTQuNjY2NjcxOCAxNiByZQpXIG4KcQoxIDEgMSByZwotOTIxMy43OTk4MDQ3IC02MDYyLjk2Njc5NjkgNTQuNjY2NjcxOCAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotOTE1OS42MzMxMzI5IC02MDYyLjk2Njc5NjkgbQotOTE1OS42MzMxMzI5IC02MDQ2Ljk2Njc5NjkgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTE4OS45NjY3OTY5IC02MDUwLjQ2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKDcpIFRqCkVUClEKUQpxCnEKLTkxNTkuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTkxNTkuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTkxMDQuOTY3MTE3MyAtNjA2Mi45NjY3OTY5IG0KLTkxMDQuOTY3MTE3MyAtNjA0Ni45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkxMzUuMjk5ODA0NyAtNjA1MC40NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCigwKSBUagpFVApRClEKcQpxCi05MTA0LjQ2Njc5NjkgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlClcgbgpxCjEgMSAxIHJnCi05MTA0LjQ2Njc5NjkgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci05MDUwLjMwMDE0MDQgLTYwNjIuOTY2Nzk2OSBtCi05MDUwLjMwMDE0MDQgLTYwNDYuOTY2Nzk2OSBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MDgwLjYzMzc4OTEgLTYwNTAuNDY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooMSkgVGoKRVQKUQpRCnEKcQotOTA0OS43OTk4MDQ3IC02MDYyLjk2Njc5NjkgNTQuNjY2NjcxOCAxNiByZQpXIG4KcQoxIDEgMSByZwotOTA0OS43OTk4MDQ3IC02MDYyLjk2Njc5NjkgNTQuNjY2NjcxOCAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODk5NS42MzMxMzI5IC02MDYyLjk2Njc5NjkgbQotODk5NS42MzMxMzI5IC02MDQ2Ljk2Njc5NjkgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTAyNS45NjY3OTY5IC02MDUwLjQ2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKDYpIFRqCkVUClEKUQpxCnEKLTg5OTUuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY4MzMzNDQgMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTg5OTUuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDU0LjY4MzMzNDQgMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg5NDAuOTUwNDU0NyAtNjA2Mi45NjY3OTY5IG0KLTg5NDAuOTUwNDU0NyAtNjA0Ni45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5NzIuMjk5ODA0NyAtNjA1MC40NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihDKSBUagpFVApRClEKcQpxCi04OTQwLjQ1MDE5NTMgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlClcgbgpxCjEgMSAxIHJnCi04OTQwLjQ1MDE5NTMgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NTY1IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04ODg2LjI4MzUzODggLTYwNjIuOTY2Nzk2OSBtCi04ODg2LjI4MzUzODggLTYwNDYuOTY2Nzk2OSBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04OTE3LjExNjIxMDkgLTYwNTAuNDY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooUykgVGoKRVQKUQpRCnEKcQotODg4NS43ODMyMDMxIC02MDYyLjk2Njc5NjkgNTQuNjgzMzM0NCAxNiByZQpXIG4KcQoxIDEgMSByZwotODg4NS43ODMyMDMxIC02MDYyLjk2Njc5NjkgNTQuNjgzMzM0NCAxNiByZQpmClEKUQpxCjAuNTg4MjM1MyAwLjU5NjA3ODQgMC42MDM5MjE2IFJHCjEgdwotODgzMS41OTk4Njg4IC02MDYyLjk2Njc5NjkgbQotODgzMS41OTk4Njg4IC02MDQ2Ljk2Njc5NjkgbApTClEKcQowIDAgMCByZwoxIDAgMCAtMSAtODg2Mi45NTAxOTUzIC02MDUwLjQ2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKEQpIFRqCkVUClEKUQpxCnEKLTg4MzEuMDk5NjA5NCAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTg4MzEuMDk5NjA5NCAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKZgpRClEKcQowLjU4ODIzNTMgMC41OTYwNzg0IDAuNjAzOTIxNiBSRwoxIHcKLTg3NzYuOTMyOTM3NiAtNjA2Mi45NjY3OTY5IG0KLTg3NzYuOTMyOTM3NiAtNjA0Ni45NjY3OTY5IGwKUwpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg4MDcuMjY2NjAxNiAtNjA1MC40NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCigwKSBUagpFVApRClEKcQpxCi04Nzc2LjQzMzU5MzggLTYwNjIuOTY2Nzk2OSA1NC42ODMzMzQ0IDE2IHJlClcgbgpxCjEgMSAxIHJnCi04Nzc2LjQzMzU5MzggLTYwNjIuOTY2Nzk2OSA1NC42ODMzMzQ0IDE2IHJlCmYKUQpRCnEKMC41ODgyMzUzIDAuNTk2MDc4NCAwLjYwMzkyMTYgUkcKMSB3Ci04NzIyLjI1MDI1OTQgLTYwNjIuOTY2Nzk2OSBtCi04NzIyLjI1MDI1OTQgLTYwNDYuOTY2Nzk2OSBsClMKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NzUyLjU5OTYwOTQgLTYwNTAuNDY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooMCkgVGoKRVQKUQpRCnEKcQotODcyMS43NSAtNjA2Mi45NjY3OTY5IDU0LjY2NjY3MTggMTYgcmUKVyBuCnEKMSAxIDEgcmcKLTg3MjEuNzUgLTYwNjIuOTY2Nzk2OSA1NC42NjY2NzE4IDE2IHJlCmYKUQpRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg2OTcuNDE2OTkyMiAtNjA1MC40NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCigxKSBUagpFVApRClEKUQpRClEKUQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg2NDYuMDgzMDA3OCAtNjA1MC40NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihDYXRlZ29yeSkgVGoKRVQKUQpRCnEKcQpxCjAuNTAxOTYwOCAwLjUwMTk2MDggMC41MDE5NjA4IFJHCjEgdwotODQ4MC42MzM3ODkxIC02MDYzLjQ2Njc5NjkgNjc3LjE4MzM0OTYgMTcgcmUKUwpRCnEKcQpxCnEKLTg0ODAuMTMzNzg5MSAtNjA2Mi45NjY3OTY5IDY3Ni4xODMzNDk2IDE2IHJlClcgbgpxCjEgMSAxIHJnCi04NDgwLjEzMzc4OTEgLTYwNjIuOTY2Nzk2OSA2NzYuMTgzMzQ5NiAxNiByZQpmClEKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC04NDc4LjEzMzc4OTEgLTYwNDkuNDY2Nzk2OSBjbQpCVAovRjMgMTMgVGYKMCBUcgooOiBHZW5lcmFsIEluc3VyYW5jZSBTZXJ2aWNlcykgVGoKRVQKUQpRClEKUQpRClEKUQpRClEKUQpRCnEKcQpxCi05ODgzLjA0OTgwNDcgLTYwMzUuOTY2Nzk2OSAyMTA1LjEwMDA5NzcgMzAgcmUKVyBuCnEKMCAwLjIgMC40IHJnCi05ODgzLjA0OTgwNDcgLTYwMzUuOTY2Nzk2OSAyMTA1LjEwMDA5NzcgMzAgcmUKZgpRClEKcQoxIDEgMSByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC02MDE1Ljk2Njc5NjkgY20KQlQKL0Y1IDEyIFRmCjAgVHIKKERFQ0xBUkFUSU9OKSBUagpFVApRClEKUQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC01OTg1Ljk2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKEkvV2UgaGVyZWJ5IGNlcnRpZnkgdGhhdCB0aGUgcG9saWN5IHRvIHdoaWNoIHRoZSBjZXJ0aWZpY2F0ZSByZWxhdGVzIGFzIHdlbGwgYXMgdGhlIGNlcnRpZmljYXRlIG9mIGluc3VyYW5jZSBhcmUgaXNzdWVkIGluIGFjY29yZGFuY2Ugd2l0aCB0aGUgcHJvdmlzaW9uIG9mIGNoYXB0ZXIgWCwgWEkgb2YgTS5WLkFjdCAxOTg4LikgVGoKRVQKUQpRClEKcQpxCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTk4NTguMDQ5ODA0NyAtNTk1OC45NjY3OTY5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihJbiBXaXRuZXNzIHdoZXJlb2YgdGhpcyBQb2xpY3kgaGFzIGJlZW4gc2lnbmVkIGF0IFB1bmUgdGhpcyAxNS8wMy8yMDE3KSBUagpFVApRClEKUQpxCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTg1OC4wNDk4MDQ3IC01OTQwLjk2Njc5NjkgY20KQlQKL0YzIDExIFRmCjAgVHIKKEZvciBLb3RhayBNYWhpbmRyYSBHZW5lcmFsIEluc3VyYW5jZSBDb21wYW55IExpbWl0ZWQpIFRqCkVUClEKUQpRCnEKcQpxCnEKLTk4NTguMDQ5ODA0NyAtNTkyOS45NjY3OTY5IDIxMSA4NCByZQpXIG4KMjExIDAgMCAtODQgLTk4NTguMDQ5ODA0NyAtNTg0NS45NjY3OTY5IGNtCi9JNCBEbwpRClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTU4MjUuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooQXV0aG9yaXNlZCBTaWduYXRvcnkpIFRqCkVUClEKUQpRCnEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05ODU4LjA0OTgwNDcgLTU3OTIuOTY2Nzk2OSBjbQpCVAovRjMgMTEgVGYKMCBUcgooVGhpcyBkb2N1bWVudCBpcyBkaWdpdGFsbHkgc2lnbmVkLCBoZW5jZSBjb3VudGVyIHNpZ25hdHVyZSAvIHN0YW1wIGlzIG5vdCByZXF1aXJlZC4pIFRqCkVUClEKUQpRClEKUQpRClEKUQpRCnEKcQotOTg4My4wNDk4MDQ3IC01NzY4Ljk2Njc5NjkgMjEwNS4wNjY2NTA0IDEzMC4wOTk5OTA4IHJlClcgbgpxCjAuOCAwLjggMC44IHJnCi05ODgzLjA0OTgwNDcgLTU3NjguOTY2Nzk2OSAyMTA1LjA2NjY1MDQgMTMwLjA5OTk5MDggcmUKZgpRClEKcQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MTk5LjUxNjYwMTYgLTU3MTAuNDE2NTAzOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooQ0lOOiBVNjYwMDBNSDIwMTRQTEMyNjAyOTEsKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTg5MzYuNTE2NjAxNiAtNTcxMC40MTY1MDM5IGNtCkJUCi9GMyAxMSBUZgowIFRyCigyNyBCS0MsIEMgMjcsIEcgQmxvY2ssIEJhbmRyYSBLdXJsYSBDb21wbGV4LCBCYW5kcmEgRWFzdCwgTXVtYmFpIDQwMDA1MSwgTWFoYXJhc2h0cmEsIEluZGlhLikgVGoKRVQKUQpxCjAgMCAwIHJnCjEgMCAwIC0xIC05MjQ1LjAxNjYwMTYgLTU2ODguNDE2NTAzOSBjbQpCVAovRjMgMTEgVGYKMCBUcgooT2ZmaWNlOiA4dGggRmxvb3IsIFpvbmUgSVYsIEtvdGFrIEluZmluaXR5LCBCdWlsZGluZyBOby4yMSwgSW5maW5pdHkgSVQgUGFyaywgT2ZmIFdlc3Rlcm4gRXhwcmVzcyBIaWdod2F5LCBHZW5lcmFsIEFLIFZhaWR5YSBNYXJnLCBEaW5kb3NoaSwgTWFsYWRcKEVcKSwgTXVtYmFpIDQwMDA5Ny4gSW5kaWEuKSBUagpFVApRCnEKMCAwIDAgcmcKMSAwIDAgLTEgLTkxMDIuNTE2NjAxNiAtNTY2Ni40MTY1MDM5IGNtCkJUCi9GMyAxMSBUZgowIFRyCihUb2xsIEZyZWU6IDE4MDAgMjY2IDQ1NDUgRW1haWw6Y2FyZUBrb3Rhay5jb20gV2Vic2l0ZTogd3d3LmtvdGFrZ2VuZXJhbGluc3VyYW5jZS5jb20gSVJEQUkgUmVnLiBOby4gMTUyKSBUagpFVApRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTExMy4wMTY2MDE2IC01NzMyLjQxNjUwMzkgY20KQlQKL0Y1IDExIFRmCjAgVHIKKEtvdGFrIE1haGluZHJhIEdlbmVyYWwgSW5zdXJhbmNlIENvbXBhbnkgTGltaXRlZCBcKEZvcm1lcmx5IEtvdGFrIE1haGluZHJhIEdlbmVyYWwgSW5zdXJhbmNlIExpbWl0ZWRcKSkgVGoKRVQKUQpRCnEKcQowIDAgMCByZwoxIDAgMCAtMSAtOTAzMi41MTY2MDE2IC01NzEwLjQxNjUwMzkgY20KQlQKL0Y1IDExIFRmCjAgVHIKKFJlZ2lzdGVyZWQgT2ZmaWNlOikgVGoKRVQKUQpRClEKUQpRClEKUQpRClEKCmVuZHN0cmVhbSBlbmRvYmoKCjE3IDAgb2JqIDw8CiAgL0NvbnRlbnRzIDE2IDAgUgogIC9QYXJlbnQgMiAwIFIKICAvTWVkaWFCb3ggWyAwIDAgMjEwNS4xMDAwOTc3IDExMTkuMjMzMjkxNiBdCiAgL1R5cGUgL1BhZ2UKICAvUHJvY1NldCBbIC9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUkgXQogIC9SZXNvdXJjZXMgPDwKICAgIC9Gb250IDw8CiAgICAgIC9GMiA2IDAgUgogICAgICAvRjUgOSAwIFIKICAgICAgL0YzIDcgMCBSCiAgICA+PgogICAgL0V4dEdTdGF0ZSA8PD4+CiAgICAvWE9iamVjdCA8PAogICAgICAvSTEgNSAwIFIKICAgICAgL0k0IDggMCBSCiAgICA+PgogICAgL1BhdHRlcm4gPDw+PgogICAgL1NoYWRpbmcgPDw+PgogID4+CiAgL0Fubm90cyBbIF0KPj4gZW5kb2JqCgp4cmVmCjAgMTgKMDAwMDAwMDAwMCA2NTUzNSBmIAowMDAwMDAwMDIyIDAwMDAwIG4gCjAwMDAwMDAwNzYgMDAwMDAgbiAKMDAwMDAwMDE2MyAwMDAwMCBuIAowMDAwMDA1OTE0IDAwMDAwIG4gCjAwMDAwMDYyOTMgMDAwMDAgbiAKMDAwMDAxNDMzNyAwMDAwMCBuIAowMDAwMDE0NDE2IDAwMDAwIG4gCjAwMDAwMTQ0OTMgMDAwMDAgbiAKMDAwMDAyMTEwOSAwMDAwMCBuIAowMDAwMDIxMTkxIDAwMDAwIG4gCjAwMDAwMjI5NjggMDAwMDAgbiAKMDAwMDAyMzM1MCAwMDAwMCBuIAowMDAwMDQ3NjU0IDAwMDAwIG4gCjAwMDAwOTcwODEgMDAwMDAgbiAKMDAwMDA5NzQ2MyAwMDAwMCBuIAowMDAwMTExOTA5IDAwMDAwIG4gCjAwMDAxMzA5OTYgMDAwMDAgbiAKCnRyYWlsZXIKPDwKICAvU2l6ZSAxOAogIC9Sb290IDEgMCBSCiAgL0luZm8gPDwKICAgIC9Qcm9kdWNlciAoS2VuZG8gVUkgUERGIEdlbmVyYXRvciB2LjIwMTYuMy45MTQpCiAgICAvVGl0bGUgKCkKICAgIC9BdXRob3IgKCkKICAgIC9TdWJqZWN0ICgpCiAgICAvS2V5d29yZHMgKCkKICAgIC9DcmVhdG9yIChLZW5kbyBVSSBQREYgR2VuZXJhdG9yIHYuMjAxNi4zLjkxNCkKICAgIC9DcmVhdGlvbkRhdGUgKEQ6MjAxNzAzMTUxMzQ3MzNaKQogID4+Cj4+CgpzdGFydHhyZWYKMTMxMzc4CiUlRU9GCg==";

//				File file = new File("D:/abc.pdf");
//				FileInputStream fileInputStream;
//				byte[] data = null;
//				byte[] finalData = null;
//				ByteArrayOutputStream byteArrayOutputStream = null;
//
//				try {
//					fileInputStream = new FileInputStream(file);
//					data = new byte[(int) file.length()];
//					finalData = new byte[(int) file.length()];
//					byteArrayOutputStream = new ByteArrayOutputStream();
//
//					fileInputStream.read(data);
//					byteArrayOutputStream.write(data);
//					finalData = byteArrayOutputStream.toByteArray();
//
//					fileInputStream.close();
//
//				} catch (FileNotFoundException e) {
//					// LOGGER.info("File not found" + e);
//				} catch (IOException e) {
//					// LOGGER.info("IO exception" + e);
//				}
//				System.out.println("finalData = " + finalData.toString());

				// byte[] data = null;
				// try {
				//// data = strPolicyPdf.getBytes("UTF-8");
				// } catch (UnsupportedEncodingException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

				ArrayList<String> pdfArr = new ArrayList<>();
				pdfArr.add(pojo.getAddress());
				pdfArr.add(pojo.getBasicOwnDmg());
				pdfArr.add(pojo.getBnsPerc());
				pdfArr.add(pojo.getBscTPPrem());
				pdfArr.add(pojo.getCngLpgKit());
				pdfArr.add(pojo.getCubicCapacity());
				pdfArr.add(pojo.getCvrNoteNo());
				pdfArr.add(pojo.getElecAccsrFitd());
				pdfArr.add(pojo.getEmail());
				pdfArr.add(pojo.getEngNo());
				pdfArr.add(pojo.getFromDate());
				pdfArr.add(pojo.getHypothecatedTo());
				pdfArr.add(pojo.getIdvVehcl());
				pdfArr.add(pojo.getManufacturer());
				pdfArr.add(pojo.getMobile());
				pdfArr.add(pojo.getModel());
				pdfArr.add(pojo.getName());
				pdfArr.add(pojo.getNeAccsrsFitd());
				pdfArr.add(pojo.getPhone());
				pdfArr.add(pojo.getPolIssOfc());
				pdfArr.add(pojo.getPolIssOn());
				pdfArr.add(pojo.getRsa());
				pdfArr.add(pojo.getRtoLoc());
				pdfArr.add(pojo.getSeatingCapacity());
				pdfArr.add(pojo.getToDate());
				pdfArr.add(pojo.getTotalLibPrem());
				pdfArr.add(pojo.getTotalOwnDmgPrem());
				pdfArr.add(pojo.getTotalPkg());
				pdfArr.add(pojo.getTotalValVhcl());
				pdfArr.add(pojo.getTrailer());
				pdfArr.add(pojo.getVariant());
				pdfArr.add(pojo.getVehChassisNo());
				pdfArr.add(pojo.getVehRegNo());
				pdfArr.add(pojo.getYrOfManfctr());

//				ExportReport exRep = new ExportReport();
//				 byte[] pdfBa = exRep.generateReport("Kotakreport.jrxml", "pdf", pdfArr, null, res, null);
//
//				 JSONObject jn = new JSONObject(strXml);
//				 ClsSignPDF str = service.getSignPolicyPDF(jn.getString("USERID"),jn.getString("PASSWORD"), jn.getString("ProductType"),hashmapPartner.get("PolicyNo"),hashmapPartner.get("ProcessTime"),hashmapPartner.get("TransactionID"),jn.getString("TransactionReference"),jn.getString("CustomerName"), pdfBa);
//				
//				 JAXBElement<byte[]> spdf = str.getPolicyPDF();
//				 byte[] b = spdf.getValue();
//				// System.out.println("b = = = = = = " + b.toString());
//				 OutputStream out;
//				 try {
//				 out = new FileOutputStream("D:/SignedPolicyKotak.pdf");
//				 out.write(b);
//				
//				 out.close();
//				 System.out.println("write success");
//				 } catch (Exception e) {
//				 System.out.println(e);
//				 }
//
//				 System.out.println("Signed PDF = = = " + str.getPolicyPDF());

				String xmlpropsl = xmlFileProposal("[" + strXml + "]");

				// System.out.println("xmlpropsl =============)" + xmlpropsl);
				//
				// String rtrnXML = PostInfoToAPIXml(xmlpropsl,
				// "SaveProposalPrivateCarGeneric");
				// System.out.println("Premium in partner ===== " + rtrnXML);

				// HashMap<String, String> hshmp = readResponse(rtrnXML);
				
				HashMap<String, String> hshmp = readResponse(xmlpropsl);
				
				System.out.println("hshmp '''''''' " + hshmp);
				
				String procedureName2 = "PR_PROPOSAL";
//				 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
//				 Integer mtrGrpRespGicId = new Integer(Integer.parseInt(motorGroupResponseGicId));
				 Integer value2 = new Integer(1);
				// System.out.println("valu ''''' " + value);
				// System.out.println("hashmap //////// " +
//				 hashmapKotak.toString());
//				 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
//				 integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(savebn.getMtrGrpRespGrpId(), savebn.getMtrGrpRespGicId(), savebn.getMotorGroupResponseSessionId(), value2, hshmp, savebn.getIPAddress(), savebn.getUserId(), savebn.getBranchId(), savebn.getUserDesc(), procedureName2);


				Gson gs = new Gson();
				String jstr = gs.toJson(hshmp);

				System.out.println("jstr '''''''' " + jstr);
				
				// hmInTxtFile(jstr);

				// System.out.println("{{{{{{{{{ hshmp }}}}} " + hshmp);
				
				break;

			case "Proposal":

				if (jsonN.contains("}{")) {
					jsonN = jsonN.replace("}{", ",");
				}

				System.out.println("jsonNames --]]]]]]]]] " + jsonN);
				// return Premium;
				// String xmlData = xmlFile("["+jsonN+"]");
				// Premium = PostInfoToAPIXml(xmlFileProposal("["+jsonN+"]"),
				// "SaveProposalPrivateCarGeneric");
				// Premium = PostInfoToAPI(jsonN,
				// "CalculatePremiumPrivateCarGeneric");
				System.out.println("Premium in proposal ===== " + Premium);

			default:
				break;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Premium = "[" + Premium + "]";
		Premium = Premium+jsonNames;
		if (Premium.contains("}{")) {
			Premium = Premium.replace("}{", ",");
		}
		
		System.out.println("LAST PREMIUM  - - - " + Premium);
		return "[" + Premium + "]";
	}
	
	
	

	// public void hmInTxtFile(String js){
	// //write to file : "fileone"
	// try{
	// File fileOne=new File("D:/fileone");
	// FileOutputStream fos=new FileOutputStream(fileOne);
	// ObjectOutputStream oos=new ObjectOutputStream(fos);
	//
	// oos.writeObject(js);
	// oos.flush();
	// oos.close();
	// fos.close();
	// }catch(Exception e){}
	// }

	@RequestMapping(value = "user/kotakPayment", method = RequestMethod.GET)
	public org.springframework.web.servlet.ModelAndView kotakPayment(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String paymentResponse = null;

		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
        Date time = new Date();
        System.out.println(timeFormat.format(time));
        String t = timeFormat.format(time).toString();
        System.out.println("t = " + t); 

        long millis = System.currentTimeMillis() % 1000;
        System.out.println("millis = " + millis);
        
        DateFormat dateFormat = new SimpleDateFormat("DDYYMM");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String sysDate = dateFormat.format(date);

//        for (int i = 1; i <= 10; i++) {

            String s = String.format("%04d", Integer.parseInt(t));
            String m = String.format("%03d", millis);
            String txnId = s + sysDate + m;
            System.out.println("s = " + txnId);
		
		
		
		String amount = request.getParameter("amount");
		String productInfo = request.getParameter("productinfo");
		String SURL = request.getParameter("SURL");
		String FURL = request.getParameter("FURL");
		String CURL = request.getParameter("CURL");
		String key = request.getParameter("key");
		kbean.setSalt(request.getParameter("salt"));
		kbean.setTxnid(txnId);
		String FirstName = request.getParameter("FirstName");
		String email = request.getParameter("Email");
		String Phone = request.getParameter("Phone");
		kbean.setQuotationNumber(request.getParameter("quotationNumber"));
		kbean.setCustomerID(request.getParameter("customerID"));
		kbean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
		kbean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
		kbean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
		kbean.setUserId(request.getParameter("userId"));
		kbean.setUserDesc(request.getParameter("userDesc"));
		kbean.setBranchId(request.getParameter("branchId"));
		
		savebn.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
		savebn.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
		savebn.setUserId(request.getParameter("userId"));
		savebn.setUserDesc(request.getParameter("userDesc"));
		savebn.setBranchId(request.getParameter("branchId"));
		savebn.setIPAddress(request.getParameter("IPAddress"));
		
		savebn.setMtrGrpRespGrpId(new Integer(savebn.getMotorGroupResponseGroupId()));
		System.out.println("savebn.getMotorGroupResponseGroupId() = " + savebn.getMotorGroupResponseGroupId());
		System.out.println("savebn.getMotorGroupResponseGicId() = " + savebn.getMotorGroupResponseGicId());

		System.out.println("savebn.getMtrGrpRespGrpId() = " + savebn.getMtrGrpRespGrpId() + "savebn.getMtrGrpRespGicId() = " + savebn.getMtrGrpRespGicId() + "savebn.getMotorGroupResponseSessionId() = " + savebn.getMotorGroupResponseSessionId() + "payRequest = " + payRequest + "savebn.getIPAddress() = " + savebn.getIPAddress() + "savebn.getUserId() = " + savebn.getUserId() + "savebn.getBranchId() = " + savebn.getBranchId() + "savebn.getUserDesc() = " + savebn.getUserDesc() );

		
		 savebn.setMtrGrpRespGicId(new Integer(savebn.getMotorGroupResponseGicId()));
		
//		String motorGroupResponseGroupId = request.getParameter("motorGroupResponseGroupId");
//		String motorGroupResponseSessionId = request.getParameter("motorGroupResponseSessionId");
//		String motorGroupResponseGicId = request.getParameter("motorGroupResponseGicId");
//		String userId = request.getParameter("userId");
//		String userDesc = request.getParameter("userDesc");
//		String branchId = request.getParameter("branchId");
//		String IPAddress = request.getParameter("IPAddress"); 

		String passToHash = key + "|" + kbean.getTxnid() + "|" + amount + "|" + productInfo + "|" + FirstName + "|"
				+ email + "|||||||||||" + kbean.getSalt();
		System.out.println("passToHash ===============> " + passToHash);

		BufferedWriter output = null;
		File file = null;
		try {
			Properties props = null;
			try {
				props = Utils.readProperties("datasource.properties");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			String sourceFileName = props.getProperty("server.path") + "/hash/shaString.txt";

			file = new File(sourceFileName);
			 if (file.exists()) {
			 file.delete();
			 }
			 if (!file.exists()) {
			 file.createNewFile();
			 }
			 file.createNewFile();
			output = new BufferedWriter(new FileWriter(file));
			output.write(passToHash);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}  
		}
		
		
		kbean.setHash(kotakDao.getSha512());

		payRequest = new HashMap<>();
		payRequest.put("txnid", kbean.getTxnid());
		payRequest.put("amount", amount);
		payRequest.put("productInfo", productInfo);
		payRequest.put("SURL", SURL);
		payRequest.put("FURL", FURL);
		payRequest.put("CURL", CURL);
		payRequest.put("key", key);
		payRequest.put("hash", kbean.getHash());
		System.out.println("kbean.getHash() = " + kbean.getHash());
		payRequest.put("FirstName", FirstName); 
		payRequest.put("email", email);
		payRequest.put("Phone", Phone);
		// payRequest.put("quotationNumber", quotationNumber);
		payRequest.put("customerID", kbean.getCustomerID());
		// payRequest.put("actionUrl", actionUrl);

		
//		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
//		 Integer mtrGrpRespGicId = new Integer(Integer.parseInt(motorGroupResponseGicId));
//		 Integer value = new Integer(2);
//		// System.out.println("valu ''''' " + value);
//		// System.out.println("hashmap //////// " + 
////		 hashmapKotak.toString());
////		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
//		 integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(mtrGrpRespGrpId, mtrGrpRespGicId, motorGroupResponseSessionId, value, payRequest, IPAddress, userId, branchId, userDesc, procedureName);

		System.out.println("payRequest" + payRequest);
		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView("common/KotakGicPayment");
		andView.addObject("data", payRequest);
		return andView; 

	}

	@RequestMapping(value = "/KotakPaymentStatus", method = RequestMethod.POST)
	public org.springframework.web.servlet.ModelAndView TransactionStatusPost(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String jsonResponse = null;

		kbean.setMihpayid(request.getParameter("mihpayid"));
		String mode = request.getParameter("mode");
		String status = request.getParameter("status");
		String key = request.getParameter("key");
		String txnid = request.getParameter("txnid");
//		String txnid = request.getParameter("txnid");
		String amount = request.getParameter("amount");
		String hashResp = request.getParameter("Hash");
		String PG_TYPE = request.getParameter("PG_TYPE");
		String error_code = request.getParameter("Error");
		String bank_ref_num = request.getParameter("bank_ref_num");
		String unmappedstatus = request.getParameter("unmappedstatus");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstname");
		String productinfo = request.getParameter("productinfo");

		System.out.println("mihpayid-" + kbean.getMihpayid() + "mode-" + mode + "key-" + key + "amount-" + amount
				+ "email- " + email + "firstName- " + firstName + "PG_TYPE" + PG_TYPE + "error_code" + error_code);
		System.out.println("txnid-" + txnid + "status-" + status + "hashResp- " + hashResp + "unmappedstatus- "
				+ unmappedstatus + "bank_ref_num" + bank_ref_num);

		String passToHash = kbean.getSalt() + "|" + status + "|||||||||||" + "|" + email + "|" + firstName + "|"
				+ productinfo + "|" + amount + "|" + txnid + "|" + key;
		System.out.println("passToHashResp ===============> " + passToHash);

		
		
		BufferedWriter output = null;
		File file = null;
		try {
			Properties props = null;
			try {
				props = Utils.readProperties("datasource.properties");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			String sourceFileName = props.getProperty("server.path") + "/hash/shaString.txt";

			file = new File(sourceFileName);
			 if (file.exists()) {
			 file.delete();
			 }
			 if (!file.exists()) {
			 file.createNewFile();
			 }
			 file.createNewFile();
			output = new BufferedWriter(new FileWriter(file));
			output.write(passToHash);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
//		BufferedWriter output = null;
//		try {
//
//			Properties props = null;
//			try {
//				props = Utils.readProperties("datasource.properties");
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//
//			String sourceFileName = props.getProperty("server.path") + "/hash/SHAstring.txt";
//
//			File file = new File(sourceFileName);
//			output = new BufferedWriter(new FileWriter(file));
//			output.write(passToHash);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}

		String hashRes = kotakDao.getSha512();
		System.out.println("hashRes  = " + hashRes);

		HashMap<String, String> payResponse = new HashMap<>();

		if (hashRes.equalsIgnoreCase(kbean.getHash())) {
			System.out.println("<<<<<<<<<<<<<<<<<<<< SUCCESS >>>>>>>>>>>>>>>>>>>>>>");
		}

		payResponse.put("mihpayid", kbean.getMihpayid());
		payResponse.put("mode", mode);
		payResponse.put("status", status);
		payResponse.put("key", key);
		payResponse.put("txnid", txnid);
		payResponse.put("hashResp", hashResp);
		payResponse.put("amount", amount);
		payResponse.put("PG_TYPE", PG_TYPE);
		payResponse.put("error_code", error_code);
		payResponse.put("bank_ref_num", bank_ref_num);
		payResponse.put("unmappedstatus", unmappedstatus);
		
		// bean.setUserId(userId);
		// bean.setUserDesc(userDesc);
		// bean.setBranchId(branchId);
		// bean.setGroupId(motorGroupResponseGroupId);
		// bean.setSessionId(motorGroupResponseSessionId);
		// bean.setGicId(motorGroupResponseGicId);
		// bean.setResponseType("2");
		// bean.setIpAddress(IPAddress);
		System.out.println("payResponse---" + payResponse);
		if (status.equalsIgnoreCase("success")) {
			// policyCreation(payResponse);
		} else {
			// payResponse.put("customerID", customerID);
			// payResponse.put("QuotationNumber", quotationNumber);
			// payResponse.put("proposalNo", quotationNumber);
			//// saveTransData(payResponse);
			// MotorResponseBean objResult =
			// integrationSaveResponseDao.saveRelianceProposalDataDump(payResponse,
			// bean);
			//
			// MotorResponseBean objResult1 =
			// integrationSaveResponseDao.saveRelianceProposalDataProcess(bean);
		}

		String procedureName = "PR_PROPOSAL";  
		Integer value = new Integer(2);
		
		integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(savebn.getMtrGrpRespGrpId(), savebn.getMtrGrpRespGicId(), savebn.getMotorGroupResponseSessionId(), value, payRequest, savebn.getIPAddress(), savebn.getUserId(), savebn.getBranchId(), savebn.getUserDesc(), procedureName);

		
		System.out.println("In transaction status");
		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView(
				"common/KotakPaymentResponse");
		andView.addObject("data", payResponse);
		return andView;

	}

	private String xmlFilePartner(String jsonN) {
		String xx = "";
		Document document2;
		org.w3c.dom.Document document = null;

		JSONArray jsonarray;
		try {
			jsonarray = new JSONArray(jsonN);

			System.out.println("jsonarray" + jsonarray);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonResult = jsonarray.getJSONObject(i);

				org.jdom2.Element rootelement = new org.jdom2.Element("PartnerIntegration");
				document2 = new Document(rootelement);

				org.jdom2.Element UserID = new org.jdom2.Element("UserID");
				UserID.setAttribute("type", "String");
				UserID.setText(jsonResult.getString("USERID").trim());

				org.jdom2.Element Password = new org.jdom2.Element("Password");
				Password.setAttribute("type", "String");
				Password.setText(jsonResult.getString("PASSWORD").trim());

				org.jdom2.Element ProductType = new org.jdom2.Element("ProductType");
				ProductType.setAttribute("type", "String");
				ProductType.setText(jsonResult.getString("ProductType").trim());

				org.jdom2.Element TransactionType = new org.jdom2.Element("TransactionType");
				TransactionType.setAttribute("type", "String");
				TransactionType.setText(jsonResult.getString("TransactionType").trim());

				org.jdom2.Element TransactionID = new org.jdom2.Element("TransactionID");
				TransactionID.setAttribute("type", "String");
				TransactionID.setText(jsonResult.getString("TransactionID").trim());

				org.jdom2.Element PolicyNo = new org.jdom2.Element("PolicyNo");
				PolicyNo.setAttribute("type", "String");
				PolicyNo.setText(jsonResult.getString("POLICYNO").trim());

				org.jdom2.Element ProposalDate = new org.jdom2.Element("ProposalDate");
				ProposalDate.setAttribute("type", "date");
				ProposalDate.setText(jsonResult.getString("ProposalDate").trim());

				org.jdom2.Element ProposalTime = new org.jdom2.Element("ProposalTime");
				ProposalTime.setAttribute("type", "time");
				ProposalTime.setText(jsonResult.getString("ProposalTime").trim());

				org.jdom2.Element PolicyStartDate = new org.jdom2.Element("PolicyStartDate");
				PolicyStartDate.setAttribute("type", "datetime");

				String psd = jsonResult.getString("POLICY_STARTDATE").trim();
				Date dateFormat = new SimpleDateFormat("dd/mm/yyyy").parse(psd);
				String policyStrDt = new SimpleDateFormat("YYYY-MM-dd").format(dateFormat);
				System.out.println("policy Srt date = " + policyStrDt);

				PolicyStartDate.setText(policyStrDt);

				org.jdom2.Element PolicyEndDate = new org.jdom2.Element("PolicyEndDate");
				PolicyEndDate.setAttribute("type", "date");

				String ped = jsonResult.getString("POLICY_ENDDATE").trim();
				Date dateFor = new SimpleDateFormat("dd/mm/yyyy").parse(ped);
				String policyEndDt = new SimpleDateFormat("YYYY-MM-dd").format(dateFor);
				System.out.println("policy end date = " + policyEndDt);

				PolicyEndDate.setText(policyEndDt);

				org.jdom2.Element ProposerType = new org.jdom2.Element("ProposerType");
				ProposerType.setAttribute("type", "String");
				ProposerType.setText(jsonResult.getString("ProposerType").trim());

				org.jdom2.Element CustomerName = new org.jdom2.Element("CustomerName");
				CustomerName.setAttribute("type", "String");
				CustomerName.setText(jsonResult.getString("CustomerName").trim());

				org.jdom2.Element EngineNos = new org.jdom2.Element("EngineNos");
				EngineNos.setAttribute("type", "String");
				EngineNos.setText(jsonResult.getString("EngineNo").trim());

				org.jdom2.Element ChassisNos = new org.jdom2.Element("ChassisNos");
				ChassisNos.setAttribute("type", "String");
				ChassisNos.setText(jsonResult.getString("ChasisNo").trim());

				org.jdom2.Element Make = new org.jdom2.Element("Make");
				Make.setAttribute("type", "String");
				Make.setText(jsonResult.getString("Make").trim());

				org.jdom2.Element Model = new org.jdom2.Element("Model");
				Model.setAttribute("type", "String");
				Model.setText(jsonResult.getString("Model").trim());

				org.jdom2.Element Variant = new org.jdom2.Element("Variant");
				Variant.setAttribute("type", "String");
				Variant.setText(jsonResult.getString("Variant").trim());

				org.jdom2.Element IDV = new org.jdom2.Element("IDV");
				IDV.setAttribute("type", "int");
				IDV.setText(jsonResult.getString("IDV").trim());

				org.jdom2.Element TotalA = new org.jdom2.Element("TotalA");
				TotalA.setAttribute("type", "int");
				String ta = jsonResult.getString("TotalA").trim();
				float fta = Float.parseFloat(ta);
				int ita = (int) fta;
				TotalA.setText("" + ita);

				org.jdom2.Element TotalB = new org.jdom2.Element("TotalB");
				TotalB.setAttribute("type", "int");
				TotalB.setText(jsonResult.getString("TotalB").trim());

				org.jdom2.Element GrossPremium = new org.jdom2.Element("GrossPremium");
				GrossPremium.setAttribute("type", "int");
				String s = jsonResult.getString("GrossPremium").trim();
				float f = Float.parseFloat(s);
				int gp = (int) f;
				GrossPremium.setText(gp + "");

				org.jdom2.Element ServiceORSalesTax = new org.jdom2.Element("ServiceORSalesTax");
				ServiceORSalesTax.setAttribute("type", "int");
				String ss = jsonResult.getString("ServiceORSalesTax").trim();
				float ff = Float.parseFloat(ss);
				int sos = (int) ff;
				ServiceORSalesTax.setText("" + sos);

				org.jdom2.Element NetPremium = new org.jdom2.Element("NetPremium");
				NetPremium.setAttribute("type", "int");
				String snp = jsonResult.getString("NetPremium").trim();
				float fnp = Float.parseFloat(snp);
				int np = (int) fnp;
				NetPremium.setText("" + np);

				org.jdom2.Element TransactionReference = new org.jdom2.Element("TransactionReference");
				TransactionReference.setAttribute("type", "int");
				TransactionReference.setText(jsonResult.getString("TransactionReference").trim());

				org.jdom2.Element TransactionDate = new org.jdom2.Element("TransactionDate");
				TransactionDate.setAttribute("type", "date");
				TransactionDate.setText(jsonResult.getString("TransactionDate").trim());

				org.jdom2.Element TransactionAmount = new org.jdom2.Element("TransactionAmount");
				TransactionAmount.setAttribute("type", "int");
				TransactionAmount.setText(jsonResult.getString("TransactionAmount").trim());

				org.jdom2.Element Prefix = new org.jdom2.Element("Prefix");
				Prefix.setText(jsonResult.getString("Prefix").trim());

				org.jdom2.Element VehicleRegistrationNumber = new org.jdom2.Element("VehicleRegistrationNumber");
				VehicleRegistrationNumber.setAttribute("type", "String");
				VehicleRegistrationNumber.setText(jsonResult.getString("VehicleRegistrationNumber").trim());

				org.jdom2.Element BiFuelKit = new org.jdom2.Element("BiFuelKit");
				BiFuelKit.setAttribute("type", "String");
				BiFuelKit.setText(jsonResult.getString("BiFuelKit").trim());

				org.jdom2.Element BiFuelKitIDV = new org.jdom2.Element("BiFuelKitIDV");
				BiFuelKitIDV.setAttribute("type", "int");
				BiFuelKitIDV.setText(jsonResult.getString("BiFuelKitIDV").trim());

				org.jdom2.Element NCB = new org.jdom2.Element("NCB");
				NCB.setAttribute("type", "int");
				NCB.setText(jsonResult.getString("strNCBPercentage").trim());

				org.jdom2.Element LegalLiabilityPaidDriver = new org.jdom2.Element("LegalLiabilityPaidDriver");
				LegalLiabilityPaidDriver.setAttribute("type", "String");
				LegalLiabilityPaidDriver
						.setText(jsonResult.getString("WiderLegalLiabilityToPaidDriverCleanerConductorIMT28").trim());

				org.jdom2.Element ZeroDepreciation = new org.jdom2.Element("ZeroDepreciation");
				ZeroDepreciation.setAttribute("type", "String");
				ZeroDepreciation.setText(jsonResult.getString("ZeroDepreciation").trim());

				org.jdom2.Element EngineProtector = new org.jdom2.Element("EngineProtector");
				EngineProtector.setAttribute("type", "String");
				EngineProtector.setText(jsonResult.getString("EngineProtector").trim());

				org.jdom2.Element ReturnToInvoice = new org.jdom2.Element("ReturnToInvoice");
				ReturnToInvoice.setAttribute("type", "String");
				ReturnToInvoice.setText(jsonResult.getString("ReturnToInvoice").trim());

				org.jdom2.Element PAUnnamedPassengerSI = new org.jdom2.Element("PAUnnamedPassengerSI");
				PAUnnamedPassengerSI.setAttribute("type", "String");
				PAUnnamedPassengerSI.setText(jsonResult.getString("PAUnnamedPassengerSI").trim());

				org.jdom2.Element Consumables_Addon = new org.jdom2.Element("Consumables_Addon");
				Consumables_Addon.setAttribute("type", "String");
				Consumables_Addon.setText(jsonResult.getString("Consumables_Addon").trim());

				org.jdom2.Element RoadsideAssistance = new org.jdom2.Element("RoadsideAssistance");
				RoadsideAssistance.setAttribute("type", "String");
				RoadsideAssistance.setText(jsonResult.getString("RoadsideAssistance").trim());

				org.jdom2.Element IMTNos = new org.jdom2.Element("IMTNos");
				IMTNos.setAttribute("type", "String");
				IMTNos.setText(jsonResult.getString("IMTNos").trim());

				org.jdom2.Element Additional_Info = new org.jdom2.Element("Additional_Info");
				Additional_Info.setAttribute("type", "String");
				Additional_Info.setText("N/A");

				rootelement.addContent(UserID);
				rootelement.addContent(Password);
				rootelement.addContent(ProductType);
				rootelement.addContent(TransactionType);
				rootelement.addContent(TransactionID);
				rootelement.addContent(PolicyNo);
				rootelement.addContent(ProposalDate);
				rootelement.addContent(ProposalTime);
				rootelement.addContent(PolicyStartDate);
				rootelement.addContent(PolicyEndDate);
				rootelement.addContent(ProposerType);
				rootelement.addContent(CustomerName);
				rootelement.addContent(EngineNos);
				rootelement.addContent(ChassisNos);
				rootelement.addContent(Make);
				rootelement.addContent(Model);
				rootelement.addContent(Variant);
				rootelement.addContent(IDV);
				rootelement.addContent(TotalA);
				rootelement.addContent(TotalB);
				rootelement.addContent(GrossPremium);
				rootelement.addContent(ServiceORSalesTax);
				rootelement.addContent(NetPremium);
				rootelement.addContent(TransactionReference);
				rootelement.addContent(TransactionDate);
				rootelement.addContent(TransactionAmount);
				rootelement.addContent(Prefix);
				rootelement.addContent(VehicleRegistrationNumber);
				rootelement.addContent(BiFuelKit);
				rootelement.addContent(BiFuelKitIDV);
				rootelement.addContent(NCB);
				rootelement.addContent(LegalLiabilityPaidDriver);
				rootelement.addContent(ZeroDepreciation);
				rootelement.addContent(EngineProtector);
				rootelement.addContent(ReturnToInvoice);
				rootelement.addContent(PAUnnamedPassengerSI);
				rootelement.addContent(Consumables_Addon);
				rootelement.addContent(RoadsideAssistance);
				rootelement.addContent(IMTNos);
				rootelement.addContent(Additional_Info);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xx = xmlOutput.outputString(document2);
				try {
					xmlOutput.output(document2, new FileWriter("D:\\getPartnerSoapRequest.xml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return xx;
	}

	private HashMap<String, String> readResponse(String str) {
		// System.out.println("Inside read response method " + str);
		data = new HashMap();
		HashMap tax;
		HashMap tax1;
		HashMap tax2;
		HashMap tax3;
		HashMap tax4;

		ArrayList coverlist = new ArrayList<>();
		try {
			data.clear();
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(new ByteArrayInputStream(str.getBytes()));
			// -------------------------------------------------------------------
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			String xt = xmlOutput.outputString(document);

			xmlOutput.output(document, new FileWriter(path + "\\respfile.xml"));
			// -----------------------------------------------------------
			org.jdom2.Element root = document.getRootElement();
			List list = root.getChildren();
//			System.out.println("LIST=" + list);
			Iterator itr = list.iterator();
			data.put("Company", "KOTAK");
			int i = 0, x = 1;
			while (itr.hasNext()) {
				org.jdom2.Element s = (org.jdom2.Element) itr.next();
				List lst = s.getChildren();
				Iterator iterator = lst.iterator();
//				System.out.println("lst=" + lst);
				HashMap nonElecAcc = new HashMap();
				HashMap ElecAcc = new HashMap();
				HashMap nmdPsngrNomDet = new HashMap();
				HashMap ownrDvrNomDet = new HashMap();
				HashMap trailDetSI = new HashMap();
				HashMap bscTPinclTPPDprem = new HashMap();
				HashMap rskVehBsVal = new HashMap();
				HashMap ownDmg = new HashMap();
				HashMap engPtrct = new HashMap();
				HashMap rtrnToInvc = new HashMap();
				HashMap cnsmblCvr = new HashMap();
				HashMap lglLibPdDvrClnrCndctor = new HashMap();
				HashMap lglLibEmpClnrCndctor = new HashMap();
				HashMap rsa = new HashMap();
				HashMap rallTP = new HashMap();
				HashMap depCvr = new HashMap();
				HashMap rallOD = new HashMap();
				HashMap libSolSailAirman = new HashMap();
				HashMap geoExTP = new HashMap();
				HashMap geoExOD = new HashMap();
				HashMap ownrDrvr = new HashMap();
				HashMap cngKitTP = new HashMap();
				HashMap trailerOD = new HashMap();
				HashMap trailerTP = new HashMap();
				HashMap rskTrailer = new HashMap();
				HashMap elecAccOD = new HashMap();
				HashMap elecalAccOD = new HashMap();
				HashMap cngLpgKit = new HashMap();
				HashMap nmdPACvr = new HashMap();
				HashMap unmdPACvr = new HashMap();
				HashMap pdDvrPACvr = new HashMap();
				nonElecAcc.clear();
				ElecAcc.clear();
				nmdPsngrNomDet.clear();
				
				if (!lst.isEmpty()) {
					int j = 0;
					int k = 0;
					int l = 0;

					while (iterator.hasNext()) {

						org.jdom2.Element rncv = (org.jdom2.Element) iterator.next();
						// System.out.println("rncv--" + rncv);
						List ls = rncv.getChildren();
						Iterator itra = ls.iterator();
						if (!(ls.isEmpty())) {
//							System.out.println("ls-->" + ls);
							tax = new HashMap();
							// System.out.println("tax is empty----" + tax
							// +"----"+tax.isEmpty());
							while (itra.hasNext()) {

								org.jdom2.Element tx = (org.jdom2.Element) itra.next();
								List txls = tx.getChildren();
								Iterator itra1 = txls.iterator();
								if (!txls.isEmpty()) {
//									System.out.println("txls-->" + txls);
									tax1 = new HashMap();
									while (itra1.hasNext()) {
										org.jdom2.Element tx1 = (org.jdom2.Element) itra1.next();
										List tx1ls = tx1.getChildren();
										Iterator itra2 = tx1ls.iterator();
										if (!tx1ls.isEmpty()) {
//											System.out.println("tx1ls-->" + tx1ls);
											tax2 = new HashMap();
											while (itra2.hasNext()) {
												org.jdom2.Element tx2 = (org.jdom2.Element) itra2.next();
												List tx2ls = tx2.getChildren();
												Iterator itra3 = tx2ls.iterator();
												if (!tx2ls.isEmpty()) {
//													 System.out.println("tx2ls-->"
//													 + tx2ls);
													tax3 = new HashMap();
													while (itra3.hasNext()) {
														org.jdom2.Element tx3 = (org.jdom2.Element) itra3.next();
														List tx3ls = tx3.getChildren();
														Iterator itra4 = tx3ls.iterator();
														if (!tx3ls.isEmpty()) {
//															 System.out.println("tx3ls-->"
//															 + tx3ls);
															tax4 = new HashMap();
			
															while (itra4.hasNext()) {
																org.jdom2.Element tx4 = (org.jdom2.Element) itra4
																		.next();
																List tx4ls = tx4.getChildren();
																Iterator itra5 = tx4ls.iterator();
																if (tx4ls.isEmpty()) {
//																	 System.out.println("tx4ls-->"
//																	 + tx4ls);
																	
//																	String node1Type = tx3.getAttributeValue("Type");
//																	System.out.println("node1Type = " + node1Type);
//																	String node1Name = tx3.getAttributeValue("Name");
//																	System.out.println("node1Name = " + node1Name);
//																	String node1Value = tx3.getAttributeValue("Value");
//																	System.out.println("node1Value = " + node1Value);
																	String node2Name = tx4.getAttributeValue("Name");
//																	System.out.println("node2Name = " + node2Name);
																	String node2Value = tx4.getAttributeValue("Value");
//																	System.out.println("node2Value = " + node2Value);
																	String tx2name = tx2.getAttributeValue("Name");
//																	System.out.println("tx2name = " + tx2name);
																	
																	if ((tx2.getAttributeValue("Name").equalsIgnoreCase("NonElectricalAccessiories"))) {
																		x = 0;
																		data.put("NonElectricalAccessiories"+node2Name, node2Value);
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("ElectricalAccessiories"))) { 
																		x = 0;
																		data.put("ElectricalAccessiories"+node2Name, node2Value);													
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("NamedpassengerNomineeDetail"))) { 
																		x = 0;
																		data.put("NamedpassengerNomineeDetail"+node2Name, node2Value);	

																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("OwnerDriverNomineeDetails"))) {
																		x = 0;
																		data.put("OwnerDriverNomineeDetails"+node2Name, node2Value);	
																		
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("TrailerDetails"))) { 
																		x = 0;
																		data.put("TrailerDetails"+node2Name, node2Value);
																		
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("Electronic Accessories"))) {
																		x = 0;
																		data.put("ElectronicAccessories"+node2Name, node2Value);
																		
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("NonElectrical Accessories"))) {
																		x = 0;
																		data.put("NonElectricalAccessories"+node2Name, node2Value);
																		
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("CNG and LPG Kit"))) {
																		x = 0;
																		data.put("CNGandLPGKit"+node2Name, node2Value);
																		
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("Named PA Cover"))) {
																		x = 0;
																		data.put("NamedPACover"+node2Name, node2Value);

																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("Unnamed PA cover"))) {
																		x = 0;
																		data.put("UnnamedPACover"+node2Name, node2Value);
																		
																	}else if ((tx2.getAttributeValue("Name").equalsIgnoreCase("Paid driver PA cover"))) {
																		x = 0;
																		data.put("PaiddriverPAcover"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Basic TP including TPPD premium"))) {
																		x = 0;
																		data.put("BasicTPincludingTPPDpremium" + node2Name, node2Value);	
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Own Damage"))) {
																		x = 0;
																		data.put("OwnDamage" + node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Engine Protect"))) {
																		x = 0;
																		data.put("EngineProtect"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Return To Invoice"))) {
																		x = 0;
																		data.put("ReturnToInvoice"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Consumable Cover"))) {
																		data.put("ConsumableCover"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Legal Liability for paid driver cleaner conductor"))) {
																		data.put("LegLibPdDvrClnrCndctr"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Legal Liability for Employees other than paid driver conductor cleaner"))) {
																		data.put("LglLibEmpClnrCndctor"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Road Side Assistance"))) {
																		data.put("RSA"+node2Name, node2Value);
//																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Rallies TP"))) {
																		data.put("RalliesTP"+node2Name, node2Value);

																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Depreciation Cover"))) {
																		data.put("DepreciationCover"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Rallies OD"))) {
																		data.put("RalliesOD"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Liability to soldier sailor airman"))) {
																		data.put("LibSolSailAirman"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Geographical Extension TP"))) {
																		data.put("GeoExTP"+node2Name, node2Value);
//																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Geographical Extension OD"))) {
																		data.put("GeoExOD"+node2Name, node2Value);

																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Owner Driver"))) {
																		data.put("OwnerDriver"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("CNG Kit TP"))) {
																		data.put("CNGKitTP"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Trailer OD"))) {
																		data.put("TrailerOD"+node2Name, node2Value);
																		
																	}else if ((tx3.getAttributeValue("Name").equalsIgnoreCase("Trailer TP"))) {
																		data.put("TrailerTP"+node2Name, node2Value);
																		
																	}
//																	else if (tx2.getAttributeValue("Name").equalsIgnoreCase("Electronic Accessories")) {
//																		elecAccOD.put(node2Name, node2Value);
//																	}
//																	System.out.println("tx2name2222 = " + tx2name);
																	if (tx2name.equalsIgnoreCase("Electronic Accessories")) {
																		data.put("ElectronicAccessories"+node2Name, node2Value);
																	}
																	if (tx2name.equalsIgnoreCase("Non Electrical Accessories OD")) {
																		data.put("NonElectricalAccessoriesOD"+node2Name, node2Value);
																	}
																	if (tx2name.equalsIgnoreCase("CNG and LPG Kit")) {
																		data.put("CNGandLPGKit"+node2Name, node2Value);
																	}
																	if (tx2name.equalsIgnoreCase("Named PA Cover")) {
																		data.put("NamedPACover"+node2Name, node2Value);
																	}
																	if (tx2name.equalsIgnoreCase("Unnamed PA Cover")) {
																		data.put("UnnamedPACover"+node2Name, node2Value);
																	}
																	if (tx2name.equalsIgnoreCase("Paid Driver PA Cover")) {
																		data.put("PaidDriverPACover"+node2Name, node2Value);
																	}
																	
																	
//																	while (itra5.hasNext()) {
//																		org.jdom2.Element tx5 = (org.jdom2.Element) itra5
//																				.next();
//																		List tx5ls = tx5.getChildren();
//																		Iterator itra6 = tx5ls.iterator();
//																		if (!tx5ls.isEmpty()) {
//																			while (itra6.hasNext()) {
//																				org.jdom2.Element tx6 = (org.jdom2.Element) itra6
//																						.next();
//																				List tx6ls = tx5.getChildren();
//																				System.out.println("tx6ls === " + tx6ls);
//																				Iterator itra7 = tx6ls.iterator();
//																				if (!tx5ls.isEmpty()) {
//																					
//																				}
//																				data.put(tx6.getName(), tx6.getAttributeValue("Value"));
//																				// System.out.println("tx5555555555555-------------->>"+tx5.getAttributeValue("Value"));
//																				// System.out.println("tx5555555555555-------------->>"+tx5.getName());
//																			}
//																		}
//																		data.put(tx5.getName(), tx5.getAttributeValue("Value"));
//																		// System.out.println("tx5555555555555-------------->>"+tx5.getAttributeValue("Value"));
//																		// System.out.println("tx5555555555555-------------->>"+tx5.getName());
//																	}
//
																}
//																System.out.println("cover"+i+"***-->>"+cover);
																// **********************************************

//																data.put(tx4.getName(), tx4.getAttributeValue("Value"));
//																// System.out.println("tx5555555555555-------------->>"+tx5.getAttributeValue("Value"));
//																// System.out.println("tx5555555555555-------------->>"+tx5.getName());
															}
															if (x == 0) {
//																i++;
//																 System.out.println("nonElecAcc"+"-->>"+nonElecAcc);
//																data.put("nonElecAcc", nonElecAcc);
//																coverlist.add("cover" + i);
//																i--;
															}
															if (x == 0) {
//																i++;
//																 System.out.println("ElecAcc"+"-->>"+ElecAcc);
//																data.put("ElecAcc", ElecAcc);
//																coverlist.add("cover" + i);
															}
															if (x == 0) {
//																i++;
//																 System.out.println("nmdPsngrNomDet"+"-->>"+nmdPsngrNomDet);
//																data.put("nmdPsngrNomDet", nmdPsngrNomDet);
//																coverlist.add("cover" + i);
															}
															if (x == 0) {
//																i++;
//																 System.out.println("ownrDvrNomDet"+"-->>"+ownrDvrNomDet);
//																data.put("ownrDvrNomDet", ownrDvrNomDet);
//																coverlist.add("cover" + i);
															}
															
//																rskVehBsVal.put("bscTPinclTPPDprem", bscTPinclTPPDprem);
//																rskVehBsVal.put("ownDmg", ownDmg);
//																rskVehBsVal.put("engPtrct", engPtrct);
//																rskVehBsVal.put("rtrnToInvc", rtrnToInvc);
//																rskVehBsVal.put("cnsmblCvr", cnsmblCvr);
//																rskVehBsVal.put("lglLibPdDvrClnrCndctor", lglLibPdDvrClnrCndctor);
//																rskVehBsVal.put("lglLibEmpClnrCndctor", lglLibEmpClnrCndctor);
//																rskVehBsVal.put("rsa", rsa);
//																rskVehBsVal.put("rallTP", rallTP);
//																rskVehBsVal.put("depCvr", depCvr);
//																rskVehBsVal.put("rallOD", rallOD);
//																rskVehBsVal.put("libSolSailAirman", libSolSailAirman);
//																rskVehBsVal.put("geoExTP", geoExTP);
//																rskVehBsVal.put("geoExOD", geoExOD);
//																rskVehBsVal.put("ownrDrvr", ownrDrvr);
//																rskVehBsVal.put("cngKitTP", cngKitTP);
//																rskTrailer.put("trailerOD", trailerOD);
//																rskTrailer.put("trailerTP", trailerTP);
															
//															System.out.println("coverlist = " + coverlist);
															
														}
														// **********************************************

//														data.put(tx3.getName(), tx3.getAttributeValue("Value"));
//														// System.out.println("tx4444444444444-------------->>"+tx4.getAttributeValue("Value"));
//														// System.out.println("tx4444444444444-------------->>"+tx4.getName());
													}
													
//													data.put("RiskVehicleBaseVal", rskVehBsVal);
//													data.put("RiskTrailer", rskTrailer);
//													data.put("elecAccOD", elecAccOD);
//													data.put("elecalAccOD", elecalAccOD);
//													data.put("cngLpgKit", cngLpgKit);
//													data.put("nmdPACvr", nmdPACvr);
//													data.put("unmdPACvr", unmdPACvr);
//													data.put("pdDvrPACvr", pdDvrPACvr);
//													data.put(tx1.getAttributeValue("Name"), tx1.getAttributeValue("Value"));
												}
												// **********************************************

//												data.put(tx2.getName(), tx2.getAttributeValue("Value"));
												// System.out.println("tx3333333333333-------------->>"+tx3.getAttributeValue("Value"));
												// System.out.println("tx3333333333333-------------->>"+tx3.getName());
											}

											
											
											
											
										}
										// **********************************************
										String tx1name = tx1.getName();
										data.put(tx1name, tx1.getAttributeValue("Value"));
										// System.out.println("tx222222222222-------------->>"+tx2.getAttributeValue("Value"));
										// System.out.println("tx222222222222-------------->>"+tx2.getName());
									}

								}
								String txname = tx.getName();
//								System.out.println("txname : " + txname);
								data.put(txname, tx.getAttributeValue("Value"));
								// System.out.println("tx111111111111-------------->>"+tx1.getAttributeValue("Value"));
								// System.out.println("tx111111111111-------------->>"+tx1.getName());

							}
							j++;
						} else {
							x = 0;
							if (!(rncv.getValue() == null)) {
								// System.out.println("rncv.getName()------>>" +
								// rncv.getName());
								// System.out.println("rncv.getValue()------>>"
								// + rncv.getValue());
								data.put(rncv.getName(), rncv.getValue());
							} else {
								// System.out.println("s.getName()----->>" +
								// s.getName());
								data.put(s.getName(), "");
							}
						}
					}

				} else {
					// System.out.println("s.getAttributeValue elseeeeee
					// ---------------->>"+s.getAttributeValue("Value"));
					// System.out.println("s.getValue() elseeeeee
					// ---------------->>"+s.getValue());
					if (!(s.getAttributeValue("Value") == null)) {
						// System.out.println("getName----" + s.getName());
						// System.out.println("getValue----" + s.getValue());

						data.put(s.getName(), s.getAttributeValue("Value"));
					} else if (!(s.getValue() == null)) {
						// System.out.println("getName----" + s.getName());
						// System.out.println("getValue----" + s.getValue());
						data.put(s.getName(), s.getValue());
					} else {
						// System.out.println("getName----" + s.getName());
						// System.out.println("getValue----" + s.getValue());
						data.put(s.getName(), "");
					}
				}
			}
//			data.put("coverlist", coverlist);
			// System.out.println("data--" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private HashMap<String, String> readResponce(String str) {
		data = new HashMap();
		HashMap tax;
		ArrayList coverlist = new ArrayList<>();
		try {
			data.clear();
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(new ByteArrayInputStream(str.getBytes()));
			// -------------------------------------------------------------------
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			String xt = xmlOutput.outputString(document);

			xmlOutput.output(document, new FileWriter(path + "\\KotakResponse.xml"));
			// -----------------------------------------------------------
			org.jdom2.Element root = document.getRootElement();
			java.util.List<Element> list = root.getChildren();
			System.out.println("LIST=" + list);
			Iterator itr = list.iterator();
			data.put("Company", "Kotak");
			int i = 0, x = 1, temp = 0;
			while (itr.hasNext()) {
				org.jdom2.Element s = (org.jdom2.Element) itr.next();
				java.util.List<Element> lst = s.getChildren();
				// System.out.println("lst=" + lst);
				Iterator iterator = lst.iterator();
				HashMap cover = new HashMap();
				cover.clear();
				if (!lst.isEmpty()) {
					int j = 0;
					while (iterator.hasNext()) {
						org.jdom2.Element rncv = (org.jdom2.Element) iterator.next();
						// System.out.println("rncv--" + rncv);
						java.util.List<Element> ls = rncv.getChildren();
						Iterator itra = ls.iterator();
						if (!(ls.isEmpty())) {
							System.out.println("ls-->" + ls);
							tax = new HashMap();
							while (itra.hasNext()) {
								org.jdom2.Element tx1 = (org.jdom2.Element) itra.next();

								// System.out.println("rncv--" + tx1);
								java.util.List<Element> ls1 = tx1.getChildren();
								Iterator itra1 = ls1.iterator();

								if (!(ls1.isEmpty())) {
									while (itra1.hasNext()) {
										org.jdom2.Element tx2 = (org.jdom2.Element) itra1.next();
										System.out.println("ls1-->" + ls1);
										System.out.println("tx2-->" + tx2);
										// *******************************************
										// System.out.println("rncv--" + tx2);
										java.util.List<Element> ls2 = tx2.getChildren();
										System.out.println("ls2-->" + ls2);
										Iterator itra2 = ls2.iterator();

										if (!(ls2.isEmpty())) {
											while (itra2.hasNext()) {
												org.jdom2.Element tx3 = (org.jdom2.Element) itra2.next();
												System.out.println("ls2-->" + ls2);

												// *******************************************
												// System.out.println("rncv--" +
												// tx3);
												java.util.List<Element> ls3 = tx3.getChildren();
												Iterator itra3 = ls3.iterator();

												if (!(ls3.isEmpty())) {
													while (itra3.hasNext()) {
														org.jdom2.Element tx4 = (org.jdom2.Element) itra3.next();
														System.out.println("ls3-->" + ls3);
														// *******************************************
														// System.out.println("rncv--"
														// + tx4);
														java.util.List<Element> ls4 = tx4.getChildren();
														Iterator itra4 = ls4.iterator();

														if (!(ls4.isEmpty())) {
															while (itra4.hasNext()) {
																org.jdom2.Element tx5 = (org.jdom2.Element) itra4
																		.next();

																System.out.println("ls4-->" + ls4);
																// *******************************************
																// System.out.println("rncv--"
																// + tx5);
																java.util.List<Element> ls5 = tx5.getChildren();
																Iterator itra5 = ls5.iterator();

																if (!(ls5.isEmpty())) {
																	System.out.println("ls5-->" + ls5);
																	String node1Type = tx4.getAttributeValue("Type");
																	String node1Name = tx4.getAttributeValue("Name");
																	cover = new HashMap<>();
																	while (itra5.hasNext()) {
																		org.jdom2.Element tx6 = (org.jdom2.Element) itra5
																				.next();

																		String name = tx6.getAttributeValue("Name");
																		// System.out.println("node1Type==>>"+node1Type);
																		// System.out.println("node1Name==>>"+node1Name);
																		// System.out.println("name2Name==>>"+name);
																		if ((node1Type.equals("Group")
																				&& node1Name.equalsIgnoreCase("Covers"))
																				&& (name.equalsIgnoreCase("Premium")
																						|| name.equalsIgnoreCase("Rate")
																						|| name.equalsIgnoreCase(
																								"SumInsured")
																						|| name.equalsIgnoreCase(
																								"Applicable")
																						|| name.equalsIgnoreCase(
																								"CoverGroups"))) {
																			x = 0;
																			cover.put(tx6.getName(),
																					tx6.getAttributeValue("Value"));
																		} else if ((node1Type.equals("Group")
																				&& node1Name.equalsIgnoreCase(
																						"AddonCovers"))
																				&& (name.equalsIgnoreCase("Premium")
																						|| name.equalsIgnoreCase("Rate")
																						|| name.equalsIgnoreCase(
																								"SumInsured")
																						|| name.equalsIgnoreCase(
																								"Applicable")
																						|| name.equalsIgnoreCase(
																								"AddonCoverGroups"))) {
																			x = 0;
																			cover.put(tx6.getName(),
																					tx6.getAttributeValue("Value"));
																		} else if ((node1Type.equals("Group")
																				&& node1Name.equalsIgnoreCase(
																						"Other Discount Group"))
																				&& (name.equalsIgnoreCase("Premium")
																						|| name.equalsIgnoreCase("Rate")
																						|| name.equalsIgnoreCase(
																								"SumInsured")
																						|| name.equalsIgnoreCase(
																								"Applicable")
																						|| name.equalsIgnoreCase(
																								"Description")
																						|| name.equalsIgnoreCase(
																								"Discount Amount")
																						|| name.equalsIgnoreCase(
																								"Discount Rate"))) {
																			x = 0;
																			cover.put(tx6.getName(),
																					tx6.getAttributeValue("Value"));
																		} else {
																			x = 1;
																			data.put(tx6.getName(),
																					tx6.getAttributeValue("Value"));
																		}
																		System.out.println(
																				"tx6666666666666-------------->>" + tx6
																						.getAttributeValue("Value"));
																		System.out.println(
																				"tx6666666666666-------------->>"
																						+ tx6.getName());
																	}

																	if (x == 0) {
																		i++;
																		// System.out.println("cover"+i+"
																		// ***-->>"+cover);
																		data.put("cover" + i, cover);
																		coverlist.add("cover" + i);
																	}

																}
																// **********************************************

																data.put(tx5.getName(), tx5.getAttributeValue("Value"));
																// System.out.println("tx5555555555555-------------->>"+tx5.getAttributeValue("Value"));
																// System.out.println("tx5555555555555-------------->>"+tx5.getName());
															}

														}
														// **********************************************

														data.put(tx4.getName(), tx4.getAttributeValue("Value"));
														// System.out.println("tx4444444444444-------------->>"+tx4.getAttributeValue("Value"));
														// System.out.println("tx4444444444444-------------->>"+tx4.getName());
													}

												}
												// **********************************************

												data.put(tx3.getName(), tx3.getAttributeValue("Value"));
												// System.out.println("tx3333333333333-------------->>"+tx3.getAttributeValue("Value"));
												// System.out.println("tx3333333333333-------------->>"+tx3.getName());
											}

										}
										// **********************************************

										data.put(tx2.getName(), tx2.getAttributeValue("Value"));
										// System.out.println("tx222222222222-------------->>"+tx2.getAttributeValue("Value"));
										// System.out.println("tx222222222222-------------->>"+tx2.getName());
									}

								}
								data.put(tx1.getName(), tx1.getAttributeValue("Value"));
								// System.out.println("tx111111111111-------------->>"+tx1.getAttributeValue("Value"));
								// System.out.println("tx111111111111-------------->>"+tx1.getName());

							}
							j++;
						} else {
							x = 0;
							if (!(rncv.getValue() == null)) {
								// System.out.println("rncv.getName()------>>" +
								// rncv.getName());
								// System.out.println("rncv.getValue()------>>"
								// + rncv.getValue());
								data.put(rncv.getName(), rncv.getValue());
							} else {
								// System.out.println("s.getName()----->>" +
								// s.getName());
								data.put(s.getName(), "");
							}
						}
					}

				} else {
					// System.out.println("s.getAttributeValue elseeeeee
					// ---------------->>"+s.getAttributeValue("Value"));
					// System.out.println("s.getValue() elseeeeee
					// ---------------->>"+s.getValue());
					if (!(s.getAttributeValue("Value") == null)) {
						// System.out.println("getName----" + s.getName());
						// System.out.println("getValue----" + s.getValue());

						data.put(s.getName(), s.getAttributeValue("Value"));
					} else if (!(s.getValue() == null)) {
						// System.out.println("getName----" + s.getName());
						// System.out.println("getValue----" + s.getValue());
						data.put(s.getName(), s.getValue());
					} else {
						// System.out.println("getName----" + s.getName());
						// System.out.println("getValue----" + s.getValue());
						data.put(s.getName(), "");
					}
				}
			}
			data.put("coverlist", coverlist);
			// System.out.println("data--" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}


	
	private String xmlFileProposal(String jsonNames) {
		String xx = "";
		Document document2;
		org.w3c.dom.Document document = null;

		rootelement = new org.jdom2.Element("Root");
		rootelement.setAttribute("Code", "3121");
		rootelement.setAttribute("Name", "PrivateCarInsurancePolicy");
		document2 = new Document(rootelement);

		JSONArray jsonarray;
		try {
			jsonarray = new JSONArray(jsonNames);
		
//		 System.out.println("jsonarray"+jsonarray);
		for (int i = 0; i < jsonarray.length(); i++) {
		 jsonResult = jsonarray.getJSONObject(i);
		
		ProposalDetails = new org.jdom2.Element("ProposalDetails");
		org.jdom2.Element RiskDetails = new org.jdom2.Element("RiskDetails");

		// org.jdom2.Element GeneralPurposeInformation = new org.jdom2.Element(
		// "GeneralPurposeInformation");

		Block = new org.jdom2.Element("Block");

		org.jdom2.Element PropRisks_AAOmembershipno = new org.jdom2.Element("PropRisks_AAOmembershipno");
		PropRisks_AAOmembershipno.setAttribute("Value", "666666");
		PropRisks_AAOmembershipno.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_AddOnDiscnt = new org.jdom2.Element("PropRisks_AddOnDiscnt");
		PropRisks_AddOnDiscnt.setAttribute("Value", "500");
		PropRisks_AddOnDiscnt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Age_Value = new org.jdom2.Element("PropRisks_Age_Value");
		PropRisks_Age_Value.setAttribute("Value", "46");
		PropRisks_Age_Value.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_AgeCoverageDetails = new org.jdom2.Element("PropRisks_AgeCoverageDetails");
		PropRisks_AgeCoverageDetails.setAttribute("Value", "16");
		PropRisks_AgeCoverageDetails.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_AntiTheftCheck = new org.jdom2.Element("PropRisks_AntiTheftCheck");
		PropRisks_AntiTheftCheck.setAttribute("Value", "16");
		PropRisks_AntiTheftCheck.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_ApplicableDocuments = new org.jdom2.Element("PropRisks_ApplicableDocuments");
		PropRisks_ApplicableDocuments.setAttribute("Value", "");
		PropRisks_ApplicableDocuments.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_AuthorityLocation = new org.jdom2.Element("PropRisks_AuthorityLocation");
		PropRisks_AuthorityLocation.setAttribute("Value", "MUMBAI");
		PropRisks_AuthorityLocation.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_AutoMobileAssocName = new org.jdom2.Element("PropRisks_AutoMobileAssocName");
		PropRisks_AutoMobileAssocName.setAttribute("Value", "AAIE");
		PropRisks_AutoMobileAssocName.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_AutomobileAssociC = new org.jdom2.Element("PropRisks_AutomobileAssociC");
		PropRisks_AutomobileAssociC.setAttribute("Value", "True");
		PropRisks_AutomobileAssociC.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_Bangladesh = new org.jdom2.Element("PropRisks_Bangladesh");
		PropRisks_Bangladesh.setAttribute("Value", "True");
		PropRisks_Bangladesh.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_BasisOfRating = new org.jdom2.Element("PropRisks_BasisOfRating");
		PropRisks_BasisOfRating.setAttribute("Value", "GLM");
		PropRisks_BasisOfRating.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_Bhutan = new org.jdom2.Element("PropRisks_Bhutan");
		PropRisks_Bhutan.setAttribute("Value", "True");
		PropRisks_Bhutan.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_BreakInInsurance = new org.jdom2.Element("PropRisks_Bhutan");
		PropRisks_BreakInInsurance.setAttribute("Value", "No");
		PropRisks_BreakInInsurance.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_BreakInInsuranceGLMMulti = new org.jdom2.Element(
				"PropRisks_BreakInInsuranceGLMMulti");
		PropRisks_BreakInInsuranceGLMMulti.setAttribute("Value", "0.88");
		PropRisks_BreakInInsuranceGLMMulti.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_BusinessChnlGLMMultiplier = new org.jdom2.Element(
				"PropRisks_BusinessChnlGLMMultiplier");
		PropRisks_BusinessChnlGLMMultiplier.setAttribute("Value", "1");
		PropRisks_BusinessChnlGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CNGLPGkitValue = new org.jdom2.Element("PropRisks_CNGLPGkitValue");
		PropRisks_CNGLPGkitValue.setAttribute("Value", "50000");
		PropRisks_CNGLPGkitValue.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CSD_Value = new org.jdom2.Element("PropRisks_CSD_Value");
		PropRisks_CSD_Value.setAttribute("Value", "True");
		PropRisks_CSD_Value.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_CapitalSIPerPerson = new org.jdom2.Element("PropRisks_CapitalSIPerPerson");
		PropRisks_CapitalSIPerPerson.setAttribute("Value", "10000");
		PropRisks_CapitalSIPerPerson.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CertifiedVintageCarByCarClub = new org.jdom2.Element(
				"PropRisks_CertifiedVintageCarByCarClub");
		PropRisks_CertifiedVintageCarByCarClub.setAttribute("Value", "True");
		PropRisks_CertifiedVintageCarByCarClub.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_ChasisNo = new org.jdom2.Element("PropRisks_ChasisNo");
		PropRisks_ChasisNo.setAttribute("Value", "");
		PropRisks_ChasisNo.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ChassisNumber = new org.jdom2.Element("PropRisks_ChassisNumber");
		PropRisks_ChassisNumber.setAttribute("Value", jsonResult.getString("ChasisNo").trim());
		PropRisks_ChassisNumber.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ChkForGeographical = new org.jdom2.Element("PropRisks_ChkForGeographical");
		PropRisks_ChkForGeographical.setAttribute("Value", "True");
		PropRisks_ChkForGeographical.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_ChkForRacing = new org.jdom2.Element("PropRisks_ChkForRacing");
		PropRisks_ChkForRacing.setAttribute("Value", "True");
		PropRisks_ChkForRacing.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_CityCode = new org.jdom2.Element("PropRisks_CityCode");
		PropRisks_CityCode.setAttribute("Value", "");
		PropRisks_CityCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ClaimFreeYearGLMDepCvr = new org.jdom2.Element("PropRisks_ClaimFreeYearGLMDepCvr");
		PropRisks_ClaimFreeYearGLMDepCvr.setAttribute("Value", "1");
		PropRisks_ClaimFreeYearGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Colorofthevehicle = new org.jdom2.Element("PropRisks_Colorofthevehicle");
		PropRisks_Colorofthevehicle.setAttribute("Value", "BLACK");
		PropRisks_Colorofthevehicle.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_CompForVolExc1 = new org.jdom2.Element("PropRisks_CompForVolExc1");
		PropRisks_CompForVolExc1.setAttribute("Value", "0");
		PropRisks_CompForVolExc1.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CompForVolExc2 = new org.jdom2.Element("PropRisks_CompForVolExc2");
		PropRisks_CompForVolExc2.setAttribute("Value", "0");
		PropRisks_CompForVolExc2.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CompForVolExc3 = new org.jdom2.Element("PropRisks_CompForVolExc3");
		PropRisks_CompForVolExc3.setAttribute("Value", "0");
		PropRisks_CompForVolExc3.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CompForVolExc4 = new org.jdom2.Element("PropRisks_CompForVolExc4");
		PropRisks_CompForVolExc4.setAttribute("Value", "0");
		PropRisks_CompForVolExc4.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CompulsoryExcess = new org.jdom2.Element("PropRisks_CompulsoryExcess");
		PropRisks_CompulsoryExcess.setAttribute("Value", "1000");
		PropRisks_CompulsoryExcess.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CompulsoryPAwithOwnerDriver = new org.jdom2.Element(
				"PropRisks_CompulsoryPAwithOwnerDriver");
		PropRisks_CompulsoryPAwithOwnerDriver.setAttribute("Value", "True");
		PropRisks_CompulsoryPAwithOwnerDriver.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_ConsumableCvrPrm = new org.jdom2.Element("PropRisks_ConsumableCvrPrm");
		PropRisks_ConsumableCvrPrm.setAttribute("Value", "0.2959");
		PropRisks_ConsumableCvrPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_ConsumablesExpenses = new org.jdom2.Element("PropRisks_ConsumableCvrPrm");
		PropRisks_ConsumablesExpenses.setAttribute("Value", jsonResult.getString("Consumables_Addon"));
		PropRisks_ConsumablesExpenses.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_CountryCode = new org.jdom2.Element("PropRisks_ConsumableCvrPrm");
		PropRisks_CountryCode.setAttribute("Value", "");
		PropRisks_CountryCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_CrossSellDiscount = new org.jdom2.Element("PropRisks_CrossSellDiscount");
		PropRisks_CrossSellDiscount.setAttribute("Value", "");
		PropRisks_CrossSellDiscount.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_CrossSellDiscountGLM = new org.jdom2.Element("PropRisks_CrossSellDiscountGLM");
		PropRisks_CrossSellDiscountGLM.setAttribute("Value", "10");
		PropRisks_CrossSellDiscountGLM.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CubicCapacity = new org.jdom2.Element("PropRisks_CubicCapacity");
		PropRisks_CubicCapacity.setAttribute("Value", "1498");
		PropRisks_CubicCapacity.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_CvrPrm = new org.jdom2.Element("PropRisks_CubicCapacity");
		PropRisks_CvrPrm.setAttribute("Value", "1");
		PropRisks_CvrPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_DateofBirth = new org.jdom2.Element("PropRisks_DateofBirth");
		PropRisks_DateofBirth.setAttribute("Value", "");
		PropRisks_DateofBirth.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_DateofBirthofNominee = new org.jdom2.Element("PropRisks_DateofBirthofNominee");
		PropRisks_DateofBirthofNominee.setAttribute("Value", "01/01/2001");
		PropRisks_DateofBirthofNominee.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_DateofRegistration = new org.jdom2.Element("PropRisks_DateofRegistration");
		PropRisks_DateofRegistration.setAttribute("Value", "26/02/2016");
		PropRisks_DateofRegistration.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Dateofpurchase = new org.jdom2.Element("PropRisks_Dateofpurchase");
		PropRisks_Dateofpurchase.setAttribute("Value", "24/02/2016");
		PropRisks_Dateofpurchase.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_DepreciationReimbursement = new org.jdom2.Element(
				"PropRisks_DepreciationReimbursement");
		PropRisks_DepreciationReimbursement.setAttribute("Value", "True");
		PropRisks_DepreciationReimbursement.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_DepriciationCvrPrm = new org.jdom2.Element("PropRisks_DepriciationCvrPrm");
		PropRisks_DepriciationCvrPrm.setAttribute("Value", "1.0063");
		PropRisks_DepriciationCvrPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_DiscntPrm = new org.jdom2.Element("PropRisks_DepriciationCvrPrm");
		PropRisks_DiscntPrm.setAttribute("Value", "0");
		PropRisks_DiscntPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_DiscountOnIMTRate = new org.jdom2.Element("PropRisks_DiscountOnIMTRate");
		PropRisks_DiscountOnIMTRate.setAttribute("Value", "0");
		PropRisks_DiscountOnIMTRate.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_DiscountforOptingSoftCopy = new org.jdom2.Element(
				"PropRisks_DiscountforOptingSoftCopy");
		PropRisks_DiscountforOptingSoftCopy.setAttribute("Value", "True");
		PropRisks_DiscountforOptingSoftCopy.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_DistrictCode = new org.jdom2.Element("PropRisks_DistrictCode");
		PropRisks_DistrictCode.setAttribute("Value", "");
		PropRisks_DistrictCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Drivingexperience = new org.jdom2.Element("PropRisks_Drivingexperience");
		PropRisks_Drivingexperience.setAttribute("Value", "5");
		PropRisks_Drivingexperience.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Educationalqualification = new org.jdom2.Element(
				"PropRisks_Educationalqualification");
		PropRisks_Educationalqualification.setAttribute("Value", "OTHERS");
		PropRisks_Educationalqualification.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ElectricalAccessories = new org.jdom2.Element("PropRisks_ElectricalAccessories");
		PropRisks_ElectricalAccessories.setAttribute("Value", "15000");
		PropRisks_ElectricalAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_EmergencyMedicalExpenses = new org.jdom2.Element(
				"PropRisks_EmergencyMedicalExpenses");
		PropRisks_EmergencyMedicalExpenses.setAttribute("Value", "True");
		PropRisks_EmergencyMedicalExpenses.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_EmployeesDiscount = new org.jdom2.Element("PropRisks_EmployeesDiscount");
		PropRisks_EmployeesDiscount.setAttribute("Value", "True");
		PropRisks_EmployeesDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_EnginePretectPrm = new org.jdom2.Element("PropRisks_EnginePretectPrm");
		PropRisks_EnginePretectPrm.setAttribute("Value", "0.8266");
		PropRisks_EnginePretectPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_EngineSecure = new org.jdom2.Element("PropRisks_EngineSecure");
		PropRisks_EngineSecure.setAttribute("Value", jsonResult.getString("EngineProtector").trim());
		PropRisks_EngineSecure.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_Engineno = new org.jdom2.Element("PropRisks_Engineno");
		PropRisks_Engineno.setAttribute("Value", jsonResult.getString("EngineNo").trim());
		PropRisks_Engineno.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_EntitledforNCB = new org.jdom2.Element("PropRisks_EntitledforNCB");
		PropRisks_EntitledforNCB.setAttribute("Value", "");
		PropRisks_EntitledforNCB.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_EventName = new org.jdom2.Element("PropRisks_EventName");
		PropRisks_EventName.setAttribute("Value", "gggg");
		PropRisks_EventName.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_FirstName = new org.jdom2.Element("PropRisks_FirstName");
		PropRisks_FirstName.setAttribute("Value", "SANKET");
		PropRisks_FirstName.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ForUWLoading = new org.jdom2.Element("PropRisks_ForUWLoading");
		PropRisks_ForUWLoading.setAttribute("Value", "0");
		PropRisks_ForUWLoading.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_FromDateForRacing = new org.jdom2.Element("PropRisks_FromDateForRacing");
		PropRisks_FromDateForRacing.setAttribute("Value", "");
		PropRisks_FromDateForRacing.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_FromDateForRallies = new org.jdom2.Element("PropRisks_FromDateForRallies");
		PropRisks_FromDateForRacing.setAttribute("Value", "");
		PropRisks_FromDateForRacing.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_FromDateUForm = new org.jdom2.Element("PropRisks_FromDateUForm");
		PropRisks_FromDateUForm.setAttribute("Value", "");
		PropRisks_FromDateUForm.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_FuelType = new org.jdom2.Element("PropRisks_FuelType");
		PropRisks_FuelType.setAttribute("Value", "Diesel");
		PropRisks_FuelType.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_FuelTypeGLMConsmCvr = new org.jdom2.Element("PropRisks_FuelTypeGLMConsmCvr");
		PropRisks_FuelTypeGLMConsmCvr.setAttribute("Value", "1.18");
		PropRisks_FuelTypeGLMConsmCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_FuelTypeGLMDepCvr = new org.jdom2.Element("PropRisks_FuelTypeGLMDepCvr");
		PropRisks_FuelTypeGLMDepCvr.setAttribute("Value", "1.47");
		PropRisks_FuelTypeGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_FuelTypeGLMEngProt = new org.jdom2.Element("PropRisks_FuelTypeGLMEngProt");
		PropRisks_FuelTypeGLMEngProt.setAttribute("Value", "1.28");
		PropRisks_FuelTypeGLMEngProt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_FuelTypeGLMMultiplier = new org.jdom2.Element("PropRisks_FuelTypeGLMMultiplier");
		PropRisks_FuelTypeGLMMultiplier.setAttribute("Value", "1.16");
		PropRisks_FuelTypeGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_FuelTypeGLMRetInv = new org.jdom2.Element("PropRisks_FuelTypeGLMRetInv");
		PropRisks_FuelTypeGLMRetInv.setAttribute("Value", "1.38");
		PropRisks_FuelTypeGLMRetInv.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_GLMRate = new org.jdom2.Element("PropRisks_GLMRate");
		PropRisks_GLMRate.setAttribute("Value", "2.2612");
		PropRisks_GLMRate.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_GeneralRemarks = new org.jdom2.Element("PropRisks_GeneralRemarks");
		PropRisks_GeneralRemarks.setAttribute("Value", "");
		PropRisks_GeneralRemarks.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Geographicalareaextention = new org.jdom2.Element(
				"PropRisks_Geographicalareaextention");
		PropRisks_Geographicalareaextention.setAttribute("Value", "True");
		PropRisks_Geographicalareaextention.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IDVFlag = new org.jdom2.Element("PropRisks_IDVFlag");
		PropRisks_IDVFlag.setAttribute("Value", "699777");
		PropRisks_IDVFlag.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_IDVofTrailer = new org.jdom2.Element("PropRisks_IDVofTrailer");
		PropRisks_IDVofTrailer.setAttribute("Value", "40000");
		PropRisks_IDVofTrailer.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_IDVofthevehicle = new org.jdom2.Element("PropRisks_IDVofthevehicle");
		PropRisks_IDVofthevehicle.setAttribute("Value", jsonResult.getString("IDV").trim());
		PropRisks_IDVofthevehicle.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_IfOthersPleasespecify = new org.jdom2.Element("PropRisks_IfOthersPleasespecify");
		PropRisks_IfOthersPleasespecify.setAttribute("Value", "sasa");
		PropRisks_IfOthersPleasespecify.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ImposedExcessAmount = new org.jdom2.Element("PropRisks_ImposedExcessAmount");
		PropRisks_ImposedExcessAmount.setAttribute("Value", "333333");
		PropRisks_ImposedExcessAmount.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ImprtdVehcleWoutPaymtCustmDuty = new org.jdom2.Element(
				"PropRisks_ImprtdVehcleWoutPaymtCustmDuty");
		PropRisks_ImprtdVehcleWoutPaymtCustmDuty.setAttribute("Value", "True");
		PropRisks_ImprtdVehcleWoutPaymtCustmDuty.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_InsuredAge = new org.jdom2.Element("PropRisks_InsuredAge");
		PropRisks_InsuredAge.setAttribute("Value", "46");
		PropRisks_InsuredAge.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InsuredAgeGLMMultiplier = new org.jdom2.Element(
				"PropRisks_InsuredAgeGLMMultiplier");
		PropRisks_InsuredAgeGLMMultiplier.setAttribute("Value", "0.96");
		PropRisks_InsuredAgeGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InsuredCreditScore = new org.jdom2.Element("PropRisks_InsuredCreditScore");
		PropRisks_InsuredCreditScore.setAttribute("Value", "5555");
		PropRisks_InsuredCreditScore.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InsuredCreditScoreGLM = new org.jdom2.Element("PropRisks_InsuredCreditScoreGLM");
		PropRisks_InsuredCreditScoreGLM.setAttribute("Value", "1");
		PropRisks_InsuredCreditScoreGLM.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InsuredGender = new org.jdom2.Element("PropRisks_InsuredGender");
		PropRisks_InsuredGender.setAttribute("Value", "Male");
		PropRisks_InsuredGender.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_InsuredGenderGLMMultiplier = new org.jdom2.Element(
				"PropRisks_InsuredGenderGLMMultiplier");
		PropRisks_InsuredGenderGLMMultiplier.setAttribute("Value", "0.85");
		PropRisks_InsuredGenderGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InsuredHasDrivinglicense = new org.jdom2.Element(
				"PropRisks_InsuredHasDrivinglicense");
		PropRisks_InsuredHasDrivinglicense.setAttribute("Value", "True");
		PropRisks_InsuredHasDrivinglicense.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_InsuredProfession = new org.jdom2.Element("PropRisks_InsuredProfession");
		PropRisks_InsuredProfession.setAttribute("Value", "Others");
		PropRisks_InsuredProfession.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_InsuredProfessionGLMMulti = new org.jdom2.Element(
				"PropRisks_InsuredProfessionGLMMulti");
		PropRisks_InsuredProfessionGLMMulti.setAttribute("Value", "1.01");
		PropRisks_InsuredProfessionGLMMulti.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InsureddrivingScore = new org.jdom2.Element("PropRisks_InsureddrivingScore");
		PropRisks_InsureddrivingScore.setAttribute("Value", "6666");
		PropRisks_InsureddrivingScore.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InsureddrivingScoreGLM = new org.jdom2.Element("PropRisks_InsureddrivingScoreGLM");
		PropRisks_InsureddrivingScoreGLM.setAttribute("Value", "1");
		PropRisks_InsureddrivingScoreGLM.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InteractionWithRatingFactor = new org.jdom2.Element(
				"PropRisks_InteractionWithRatingFactor");
		PropRisks_InteractionWithRatingFactor.setAttribute("Value", "0.9");
		PropRisks_InteractionWithRatingFactor.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_InteractionWithRtngFactGLM = new org.jdom2.Element(
				"PropRisks_InteractionWithRtngFactGLM");
		PropRisks_InteractionWithRtngFactGLM.setAttribute("Value", "1");
		PropRisks_InteractionWithRtngFactGLM.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_IsExternalCNGLPGAvailable = new org.jdom2.Element(
				"PropRisks_IsExternalCNGLPGAvailable");
		PropRisks_IsExternalCNGLPGAvailable.setAttribute("Value", "True");
		PropRisks_IsExternalCNGLPGAvailable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IsNewRateApplicable = new org.jdom2.Element("PropRisks_IsNewRateApplicable");
		PropRisks_IsNewRateApplicable.setAttribute("Value", "False");
		PropRisks_IsNewRateApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IsNonElctrclAcesrsRequired = new org.jdom2.Element(
				"PropRisks_IsNonElctrclAcesrsRequired");
		PropRisks_IsNonElctrclAcesrsRequired.setAttribute("Value", "True");
		PropRisks_IsNonElctrclAcesrsRequired.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IsPAToOwnerDriverExcluded = new org.jdom2.Element(
				"PropRisks_IsPAToOwnerDriverExcluded");
		PropRisks_IsPAToOwnerDriverExcluded.setAttribute("Value", "False");
		PropRisks_IsPAToOwnerDriverExcluded.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IsTrailerAvailable = new org.jdom2.Element("PropRisks_IsTrailerAvailable");
		PropRisks_IsTrailerAvailable.setAttribute("Value", "True");
		PropRisks_IsTrailerAvailable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IsVehcleUsdForDrivngTuitions = new org.jdom2.Element(
				"PropRisks_IsVehcleUsdForDrivngTuitions");
		PropRisks_IsVehcleUsdForDrivngTuitions.setAttribute("Value", "True");
		PropRisks_IsVehcleUsdForDrivngTuitions.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IsVehicle = new org.jdom2.Element("PropRisks_IsVehicle");
		PropRisks_IsVehicle.setAttribute("Value", "");
		PropRisks_IsVehicle.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_IsVehicleUsedforblindpersons = new org.jdom2.Element(
				"PropRisks_IsVehicleUsedforblindpersons");
		PropRisks_IsVehicleUsedforblindpersons.setAttribute("Value", "True");
		PropRisks_IsVehicleUsedforblindpersons.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_IsVehicleusedlimitedtopremises = new org.jdom2.Element(
				"PropRisks_IsVehicleusedlimitedtopremises");
		PropRisks_IsVehicleusedlimitedtopremises.setAttribute("Value", "True");
		PropRisks_IsVehicleusedlimitedtopremises.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_Issuer = new org.jdom2.Element("PropRisks_Issuer");
		PropRisks_Issuer.setAttribute("Value", "0");
		PropRisks_Issuer.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_KotakGrpEmployeeDiscount = new org.jdom2.Element(
				"PropRisks_KotakGrpEmployeeDiscount");
		PropRisks_KotakGrpEmployeeDiscount.setAttribute("Value", "0");
		PropRisks_KotakGrpEmployeeDiscount.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_LastName = new org.jdom2.Element("PropRisks_LastName");
		PropRisks_LastName.setAttribute("Value", "GADKARI");
		PropRisks_LastName.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_LdngPrm = new org.jdom2.Element("PropRisks_LdngPrm");
		PropRisks_LdngPrm.setAttribute("Value", "0");
		PropRisks_LdngPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_LegalLiabilityToEmployees = new org.jdom2.Element(
				"PropRisks_LegalLiabilityToEmployees");
		PropRisks_LegalLiabilityToEmployees.setAttribute("Value", "True");
		PropRisks_LegalLiabilityToEmployees.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_LiabilityToSoldierSailorAirMan = new org.jdom2.Element(
				"PropRisks_LiabilityToSoldierSailorAirMan");
		PropRisks_LiabilityToSoldierSailorAirMan.setAttribute("Value", "True");
		PropRisks_LiabilityToSoldierSailorAirMan.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_LiscenseNo = new org.jdom2.Element("PropRisks_LiscenseNo");
		PropRisks_LiscenseNo.setAttribute("Value", "777777");
		PropRisks_LiscenseNo.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_LoadEmrgencyMedicalExp = new org.jdom2.Element("PropRisks_LoadEmrgencyMedicalExp");
		PropRisks_LoadEmrgencyMedicalExp.setAttribute("Value", "0");
		PropRisks_LoadEmrgencyMedicalExp.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_LoadHomeProtectSublt = new org.jdom2.Element("PropRisks_LoadHomeProtectSublt");
		PropRisks_LoadEmrgencyMedicalExp.setAttribute("Value", "0");
		PropRisks_LoadHomeProtectSublt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_LoadingDiscountOnAddOnCovers = new org.jdom2.Element(
				"PropRisks_LoadingDiscountOnAddOnCovers");
		PropRisks_LoadingDiscountOnAddOnCovers.setAttribute("Value", "0");
		PropRisks_LoadingDiscountOnAddOnCovers.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_LoadingNewCarRplacmnt = new org.jdom2.Element("PropRisks_LoadingNewCarRplacmnt");
		PropRisks_LoadingNewCarRplacmnt.setAttribute("Value", "0");
		PropRisks_LoadingNewCarRplacmnt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_LocationCode = new org.jdom2.Element("PropRisks_LocationCode");
		PropRisks_LocationCode.setAttribute("Value", "");
		PropRisks_LocationCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_MainDriver = new org.jdom2.Element("PropRisks_MainDriver");
		PropRisks_MainDriver.setAttribute("Value", "Self - Owner Driver");
		PropRisks_MainDriver.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_MakeGLMDepCvr = new org.jdom2.Element("PropRisks_MakeGLMDepCvr");
		PropRisks_MakeGLMDepCvr.setAttribute("Value", "1.03");
		PropRisks_MakeGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_MakeGLMEngProt = new org.jdom2.Element("PropRisks_MakeGLMEngProt");
		PropRisks_MakeGLMEngProt.setAttribute("Value", "1.03");
		PropRisks_MakeGLMEngProt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_MakeGLMMultiplier = new org.jdom2.Element("PropRisks_MakeGLMMultiplier");
		PropRisks_MakeGLMMultiplier.setAttribute("Value", "1.08");
		PropRisks_MakeGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_MakeGLMRetInv = new org.jdom2.Element("PropRisks_MakeGLMRetInv");
		PropRisks_MakeGLMRetInv.setAttribute("Value", "1.03");
		PropRisks_MakeGLMRetInv.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Maldives = new org.jdom2.Element("PropRisks_Maldives");
		PropRisks_Maldives.setAttribute("Value", "True");
		PropRisks_Maldives.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_Manufacture = new org.jdom2.Element("PropRisks_Manufacture");
		PropRisks_Manufacture.setAttribute("Value", jsonResult.getString("Make").trim());
		PropRisks_Manufacture.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ManufactureYear = new org.jdom2.Element("PropRisks_ManufactureYear");
		PropRisks_ManufactureYear.setAttribute("Value", "2016");
		PropRisks_ManufactureYear.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_ManufacturerCode = new org.jdom2.Element("PropRisks_ManufacturerCode");
		PropRisks_ManufacturerCode.setAttribute("Value", "5");
		PropRisks_ManufacturerCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_MarketMovement = new org.jdom2.Element("PropRisks_MarketMovement");
		PropRisks_MarketMovement.setAttribute("Value", jsonResult.getString("MarketMovement").trim());
		PropRisks_MarketMovement.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_MarketMovementGLMMultiplier = new org.jdom2.Element(
				"PropRisks_MarketMovementGLMMultiplier");
		PropRisks_MarketMovementGLMMultiplier.setAttribute("Value", "10");
		PropRisks_MarketMovementGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Model = new org.jdom2.Element("PropRisks_Model");
		PropRisks_Model.setAttribute("Value", jsonResult.getString("EngineNo").trim());
		PropRisks_Model.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ModelCluster = new org.jdom2.Element("PropRisks_ModelCluster");
		PropRisks_ModelCluster.setAttribute("Value", "Category");
		PropRisks_ModelCluster.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ModelClusterGLMDepCvr = new org.jdom2.Element("PropRisks_ModelClusterGLMDepCvr");
		PropRisks_ModelClusterGLMDepCvr.setAttribute("Value", "1");
		PropRisks_ModelClusterGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_ModelClusterGLMMultiplier = new org.jdom2.Element(
				"PropRisks_ModelClusterGLMMultiplier");
		PropRisks_ModelClusterGLMMultiplier.setAttribute("Value", "1.03");
		PropRisks_ModelClusterGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_ModelCode = new org.jdom2.Element("PropRisks_ModelCode");
		PropRisks_ModelCode.setAttribute("Value", "1352");
		PropRisks_ModelCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ModelVariant = new org.jdom2.Element("PropRisks_ModelVariant");
		PropRisks_ModelVariant.setAttribute("Value", jsonResult.getString("strVehicleVariant").trim());
		PropRisks_ModelVariant.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_NCBConfirmation = new org.jdom2.Element("PropRisks_NCBConfirmation");
		PropRisks_NCBConfirmation.setAttribute("Value", "NCB reserving");
		PropRisks_NCBConfirmation.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_NCBProtRatePop = new org.jdom2.Element("PropRisks_NCBProtRatePop");
		PropRisks_NCBProtRatePop.setAttribute("Value", "0");
		PropRisks_NCBProtRatePop.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NCBRatePop = new org.jdom2.Element("PropRisks_NCBRatePop");
		PropRisks_NCBRatePop.setAttribute("Value", "0");
		PropRisks_NCBRatePop.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NCPrm = new org.jdom2.Element("PropRisks_NCPrm");
		PropRisks_NCPrm.setAttribute("Value", "14583.32");
		PropRisks_NCPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NameOfNominee = new org.jdom2.Element("PropRisks_NameOfNominee");
		PropRisks_NameOfNominee.setAttribute("Value", "sfsf");
		PropRisks_NameOfNominee.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Nepal = new org.jdom2.Element("PropRisks_Nepal");
		PropRisks_Nepal.setAttribute("Value", "True");
		PropRisks_Nepal.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_NewCarReplacement = new org.jdom2.Element("PropRisks_NewCarReplacement");
		PropRisks_NewCarReplacement.setAttribute("Value", "False");
		PropRisks_NewCarReplacement.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_NoClaimBonusApplicable = new org.jdom2.Element("PropRisks_NoClaimBonusApplicable");
		PropRisks_NoClaimBonusApplicable.setAttribute("Value", jsonResult.getString("NCB").trim());
		PropRisks_NoClaimBonusApplicable.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoOfClaimFreeYearsCompleted = new org.jdom2.Element(
				"PropRisks_NoOfClaimFreeYearsCompleted");
		PropRisks_NoOfClaimFreeYearsCompleted.setAttribute("Value", "1");
		PropRisks_NoOfClaimFreeYearsCompleted.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoOfPersonWiderLegalLiability = new org.jdom2.Element(
				"PropRisks_NoOfPersonWiderLegalLiability");
		PropRisks_NoOfPersonWiderLegalLiability.setAttribute("Value", "3");
		PropRisks_NoOfPersonWiderLegalLiability.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoOfPersonsLiabilityEmployee = new org.jdom2.Element(
				"PropRisks_NoOfPersonsLiabilityEmployee");
		PropRisks_NoOfPersonsLiabilityEmployee.setAttribute("Value", "5");
		PropRisks_NoOfPersonsLiabilityEmployee.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoOfPersonsNamed = new org.jdom2.Element("PropRisks_NoOfPersonsNamed");
		PropRisks_NoOfPersonsNamed.setAttribute("Value", "1");
		PropRisks_NoOfPersonsNamed.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoOfPersonsPaidDriver = new org.jdom2.Element("PropRisks_NoOfPersonsPaidDriver");
		PropRisks_NoOfPersonsPaidDriver.setAttribute("Value", "4");
		PropRisks_NoOfPersonsPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoOfSoldierSailorAirMan = new org.jdom2.Element(
				"PropRisks_NoOfSoldierSailorAirMan");
		PropRisks_NoOfSoldierSailorAirMan.setAttribute("Value", "6");
		PropRisks_NoOfSoldierSailorAirMan.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NonElectricalAccessories = new org.jdom2.Element(
				"PropRisks_NonElectricalAccessories");
		PropRisks_NonElectricalAccessories.setAttribute("Value", "25000");
		PropRisks_NonElectricalAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoofClmFreeYearCmpltdGLM = new org.jdom2.Element(
				"PropRisks_NoofClmFreeYearCmpltdGLM");
		PropRisks_NoofClmFreeYearCmpltdGLM.setAttribute("Value", "1");
		PropRisks_NoofClmFreeYearCmpltdGLM.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NoofWheels = new org.jdom2.Element("PropRisks_NoofWheels");
		PropRisks_NoofWheels.setAttribute("Value", "4");
		PropRisks_NoofWheels.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_NumberofPersonsUnnamed = new org.jdom2.Element("PropRisks_NumberofPersonsUnnamed");
		PropRisks_NumberofPersonsUnnamed.setAttribute("Value", "5");
		PropRisks_NumberofPersonsUnnamed.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Occupation = new org.jdom2.Element("PropRisks_Occupation");
		PropRisks_Occupation.setAttribute("Value", jsonResult.getString("OCCUPATION").trim());
		PropRisks_Occupation.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OpenCvrNo = new org.jdom2.Element("PropRisks_OpenCvrNo");
		PropRisks_OpenCvrNo.setAttribute("Value", "False");
		PropRisks_OpenCvrNo.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_OriginalNCBPercentage = new org.jdom2.Element("PropRisks_OriginalNCBPercentage");
		PropRisks_OriginalNCBPercentage.setAttribute("Value", "20");
		PropRisks_OriginalNCBPercentage.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_OtherChk1 = new org.jdom2.Element("PropRisks_OtherChk1");
		PropRisks_OtherChk1.setAttribute("Value", "True");
		PropRisks_OtherChk1.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_OtherChk2 = new org.jdom2.Element("PropRisks_OtherChk2");
		PropRisks_OtherChk2.setAttribute("Value", "True");
		PropRisks_OtherChk2.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_OtherChk3 = new org.jdom2.Element("PropRisks_OtherChk3");
		PropRisks_OtherChk3.setAttribute("Value", "True");
		PropRisks_OtherChk3.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_OtherChk4 = new org.jdom2.Element("PropRisks_OtherChk4");
		PropRisks_OtherChk4.setAttribute("Value", "True");
		PropRisks_OtherChk4.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_OtherInfo4 = new org.jdom2.Element("PropRisks_OtherInfo4");
		PropRisks_OtherInfo4.setAttribute("Value", "0");
		PropRisks_OtherInfo4.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInfoP = new org.jdom2.Element("PropRisks_OtherInfoP");
		PropRisks_OtherInfoP.setAttribute("Value", "SLDocumentManagement.xap");
		PropRisks_OtherInfoP.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation11 = new org.jdom2.Element("PropRisks_OtherInformation11");
		PropRisks_OtherInformation11.setAttribute("Value", "ERWERW,MUMBAI,MUMBAI,MAHARASHTRA,400022");
		PropRisks_OtherInformation11.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation12 = new org.jdom2.Element("PropRisks_OtherInformation12");
		PropRisks_OtherInformation12.setAttribute("Value", "ERWERW,MUMBAI,MUMBAI,MAHARASHTRA,400022");
		PropRisks_OtherInformation12.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation13 = new org.jdom2.Element("PropRisks_OtherInformation13");
		PropRisks_OtherInformation13.setAttribute("Value", "01/01/1970");
		PropRisks_OtherInformation13.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation14 = new org.jdom2.Element("PropRisks_OtherInformation14");
		PropRisks_OtherInformation14.setAttribute("Value", "9876543210");
		PropRisks_OtherInformation14.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation1N = new org.jdom2.Element("PropRisks_OtherInformation1N");
		PropRisks_OtherInformation1N.setAttribute("Value", "0");
		PropRisks_OtherInformation1N.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_OtherInformation1T = new org.jdom2.Element("PropRisks_OtherInformation1T");
		PropRisks_OtherInformation1T.setAttribute("Value", "");
		PropRisks_OtherInformation1T.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation2n = new org.jdom2.Element("PropRisks_OtherInformation2n");
		PropRisks_OtherInformation2n.setAttribute("Value", "0");
		PropRisks_OtherInformation2n.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_OtherInformation3T = new org.jdom2.Element("PropRisks_OtherInformation3T");
		PropRisks_OtherInformation3T.setAttribute("Value", "");
		PropRisks_OtherInformation3T.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation4N = new org.jdom2.Element("PropRisks_OtherInformation4N");
		PropRisks_OtherInformation4N.setAttribute("Value", "0.3127");
		PropRisks_OtherInformation4N.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_OtherInformation4T = new org.jdom2.Element("PropRisks_OtherInformation4T");
		PropRisks_OtherInformation4T.setAttribute("Value", "");
		PropRisks_OtherInformation4T.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_OtherInformation5N = new org.jdom2.Element("PropRisks_OtherInformation5N");
		PropRisks_OtherInformation5N.setAttribute("Value", "0");
		PropRisks_OtherInformation5N.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_OtherNumeric11 = new org.jdom2.Element("PropRisks_OtherNumeric11");
		PropRisks_OtherNumeric11.setAttribute("Value", "15823.36");
		PropRisks_OtherNumeric11.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_OtherNumeric12 = new org.jdom2.Element("PropRisks_OtherNumeric12");
		PropRisks_OtherNumeric12.setAttribute("Value", "0");
		PropRisks_OtherNumeric12.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Pakistan = new org.jdom2.Element("PropRisks_Pakistan");
		PropRisks_Pakistan.setAttribute("Value", "True");
		PropRisks_Pakistan.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_PlaceofRegGLMDepCvr = new org.jdom2.Element("PropRisks_PlaceofRegGLMDepCvr");
		PropRisks_PlaceofRegGLMDepCvr.setAttribute("Value", "1");
		PropRisks_PlaceofRegGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_PolicyAge = new org.jdom2.Element("PropRisks_PolicyAge");
		PropRisks_PolicyAge.setAttribute("Value", "0");
		PropRisks_PolicyAge.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_PolicyAgeGLMMultiplier = new org.jdom2.Element("PropRisks_PolicyAgeGLMMultiplier");
		PropRisks_PolicyAgeGLMMultiplier.setAttribute("Value", "0.98");
		PropRisks_PolicyAgeGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_PreInspectionNumber = new org.jdom2.Element("PropRisks_PreInspectionNumber");
		PropRisks_PreInspectionNumber.setAttribute("Value", "789456");
		PropRisks_PreInspectionNumber.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_PrevYearNCB = new org.jdom2.Element("PropRisks_PrevYearNCB");
		PropRisks_PrevYearNCB.setAttribute("Value", "");
		PropRisks_PrevYearNCB.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_PromoterName = new org.jdom2.Element("PropRisks_PromoterName");
		PropRisks_PromoterName.setAttribute("Value", "sdf");
		PropRisks_PromoterName.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ProposalType = new org.jdom2.Element("PropRisks_ProposalType");
		PropRisks_ProposalType.setAttribute("Value", "New Business");
		PropRisks_ProposalType.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RTOCluster = new org.jdom2.Element("PropRisks_RTOCluster");
		PropRisks_RTOCluster.setAttribute("Value", "Category 7");
		PropRisks_RTOCluster.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RTOClusterGLMMultiplier = new org.jdom2.Element(
				"PropRisks_RTOClusterGLMMultiplier");
		PropRisks_RTOClusterGLMMultiplier.setAttribute("Value", "1");
		PropRisks_RTOClusterGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_RTOCode = new org.jdom2.Element("PropRisks_RTOCode");
		PropRisks_RTOCode.setAttribute("Value", "21170");
		PropRisks_RTOCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RallyCheck = new org.jdom2.Element("PropRisks_RallyCheck");
		PropRisks_RallyCheck.setAttribute("Value", "False");
		PropRisks_RallyCheck.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_RateForReliabOD = new org.jdom2.Element("PropRisks_RateForReliabOD");
		PropRisks_RateForReliabOD.setAttribute("Value", "60");
		PropRisks_RateForReliabOD.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_RateForReliabTP = new org.jdom2.Element("PropRisks_RateForReliabTP");
		PropRisks_RateForReliabTP.setAttribute("Value", "25");
		PropRisks_RateForReliabTP.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_RegistrationNoAsPerOldLogic = new org.jdom2.Element(
				"PropRisks_RegistrationNoAsPerOldLogic");
		PropRisks_RegistrationNoAsPerOldLogic.setAttribute("Value", "False");
		PropRisks_RegistrationNoAsPerOldLogic.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_RegistrationNumber = new org.jdom2.Element("PropRisks_RegistrationNumber");
		PropRisks_RegistrationNumber.setAttribute("Value", jsonResult.getString("VehicleRegistrationNumber1").trim());
		PropRisks_RegistrationNumber.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RegistrationNumber2 = new org.jdom2.Element("PropRisks_RegistrationNumber2");
		PropRisks_RegistrationNumber2.setAttribute("Value", jsonResult.getString("VehicleRegistrationNumber2").trim());
		PropRisks_RegistrationNumber2.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RegistrationNumber3 = new org.jdom2.Element("PropRisks_RegistrationNumber3");
		PropRisks_RegistrationNumber3.setAttribute("Value", jsonResult.getString("VehicleRegistrationNumber3").trim());
		PropRisks_RegistrationNumber3.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RegistrationNumber4 = new org.jdom2.Element("PropRisks_RegistrationNumber4");
		PropRisks_RegistrationNumber4.setAttribute("Value", jsonResult.getString("VehicleRegistrationNumber4").trim());
		PropRisks_RegistrationNumber4.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RegistrationNumber5 = new org.jdom2.Element("PropRisks_RegistrationNumber5");
		PropRisks_RegistrationNumber5.setAttribute("Value", "");
		PropRisks_RegistrationNumber5.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Relationship_Value = new org.jdom2.Element("PropRisks_Relationship");
		PropRisks_Relationship_Value.setAttribute("Value", "OTHERS");
		PropRisks_Relationship_Value.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ReliabilityTrialCheck = new org.jdom2.Element("PropRisks_ReliabilityTrialCheck");
		PropRisks_ReliabilityTrialCheck.setAttribute("Value", "True");
		PropRisks_ReliabilityTrialCheck.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_Reliabilitytrialorracingdate = new org.jdom2.Element(
				"PropRisks_Reliabilitytrialorracingdate");
		PropRisks_Reliabilitytrialorracingdate.setAttribute("Value", "26/02/2016");
		PropRisks_Reliabilitytrialorracingdate.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Reliabilitytrialorracingtodate = new org.jdom2.Element(
				"PropRisks_Reliabilitytrialorracingtodate");
		PropRisks_Reliabilitytrialorracingtodate.setAttribute("Value", "26/02/2016");
		PropRisks_Reliabilitytrialorracingtodate.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RestrictedToGarage = new org.jdom2.Element("PropRisks_RestrictedToGarage");
		PropRisks_RestrictedToGarage.setAttribute("Value", "False");
		PropRisks_RestrictedToGarage.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_ReturnToInvoice = new org.jdom2.Element("PropRisks_ReturnToInvoice");
		PropRisks_ReturnToInvoice.setAttribute("Value", jsonResult.getString("ReturnToInvoice").trim());
		PropRisks_ReturnToInvoice.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_RiskVariant = new org.jdom2.Element("PropRisks_RiskVariant");
		PropRisks_RiskVariant.setAttribute("Value", "ComprehensivePolicy");
		PropRisks_RiskVariant.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RiskVariantSection = new org.jdom2.Element("PropRisks_RiskVariantSection");
		PropRisks_RiskVariantSection.setAttribute("Value", "");
		PropRisks_RiskVariantSection.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_RoadSideAsistancePrm = new org.jdom2.Element("PropRisks_RoadSideAsistancePrm");
		PropRisks_RoadSideAsistancePrm.setAttribute("Value", "500");
		PropRisks_RoadSideAsistancePrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_RoadsideAssistance = new org.jdom2.Element("PropRisks_RoadsideAssistance");
		PropRisks_RoadsideAssistance.setAttribute("Value", jsonResult.getString("RoadsideAssistance").trim());
		PropRisks_RoadsideAssistance.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_SeatingCapacity = new org.jdom2.Element("PropRisks_SeatingCapacity");
		PropRisks_SeatingCapacity.setAttribute("Value", "5");
		PropRisks_SeatingCapacity.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_SourcingChannelGLMConsmCvr = new org.jdom2.Element(
				"PropRisks_SourcingChannelGLMConsmCvr");
		PropRisks_SourcingChannelGLMConsmCvr.setAttribute("Value", "1");
		PropRisks_SourcingChannelGLMConsmCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_SourcingChannelGLMDepCvr = new org.jdom2.Element(
				"PropRisks_SourcingChannelGLMDepCvr");
		PropRisks_SourcingChannelGLMDepCvr.setAttribute("Value", "1");
		PropRisks_SourcingChannelGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_SourcingChannelGLMEngProt = new org.jdom2.Element(
				"PropRisks_SourcingChannelGLMEngProt");
		PropRisks_SourcingChannelGLMEngProt.setAttribute("Value", "1");
		PropRisks_SourcingChannelGLMEngProt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_SourcingChannelGLMRetInv = new org.jdom2.Element(
				"PropRisks_SourcingChannelGLMRetInv");
		PropRisks_SourcingChannelGLMRetInv.setAttribute("Value", "1");
		PropRisks_SourcingChannelGLMRetInv.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_SpecialCondition = new org.jdom2.Element("PropRisks_SpecialCondition");
		PropRisks_SpecialCondition.setAttribute("Value", "eeee");
		PropRisks_SpecialCondition.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_SriLanka = new org.jdom2.Element("PropRisks_SriLanka");
		PropRisks_SriLanka.setAttribute("Value", "True");
		PropRisks_SriLanka.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_SumInsuredPaidDriver = new org.jdom2.Element("PropRisks_SumInsuredPaidDriver");
		PropRisks_SumInsuredPaidDriver.setAttribute("Value", "20000");
		PropRisks_SumInsuredPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_TACMakeCode = new org.jdom2.Element("PropRisks_TACMakeCode");
		PropRisks_TACMakeCode.setAttribute("Value", "");
		PropRisks_TACMakeCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_TPCoverCndtn = new org.jdom2.Element("PropRisks_TPCoverCndtn");
		PropRisks_TPCoverCndtn.setAttribute("Value", "False");
		PropRisks_TPCoverCndtn.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_TPPDRestricted = new org.jdom2.Element("PropRisks_TPPDRestricted");
		PropRisks_TPPDRestricted.setAttribute("Value", "True");
		PropRisks_TPPDRestricted.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_ToDateForRacing = new org.jdom2.Element("PropRisks_ToDateForRacing");
		PropRisks_ToDateForRacing.setAttribute("Value", "");
		PropRisks_ToDateForRacing.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_ToDateForRallies = new org.jdom2.Element("PropRisks_ToDateForRallies");
		PropRisks_ToDateForRallies.setAttribute("Value", "");
		PropRisks_ToDateForRallies.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_TotalInsuredDeclaredValue = new org.jdom2.Element(
				"PropRisks_TotalInsuredDeclaredValue");
		PropRisks_TotalInsuredDeclaredValue.setAttribute("Value", "829777");
		PropRisks_TotalInsuredDeclaredValue.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_TrailerODPrm = new org.jdom2.Element("PropRisks_TrailerODPrm");
		PropRisks_TrailerODPrm.setAttribute("Value", "250");
		PropRisks_TrailerODPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_TrailerRegistrationNumber = new org.jdom2.Element(
				"PropRisks_TrailerRegistrationNumber");
		PropRisks_TrailerRegistrationNumber.setAttribute("Value", "MH01SA1230");
		PropRisks_TrailerRegistrationNumber.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_TrailerchassisNumber = new org.jdom2.Element("PropRisks_TrailerchassisNumber");
		PropRisks_TrailerchassisNumber.setAttribute("Value", "444444");
		PropRisks_TrailerchassisNumber.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_TypeOfRoad = new org.jdom2.Element("PropRisks_TypeOfRoad");
		PropRisks_TypeOfRoad.setAttribute("Value", "Hilly Road");
		PropRisks_TypeOfRoad.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_TypeofPolicyHolder = new org.jdom2.Element("PropRisks_TypeofPolicyHolder");
		PropRisks_TypeofPolicyHolder.setAttribute("Value", "Individual Owner");
		PropRisks_TypeofPolicyHolder.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_TypeofPolicyHolderGLM = new org.jdom2.Element("PropRisks_TypeofPolicyHolderGLM");
		PropRisks_TypeofPolicyHolderGLM.setAttribute("Value", "0.94");
		PropRisks_TypeofPolicyHolderGLM.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_TypeofTransmission = new org.jdom2.Element("PropRisks_TypeofTransmission");
		PropRisks_TypeofTransmission.setAttribute("Value", "MANUAL");
		PropRisks_TypeofTransmission.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Typeofbody = new org.jdom2.Element("PropRisks_Typeofbody");
		PropRisks_Typeofbody.setAttribute("Value", "");
		PropRisks_Typeofbody.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_UserType = new org.jdom2.Element("PropRisks_UserType");
		PropRisks_UserType.setAttribute("Value", "");
		PropRisks_UserType.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_Variant = new org.jdom2.Element("PropRisks_Variant");
		PropRisks_Variant.setAttribute("Value", "");
		PropRisks_Variant.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_VariantCode = new org.jdom2.Element("PropRisks_VariantCode");
		PropRisks_VariantCode.setAttribute("Value", "1354");
		PropRisks_VariantCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_VehcleFitWithFibrGlasFuelTank = new org.jdom2.Element(
				"PropRisks_VehcleFitWithFibrGlasFuelTank");
		PropRisks_VehcleFitWithFibrGlasFuelTank.setAttribute("Value", "True");
		PropRisks_VehcleFitWithFibrGlasFuelTank.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_VehicelSegmentGLMConsmCvr = new org.jdom2.Element(
				"PropRisks_VehicelSegmentGLMConsmCvr");
		PropRisks_VehicelSegmentGLMConsmCvr.setAttribute("Value", "1");
		PropRisks_VehicelSegmentGLMConsmCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicelSegmentGLMEngProt = new org.jdom2.Element(
				"PropRisks_VehicelSegmentGLMEngProt");
		PropRisks_VehicelSegmentGLMEngProt.setAttribute("Value", "1");
		PropRisks_VehicelSegmentGLMEngProt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicelSegmentGLMRetInv = new org.jdom2.Element(
				"PropRisks_VehicelSegmentGLMRetInv");
		PropRisks_VehicelSegmentGLMRetInv.setAttribute("Value", "1");
		PropRisks_VehicelSegmentGLMRetInv.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicleAgeGLMConsmCvr = new org.jdom2.Element("PropRisks_VehicleAgeGLMConsmCvr");
		PropRisks_VehicleAgeGLMConsmCvr.setAttribute("Value", "1.14");
		PropRisks_VehicleAgeGLMConsmCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicleAgeGLMEngProt = new org.jdom2.Element("PropRisks_VehicleAgeGLMEngProt");
		PropRisks_VehicleAgeGLMEngProt.setAttribute("Value", "1.14");
		PropRisks_VehicleAgeGLMEngProt.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicleAgeGLMMultiplier = new org.jdom2.Element(
				"PropRisks_VehicleAgeGLMMultiplier");
		PropRisks_VehicleAgeGLMMultiplier.setAttribute("Value", "1.14");
		PropRisks_VehicleAgeGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicleAge_Mandatary = new org.jdom2.Element("PropRisks_VehicleAge_Mandatary");
		PropRisks_VehicleAge_Mandatary.setAttribute("Value", "0.175");
		PropRisks_VehicleAge_Mandatary.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicleInspection = new org.jdom2.Element("PropRisks_VehicleInspection");
		PropRisks_VehicleInspection.setAttribute("Value", "False");
		PropRisks_VehicleInspection.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_VehicleSegment = new org.jdom2.Element("PropRisks_VehicleSegment");
		PropRisks_VehicleSegment.setAttribute("Value", "");
		PropRisks_VehicleSegment.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_VehicleSegmentGLMDepCvr = new org.jdom2.Element(
				"PropRisks_VehicleSegmentGLMDepCvr");
		PropRisks_VehicleSegmentGLMDepCvr.setAttribute("Value", "1");
		PropRisks_VehicleSegmentGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicleSegmentGLMMultiplier = new org.jdom2.Element(
				"PropRisks_VehicleSegmentGLMMultiplier");
		PropRisks_VehicleSegmentGLMMultiplier.setAttribute("Value", "1");
		PropRisks_VehicleSegmentGLMMultiplier.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_VehicleSubTypeGLMMultiplier = new org.jdom2.Element(
				"PropRisks_VehicleSubTypeGLMMultiplier");
		PropRisks_VehicleSubTypeGLMMultiplier.setAttribute("Value", "0");
		PropRisks_VehicleSubTypeGLMMultiplier.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VehicleType = new org.jdom2.Element("PropRisks_VehicleType");
		PropRisks_VehicleType.setAttribute("Value", "Indigenous");
		PropRisks_VehicleType.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_VehicleTypeCode = new org.jdom2.Element("PropRisks_VehicleTypeCode");
		PropRisks_VehicleTypeCode.setAttribute("Value", "45");
		PropRisks_VehicleTypeCode.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_VehicleUse = new org.jdom2.Element("PropRisks_VehicleUse");
		PropRisks_VehicleUse.setAttribute("Value", "Driving Tuitions");
		PropRisks_VehicleUse.setAttribute("Type", "String");

		org.jdom2.Element PropRisks_VlntryDedctbleFrDprctnCover = new org.jdom2.Element(
				"PropRisks_VlntryDedctbleFrDprctnCover");
		PropRisks_VlntryDedctbleFrDprctnCover.setAttribute("Value", "1000");
		PropRisks_VlntryDedctbleFrDprctnCover.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VolDepCoverDiscPrm = new org.jdom2.Element("PropRisks_VolDepCoverDiscPrm");
		PropRisks_VolDepCoverDiscPrm.setAttribute("Value", "0");
		PropRisks_VolDepCoverDiscPrm.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VoluntaryDeductGLMDepCvr = new org.jdom2.Element(
				"PropRisks_VoluntaryDeductGLMDepCvr");
		PropRisks_VoluntaryDeductGLMDepCvr.setAttribute("Value", "1.06");
		PropRisks_VoluntaryDeductGLMDepCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_VoluntaryDeductibleAmount = new org.jdom2.Element(
				"PropRisks_VoluntaryDeductibleAmount");
		PropRisks_VoluntaryDeductibleAmount.setAttribute("Value", "2500");
		PropRisks_VoluntaryDeductibleAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropRisks_Whethervehicleisdeclined = new org.jdom2.Element(
				"PropRisks_Whethervehicleisdeclined");
		PropRisks_Whethervehicleisdeclined.setAttribute("Value", "False");
		PropRisks_Whethervehicleisdeclined.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_WiderLegalLiabilityToPaid = new org.jdom2.Element(
				"PropRisks_WiderLegalLiabilityToPaid");
		PropRisks_WiderLegalLiabilityToPaid.setAttribute("Value", jsonResult.getString("WiderLegalLiabilityToPaidDriverCleanerConductorIMT28").trim());
		PropRisks_WiderLegalLiabilityToPaid.setAttribute("Type", "Boolean");

		org.jdom2.Element PropRisks_Zone_Mandatary = new org.jdom2.Element("PropRisks_Zone_Mandatary");
		PropRisks_Zone_Mandatary.setAttribute("Value", "Zone-A");
		PropRisks_Zone_Mandatary.setAttribute("Type", "String");

		/////////////////////////////////////////////////////////////////

		otherDetailsGrid();
		riskCoverDetailsGrid();
		generalPurposeInfo();
		policyLoadingDiscDetails();
		customerDetails();
		otherMappingFiles();
		

		org.jdom2.Element PersonalDetails = new org.jdom2.Element("PersonalDetails");

		org.jdom2.Element CustomerDetails = new org.jdom2.Element("CustomerDetails");

		org.jdom2.Element CustomerId = new org.jdom2.Element("CustomerId");
		CustomerId.setAttribute("Name", "Customer ID");
		CustomerId.setAttribute("Value", "");
		CustomerId.setAttribute("Type", "Double");

		org.jdom2.Element Salutation = new org.jdom2.Element("Salutation");
		Salutation.setAttribute("Name", "Salutation");
		Salutation.setAttribute("Value", "");
		Salutation.setAttribute("Type", "String");

		org.jdom2.Element LastName = new org.jdom2.Element("LastName");
		LastName.setAttribute("Name", "LastName");
		LastName.setAttribute("Value", "");
		LastName.setAttribute("Type", "String");

		org.jdom2.Element ISUIICEmployee = new org.jdom2.Element("ISUIICEmployee");
		ISUIICEmployee.setAttribute("Name", "ISUIICEmployee");
		ISUIICEmployee.setAttribute("Value", "N");
		ISUIICEmployee.setAttribute("Type", "Char");

		org.jdom2.Element DOB = new org.jdom2.Element("DOB");
		DOB.setAttribute("Name", "DOB");
		DOB.setAttribute("Value", "");
		DOB.setAttribute("Type", "String");

		org.jdom2.Element FirstName = new org.jdom2.Element("FirstName");
		FirstName.setAttribute("Name", "FirstName");
		FirstName.setAttribute("Value", "");
		FirstName.setAttribute("Type", "String");

		org.jdom2.Element CustomerType = new org.jdom2.Element("CustomerType");
		CustomerType.setAttribute("Name", "CustomerType");
		CustomerType.setAttribute("Value", "");
		CustomerType.setAttribute("Type", "String");

		org.jdom2.Element PermanentLocationSameAsMailLocation = new org.jdom2.Element(
				"PermanentLocationSameAsMailLocation");
		PermanentLocationSameAsMailLocation.setAttribute("Name", "PermanentLocationSameAsMailLocation");
		PermanentLocationSameAsMailLocation.setAttribute("Value", "False");
		PermanentLocationSameAsMailLocation.setAttribute("Type", "Boolean");

		org.jdom2.Element Gender = new org.jdom2.Element("Gender");
		Gender.setAttribute("Name", "Gender");
		Gender.setAttribute("Value", "");
		Gender.setAttribute("Type", "String");

		org.jdom2.Element PanNo = new org.jdom2.Element("PanNo");
		PanNo.setAttribute("Name", "PanNo");
		PanNo.setAttribute("Value", "");
		PanNo.setAttribute("Type", "String");

		org.jdom2.Element EmailId = new org.jdom2.Element("EmailId");
		EmailId.setAttribute("Name", "EmailId");
		EmailId.setAttribute("Value", "");
		EmailId.setAttribute("Type", "String");

		rootelement.addContent(ProposalDetails);
		ProposalDetails.addContent(RiskDetails);
		RiskDetails.addContent(Block);
		Block.addContent(PropRisks_AAOmembershipno);
		Block.addContent(PropRisks_AddOnDiscnt);
		Block.addContent(PropRisks_Age_Value);
		Block.addContent(PropRisks_AgeCoverageDetails);
		Block.addContent(PropRisks_AntiTheftCheck);
		Block.addContent(PropRisks_ApplicableDocuments);
		Block.addContent(PropRisks_AuthorityLocation);
		Block.addContent(PropRisks_AutoMobileAssocName);
		Block.addContent(PropRisks_AutomobileAssociC);
		Block.addContent(PropRisks_Bangladesh);
		Block.addContent(PropRisks_BasisOfRating);
		Block.addContent(PropRisks_Bhutan);
		Block.addContent(PropRisks_BreakInInsurance);
		Block.addContent(PropRisks_BreakInInsuranceGLMMulti);
		Block.addContent(PropRisks_BusinessChnlGLMMultiplier);
		Block.addContent(PropRisks_CNGLPGkitValue);
		Block.addContent(PropRisks_CSD_Value);
		Block.addContent(PropRisks_CapitalSIPerPerson);
		Block.addContent(PropRisks_CertifiedVintageCarByCarClub);
		Block.addContent(PropRisks_ChasisNo);
		Block.addContent(PropRisks_ChassisNumber);
		Block.addContent(PropRisks_ChkForGeographical);
		Block.addContent(PropRisks_ChkForRacing);
		Block.addContent(PropRisks_CityCode);
		Block.addContent(PropRisks_ClaimFreeYearGLMDepCvr);
		Block.addContent(PropRisks_Colorofthevehicle);
		Block.addContent(PropRisks_CompForVolExc1);
		Block.addContent(PropRisks_CompForVolExc2);
		Block.addContent(PropRisks_CompForVolExc3);
		Block.addContent(PropRisks_CompForVolExc4);
		Block.addContent(PropRisks_CompulsoryExcess);
		Block.addContent(PropRisks_CompulsoryPAwithOwnerDriver);
		Block.addContent(PropRisks_CountryCode);
		Block.addContent(PropRisks_CrossSellDiscount);
		Block.addContent(PropRisks_CrossSellDiscountGLM);
		Block.addContent(PropRisks_CubicCapacity);
		Block.addContent(PropRisks_CvrPrm);
		Block.addContent(PropRisks_DateofBirth);
		Block.addContent(PropRisks_DateofBirthofNominee);
		Block.addContent(PropRisks_DateofRegistration);
		Block.addContent(PropRisks_Dateofpurchase);
		Block.addContent(PropRisks_DepreciationReimbursement);
		Block.addContent(PropRisks_DepriciationCvrPrm);
		Block.addContent(PropRisks_DiscntPrm);
		Block.addContent(PropRisks_DiscountOnIMTRate);
		Block.addContent(PropRisks_DiscountforOptingSoftCopy);
		Block.addContent(PropRisks_DistrictCode);
		Block.addContent(PropRisks_Drivingexperience);
		Block.addContent(PropRisks_Educationalqualification);
		Block.addContent(PropRisks_ElectricalAccessories);
		Block.addContent(PropRisks_EmergencyMedicalExpenses);
		Block.addContent(PropRisks_EmployeesDiscount);
		Block.addContent(PropRisks_EnginePretectPrm);
		Block.addContent(PropRisks_EngineSecure);
		Block.addContent(PropRisks_Engineno);
		Block.addContent(PropRisks_EntitledforNCB);
		Block.addContent(PropRisks_EventName);
		Block.addContent(PropRisks_FirstName);
		Block.addContent(PropRisks_ForUWLoading);
		Block.addContent(PropRisks_FromDateForRacing);
		Block.addContent(PropRisks_FromDateForRallies);
		Block.addContent(PropRisks_FromDateUForm);
		Block.addContent(PropRisks_FuelType);
		Block.addContent(PropRisks_FuelTypeGLMConsmCvr);
		Block.addContent(PropRisks_FuelTypeGLMDepCvr);
		Block.addContent(PropRisks_FuelTypeGLMEngProt);
		Block.addContent(PropRisks_FuelTypeGLMMultiplier);
		Block.addContent(PropRisks_FuelTypeGLMRetInv);
		Block.addContent(PropRisks_GLMRate);
		Block.addContent(PropRisks_GeneralRemarks);
		Block.addContent(PropRisks_Geographicalareaextention);
		Block.addContent(PropRisks_IDVFlag);
		Block.addContent(PropRisks_IDVofTrailer);
		Block.addContent(PropRisks_IDVofthevehicle);
		Block.addContent(PropRisks_IfOthersPleasespecify);
		Block.addContent(PropRisks_ImposedExcessAmount);
		Block.addContent(PropRisks_ImprtdVehcleWoutPaymtCustmDuty);
		Block.addContent(PropRisks_InsuredAge);
		Block.addContent(PropRisks_InsuredAgeGLMMultiplier);
		Block.addContent(PropRisks_InsuredCreditScore);
		Block.addContent(PropRisks_InsuredCreditScoreGLM);
		Block.addContent(PropRisks_InsuredGender);
		Block.addContent(PropRisks_InsuredGenderGLMMultiplier);
		Block.addContent(PropRisks_InsuredHasDrivinglicense);
		Block.addContent(PropRisks_InsuredProfession);
		Block.addContent(PropRisks_InsuredProfessionGLMMulti);
		Block.addContent(PropRisks_InsureddrivingScore);
		Block.addContent(PropRisks_InsureddrivingScoreGLM);
		Block.addContent(PropRisks_InteractionWithRatingFactor);
		Block.addContent(PropRisks_InteractionWithRtngFactGLM);
		Block.addContent(PropRisks_IsExternalCNGLPGAvailable);
		Block.addContent(PropRisks_IsNewRateApplicable);
		Block.addContent(PropRisks_IsNonElctrclAcesrsRequired);
		Block.addContent(PropRisks_IsPAToOwnerDriverExcluded);
		Block.addContent(PropRisks_IsTrailerAvailable);
		Block.addContent(PropRisks_IsVehcleUsdForDrivngTuitions);
		Block.addContent(PropRisks_IsVehicle);
		Block.addContent(PropRisks_IsVehicleUsedforblindpersons);
		Block.addContent(PropRisks_IsVehicleusedlimitedtopremises);
		Block.addContent(PropRisks_Issuer);
		Block.addContent(PropRisks_KotakGrpEmployeeDiscount);
		Block.addContent(PropRisks_LastName);
		Block.addContent(PropRisks_LdngPrm);
		Block.addContent(PropRisks_LegalLiabilityToEmployees);
		Block.addContent(PropRisks_LiabilityToSoldierSailorAirMan);
		Block.addContent(PropRisks_LiscenseNo);
		Block.addContent(PropRisks_LoadEmrgencyMedicalExp);
		Block.addContent(PropRisks_LoadHomeProtectSublt);
		Block.addContent(PropRisks_LoadingDiscountOnAddOnCovers);
		Block.addContent(PropRisks_LoadingNewCarRplacmnt);
		Block.addContent(PropRisks_LocationCode);
		Block.addContent(PropRisks_MainDriver);
		Block.addContent(PropRisks_MakeGLMDepCvr);
		Block.addContent(PropRisks_MakeGLMEngProt);
		Block.addContent(PropRisks_MakeGLMMultiplier);
		Block.addContent(PropRisks_MakeGLMRetInv);
		Block.addContent(PropRisks_Maldives);
		Block.addContent(PropRisks_Manufacture);
		Block.addContent(PropRisks_ManufacturerCode);
		Block.addContent(PropRisks_ManufactureYear);
		Block.addContent(PropRisks_MarketMovement);
		Block.addContent(PropRisks_MarketMovementGLMMultiplier);
		Block.addContent(PropRisks_Model);
		Block.addContent(PropRisks_ModelCluster);
		Block.addContent(PropRisks_ModelClusterGLMDepCvr);
		Block.addContent(PropRisks_ModelClusterGLMMultiplier);
		Block.addContent(PropRisks_ModelCode);
		Block.addContent(PropRisks_ModelVariant);
		Block.addContent(PropRisks_NCBConfirmation);
		Block.addContent(PropRisks_NCPrm);
		Block.addContent(PropRisks_NameOfNominee);
		Block.addContent(PropRisks_Nepal);
		Block.addContent(PropRisks_NewCarReplacement);
		Block.addContent(PropRisks_NoClaimBonusApplicable);
		Block.addContent(PropRisks_NoOfClaimFreeYearsCompleted);
		Block.addContent(PropRisks_NoOfPersonWiderLegalLiability);
		Block.addContent(PropRisks_NoOfPersonsNamed);
		Block.addContent(PropRisks_NoOfPersonsPaidDriver);
		Block.addContent(PropRisks_NoOfSoldierSailorAirMan);
		Block.addContent(PropRisks_NonElectricalAccessories);
		Block.addContent(PropRisks_NoofClmFreeYearCmpltdGLM);
		Block.addContent(PropRisks_NoofWheels);
		Block.addContent(PropRisks_NumberofPersonsUnnamed);
		Block.addContent(PropRisks_Occupation);
		Block.addContent(PropRisks_OpenCvrNo);
		Block.addContent(PropRisks_OriginalNCBPercentage);
		Block.addContent(PropRisks_OtherChk1);
		Block.addContent(PropRisks_OtherChk2);
		Block.addContent(PropRisks_OtherChk3);
		Block.addContent(PropRisks_OtherChk4);
		Block.addContent(PropRisks_OtherInfo4);
		Block.addContent(PropRisks_OtherInfoP);
		Block.addContent(PropRisks_OtherInformation11);
		Block.addContent(PropRisks_OtherInformation12);
		Block.addContent(PropRisks_OtherInformation13);
		Block.addContent(PropRisks_OtherInformation14);
		Block.addContent(PropRisks_OtherInformation1N);
		Block.addContent(PropRisks_OtherInformation1T);
		Block.addContent(PropRisks_OtherInformation2n);
		Block.addContent(PropRisks_OtherInformation3T);
		Block.addContent(PropRisks_OtherInformation4N);
		Block.addContent(PropRisks_OtherInformation4T);
		Block.addContent(PropRisks_OtherInformation5N);
		Block.addContent(PropRisks_OtherNumeric11);
		Block.addContent(PropRisks_OtherNumeric12);
		Block.addContent(PropRisks_Pakistan);
		Block.addContent(PropRisks_PlaceofRegGLMDepCvr);
		Block.addContent(PropRisks_PolicyAge);
		Block.addContent(PropRisks_PolicyAgeGLMMultiplier);
		Block.addContent(PropRisks_PreInspectionNumber);
		Block.addContent(PropRisks_PrevYearNCB);
		Block.addContent(PropRisks_PromoterName);
		Block.addContent(PropRisks_ProposalType);
		Block.addContent(PropRisks_RTOCluster);
		Block.addContent(PropRisks_RTOClusterGLMMultiplier);
		Block.addContent(PropRisks_RTOCode);
		Block.addContent(PropRisks_RallyCheck);
		Block.addContent(PropRisks_RateForReliabOD);
		Block.addContent(PropRisks_RateForReliabTP);
		Block.addContent(PropRisks_RegistrationNoAsPerOldLogic);
		Block.addContent(PropRisks_RegistrationNumber);
		Block.addContent(PropRisks_RegistrationNumber2);
		Block.addContent(PropRisks_RegistrationNumber3);
		Block.addContent(PropRisks_RegistrationNumber4);
		Block.addContent(PropRisks_RegistrationNumber5);
		Block.addContent(PropRisks_Relationship_Value);
		Block.addContent(PropRisks_ReliabilityTrialCheck);
		Block.addContent(PropRisks_Reliabilitytrialorracingdate);
		Block.addContent(PropRisks_Reliabilitytrialorracingtodate);
		Block.addContent(PropRisks_RestrictedToGarage);
		Block.addContent(PropRisks_ReturnToInvoice);
		Block.addContent(PropRisks_RiskVariant);
		Block.addContent(PropRisks_RiskVariantSection);
		Block.addContent(PropRisks_RoadSideAsistancePrm);
		Block.addContent(PropRisks_RoadsideAssistance);
		Block.addContent(PropRisks_SeatingCapacity);
		Block.addContent(PropRisks_SourcingChannelGLMConsmCvr);
		Block.addContent(PropRisks_SourcingChannelGLMDepCvr);
		Block.addContent(PropRisks_SourcingChannelGLMEngProt);
		Block.addContent(PropRisks_SourcingChannelGLMRetInv);
		Block.addContent(PropRisks_SpecialCondition);
		Block.addContent(PropRisks_SriLanka);
		Block.addContent(PropRisks_SumInsuredPaidDriver);
		Block.addContent(PropRisks_TACMakeCode);
		Block.addContent(PropRisks_TPCoverCndtn);
		Block.addContent(PropRisks_TPPDRestricted);
		Block.addContent(PropRisks_ToDateForRacing);
		Block.addContent(PropRisks_ToDateForRallies);
		Block.addContent(PropRisks_TotalInsuredDeclaredValue);
		Block.addContent(PropRisks_TrailerODPrm);
		Block.addContent(PropRisks_TrailerRegistrationNumber);
		Block.addContent(PropRisks_TrailerchassisNumber);
		Block.addContent(PropRisks_TypeOfRoad);
		Block.addContent(PropRisks_TypeofPolicyHolder);
		Block.addContent(PropRisks_TypeofTransmission);
		Block.addContent(PropRisks_Typeofbody);
		Block.addContent(PropRisks_UserType);
		Block.addContent(PropRisks_Variant);
		Block.addContent(PropRisks_VariantCode);
		Block.addContent(PropRisks_VehcleFitWithFibrGlasFuelTank);
		Block.addContent(PropRisks_VehicelSegmentGLMConsmCvr);
		Block.addContent(PropRisks_VehicelSegmentGLMEngProt);
		Block.addContent(PropRisks_VehicleAgeGLMConsmCvr);
		Block.addContent(PropRisks_VehicleAgeGLMEngProt);
		Block.addContent(PropRisks_VehicleAgeGLMMultiplier);
		Block.addContent(PropRisks_VehicleAge_Mandatary);
		Block.addContent(PropRisks_VehicleInspection);
		Block.addContent(PropRisks_VehicleSegment);
		Block.addContent(PropRisks_VehicleSegmentGLMDepCvr);
		Block.addContent(PropRisks_VehicleSegmentGLMMultiplier);
		Block.addContent(PropRisks_VehicleSubTypeGLMMultiplier);
		Block.addContent(PropRisks_VehicleType);
		Block.addContent(PropRisks_VehicleTypeCode);
		Block.addContent(PropRisks_VehicleUse);
		Block.addContent(PropRisks_VlntryDedctbleFrDprctnCover);
		Block.addContent(PropRisks_VolDepCoverDiscPrm);
		Block.addContent(PropRisks_VoluntaryDeductGLMDepCvr);
		Block.addContent(PropRisks_VoluntaryDeductibleAmount);
		Block.addContent(PropRisks_Whethervehicleisdeclined);
		Block.addContent(PropRisks_WiderLegalLiabilityToPaid);
		Block.addContent(PropRisks_Zone_Mandatary);

		// Block.addContent(OtherDetailsGrid);
		// OtherDetailsGrid.addContent(NonElectricalAccessiories);
		// NonElectricalAccessiories.addContent(NonElectricalAccessiories1);
		// NonElectricalAccessiories1.addContent(Make);
		// NonElectricalAccessiories1.addContent(Model);
		// NonElectricalAccessiories1.addContent(Year);
		// NonElectricalAccessiories1.addContent(IDV);
		// NonElectricalAccessiories1.addContent(TypeofAccessories);
		// NonElectricalAccessiories1.addContent(Description);
		// NonElectricalAccessiories1.addContent(SerialNo);
		// NonElectricalAccessiories1.addContent(Remarks);
		//
		// OtherDetailsGrid.addContent(ElectricalAccessiories);
		// ElectricalAccessiories.addContent(ElectricalAccessiories1);
		// ElectricalAccessiories1.addContent(MakeElectricalAcc);
		// ElectricalAccessiories1.addContent(ModelElectricalAcc);
		// ElectricalAccessiories1.addContent(yearElectricalAcc);
		// ElectricalAccessiories1.addContent(IDVElectricalAcc);
		// ElectricalAccessiories1.addContent(TypeOfAccessoriesElectricalAcc);
		// ElectricalAccessiories1.addContent(DescriptionElectricalAcc);
		// ElectricalAccessiories1.addContent(SerialNoElectricalAcc);
		// ElectricalAccessiories1.addContent(RemarksElectricalAcc);
		//
		// OtherDetailsGrid.addContent(NamedpassengerNomineeDetail);
		// NamedpassengerNomineeDetail.addContent(NamedpassengerNomineeDetail1);
		// NamedpassengerNomineeDetail1.addContent(NamedPerson);
		// NamedpassengerNomineeDetail1.addContent(CapitalSIPerPerson);
		// NamedpassengerNomineeDetail1.addContent(Nominee);
		// NamedpassengerNomineeDetail1.addContent(Relationship);
		// NamedpassengerNomineeDetail1.addContent(AgeofNominee);
		// NamedpassengerNomineeDetail1.addContent(Nameofappointee);
		// NamedpassengerNomineeDetail1.addContent(RelationshipToNominee);
		//
		// OtherDetailsGrid.addContent(OwnerDriverNomineeDetails);
		// OwnerDriverNomineeDetails.addContent(OwnerDriverNomineeDetails1);
		// OwnerDriverNomineeDetails1.addContent(NomineeOwnerDriver);
		// OwnerDriverNomineeDetails1.addContent(Age);
		// OwnerDriverNomineeDetails1.addContent(RelationshipOwnerDriver);
		// OwnerDriverNomineeDetails1.addContent(NameofappointeeOwnerDriver);
		// OwnerDriverNomineeDetails1.addContent(Relationshiptonominee);
		//
		// OtherDetailsGrid.addContent(TrailerDetails);
		// TrailerDetails.addContent(TrailerDetails1);
		// TrailerDetails1.addContent(SI);
		//
		// OtherDetailsGrid.addContent(ElectronicAccessoriesDetails);
		// ElectronicAccessoriesDetails.addContent(ElectronicAccessoriesDetails1);
		// ElectronicAccessoriesDetails1.addContent(SIElectronicAccessoriesDetails);
		//
		// OtherDetailsGrid.addContent(NonElectricalAccessoriesDetails);
		// NonElectricalAccessoriesDetails.addContent(NonElectricalAccessoriesDetails1);
		// NonElectricalAccessoriesDetails1.addContent(SINonElectronicAccessoriesDetails);
		//
		// OtherDetailsGrid.addContent(CNGandLPGKitDetails);
		// CNGandLPGKitDetails.addContent(CNGandLPGKitDetails1);
		// CNGandLPGKitDetails1.addContent(SICng);
		//
		// OtherDetailsGrid.addContent(NamedPACoverDetails);
		// NamedPACoverDetails.addContent(NamedPACoverDetails1);
		// NamedPACoverDetails1.addContent(SINamedPACoverDetails1);
		//
		//
		// OtherDetailsGrid.addContent(UnnamedPAcoverDetails);
		// UnnamedPAcoverDetails.addContent(UnnamedPAcoverDetails1);
		// UnnamedPAcoverDetails1.addContent(SIUnNamedPACoverDetails1);
		//
		// OtherDetailsGrid.addContent(PaiddriverPAcoverDetails);
		// PaiddriverPAcoverDetails.addContent(PaiddriverPAcoverDetails1);
		// PaiddriverPAcoverDetails1.addContent(SIPaidDriver);
		//
		// Block.addContent(RiskCoverDetailsGrid);
		// RiskCoverDetailsGrid.addContent(RiskGroup);
		// RiskGroup.addContent(CoverSIComponant);
		// CoverSIComponant.addContent(PropCoverDetails_Applicable);
		// CoverSIComponant.addContent(PropCoverDetails_BaseTariffRate);
		// CoverSIComponant.addContent(PropCoverDetails_Commission);
		// CoverSIComponant.addContent(PropCoverDetails_CoverGroups);
		// CoverSIComponant.addContent(PropCoverDetails_CumEndSerTax);
		// CoverSIComponant.addContent(PropCoverDetails_CumalativEndPrem);
		// CoverSIComponant.addContent(PropCoverDetails_CumulativePremium);
		// CoverSIComponant.addContent(PropCoverDetails_CumulativeServicetax);
		// CoverSIComponant.addContent(PropCoverDetails_DifferentialSI);
		// CoverSIComponant.addContent(PropCoverDetails_EndorsementAmount);
		// CoverSIComponant.addContent(PropCoverDetails_FinalRate);
		// CoverSIComponant.addContent(PropCoverDetails_IsDataDeleted);
		// CoverSIComponant.addContent(PropCoverDetails_IsOldDataDeleted);
		// CoverSIComponant.addContent(PropCoverDetails_LoadingDiscount_Col);
		// CoverSIComponant.addContent(PropCoverDetails_MMCPCode);
		// CoverSIComponant.addContent(PropCoverDetails_Premium);
		// CoverSIComponant.addContent(PropCoverDetails_Rate);
		// CoverSIComponant.addContent(PropCoverDetails_SumInsured);
		// CoverSIComponant.addContent(PropCoverDetails_TariffRate);
		//
		//
		// RiskGroup.addContent(CoverSIComponantOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_ApplicableOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_BaseTariffRateOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_CommissionOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_CoverGroupsOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumEndSerTaxOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumalativEndPremOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumulativePremiumOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumulativeServicetaxOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_DifferentialSIOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_EndorsementAmountOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_FinalRateOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_IsDataDeletedOwnDamgae);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_IsOldDataDeletedOwnerDriver);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_LoadingDiscount_ColOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_MMCPCodeownDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_PremiumOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_RateOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_SumInsuredOwnDamage);
		// CoverSIComponantOwnDamage.addContent(PropCoverDetails_TariffRateOwnDamage);
		//
		//
		// RiskGroup.addContent(CoverSIComponantEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_ApplicableEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_BaseTariffRateEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_CommissionEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_CoverGroupsEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumEndSerTaxEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumalativEndPremEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumulativePremiumEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumulativeServicetaxEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_DifferentialSIEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_EndorsementAmountEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_FinalRateEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_IsDataDeletedEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_IsOldDataDeletedEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_LoadingDiscount_ColEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_MMCPCodeEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_PremiumEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_RateEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_SumInsuredEngineProtect);
		// CoverSIComponantEngineProtect.addContent(PropCoverDetails_TariffRateEngineProtect);
		//
		//
		// RiskGroup.addContent(CoverSIComponantRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_ApplicableRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_BaseTariffRateRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CommissionRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CoverGroupsRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumEndSerTaxRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumalativEndPremRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumulativePremiumRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumulativeServicetaxRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_DifferentialSIRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_EndorsementAmountRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_FinalRateRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_IsDataDeletedRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_IsOldDataDeletedRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_LoadingDiscount_ColRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_MMCPCodeRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_PremiumRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_RateRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_SumInsuredRetToInvoice);
		// CoverSIComponantRetToInvoice.addContent(PropCoverDetails_TariffRateRetToInvoice);
		//
		// RiskGroup.addContent(CoverSIComponantConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_ApplicableConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_BaseTariffRateConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CommissionRateConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CoverGroupsConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumEndSerTaxConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumalativEndPremConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumulativePremiumConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumulativeServicetaxConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_DifferentialSIConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_EndorsementAmountConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_FinalRateConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_IsDataDeletedConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_IsOldDataDeletedConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_LoadingDiscount_ColConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_MMCPCodeConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_PremiumConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_RateConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_SumInsuredConsumableCvr);
		// CoverSIComponantConsumableCvr.addContent(PropCoverDetails_TariffRateConsumableCvr);
		//
		//
		// RiskGroup.addContent(CoverSIComponantLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_ApplicableLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_BaseTariffRateLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_CommissionRateLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_CoverGroupsLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_CumEndSerTaxLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_CumalativEndPremLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_CumulativePremiumLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_CumulativeServicetaxLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_DifferentialSILegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_EndorsementAmountLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_FinalRateLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_IsDataDeletedLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_IsOldDataDeletedLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_LoadingDiscount_ColLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_MMCPCodeLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_PremiumLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_RateLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_SumInsuredLegalLib);
		// CoverSIComponantLegalLib.addContent(PropCoverDetails_TariffRateLegalLib);
		//
		// RiskGroup.addContent(CoverSIComponantLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_ApplicableLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_BaseTariffRateLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CommissionRateLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CoverGroupsLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumEndSerTaxLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumalativEndPremLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumulativePremiumLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumulativeServicetaxLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_DifferentialSILegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_EndorsementAmountLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_FinalRateLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_IsDataDeletedLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_IsOldDataDeletedLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_LoadingDiscount_ColLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_MMCPCodeLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_PremiumLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_RateLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_SumInsuredLegalLibEmp);
		// CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_TariffRateLegalLibEmp);
		//
		// RiskGroup.addContent(CoverSIComponantRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_ApplicableRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_BaseTariffRateRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_CommissionRateRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_CoverGroupsRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_CumEndSerTaxRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_CumalativEndPremRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_CumulativePremiumRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_CumulativeServicetaxRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_DifferentialSIRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_EndorsementAmountRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_FinalRateRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_IsDataDeletedRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_IsOldDataDeletedRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_LoadingDiscount_ColRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_MMCPCodeRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_PremiumRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_RateRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_SumInsuredRoadSide);
		// CoverSIComponantRoadSide.addContent(PropCoverDetails_TariffRateRoadSide);
		//
		// RiskGroup.addContent(CoverSIComponantRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_ApplicableRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_BaseTariffRateRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_CommissionRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_CoverGroupsRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumEndSerTaxRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumalativEndPremRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumulativePremiumRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumulativeServicetaxRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_DifferentialSIRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_EndorsementAmountRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_FinalRateRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_IsDataDeletedRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_IsOldDataDeletedRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_LoadingDiscount_ColRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_MMCPCodeRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_PremiumRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_RateRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_SumInsuredRalliesTp);
		// CoverSIComponantRalliesTp.addContent(PropCoverDetails_TariffRateRalliesTp);
		//
		// RiskGroup.addContent(CoverSIComponantDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_ApplicableDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_BaseTariffRateDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_CommissionDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_CoverGroupsDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_CumEndSerTaxDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_CumalativEndPremDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_CumulativePremiumDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_CumulativeServicetaxDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_DifferentialSIDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_EndorsementAmountDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_FinalRateDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_IsDataDeletedDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_IsOldDataDeletedDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_LoadingDiscount_ColDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_MMCPCodeDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_PremiumDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_RateDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_SumInsuredDprCvr);
		// CoverSIComponantDprCvr.addContent(PropCoverDetails_TariffRateDprCvr);
		//
		// RiskGroup.addContent(CoverSIComponantRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_ApplicableRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_BaseTariffRateRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_CommissionRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_CoverGroupsRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumEndSerTaxRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumalativEndPremRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumulativePremiumRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumulativeServicetaxRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_DifferentialSIRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_EndorsementAmountRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_FinalRateRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_IsDataDeletedRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_IsOldDataDeletedRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_LoadingDiscount_ColRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_MMCPCodeRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_PremiumRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_RateRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_SumInsuredRalliesOD);
		// CoverSIComponantRalliesOD.addContent(PropCoverDetails_TariffRateRalliesOD);
		//
		// RiskGroup.addContent(CoverSIComponantLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_LoadingDiscount_ColLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_ApplicableLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_BaseTariffRateLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CommissionLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CoverGroupsLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumEndSerTaxLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumalativEndPremLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumulativePremiumLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumulativeServicetaxLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_DifferentialSILibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_EndorsementAmountLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_FinalRateLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_IsDataDeletedLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_IsOldDataDeletedLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_MMCPCodeLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_PremiumLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_RateLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_SumInsuredLibToSoldier);
		// CoverSIComponantLibToSoldier.addContent(PropCoverDetails_TariffRateLibToSoldier);
		//
		// RiskGroup.addContent(CoverSIComponantGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_LoadingDiscount_ColGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_ApplicableGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_BaseTariffRateGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CommissionGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CoverGroupsGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumEndSerTaxGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumalativEndPremGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumulativePremiumGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumulativeServicetaxGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_DifferentialSIGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_EndorsementAmountGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_FinalRateGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_IsDataDeletedGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_IsOldDataDeletedGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_MMCPCodeGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_PremiumGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_RateGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_SumInsuredGeoExtTp);
		// CoverSIComponantGeoExtTp.addContent(PropCoverDetails_TariffRateGeoExtTp);
		//
		// RiskGroup.addContent(CoverSIComponantGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_LoadingDiscount_ColGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_ApplicableGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_BaseTariffRateGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CommissionGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CoverGroupsGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumEndSerTaxGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumalativEndPremGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumulativePremiumGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumulativeServicetaxGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_DifferentialSIGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_EndorsementAmountGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_FinalRateGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_IsDataDeletedGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_IsOldDataDeletedGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_MMCPCodeGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_PremiumGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_RateGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_SumInsuredGeoExtOD);
		// CoverSIComponantGeoExtOD.addContent(PropCoverDetails_TariffRateGeoExtOD);
		//
		// RiskGroup.addContent(CoverSIComponantOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_LoadingDiscount_ColOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_ApplicableOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_BaseTariffRateOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CommissionOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CoverGroupsOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumEndSerTaxOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumalativEndPremOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumulativePremiumOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumulativeServicetaxOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_DifferentialSIOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_EndorsementAmountOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_FinalRateOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_IsDataDeletedOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_IsOldDataDeletedOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_MMCPCodeOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_PremiumOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_RateOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_SumInsuredOwnerDriver);
		// CoverSIComponantOwnerDriver.addContent(PropCoverDetails_TariffRateOwnerDriver);
		//
		// RiskGroup.addContent(CoverSIComponantCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_LoadingDiscount_ColCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_ApplicableCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_BaseTariffRateCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_CommissionCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_CoverGroupsCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumEndSerTaxCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumalativEndPremCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumulativePremiumCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumulativeServicetaxCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_DifferentialSICngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_EndorsementAmountCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_FinalRateCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_IsDataDeletedCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_IsOldDataDeletedCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_MMCPCodeCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_PremiumCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_RateCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_SumInsuredCngKitTp);
		// CoverSIComponantCngKitTp.addContent(PropCoverDetails_TariffRateCngKitTp);
		//
		//
		//
		// RiskCoverDetailsGrid.addContent(RiskGroupTrailer);
		// RiskGroupTrailer.addContent(CoverSIComponantTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_LoadingDiscount_ColTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_ApplicableTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_BaseTariffRateTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_CommissionTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_CoverGroupsTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumEndSerTaxTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumalativEndPremTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumulativePremiumTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumulativeServicetaxTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_DifferentialSITrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_EndorsementAmountTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_FinalRateTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_IsDataDeletedTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_IsOldDataDeletedTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_MMCPCodeTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_PremiumTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_RateTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_SumInsuredTrailerOD);
		// CoverSIComponantTrailerOD.addContent(PropCoverDetails_TariffRateTrailerOD);
		//
		// RiskGroupTrailer.addContent(CoverSIComponantTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_LoadingDiscount_ColTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_ApplicableTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_BaseTariffRateTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_CommissionTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_CoverGroupsTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumEndSerTaxTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumalativEndPremTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumulativePremiumTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumulativeServicetaxTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_DifferentialSITrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_EndorsementAmountTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_FinalRateTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_IsDataDeletedTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_IsOldDataDeletedTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_MMCPCodeTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_PremiumTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_RateTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_SumInsuredTrailerTp);
		// CoverSIComponantTrailerTp.addContent(PropCoverDetails_TariffRateTrailerTp);
		//
		// RiskCoverDetailsGrid.addContent(RiskGroupElectAccessories);
		// RiskGroupElectAccessories.addContent(CoverSIComponantElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_LoadingDiscount_ColElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_ApplicableElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_BaseTariffRateElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_CommissionElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_CoverGroupsElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumEndSerTaxElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumalativEndPremElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumulativePremiumElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumulativeServicetaxElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_DifferentialSIElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_EndorsementAmountElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_FinalRateElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_IsDataDeletedElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_IsOldDataDeletedElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_MMCPCodeElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_PremiumElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_RateElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_SumInsuredElectAccessories);
		// CoverSIComponantElectAccessories.addContent(PropCoverDetails_TariffRateElectAccessories);
		//
		// RiskCoverDetailsGrid.addContent(RiskGroupNonElectAccessories);
		// RiskGroupNonElectAccessories.addContent(CoverSIComponantNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_LoadingDiscount_ColNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_ApplicableNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_BaseTariffRateNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CommissionNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CoverGroupsNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumEndSerTaxNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumalativEndPremNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumulativePremiumNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumulativeServicetaxNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_DifferentialSINonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_EndorsementAmountNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_FinalRateNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_IsDataDeletedNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_IsOldDataDeletedNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_MMCPCodeNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_PremiumNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_RateNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_SumInsuredNonElectAccessories);
		// CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_TariffRateNonElectAccessories);
		//
		//
		// RiskCoverDetailsGrid.addContent(RiskGroupCngLpg);
		// RiskGroupCngLpg.addContent(CoverSIComponantCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_LoadingDiscount_ColCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_ApplicableCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_BaseTariffRateCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_CommissionCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_CoverGroupsCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_CumEndSerTaxCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_CumalativEndPremCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_CumulativePremiumCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_CumulativeServicetaxCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_DifferentialSICngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_EndorsementAmountCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_FinalRateCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_IsDataDeletedCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_IsOldDataDeletedCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_MMCPCodeCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_PremiumNonCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_RateCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_SumInsuredCngLpg);
		// CoverSIComponantCngLpg.addContent(PropCoverDetails_TariffRateCngLpg);
		//
		// RiskCoverDetailsGrid.addContent(RiskGroupNamedPa);
		// RiskGroupNamedPa.addContent(CoverSIComponantNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_LoadingDiscount_ColNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_ApplicableNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_BaseTariffRateNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_CommissionNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_CoverGroupsNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_CumEndSerTaxNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_CumalativEndPremNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_CumulativePremiumNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_CumulativeServicetaxNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_DifferentialSINamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_EndorsementAmountNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_FinalRateNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_IsDataDeletedNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_IsOldDataDeletedNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_MMCPCodeNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_PremiumNonNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_RateNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_SumInsuredNamedPa);
		// CoverSIComponantNamedPa.addContent(PropCoverDetails_TariffRateNamedPa);
		//
		//
		// RiskCoverDetailsGrid.addContent(RiskGroupUnNamedPa);
		// RiskGroupUnNamedPa.addContent(CoverSIComponantUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_LoadingDiscount_ColUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_ApplicableUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_BaseTariffRateUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CommissionUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CoverGroupsUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumEndSerTaxUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumalativEndPremUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumulativePremiumUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumulativeServicetaxUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_DifferentialSIUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_EndorsementAmountUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_FinalRateUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_IsDataDeletedUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_IsOldDataDeletedUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_MMCPCodeUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_PremiumNonUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_RateUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_SumInsuredUnNamedPa);
		// CoverSIComponantUnNamedPa.addContent(PropCoverDetails_TariffRateUnNamedPa);
		//
		//
		// RiskCoverDetailsGrid.addContent(RiskGroupPaidDriver);
		// RiskGroupPaidDriver.addContent(CoverSIComponantPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_LoadingDiscount_ColPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_ApplicablePaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_BaseTariffRatePaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_CommissionPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_CoverGroupsPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumEndSerTaxPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumalativEndPremPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumulativePremiumPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumulativeServicetaxPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_DifferentialSIPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_EndorsementAmountPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_FinalRatePaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_IsDataDeletedPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_IsOldDataDeletedPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_MMCPCodePaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_PremiumNonPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_RatePaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_SumInsuredPaidDriver);
		// CoverSIComponantPaidDriver.addContent(PropCoverDetails_TariffRatePaidDriver);
		//

		// ProposalDetails.addContent(GeneralPurposeInformation);

		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());

		xx = xmlOutput.outputString(document2);
		try {
			xmlOutput.output(document2, new FileWriter("D:\\getKotakProposalRequest.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File fXmlFile = new File("D:\\file.xml");
		}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return xx;
	}

	private void otherDetailsGrid() throws JSONException {
		org.jdom2.Element OtherDetailsGrid = new org.jdom2.Element("OtherDetailsGrid");

		org.jdom2.Element NonElectricalAccessiories = new org.jdom2.Element("NonElectricalAccessiories");
		NonElectricalAccessiories.setAttribute("Name", "NonElectricalAccessiories");
		NonElectricalAccessiories.setAttribute("Value", "GRP288");

		org.jdom2.Element NonElectricalAccessiories1 = new org.jdom2.Element("NonElectricalAccessiories");
		NonElectricalAccessiories1.setAttribute("Type", "GroupData");

		org.jdom2.Element Make = new org.jdom2.Element("Make");
		Make.setAttribute("Name", "Make");
		Make.setAttribute("Value", jsonResult.getString("Make").trim());
		Make.setAttribute("Type", "String");

		org.jdom2.Element Model = new org.jdom2.Element("Model");
		Model.setAttribute("Name", "Model");
		Model.setAttribute("Value", "hghg");
		Model.setAttribute("Type", "String");

		org.jdom2.Element Year = new org.jdom2.Element("Year");
		Year.setAttribute("Name", "Year");
		Year.setAttribute("Value", "2016");
		Year.setAttribute("Type", "Double");

		org.jdom2.Element IDV = new org.jdom2.Element("IDV");
		IDV.setAttribute("Name", "IDV");
		IDV.setAttribute("Value", jsonResult.getString("IDV").trim());
		IDV.setAttribute("Type", "Double");

		org.jdom2.Element TypeofAccessories = new org.jdom2.Element("TypeofAccessories");
		TypeofAccessories.setAttribute("Name", "TypeofAccessories");
		TypeofAccessories.setAttribute("Value", "Lugguage Carrier");
		TypeofAccessories.setAttribute("Type", "String");

		org.jdom2.Element Description = new org.jdom2.Element("Description");
		Description.setAttribute("Name", "Description");
		Description.setAttribute("Value", "dgdg");
		Description.setAttribute("Type", "String");

		org.jdom2.Element SerialNo = new org.jdom2.Element("SerialNo");
		SerialNo.setAttribute("Name", "SerialNo");
		SerialNo.setAttribute("Value", "h1111");
		SerialNo.setAttribute("Type", "String");

		org.jdom2.Element Remarks = new org.jdom2.Element("Remarks");
		Remarks.setAttribute("Name", "Remarks");
		Remarks.setAttribute("Value", "jjjj");
		Remarks.setAttribute("Type", "String");

		org.jdom2.Element ElectricalAccessiories = new org.jdom2.Element("ElectricalAccessiories");
		ElectricalAccessiories.setAttribute("Name", "ElectricalAccessiories");
		ElectricalAccessiories.setAttribute("Value", "GRP289");
		ElectricalAccessiories.setAttribute("Type", "String");

		org.jdom2.Element ElectricalAccessiories1 = new org.jdom2.Element("ElectricalAccessiories");
		ElectricalAccessiories1.setAttribute("Type", "GroupData");

		org.jdom2.Element MakeElectricalAcc = new org.jdom2.Element("Make");
		MakeElectricalAcc.setAttribute("Name", "Make");
		MakeElectricalAcc.setAttribute("Value", "K11");
		MakeElectricalAcc.setAttribute("Type", "String");

		org.jdom2.Element ModelElectricalAcc = new org.jdom2.Element("Model");
		ModelElectricalAcc.setAttribute("Name", "Model");
		ModelElectricalAcc.setAttribute("Value", jsonResult.getString("Model").trim());
		ModelElectricalAcc.setAttribute("Type", "String");

		org.jdom2.Element yearElectricalAcc = new org.jdom2.Element("Year");
		yearElectricalAcc.setAttribute("Name", "Year");
		yearElectricalAcc.setAttribute("Value", "K11");
		yearElectricalAcc.setAttribute("Type", "String");

		org.jdom2.Element IDVElectricalAcc = new org.jdom2.Element("IDV");
		IDVElectricalAcc.setAttribute("Name", "IDV");
		IDVElectricalAcc.setAttribute("Value", "15000");
		IDVElectricalAcc.setAttribute("Type", "Double");

		org.jdom2.Element TypeOfAccessoriesElectricalAcc = new org.jdom2.Element("TypeofAccessories");
		TypeOfAccessoriesElectricalAcc.setAttribute("Name", "TypeofAccessories");
		TypeOfAccessoriesElectricalAcc.setAttribute("Value", "Fan");
		TypeOfAccessoriesElectricalAcc.setAttribute("Type", "String");

		org.jdom2.Element DescriptionElectricalAcc = new org.jdom2.Element("Description");
		DescriptionElectricalAcc.setAttribute("Name", "Description");
		DescriptionElectricalAcc.setAttribute("Value", "IIIII");
		DescriptionElectricalAcc.setAttribute("Type", "String");

		org.jdom2.Element SerialNoElectricalAcc = new org.jdom2.Element("SerialNo");
		SerialNoElectricalAcc.setAttribute("Name", "SerialNo");
		SerialNoElectricalAcc.setAttribute("Value", "I22");
		SerialNoElectricalAcc.setAttribute("Type", "String");

		org.jdom2.Element RemarksElectricalAcc = new org.jdom2.Element("Remarks");
		RemarksElectricalAcc.setAttribute("Name", "Remarks");
		RemarksElectricalAcc.setAttribute("Value", "mmmm");
		RemarksElectricalAcc.setAttribute("Type", "String");

		org.jdom2.Element NamedpassengerNomineeDetail = new org.jdom2.Element("NamedpassengerNomineeDetail");
		NamedpassengerNomineeDetail.setAttribute("Name", "NamedpassengerNomineeDetail");
		NamedpassengerNomineeDetail.setAttribute("Value", "GRP291");

		org.jdom2.Element NamedpassengerNomineeDetail1 = new org.jdom2.Element("NamedpassengerNomineeDetail");
		NamedpassengerNomineeDetail1.setAttribute("Type", "GroupData");

		org.jdom2.Element NamedPerson = new org.jdom2.Element("NamedPerson");
		NamedPerson.setAttribute("Name", "NamedPerson");
		NamedPerson.setAttribute("Value", "nnnnnn");
		NamedPerson.setAttribute("Type", "String");

		org.jdom2.Element CapitalSIPerPerson = new org.jdom2.Element("CapitalSIPerPerson");
		CapitalSIPerPerson.setAttribute("Name", "CapitalSIPerPerson");
		CapitalSIPerPerson.setAttribute("Value", "100000");
		CapitalSIPerPerson.setAttribute("Type", "Double");

		org.jdom2.Element Nominee = new org.jdom2.Element("Nominee");
		Nominee.setAttribute("Name", "Nominee");
		Nominee.setAttribute("Value", "ooooo");
		Nominee.setAttribute("Type", "String");

		org.jdom2.Element Relationship = new org.jdom2.Element("Relationship");
		Relationship.setAttribute("Name", "Relationship");
		Relationship.setAttribute("Value", "Dependent Son");
		Relationship.setAttribute("Type", "String");

		org.jdom2.Element AgeofNominee = new org.jdom2.Element("AgeofNominee");
		AgeofNominee.setAttribute("Name", "AgeofNominee");
		AgeofNominee.setAttribute("Value", "16");
		AgeofNominee.setAttribute("Type", "Double");

		org.jdom2.Element Nameofappointee = new org.jdom2.Element("Nameofappointee");
		Nameofappointee.setAttribute("Name", "Nameofappointee");
		Nameofappointee.setAttribute("Value", "sfsd");
		Nameofappointee.setAttribute("Type", "String");

		org.jdom2.Element RelationshipToNominee = new org.jdom2.Element("RelationshipToNominee");
		RelationshipToNominee.setAttribute("Name", "RelationshipToNominee");
		RelationshipToNominee.setAttribute("Value", "Dependent Son");
		RelationshipToNominee.setAttribute("Type", "String");

		org.jdom2.Element OwnerDriverNomineeDetails = new org.jdom2.Element("OwnerDriverNomineeDetails");
		OwnerDriverNomineeDetails.setAttribute("Name", "OwnerDriverNomineeDetails");
		OwnerDriverNomineeDetails.setAttribute("Value", "Grp292");

		org.jdom2.Element OwnerDriverNomineeDetails1 = new org.jdom2.Element("OwnerDriverNomineeDetails");
		OwnerDriverNomineeDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element NomineeOwnerDriver = new org.jdom2.Element("Nominee");
		NomineeOwnerDriver.setAttribute("Name", "Nominee");
		NomineeOwnerDriver.setAttribute("Value", "qqqq");
		NomineeOwnerDriver.setAttribute("Type", "String");

		org.jdom2.Element NomineeDOB = new org.jdom2.Element("NomineeDOB");
		NomineeDOB.setAttribute("Name", "NomineeDOB");
		NomineeDOB.setAttribute("Value", "01/01/2001");
		NomineeDOB.setAttribute("Type", "String");

		org.jdom2.Element Age = new org.jdom2.Element("Age");
		Age.setAttribute("Name", "Age");
		Age.setAttribute("Value", "16");
		Age.setAttribute("Type", "Double");

		org.jdom2.Element RelationshipOwnerDriver = new org.jdom2.Element("Relationship");
		RelationshipOwnerDriver.setAttribute("Name", "Relationship");
		RelationshipOwnerDriver.setAttribute("Value", "OTHERS");
		RelationshipOwnerDriver.setAttribute("Type", "String");

		org.jdom2.Element NameofappointeeOwnerDriver = new org.jdom2.Element("Nameofappointee");
		NameofappointeeOwnerDriver.setAttribute("Name", "NameOfAppointee");
		NameofappointeeOwnerDriver.setAttribute("Value", "sfs");
		NameofappointeeOwnerDriver.setAttribute("Type", "String");

		org.jdom2.Element Relationshiptonominee = new org.jdom2.Element("Relationshiptonominee");
		Relationshiptonominee.setAttribute("Name", "Relationshiptonominee");
		Relationshiptonominee.setAttribute("Value", "Dependent Son");
		Relationshiptonominee.setAttribute("Type", "String");

		org.jdom2.Element TrailerDetails = new org.jdom2.Element("TrailerDetails");
		TrailerDetails.setAttribute("Name", "TrailerDetails");
		TrailerDetails.setAttribute("Value", "0");

		org.jdom2.Element TrailerDetails1 = new org.jdom2.Element("TrailerDetails");
		TrailerDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element SI = new org.jdom2.Element("SI");
		SI.setAttribute("Name", "SI");
		SI.setAttribute("Value", "40000");
		SI.setAttribute("Type", "Double");

		org.jdom2.Element ElectronicAccessoriesDetails = new org.jdom2.Element("ElectronicAccessoriesDetails");
		ElectronicAccessoriesDetails.setAttribute("Name", "Electronic Accessories");
		ElectronicAccessoriesDetails.setAttribute("Value", "0");

		org.jdom2.Element ElectronicAccessoriesDetails1 = new org.jdom2.Element("ElectronicAccessoriesDetails");
		ElectronicAccessoriesDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element SIElectronicAccessoriesDetails = new org.jdom2.Element("SI");
		SIElectronicAccessoriesDetails.setAttribute("Name", "SI");
		SIElectronicAccessoriesDetails.setAttribute("Value", "15000");
		SIElectronicAccessoriesDetails.setAttribute("Type", "Double");

		org.jdom2.Element NonElectricalAccessoriesDetails = new org.jdom2.Element("NonElectricalAccessoriesDetails");
		NonElectricalAccessoriesDetails.setAttribute("Name", "NonElectrical Accessories");
		NonElectricalAccessoriesDetails.setAttribute("Value", "0");

		org.jdom2.Element NonElectricalAccessoriesDetails1 = new org.jdom2.Element("NonElectricalAccessoriesDetails");
		NonElectricalAccessoriesDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element SINonElectronicAccessoriesDetails = new org.jdom2.Element("SI");
		SINonElectronicAccessoriesDetails.setAttribute("Name", "SI");
		SINonElectronicAccessoriesDetails.setAttribute("Value", "25000");
		SINonElectronicAccessoriesDetails.setAttribute("Type", "Double");

		org.jdom2.Element CNGandLPGKitDetails = new org.jdom2.Element("CNGandLPGKitDetails");
		CNGandLPGKitDetails.setAttribute("Name", "CNG and LPG Kit");
		CNGandLPGKitDetails.setAttribute("Value", "0");

		org.jdom2.Element CNGandLPGKitDetails1 = new org.jdom2.Element("CNGandLPGKitDetails");
		CNGandLPGKitDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element SICng = new org.jdom2.Element("SI");
		SICng.setAttribute("Name", "SI");
		SICng.setAttribute("Value", "50000");
		SICng.setAttribute("Type", "Double");

		org.jdom2.Element NamedPACoverDetails = new org.jdom2.Element("NamedPACoverDetails");
		NamedPACoverDetails.setAttribute("Name", "Named PA Cover");
		NamedPACoverDetails.setAttribute("Value", "0");

		org.jdom2.Element NamedPACoverDetails1 = new org.jdom2.Element("NamedPACoverDetails");
		NamedPACoverDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element SINamedPACoverDetails1 = new org.jdom2.Element("SI");
		SINamedPACoverDetails1.setAttribute("Name", "SI");
		SINamedPACoverDetails1.setAttribute("Value", "100000");
		SINamedPACoverDetails1.setAttribute("Type", "Double");

		org.jdom2.Element UnnamedPAcoverDetails = new org.jdom2.Element("UnnamedPAcoverDetails");
		UnnamedPAcoverDetails.setAttribute("Name", "Unnamed PA cover");
		UnnamedPAcoverDetails.setAttribute("Value", "0");

		org.jdom2.Element UnnamedPAcoverDetails1 = new org.jdom2.Element("UnnamedPAcoverDetails");
		UnnamedPAcoverDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element SIUnNamedPACoverDetails1 = new org.jdom2.Element("SI");
		SIUnNamedPACoverDetails1.setAttribute("Name", "SI");
		SIUnNamedPACoverDetails1.setAttribute("Value", "50000");
		SIUnNamedPACoverDetails1.setAttribute("Type", "Double");

		org.jdom2.Element PaiddriverPAcoverDetails = new org.jdom2.Element("PaiddriverPAcoverDetails");
		PaiddriverPAcoverDetails.setAttribute("Name", "Paid driver PA cover");
		PaiddriverPAcoverDetails.setAttribute("Value", "0");

		org.jdom2.Element PaiddriverPAcoverDetails1 = new org.jdom2.Element("PaiddriverPAcoverDetails");
//		PaiddriverPAcoverDetails1.setAttribute("Value", "GridData");
		PaiddriverPAcoverDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element SIPaidDriver = new org.jdom2.Element("SI");
		SIPaidDriver.setAttribute("Name", "SI");
		SIPaidDriver.setAttribute("Value", "80000");
		SIPaidDriver.setAttribute("Type", "Double");

		Block.addContent(OtherDetailsGrid);
		OtherDetailsGrid.addContent(NonElectricalAccessiories);
		NonElectricalAccessiories.addContent(NonElectricalAccessiories1);
		NonElectricalAccessiories1.addContent(Make);
		NonElectricalAccessiories1.addContent(Model);
		NonElectricalAccessiories1.addContent(Year);
		NonElectricalAccessiories1.addContent(IDV);
		NonElectricalAccessiories1.addContent(TypeofAccessories);
		NonElectricalAccessiories1.addContent(Description);
		NonElectricalAccessiories1.addContent(SerialNo);
		NonElectricalAccessiories1.addContent(Remarks);

		OtherDetailsGrid.addContent(ElectricalAccessiories);
		ElectricalAccessiories.addContent(ElectricalAccessiories1);
		ElectricalAccessiories1.addContent(MakeElectricalAcc);
		ElectricalAccessiories1.addContent(ModelElectricalAcc);
		ElectricalAccessiories1.addContent(yearElectricalAcc);
		ElectricalAccessiories1.addContent(IDVElectricalAcc);
		ElectricalAccessiories1.addContent(TypeOfAccessoriesElectricalAcc);
		ElectricalAccessiories1.addContent(DescriptionElectricalAcc);
		ElectricalAccessiories1.addContent(SerialNoElectricalAcc);
		ElectricalAccessiories1.addContent(RemarksElectricalAcc);

		OtherDetailsGrid.addContent(NamedpassengerNomineeDetail);
		NamedpassengerNomineeDetail.addContent(NamedpassengerNomineeDetail1);
		NamedpassengerNomineeDetail1.addContent(NamedPerson);
		NamedpassengerNomineeDetail1.addContent(CapitalSIPerPerson);
		NamedpassengerNomineeDetail1.addContent(Nominee);
		NamedpassengerNomineeDetail1.addContent(Relationship);
		NamedpassengerNomineeDetail1.addContent(AgeofNominee);
		NamedpassengerNomineeDetail1.addContent(Nameofappointee);
		NamedpassengerNomineeDetail1.addContent(RelationshipToNominee);

		OtherDetailsGrid.addContent(OwnerDriverNomineeDetails);
		OwnerDriverNomineeDetails.addContent(OwnerDriverNomineeDetails1);
		OwnerDriverNomineeDetails1.addContent(NomineeOwnerDriver);
		OwnerDriverNomineeDetails1.addContent(Age);
		OwnerDriverNomineeDetails1.addContent(RelationshipOwnerDriver);
		OwnerDriverNomineeDetails1.addContent(NameofappointeeOwnerDriver);
		OwnerDriverNomineeDetails1.addContent(Relationshiptonominee);

		OtherDetailsGrid.addContent(TrailerDetails);
		TrailerDetails.addContent(TrailerDetails1);
		TrailerDetails1.addContent(SI);

		OtherDetailsGrid.addContent(ElectronicAccessoriesDetails);
		ElectronicAccessoriesDetails.addContent(ElectronicAccessoriesDetails1);
		ElectronicAccessoriesDetails1.addContent(SIElectronicAccessoriesDetails);

		OtherDetailsGrid.addContent(NonElectricalAccessoriesDetails);
		NonElectricalAccessoriesDetails.addContent(NonElectricalAccessoriesDetails1);
		NonElectricalAccessoriesDetails1.addContent(SINonElectronicAccessoriesDetails);

		OtherDetailsGrid.addContent(CNGandLPGKitDetails);
		CNGandLPGKitDetails.addContent(CNGandLPGKitDetails1);
		CNGandLPGKitDetails1.addContent(SICng);

		OtherDetailsGrid.addContent(NamedPACoverDetails);
		NamedPACoverDetails.addContent(NamedPACoverDetails1);
		NamedPACoverDetails1.addContent(SINamedPACoverDetails1);

		OtherDetailsGrid.addContent(UnnamedPAcoverDetails);
		UnnamedPAcoverDetails.addContent(UnnamedPAcoverDetails1);
		UnnamedPAcoverDetails1.addContent(SIUnNamedPACoverDetails1);

		OtherDetailsGrid.addContent(PaiddriverPAcoverDetails);
		PaiddriverPAcoverDetails.addContent(PaiddriverPAcoverDetails1);
		PaiddriverPAcoverDetails1.addContent(SIPaidDriver);
	}

	private void riskCoverDetailsGrid() {

		org.jdom2.Element RiskCoverDetailsGrid = new org.jdom2.Element("RiskCoverDetailsGrid");

		org.jdom2.Element RiskGroup = new org.jdom2.Element("RiskGroup");
		RiskGroup.setAttribute("Name", "Vehicle Base Value");
		RiskGroup.setAttribute("Value", "Risk Wise Cover Details");
		RiskGroup.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponant = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponant.setAttribute("Name", "Basic TP including TPPD premium");
		CoverSIComponant.setAttribute("Value", "6000");
		CoverSIComponant.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_Applicable = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_Applicable.setAttribute("Name", "PropCoverDetails_Applicable ");
		PropCoverDetails_Applicable.setAttribute("Value", "True");
		PropCoverDetails_Applicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRate = new org.jdom2.Element("PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRate.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRate.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRate.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_Commission = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_Commission.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_Commission.setAttribute("Value", "0");
		PropCoverDetails_Commission.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroups = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroups.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroups.setAttribute("Value", "Basic TP including TPPD premium");
		PropCoverDetails_CoverGroups.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTax = new org.jdom2.Element("PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTax.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTax.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTax.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPrem = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPrem.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPrem.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPrem.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremium = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremium.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremium.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremium.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetax = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetax.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetax.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetax.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSI = new org.jdom2.Element("PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSI.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSI.setAttribute("Value", "6000");
		PropCoverDetails_DifferentialSI.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmount = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmount.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmount.setAttribute("Value", "1598");
		PropCoverDetails_EndorsementAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRate = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRate.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRate.setAttribute("Value", "0");
		PropCoverDetails_FinalRate.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeleted = new org.jdom2.Element("PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeleted.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeleted.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeleted.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeleted = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeleted.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeleted.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeleted.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_Col = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_Col.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_Col.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_Col.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_MMCPCode = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCode.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCode.setAttribute("Value", "");
		PropCoverDetails_MMCPCode.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_Premium = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_Premium.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_Premium.setAttribute("Value", "1598");
		PropCoverDetails_Premium.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_Rate = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_Rate.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_Rate.setAttribute("Value", "1598");
		PropCoverDetails_Rate.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsured = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsured.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsured.setAttribute("Value", "6000");
		PropCoverDetails_SumInsured.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRate = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRate.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRate.setAttribute("Value", "1598");
		PropCoverDetails_TariffRate.setAttribute("Type", "Double");

		/////////////////// BAsic TP including TPPD end//////////////

		////////////////// own damage start///////////////////

		org.jdom2.Element CoverSIComponantOwnDamage = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantOwnDamage.setAttribute("Name", "Own Damage");
		CoverSIComponantOwnDamage.setAttribute("Value", "699777");
		CoverSIComponantOwnDamage.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColOwnDamage.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColOwnDamage.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColOwnDamage.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableOwnDamage = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableOwnDamage.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableOwnDamage.setAttribute("Value", "True");
		PropCoverDetails_ApplicableOwnDamage.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateOwnDamage.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateOwnDamage.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionOwnDamage = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionOwnDamage.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionOwnDamage.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsOwnDamage = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsOwnDamage.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsOwnDamage.setAttribute("Value", "Own Damage");
		PropCoverDetails_CoverGroupsOwnDamage.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxOwnDamage.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxOwnDamage.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremOwnDamage.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremOwnDamage.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumOwnDamage.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumOwnDamage.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxOwnDamage.setAttribute("Name", "PropCoverDetails_CumulativeServicetax1");
		PropCoverDetails_CumulativeServicetaxOwnDamage.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIOwnDamage.setAttribute("Name", "PropCoverDetails_DifferentialSI1");
		PropCoverDetails_DifferentialSIOwnDamage.setAttribute("Value", "699777");
		PropCoverDetails_DifferentialSIOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountOwnDamage = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountOwnDamage.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountOwnDamage.setAttribute("Value", "15823.36");
		PropCoverDetails_EndorsementAmountOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateOwnDamage = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateOwnDamage.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateOwnDamage.setAttribute("Value", "0");
		PropCoverDetails_FinalRateOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedOwnDamgae = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedOwnDamgae.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedOwnDamgae.setAttribute("Value", "0");
		PropCoverDetails_IsDataDeletedOwnDamgae.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedownDamage = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedownDamage.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedownDamage.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedownDamage.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeownDamage = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeownDamage.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeownDamage.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeownDamage.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumOwnDamage = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumOwnDamage.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumOwnDamage.setAttribute("Value", "15823.36");
		PropCoverDetails_PremiumOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateOwnDamage = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateOwnDamage.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateOwnDamage.setAttribute("Value", "2.2612");
		PropCoverDetails_RateOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredOwnDamage = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredOwnDamage.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredOwnDamage.setAttribute("Value", "699777");
		PropCoverDetails_SumInsuredOwnDamage.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateOwnDamage = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateOwnDamage.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateOwnDamage.setAttribute("Value", "2.2612");
		PropCoverDetails_TariffRateOwnDamage.setAttribute("Type", "Double");

		//////////// Own Damage end//////////////////////

		///////////////// engine protect start///////////

		org.jdom2.Element CoverSIComponantEngineProtect = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantEngineProtect.setAttribute("Name", "Engine Protect");
		CoverSIComponantEngineProtect.setAttribute("Value", "789777");
		CoverSIComponantEngineProtect.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColEngineProtect.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColEngineProtect.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColEngineProtect.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableEngineProtect.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableEngineProtect.setAttribute("Value", "True");
		PropCoverDetails_ApplicableEngineProtect.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateEngineProtect.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateEngineProtect.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionEngineProtect.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionEngineProtect.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsEngineProtect.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsEngineProtect.setAttribute("Value", "Engine Protect");
		PropCoverDetails_CoverGroupsEngineProtect.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxEngineProtect.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxEngineProtect.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremEngineProtect.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremEngineProtect.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumEngineProtect.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumEngineProtect.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxEngineProtect.setAttribute("Name",
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxEngineProtect.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIEngineProtect.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIEngineProtect.setAttribute("Value", "789777");
		PropCoverDetails_DifferentialSIEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountEngineProtect.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountEngineProtect.setAttribute("Value", "6528.3");
		PropCoverDetails_EndorsementAmountEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateEngineProtect = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateEngineProtect.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateEngineProtect.setAttribute("Value", "0");
		PropCoverDetails_FinalRateEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedEngineProtect.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedEngineProtect.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedEngineProtect.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedEngineProtect.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedEngineProtect.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedEngineProtect.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeEngineProtect = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeEngineProtect.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeEngineProtect.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeEngineProtect.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumEngineProtect = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumEngineProtect.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumEngineProtect.setAttribute("Value", "6528.3");
		PropCoverDetails_PremiumEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateEngineProtect = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateEngineProtect.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateEngineProtect.setAttribute("Value", "0.8266");
		PropCoverDetails_RateEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredEngineProtect.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredEngineProtect.setAttribute("Value", "789777");
		PropCoverDetails_SumInsuredEngineProtect.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateEngineProtect = new org.jdom2.Element(
				"PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateEngineProtect.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateEngineProtect.setAttribute("Value", "0");
		PropCoverDetails_TariffRateEngineProtect.setAttribute("Type", "Double");

		////////////// Engine Protect End///////////////////////////

		////////////// Return To Invoice Start//////////////////

		org.jdom2.Element CoverSIComponantRetToInvoice = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantRetToInvoice.setAttribute("Name", "Return To Invoice");
		CoverSIComponantRetToInvoice.setAttribute("Value", "789777");
		CoverSIComponantRetToInvoice.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRetToInvoice.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRetToInvoice.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColRetToInvoice.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRetToInvoice.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRetToInvoice.setAttribute("Value", "True");
		PropCoverDetails_ApplicableRetToInvoice.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRetToInvoice.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRetToInvoice.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionRetToInvoice.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRetToInvoice.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRetToInvoice.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRetToInvoice.setAttribute("Value", "Return To Invoice");
		PropCoverDetails_CoverGroupsRetToInvoice.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRetToInvoice.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRetToInvoice.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRetToInvoice.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRetToInvoice.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRetToInvoice.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRetToInvoice.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRetToInvoice.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRetToInvoice.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRetToInvoice.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRetToInvoice.setAttribute("Value", "789777");
		PropCoverDetails_DifferentialSIRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRetToInvoice.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRetToInvoice.setAttribute("Value", "2469.63");
		PropCoverDetails_EndorsementAmountRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateRetToInvoice = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRetToInvoice.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRetToInvoice.setAttribute("Value", "0");
		PropCoverDetails_FinalRateRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRetToInvoice.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRetToInvoice.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedRetToInvoice.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRetToInvoice.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRetToInvoice.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedRetToInvoice.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeRetToInvoice = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRetToInvoice.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRetToInvoice.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeRetToInvoice.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumRetToInvoice = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumRetToInvoice.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumRetToInvoice.setAttribute("Value", "2469.63");
		PropCoverDetails_PremiumRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateRetToInvoice = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateRetToInvoice.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateRetToInvoice.setAttribute("Value", "0.3127");
		PropCoverDetails_RateRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRetToInvoice.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRetToInvoice.setAttribute("Value", "789777");
		PropCoverDetails_SumInsuredRetToInvoice.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateRetToInvoice = new org.jdom2.Element(
				"PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRetToInvoice.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRetToInvoice.setAttribute("Value", "0");
		PropCoverDetails_TariffRateRetToInvoice.setAttribute("Type", "Double");

		////////////////////// Return to invoice
		////////////////////// end////////////////////////////////////

		/////////////////// Consumable Cover
		/////////////////// Start//////////////////////////////////////

		org.jdom2.Element CoverSIComponantConsumableCvr = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantConsumableCvr.setAttribute("Name", "Consumable Cover");
		CoverSIComponantConsumableCvr.setAttribute("Value", "789777");
		CoverSIComponantConsumableCvr.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColConsumableCvr.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColConsumableCvr.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColConsumableCvr.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableConsumableCvr.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableConsumableCvr.setAttribute("Value", "True");
		PropCoverDetails_ApplicableConsumableCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateConsumableCvr.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateConsumableCvr.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRateConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateConsumableCvr.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateConsumableCvr.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionRateConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsConsumableCvr.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsConsumableCvr.setAttribute("Value", "Return To Invoice");
		PropCoverDetails_CoverGroupsConsumableCvr.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxConsumableCvr.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxConsumableCvr.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremConsumableCvr.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremConsumableCvr.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumConsumableCvr.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumConsumableCvr.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxConsumableCvr.setAttribute("Name",
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxConsumableCvr.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIConsumableCvr.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIConsumableCvr.setAttribute("Value", "789777");
		PropCoverDetails_DifferentialSIConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountConsumableCvr.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountConsumableCvr.setAttribute("Value", "2469.63");
		PropCoverDetails_EndorsementAmountConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateConsumableCvr = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateConsumableCvr.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateConsumableCvr.setAttribute("Value", "0");
		PropCoverDetails_FinalRateConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedConsumableCvr.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedConsumableCvr.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedConsumableCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedConsumableCvr.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedConsumableCvr.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedConsumableCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeConsumableCvr = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeConsumableCvr.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeConsumableCvr.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeConsumableCvr.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumConsumableCvr = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumConsumableCvr.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumConsumableCvr.setAttribute("Value", "2336.95");
		PropCoverDetails_PremiumConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateConsumableCvr = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateConsumableCvr.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateConsumableCvr.setAttribute("Value", "0.2959");
		PropCoverDetails_RateConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredConsumableCvr.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredConsumableCvr.setAttribute("Value", "789777");
		PropCoverDetails_SumInsuredConsumableCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateConsumableCvr = new org.jdom2.Element(
				"PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateConsumableCvr.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateConsumableCvr.setAttribute("Value", "0");
		PropCoverDetails_TariffRateConsumableCvr.setAttribute("Type", "Double");

		/////////////////// End Consumable Cover
		/////////////////// End////////////////////////////////

		///////////// Legal Liability for paid driver
		///////////// End/////////////////////////

		org.jdom2.Element CoverSIComponantLegalLib = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantLegalLib.setAttribute("Name", "Legal Liability for paid driver cleaner conductor");
		CoverSIComponantLegalLib.setAttribute("Value", "789777");
		CoverSIComponantLegalLib.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColLegalLib = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColLegalLib.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColLegalLib.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColLegalLib.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableLegalLib = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableLegalLib.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableLegalLib.setAttribute("Value", "True");
		PropCoverDetails_ApplicableLegalLib.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateLegalLib = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateLegalLib.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateLegalLib.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRateLegalLib = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateLegalLib.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateLegalLib.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionRateLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsLegalLib = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsLegalLib.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsLegalLib.setAttribute("Value", "Legal Liability for paid driver cleaner conductor");
		PropCoverDetails_CoverGroupsLegalLib.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxLegalLib = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxLegalLib.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxLegalLib.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremLegalLib = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremLegalLib.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremLegalLib.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumLegalLib = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumLegalLib.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumLegalLib.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxLegalLib = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxLegalLib.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxLegalLib.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSILegalLib = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSILegalLib.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSILegalLib.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSILegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountLegalLib = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountLegalLib.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountLegalLib.setAttribute("Value", "150");
		PropCoverDetails_EndorsementAmountLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateLegalLib = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateLegalLib.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateLegalLib.setAttribute("Value", "0");
		PropCoverDetails_FinalRateLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedLegalLib = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedLegalLib.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedLegalLib.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedLegalLib.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedLegalLib = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedLegalLib.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedLegalLib.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedLegalLib.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeLegalLib = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeLegalLib.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeLegalLib.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeLegalLib.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumLegalLib = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumLegalLib.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumLegalLib.setAttribute("Value", "150");
		PropCoverDetails_PremiumLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateLegalLib = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateLegalLib.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateLegalLib.setAttribute("Value", "50");
		PropCoverDetails_RateLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredLegalLib = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredLegalLib.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredLegalLib.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredLegalLib.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateLegalLib = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateLegalLib.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateLegalLib.setAttribute("Value", "50");
		PropCoverDetails_TariffRateLegalLib.setAttribute("Type", "Double");

		///////////// End Legal Liability for paid driver
		///////////// /////////////////////////

		///////////// Legal Liability for Employess Start
		///////////// /////////////////////////

		org.jdom2.Element CoverSIComponantLegalLibEmp = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantLegalLibEmp.setAttribute("Name",
				"Legal Liability for Employees other than paid driver conductor cleaner");
		CoverSIComponantLegalLibEmp.setAttribute("Value", "");
		CoverSIComponantLegalLibEmp.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColLegalLibEmp.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColLegalLibEmp.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColLegalLibEmp.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableLegalLibEmp = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableLegalLibEmp.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableLegalLibEmp.setAttribute("Value", "True");
		PropCoverDetails_ApplicableLegalLibEmp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateLegalLibEmp.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRateLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateLegalLibEmp.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_CommissionRateLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsLegalLibEmp.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsLegalLibEmp.setAttribute("Value",
				"Legal Liability for Employees other than paid driver conductor cleaner");
		PropCoverDetails_CoverGroupsLegalLibEmp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxLegalLibEmp.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremLegalLibEmp.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumLegalLibEmp.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxLegalLibEmp.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSILegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSILegalLibEmp.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSILegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSILegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountLegalLibEmp.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountLegalLibEmp.setAttribute("Value", "250");
		PropCoverDetails_EndorsementAmountLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateLegalLibEmp = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateLegalLibEmp.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_FinalRateLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedLegalLibEmp.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedLegalLibEmp.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedLegalLibEmp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedLegalLibEmp = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedLegalLibEmp.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedLegalLibEmp.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedLegalLibEmp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeLegalLibEmp = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeLegalLibEmp.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeLegalLibEmp.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeLegalLibEmp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumLegalLibEmp = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumLegalLibEmp.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumLegalLibEmp.setAttribute("Value", "250");
		PropCoverDetails_PremiumLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateLegalLibEmp = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateLegalLibEmp.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateLegalLibEmp.setAttribute("Value", "50");
		PropCoverDetails_RateLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredLegalLibEmp = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredLegalLibEmp.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredLegalLibEmp.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredLegalLibEmp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateLegalLibEmp = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateLegalLibEmp.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateLegalLibEmp.setAttribute("Value", "50");
		PropCoverDetails_TariffRateLegalLibEmp.setAttribute("Type", "Double");

		///////////// End Legal Liability for Employees
		///////////// /////////////////////////

		////////////// Road Side Assist
		////////////// Start///////////////////////////////////////

		org.jdom2.Element CoverSIComponantRoadSide = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantRoadSide.setAttribute("Name", "Road Side Assistance");
		CoverSIComponantRoadSide.setAttribute("Value", "");
		CoverSIComponantRoadSide.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColRoadSide = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRoadSide.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRoadSide.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColRoadSide.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableRoadSide = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRoadSide.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRoadSide.setAttribute("Value", "True");
		PropCoverDetails_ApplicableRoadSide.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateRoadSide = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRoadSide.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRoadSide.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRateRoadSide = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateRoadSide.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRateRoadSide.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionRateRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsRoadSide = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRoadSide.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRoadSide.setAttribute("Value", "Road Side Assistance");
		PropCoverDetails_CoverGroupsRoadSide.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxRoadSide = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRoadSide.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRoadSide.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremRoadSide = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRoadSide.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRoadSide.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumRoadSide = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRoadSide.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRoadSide.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxRoadSide = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRoadSide.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRoadSide.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIRoadSide = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRoadSide.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRoadSide.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSIRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountRoadSide = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRoadSide.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRoadSide.setAttribute("Value", "500");
		PropCoverDetails_EndorsementAmountRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateRoadSide = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRoadSide.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRoadSide.setAttribute("Value", "0");
		PropCoverDetails_FinalRateRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedRoadSide = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRoadSide.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRoadSide.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedRoadSide.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedRoadSide = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRoadSide.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRoadSide.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedRoadSide.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeRoadSide = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRoadSide.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRoadSide.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeRoadSide.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumRoadSide = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumRoadSide.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumRoadSide.setAttribute("Value", "500");
		PropCoverDetails_PremiumRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateRoadSide = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateRoadSide.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateRoadSide.setAttribute("Value", "500");
		PropCoverDetails_RateRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredRoadSide = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRoadSide.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRoadSide.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredRoadSide.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateRoadSide = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRoadSide.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRoadSide.setAttribute("Value", "0");
		PropCoverDetails_TariffRateRoadSide.setAttribute("Type", "Double");

		///////////// End Road Side///////////////////////////////////////

		////////////// Rallies TP Start///////////////////////////////////////

		org.jdom2.Element CoverSIComponantRalliesTp = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantRalliesTp.setAttribute("Name", "Rallies TP");
		CoverSIComponantRalliesTp.setAttribute("Value", "0");
		CoverSIComponantRalliesTp.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRalliesTp.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRalliesTp.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColRalliesTp.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableRalliesTp = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRalliesTp.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRalliesTp.setAttribute("Value", "True");
		PropCoverDetails_ApplicableRalliesTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRalliesTp.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRalliesTp = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionRalliesTp.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_CommissionRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsRalliesTp = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRalliesTp.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRalliesTp.setAttribute("Value", "Rallies TP");
		PropCoverDetails_CoverGroupsRalliesTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRalliesTp.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRalliesTp.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRalliesTp.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRalliesTp.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRalliesTp.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSIRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRalliesTp.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRalliesTp.setAttribute("Value", "25");
		PropCoverDetails_EndorsementAmountRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateRalliesTp = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRalliesTp.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_FinalRateRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRalliesTp.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRalliesTp.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedRalliesTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedRalliesTp = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRalliesTp.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRalliesTp.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedRalliesTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeRalliesTp = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRalliesTp.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRalliesTp.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeRalliesTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumRalliesTp = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumRalliesTp.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumRalliesTp.setAttribute("Value", "25");
		PropCoverDetails_PremiumRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateRalliesTp = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateRalliesTp.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateRalliesTp.setAttribute("Value", "25");
		PropCoverDetails_RateRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredRalliesTp = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRalliesTp.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredRalliesTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateRalliesTp = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRalliesTp.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRalliesTp.setAttribute("Value", "0");
		PropCoverDetails_TariffRateRalliesTp.setAttribute("Type", "Double");

		///////////// End Rallies TP///////////////////////////////////////

		///////////// Depreciation Cover
		///////////// Start///////////////////////////////////////

		org.jdom2.Element CoverSIComponantDprCvr = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantDprCvr.setAttribute("Name", "Depreciation Cover");
		CoverSIComponantDprCvr.setAttribute("Value", "789777");
		CoverSIComponantDprCvr.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColDprCvr = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColDprCvr.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColDprCvr.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColDprCvr.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableDprCvr = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableDprCvr.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableDprCvr.setAttribute("Value", "True");
		PropCoverDetails_ApplicableDprCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateDprCvr = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateDprCvr.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateDprCvr.setAttribute("Value", "17.5");
		PropCoverDetails_BaseTariffRateDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionDprCvr = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionDprCvr.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionDprCvr.setAttribute("Value", "0");
		PropCoverDetails_CommissionDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsDprCvr = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsDprCvr.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsDprCvr.setAttribute("Value", "Depreciation Cover");
		PropCoverDetails_CoverGroupsDprCvr.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxDprCvr = new org.jdom2.Element("PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxDprCvr.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxDprCvr.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremDprCvr = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremDprCvr.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremDprCvr.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumDprCvr = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumDprCvr.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumDprCvr.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxDprCvr = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxDprCvr.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxDprCvr.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIDprCvr = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIDprCvr.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIDprCvr.setAttribute("Value", "789777");
		PropCoverDetails_DifferentialSIDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountDprCvr = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountDprCvr.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountDprCvr.setAttribute("Value", "7947.53");
		PropCoverDetails_EndorsementAmountDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateDprCvr = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateDprCvr.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateDprCvr.setAttribute("Value", "0");
		PropCoverDetails_FinalRateDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedDprCvr = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedDprCvr.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedDprCvr.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedDprCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedDprCvr = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedDprCvr.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedDprCvr.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedDprCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeDprCvr = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeDprCvr.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeDprCvr.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeDprCvr.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumDprCvr = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumDprCvr.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumDprCvr.setAttribute("Value", "7947.53");
		PropCoverDetails_PremiumDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateDprCvr = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateDprCvr.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateDprCvr.setAttribute("Value", "1.0063");
		PropCoverDetails_RateDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredDprCvr = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredDprCvr.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredDprCvr.setAttribute("Value", "789777");
		PropCoverDetails_SumInsuredDprCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateDprCvr = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateDprCvr.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateDprCvr.setAttribute("Value", "0");
		PropCoverDetails_TariffRateDprCvr.setAttribute("Type", "Double");

		///////////// Deprec Cover End//////////////////

		/////////////////// Liability to soldier sailor
		/////////////////// airman//////////////////////////////

		org.jdom2.Element CoverSIComponantLibToSoldier = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantLibToSoldier.setAttribute("Name", "Liability to soldier sailor airman");
		CoverSIComponantLibToSoldier.setAttribute("Value", "0");
		CoverSIComponantLibToSoldier.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColLibToSoldier.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColLibToSoldier.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColLibToSoldier.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableLibToSoldier.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableLibToSoldier.setAttribute("Value", "True");
		PropCoverDetails_ApplicableLibToSoldier.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateLibToSoldier.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionLibToSoldier.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_CommissionLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsLibToSoldier.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsLibToSoldier.setAttribute("Value", "Liability to soldier sailor airman");
		PropCoverDetails_CoverGroupsLibToSoldier.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxLibToSoldier.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremLibToSoldier.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumLibToSoldier.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxLibToSoldier.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSILibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSILibToSoldier.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSILibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSILibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountLibToSoldier.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountLibToSoldier.setAttribute("Value", "100");
		PropCoverDetails_EndorsementAmountLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateLibToSoldier = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateLibToSoldier.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_FinalRateLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedLibToSoldier.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedLibToSoldier.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedLibToSoldier.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedLibToSoldier.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedLibToSoldier.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedLibToSoldier.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeLibToSoldier = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeLibToSoldier.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeLibToSoldier.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeLibToSoldier.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumLibToSoldier = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumLibToSoldier.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumLibToSoldier.setAttribute("Value", "100");
		PropCoverDetails_PremiumLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateLibToSoldier = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateLibToSoldier.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateLibToSoldier.setAttribute("Value", "100");
		PropCoverDetails_RateLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredLibToSoldier.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredLibToSoldier.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateLibToSoldier = new org.jdom2.Element(
				"PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateLibToSoldier.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateLibToSoldier.setAttribute("Value", "0");
		PropCoverDetails_TariffRateLibToSoldier.setAttribute("Type", "Double");

		///////////// End lib to soldier///////////////////////////////////////

		/////////////////// Geographical Extension
		/////////////////// tp//////////////////////////////
		org.jdom2.Element CoverSIComponantGeoExtTp = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantGeoExtTp.setAttribute("Name", "Geographical Extension TP");
		CoverSIComponantGeoExtTp.setAttribute("Value", "0");
		CoverSIComponantGeoExtTp.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColGeoExtTp.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColGeoExtTp.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColGeoExtTp.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableGeoExtTp = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableGeoExtTp.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableGeoExtTp.setAttribute("Value", "True");
		PropCoverDetails_ApplicableGeoExtTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateGeoExtTp.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionGeoExtTp = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionGeoExtTp.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_CommissionGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsGeoExtTp = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsGeoExtTp.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsGeoExtTp.setAttribute("Value", "Geographical Extension TP");
		PropCoverDetails_CoverGroupsGeoExtTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxGeoExtTp.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremGeoExtTp.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumGeoExtTp.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxGeoExtTp.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIGeoExtTp.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSIGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountGeoExtTp.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountGeoExtTp.setAttribute("Value", "100");
		PropCoverDetails_EndorsementAmountGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateGeoExtTp = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateGeoExtTp.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_FinalRateGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedGeoExtTp.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedGeoExtTp.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedGeoExtTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedGeoExtTp = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedGeoExtTp.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedGeoExtTp.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedGeoExtTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeGeoExtTp = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeGeoExtTp.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeGeoExtTp.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeGeoExtTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumGeoExtTp = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumGeoExtTp.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumGeoExtTp.setAttribute("Value", "100");
		PropCoverDetails_PremiumGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateGeoExtTp = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateGeoExtTp.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateGeoExtTp.setAttribute("Value", "100");
		PropCoverDetails_RateGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredGeoExtTp = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredGeoExtTp.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredGeoExtTp.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredGeoExtTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateGeoExtTp = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateGeoExtTp.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateGeoExtTp.setAttribute("Value", "100");
		PropCoverDetails_TariffRateGeoExtTp.setAttribute("Type", "Double");

		///////////// End Geo extension
		///////////// tp///////////////////////////////////////

		/////////////////// Geographical Extension
		/////////////////// tp//////////////////////////////

		org.jdom2.Element CoverSIComponantGeoExtOD = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantGeoExtOD.setAttribute("Name", "Geographical Extension OD");
		CoverSIComponantGeoExtOD.setAttribute("Value", "0");
		CoverSIComponantGeoExtOD.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColGeoExtOD.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColGeoExtOD.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColGeoExtOD.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableGeoExtOD = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableGeoExtOD.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableGeoExtOD.setAttribute("Value", "True");
		PropCoverDetails_ApplicableGeoExtOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateGeoExtOD.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionGeoExtOD = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionGeoExtOD.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionGeoExtOD.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsGeoExtOD = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsGeoExtOD.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsGeoExtOD.setAttribute("Value", "Geographical Extension OD");
		PropCoverDetails_CoverGroupsGeoExtOD.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxGeoExtOD.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremGeoExtOD.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumGeoExtOD.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxGeoExtOD.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIGeoExtOD.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSIGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountGeoExtOD.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountGeoExtOD.setAttribute("Value", "400");
		PropCoverDetails_EndorsementAmountGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateGeoExtOD = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateGeoExtOD.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_FinalRateGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedGeoExtOD.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedGeoExtOD.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedGeoExtOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedGeoExtOD = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedGeoExtOD.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedGeoExtOD.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedGeoExtOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeGeoExtOD = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeGeoExtOD.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeGeoExtOD.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeGeoExtOD.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumGeoExtOD = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumGeoExtOD.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumGeoExtOD.setAttribute("Value", "400");
		PropCoverDetails_PremiumGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateGeoExtOD = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateGeoExtOD.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateGeoExtOD.setAttribute("Value", "400");
		PropCoverDetails_RateGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredGeoExtOD = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredGeoExtOD.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredGeoExtOD.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredGeoExtOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateGeoExtOD = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateGeoExtOD.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateGeoExtOD.setAttribute("Value", "400");
		PropCoverDetails_TariffRateGeoExtOD.setAttribute("Type", "Double");

		///////////// End Geo extension
		///////////// OD///////////////////////////////////////

		///////////////////// Owner Drivaer///////////////////////////
		org.jdom2.Element CoverSIComponantOwnerDriver = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantOwnerDriver.setAttribute("Name", "Owner Driver");
		CoverSIComponantOwnerDriver.setAttribute("Value", "200000");
		CoverSIComponantOwnerDriver.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColOwnerDriver.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColOwnerDriver.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColOwnerDriver.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableOwnerDriver = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableOwnerDriver.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableOwnerDriver.setAttribute("Value", "True");
		PropCoverDetails_ApplicableOwnerDriver.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateOwnerDriver.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionOwnerDriver = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionOwnerDriver.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_CommissionOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsOwnerDriver.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsOwnerDriver.setAttribute("Value", "Geographical Extension OD");
		PropCoverDetails_CoverGroupsOwnerDriver.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxOwnerDriver.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremOwnerDriver.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumOwnerDriver.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxOwnerDriver.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIOwnerDriver.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIOwnerDriver.setAttribute("Value", "200000");
		PropCoverDetails_DifferentialSIOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountOwnerDriver.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountOwnerDriver.setAttribute("Value", "100");
		PropCoverDetails_EndorsementAmountOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateOwnerDriver = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateOwnerDriver.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_FinalRateOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedOwnerDriver.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedOwnerDriver.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedOwnerDriver.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedOwnerDriver = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedOwnerDriver.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedOwnerDriver.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedOwnerDriver.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeOwnerDriver = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeOwnerDriver.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeOwnerDriver.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeOwnerDriver.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumOwnerDriver = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumOwnerDriver.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumOwnerDriver.setAttribute("Value", "100");
		PropCoverDetails_PremiumOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateOwnerDriver = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateOwnerDriver.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateOwnerDriver.setAttribute("Value", "100");
		PropCoverDetails_RateOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredOwnerDriver = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredOwnerDriver.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredOwnerDriver.setAttribute("Value", "200000");
		PropCoverDetails_SumInsuredOwnerDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateOwnerDriver = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateOwnerDriver.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateOwnerDriver.setAttribute("Value", "0");
		PropCoverDetails_TariffRateOwnerDriver.setAttribute("Type", "Double");
		///////////// End Owner Driver///////////////////////////////////////

		///////////////////// CNG Kit TP///////////////////////////
		org.jdom2.Element CoverSIComponantCngKitTp = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantCngKitTp.setAttribute("Name", "CNG Kit TP");
		CoverSIComponantCngKitTp.setAttribute("Value", "0");
		CoverSIComponantCngKitTp.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColCngKitTp.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColCngKitTp.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColCngKitTp.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableCngKitTp = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableCngKitTp.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableCngKitTp.setAttribute("Value", "True");
		PropCoverDetails_ApplicableCngKitTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateCngKitTp.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionCngKitTp = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionCngKitTp.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_CommissionCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsCngKitTp = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsCngKitTp.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsCngKitTp.setAttribute("Value", "Geographical Extension OD");
		PropCoverDetails_CoverGroupsCngKitTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxCngKitTp.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremCngKitTp.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumCngKitTp.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxCngKitTp.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSICngKitTp = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSICngKitTp.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSICngKitTp.setAttribute("Value", "0");
		PropCoverDetails_DifferentialSICngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountCngKitTp.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountCngKitTp.setAttribute("Value", "60");
		PropCoverDetails_EndorsementAmountCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateCngKitTp = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateCngKitTp.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_FinalRateCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedCngKitTp.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedCngKitTp.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedCngKitTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedCngKitTp = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedCngKitTp.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedCngKitTp.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedCngKitTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeCngKitTp = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeCngKitTp.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeCngKitTp.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeCngKitTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumCngKitTp = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumCngKitTp.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumCngKitTp.setAttribute("Value", "60");
		PropCoverDetails_PremiumCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateCngKitTp = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateCngKitTp.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateCngKitTp.setAttribute("Value", "60");
		PropCoverDetails_RateCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredCngKitTp = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredCngKitTp.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredCngKitTp.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredCngKitTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateCngKitTp = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateCngKitTp.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateCngKitTp.setAttribute("Value", "60");
		PropCoverDetails_TariffRateCngKitTp.setAttribute("Type", "Double");
		///////////// End CNG Kit Tp///////////////////////////////////////

		//////////// Vehicle base Value End////////////////////////

		///////////////////// Risk Wise Cover Details///////////////////////////

		 org.jdom2.Element RiskGroupTrailer = new org.jdom2.Element(
		 "RiskGroup");
		 RiskGroupTrailer.setAttribute("Name","Trailer");
		 RiskGroupTrailer.setAttribute("Value","Risk Wise Cover Details");
		 RiskGroupTrailer.setAttribute("Type","GroupData");

		org.jdom2.Element CoverSIComponantRiskWiseCvr = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantRiskWiseCvr.setAttribute("Name", "Trailer");
		CoverSIComponantRiskWiseCvr.setAttribute("Value", "Risk Wise Cover Details");
		CoverSIComponantRiskWiseCvr.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRiskWiseCvr.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRiskWiseCvr.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColRiskWiseCvr.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRiskWiseCvr.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRiskWiseCvr.setAttribute("Value", "True");
		PropCoverDetails_ApplicableRiskWiseCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRiskWiseCvr.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRiskWiseCvr.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionRiskWiseCvr.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRiskWiseCvr.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRiskWiseCvr.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRiskWiseCvr.setAttribute("Value", "Trailer OD");
		PropCoverDetails_CoverGroupsRiskWiseCvr.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRiskWiseCvr.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRiskWiseCvr.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRiskWiseCvr.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRiskWiseCvr.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRiskWiseCvr.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRiskWiseCvr.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRiskWiseCvr.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRiskWiseCvr.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRiskWiseCvr.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRiskWiseCvr.setAttribute("Value", "40000");
		PropCoverDetails_DifferentialSIRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRiskWiseCvr.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRiskWiseCvr.setAttribute("Value", "250");
		PropCoverDetails_EndorsementAmountRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRiskWiseCvr.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRiskWiseCvr.setAttribute("Value", "0");
		PropCoverDetails_FinalRateRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRiskWiseCvr.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRiskWiseCvr.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedRiskWiseCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedRiskWiseCvr = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRiskWiseCvr.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRiskWiseCvr.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedRiskWiseCvr.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRiskWiseCvr.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRiskWiseCvr.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeRiskWiseCvr.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumRiskWiseCvr.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumRiskWiseCvr.setAttribute("Value", "250");
		PropCoverDetails_PremiumRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateRiskWiseCvr.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateRiskWiseCvr.setAttribute("Value", "0.005");
		PropCoverDetails_RateRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRiskWiseCvr.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRiskWiseCvr.setAttribute("Value", "40000");
		PropCoverDetails_SumInsuredRiskWiseCvr.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateRiskWiseCvr = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRiskWiseCvr.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRiskWiseCvr.setAttribute("Value", "0");
		PropCoverDetails_TariffRateRiskWiseCvr.setAttribute("Type", "Double");
		///////////// End Risk Wise Cover///////////////////////////////////////

		///////////////////// Trailer OD//////////////////////////

//		org.jdom2.Element RiskGroupTrailer = new org.jdom2.Element("RiskGroup");
//		RiskGroup.setAttribute("Name", "Trailer");
//		RiskGroup.setAttribute("Value", "Risk Wise Cover Details");
//		RiskGroup.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponantTrailerOD = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantTrailerOD.setAttribute("Name", "Trailer OD");
		CoverSIComponantTrailerOD.setAttribute("Value", "40000");
		CoverSIComponantTrailerOD.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColTrailerOD.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColTrailerOD.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColTrailerOD.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableTrailerOD = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableTrailerOD.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableTrailerOD.setAttribute("Value", "True");
		PropCoverDetails_ApplicableTrailerOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateTrailerOD.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateTrailerOD.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionTrailerOD = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionTrailerOD.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionTrailerOD.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsTrailerOD = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsTrailerOD.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsTrailerOD.setAttribute("Value", "Trailer OD");
		PropCoverDetails_CoverGroupsTrailerOD.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxTrailerOD.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxTrailerOD.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremTrailerOD.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremTrailerOD.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumTrailerOD.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumTrailerOD.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxTrailerOD.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxTrailerOD.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSITrailerOD = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSITrailerOD.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSITrailerOD.setAttribute("Value", "40000");
		PropCoverDetails_DifferentialSITrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountTrailerOD.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountTrailerOD.setAttribute("Value", "250");
		PropCoverDetails_EndorsementAmountTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateTrailerOD = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateTrailerOD.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateTrailerOD.setAttribute("Value", "0");
		PropCoverDetails_FinalRateTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedTrailerOD.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedTrailerOD.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedTrailerOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedTrailerOD = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedTrailerOD.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedTrailerOD.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedTrailerOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeTrailerOD = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeTrailerOD.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeTrailerOD.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeTrailerOD.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumTrailerOD = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumTrailerOD.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumTrailerOD.setAttribute("Value", "250");
		PropCoverDetails_PremiumTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateTrailerOD = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateTrailerOD.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateTrailerOD.setAttribute("Value", "0.005");
		PropCoverDetails_RateTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredTrailerOD = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredTrailerOD.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredTrailerOD.setAttribute("Value", "40000");
		PropCoverDetails_SumInsuredTrailerOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateTrailerOD = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateTrailerOD.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateTrailerOD.setAttribute("Value", "0.005");
		PropCoverDetails_TariffRateTrailerOD.setAttribute("Type", "Double");
		///////////////////// Trailer Od END/////////////////////////

		///////////////////// Trailer Tp///////////////////////////
		org.jdom2.Element CoverSIComponantTrailerTp = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantTrailerTp.setAttribute("Name", "Trailer TP");
		CoverSIComponantTrailerTp.setAttribute("Value", "0");
		CoverSIComponantTrailerTp.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColTrailerTp.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColTrailerTp.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColTrailerTp.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableTrailerTp = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableTrailerTp.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableTrailerTp.setAttribute("Value", "True");
		PropCoverDetails_ApplicableTrailerTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateTrailerTp.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateTrailerTp.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionTrailerTp = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionTrailerTp.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionTrailerTp.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsTrailerTp = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsTrailerTp.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsTrailerTp.setAttribute("Value", "Trailer TP");
		PropCoverDetails_CoverGroupsTrailerTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxTrailerTp.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxTrailerTp.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremTrailerTp.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremTrailerTp.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumTrailerTp.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumTrailerTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxTrailerTp.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxTrailerTp.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSITrailerTp = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSITrailerTp.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSITrailerTp.setAttribute("Value", "40000");
		PropCoverDetails_DifferentialSITrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountTrailerTp.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountTrailerTp.setAttribute("Value", "125");
		PropCoverDetails_EndorsementAmountTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateTrailerTp = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateTrailerTp.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateTrailerTp.setAttribute("Value", "0");
		PropCoverDetails_FinalRateTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedTrailerTp.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedTrailerTp.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedTrailerTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedTrailerTp = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedTrailerTp.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedTrailerTp.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedTrailerTp.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeTrailerTp = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeTrailerTp.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeTrailerTp.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeTrailerTp.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumTrailerTp = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumTrailerTp.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumTrailerTp.setAttribute("Value", "125");
		PropCoverDetails_PremiumTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateTrailerTp = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateTrailerTp.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateTrailerTp.setAttribute("Value", "125");
		PropCoverDetails_RateTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredTrailerTp = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredTrailerTp.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredTrailerTp.setAttribute("Value", "0");
		PropCoverDetails_SumInsuredTrailerTp.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateTrailerTp = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateTrailerTp.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateTrailerTp.setAttribute("Value", "125");
		PropCoverDetails_TariffRateTrailerTp.setAttribute("Type", "Double");
		///////////// End Trailer Tp///////////////////////////////////////

		///////////// Risk Group Trailer End/////////////////////////

		///////////////////// Electronic Accessories///////////////////////////
		org.jdom2.Element RiskGroupElectAccessories = new org.jdom2.Element("RiskGroup");
		RiskGroupElectAccessories.setAttribute("Name", "Electronic Accessories");
		RiskGroupElectAccessories.setAttribute("Value", "Risk Wise Cover");
		RiskGroupElectAccessories.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponantElectAccessories = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantElectAccessories.setAttribute("Name", "Electronic Accessories OD");
		CoverSIComponantElectAccessories.setAttribute("Value", "15000");
		CoverSIComponantElectAccessories.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColElectAccessories.setAttribute("Name",
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColElectAccessories.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColElectAccessories.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableElectAccessories.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableElectAccessories.setAttribute("Value", "True");
		PropCoverDetails_ApplicableElectAccessories.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateElectAccessories.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionElectAccessories.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionElectAccessories.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsElectAccessories.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsElectAccessories.setAttribute("Value", "Electronic Accessories");
		PropCoverDetails_CoverGroupsElectAccessories.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxElectAccessories.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremElectAccessories.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumElectAccessories.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxElectAccessories.setAttribute("Name",
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIElectAccessories.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIElectAccessories.setAttribute("Value", "15000");
		PropCoverDetails_DifferentialSIElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountElectAccessories.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountElectAccessories.setAttribute("Value", "600");
		PropCoverDetails_EndorsementAmountElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateElectAccessories.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_FinalRateElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedElectAccessories.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedElectAccessories.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedElectAccessories.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedElectAccessories.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedElectAccessories.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedElectAccessories.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeElectAccessories.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeElectAccessories.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeElectAccessories.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumElectAccessories = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumElectAccessories.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumElectAccessories.setAttribute("Value", "600");
		PropCoverDetails_PremiumElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateElectAccessories = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateElectAccessories.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateElectAccessories.setAttribute("Value", "4");
		PropCoverDetails_RateElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredElectAccessories.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredElectAccessories.setAttribute("Value", "15000");
		PropCoverDetails_SumInsuredElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateElectAccessories.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateElectAccessories.setAttribute("Value", "4");
		PropCoverDetails_TariffRateElectAccessories.setAttribute("Type", "Double");
		///////////// End Trailer Tp///////////////////////////////////////

		///////////////////// Non Electrical
		///////////////////// Accessories///////////////////////////
		org.jdom2.Element RiskGroupNonElectAccessories = new org.jdom2.Element("RiskGroup");
		RiskGroupNonElectAccessories.setAttribute("Name", "Non Electrical Accessories OD");
		RiskGroupNonElectAccessories.setAttribute("Value", "Risk Wise Cover Details");
		RiskGroupNonElectAccessories.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponantNonElectAccessories = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantNonElectAccessories.setAttribute("Name", "Electrical Accessories OD");
		CoverSIComponantNonElectAccessories.setAttribute("Value", "25000");
		CoverSIComponantNonElectAccessories.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColNonElectAccessories.setAttribute("Name",
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColNonElectAccessories.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColNonElectAccessories.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableNonElectAccessories.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableNonElectAccessories.setAttribute("Value", "True");
		PropCoverDetails_ApplicableNonElectAccessories.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateNonElectAccessories.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateNonElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_Commission");
		PropCoverDetails_CommissionNonElectAccessories.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionNonElectAccessories.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsNonElectAccessories.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsNonElectAccessories.setAttribute("Value", "Electronic Accessories");
		PropCoverDetails_CoverGroupsNonElectAccessories.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxNonElectAccessories.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxNonElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremNonElectAccessories.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremNonElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumNonElectAccessories.setAttribute("Name",
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumNonElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxNonElectAccessories.setAttribute("Name",
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxNonElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSINonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSINonElectAccessories.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSINonElectAccessories.setAttribute("Value", "25000");
		PropCoverDetails_DifferentialSINonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountNonElectAccessories.setAttribute("Name",
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountNonElectAccessories.setAttribute("Value", "565.3");
		PropCoverDetails_EndorsementAmountNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateNonElectAccessories.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateNonElectAccessories.setAttribute("Value", "0");
		PropCoverDetails_FinalRateNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedNonElectAccessories.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedNonElectAccessories.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedNonElectAccessories.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedNonElectAccessories.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedNonElectAccessories.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedNonElectAccessories.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeNonElectAccessories.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeNonElectAccessories.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeNonElectAccessories.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonElectAccessories.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonElectAccessories.setAttribute("Value", "565.3");
		PropCoverDetails_PremiumNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateNonElectAccessories = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateNonElectAccessories.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateNonElectAccessories.setAttribute("Value", "2.2612");
		PropCoverDetails_RateNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredNonElectAccessories.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredNonElectAccessories.setAttribute("Value", "25000");
		PropCoverDetails_SumInsuredNonElectAccessories.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateNonElectAccessories = new org.jdom2.Element(
				"PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateNonElectAccessories.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateNonElectAccessories.setAttribute("Value", "2.2612");
		PropCoverDetails_TariffRateNonElectAccessories.setAttribute("Type", "Double");
		///////////// End Trailer Tp///////////////////////////////////////

		///////////// Non Electrical Accessories End///////////////////

		/////////////////// CNG and LPG Kit Start///////////////////////////

		org.jdom2.Element RiskGroupCngLpg = new org.jdom2.Element("RiskGroup");
		RiskGroupCngLpg.setAttribute("Name", "CNG and LPG Kit");
		RiskGroupCngLpg.setAttribute("Value", "Risk Wise Cover Details");
		RiskGroupCngLpg.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponantCngLpg = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantCngLpg.setAttribute("Name", "CNG Kit OD");
		CoverSIComponantCngLpg.setAttribute("Value", "50000");
		CoverSIComponantCngLpg.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColCngLpg = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColCngLpg.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColCngLpg.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColCngLpg.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableCngLpg = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableCngLpg.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableCngLpg.setAttribute("Value", "True");
		PropCoverDetails_ApplicableCngLpg.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateCngLpg = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateCngLpg.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateCngLpg.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionCngLpg = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionCngLpg.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionCngLpg.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsCngLpg = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsCngLpg.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsCngLpg.setAttribute("Value", "CNG Kit OD");
		PropCoverDetails_CoverGroupsCngLpg.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxCngLpg = new org.jdom2.Element("PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxCngLpg.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxCngLpg.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremCngLpg = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremCngLpg.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremCngLpg.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumCngLpg = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumCngLpg.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumCngLpg.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxCngLpg = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxCngLpg.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxCngLpg.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSICngLpg = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSICngLpg.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSICngLpg.setAttribute("Value", "50000");
		PropCoverDetails_DifferentialSICngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountCngLpg = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountCngLpg.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountCngLpg.setAttribute("Value", "2000");
		PropCoverDetails_EndorsementAmountCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateCngLpg = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateCngLpg.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateCngLpg.setAttribute("Value", "0");
		PropCoverDetails_FinalRateCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedCngLpg = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedCngLpg.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedCngLpg.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedCngLpg.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedCngLpg = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedCngLpg.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedCngLpg.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedCngLpg.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeCngLpg = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeCngLpg.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeCngLpg.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeCngLpg.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumNonCngLpg = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonCngLpg.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonCngLpg.setAttribute("Value", "2000");
		PropCoverDetails_PremiumNonCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateCngLpg = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateCngLpg.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateCngLpg.setAttribute("Value", "4");
		PropCoverDetails_RateCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredCngLpg = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredCngLpg.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredCngLpg.setAttribute("Value", "50000");
		PropCoverDetails_SumInsuredCngLpg.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateCngLpg = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateCngLpg.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateCngLpg.setAttribute("Value", "4");
		PropCoverDetails_TariffRateCngLpg.setAttribute("Type", "Double");
		///////////// End Trailer Tp///////////////////////////////////////

		///////////// CNG and LPG Kit End///////////////////

		/////////////////// Named PA Cover///////////////////////////
		org.jdom2.Element RiskGroupNamedPa = new org.jdom2.Element("RiskGroup");
		RiskGroupNamedPa.setAttribute("Name", "Named PA Cover");
		RiskGroupNamedPa.setAttribute("Value", "Risk Wise Cover Details");
		RiskGroupNamedPa.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponantNamedPa = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantNamedPa.setAttribute("Name", "Named Passengers Personal Accident");
		CoverSIComponantNamedPa.setAttribute("Value", "100000");
		CoverSIComponantNamedPa.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColNamedPa = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColNamedPa.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColNamedPa.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColNamedPa.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableNamedPa = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableNamedPa.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableNamedPa.setAttribute("Value", "True");
		PropCoverDetails_ApplicableNamedPa.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateNamedPa = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateNamedPa.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateNamedPa.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionNamedPa = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionNamedPa.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CommissionNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsNamedPa = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsNamedPa.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsNamedPa.setAttribute("Value", "Named Passengers Personal Accident");
		PropCoverDetails_CoverGroupsNamedPa.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxNamedPa = new org.jdom2.Element("PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxNamedPa.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremNamedPa = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremNamedPa.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumNamedPa = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumNamedPa.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxNamedPa = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxNamedPa.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSINamedPa = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSINamedPa.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSINamedPa.setAttribute("Value", "100000");
		PropCoverDetails_DifferentialSINamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountNamedPa = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountNamedPa.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountNamedPa.setAttribute("Value", "50");
		PropCoverDetails_EndorsementAmountNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateNamedPa = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateNamedPa.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateNamedPa.setAttribute("Value", "0");
		PropCoverDetails_FinalRateNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedNamedPa = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedNamedPa.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedNamedPa.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedNamedPa.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedNamedPa = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedNamedPa.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedNamedPa.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedNamedPa.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeNamedPa = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeNamedPa.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeNamedPa.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeNamedPa.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumNonNamedPa = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonNamedPa.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonNamedPa.setAttribute("Value", "50");
		PropCoverDetails_PremiumNonNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateNamedPa = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateNamedPa.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateNamedPa.setAttribute("Value", "0.05");
		PropCoverDetails_RateNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredNamedPa = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredNamedPa.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredNamedPa.setAttribute("Value", "100000");
		PropCoverDetails_SumInsuredNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateNamedPa = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateNamedPa.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateNamedPa.setAttribute("Value", "0.05");
		PropCoverDetails_TariffRateNamedPa.setAttribute("Type", "Double");
		///////////// End Trailer Tp///////////////////////////////////////

		///////////// Named PA Cover End///////////////////////////////

		/////////////////// UnNamed PA Cover///////////////////////////
		org.jdom2.Element RiskGroupUnNamedPa = new org.jdom2.Element("RiskGroup");
		RiskGroupUnNamedPa.setAttribute("Name", "Unnamed PA Cover");
		RiskGroupUnNamedPa.setAttribute("Value", "Risk Wise Cover Details");
		RiskGroupUnNamedPa.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponantUnNamedPa = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantUnNamedPa.setAttribute("Name", "Unnamed Passengers Personal Accident");
		CoverSIComponantUnNamedPa.setAttribute("Value", "50000");
		CoverSIComponantUnNamedPa.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColUnNamedPa.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColUnNamedPa.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColUnNamedPa.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableUnNamedPa = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableUnNamedPa.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableUnNamedPa.setAttribute("Value", "True");
		PropCoverDetails_ApplicableUnNamedPa.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateUnNamedPa.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateUnNamedPa.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRateUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionUnNamedPa = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionUnNamedPa.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionUnNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CommissionUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsUnNamedPa = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsUnNamedPa.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsUnNamedPa.setAttribute("Value", "Unnamed Passengers Personal Accident");
		PropCoverDetails_CoverGroupsUnNamedPa.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxUnNamedPa.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxUnNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremUnNamedPa.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremUnNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumUnNamedPa.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumUnNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxUnNamedPa.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxUnNamedPa.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIUnNamedPa.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIUnNamedPa.setAttribute("Value", "50000");
		PropCoverDetails_DifferentialSIUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountUnNamedPa.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountUnNamedPa.setAttribute("Value", "25");
		PropCoverDetails_EndorsementAmountUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateUnNamedPa = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateUnNamedPa.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateUnNamedPa.setAttribute("Value", "0");
		PropCoverDetails_FinalRateUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedUnNamedPa.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedUnNamedPa.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedUnNamedPa.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedUnNamedPa = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedUnNamedPa.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedUnNamedPa.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedUnNamedPa.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeUnNamedPa = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeUnNamedPa.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeUnNamedPa.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeUnNamedPa.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumNonUnNamedPa = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonUnNamedPa.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonUnNamedPa.setAttribute("Value", "25");
		PropCoverDetails_PremiumNonUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateUnNamedPa = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateUnNamedPa.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateUnNamedPa.setAttribute("Value", "0.05");
		PropCoverDetails_RateUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredUnNamedPa = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredUnNamedPa.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredUnNamedPa.setAttribute("Value", "50000");
		PropCoverDetails_SumInsuredUnNamedPa.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateUnNamedPa = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateUnNamedPa.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateUnNamedPa.setAttribute("Value", "0.05");
		PropCoverDetails_TariffRateUnNamedPa.setAttribute("Type", "Double");
		///////////// End Trailer Tp///////////////////////////////////////

		///////////// Unnamed PA Cover End////////////////

		/////////////////// Paid Driver pa Cover///////////////////////////
		org.jdom2.Element RiskGroupPaidDriver = new org.jdom2.Element("RiskGroup");
		RiskGroupPaidDriver.setAttribute("Name", "Paid Driver PA Cover");
		RiskGroupPaidDriver.setAttribute("Value", "Risk Wise Cover Details");
		RiskGroupPaidDriver.setAttribute("Type", "GroupData");

		org.jdom2.Element CoverSIComponantPaidDriver = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantPaidDriver.setAttribute("Name", "Paid Driver PA Cover");
		CoverSIComponantPaidDriver.setAttribute("Value", "80000");
		CoverSIComponantPaidDriver.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColPaidDriver.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColPaidDriver.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColPaidDriver.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicablePaidDriver = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicablePaidDriver.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicablePaidDriver.setAttribute("Value", "True");
		PropCoverDetails_ApplicablePaidDriver.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRatePaidDriver = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRatePaidDriver.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRatePaidDriver.setAttribute("Value", "0");
		PropCoverDetails_BaseTariffRatePaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionPaidDriver = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionPaidDriver.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionPaidDriver.setAttribute("Value", "0");
		PropCoverDetails_CommissionPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsPaidDriver.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsPaidDriver.setAttribute("Value", "Paid Driver PA Cover");
		PropCoverDetails_CoverGroupsPaidDriver.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxPaidDriver.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxPaidDriver.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremPaidDriver.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremPaidDriver.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumPaidDriver.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumPaidDriver.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxPaidDriver.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxPaidDriver.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIPaidDriver.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIPaidDriver.setAttribute("Value", "80000");
		PropCoverDetails_DifferentialSIPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountPaidDriver.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountPaidDriver.setAttribute("Value", "40");
		PropCoverDetails_EndorsementAmountPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRatePaidDriver = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRatePaidDriver.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRatePaidDriver.setAttribute("Value", "0");
		PropCoverDetails_FinalRatePaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedPaidDriver.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedPaidDriver.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedPaidDriver.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedPaidDriver = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedPaidDriver.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedPaidDriver.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedPaidDriver.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodePaidDriver = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodePaidDriver.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodePaidDriver.setAttribute("Value", "");
		PropCoverDetails_MMCPCodePaidDriver.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumNonPaidDriver = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonPaidDriver.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumNonPaidDriver.setAttribute("Value", "40");
		PropCoverDetails_PremiumNonPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RatePaidDriver = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RatePaidDriver.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RatePaidDriver.setAttribute("Value", "0.05");
		PropCoverDetails_RatePaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredPaidDriver = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredPaidDriver.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredPaidDriver.setAttribute("Value", "80000");
		PropCoverDetails_SumInsuredPaidDriver.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRatePaidDriver = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRatePaidDriver.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRatePaidDriver.setAttribute("Value", "0.05");
		PropCoverDetails_TariffRatePaidDriver.setAttribute("Type", "Double");
		///////////// End Trailer Tp///////////////////////////////////////

		///////////////// Paid Driver PA Cover End//////////////////

		//////////////////// Rallies OD//////////////////

		org.jdom2.Element CoverSIComponantRalliesOD = new org.jdom2.Element("CoverSIComponant");
		CoverSIComponantRalliesOD.setAttribute("Name", "Rallies OD");
		CoverSIComponantRalliesOD.setAttribute("Value", "789777");
		CoverSIComponantRalliesOD.setAttribute("Type", "");

		org.jdom2.Element PropCoverDetails_LoadingDiscount_ColRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRalliesOD.setAttribute("Name", "PropCoverDetails_LoadingDiscount_Col");
		PropCoverDetails_LoadingDiscount_ColRalliesOD.setAttribute("Value", "");
		PropCoverDetails_LoadingDiscount_ColRalliesOD.setAttribute("Type", "CoverDetails_LoadingDiscount[]");

		org.jdom2.Element PropCoverDetails_ApplicableRalliesOD = new org.jdom2.Element("PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRalliesOD.setAttribute("Name", "PropCoverDetails_Applicable");
		PropCoverDetails_ApplicableRalliesOD.setAttribute("Value", "True");
		PropCoverDetails_ApplicableRalliesOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_BaseTariffRateRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRalliesOD.setAttribute("Name", "PropCoverDetails_BaseTariffRate");
		PropCoverDetails_BaseTariffRateRalliesOD.setAttribute("Value", "17.5");
		PropCoverDetails_BaseTariffRateRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CommissionRalliesOD = new org.jdom2.Element("PropCoverDetails_Commission");
		PropCoverDetails_CommissionRalliesOD.setAttribute("Name", "PropCoverDetails_Commission");
		PropCoverDetails_CommissionRalliesOD.setAttribute("Value", "17.5");
		PropCoverDetails_CommissionRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CoverGroupsRalliesOD = new org.jdom2.Element("PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRalliesOD.setAttribute("Name", "PropCoverDetails_CoverGroups");
		PropCoverDetails_CoverGroupsRalliesOD.setAttribute("Value", "Rallies OD");
		PropCoverDetails_CoverGroupsRalliesOD.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_CumEndSerTaxRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRalliesOD.setAttribute("Name", "PropCoverDetails_CumEndSerTax");
		PropCoverDetails_CumEndSerTaxRalliesOD.setAttribute("Value", "0");
		PropCoverDetails_CumEndSerTaxRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumalativEndPremRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRalliesOD.setAttribute("Name", "PropCoverDetails_CumalativEndPrem");
		PropCoverDetails_CumalativEndPremRalliesOD.setAttribute("Value", "0");
		PropCoverDetails_CumalativEndPremRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativePremiumRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRalliesOD.setAttribute("Name", "PropCoverDetails_CumulativePremium");
		PropCoverDetails_CumulativePremiumRalliesOD.setAttribute("Value", "0");
		PropCoverDetails_CumulativePremiumRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_CumulativeServicetaxRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRalliesOD.setAttribute("Name", "PropCoverDetails_CumulativeServicetax");
		PropCoverDetails_CumulativeServicetaxRalliesOD.setAttribute("Value", "0");
		PropCoverDetails_CumulativeServicetaxRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_DifferentialSIRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRalliesOD.setAttribute("Name", "PropCoverDetails_DifferentialSI");
		PropCoverDetails_DifferentialSIRalliesOD.setAttribute("Value", "789777");
		PropCoverDetails_DifferentialSIRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_EndorsementAmountRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRalliesOD.setAttribute("Name", "PropCoverDetails_EndorsementAmount");
		PropCoverDetails_EndorsementAmountRalliesOD.setAttribute("Value", "60");
		PropCoverDetails_EndorsementAmountRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_FinalRateRalliesOD = new org.jdom2.Element("PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRalliesOD.setAttribute("Name", "PropCoverDetails_FinalRate");
		PropCoverDetails_FinalRateRalliesOD.setAttribute("Value", "0");
		PropCoverDetails_FinalRateRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_IsDataDeletedRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRalliesOD.setAttribute("Name", "PropCoverDetails_IsDataDeleted");
		PropCoverDetails_IsDataDeletedRalliesOD.setAttribute("Value", "False");
		PropCoverDetails_IsDataDeletedRalliesOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_IsOldDataDeletedRalliesOD = new org.jdom2.Element(
				"PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRalliesOD.setAttribute("Name", "PropCoverDetails_IsOldDataDeleted");
		PropCoverDetails_IsOldDataDeletedRalliesOD.setAttribute("Value", "False");
		PropCoverDetails_IsOldDataDeletedRalliesOD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoverDetails_MMCPCodeRalliesOD = new org.jdom2.Element("PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRalliesOD.setAttribute("Name", "PropCoverDetails_MMCPCode");
		PropCoverDetails_MMCPCodeRalliesOD.setAttribute("Value", "");
		PropCoverDetails_MMCPCodeRalliesOD.setAttribute("Type", "String");

		org.jdom2.Element PropCoverDetails_PremiumRalliesOD = new org.jdom2.Element("PropCoverDetails_Premium");
		PropCoverDetails_PremiumRalliesOD.setAttribute("Name", "PropCoverDetails_Premium");
		PropCoverDetails_PremiumRalliesOD.setAttribute("Value", "60");
		PropCoverDetails_PremiumRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_RateRalliesOD = new org.jdom2.Element("PropCoverDetails_Rate");
		PropCoverDetails_RateRalliesOD.setAttribute("Name", "PropCoverDetails_Rate");
		PropCoverDetails_RateRalliesOD.setAttribute("Value", "60");
		PropCoverDetails_RateRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_SumInsuredRalliesOD = new org.jdom2.Element("PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRalliesOD.setAttribute("Name", "PropCoverDetails_SumInsured");
		PropCoverDetails_SumInsuredRalliesOD.setAttribute("Value", "789777");
		PropCoverDetails_SumInsuredRalliesOD.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverDetails_TariffRateRalliesOD = new org.jdom2.Element("PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRalliesOD.setAttribute("Name", "PropCoverDetails_TariffRate");
		PropCoverDetails_TariffRateRalliesOD.setAttribute("Value", "0");
		PropCoverDetails_TariffRateRalliesOD.setAttribute("Type", "Double");

		///////////// End Depreciation
		///////////// Cover///////////////////////////////////////

		///////////////// Rallies OD End///////////////

		Block.addContent(RiskCoverDetailsGrid);

		RiskCoverDetailsGrid.addContent(RiskGroup);

		RiskGroup.addContent(CoverSIComponant);
		CoverSIComponant.addContent(PropCoverDetails_Applicable);
		CoverSIComponant.addContent(PropCoverDetails_BaseTariffRate);
		CoverSIComponant.addContent(PropCoverDetails_Commission);
		CoverSIComponant.addContent(PropCoverDetails_CoverGroups);
		CoverSIComponant.addContent(PropCoverDetails_CumEndSerTax);
		CoverSIComponant.addContent(PropCoverDetails_CumalativEndPrem);
		CoverSIComponant.addContent(PropCoverDetails_CumulativePremium);
		CoverSIComponant.addContent(PropCoverDetails_CumulativeServicetax);
		CoverSIComponant.addContent(PropCoverDetails_DifferentialSI);
		CoverSIComponant.addContent(PropCoverDetails_EndorsementAmount);
		CoverSIComponant.addContent(PropCoverDetails_FinalRate);
		CoverSIComponant.addContent(PropCoverDetails_IsDataDeleted);
		CoverSIComponant.addContent(PropCoverDetails_IsOldDataDeleted);
		CoverSIComponant.addContent(PropCoverDetails_LoadingDiscount_Col);
		CoverSIComponant.addContent(PropCoverDetails_MMCPCode);
		CoverSIComponant.addContent(PropCoverDetails_Premium);
		CoverSIComponant.addContent(PropCoverDetails_Rate);
		CoverSIComponant.addContent(PropCoverDetails_SumInsured);
		CoverSIComponant.addContent(PropCoverDetails_TariffRate);

		RiskGroup.addContent(CoverSIComponantOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_ApplicableOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_BaseTariffRateOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_CommissionOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_CoverGroupsOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumEndSerTaxOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumalativEndPremOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumulativePremiumOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_CumulativeServicetaxOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_DifferentialSIOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_EndorsementAmountOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_FinalRateOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_IsDataDeletedOwnDamgae);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_IsOldDataDeletedownDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_LoadingDiscount_ColOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_MMCPCodeownDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_PremiumOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_RateOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_SumInsuredOwnDamage);
		CoverSIComponantOwnDamage.addContent(PropCoverDetails_TariffRateOwnDamage);

		RiskGroup.addContent(CoverSIComponantEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_ApplicableEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_BaseTariffRateEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_CommissionEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_CoverGroupsEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumEndSerTaxEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumalativEndPremEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumulativePremiumEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_CumulativeServicetaxEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_DifferentialSIEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_EndorsementAmountEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_FinalRateEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_IsDataDeletedEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_IsOldDataDeletedEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_LoadingDiscount_ColEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_MMCPCodeEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_PremiumEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_RateEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_SumInsuredEngineProtect);
		CoverSIComponantEngineProtect.addContent(PropCoverDetails_TariffRateEngineProtect);

		RiskGroup.addContent(CoverSIComponantRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_ApplicableRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_BaseTariffRateRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CommissionRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CoverGroupsRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumEndSerTaxRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumalativEndPremRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumulativePremiumRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_CumulativeServicetaxRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_DifferentialSIRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_EndorsementAmountRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_FinalRateRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_IsDataDeletedRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_IsOldDataDeletedRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_LoadingDiscount_ColRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_MMCPCodeRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_PremiumRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_RateRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_SumInsuredRetToInvoice);
		CoverSIComponantRetToInvoice.addContent(PropCoverDetails_TariffRateRetToInvoice);

		RiskGroup.addContent(CoverSIComponantConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_ApplicableConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_BaseTariffRateConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CommissionRateConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CoverGroupsConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumEndSerTaxConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumalativEndPremConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumulativePremiumConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_CumulativeServicetaxConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_DifferentialSIConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_EndorsementAmountConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_FinalRateConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_IsDataDeletedConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_IsOldDataDeletedConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_LoadingDiscount_ColConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_MMCPCodeConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_PremiumConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_RateConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_SumInsuredConsumableCvr);
		CoverSIComponantConsumableCvr.addContent(PropCoverDetails_TariffRateConsumableCvr);

		RiskGroup.addContent(CoverSIComponantLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_ApplicableLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_BaseTariffRateLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_CommissionRateLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_CoverGroupsLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_CumEndSerTaxLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_CumalativEndPremLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_CumulativePremiumLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_CumulativeServicetaxLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_DifferentialSILegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_EndorsementAmountLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_FinalRateLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_IsDataDeletedLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_IsOldDataDeletedLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_LoadingDiscount_ColLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_MMCPCodeLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_PremiumLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_RateLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_SumInsuredLegalLib);
		CoverSIComponantLegalLib.addContent(PropCoverDetails_TariffRateLegalLib);

		RiskGroup.addContent(CoverSIComponantLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_ApplicableLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_BaseTariffRateLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CommissionRateLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CoverGroupsLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumEndSerTaxLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumalativEndPremLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumulativePremiumLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_CumulativeServicetaxLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_DifferentialSILegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_EndorsementAmountLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_FinalRateLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_IsDataDeletedLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_IsOldDataDeletedLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_LoadingDiscount_ColLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_MMCPCodeLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_PremiumLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_RateLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_SumInsuredLegalLibEmp);
		CoverSIComponantLegalLibEmp.addContent(PropCoverDetails_TariffRateLegalLibEmp);

		RiskGroup.addContent(CoverSIComponantRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_ApplicableRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_BaseTariffRateRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_CommissionRateRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_CoverGroupsRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_CumEndSerTaxRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_CumalativEndPremRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_CumulativePremiumRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_CumulativeServicetaxRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_DifferentialSIRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_EndorsementAmountRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_FinalRateRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_IsDataDeletedRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_IsOldDataDeletedRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_LoadingDiscount_ColRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_MMCPCodeRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_PremiumRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_RateRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_SumInsuredRoadSide);
		CoverSIComponantRoadSide.addContent(PropCoverDetails_TariffRateRoadSide);

		RiskGroup.addContent(CoverSIComponantRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_ApplicableRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_BaseTariffRateRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_CommissionRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_CoverGroupsRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumEndSerTaxRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumalativEndPremRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumulativePremiumRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_CumulativeServicetaxRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_DifferentialSIRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_EndorsementAmountRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_FinalRateRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_IsDataDeletedRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_IsOldDataDeletedRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_LoadingDiscount_ColRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_MMCPCodeRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_PremiumRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_RateRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_SumInsuredRalliesTp);
		CoverSIComponantRalliesTp.addContent(PropCoverDetails_TariffRateRalliesTp);

		RiskGroup.addContent(CoverSIComponantDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_ApplicableDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_BaseTariffRateDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_CommissionDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_CoverGroupsDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_CumEndSerTaxDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_CumalativEndPremDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_CumulativePremiumDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_CumulativeServicetaxDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_DifferentialSIDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_EndorsementAmountDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_FinalRateDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_IsDataDeletedDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_IsOldDataDeletedDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_LoadingDiscount_ColDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_MMCPCodeDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_PremiumDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_RateDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_SumInsuredDprCvr);
		CoverSIComponantDprCvr.addContent(PropCoverDetails_TariffRateDprCvr);

		RiskGroup.addContent(CoverSIComponantRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_ApplicableRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_BaseTariffRateRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_CommissionRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_CoverGroupsRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumEndSerTaxRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumalativEndPremRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumulativePremiumRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_CumulativeServicetaxRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_DifferentialSIRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_EndorsementAmountRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_FinalRateRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_IsDataDeletedRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_IsOldDataDeletedRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_LoadingDiscount_ColRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_MMCPCodeRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_PremiumRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_RateRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_SumInsuredRalliesOD);
		CoverSIComponantRalliesOD.addContent(PropCoverDetails_TariffRateRalliesOD);

		RiskGroup.addContent(CoverSIComponantLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_LoadingDiscount_ColLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_ApplicableLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_BaseTariffRateLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CommissionLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CoverGroupsLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumEndSerTaxLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumalativEndPremLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumulativePremiumLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_CumulativeServicetaxLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_DifferentialSILibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_EndorsementAmountLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_FinalRateLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_IsDataDeletedLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_IsOldDataDeletedLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_MMCPCodeLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_PremiumLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_RateLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_SumInsuredLibToSoldier);
		CoverSIComponantLibToSoldier.addContent(PropCoverDetails_TariffRateLibToSoldier);

		RiskGroup.addContent(CoverSIComponantGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_LoadingDiscount_ColGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_ApplicableGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_BaseTariffRateGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CommissionGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CoverGroupsGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumEndSerTaxGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumalativEndPremGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumulativePremiumGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_CumulativeServicetaxGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_DifferentialSIGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_EndorsementAmountGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_FinalRateGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_IsDataDeletedGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_IsOldDataDeletedGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_MMCPCodeGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_PremiumGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_RateGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_SumInsuredGeoExtTp);
		CoverSIComponantGeoExtTp.addContent(PropCoverDetails_TariffRateGeoExtTp);

		RiskGroup.addContent(CoverSIComponantGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_LoadingDiscount_ColGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_ApplicableGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_BaseTariffRateGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CommissionGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CoverGroupsGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumEndSerTaxGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumalativEndPremGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumulativePremiumGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_CumulativeServicetaxGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_DifferentialSIGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_EndorsementAmountGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_FinalRateGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_IsDataDeletedGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_IsOldDataDeletedGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_MMCPCodeGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_PremiumGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_RateGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_SumInsuredGeoExtOD);
		CoverSIComponantGeoExtOD.addContent(PropCoverDetails_TariffRateGeoExtOD);

		RiskGroup.addContent(CoverSIComponantOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_LoadingDiscount_ColOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_ApplicableOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_BaseTariffRateOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CommissionOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CoverGroupsOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumEndSerTaxOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumalativEndPremOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumulativePremiumOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_CumulativeServicetaxOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_DifferentialSIOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_EndorsementAmountOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_FinalRateOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_IsDataDeletedOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_IsOldDataDeletedOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_MMCPCodeOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_PremiumOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_RateOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_SumInsuredOwnerDriver);
		CoverSIComponantOwnerDriver.addContent(PropCoverDetails_TariffRateOwnerDriver);

		RiskGroup.addContent(CoverSIComponantCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_LoadingDiscount_ColCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_ApplicableCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_BaseTariffRateCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_CommissionCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_CoverGroupsCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumEndSerTaxCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumalativEndPremCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumulativePremiumCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_CumulativeServicetaxCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_DifferentialSICngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_EndorsementAmountCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_FinalRateCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_IsDataDeletedCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_IsOldDataDeletedCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_MMCPCodeCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_PremiumCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_RateCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_SumInsuredCngKitTp);
		CoverSIComponantCngKitTp.addContent(PropCoverDetails_TariffRateCngKitTp);

		RiskCoverDetailsGrid.addContent(RiskGroupTrailer);
		RiskGroupTrailer.addContent(CoverSIComponantTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_LoadingDiscount_ColTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_ApplicableTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_BaseTariffRateTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_CommissionTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_CoverGroupsTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumEndSerTaxTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumalativEndPremTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumulativePremiumTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_CumulativeServicetaxTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_DifferentialSITrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_EndorsementAmountTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_FinalRateTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_IsDataDeletedTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_IsOldDataDeletedTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_MMCPCodeTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_PremiumTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_RateTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_SumInsuredTrailerOD);
		CoverSIComponantTrailerOD.addContent(PropCoverDetails_TariffRateTrailerOD);

		RiskGroupTrailer.addContent(CoverSIComponantTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_LoadingDiscount_ColTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_ApplicableTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_BaseTariffRateTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_CommissionTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_CoverGroupsTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumEndSerTaxTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumalativEndPremTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumulativePremiumTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_CumulativeServicetaxTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_DifferentialSITrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_EndorsementAmountTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_FinalRateTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_IsDataDeletedTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_IsOldDataDeletedTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_MMCPCodeTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_PremiumTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_RateTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_SumInsuredTrailerTp);
		CoverSIComponantTrailerTp.addContent(PropCoverDetails_TariffRateTrailerTp);

		RiskCoverDetailsGrid.addContent(RiskGroupElectAccessories);
		RiskGroupElectAccessories.addContent(CoverSIComponantElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_LoadingDiscount_ColElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_ApplicableElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_BaseTariffRateElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_CommissionElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_CoverGroupsElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumEndSerTaxElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumalativEndPremElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumulativePremiumElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_CumulativeServicetaxElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_DifferentialSIElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_EndorsementAmountElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_FinalRateElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_IsDataDeletedElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_IsOldDataDeletedElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_MMCPCodeElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_PremiumElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_RateElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_SumInsuredElectAccessories);
		CoverSIComponantElectAccessories.addContent(PropCoverDetails_TariffRateElectAccessories);

		RiskCoverDetailsGrid.addContent(RiskGroupNonElectAccessories);
		RiskGroupNonElectAccessories.addContent(CoverSIComponantNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_LoadingDiscount_ColNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_ApplicableNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_BaseTariffRateNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CommissionNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CoverGroupsNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumEndSerTaxNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumalativEndPremNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumulativePremiumNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_CumulativeServicetaxNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_DifferentialSINonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_EndorsementAmountNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_FinalRateNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_IsDataDeletedNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_IsOldDataDeletedNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_MMCPCodeNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_PremiumNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_RateNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_SumInsuredNonElectAccessories);
		CoverSIComponantNonElectAccessories.addContent(PropCoverDetails_TariffRateNonElectAccessories);

		RiskCoverDetailsGrid.addContent(RiskGroupCngLpg);
		RiskGroupCngLpg.addContent(CoverSIComponantCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_LoadingDiscount_ColCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_ApplicableCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_BaseTariffRateCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_CommissionCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_CoverGroupsCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_CumEndSerTaxCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_CumalativEndPremCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_CumulativePremiumCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_CumulativeServicetaxCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_DifferentialSICngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_EndorsementAmountCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_FinalRateCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_IsDataDeletedCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_IsOldDataDeletedCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_MMCPCodeCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_PremiumNonCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_RateCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_SumInsuredCngLpg);
		CoverSIComponantCngLpg.addContent(PropCoverDetails_TariffRateCngLpg);

		RiskCoverDetailsGrid.addContent(RiskGroupNamedPa);
		RiskGroupNamedPa.addContent(CoverSIComponantNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_LoadingDiscount_ColNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_ApplicableNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_BaseTariffRateNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_CommissionNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_CoverGroupsNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_CumEndSerTaxNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_CumalativEndPremNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_CumulativePremiumNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_CumulativeServicetaxNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_DifferentialSINamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_EndorsementAmountNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_FinalRateNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_IsDataDeletedNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_IsOldDataDeletedNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_MMCPCodeNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_PremiumNonNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_RateNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_SumInsuredNamedPa);
		CoverSIComponantNamedPa.addContent(PropCoverDetails_TariffRateNamedPa);

		RiskCoverDetailsGrid.addContent(RiskGroupUnNamedPa);
		RiskGroupUnNamedPa.addContent(CoverSIComponantUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_LoadingDiscount_ColUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_ApplicableUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_BaseTariffRateUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CommissionUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CoverGroupsUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumEndSerTaxUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumalativEndPremUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumulativePremiumUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_CumulativeServicetaxUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_DifferentialSIUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_EndorsementAmountUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_FinalRateUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_IsDataDeletedUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_IsOldDataDeletedUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_MMCPCodeUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_PremiumNonUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_RateUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_SumInsuredUnNamedPa);
		CoverSIComponantUnNamedPa.addContent(PropCoverDetails_TariffRateUnNamedPa);

		RiskCoverDetailsGrid.addContent(RiskGroupPaidDriver);
		RiskGroupPaidDriver.addContent(CoverSIComponantPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_LoadingDiscount_ColPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_ApplicablePaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_BaseTariffRatePaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_CommissionPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_CoverGroupsPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumEndSerTaxPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumalativEndPremPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumulativePremiumPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_CumulativeServicetaxPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_DifferentialSIPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_EndorsementAmountPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_FinalRatePaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_IsDataDeletedPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_IsOldDataDeletedPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_MMCPCodePaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_PremiumNonPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_RatePaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_SumInsuredPaidDriver);
		CoverSIComponantPaidDriver.addContent(PropCoverDetails_TariffRatePaidDriver);

	}

	private void generalPurposeInfo() throws JSONException {

		org.jdom2.Element GeneralPurposeInformation = new org.jdom2.Element("GeneralPurposeInformation");

		// ProposalDetails.addContent(GeneralPurposeInformation);

		org.jdom2.Element ErrorTrackingNeeded = new org.jdom2.Element("ErrorTrackingNeeded");
		ErrorTrackingNeeded.setAttribute("Value", "False");
		ErrorTrackingNeeded.setAttribute("Type", "Boolean");

		org.jdom2.Element ProductType = new org.jdom2.Element("ProductType");
		ProductType.setAttribute("Value", "SB");
		ProductType.setAttribute("Type", "String");

		org.jdom2.Element PropBranchDetails_IMDBranchCode = new org.jdom2.Element("PropBranchDetails_IMDBranchCode");
		PropBranchDetails_IMDBranchCode.setAttribute("Value", "");
		PropBranchDetails_IMDBranchCode.setAttribute("Type", "String");

		org.jdom2.Element PropBranchDetails_IMDBranchName = new org.jdom2.Element("PropBranchDetails_IMDBranchName");
		PropBranchDetails_IMDBranchName.setAttribute("Value", "");
		PropBranchDetails_IMDBranchName.setAttribute("Type", "String");

		org.jdom2.Element PropCalculation_CalculateRate = new org.jdom2.Element("PropCalculation_CalculateRate");
		PropCalculation_CalculateRate.setAttribute("Value", "True");
		PropCalculation_CalculateRate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCalculation_Validate = new org.jdom2.Element("PropCalculation_Validate");
		PropCalculation_Validate.setAttribute("Value", "False");
		PropCalculation_Validate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCalculation_ValidateData = new org.jdom2.Element("PropCalculation_ValidateData");
		PropCalculation_ValidateData.setAttribute("Value", "False");
		PropCalculation_ValidateData.setAttribute("Type", "Boolean");

		org.jdom2.Element PropClauseDetails_Component51 = new org.jdom2.Element("PropClauseDetails_Component51");
		PropClauseDetails_Component51.setAttribute("Value", "");
		PropClauseDetails_Component51.setAttribute("Type", "String");

		org.jdom2.Element PropClauseDetails_DepartmentCode_Mandatory = new org.jdom2.Element(
				"PropClauseDetails_DepartmentCode_Mandatory");
		PropClauseDetails_DepartmentCode_Mandatory.setAttribute("Value", "0");
		PropClauseDetails_DepartmentCode_Mandatory.setAttribute("Type", "Double");

		org.jdom2.Element PropClauseDetails_ProductCode_Mandatory = new org.jdom2.Element(
				"PropClauseDetails_ProductCode_Mandatory");
		PropClauseDetails_ProductCode_Mandatory.setAttribute("Value", "0");
		PropClauseDetails_ProductCode_Mandatory.setAttribute("Type", "Double");

		org.jdom2.Element PropClauseDetails_SectionCode = new org.jdom2.Element(
				"PropClauseDetails_ProductCode_SectionCode");
		PropClauseDetails_SectionCode.setAttribute("Value", "");
		PropClauseDetails_SectionCode.setAttribute("Type", "String");

		org.jdom2.Element PropCoinsuranceDetails_AddRow = new org.jdom2.Element("PropCoinsuranceDetails_AddRow");
		PropCoinsuranceDetails_AddRow.setAttribute("Value", "True");
		PropCoinsuranceDetails_AddRow.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoinsuranceDetails_CoinsuranceType = new org.jdom2.Element(
				"PropCoinsuranceDetails_CoinsuranceType");
		PropCoinsuranceDetails_CoinsuranceType.setAttribute("Value", "");
		PropCoinsuranceDetails_CoinsuranceType.setAttribute("Type", "String");

		org.jdom2.Element PropCoinsuranceDetails_Commissiontobepaidbytheleader = new org.jdom2.Element(
				"PropCoinsuranceDetails_Commissiontobepaidbytheleader");
		PropCoinsuranceDetails_Commissiontobepaidbytheleader.setAttribute("Value", "False");
		PropCoinsuranceDetails_Commissiontobepaidbytheleader.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoinsuranceDetails_PolicyNooftheleader = new org.jdom2.Element(
				"PropCoinsuranceDetails_PolicyNooftheleader");
		PropCoinsuranceDetails_PolicyNooftheleader.setAttribute("Value", "");
		PropCoinsuranceDetails_PolicyNooftheleader.setAttribute("Type", "String");

		org.jdom2.Element PropCoinsuranceDetails_ReferenceNumber = new org.jdom2.Element(
				"PropCoinsuranceDetails_ReferenceNumber");
		PropCoinsuranceDetails_ReferenceNumber.setAttribute("Value", "");
		PropCoinsuranceDetails_ReferenceNumber.setAttribute("Type", "String");

		org.jdom2.Element PropCoinsuranceDetails_SERVTAXCOMMRCVRLEADER = new org.jdom2.Element(
				"PropCoinsuranceDetails_SERVTAXCOMMRCVRLEADER");
		PropCoinsuranceDetails_SERVTAXCOMMRCVRLEADER.setAttribute("Value", "False");
		PropCoinsuranceDetails_SERVTAXCOMMRCVRLEADER.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoinsuranceDetails_Servicetaxtobepaidbytheleader = new org.jdom2.Element(
				"PropCoinsuranceDetails_Servicetaxtobepaidbytheleader");
		PropCoinsuranceDetails_Servicetaxtobepaidbytheleader.setAttribute("Value", "True");
		PropCoinsuranceDetails_Servicetaxtobepaidbytheleader.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCoinsuranceDetails_Validate = new org.jdom2.Element("PropCoinsuranceDetails_Validate");
		PropCoinsuranceDetails_Validate.setAttribute("Value", "");
		PropCoinsuranceDetails_Validate.setAttribute("Type", "String");

		org.jdom2.Element PropConditionDetails_ConditionDescription = new org.jdom2.Element(
				"PropConditionDetails_ConditionDescription");
		PropConditionDetails_ConditionDescription.setAttribute("Value", "");
		PropConditionDetails_ConditionDescription.setAttribute("Type", "String");

		org.jdom2.Element PropConditionDetails_DepartmentCode = new org.jdom2.Element(
				"PropConditionDetails_DepartmentCode");
		PropConditionDetails_DepartmentCode.setAttribute("Value", "0");
		PropConditionDetails_DepartmentCode.setAttribute("Type", "Double");

		org.jdom2.Element PropConditionDetails_ProductCode = new org.jdom2.Element("PropConditionDetails_ProductCode");
		PropConditionDetails_ProductCode.setAttribute("Value", "0");
		PropConditionDetails_ProductCode.setAttribute("Type", "Double");

		org.jdom2.Element PropConditionDetails_SectionCode = new org.jdom2.Element("PropConditionDetails_SectionCode");
		PropConditionDetails_SectionCode.setAttribute("Value", "");
		PropConditionDetails_SectionCode.setAttribute("Type", "String");

		org.jdom2.Element PropConditionDetails_SpecialConditionDescription = new org.jdom2.Element(
				"PropConditionDetails_SpecialConditionDescription");
		PropConditionDetails_SpecialConditionDescription.setAttribute("Value", "");
		PropConditionDetails_SpecialConditionDescription.setAttribute("Type", "String");

		org.jdom2.Element PropCoverNoteDetsils_CoverNoteBookNo = new org.jdom2.Element(
				"PropCoverNoteDetsils_CoverNoteBookNo");
		PropCoverNoteDetsils_CoverNoteBookNo.setAttribute("Value", "");
		PropCoverNoteDetsils_CoverNoteBookNo.setAttribute("Type", "String");

		org.jdom2.Element PropCoverNoteDetsils_CoverNoteLeafNo = new org.jdom2.Element(
				"PropCoverNoteDetsils_CoverNoteLeafNo");
		PropCoverNoteDetsils_CoverNoteLeafNo.setAttribute("Value", "");
		PropCoverNoteDetsils_CoverNoteLeafNo.setAttribute("Type", "String");

		org.jdom2.Element PropCoverNoteDetsils_IssuedOnDt = new org.jdom2.Element("PropCoverNoteDetsils_IssuedOnDt");
		PropCoverNoteDetsils_IssuedOnDt.setAttribute("Value", "");
		PropCoverNoteDetsils_IssuedOnDt.setAttribute("Type", "String");

		org.jdom2.Element PropCoverNoteDetsils_IssuedonTime = new org.jdom2.Element(
				"PropCoverNoteDetsils_IssuedonTime");
		PropCoverNoteDetsils_IssuedonTime.setAttribute("Value", "");
		PropCoverNoteDetsils_IssuedonTime.setAttribute("Type", "String");

		org.jdom2.Element PropCoverNoteDetsils_LeafNo = new org.jdom2.Element("PropCoverNoteDetsils_LeafNo");
		PropCoverNoteDetsils_LeafNo.setAttribute("Value", "0");
		PropCoverNoteDetsils_LeafNo.setAttribute("Type", "Double");

		org.jdom2.Element PropCoverNoteDetsils_ReceivedOn = new org.jdom2.Element("PropCoverNoteDetsils_ReceivedOn");
		PropCoverNoteDetsils_ReceivedOn.setAttribute("Value", "");
		PropCoverNoteDetsils_ReceivedOn.setAttribute("Type", "String");

		org.jdom2.Element PropCoverNoteDetsils_Validate = new org.jdom2.Element("PropCoverNoteDetsils_Validate");
		PropCoverNoteDetsils_Validate.setAttribute("Value", "True");
		PropCoverNoteDetsils_Validate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropCustomerDtls_CustomerID_Mandatary = new org.jdom2.Element(
				"PropCustomerDtls_CustomerID_Mandatary");
		PropCustomerDtls_CustomerID_Mandatary.setAttribute("Value", jsonResult.getString("USERID").trim());
		PropCustomerDtls_CustomerID_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropCustomerDtls_CustomerName = new org.jdom2.Element("PropCustomerDtls_CustomerName");
		PropCustomerDtls_CustomerName.setAttribute("Value", jsonResult.getString("CustomerName").trim());
		PropCustomerDtls_CustomerName.setAttribute("Type", "String");

		org.jdom2.Element PropCustomerDtls_CustomerType = new org.jdom2.Element("PropCustomerDtls_CustomerType");
		PropCustomerDtls_CustomerType.setAttribute("Value", "");
		PropCustomerDtls_CustomerType.setAttribute("Type", "String");

		org.jdom2.Element PropCustomerDtls_PayeeCustomerID = new org.jdom2.Element("PropCustomerDtls_PayeeCustomerID");
		PropCustomerDtls_PayeeCustomerID.setAttribute("Value", "");
		PropCustomerDtls_PayeeCustomerID.setAttribute("Type", "String");

		org.jdom2.Element PropCustomerDtls_PayeeCustomerName = new org.jdom2.Element(
				"PropCustomerDtls_PayeeCustomerName");
		PropCustomerDtls_PayeeCustomerName.setAttribute("Value", "");
		PropCustomerDtls_PayeeCustomerName.setAttribute("Type", "String");

		org.jdom2.Element PropCustomerReferenceInfo_OldPolicyNumber = new org.jdom2.Element(
				"PropCustomerReferenceInfo_OldPolicyNumber");
		PropCustomerReferenceInfo_OldPolicyNumber.setAttribute("Value", "");
		PropCustomerReferenceInfo_OldPolicyNumber.setAttribute("Type", "String");

		org.jdom2.Element PropCustomerReferenceInfo_PolicyConversionDate = new org.jdom2.Element(
				"PropCustomerReferenceInfo_PolicyConversionDate");
		PropCustomerReferenceInfo_PolicyConversionDate.setAttribute("Value", "");
		PropCustomerReferenceInfo_PolicyConversionDate.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_BranchDetails = new org.jdom2.Element(
				"PropDistributionChannel_BranchDetails");
		PropDistributionChannel_BranchDetails.setAttribute("Value", "");
		PropDistributionChannel_BranchDetails.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_BusineeChannelBrunch = new org.jdom2.Element(
				"PropDistributionChannel_BusineeChannelBrunch");
		PropDistributionChannel_BusineeChannelBrunch.setAttribute("Value", "");
		PropDistributionChannel_BusineeChannelBrunch.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_BusineeChanneltype = new org.jdom2.Element(
				"PropDistributionChannel_BusineeChanneltype");
		PropDistributionChannel_BusineeChanneltype.setAttribute("Value", "BROKER CORPORATE");
		PropDistributionChannel_BusineeChanneltype.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_BusinessServicingChannelType = new org.jdom2.Element(
				"PropDistributionChannel_BusinessServicingChannelType");
		PropDistributionChannel_BusinessServicingChannelType.setAttribute("Value", "");
		PropDistributionChannel_BusinessServicingChannelType.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_BusinessSharingPercent = new org.jdom2.Element(
				"PropDistributionChannel_BusinessSharingPercent");
		PropDistributionChannel_BusinessSharingPercent.setAttribute("Value", "0");
		PropDistributionChannel_BusinessSharingPercent.setAttribute("Type", "Double");

		org.jdom2.Element PropDistributionChannel_BusinessSource_Mandatary = new org.jdom2.Element(
				"PropDistributionChannel_BusinessSource_Mandatary");
		PropDistributionChannel_BusinessSource_Mandatary.setAttribute("Value", "INTERMEDIARY");
		PropDistributionChannel_BusinessSource_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_BusinessSourcetype = new org.jdom2.Element(
				"PropDistributionChannel_BusinessSourcetype");
		PropDistributionChannel_BusinessSourcetype.setAttribute("Value", "");
		PropDistributionChannel_BusinessSourcetype.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_Commission = new org.jdom2.Element(
				"PropDistributionChannel_Commission");
		PropDistributionChannel_Commission.setAttribute("Value", "17.5");
		PropDistributionChannel_Commission.setAttribute("Type", "Double");

		org.jdom2.Element PropDistributionChannel_EndorsementDtls = new org.jdom2.Element(
				"PropDistributionChannel_EndorsementDtls");
		PropDistributionChannel_EndorsementDtls.setAttribute("Value", "");
		PropDistributionChannel_EndorsementDtls.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_FieldUserDetails = new org.jdom2.Element(
				"PropDistributionChannel_FieldUserDetails");
		PropDistributionChannel_FieldUserDetails.setAttribute("Value", "");
		PropDistributionChannel_FieldUserDetails.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_FolderFilePath = new org.jdom2.Element(
				"PropDistributionChannel_FolderFilePath");
		PropDistributionChannel_FolderFilePath.setAttribute("Value", "");
		PropDistributionChannel_FolderFilePath.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_InterMediaryDetails = new org.jdom2.Element(
				"PropDistributionChannel_InterMediaryDetails");
		PropDistributionChannel_InterMediaryDetails.setAttribute("Value", "");
		PropDistributionChannel_InterMediaryDetails.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_MODetails = new org.jdom2.Element(
				"PropDistributionChannel_MODetails");
		PropDistributionChannel_MODetails.setAttribute("Value", "");
		PropDistributionChannel_MODetails.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_OperationMode = new org.jdom2.Element(
				"PropDistributionChannel_OperationMode");
		PropDistributionChannel_OperationMode.setAttribute("Value", "VIEWPROPOSAL");
		PropDistributionChannel_OperationMode.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_SPDetails = new org.jdom2.Element(
				"PropDistributionChannel_SPDetails");
		PropDistributionChannel_SPDetails.setAttribute("Value", "");
		PropDistributionChannel_SPDetails.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_SerIntermediaryDetails = new org.jdom2.Element(
				"PropDistributionChannel_SerIntermediaryDetails");
		PropDistributionChannel_SerIntermediaryDetails.setAttribute("Value", "");
		PropDistributionChannel_SerIntermediaryDetails.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_ServiceProvider = new org.jdom2.Element(
				"PropDistributionChannel_ServiceProvider");
		PropDistributionChannel_ServiceProvider.setAttribute("Value", "0");
		PropDistributionChannel_ServiceProvider.setAttribute("Type", "Double");

		org.jdom2.Element PropDistributionChannel_SpecialDiscount = new org.jdom2.Element(
				"PropDistributionChannel_SpecialDiscount");
		PropDistributionChannel_SpecialDiscount.setAttribute("Value", "100");
		PropDistributionChannel_SpecialDiscount.setAttribute("Type", "Double");

		org.jdom2.Element PropDistributionChannel_TERRORISMCOMMISSION = new org.jdom2.Element(
				"PropDistributionChannel_TERRORISMCOMMISSION");
		PropDistributionChannel_TERRORISMCOMMISSION.setAttribute("Value", "0");
		PropDistributionChannel_TERRORISMCOMMISSION.setAttribute("Type", "String");

		org.jdom2.Element PropDistributionChannel_Validate = new org.jdom2.Element("PropDistributionChannel_Validate");
		PropDistributionChannel_Validate.setAttribute("Value", "False");
		PropDistributionChannel_Validate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropDistributionChannel_VerticalDtls = new org.jdom2.Element(
				"PropDistributionChannel_VerticalDtls");
		PropDistributionChannel_VerticalDtls.setAttribute("Value", "");
		PropDistributionChannel_VerticalDtls.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_CancleDueToClaim = new org.jdom2.Element(
				"PropEndorsementDtls_CancleDueToClaim");
		PropEndorsementDtls_CancleDueToClaim.setAttribute("Value", "False");
		PropEndorsementDtls_CancleDueToClaim.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_CancellationOption = new org.jdom2.Element(
				"PropEndorsementDtls_CancellationOption");
		PropEndorsementDtls_CancellationOption.setAttribute("Value", "");
		PropEndorsementDtls_CancellationOption.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_CancellationReason = new org.jdom2.Element(
				"PropEndorsementDtls_CancellationReason");
		PropEndorsementDtls_CancellationReason.setAttribute("Value", "");
		PropEndorsementDtls_CancellationReason.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_CertificateofVintageCar = new org.jdom2.Element(
				"PropEndorsementDtls_CertificateofVintageCar");
		PropEndorsementDtls_CertificateofVintageCar.setAttribute("Value", "False");
		PropEndorsementDtls_CertificateofVintageCar.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_CoverType = new org.jdom2.Element("PropEndorsementDtls_CoverType");
		PropEndorsementDtls_CoverType.setAttribute("Value", "");
		PropEndorsementDtls_CoverType.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_DataEntryError = new org.jdom2.Element(
				"PropEndorsementDtls_DataEntryError");
		PropEndorsementDtls_DataEntryError.setAttribute("Value", "False");
		PropEndorsementDtls_DataEntryError.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_DateofEndfrominsured = new org.jdom2.Element(
				"PropEndorsementDtls_DateofEndfrominsured");
		PropEndorsementDtls_DateofEndfrominsured.setAttribute("Value", "");
		PropEndorsementDtls_DateofEndfrominsured.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_Description = new org.jdom2.Element("PropEndorsementDtls_Description");
		PropEndorsementDtls_Description.setAttribute("Value", "");
		PropEndorsementDtls_Description.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_DocForCommPrivate = new org.jdom2.Element(
				"PropEndorsementDtls_DocForCommPrivate");
		PropEndorsementDtls_DocForCommPrivate.setAttribute("Value", "False");
		PropEndorsementDtls_DocForCommPrivate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_DocOFRequisition = new org.jdom2.Element(
				"PropEndorsementDtls_DocOFRequisition");
		PropEndorsementDtls_DocOFRequisition.setAttribute("Value", "");
		PropEndorsementDtls_DocOFRequisition.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_DocProofothersspecify = new org.jdom2.Element(
				"PropEndorsementDtls_DocProofothersspecify");
		PropEndorsementDtls_DocProofothersspecify.setAttribute("Value", "");
		PropEndorsementDtls_DocProofothersspecify.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_DocproofofforNCB = new org.jdom2.Element(
				"PropEndorsementDtls_DocproofofforNCB");
		PropEndorsementDtls_DocproofofforNCB.setAttribute("Value", "");
		PropEndorsementDtls_DocproofofforNCB.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_DoubleINSOption = new org.jdom2.Element(
				"PropEndorsementDtls_DoubleINSOption");
		PropEndorsementDtls_DoubleINSOption.setAttribute("Value", "Automatic");
		PropEndorsementDtls_DoubleINSOption.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_EndorsementTypeCode = new org.jdom2.Element(
				"PropEndorsementDtls_EndorsementTypeCode");
		PropEndorsementDtls_EndorsementTypeCode.setAttribute("Value", "0");
		PropEndorsementDtls_EndorsementTypeCode.setAttribute("Type", "Double");

		org.jdom2.Element PropEndorsementDtls_INSCoName = new org.jdom2.Element("PropEndorsementDtls_INSCoName");
		PropEndorsementDtls_INSCoName.setAttribute("Value", "");
		PropEndorsementDtls_INSCoName.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_INSCoOffCodeAdd = new org.jdom2.Element(
				"PropEndorsementDtls_INSCoOffCodeAdd");
		PropEndorsementDtls_INSCoOffCodeAdd.setAttribute("Value", "");
		PropEndorsementDtls_INSCoOffCodeAdd.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_ISSellerIndured = new org.jdom2.Element(
				"PropEndorsementDtls_ISSellerIndured");
		PropEndorsementDtls_ISSellerIndured.setAttribute("Value", "True");
		PropEndorsementDtls_ISSellerIndured.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_LaidUpFromDate = new org.jdom2.Element(
				"PropEndorsementDtls_LaidUpFromDate");
		PropEndorsementDtls_LaidUpFromDate.setAttribute("Value", "");
		PropEndorsementDtls_LaidUpFromDate.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_LaidUpToDate = new org.jdom2.Element("PropEndorsementDtls_LaidUpToDate");
		PropEndorsementDtls_LaidUpToDate.setAttribute("Value", "");
		PropEndorsementDtls_LaidUpToDate.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_LaidupVehicleCheck = new org.jdom2.Element(
				"PropEndorsementDtls_LaidupVehicleCheck");
		PropEndorsementDtls_LaidupVehicleCheck.setAttribute("Value", "False");
		PropEndorsementDtls_LaidupVehicleCheck.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_MajorRepairRenovation = new org.jdom2.Element(
				"PropEndorsementDtls_MajorRepairRenovation");
		PropEndorsementDtls_MajorRepairRenovation.setAttribute("Value", "False");
		PropEndorsementDtls_MajorRepairRenovation.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_NOCFinancier = new org.jdom2.Element("PropEndorsementDtls_NOCFinancier");
		PropEndorsementDtls_NOCFinancier.setAttribute("Value", "False");
		PropEndorsementDtls_NOCFinancier.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_PolicyFrom = new org.jdom2.Element("PropEndorsementDtls_PolicyFrom");
		PropEndorsementDtls_PolicyFrom.setAttribute("Value", "");
		PropEndorsementDtls_PolicyFrom.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_PolicyIssueDate = new org.jdom2.Element(
				"PropEndorsementDtls_PolicyIssueDate");
		PropEndorsementDtls_PolicyIssueDate.setAttribute("Value", "");
		PropEndorsementDtls_PolicyIssueDate.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_PolicyNo = new org.jdom2.Element("PropEndorsementDtls_PolicyNo");
		PropEndorsementDtls_PolicyNo.setAttribute("Value", "");
		PropEndorsementDtls_PolicyNo.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_PolicyTo = new org.jdom2.Element("PropEndorsementDtls_PolicyTo");
		PropEndorsementDtls_PolicyTo.setAttribute("Value", "");
		PropEndorsementDtls_PolicyTo.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_Reasonfornametransfer = new org.jdom2.Element(
				"PropEndorsementDtls_Reasonfornametransfer");
		PropEndorsementDtls_Reasonfornametransfer.setAttribute("Value", "");
		PropEndorsementDtls_Reasonfornametransfer.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_SICCode = new org.jdom2.Element("PropEndorsementDtls_SICCode");
		PropEndorsementDtls_SICCode.setAttribute("Value", "");
		PropEndorsementDtls_SICCode.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_SICName = new org.jdom2.Element("PropEndorsementDtls_SICName");
		PropEndorsementDtls_SICName.setAttribute("Value", "");
		PropEndorsementDtls_SICName.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_TypeOfTransfer = new org.jdom2.Element(
				"PropEndorsementDtls_SICTypeOfTransfer");
		PropEndorsementDtls_TypeOfTransfer.setAttribute("Value", "");
		PropEndorsementDtls_TypeOfTransfer.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_TypeOfEndorsement = new org.jdom2.Element(
				"PropEndorsementDtls_TypeOfEndorsement");
		PropEndorsementDtls_TypeOfEndorsement.setAttribute("Value", "");
		PropEndorsementDtls_TypeOfEndorsement.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_UFormFromDate = new org.jdom2.Element(
				"PropEndorsementDtls_UFormFromDate");
		PropEndorsementDtls_UFormFromDate.setAttribute("Value", "");
		PropEndorsementDtls_UFormFromDate.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_UFormToDate = new org.jdom2.Element("PropEndorsementDtls_UFormToDate");
		PropEndorsementDtls_UFormToDate.setAttribute("Value", "");
		PropEndorsementDtls_UFormToDate.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_UsageinInsuredPremises = new org.jdom2.Element(
				"PropEndorsementDtls_UsageinInsuredPremises");
		PropEndorsementDtls_UsageinInsuredPremises.setAttribute("Value", "False");
		PropEndorsementDtls_UsageinInsuredPremises.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_ValuationReport = new org.jdom2.Element(
				"PropEndorsementDtls_ValuationReport");
		PropEndorsementDtls_ValuationReport.setAttribute("Value", "True");
		PropEndorsementDtls_ValuationReport.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementDtls_VehicleLaidUptype = new org.jdom2.Element(
				"PropEndorsementDtls_VehicleLaidUptype");
		PropEndorsementDtls_VehicleLaidUptype.setAttribute("Value", "");
		PropEndorsementDtls_VehicleLaidUptype.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementDtls_orgcertificatesurrendered = new org.jdom2.Element(
				"PropEndorsementDtls_orgcertificatesurrendered");
		PropEndorsementDtls_orgcertificatesurrendered.setAttribute("Value", "False");
		PropEndorsementDtls_orgcertificatesurrendered.setAttribute("Type", "Boolean");

		org.jdom2.Element PropEndorsementEffectiveDate_EffectiveDate = new org.jdom2.Element(
				"PropEndorsementEffectiveDate_EffectiveDate");
		PropEndorsementEffectiveDate_EffectiveDate.setAttribute("Value", "");
		PropEndorsementEffectiveDate_EffectiveDate.setAttribute("Type", "String");

		org.jdom2.Element PropEndorsementEffectiveDate_EffectiveTime = new org.jdom2.Element(
				"PropEndorsementEffectiveDate_EffectiveTime");
		PropEndorsementEffectiveDate_EffectiveTime.setAttribute("Value", "");
		PropEndorsementEffectiveDate_EffectiveTime.setAttribute("Type", "String");

		org.jdom2.Element PropExclusionDetails_DepartmentCode = new org.jdom2.Element(
				"PropExclusionDetails_DepartmentCode");
		PropExclusionDetails_DepartmentCode.setAttribute("Value", "0");
		PropExclusionDetails_DepartmentCode.setAttribute("Type", "Double");

		org.jdom2.Element PropExclusionDetails_ProductCode = new org.jdom2.Element("PropExclusionDetails_ProductCode");
		PropExclusionDetails_ProductCode.setAttribute("Value", "0");
		PropExclusionDetails_ProductCode.setAttribute("Type", "Double");

		org.jdom2.Element PropExclusionDetails_SectionCode = new org.jdom2.Element("PropExclusionDetails_SectionCode");
		PropExclusionDetails_SectionCode.setAttribute("Value", "");
		PropExclusionDetails_SectionCode.setAttribute("Type", "String");

		org.jdom2.Element PropFees_PPCFee = new org.jdom2.Element("PropFees_PPCFee");
		PropFees_PPCFee.setAttribute("Value", "0");
		PropFees_PPCFee.setAttribute("Type", "Double");

		org.jdom2.Element PropFieldUserDetails_FiledUserCode = new org.jdom2.Element(
				"PropFieldUserDetails_FiledUserCode");
		PropFieldUserDetails_FiledUserCode.setAttribute("Value", "");
		PropFieldUserDetails_FiledUserCode.setAttribute("Type", "String");

		org.jdom2.Element PropFieldUserDetails_FiledUserLocation = new org.jdom2.Element(
				"PropFieldUserDetails_FiledUserLocation");
		PropFieldUserDetails_FiledUserLocation.setAttribute("Value", "");
		PropFieldUserDetails_FiledUserLocation.setAttribute("Type", "String");

		org.jdom2.Element PropFieldUserDetails_FieldUserName = new org.jdom2.Element(
				"PropFieldUserDetails_FieldUserName");
		PropFieldUserDetails_FieldUserName.setAttribute("Value", "");
		PropFieldUserDetails_FieldUserName.setAttribute("Type", "String");

		org.jdom2.Element PropFieldUserDetails_FieldUserVertical = new org.jdom2.Element(
				"PPropFieldUserDetails_FieldUserVertical");
		PropFieldUserDetails_FieldUserVertical.setAttribute("Value", "");
		PropFieldUserDetails_FieldUserVertical.setAttribute("Type", "String");

		org.jdom2.Element PropFieldUserDetails_FieldUserUserID = new org.jdom2.Element(
				"PropFieldUserDetails_FieldUserUserID");
		PropFieldUserDetails_FieldUserUserID.setAttribute("Value", "");
		PropFieldUserDetails_FieldUserUserID.setAttribute("Type", "String");

		org.jdom2.Element PropFinancierDetails_AddRow = new org.jdom2.Element("PropFinancierDetails_AddRow");
		PropFinancierDetails_AddRow.setAttribute("Value", "False");
		PropFinancierDetails_AddRow.setAttribute("Type", "Boolean");

		org.jdom2.Element PropFinancierDetails_FinancierCodeGenerator = new org.jdom2.Element(
				"PropFinancierDetails_FinancierCodeGenerator");
		PropFinancierDetails_FinancierCodeGenerator.setAttribute("Value", "False");
		PropFinancierDetails_FinancierCodeGenerator.setAttribute("Type", "Boolean");

		org.jdom2.Element PropFinancierDetails_Validate = new org.jdom2.Element("PropFinancierDetails_Validate");
		PropFinancierDetails_Validate.setAttribute("Value", "True");
		PropFinancierDetails_Validate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralNodes_ApplicationDate = new org.jdom2.Element("PropGeneralNodes_ApplicationDate");
		PropGeneralNodes_ApplicationDate.setAttribute("Value", "26/02/2016");
		PropGeneralNodes_ApplicationDate.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralNodes_ApplicationNo = new org.jdom2.Element("PropGeneralNodes_ApplicationNo");
		PropGeneralNodes_ApplicationNo.setAttribute("Value", "10201600003082");
		PropGeneralNodes_ApplicationNo.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralNodes_ParentCompanyName = new org.jdom2.Element(
				"PropGeneralNodes_ParentCompanyName");
		PropGeneralNodes_ParentCompanyName.setAttribute("Value", "");
		PropGeneralNodes_ParentCompanyName.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralNodes_PartnerApplicationDate = new org.jdom2.Element(
				"PropGeneralNodes_PartnerApplicationDate");
		PropGeneralNodes_PartnerApplicationDate.setAttribute("Value", "26/02/2016");
		PropGeneralNodes_PartnerApplicationDate.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralNodes_PartnerReferanceNO = new org.jdom2.Element(
				"PropGeneralNodes_PartnerReferanceNO");
		PropGeneralNodes_PartnerReferanceNO.setAttribute("Value", "111111");
		PropGeneralNodes_PartnerReferanceNO.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralNodes_RIInwardSharePercentage = new org.jdom2.Element(
				"PropGeneralNodes_RIInwardSharePercentage");
		PropGeneralNodes_RIInwardSharePercentage.setAttribute("Value", "0");
		PropGeneralNodes_RIInwardSharePercentage.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalGroup_CoverNoteDetails = new org.jdom2.Element(
				"PropGeneralProposalGroup_CoverNoteDetails");
		PropGeneralProposalGroup_CoverNoteDetails.setAttribute("Value", "");
		PropGeneralProposalGroup_CoverNoteDetails.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalGroup_DistributionChannel = new org.jdom2.Element(
				"PropGeneralProposalGroup_DistributionChannel");
		PropGeneralProposalGroup_DistributionChannel.setAttribute("Value", "");
		PropGeneralProposalGroup_DistributionChannel.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalGroup_Fees = new org.jdom2.Element("PropGeneralProposalGroup_Fees");
		PropGeneralProposalGroup_Fees.setAttribute("Value", "");
		PropGeneralProposalGroup_Fees.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalGroup_GeneralProposalInformation = new org.jdom2.Element(
				"PropGeneralProposalGroup_GeneralProposalInformation");
		PropGeneralProposalGroup_GeneralProposalInformation.setAttribute("Value", "");
		PropGeneralProposalGroup_GeneralProposalInformation.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ActiveFlag = new org.jdom2.Element(
				"PropGeneralProposalInformation_ActiveFlag");
		PropGeneralProposalInformation_ActiveFlag.setAttribute("Value", "YES");
		PropGeneralProposalInformation_ActiveFlag.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ApplicationNumber = new org.jdom2.Element(
				"PropGeneralProposalInformation_ApplicationNumber");
		PropGeneralProposalInformation_ApplicationNumber.setAttribute("Value", "");
		PropGeneralProposalInformation_ApplicationNumber.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_BankID = new org.jdom2.Element(
				"PropGeneralProposalInformation_BankID");
		PropGeneralProposalInformation_BankID.setAttribute("Value", "0");
		PropGeneralProposalInformation_BankID.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_BlnEndtNoBlock = new org.jdom2.Element(
				"PropGeneralProposalInformation_BlnEndtNoBlock");
		PropGeneralProposalInformation_BlnEndtNoBlock.setAttribute("Value", "False");
		PropGeneralProposalInformation_BlnEndtNoBlock.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_BranchInwardDate = new org.jdom2.Element(
				"PropGeneralProposalInformation_BranchInwardDate");
		PropGeneralProposalInformation_BranchInwardDate.setAttribute("Value", "26/02/2016");
		PropGeneralProposalInformation_BranchInwardDate.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_BranchInwardNumber = new org.jdom2.Element(
				"PropGeneralProposalInformation_BranchInwardNumber");
		PropGeneralProposalInformation_BranchInwardNumber.setAttribute("Value", "KP000116022500013");
		PropGeneralProposalInformation_BranchInwardNumber.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_BranchOfficeCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_BranchOfficeCode");
		PropGeneralProposalInformation_BranchOfficeCode.setAttribute("Value", "90001");
		PropGeneralProposalInformation_BranchOfficeCode.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_BussinessSourceInfo = new org.jdom2.Element(
				"PropGeneralProposalInformation_BussinessSourceInfo");
		PropGeneralProposalInformation_BussinessSourceInfo.setAttribute("Value", "");
		PropGeneralProposalInformation_BussinessSourceInfo.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_BussinessType_Mandatary = new org.jdom2.Element(
				"PropGeneralProposalInformation_BussinessType_Mandatary");
		PropGeneralProposalInformation_BussinessType_Mandatary.setAttribute("Value", jsonResult.getString("strBusinessType").trim());
		PropGeneralProposalInformation_BussinessType_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_CertificateNumber = new org.jdom2.Element(
				"PropGeneralProposalInformation_CertificateNumber");
		PropGeneralProposalInformation_CertificateNumber.setAttribute("Value", "");
		PropGeneralProposalInformation_CertificateNumber.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ChannelNumber = new org.jdom2.Element(
				"PropGeneralProposalInformation_ChannelNumber");
		PropGeneralProposalInformation_ChannelNumber.setAttribute("Value", "");
		PropGeneralProposalInformation_ChannelNumber.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_CoverNotePlace = new org.jdom2.Element(
				"PropGeneralProposalInformation_CoverNotePlace");
		PropGeneralProposalInformation_CoverNotePlace.setAttribute("Value", "");
		PropGeneralProposalInformation_CoverNotePlace.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_CovernoteGenType = new org.jdom2.Element(
				"PropGeneralProposalInformation_CovernoteGenType");
		PropGeneralProposalInformation_CovernoteGenType.setAttribute("Value", "AUTO");
		PropGeneralProposalInformation_CovernoteGenType.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_CustomerDtls = new org.jdom2.Element(
				"PropGeneralProposalInformation_CustomerDtls");
		PropGeneralProposalInformation_CustomerDtls.setAttribute("Value", "");
		PropGeneralProposalInformation_CustomerDtls.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_CustomerReferenceInfo = new org.jdom2.Element(
				"PropGeneralProposalInformation_CustomerReferenceInfo");
		PropGeneralProposalInformation_CustomerReferenceInfo.setAttribute("Value", "");
		PropGeneralProposalInformation_CustomerReferenceInfo.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_DayDiff = new org.jdom2.Element(
				"PropGeneralProposalInformation_DayDiff");
		PropGeneralProposalInformation_DayDiff.setAttribute("Value", "");
		PropGeneralProposalInformation_DayDiff.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_DeadId = new org.jdom2.Element(
				"PropGeneralProposalInformation_DeadId");
		PropGeneralProposalInformation_DeadId.setAttribute("Value", "");
		PropGeneralProposalInformation_DeadId.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_DepartmentCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_DepartmentCode");
		PropGeneralProposalInformation_DepartmentCode.setAttribute("Value", "31");
		PropGeneralProposalInformation_DepartmentCode.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_DepartmentName = new org.jdom2.Element(
				"PropGeneralProposalInformation_DepartmentName");
		PropGeneralProposalInformation_DepartmentName.setAttribute("Value", "");
		PropGeneralProposalInformation_DepartmentName.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_DisplayOfficeCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_DisplayOfficeCode");
		PropGeneralProposalInformation_DisplayOfficeCode.setAttribute("Value", "0001");
		PropGeneralProposalInformation_DisplayOfficeCode.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_DisplayStatus = new org.jdom2.Element(
				"PropGeneralProposalInformation_DisplayStatus");
		PropGeneralProposalInformation_DisplayStatus.setAttribute("Value", "");
		PropGeneralProposalInformation_DisplayStatus.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_EmployeeName = new org.jdom2.Element(
				"PropGeneralProposalInformation_EmployeeName");
		PropGeneralProposalInformation_EmployeeName.setAttribute("Value", "");
		PropGeneralProposalInformation_EmployeeName.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementDescription = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementDescription");
		PropGeneralProposalInformation_EndorsementDescription.setAttribute("Value", "");
		PropGeneralProposalInformation_EndorsementDescription.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementEffectiveDate = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementEffectiveDate");
		PropGeneralProposalInformation_EndorsementEffectiveDate.setAttribute("Value", "");
		PropGeneralProposalInformation_EndorsementEffectiveDate.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementNo = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementEffectiveNo");
		PropGeneralProposalInformation_EndorsementNo.setAttribute("Value", "0");
		PropGeneralProposalInformation_EndorsementNo.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementRequestType = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementRequestType");
		PropGeneralProposalInformation_EndorsementRequestType.setAttribute("Value", "");
		PropGeneralProposalInformation_EndorsementRequestType.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementRequestTypeCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementRequestTypeCode");
		PropGeneralProposalInformation_EndorsementRequestTypeCode.setAttribute("Value", "");
		PropGeneralProposalInformation_EndorsementRequestTypeCode.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementSi = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementSi");
		PropGeneralProposalInformation_EndorsementSi.setAttribute("Value", "829777");
		PropGeneralProposalInformation_EndorsementSi.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementType = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementType");
		PropGeneralProposalInformation_EndorsementType.setAttribute("Value", "");
		PropGeneralProposalInformation_EndorsementType.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_EndorsementWording = new org.jdom2.Element(
				"PropGeneralProposalInformation_EndorsementWording");
		PropGeneralProposalInformation_EndorsementWording.setAttribute("Value", "");
		PropGeneralProposalInformation_EndorsementWording.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_FrontingCompany = new org.jdom2.Element(
				"PropGeneralProposalInformation_FrontingCompany");
		PropGeneralProposalInformation_FrontingCompany.setAttribute("Value", "");
		PropGeneralProposalInformation_FrontingCompany.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_IfOthersProvideReason = new org.jdom2.Element(
				"PropGeneralProposalInformation_IfOthersProvideReason");
		PropGeneralProposalInformation_IfOthersProvideReason.setAttribute("Value", "");
		PropGeneralProposalInformation_IfOthersProvideReason.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_IndustryCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_IndustryCode");
		PropGeneralProposalInformation_IndustryCode.setAttribute("Value", "");
		PropGeneralProposalInformation_IndustryCode.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_InternalUWComments = new org.jdom2.Element(
				"PropGeneralProposalInformation_InternalUWComments");
		PropGeneralProposalInformation_InternalUWComments.setAttribute("Value", "cccc");
		PropGeneralProposalInformation_InternalUWComments.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_InwardNo = new org.jdom2.Element(
				"PropGeneralProposalInformation_InwardNo");
		PropGeneralProposalInformation_InwardNo.setAttribute("Value", "");
		PropGeneralProposalInformation_InwardNo.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_IsNCBApplicable = new org.jdom2.Element(
				"PropGeneralProposalInformation_IsNCBApplicable");
		PropGeneralProposalInformation_IsNCBApplicable.setAttribute("Value", "False");
		PropGeneralProposalInformation_IsNCBApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_IsSPCalcByDayDiffReqd = new org.jdom2.Element(
				"PropGeneralProposalInformation_IsSPCalcByDayDiffReqd");
		PropGeneralProposalInformation_IsSPCalcByDayDiffReqd.setAttribute("Value", "");
		PropGeneralProposalInformation_IsSPCalcByDayDiffReqd.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_LeadGenerator = new org.jdom2.Element(
				"PropGeneralProposalInformation_LeadGenerator");
		PropGeneralProposalInformation_LeadGenerator.setAttribute("Value", "bbb");
		PropGeneralProposalInformation_LeadGenerator.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ManualCovernoteNo = new org.jdom2.Element(
				"PropGeneralProposalInformation_ManualCovernoteNo");
		PropGeneralProposalInformation_ManualCovernoteNo.setAttribute("Value", "");
		PropGeneralProposalInformation_ManualCovernoteNo.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_MasterPolicy = new org.jdom2.Element(
				"PropGeneralProposalInformation_MasterPolicy");
		PropGeneralProposalInformation_MasterPolicy.setAttribute("Value", "");
		PropGeneralProposalInformation_MasterPolicy.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_MasterPolicyVersion = new org.jdom2.Element(
				"PropGeneralProposalInformation_MasterPolicyVersion");
		PropGeneralProposalInformation_MasterPolicyVersion.setAttribute("Value", "");
		PropGeneralProposalInformation_MasterPolicyVersion.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_MethodOfCalculation = new org.jdom2.Element(
				"PropGeneralProposalInformation_MethodOfCalculation");
		PropGeneralProposalInformation_MethodOfCalculation.setAttribute("Value", "12345");
		PropGeneralProposalInformation_MethodOfCalculation.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_NCBpercentage = new org.jdom2.Element(
				"PropGeneralProposalInformation_NCBpercentage");
		PropGeneralProposalInformation_NCBpercentage.setAttribute("Value", "0");
		PropGeneralProposalInformation_NCBpercentage.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_NextB = new org.jdom2.Element(
				"PropGeneralProposalInformation_NextB");
		PropGeneralProposalInformation_NextB.setAttribute("Value", "False");
		PropGeneralProposalInformation_NextB.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_NoPrevInsuranceFlag = new org.jdom2.Element(
				"PropGeneralProposalInformation_NoPrevInsuranceFlag");
		PropGeneralProposalInformation_NoPrevInsuranceFlag.setAttribute("Value", "False");
		PropGeneralProposalInformation_NoPrevInsuranceFlag.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_NonLiablePeriod = new org.jdom2.Element(
				"PropGeneralProposalInformation_NonLiablePeriod");
		PropGeneralProposalInformation_NonLiablePeriod.setAttribute("Value", "");
		PropGeneralProposalInformation_NonLiablePeriod.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_NumCovernoteNo = new org.jdom2.Element(
				"PropGeneralProposalInformation_NumCovernoteNo");
		PropGeneralProposalInformation_NumCovernoteNo.setAttribute("Value", "0");
		PropGeneralProposalInformation_NumCovernoteNo.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_OfficeCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_OfficeCode");
		PropGeneralProposalInformation_OfficeCode.setAttribute("Value", "");
		PropGeneralProposalInformation_OfficeCode.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_OfficeName = new org.jdom2.Element(
				"PropGeneralProposalInformation_OfficeName");
		PropGeneralProposalInformation_OfficeName.setAttribute("Value", "HO");
		PropGeneralProposalInformation_OfficeName.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_OldCovernoteNo = new org.jdom2.Element(
				"PropGeneralProposalInformation_OldCovernoteNo");
		PropGeneralProposalInformation_OldCovernoteNo.setAttribute("Value", "");
		PropGeneralProposalInformation_OldCovernoteNo.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_OpsSalesPartnerUserComments = new org.jdom2.Element(
				"PropGeneralProposalInformation_OpsSalesPartnerUserComments");
		PropGeneralProposalInformation_OpsSalesPartnerUserComments.setAttribute("Value", "dddd");
		PropGeneralProposalInformation_OpsSalesPartnerUserComments.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_OptionForCalculation = new org.jdom2.Element(
				"PropGeneralProposalInformation_OptionForCalculation");
		PropGeneralProposalInformation_OptionForCalculation.setAttribute("Value", "Yearly");
		PropGeneralProposalInformation_OptionForCalculation.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_PolicyCurrency = new org.jdom2.Element(
				"PropGeneralProposalInformation_PolicyCurrency");
		PropGeneralProposalInformation_PolicyCurrency.setAttribute("Value", "");
		PropGeneralProposalInformation_PolicyCurrency.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_PolicyEffectivedate = new org.jdom2.Element(
				"PropGeneralProposalInformation_PolicyEffectivedate");
		PropGeneralProposalInformation_PolicyEffectivedate.setAttribute("Value", "");
		PropGeneralProposalInformation_PolicyEffectivedate.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_PolicyNo = new org.jdom2.Element(
				"PropGeneralProposalInformation_PolicyNo");
		PropGeneralProposalInformation_PolicyNo.setAttribute("Value", "0");
		PropGeneralProposalInformation_PolicyNo.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_PolicyNumberChar = new org.jdom2.Element(
				"PropGeneralProposalInformation_PolicyNumberChar");
		PropGeneralProposalInformation_PolicyNumberChar.setAttribute("Value", "");
		PropGeneralProposalInformation_PolicyNumberChar.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_PolicySchedule_Mandatary = new org.jdom2.Element(
				"PropGeneralProposalInformation_PolicySchedule_Mandatary");
		PropGeneralProposalInformation_PolicySchedule_Mandatary.setAttribute("Value", "Yes");
		PropGeneralProposalInformation_PolicySchedule_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_PolicyTerm = new org.jdom2.Element(
				"PropGeneralProposalInformation_PolicyTerm");
		PropGeneralProposalInformation_PolicyTerm.setAttribute("Value", "");
		PropGeneralProposalInformation_PolicyTerm.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_PrintingType = new org.jdom2.Element(
				"PropGeneralProposalInformation_PrintingType");
		PropGeneralProposalInformation_PrintingType.setAttribute("Value", "With Policy Wording");
		PropGeneralProposalInformation_PrintingType.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ProductCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_ProductCode");
		PropGeneralProposalInformation_ProductCode.setAttribute("Value", "3121");
		PropGeneralProposalInformation_ProductCode.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_ProductName = new org.jdom2.Element(
				"PropGeneralProposalInformation_ProductName");
		PropGeneralProposalInformation_ProductName.setAttribute("Value", "");
		PropGeneralProposalInformation_ProductName.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ProposalDate_Mandatary = new org.jdom2.Element(
				"PropGeneralProposalInformation_ProposalDate_Mandatary");
		PropGeneralProposalInformation_ProposalDate_Mandatary.setAttribute("Value", "26/02/2016");
		PropGeneralProposalInformation_ProposalDate_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ProposalFormNumber = new org.jdom2.Element(
				"PropGeneralProposalInformation_ProposalFormNumber");
		PropGeneralProposalInformation_ProposalFormNumber.setAttribute("Value", "");
		PropGeneralProposalInformation_ProposalFormNumber.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ProvisionalBooking = new org.jdom2.Element(
				"PropGeneralProposalInformation_ProvisionalBooking");
		PropGeneralProposalInformation_ProvisionalBooking.setAttribute("Value", "");
		PropGeneralProposalInformation_ProvisionalBooking.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ProvisionalBookingReason = new org.jdom2.Element(
				"PropGeneralProposalInformation_ProvisionalBookingReason");
		PropGeneralProposalInformation_ProvisionalBookingReason.setAttribute("Value", "");
		PropGeneralProposalInformation_ProvisionalBookingReason.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_QuickId = new org.jdom2.Element(
				"PropGeneralProposalInformation_QuickId");
		PropGeneralProposalInformation_QuickId.setAttribute("Value", "");
		PropGeneralProposalInformation_QuickId.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_QuoteNumber = new org.jdom2.Element(
				"PropGeneralProposalInformation_QuoteNumber");
		PropGeneralProposalInformation_QuoteNumber.setAttribute("Value", "");
		PropGeneralProposalInformation_QuoteNumber.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ReInsuranceInward = new org.jdom2.Element(
				"PropGeneralProposalInformation_ReInsuranceInward");
		PropGeneralProposalInformation_ReInsuranceInward.setAttribute("Value", "False");
		PropGeneralProposalInformation_ReInsuranceInward.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_ReSharePer = new org.jdom2.Element(
				"PropGeneralProposalInformation_ReSharePer");
		PropGeneralProposalInformation_ReSharePer.setAttribute("Value", "");
		PropGeneralProposalInformation_ReSharePer.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ReferenceNoDate = new org.jdom2.Element(
				"PropGeneralProposalInformation_ReferenceNoDate");
		PropGeneralProposalInformation_ReferenceNoDate.setAttribute("Value", "");
		PropGeneralProposalInformation_ReferenceNoDate.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ReferralCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_ReferralCode");
		PropGeneralProposalInformation_ReferralCode.setAttribute("Value", "");
		PropGeneralProposalInformation_ReferralCode.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_RelationshipType = new org.jdom2.Element(
				"PropGeneralProposalInformation_RelationshipType");
		PropGeneralProposalInformation_RelationshipType.setAttribute("Value", "");
		PropGeneralProposalInformation_RelationshipType.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_Remarks = new org.jdom2.Element(
				"PropGeneralProposalInformation_Remarks");
		PropGeneralProposalInformation_Remarks.setAttribute("Value", "");
		PropGeneralProposalInformation_Remarks.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_RetainCancellationPremium = new org.jdom2.Element(
				"PropGeneralProposalInformation_RetainCancellationPremium");
		PropGeneralProposalInformation_RetainCancellationPremium.setAttribute("Value", "");
		PropGeneralProposalInformation_RetainCancellationPremium.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_SalesTax = new org.jdom2.Element(
				"PropGeneralProposalInformation_SalesTax");
		PropGeneralProposalInformation_SalesTax.setAttribute("Value", "");
		PropGeneralProposalInformation_SalesTax.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_Sector_Mandatary = new org.jdom2.Element(
				"PropGeneralProposalInformation_Sector_Mandatary");
		PropGeneralProposalInformation_Sector_Mandatary.setAttribute("Value", "Urban");
		PropGeneralProposalInformation_Sector_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_SerDealId = new org.jdom2.Element(
				"PropGeneralProposalInformation_SerDealId");
		PropGeneralProposalInformation_SerDealId.setAttribute("Value", "");
		PropGeneralProposalInformation_SerDealId.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ServiceTaxExemption = new org.jdom2.Element(
				"PropGeneralProposalInformation_ServiceTaxExemption");
		PropGeneralProposalInformation_ServiceTaxExemption.setAttribute("Value", "False");
		PropGeneralProposalInformation_ServiceTaxExemption.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_ServiceTaxExemptionCategory_Mandatary = new org.jdom2.Element(
				"PropGeneralProposalInformation_ServiceTaxExemptionCategory_Mandatary");
		PropGeneralProposalInformation_ServiceTaxExemptionCategory_Mandatary.setAttribute("Value", "No Exemption");
		PropGeneralProposalInformation_ServiceTaxExemptionCategory_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ServiceTaxExemptionReason = new org.jdom2.Element(
				"PropGeneralProposalInformation_ServiceTaxExemptionReason");
		PropGeneralProposalInformation_ServiceTaxExemptionReason.setAttribute("Value", "No Exemption");
		PropGeneralProposalInformation_ServiceTaxExemptionReason.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_SourceOfTransaction = new org.jdom2.Element(
				"PropGeneralProposalInformation_SourceOfTransaction");
		PropGeneralProposalInformation_SourceOfTransaction.setAttribute("Value", "aaaa");
		PropGeneralProposalInformation_SourceOfTransaction.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_TotalSi = new org.jdom2.Element(
				"PropGeneralProposalInformation_TotalSi");
		PropGeneralProposalInformation_TotalSi.setAttribute("Value", "829777");
		PropGeneralProposalInformation_TotalSi.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposalInformation_TypeOfBusiness = new org.jdom2.Element(
				"PropGeneralProposalInformation_TypeOfBusiness");
		PropGeneralProposalInformation_TypeOfBusiness.setAttribute("Value", "");
		PropGeneralProposalInformation_TypeOfBusiness.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_TypeOfCalculation = new org.jdom2.Element(
				"PropGeneralProposalInformation_TypeOfCalculation");
		PropGeneralProposalInformation_TypeOfCalculation.setAttribute("Value", "");
		PropGeneralProposalInformation_TypeOfCalculation.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_TypeOfIndustry = new org.jdom2.Element(
				"PropGeneralProposalInformation_TypeOfIndustry");
		PropGeneralProposalInformation_TypeOfIndustry.setAttribute("Value", "");
		PropGeneralProposalInformation_TypeOfIndustry.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_TypeOfPolicy = new org.jdom2.Element(
				"PropGeneralProposalInformation_TypeOfPolicy");
		PropGeneralProposalInformation_TypeOfPolicy.setAttribute("Value", "SYSTEM");
		PropGeneralProposalInformation_TypeOfPolicy.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_TypeofCancellation = new org.jdom2.Element(
				"PropGeneralProposalInformation_TypeofCancellation");
		PropGeneralProposalInformation_TypeofCancellation.setAttribute("Value", "");
		PropGeneralProposalInformation_TypeofCancellation.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_UWLocation = new org.jdom2.Element(
				"PropGeneralProposalInformation_UWLocation");
		PropGeneralProposalInformation_UWLocation.setAttribute("Value", "");
		PropGeneralProposalInformation_UWLocation.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_UWLocationCode = new org.jdom2.Element(
				"PropGeneralProposalInformation_UWLocationCode");
		PropGeneralProposalInformation_UWLocationCode.setAttribute("Value", "");
		PropGeneralProposalInformation_UWLocationCode.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ValidationFlag = new org.jdom2.Element(
				"PropGeneralProposalInformation_UWLocationCode");
		PropGeneralProposalInformation_ValidationFlag.setAttribute("Value", "");
		PropGeneralProposalInformation_ValidationFlag.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposalInformation_ValuationReport = new org.jdom2.Element(
				"PropGeneralProposalInformation_ValuationReport");
		PropGeneralProposalInformation_ValuationReport.setAttribute("Value", "False");
		PropGeneralProposalInformation_ValuationReport.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_VehicleLaidUpFrom = new org.jdom2.Element(
				"PropGeneralProposalInformation_VehicleLaidUpFrom");
		PropGeneralProposalInformation_VehicleLaidUpFrom.setAttribute("Value", "False");
		PropGeneralProposalInformation_VehicleLaidUpFrom.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_VehicleLaidUpTo = new org.jdom2.Element(
				"PropGeneralProposalInformation_VehicleLaidUpTo");
		PropGeneralProposalInformation_VehicleLaidUpTo.setAttribute("Value", "False");
		PropGeneralProposalInformation_VehicleLaidUpTo.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_covernoteissuedate = new org.jdom2.Element(
				"PropGeneralProposalInformation_covernoteissuedate");
		PropGeneralProposalInformation_covernoteissuedate.setAttribute("Value", "False");
		PropGeneralProposalInformation_covernoteissuedate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_covernoteissuetime = new org.jdom2.Element(
				"PropGeneralProposalInformation_covernoteissuetime");
		PropGeneralProposalInformation_covernoteissuetime.setAttribute("Value", "False");
		PropGeneralProposalInformation_covernoteissuetime.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_disprntoffcd = new org.jdom2.Element(
				"PropGeneralProposalInformation_disprntoffcd");
		PropGeneralProposalInformation_disprntoffcd.setAttribute("Value", "False");
		PropGeneralProposalInformation_disprntoffcd.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_iscovernoteused = new org.jdom2.Element(
				"PropGeneralProposalInformation_iscovernoteused");
		PropGeneralProposalInformation_iscovernoteused.setAttribute("Value", "False");
		PropGeneralProposalInformation_iscovernoteused.setAttribute("Type", "Boolean");

		org.jdom2.Element PropGeneralProposalInformation_numTotalFee = new org.jdom2.Element(
				"PropGeneralProposalInformation_numTotalFee");
		PropGeneralProposalInformation_numTotalFee.setAttribute("Value", "0");
		PropGeneralProposalInformation_numTotalFee.setAttribute("Type", "Double");

		org.jdom2.Element PropGeneralProposal_GeneralProposalGroup = new org.jdom2.Element(
				"PropGeneralProposal_GeneralProposalGroup");
		PropGeneralProposal_GeneralProposalGroup.setAttribute("Value", "");
		PropGeneralProposal_GeneralProposalGroup.setAttribute("Type", "String");

		org.jdom2.Element PropGeneralProposal_IsCustomWorkflowCondition = new org.jdom2.Element(
				"PropGeneralProposal_IsCustomWorkflowCondition");
		PropGeneralProposal_IsCustomWorkflowCondition.setAttribute("Value", "");
		PropGeneralProposal_IsCustomWorkflowCondition.setAttribute("Type", "String");

		org.jdom2.Element PropHigherExcessDiscount_HE_Disc_RateType_In = new org.jdom2.Element(
				"PropHigherExcessDiscount_HE_Disc_RateType_In");
		PropHigherExcessDiscount_HE_Disc_RateType_In.setAttribute("Value", "0");
		PropHigherExcessDiscount_HE_Disc_RateType_In.setAttribute("Type", "Double");

		org.jdom2.Element PropIntermediaryDetails_BrokeragePercentage = new org.jdom2.Element(
				"PropIntermediaryDetails_BrokeragePercentage");
		PropIntermediaryDetails_BrokeragePercentage.setAttribute("Value", "");
		PropIntermediaryDetails_BrokeragePercentage.setAttribute("Type", "String");

		org.jdom2.Element PropIntermediaryDetails_IntermediaryCityCode = new org.jdom2.Element(
				"PropIntermediaryDetails_IntermediaryCityCode");
		PropIntermediaryDetails_IntermediaryCityCode.setAttribute("Value", "");
		PropIntermediaryDetails_IntermediaryCityCode.setAttribute("Type", "String");

		org.jdom2.Element PropIntermediaryDetails_IntermediaryCityName = new org.jdom2.Element(
				"PropIntermediaryDetails_IntermediaryCityName");
		PropIntermediaryDetails_IntermediaryCityName.setAttribute("Value", "");
		PropIntermediaryDetails_IntermediaryCityName.setAttribute("Type", "String");

		org.jdom2.Element PropIntermediaryDetails_IntermediaryCode = new org.jdom2.Element(
				"PropIntermediaryDetails_IntermediaryCode");
		PropIntermediaryDetails_IntermediaryCode.setAttribute("Value", "3101780000");
		PropIntermediaryDetails_IntermediaryCode.setAttribute("Type", "String");

		org.jdom2.Element PropIntermediaryDetails_IntermediaryName = new org.jdom2.Element(
				"PropIntermediaryDetails_IntermediaryName");
		PropIntermediaryDetails_IntermediaryName.setAttribute("Value", "BAS EK KING RAJPUT");
		PropIntermediaryDetails_IntermediaryName.setAttribute("Type", "String");

		org.jdom2.Element PropIntermediaryDetails_IntermediaryType = new org.jdom2.Element(
				"PropIntermediaryDetails_IntermediaryType");
		PropIntermediaryDetails_IntermediaryType.setAttribute("Value", "BROKER");
		PropIntermediaryDetails_IntermediaryType.setAttribute("Type", "String");

		org.jdom2.Element PropMODetails_PrimaryMOCode = new org.jdom2.Element("PropMODetails_PrimaryMOCode");
		PropMODetails_PrimaryMOCode.setAttribute("Value", "367");
		PropMODetails_PrimaryMOCode.setAttribute("Type", "String");

		org.jdom2.Element PropMODetails_PrimaryMOName = new org.jdom2.Element("PropMODetails_PrimaryMOName");
		PropMODetails_PrimaryMOName.setAttribute("Value", "BAS EK KING RAJPUT");
		PropMODetails_PrimaryMOName.setAttribute("Type", "String");

		org.jdom2.Element PropMODetails_SecondaryMOCode = new org.jdom2.Element("PropMODetails_SecondaryMOCode");
		PropMODetails_SecondaryMOCode.setAttribute("Value", "");
		PropMODetails_SecondaryMOCode.setAttribute("Type", "String");

		org.jdom2.Element PropMODetails_SecondaryMOLocation = new org.jdom2.Element(
				"PropMODetails_SecondaryMOLocation");
		PropMODetails_SecondaryMOLocation.setAttribute("Value", "");
		PropMODetails_SecondaryMOLocation.setAttribute("Type", "String");

		org.jdom2.Element PropMODetails_SecondaryMOName = new org.jdom2.Element("PropMODetails_SecondaryMOName");
		PropMODetails_SecondaryMOName.setAttribute("Value", "");
		PropMODetails_SecondaryMOName.setAttribute("Type", "String");

		org.jdom2.Element PropMODetails_TertiaryMOCode = new org.jdom2.Element("PropMODetails_TertiaryMOCode");
		PropMODetails_TertiaryMOCode.setAttribute("Value", "");
		PropMODetails_TertiaryMOCode.setAttribute("Type", "String");

		org.jdom2.Element PropMODetails_TertiaryMOName = new org.jdom2.Element("PropMODetails_TertiaryMOName");
		PropMODetails_TertiaryMOName.setAttribute("Value", "");
		PropMODetails_TertiaryMOName.setAttribute("Type", "String");

		org.jdom2.Element PropMotorOtherNodes_AfterFetch = new org.jdom2.Element("PropMotorOtherNodes_AfterFetch");
		PropMotorOtherNodes_AfterFetch.setAttribute("Value", "");
		PropMotorOtherNodes_AfterFetch.setAttribute("Type", "String");

		org.jdom2.Element PropMotorOtherNodes_CancelationDetail = new org.jdom2.Element(
				"PropMotorOtherNodes_CancelationDetail");
		PropMotorOtherNodes_CancelationDetail.setAttribute("Value", "");
		PropMotorOtherNodes_CancelationDetail.setAttribute("Type", "String");

		org.jdom2.Element PropMotorOtherNodes_EndorsementDtls = new org.jdom2.Element(
				"PropMotorOtherNodes_EndorsementDtls");
		PropMotorOtherNodes_EndorsementDtls.setAttribute("Value", "");
		PropMotorOtherNodes_EndorsementDtls.setAttribute("Type", "String");

		org.jdom2.Element PropMotorOtherNodes_GeneralNodes = new org.jdom2.Element("PropMotorOtherNodes_GeneralNodes");
		PropMotorOtherNodes_GeneralNodes.setAttribute("Value", "");
		PropMotorOtherNodes_GeneralNodes.setAttribute("Type", "String");

		org.jdom2.Element PropMotorOtherNodes_validate_aftercalc = new org.jdom2.Element(
				"PropMotorOtherNodes_validate_aftercalc");
		PropMotorOtherNodes_validate_aftercalc.setAttribute("Value", "");
		PropMotorOtherNodes_validate_aftercalc.setAttribute("Type", "String");

		org.jdom2.Element PropMotorOtherNodes_validate_beforecalc = new org.jdom2.Element(
				"PropMotorOtherNodes_validate_beforecalc");
		PropMotorOtherNodes_validate_beforecalc.setAttribute("Value", "");
		PropMotorOtherNodes_validate_beforecalc.setAttribute("Type", "String");

		org.jdom2.Element PropNonLiablePeriod_NonLiableEndTime = new org.jdom2.Element(
				"PropNonLiablePeriod_NonLiableEndTime");
		PropNonLiablePeriod_NonLiableEndTime.setAttribute("Value", "");
		PropNonLiablePeriod_NonLiableEndTime.setAttribute("Type", "String");

		org.jdom2.Element PropNonLiablePeriod_NonLiableFromDate = new org.jdom2.Element(
				"PropNonLiablePeriod_NonLiableFromDate");
		PropNonLiablePeriod_NonLiableFromDate.setAttribute("Value", "");
		PropNonLiablePeriod_NonLiableFromDate.setAttribute("Type", "String");

		org.jdom2.Element PropNonLiablePeriod_NonLiableStartTime = new org.jdom2.Element(
				"PropNonLiablePeriod_NonLiableStartTime");
		PropNonLiablePeriod_NonLiableStartTime.setAttribute("Value", "");
		PropNonLiablePeriod_NonLiableStartTime.setAttribute("Type", "String");

		org.jdom2.Element PropNonLiablePeriod_NonLiableToDate = new org.jdom2.Element(
				"PropNonLiablePeriod_NonLiableToDate");
		PropNonLiablePeriod_NonLiableToDate.setAttribute("Value", "");
		PropNonLiablePeriod_NonLiableToDate.setAttribute("Type", "String");

		org.jdom2.Element PropParameters_AddClause = new org.jdom2.Element("PropParameters_AddClause");
		PropParameters_AddClause.setAttribute("Value", "False");
		PropParameters_AddClause.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_AddCondition = new org.jdom2.Element("PropParameters_AddCondition");
		PropParameters_AddCondition.setAttribute("Value", "False");
		PropParameters_AddCondition.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_AddExclusion = new org.jdom2.Element("PropParameters_AddExclusion");
		PropParameters_AddExclusion.setAttribute("Value", "False");
		PropParameters_AddExclusion.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_AddWarranty = new org.jdom2.Element("PropParameters_AddWarranty");
		PropParameters_AddWarranty.setAttribute("Value", "False");
		PropParameters_AddWarranty.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_AfterFetch = new org.jdom2.Element("PropParameters_AfterFetch");
		PropParameters_AfterFetch.setAttribute("Value", "True");
		PropParameters_AfterFetch.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ClauseApplicable = new org.jdom2.Element("PropParameters_ClauseApplicable");
		PropParameters_ClauseApplicable.setAttribute("Value", "True");
		PropParameters_ClauseApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_CoInsuranceApplicability = new org.jdom2.Element(
				"PropParameters_CoInsuranceApplicability");
		PropParameters_CoInsuranceApplicability.setAttribute("Value", "True");
		PropParameters_CoInsuranceApplicability.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_CommissionApplicable = new org.jdom2.Element(
				"PropParameters_CommissionApplicable");
		PropParameters_CommissionApplicable.setAttribute("Value", "True");
		PropParameters_CommissionApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ConditionApplicable = new org.jdom2.Element(
				"PropParameters_ConditionApplicable");
		PropParameters_ConditionApplicable.setAttribute("Value", "True");
		PropParameters_ConditionApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_CoverNoteApplicability = new org.jdom2.Element(
				"PropParameters_CoverNoteApplicability");
		PropParameters_CoverNoteApplicability.setAttribute("Value", "True");
		PropParameters_CoverNoteApplicability.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_EQHigherExcessDiscount = new org.jdom2.Element(
				"PropParameters_EQHigherExcessDiscount");
		PropParameters_EQHigherExcessDiscount.setAttribute("Value", "False");
		PropParameters_EQHigherExcessDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ExclusionApplicable = new org.jdom2.Element(
				"PropParameters_ExclusionApplicable");
		PropParameters_ExclusionApplicable.setAttribute("Value", "True");
		PropParameters_ExclusionApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_FamilyPackageDiscount = new org.jdom2.Element(
				"PropParameters_FamilyPackageDiscount");
		PropParameters_FamilyPackageDiscount.setAttribute("Value", "False");
		PropParameters_FamilyPackageDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_FinancierApplicability = new org.jdom2.Element(
				"PropParameters_FinancierApplicability");
		PropParameters_FinancierApplicability.setAttribute("Value", "True");
		PropParameters_FinancierApplicability.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_FloaterRiskLoading = new org.jdom2.Element(
				"PropParameters_FloaterRiskLoading");
		PropParameters_FloaterRiskLoading.setAttribute("Value", "False");
		PropParameters_FloaterRiskLoading.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_GroupDiscount = new org.jdom2.Element("PropParameters_GroupDiscount");
		PropParameters_GroupDiscount.setAttribute("Value", "False");
		PropParameters_GroupDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_HighClaimLoading = new org.jdom2.Element("PropParameters_HighClaimLoading");
		PropParameters_HighClaimLoading.setAttribute("Value", "False");
		PropParameters_HighClaimLoading.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_HigherExcessDiscount = new org.jdom2.Element(
				"PropParameters_HigherExcessDiscount");
		PropParameters_HigherExcessDiscount.setAttribute("Value", "False");
		PropParameters_HigherExcessDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_Installment = new org.jdom2.Element("PropParameters_Installment");
		PropParameters_Installment.setAttribute("Value", "False");
		PropParameters_Installment.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_LongTermPolicy = new org.jdom2.Element("PropParameters_LongTermPolicy");
		PropParameters_LongTermPolicy.setAttribute("Value", "False");
		PropParameters_LongTermPolicy.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_LowClaimDiscount = new org.jdom2.Element("PropParameters_LowClaimDiscount");
		PropParameters_LowClaimDiscount.setAttribute("Value", "False");
		PropParameters_LowClaimDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_MinimumPremium = new org.jdom2.Element("PropParameters_MinimumPremium");
		PropParameters_MinimumPremium.setAttribute("Value", "True");
		PropParameters_MinimumPremium.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_NoClaimDiscount = new org.jdom2.Element("PropParameters_NoClaimDiscount");
		PropParameters_NoClaimDiscount.setAttribute("Value", "False");
		PropParameters_NoClaimDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_OverageLoading = new org.jdom2.Element("PropParameters_OverageLoading");
		PropParameters_OverageLoading.setAttribute("Value", "False");
		PropParameters_OverageLoading.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_PasPolicyApplicable = new org.jdom2.Element(
				"PropParameters_PasPolicyApplicable");
		PropParameters_PasPolicyApplicable.setAttribute("Value", "");
		PropParameters_PasPolicyApplicable.setAttribute("Type", "String");

		org.jdom2.Element PropParameters_PastPolicyApplicable = new org.jdom2.Element(
				"PropParameters_PastPolicyApplicable");
		PropParameters_PastPolicyApplicable.setAttribute("Value", "False");
		PropParameters_PastPolicyApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_PolicyCancellation = new org.jdom2.Element(
				"PropParameters_PolicyCancellation");
		PropParameters_PolicyCancellation.setAttribute("Value", "False");
		PropParameters_PolicyCancellation.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_PopulateTransactionBegin = new org.jdom2.Element(
				"PropParameters_PopulateTransactionBegin");
		PropParameters_PopulateTransactionBegin.setAttribute("Value", "True");
		PropParameters_PopulateTransactionBegin.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_PreviousPolicyApplicable = new org.jdom2.Element(
				"PropParameters_PreviousPolicyApplicable");
		PropParameters_PreviousPolicyApplicable.setAttribute("Value", "");
		PropParameters_PreviousPolicyApplicable.setAttribute("Type", "String");

		org.jdom2.Element PropParameters_RenewalAllowed = new org.jdom2.Element("PropParameters_RenewalAllowed");
		PropParameters_RenewalAllowed.setAttribute("Value", "True");
		PropParameters_RenewalAllowed.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_SectionDiscount = new org.jdom2.Element("PropParameters_SectionDiscount");
		PropParameters_SectionDiscount.setAttribute("Value", "False");
		PropParameters_SectionDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ServiceTaxExempted = new org.jdom2.Element(
				"PropParameters_ServiceTaxExempted");
		PropParameters_ServiceTaxExempted.setAttribute("Value", "False");
		PropParameters_ServiceTaxExempted.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ShortPeriodLongPeriod = new org.jdom2.Element(
				"PropParameters_ShortPeriodLongPeriod");
		PropParameters_ShortPeriodLongPeriod.setAttribute("Value", "True");
		PropParameters_ShortPeriodLongPeriod.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_SpecialDiscountApplicable = new org.jdom2.Element(
				"PropParameters_SpecialDiscountApplicable");
		PropParameters_SpecialDiscountApplicable.setAttribute("Value", "True");
		PropParameters_SpecialDiscountApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_StaffDiscount = new org.jdom2.Element("PropParameters_StaffDiscount");
		PropParameters_StaffDiscount.setAttribute("Value", "False");
		PropParameters_StaffDiscount.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_StampDutyChargeable = new org.jdom2.Element(
				"PropParameters_StampDutyChargeable");
		PropParameters_StampDutyChargeable.setAttribute("Value", "False");
		PropParameters_StampDutyChargeable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_TariffProduct = new org.jdom2.Element("PropParameters_TariffProduct");
		PropParameters_TariffProduct.setAttribute("Value", "True");
		PropParameters_TariffProduct.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_TransferFees = new org.jdom2.Element("PropParameters_TransferFees");
		PropParameters_TransferFees.setAttribute("Value", "False");
		PropParameters_TransferFees.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_VDFDiscountAllowed = new org.jdom2.Element(
				"PropParameters_VDFDiscountAllowed");
		PropParameters_VDFDiscountAllowed.setAttribute("Value", "False");
		PropParameters_VDFDiscountAllowed.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ValidateClause = new org.jdom2.Element("PropParameters_ValidateClause");
		PropParameters_ValidateClause.setAttribute("Value", "True");
		PropParameters_ValidateClause.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ValidateCondition = new org.jdom2.Element("PropParameters_ValidateCondition");
		PropParameters_ValidateCondition.setAttribute("Value", "True");
		PropParameters_ValidateCondition.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ValidateEndorsementData = new org.jdom2.Element(
				"PropParameters_ValidateEndorsementData");
		PropParameters_ValidateEndorsementData.setAttribute("Value", "True");
		PropParameters_ValidateEndorsementData.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ValidateExclusion = new org.jdom2.Element("PropParameters_ValidateExclusion");
		PropParameters_ValidateExclusion.setAttribute("Value", "True");
		PropParameters_ValidateExclusion.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_ValidateWarranty = new org.jdom2.Element("PropParameters_ValidateWarranty");
		PropParameters_ValidateWarranty.setAttribute("Value", "True");
		PropParameters_ValidateWarranty.setAttribute("Type", "Boolean");

		org.jdom2.Element PropParameters_WarrentyApplicable = new org.jdom2.Element(
				"PropParameters_WarrentyApplicable");
		PropParameters_WarrentyApplicable.setAttribute("Value", "True");
		PropParameters_WarrentyApplicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropPolicyCoverDetails_Applicable = new org.jdom2.Element(
				"PropPolicyCoverDetails_Applicable");
		PropPolicyCoverDetails_Applicable.setAttribute("Value", "False");
		PropPolicyCoverDetails_Applicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropPolicyCoverDetails_CoverGroups = new org.jdom2.Element(
				"PropPolicyCoverDetails_CoverGroups");
		PropPolicyCoverDetails_CoverGroups.setAttribute("Value", "");
		PropPolicyCoverDetails_CoverGroups.setAttribute("Type", "String");

		org.jdom2.Element PropPolicyCoverDetails_DifferentialSI = new org.jdom2.Element(
				"PropPolicyCoverDetails_DifferentialSI");
		PropPolicyCoverDetails_DifferentialSI.setAttribute("Value", "0");
		PropPolicyCoverDetails_DifferentialSI.setAttribute("Type", "Double");

		org.jdom2.Element PropPolicyCoverDetails_EndorsementAmount = new org.jdom2.Element(
				"PropPolicyCoverDetails_EndorsementAmount");
		PropPolicyCoverDetails_EndorsementAmount.setAttribute("Value", "0");
		PropPolicyCoverDetails_EndorsementAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropPolicyCoverDetails_Premium = new org.jdom2.Element("PropPolicyCoverDetails_Premium");
		PropPolicyCoverDetails_Premium.setAttribute("Value", "0");
		PropPolicyCoverDetails_Premium.setAttribute("Type", "Double");

		org.jdom2.Element PropPolicyCoverDetails_Rate = new org.jdom2.Element("PropPolicyCoverDetails_Rate");
		PropPolicyCoverDetails_Rate.setAttribute("Value", "0");
		PropPolicyCoverDetails_Rate.setAttribute("Type", "Double");

		org.jdom2.Element PropPolicyCoverDetails_SumInsured = new org.jdom2.Element(
				"PropPolicyCoverDetails_SumInsured");
		PropPolicyCoverDetails_SumInsured.setAttribute("Value", "0");
		PropPolicyCoverDetails_SumInsured.setAttribute("Type", "Double");

		org.jdom2.Element PropPolicyEffectivedate_Fromdate_Mandatary = new org.jdom2.Element(
				"PropPolicyEffectivedate_Fromdate_Mandatary");
		PropPolicyEffectivedate_Fromdate_Mandatary.setAttribute("Value", "26/02/2016");
		PropPolicyEffectivedate_Fromdate_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropPolicyEffectivedate_Fromhour_Mandatary = new org.jdom2.Element(
				"PropPolicyEffectivedate_Fromhour_Mandatary");
		PropPolicyEffectivedate_Fromhour_Mandatary.setAttribute("Value", "18:11");
		PropPolicyEffectivedate_Fromhour_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropPolicyEffectivedate_Todate_Mandatary = new org.jdom2.Element(
				"PropPolicyEffectivedate_Todate_Mandatary");
		PropPolicyEffectivedate_Todate_Mandatary.setAttribute("Value", "25/02/2017");
		PropPolicyEffectivedate_Todate_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropPolicyEffectivedate_Tohour_Mandatary = new org.jdom2.Element(
				"PropPolicyEffectivedate_Tohour_Mandatary");
		PropPolicyEffectivedate_Tohour_Mandatary.setAttribute("Value", "23:59");
		PropPolicyEffectivedate_Tohour_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropPremiumCalculation_BasicPremium = new org.jdom2.Element(
				"PropPremiumCalculation_BasicPremium");
		PropPremiumCalculation_BasicPremium.setAttribute("Value", "21076.66");
		PropPremiumCalculation_BasicPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_Cess = new org.jdom2.Element("PropPremiumCalculation_Cess");
		PropPremiumCalculation_Cess.setAttribute("Value", "-0.22");
		PropPremiumCalculation_Cess.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_CustomCoverLDPremium = new org.jdom2.Element(
				"PropPremiumCalculation_CustomCoverLDPremium");
		PropPremiumCalculation_CustomCoverLDPremium.setAttribute("Value", "0");
		PropPremiumCalculation_CustomCoverLDPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_ERFAmount = new org.jdom2.Element("PropPremiumCalculation_ERFAmount");
		PropPremiumCalculation_ERFAmount.setAttribute("Value", "0");
		PropPremiumCalculation_ERFAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_EndorsementERFAmount = new org.jdom2.Element(
				"PropPremiumCalculation_EndorsementERFAmount");
		PropPremiumCalculation_EndorsementERFAmount.setAttribute("Value", "0");
		PropPremiumCalculation_EndorsementERFAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_EndorsementPremium = new org.jdom2.Element(
				"PropPremiumCalculation_EndorsementPremium");
		PropPremiumCalculation_EndorsementPremium.setAttribute("Value", "33532.07");
		PropPremiumCalculation_EndorsementPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_EndorsementServiceTax = new org.jdom2.Element(
				"PropPremiumCalculation_EndorsementServiceTax");
		PropPremiumCalculation_EndorsementServiceTax.setAttribute("Value", "4862.15");
		PropPremiumCalculation_EndorsementServiceTax.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_EndorsementStampDuty = new org.jdom2.Element(
				"PropPremiumCalculation_EndorsementStampDuty");
		PropPremiumCalculation_EndorsementStampDuty.setAttribute("Value", "0.5");
		PropPremiumCalculation_EndorsementStampDuty.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_EndorsementTerrorismPremium = new org.jdom2.Element(
				"PropPremiumCalculation_EndorsementTerrorismPremium");
		PropPremiumCalculation_EndorsementTerrorismPremium.setAttribute("Value", "0");
		PropPremiumCalculation_EndorsementTerrorismPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_GrossCoverPremium = new org.jdom2.Element(
				"PropPremiumCalculation_GrossCoverPremium");
		PropPremiumCalculation_GrossCoverPremium.setAttribute("Value", "18988.66");
		PropPremiumCalculation_GrossCoverPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_NetODPremium = new org.jdom2.Element(
				"PropPremiumCalculation_NetODPremium");
		PropPremiumCalculation_NetODPremium.setAttribute("Value", "31009.07");
		PropPremiumCalculation_NetODPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_NetPremium = new org.jdom2.Element(
				"PropPremiumCalculation_NetPremium");
		PropPremiumCalculation_NetPremium.setAttribute("Value", "33532.07");
		PropPremiumCalculation_NetPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_NetTPPremium = new org.jdom2.Element(
				"PropPremiumCalculation_NetTPPremium");
		PropPremiumCalculation_NetTPPremium.setAttribute("Value", "2523");
		PropPremiumCalculation_NetTPPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_SP_PR_LTFactor = new org.jdom2.Element(
				"PropPremiumCalculation_SP_PR_LTFactor");
		PropPremiumCalculation_SP_PR_LTFactor.setAttribute("Value", "1");
		PropPremiumCalculation_SP_PR_LTFactor.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_ServiceTax = new org.jdom2.Element(
				"PropPremiumCalculation_ServiceTax");
		PropPremiumCalculation_ServiceTax.setAttribute("Value", "4862.15");
		PropPremiumCalculation_ServiceTax.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_StampDuty = new org.jdom2.Element("PropPremiumCalculation_StampDuty");
		PropPremiumCalculation_StampDuty.setAttribute("Value", "0.5");
		PropPremiumCalculation_StampDuty.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_StampDutyApplicability = new org.jdom2.Element(
				"PropPremiumCalculation_StampDutyApplicability");
		PropPremiumCalculation_StampDutyApplicability.setAttribute("Value", "False");
		PropPremiumCalculation_StampDutyApplicability.setAttribute("Type", "Boolean");

		org.jdom2.Element PropPremiumCalculation_TerrorismPremium = new org.jdom2.Element(
				"PropPremiumCalculation_TerrorismPremium");
		PropPremiumCalculation_TerrorismPremium.setAttribute("Value", "0");
		PropPremiumCalculation_TerrorismPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_TotalPremium = new org.jdom2.Element(
				"PropPremiumCalculation_TotalPremium");
		PropPremiumCalculation_TotalPremium.setAttribute("Value", "38394");
		PropPremiumCalculation_TotalPremium.setAttribute("Type", "Double");

		org.jdom2.Element PropPremiumCalculation_TransferFeeAmount = new org.jdom2.Element(
				"PropPremiumCalculation_TransferFeeAmount");
		PropPremiumCalculation_TransferFeeAmount.setAttribute("Value", "0");
		PropPremiumCalculation_TransferFeeAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropPreviousPolicyDetails_AddRow = new org.jdom2.Element("PropPreviousPolicyDetails_AddRow");
		PropPreviousPolicyDetails_AddRow.setAttribute("Value", "False");
		PropPreviousPolicyDetails_AddRow.setAttribute("Type", "Boolean");

		org.jdom2.Element PropPreviousPolicyDetails_CalculateClaimRatio = new org.jdom2.Element(
				"PropPreviousPolicyDetails_CalculateClaimRatio");
		PropPreviousPolicyDetails_CalculateClaimRatio.setAttribute("Value", "True");
		PropPreviousPolicyDetails_CalculateClaimRatio.setAttribute("Type", "Boolean");

		org.jdom2.Element PropPreviousPolicyDetails_ClaimRatio = new org.jdom2.Element(
				"PropPreviousPolicyDetails_ClaimRatio");
		PropPreviousPolicyDetails_ClaimRatio.setAttribute("Value", "0");
		PropPreviousPolicyDetails_ClaimRatio.setAttribute("Type", "Double");

		org.jdom2.Element PropPreviousPolicyDetails_Combo1 = new org.jdom2.Element("PropPreviousPolicyDetails_Combo1");
		PropPreviousPolicyDetails_Combo1.setAttribute("Value", "");
		PropPreviousPolicyDetails_Combo1.setAttribute("Type", "String");

		org.jdom2.Element PropPreviousPolicyDetails_Excess = new org.jdom2.Element("PropPreviousPolicyDetails_Excess");
		PropPreviousPolicyDetails_Excess.setAttribute("Value", "");
		PropPreviousPolicyDetails_Excess.setAttribute("Type", "String");

		org.jdom2.Element PropPreviousPolicyDetails_FirstPolicyInceptionDate = new org.jdom2.Element(
				"PropPreviousPolicyDetails_FirstPolicyInceptionDate");
		PropPreviousPolicyDetails_FirstPolicyInceptionDate.setAttribute("Value", "0");
		PropPreviousPolicyDetails_FirstPolicyInceptionDate.setAttribute("Type", "Double");

		org.jdom2.Element PropPreviousPolicyDetails_InceptionYear = new org.jdom2.Element(
				"PropPreviousPolicyDetails_InceptionYear");
		PropPreviousPolicyDetails_InceptionYear.setAttribute("Value", "0");
		PropPreviousPolicyDetails_InceptionYear.setAttribute("Type", "Double");

		org.jdom2.Element PropPreviousPolicyDetails_NCBPercentage = new org.jdom2.Element(
				"PropPreviousPolicyDetails_NCBPercentage");
		PropPreviousPolicyDetails_NCBPercentage.setAttribute("Value", "0");
		PropPreviousPolicyDetails_NCBPercentage.setAttribute("Type", "Double");

		org.jdom2.Element PropPreviousPolicyDetails_OldTariffSlab = new org.jdom2.Element(
				"PropPreviousPolicyDetails_OldTariffSlab");
		PropPreviousPolicyDetails_OldTariffSlab.setAttribute("Value", "False");
		PropPreviousPolicyDetails_OldTariffSlab.setAttribute("Type", "Boolean");

		org.jdom2.Element PropPreviousPolicyDetails_PrevInsPincode = new org.jdom2.Element(
				"PropPreviousPolicyDetails_PrevInsPincode");
		PropPreviousPolicyDetails_PrevInsPincode.setAttribute("Value", "");
		PropPreviousPolicyDetails_PrevInsPincode.setAttribute("Type", "String");

		org.jdom2.Element PropPreviousPolicyDetails_PreviousPolicyType = new org.jdom2.Element(
				"PropPreviousPolicyDetails_PreviousPolicyType");
		PropPreviousPolicyDetails_PreviousPolicyType.setAttribute("Value", "");
		PropPreviousPolicyDetails_PreviousPolicyType.setAttribute("Type", "String");

		org.jdom2.Element PropPreviousPolicyDetails_RenewalCounter = new org.jdom2.Element(
				"PropPreviousPolicyDetails_RenewalCounter");
		PropPreviousPolicyDetails_RenewalCounter.setAttribute("Value", "0");
		PropPreviousPolicyDetails_RenewalCounter.setAttribute("Type", "Double");

		org.jdom2.Element PropPreviousPolicyDetails_TPAName = new org.jdom2.Element(
				"PropPreviousPolicyDetails_TPAName");
		PropPreviousPolicyDetails_TPAName.setAttribute("Value", "");
		PropPreviousPolicyDetails_TPAName.setAttribute("Type", "String");

		org.jdom2.Element PropPreviousPolicyDetails_Validate = new org.jdom2.Element(
				"PropPreviousPolicyDetails_Validate");
		PropPreviousPolicyDetails_Validate.setAttribute("Value", "True");
		PropPreviousPolicyDetails_Validate.setAttribute("Type", "Boolean");

		org.jdom2.Element PropProductDetails_Component17 = new org.jdom2.Element("PropProductDetails_Component17");
		PropProductDetails_Component17.setAttribute("Value", "");
		PropProductDetails_Component17.setAttribute("Type", "String");

		org.jdom2.Element PropProductDetails_DepartmentCode = new org.jdom2.Element(
				"PropProductDetails_DepartmentCode");
		PropProductDetails_DepartmentCode.setAttribute("Value", "31");
		PropProductDetails_DepartmentCode.setAttribute("Type", "Double");

		org.jdom2.Element PropProductDetails_ProductCode = new org.jdom2.Element("PropProductDetails_ProductCode");
		PropProductDetails_ProductCode.setAttribute("Value", "3121");
		PropProductDetails_ProductCode.setAttribute("Type", "Double");

		org.jdom2.Element PropProductDetails_ProductName = new org.jdom2.Element("PropProductDetails_ProductName");
		PropProductDetails_ProductName.setAttribute("Value", "");
		PropProductDetails_ProductName.setAttribute("Type", "String");

		org.jdom2.Element PropProductName = new org.jdom2.Element("PropProductName");
		PropProductName.setAttribute("Value", "Comprehensive Policy");
		PropProductName.setAttribute("Type", "String");

		org.jdom2.Element PropReferenceNoDate_ReferenceDate_Mandatary = new org.jdom2.Element(
				"PropReferenceNoDate_ReferenceDate_Mandatary");
		PropReferenceNoDate_ReferenceDate_Mandatary.setAttribute("Value", "26/02/2016");
		PropReferenceNoDate_ReferenceDate_Mandatary.setAttribute("Type", "String");

		org.jdom2.Element PropReferenceNoDate_ReferenceNo_Mandatary = new org.jdom2.Element(
				"PropReferenceNoDate_ReferenceNo_Mandatary");
		PropReferenceNoDate_ReferenceNo_Mandatary.setAttribute("Value", "201602260000166");
		PropReferenceNoDate_ReferenceNo_Mandatary.setAttribute("Type", "Double");

		org.jdom2.Element PropSPDetails_SPCode = new org.jdom2.Element("PropSPDetails_SPCode");
		PropSPDetails_SPCode.setAttribute("Value", "0");
		PropSPDetails_SPCode.setAttribute("Type", "Double");

		org.jdom2.Element PropSPDetails_SPName = new org.jdom2.Element("PropSPDetails_SPName");
		PropSPDetails_SPName.setAttribute("Value", "");
		PropSPDetails_SPName.setAttribute("Type", "String");

		org.jdom2.Element PropSerIntermediaryDetails_SerIntermediaryCode = new org.jdom2.Element(
				"PropSerIntermediaryDetails_SerIntermediaryCode");
		PropSerIntermediaryDetails_SerIntermediaryCode.setAttribute("Value", "");
		PropSerIntermediaryDetails_SerIntermediaryCode.setAttribute("Type", "String");

		org.jdom2.Element PropSerIntermediaryDetails_SerIntermediaryName = new org.jdom2.Element(
				"PropSerIntermediaryDetails_SerIntermediaryName");
		PropSerIntermediaryDetails_SerIntermediaryName.setAttribute("Value", "");
		PropSerIntermediaryDetails_SerIntermediaryName.setAttribute("Type", "String");

		org.jdom2.Element PropSerIntermediaryDetails_SerIntermediaryType = new org.jdom2.Element(
				"PropSerIntermediaryDetails_SerIntermediaryType");
		PropSerIntermediaryDetails_SerIntermediaryType.setAttribute("Value", "");
		PropSerIntermediaryDetails_SerIntermediaryType.setAttribute("Type", "String");

		org.jdom2.Element PropServiceTaxExemptionCategory_Component43 = new org.jdom2.Element(
				"PropServiceTaxExemptionCategory_Component43");
		PropServiceTaxExemptionCategory_Component43.setAttribute("Value", "");
		PropServiceTaxExemptionCategory_Component43.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_PrimaryVerticalCode = new org.jdom2.Element(
				"PropVerticalDtls_PrimaryVerticalCode");
		PropVerticalDtls_PrimaryVerticalCode.setAttribute("Value", "");
		PropVerticalDtls_PrimaryVerticalCode.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_PrimaryVerticalName = new org.jdom2.Element(
				"PropVerticalDtls_PrimaryVerticalName");
		PropVerticalDtls_PrimaryVerticalName.setAttribute("Value", "");
		PropVerticalDtls_PrimaryVerticalName.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_SecondaryVerticalCode = new org.jdom2.Element(
				"PropVerticalDtls_SecondaryVerticalCode");
		PropVerticalDtls_SecondaryVerticalCode.setAttribute("Value", "");
		PropVerticalDtls_SecondaryVerticalCode.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_SecondaryVerticalName = new org.jdom2.Element(
				"PropVerticalDtls_SecondaryVerticalName");
		PropVerticalDtls_SecondaryVerticalName.setAttribute("Value", "");
		PropVerticalDtls_SecondaryVerticalName.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_ServicingPrimaryVerticalCode = new org.jdom2.Element(
				"PropVerticalDtls_ServicingPrimaryVerticalCode");
		PropVerticalDtls_ServicingPrimaryVerticalCode.setAttribute("Value", "");
		PropVerticalDtls_ServicingPrimaryVerticalCode.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_ServicingPrimaryVerticalName = new org.jdom2.Element(
				"PropVerticalDtls_ServicingPrimaryVerticalName");
		PropVerticalDtls_ServicingPrimaryVerticalName.setAttribute("Value", "");
		PropVerticalDtls_ServicingPrimaryVerticalName.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_ServicingSecondaryVerticalCode = new org.jdom2.Element(
				"PropVerticalDtls_ServicingSecondaryVerticalCode");
		PropVerticalDtls_ServicingSecondaryVerticalCode.setAttribute("Value", "");
		PropVerticalDtls_ServicingSecondaryVerticalCode.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_ServicingSecondaryVerticalName = new org.jdom2.Element(
				"PropVerticalDtls_ServicingSecondaryVerticalName");
		PropVerticalDtls_ServicingSecondaryVerticalName.setAttribute("Value", "");
		PropVerticalDtls_ServicingSecondaryVerticalName.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_ServicingTertiaryVerticalCode = new org.jdom2.Element(
				"PropVerticalDtls_ServicingTertiaryVerticalCode");
		PropVerticalDtls_ServicingTertiaryVerticalCode.setAttribute("Value", "");
		PropVerticalDtls_ServicingTertiaryVerticalCode.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_ServicingTertiaryVerticalName = new org.jdom2.Element(
				"PropVerticalDtls_ServicingTertiaryVerticalName");
		PropVerticalDtls_ServicingTertiaryVerticalName.setAttribute("Value", "");
		PropVerticalDtls_ServicingTertiaryVerticalName.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_TertiaryVerticalCode = new org.jdom2.Element(
				"PropVerticalDtls_TertiaryVerticalCode");
		PropVerticalDtls_TertiaryVerticalCode.setAttribute("Value", "");
		PropVerticalDtls_TertiaryVerticalCode.setAttribute("Type", "String");

		org.jdom2.Element PropVerticalDtls_TertiaryVerticalName = new org.jdom2.Element(
				"PropVerticalDtls_TertiaryVerticalName");
		PropVerticalDtls_TertiaryVerticalName.setAttribute("Value", "");
		PropVerticalDtls_TertiaryVerticalName.setAttribute("Type", "String");

		org.jdom2.Element PropWarranty_DepartmentCode = new org.jdom2.Element("PropWarranty_DepartmentCode");
		PropWarranty_DepartmentCode.setAttribute("Value", "0");
		PropWarranty_DepartmentCode.setAttribute("Type", "Double");

		org.jdom2.Element PropWarranty_ProductCode = new org.jdom2.Element("PropWarranty_ProductCode");
		PropWarranty_ProductCode.setAttribute("Value", "0");
		PropWarranty_ProductCode.setAttribute("Type", "Double");

		org.jdom2.Element PropWarranty_SectionCode = new org.jdom2.Element("PropWarranty_SectionCode");
		PropWarranty_SectionCode.setAttribute("Value", "");
		PropWarranty_SectionCode.setAttribute("Type", "String");

		ProposalDetails.addContent(GeneralPurposeInformation);
		GeneralPurposeInformation.addContent(ErrorTrackingNeeded);
		GeneralPurposeInformation.addContent(ProductType);
		GeneralPurposeInformation.addContent(PropBranchDetails_IMDBranchCode);
		GeneralPurposeInformation.addContent(PropBranchDetails_IMDBranchName);
		GeneralPurposeInformation.addContent(PropCalculation_CalculateRate);
		GeneralPurposeInformation.addContent(PropCalculation_Validate);
		GeneralPurposeInformation.addContent(PropCalculation_ValidateData);
		GeneralPurposeInformation.addContent(PropClauseDetails_Component51);
		GeneralPurposeInformation.addContent(PropClauseDetails_DepartmentCode_Mandatory);
		GeneralPurposeInformation.addContent(PropClauseDetails_ProductCode_Mandatory);
		GeneralPurposeInformation.addContent(PropClauseDetails_SectionCode);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_AddRow);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_CoinsuranceType);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_Commissiontobepaidbytheleader);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_PolicyNooftheleader);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_ReferenceNumber);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_SERVTAXCOMMRCVRLEADER);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_Servicetaxtobepaidbytheleader);
		GeneralPurposeInformation.addContent(PropCoinsuranceDetails_Validate);
		GeneralPurposeInformation.addContent(PropConditionDetails_ConditionDescription);
		GeneralPurposeInformation.addContent(PropConditionDetails_DepartmentCode);
		GeneralPurposeInformation.addContent(PropConditionDetails_ProductCode);
		GeneralPurposeInformation.addContent(PropConditionDetails_SectionCode);
		GeneralPurposeInformation.addContent(PropConditionDetails_SpecialConditionDescription);
		GeneralPurposeInformation.addContent(PropCoverNoteDetsils_CoverNoteBookNo);
		GeneralPurposeInformation.addContent(PropCoverNoteDetsils_CoverNoteLeafNo);
		GeneralPurposeInformation.addContent(PropCoverNoteDetsils_IssuedOnDt);
		GeneralPurposeInformation.addContent(PropCoverNoteDetsils_IssuedonTime);
		GeneralPurposeInformation.addContent(PropCoverNoteDetsils_LeafNo);
		GeneralPurposeInformation.addContent(PropCoverNoteDetsils_ReceivedOn);
		GeneralPurposeInformation.addContent(PropCoverNoteDetsils_Validate);
		GeneralPurposeInformation.addContent(PropCustomerDtls_CustomerID_Mandatary);
		GeneralPurposeInformation.addContent(PropCustomerDtls_CustomerName);
		GeneralPurposeInformation.addContent(PropCustomerDtls_CustomerType);
		GeneralPurposeInformation.addContent(PropCustomerDtls_PayeeCustomerID);
		GeneralPurposeInformation.addContent(PropCustomerDtls_PayeeCustomerName);
		GeneralPurposeInformation.addContent(PropCustomerReferenceInfo_OldPolicyNumber);
		GeneralPurposeInformation.addContent(PropCustomerReferenceInfo_PolicyConversionDate);
		GeneralPurposeInformation.addContent(PropDistributionChannel_BranchDetails);
		GeneralPurposeInformation.addContent(PropDistributionChannel_BusineeChannelBrunch);
		GeneralPurposeInformation.addContent(PropDistributionChannel_BusineeChanneltype);
		GeneralPurposeInformation.addContent(PropDistributionChannel_BusinessServicingChannelType);
		GeneralPurposeInformation.addContent(PropDistributionChannel_BusinessSharingPercent);
		GeneralPurposeInformation.addContent(PropDistributionChannel_BusinessSource_Mandatary);
		GeneralPurposeInformation.addContent(PropDistributionChannel_BusinessSourcetype);
		GeneralPurposeInformation.addContent(PropDistributionChannel_Commission);
		GeneralPurposeInformation.addContent(PropDistributionChannel_EndorsementDtls);
		GeneralPurposeInformation.addContent(PropDistributionChannel_FieldUserDetails);
		GeneralPurposeInformation.addContent(PropDistributionChannel_FolderFilePath);
		GeneralPurposeInformation.addContent(PropDistributionChannel_InterMediaryDetails);
		GeneralPurposeInformation.addContent(PropDistributionChannel_MODetails);
		GeneralPurposeInformation.addContent(PropDistributionChannel_OperationMode);
		GeneralPurposeInformation.addContent(PropDistributionChannel_SerIntermediaryDetails);
		GeneralPurposeInformation.addContent(PropDistributionChannel_ServiceProvider);
		GeneralPurposeInformation.addContent(PropDistributionChannel_SpecialDiscount);
		GeneralPurposeInformation.addContent(PropDistributionChannel_TERRORISMCOMMISSION);
		GeneralPurposeInformation.addContent(PropDistributionChannel_Validate);
		GeneralPurposeInformation.addContent(PropDistributionChannel_VerticalDtls);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_CancleDueToClaim);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_CancellationOption);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_CancellationReason);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_CertificateofVintageCar);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_CoverType);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_DataEntryError);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_DateofEndfrominsured);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_Description);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_DocForCommPrivate);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_DocOFRequisition);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_DocProofothersspecify);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_DocproofofforNCB);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_DoubleINSOption);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_EndorsementTypeCode);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_INSCoName);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_INSCoOffCodeAdd);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_ISSellerIndured);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_LaidUpFromDate);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_LaidUpToDate);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_LaidupVehicleCheck);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_MajorRepairRenovation);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_NOCFinancier);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_PolicyFrom);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_PolicyIssueDate);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_PolicyNo);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_PolicyTo);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_Reasonfornametransfer);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_SICCode);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_SICName);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_TypeOfTransfer);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_TypeOfEndorsement);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_UFormFromDate);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_UFormToDate);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_UsageinInsuredPremises);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_ValuationReport);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_VehicleLaidUptype);
		GeneralPurposeInformation.addContent(PropEndorsementDtls_orgcertificatesurrendered);
		GeneralPurposeInformation.addContent(PropEndorsementEffectiveDate_EffectiveDate);
		GeneralPurposeInformation.addContent(PropEndorsementEffectiveDate_EffectiveTime);
		GeneralPurposeInformation.addContent(PropExclusionDetails_DepartmentCode);
		GeneralPurposeInformation.addContent(PropExclusionDetails_ProductCode);
		GeneralPurposeInformation.addContent(PropExclusionDetails_SectionCode);
		GeneralPurposeInformation.addContent(PropFees_PPCFee);
		GeneralPurposeInformation.addContent(PropFieldUserDetails_FiledUserCode);
		GeneralPurposeInformation.addContent(PropFieldUserDetails_FiledUserLocation);
		GeneralPurposeInformation.addContent(PropFieldUserDetails_FieldUserName);
		GeneralPurposeInformation.addContent(PropFieldUserDetails_FieldUserVertical);
		GeneralPurposeInformation.addContent(PropFieldUserDetails_FieldUserUserID);
		GeneralPurposeInformation.addContent(PropFinancierDetails_AddRow);
		GeneralPurposeInformation.addContent(PropFinancierDetails_FinancierCodeGenerator);
		GeneralPurposeInformation.addContent(PropFinancierDetails_Validate);
		GeneralPurposeInformation.addContent(PropGeneralNodes_ApplicationDate);
		GeneralPurposeInformation.addContent(PropGeneralNodes_ApplicationNo);
		GeneralPurposeInformation.addContent(PropGeneralNodes_ParentCompanyName);
		GeneralPurposeInformation.addContent(PropGeneralNodes_PartnerReferanceNO);
		GeneralPurposeInformation.addContent(PropGeneralNodes_RIInwardSharePercentage);
		GeneralPurposeInformation.addContent(PropGeneralProposalGroup_CoverNoteDetails);
		GeneralPurposeInformation.addContent(PropGeneralProposalGroup_DistributionChannel);
		GeneralPurposeInformation.addContent(PropGeneralProposalGroup_Fees);
		GeneralPurposeInformation.addContent(PropGeneralProposalGroup_GeneralProposalInformation);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ActiveFlag);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ApplicationNumber);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_BankID);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_BlnEndtNoBlock);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_BranchInwardDate);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_BranchInwardNumber);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_BranchOfficeCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_BussinessSourceInfo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_BussinessType_Mandatary);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_CertificateNumber);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ChannelNumber);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_CoverNotePlace);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_CovernoteGenType);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_CustomerDtls);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_CustomerReferenceInfo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_DayDiff);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_DeadId);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_DepartmentCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_DepartmentName);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_DisplayOfficeCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_DisplayStatus);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EmployeeName);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementDescription);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementEffectiveDate);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementNo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementRequestType);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementRequestTypeCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementSi);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementType);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_EndorsementWording);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_FrontingCompany);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_IfOthersProvideReason);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_IndustryCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_InternalUWComments);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_InwardNo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_IsNCBApplicable);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_IsSPCalcByDayDiffReqd);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_LeadGenerator);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ManualCovernoteNo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_MasterPolicy);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_MasterPolicyVersion);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_MethodOfCalculation);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_NCBpercentage);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_NextB);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_NoPrevInsuranceFlag);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_NonLiablePeriod);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_NumCovernoteNo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_OfficeCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_OfficeName);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_OldCovernoteNo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_OpsSalesPartnerUserComments);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_OptionForCalculation);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_PolicyCurrency);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_PolicyEffectivedate);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_PolicyNo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_PolicyNumberChar);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_PolicySchedule_Mandatary);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_PolicyTerm);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_PrintingType);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ProductCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ProductName);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ProposalDate_Mandatary);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ProposalFormNumber);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ProvisionalBooking);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ProvisionalBookingReason);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_QuickId);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_QuoteNumber);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ReInsuranceInward);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ReSharePer);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ReferenceNoDate);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ReferralCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_RelationshipType);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_Remarks);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_RetainCancellationPremium);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_SalesTax);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_Sector_Mandatary);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_SerDealId);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ServiceTaxExemption);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ServiceTaxExemptionCategory_Mandatary);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ServiceTaxExemptionReason);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_SourceOfTransaction);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_TotalSi);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_TypeOfBusiness);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_TypeOfCalculation);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_TypeOfIndustry);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_TypeOfPolicy);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_TypeofCancellation);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_UWLocation);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_UWLocationCode);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ValidationFlag);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_ValuationReport);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_VehicleLaidUpFrom);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_VehicleLaidUpTo);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_covernoteissuedate);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_covernoteissuetime);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_disprntoffcd);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_iscovernoteused);
		GeneralPurposeInformation.addContent(PropGeneralProposalInformation_numTotalFee);
		GeneralPurposeInformation.addContent(PropGeneralProposal_GeneralProposalGroup);
		GeneralPurposeInformation.addContent(PropGeneralProposal_IsCustomWorkflowCondition);
		GeneralPurposeInformation.addContent(PropHigherExcessDiscount_HE_Disc_RateType_In);
		GeneralPurposeInformation.addContent(PropIntermediaryDetails_BrokeragePercentage);
		GeneralPurposeInformation.addContent(PropIntermediaryDetails_IntermediaryCityCode);
		GeneralPurposeInformation.addContent(PropIntermediaryDetails_IntermediaryCityName);
		GeneralPurposeInformation.addContent(PropIntermediaryDetails_IntermediaryCode);
		GeneralPurposeInformation.addContent(PropIntermediaryDetails_IntermediaryName);
		GeneralPurposeInformation.addContent(PropIntermediaryDetails_IntermediaryType);
		GeneralPurposeInformation.addContent(PropMODetails_PrimaryMOCode);
		GeneralPurposeInformation.addContent(PropMODetails_PrimaryMOName);
		GeneralPurposeInformation.addContent(PropMODetails_SecondaryMOCode);
		GeneralPurposeInformation.addContent(PropMODetails_SecondaryMOLocation);
		GeneralPurposeInformation.addContent(PropMODetails_SecondaryMOName);
		GeneralPurposeInformation.addContent(PropMODetails_TertiaryMOCode);
		GeneralPurposeInformation.addContent(PropMODetails_TertiaryMOName);
		GeneralPurposeInformation.addContent(PropMotorOtherNodes_AfterFetch);
		GeneralPurposeInformation.addContent(PropMotorOtherNodes_CancelationDetail);
		GeneralPurposeInformation.addContent(PropMotorOtherNodes_EndorsementDtls);
		GeneralPurposeInformation.addContent(PropMotorOtherNodes_GeneralNodes);
		GeneralPurposeInformation.addContent(PropMotorOtherNodes_validate_aftercalc);
		GeneralPurposeInformation.addContent(PropMotorOtherNodes_validate_beforecalc);
		GeneralPurposeInformation.addContent(PropNonLiablePeriod_NonLiableEndTime);
		GeneralPurposeInformation.addContent(PropNonLiablePeriod_NonLiableFromDate);
		GeneralPurposeInformation.addContent(PropNonLiablePeriod_NonLiableStartTime);
		GeneralPurposeInformation.addContent(PropNonLiablePeriod_NonLiableToDate);
		GeneralPurposeInformation.addContent(PropParameters_AddClause);
		GeneralPurposeInformation.addContent(PropParameters_AddCondition);
		GeneralPurposeInformation.addContent(PropParameters_AddExclusion);
		GeneralPurposeInformation.addContent(PropParameters_AddWarranty);
		GeneralPurposeInformation.addContent(PropParameters_AfterFetch);
		GeneralPurposeInformation.addContent(PropParameters_ClauseApplicable);
		GeneralPurposeInformation.addContent(PropParameters_CoInsuranceApplicability);
		GeneralPurposeInformation.addContent(PropParameters_CommissionApplicable);
		GeneralPurposeInformation.addContent(PropParameters_ConditionApplicable);
		GeneralPurposeInformation.addContent(PropParameters_CoverNoteApplicability);
		GeneralPurposeInformation.addContent(PropParameters_EQHigherExcessDiscount);
		GeneralPurposeInformation.addContent(PropParameters_ExclusionApplicable);
		GeneralPurposeInformation.addContent(PropParameters_FamilyPackageDiscount);
		GeneralPurposeInformation.addContent(PropParameters_FinancierApplicability);
		GeneralPurposeInformation.addContent(PropParameters_FloaterRiskLoading);
		GeneralPurposeInformation.addContent(PropParameters_GroupDiscount);
		GeneralPurposeInformation.addContent(PropParameters_HighClaimLoading);
		GeneralPurposeInformation.addContent(PropParameters_HigherExcessDiscount);
		GeneralPurposeInformation.addContent(PropParameters_Installment);
		GeneralPurposeInformation.addContent(PropParameters_LongTermPolicy);
		GeneralPurposeInformation.addContent(PropParameters_LowClaimDiscount);
		GeneralPurposeInformation.addContent(PropParameters_MinimumPremium);
		GeneralPurposeInformation.addContent(PropParameters_NoClaimDiscount);
		GeneralPurposeInformation.addContent(PropParameters_OverageLoading);
		GeneralPurposeInformation.addContent(PropParameters_PasPolicyApplicable);
		GeneralPurposeInformation.addContent(PropParameters_PastPolicyApplicable);
		GeneralPurposeInformation.addContent(PropParameters_PolicyCancellation);
		GeneralPurposeInformation.addContent(PropParameters_PopulateTransactionBegin);
		GeneralPurposeInformation.addContent(PropParameters_PreviousPolicyApplicable);
		GeneralPurposeInformation.addContent(PropParameters_RenewalAllowed);
		GeneralPurposeInformation.addContent(PropParameters_SectionDiscount);
		GeneralPurposeInformation.addContent(PropParameters_ServiceTaxExempted);
		GeneralPurposeInformation.addContent(PropParameters_ShortPeriodLongPeriod);
		GeneralPurposeInformation.addContent(PropParameters_StaffDiscount);
		GeneralPurposeInformation.addContent(PropParameters_StampDutyChargeable);
		GeneralPurposeInformation.addContent(PropParameters_TariffProduct);
		GeneralPurposeInformation.addContent(PropParameters_TransferFees);
		GeneralPurposeInformation.addContent(PropParameters_VDFDiscountAllowed);
		GeneralPurposeInformation.addContent(PropParameters_ValidateClause);
		GeneralPurposeInformation.addContent(PropParameters_ValidateCondition);
		GeneralPurposeInformation.addContent(PropParameters_ValidateEndorsementData);
		GeneralPurposeInformation.addContent(PropParameters_ValidateExclusion);
		GeneralPurposeInformation.addContent(PropParameters_ValidateWarranty);
		GeneralPurposeInformation.addContent(PropParameters_WarrentyApplicable);
		GeneralPurposeInformation.addContent(PropPolicyCoverDetails_Applicable);
		GeneralPurposeInformation.addContent(PropPolicyCoverDetails_CoverGroups);
		GeneralPurposeInformation.addContent(PropPolicyCoverDetails_DifferentialSI);
		GeneralPurposeInformation.addContent(PropPolicyCoverDetails_EndorsementAmount);
		GeneralPurposeInformation.addContent(PropPolicyCoverDetails_Premium);
		GeneralPurposeInformation.addContent(PropPolicyCoverDetails_Rate);
		GeneralPurposeInformation.addContent(PropPolicyCoverDetails_SumInsured);
		GeneralPurposeInformation.addContent(PropPolicyEffectivedate_Fromdate_Mandatary);
		GeneralPurposeInformation.addContent(PropPolicyEffectivedate_Fromhour_Mandatary);
		GeneralPurposeInformation.addContent(PropPolicyEffectivedate_Todate_Mandatary);
		GeneralPurposeInformation.addContent(PropPolicyEffectivedate_Tohour_Mandatary);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_BasicPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_CustomCoverLDPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_ERFAmount);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_EndorsementERFAmount);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_EndorsementPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_EndorsementServiceTax);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_EndorsementStampDuty);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_EndorsementTerrorismPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_GrossCoverPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_NetODPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_NetPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_NetTPPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_SP_PR_LTFactor);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_ServiceTax);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_StampDuty);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_StampDutyApplicability);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_TerrorismPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_TotalPremium);
		GeneralPurposeInformation.addContent(PropPremiumCalculation_TransferFeeAmount);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_AddRow);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_CalculateClaimRatio);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_ClaimRatio);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_Combo1);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_Excess);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_FirstPolicyInceptionDate);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_InceptionYear);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_NCBPercentage);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_OldTariffSlab);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_PrevInsPincode);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_PreviousPolicyType);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_TPAName);
		GeneralPurposeInformation.addContent(PropPreviousPolicyDetails_Validate);
		GeneralPurposeInformation.addContent(PropProductDetails_Component17);
		GeneralPurposeInformation.addContent(PropProductDetails_DepartmentCode);
		GeneralPurposeInformation.addContent(PropProductDetails_ProductCode);
		GeneralPurposeInformation.addContent(PropProductDetails_ProductName);
		GeneralPurposeInformation.addContent(PropProductName);
		GeneralPurposeInformation.addContent(PropReferenceNoDate_ReferenceDate_Mandatary);
		GeneralPurposeInformation.addContent(PropReferenceNoDate_ReferenceNo_Mandatary);
		GeneralPurposeInformation.addContent(PropSPDetails_SPCode);
		GeneralPurposeInformation.addContent(PropSPDetails_SPName);
		GeneralPurposeInformation.addContent(PropSerIntermediaryDetails_SerIntermediaryCode);
		GeneralPurposeInformation.addContent(PropSerIntermediaryDetails_SerIntermediaryName);
		GeneralPurposeInformation.addContent(PropSerIntermediaryDetails_SerIntermediaryType);
		GeneralPurposeInformation.addContent(PropServiceTaxExemptionCategory_Component43);
		GeneralPurposeInformation.addContent(PropVerticalDtls_PrimaryVerticalCode);
		GeneralPurposeInformation.addContent(PropVerticalDtls_PrimaryVerticalName);
		GeneralPurposeInformation.addContent(PropVerticalDtls_SecondaryVerticalCode);
		GeneralPurposeInformation.addContent(PropVerticalDtls_SecondaryVerticalName);
		GeneralPurposeInformation.addContent(PropVerticalDtls_ServicingPrimaryVerticalCode);
		GeneralPurposeInformation.addContent(PropVerticalDtls_ServicingPrimaryVerticalName);
		GeneralPurposeInformation.addContent(PropVerticalDtls_ServicingSecondaryVerticalCode);
		GeneralPurposeInformation.addContent(PropVerticalDtls_ServicingSecondaryVerticalName);
		GeneralPurposeInformation.addContent(PropVerticalDtls_ServicingTertiaryVerticalCode);
		GeneralPurposeInformation.addContent(PropVerticalDtls_ServicingTertiaryVerticalName);
		GeneralPurposeInformation.addContent(PropVerticalDtls_TertiaryVerticalCode);
		GeneralPurposeInformation.addContent(PropVerticalDtls_TertiaryVerticalName);
		GeneralPurposeInformation.addContent(PropWarranty_DepartmentCode);
		GeneralPurposeInformation.addContent(PropWarranty_ProductCode);
		GeneralPurposeInformation.addContent(PropWarranty_SectionCode);

	}

	private void policyLoadingDiscDetails() {

		org.jdom2.Element PolicyLoadingDiscountDetails = new org.jdom2.Element("PolicyLoadingDiscountDetails");

		org.jdom2.Element PolicyLoadingDiscountDetails1 = new org.jdom2.Element("PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetails1.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_Applicable = new org.jdom2.Element("PropLoadingDiscount_Applicable");
		PropLoadingDiscount_Applicable.setAttribute("Value", "True");
		PropLoadingDiscount_Applicable.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmount = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmount.setAttribute("Value", "50");
		PropLoadingDiscount_CalculatedAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_Description = new org.jdom2.Element("PropLoadingDiscount_Description");
		PropLoadingDiscount_Description.setAttribute("Value", "Fiber Glass Fuel Tank");
		PropLoadingDiscount_Description.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSI = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSI");
		PropLoadingDiscount_DifferentialSI.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSI.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmount = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmount.setAttribute("Value", "50");
		PropLoadingDiscount_EndorsementAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmount = new org.jdom2.Element("PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmount.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmount.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeleted = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeleted.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeleted.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeleted = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeleted.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeleted.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCap = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCap.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCap.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCap = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCap.setAttribute("Value", "0");
		PropLoadingDiscount_MinCap.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_Premium = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_Premium.setAttribute("Value", "18988.66");
		PropLoadingDiscount_Premium.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_Rate = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_Rate.setAttribute("Value", "50");
		PropLoadingDiscount_Rate.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsured = new org.jdom2.Element("PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsured.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsured.setAttribute("Type", "Double");

		org.jdom2.Element PolicyLoadingDiscountDetails2 = new org.jdom2.Element("PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetails2.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_Applicable2 = new org.jdom2.Element("PropLoadingDiscount_Applicable");
		PropLoadingDiscount_Applicable2.setAttribute("Value", "True");
		PropLoadingDiscount_Applicable2.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmount2 = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmount2.setAttribute("Value", "5696.6");
		PropLoadingDiscount_CalculatedAmount2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_Description2 = new org.jdom2.Element("PropLoadingDiscount_Description");
		PropLoadingDiscount_Description2.setAttribute("Value", "Foreign Embassy");
		PropLoadingDiscount_Description2.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSI2 = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSI");
		PropLoadingDiscount_DifferentialSI2.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSI2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmount2 = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmount2.setAttribute("Value", "5696.6");
		PropLoadingDiscount_EndorsementAmount2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmount2 = new org.jdom2.Element("PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmount2.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmount2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeleted2 = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeleted2.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeleted2.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeleted2 = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeleted2.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeleted2.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCap2 = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCap2.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCap2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCap2 = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCap2.setAttribute("Value", "0");
		PropLoadingDiscount_MinCap2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_Premium2 = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_Premium2.setAttribute("Value", "18988.66");
		PropLoadingDiscount_Premium2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_Rate2 = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_Rate2.setAttribute("Value", "30");
		PropLoadingDiscount_Rate2.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsured2 = new org.jdom2.Element("PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsured2.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsured2.setAttribute("Type", "Double");

		/////////////////////////////////////////////////////////////////////////

		org.jdom2.Element PolicyLoadingDiscountDetailsDrivingTutions = new org.jdom2.Element(
				"PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsDrivingTutions.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableDrivingTutions.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableDrivingTutions.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountDrivingTutions.setAttribute("Value", "11393.2");
		PropLoadingDiscount_CalculatedAmountDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionDrivingTutions.setAttribute("Value", "Driving Tutions");
		PropLoadingDiscount_DescriptionDrivingTutions.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSI");
		PropLoadingDiscount_DifferentialSIDrivingTutions.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountDrivingTutions.setAttribute("Value", "11393.2");
		PropLoadingDiscount_EndorsementAmountDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountDrivingTutions.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedDrivingTutions.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedDrivingTutions.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedDrivingTutions.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedDrivingTutions.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapDrivingTutions.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapDrivingTutions.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumDrivingTutions.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateDrivingTutions = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateDrivingTutions.setAttribute("Value", "60");
		PropLoadingDiscount_RateDrivingTutions.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredDrivingTutions = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredDrivingTutions.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredDrivingTutions.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////

		org.jdom2.Element PolicyLoadingDiscountDetailsHandicapped = new org.jdom2.Element(
				"PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsHandicapped.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableHandicapped.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableHandicapped.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountHandicapped.setAttribute("Value", "9494.33");
		PropLoadingDiscount_CalculatedAmountHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionHandicapped.setAttribute("Value", "Handicapped Discount");
		PropLoadingDiscount_DescriptionHandicapped.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSIHandicapped.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountHandicapped.setAttribute("Value", "9494.33");
		PropLoadingDiscount_EndorsementAmountHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountHandicapped.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedHandicapped.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedHandicapped.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedHandicapped.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedHandicapped.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapHandicapped = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapHandicapped.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapHandicapped = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapHandicapped.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumHandicapped = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumHandicapped.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateHandicapped = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateHandicapped.setAttribute("Value", "50");
		PropLoadingDiscount_RateHandicapped.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredHandicapped = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredHandicapped.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredHandicapped.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////

		org.jdom2.Element PolicyLoadingDiscountDetailsVoluntary = new org.jdom2.Element("PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsVoluntary.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableVoluntary.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableVoluntary.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountVoluntary.setAttribute("Value", "750");
		PropLoadingDiscount_CalculatedAmountVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionVoluntary.setAttribute("Value", "Voluntary Excess Discount");
		PropLoadingDiscount_DescriptionVoluntary.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSIVoluntary.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountVoluntary.setAttribute("Value", "750");
		PropLoadingDiscount_EndorsementAmountVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountVoluntary.setAttribute("Value", "750");
		PropLoadingDiscount_FixedAmountVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedVoluntary.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedVoluntary.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedVoluntary.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedVoluntary.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapVoluntary = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapVoluntary.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapVoluntary = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapVoluntary.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumVoluntary = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumVoluntary.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateVoluntary = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateVoluntary.setAttribute("Value", "20");
		PropLoadingDiscount_RateVoluntary.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredVoluntary = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredVoluntary.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredVoluntary.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////

		org.jdom2.Element PolicyLoadingDiscountDetailsOwnPremises = new org.jdom2.Element(
				"PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsOwnPremises.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableOwnPremises = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableOwnPremises.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableOwnPremises.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountOwnPremises = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountOwnPremises.setAttribute("Value", "6328.92");
		PropLoadingDiscount_CalculatedAmountOwnPremises.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionOwnPremises = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionOwnPremises.setAttribute("Value", "Own Premises Discount OD");
		PropLoadingDiscount_DescriptionOwnPremises.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIOwnPremisesn = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSIOwnPremisesn.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIOwnPremisesn.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountOwnPremisesn = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountOwnPremisesn.setAttribute("Value", "6328.92");
		PropLoadingDiscount_EndorsementAmountOwnPremisesn.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountOwnPremisesn = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountOwnPremisesn.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountOwnPremisesn.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedOwnPremisesn = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedOwnPremisesn.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedOwnPremisesn.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedOwnPremisesn = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedOwnPremisesn.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedOwnPremisesn.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapOwnPremisesn = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapOwnPremisesn.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapOwnPremisesn.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapOwnPremisesn = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapOwnPremisesn.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapOwnPremisesn.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumOwnPremisesn = new org.jdom2.Element(
				"PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumOwnPremisesn.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumOwnPremisesn.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateOwnPremisesn = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateOwnPremisesn.setAttribute("Value", "33.33");
		PropLoadingDiscount_RateOwnPremisesn.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredOwnPremisesn = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredOwnPremisesn.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredOwnPremisesn.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		org.jdom2.Element PolicyLoadingDiscountDetailsVintageCar = new org.jdom2.Element(
				"PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsVintageCar.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableVintageCar.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableVintageCar.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountVintageCar.setAttribute("Value", "4747.17");
		PropLoadingDiscount_CalculatedAmountVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionVintageCar.setAttribute("Value", "Vintage Car discount");
		PropLoadingDiscount_DescriptionVintageCar.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSIVintageCar.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountVintageCar.setAttribute("Value", "4747.17");
		PropLoadingDiscount_EndorsementAmountVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountVintageCar.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedVintageCar.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedVintageCar.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedVintageCar.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedVintageCar.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapVintageCar = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapVintageCar.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapVintageCar = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapVintageCar.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumVintageCar = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumVintageCar.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateVintageCar = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateVintageCar.setAttribute("Value", "25");
		PropLoadingDiscount_RateVintageCar.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredVintageCar = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredVintageCar.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredVintageCar.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////
		org.jdom2.Element PolicyLoadingDiscountDetailsTPPD = new org.jdom2.Element("PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsTPPD.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableTPPD = new org.jdom2.Element("PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableTPPD.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableTPPD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountTPPD = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountTPPD.setAttribute("Value", "100");
		PropLoadingDiscount_CalculatedAmountTPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionTPPD = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionTPPD.setAttribute("Value", "TPPD Restriction");
		PropLoadingDiscount_DescriptionTPPD.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSITPPD = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSITPPD.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSITPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountTPPD = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountTPPD.setAttribute("Value", "100");
		PropLoadingDiscount_EndorsementAmountTPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountTPPD = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountTPPD.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountTPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedTPPD = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedTPPD.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedTPPD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedTPPD = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedTPPD.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedTPPD.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapTPPD = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapTPPD.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapTPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapTPPD = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapTPPD.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapTPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumTPPD = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumTPPD.setAttribute("Value", "2623");
		PropLoadingDiscount_PremiumTPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateTPPD = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateTPPD.setAttribute("Value", "100");
		PropLoadingDiscount_RateTPPD.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredTPPD = new org.jdom2.Element("PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredTPPD.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredTPPD.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////
		org.jdom2.Element PolicyLoadingDiscountDetailsCoverDisc = new org.jdom2.Element("PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsCoverDisc.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableCoverDisc.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableCoverDisc.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountCoverDisc.setAttribute("Value", "500");
		PropLoadingDiscount_CalculatedAmountCoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionCoverDisc.setAttribute("Value",
				"Voluntary Deductible For Depreciation Cover discount");
		PropLoadingDiscount_DescriptionCoverDisc.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSICoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSICoverDisc.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSICoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountCoverDisc.setAttribute("Value", "500");
		PropLoadingDiscount_EndorsementAmountCoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountCoverDisc.setAttribute("Value", "500");
		PropLoadingDiscount_FixedAmountCoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedCoverDisc.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedCoverDisc.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedCoverDisc.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedCoverDisc.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapCoverDisc = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapCoverDisc.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapCoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapCoverDisc = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapCoverDisc.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapCoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumCoverDisc = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumCoverDisc.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumCoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateCoverDisc = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateCoverDisc.setAttribute("Value", "10");
		PropLoadingDiscount_RateCoverDisc.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredCoverDisc = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredCoverDisc.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredCoverDisc.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		org.jdom2.Element PolicyLoadingDiscountDetailsAntiTheft = new org.jdom2.Element("PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsAntiTheft.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableAntiTheft.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableAntiTheft.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_CalculatedAmountAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		PropLoadingDiscount_CalculatedAmountAntiTheft.setAttribute("Value", "474.72");
		PropLoadingDiscount_CalculatedAmountAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionAntiTheft.setAttribute("Value", "Discount For Anti-Theft Devices");
		PropLoadingDiscount_DescriptionAntiTheft.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSIAntiTheft.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountAntiTheft.setAttribute("Value", "474.72");
		PropLoadingDiscount_EndorsementAmountAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountAntiTheft.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedAntiTheft.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedAntiTheft.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedAntiTheft.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedAntiTheft.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapAntiTheft = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapAntiTheft.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapAntiTheft = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapAntiTheft.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumAntiTheft = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumAntiTheft.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateAntiTheft = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateAntiTheft.setAttribute("Value", "2.5");
		PropLoadingDiscount_RateAntiTheft.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredAntiTheft = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredAntiTheft.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredAntiTheft.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////
		org.jdom2.Element PolicyLoadingDiscountDetailsAutomobile = new org.jdom2.Element(
				"PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsAutomobile.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableAutomobile.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableAutomobile.setAttribute("Type", "Boolean");

		org.jdom2.Element propLoadingDiscount_CalculatedAmountAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		propLoadingDiscount_CalculatedAmountAutomobile.setAttribute("Value", "200");
		propLoadingDiscount_CalculatedAmountAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionAutomobile.setAttribute("Value", "Automation Association Discount");
		PropLoadingDiscount_DescriptionAutomobile.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSIAutomobile.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountAutomobile.setAttribute("Value", "200");
		PropLoadingDiscount_EndorsementAmountAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountAutomobile.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedAutomobile.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedAutomobile.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedAutomobile.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedAutomobile.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapAutomobile = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapAutomobile.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapAutomobile = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapAutomobile.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumAutomobile = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumAutomobile.setAttribute("Value", "18988.66");
		PropLoadingDiscount_PremiumAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateAutomobile = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateAutomobile.setAttribute("Value", "5");
		PropLoadingDiscount_RateAutomobile.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredAutomobile = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredAutomobile.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredAutomobile.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////
		org.jdom2.Element PolicyLoadingDiscountDetailsSoftCopy = new org.jdom2.Element("PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsSoftCopy.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableSoftCopy.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableSoftCopy.setAttribute("Type", "Boolean");

		org.jdom2.Element propLoadingDiscount_CalculatedAmountSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		propLoadingDiscount_CalculatedAmountSoftCopy.setAttribute("Value", "200");
		propLoadingDiscount_CalculatedAmountSoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionSoftCopy.setAttribute("Value", "Discount for Opting Soft Copy");
		PropLoadingDiscount_DescriptionSoftCopy.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSISoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSISoftCopy.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSISoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountSoftCopy.setAttribute("Value", "200");
		PropLoadingDiscount_EndorsementAmountSoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountSoftCopy.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountSoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedSoftCopy.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedSoftCopy.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedSoftCopy.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedSoftCopy.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapSoftCopy = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapSoftCopy.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapSoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapSoftCopy = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapSoftCopy.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapSoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumSoftCopy = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumSoftCopy.setAttribute("Value", "15823.36");
		PropLoadingDiscount_PremiumSoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateSoftCopy = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateSoftCopy.setAttribute("Value", "5");
		PropLoadingDiscount_RateSoftCopy.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredSoftCopy = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredSoftCopy.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredSoftCopy.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		org.jdom2.Element PolicyLoadingDiscountDetailsClaimBonus = new org.jdom2.Element(
				"PolicyLoadingDiscountDetails");
		PolicyLoadingDiscountDetailsClaimBonus.setAttribute("Type", "GroupData");

		org.jdom2.Element PropLoadingDiscount_ApplicableClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_Applicable");
		PropLoadingDiscount_ApplicableClaimBonus.setAttribute("Value", "True");
		PropLoadingDiscount_ApplicableClaimBonus.setAttribute("Type", "Boolean");

		org.jdom2.Element propLoadingDiscount_CalculatedAmountClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_CalculatedAmount");
		propLoadingDiscount_CalculatedAmountClaimBonus.setAttribute("Value", "2916.66");
		propLoadingDiscount_CalculatedAmountClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_DescriptionClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_Description");
		PropLoadingDiscount_DescriptionClaimBonus.setAttribute("Value", "No Claim Bonus");
		PropLoadingDiscount_DescriptionClaimBonus.setAttribute("Type", "String");

		org.jdom2.Element PropLoadingDiscount_DifferentialSIClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_DifferentialSIHandicapped");
		PropLoadingDiscount_DifferentialSIClaimBonus.setAttribute("Value", "0");
		PropLoadingDiscount_DifferentialSIClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_EndorsementAmountClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_EndorsementAmount");
		PropLoadingDiscount_EndorsementAmountClaimBonus.setAttribute("Value", "2916.66");
		PropLoadingDiscount_EndorsementAmountClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_FixedAmountClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_FixedAmount");
		PropLoadingDiscount_FixedAmountClaimBonus.setAttribute("Value", "0");
		PropLoadingDiscount_FixedAmountClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_IsDataDeletedClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_IsDataDeleted");
		PropLoadingDiscount_IsDataDeletedClaimBonus.setAttribute("Value", "False");
		PropLoadingDiscount_IsDataDeletedClaimBonus.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_IsOldDataDeletedClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_IsOldDataDeleted");
		PropLoadingDiscount_IsOldDataDeletedClaimBonus.setAttribute("Value", "False");
		PropLoadingDiscount_IsOldDataDeletedClaimBonus.setAttribute("Type", "Boolean");

		org.jdom2.Element PropLoadingDiscount_MaxCapClaimBonus = new org.jdom2.Element("PropLoadingDiscount_MaxCap");
		PropLoadingDiscount_MaxCapClaimBonus.setAttribute("Value", "0");
		PropLoadingDiscount_MaxCapClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_MinCapClaimBonus = new org.jdom2.Element("PropLoadingDiscount_MinCap");
		PropLoadingDiscount_MinCapClaimBonus.setAttribute("Value", "0");
		PropLoadingDiscount_MinCapClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_PremiumClaimBonus = new org.jdom2.Element("PropLoadingDiscount_Premium");
		PropLoadingDiscount_PremiumClaimBonus.setAttribute("Value", "14583.32");
		PropLoadingDiscount_PremiumClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_RateClaimBonus = new org.jdom2.Element("PropLoadingDiscount_Rate");
		PropLoadingDiscount_RateClaimBonus.setAttribute("Value", "20");
		PropLoadingDiscount_RateClaimBonus.setAttribute("Type", "Double");

		org.jdom2.Element PropLoadingDiscount_SumInsuredClaimBonus = new org.jdom2.Element(
				"PropLoadingDiscount_SumInsured");
		PropLoadingDiscount_SumInsuredClaimBonus.setAttribute("Value", "829777");
		PropLoadingDiscount_SumInsuredClaimBonus.setAttribute("Type", "Double");
		//////////////////////////////////////////////////////////////////////////////

		ProposalDetails.addContent(PolicyLoadingDiscountDetails);
		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetails1);

		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_Applicable);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_CalculatedAmount);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_Description);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_DifferentialSI);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_EndorsementAmount);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_FixedAmount);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_IsDataDeleted);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_IsOldDataDeleted);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_MaxCap);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_MinCap);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_Premium);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_Rate);
		PolicyLoadingDiscountDetails1.addContent(PropLoadingDiscount_SumInsured);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetails2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_Applicable2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_CalculatedAmount2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_Description2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_DifferentialSI2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_EndorsementAmount2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_FixedAmount2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_IsDataDeleted2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_IsOldDataDeleted2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_MaxCap2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_MinCap2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_Premium2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_Rate2);
		PolicyLoadingDiscountDetails2.addContent(PropLoadingDiscount_SumInsured2);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_ApplicableDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_CalculatedAmountDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_DescriptionDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_DifferentialSIDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_EndorsementAmountDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_FixedAmountDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_IsDataDeletedDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_IsOldDataDeletedDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_MaxCapDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_MinCapDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_PremiumDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_RateDrivingTutions);
		PolicyLoadingDiscountDetailsDrivingTutions.addContent(PropLoadingDiscount_SumInsuredDrivingTutions);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_ApplicableHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_CalculatedAmountHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_DescriptionHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_DifferentialSIHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_EndorsementAmountHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_FixedAmountHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_IsDataDeletedHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_IsOldDataDeletedHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_MaxCapHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_MinCapHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_PremiumHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_RateHandicapped);
		PolicyLoadingDiscountDetailsHandicapped.addContent(PropLoadingDiscount_SumInsuredHandicapped);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_ApplicableVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_CalculatedAmountVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_DescriptionVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_DifferentialSIVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_EndorsementAmountVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_FixedAmountVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_IsDataDeletedVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_IsOldDataDeletedVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_MaxCapVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_MinCapVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_PremiumVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_RateVoluntary);
		PolicyLoadingDiscountDetailsVoluntary.addContent(PropLoadingDiscount_SumInsuredVoluntary);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsOwnPremises);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_ApplicableOwnPremises);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_CalculatedAmountOwnPremises);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_DescriptionOwnPremises);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_DifferentialSIOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_EndorsementAmountOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_FixedAmountOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_IsDataDeletedOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_IsOldDataDeletedOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_MaxCapOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_MinCapOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_PremiumOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_RateOwnPremisesn);
		PolicyLoadingDiscountDetailsOwnPremises.addContent(PropLoadingDiscount_SumInsuredOwnPremisesn);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_ApplicableVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_CalculatedAmountVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_DescriptionVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_DifferentialSIVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_EndorsementAmountVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_FixedAmountVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_IsDataDeletedVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_IsOldDataDeletedVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_MaxCapVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_MinCapVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_PremiumVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_RateVintageCar);
		PolicyLoadingDiscountDetailsVintageCar.addContent(PropLoadingDiscount_SumInsuredVintageCar);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_ApplicableTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_CalculatedAmountTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_DescriptionTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_DifferentialSITPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_EndorsementAmountTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_FixedAmountTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_IsDataDeletedTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_IsOldDataDeletedTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_MaxCapTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_MinCapTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_PremiumTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_RateTPPD);
		PolicyLoadingDiscountDetailsTPPD.addContent(PropLoadingDiscount_SumInsuredTPPD);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_ApplicableCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_CalculatedAmountCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_DescriptionCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_DifferentialSICoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_EndorsementAmountCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_FixedAmountCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_IsDataDeletedCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_IsOldDataDeletedCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_MaxCapCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_MinCapCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_PremiumCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_RateCoverDisc);
		PolicyLoadingDiscountDetailsCoverDisc.addContent(PropLoadingDiscount_SumInsuredCoverDisc);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_ApplicableAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_CalculatedAmountAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_DescriptionAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_DifferentialSIAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_EndorsementAmountAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_FixedAmountAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_IsDataDeletedAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_IsOldDataDeletedAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_MaxCapAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_MinCapAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_PremiumAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_RateAntiTheft);
		PolicyLoadingDiscountDetailsAntiTheft.addContent(PropLoadingDiscount_SumInsuredAntiTheft);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_ApplicableAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(propLoadingDiscount_CalculatedAmountAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_DescriptionAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_DifferentialSIAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_EndorsementAmountAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_FixedAmountAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_IsDataDeletedAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_IsOldDataDeletedAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_MaxCapAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_MinCapAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_PremiumAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_RateAutomobile);
		PolicyLoadingDiscountDetailsAutomobile.addContent(PropLoadingDiscount_SumInsuredAutomobile);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_ApplicableSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(propLoadingDiscount_CalculatedAmountSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_DescriptionSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_DifferentialSISoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_EndorsementAmountSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_FixedAmountSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_IsDataDeletedSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_IsOldDataDeletedSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_MaxCapSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_MinCapSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_PremiumSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_RateSoftCopy);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_SumInsuredSoftCopy);

		PolicyLoadingDiscountDetails.addContent(PolicyLoadingDiscountDetailsClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_ApplicableClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(propLoadingDiscount_CalculatedAmountClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_DescriptionClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_DifferentialSIClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_EndorsementAmountClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_FixedAmountClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_IsDataDeletedClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_IsOldDataDeletedClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_MaxCapClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_MinCapClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_PremiumClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_RateClaimBonus);
		PolicyLoadingDiscountDetailsSoftCopy.addContent(PropLoadingDiscount_SumInsuredClaimBonus);

	}

	private void otherMappingFiles() {

		org.jdom2.Element OtherMappingFields = new org.jdom2.Element("OtherMappingFields");

		org.jdom2.Element BasicTPincludingTPPDpremium = new org.jdom2.Element("BasicTPincludingTPPDpremium");
		BasicTPincludingTPPDpremium.setAttribute("Name", "Basic TP including TPPD premium");
		BasicTPincludingTPPDpremium.setAttribute("Value", "6000");
		BasicTPincludingTPPDpremium.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element OwnDamage = new org.jdom2.Element("OwnDamage");
		OwnDamage.setAttribute("Name", "Own Damage");
		OwnDamage.setAttribute("Value", "699777");
		OwnDamage.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element EngineProtect = new org.jdom2.Element("EngineProtect");
		EngineProtect.setAttribute("Name", "Engine Protect");
		EngineProtect.setAttribute("Value", "789777");
		EngineProtect.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element ReturntoInvoice = new org.jdom2.Element("ReturntoInvoice");
		ReturntoInvoice.setAttribute("Name", "Return to Invoice");
		ReturntoInvoice.setAttribute("Value", "789777");
		ReturntoInvoice.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element ConsumableCover = new org.jdom2.Element("ConsumableCover");
		ConsumableCover.setAttribute("Name", "Consumable Cover");
		ConsumableCover.setAttribute("Value", "789777");
		ConsumableCover.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element LegalLibforpaiddriver = new org.jdom2.Element("LegalLiabilityforpaiddrivercleanerconductor");
		LegalLibforpaiddriver.setAttribute("Name", "Legal Liability for paid driver cleaner conductor");
		LegalLibforpaiddriver.setAttribute("Value", "0");
		LegalLibforpaiddriver.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element LegalLibEmployees = new org.jdom2.Element(
				"LegalLiabilityforEmployeesotherthanpaiddriverconductorcleaner");
		LegalLibEmployees.setAttribute("Name",
				"Legal Liability for Employees other than paid driver conductor cleaner");
		LegalLibEmployees.setAttribute("Value", "0");
		LegalLibEmployees.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element RoadSideAssistance = new org.jdom2.Element("RoadSideAssistance");
		RoadSideAssistance.setAttribute("Name", "Road Side Assistance");
		RoadSideAssistance.setAttribute("Value", "0");
		RoadSideAssistance.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element RalliesTP = new org.jdom2.Element("RalliesTP");
		RalliesTP.setAttribute("Name", "RalliesTP");
		RalliesTP.setAttribute("Value", "0");
		RalliesTP.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element DepreciationCover = new org.jdom2.Element("DepreciationCover");
		DepreciationCover.setAttribute("Name", "Depreciation Cover");
		DepreciationCover.setAttribute("Value", "0");
		DepreciationCover.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element RalliesOD = new org.jdom2.Element("RalliesOD");
		RalliesOD.setAttribute("Name", "Rallies OD");
		RalliesOD.setAttribute("Value", "789777");
		RalliesOD.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element Liabilitytosoldiersailorairman = new org.jdom2.Element("Liabilitytosoldiersailorairman");
		Liabilitytosoldiersailorairman.setAttribute("Name", "Liability to soldier sailor airman");
		Liabilitytosoldiersailorairman.setAttribute("Value", "0");
		Liabilitytosoldiersailorairman.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element GeographicalExtensionTP = new org.jdom2.Element("GeographicalExtensionTP");
		GeographicalExtensionTP.setAttribute("Name", "Geographical Extension TP");
		GeographicalExtensionTP.setAttribute("Value", "0");
		GeographicalExtensionTP.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element GeographicalExtensionOD = new org.jdom2.Element("GeographicalExtensionOD");
		GeographicalExtensionOD.setAttribute("Name", "Geographical Extension OD");
		GeographicalExtensionOD.setAttribute("Value", "0");
		GeographicalExtensionOD.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element OwnerDriver = new org.jdom2.Element("OwnerDriver");
		OwnerDriver.setAttribute("Name", "Owner Driver");
		OwnerDriver.setAttribute("Value", "200000");
		OwnerDriver.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element CNGKitTP = new org.jdom2.Element("CNGKitTP");
		CNGKitTP.setAttribute("Name", "CNG Kit TP");
		CNGKitTP.setAttribute("Value", "0");
		CNGKitTP.setAttribute("Type", "Vehicle Base Value");

		org.jdom2.Element TrailerOD = new org.jdom2.Element("TrailerOD");
		TrailerOD.setAttribute("Name", "CNG Kit TP");
		TrailerOD.setAttribute("Value", "40000");
		TrailerOD.setAttribute("Type", "Trailer");

		org.jdom2.Element TrailerTP = new org.jdom2.Element("TrailerOD");
		TrailerTP.setAttribute("Name", "Trailer TP");
		TrailerTP.setAttribute("Value", "0");
		TrailerTP.setAttribute("Type", "Trailer");

		org.jdom2.Element ElectronicAccessoriesOD = new org.jdom2.Element("ElectronicAccessoriesOD");
		ElectronicAccessoriesOD.setAttribute("Name", "Electronic Accessories OD");
		ElectronicAccessoriesOD.setAttribute("Value", "15000");
		ElectronicAccessoriesOD.setAttribute("Type", "Electronic Accessories");

		org.jdom2.Element NonElectricalAccessoriesOD = new org.jdom2.Element("ElectronicAccessoriesOD");
		NonElectricalAccessoriesOD.setAttribute("Name", "Non Electrical Accessories OD");
		NonElectricalAccessoriesOD.setAttribute("Value", "25000");
		NonElectricalAccessoriesOD.setAttribute("Type", "Non Electrical Accessories");

		org.jdom2.Element CNGKitOD = new org.jdom2.Element("CNGKitOD");
		CNGKitOD.setAttribute("Name", "CNG Kit OD");
		CNGKitOD.setAttribute("Value", "5000");
		CNGKitOD.setAttribute("Type", "CNG and LPG Kit");

		org.jdom2.Element NamedPassengersPersonalAccident = new org.jdom2.Element("NamedPassengersPersonalAccident");
		NamedPassengersPersonalAccident.setAttribute("Name", "Named Passengers Personal");
		NamedPassengersPersonalAccident.setAttribute("Value", "100000");
		NamedPassengersPersonalAccident.setAttribute("Type", "Named PA Cover");

		org.jdom2.Element UnnamedPassengersPersonalAccident = new org.jdom2.Element(
				"UnnamedPassengersPersonalAccident");
		UnnamedPassengersPersonalAccident.setAttribute("Name", "Unnamed Passengers Personal Accident");
		UnnamedPassengersPersonalAccident.setAttribute("Value", "50000");
		UnnamedPassengersPersonalAccident.setAttribute("Type", "Unnamed PA cover");

		org.jdom2.Element PaidDriverPACover = new org.jdom2.Element("PaidDriverPACover");
		PaidDriverPACover.setAttribute("Name", "Paid Driver PA Cover");
		PaidDriverPACover.setAttribute("Value", "80000");
		PaidDriverPACover.setAttribute("Type", "Paid driver PA cover");

		ProposalDetails.addContent(OtherMappingFields);
		OtherMappingFields.addContent(BasicTPincludingTPPDpremium);
		OtherMappingFields.addContent(OwnDamage);
		OtherMappingFields.addContent(EngineProtect);
		OtherMappingFields.addContent(ReturntoInvoice);
		OtherMappingFields.addContent(ConsumableCover);
		OtherMappingFields.addContent(LegalLibforpaiddriver);
		OtherMappingFields.addContent(LegalLibEmployees);
		OtherMappingFields.addContent(RoadSideAssistance);
		OtherMappingFields.addContent(RalliesTP);
		OtherMappingFields.addContent(DepreciationCover);
		OtherMappingFields.addContent(RalliesOD);
		OtherMappingFields.addContent(Liabilitytosoldiersailorairman);
		OtherMappingFields.addContent(GeographicalExtensionTP);
		OtherMappingFields.addContent(GeographicalExtensionOD);
		OtherMappingFields.addContent(OwnerDriver);
		OtherMappingFields.addContent(CNGKitTP);
		OtherMappingFields.addContent(TrailerOD);
		OtherMappingFields.addContent(TrailerTP);
		OtherMappingFields.addContent(ElectronicAccessoriesOD);
		OtherMappingFields.addContent(NonElectricalAccessoriesOD);
		OtherMappingFields.addContent(CNGKitOD);
		OtherMappingFields.addContent(NamedPassengersPersonalAccident);
		OtherMappingFields.addContent(UnnamedPassengersPersonalAccident);
		OtherMappingFields.addContent(PaidDriverPACover);

	}

	private void customerDetails() {

		org.jdom2.Element CustomerDetails = new org.jdom2.Element("CustomerDetails");

		org.jdom2.Element CustomerId = new org.jdom2.Element("CustomerId");
		CustomerId.setAttribute("Name", "Customer ID");
		CustomerId.setAttribute("Value", "");
		CustomerId.setAttribute("Type", "Double");

		org.jdom2.Element Salutation = new org.jdom2.Element("Salutation");
		Salutation.setAttribute("Name", "Salutation");
		Salutation.setAttribute("Value", "");
		Salutation.setAttribute("Type", "String");

		org.jdom2.Element LastName = new org.jdom2.Element("LastName");
		LastName.setAttribute("Name", "LastName");
		LastName.setAttribute("Value", "");
		LastName.setAttribute("Type", "String");

		org.jdom2.Element ISUIICEmployee = new org.jdom2.Element("ISUIICEmployee");
		ISUIICEmployee.setAttribute("Name", "ISUIICEmployee");
		ISUIICEmployee.setAttribute("Value", "N");
		ISUIICEmployee.setAttribute("Type", "Char");

		org.jdom2.Element DOB = new org.jdom2.Element("DOB");
		DOB.setAttribute("Name", "DOB");
		DOB.setAttribute("Value", "");
		DOB.setAttribute("Type", "String");

		org.jdom2.Element FirstName = new org.jdom2.Element("FirstName");
		FirstName.setAttribute("Name", "FirstName");
		FirstName.setAttribute("Value", "");
		FirstName.setAttribute("Type", "String");

		org.jdom2.Element CustomerType = new org.jdom2.Element("CustomerType");
		CustomerType.setAttribute("Name", "CustomerType");
		CustomerType.setAttribute("Value", "");
		CustomerType.setAttribute("Type", "String");

		org.jdom2.Element PermanentLocationSameAsMailLocation = new org.jdom2.Element(
				"PermanentLocationSameAsMailLocation");
		PermanentLocationSameAsMailLocation.setAttribute("Name", "PermanentLocationSameAsMailLocation");
		PermanentLocationSameAsMailLocation.setAttribute("Value", "False");
		PermanentLocationSameAsMailLocation.setAttribute("Type", "Boolean");

		org.jdom2.Element Gender = new org.jdom2.Element("Gender");
		Gender.setAttribute("Name", "Gender");
		Gender.setAttribute("Value", "");
		Gender.setAttribute("Type", "String");

		org.jdom2.Element PanNo = new org.jdom2.Element("PanNo");
		PanNo.setAttribute("Name", "PanNo");
		PanNo.setAttribute("Value", "");
		PanNo.setAttribute("Type", "String");

		org.jdom2.Element EmailId = new org.jdom2.Element("EmailId");
		EmailId.setAttribute("Name", "EmailId");
		EmailId.setAttribute("Value", "");
		EmailId.setAttribute("Type", "String");

		rootelement.addContent(CustomerDetails);
		CustomerDetails.addContent(CustomerId);
		CustomerDetails.addContent(Salutation);
		CustomerDetails.addContent(LastName);
		CustomerDetails.addContent(ISUIICEmployee);
		CustomerDetails.addContent(DOB);
		CustomerDetails.addContent(FirstName);
		CustomerDetails.addContent(CustomerType);
		CustomerDetails.addContent(PermanentLocationSameAsMailLocation);
		CustomerDetails.addContent(Gender);
		CustomerDetails.addContent(PanNo);
		CustomerDetails.addContent(EmailId);

	}

	private String PostInfoToAPI(Object obj, String MethodName) {
		String str = "";
		try {
			URL url = new URL(
					"https://kgipass.kotakmahindrageneralinsurance.co.in/KGIGenericPremiumCalcService/kgiservice.svc/"
							+ MethodName);
			System.out.println("Url-->>>" + url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			String request = obj.toString();
			byte[] data = request.getBytes("UTF-8");
			java.io.OutputStream outputStream = connection.getOutputStream();
			outputStream.write(data, 0, data.length);
			outputStream.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			str = response.toString();
			System.out.println("String==Json===>" + str);

		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}

	private String PostInfoToAPIXml(Object obj, String MethodName) {
		String str = "";
		try {
			// "https://kgipass.kotakmahindrageneralinsurance.co.in/GCCustomerPortalServiceGateway/CustomerPortalService.svc/"
			URL url = new URL(
					"https://kgipass.kotakmahindrageneralinsurance.co.in/GCCustomerPortalServiceGateway/CustomerPortalService.svc/"

							+ MethodName);
			System.out.println("Url-->>>" + url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");
			String request = obj.toString();
			byte[] data = request.getBytes("UTF-8");
			java.io.OutputStream outputStream = connection.getOutputStream();
			outputStream.write(data, 0, data.length);
			outputStream.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			str = response.toString();
			// System.out.println("String==XML===>" + str);

		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}

}
