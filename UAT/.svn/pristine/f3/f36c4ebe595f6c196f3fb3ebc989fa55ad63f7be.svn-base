package com.uat.hbc.insurance.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import com.google.gson.Gson;
import com.shreeRamGIC.ArrayOfCoverDtl;
import com.shreeRamGIC.AuthHeader;
import com.shreeRamGIC.CoverDtl;
import com.shreeRamGIC.MPCProposalEntryETT;
import com.shreeRamGIC.MPCProposalResponseETT;
import com.shreeRamGIC.PolicyApprovalETT;
import com.shreeRamGIC.PolicyApprovalResponseETT;
import com.shreeRamGIC.ShriramService;
import com.shreeRamGIC.ShriramServiceSoap;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.insurance.dao.IntegrationShreeRamGICDao;
import com.uat.hbc.insurance.model.MotorCalculatorShreeRamGICBean;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;


@Controller
public class MotorCalculatorShreeRamGICController {
	
	
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;
	@Autowired
	IntegrationShreeRamGICDao shreeRamGICDao;
	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;
	
      String path=System.getProperty("user.home");
	  String  methodName="";
	  
	  HashMap<String, String> hashmap ;
	  HashMap extraParaMap;

		
		MotorCalculatorShreeRamGICBean shreeRamBean;
	  


	@RequestMapping("user/motorCalculatorShreeRam")
		public ModelAndView callJSP(HttpServletRequest req,HttpServletResponse res) {
			ModelAndView model = new ModelAndView();
			model.setViewName("admin/motorCalculatorShreeRam");
			return model;
		}
	  
