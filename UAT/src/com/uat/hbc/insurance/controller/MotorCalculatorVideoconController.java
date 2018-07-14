package com.uat.hbc.insurance.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import oracle.net.aso.l;

import org.w3c.dom.Document;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.codehaus.jackson.map.util.JSONPObject;
import org.jdom2.JDOMException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uat.hbc.common.dao.IntegrationSaveResponseDao;
import com.uat.hbc.common.model.MotorResponseBean;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.insurance.dao.IntegrationVideoconDao;
import com.uat.hbc.insurance.model.VideoconIntBean;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;

@Controller
public class MotorCalculatorVideoconController {
	@Autowired
			IntegrationVideoconDao masterDao;
			@Qualifier("dbProcessImpl")
			DbProcess dbProcess;

	String request_for;
	String method;
	String s1;
	HashMap<String, String> hashmap = new HashMap<>();
	String quotationNumber , Email , customerID;

	MotorResponseBean bean;
	@Autowired
    IntegrationSaveResponseDao integrationSaveResponseDao;
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;
	VideoconIntBean videoconIntBean;
	
//	String motorGroupResponseGroupId=null,motorGroupResponseSessionId=null,motorGroupResponseGicId=null;
	
	@RequestMapping("user/ADMotorCalculatorVideocon")
	public ModelAndView callJsp(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/ADMotorCalculatorVideocon");
	
		return modelAndView;
	}
	
	
	public MotorCalculatorVideoconController() {
		bean = new MotorResponseBean();
		videoconIntBean = new VideoconIntBean();
	}


	
	@RequestMapping(value = "user/motorCalculatorVideocon", method = RequestMethod.POST, produces ="application/json; charset=UTF-8")
	public @ResponseBody String getProposal(HttpServletRequest request,
			HttpServletResponse response) throws JDOMException, IOException {
		
//		 MotorResponseBean proposalResult=integrationSaveResponseDao.saveProposalData(hashmap,motorGroupResponseGroupId,motorGroupResponseSessionId,motorGroupResponseGicId,bean);
		videoconIntBean = new VideoconIntBean();
		String s = null;
		String newValue="";
		try {
			System.out.println("11111111111111111111111111111111111111111111");
			System.out.println("IN motorCalculatorVideocon");
			request_for = request.getParameter("request_for");
			String pkg_name = request.getParameter("pkg_name");
//			System.out.println("pkg_name==>"+pkg_name);
			String proc_name=request.getParameter("proc_name");
//			System.out.println("proc_name==>"+proc_name);
			String table_name=request.getParameter("table_name");
			String str_ClientType = request.getParameter("str_ClientType");
			String str_lastName = request.getParameter("str_lastName");
			String str_MidName = request.getParameter("str_MidName");
			String str_ForeName = request.getParameter("str_ForeName");
			String str_CorporateName = request.getParameter("str_CorporateName");
			String str_OccupationID = request.getParameter("str_OccupationID");
			String str_DOB = request.getParameter("str_DOB");
//			System.out.println("Dob" +str_DOB);
			String str_Gender = request.getParameter("str_Gender");
			String str_PhoneNo = request.getParameter("str_PhoneNo");
			String str_MobileNo = request.getParameter("str_MobileNo");
			String str_ClientAddress = request.getParameter("str_ClientAddress");
			String str_CLD_CA_AddressType = request.getParameter("str_CLD_CA_AddressType");
			String str_CLD_CA_Address1 = request.getParameter("str_CLD_CA_Address1");
			String str_CLD_CA_Address2 = request.getParameter("str_CLD_CA_Address2");
			String str_CLD_CA_Address3 = request.getParameter("str_CLD_CA_Address3");
			String str_CLD_CA_AreaID = request.getParameter("str_CLD_CA_AreaID");
			String str_CLD_CA_Pincode = request.getParameter("str_CLD_CA_Pincode");
			String str_CLD_CA_Country = request.getParameter("str_CLD_CA_Country");
			String str_CLD_CA_NearestLandmark = request.getParameter("str_CLD_CA_NearestLandmark");
			String str_CLD_PA_AddressType = request.getParameter("str_CLD_PA_AddressType");
			String str_CLD_PA_Address1 = request.getParameter("str_CLD_PA_Address1");
			String str_CLD_PA_Address2 = request.getParameter("str_CLD_PA_Address2");
			String str_CLD_PA_Address3 = request.getParameter("str_CLD_PA_Address3");
			String str_CLD_PA_AreaID = request.getParameter("str_CLD_PA_AreaID");
			String str_CLD_PA_Pincode = request.getParameter("str_CLD_PA_Pincode");
			String str_CLD_PA_Country = request.getParameter("str_CLD_PA_Country");
			String str_CLD_PA_NearestLandmark = request.getParameter("str_CLD_PA_NearestLandmark");
			String str_EmailID = request.getParameter("str_EmailID");
			String str_Salutation = request.getParameter("str_Salutation");
			String str_MaritalStatus = request.getParameter("str_MaritalStatus");
			String str_Nationality = request.getParameter("str_Nationality");
			String str_PANno = request.getParameter("str_PANno");
			String str_P_BusinessType = request.getParameter("str_P_BusinessType");
			String str_ProductID = request.getParameter("str_ProductID");
			String str_VehID = request.getParameter("str_VehID");
			String str_ModelID = request.getParameter("str_ModelID");
			String str_VarID = request.getParameter("str_VarID");
			String str_EngineNo = request.getParameter("str_EngineNo");
			String str_Chassis = request.getParameter("str_Chassis");
			String str_IsVehicleHypothicated = request.getParameter("str_IsVehicleHypothicated");
			String str_FinanceTypeID = request.getParameter("str_FinanceTypeID");
			String str_FinancierName = request.getParameter("str_FinancierName");
			String str_StateOfRegistrationID = request.getParameter("str_StateOfRegistrationID");
			String str_Rto_City = request.getParameter("str_Rto_City");
			String str_ISNewVehicle = request.getParameter("str_ISNewVehicle");
			String str_RegNo1 = request.getParameter("str_RegNo1");
			String str_RegNo2 = request.getParameter("str_RegNo2");
			String str_RegNo3 = request.getParameter("str_RegNo3");
			String str_RegNo4 = request.getParameter("str_RegNo4");
			String str_Covers = request.getParameter("str_Covers");
			String str_CoversValue = request.getParameter("str_CoversValue");
			String str_IsBiFuelKit = request.getParameter("str_IsBiFuelKit");
			String str_ISLpgCng = request.getParameter("str_ISLpgCng");
			String str_BFK_Amt = request.getParameter("str_BFK_Amt");
			String str_PrevInsName = request.getParameter("str_PrevInsName");
			String str_PrevStartDt = request.getParameter("str_PrevStartDt");
			String str_PrevEndDt = request.getParameter("str_PrevEndDt");
			String str_PrevPolicyType = request.getParameter("str_PrevPolicyType");
			String str_PrevPolicyNo = request.getParameter("str_PrevPolicyNo");
			String str_PrevClaimNo = request.getParameter("str_PrevClaimNo");
			String str_PrevClaimAmt = request.getParameter("str_PrevClaimAmt");
			String str_NCBIsApp = request.getParameter("str_NCBIsApp");
			String str_NCBEligiCrit = request.getParameter("str_NCBEligiCrit");
			String str_NCBPrevNCB = request.getParameter("str_NCBPrevNCB");
			String str_NCBCurNCB = request.getParameter("str_NCBCurNCB");
			String str_IsTrailerAttached = request.getParameter("str_IsTrailerAttached");
			String str_IsInspDone = request.getParameter("str_IsInspDone");
			String str_InspDoneByWhom = request.getParameter("str_InspDoneByWhom");
			String str_InspReportDate = request.getParameter("str_InspReportDate");
//			System.out.println("str_InspReportDate" +str_InspReportDate);
			String str_InspDate = request.getParameter("str_InspDate");
//			System.out.println("str_InspDate" +str_InspDate);
			String str_ZONE_ID = request.getParameter("str_ZONE_ID");
			String str_PAO_NomineeRepRel = request.getParameter("str_PAO_NomineeRepRel");
			String str_PAO_NomineeRel = request.getParameter("str_PAO_NomineeRel");
			String str_GSTIN = request.getParameter("str_GSTIN");
			String str_IsNilDepOptedInPrevPolicy = request.getParameter("str_IsNilDepOptedInPrevPolicy");
			String str_PolicyType = request.getParameter("str_PolicyType");
			String str_custAge = request.getParameter("str_custAge");
			String str_ownDriverAppointeeName = request.getParameter("str_ownDriverAppointeeName");
			String str_CoversNo = request.getParameter("str_CoversNo");
			String str_prevGICId = request.getParameter("str_prevGICId");
			String str_prevPolicyAge = request.getParameter("str_prevPolicyAge");
			String str_fuelType = request.getParameter("str_fuelType");
			String preInsPreInsCmpId = request.getParameter("preInsPreInsCmpId");
			
			
//			System.out.println("request_for: "+request_for);
			str_Salutation=str_Salutation.replace(".", "");
			HashMap inputParaList = new HashMap<>();
			HashMap input = new HashMap<>();
			
			inputParaList.put("PI_TABLE_NAME", table_name);
			inputParaList.put("PI_CUST_TYPE", str_ClientType);
			inputParaList.put("PI_Rto_City", str_Rto_City);
			inputParaList.put("PI_ZONE_ID", str_ZONE_ID);
			inputParaList.put("PI_PolicyType", str_PolicyType);
			inputParaList.put("PI_VarID", str_VarID);
			inputParaList.put("PI_P_BusinessType", str_P_BusinessType);
			inputParaList.put("PI_ProductID", str_ProductID);
			inputParaList.put("PI_VehID", str_VehID);
			inputParaList.put("PI_ModelID", str_ModelID);
//			inputParaList.put("PI_CustAge", str_custAge);
			inputParaList.put("PI_OccupationID", str_OccupationID);
			inputParaList.put("PI_FIN_ID", str_FinanceTypeID);
			inputParaList.put("PI_PREV_GIC_ID", str_prevGICId);
			inputParaList.put("PI_AreaID", str_CLD_PA_AreaID);
//			inputParaList.put("PI_Corrs_AreaID", str_CLD_CA_AreaID);
			inputParaList.put("PI_Nom_Rel", str_PAO_NomineeRel);
			inputParaList.put("PI_NomineeRepRel", str_PAO_NomineeRepRel);
			inputParaList.put("PI_Own_Dri_Appointee_Rel", str_ownDriverAppointeeName);
			inputParaList.put("PI_Covers", str_Covers);
			inputParaList.put("PI_COV_VAL", str_CoversValue);
			inputParaList.put("PI_COV_NO", str_CoversNo);
			System.out.println("str_Covers==============>>"+str_Covers);
			System.out.println("str_CoversValue==============>>"+str_CoversValue);
			System.out.println("str_CoversNo==============>>"+str_CoversNo);
			     				
            String retMsg = "";
			String jsonNames = "";
			System.out.println("inputParaListCover--==" + inputParaList);
			
			jsonNames = masterDao.getVideoconData(pkg_name, proc_name, inputParaList);
			
			System.out.println("jsonNames Combine first Response======>>"+jsonNames);
			String jsonNamesArr[]= jsonNames.split("\\}\\]\\[\\{");
			
			jsonNames=jsonNamesArr[0];
			String coverList="";
			if(jsonNamesArr.length>1){
				 coverList =jsonNamesArr[1];
				 coverList="[{"+coverList;
				 jsonNames=jsonNames+"}]";
			}
			
			System.out.println("jsonName controller::: "+jsonNames);
			System.out.println("coverList Controller::: "+coverList);
			
			
			
			String str_isFullQuote = request.getParameter("str_isFullQuote");
			
			switch (request_for) {
			case "premium":
				System.out.println("222222222222222222222222222222222222222222222222");
				method = "PostPremiumDetails";
				str_isFullQuote= "false";
				break;
			case "proposal":
				method = "PostPremiumDetails";
				str_isFullQuote= "true";
				break;
			default:
				break;
			}
						
			String str_ManfMonth = request.getParameter("str_ManfMonth");
			System.out.println(str_ManfMonth);
			if(str_ManfMonth.length()==2)
			{
				str_ManfMonth=str_ManfMonth;
			}else{
				str_ManfMonth="0"+str_ManfMonth;
			}
			System.out.println("33333333333333333333333333333333333333333333333");
			String str_ManfYear = request.getParameter("str_ManfYear");
			String str_DeliveryDate = request.getParameter("str_DeliveryDate");
			String str_RegistrationDate = request.getParameter("str_RegistrationDate");
			String str_VehicleIDV = request.getParameter("str_VehicleIDV");
			String str_NoOfPassengerForLLToPaidDriver = request.getParameter("str_NoOfPassengerForLLToPaidDriver");
			String str_NoOfPassengerForLLToEmployee = request.getParameter("str_NoOfPassengerForLLToEmployee");
			String str_NoOfPerunnamed = request.getParameter("str_NoOfPerunnamed");
			String str_UnnamedPASI = request.getParameter("str_UnnamedPASI");
			String str_lstAccessories_Description = request.getParameter("str_lstAccessories_Description");
			String str_lstAccessories_Make = request.getParameter("str_lstAccessories_Make");
			String str_lstAccessories_Model = request.getParameter("str_lstAccessories_Model");
			String str_lstAccessories_ManufactureYear = request.getParameter("str_lstAccessories_ManufactureYear");
			String str_lstAccessories_SerialNo= request.getParameter("str_lstAccessories_SerialNo");
			String str_lstAccessories_SumInsured = request.getParameter("str_lstAccessories_SumInsured");
			
			String str_lstNonElecAccessories_Description = request.getParameter("str_lstNonElecAccessories_Description");
			String str_lstNonElecAccessories_Make = request.getParameter("str_lstNonElecAccessories_Make");
			String str_lstNonElecAccessories_Model = request.getParameter("str_lstNonElecAccessories_Model");
			String str_lstNonElecAccessories_ManufactureYear = request.getParameter("str_lstNonElecAccessories_ManufactureYear");
			String str_lstNonElecAccessories_SerialNo= request.getParameter("str_lstNonElecAccessories_SerialNo");
			String str_lstNonElecAccessories_SumInsured = request.getParameter("str_lstNonElecAccessories_SumInsured");
			String str_NoOfPernamed = request.getParameter("str_NoOfPernamed");
			String str_NamedPASI = request.getParameter("str_NamedPASI");
			String str_NoOfPaidDriverPassenger = request.getParameter("str_NoOfPaidDriverPassenger");
			String str_PAToPaidDriverSI = request.getParameter("str_PAToPaidDriverSI");
			String str_FuelSI = request.getParameter("str_FuelSI");
			String str_lstTrailer_ChassisNumber = request.getParameter("str_lstTrailer_ChassisNumber");
			String str_lstTrailer_EngineNumber = request.getParameter("str_lstTrailer_EngineNumber");
			String str_lstTrailer_SumInsured = request.getParameter("str_lstTrailer_SumInsured");
			String str_FiberGlassSI = request.getParameter("str_FiberGlassSI");
			String str_AAIMembshipNumber = request.getParameter("str_AAIMembshipNumber");
			String str_AAIAssociationCode = request.getParameter("str_AAIAssociationCode");
			String str_AAIAssociationName = request.getParameter("str_AAIAssociationName");
			String str_AAIMembshipExpiryDate = request.getParameter("str_AAIMembshipExpiryDate");
			String str_IsAntiTheftDeviceCertifiedByARAI = request.getParameter("str_IsAntiTheftDeviceCertifiedByARAI");
			String str_VoluntaryExcessAmt = request.getParameter("str_VoluntaryExcessAmt");
			String str_NoNomineeDetails = request.getParameter("str_NoNomineeDetails");
			String str_NomineeFirstName = request.getParameter("str_NomineeFirstName");
			String str_NomineelastName = request.getParameter("str_NomineelastName");
			String str_OtherRelation = request.getParameter("str_OtherRelation");
			String str_IsMinor = request.getParameter("str_IsMinor");
			String str_RepFirstName = request.getParameter("str_RepFirstName");
			String str_RepLastName = request.getParameter("str_RepLastName");
			String str_RepOtherRelation = request.getParameter("str_RepOtherRelation");
			String str_GAPCoverSI = request.getParameter("str_GAPCoverSI");
			String str_KeyLossCoverSI = request.getParameter("str_KeyLossCoverSI");
			String str_TPSource = request.getParameter("str_TPSource");
			String str_PinCodeLocality = request.getParameter("str_PinCodeLocality");
			String str_MailingPinCodeLocality = request.getParameter("str_MailingPinCodeLocality");
			String str_PermanentLocationSameAsMailLocation = request.getParameter("str_PermanentLocationSameAsMailLocation");
			String str_IsEIAAvailable = request.getParameter("str_IsEIAAvailable");
			String str_EIAAccNo = request.getParameter("str_EIAAccNo");
			String str_IsEIAPolicy = request.getParameter("str_IsEIAPolicy");
			String str_EIAAccWith = request.getParameter("str_EIAAccWith");
			String str_EIAPanNo = request.getParameter("str_EIAPanNo");
			String str_EIAUIDNo = request.getParameter("str_EIAUIDNo");
			String str_CustType = request.getParameter("str_CustType");
			System.out.println("str_CustType::" +str_CustType);
			String str_NomineeRelationship = request.getParameter("str_NomineeRelationship");
			String str_AgreementType = request.getParameter("str_AgreementType");
			String str_FinancierAddress = request.getParameter("str_FinancierAddress");
			
			String electricalItem = request.getParameter("ElectricalItem");
			String nonelectricalItem = request.getParameter("NonElectricalItem");
			String paownerdriver = request.getParameter("paownerdriver");
			System.out.println("paownerdriver::: " +paownerdriver);
			String preInsNcbAmount = request.getParameter("preInsNcbAmount");
			String preInsNoOfClaim = request.getParameter("preInsNoOfClaim");
			if(preInsNoOfClaim.equalsIgnoreCase("Select"))
			{
				preInsNoOfClaim = "0";
			}
			String str_ProductName =  request.getParameter("str_ProductName");
			
			if(paownerdriver.trim().equalsIgnoreCase("INDIVIDUAL")){
				paownerdriver="Yes";
			}else{
				paownerdriver="No";
			}
			System.out.println("GroupId:" +request.getParameter("groupId"));
			
		videoconIntBean.setMotorGroupResponseGroupId(request.getParameter("groupId"));
		System.out.println("setMotorGroupResponseGroupId::" +videoconIntBean.getMotorGroupResponseGroupId());
		videoconIntBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
		videoconIntBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
		videoconIntBean.setUserId(request.getParameter("userId"));
		videoconIntBean.setUserDesc(request.getParameter("userDesc"));
		videoconIntBean.setBranchId(request.getParameter("branchId"));
		videoconIntBean.setIPAddress(request.getParameter("IpAddress"));
			
			
			if(videoconIntBean.getUserId()!=null){
				bean.setUserId((videoconIntBean.getUserId()));
			}
			bean.setUserDesc(videoconIntBean.getUserDesc());
			bean.setBranchId(videoconIntBean.getBranchId());
			bean.setGroupId(videoconIntBean.getMotorGroupResponseGroupId());	
			bean.setSessionId(videoconIntBean.getMotorGroupResponseSessionId());
			bean.setGicId(videoconIntBean.getMotorGroupResponseGicId());
			
			
			//*******************************************************************************
			String depriciationCover="No",liabilityToPaidDriver="No",NoPassToLLPaiddriver="0",paUnnamed="No",
			noOfPerunnamed="0",unnamedPaSi="0",tppdDiscount="No",antiTheftdevice="No",legalliabilityToEmployee="No",NoOfPassengerForLLToEmployee="0",
			roadSideAsstCover="No",fiberGlassTankCover="No",fiberGlassSI="",drivingtution="No",AAIMembship="No",limitedToOwnPremise="No",
		    vehicleForHandicap="No", isGeoExtension="No",geoCountries="";
			
			if(!coverList.equals("")){
			String coverId="",coverNo="",coverVal="";
			JSONArray coverJson = new JSONArray(coverList);
			for (int table = 0; table < coverJson.length(); ++table) {
		            org.json.JSONObject obj = (org.json.JSONObject) coverJson.get(table);
		            String gicId = "", gicCompany = "";
		            if (!obj.isNull("HB_COVER_ID")) {
		                coverId = "" + obj.getInt("HB_COVER_ID");
		                System.out.println("1111111111 coverId==>>"+coverId);
		            }
		            if (!obj.isNull("COV_NO")) {
		            	coverNo = "" + obj.getInt("COV_NO");
		                System.out.println("1111111111 coverNo==>>"+coverNo);
		            }
		            if (!obj.isNull("COVER_VAL")) {
		            	coverVal = "" + obj.getString("COVER_VAL");
		                System.out.println("1111111111 coverVal==>>"+coverVal);
		            }
		            
		            if (coverId.equals("8")) {
		            	System.out.println("cover 8------------>>"+coverId);
		            	depriciationCover="Yes";
					}
		            if (coverId.equals("23")) {
		            	System.out.println("cover 23 Geoextension------------>>"+coverId);
		            	isGeoExtension="Yes";
		            	geoCountries = coverVal; 
		            }
		            
					if (coverId.equals("56")) {
						System.out.println("cover 56------------>>"+coverId);
						liabilityToPaidDriver="Yes";
						NoPassToLLPaiddriver=coverNo;	
					}
					
					if (coverId.equals("54")) {
						System.out.println("cover 54------------>>"+coverId);
						paUnnamed="Yes";
						noOfPerunnamed=coverNo;
						unnamedPaSi=coverVal;
					}
					
					if (coverId.equals("52")) {
						System.out.println("cover 52------------>>"+coverId);
						tppdDiscount="Yes";
					}
					
					if (coverId.equals("21")) {
						System.out.println("cover 21------------>>"+coverId);
						antiTheftdevice="Yes";
						str_IsAntiTheftDeviceCertifiedByARAI="True";
					}	
					
					if (coverId.equals("62")) {
						legalliabilityToEmployee="Yes";
						NoOfPassengerForLLToEmployee=coverNo;
					}
					
					if (coverId.equals("25")) {
						roadSideAsstCover="Yes";
					}
					if (coverId.equals("22")) {
						fiberGlassTankCover="Yes";
						fiberGlassSI=coverVal;
					}
					if (coverId.equals("15")) {
						if(!str_P_BusinessType.equals("1")){
							input.put("NoPreviousPolicyHistory", false);
						}
					}
					if (coverId.equals("20")) {
						drivingtution="Yes";
					}
					if (coverId.equals("109")) {
						AAIMembship="Yes";
						
					}
					if (coverId.equals("18")) {
						limitedToOwnPremise="Yes";
					}
					if (coverId.equals("24")) {
						vehicleForHandicap="Yes";
					}
				}
			}
			
		         //*******************************************************************************
			
			
			input.put("IsFullQuote",str_isFullQuote); 
			input.put("ManfMonth",str_ManfMonth);
			input.put("ManfYear",str_ManfYear); 
			input.put("DeliveryDate", str_DeliveryDate);
			input.put("RegistrationDate", str_RegistrationDate);
			input.put("VehicleIDV", str_VehicleIDV);
		/*	input.put("PolicyStartDate",str_PolicyStartDate);
			input.put("PolicyEndDate",str_PolicyEndDate);
			input.put("PolicyTenure",str_PolicyTenure);*/
			List<String> geographicalExtnList = new ArrayList<String>();
			
			String geoArr[]= geoCountries.split(",");
			for(int index = 0; index<geoArr.length; index++)
			{
				geographicalExtnList.add(geoArr[index]);
			}
			
			input.put("NoOfPassengerForLLToPaidDriver",str_NoOfPassengerForLLToPaidDriver);
			input.put("NoOfPassengerForLLToEmployee",str_NoOfPassengerForLLToEmployee);
			input.put("NoOfPerunnamed", str_NoOfPerunnamed);
			input.put("UnnamedPASI", str_UnnamedPASI);
			input.put("IsNilDepOptedInPrevPolicy", str_IsNilDepOptedInPrevPolicy);
			
			
			

//**************************************************************************************
			input.put("REGNO1", str_RegNo1);
			input.put("REGNO2", str_RegNo2);
			input.put("REGNO3", str_RegNo3);
			input.put("REGNO4", str_RegNo4);
			input.put("PREVIOUSPOLICYSTARTDATE", str_PrevStartDt);
			input.put("PREVIOUSPOLICYENDDATE", str_PrevEndDt);
			input.put("PREVIOUSPOLICYNUMBER", str_PrevPolicyNo);
			input.put("PREVIOUSYEARNCBPERCENTAGE", str_NCBPrevNCB);
			input.put("PREVIOUSPOLICYTENURE", str_prevPolicyAge);
			input.put("GeographicalExtn", isGeoExtension);
			input.put("GeographicalExtnList", geographicalExtnList);					

// add Cover
			input.put("DRIVINGTUITION", drivingtution);					// add Cover
			input.put("VINTAGECAR", "no");						// add Cover
			input.put("LEGALLIABILITYTOPAIDDRIVER", liabilityToPaidDriver);
			input.put("NoOfPassengerForLLToPaidDriver", NoPassToLLPaiddriver);		//this if LEGALLIABILITYTOPAIDDRIVER is obtaid
			
			input.put("LEGALLIABILITYTOEMPLOYEE", legalliabilityToEmployee);
			input.put("NoOfPassengerForLLToEmployee", NoOfPassengerForLLToEmployee);			

//this if LEGALLIABILITYTOEMPLOYEE is obtaid
			
			input.put("PAUNNNAMED", paUnnamed);
			input.put("NoOfPerunnamed", noOfPerunnamed);						

//this if PAUNNNAMED is obtaid
			input.put("UnnamedPASI", unnamedPaSi);						

	//this if PAUNNNAMED is obtaid
			
			
			input.put("PAOWNERDRIVER", paownerdriver);
			input.put("LIMTEDTOOWNPREMISE", limitedToOwnPremise);
			input.put("ELECTRICALACCESSORIES", "No");
			input.put("NONELECTRICALACCESSORIES", "No");
			
			input.put("PANAMED", "No");
			input.put("NoOfPernamed", "0");							

//this if PANAMED is obtaid
			input.put("NamedPASI", "0");								

//this if PANAMED is obtaid
			
			input.put("PATOPAIDDRIVER", "NO");					//add Cover
			input.put("EXTERNALFUELKIT", "No");	
			input.put("FuelSI", "100");	
			
			String fuelTyp="";
			if((str_fuelType.toUpperCase()).contains("LPG")){
				fuelTyp="LPG";
			}else if((str_fuelType.toUpperCase()).contains("CNG")){
				fuelTyp="CNG";
			}
			input.put("FUELTYPE", fuelTyp);
			
			input.put("ISTRAILERATTCHED", "No");			//add Cover
			input.put("FIBERGLASSTANKCOVER", fiberGlassTankCover);
			input.put("FiberGlassSI", fiberGlassSI);					//this if FIBERGLASSTANKCOVER is obtaid
			
			input.put("AAIMEMBSHIP", AAIMembship);
			input.put("ANTITHEFTDEVICE", antiTheftdevice);			//add Cover
			input.put("IsAntiTheftDeviceCertifiedByARAI", str_IsAntiTheftDeviceCertifiedByARAI);
			input.put("VEHICLEFORHANDICAP", vehicleForHandicap);
			input.put("TPPDDISCOUNT", tppdDiscount);
			input.put("FOREIGNEMBASSY", "No");
			input.put("VOLUNTARYEXCESS", "No");			//add Cover
			input.put("NOMINEERELATIONSHIP", str_NomineeRelationship);		
			input.put("REPRELATIONWITHMINOR", str_NomineeRelationship);
			input.put("ISINSPECTIONDONE", "false");			//data from prev policy
			input.put("INSPECTIONDONEBYWHOM", "");
			input.put("REPORTDATE", str_InspReportDate);
			input.put("INSPECTIONDATE", str_InspReportDate);				//data from prev policy
			input.put("GAPCOVER", "No");
			input.put("GAPCoverSI", "");					//this if GAPCOVER is obtaid
			input.put("ROADSIDEASSTCOVER", roadSideAsstCover);
			input.put("CONSUMABLECOVER", "");
			input.put("DEPRECIATIONCOVER", depriciationCover);
			input.put("ENGINESAFECOVER", "No");
			input.put("KEYLOSSCOVER", "No");
			input.put("PASSENGERASSTCOVER", "No");
			input.put("ENGINENO", str_EngineNo);
			input.put("CHASSISNO", str_Chassis);
			
			input.put("NoPreviousPolicyHistory", true);
			input.put("PreviousPolicyType", "PackagePolicy");
			input.put("ClaimAmount", preInsNcbAmount);
			input.put("NoOfClaims", preInsNoOfClaim);
			input.put("GICID", videoconIntBean.getMotorGroupResponseGicId());
			input.put("Company", "Liberty Videocon");
			input.put("productName",str_ProductName);
			input.put("FNAME", str_ForeName);
			input.put("LNAME",str_lastName );
			
			//AllCovers

			

//***************************************************************************************
			
			    String Make="";
			    String Model="";
			    String Accessory="";
			    String MrfYear=	"";
			    String SumInsured="";
			    
			System.out.println("electricalItem====>>"+electricalItem);
			System.out.println("nonelectricalItem====>>"+nonelectricalItem);
			
			if ((!electricalItem.isEmpty() && electricalItem != null)) {
				System.out.println("444444444444444444444444444444444444444444444");
				String[] eleItem = electricalItem.split("~");
				int length = eleItem.length;
				if (length > 0) {
					for (int size = 0; size < length; size++) {
						System.out
								.println("55555555555555555555555555555555555555555555555555");
						List lstcAccessoriesList = new ArrayList();
						HashMap lstAccessories = new HashMap<>();
						System.out.println("eleItem complete===>>" + eleItem);
						String electricalitem = eleItem[size];
						String[] EleItem = electricalitem.split(",");

						System.out.println(" EleItem----->" + EleItem[0]
								+ "eeeeeee" + EleItem[1]);
						String makeModel = EleItem[1];
						String MakeModel[] = makeModel.split(" ");

						System.out.println(" EleItem----->" + MakeModel[0]
								+ "eeeeeee" + MakeModel[1]);

						Make = MakeModel[0];
						Model = MakeModel[1];

						Accessory = EleItem[0];
						MrfYear = EleItem[2];
						SumInsured = EleItem[3];

						System.out.println("Accessory==>>" + Accessory);
						System.out.println("Make==>>" + Make);
						System.out.println("Model==>>" + Model);
						System.out.println("MrfYear==>>" + MrfYear);
						System.out.println("str_lstAccessories_SerialNo==>>"+ str_lstAccessories_SerialNo);
						System.out.println("SumInsured==>>" + SumInsured);

						lstAccessories.put("Description", Accessory);
						lstAccessories.put("Make", Make);
						lstAccessories.put("Model", Model);
						lstAccessories.put("ManufactureYear", MrfYear);
						lstAccessories.put("SerialNo",str_lstAccessories_SerialNo);
						lstAccessories.put("SumInsured", SumInsured);
						lstcAccessoriesList.add(lstAccessories);
						input.put("lstAccessories", lstcAccessoriesList);
					}
				}
			} else {
				System.out
						.println("6666666666666666666666666666666666666666666");
				List lstcAccessoriesList = new ArrayList();
				HashMap lstAccessories = new HashMap<>();
				lstAccessories.put("Description", Accessory);
				lstAccessories.put("Make", Make);
				lstAccessories.put("Model", Model);
				lstAccessories.put("ManufactureYear", MrfYear);
				lstAccessories.put("SerialNo", str_lstAccessories_SerialNo);
				lstAccessories.put("SumInsured", SumInsured);
				lstcAccessoriesList.add(lstAccessories);
				input.put("lstAccessories", lstcAccessoriesList);
			}

			String noneleMake = "";
			String noneleModel = "";

			String noneleAccessory = "";
			String noneleMrfYear = "";
			String noneleSumInsured = "";

			if ((!nonelectricalItem.isEmpty() && nonelectricalItem != null)) {
				System.out.println("7777777777777777777777777777777777777777777777");
				String[] noneleItem = nonelectricalItem.split("~");
				int length = noneleItem.length;
				if (length > 0) {
					for (int size = 0; size < length; size++) {
						String nonelectricalitem = noneleItem[size];
						String[] nonEleItem = nonelectricalItem.split(",");

						String makeModel = nonEleItem[1];
						String MakeModel[] = makeModel.split(" ");
						Make = MakeModel[0];
						Model = MakeModel[1];

						Accessory = nonEleItem[0];
						MrfYear = nonEleItem[2];
						SumInsured = nonEleItem[3];

						List lstNonElecAccessoriesList = new ArrayList();
						HashMap lstNonElecAccessories = new HashMap<>();
						lstNonElecAccessories.put("Description", Accessory);
						lstNonElecAccessories.put("Make", Make);
						lstNonElecAccessories.put("Model", Model);
						lstNonElecAccessories.put("ManufactureYear", MrfYear);
						lstNonElecAccessories.put("SerialNo",
								str_lstNonElecAccessories_SerialNo);
						lstNonElecAccessories.put("SumInsured", SumInsured);
						lstNonElecAccessoriesList.add(lstNonElecAccessories);
						input.put("lstNonElecAccessories",
								lstNonElecAccessoriesList);
					}
				}
			} else {
				System.out.println("88888888888888888888888888888888888888888888");
				List lstNonElecAccessoriesList = new ArrayList();
				HashMap lstNonElecAccessories = new HashMap<>();
				lstNonElecAccessories.put("Description", Accessory);
				lstNonElecAccessories.put("Make", Make);
				lstNonElecAccessories.put("Model", Model);
				lstNonElecAccessories.put("ManufactureYear", MrfYear);
				lstNonElecAccessories.put("SerialNo",str_lstNonElecAccessories_SerialNo);
				lstNonElecAccessories.put("SumInsured", SumInsured);
				lstNonElecAccessoriesList.add(lstNonElecAccessories);
				input.put("lstNonElecAccessories", lstNonElecAccessoriesList);
			}

			input.put("NoOfPernamed", str_NoOfPernamed);
			input.put("NamedPASI", str_NamedPASI);
			input.put("NoOfPaidDriverPassenger",str_NoOfPaidDriverPassenger);
			input.put("PAToPaidDriverSI", str_PAToPaidDriverSI);
//			input.put("FuelSI", str_FuelSI);
			List lstTrailerList= new ArrayList();
			HashMap lstTrailer = new HashMap<>();
			lstTrailer.put("ChassisNumber", str_lstTrailer_ChassisNumber);
			lstTrailer.put("EngineNumber",str_lstTrailer_EngineNumber);
			lstTrailer.put("SumInsured",str_lstTrailer_SumInsured);
			lstTrailerList.add(lstTrailer);
			input.put("lstTrailer", lstTrailerList);
			
			
//			input.put("FiberGlassSI", str_FiberGlassSI);
			input.put("AAIMembshipNumber", str_AAIMembshipNumber);
			input.put("AAIAssociationCode", str_AAIAssociationCode);
			input.put("AAIAssociationName", str_AAIAssociationName);
			input.put("AAIMembshipExpiryDate", str_AAIMembshipExpiryDate);
			
			input.put("VoluntaryExcessAmt", str_VoluntaryExcessAmt);
			input.put("NoNomineeDetails",str_NoNomineeDetails);
			input.put("NomineeFirstName", str_NomineeFirstName);
			input.put("NomineelastName", str_NomineelastName);
			input.put("NomineeRelationship",str_NomineeRelationship);
			input.put("OtherRelation",str_OtherRelation);
			input.put("IsMinor", str_IsMinor);
			input.put("RepFirstName",str_RepFirstName);
			input.put("RepLastName", str_RepLastName);
			input.put("RepOtherRelation",str_RepOtherRelation);
//			input.put("GAPCover","Yes");
//			input.put("GAPCoverSI","1000");
//			input.put("KeyLossCover", "Yes");
//			input.put("KeyLossCoverSI", "200");
			input.put("IsFinancierDetails", str_IsVehicleHypothicated);
			input.put("AgreementType", str_AgreementType);
			input.put("FinancierName", str_FinancierName);
			input.put("FinancierAddress", str_FinancierAddress);
			
			HashMap cust = new HashMap<>();
			HashMap custmerObj = new HashMap<>();
			custmerObj.put("TPSource",str_TPSource);
			custmerObj.put("CustomerType",str_CustType);
			custmerObj.put("Salutation",str_Salutation);
			custmerObj.put("FirstName",str_ForeName);
			custmerObj.put("LastName", str_lastName);
			custmerObj.put("DOB",str_DOB);
			custmerObj.put("EmailId",str_EmailID);
			custmerObj.put("MobileNumber", str_MobileNo);
			custmerObj.put("AddressLine1",str_CLD_CA_Address1);
			custmerObj.put("AddressLine2",str_CLD_CA_Address2);
			custmerObj.put("AddressLine3",str_CLD_CA_Address3);
			custmerObj.put("PinCode",str_CLD_CA_Pincode);
			custmerObj.put("PinCodeLocality",str_PinCodeLocality);
			custmerObj.put("PanNo",str_PANno);
			custmerObj.put("MailingAddressLine1",str_CLD_PA_Address1);
			custmerObj.put("MailingPinCode",str_CLD_PA_Pincode);
			custmerObj.put("MailingPinCodeLocality",str_MailingPinCodeLocality);
			custmerObj.put("PermanentLocationSameAsMailLocation", str_PermanentLocationSameAsMailLocation);
			custmerObj.put("IsEIAAvailable",str_IsEIAAvailable);
			custmerObj.put("EIAAccNo", str_EIAAccNo);
			custmerObj.put("IsEIAPolicy",str_IsEIAPolicy);
			custmerObj.put("EIAAccWith", str_EIAAccWith);
			custmerObj.put("EIAPanNo", str_EIAPanNo);
			custmerObj.put("EIAUIDNo", str_EIAUIDNo);
			custmerObj.put("GSTIN", str_GSTIN);
			
			cust.put("CustmerObj", custmerObj);
			
			/////////////////////////////////////
			
			             
			//////////////////////////////////////
		    Gson gson = new Gson();
	        String json = gson.toJson(input);
	        System.out.println("json additional parameters = " + json);
	        String json1 = gson.toJson(cust);
	        System.out.println("json1 = " + json1);
	        
	        //************************************
	        
	        
	        String result=  json+""+jsonNames +""+json1;
	        System.out.println("result Befor Removing brackets = " + result);
	       
	        if(result.contains("}[{")){
		 	       result = result.replace("}[{", ",");
		 	      }
	        if(result.contains("}]{")){
	 	       result = result.replace("}]{", ",");
	 	      }
		  
	   
	       System.out.println("result After Removing brackets = " + result);
			
		
	              
	s = PostInfoToAPI(result, method);
	System.out.println("Final hear........................");
	//******************************************************
	//Code For save data of proposal In Database
	
	System.out.println("videoconIntBean.getMotorGroupResponseGicId()" + videoconIntBean.getMotorGroupResponseGicId());
	if((videoconIntBean.getMotorGroupResponseGicId()!=null)){
	HashMap<String, String> hashmapVdi = new HashMap<>();
	String str = s.substring(1, s.length()-1);           //remove curly brackets
	String[] keyValuePairs = str.split(",");              //split the string to creat key-value pairs

	for(String pair : keyValuePairs)                        //iterate over the pairs
	{
	    String[] entry = pair.split(":");                   //split the pairs to get key and value 
	    hashmapVdi.put((entry[0].trim()).replaceAll("\"", ""), (entry[1].trim()).replaceAll("\"", ""));   
	}
	

		bean.setUserId(videoconIntBean.getUserId());
		bean.setUserDesc(videoconIntBean.getUserDesc());
		bean.setBranchId(videoconIntBean.getBranchId());
		bean.setGroupId(videoconIntBean.getMotorGroupResponseGroupId());
		System.out.println(bean.getGroupId());
		bean.setSessionId(videoconIntBean.getMotorGroupResponseSessionId());
		bean.setGicId(videoconIntBean.getMotorGroupResponseGicId());
		bean.setResponseType("1");
		bean.setIpAddress(videoconIntBean.getIPAddress());

//		MotorResponseBean objResult = integrationSaveResponseDao
//				.saveRelianceProposalDataDump(hashmapVdi, bean);
//
//		MotorResponseBean objResult1 = integrationSaveResponseDao
//				.saveRelianceProposalDataProcess(bean);
		
		Iterator vidItr = hashmapVdi.entrySet().iterator();
		String errorText="";
		while (vidItr.hasNext()) {
			Map.Entry entry = (Map.Entry) vidItr.next();
			String mapKey = (String)entry.getKey();
				if (mapKey.equalsIgnoreCase("ErrorText")) {
					errorText = (null == entry.getValue()) ? "" : entry.getValue().toString();
					System.out.println("errorText>>>"+ errorText);
				}
			}
		if(errorText.equalsIgnoreCase("null")){
		if(request_for.equalsIgnoreCase("premium")){
		
			String procedureName = "PR_PREMIUM";
			System.out.println("MotorGroupResponseSessionId:" +videoconIntBean.getMotorGroupResponseSessionId());
			System.out.println("getUserId:" +videoconIntBean.getUserId());
			System.out.println("getUserDesc:" +videoconIntBean.getUserDesc());
			System.out.println("getBranchId:" +videoconIntBean.getBranchId());
			System.out.println("getMotorGroupResponseGroupId:" +videoconIntBean.getMotorGroupResponseGroupId());
			System.out.println("getMotorGroupResponseGicId:" +videoconIntBean.getMotorGroupResponseGicId());
			System.out.println("getIPAddress:" +videoconIntBean.getIPAddress());
			
			Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(videoconIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(videoconIntBean.getMotorGroupResponseGicId()), videoconIntBean.getMotorGroupResponseSessionId(),
					1, hashmapVdi, videoconIntBean.getIPAddress(), videoconIntBean.getUserId(), videoconIntBean.getBranchId(),
					videoconIntBean.getUserDesc(), procedureName);
		}
		else if(request_for.equalsIgnoreCase("proposal")){
			String procedureName = "PR_PROPOSAL";
			System.out.println("MotorGroupResponseSessionId:" +videoconIntBean.getMotorGroupResponseSessionId());
			System.out.println("getUserId:" +videoconIntBean.getUserId());
			System.out.println("getUserDesc:" +videoconIntBean.getUserDesc());
			System.out.println("getBranchId:" +videoconIntBean.getBranchId());
			System.out.println("getMotorGroupResponseGroupId:" +videoconIntBean.getMotorGroupResponseGroupId());
			System.out.println("getMotorGroupResponseGicId:" +videoconIntBean.getMotorGroupResponseGicId());
			System.out.println("getIPAddress:" +videoconIntBean.getIPAddress());
			Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(videoconIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(videoconIntBean.getMotorGroupResponseGicId()), videoconIntBean.getMotorGroupResponseSessionId(),
					1, hashmapVdi, videoconIntBean.getIPAddress(), videoconIntBean.getUserId(), videoconIntBean.getBranchId(),
					videoconIntBean.getUserDesc(), procedureName);
			}

	}
	}
	
	
	
	
	s= s + result;
	System.out.println("json response=============>>"+result);
	if(s.contains("}{")){
		s = s.replace("}{", ",");
 	 }
	
	/*if(s.contains("}}")){
		s = s.replace("}}", "}");
	}*/
    System.out.println("s = " + s);
	 newValue= "[" + s + "]";
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("server response=============>>"+s);
		System.out.println("combine response=============>>"+newValue);
		return newValue;

	}
	
	@RequestMapping(value = "/TransactionStatus", method = RequestMethod.POST)
	public org.springframework.web.servlet.ModelAndView TransactionStatusPost(
			HttpServletRequest request, HttpServletResponse response)
			throws JSONException {
		String jsonResponse = null;

		String mihpayid = request.getParameter("mihpayid");
		String amount = request.getParameter("amount");
		String PG_TYPE = request.getParameter("PG_TYPE");
		String error_code = request.getParameter("error_code");
		String txnid = request.getParameter("txnid");
		String status = request.getParameter("status");
		String bank_ref_num = request.getParameter("bank_ref_num");
		System.out.println("mihpayid-" + mihpayid + "amount-" + amount
				+ "PG_TYPE" + PG_TYPE + "error_code" + error_code);
		System.out.println("txnid-" + txnid + "status-" + status
				+ "bank_ref_num" + bank_ref_num);

		HashMap<String, String> payResponse = new HashMap<>();
		payResponse.put("mihpayid", mihpayid);
		payResponse.put("amount", amount);
		payResponse.put("PG_TYPE", PG_TYPE);
		payResponse.put("error_code", error_code);
		payResponse.put("txnid", txnid);
		payResponse.put("status", status);
		payResponse.put("bank_ref_num", bank_ref_num);
		bean.setUserId(videoconIntBean.getUserId());
		bean.setUserDesc(videoconIntBean.getUserDesc());
		bean.setBranchId(videoconIntBean.getBranchId());
		bean.setGroupId(videoconIntBean.getMotorGroupResponseGroupId());
		bean.setSessionId(videoconIntBean.getMotorGroupResponseSessionId());
		bean.setGicId(videoconIntBean.getMotorGroupResponseGicId());
		bean.setResponseType("2");
		bean.setIpAddress(videoconIntBean.getIPAddress());
		System.out.println("payResponse---" + payResponse);
		if (status.equalsIgnoreCase("success")) {
			policyCreation(payResponse,videoconIntBean);
		} else {
			payResponse.put("customerID", customerID);
			payResponse.put("QuotationNumber", quotationNumber);
			payResponse.put("proposalNo", quotationNumber);

			String procedureName = "PR_PROPOSAL";
			Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(videoconIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(videoconIntBean.getMotorGroupResponseGicId()), videoconIntBean.getMotorGroupResponseSessionId(),
					2, payResponse, videoconIntBean.getIPAddress(), videoconIntBean.getUserId(), videoconIntBean.getBranchId(),
					videoconIntBean.getUserDesc(), procedureName);
		}

		System.out.println("In transaction status");
		org.springframework.web.servlet.ModelAndView andView = new 

org.springframework.web.servlet.ModelAndView(
				"common/TransactionStatus");
		andView.addObject("data", payResponse);
		return andView;

	}

	
	@RequestMapping(value = "user/videoconPayment" ,method = RequestMethod.GET)
	public org.springframework.web.servlet.ModelAndView videoconPayment(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String paymentResponse = null;
		videoconIntBean = new VideoconIntBean();
			String sqlMstId= request.getParameter("sqlMstId");
			System.out.println(sqlMstId);
			String param= request.getParameter("param");
			System.out.println(param);
			param = "'"+ param +"'";
			String result = masterDao.getRecordList(sqlMstId, param, null, null,
					null, null, "1", "11", null);
			
			System.out.println("JSON DATA:" + result);
			
			JSONArray arrobj = new JSONArray(result);
			String txnid= "";
			for (int table = 0; table < arrobj.length(); ++table) {
		    try {
		    	  org.json.JSONObject obj = (org.json.JSONObject) arrobj.get(table);
				if (!obj.isNull("(NVL(\u0027LVGI\u0027,\u0027\u0027)||TO_CHAR(SEQ_LVGI_TRANS_NO.NEXTVAL))")) {
		    	txnid = "" + obj.getString("(NVL(\u0027LVGI\u0027,\u0027\u0027)||TO_CHAR(SEQ_LVGI_TRANS_NO.NEXTVAL))");
		    	System.out.println("txnid" + txnid);
				}
		    }catch (Exception e) {
				// TODO: handle exception
				  e.printStackTrace();
			}
			}
			String amount= request.getParameter("amount");
			String productinfo= request.getParameter("productinfo");
			String SURL= request.getParameter("SURL");
			String FURL= request.getParameter("FURL");
			String key= request.getParameter("key");
			String FirstName= request.getParameter("FirstName");
			Email = request.getParameter("Email");
			String Phone= request.getParameter("Phone");
			quotationNumber= request.getParameter("quotationNumber");
			customerID= request.getParameter("customerID");
			String actionUrl = "http://115.112.62.214:7070/Home/CapturePayment";
			videoconIntBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
			videoconIntBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
			videoconIntBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
			videoconIntBean.setUserId(request.getParameter("userId"));
			videoconIntBean.setUserDesc(request.getParameter("userDesc"));
			videoconIntBean.setBranchId(request.getParameter("branchId"));
			
			HashMap<String,String> payRequest=new HashMap<>();
			payRequest.put("txnid", txnid);
			payRequest.put("amount", amount);
			payRequest.put("productinfo", productinfo);
			payRequest.put("SURL", SURL);
			payRequest.put("FURL", FURL);
			payRequest.put("key", key);
			payRequest.put("FirstName", FirstName);
			payRequest.put("Email", Email);
			payRequest.put("Phone", Phone);
			payRequest.put("quotationNumber", quotationNumber);
			payRequest.put("customerID", customerID);
			payRequest.put("actionUrl", actionUrl);
			
			System.out.println("payRequest" +payRequest);
	        ModelAndView model = new ModelAndView("common/videoconPayment");
	        model.addObject("data", payRequest);
	        return model;
			 
	
	}
	
	private void policyCreation(HashMap payResp,VideoconIntBean videoconIntBean) throws JSONException {
		Iterator entries = payResp.entrySet().iterator();
		String mihpayid = "", amount = "", PG_TYPE = "", error_code = "", txnid = "", status = "", bank_ref_num 

= "";
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			String mapKey = (String) entry.getKey();
			if (mapKey.equalsIgnoreCase("mihpayid")) {
				mihpayid = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
			}
			if (mapKey.equalsIgnoreCase("amount")) {
				amount = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
			}
			if (mapKey.equalsIgnoreCase("PG_TYPE")) {
				PG_TYPE = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();

			}
			if (mapKey.equalsIgnoreCase("error_code")) {
				error_code = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
			}
			if (mapKey.equalsIgnoreCase("txnid")) {
				txnid = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
			}
			if (mapKey.equalsIgnoreCase("status")) {
				status = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
			}
			if (mapKey.equalsIgnoreCase("bank_ref_num")) {
				bank_ref_num = (null == entry.getValue()) ? "" : entry
						.getValue().toString();
			}
		}
		JSONObject json = new JSONObject();
		try {

			json.put("QuotationNumber", quotationNumber);
			json.put("TPSourceName", "HopeBox Insurance Broking Pvt Ltd");
			json.put("PremiumAmount", amount);
			json.put("TransactionID", txnid);
			json.put("SendEmailtoCustomer", "true");
			json.put("TPEmailID", Email);
			json.put("PaymentSource", "LVGI-PAYU");
			json.put("OTP", "0");
			json.put("OTPCreatedDate", "14/12/2017 00:00:00");
			json.put("OTPEnteredDate", "14/12/2017 00:00:00");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		method = "IssuePolicy";
		String s = PostInfoToAPI(json, method);
		System.out.println("string--" + s);
		String jsonInput = json.toString();

		HashMap<String, String> map1 = GetHashmap(s);
		HashMap<String, String> map2 = GetHashmap(jsonInput);
		map2.put("mihpayid", mihpayid);
		map2.put("amount", amount);
		map2.put("PG_TYPE", PG_TYPE);
		map2.put("error_code", error_code);
		map2.put("status", status);
		map2.put("bank_ref_num", bank_ref_num);
		map2.put("customerID", customerID);
		map1.put("proposalNo", quotationNumber);

		HashMap<String, String> saveMap = new HashMap<>();
		saveMap.putAll(map1);
		saveMap.putAll(map2);

		String procedureName = "PR_PROPOSAL";
		Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(videoconIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(videoconIntBean.getMotorGroupResponseGicId()), videoconIntBean.getMotorGroupResponseSessionId(),
				2, saveMap, videoconIntBean.getIPAddress(), videoconIntBean.getUserId(), videoconIntBean.getBranchId(),
				videoconIntBean.getUserDesc(), procedureName);
		
	}
	
	public HashMap<String,String> GetHashmap(String s) throws JSONException
	{
		 HashMap<String, String> map = new HashMap<String, String>();
	        JSONObject jObject = new JSONObject(s);
	        Iterator<?> keys = jObject.keys();

	        while( keys.hasNext() ){
	            String key = (String)keys.next();
	            String value = jObject.getString(key); 
	            map.put(key, value);

	        }
		return map;
	}
	
private String PostInfoToAPI(Object obj, String MethodName) {
		String str = null;

		try {
			// http://168.87.83.122:8180/api/IMDTPService/{Method Name}
		URL url = new URL("http://168.87.83.122:8180/api/IMDTPService/" +MethodName);
//	URL url = new URL("https://partner.libertyvideocon.com/AVOGI/Motor/OpportunityMain/" +MethodName);
//			URL url = new URL("http://115.112.62.214:1761/CustomerPAYU_Test/CustPayU.aspx");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			String request = obj.toString();
			System.out.println(request);
			byte[] data = request.getBytes("UTF-8");
			str = new String(data);
			System.out.println(" byte[] " + str);
			
			OutputStream outputStream = connection.getOutputStream();
		
			outputStream.write(data, 0, data.length);
			System.out.println(" outputStream " + outputStream);
			outputStream.close();
			System.out.println("connection-----" + connection);
		
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			System.out.println("in==>" + in);
			String inputLine;
			StringBuffer response = new StringBuffer();
			// System.out.println("RESPONSE==>" + response);
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println("RESPONSE==>" + response);
			in.close();
			str = response.toString();
			System.out.println("String=====>" + str);
			
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

@RequestMapping(value = "/VidpaymentResult", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
public @ResponseBody void getVidpaymentResult(HttpServletRequest request,
		HttpServletResponse response) throws JDOMException, IOException {
			System.out.println("In VidpaymentResult");
			String PayUID = request.getParameter("PayUID");
			String amount = request.getParameter("amount");
			String gatewayType = request.getParameter("gatewayType");
			String errorCode = request.getParameter("errorCode");
			String transId = request.getParameter("transId");
			String status = request.getParameter("status");
			String bankRefNo = request.getParameter("bankRefNo");
			System.out.println("PayUID " +PayUID);
			System.out.println("amount " +amount);
			System.out.println("gatewayType " +gatewayType);
			System.out.println("errorCode " +errorCode);
			System.out.println("transId " +transId);
			System.out.println("status " +status);
			System.out.println("bankRefNo " +bankRefNo);
			
			
	
}

}





