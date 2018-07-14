package com.uat.hbc.common.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.dom.DOMSource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



















import com.google.gson.Gson;
import com.hdfc.GetIDV;
import com.hdfc.GetPlanTypes;
import com.hdfc.GetPremium;
import com.hdfc.Service;
import com.hdfc.ServiceSoap;
import com.hdfc.generatePolicyPdf.GenSchedules;
import com.hdfc.generatePolicyPdf.GenSchedulesSoap;
import com.hdfc.proposal.Xmlstring;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.uat.hbc.common.dao.IntegrationHDFCDao;
import com.uat.hbc.common.dao.IntegrationSaveResponseDao;
import com.uat.hbc.common.model.HDFCIntBean;
import com.uat.hbc.common.model.MotorResponseBean;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;

@Controller
public class HdfcIntegrationController {
	@Autowired
	IntegrationHDFCDao hdfcDao;
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;
	DOMSource domSource;
	String path = System.getProperty("user.home");
	HashMap data;
	HDFCIntBean hdfcIntBean;
	@Autowired
	IntegrationSaveResponseDao integrationSaveResponseDao;
	HashMap hashmap ;
	HashMap<String, String> hashmapProposal;
	HashMap<String, String> hashmapinputs ;
	HashMap<String, String> hashmapreqParm;
	HashMap<String, String> hashmapPremium;
	HashMap inputParaList;
	HashMap<String, String> payRequest;
	MotorResponseBean bean;

	public HdfcIntegrationController() {
		hashmap = new HashMap<>();
	 hashmapProposal = new HashMap<>();
		 hashmapinputs = new HashMap<>();
		hashmapreqParm = new HashMap<>();
		hashmapPremium = new HashMap<>();
		 bean = new MotorResponseBean();
	}

	
	@RequestMapping(value = "user/HdfcPayment", method = RequestMethod.GET)
	public org.springframework.web.servlet.ModelAndView callJsp(
			HttpServletRequest request, HttpServletResponse response) {
		hdfcIntBean= new HDFCIntBean();
		hdfcIntBean.setCustomerId(request.getParameter("CustomerId"));
		String TxnAmount = request.getParameter("TxnAmount");
		hdfcIntBean.setAdditionalInfo1(request.getParameter("AdditionalInfo1"));
		hdfcIntBean.setAdditionalInfo2(request.getParameter("AdditionalInfo2"));
		hdfcIntBean.setAdditionalInfo3(request.getParameter("AdditionalInfo3"));
		hdfcIntBean.setHdnPayMode(request.getParameter("hdnPayMode"));
		hdfcIntBean.setUserName(request.getParameter("UserName"));
		hdfcIntBean.setUserMailId(request.getParameter("UserMailId"));
		hdfcIntBean.setProductCd(request.getParameter("ProductCd"));
		String ProducerCd = request.getParameter("ProducerCd");
		String responseUrl = request.getParameter("responseUrl");
		hdfcIntBean.setUserId(request.getParameter("userId"));
		hdfcIntBean.setUserDesc(request.getParameter("userDesc"));
		hdfcIntBean.setBranchId(request.getParameter("branchId"));
		hdfcIntBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
		hdfcIntBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
		hdfcIntBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
		hdfcIntBean.setiPAddress(request.getParameter("iPAddress"));

		bean.setUserId(hdfcIntBean.getUserId());
		bean.setUserDesc(hdfcIntBean.getUserDesc());
		bean.setBranchId(hdfcIntBean.getBranchId());
		bean.setGroupId(hdfcIntBean.getMotorGroupResponseGroupId());
		bean.setSessionId(hdfcIntBean.getMotorGroupResponseSessionId());
		bean.setGicId(hdfcIntBean.getMotorGroupResponseGicId());
		bean.setResponseType("2");

		payRequest = new HashMap<>();
		payRequest.put("CustomerId", hdfcIntBean.getCustomerId());
		payRequest.put("TxnAmount", TxnAmount);
		payRequest.put("AdditionalInfo1", hdfcIntBean.getAdditionalInfo1());
		payRequest.put("AdditionalInfo2", hdfcIntBean.getAdditionalInfo2());
		payRequest.put("AdditionalInfo3", hdfcIntBean.getAdditionalInfo3());
		payRequest.put("hdnPayMode", hdfcIntBean.getHdnPayMode());
		payRequest.put("UserName", hdfcIntBean.getUserName());
		payRequest.put("UserMailId", hdfcIntBean.getUserMailId());
		payRequest.put("ProductCd", hdfcIntBean.getProductCd());
		payRequest.put("ProducerCd", ProducerCd);
		payRequest.put("responseUrl", responseUrl);
		
		System.out.println("payRequest" + payRequest);
		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView(
				"common/HdfcPayment");
		
		andView.addObject("data", payRequest);
		return andView;
	}

//	private void policyGeneration() {
//		System.out.println("policygeneration");
//		com.hdfc.generatePolicy.Service seviceGeneratePolicy = new com.hdfc.generatePolicy.Service();
//		com.hdfc.generatePolicy.ServiceSoap api = seviceGeneratePolicy
//				.getServiceSoap();
//		GenerateTWTransNo generateTWTransNo = new GenerateTWTransNo();
//		String generatePolicy = "<Root><CustomerID>MT1605001521T</CustomerID><TxnAmount>14857</TxnAmount><AdditionalInfo1>NB</AdditionalInfo1><AdditionalInfo2>MOT</AdditionalInfo2><AdditionalInfo3>1</AdditionalInfo3><hdnPayMode>CC</hdnPayMode><UserName>Nandkishor Chavan</UserName><UserMailId>amrutamahale6@gmail.com</UserMailId><ProductCd>MOT</ProductCd><ProducerCd>LEK0001-MT1605001521T</ProducerCd></Root>";
//		// String generatePolicy =
//		// <Root><PolicyNo>2311100073714100000</PolicyNo><Msg>Successful</Msg><ProposalNo>MT1605001521T</ProposalNo><Amt>14857</Amt></Root>";
//		generateTWTransNo.setNewTWCPURL(generatePolicy);
//		String policy = api.generateTWTransNo(generatePolicy);
//		System.out.println("policy-->>>> " + policy);
//	}

	
	@RequestMapping(value = "/HdfcPaymentStatus", method = RequestMethod.GET)
	public org.springframework.web.servlet.ModelAndView callJspStatus(
			HttpServletRequest request, HttpServletResponse response) {

		String PolicyNo = request.getParameter("PolicyNo");
		System.out.println("PolicyNo-->>> " + PolicyNo);
		String Msg = request.getParameter("Msg");
		System.out.println("Msg-->>> " + Msg);
		String ProposalNo = request.getParameter("ProposalNo");
		String Amt = request.getParameter("Amt");
		System.out.println("ProposalNo-->>>" + ProposalNo);
		System.out.println("Amt-->>>" + Amt);
		
		HashMap<String, String> payResponse = new HashMap<>();
		payResponse.put("PolicyNo", PolicyNo);
		payResponse.put("Msg", Msg);
		payResponse.put("ProposalNo", ProposalNo);
		payResponse.put("Amt", Amt);

		System.out.println("payResponse" + payResponse);
		System.out.println("payRequest" + payRequest);
		
		HashMap transMap = new HashMap();
		transMap.put("CustomerId", hdfcIntBean.getCustomerId());
		transMap.put("AdditionalInfo1", hdfcIntBean.getAdditionalInfo1());
		transMap.put("AdditionalInfo2", hdfcIntBean.getAdditionalInfo2());
		transMap.put("AdditionalInfo3", hdfcIntBean.getAdditionalInfo3());
		transMap.put("hdnPayMode", hdfcIntBean.getHdnPayMode());
		transMap.put("UserName", hdfcIntBean.getUserName());
		transMap.put("UserMailId", hdfcIntBean.getUserMailId());
		transMap.put("ProductCd", hdfcIntBean.getProductCd());
		transMap.putAll(payResponse);

//		MotorResponseBean objResult = integrationSaveResponseDao.saveRelianceProposalDataDump(transMap, bean);
//
//		MotorResponseBean objResult1 = integrationSaveResponseDao.saveRelianceProposalDataProcess(bean);
		String procedureName = "PR_PROPOSAL";
		Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(hdfcIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(hdfcIntBean.getMotorGroupResponseGicId()), hdfcIntBean.getMotorGroupResponseSessionId(),
				2, transMap, hdfcIntBean.getiPAddress(), hdfcIntBean.getUserId(), hdfcIntBean.getBranchId(),hdfcIntBean.getUserDesc(), procedureName);
		// saveTransData(transMap);
		if(Msg.equalsIgnoreCase("Successful")){
			getPolicyPdf(PolicyNo, hdfcIntBean.getMenuCode());
		}
		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView("common/HdfcPaymentStatus");
		andView.addObject("data", payResponse);
		return andView;
	}
	