	  public MotorCalculatorShreeRamGICController(){
		hashmap = new HashMap<>();
		shreeRamBean =new MotorCalculatorShreeRamGICBean();
		extraParaMap = new HashMap<>();
	  }
	  
		
		@RequestMapping(value = "user/motorCalculatorShreeRam", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public @ResponseBody String ShreeRamGICController(HttpServletRequest request,HttpServletResponse res) throws JsonGenerationException, JsonMappingException, IOException {
		 String jsonNames = "" , json="";
		 shreeRamBean =new MotorCalculatorShreeRamGICBean();
		 hashmap = new HashMap<>();
		 extraParaMap = new HashMap<>();
		try {
			String pkg_name = request.getParameter("pkg_name");
			System.out.println("pkg_name==>" + pkg_name);
			String proc_name = request.getParameter("procName");
			System.out.println("proc_name==>" + proc_name);
			String table_name = request.getParameter("table_name");
			System.out.println("table_name==>" + table_name);
			System.out.println("reqFfor==>" + request.getParameter("request_for"));
			String reqFor=request.getParameter("request_for");
			shreeRamBean.setRequest_for(reqFor);
			System.out.println("reqFor==>" + reqFor);
			System.out.println("reqFforBean==>" + shreeRamBean.getRequest_for());
			String clienttype = request.getParameter("clienttype");
			String rtoCityId = request.getParameter("rtoCityId");
			String zoneId = request.getParameter("zoneId");
			String policyType = request.getParameter("policyType");
			String varID = request.getParameter("varID");
			String businessType = request.getParameter("businessType");
			String productID = request.getParameter("productID");
			String vehID = request.getParameter("vehID");
			String modelID = request.getParameter("modelID");
			String occupationID = request.getParameter("occupationID");
			String finId = request.getParameter("finId");
			String prevGICID = request.getParameter("prevGICID");
			String areaId = request.getParameter("areaId");
			String nomRel = request.getParameter("nomRel");
			String nomRelRep = request.getParameter("nomRelRep");
			String ownDriAppointeeRel = request.getParameter("ownDriAppointeeRel");
			String covers = request.getParameter("covers");
			String coverVal = request.getParameter("coverVal");
			String coverNo = request.getParameter("coverNo");
			
			String prevProduct = request.getParameter("PrevProduct");
			String prevPolicyType = request.getParameter("PrevPolicyType");
			String finAgrementType = request.getParameter("FinAgrementType");
			String bodyType = request.getParameter("BodyType");
			String presentAreaId = request.getParameter("PresentAreaId");
			String permantAreaId = request.getParameter("PermantAreaId");
			
			System.out.println("1111111111111111111************************");
			HashMap inputParaList = new HashMap<>();
			
			if((!reqFor.equalsIgnoreCase("PolicyApprove"))&&(!reqFor.equalsIgnoreCase("policyScheduleURL"))){
				System.out.println("222222222222222222222************************");
			inputParaList.put("PI_TABLE_NAME", table_name);
			inputParaList.put("PI_Rto_City", rtoCityId);
			inputParaList.put("PI_ZONE_ID", zoneId);
			inputParaList.put("PI_PolicyType", policyType);
			inputParaList.put("PI_VarID", varID);
			inputParaList.put("PI_P_BusinessType", businessType);
			inputParaList.put("PI_ProductID", productID);
			inputParaList.put("PI_VehID", vehID);
			inputParaList.put("PI_ModelID", modelID);
			inputParaList.put("PI_CUST_TYPE", clienttype);
			inputParaList.put("PI_FIN_ID", finId);
			inputParaList.put("PI_PREV_GIC_ID", prevGICID);
			inputParaList.put("PI_Nom_Rel", nomRel);
			inputParaList.put("PI_Prev_Product", "");
			inputParaList.put("PI_Prev_PolicyType", "");
			inputParaList.put("PI_FIN_AgreementType", "");
			inputParaList.put("PI_Bodytype", "");
			inputParaList.put("PI_Present_AreaID", "");
			inputParaList.put("PI_Permanent_AreaID", "");
			inputParaList.put("PI_posPolicyNo", "");
			inputParaList.put("PI_SalutaionID", "");
			inputParaList.put("PI_Covers", covers);
			inputParaList.put("PI_COV_VAL", coverVal);
			inputParaList.put("PI_COV_NO", coverNo);

			System.out.println("inputParaList::<>" +inputParaList );
			
			jsonNames = shreeRamGICDao.getShreeRamGICGData("PKG_MOTOR_CALC", proc_name, inputParaList);
			
			System.out.println("jsonNames:: " +jsonNames);
			
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
			
			
			if(!coverList.equals("")){
				String coverId="",coverName="",coverNumber="",coverValue="";
				JSONArray coverJson = new JSONArray(coverList);
				for (int table = 0; table < coverJson.length(); ++table) {
			            org.json.JSONObject obj = (org.json.JSONObject) coverJson.get(table);
			            String gicId = "", gicCompany = "";
			            if (!obj.isNull("HB_COVER_ID")) {
			                coverId = "" + obj.getInt("HB_COVER_ID");
			                System.out.println("1111111111 coverName==>>"+coverId);
			            }
			            if (!obj.isNull("GIC_COVER_NAME")) {
			                coverName = "" + obj.getString("GIC_COVER_NAME");
			                System.out.println("1111111111 coverName==>>"+coverName);
			            }
			            if (!obj.isNull("COV_NO")) {
			            	coverNumber = "" + obj.getInt("COV_NO");
			                System.out.println("1111111111 coverNo==>>"+coverNo);
			            }
			            if (!obj.isNull("COVER_VAL")) {
			            	coverValue = "" + obj.getString("COVER_VAL");
			                System.out.println("1111111111 coverVal==>>"+coverVal);
			            }
			            
			            //Note = All Covers Not Set
			            
			            if (coverId.equalsIgnoreCase("2")) {
			            	shreeRamBean.setNonElectricalaccessRemarks("");
			            	shreeRamBean.setNonElectricalaccessSI(coverValue);
			            	shreeRamBean.setNonElectricalaccessYN("1");
						}
			            
			            if (coverId.equalsIgnoreCase("9")) {
			            	shreeRamBean.setElectricalaccessRemarks("");
			            	shreeRamBean.setElectricalaccessSI(coverValue);
			            	shreeRamBean.setElectricalaccessYN("1");
			            }
			            
			            if (coverId.equalsIgnoreCase("104")) {	//VoluntaryExcess
						}
			            
			            if (coverId.equalsIgnoreCase("105")) {
			            	shreeRamBean.setcNGKitYN("1");
			            	shreeRamBean.setcNGKitSI(coverValue);
			            }
			            
			            if (coverId.equalsIgnoreCase("54")) {
			            	shreeRamBean.setpAforUnnamedPassengerYN("1");
			            	shreeRamBean.setpAforUnnamedPassengerSI(coverValue);
						}
			            
			            if (coverId.equalsIgnoreCase("52")) {
			            	shreeRamBean.setLimitedTPPDYN("1");
						}
			            //PA Owner Driver Remaining
			            if (coverId.equalsIgnoreCase("")) {
			            	shreeRamBean.setAppointeeNameforPAOwnerDriver("");
			            	shreeRamBean.setAppointeeRelationforPAOwnerDrive("");
			            	shreeRamBean.setNomineeAgeforPAOwnerDriver("");
			            	shreeRamBean.setNomineeNameforPAOwnerDriver("");
			            	shreeRamBean.setNomineeRelationforPAOwnerDriver("");
			            	shreeRamBean.setpAOwnerDriverExclusion("");
			            	shreeRamBean.setpAOwnerDriverExReason("");
						}
			            
			            if (coverId.equalsIgnoreCase("21")) {
			            	shreeRamBean.setAntiTheftYN("1");
						}
			            
			            if (coverId.equalsIgnoreCase("56")) {
			            	shreeRamBean.setlLtoPaidDriverYN("1");//Incomplete
						}
			            
			            if (coverId.equalsIgnoreCase("8")) {
			            	shreeRamBean.setIsDepDeductWaiver("N");
			            	shreeRamBean.setNilDepreciationCoverYN("1");
						}
			            
			            if (coverId.equalsIgnoreCase("18")) {
			            	shreeRamBean.setLimitOwnPremiseYN("1");
						}
			            
			            if (coverId.equalsIgnoreCase("")) {		//Geo Extension
			            	shreeRamBean.setGeoCountryList(coverValue);
						}
				}
			}
			
			}
			System.out.println("33333333333333333333************************");
			String premiumFromProposal = request.getParameter("premiumFromProposal");
			String strRTOCode = request.getParameter("strRTOCode");
			String strFirstRegDt = request.getParameter("strFirstRegDt");
			String strInsuredState = request.getParameter("strInsuredState");
			String strPrevPolExpDt = request.getParameter("strPrevPolExpDt");
			String strPrevPolClaimYN = request.getParameter("strPrevPolClaimYN");
			String strPrevPolNCB = request.getParameter("strPrevPolNCB");
			String adharEnrollNo = request.getParameter("adharEnrollNo");
			String adharNo = request.getParameter("adharNo");
			String addonPackage = request.getParameter("addonPackage");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String address3 = request.getParameter("address3");
			String isAntiTheft = "";
			String appointeeNameforPAOwnerDriver = request.getParameter("appointeeNameforPAOwnerDriver");
			String appointeeRelationforPAOwnerDriver = request.getParameter("appointeeRelationforPAOwnerDriver");
			String breakIn = request.getParameter("breakIn");
			String chassisNo = request.getParameter("chassisNo");
			String city = request.getParameter("city");
			String CNGKitSI = "";
			String coverNoteDt = request.getParameter("coverNoteDt");
			String coverNoteNo = request.getParameter("coverNoteNo");
			String isDailyExpRem = "";
			String dob = request.getParameter("dob");
//			String isDepDeductWaiver = "";
			String DeTariff = request.getParameter("DeTariff");
			String isDriverAge = request.getParameter("isDriverAge");
			String ElectricalaccessRemarks = request.getParameter("ElectricalaccessRemarks");
			String isElectricalaccess = "";
			String ElectricalaccessSI = "";
			String EMailID = request.getParameter("EMailID");
			String EngineNo = request.getParameter("EngineNo");
			String FaxNo = request.getParameter("FaxNo");
			String FirstRegDt = request.getParameter("FirstRegDt");
			String Form16 = request.getParameter("Form16");
			String Gender = request.getParameter("Gender");
			String GSTNo = request.getParameter("GSTNo");
			String HypothecationAddress1 = request.getParameter("HypothecationAddress1");
			String HypothecationAddress2 = request.getParameter("HypothecationAddress2");
			String HypothecationAddress3 = request.getParameter("HypothecationAddress3");
			String HypothecationAgreementNo = request.getParameter("HypothecationAgreementNo");
			String HypothecationBankName = request.getParameter("HypothecationBankName");
			String HypothecationCity = request.getParameter("HypothecationCity");
			String HypothecationCountry = request.getParameter("HypothecationCountry");
			String HypothecationPinCode = request.getParameter("HypothecationPinCode");
			String HypothecationState = request.getParameter("HypothecationState");
			String HypothecationType = request.getParameter("HypothecationType");
			String IDVOfVehicle = request.getParameter("IDVOfVehicle");
			String isInBuiltCNGKit = request.getParameter("isInBuiltCNGKit");
			String InsuredName = request.getParameter("InsuredName");
			String InsuredPrefix = request.getParameter("InsuredPrefix");
			String isInvReturn = request.getParameter("isInvReturn");
			String isKeyReplacement ="";
			String isLimitedTPPD ="";
			String isLimitOwnPremise ="";
			String isLLtoPaidDriver ="";
			String isLossOfPersonBelong ="";
			String MobileNo = request.getParameter("MobileNo");
			String isMultiCarBenefit = "";
			String NilDepreciationCover = "";
			String NoEmpCoverLL = request.getParameter("NoEmpCoverLL");
			String NomineeAgeforPAOwnerDriver = request.getParameter("NomineeAgeforPAOwnerDriver");
			String NomineeNameforPAOwnerDriver = request.getParameter("NomineeNameforPAOwnerDriver");
			String NomineeRelationforPAOwnerDriver = request.getParameter("NomineeRelationforPAOwnerDriver");
			String NonElectricalaccessRemarks = request.getParameter("NonElectricalaccessRemarks");
			String NonElectricalaccessSI = "";
			String isNonElectricalaccess = "";
			String PAforUnnamedPassengerSI = "";
			String isPAforUnnamedPassenger = "";
			String PanNo = request.getParameter("PanNo");
			String PAOwnerDriverExclusion = request.getParameter("PAOwnerDriverExclusion");
			String PAOwnerDriverExReason = request.getParameter("PAOwnerDriverExReason");
			String PAPaidCleanerCount = request.getParameter("PAPaidCleanerCount");
			String PAPaidConductorCount = request.getParameter("PAPaidConductorCount");
			String PAPaidDriverConductorCleanerSI = request.getParameter("PAPaidDriverConductorCleanerSI");
			String isPAPaidDriverConductorCleaner = request.getParameter("isPAPaidDriverConductorCleaner");
			String PAPaidDriverCount = "";
			String PinCode = request.getParameter("PinCode");
//			String PolicyFromDt = request.getParameter("PolicyFromDt");
//			String PolicyIssueDt = request.getParameter("PolicyIssueDt");
//			String PolicyToDt = request.getParameter("PolicyToDt");
			String PolicyType = request.getParameter("PolicyType");
			String POSAgentName = request.getParameter("POSAgentName");
			String POSAgentPanNo = request.getParameter("POSAgentPanNo");
			String PreInspection = request.getParameter("PreInspection");
			String isPreInspectionReport = request.getParameter("isPreInspectionReport");
			String PreviousInsurer = request.getParameter("PreviousInsurer");
			String PreviousNilDepreciation = request.getParameter("PreviousNilDepreciation");
			String isPreviousPolicyClaim = request.getParameter("isPreviousPolicyClaim");
			String PreviousPolicyFromDt = request.getParameter("PreviousPolicyFromDt");
			String PreviousPolicyNCBPerc = request.getParameter("PreviousPolicyNCBPerc");
			String PreviousPolicyNo = request.getParameter("PreviousPolicyNo");
			String PreviousPolicySI = request.getParameter("PreviousPolicySI");
			String PreviousPolicyToDt = request.getParameter("PreviousPolicyToDt");
			String PreviousPolicyType = request.getParameter("PreviousPolicyType");
			String PreviousPolicyUWYear = request.getParameter("PreviousPolicyUWYear");
			String ProdCode = request.getParameter("ProdCode");
			String proposalType = request.getParameter("PROPOSALTYPE");//
			String referenceNo = request.getParameter("ReferenceNo");
			String regNo1 = request.getParameter("regNo1");
			String regNo2 = request.getParameter("regNo2");
			String regNo3 = request.getParameter("regNo3");
			String regNo4 = request.getParameter("regNo4");
			String rtoCode = request.getParameter("rtoCode");
			String SpecifiedPersonField = request.getParameter("SpecifiedPersonField");
			String TelephoneNo = request.getParameter("TelephoneNo");
			String VehiclePurpose = request.getParameter("VehiclePurpose");
			String VehicleType = request.getParameter("VehicleType");
			String voluntaryExcess = "";
			String isgeoExt = request.getParameter("isgeoExt");

//			String geoCountryList = request.getParameter("geoCountryList");
			String proposalNo = request.getParameter("proposalNoPolicyApp");
			String transactionNo = request.getParameter("TransactionNo");
			String cardNo = request.getParameter("CardNo");
			String cardHolderName = request.getParameter("CardHolderName");
			String cardType = request.getParameter("CardType");
			String cardValidUpto = request.getParameter("cardValidUpto");
			String bankName = request.getParameter("BankName");
			String branchName = request.getParameter("BranchName");
			String paymentType = request.getParameter("paymentType");
			String chequeType = request.getParameter("ChequeType");
			String chequeClearType = request.getParameter("ChequeClearType");
			String cashtype = request.getParameter("Cashtype");
			String transactionDate = request.getParameter("TransactionDate");
			
			String polSysIdForPdf = request.getParameter("PolSysIdForPdf");
			System.out.println("proposalNo=======>>"+proposalNo);
			System.out.println("transactionNo=======>>"+transactionNo);
			System.out.println("polSysIdForPdf=======>>"+polSysIdForPdf);
			
			
			shreeRamBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
			shreeRamBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
			shreeRamBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
			shreeRamBean.setMotorGroupResponseGicName(request.getParameter("motorGroupResponseGicName"));
			shreeRamBean.setUserId(request.getParameter("userId"));
			shreeRamBean.setUserDesc(request.getParameter("userDesc"));
			shreeRamBean.setBranchId(request.getParameter("branchId"));
			shreeRamBean.setIPAddress(request.getParameter("IpAddress"));
			
			
			String gender = "";
			if((!reqFor.equalsIgnoreCase("PolicyApprove"))&&(!reqFor.equalsIgnoreCase("policyScheduleURL"))){
				if (Gender.equalsIgnoreCase("male")) {
					gender = "M";
				} else if (Gender.equalsIgnoreCase("female")) {
					gender = "F";
				}
			}

			extraParaMap.put("strRTOCode", strRTOCode);
			extraParaMap.put("strFirstRegDt", strFirstRegDt);
			extraParaMap.put("strInsuredState", strInsuredState);
			extraParaMap.put("strPrevPolExpDt", strPrevPolExpDt);
			extraParaMap.put("strPrevPolClaimYN", strPrevPolClaimYN);
			extraParaMap.put("strPrevPolNCB", strPrevPolNCB);
			extraParaMap.put("adharEnrollNo", adharEnrollNo);
			extraParaMap.put("adharNo", adharNo);
			extraParaMap.put("addonPackage", addonPackage);
			extraParaMap.put("address1", address1);
			extraParaMap.put("address2", address2);
			extraParaMap.put("address3", address3);
			extraParaMap.put("isAntiTheft", isAntiTheft);
			extraParaMap.put("appointeeNameforPAOwnerDriver", appointeeNameforPAOwnerDriver);
			extraParaMap.put("appointeeRelationforPAOwnerDriver", appointeeRelationforPAOwnerDriver);
			extraParaMap.put("breakIn", breakIn);
			extraParaMap.put("chassisNo", chassisNo);
			extraParaMap.put("city", city);
			extraParaMap.put("CNGKitSI", CNGKitSI);
			extraParaMap.put("coverNoteDt", coverNoteDt);
			extraParaMap.put("coverNoteNo", coverNoteNo);
			extraParaMap.put("isDailyExpRem", isDailyExpRem);
			extraParaMap.put("dob", dob);
			extraParaMap.put("DeTariff", DeTariff);
			extraParaMap.put("isDriverAge", isDriverAge);
			extraParaMap.put("ElectricalaccessRemarks", ElectricalaccessRemarks);
			extraParaMap.put("isElectricalaccess", isElectricalaccess);
			extraParaMap.put("ElectricalaccessSI", ElectricalaccessSI);
			extraParaMap.put("EMailID", EMailID);
			extraParaMap.put("EngineNo", EngineNo);
			extraParaMap.put("FaxNo", FaxNo);
			extraParaMap.put("FirstRegDt", FirstRegDt);
			extraParaMap.put("Form16", Form16);
			extraParaMap.put("Gender", gender);
			extraParaMap.put("GSTNo", GSTNo);
			extraParaMap.put("HypothecationAddress1", HypothecationAddress1);
			extraParaMap.put("HypothecationAddress2", HypothecationAddress2);
			extraParaMap.put("HypothecationAddress3", HypothecationAddress3);
			extraParaMap.put("HypothecationAgreementNo", HypothecationAgreementNo);
			extraParaMap.put("HypothecationBankName", HypothecationBankName);
			extraParaMap.put("HypothecationCity", HypothecationCity);
			extraParaMap.put("HypothecationCountry", HypothecationCountry);
			extraParaMap.put("HypothecationState", HypothecationState);
			extraParaMap.put("HypothecationPinCode", HypothecationPinCode);
			extraParaMap.put("HypothecationType", HypothecationType);
			extraParaMap.put("IDVOfVehicle", IDVOfVehicle);
			extraParaMap.put("isInBuiltCNGKit", isInBuiltCNGKit);
			extraParaMap.put("InsuredName", InsuredName);
			extraParaMap.put("InsuredPrefix", InsuredPrefix);
			extraParaMap.put("InsuredNamisInvReturne", isInvReturn);
			extraParaMap.put("isKeyReplacement", isKeyReplacement);
			extraParaMap.put("isLimitedTPPD", isLimitedTPPD);
			extraParaMap.put("isLimitOwnPremise", isLimitOwnPremise);
			extraParaMap.put("isLLtoPaidDriver", isLLtoPaidDriver);
			extraParaMap.put("isLossOfPersonBelong", isLossOfPersonBelong);
			extraParaMap.put("MobileNo", MobileNo);
			extraParaMap.put("isMultiCarBenefit", isMultiCarBenefit);
			extraParaMap.put("NilDepreciationCover", NilDepreciationCover);
			extraParaMap.put("NoEmpCoverLL", NoEmpCoverLL);
			extraParaMap.put("NomineeAgeforPAOwnerDriver", NomineeAgeforPAOwnerDriver);
			extraParaMap.put("NomineeNameforPAOwnerDriver", NomineeNameforPAOwnerDriver);
			extraParaMap.put("NomineeRelationforPAOwnerDriver", NomineeRelationforPAOwnerDriver);
			extraParaMap.put("NonElectricalaccessRemarks", NonElectricalaccessRemarks);
			extraParaMap.put("NonElectricalaccessSI", NonElectricalaccessSI);
			extraParaMap.put("isNonElectricalaccess", isNonElectricalaccess);
			extraParaMap.put("PAforUnnamedPassengerSI", PAforUnnamedPassengerSI);
			extraParaMap.put("isPAforUnnamedPassenger", isPAforUnnamedPassenger);
			extraParaMap.put("PanNo", PanNo);
			extraParaMap.put("PAOwnerDriverExclusion", PAOwnerDriverExclusion);
			extraParaMap.put("PAOwnerDriverExReason", PAOwnerDriverExReason);
			extraParaMap.put("PAPaidCleanerCount", PAPaidCleanerCount);
			extraParaMap.put("PAPaidConductorCount", PAPaidConductorCount);
			extraParaMap.put("PAPaidDriverConductorCleanerSI", PAPaidDriverConductorCleanerSI);
			extraParaMap.put("isPAPaidDriverConductorCleaner", isPAPaidDriverConductorCleaner);
			extraParaMap.put("PAPaidDriverCount", PAPaidDriverCount);
			extraParaMap.put("PinCode", PinCode);
			extraParaMap.put("PolicyType", PolicyType);
			extraParaMap.put("POSAgentName", POSAgentName);
			extraParaMap.put("POSAgentPanNo", POSAgentPanNo);
			extraParaMap.put("PreInspection", PreInspection);
			extraParaMap.put("isPreInspectionReport", isPreInspectionReport);
			extraParaMap.put("PreviousInsurer", PreviousInsurer);
			extraParaMap.put("PreviousNilDepreciation", PreviousNilDepreciation);
			extraParaMap.put("isPreviousPolicyClaim", isPreviousPolicyClaim);
			extraParaMap.put("PreviousPolicyFromDt", PreviousPolicyFromDt);
			extraParaMap.put("PreviousPolicyNCBPerc", PreviousPolicyNCBPerc);
			extraParaMap.put("PreviousPolicyNo", PreviousPolicyNo);
			extraParaMap.put("PreviousPolicySI", PreviousPolicySI);
			extraParaMap.put("PreviousPolicyToDt", PreviousPolicyToDt);
			extraParaMap.put("PreviousPolicyType", PreviousPolicyType);
			extraParaMap.put("PreviousPolicyUWYear", PreviousPolicyUWYear);
			extraParaMap.put("ProdCode", ProdCode);
			extraParaMap.put("proposalType", proposalType);
			extraParaMap.put("ReferenceNo", referenceNo);
			extraParaMap.put("regNo1", regNo1);
			extraParaMap.put("regNo2", regNo2);
			extraParaMap.put("regNo3", regNo3);
			extraParaMap.put("regNo4", regNo4);
			extraParaMap.put("rtoCode", rtoCode);
			extraParaMap.put("SpecifiedPersonField", SpecifiedPersonField);
			extraParaMap.put("TelephoneNo", TelephoneNo);
			extraParaMap.put("VehiclePurpose", VehiclePurpose);
			extraParaMap.put("VehicleType", VehicleType);
			extraParaMap.put("voluntaryExcess", voluntaryExcess);
			extraParaMap.put("isgeoExt", isgeoExt);
							
			extraParaMap.put("proposalNoPolicyApp",proposalNo );//"10014/31/18/P/000014"
			extraParaMap.put("transactionNo", transactionNo);
			extraParaMap.put("cardNo", cardNo);
			extraParaMap.put("cardHolderName", cardHolderName);
			extraParaMap.put("cardType", cardType);
			extraParaMap.put("cardValidUpto", cardValidUpto);
			extraParaMap.put("bankName", bankName);
			extraParaMap.put("branchName", branchName);
			extraParaMap.put("paymentType", paymentType);
			extraParaMap.put("chequeType", chequeType);
			extraParaMap.put("chequeClearType", chequeClearType);
			extraParaMap.put("cashtype", cashtype);
			extraParaMap.put("transactionDate", transactionDate);
			
			extraParaMap.put("polSysIdForPdf", polSysIdForPdf);
			
			
			if (shreeRamBean.getRequest_for().equalsIgnoreCase("proposal")) {
				if (!shreeRamBean.getGeoCountryList().equalsIgnoreCase("")) {
					if (shreeRamBean.getGeoCountryList().contains("SriLanka")) {
						shreeRamBean.setSriLanka("1");
					}
					if (shreeRamBean.getGeoCountryList().contains("Bangladesh")) {
						shreeRamBean.setBangladesh("1");
					}
					if (shreeRamBean.getGeoCountryList().contains("Bhutan")) {
						shreeRamBean.setBhutan("1");
					}
					if (shreeRamBean.getGeoCountryList().contains("Nepal")) {
						shreeRamBean.setNepal("1");
					}
					if (shreeRamBean.getGeoCountryList().contains("Pakistan")) {
						shreeRamBean.setPakistan("1");
					}
					if (shreeRamBean.getGeoCountryList().contains("Maldives")) {
						shreeRamBean.setMaldives("1");
					}
				}
			}
			

//			HashMap prePro = new HashMap<>();
			System.out.println("22222222222222222222222222222222222");
			
			Gson gson = new Gson();
			json = gson.toJson(extraParaMap);
			
			 System.out.println("json additional parameters = " + json);
			 jsonNames = json + "" + jsonNames ;
			  System.out.println("result Befor Removing brackets = " +
			  jsonNames);
			  jsonNames= "[" + jsonNames + "]"; 
				System.out.println("final jsonNames = " + jsonNames);
				System.out.println("Request_type--==" + shreeRamBean.getRequest_for());
				
				if(jsonNames.contains("}]]"))
				{
					jsonNames = jsonNames.replace("}]]", "}]");
				}
				if(jsonNames.contains("}[{"))
				{
					jsonNames = jsonNames.replace("}[{", ",");
				}
				if(jsonNames.contains("}]}]"))
				{
					jsonNames = jsonNames.replace("}]}]", "}]");
				}
				System.out.println("After jsonNames = " + jsonNames);
			  
		
			ShriramService shriRamService= new ShriramService();
			System.out.println("*********************************************");
			ShriramServiceSoap shreeRamApi = shriRamService.getShriramServiceSoap();
			
			AuthHeader authHeader =new AuthHeader();
			
			String userName="Emiton";			
			String password="Emiton@123";			
			authHeader.setUsername(userName);
			authHeader.setPassword(password);
			
			switch (shreeRamBean.getRequest_for()) {
			case "premium":
				System.out.println("333333333333333333333333333333333333333333");
				// HashMap premium = new HashMap();

				hashmap = GetShreeRamQuoteResponse(authHeader, shreeRamApi, jsonNames);
				System.out.println("premium------->>" + hashmap);
				
				saveHashMapRespo("PR_PREMIUM",hashmap,1);

				
				
			break;
			case "proposal":
//				HashMap proposal = new HashMap();
				MPCProposalEntryETT objPolicyEntryETT = new MPCProposalEntryETT();
				hashmap = GetShreeRamProposalResponse(objPolicyEntryETT, authHeader,jsonNames, shreeRamApi);
				System.out.println("hashmap------->>"+hashmap);
				
				if(premiumFromProposal.equalsIgnoreCase("Yes")){
					saveHashMapRespo("PR_PREMIUM",hashmap,1);
				}else{
					saveHashMapRespo("PR_PROPOSAL",hashmap,1);
				}
				
				break;
			
			case "PolicyApprove":	
			
//				HashMap policyApprove = new HashMap();
				PolicyApprovalETT objPolicyApprovalETT = new PolicyApprovalETT();
				hashmap = GetShreeRamPolicyApproval(objPolicyApprovalETT, authHeader,jsonNames, shreeRamApi);
				System.out.println("hashmap PolicyApprove ====>> "+hashmap);
				
				saveHashMapRespo("PR_PROPOSAL",hashmap,2);		// ResponseType pass "2" when u save data of payment
			break;
			case "policyScheduleURL":	
				hashmap = GetShreeRamPolicyScheduleURL(authHeader,jsonNames, shreeRamApi);
				
				break;
			
			default:
				break;
			}

				
			
		} catch (Exception e) {
			System.out.println("//////////////////////////" + e);
		}

		
		hashmap.putAll(extraParaMap);
//		hashmap.putAll(inputParaList);
		hashmap.put("POLICYFROMDT", shreeRamBean.getPolicyFromdate());
		hashmap.put("POLICYTODT", shreeRamBean.getPolicyTodate());
		hashmap.put("GICNAME", shreeRamBean.getMotorGroupResponseGicName());
		hashmap.put("GICID", shreeRamBean.getMotorGroupResponseGicId());
		
		System.out.println("hashmap====>>"+hashmap);
		
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonFinalResp = mapperObj.writeValueAsString(hashmap);
        System.out.println("jsonFinalResp==>>"+jsonFinalResp);
		
        String finalRespo= "["+	jsonFinalResp +"]";
        System.out.println("finalRespoWith Brackets=====>>"+finalRespo);
		
		return finalRespo;

	}

//	@RequestMapping(value = "/ShreeRamPayment", method = RequestMethod.GET)
//	public org.springframework.web.servlet.ModelAndView videoconPayment(HttpServletRequest request,
//			HttpServletResponse response) throws JSONException {
//
//		
//		String proposalNo= request.getParameter("ProposalNo");
//		String transactionNo= request.getParameter("TransactionNo");
//		String amount= request.getParameter("amount");
//		String paymentMode= request.getParameter("paymentMode");
//		String transactionDate= request.getParameter("transactionDate");
//		String actionUrl= request.getParameter("actionUrl");
//		String insurarName = request.getParameter("InsurarName");
//		shreeRamBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
//		shreeRamBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
//		shreeRamBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
//		shreeRamBean.setUserId(request.getParameter("userId"));
//		shreeRamBean.setUserDesc(request.getParameter("userDesc"));
//		shreeRamBean.setBranchId(request.getParameter("branchId"));
//		
//		HashMap<String, String> payRequest = new HashMap<>();
//		
//		payRequest.put("ProposalNo", proposalNo);
//		payRequest.put("TransactionNo", transactionNo);
//		payRequest.put("amount", amount);
//		payRequest.put("paymentMode", paymentMode);
//		payRequest.put("transactionDate", transactionDate);
//		payRequest.put("actionUrl", actionUrl);
////		payRequest.put("insurarName", insurarName);
////		payRequest.put("motorGroupResponseGroupId", shreeRamBean.getMotorGroupResponseGroupId());
////		payRequest.put("motorGroupResponseSessionId", shreeRamBean.getMotorGroupResponseSessionId());
////		payRequest.put("motorGroupResponseGicId", shreeRamBean.getMotorGroupResponseGicId());
////		payRequest.put("userId", shreeRamBean.getUserId());
////		payRequest.put("userDesc", shreeRamBean.getUserDesc());
////		payRequest.put("branchId", shreeRamBean.getBranchId());
//		
//		System.out.println("aa gaya yaha");
//		System.out.println("payRequest" + payRequest);
//		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView
//		("common/shreeRamPayment");
//		andView.addObject("data", payRequest);
//		return andView;
//	}

	private void saveHashMapRespo(String proceName,HashMap<String, String> hashmap,int responseType) {
//		System.out.println("MotorGroupResponseSessionId:" + shreeRamBean.getMotorGroupResponseSessionId());
//		System.out.println("getUserId:" + shreeRamBean.getUserId());
//		System.out.println("getUserDesc:" + shreeRamBean.getUserDesc());
//		System.out.println("getBranchId:" + shreeRamBean.getBranchId());
//		System.out.println("getMotorGroupResponseGroupId:" + shreeRamBean.getMotorGroupResponseGroupId());
//		System.out.println("getMotorGroupResponseGicId:" + shreeRamBean.getMotorGroupResponseGicId());
//		System.out.println("getIPAddress:" + shreeRamBean.getIPAddress());

		Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(
				Integer.parseInt(shreeRamBean.getMotorGroupResponseGroupId()),
				Integer.parseInt(shreeRamBean.getMotorGroupResponseGicId()),
				shreeRamBean.getMotorGroupResponseSessionId(), responseType, hashmap, shreeRamBean.getIPAddress(),
				shreeRamBean.getUserId(), shreeRamBean.getBranchId(), shreeRamBean.getUserDesc(), proceName);
	}

	private HashMap GetShreeRamPolicyScheduleURL(AuthHeader authHeader,
		String jsonNames, ShriramServiceSoap shreeRamApi) throws JSONException {
		HashMap pdfGenUrlMap = new HashMap<>();
		String strPolSysId="";
		HashMap policyApprovalMap = new HashMap<>();
		JSONArray jsonarray = new JSONArray(jsonNames);
		for (int i = 0; i < jsonarray.length(); i++) {

			JSONObject jsonResult = jsonarray.getJSONObject(i);
		 strPolSysId=jsonResult.getString("polSysIdForPdf").trim();
		}
		String policyUrl= shreeRamApi.policyScheduleURL(strPolSysId, authHeader);
		
		pdfGenUrlMap.put("PolicyScheduleURL", policyUrl);
		return pdfGenUrlMap;
	}

	private HashMap GetShreeRamPolicyApproval(PolicyApprovalETT objPolicyApprovalETT, AuthHeader authHeader,
			String jsonNames, ShriramServiceSoap shreeRamApi) throws JSONException {
		
		HashMap policyApprovalMap = new HashMap<>();
		JSONArray jsonarray = new JSONArray(jsonNames);
		System.out.println("jsonResult======>>" + jsonNames);
		System.out.println("jsonarray.length()" + jsonarray.length());
		for (int i = 0; i < jsonarray.length(); i++) {

			JSONObject jsonResult = jsonarray.getJSONObject(i);

			objPolicyApprovalETT.setBankName(jsonResult.getString("bankName").trim());
			objPolicyApprovalETT.setBranchName(jsonResult.getString("branchName").trim());
			objPolicyApprovalETT.setCardholderName(jsonResult.getString("cardHolderName").trim());
			objPolicyApprovalETT.setCardNumber(jsonResult.getString("cardNo").trim());
			objPolicyApprovalETT.setCardType(jsonResult.getString("cardType").trim());
			objPolicyApprovalETT.setCardValidUpTo(jsonResult.getString("cardValidUpto").trim());
			objPolicyApprovalETT.setCashType(jsonResult.getString("cardType").trim());
			objPolicyApprovalETT.setChequeClearType(jsonResult.getString("chequeClearType").trim());
			objPolicyApprovalETT.setChequeType(jsonResult.getString("chequeType").trim());
			objPolicyApprovalETT.setPaymentType(jsonResult.getString("paymentType").trim());
			objPolicyApprovalETT.setProposalNo(jsonResult.getString("proposalNoPolicyApp").trim());
			objPolicyApprovalETT.setTransactionDate(jsonResult.getString("transactionDate").trim());
			objPolicyApprovalETT.setTransactionNumber(jsonResult.getString("transactionNo").trim());

		}
		
		PolicyApprovalResponseETT policyApproveResponse = shreeRamApi.policyApprove(objPolicyApprovalETT, authHeader);
		String approvedPolNo= policyApproveResponse.getApprovePolNo();
		String approvePolSysId= policyApproveResponse.getApprovePolSysId();
		String errCode= policyApproveResponse.getErrCode();
		String errDesc= policyApproveResponse.getErrDesc();
		
		System.out.println("errorCode:::" +errCode);
		System.out.println("errorDesc:::" +errDesc);
		System.out.println("approvedPolNo:::" +approvedPolNo);
		System.out.println("approvePolSysId:::" +approvePolSysId);
		
		policyApprovalMap.put("errorCode", errCode);
		policyApprovalMap.put("errorDesc", errDesc);
		policyApprovalMap.put("approvedPolNo", approvedPolNo);
		policyApprovalMap.put("approvePolSysId", approvePolSysId);
		
		return policyApprovalMap;
	}

	private HashMap GetShreeRamProposalResponse(MPCProposalEntryETT objPolicyEntryETT, AuthHeader authHeader,
			String jsonNames, ShriramServiceSoap shreeRamApi) throws JSONException {
		HashMap proposalResponseMap = new HashMap<>();
	
		JSONArray jsonarray = new JSONArray(jsonNames);
		System.out.println("jsonResult======>>"+jsonNames);
		System.out.println("jsonarray.length()" + jsonarray.length());
		for (int i = 0; i < jsonarray.length(); i++) {
			

			JSONObject jsonResult = jsonarray.getJSONObject(i);
			
			if(i==jsonarray.length()-9){
				System.out.println("jsonResult****======>>"+jsonResult);
			}
			
			objPolicyEntryETT.setAadharEnrollNo(jsonResult.getString("adharEnrollNo").trim());
			objPolicyEntryETT.setAadharNo(jsonResult.getString("adharNo").trim());
			objPolicyEntryETT.setAddonPackage("ADDON_01");		//jsonResult.getString("addonPackage").trim()
			objPolicyEntryETT.setAddress1(jsonResult.getString("address1").trim());
			objPolicyEntryETT.setAddress2(jsonResult.getString("address2").trim());
			objPolicyEntryETT.setAddress3(jsonResult.getString("address3").trim());
			objPolicyEntryETT.setAntiTheftYN(shreeRamBean.getAntiTheftYN());//Covers
			objPolicyEntryETT.setAppointeeNameforPAOwnerDriver(shreeRamBean.getAppointeeNameforPAOwnerDriver());//Covers
			objPolicyEntryETT.setAppointeeRelationforPAOwnerDriver(shreeRamBean.getAppointeeRelationforPAOwnerDrive());//Covers
			objPolicyEntryETT.setBangladesh(shreeRamBean.getBangladesh());
			objPolicyEntryETT.setBhutan(shreeRamBean.getBhutan());
			objPolicyEntryETT.setBreakIn(jsonResult.getString("breakIn").trim());
			objPolicyEntryETT.setChassisNo(jsonResult.getString("chassisNo").trim());
			objPolicyEntryETT.setCity(jsonResult.getString("city").trim());
			objPolicyEntryETT.setCNGKitSI(shreeRamBean.getcNGKitSI());//Covers
			objPolicyEntryETT.setCNGKitYN(shreeRamBean.getcNGKitYN());//Covers
			objPolicyEntryETT.setColour(shreeRamBean.getColour());//Covers
			objPolicyEntryETT.setCoverNoteDt(jsonResult.getString("coverNoteDt").trim());
			objPolicyEntryETT.setCoverNoteNo(jsonResult.getString("coverNoteNo").trim());
			objPolicyEntryETT.setDailyExpRemYN(jsonResult.getString("isDailyExpRem").trim());
			objPolicyEntryETT.setDateOfBirth(jsonResult.getString("dob").trim());
			objPolicyEntryETT.setDepDeductWaiverYN(shreeRamBean.getIsDepDeductWaiver());
			objPolicyEntryETT.setDeTariff(jsonResult.getString("DeTariff").trim());
			objPolicyEntryETT.setDriverAgeYN(jsonResult.getString("isDriverAge").trim());
			objPolicyEntryETT.setElectricalaccessRemarks(shreeRamBean.getElectricalaccessRemarks());//Covers
			objPolicyEntryETT.setElectricalaccessSI(shreeRamBean.getElectricalaccessSI());//Covers
			objPolicyEntryETT.setElectricalaccessYN(shreeRamBean.getElectricalaccessYN());//Covers
			objPolicyEntryETT.setEMailID(jsonResult.getString("EMailID").trim());
			objPolicyEntryETT.setEmergencyTranHotelExpRemYN(shreeRamBean.getIsEmergencyTranHotelExpRem());
			objPolicyEntryETT.setEngineNo(jsonResult.getString("EngineNo").trim());
			objPolicyEntryETT.setFaxNo(jsonResult.getString("FaxNo").trim());
			objPolicyEntryETT.setFirstRegDt(jsonResult.getString("POLICYFROMDT").trim());
			objPolicyEntryETT.setForm16(jsonResult.getString("Form16").trim());
			objPolicyEntryETT.setGender(jsonResult.getString("Gender").trim());
			objPolicyEntryETT.setGSTNo(jsonResult.getString("GSTNo").trim());
			objPolicyEntryETT.setHypothecationAddress1(jsonResult.getString("HypothecationAddress1").trim());
			objPolicyEntryETT.setHypothecationAddress2(jsonResult.getString("HypothecationAddress2").trim());
			objPolicyEntryETT.setHypothecationAddress3(jsonResult.getString("HypothecationAddress3").trim());
			objPolicyEntryETT.setHypothecationAgreementNo(jsonResult.getString("HypothecationAgreementNo").trim());
			objPolicyEntryETT.setHypothecationBankName(jsonResult.getString("HypothecationBankName").trim());
			objPolicyEntryETT.setHypothecationCity(jsonResult.getString("HypothecationCity").trim());
			objPolicyEntryETT.setHypothecationCountry(jsonResult.getString("HypothecationCountry").trim());
			objPolicyEntryETT.setHypothecationPinCode(jsonResult.getString("HypothecationPinCode").trim());
			objPolicyEntryETT.setHypothecationState(jsonResult.getString("HypothecationState").trim());
			objPolicyEntryETT.setHypothecationType(jsonResult.getString("HypothecationType").trim());
			objPolicyEntryETT.setIDVOfVehicle("552822.9");//jsonResult.getString("IDVOfVehicle").trim()
			objPolicyEntryETT.setInBuiltCNGKitYN(jsonResult.getString("isInBuiltCNGKit").trim());
			objPolicyEntryETT.setInsuredName(jsonResult.getString("InsuredName").trim());
			objPolicyEntryETT.setInsuredPrefix("1");//jsonResult.getString("InsuredPrefix").trim()
			objPolicyEntryETT.setInvReturnYN(jsonResult.getString("InsuredNamisInvReturne").trim());
			objPolicyEntryETT.setKeyReplacementYN(shreeRamBean.getKeyReplacementYN());//Covers
			objPolicyEntryETT.setLimitedTPPDYN(shreeRamBean.getLimitedTPPDYN());//Covers
			objPolicyEntryETT.setLimitOwnPremiseYN(shreeRamBean.getLimitOwnPremiseYN());//Covers
			objPolicyEntryETT.setLLtoPaidDriverYN(shreeRamBean.getlLtoPaidDriverYN());//Covers
			objPolicyEntryETT.setLossOfPersonBelongYN(shreeRamBean.getLossOfPersonBelongYN());//Covers
			objPolicyEntryETT.setMaldives(shreeRamBean.getMaldives());
			objPolicyEntryETT.setMobileNo(jsonResult.getString("MobileNo").trim());
			objPolicyEntryETT.setMultiCarBenefitYN(jsonResult.getString("isMultiCarBenefit").trim());
			objPolicyEntryETT.setNepal(shreeRamBean.getNepal());
			objPolicyEntryETT.setNilDepreciationCoverYN(shreeRamBean.getNilDepreciationCoverYN());//Covers
			objPolicyEntryETT.setNoEmpCoverLL(shreeRamBean.getNoEmpCoverLL());//Covers
			objPolicyEntryETT.setNomineeAgeforPAOwnerDriver(shreeRamBean.getNomineeAgeforPAOwnerDriver());//Covers
			objPolicyEntryETT.setNomineeNameforPAOwnerDriver(shreeRamBean.getNomineeNameforPAOwnerDriver());//Covers
			objPolicyEntryETT.setNomineeRelationforPAOwnerDriver(shreeRamBean.getNomineeRelationforPAOwnerDriver());//Covers
			objPolicyEntryETT.setNonElectricalaccessRemarks(shreeRamBean.getNonElectricalaccessRemarks());//Covers
			objPolicyEntryETT.setNonElectricalaccessSI(shreeRamBean.getNonElectricalaccessSI());//Covers
			objPolicyEntryETT.setNonElectricalaccessYN(shreeRamBean.getNonElectricalaccessYN());//Covers
			objPolicyEntryETT.setPAforUnnamedPassengerSI(shreeRamBean.getpAforUnnamedPassengerSI());//Covers
			objPolicyEntryETT.setPAforUnnamedPassengerYN(shreeRamBean.getpAforUnnamedPassengerYN());//Covers
			objPolicyEntryETT.setPakistan(shreeRamBean.getPakistan());
			objPolicyEntryETT.setPanNo(jsonResult.getString("PanNo").trim());
			objPolicyEntryETT.setPAOwnerDriverExclusion(shreeRamBean.getpAOwnerDriverExclusion());//Covers
			objPolicyEntryETT.setPAOwnerDriverExReason(shreeRamBean.getpAOwnerDriverExReason());//Covers
			objPolicyEntryETT.setPAPaidCleanerCount(shreeRamBean.getpAPaidCleanerCount());//Covers
			objPolicyEntryETT.setPAPaidConductorCount(shreeRamBean.getpAPaidConductorCount());//Covers
			objPolicyEntryETT.setPAPaidDriverConductorCleanerSI(shreeRamBean.getpAPaidDriverConductorCleanerSI());//Covers
			objPolicyEntryETT.setPAPaidDriverConductorCleanerYN(shreeRamBean.getpAPaidDriverConductorCleanerYN());//Covers
			objPolicyEntryETT.setPAPaidDriverCount(shreeRamBean.getPAPaidDriverCount());//Covers
			objPolicyEntryETT.setPinCode(jsonResult.getString("PinCode").trim());
			objPolicyEntryETT.setPolicyFromDt(jsonResult.getString("POLICYFROMDT").trim());
			objPolicyEntryETT.setPolicyIssueDt(jsonResult.getString("POLICYISSUEDT").trim());
			objPolicyEntryETT.setPolicyToDt(jsonResult.getString("POLICYTODT").trim());//Return from JsonRespo
			objPolicyEntryETT.setPolicyType("MOT-PLT-001");//jsonResult.getString("PolicyType").trim()
			objPolicyEntryETT.setPOSAgentName(jsonResult.getString("AGENTNAME").trim());//Return from JsonRespo
			objPolicyEntryETT.setPOSAgentPanNo(jsonResult.getString("AGENTPANNO").trim());//Return from JsonRespo
			objPolicyEntryETT.setPreInspection(jsonResult.getString("PreInspection").trim());
			objPolicyEntryETT.setPreInspectionReportYN(jsonResult.getString("isPreInspectionReport").trim());
			objPolicyEntryETT.setPreviousInsurer(jsonResult.getString("PreviousInsurer").trim());
			objPolicyEntryETT.setPreviousNilDepreciation(jsonResult.getString("PreviousNilDepreciation").trim());
			objPolicyEntryETT.setPreviousPolicyClaimYN(jsonResult.getString("isPreviousPolicyClaim").trim());
			objPolicyEntryETT.setPreviousPolicyFromDt(jsonResult.getString("PreviousPolicyFromDt").trim());
			objPolicyEntryETT.setPreviousPolicyNCBPerc(jsonResult.getString("PreviousPolicyNCBPerc").trim());
			objPolicyEntryETT.setPreviousPolicyNo(jsonResult.getString("PreviousPolicyNo").trim());
			objPolicyEntryETT.setPreviousPolicySI(jsonResult.getString("PreviousPolicySI").trim());
			objPolicyEntryETT.setPreviousPolicyToDt(jsonResult.getString("PreviousPolicyToDt").trim());
			objPolicyEntryETT.setPreviousPolicyType(jsonResult.getString("PreviousPolicyType").trim());
			objPolicyEntryETT.setPreviousPolicyUWYear(jsonResult.getString("PreviousPolicyUWYear").trim());
			objPolicyEntryETT.setProdCode(jsonResult.getString("ProdCode").trim());
			objPolicyEntryETT.setProposalType(jsonResult.getString("PROPOSALTYPE").trim());
			objPolicyEntryETT.setReferenceNo(jsonResult.getString("ReferenceNo"));
			objPolicyEntryETT.setRegNo1(jsonResult.getString("regNo1").trim());
			objPolicyEntryETT.setRegNo2(jsonResult.getString("regNo2").trim());
			objPolicyEntryETT.setRegNo3(jsonResult.getString("regNo3").trim());
			objPolicyEntryETT.setRegNo4(jsonResult.getString("regNo4").trim());
			objPolicyEntryETT.setRTOCode("MH-27");//jsonResult.getString("strRTOCode").trim()
			objPolicyEntryETT.setSpecifiedPersonField(jsonResult.getString("SpecifiedPersonField").trim());
			objPolicyEntryETT.setSriLanka(shreeRamBean.getSriLanka());
			objPolicyEntryETT.setState(jsonResult.getString("STATE").trim());
			objPolicyEntryETT.setTelephoneNo(jsonResult.getString("TelephoneNo").trim());
			objPolicyEntryETT.setVehicleCode(jsonResult.getString("VEHICLECODE").trim());
			objPolicyEntryETT.setVehiclePurposeYN(jsonResult.getString("VehiclePurpose").trim());
			objPolicyEntryETT.setVehicleType(jsonResult.getString("VehicleType").trim());
			objPolicyEntryETT.setVoluntaryExcess(jsonResult.getString("voluntaryExcess").trim());
			
			shreeRamBean.setPolicyFromdate(jsonResult.getString("POLICYFROMDT").trim());
			shreeRamBean.setPolicyTodate(jsonResult.getString("POLICYTODT").trim());
			
		}
		
		System.out.println("getAadharEnrollNo========> "+objPolicyEntryETT.getAadharEnrollNo());
		System.out.println("getAadharNo========> "+objPolicyEntryETT.getAadharNo());
		System.out.println("getAddonPackage========> "+objPolicyEntryETT.getAddonPackage());
		System.out.println("getAddress1========> "+objPolicyEntryETT.getAddress1());
		System.out.println("getAddress2========> "+objPolicyEntryETT.getAddress2());
		System.out.println("getAddress3========> "+objPolicyEntryETT.getAddress3());
		System.out.println("getAntiTheftYN========> "+objPolicyEntryETT.getAntiTheftYN());
		System.out.println("getAppointeeNameforPAOwnerDriver========> "+objPolicyEntryETT.getAppointeeNameforPAOwnerDriver());
		System.out.println("getAppointeeRelationforPAOwnerDriver========> "+objPolicyEntryETT.getAppointeeRelationforPAOwnerDriver());
		System.out.println("getBangladesh========> "+objPolicyEntryETT.getBangladesh());
		System.out.println("getBhutan========> "+objPolicyEntryETT.getBhutan());
		System.out.println("getBreakIn========> "+objPolicyEntryETT.getBreakIn());
		System.out.println("getChassisNo========> "+objPolicyEntryETT.getChassisNo());
		System.out.println("getCity========> "+objPolicyEntryETT.getCity());
		System.out.println("getCNGKitSI========> "+objPolicyEntryETT.getCNGKitSI());
		System.out.println("getCNGKitYN========> "+objPolicyEntryETT.getCNGKitYN());
		System.out.println("getColour========> "+objPolicyEntryETT.getColour());
		System.out.println("getCoverNoteDt========> "+objPolicyEntryETT.getCoverNoteDt());
		System.out.println("getCoverNoteNo========> "+objPolicyEntryETT.getCoverNoteNo());
		System.out.println("getDailyExpRemYN========> "+objPolicyEntryETT.getDailyExpRemYN());
		System.out.println("getDateOfBirth========> "+objPolicyEntryETT.getDateOfBirth());
		System.out.println("getDepDeductWaiverYN========> "+objPolicyEntryETT.getDepDeductWaiverYN());
		System.out.println("getDeTariff========> "+objPolicyEntryETT.getDeTariff());
		System.out.println("getDriverAgeYN========> "+objPolicyEntryETT.getDriverAgeYN());
		System.out.println("getElectricalaccessRemarks========> "+objPolicyEntryETT.getElectricalaccessRemarks());
		System.out.println("getElectricalaccessSI========> "+objPolicyEntryETT.getElectricalaccessSI());
		System.out.println("getElectricalaccessYN========> "+objPolicyEntryETT.getElectricalaccessYN());
		System.out.println("getEMailID========> "+objPolicyEntryETT.getEMailID());
		System.out.println("getEmergencyTranHotelExpRemYN========> "+objPolicyEntryETT.getEmergencyTranHotelExpRemYN());
		System.out.println("getEngineNo========> "+objPolicyEntryETT.getEngineNo());
		System.out.println("getFirstRegDt========> "+objPolicyEntryETT.getFirstRegDt());
		System.out.println("getForm16========> "+objPolicyEntryETT.getForm16());
		System.out.println("getGender========> "+objPolicyEntryETT.getGender());
		System.out.println("getGSTNo========> "+objPolicyEntryETT.getGSTNo());
		System.out.println("getHypothecationAddress1========> "+objPolicyEntryETT.getHypothecationAddress1());
		System.out.println("getHypothecationAddress2========> "+objPolicyEntryETT.getHypothecationAddress2());
		System.out.println("getHypothecationAddress3========> "+objPolicyEntryETT.getHypothecationAddress3());
		System.out.println("getHypothecationAgreementNo========> "+objPolicyEntryETT.getHypothecationAgreementNo());
		System.out.println("getHypothecationBankName========> "+objPolicyEntryETT.getHypothecationBankName());
		System.out.println("getHypothecationCity========> "+objPolicyEntryETT.getHypothecationCity());
		System.out.println("getHypothecationCountry========> "+objPolicyEntryETT.getHypothecationCountry());
		System.out.println("getHypothecationPinCode========> "+objPolicyEntryETT.getHypothecationPinCode());
		System.out.println("getHypothecationState========> "+objPolicyEntryETT.getHypothecationState());
		System.out.println("getHypothecationType========> "+objPolicyEntryETT.getHypothecationType());
		System.out.println("getIDVOfVehicle========> "+objPolicyEntryETT.getIDVOfVehicle());
		System.out.println("getInBuiltCNGKitYN========> "+objPolicyEntryETT.getInBuiltCNGKitYN());
		System.out.println("getInsuredName========> "+objPolicyEntryETT.getInsuredName());
		System.out.println("getInsuredPrefix========> "+objPolicyEntryETT.getInsuredPrefix());
		System.out.println("getInvReturnYN========> "+objPolicyEntryETT.getInvReturnYN());
		System.out.println("getKeyReplacementYN========> "+objPolicyEntryETT.getKeyReplacementYN());
		System.out.println("getLimitedTPPDYN========> "+objPolicyEntryETT.getLimitedTPPDYN());
		System.out.println("getLimitOwnPremiseYN========> "+objPolicyEntryETT.getLimitOwnPremiseYN());
		System.out.println("getLLtoPaidDriverYN========> "+objPolicyEntryETT.getLLtoPaidDriverYN());
		System.out.println("getLossOfPersonBelongYN========> "+objPolicyEntryETT.getLossOfPersonBelongYN());
		System.out.println("getMaldives========> "+objPolicyEntryETT.getMaldives());
		System.out.println("getMobileNo========> "+objPolicyEntryETT.getMobileNo());
		System.out.println("getMultiCarBenefitYN========> "+objPolicyEntryETT.getMultiCarBenefitYN());
		System.out.println("getNepal========> "+objPolicyEntryETT.getNepal());
		System.out.println("getNilDepreciationCoverYN========> "+objPolicyEntryETT.getNilDepreciationCoverYN());
		System.out.println("getNoEmpCoverLL========> "+objPolicyEntryETT.getNoEmpCoverLL());
		System.out.println("getNomineeAgeforPAOwnerDriver========> "+objPolicyEntryETT.getNomineeAgeforPAOwnerDriver());
		System.out.println("getNomineeNameforPAOwnerDriver========> "+objPolicyEntryETT.getNomineeNameforPAOwnerDriver());
		System.out.println("getNomineeRelationforPAOwnerDriver========> "+objPolicyEntryETT.getNomineeRelationforPAOwnerDriver());
		System.out.println("getNonElectricalaccessRemarks========> "+objPolicyEntryETT.getNonElectricalaccessRemarks());
		System.out.println("getNonElectricalaccessSI========> "+objPolicyEntryETT.getNonElectricalaccessSI());
		System.out.println("getNonElectricalaccessYN========> "+objPolicyEntryETT.getNonElectricalaccessYN());
		System.out.println("getPAforUnnamedPassengerSI========> "+objPolicyEntryETT.getPAforUnnamedPassengerSI());
		System.out.println("getPAforUnnamedPassengerYN========> "+objPolicyEntryETT.getPAforUnnamedPassengerYN());
		System.out.println("getPakistan========> "+objPolicyEntryETT.getPakistan());
		System.out.println("getPanNo========> "+objPolicyEntryETT.getPanNo());
		System.out.println("getPAOwnerDriverExclusion========> "+objPolicyEntryETT.getPAOwnerDriverExclusion());
		System.out.println("getPAOwnerDriverExReason========> "+objPolicyEntryETT.getPAOwnerDriverExReason());
		System.out.println("getPAPaidCleanerCount========> "+objPolicyEntryETT.getPAPaidCleanerCount());
		System.out.println("getPAPaidConductorCount========> "+objPolicyEntryETT.getPAPaidConductorCount());
		System.out.println("getPAPaidDriverConductorCleanerSI========> "+objPolicyEntryETT.getPAPaidDriverConductorCleanerSI());
		System.out.println("getPAPaidDriverConductorCleanerYN========> "+objPolicyEntryETT.getPAPaidDriverConductorCleanerYN());
		System.out.println("getPAPaidDriverCount========> "+objPolicyEntryETT.getPAPaidDriverCount());
		System.out.println("getPinCode========> "+objPolicyEntryETT.getPinCode());
		System.out.println("getPolicyFromDt========> "+objPolicyEntryETT.getPolicyFromDt());
		System.out.println("getPolicyIssueDt========> "+objPolicyEntryETT.getPolicyIssueDt());
		System.out.println("getPolicyToDt========> "+objPolicyEntryETT.getPolicyToDt());
		System.out.println("getPolicyType========> "+objPolicyEntryETT.getPolicyType());
		System.out.println("getPOSAgentName========> "+objPolicyEntryETT.getPOSAgentName());
		System.out.println("getPOSAgentPanNo========> "+objPolicyEntryETT.getPOSAgentPanNo());
		System.out.println("getPreInspection========> "+objPolicyEntryETT.getPreInspection());
		System.out.println("getPreInspectionReportYN========> "+objPolicyEntryETT.getPreInspectionReportYN());
		System.out.println("getPreviousInsurer========> "+objPolicyEntryETT.getPreviousInsurer());
		System.out.println("getPreviousNilDepreciation========> "+objPolicyEntryETT.getPreviousNilDepreciation());
		System.out.println("getPreviousPolicyClaimYN========> "+objPolicyEntryETT.getPreviousPolicyClaimYN());
		System.out.println("getPreviousPolicyFromDt========> "+objPolicyEntryETT.getPreviousPolicyFromDt());
		System.out.println("getPreviousPolicyNCBPerc========> "+objPolicyEntryETT.getPreviousPolicyNCBPerc());
		System.out.println("getPreviousPolicyNo========> "+objPolicyEntryETT.getPreviousPolicyNo());
		System.out.println("getPreviousPolicySI========> "+objPolicyEntryETT.getPreviousPolicySI());
		System.out.println("getPreviousPolicyToDt========> "+objPolicyEntryETT.getPreviousPolicyToDt());
		System.out.println("getPreviousPolicyType========> "+objPolicyEntryETT.getPreviousPolicyType());
		System.out.println("getPreviousPolicyUWYear========> "+objPolicyEntryETT.getPreviousPolicyUWYear());
		System.out.println("getProdCode========> "+objPolicyEntryETT.getProdCode());
		System.out.println("getProposalType========> "+objPolicyEntryETT.getProposalType());
		System.out.println("getReferenceNo========> "+objPolicyEntryETT.getReferenceNo());
		System.out.println("getRegNo1========> "+objPolicyEntryETT.getRegNo1());
		System.out.println("getRegNo2========> "+objPolicyEntryETT.getRegNo2());
		System.out.println("getRegNo3========> "+objPolicyEntryETT.getRegNo3());
		System.out.println("getRegNo4========> "+objPolicyEntryETT.getRegNo4());
		System.out.println("getRTOCode========> "+objPolicyEntryETT.getRTOCode());
		System.out.println("getSpecifiedPersonField========> "+objPolicyEntryETT.getSpecifiedPersonField());
		System.out.println("getSriLanka========> "+objPolicyEntryETT.getSriLanka());
		System.out.println("getState========> "+objPolicyEntryETT.getState());
		System.out.println("getTelephoneNo========> "+objPolicyEntryETT.getTelephoneNo());
		System.out.println("getVehicleCode========> "+objPolicyEntryETT.getVehicleCode());
		System.out.println("getVehiclePurposeYN========> "+objPolicyEntryETT.getVehiclePurposeYN());
		System.out.println("getVehicleType========> "+objPolicyEntryETT.getVehicleType());
		System.out.println("getVoluntaryExcess========> "+objPolicyEntryETT.getVoluntaryExcess());
		
		
		System.out.println("1111111111111111111111111111111111");
		MPCProposalResponseETT proposalResponse = shreeRamApi.generateProposal(objPolicyEntryETT, authHeader);
		
		String errorCode= proposalResponse.getERRORCODE();
		String errorDesc = proposalResponse.getERRORDESC();
		String polSysId = proposalResponse.getPOLSYSID();
		String proposalNo = proposalResponse.getPROPOSALNO();
		String vehicleIdv = proposalResponse.getVehicleIDV();
		System.out.println("errorCode:::" +errorCode);
		System.out.println("errorDesc:::" +errorDesc);
		System.out.println("polSysId:::" +polSysId);
		System.out.println("proposalNo:::" +proposalNo);
		System.out.println("vehicleIdv:::" +vehicleIdv);
		
		proposalResponseMap.put("errorCode", errorCode);
		proposalResponseMap.put("errorDesc", errorDesc);
		proposalResponseMap.put("polSysId", polSysId);
		proposalResponseMap.put("proposalNo", proposalNo);
		proposalResponseMap.put("vehicleIdv", vehicleIdv);
		
		
		if(errorCode.equals("0")){
			System.out.println("22222222222222222222222222222222222222");
			ArrayOfCoverDtl coverDtlList = proposalResponse.getCoverDtlList();
			System.out.println("3333333333333333333333333333333333");
			List<CoverDtl> coverDtl = coverDtlList.getCoverDtl();
			System.out.println("4444444444444444444444444444");
			int i=1;
			for(int index=0; index<coverDtl.size(); index++)
			{
				System.out.println("index------>>"+i);
				String covDesc=coverDtl.get(index).getCoverDesc();
				String covPre=coverDtl.get(index).getPremium();
				String covType=coverDtl.get(index).getType();
				
				String covName=covDesc.replace(" ", "_");
				
				System.out.println("cov_Desc_"+covName+"  -->>" +covDesc);
				System.out.println("cov_Pre_"+covName+"  -->>" +covPre);
				System.out.println("cov_Type_"+covName+"  -->>" +covType);
				
				proposalResponseMap.put("cov_Desc_"+covName, covDesc);
				proposalResponseMap.put("cov_Pre_"+covName, covPre);
				proposalResponseMap.put("cov_Type_"+covName, covType);
				i++;
			}
		}
		
		
		
		return proposalResponseMap;
	}

	private HashMap GetShreeRamQuoteResponse(AuthHeader authHeader, ShriramServiceSoap shreeRamApi, String jsonNames) throws JSONException {
		HashMap quoteResponseMap = new HashMap<>();
		
		String strProductCode="",strPolicyType="",strRTOCode="",strVehicleCode="",strADDONCover="",strFirstRegDt="",strInsuredState="",strPrevPolExpDt="",strPrevPolClaimYN="",strPrevPolNCB="";
		JSONArray jsonarray = new JSONArray(jsonNames);
		 System.out.println("jsonarray.length()"+jsonarray.length());
		for (int i = 0; i < jsonarray.length(); i++) {
			
			
			JSONObject jsonResult = jsonarray.getJSONObject(i);
			 strProductCode = jsonResult.getString("PRODCODE").trim();
			 strPolicyType = jsonResult.getString("POLICYTYPE").trim();
			 strRTOCode = jsonResult.getString("strRTOCode").trim();
			 strVehicleCode = jsonResult.getString("VEHICLECODE").trim();
			 strADDONCover = "ADDON_01";//jsonResult.getString("WACODE").trim();
			 strFirstRegDt = jsonResult.getString("strFirstRegDt").trim();
			 strInsuredState = jsonResult.getString("strInsuredState").trim();
			 strPrevPolExpDt = jsonResult.getString("strPrevPolExpDt").trim();
			 strPrevPolClaimYN = jsonResult.getString("strPrevPolClaimYN").trim();
			 strPrevPolNCB = jsonResult.getString("strPrevPolNCB").trim();
			 
			 System.out.println("strProductCode---------->>"+strProductCode);
			 System.out.println("strPolicyType---------->>"+strPolicyType);
			 System.out.println("strRTOCode---------->>"+strRTOCode);
			 System.out.println("strVehicleCode---------->>"+strVehicleCode);
			 System.out.println("strADDONCover---------->>"+strADDONCover);
			 System.out.println("strFirstRegDt---------->>"+strFirstRegDt);
			 System.out.println("strInsuredState---------->>"+strInsuredState);
			 System.out.println("strPrevPolExpDt---------->>"+strPrevPolExpDt);
			 System.out.println("strPrevPolClaimYN---------->>"+strPrevPolClaimYN);
			 System.out.println("strPrevPolNCB---------->>"+strPrevPolNCB);
		}
		
		MPCProposalResponseETT quoteResponse = shreeRamApi.getQuot(strProductCode, strPolicyType, strRTOCode, strVehicleCode, strADDONCover, strFirstRegDt, strInsuredState, strPrevPolExpDt, strPrevPolClaimYN, strPrevPolNCB, authHeader);
		
		
		
		String errorCode= quoteResponse.getERRORCODE();
		String errorDesc = quoteResponse.getERRORDESC();
		String polSysId = quoteResponse.getPOLSYSID();
		String proposalNo = quoteResponse.getPROPOSALNO();
		String vehicleIdv = quoteResponse.getVehicleIDV();
		
		System.out.println("errorCode::" +errorCode);
		System.out.println("errorDesc::" +errorDesc);
		System.out.println("polSysId::" +polSysId);
		System.out.println("proposalNo::" +proposalNo);
		System.out.println("vehicleIdv::" +vehicleIdv);

		quoteResponseMap.put("errorCode", errorCode);
		quoteResponseMap.put("errorDesc", errorDesc);
		quoteResponseMap.put("polSysId", polSysId);
		quoteResponseMap.put("proposalNo", proposalNo);
		quoteResponseMap.put("vehicleIdv", vehicleIdv);
		
		if(errorCode.equals("0")){
			ArrayOfCoverDtl coverDtlList = quoteResponse.getCoverDtlList();
			List<CoverDtl> coverDtl = coverDtlList.getCoverDtl();
			int i=1;
			for(int index=0; index<coverDtl.size(); index++)
			{
				
				System.out.println("index------>>"+i);
				String covDesc=coverDtl.get(index).getCoverDesc();
				String covPre=coverDtl.get(index).getPremium();
				String covType=coverDtl.get(index).getType();
				
				String covName=covDesc.replace(" ", "_");
				
				System.out.println("cov_Desc_"+covName+"  -->>" +covDesc);
				System.out.println("cov_Pre_"+covName+"  -->>" +covPre);
				System.out.println("cov_Type_"+covName+"  -->>" +covType);
				
				quoteResponseMap.put("cov_Desc_"+covName, covDesc);
				quoteResponseMap.put("cov_Pre_"+covName, covPre);
				quoteResponseMap.put("cov_Type_"+covName, covType);
				i++;
			}
		}
		
		
		return quoteResponseMap;
	}
				
}