	private void getPolicyPdf(String policyNo,String MenuCode) {
		GenSchedules genSchedules = new GenSchedules();
		GenSchedulesSoap scheduleResponse = genSchedules.getGenSchedulesSoap();
		byte[] response = scheduleResponse.getPSSPDFInBytes(
				policyNo, MenuCode);
		FileWriter fstream;
		try {
			fstream = new FileWriter("HdfcPolicy.pdf");
			BufferedWriter out = new BufferedWriter(fstream);
			for (Byte response1 : response) {
				out.write(response1);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// private void saveTransData(HashMap transMap) {
	// String saveResponse = integrationSaveResponseDao.saveHDFCPayment(
	// transMap, bean);
	// }

	@RequestMapping(value = "user/HdfcIntegrationController", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String saveDetails(
			HttpServletRequest request, HttpServletResponse res)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		hdfcIntBean = new HDFCIntBean();
		String jsonNames = "";
		System.out.println(" in HdfcIntegrationController");
		Service sevice1 = new Service();
		ServiceSoap api = sevice1.getServiceSoap();
		JSONObject json = new JSONObject();
		JSONObject jsonPremium = new JSONObject();
		String agentCode = request.getParameter("agentCode");
		String businessType = request.getParameter("businessType");
		String vehicleModelCode = request.getParameter("vehicleModelCode");
		String rtoLocationCode = request.getParameter("rtoLocationCode");
		String rtoCity = request.getParameter("rtoCity");
		String policyStartDate = request.getParameter("policyStartDate");
		String firstRegistrationDate = request
				.getParameter("firstRegistrationDate");
		String pkgName = request.getParameter("pkgName");
		String tableName = request.getParameter("tableName");
		String procName = request.getParameter("procName");
		String covers = request.getParameter("covers");
		String coversValue = request.getParameter("coversValue");
		hdfcIntBean.setRequest_for(request.getParameter("request_for"));
		hdfcIntBean.setVehicleClassCode(request.getParameter("vehicleClassCode"));
		String manufacturerCode = request.getParameter("manufacturerCode");
		hdfcIntBean.setRegistrationDate(request.getParameter("registrationDate"));
		hdfcIntBean.setPrevPolicyEndDate(request.getParameter("prevPolicyEndDate"));
		String basedon_IDV_ExShowRoom = request
				.getParameter("basedon_IDV_ExShowRoom");
		// String exshowroomPrice = request.getParameter("exshowroomPrice");
		hdfcIntBean.setCustomerType(request.getParameter("customerType"));
		String customerAge = request.getParameter("customerAge");
		hdfcIntBean.setManufacturingYear(request.getParameter("manufacturingYear"));
		hdfcIntBean.setPrePolicyStartDate(request.getParameter("prePolicyStartDate"));
		String policyEndDate = request.getParameter("policyEndDate");
		String prePolStartDate = request.getParameter("prePolStartDate");
		hdfcIntBean.setExshowroomPrice(request.getParameter("exshowroomPrice"));
		String SumInsured = request.getParameter("SumInsured");
		String IsPreviousClaim = request.getParameter("IsPreviousClaim");
		System.out.println("IsPreviousClaim::"+IsPreviousClaim);
		String previousDiscount = request.getParameter("previousDiscount");
		String lpgCngKit = request.getParameter("lpgCngKit");
		String paidDriverSi = request.getParameter("paidDriverSi");
		String unnamedSi = request.getParameter("unnamedSi");
		String noOfEmployees = request.getParameter("noOfEmployees");
		String noOfllDrivers = request.getParameter("noOfllDrivers");
		String occupationType = request.getParameter("occupationType");
		String numIsRti = request.getParameter("numIsRti");
		String numIsEmrAsstCvr = request.getParameter("numIsEmrAsstCvr");
		String txtPlanType = request.getParameter("txtPlanType");
		String numIsEmrAsstWiderCvr = request
				.getParameter("numIsEmrAsstWiderCvr");
		String isNcbProtection = request.getParameter("isNcbProtection");
		String isEngineGearBoxProtection = request
				.getParameter("isEngineGearBoxProtection");
		String isCostOfConsumable = request.getParameter("isCostOfConsumable");
		String isLossOfUseDownProtection = request
				.getParameter("isLossOfUseDownProtection");
		String manufactureDate = request.getParameter("manufactureDate");
		String manufactureName = request.getParameter("manufactureName");
		String vehicleModelcode = request.getParameter("vehicleModelcode");
		String vehicleModel = request.getParameter("vehicleModel");
		String vehicleRegno = request.getParameter("vehicleRegno");
		String EngineNo = request.getParameter("EngineNo");
		String ChassisNo = request.getParameter("ChassisNo");
		String FuelType = request.getParameter("FuelType");
		String VehicleOwnedby = request.getParameter("VehicleOwnedby");
		String NameFinancialInstitution = request
				.getParameter("NameFinancialInstitution");
		String NCBExpiringPolicy = request.getParameter("NCBExpiringPolicy");
		String NCBRenewalPolicy = request.getParameter("NCBRenewalPolicy");
		String TotalPremium = request.getParameter("TotalPremium");
		String ServiceTax = request.getParameter("ServiceTax");
		String TotalAmoutpayable = request.getParameter("TotalAmoutpayable");
		String PreInsurerCode = request.getParameter("PreInsurerCode");
		String PrePolicyNumber = request.getParameter("PrePolicyNumber");
		String FirstName = request.getParameter("FirstName");
		String LastName = request.getParameter("LastName");
		String dob = request.getParameter("dob");
		String Gender = request.getParameter("Gender");
		String EmailId = request.getParameter("EmailId");
		String ContactnoOffice = request.getParameter("ContactnoOffice");
		String ContactnoHome = request.getParameter("ContactnoHome");
		String ContactnoMobile = request.getParameter("ContactnoMobile");
		String CarAddress1 = request.getParameter("CarAddress1");
		String CarAddress2 = request.getParameter("CarAddress2");
		String CarAddress3 = request.getParameter("CarAddress3");
		String nomineeName = request.getParameter("nomineeName");
		String nomineeAge = request.getParameter("nomineeAge");
		String nomineeRelation = request.getParameter("nomineeRelation");
		String CarCitycode = request.getParameter("CarCitycode");
		String CarCity = request.getParameter("CarCity");
		String CarState = request.getParameter("CarState");
		String CarStatecode = request.getParameter("CarStatecode");
		String CarPin = request.getParameter("CarPin");
		String CorresAddress1 = request.getParameter("CorresAddress1");
		String CorresAddress2 = request.getParameter("CorresAddress2");
		String CorresAddress3 = request.getParameter("CorresAddress3");
		String CorresCitycode = request.getParameter("CorresCitycode");
		String CorresCity = request.getParameter("CorresCity");
		String CorresStatecode = request.getParameter("CorresStatecode");
		String CorresState = request.getParameter("CorresState");
		String CorresPin = request.getParameter("CorresPin");
		String Data1 = request.getParameter("Data1");
		String Data2 = request.getParameter("Data2");
		String Data3 = request.getParameter("Data3");
		String Data4 = request.getParameter("Data4");
		String Data5 = request.getParameter("Data5");
		String IsEmergencyCover = request.getParameter("IsEmergencyCover");
		String OwnerDriverNomineeName = request
				.getParameter("OwnerDriverNomineeName");
		String OwnerDriverNomineeAge = request
				.getParameter("OwnerDriverNomineeAge");
		String OwnerDriverNomineeRelationship = request
				.getParameter("OwnerDriverNomineeRelationship");
		String OwnerDriverAppointeeName = request
				.getParameter("OwnerDriverAppointeeName");
		String OwnerDriverAppointeeRelationship = request
				.getParameter("OwnerDriverAppointeeName");
		String PANCard = request.getParameter("PANCard");
		String IsAgeDisc = request.getParameter("IsAgeDisc");
		String IsZeroDeptCover = request.getParameter("IsZeroDeptCover");
		String IsZeroDeptRollOver = request.getParameter("IsZeroDeptRollOver");
		String BiFuelType = request.getParameter("BiFuelType");
		System.out.println("BiFuelType::" + BiFuelType);
		String PlanType = request.getParameter("PlanType");
		String IsRTICover = request.getParameter("IsRTICover");
			String NoofNamedPassenger = request.getParameter("NoofNamedPassenger");
		String IsCustomerAuthenticationDone = request
				.getParameter("IsCustomerAuthenticationDone");
		String AuthenticationType = request.getParameter("AuthenticationType");
		String UIDNo = request.getParameter("UIDNo");
		hdfcIntBean.setMotorGroupResponseGroupId(request
				.getParameter("motorGroupResponseGroupId"));
		hdfcIntBean.setMotorGroupResponseSessionId(request
				.getParameter("motorGroupResponseSessionId"));
		hdfcIntBean.setMotorGroupResponseGicId(request
				.getParameter("motorGroupResponseGicId"));
		String zoneID = request.getParameter("zoneID");
		String policyType = request.getParameter("policyType");
		String financeId = request.getParameter("financeId");
		String prevGicId = request.getParameter("prevGicId");
		String carAreaId = request.getParameter("carAreaId");
		String corrsCarId = request.getParameter("corrsCarId");
		hdfcIntBean.setUserId(request.getParameter("userId"));
		hdfcIntBean.setUserDesc(request.getParameter("userDesc"));
		hdfcIntBean.setBranchId(request.getParameter("branchId"));
		String varid = request.getParameter("varid");
		String namedSi = request.getParameter("namedSi");
		String covVal = request.getParameter("covVal");
		String covNo = request.getParameter("covNo");
		String finalPrem = request.getParameter("finalPrem");
		String serviceTax = request.getParameter("serviceTax");
		String netPrem = request.getParameter("netPrem");
		hdfcIntBean.setiPAddress(request.getParameter("iPAddress"));
		String fuelType = request.getParameter("FuelType");
		String claimNo = request.getParameter("claimNo");
		String prePolicyNo = request.getParameter("prePolicyNo");
		String PanNo = request.getParameter("PanNo");
		String motorGroupResponseGicName = request.getParameter("motorGroupResponseGicName");
		String proposalType = request.getParameter("proposalType");
	
		
		bean.setUserId(hdfcIntBean.getUserId());
		bean.setUserDesc(hdfcIntBean.getUserDesc());
		bean.setBranchId(hdfcIntBean.getBranchId());
		bean.setGroupId(hdfcIntBean.getMotorGroupResponseGroupId());
		bean.setSessionId(hdfcIntBean.getMotorGroupResponseSessionId());
		bean.setGicId(hdfcIntBean.getMotorGroupResponseGicId());

		inputParaList = new HashMap<>();
		inputParaList.put("PI_TABLE_NAME", tableName);
		System.out.println("tableName--->>> " + tableName);

		System.out.println("ProcName--->>> " + procName);

		inputParaList.put("PI_Rto_City", rtoCity);
		System.out.println("rtoCity-->>>" + rtoCity);

		inputParaList.put("PI_ZONE_ID", zoneID);
		System.out.println("ZoneId-->>>" + zoneID);

		inputParaList.put("PI_PolicyType", policyType);
		System.out.println("policyType--->>>> " + policyType);

		inputParaList.put("PI_VarID", varid);
		System.out.println("variance--->>>> " + varid);

		inputParaList.put("PI_P_BusinessType", businessType);
		System.out.println("businessType--->>>> " + businessType);

		inputParaList.put("PI_ProductID", hdfcIntBean.getVehicleClassCode());
		System.out.println("vehicleClassCode--->>>> " + hdfcIntBean.getVehicleClassCode());

		inputParaList.put("PI_VehID", manufacturerCode);
		System.out.println("manufacturerCode--->>>> " + manufacturerCode);

		inputParaList.put("PI_ModelID", vehicleModelCode);
		System.out.println("vehicleModelCode--->>>> " + vehicleModelCode);

		inputParaList.put("PI_CustAge", customerAge);
		System.out.println("customerAge--->>>> " + customerAge);

		inputParaList.put("PI_CUST_TYPE", hdfcIntBean.getCustomerType());
		System.out.println("customerType--->>>> " + hdfcIntBean.getCustomerType());

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
if(covers != null && ! covers.isEmpty())
{
	System.out.println("In covers not Empty");
	
}else{
	covers="";
}
		
		inputParaList.put("PI_COVERS", covers);
		System.out.println("cover--->>>> " + covers);

		inputParaList.put("PI_COV_VAL", covVal);
		System.out.println("CovVal------>> " + covVal);

		inputParaList.put("pi_cov_no", covNo);
		System.out.println("CoverNo---->>>> " + covNo);
		
		inputParaList.put("PI_CLAIMNO", claimNo);
		System.out.println("PI_CLAIMNO---->>>> " + claimNo);

		inputParaList.put("PI_Nom_Rel", OwnerDriverNomineeRelationship);

		System.out.println("OwnerDriverNomineeRelationship--->>>> "
				+ OwnerDriverNomineeRelationship);

		inputParaList.put("PI_Own_Dri_Appointee_Rel",
				Integer.parseInt(OwnerDriverAppointeeRelationship));

		System.out.println("OwnerDriverAppointeeRelationship--->>>> "
				+ OwnerDriverAppointeeRelationship);
		System.out.println("basedon_IDV_ExShowRoom--->>>"
				+ basedon_IDV_ExShowRoom);

		// HashMap json = new HashMap<>();

		System.out.println("inputParaList ::" +inputParaList);
		System.out.println("JSON::" + json);
		jsonNames = hdfcDao.getHDFCData("PKG_MOTOR_CALC", procName,
				inputParaList);
		
		System.out.println("jsonNames -- " + jsonNames);

		String jsonNamesArr[] = jsonNames.split("\\}\\]\\[\\{");
		
		jsonNames = jsonNamesArr[0];
		String coverList1 = "";
		if (jsonNamesArr.length > 1) {
			coverList1 = jsonNamesArr[1];
			System.out.println("coverList1->" + coverList1);
			coverList1 = "[{" + coverList1;
			jsonNames = jsonNames + "}]";
		}
		
		System.out.println("jsonName controller::: " + jsonNames);
		System.out.println("coverList Controller::: " + coverList1);

		// //////////////////////////////////////////////////////
		String NonElectical_Acc = "0",engineGearBox="0", PaidDriverSi = "0", Electical_Acc = "0", pa_cover_owner_driver = "0", ZeroDept_Cover = "0", UnNamed_Passenger = "0", UnnamedSi = "0";
		if (!coverList1.equals("")) {
			String coverId = "", coverNo = "", coverVal = "";
			JSONArray coverJson = new JSONArray(coverList1);
			for (int table = 0; table < coverJson.length(); ++table) {
				org.json.JSONObject obj = (org.json.JSONObject) coverJson
						.get(table);
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
					coverVal = "" + obj.getString("COVER_VAL");
					System.out.println("1111111111 coverVal==>>" + coverVal);
				}
				if (coverId.equals("2")) {
					System.out.println("cover 2------------>>" + coverId);
					NonElectical_Acc = coverVal;
				}
				if (coverId.equals("9")) {
					System.out.println("cover 9------------>>" + coverId);
					System.out.println("CoverVal->>" + coverVal);
					Electical_Acc = coverVal;
				}
				if (coverId.equals("53")) {
					System.out.println("cover 53------------>>" + coverId);
					System.out.println("CoverVal->>" + coverVal);
					pa_cover_owner_driver = "1";
				}
				if (coverId.equals("8")) {
					System.out.println("cover 8------------>>" + coverId);
					System.out.println("CoverVal->>" + coverVal);
					ZeroDept_Cover = "1";
				}
				if (coverId.equals("54")) {
					System.out.println("cover 54------------>>" + coverId);
					System.out.println("CoverVal->>" + coverVal);
					UnNamed_Passenger = coverNo;
					UnnamedSi = coverVal;
				}
				if (coverId.equals("56")) {
					System.out.println("cover 54------------>>" + coverId);
					System.out.println("CoverVal->>" + coverVal);
					PaidDriverSi = coverVal;
				}
				if (coverId.equals("32")) {
					System.out.println("Engine Gear Box cover 32------------>>" + coverId);
					System.out.println("CoverVal->>" + coverVal);
					engineGearBox = "1";
				}

			}
		}

		// *******************************************************************************
		json.put("basedon_IDV_ExShowRoom", basedon_IDV_ExShowRoom);
		json.put("manufacturingYear", hdfcIntBean.getManufacturingYear());
		json.put("registrationDate", hdfcIntBean.getRegistrationDate());
		json.put("prePolicyStartDate", "01/01/1990");
		json.put("prevPolicyEndDate", "01/01/1990");
		json.put("exshowroomPrice", hdfcIntBean.getExshowroomPrice());
		json.put("SumInsured", SumInsured);
		System.out.println("SumInsured->" + SumInsured);
		json.put("rtoLocationCode", rtoLocationCode);
		json.put("IsPreviousClaim", IsPreviousClaim);
		json.put("previousDiscount", previousDiscount);
		json.put("electicalAcc", Electical_Acc);
		json.put("nonElecticalAcc", NonElectical_Acc);
		json.put("lpgCngKit", lpgCngKit);
		json.put("paidDriverSi", PaidDriverSi);
		json.put("unnamedSi", UnnamedSi);
		json.put("noOfEmployees", noOfEmployees);
		json.put("noOfllDrivers", noOfllDrivers);
		json.put("isPaCoverOwnerDriver", pa_cover_owner_driver);
		json.put("numIsRti", numIsRti);
		json.put("numIsEmrAsstCvr", numIsEmrAsstCvr);
		json.put("numIsZeroDept", ZeroDept_Cover);
		json.put("txtPlanType", txtPlanType);
		json.put("numIsEmrAsstWiderCvr", numIsEmrAsstWiderCvr);
		json.put("isNcbProtection", isNcbProtection);
		json.put("isEngineGearBoxProtection", engineGearBox);
		json.put("isCostOfConsumable", isCostOfConsumable);
		json.put("isLossOfUseDownProtection", isLossOfUseDownProtection);
		// //////////////////////////////////////////////////////
		
			
		if((BiFuelType.toUpperCase()).contains("LPG")){
			BiFuelType="LPG";
		}else if((BiFuelType.toUpperCase()).contains("CNG")){
			BiFuelType="CNG";
		}
		else if((BiFuelType.toUpperCase()).contains("--Select--")){
			BiFuelType="CNG";
		}
		
		
		jsonPremium.put("manufacturingYear", hdfcIntBean.getManufacturingYear());
		jsonPremium.put("exshowroomPrice", hdfcIntBean.getExshowroomPrice());
		jsonPremium.put("vehicleRegno", vehicleRegno);
		jsonPremium.put("EngineNo", EngineNo);
		jsonPremium.put("ChassisNo", ChassisNo);
		jsonPremium.put("SumInsured", SumInsured);
		jsonPremium.put("lpgCngKit", lpgCngKit);
		jsonPremium.put("FirstName", FirstName);
		jsonPremium.put("LastName", LastName);
		jsonPremium.put("dob", dob);
		jsonPremium.put("Gender", Gender);
		jsonPremium.put("EmailId", EmailId);
		jsonPremium.put("ContactnoMobile", ContactnoMobile);
		jsonPremium.put("CarAddress1", CarAddress1);
		jsonPremium.put("CarAddress2", CarAddress2);
		jsonPremium.put("CarAddress3", CarAddress3);
		jsonPremium.put("CorresAddress1", CorresAddress1);
		jsonPremium.put("CorresAddress2", CorresAddress2);
		jsonPremium.put("CorresAddress3", CorresAddress3);
		jsonPremium.put("nomineeName", nomineeName);
		jsonPremium.put("nomineeAge", nomineeAge);
		jsonPremium.put("nomineeRelation", nomineeRelation);
		jsonPremium.put("LocationCode", rtoLocationCode);
		jsonPremium.put("prePolicyStartDate", hdfcIntBean.getPrePolicyStartDate());
		jsonPremium.put("prevPolicyEndDate", hdfcIntBean.getPrevPolicyEndDate());
		jsonPremium.put("Data1", Data1);
		jsonPremium.put("IsAgeDisc", IsAgeDisc);
		jsonPremium.put("electicalAcc", Electical_Acc);
		jsonPremium.put("nonElecticalAcc", NonElectical_Acc);
		jsonPremium.put("noOfllDrivers", noOfllDrivers);
		jsonPremium.put("paidDriverSi", PaidDriverSi);
		jsonPremium.put("noOfEmployees", noOfEmployees);
		jsonPremium.put("unnamedSi", UnnamedSi);
		jsonPremium.put("IsPreviousClaim", IsPreviousClaim);
		jsonPremium.put("NCBExpiringPolicy", NCBExpiringPolicy);
		jsonPremium.put("NCBRenewalPolicy", NCBRenewalPolicy);
		jsonPremium.put("IsEmergencyCover", IsEmergencyCover);
		jsonPremium.put("isPaCoverOwnerDriver", pa_cover_owner_driver);
		jsonPremium.put("IsZeroDeptCover", ZeroDept_Cover);
		jsonPremium.put("IsZeroDeptRollOver", IsZeroDeptRollOver);
		jsonPremium.put("IsRTICover", IsRTICover);
		jsonPremium.put("NoofUnNamedPassenger", UnNamed_Passenger);
		jsonPremium.put("NoofNamedPassenger", NoofNamedPassenger);
		jsonPremium.put("namedSi", namedSi);
		jsonPremium.put("numIsEmrAsstWiderCvr", numIsEmrAsstWiderCvr);
		jsonPremium.put("isNcbProtection", isNcbProtection);
		jsonPremium.put("isEngineGearBoxProtection", engineGearBox);
		jsonPremium.put("isCostOfConsumable", isCostOfConsumable);
		jsonPremium.put("isLossOfUseDownProtection", isLossOfUseDownProtection);
		jsonPremium.put("serviceTax", serviceTax);
		jsonPremium.put("finalPrem", finalPrem);
		jsonPremium.put("netPrem", netPrem);
		jsonPremium.put("prePolicyNo", prePolicyNo);
		jsonPremium.put("fuelType", fuelType);
		jsonPremium.put("PanNo", PanNo);
		jsonPremium.put("BiFuelType", BiFuelType);

		if (hdfcIntBean.getCustomerType().equals("1")) {
			hdfcIntBean.setCustomerTypeHdfc("O");
		} else if (hdfcIntBean.getCustomerType().equals("0")) {
			hdfcIntBean.setCustomerTypeHdfc("I");
		}
		
		switch (hdfcIntBean.getRequest_for()) {
		case "coverage":
			hashmap.clear();
			String coverList = getCoversList(jsonNames);
			System.out.println("Case1 Coverage--------->>" + coverList);
			hashmap.put(coverList, "");
			break;
		case "idv":
			hashmap.clear();
			String xmlString = xmlFileIdv(jsonNames);
			String idv = getIdv(xmlString);
			System.out.println("Case2 idv--------->>" + idv);
			if(!idv.isEmpty()){
				hashmap = readResponce(idv);
			}
			
			System.out.println("Idv HAshmap::: " + hashmap);
			Iterator entries = hashmap.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String mapKey = (String) entry.getKey();
				if (mapKey.equalsIgnoreCase("exshowroomPrice")) {
					hdfcIntBean.setMapExShowroomPrice((null == entry.getValue()) ? ""
							: entry.getValue().toString());
					System.out.println("mapExShowroomPrice-->>>"
							+ hdfcIntBean.getMapExShowroomPrice());
				}
			}

			break;
		case "premium":
			hashmap.clear();
			Gson gson = new Gson();
			json.toString();
			String json1 = json.toString();
			 System.out.println("jsonNames}}}}>>" +jsonNames);
			 System.out.println("json1}}}}>>" +json1);
			
			
			String jsons = jsonNames + json1;
			
			jsons = jsons.replace("[{", "{");
			jsons = jsons.replace("}]{", ",");

			if(jsons.contains("},{"))
			{
				jsons = jsons.replace("},{", ",");
			}
			jsons = "[" + jsons + "]";

			System.out.println("s3======= " + jsons);

			xmlString = xmlFilePremium(jsons);
			hdfcIntBean.setPremium(getPremium(xmlString, Integer.parseInt(hdfcIntBean.getVehicleClassCode())));
			System.out.println("Case3 premium--------->>" + hdfcIntBean.getPremium());
			
			
			if(!hdfcIntBean.getPremium().isEmpty()){
				hashmapPremium = readResponce(hdfcIntBean.getPremium());
			}
			System.out.println("Premium hashmap-->>>" + hashmap);
			if (jsonNames.contains("[")) {
				jsonNames = jsonNames.replace("[", "");
			}
			if (jsonNames.contains("]")) {
				jsonNames = jsonNames.replace("]", "");
			}
			jsonNames = jsonNames.substring(1, jsonNames.length() - 1); // remove
																		// curly
																		// brackets
			String[] keyValuePairs1 = jsonNames.split(","); // split the string
															// to creat
															// key-value pairs

			for (String pair : keyValuePairs1) // iterate over the pairs
			{
				String[] entry = pair.split(":"); // split the pairs to get key
													// and value
				hashmapinputs.put((entry[0].trim()).replaceAll("\"", ""),
						(entry[1].trim()).replaceAll("\"", ""));
			}
			System.out.println("hashmapinputs::" + hashmapinputs);

			
			hashmap.putAll(hashmapPremium);
			if(!hashmapPremium.isEmpty())
			{
				hashmap.put("GICNAME", motorGroupResponseGicName);
				hashmap.put("PROPOSALTYPE", proposalType);
				hashmap.put("GICID", hdfcIntBean.getMotorGroupResponseGicId());
				hashmap.put("POLICYTENURE", "1");
				hashmap.putAll(hashmapinputs);
				String procedureName = "PR_PREMIUM";
				savePreMiumProposalData(hashmap,procedureName);
				
				// System.out.println("premium Hashmap::: " + hashmap);
				 Iterator entriesIterator = hashmap.entrySet().iterator();
				 while (entriesIterator.hasNext()) {
				 Map.Entry entry = (Map.Entry) entriesIterator.next();
				 String mapKey = (String) entry.getKey();
				 if (mapKey.equalsIgnoreCase("AGENTCODE")) {
					 hdfcIntBean.setMenuCode((null == entry.getValue()) ? "" : entry.getValue().toString());
				System.out.println("menuCode:::" + hdfcIntBean.getMenuCode());
				 }
				 }
			}else{
				hashmap.put("XMLError", "Error");
			}
			 
			break;

		case "proposal":
			hashmap.clear();
			Gson gson2 = new Gson();
//			jsonPremium.toString();
			String json2 = jsonPremium.toString();
			String jsons1 = jsonNames + json2;
			jsons1 = jsons1.replace("[{", "{");
			jsons1 = jsons1.replace("}]{", ",");

			jsons1 = "[" + jsons1 + "]";
			System.out.println("s3======= " + jsons1);
			xmlString = xmlProposal(jsons1);
			hdfcIntBean.setProposal(getProposal(xmlString));
			System.out.println("Case4 proposal--------->>" + hdfcIntBean.getProposal());
			if(!hdfcIntBean.getProposal().isEmpty()){
				hashmapProposal = readResponce(hdfcIntBean.getProposal());
			}
			if (jsonNames.contains("[")) {
				jsonNames = jsonNames.replace("[", "");
			}
			if (jsonNames.contains("]")) {
				jsonNames = jsonNames.replace("]", "");
			}

			jsonNames = jsonNames.substring(1, jsonNames.length() - 1); // remove
																		// curly
																		// brackets
			String[] keyValuePairs = jsonNames.split(","); // split the string
															// to creat
															// key-value pairs

			for (String pair : keyValuePairs) // iterate over the pairs
			{
				String[] entry = pair.split(":"); // split the pairs to get key
													// and value
				hashmapinputs.put((entry[0].trim()).replaceAll("\"", ""),
						(entry[1].trim()).replaceAll("\"", "")); // add them to
																	// the
																	// hashmap
																	// and trim
																	// whitespaces
			}
			if (json2.contains("[")) {
				json2 = json2.replace("[", "");
			}
			if (json2.contains("]")) {
				json2 = json2.replace("]", "");
			}

			json2 = json2.substring(1, json2.length() - 1); // remove curly
															// brackets
			String[] keyValuePairsInput = json2.split(","); // split the string
															// to creat
															// key-value pairs

			for (String pair : keyValuePairsInput) // iterate over the pairs
			{
				String[] entry = pair.split(":"); // split the pairs to get key
													// and value
				hashmapreqParm.put((entry[0].trim()).replaceAll("\"", ""),
						(entry[1].trim()).replaceAll("\"", "")); // add them to
																	// the
																	// hashmap
																	// and trim
																	// whitespaces
			}

			System.out.println("hashmapinputs::" + hashmapinputs);
			
			
			if(!hashmapProposal.isEmpty())
			{
				hashmap.putAll(hashmapProposal);
				hashmap.putAll(hashmapreqParm);
				hashmap.putAll(hashmapinputs);
				System.out.println("proposal HAshmap::: " + hashmap);
				Iterator entriesIterator2 = hashmapProposal.entrySet().iterator();
				while (entriesIterator2.hasNext()) {
					Map.Entry entry = (Map.Entry) entriesIterator2.next();
					String mapKey = (String) entry.getKey();
					if (mapKey.equalsIgnoreCase("WsMessage")) {
						hdfcIntBean.setProposalNo((null == entry.getValue()) ? "" : entry
								.getValue().toString());
					}
					if (mapKey.equalsIgnoreCase("WsStatus")) {
						hdfcIntBean.setProposalStatus((null == entry.getValue()) ? "" : entry
								.getValue().toString());
					}
				}

				System.out.println("proposalNo==>>" + hdfcIntBean.getProposalNo());
				System.out.println("proposalStatus==>>" + hdfcIntBean.getProposalStatus());
				bean.setProposalNo(hdfcIntBean.getProposalNo());
				bean.setProposalStatus(hdfcIntBean.getProposalStatus());
				
				Iterator entriesIterator3 = hashmapProposal.entrySet().iterator();
				while (entriesIterator3.hasNext()) {
					System.out.println("entriesIterator3:"+entriesIterator3);
					Map.Entry entry = (Map.Entry) entriesIterator3.next();
					String mapKey = (String) entry.getKey();
					if (mapKey.equalsIgnoreCase("WsMessage")) {
						entriesIterator3.remove();
					}
				}
				
				if(hdfcIntBean.getProposalNo().contains("'"))
				{
					hdfcIntBean.setProposalNo(hdfcIntBean.getProposalNo().replace("'", ""));
				}
				
				hashmap.put("WsMessage",hdfcIntBean.getProposalNo());
				
				String procedureName1 = "PR_PROPOSAL";
				savePreMiumProposalData(hashmap, procedureName1);
			}else{
				hashmap.put("XMLError", "Error");
			}
			
			
			break;

		default:
			break;
		}

		ObjectMapper mapperObj = new ObjectMapper();
		String jsonFinalResp = mapperObj.writeValueAsString(hashmap);
        System.out.println("jsonFinalResp==>>"+jsonFinalResp);
		
        String finalRespo= "["+	jsonFinalResp +"]";
        System.out.println("finalRespoWith Brackets=====>>"+finalRespo);
		
		return finalRespo;
	}

	private void savePreMiumProposalData(HashMap hashmap2, String procedureName) {
		 	bean.setUserId(hdfcIntBean.getUserId());
			bean.setUserDesc(hdfcIntBean.getUserDesc());
			bean.setBranchId(hdfcIntBean.getBranchId());
			bean.setGroupId(hdfcIntBean.getMotorGroupResponseGroupId());
			bean.setSessionId(hdfcIntBean.getMotorGroupResponseSessionId());
			bean.setGicId(hdfcIntBean.getMotorGroupResponseGicId());
			bean.setResponseType("1");
					
			Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(hdfcIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(hdfcIntBean.getMotorGroupResponseGicId()), hdfcIntBean.getMotorGroupResponseSessionId(),
					1, hashmap2, hdfcIntBean.getiPAddress(), hdfcIntBean.getUserId(), hdfcIntBean.getBranchId(),
					hdfcIntBean.getUserDesc(), procedureName);
		
	}


	private String getProposal(String xmlString2) {

		com.hdfc.proposal.Service servicePrposal = new com.hdfc.proposal.Service();
		com.hdfc.proposal.ServiceSoap apiProposal = servicePrposal
				.getServiceSoap();
		Xmlstring xmlstring = new Xmlstring();
		xmlstring.setStr(xmlString2);
		String xmlString = apiProposal.xmlstring(xmlString2);

		return xmlString;
	}

	private String getPremium(String xmlString2, int vehicleClassCode) {
		Service sevice1 = new Service();
		ServiceSoap api = sevice1.getServiceSoap();
		GetPremium premium = new GetPremium();
		premium.setStr(xmlString2);
		premium.setVehicleClassCode(vehicleClassCode);
		String getPremium = api.getPremium(xmlString2, vehicleClassCode);
		System.out.println("getPremium:" +getPremium );
		return getPremium;
	}

	private String getIdv(String xmlString) {
		Service sevice1 = new Service();
		ServiceSoap api = sevice1.getServiceSoap();
		GetIDV idv = new GetIDV();
		idv.setStr(xmlString);
		String IDV = api.getIDV(xmlString);
		System.out.println("Idv--->>>>> " + IDV);

		return IDV;
	}

	private String getCoversList(String jsonNames) {
		Service sevice1 = new Service();
		ServiceSoap api = sevice1.getServiceSoap();
		String covers = "";
		GetPlanTypes planType = new GetPlanTypes();
		try {
			// System.out.println("jsonNames"+jsonNames);
			JSONArray jsonarray = new JSONArray(jsonNames);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonResult = jsonarray.getJSONObject(i);
				 String agentCode = jsonResult.getString("AGENTCODE").trim();
//				String agentCode = "FWC00015";
				String businessType = jsonResult.getString("TYPE_OF_BUSINESS")
						.trim();
				String firstRegistrationDate = hdfcIntBean.getRegistrationDate();
				String policyStartDate = jsonResult.getString(
						"POLICY_STARTDATE").trim();
				String rtoLocationCode = jsonResult.getString(
						"REGISTRATION_CITYCODE").trim();
				String vehicleModelCode = jsonResult.getString(
						"VEHICLE_MODELCODE").trim();

				planType.setAgentCode(agentCode);
				planType.setBusinessType(businessType);
				planType.setFirstRegistrationDate(firstRegistrationDate);
				planType.setPolicyStartDate(policyStartDate);
				planType.setRTOLocationCode(rtoLocationCode);
				planType.setVehicleModelCode(vehicleModelCode);

				covers = api.getPlanTypes(agentCode, businessType,
						vehicleModelCode, rtoLocationCode, policyStartDate,
						firstRegistrationDate);
				System.out.println("covers ::::" + covers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return covers;
	}

	public String xmlFileIdv(String jsonNames) {
		String xx = "";
		Document document2;
		org.w3c.dom.Document document = null;
		System.out.println("IDV JsonNames:>>>>>>" + jsonNames);
		try {
			System.out.println("IDV jsonNames" + jsonNames);
			JSONArray jsonarray = new JSONArray(jsonNames);
			System.out.println("IDV jsonarray" + jsonarray);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonResult = jsonarray.getJSONObject(i);
				System.out.println("IDV Result<<<<<<:" + jsonResult);
				org.jdom2.Element rootelement = new org.jdom2.Element("IDV");
				document2 = new Document(rootelement);

				org.jdom2.Element policyStartDate = new org.jdom2.Element(
						"policy_start_date");
				policyStartDate.setText(jsonResult
						.getString("POLICY_STARTDATE").trim());

				org.jdom2.Element vehicleClassCode = new org.jdom2.Element(
						"vehicle_class_cd");
				vehicleClassCode.setText(this.hdfcIntBean.getVehicleClassCode());

				org.jdom2.Element rtoLocationCode = new org.jdom2.Element(
						"RTOLocationCode");
				rtoLocationCode.setText(jsonResult.getString(
						"REGISTRATION_CITYCODE").trim());

				org.jdom2.Element vehicleModelCode = new org.jdom2.Element(
						"vehiclemodelcode");
				vehicleModelCode.setText(jsonResult.getString(
						"VEHICLE_MODELCODE").trim());

				org.jdom2.Element manufactureCode = new org.jdom2.Element(
						"manufacturer_code");
				manufactureCode.setText(jsonResult.getString(
						"MANUFACTURER_CODE").trim());

				org.jdom2.Element purchaseRegnDate = new org.jdom2.Element(
						"purchaseregndate");
				purchaseRegnDate.setText(hdfcIntBean.getRegistrationDate().trim());

				org.jdom2.Element prevPolicyEnddate = new org.jdom2.Element(
						"prev_policy_end_date");
				prevPolicyEnddate.setText(hdfcIntBean.getPrevPolicyEndDate().trim());

				org.jdom2.Element typeOfBusiness = new org.jdom2.Element(
						"typeofbusiness");
				typeOfBusiness.setText(jsonResult.getString("TYPE_OF_BUSINESS")
						.trim());

				rootelement.addContent(policyStartDate);
				rootelement.addContent(vehicleClassCode);
				rootelement.addContent(rtoLocationCode);
				rootelement.addContent(vehicleModelCode);
				rootelement.addContent(manufactureCode);
				rootelement.addContent(purchaseRegnDate);
				rootelement.addContent(prevPolicyEnddate);
				rootelement.addContent(typeOfBusiness);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xx = xmlOutput.outputString(document2);
				xmlOutput.output(document2, new FileWriter(path+"\\getIDVRequest.xml"));

				File fXmlFile = new File("D:\\file.xml");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return xx;
	}

	public String xmlFilePremium(String jsonNames) {
		String xx = "";
		Document document2;
		System.out.println("Premium JsonNames:>>>>>>" + jsonNames);
		try {
			JSONArray jsonarray = new JSONArray(jsonNames);
			System.out.println("jsonarray" + jsonarray);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonResult = jsonarray.getJSONObject(i);
				System.out.println("Premium Result<<<<<<:" + jsonResult);
				org.jdom2.Element rootelement = new org.jdom2.Element(
						"PCVPremiumCalc");
				document2 = new Document(rootelement);

				org.jdom2.Element agentcode = new org.jdom2.Element("agentcode");
				 agentcode.setText(jsonResult.getString("AGENTCODE").trim());
//				agentcode.setText("FWC00015");

				org.jdom2.Element typeofbusiness = new org.jdom2.Element(
						"typeofbusiness");
				typeofbusiness.setText(jsonResult.getString("TYPE_OF_BUSINESS")
						.trim());

				org.jdom2.Element basedon_IDV_ExShowRoom = new org.jdom2.Element(
						"basedon_IDV_ExShowRoom");
				basedon_IDV_ExShowRoom.setText("1");

				org.jdom2.Element txt_cust_type = new org.jdom2.Element(
						"txt_cust_type");
				txt_cust_type.setText(jsonResult.getString("VEHICLE_OWNEDBY").trim());

				
				
				org.jdom2.Element custage = new org.jdom2.Element("custage");
				custage.setText(jsonResult.getString("CUSTAGE").trim());

				org.jdom2.Element manufacturingyear = new org.jdom2.Element(
						"manufacturingyear");
				manufacturingyear.setText(hdfcIntBean.getManufacturingYear());

				org.jdom2.Element policystartdate = new org.jdom2.Element(
						"policystartdate");
				policystartdate.setText(jsonResult
						.getString("POLICY_STARTDATE").trim());

				org.jdom2.Element policyenddate = new org.jdom2.Element(
						"policyenddate");
				policyenddate.setText(jsonResult.getString("POLICY_ENDDATE")
						.trim());

				org.jdom2.Element purchaseregndate = new org.jdom2.Element(
						"purchaseregndate");
				purchaseregndate.setText(hdfcIntBean.getRegistrationDate());

				org.jdom2.Element prepolstartdate = new org.jdom2.Element(
						"prepolstartdate");
				prepolstartdate.setText("01/01/1990");

				org.jdom2.Element prepolicyenddate = new org.jdom2.Element(
						"prepolicyenddate");
				prepolicyenddate.setText("01/01/1990");

				org.jdom2.Element exshowroomprice = new org.jdom2.Element(
						"exshowroomprice");
				exshowroomprice.setText(jsonResult.getString("exshowroomPrice")
						.trim());

				org.jdom2.Element Sum_Insured = new org.jdom2.Element(
						"Sum_Insured");
				Sum_Insured.setText(jsonResult.getString("SumInsured").trim());
//				Sum_Insured.setText("385410.0");

				org.jdom2.Element vehiclemodelcode = new org.jdom2.Element(
						"vehiclemodelcode");
				vehiclemodelcode.setText(jsonResult.getString(
						"VEHICLE_MODELCODE").trim());

				org.jdom2.Element manufacturer_code = new org.jdom2.Element(
						"manufacturer_code");
				manufacturer_code.setText(jsonResult.getString(
						"MANUFACTURER_CODE").trim());

				org.jdom2.Element rtolocationcode = new org.jdom2.Element(
						"rtolocationcode");
				rtolocationcode.setText(jsonResult.getString(
						"REGISTRATION_CITYCODE").trim());

				org.jdom2.Element IsPreviousClaim = new org.jdom2.Element(
						"IsPreviousClaim");
				IsPreviousClaim.setText(jsonResult.getString("IsPreviousClaim")
						.trim());

				org.jdom2.Element previousdiscount = new org.jdom2.Element(
						"previousdiscount");
				previousdiscount.setText(jsonResult.getString(
						"previousDiscount").trim());

				org.jdom2.Element electicalacc = new org.jdom2.Element(
						"electicalacc");
				electicalacc.setText(jsonResult.getString("electicalAcc")
						.trim());

				org.jdom2.Element nonelecticalacc = new org.jdom2.Element(
						"nonelecticalacc");
				nonelecticalacc.setText(jsonResult.getString("nonElecticalAcc")
						.trim());

				org.jdom2.Element lpg_cngkit = new org.jdom2.Element(
						"lpg_cngkit");
				lpg_cngkit.setText(jsonResult.getString("lpgCngKit").trim());

				org.jdom2.Element paiddriversi = new org.jdom2.Element(
						"paiddriversi");
				paiddriversi.setText(jsonResult.getString("paidDriverSi")
						.trim());

				org.jdom2.Element unnamedsi = new org.jdom2.Element("unnamedsi");
				unnamedsi.setText(jsonResult.getString("unnamedSi").trim());

				org.jdom2.Element noofemployees = new org.jdom2.Element(
						"noofemployees");
				noofemployees.setText(jsonResult.getString("noOfEmployees")
						.trim());

				org.jdom2.Element nooflldrivers = new org.jdom2.Element(
						"nooflldrivers");
				nooflldrivers.setText(jsonResult.getString("noOfllDrivers")
						.trim());

				org.jdom2.Element occupationtype = new org.jdom2.Element(
						"occupationtype");
				occupationtype.setText("0");

				org.jdom2.Element is_pa_cover_owner_driver = new org.jdom2.Element(
						"is_pa_cover_owner_driver");
//				is_pa_cover_owner_driver.setText(jsonResult.getString(
//						"isPaCoverOwnerDriver").trim());
				is_pa_cover_owner_driver.setText("1");
				
				org.jdom2.Element num_is_rti = new org.jdom2.Element(
						"num_is_rti");
				num_is_rti.setText(jsonResult.getString("numIsRti").trim());

				org.jdom2.Element num_is_emr_asst_cvr = new org.jdom2.Element(
						"num_is_emr_asst_cvr");
				num_is_emr_asst_cvr.setText(jsonResult.getString(
						"numIsEmrAsstCvr").trim());

				org.jdom2.Element num_is_zero_dept = new org.jdom2.Element(
						"num_is_zero_dept");
				num_is_zero_dept.setText(jsonResult.getString("numIsZeroDept")
						.trim());

				org.jdom2.Element txt_plan_type = new org.jdom2.Element(
						"txt_plan_type");
				txt_plan_type.setText(jsonResult.getString("txtPlanType")
						.trim());

				org.jdom2.Element num_is_emr_asst_wider_cvr = new org.jdom2.Element(
						"num_is_emr_asst_wider_cvr");
				num_is_emr_asst_wider_cvr.setText(jsonResult.getString(
						"numIsEmrAsstWiderCvr").trim());

				org.jdom2.Element AddOnCovers = new org.jdom2.Element(
						"AddOnCovers");

				org.jdom2.Element is_ncb_protection = new org.jdom2.Element(
						"is_ncb_protection");
				is_ncb_protection.setText(jsonResult.getString(
						"isNcbProtection").trim());

				org.jdom2.Element is_engine_gear_box_protection = new org.jdom2.Element(
						"is_engine_gear_box_protection");
				is_engine_gear_box_protection.setText(jsonResult.getString(
						"isEngineGearBoxProtection").trim());

				org.jdom2.Element is_cost_of_consumable = new org.jdom2.Element(
						"is_cost_of_consumable");
				is_cost_of_consumable.setText(jsonResult.getString(
						"isCostOfConsumable").trim());

				org.jdom2.Element is_loss_of_use_down_protection = new org.jdom2.Element(
						"is_loss_of_use_down_protection");
				is_loss_of_use_down_protection.setText(jsonResult.getString(
						"isLossOfUseDownProtection").trim());

				rootelement.addContent(agentcode);
				rootelement.addContent(typeofbusiness);
				rootelement.addContent(basedon_IDV_ExShowRoom);
				rootelement.addContent(txt_cust_type);
				rootelement.addContent(custage);
				rootelement.addContent(manufacturingyear);
				rootelement.addContent(policystartdate);
				rootelement.addContent(policyenddate);
				rootelement.addContent(purchaseregndate);
				rootelement.addContent(prepolstartdate);
				rootelement.addContent(prepolicyenddate);
				rootelement.addContent(exshowroomprice);
				rootelement.addContent(Sum_Insured);
				rootelement.addContent(vehiclemodelcode);
				rootelement.addContent(manufacturer_code);
				rootelement.addContent(rtolocationcode);
				rootelement.addContent(IsPreviousClaim);
				rootelement.addContent(previousdiscount);
				rootelement.addContent(electicalacc);
				rootelement.addContent(nonelecticalacc);
				rootelement.addContent(lpg_cngkit);
				rootelement.addContent(paiddriversi);
				rootelement.addContent(unnamedsi);
				rootelement.addContent(noofemployees);
				rootelement.addContent(nooflldrivers);
				rootelement.addContent(occupationtype);
				rootelement.addContent(is_pa_cover_owner_driver);
				rootelement.addContent(num_is_rti);
				rootelement.addContent(num_is_emr_asst_cvr);
				rootelement.addContent(num_is_zero_dept);
				rootelement.addContent(txt_plan_type);
				rootelement.addContent(num_is_emr_asst_wider_cvr);
				rootelement.addContent(AddOnCovers);
				AddOnCovers.addContent(is_ncb_protection);
				AddOnCovers.addContent(is_engine_gear_box_protection);
				AddOnCovers.addContent(is_cost_of_consumable);
				AddOnCovers.addContent(is_loss_of_use_down_protection);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xx = xmlOutput.outputString(document2);
				xmlOutput.output(document2, new FileWriter(path+"\\getPremiumRequest.xml"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return xx;
	}

	public String xmlProposal(String jsonNames) {
		String xx = "";
		Document document2;
		System.out.println("Proposal JsonNames:>>>>>>" + jsonNames);
		try {
			System.out.println("Proposal jsonNames" + jsonNames);
			JSONArray jsonarray = new JSONArray(jsonNames);
			System.out.println("Proposal jsonarray" + jsonarray);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonResult = jsonarray.getJSONObject(i);
				System.out.println("Proposal<<<<<<:" + jsonResult);

				org.jdom2.Element rootelement = new org.jdom2.Element(
						"xmlmotorpolicy");
				document2 = new Document(rootelement);

				org.jdom2.Element AgentCode = new org.jdom2.Element("AgentCode");
				 AgentCode.setText(jsonResult.getString("AGENTCODE").trim());
//				AgentCode.setText("FWC00015");

				org.jdom2.Element Type_of_Business = new org.jdom2.Element(
						"Type_of_Business");
				Type_of_Business.setText(jsonResult.getString(
						"TYPE_OF_BUSINESS").trim());

				org.jdom2.Element Policy_Startdate = new org.jdom2.Element(
						"Policy_Startdate");
				Policy_Startdate.setText(jsonResult.getString(
						"POLICY_STARTDATE").trim());

				org.jdom2.Element Policy_Enddate = new org.jdom2.Element(
						"Policy_Enddate");
				Policy_Enddate.setText(jsonResult.getString("POLICY_ENDDATE")
						.trim());

				org.jdom2.Element Occupation_Type = new org.jdom2.Element(
						"Occupation_Type");
				Occupation_Type.setText("1");
//				Occupation_Type.setText(jsonResult.getString("OCCUPATION_TYPE").trim());

				org.jdom2.Element CustAge = new org.jdom2.Element("CustAge");
				CustAge.setText(jsonResult.getString("CUSTAGE").trim());

				org.jdom2.Element Policy_Type = new org.jdom2.Element(
						"Policy_Type");
				Policy_Type.setText(jsonResult.getString("POLICY_TYPE").trim());

				org.jdom2.Element Year_of_Manufacture = new org.jdom2.Element(
						"Year_of_Manufacture");
				Year_of_Manufacture.setText(jsonResult.getString(
						"manufacturingYear").trim());

				org.jdom2.Element Registration_Citycode = new org.jdom2.Element(
						"Registration_Citycode");
				Registration_Citycode.setText(jsonResult.getString(
						"REGISTRATION_CITYCODE").trim());

				org.jdom2.Element Registration_City = new org.jdom2.Element(
						"Registration_City");
				Registration_City.setText(jsonResult.getString(
						"REGISTRATION_CITY").trim());

				org.jdom2.Element Manufacturer_Code = new org.jdom2.Element(
						"Manufacturer_Code");
				Manufacturer_Code.setText(jsonResult.getString(
						"MANUFACTURER_CODE").trim());

				org.jdom2.Element Manufacture_Name = new org.jdom2.Element(
						"Manufacture_Name");
				Manufacture_Name.setText(jsonResult.getString(
						"MANUFACTURE_NAME").trim());

				org.jdom2.Element Vehicle_Modelcode = new org.jdom2.Element(
						"Vehicle_Modelcode");
				Vehicle_Modelcode.setText(jsonResult.getString(
						"VEHICLE_MODELCODE").trim());

				org.jdom2.Element Vehicle_Model = new org.jdom2.Element(
						"Vehicle_Model");
				Vehicle_Model.setText(jsonResult.getString("VEHICLE_MODEL")
						.trim());

				org.jdom2.Element Purchase_Regndate = new org.jdom2.Element(
						"Purchase_Regndate");
				Purchase_Regndate.setText(hdfcIntBean.getRegistrationDate());

				org.jdom2.Element Vehicle_Regno = new org.jdom2.Element(
						"Vehicle_Regno");
				Vehicle_Regno.setText(jsonResult.getString("vehicleRegno")
						.trim());

				org.jdom2.Element Engine_No = new org.jdom2.Element("Engine_No");
				Engine_No.setText(jsonResult.getString("EngineNo").trim());

				org.jdom2.Element Chassis_No = new org.jdom2.Element(
						"Chassis_No");
				Chassis_No.setText(jsonResult.getString("ChassisNo").trim());

				org.jdom2.Element Fuel_Type = new org.jdom2.Element("Fuel_Type");
				Fuel_Type.setText(jsonResult.getString("fuelType").trim());
//				Fuel_Type.setText("DIESEL");

				org.jdom2.Element Vehicle_Ownedby = new org.jdom2.Element(
						"Vehicle_Ownedby");
				Vehicle_Ownedby.setText(jsonResult.getString("VEHICLE_OWNEDBY").trim());
				
				org.jdom2.Element Name_Financial_Institution = new org.jdom2.Element(
						"Name_Financial_Institution");
				Name_Financial_Institution.setText("");

				org.jdom2.Element Ex_showroom_Price = new org.jdom2.Element(
						"Ex-showroom_Price");
				Ex_showroom_Price.setText(jsonResult.getString(
						"exshowroomPrice").trim());

				org.jdom2.Element Sum_Insured = new org.jdom2.Element(
						"Sum_Insured");
				Sum_Insured.setText(jsonResult.getString("SumInsured").trim());
				System.out.println("SumInsured" + jsonResult.getString("SumInsured").trim());
//				Sum_Insured.setText("0.0");

				org.jdom2.Element Electical_Acc = new org.jdom2.Element(
						"Electical_Acc");
				Electical_Acc.setText(jsonResult.getString("electicalAcc")
						.trim());

				org.jdom2.Element NonElectical_Acc = new org.jdom2.Element(
						"NonElectical_Acc");
				NonElectical_Acc.setText(jsonResult
						.getString("nonElecticalAcc").trim());

				org.jdom2.Element LPG_CNG_Kit = new org.jdom2.Element(
						"LPG-CNG_Kit");
				LPG_CNG_Kit.setText(jsonResult.getString("lpgCngKit").trim());

				org.jdom2.Element No_of_LLdrivers = new org.jdom2.Element(
						"No_of_LLdrivers");
				No_of_LLdrivers.setText(jsonResult.getString("noOfllDrivers")
						.trim());

				org.jdom2.Element Paiddriver_Si = new org.jdom2.Element(
						"Paiddriver_Si");
				Paiddriver_Si.setText(jsonResult.getString("paidDriverSi")
						.trim());

				org.jdom2.Element No_of_Employees = new org.jdom2.Element(
						"No_of_Employees");
				No_of_Employees.setText(jsonResult.getString("noOfEmployees")
						.trim());

				org.jdom2.Element Unnamed_Si = new org.jdom2.Element(
						"Unnamed_Si");
				Unnamed_Si.setText(jsonResult.getString("unnamedSi").trim());

				org.jdom2.Element IsPrevious_Claim = new org.jdom2.Element(
						"IsPrevious_Claim");
				IsPrevious_Claim.setText(jsonResult
						.getString("IsPreviousClaim").trim());

				org.jdom2.Element NCB_ExpiringPolicy = new org.jdom2.Element(
						"NCB_ExpiringPolicy");
				NCB_ExpiringPolicy.setText(jsonResult.getString(
						"NCBExpiringPolicy").trim());

				org.jdom2.Element NCB_RenewalPolicy = new org.jdom2.Element(
						"NCB_RenewalPolicy");
				NCB_RenewalPolicy.setText(jsonResult.getString(
						"NCBExpiringPolicy").trim());

				org.jdom2.Element Total_Premium = new org.jdom2.Element(
						"Total_Premium");
//				Total_Premium.setText("16722");
				Total_Premium.setText(jsonResult.getString(
						"netPrem").trim());

				org.jdom2.Element Service_Tax = new org.jdom2.Element(
						"Service_Tax");
//				Service_Tax.setText("3010");
				Service_Tax.setText(jsonResult.getString(
						"serviceTax").trim());

				org.jdom2.Element Total_Amoutpayable = new org.jdom2.Element(
						"Total_Amoutpayable");
//				Total_Amoutpayable.setText("19732");
				Total_Amoutpayable.setText(jsonResult.getString(
						"finalPrem").trim());
				
				org.jdom2.Element PrePolicy_Startdate = new org.jdom2.Element(
						"PrePolicy_Startdate");
				PrePolicy_Startdate.setText(jsonResult.getString(
						"prePolicyStartDate").trim());

				org.jdom2.Element PrePolicy_Enddate = new org.jdom2.Element(
						"PrePolicy_Enddate");
				PrePolicy_Enddate.setText(jsonResult.getString(
						"prevPolicyEndDate").trim());

				org.jdom2.Element PreInsurerCode = new org.jdom2.Element(
						"PreInsurerCode");
				PreInsurerCode.setText(jsonResult.getString(
						"PREINSURERCODE").trim());
				
				org.jdom2.Element PrePolicy_Number = new org.jdom2.Element(
						"PrePolicy_Number");
				PrePolicy_Number.setText(jsonResult.getString(
						"prePolicyNo").trim());
				
				org.jdom2.Element First_Name = new org.jdom2.Element(
						"First_Name");
				First_Name.setText(jsonResult.getString("FirstName").trim());

				org.jdom2.Element Last_Name = new org.jdom2.Element("Last_Name");
				Last_Name.setText(jsonResult.getString("LastName").trim());

				org.jdom2.Element Date_of_Birth = new org.jdom2.Element(
						"Date_of_Birth");
				Date_of_Birth.setText(jsonResult.getString("dob").trim());

				org.jdom2.Element Gender = new org.jdom2.Element("Gender");
				Gender.setText(jsonResult.getString("Gender").trim());

				org.jdom2.Element Email_Id = new org.jdom2.Element("Email_Id");
				Email_Id.setText(jsonResult.getString("EmailId").trim());

				org.jdom2.Element Contactno_Office = new org.jdom2.Element(
						"Contactno_Office");

				org.jdom2.Element Contactno_Home = new org.jdom2.Element(
						"Contactno_Home");

				org.jdom2.Element Contactno_Mobile = new org.jdom2.Element(
						"Contactno_Mobile");
				Contactno_Mobile.setText(jsonResult
						.getString("ContactnoMobile").trim());

				org.jdom2.Element Car_Address1 = new org.jdom2.Element(
						"Car_Address1");
				Car_Address1
						.setText(jsonResult.getString("CarAddress1").trim());

				org.jdom2.Element Car_Address2 = new org.jdom2.Element(
						"Car_Address2");
				Car_Address2
						.setText(jsonResult.getString("CarAddress2").trim());

				org.jdom2.Element Car_Address3 = new org.jdom2.Element(
						"Car_Address3");
				Car_Address3
						.setText(jsonResult.getString("CarAddress3").trim());

				org.jdom2.Element Car_Citycode = new org.jdom2.Element(
						"Car_Citycode");
				Car_Citycode.setText(jsonResult.getString("CAR_CITYCODE")
						.trim());

				org.jdom2.Element Car_City = new org.jdom2.Element("Car_City");
				Car_City.setText(jsonResult.getString("CAR_CITY").trim());

				org.jdom2.Element Car_Statecode = new org.jdom2.Element(
						"Car_Statecode");
				Car_Statecode.setText(jsonResult.getString("CAR_STATECODE")
						.trim());

				org.jdom2.Element Car_State = new org.jdom2.Element("Car_State");
				Car_State.setText(jsonResult.getString("CAR_STATE").trim());

				org.jdom2.Element Car_Pin = new org.jdom2.Element("Car_Pin");
				Car_Pin.setText(jsonResult.getString("CAR_PIN").trim());

				org.jdom2.Element Corres_Address1 = new org.jdom2.Element(
						"Corres_Address1");
				Corres_Address1.setText(jsonResult.getString("CorresAddress1")
						.trim());

				org.jdom2.Element Corres_Address2 = new org.jdom2.Element(
						"Corres_Address2");
				Corres_Address2.setText(jsonResult.getString("CorresAddress2")
						.trim());

				org.jdom2.Element Corres_Address3 = new org.jdom2.Element(
						"Corres_Address3");
				Corres_Address3.setText(jsonResult.getString("CorresAddress3")
						.trim());

				org.jdom2.Element Corres_Citycode = new org.jdom2.Element(
						"Corres_Citycode");
				Corres_Citycode.setText(jsonResult.getString("CORRES_CITYCODE")
						.trim());

				org.jdom2.Element Corres_City = new org.jdom2.Element(
						"Corres_City");
				Corres_City.setText(jsonResult.getString("CORRES_CITY").trim());

				org.jdom2.Element Corres_Statecode = new org.jdom2.Element(
						"Corres_Statecode");
				Corres_Statecode.setText(jsonResult.getString(
						"CORRES_STATECODE").trim());

				org.jdom2.Element Corres_State = new org.jdom2.Element(
						"Corres_State");
				Corres_State.setText(jsonResult.getString("CORRES_STATE")
						.trim());

				org.jdom2.Element Corres_Pin = new org.jdom2.Element(
						"Corres_Pin");
				Corres_Pin.setText(jsonResult.getString("CORRES_PIN").trim());

				org.jdom2.Element Data1 = new org.jdom2.Element("Data1");
				Data1.setText(jsonResult.getString("Data1").trim());

				org.jdom2.Element Data2 = new org.jdom2.Element("Data2");
				org.jdom2.Element Data3 = new org.jdom2.Element("Data3");
				org.jdom2.Element Data4 = new org.jdom2.Element("Data4");
				org.jdom2.Element Data5 = new org.jdom2.Element("Data5");
				//
				org.jdom2.Element IsEmergency_Cover = new org.jdom2.Element(
						"IsEmergency_Cover");
				IsEmergency_Cover.setText(jsonResult.getString(
						"IsEmergencyCover").trim());

				org.jdom2.Element Owner_Driver_Nominee_Name = new org.jdom2.Element(
						"Owner_Driver_Nominee_Name");
				Owner_Driver_Nominee_Name.setText(jsonResult.getString(
						"nomineeName").trim());

				org.jdom2.Element Owner_Driver_Nominee_Age = new org.jdom2.Element(
						"Owner_Driver_Nominee_Age");
				Owner_Driver_Nominee_Age.setText(jsonResult.getString(
						"nomineeAge").trim());

				org.jdom2.Element Owner_Driver_Nominee_Relationship = new org.jdom2.Element(
						"Owner_Driver_Nominee_Relationship");
				Owner_Driver_Nominee_Relationship.setText("");

				org.jdom2.Element Owner_Driver_Appointee_Name = new org.jdom2.Element(
						"Owner_Driver_Appointee_Name");
				Owner_Driver_Appointee_Name.setText("");

				org.jdom2.Element Owner_Driver_Appointee_Relationship = new org.jdom2.Element(
						"Owner_Driver_Appointee_Relationship");
				Owner_Driver_Appointee_Relationship.setText("");

				org.jdom2.Element PAN_Card = new org.jdom2.Element("PAN_Card");
				PAN_Card.setText(jsonResult.getString("PanNo").trim());
				
				org.jdom2.Element IsAgeDisc = new org.jdom2.Element("IsAgeDisc");
				IsAgeDisc.setText(jsonResult.getString("IsAgeDisc").trim());

				org.jdom2.Element is_pa_cover_owner_driver = new org.jdom2.Element(
						"is_pa_cover_owner_driver");
				is_pa_cover_owner_driver.setText(jsonResult.getString(
						"isPaCoverOwnerDriver").trim());
				//
				is_pa_cover_owner_driver.setText("1");
				org.jdom2.Element IsZeroDept_Cover = new org.jdom2.Element(
						"IsZeroDept_Cover");
				IsZeroDept_Cover.setText(jsonResult
						.getString("IsZeroDeptCover").trim());

				org.jdom2.Element IsZeroDept_RollOver = new org.jdom2.Element(
						"IsZeroDept_RollOver");
				IsZeroDept_RollOver.setText(jsonResult.getString(
						"IsZeroDeptRollOver").trim());

				org.jdom2.Element BiFuelType = new org.jdom2.Element(
						"BiFuelType");
				BiFuelType.setText(jsonResult.getString(
						"BiFuelType").trim());

				org.jdom2.Element LocationCode = new org.jdom2.Element(
						"LocationCode");
//				LocationCode.setText(jsonResult.getString(
//						"LOCATIONCODE").trim());
				System.out.println("LocationCode:::" +jsonResult.getString("LOCATIONCODE"));
				
				LocationCode.setText("113");
				
				org.jdom2.Element PlanType = new org.jdom2.Element("PlanType");
				PlanType.setText("");

				org.jdom2.Element IsRTICover = new org.jdom2.Element(
						"IsRTICover");
				IsRTICover.setText(jsonResult.getString("IsRTICover").trim());

				org.jdom2.Element No_of_UnNamed_Passenger = new org.jdom2.Element(
						"No_of_UnNamed_Passenger");
				No_of_UnNamed_Passenger.setText(jsonResult.getString(
						"NoofUnNamedPassenger").trim());

				org.jdom2.Element No_of_Named_Passenger = new org.jdom2.Element(
						"No_of_Named_Passenger");
				No_of_Named_Passenger.setText(jsonResult.getString(
						"NoofNamedPassenger").trim());

				org.jdom2.Element Named_Si = new org.jdom2.Element("Named_Si");
				Named_Si.setText(jsonResult.getString("namedSi").trim());

				org.jdom2.Element IsCustomerAuthenticationDone = new org.jdom2.Element(
						"IsCustomerAuthenticationDone");
				IsCustomerAuthenticationDone.setText(jsonResult.getString(
						"ISCUSTOMERAUTHENTICATIONDONE").trim());

				org.jdom2.Element AuthenticationType = new org.jdom2.Element(
						"AuthenticationType");
				AuthenticationType.setText(jsonResult.getString(
						"AUTHENTICATIONTYPE").trim());

				org.jdom2.Element UIDNo = new org.jdom2.Element("UIDNo");
				UIDNo.setText(jsonResult.getString("UIDNO").trim());

				org.jdom2.Element num_is_emr_asst_wider_cvr = new org.jdom2.Element(
						"num_is_emr_asst_wider_cvr");
				num_is_emr_asst_wider_cvr.setText(jsonResult.getString(
						"numIsEmrAsstWiderCvr").trim());

				org.jdom2.Element AddOnCovers = new org.jdom2.Element(
						"AddOnCovers");
				AddOnCovers.setText("");

				org.jdom2.Element is_ncb_protection = new org.jdom2.Element(
						"is_ncb_protection");
				is_ncb_protection.setText(jsonResult.getString(
						"isNcbProtection").trim());

				org.jdom2.Element is_engine_gear_box_protection = new org.jdom2.Element(
						"is_engine_gear_box_protection");
				is_engine_gear_box_protection.setText(jsonResult.getString(
						"isEngineGearBoxProtection").trim());

				org.jdom2.Element is_cost_of_consumable = new org.jdom2.Element(
						"is_cost_of_consumable");
				is_cost_of_consumable.setText(jsonResult.getString(
						"isCostOfConsumable").trim());

				org.jdom2.Element is_loss_of_use_down_protection = new org.jdom2.Element(
						"is_loss_of_use_down_protection");
				is_loss_of_use_down_protection.setText(jsonResult.getString(
						"isLossOfUseDownProtection").trim());

				rootelement.addContent(AgentCode);
				rootelement.addContent(Type_of_Business);
				rootelement.addContent(Policy_Startdate);
				rootelement.addContent(Policy_Enddate);
				rootelement.addContent(Occupation_Type);
				rootelement.addContent(CustAge);
				rootelement.addContent(Policy_Type);
				rootelement.addContent(Year_of_Manufacture);
				rootelement.addContent(Registration_Citycode);
				rootelement.addContent(Registration_City);
				rootelement.addContent(Manufacturer_Code);
				rootelement.addContent(Manufacture_Name);
				rootelement.addContent(Vehicle_Modelcode);
				rootelement.addContent(Vehicle_Model);
				rootelement.addContent(Purchase_Regndate);
				rootelement.addContent(Vehicle_Regno);
				rootelement.addContent(Engine_No);
				rootelement.addContent(Chassis_No);
				rootelement.addContent(Fuel_Type);
				rootelement.addContent(Vehicle_Ownedby);
				rootelement.addContent(Name_Financial_Institution);
				rootelement.addContent(Ex_showroom_Price);
				rootelement.addContent(Sum_Insured);
				rootelement.addContent(Electical_Acc);
				rootelement.addContent(NonElectical_Acc);
				rootelement.addContent(LPG_CNG_Kit);
				rootelement.addContent(No_of_LLdrivers);
				rootelement.addContent(Paiddriver_Si);
				rootelement.addContent(No_of_Employees);
				rootelement.addContent(Unnamed_Si);
				rootelement.addContent(IsPrevious_Claim);
				rootelement.addContent(NCB_ExpiringPolicy);
				rootelement.addContent(NCB_RenewalPolicy);
				rootelement.addContent(Total_Premium);
				rootelement.addContent(Service_Tax);
				rootelement.addContent(Total_Amoutpayable);
				rootelement.addContent(PrePolicy_Startdate);
				rootelement.addContent(PrePolicy_Enddate);
				rootelement.addContent(PreInsurerCode);
				rootelement.addContent(PrePolicy_Number);
				rootelement.addContent(First_Name);
				rootelement.addContent(Last_Name);
				rootelement.addContent(Date_of_Birth);
				rootelement.addContent(Gender);
				rootelement.addContent(Email_Id);
				rootelement.addContent(Contactno_Office);
				rootelement.addContent(Contactno_Home);
				rootelement.addContent(Contactno_Mobile);
				rootelement.addContent(Car_Address1);
				rootelement.addContent(Car_Address2);
				rootelement.addContent(Car_Address3);
				rootelement.addContent(Car_Citycode);
				rootelement.addContent(Car_City);
				rootelement.addContent(Car_Statecode);
				rootelement.addContent(Car_State);
				rootelement.addContent(Car_Pin);
				rootelement.addContent(Corres_Address1);
				rootelement.addContent(Corres_Address2);
				rootelement.addContent(Corres_Address3);
				rootelement.addContent(Corres_Citycode);
				rootelement.addContent(Corres_City);
				rootelement.addContent(Corres_Statecode);
				rootelement.addContent(Corres_State);
				rootelement.addContent(Corres_Pin);
				rootelement.addContent(Data1);
				rootelement.addContent(Data2);
				rootelement.addContent(Data3);
				rootelement.addContent(Data4);
				rootelement.addContent(Data5);
				rootelement.addContent(IsEmergency_Cover);
				rootelement.addContent(Owner_Driver_Nominee_Name);
				rootelement.addContent(Owner_Driver_Nominee_Age);
				rootelement.addContent(Owner_Driver_Nominee_Relationship);
				rootelement.addContent(Owner_Driver_Appointee_Name);
				rootelement.addContent(Owner_Driver_Appointee_Relationship);
				rootelement.addContent(PAN_Card);
				rootelement.addContent(IsAgeDisc);
				rootelement.addContent(is_pa_cover_owner_driver);
				rootelement.addContent(IsZeroDept_Cover);
				rootelement.addContent(IsZeroDept_RollOver);
				rootelement.addContent(BiFuelType);
				rootelement.addContent(LocationCode);
				rootelement.addContent(PlanType);
				rootelement.addContent(IsRTICover);
				rootelement.addContent(No_of_UnNamed_Passenger);
				rootelement.addContent(No_of_Named_Passenger);
				rootelement.addContent(Named_Si);
				rootelement.addContent(IsCustomerAuthenticationDone);
				rootelement.addContent(AuthenticationType);
				rootelement.addContent(UIDNo);
				rootelement.addContent(num_is_emr_asst_wider_cvr);
				rootelement.addContent(AddOnCovers);
				AddOnCovers.addContent(is_ncb_protection);
				AddOnCovers.addContent(is_engine_gear_box_protection);
				AddOnCovers.addContent(is_cost_of_consumable);
				AddOnCovers.addContent(is_loss_of_use_down_protection);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xx = xmlOutput.outputString(document2);
				xmlOutput.output(document2, new FileWriter(path+"\\getProposalRequest.xml"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return xx;
	}

	private HashMap<String, String> readResponce(String str) {
		data = new HashMap();
		HashMap tax;
		ArrayList coverlist = new ArrayList<>();
		HashMap cover = new HashMap();
		try {
			data.clear();
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(new ByteArrayInputStream(str
					.getBytes()));
			// -------------------------------------------------------------------
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			String xt = xmlOutput.outputString(document);

			xmlOutput.output(document, new FileWriter(path + "\\respfileHDFC.xml"));
			// -----------------------------------------------------------
			org.jdom2.Element root = document.getRootElement();
			List list = root.getChildren();
			Iterator itr = list.iterator();
			// data.put("Company", "HDFC");
			int i = 0, x = 1;
			while (itr.hasNext()) {
				org.jdom2.Element s = (org.jdom2.Element) itr.next();
				List lst = s.getChildren();
				Iterator iterator = lst.iterator();

				cover.clear();
				if (!lst.isEmpty()) {
					int j = 0;
					while (iterator.hasNext()) {

						org.jdom2.Element rncv = (org.jdom2.Element) iterator
								.next();
						List ls = rncv.getChildren();
						Iterator itra = ls.iterator();
						if (!(ls.isEmpty())) {
							System.out.println("ls-->" + ls);
							tax = new HashMap();
							// System.out.println("tax is empty----" + tax
							// +"----"+tax.isEmpty());
							while (itra.hasNext()) {

								org.jdom2.Element tx = (org.jdom2.Element) itra
										.next();
								// System.out.println("nodename----" +
								// tx.getName());
								// System.out.println("nodecv------" +
								// tx.getValue());
								tax.put(tx.getName(), tx.getValue());
							}
							j++;
							// System.out.println(j+"---"+tax);
							data.put("tax" + j, tax);
							// System.out.println("data----" + tax
							// +"----"+data);
						} else {
							x = 0;
							// System.out.println("rncvname----" +
							// rncv.getName());
							// System.out.println("rncvvalue------" +
							// rncv.getValue());
							if (!(rncv.getValue() == null)) {
								// System.out.println("nodename----" +
								// rncv.getName());
								// System.out
								// .println("nodecv------" + rncv.getValue());

								// List valList=rncv.getChildren();
								cover.put(rncv.getName(), rncv.getValue());
								// System.out.println("Cover----"+cover);
								// data.put("cover"+i, cover);
								// i++;
							} else {

								// System.out.println("getName----" +
								// rncv.getName());
								// System.out
								// .println("getValue----" + rncv.getValue());
								cover.put(s.getName(), "");
								// System.out.println("Cover----"+cover);
								// data.put("cover"+i, cover);
								// i++;
							}

						}

					}
					if (x == 0) {
						i++;
						data.put("", cover);
						// coverlist.add("cover"+i);
					}

				} else {

					if (!(s.getValue() == null)) {
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
			// data.put("coverlist", coverlist);
			// System.out.println("data--" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (hdfcIntBean.getRequest_for().equals("proposal")) {
			System.out.println("Proposal");
			data = cover;
		}
		return data;
	}

}
