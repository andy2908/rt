package com.uat.hbc.insurance.controller;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.dom.DOMSource;

import org.codehaus.jackson.map.ObjectMapper;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.futureGenerali.IService;
import com.futureGenerali.Service;
import com.google.gson.Gson;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.insurance.dao.IntegrationFutureGeneraliDao;
import com.uat.hbc.insurance.model.FutureGInteBean;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;

@Controller
public class MotorCalculatorFutureGeneraliController {

	DOMSource domSource;
	@Autowired
	IntegrationFutureGeneraliDao futureGDao;
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;
	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;

	String path = System.getProperty("user.home");
	LinkedHashMap data;
	LinkedHashMap<String, String> preProResponse ;
	LinkedHashMap<String, String> preProResponseForClient ;
	FutureGInteBean futureGInteBean;

	
	
	public MotorCalculatorFutureGeneraliController() {
		futureGInteBean= new FutureGInteBean();
		preProResponse = new LinkedHashMap<>();
	}

	
	/////////////////Used for logged in user/////////////////////
	
	@RequestMapping(value = "user/motorCalculatorFutureGenerali", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getProposal(HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String transactionDate = dateFormat.format(date);
		
		futureGInteBean= new FutureGInteBean();
		String jsonNames = "", json = "";
		try {
			String pkg_name = request.getParameter("pkg_name");

			System.out.println("pkg_name==>" + pkg_name);

			String proc_name = request.getParameter("procName");

			System.out.println("proc_name==>" + proc_name);

			String table_name = request.getParameter("table_name");

			futureGInteBean.setRequest_for(request.getParameter("request_for"));
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
			String salutId = request.getParameter("salutId");
			System.out.println("salutId::" +salutId);
			String addrType = request.getParameter("addrType");
			String addrType1 = request.getParameter("addrType1");
			String addrType2 = request.getParameter("addrType2");

			String clientID = request.getParameter("clientID");
			String receiptNo = request.getParameter("receiptNo");
			String referenceNo = request.getParameter("referenceNo");
			String customerType = request.getParameter("customerType");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String maritalStatus = request.getParameter("maritalStatus");
			String custPanNo = request.getParameter("custPanNo");
			String custGstIn = request.getParameter("custGstIn");
			String custAddharNo = request.getParameter("custAddharNo");
			String ckycNo = request.getParameter("ckycNo");
			String eiaNo = request.getParameter("eiaNo");
			String resAddrLine1 = request.getParameter("resAddrLine1");
			String resAddrLine2 = request.getParameter("resAddrLine2");
			String resAddrLine3 = request.getParameter("resAddrLine3");
			String reslandmark = request.getParameter("reslandmark");
			String reshomePhoneNo = request.getParameter("reshomePhoneNo");
			String resofficePhoneNo = request.getParameter("resofficePhoneNo");
			String resfaxNo = request.getParameter("resfaxNo");
			String resmobileNo = request.getParameter("resmobileNo");
			String emailId = request.getParameter("emailId");
			String officeAddrLine1 = request.getParameter("officeAddrLine1");
			String officeAddrLine2 = request.getParameter("officeAddrLine2");
			String officeAddrLine3 = request.getParameter("officeAddrLine3");
			String officeLandMark = request.getParameter("officeLandMark");
			String officePhoneNo = request.getParameter("officePhoneNo");
			String officeTelPhNo = request.getParameter("officeTelPhNo");
			String officeFaxNo = request.getParameter("officeFaxNo");
			String officeMObNo = request.getParameter("officeMObNo");
			String officeemailId = request.getParameter("officeemailId");
			String checkType = request.getParameter("checkType");
			String bsbCode = request.getParameter("bsbCode");
//			String transactionDate = request.getParameter("transactionDate");
			String receiptType = request.getParameter("receiptType");
			String amount = request.getParameter("amount");
			String tranRefNo = request.getParameter("tranRefNo");
			String tranRefNoDate = request.getParameter("tranRefNoDate");
			String typeOfVehicle = request.getParameter("typeOfVehicle");
			String vehicleClass = request.getParameter("vehicleClass");
			String registrationNo = request.getParameter("registrationNo");
			String registrationDate = request.getParameter("registrationDate");
			String manufacturingYear = request.getParameter("manufacturingYear");
			String inbuiltKit = request.getParameter("inbuiltKit");
			String IVDOfCNGOrLPG = request.getParameter("IVDOfCNGOrLPG");
			String engineNo = request.getParameter("engineNo");
			String chassiNo = request.getParameter("chassiNo");
			String cubicCapacity = request.getParameter("cubicCapacity");
			String seatingCapacity = request.getParameter("seatingCapacity");
			String idv = request.getParameter("idv");
			String grossWeigh = request.getParameter("grossWeigh");
			String carriageCapacityFlag = request.getParameter("carriageCapacityFlag");
			String trailerTowedBy = request.getParameter("trailerTowedBy");
			String trailerRegNo = request.getParameter("trailerRegNo");
			String noOfTrailer = request.getParameter("noOfTrailer");
			String trailerValLimPaxIDVDays = request.getParameter("trailerValLimPaxIDVDays");
			String ncb = request.getParameter("ncb");
			String privateCommercialUsage = request.getParameter("privateCommercialUsage");
			String imt23 = request.getParameter("imt23");
			String CPAReq = request.getParameter("CPAReq");
			String CPANomName = request.getParameter("CPANomName");
			String CPANomAge = request.getParameter("CPANomAge");
			String CPANomAgeDet = request.getParameter("CPANomAgeDet");
			String CPANomPerc = request.getParameter("CPANomPerc");
			String CPAAppointeeName = request.getParameter("CPAAppointeeName");
			String CPAAppointeRel = request.getParameter("CPAAppointeRel");
			String NPAReq = request.getParameter("NPAReq");
			String NPAName = request.getParameter("NPAName");
			String NPALimit = request.getParameter("NPALimit");
			String NPANomName = request.getParameter("NPANomName");
			String NPANomAge = request.getParameter("NPANomAge");
			String NPANomAgeDet = request.getParameter("NPANomAgeDet");
			String NPARel = request.getParameter("NPARel");
			String NPAAppinteeName = request.getParameter("NPAAppinteeName");
			String NPAAppinteeRel = request.getParameter("NPAAppinteeRel");
			// String AddonReq = request.getParameter("AddonReq");
			// String CoverCode = request.getParameter("CoverCode");
			String UsedCar = request.getParameter("UsedCar");
			String PurchaseDate = request.getParameter("PurchaseDate");
			String InspectionRptNo = request.getParameter("InspectionRptNo");
			String InspectionDt = request.getParameter("InspectionDt");
			String RollOver = request.getParameter("RollOver");
			String PolicyNo = request.getParameter("policyNo");
			System.out.println("PolicyNo:::" +PolicyNo);
			String InsuredName = request.getParameter("InsuredName");
			String PreviousPolExpDt = request.getParameter("PreviousPolExpDt");
			String ClientCode = request.getParameter("ClientCode");
			String rollOverAddress1 = request.getParameter("rollOverAddress1");
			String rollOverAddress2 = request.getParameter("rollOverAddress2");
			String rollOverAddress3 = request.getParameter("rollOverAddress3");
			String rollOverAddress4 = request.getParameter("rollOverAddress4");
			String rollOverAddress5 = request.getParameter("rollOverAddress5");
			String rollOverPinCode = request.getParameter("rollOverPinCode");
			String rollOverInspectionRptNo = request.getParameter("rollOverInspectionRptNo");
			String rollOverInspectionDt = request.getParameter("rollOverInspectionDt");
			String NCBDeclartion = request.getParameter("NCBDeclartion");
			String ClaimInExpiringPolicy = request.getParameter("ClaimInExpiringPolicy");
			String NCBInExpiringPolicy = request.getParameter("NCBInExpiringPolicy");
			String NewVehicle = request.getParameter("NewVehicle");
			String newVehicleInspectionRptNo = request.getParameter("newVehicleInspectionRptNo");
			String newVehicleInspectionDt = request.getParameter("newVehicleInspectionDt");
			String TcsAmount = request.getParameter("TCSAmount");
			System.out.println("TcsAmount:::" +TcsAmount);
			String RollOverPolicyNo = request.getParameter("RollOverPolicyNo");
			String rAreaId = request.getParameter("rAreaId");
			String pAreaId = request.getParameter("pAreaId");
			String addressType1 = request.getParameter("addressType1");
			String addressType2 = request.getParameter("addressType2");
			String rtoCode = request.getParameter("RTOCode");
			System.out.println("rtoCode::" + rtoCode);
			String AddonReq = "N";
			String CoverCode = "";
			futureGInteBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
			futureGInteBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
			futureGInteBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
			futureGInteBean.setUserId(request.getParameter("userId"));
			futureGInteBean.setUserDesc(request.getParameter("userDesc"));
			futureGInteBean.setBranchId(request.getParameter("branchId"));
			if(addressType2.equalsIgnoreCase("P"))
			{
				addressType2= "B";
			}
			
			
			HashMap inputParaList = new HashMap<>();

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
			inputParaList.put("PI_OccupationID", occupationID);
			inputParaList.put("PI_FIN_ID", finId);
			inputParaList.put("PI_PREV_GIC_ID", prevGICID);
//			inputParaList.put("PI_AREAID", areaId);
			inputParaList.put("PI_PAreaID", pAreaId);
			inputParaList.put("PI_RAreaID", rAreaId);
			inputParaList.put("PI_Nom_Rel", nomRel);
			inputParaList.put("PI_NomineeRepRel", nomRelRep);
			inputParaList.put("PI_Own_Dri_Appointee_Rel", ownDriAppointeeRel);
			inputParaList.put("PI_SalutaionID", salutId);
			inputParaList.put("PI_AddressType", addrType);
			inputParaList.put("PI_Covers", covers);
			inputParaList.put("PI_COV_VAL", coverVal);
			inputParaList.put("PI_COV_NO", coverNo);

			if (futureGInteBean.getRequest_for().equalsIgnoreCase("premium")) {
				futureGInteBean.setMethodName("ENQ");
				futureGInteBean.setPolicyIssuetype("C");

			} else if (futureGInteBean.getRequest_for().equalsIgnoreCase("proposal")) {
				futureGInteBean.setMethodName("CRT");
				futureGInteBean.setPolicyIssuetype("I");
			}

			System.out.println("inputParaList::<>" + inputParaList);

			jsonNames = futureGDao.getFutureGData("PKG_MOTOR_CALC", proc_name, inputParaList);

//			System.out.println("jsonNames:: " + jsonNames);

//			System.out.println("jsonNames Combine first Response======>>" + jsonNames);
			String jsonNamesArr[] = jsonNames.split("\\}\\]\\[\\{");

			jsonNames = jsonNamesArr[0];
			String coverList = "";
			if (jsonNamesArr.length > 1) {
				coverList = jsonNamesArr[1];
				coverList = "[{" + coverList;
			}

			jsonNames = jsonNames + "}]";
			System.out.println("jsonName controller::: " + jsonNames);
			System.out.println("coverList Controller::: " + coverList);
			if(!coverList.equals("")){
				String coverId="",coverNoOFItems="",coverValue="", isMandatory="", coverStatus="", coverIdentity="";
				JSONArray coverJson = new JSONArray(coverList);
				for (int table = 0; table < coverJson.length(); ++table) {
			            org.json.JSONObject obj = (org.json.JSONObject) coverJson.get(table);
			            String gicId = "", gicCompany = "";
			            if (!obj.isNull("HB_COVER_ID")) {
			                coverId = "" + obj.getInt("HB_COVER_ID");
//			                System.out.println("1111111111 coverId==>>"+coverId);
			            }
						 if (!obj.isNull("ISMANDATORY")) {
							 isMandatory = obj.getString("ISMANDATORY");
							if(!isMandatory.equalsIgnoreCase("N"))
							{
								isMandatory = "true";
							}else{
								isMandatory = "false";
							}
						 }
						 if (!obj.isNull("COVER_IDENTITY")) {
							 coverIdentity = ""+obj.getInt("COVER_IDENTITY");
							
						 }
						 if (!obj.isNull("COVER_STATUS")) {
							 coverStatus = obj.getString("COVER_STATUS");
							 if(!coverStatus.equalsIgnoreCase("N"))
							 {
								 coverStatus = "true";
							 }else{
								 coverStatus = "false";
							 }
						 }
			            if (!obj.isNull("COV_NO")) {
			            	coverNoOFItems = "" + obj.getInt("COV_NO");
			                System.out.println("1111111111 coverNo==>>"+coverNo);
			            }
			            if (!obj.isNull("COVER_VAL")) {
			            	coverValue = "" + obj.getString("COVER_VAL");
			                System.out.println("1111111111 coverVal==>>"+coverValue);
			            }
			            
			            if(coverId.equals("52"))
			            {
			            	futureGInteBean.setRestrictedTPPD("Y");
			            	/*isTPPDCover="true";
			            	TPPDCoverPolicyCoverID ="";
			            	TPPDCoverSumInsured=coverValue;
			            	TPPDCoverPackageName="";*/

			            }
			            if(coverId.equals(""))
			            {
			            	/*isBasicODCoverage="true";
			            	BasicODCoverageIsChecked =coverStatus;
			            	BasicODCoverageNoOfItems= coverNoOFItems;
			            	BasicODCoveragePackageName ="";*/
			            }
			            
			            if(coverId.equals(""))
			            {
			            	/*isBasicLiability="true";
			            	BasicLiabilityIsChecked=coverStatus;
							BasicLiabilityNoOfItems = coverNoOFItems;
							BasicLiabilityPackageName ="";*/
			            }
			            if(coverId.equals("25"))
			            {
//			            	RoadTaxSumInsured = coverValue;
			            }
			            if(coverId.equals("8"))
			            {
			            	System.out.println("Zero dep");
			            }
			            if(coverId.equals("54"))
			            {
			            	futureGInteBean.setpACoverForUnnamedPassengers(coverValue) ;
			            
			            }
			            if(coverId.equals("22"))
			            {
			            	futureGInteBean.setFibreGlassTank("Y");
			            }
			            if(coverId.equals("15"))
			            {
			            	futureGInteBean.setNcb(coverValue);
			            	
			            }
			            if(coverId.equals("23"))
			            {
			            	futureGInteBean.setGeographicalArea(coverValue);
			            			            	
			            } 
			            if(coverId.equals("56"))
			            {
			            	futureGInteBean.setLegalLiabilitytoPaidDriver("1");
			            	futureGInteBean.setLegalLiabilityForOtherEmployees("1");
			            	/* PaToPaidDriverNoOfItems= coverNoOFItems;
			            	 PaToPaidDriverSumInsured=coverValue;*/

			            	
			            }
			            if(coverId.equals("28"))
			            {
			            	futureGInteBean.setRestrictedTPPD("Y");
			            	/* PaToPaidDriverNoOfItems= coverNoOFItems;
			            	 PaToPaidDriverSumInsured=coverValue;*/
			            	
			            	
			            }
			            	
			            if(coverId.equals("20"))
			            {
			            	/*IsUsedForDrivingTuition ="true";
			            	DrivingTuitionCoverageIsChecked = coverStatus;
			            	DrivingTuitionCoverageNoOfItems = coverNoOFItems;
			            	DrivingTuitionCoveragePackageName = "";*/
			            	
			            }
			            if(coverId.equals("109"))
			            {
			            	/*IsAutomobileAssociationMembershipDiscount ="true";
			            	IsAutomobileAssociationMember ="true";
			            	aaiMemberIsChecked = coverStatus;
			            	aaiMemberNoOfItems = coverNoOFItems;
			            	aaiMemberPackageName = "";
			            	aaiMemberIsMandatory = isMandatory;*/
			            
			            }
			            
			            if(coverId.equals("62"))
			            {
				            System.out.println("Liability to Employees");
				            
				          
				          /*  IsLiabilityToEmployeeCovered = "true";
				            LiabilityToEmployeeIsChecked = coverStatus;
				            LiabilityToEmployeeNoOfItems = coverNoOFItems;
				            LiabilityToEmployeePackageName= "";
				            LiabilityToEmployeePolicyCoverID = "";*/

			            
			            }
			            if(coverId.equals("53"))
			            {
			            /*	isPAToOwnerDriverCoverd="true";
			            	PACoverToOwnerDriverNo = coverNoOFItems ;*/
			            }
			            if(coverId.equals("24"))
			            {
			            	futureGInteBean.setUseForHandicap("Y");
			       		}
			            if(coverId.equals("104"))
			            {
			            	
//			            	voluntaryDeductibleSumInsured=coverValue;
			            
			           }
			            if(coverId.equals("21"))
			            {
			            	System.out.println("anti theft");
			            	futureGInteBean.setAntiThiefDevice("Y");
			            	
			            }
			          if(coverId.equals("108"))
			            {
//			            	isbifuelkit ="Y";
//			            	bifuelkitSumInsured=coverValue;
//			            	
			            	
			            }
				          if(coverId.equals("9"))
			          {
				        	  futureGInteBean.setElectricalAccessoriesValues(coverValue);
			        	  
			          }
			          if(coverId.equals("2"))
			          {
			        	
			        	  futureGInteBean.setNonElectricalAccessoriesValues(coverValue);
			          }
			            
			            
					}
				}
			
			
			if (firstName.equalsIgnoreCase("")) {
				firstName = "Test";
			}
			if (firstName.equalsIgnoreCase("")) {
				firstName = "Test";
			}
			if (lastName.equalsIgnoreCase("")) {
				lastName = "Test";
			}
			if (dob.equalsIgnoreCase("")) {
				dob = "02/07/1991";
			}
			if (gender.equalsIgnoreCase("")) {
				gender = "M";
			}
			if (gender.equalsIgnoreCase("male")) {
				gender = "M";
			}
			if (gender.equalsIgnoreCase("female")) {
				gender = "F";
			}
			if (maritalStatus.equalsIgnoreCase("")) {
				maritalStatus = "Single";
			}
			if (registrationNo.equalsIgnoreCase("new")) {
				registrationNo = rtoCode + "BF0028";
			}

			HashMap prePro = new HashMap<>();
			System.out.println("PolicyNo:::" + PolicyNo);
			prePro.put("PolicyNo", PolicyNo);
			prePro.put("ClientID", clientID);
			prePro.put("ReceiptNo", receiptNo);
			prePro.put("ReferenceNo", referenceNo);
			prePro.put("ClientType", customerType);
			prePro.put("FirstName", firstName);
			prePro.put("LastName", lastName);
			prePro.put("DOB", dob);
			prePro.put("Gender", gender);
			prePro.put("MaritalStatus", maritalStatus);
			prePro.put("PANNo", custPanNo);
			prePro.put("GSTIN", custGstIn);
			prePro.put("AadharNo", custAddharNo);
			prePro.put("CKYCNo", ckycNo);
			prePro.put("EIANo", eiaNo);
			prePro.put("AddrLine1", resAddrLine1);
			prePro.put("AddrLine2", resAddrLine2);
			prePro.put("AddrLine3", resAddrLine3);
			prePro.put("Landmark", reslandmark);

			prePro.put("HomeTelNo", reshomePhoneNo);
			prePro.put("OfficeTelNo", resofficePhoneNo);
			prePro.put("FAXNO", resfaxNo);
			prePro.put("MobileNo", resmobileNo);
			prePro.put("EmailAddr", emailId);
			prePro.put("AddrLine1_2", officeAddrLine1);
			prePro.put("AddrLine2_2", officeAddrLine2);
			prePro.put("AddrLine3_2", officeAddrLine3);
			prePro.put("Landmark_2", officeLandMark);

			prePro.put("HomeTelNo_2", officePhoneNo);
			prePro.put("OfficeTelNo_2", officeTelPhNo);
			prePro.put("FAXNO_2", officeFaxNo);
			prePro.put("MobileNo_2", officeMObNo);
			prePro.put("EmailAddr_2", officeemailId);

			prePro.put("CheckType", checkType);
			prePro.put("BSBCode", bsbCode);
			prePro.put("TransactionDate", transactionDate);
//			prePro.put("ReceiptType", receiptType);
			prePro.put("Amount", amount);
			prePro.put("TranRefNo", tranRefNo);
			prePro.put("TranRefNoDate", transactionDate);

			prePro.put("TypeOfVehicle", typeOfVehicle);
			prePro.put("VehicleClass", vehicleClass);

			prePro.put("RegistrationNo", registrationNo);
			prePro.put("RegistrationDate", registrationDate);
			prePro.put("ManufacturingYear", manufacturingYear);
			prePro.put("InbuiltKit", inbuiltKit);
			prePro.put("IVDOfCNGOrLPG", IVDOfCNGOrLPG);

			prePro.put("EngineNo", engineNo);
			prePro.put("ChassiNo", chassiNo);
			prePro.put("CubicCapacity", cubicCapacity);
			
			prePro.put("SeatingCapacity", seatingCapacity);
			prePro.put("IDV", idv);
			prePro.put("GrossWeigh", grossWeigh);
			prePro.put("CarriageCapacityFlag", carriageCapacityFlag);
			prePro.put("TrailerTowedBy", trailerTowedBy);
			prePro.put("TrailerRegNo", trailerRegNo);
			prePro.put("NoOfTrailer", noOfTrailer);
			prePro.put("TrailerValLimPaxIDVDays", trailerValLimPaxIDVDays);
			prePro.put("ElectricalAccessoriesValues", futureGInteBean.getElectricalAccessoriesValues());
			prePro.put("NonElectricalAccessoriesValues", futureGInteBean.getNonElectricalAccessoriesValues());
			prePro.put("FibreGlassTank", futureGInteBean.getFibreGlassTank());
			prePro.put("GeographicalArea", futureGInteBean.getGeographicalArea());
			prePro.put("PACoverForUnnamedPassengers", futureGInteBean.getpACoverForUnnamedPassengers());
			prePro.put("LegalLiabilitytoPaidDriver", futureGInteBean.getLegalLiabilitytoPaidDriver());
			prePro.put("LegalLiabilityForOtherEmployees", futureGInteBean.getLegalLiabilityForOtherEmployees());
			prePro.put("LegalLiabilityForNonFarePayingPassengers", futureGInteBean.getLegalLiabilityForNonFarePayingPassengers());
			prePro.put("UseForHandicap", futureGInteBean.getUseForHandicap());
			prePro.put("AntiThiefDevice", futureGInteBean.getAntiThiefDevice());
			prePro.put("NCB", ncb);
			System.out.println("ttpd" +futureGInteBean.getRestrictedTPPD());
			prePro.put("RestrictedTPPD", futureGInteBean.getRestrictedTPPD());
			prePro.put("PrivateCommercialUsage", privateCommercialUsage);
			prePro.put("IMT23", imt23);
			prePro.put("CPAReq", CPAReq);
			prePro.put("CPANomName", CPANomName);
			prePro.put("CPANomAge", CPANomAge);
			prePro.put("CPANomAgeDet", CPANomAgeDet);
			prePro.put("CPANomPerc", CPANomPerc);
			prePro.put("CPAAppointeeName", CPAAppointeeName);
			prePro.put("CPAAppointeRel", CPAAppointeRel);
			prePro.put("NPAReq", NPAReq);
			prePro.put("NPAName", NPAName);
			prePro.put("NPALimit", NPALimit);
			prePro.put("NPANomName", NPANomName);
			prePro.put("NPANomAge", NPANomAge);
			prePro.put("NPANomAgeDet", NPANomAgeDet);
			prePro.put("NPARel", NPARel);
			prePro.put("NPAAppinteeName", NPAAppinteeName);
			prePro.put("NPAAppinteeRel", NPAAppinteeRel);
			prePro.put("AddonReq", AddonReq);
			prePro.put("CoverCode", CoverCode);
			prePro.put("UsedCar", UsedCar);
			prePro.put("PurchaseDate", PurchaseDate);
			prePro.put("InspectionRptNo", InspectionRptNo);
			prePro.put("InspectionDt", InspectionDt);
			prePro.put("RollOver", RollOver);
			prePro.put("RollOverPolicyNo", RollOverPolicyNo);
			prePro.put("InsuredName", InsuredName);
			prePro.put("PreviousPolExpDt", PreviousPolExpDt);
			prePro.put("ClientCode", ClientCode);
			prePro.put("rollOverAddress1", rollOverAddress1);
			prePro.put("rollOverAddress2", rollOverAddress2);
			prePro.put("rollOverAddress3", rollOverAddress3);
			prePro.put("rollOverAddress4", rollOverAddress4);
			prePro.put("rollOverAddress5", rollOverAddress5);
			prePro.put("rollOverPinCode", rollOverPinCode);
			prePro.put("rollOverInspectionRptNo", rollOverInspectionRptNo);
			prePro.put("rollOverInspectionDt", rollOverInspectionDt);
			prePro.put("NCBDeclartion", NCBDeclartion);
			prePro.put("ClaimInExpiringPolicy", ClaimInExpiringPolicy);
			prePro.put("NCBInExpiringPolicy", NCBInExpiringPolicy);
			prePro.put("NewVehicle", NewVehicle);
			prePro.put("newVehicleInspectionRptNo", newVehicleInspectionRptNo);
			prePro.put("newVehicleInspectionDt", newVehicleInspectionDt);
			prePro.put("TcsAmount", TcsAmount);
			prePro.put("Addresstype1", addressType1);
			prePro.put("Addresstype2", addressType2);
			prePro.put("RTOCode", rtoCode);

			System.out.println("prePro::::" + prePro) ;
			Gson gson = new Gson();
			json = gson.toJson(prePro);

//			System.out.println("json additional parameters = " + json);
			jsonNames = json + "" + jsonNames;
//			System.out.println("result Befor Removing brackets = " + jsonNames);
			jsonNames = "[" + jsonNames + "]";
//			System.out.println("final jsonNames = " + jsonNames);
//			System.out.println("Request_type--==" + futureGInteBean.getRequest_for());

			if (jsonNames.contains("}]]")) {
				jsonNames = jsonNames.replace("}]]", "}]");
			}
			if (jsonNames.contains("}[{")) {
				jsonNames = jsonNames.replace("}[{", ",");
			}
			if (jsonNames.contains("}]}]")) {
				jsonNames = jsonNames.replace("}]}]", "}]");
			}
//			System.out.println("After jsonNames = " + jsonNames);

			String xmlFile = xmlFile(jsonNames);
//			System.out.println("xmlFile" + xmlFile);

			String product = "Motor";
			
			Service service = new Service();
			IService webApiService = service.getBasicHttpBindingIService();

			String futureResponse = webApiService.createPolicy(product, xmlFile);
//			System.out.println("futureResponse :: " + futureResponse);
			preProResponse = new LinkedHashMap<>();
			preProResponse = readResponse(futureResponse);
			HashMap finalResponse = new HashMap<>();
			List tableListNo = new ArrayList<>();
//			System.out.println("cover List");
			String company="",policyStatus="",clientStatus="",receiptStatus="", VehicleIDV="", status="";
			Iterator coverListItr = preProResponse.entrySet().iterator();
			while (coverListItr.hasNext()) {
				Map.Entry entry = (Map.Entry) coverListItr.next();
				String mapKey = (String)entry.getKey();
					if (mapKey.equalsIgnoreCase("tableList")) {
						tableListNo = (ArrayList) ((null == entry.getValue()) ? "" : entry.getValue());
//						System.out.println("tableList if>>>"+ tableListNo);
						
					}
					if (mapKey.equalsIgnoreCase("Company")) {
						company = (String) ((null == entry.getValue()) ? "" : entry.getValue());
//						System.out.println("Company >>>88888"+ company);
						
					}
					if (mapKey.equalsIgnoreCase("Policy_Status")) {
						policyStatus = (String) ((null == entry.getValue()) ? "" : entry.getValue());
//						System.out.println("Status>>>87897"+ policyStatus);
						
					}
					if (mapKey.equalsIgnoreCase("Client_Status")) {
						clientStatus = (String) ((null == entry.getValue()) ? "" : entry.getValue());
//						System.out.println("Status>>>87897"+ clientStatus);
						
					}
					if (mapKey.equalsIgnoreCase("Receipt_Status")) {
						receiptStatus = (String) ((null == entry.getValue()) ? "" : entry.getValue());
//						System.out.println("Status>>>87897"+ receiptStatus);
						
					}
					if (mapKey.equalsIgnoreCase("Status")) {
						status = (String) ((null == entry.getValue()) ? "" : entry.getValue());
//						System.out.println("Status>>>"+ status);
						
					}
					if (mapKey.equalsIgnoreCase("VehicleIDV")) {
						VehicleIDV = (String) ((null == entry.getValue()) ? "" : entry.getValue());
//						System.out.println("VehicleIDV>>>8876"+ VehicleIDV);
						
					}
					
			}
//			finalResponse.put("Company", company);
//			finalResponse.put("Status", status);
//			finalResponse.put("VehicleIDV", VehicleIDV);
			
			for(int index=0; index < tableListNo.size(); index++)
			{
				String DBValue="",LdrErrLvl="",PolNo="", Type="", RskNo="", Code="" , Description="",BOValue="" ;	
				HashMap cvrs=new HashMap<>();
				String tableNo = (String) tableListNo.get(index);
				System.out.println("tableno::" +tableNo);
				Iterator coverListTableItr = preProResponse.entrySet().iterator();
				while (coverListTableItr.hasNext()) {
					Map.Entry entry = (Map.Entry) coverListTableItr.next();
					String mapKey = (String)entry.getKey();
				
					if(mapKey.equalsIgnoreCase(tableNo))
					{
						
						System.out.println("In if block");
//						System.out.println("preProResponse::"+ preProResponse);
						cvrs = (HashMap) ((null == entry.getValue()) ? "" : entry.getValue());
						System.out.println("tax mapping:::" +cvrs);
						Iterator cvrs_1 = cvrs.entrySet().iterator();
						while (cvrs_1.hasNext()) {
						Map.Entry<String, String> entryCover1 = (Entry<String, String>) cvrs_1.next();
						String mapKeyCover1 = (String)entryCover1.getKey();
							
							if (mapKeyCover1.equalsIgnoreCase("LdrErrLvl")) {
								LdrErrLvl = (String) ((null == entryCover1.getValue()) ? "" : entryCover1.getValue());
//								System.out.println("LdrErrLvl"+index+">>>"+ LdrErrLvl);
							}
							if (mapKeyCover1.equalsIgnoreCase("PolNo")) {
								PolNo = ((null == entryCover1.getValue()) ? "" : entryCover1.getValue()).toString();
//								System.out.println("PolNo"+index+">>>"+ PolNo);
							}
							if (mapKeyCover1.equalsIgnoreCase("RskNo")) {
								RskNo = (String) ((null == entryCover1.getValue()) ? "" : entryCover1.getValue());
//								System.out.println("RskNo"+index+">>>"+ RskNo);
							}
							if (mapKeyCover1.equalsIgnoreCase("Type")) {
								Type = (String) ((null == entryCover1.getValue()) ? "" : entryCover1.getValue());
//								System.out.println("Type"+index+">>>"+ Type);
							}
							if (mapKeyCover1.equalsIgnoreCase("DBValue")) {
										DBValue = (String) ((null == entryCover1.getValue()) ? "" : entryCover1.getValue());
//										System.out.println("DBValue"+index+">>>"+ DBValue);
							}
							if (mapKeyCover1.equalsIgnoreCase("Description")) {
										Description = (String) ((null == entryCover1.getValue()) ? "" : entryCover1.getValue());
//										System.out.println("Description"+index+">>>"+ Description);
							}
							if (mapKeyCover1.equalsIgnoreCase("BOValue")) {
										BOValue = (String) ((null == entryCover1.getValue()) ? "" : entryCover1.getValue());
//										System.out.println("BOValue"+index+">>>"+ BOValue);
							}
							if (mapKeyCover1.equalsIgnoreCase("Code")) {
								Code = (String) ((null == entryCover1.getValue()) ? "" : entryCover1.getValue());
//								System.out.println("Code"+index+">>>"+ Code);
							}
						}
						
					
					}
					
					
				}
				HashMap coverMap= new HashMap<>();
				if(tableNo.equalsIgnoreCase("table1"))
				{
					coverMap.put("table"+index+"_LdrErrLvl", LdrErrLvl);
					coverMap.put("table"+index+"_PolNo", PolNo);
				}else{
//					System.out.println("in else block of hashmap");
//					System.out.println("type value::"+Type);
					if(Type.trim().equalsIgnoreCase("OD")){
						System.out.println("I m OD" + Code);
						coverMap.put("RskNoOD"+Code.replace(" ", ""), RskNo);
						coverMap.put("TypeOD"+Code.replace(" ", ""), Type);
						coverMap.put("DBValueOD"+Code.replace(" ", ""), DBValue);
						coverMap.put("DescriptionOD"+Code.replace(" ", ""), Description);
						coverMap.put("BOValueOD"+Code.replace(" ", ""), BOValue);
						coverMap.put("table"+index+"_CodeOD", Code);
//						System.out.println("coverMap:::" + coverMap);
					}
					else if(Type.trim().equalsIgnoreCase("TP"))
					{
						System.out.println("I m TP" + Code);
						coverMap.put("RskNoTP"+Code.replace(" ", ""), RskNo);
						coverMap.put("TypeTP"+Code.replace(" ", ""), Type);
						coverMap.put("DBValueTP"+Code.replace(" ", ""), DBValue);
						coverMap.put("DescriptionTP"+Code.replace(" ", ""), Description);
						coverMap.put("BOValueTP"+Code.replace(" ", ""), BOValue);
						coverMap.put("table"+index+"_CodeTP", Code);
//						System.out.println("coverMap:::" + coverMap);
					}
				}
				preProResponse.putAll(coverMap);
				
//				System.out.println("tax map:::"+preProResponse);
			}
			
			Iterator tableListItr1 = preProResponse.entrySet().iterator();
					while (tableListItr1.hasNext()) {
					Map.Entry entry = (Map.Entry) tableListItr1.next();
					String mapKey = (String)entry.getKey();
					
						for(int index1=1;index1<=tableListNo.size()+1;index1++)
						{
							if (mapKey.equalsIgnoreCase("table"+index1)) {
								tableListItr1.remove();
//								System.out.println("coverListItr1 in 1");
							} 
						}
					
					}
			preProResponse.put("policyType", policyType);
			preProResponse.put("startDate", futureGInteBean.getStartDate());
			preProResponse.put("endDate", futureGInteBean.getEndDate());
			preProResponse.put("Status", status);
			preProResponseForClient =new LinkedHashMap<String, String>();
			preProResponseForClient.putAll(preProResponse);
//			preProResponse.putAll(finalResponse);
			System.out.println("final premium response::"+preProResponse);
//			if (futureGInteBean.getRequest_for().equalsIgnoreCase("proposal")) {
				if(receiptStatus.equalsIgnoreCase("Successful") && policyStatus.equalsIgnoreCase("Successful") && clientStatus.equalsIgnoreCase("Successful")|| policyStatus.equalsIgnoreCase("Successful") )
				{
				Iterator tableListItr2 = preProResponseForClient.entrySet().iterator();
				while (tableListItr2.hasNext()) {
				Map.Entry entry = (Map.Entry) tableListItr2.next();
				String mapKey = (String)entry.getKey();
					if (mapKey.equalsIgnoreCase("tableList")) {
						tableListItr2.remove();
					}
				}
				String procedureName="";
			
				if (futureGInteBean.getRequest_for().equalsIgnoreCase("proposal")) {
//					preProResponse.put("type",futureGInteBean.getRequest_for());
					procedureName = "PR_PROPOSAL";
					System.out.println("In save data proposal" + preProResponse);
					preProResponseForClient.put("FUID",futureGInteBean.getfUID());
					preProResponseForClient.put("CustFirstName",firstName);
					preProResponseForClient.put("CustLastName",lastName);
					preProResponseForClient.put("agentCode",futureGInteBean.getAgentCode());
					
					preProResponse.putAll(preProResponseForClient);
					
					System.out.println("groupId:::"+futureGInteBean.getMotorGroupResponseGroupId()
					+"gicId::" +futureGInteBean.getMotorGroupResponseGicId()
					+"sessionId::"+ futureGInteBean.getMotorGroupResponseSessionId()
					+" IP :::" +futureGInteBean.getIPAddress()
					+"userId:::"+futureGInteBean.getUserId()
					+"branchId::" + futureGInteBean.getBranchId()
					+"UserDesc:::" + futureGInteBean.getUserDesc());
					
					
					Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(futureGInteBean.getMotorGroupResponseGroupId()), Integer.parseInt(futureGInteBean.getMotorGroupResponseGicId()), futureGInteBean.getMotorGroupResponseSessionId(),
		 					1, preProResponseForClient, futureGInteBean.getIPAddress(), futureGInteBean.getUserId(), futureGInteBean.getBranchId(),
		 					futureGInteBean.getUserDesc(),procedureName);
				}
				
				else if(futureGInteBean.getRequest_for().equalsIgnoreCase("premium")){
//					preProResponse.put("type",futureGInteBean.getRequest_for());
					procedureName = "PR_PREMIUM";
					System.out.println("In save data premium " + preProResponse);
					
					System.out.println("groupId:::"+futureGInteBean.getMotorGroupResponseGroupId()
					+"gicId::" +futureGInteBean.getMotorGroupResponseGicId()
					+"sessionId::"+ futureGInteBean.getMotorGroupResponseSessionId()
					+" IP :::" +futureGInteBean.getIPAddress()
					+"userId:::"+futureGInteBean.getUserId()
					+"branchId::" + futureGInteBean.getBranchId()
					+"UserDesc:::" + futureGInteBean.getUserDesc());
					
					
					Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(futureGInteBean.getMotorGroupResponseGroupId()), Integer.parseInt(futureGInteBean.getMotorGroupResponseGicId()), futureGInteBean.getMotorGroupResponseSessionId(),
		 					1, preProResponseForClient, futureGInteBean.getIPAddress(), futureGInteBean.getUserId(), futureGInteBean.getBranchId(),
		 					futureGInteBean.getUserDesc(), procedureName);
				
				}
				
			}
			
		}catch (Exception e) {
			System.out.println("//////////////////////////" + e);
		}
		HashMap responseForWebClient= null;
		Gson gson1 = new Gson();
		String jsonFinal = gson1.toJson(preProResponse);
		jsonFinal= "[" + jsonFinal + "]";
		if(futureGInteBean.getRequest_for().equalsIgnoreCase("premium")){
			responseForWebClient = sendPremiumResponseToClient(jsonFinal);
			responseForWebClient.putAll(preProResponseForClient);
			
		}else if(futureGInteBean.getRequest_for().equalsIgnoreCase("proposal")){
			responseForWebClient = sendProposalResponseToClient(jsonFinal);
			//responseForWebClient.putAll(preProResponseForClient);
		}
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonFinalResp = mapperObj.writeValueAsString(responseForWebClient);
        System.out.println("jsonFinalResp==>>"+jsonFinalResp);
		
        String finalRespo= "["+	jsonFinalResp +"]";
        System.out.println("jsonFinalResp==>>"+finalRespo);
		return finalRespo;

	}
	////////////////////////////////////////////////////////////
	
	
	
	/////////////////////////// Convert XML String into
	/////////////////////////// hashmap/////////////////////////

private HashMap sendProposalResponseToClient(String jsonFinal) {
	HashMap jsonResponse= new HashMap();
	try {
		String  discOd="", od="", tp="",policyType="";
		String companyName="", vehicleIdv="", ldrErrLvl="",code="";  
        Double grossPayOD=0.0, taxAmt=0.0,grossPremium=0.0, grossPayTP=0.0, odValue=0.0,finalPremium=0.0, servTax=0.0, premAmount=0.0;
        JSONArray coverList=null;
        String errorMsg="", polNo="",startDate="",endDate="", grossPay="";
        String proposalNo="";  
  	  String Receipt_ErrorMessage="",Client_ErrorMessage="",Policy_ErrorMessage="",PolicyStatusMsg="",ClientStatusMsg="",ReceiptStatusMsg="";
        String fuID="",custFirstName="",agentCode="" , custLastName="",FuID="" ,agentCodeFG="", insuredName="",premAmt="";
        DecimalFormat df = new DecimalFormat("0.00");      
		JSONArray jsonarray = new JSONArray(jsonFinal);
		System.out.println("jsonarray" + jsonarray);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonResult = jsonarray.getJSONObject(i);
			System.out.println("<<<<<<:" + jsonResult);
			
	          PolicyStatusMsg = jsonResult.getString("Policy_Status");
	          ClientStatusMsg = jsonResult.getString("Client_Status");
	          ReceiptStatusMsg = jsonResult.getString("Receipt_Status");
	        if(PolicyStatusMsg.equalsIgnoreCase("Fail") ||ClientStatusMsg.equalsIgnoreCase("Fail") ||ReceiptStatusMsg.equalsIgnoreCase("Fail")  ) 
	        {
	        	Policy_ErrorMessage = jsonResult.getString("Policy_ErrorMessage");
	        	Client_ErrorMessage = jsonResult.getString("Client_ErrorMessage");
	        	Receipt_ErrorMessage = jsonResult.getString("Receipt_ErrorMessage");
	        }
	        else if(PolicyStatusMsg.equalsIgnoreCase("Successful") && ClientStatusMsg.equalsIgnoreCase("Successful") && ReceiptStatusMsg.equalsIgnoreCase("Successful")) 
	        {
	            if (!jsonResult.isNull("FUID")) {
	                 FuID = (jsonResult.getString("FUID"));
	             }
	             if (!jsonResult.isNull("CustFirstName")) {
	                 custFirstName = jsonResult.getString("CustFirstName");
	             }
	             if (!jsonResult.isNull("CustLastName")) {
	                 custLastName = jsonResult.getString("CustLastName");
	             }
	             if (!jsonResult.isNull("agentCode")) {
	                 agentCodeFG = (jsonResult.getString("agentCode"));
	             }
	          
	             if (!jsonResult.isNull("startDate")) {
	                startDate = jsonResult.getString("startDate");
	                if(startDate.contains(" 00:00:01"))
	                {
	                    startDate= startDate.replace(" 00:00:01","");
	                }
	            }
	            if (!jsonResult.isNull("tableList")) {
	                coverList = jsonResult.getJSONArray("tableList");
	                System.out.println("coverList:" +coverList);
	                for(int index=0; index<= coverList.length();index++)
	                {
	                    if(policyType.equalsIgnoreCase("1"))
	                    {
	                        System.out.println("policyType=="+policyType);
	                                
	                    if (!jsonResult.isNull("table"+index+"_CodeOD")) {
	                        code = jsonResult.getString("table"+index+"_CodeOD");
	                        System.out.println("code:::" +code);
	                      
	                        if(code.equalsIgnoreCase(("ServTax")))
	                        {
	                            if (!jsonResult.isNull("BOValueOD"+code)) {
	                                servTax = Double.parseDouble(jsonResult.getString("BOValueOD"+code));
	                                System.out.println("servTax::" +servTax);
	                                    
	                            }
	                        }
	                        else if(code.equalsIgnoreCase(("PrmDue")))
	                        {
	                            if (!jsonResult.isNull("BOValueOD"+code)) {
	                                finalPremium = Double.parseDouble(jsonResult.getString("BOValueOD"+code));
	                                System.out.println("finalPremium::" +finalPremium);
	                                    
	                            }
	                        }
	                          premAmount= (finalPremium + servTax);
	                         
	                    }
	          
	                    }else{
	                       if (!jsonResult.isNull("table"+index+"_CodeTP")) {
	                        code = jsonResult.getString("table"+index+"_CodeTP");
	                        if(code.equalsIgnoreCase(("ServTax")))
	                            {
	                                if (!jsonResult.isNull("BOValueTP"+code)) {
	                                servTax = Double.parseDouble(jsonResult.getString("BOValueTP"+code));
	                                }
	                            }
	                         else if(code.equalsIgnoreCase(("PrmDue")))
	                        {
	                            if (!jsonResult.isNull("BOValueTP"+code)) {
	                                finalPremium = Double.parseDouble(jsonResult.getString("BOValueTP"+code));
	                                System.out.println("finalPremium::" +finalPremium);
	                                    
	                            }
	                        }
	                         
//	                            System.out.println(df.format(value));
	                             premAmount = finalPremium + servTax;
	                      }  
	                    }
	                
	               }
	        }
	        
	        insuredName = custFirstName +" "+custLastName;
			
			
	        }
		}
		jsonResponse.put("insuredName", insuredName);
		jsonResponse.put("premAmount",  df.format(premAmount));
		jsonResponse.put("finalPremium", finalPremium);
        jsonResponse.put("servTax", servTax);
        jsonResponse.put("startDate", startDate);
        jsonResponse.put("agentCodeFG", agentCodeFG);
        jsonResponse.put("FuID", FuID);
        jsonResponse.put("PolicyStatusMsg", PolicyStatusMsg);
        jsonResponse.put("ClientStatusMsg", ClientStatusMsg);
        jsonResponse.put("ReceiptStatusMsg", ReceiptStatusMsg);
        jsonResponse.put("Policy_ErrorMessage", Policy_ErrorMessage);
        jsonResponse.put("Client_ErrorMessage", Client_ErrorMessage);
        jsonResponse.put("Receipt_ErrorMessage", Receipt_ErrorMessage);
        
        System.out.println("jsonResponse::>>" +jsonResponse);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return jsonResponse;	

}

private HashMap sendPremiumResponseToClient(String jsonFinal) {
	HashMap jsonResponse= new HashMap();
	try {
		String  discOd="", od="", tp="",policyType="";
		String companyName="", vehicleIdv="", ldrErrLvl="",code="";  
        Double grossPayOD=0.0, taxAmt=0.0,grossPremium=0.0, grossPayTP=0.0, odValue=0.0,finalPremium=0.0, servTax=0.0, premAmount=0.0;
        JSONArray coverList=null;
        String errorMsg="", polNo="",startDate="",endDate="", grossPay="",statusMsg="";
		JSONArray jsonarray = new JSONArray(jsonFinal);
		System.out.println("jsonarray" + jsonarray);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonResult = jsonarray.getJSONObject(i);
			System.out.println("<<<<<<:" + jsonResult);
		
	          statusMsg = jsonResult.getString("Policy_Status");
	          if(statusMsg.equalsIgnoreCase("Fail")) 
	          {
	        	  if (!jsonResult.isNull("ErrorMessage")) {
	        		  errorMsg=jsonResult.getString("ErrorMessage");
	        	  }
	        	  else if(!jsonResult.isNull("Policy_ErrorMessage")){
	        		  errorMsg=jsonResult.getString("Policy_ErrorMessage");
	        	  }
	          }
	          else if (statusMsg.equalsIgnoreCase("Successful")) {
	             
	             if (!jsonResult.isNull("Company")) {
	                 companyName = jsonResult.getString("Company");
	                 System.out.println("companyName:" +companyName);
	             }
	             if (!jsonResult.isNull("VehicleIDV")) {
	                 vehicleIdv = jsonResult.getString("VehicleIDV");
	                  System.out.println("vehicleIdv:" +vehicleIdv);
	             }
	             if (!jsonResult.isNull("table0_PolNo")) {
	                 polNo = jsonResult.getString("table0_PolNo");
	             }
	             if (!jsonResult.isNull("table0_LdrErrLvl")) {
	                 ldrErrLvl = jsonResult.getString("table0_LdrErrLvl");
	             }
//	            if (!futureGPre.isNull("PolNo")) {
//	                polNo = futureGPre.getString("PolNo");
//	            }
	             if (!jsonResult.isNull("startDate")) {
	            	 startDate = jsonResult.getString("startDate");
	                 if (startDate.contains(" 00:00:01")) {
	                	 startDate=  startDate.replace(" 00:00:01", "");
	                 }
	             }
	             if (!jsonResult.isNull("endDate")) {
	                 endDate = jsonResult.getString("endDate");
	                 if (endDate.contains(" 00:00:00")) {
	                	 endDate = endDate.replace(" 00:00:00", "");
	                 }
	             }
	             if (!jsonResult.isNull("policyType")) {
	                 policyType = jsonResult.getString("policyType");
	                 System.out.println("policyType::" +policyType);
	             }

	             if (!jsonResult.isNull("tableList")) {
	                 coverList = jsonResult.getJSONArray("tableList");
	                 System.out.println("tableList:" + coverList);
	                 for (int index = 0; index <= coverList.length(); index++) {
	                     int j = index + 1;
	                     System.out.println("value of j  " + j);
	                     if (!jsonResult.isNull("table" + j + "_CodeOD")) {
	                         code = jsonResult.getString("table" + j + "_CodeOD");

	                         System.out.println("code:::" + code);
	                         System.out.println("coverList::::" + coverList.length());
	                         if (code.equalsIgnoreCase(("Gross Premium"))) {
	                             if (!jsonResult.isNull("BOValueOD"+code.replace(" ", ""))) {
	                                 grossPayOD = Double.parseDouble(jsonResult.getString("BOValueOD"+code.replace(" ", "")));
//	                                System.out.println("grossPayOD::" +grossPayOD);

	                             }
	                         }
	                     } else if (!jsonResult.isNull("table" + j + "_CodeTP")) {
	                         if (code.equalsIgnoreCase(("Gross Premium"))) {
	                             if (!jsonResult.isNull("BOValueTP"+code.replace(" ", ""))) {
	                                 grossPayTP = Double.parseDouble(jsonResult.getString("BOValueTP"+code.replace(" ", "")));
	                                System.out.println("grossPayTP::" +grossPayTP);

	                             }
	                         }
	                     }
	                     odValue = grossPayOD - grossPayTP;
	                     grossPremium = grossPayOD + grossPayTP;
	                     System.out.println("odValue:::" + odValue);
	                     taxAmt = (grossPremium*18)/100;
	                     

	//{"":"0.00","table4_CodeTP":"IDV","Company":"Future Generali Total Insurance Solutions","table2_TypeTP":"TP ","table2_CodeTP":"Gross Premium","table6_CodeTP":"PrmDue","endDate":"19/01/2019 00:00:00","table6_DescriptionTP":"PrmDue","table4_TypeTP":"TP ","table6_TypeTP":"TP ","table8_TypeTP":"TP ","table4_BOValueTP":"2863.00","table8_RskNoTP":"1","table3_DBValueOD":"0.00","Status":"Successful","table2_RskNoTP":"1","table5_BOValueOD":"27735.90","table3_BOValueOD":"23504.78","table5_DescriptionOD":"PrmDue","table4_DescriptionTP":"IDV","VehicleIDV":"736596","table0_PolNo":"V0055969","table6_RskNoTP":"1","table0_LdrErrLvl":"0","table7_DBValueOD":"0.00","table4_RskNoTP":"1","table6_DBValueTP":"0.00","table8_BOValueTP":"515.34","table3_TypeOD":"OD ","table5_TypeOD":"OD ","table7_TypeOD":"OD ","startDate":"20/01/2018 00:00:01","table1_TypeOD":"OD ","table8_DescriptionTP":"ServTax","table1_DBValueOD":"0.00","table5_DBValueOD":"0.00","table1_RskNoOD":"1","table5_RskNoOD":"1","table3_RskNoOD":"1","table1_DescriptionOD":"Gross Premium","table2_BOValueTP":"2863.00","table2_DBValueTP":"0.00","coverlist":["table1","table2","table3","table4","table5","table6","table7","table8","table9"],"table3_CodeOD":"IDV","table5_CodeOD":"PrmDue","table7_CodeOD":"ServTax","table1_CodeOD":"Gross Premium","table2_DescriptionTP":"Gross Premium","table7_BOValueOD":"4230.90","table7_RskNoOD":"1","table3_DescriptionOD":"IDV","table6_BOValueTP":"3378.34","table1_BOValueOD":"23505.00","table7_DescriptionOD":"ServTax","table8_DBValueTP":"0.00","table8_CodeTP":"ServTax","policyType":"1"}
	                     if (policyType.equalsIgnoreCase("1")) {
	                         System.out.println("policyType==" + policyType);

	                         if (!jsonResult.isNull("table" + index + "_CodeOD")) {
	                             code = jsonResult.getString("table" + index + "_CodeOD");
//	                        System.out.println("code:::" +code);
	                             if (code.equalsIgnoreCase(("Gross Premium"))) {
	                                 if (!jsonResult.isNull("BOValueOD"+code.replace(" ", ""))) {
	                                    grossPay = jsonResult.getString("BOValueOD"+code.replace(" ", ""));
	                                System.out.println("grossPay::" +grossPay);

	                                 }
	                             } else if (code.equalsIgnoreCase(("ServTax"))) {
	                                 if (!jsonResult.isNull("BOValueOD"+code.replace(" ", ""))) {
	                                     servTax = Double.parseDouble(jsonResult.getString("BOValueOD"+code.replace(" ", "")));
	                                System.out.println("servTax::" +servTax);

	                                 }
	                             } else if (code.equalsIgnoreCase(("PrmDue"))) {
	                                 if (!jsonResult.isNull("BOValueOD"+code.replace(" ", ""))) {
	                                     finalPremium = Double.parseDouble(jsonResult.getString("BOValueOD"+code.replace(" ", "")));
	                                System.out.println("finalPremium::" +finalPremium);

	                                 }
	                             }
	                             premAmount =(Math.ceil(grossPremium + taxAmt));
	                         }

	                     } else if (!jsonResult.isNull("table" + index + "_CodeTP")) {
	                         code = jsonResult.getString("table" + index + "_CodeTP");
	                         if (code.equalsIgnoreCase(("Gross Premium"))) {
	                             if (!jsonResult.isNull("BOValueTP"+code.replace(" ", ""))) {
	                                 grossPay = (jsonResult.getString("BOValueTP"+code.replace(" ", "")));
	                             }
	                         } else if (code.equalsIgnoreCase(("ServTax"))) {
	                             if (!jsonResult.isNull("BOValueTP"+code.replace(" ", ""))) {
	                                 servTax =Double.parseDouble(jsonResult.getString("BOValueTP"+code.replace(" ", "")));
	                             }
	                         } else if (code.equalsIgnoreCase(("PrmDue"))) {
	                             if (!jsonResult.isNull("BOValueTP"+code.replace(" ", ""))) {
	                                 finalPremium = Double.parseDouble(jsonResult.getString("BOValueTP"+code.replace(" ", "")));
	                                 System.out.println("finalPremium::" + finalPremium);

	                             }
	                         }
	                         premAmount=(Math.ceil(finalPremium + servTax));
	                     }
	                 }
	             }
	         }
	        
		}
		jsonResponse.put("statusMsg", statusMsg);
			jsonResponse.put("ErrorMessage", errorMsg);
			jsonResponse.put("premAmount", premAmount);
			jsonResponse.put("PolNo", polNo);
			jsonResponse.put("companyName", companyName);
			jsonResponse.put("RiskStartDate", startDate);
			jsonResponse.put("PolicyTenure", "1");
			jsonResponse.put("PolicyEndDate", endDate);
			jsonResponse.put("discOd", discOd);
			jsonResponse.put("odValue", odValue);
			jsonResponse.put("grossPayTP", grossPayTP);
			jsonResponse.put("grossPay", grossPay);
			jsonResponse.put("servTax", servTax);
			jsonResponse.put("finalPremium", finalPremium);
			jsonResponse.put("discOd", discOd);
			System.out.println("jsonResponse::>>" +jsonResponse);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return jsonResponse;
}

private LinkedHashMap<String, String> readResponse(String str) {
	data = new LinkedHashMap();
	HashMap tax;
    ArrayList coverlist= new ArrayList<>();
	try {
			data.clear();
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(new ByteArrayInputStream(str.getBytes()));
			// -------------------------------------------------------------------
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			String xt = xmlOutput.outputString(document);

			xmlOutput.output(document, new FileWriter(path + "\\FutureGResponse.xml"));
			// -----------------------------------------------------------
			org.jdom2.Element root = document.getRootElement();
			List list = root.getChildren();
			System.out.println("LIST=" + list);
			Iterator itr = list.iterator();
			data.put("Company", "Future Generali India Insurance CO Ltd");
			int i = 0, x = 1;
			while (itr.hasNext()) {
				org.jdom2.Element s = (org.jdom2.Element) itr.next();
				List<org.jdom2.Element> lst = s.getChildren();

				Iterator iterator = lst.iterator();
				HashMap cover = new HashMap();
				HashMap headers = new HashMap();
				headers.clear();
				cover.clear();
				if (!lst.isEmpty()) {
					int j = 0;
					while (iterator.hasNext()) {
						org.jdom2.Element rncv = (org.jdom2.Element) iterator
								.next();
//						if(rncv.getName().contains("Policy"))
//						{
//						System.out.println("rncv--1" + rncv);
						List<org.jdom2.Element>  ls = rncv.getChildren();
						Iterator itra = ls.iterator();
						if (!(ls.isEmpty())) {
//							System.out.println("ls-->" + ls);
							tax = new HashMap();
							while (itra.hasNext()) {
								org.jdom2.Element tx1 = (org.jdom2.Element) itra
										.next();
								
//								System.out.println("rncv--2" + tx1);
								List<org.jdom2.Element>  ls1 = tx1.getChildren();
								Iterator itra1 = ls1.iterator();
								
								if (!(ls1.isEmpty())) {
									cover= new HashMap<>();
									while (itra1.hasNext()) {
										org.jdom2.Element tx2 = (org.jdom2.Element) itra1.next();
										
										//*******************************************
//										System.out.println("rncv--3" + tx2);
										List<org.jdom2.Element>  ls2 = tx2.getChildren();
										Iterator itra2 = ls2.iterator();
										
//										System.out.println("tx1.getName()::::"+tx1.getName());
										if(tx1.getName().contains("Table"))
										{
											x=0;
											cover.put(tx2.getName(),tx2.getValue());
//											System.out.println("In if block");
										}else{
											x=1;
											data.put(tx2.getName(),tx2.getValue());
//											System.out.println("tx222222222222-------------->>"+tx2.getValue());
										}
									}
									if(x==0){
										i++;
										data.put("table"+i, cover);
										coverlist.add("table"+i);
										}
										
								}
							}
							j++;
						
						} else {
							x = 0;
							if(rncv.getName().equalsIgnoreCase("Status") ||rncv.getName().equalsIgnoreCase("ErrorMessage") ){
							if (!(rncv.getValue() == null)) {
//								 System.out.println("rncv.getName()in if   ------>>" +rncv.getName());
//								 System.out.println("rncv.getValue()------>>" + rncv.getValue());
								data.put(s.getName()+"_"+rncv.getName(), rncv.getValue());
							} else {
//								 System.out.println("s.getName()----->>" +s.getName());
								 data.put(s.getName()+"_"+rncv.getName(), "");
							}
						}else{
							if (!(rncv.getValue() == null)) {
//								 System.out.println("rncv.getName() in else  ------>>" +rncv.getName());
//								 System.out.println("rncv.getValue()------>>" + rncv.getValue());
								data.put(rncv.getName(), rncv.getValue());
							} else {
//								 System.out.println("s.getName()----->>" +s.getName());
								data.put(s.getName(), "");
							}
						}
						}
//					}
					/*else if(rncv.getName().contains("Client") || rncv.getName().contains("Receipt")){
						x=0;
						headers.put(rncv.getName(),rncv.getValue());
						System.out.println("In if block");
						System.out.println("headers:::" +headers);
					}else{
						x=1;
						data.put(rncv.getName(),rncv.getValue());
						System.out.println("tx222222222222-------------->>"+rncv.getValue());
						
					}*/
				
				}
				}else {
//					System.out.println("s.getAttributeValue elseeeeee ---------------->>"+s.getAttributeValue("Value"));
					if (!(s.getAttributeValue("Value") == null)) {
//						 System.out.println("getName----" + s.getName());

						data.put(s.getName(), s.getValue());
					} else if (!(s.getValue() == null)) {
//						 System.out.println("getName----" + s.getName());
						data.put(s.getName(), s.getValue());
					} else {
//						 System.out.println("getName----" + s.getName());
						data.put(s.getName(), "");
					}
				}
			}
			data.put("tableList", coverlist);
//			System.out.println("data:::" +data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
}

	///////////////////////////////////////////////////////////////////////////////////

	public String xmlFile(String jsonNames) {

		ArrayList<String> resultList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		String retMsg = "";
		HashMap<String, String> hashmap = new HashMap<>();
		String xx = "";
		Document document2;
		org.w3c.dom.Document document = null;
		HashMap inputParaList = new HashMap<>();
		System.out.println(":>>>>>>" + jsonNames);
		try {
			// System.out.println("jsonNames"+jsonNames);
			JSONArray jsonarray = new JSONArray(jsonNames);
			System.out.println("jsonarray" + jsonarray);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonResult = jsonarray.getJSONObject(i);
				System.out.println("<<<<<<:" + jsonResult);
				// String coverDetails=jsonResult.getString("coverDetails");

				org.jdom2.Element rootelement = new org.jdom2.Element("Root");
				document2 = new Document(rootelement);

				org.jdom2.Element uid = new org.jdom2.Element("Uid");
				uid.setText(jsonResult.getString("FUID").trim());
				futureGInteBean.setfUID(jsonResult.getString("FUID").trim());
				
				org.jdom2.Element vendorCode = new org.jdom2.Element("VendorCode");
				vendorCode.setText(jsonResult.getString("VENDORCODE").trim());

				org.jdom2.Element vendorUserId = new org.jdom2.Element("VendorUserId");
				vendorUserId.setText(jsonResult.getString("VENDORUSERID").trim());

				org.jdom2.Element policyHeader = new org.jdom2.Element("PolicyHeader");

				org.jdom2.Element policyStartDate = new org.jdom2.Element("PolicyStartDate");
				policyStartDate.setText(jsonResult.getString("POLICYSTARTDATE").trim());
				futureGInteBean.setStartDate(jsonResult.getString("POLICYSTARTDATE").trim());
				
				org.jdom2.Element policyEndDate = new org.jdom2.Element("PolicyEndDate");
				policyEndDate.setText(jsonResult.getString("POLICYENDDATE").trim());
				futureGInteBean.setEndDate(jsonResult.getString("POLICYENDDATE").trim());
				
				org.jdom2.Element agentCode = new org.jdom2.Element("AgentCode");
				agentCode.setText(jsonResult.getString("AGENTCODE").trim());
				futureGInteBean.setAgentCode(jsonResult.getString("AGENTCODE").trim());

				org.jdom2.Element branchCode = new org.jdom2.Element("BranchCode");
				branchCode.setText(jsonResult.getString("BRANCHCODE").trim());

				org.jdom2.Element majorClass = new org.jdom2.Element("MajorClass");
				majorClass.setText(jsonResult.getString("MAJORCLASS").trim());

				org.jdom2.Element contractType = new org.jdom2.Element("ContractType");
				contractType.setText(jsonResult.getString("CONTRACTTYPE").trim());

				org.jdom2.Element method = new org.jdom2.Element("METHOD");
				method.setText(futureGInteBean.getMethodName());

				org.jdom2.Element policyIssueType = new org.jdom2.Element("PolicyIssueType");
				policyIssueType.setText(futureGInteBean.getPolicyIssuetype());

				org.jdom2.Element policyNo = new org.jdom2.Element("PolicyNo");
				policyNo.setText(jsonResult.getString("PolicyNo").trim());

				org.jdom2.Element clientID = new org.jdom2.Element("ClientID");
				clientID.setText(jsonResult.getString("ClientID").trim());

				org.jdom2.Element receiptNo = new org.jdom2.Element("ReceiptNo");
				receiptNo.setText(jsonResult.getString("ReceiptNo").trim());

				policyHeader.addContent(policyStartDate);
				policyHeader.addContent(policyEndDate);
				policyHeader.addContent(agentCode);
				policyHeader.addContent(branchCode);
				policyHeader.addContent(majorClass);
				policyHeader.addContent(contractType);
				policyHeader.addContent(method);
				policyHeader.addContent(policyIssueType);
				policyHeader.addContent(policyNo);
				policyHeader.addContent(clientID);
				policyHeader.addContent(receiptNo);

				org.jdom2.Element pos_MISP = new org.jdom2.Element("POS_MISP");

				org.jdom2.Element type = new org.jdom2.Element("Type");
				type.setText(jsonResult.getString("POS_MISP_TYPE").trim());

				org.jdom2.Element panNo = new org.jdom2.Element("PanNo");
				panNo.setText(jsonResult.getString("PANNO").trim());

				org.jdom2.Element name = new org.jdom2.Element("Name");
				name.setText(jsonResult.getString("POS_MISP_NAME").trim());

				org.jdom2.Element adharNo = new org.jdom2.Element("AdharNo");
				adharNo.setText(jsonResult.getString("ADHARNO").trim());

				org.jdom2.Element mobileNo = new org.jdom2.Element("MobileNo");
				mobileNo.setText(jsonResult.getString("MOBILENO").trim());

				org.jdom2.Element emailID = new org.jdom2.Element("EmailID");
				emailID.setText(jsonResult.getString("EMAILID").trim());

				org.jdom2.Element licenseNo = new org.jdom2.Element("LicenseNo");
				licenseNo.setText(jsonResult.getString("LICENSENO").trim());

				org.jdom2.Element expiryDate = new org.jdom2.Element("ExpiryDate");
				expiryDate.setText(jsonResult.getString("EXPIRYDATE").trim());

				org.jdom2.Element terminationDate = new org.jdom2.Element("TerminationDate");
				terminationDate.setText(jsonResult.getString("TERMINATIONDATE").trim());

				org.jdom2.Element referenceNo = new org.jdom2.Element("ReferenceNo");
				referenceNo.setText(jsonResult.getString("ReferenceNo").trim());

				pos_MISP.addContent(type);
				pos_MISP.addContent(panNo);
//				pos_MISP.addContent(name);
//				pos_MISP.addContent(adharNo);
//				pos_MISP.addContent(mobileNo);
//				pos_MISP.addContent(emailID);
//				pos_MISP.addContent(licenseNo);
//				pos_MISP.addContent(expiryDate);
//				pos_MISP.addContent(terminationDate);
//				pos_MISP.addContent(referenceNo);

				org.jdom2.Element client = new org.jdom2.Element("Client");

				org.jdom2.Element clientType = new org.jdom2.Element("ClientType");
				clientType.setText(jsonResult.getString("ClientType").trim());

				org.jdom2.Element creationType = new org.jdom2.Element("CreationType");
				creationType.setText(jsonResult.getString("CREATIONTYPE").trim());

				org.jdom2.Element salutation = new org.jdom2.Element("Salutation");
//				salutation.setText("MR");
				if (jsonResult.getString("SALUTATION").equalsIgnoreCase("")) {
					salutation.setText("MR");
				} else {
					salutation.setText(jsonResult.getString("SALUTATION").trim());
				}

				org.jdom2.Element firstName = new org.jdom2.Element("FirstName");
				firstName.setText(jsonResult.getString("FirstName").trim());

				org.jdom2.Element lastName = new org.jdom2.Element("LastName");
				lastName.setText(jsonResult.getString("LastName").trim());

				org.jdom2.Element dob = new org.jdom2.Element("DOB");
				dob.setText(jsonResult.getString("DOB").trim());

				org.jdom2.Element gender = new org.jdom2.Element("Gender");
				gender.setText(jsonResult.getString("Gender").trim());

				org.jdom2.Element maritalStatus = new org.jdom2.Element("MaritalStatus");
				maritalStatus.setText(jsonResult.getString("MaritalStatus").trim());

				org.jdom2.Element occupation = new org.jdom2.Element("Occupation");
				if (jsonResult.getString("OCCUPATION").trim().equalsIgnoreCase("")) {
					occupation.setText("OTHR");
				} else {
					occupation.setText(jsonResult.getString("OCCUPATION").trim());
				}

				org.jdom2.Element pANNo = new org.jdom2.Element("PANNo");
				pANNo.setText(jsonResult.getString("PANNo").trim());

				org.jdom2.Element GSTIN = new org.jdom2.Element("GSTIN");
				GSTIN.setText(jsonResult.getString("GSTIN").trim());

				org.jdom2.Element aadharNo = new org.jdom2.Element("AadharNo");
				aadharNo.setText(jsonResult.getString("AadharNo").trim());

				org.jdom2.Element CKYCNo = new org.jdom2.Element("CKYCNo");
				CKYCNo.setText(jsonResult.getString("CKYCNo").trim());

				org.jdom2.Element EIANo = new org.jdom2.Element("EIANo");
				EIANo.setText(jsonResult.getString("EIANo").trim());

				client.addContent(clientType);
				client.addContent(creationType);
				client.addContent(salutation);
				client.addContent(firstName);
				client.addContent(lastName);
				client.addContent(dob);
				client.addContent(gender);
				client.addContent(maritalStatus);
				client.addContent(occupation);
				client.addContent(pANNo);
				client.addContent(GSTIN);
				client.addContent(aadharNo);
				client.addContent(CKYCNo);
				client.addContent(EIANo);

				org.jdom2.Element Address1 = new org.jdom2.Element("Address1");

				org.jdom2.Element AddrLine1 = new org.jdom2.Element("AddrLine1");
				AddrLine1.setText(jsonResult.getString("AddrLine1").trim());

				org.jdom2.Element AddrLine2 = new org.jdom2.Element("AddrLine2");
				AddrLine2.setText(jsonResult.getString("AddrLine2").trim());

				org.jdom2.Element AddrLine3 = new org.jdom2.Element("AddrLine3");
				AddrLine3.setText(jsonResult.getString("AddrLine3").trim());

				org.jdom2.Element Landmark = new org.jdom2.Element("Landmark");
				Landmark.setText(jsonResult.getString("Landmark").trim());

				org.jdom2.Element Pincode = new org.jdom2.Element("Pincode");
				Pincode.setText(jsonResult.getString("R_PINCODE").trim());

				org.jdom2.Element City = new org.jdom2.Element("City");
				City.setText(jsonResult.getString("P_CITY").trim());

				org.jdom2.Element State = new org.jdom2.Element("State");
				State.setText(jsonResult.getString("P_STATE").trim());

				org.jdom2.Element Country = new org.jdom2.Element("Country");
				Country.setText("INDIA");
//				Country.setText(jsonResult.getString("P_COUNTRY").trim());

				org.jdom2.Element AddressType = new org.jdom2.Element("AddressType");
				AddressType.setText(jsonResult.getString("Addresstype1").trim());

				org.jdom2.Element HomeTelNo = new org.jdom2.Element("HomeTelNo");
				HomeTelNo.setText(jsonResult.getString("HomeTelNo").trim());

				org.jdom2.Element OfficeTelNo = new org.jdom2.Element("OfficeTelNo");
				OfficeTelNo.setText(jsonResult.getString("OfficeTelNo").trim());

				org.jdom2.Element FAXNO = new org.jdom2.Element("FAXNO");
				FAXNO.setText(jsonResult.getString("FAXNO").trim());

				org.jdom2.Element MobileNo = new org.jdom2.Element("MobileNo");
				MobileNo.setText(jsonResult.getString("MobileNo").trim());

				org.jdom2.Element EmailAddr = new org.jdom2.Element("EmailAddr");
				EmailAddr.setText(jsonResult.getString("EmailAddr").trim());

				Address1.addContent(AddrLine1);
				Address1.addContent(AddrLine2);
				Address1.addContent(AddrLine3);
				Address1.addContent(Landmark);
				Address1.addContent(Pincode);
				Address1.addContent(City);
				Address1.addContent(State);
				Address1.addContent(Country);
				Address1.addContent(AddressType);
				Address1.addContent(HomeTelNo);
				Address1.addContent(OfficeTelNo);
				Address1.addContent(FAXNO);
				Address1.addContent(MobileNo);
				Address1.addContent(EmailAddr);

				org.jdom2.Element Address2 = new org.jdom2.Element("Address2");

				org.jdom2.Element AddrLine1_2 = new org.jdom2.Element("AddrLine1");
				AddrLine1_2.setText(jsonResult.getString("AddrLine1_2").trim());

				org.jdom2.Element AddrLine2_2 = new org.jdom2.Element("AddrLine2");
				AddrLine2_2.setText(jsonResult.getString("AddrLine2_2").trim());

				org.jdom2.Element AddrLine3_2 = new org.jdom2.Element("AddrLine3");
				AddrLine3_2.setText(jsonResult.getString("AddrLine3_2").trim());

				org.jdom2.Element Landmark_2 = new org.jdom2.Element("Landmark");
				Landmark_2.setText(jsonResult.getString("Landmark_2").trim());

				org.jdom2.Element Pincode_2 = new org.jdom2.Element("Pincode");
				Pincode_2.setText(jsonResult.getString("R_PINCODE").trim());

				org.jdom2.Element City_2 = new org.jdom2.Element("City");
				City_2.setText(jsonResult.getString("R_CITY").trim());

				org.jdom2.Element State_2 = new org.jdom2.Element("State");
				State_2.setText(jsonResult.getString("R_STATE").trim());

				org.jdom2.Element Country_2 = new org.jdom2.Element("Country");
				Country_2.setText("INDIA");
//				Country_2.setText(jsonResult.getString("R_COUNTRY").trim());

				org.jdom2.Element AddressType_2 = new org.jdom2.Element("AddressType");
				AddressType_2.setText(jsonResult.getString("Addresstype2").trim());

				org.jdom2.Element HomeTelNo_2 = new org.jdom2.Element("HomeTelNo");
				HomeTelNo_2.setText(jsonResult.getString("HomeTelNo_2").trim());

				org.jdom2.Element OfficeTelNo_2 = new org.jdom2.Element("OfficeTelNo");
				OfficeTelNo_2.setText(jsonResult.getString("OfficeTelNo_2").trim());

				org.jdom2.Element FAXNO_2 = new org.jdom2.Element("FAXNO");
				FAXNO_2.setText(jsonResult.getString("FAXNO_2").trim());

				org.jdom2.Element MobileNo_2 = new org.jdom2.Element("MobileNo");
				MobileNo_2.setText(jsonResult.getString("MobileNo_2").trim());

				org.jdom2.Element EmailAddr_2 = new org.jdom2.Element("EmailAddr");
				EmailAddr_2.setText(jsonResult.getString("EmailAddr_2").trim());

				Address2.addContent(AddrLine1_2);
				Address2.addContent(AddrLine2_2);
				Address2.addContent(AddrLine3_2);
				Address2.addContent(Landmark_2);
				Address2.addContent(Pincode_2);
				Address2.addContent(City_2);
				Address2.addContent(State_2);
				Address2.addContent(Country_2);
				Address2.addContent(AddressType_2);
				Address2.addContent(HomeTelNo_2);
				Address2.addContent(OfficeTelNo_2);
				Address2.addContent(FAXNO_2);
				Address2.addContent(MobileNo_2);
				Address2.addContent(EmailAddr_2);

				client.addContent(Address1);
				client.addContent(Address2);

				org.jdom2.Element Receipt = new org.jdom2.Element("Receipt");

				org.jdom2.Element UniqueTranKey = new org.jdom2.Element("UniqueTranKey");
				UniqueTranKey.setText(jsonResult.getString("UNIQUETRANKEY").trim());

				org.jdom2.Element CheckType = new org.jdom2.Element("CheckType");
				CheckType.setText(jsonResult.getString("CheckType").trim());

				org.jdom2.Element BSBCode = new org.jdom2.Element("BSBCode");
				BSBCode.setText(jsonResult.getString("BSBCode").trim());

				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			    String strDate = sdf.format(today);
			    System.out.println("Current date in String Format: "+strDate);

//			    SimpleDateFormat sdf1 = new SimpleDateFormat();
//			    sdf1.applyPattern("dd/MM/yyyy HH:mm:ss.SS");
//			    Date date = sdf1.parse(strDate);
//			    System.out.println("Current date in Date Format: "+date);
				
				org.jdom2.Element TransactionDate = new org.jdom2.Element("TransactionDate");
				TransactionDate.setText(strDate);
//				TransactionDate.setText("22/01/2018 00:00:01");

				org.jdom2.Element ReceiptType = new org.jdom2.Element("ReceiptType");
				ReceiptType.setText(jsonResult.getString("RECEIPTTYPE").trim());
//				ReceiptType.setText("IVR");

				org.jdom2.Element Amount = new org.jdom2.Element("Amount");
				Amount.setText(jsonResult.getString("Amount").trim());
//				Amount.setText("31965");

				org.jdom2.Element TCSAmount = new org.jdom2.Element("TCSAmount");
				TCSAmount.setText(jsonResult.getString("TcsAmount").trim());

				org.jdom2.Element TranRefNo = new org.jdom2.Element("TranRefNo");
				TranRefNo.setText(jsonResult.getString("TranRefNo").trim());

				org.jdom2.Element TranRefNoDate = new org.jdom2.Element("TranRefNoDate");
				TranRefNoDate.setText(strDate);
//				TranRefNoDate.setText("22/01/2018 00:00:01");

				Receipt.addContent(UniqueTranKey);
				Receipt.addContent(CheckType);
				Receipt.addContent(BSBCode);
				Receipt.addContent(TransactionDate);
				Receipt.addContent(ReceiptType);
				Receipt.addContent(Amount);
				Receipt.addContent(TCSAmount);
				Receipt.addContent(TranRefNo);
				Receipt.addContent(TranRefNoDate);

				org.jdom2.Element Risk = new org.jdom2.Element("Risk");

				org.jdom2.Element RiskType = new org.jdom2.Element("RiskType");
				RiskType.setText(jsonResult.getString("RISKTYPE").trim());

				org.jdom2.Element Zone = new org.jdom2.Element("Zone");
				Zone.setText("Zone " + jsonResult.getString("VEHICLE_ZONE").trim());

				org.jdom2.Element Cover = new org.jdom2.Element("Cover");
				Cover.setText(jsonResult.getString("COVER").trim());

				org.jdom2.Element Vehicle = new org.jdom2.Element("Vehicle");

				org.jdom2.Element TypeOfVehicle = new org.jdom2.Element("TypeOfVehicle");
				TypeOfVehicle.setText(jsonResult.getString("TypeOfVehicle").trim());

				org.jdom2.Element VehicleClass = new org.jdom2.Element("VehicleClass");
				VehicleClass.setText(jsonResult.getString("VehicleClass").trim());

				org.jdom2.Element RTOCode = new org.jdom2.Element("RTOCode");
				RTOCode.setText(jsonResult.getString("RTOCode").trim());

				org.jdom2.Element Make = new org.jdom2.Element("Make");
				Make.setText(jsonResult.getString("MAKE").trim());

				org.jdom2.Element ModelCode = new org.jdom2.Element("ModelCode");
				ModelCode.setText(jsonResult.getString("MODELCODE").trim());

				org.jdom2.Element RegistrationNo = new org.jdom2.Element("RegistrationNo");
				RegistrationNo.setText(jsonResult.getString("RegistrationNo").trim());

				org.jdom2.Element RegistrationDate = new org.jdom2.Element("RegistrationDate");
				RegistrationDate.setText(jsonResult.getString("RegistrationDate").trim());

				org.jdom2.Element ManufacturingYear = new org.jdom2.Element("ManufacturingYear");
				ManufacturingYear.setText(jsonResult.getString("ManufacturingYear").trim());

				org.jdom2.Element FuelType = new org.jdom2.Element("FuelType");
				FuelType.setText(jsonResult.getString("FUELTYPE").trim());

				org.jdom2.Element CNGOrLPG = new org.jdom2.Element("CNGOrLPG");

				org.jdom2.Element InbuiltKit = new org.jdom2.Element("InbuiltKit");
				InbuiltKit.setText(jsonResult.getString("InbuiltKit").trim());

				org.jdom2.Element IVDOfCNGOrLPG = new org.jdom2.Element("IVDOfCNGOrLPG");
				IVDOfCNGOrLPG.setText(jsonResult.getString("IVDOfCNGOrLPG").trim());

				CNGOrLPG.addContent(InbuiltKit);
				CNGOrLPG.addContent(IVDOfCNGOrLPG);

				org.jdom2.Element BodyType = new org.jdom2.Element("BodyType");
				BodyType.setText(jsonResult.getString("BODYTYPE").trim());

				org.jdom2.Element EngineNo = new org.jdom2.Element("EngineNo");
				EngineNo.setText(jsonResult.getString("EngineNo").trim());

				org.jdom2.Element ChassiNo = new org.jdom2.Element("ChassiNo");
				ChassiNo.setText(jsonResult.getString("ChassiNo").trim());

				org.jdom2.Element CubicCapacity = new org.jdom2.Element("CubicCapacity");
				CubicCapacity.setText(jsonResult.getString("CubicCapacity").trim());

				org.jdom2.Element SeatingCapacity = new org.jdom2.Element("SeatingCapacity");
				SeatingCapacity.setText(jsonResult.getString("SeatingCapacity").trim());

				org.jdom2.Element IDV = new org.jdom2.Element("IDV");
				IDV.setText(jsonResult.getString("IDV").trim());

				org.jdom2.Element GrossWeigh = new org.jdom2.Element("GrossWeigh");
				GrossWeigh.setText(jsonResult.getString("GrossWeigh").trim());

				org.jdom2.Element CarriageCapacityFlag = new org.jdom2.Element("CarriageCapacityFlag");
				CarriageCapacityFlag.setText(jsonResult.getString("CarriageCapacityFlag").trim());

				org.jdom2.Element TrailerTowedBy = new org.jdom2.Element("TrailerTowedBy");
				TrailerTowedBy.setText(jsonResult.getString("TrailerTowedBy").trim());

				org.jdom2.Element TrailerRegNo = new org.jdom2.Element("TrailerRegNo");
				TrailerRegNo.setText(jsonResult.getString("TrailerRegNo").trim());

				org.jdom2.Element NoOfTrailer = new org.jdom2.Element("NoOfTrailer");
				NoOfTrailer.setText(jsonResult.getString("NoOfTrailer").trim());

				org.jdom2.Element TrailerValLimPaxIDVDays = new org.jdom2.Element("TrailerValLimPaxIDVDays");
				TrailerValLimPaxIDVDays.setText(jsonResult.getString("TrailerValLimPaxIDVDays").trim());

				Vehicle.addContent(TypeOfVehicle);
				Vehicle.addContent(VehicleClass);
				Vehicle.addContent(RTOCode);
				Vehicle.addContent(Make);
				Vehicle.addContent(ModelCode);
				Vehicle.addContent(RegistrationNo);
				Vehicle.addContent(RegistrationDate);
				Vehicle.addContent(ManufacturingYear);
				Vehicle.addContent(FuelType);
				Vehicle.addContent(CNGOrLPG);
				Vehicle.addContent(BodyType);
				Vehicle.addContent(EngineNo);
				Vehicle.addContent(ChassiNo);
				Vehicle.addContent(CubicCapacity);
				Vehicle.addContent(SeatingCapacity);
				Vehicle.addContent(IDV);
				Vehicle.addContent(GrossWeigh);
				Vehicle.addContent(CarriageCapacityFlag);
				Vehicle.addContent(TrailerTowedBy);
				Vehicle.addContent(TrailerRegNo);
				Vehicle.addContent(NoOfTrailer);
				Vehicle.addContent(TrailerValLimPaxIDVDays);

				org.jdom2.Element InterestParty = new org.jdom2.Element("InterestParty");

				org.jdom2.Element Code = new org.jdom2.Element("Code");
				Code.setText(jsonResult.getString("INTERESTPARTY_CODE").trim());

				org.jdom2.Element BankName = new org.jdom2.Element("BankName");
				BankName.setText(jsonResult.getString("BANKNAME").trim());

				InterestParty.addContent(Code);
				InterestParty.addContent(BankName);

				org.jdom2.Element AdditionalBenefit = new org.jdom2.Element("AdditionalBenefit");

				org.jdom2.Element ElectricalAccessoriesValues = new org.jdom2.Element("ElectricalAccessoriesValues");
				ElectricalAccessoriesValues.setText(jsonResult.getString("ElectricalAccessoriesValues").trim());

				org.jdom2.Element NonElectricalAccessoriesValues = new org.jdom2.Element(
						"NonElectricalAccessoriesValues");
				NonElectricalAccessoriesValues.setText(jsonResult.getString("NonElectricalAccessoriesValues").trim());

				org.jdom2.Element FibreGlassTank = new org.jdom2.Element("FibreGlassTank");
				FibreGlassTank.setText(jsonResult.getString("FibreGlassTank").trim());

				org.jdom2.Element GeographicalArea = new org.jdom2.Element("GeographicalArea");
				GeographicalArea.setText(jsonResult.getString("GeographicalArea").trim());

				org.jdom2.Element PACoverForUnnamedPassengers = new org.jdom2.Element("PACoverForUnnamedPassengers");
				PACoverForUnnamedPassengers.setText(jsonResult.getString("PACoverForUnnamedPassengers").trim());

				org.jdom2.Element LegalLiabilitytoPaidDriver = new org.jdom2.Element("LegalLiabilitytoPaidDriver");
				LegalLiabilitytoPaidDriver.setText(jsonResult.getString("LegalLiabilitytoPaidDriver").trim());

				org.jdom2.Element LegalLiabilityForOtherEmployees = new org.jdom2.Element(
						"LegalLiabilityForOtherEmployees");
				LegalLiabilityForOtherEmployees.setText(jsonResult.getString("LegalLiabilityForOtherEmployees").trim());

				org.jdom2.Element LegalLiabilityForNonFarePayingPassengers = new org.jdom2.Element(
						"LegalLiabilityForNonFarePayingPassengers");
				LegalLiabilityForNonFarePayingPassengers
						.setText(jsonResult.getString("LegalLiabilityForNonFarePayingPassengers").trim());

				org.jdom2.Element UseForHandicap = new org.jdom2.Element("UseForHandicap");
				UseForHandicap.setText(jsonResult.getString("UseForHandicap").trim());

				
				org.jdom2.Element AntiThiefDevice = new org.jdom2.Element("AntiThiefDevice");
				if(jsonResult.getString("AntiThiefDevice").trim().equalsIgnoreCase("Y"))
				{
					AntiThiefDevice.setText("OD01");
				}else{
				AntiThiefDevice.setText(jsonResult.getString("AntiThiefDevice").trim());
				}

				org.jdom2.Element NCB = new org.jdom2.Element("NCB");
				NCB.setText(jsonResult.getString("NCB").trim());

				org.jdom2.Element RestrictedTPPD = new org.jdom2.Element("RestrictedTPPD");
				RestrictedTPPD.setText(jsonResult.getString("RestrictedTPPD").trim());

				org.jdom2.Element PrivateCommercialUsage = new org.jdom2.Element("PrivateCommercialUsage");
				PrivateCommercialUsage.setText(jsonResult.getString("PrivateCommercialUsage").trim());

				org.jdom2.Element IMT23 = new org.jdom2.Element("IMT23");
				IMT23.setText(jsonResult.getString("IMT23").trim());

				org.jdom2.Element CPAReq = new org.jdom2.Element("CPAReq");
				CPAReq.setText(jsonResult.getString("CPAReq").trim());

				org.jdom2.Element CPA = new org.jdom2.Element("CPA");

				org.jdom2.Element CPANomName = new org.jdom2.Element("CPANomName");
				CPANomName.setText(jsonResult.getString("CPANomName").trim());

				org.jdom2.Element CPANomAge = new org.jdom2.Element("CPANomAge");
				CPANomAge.setText(jsonResult.getString("CPANomAge").trim());

				org.jdom2.Element CPANomAgeDet = new org.jdom2.Element("CPANomAgeDet");
				CPANomAgeDet.setText(jsonResult.getString("CPANomAgeDet").trim());

				org.jdom2.Element CPANomPerc = new org.jdom2.Element("CPANomPerc");
				CPANomPerc.setText(jsonResult.getString("CPANomPerc").trim());

				org.jdom2.Element CPARelation = new org.jdom2.Element("CPARelation");
				CPARelation.setText(jsonResult.getString("CPARELATION").trim());

				org.jdom2.Element CPAAppointeeName = new org.jdom2.Element("CPAAppointeeName");
				CPAAppointeeName.setText(jsonResult.getString("CPAAppointeeName").trim());

				org.jdom2.Element CPAAppointeRel = new org.jdom2.Element("CPAAppointeRel");
				CPAAppointeRel.setText(jsonResult.getString("CPAAppointeRel").trim());

				CPA.addContent(CPANomName);
				CPA.addContent(CPANomAge);
				CPA.addContent(CPANomAgeDet);
				CPA.addContent(CPANomPerc);
				CPA.addContent(CPARelation);
				CPA.addContent(CPAAppointeeName);
				CPA.addContent(CPAAppointeRel);

				org.jdom2.Element NPAReq = new org.jdom2.Element("NPAReq");
				NPAReq.setText(jsonResult.getString("NPAReq").trim());

				org.jdom2.Element NPA = new org.jdom2.Element("NPA");

				org.jdom2.Element NPAName = new org.jdom2.Element("NPAName");
				NPAName.setText(jsonResult.getString("NPAName").trim());

				org.jdom2.Element NPALimit = new org.jdom2.Element("NPALimit");
				NPALimit.setText(jsonResult.getString("NPALimit").trim());

				org.jdom2.Element NPANomName = new org.jdom2.Element("NPANomName");
				NPANomName.setText(jsonResult.getString("NPANomName").trim());

				org.jdom2.Element NPANomAge = new org.jdom2.Element("NPANomAge");
				NPANomAge.setText(jsonResult.getString("NPANomAge").trim());

				org.jdom2.Element NPANomAgeDet = new org.jdom2.Element("NPANomAgeDet");
				NPANomAgeDet.setText(jsonResult.getString("NPANomAgeDet").trim());

				org.jdom2.Element NPARel = new org.jdom2.Element("NPARel");
				NPARel.setText(jsonResult.getString("NPARel").trim());

				org.jdom2.Element NPAAppinteeName = new org.jdom2.Element("NPAAppinteeName");
				NPAAppinteeName.setText(jsonResult.getString("NPAAppinteeName").trim());

				org.jdom2.Element NPAAppinteeRel = new org.jdom2.Element("NPAAppinteeRel");
				NPAAppinteeRel.setText(jsonResult.getString("NPAAppinteeRel").trim());

				NPA.addContent(NPAName);
				NPA.addContent(NPALimit);
				NPA.addContent(NPANomName);
				NPA.addContent(NPANomAge);
				NPA.addContent(NPANomAgeDet);
				NPA.addContent(NPARel);
				NPA.addContent(NPAAppinteeName);
				NPA.addContent(NPAAppinteeRel);

				AdditionalBenefit.addContent(ElectricalAccessoriesValues);
				AdditionalBenefit.addContent(NonElectricalAccessoriesValues);
				AdditionalBenefit.addContent(FibreGlassTank);
				AdditionalBenefit.addContent(GeographicalArea);
				AdditionalBenefit.addContent(PACoverForUnnamedPassengers);
				AdditionalBenefit.addContent(LegalLiabilitytoPaidDriver);
				AdditionalBenefit.addContent(LegalLiabilityForOtherEmployees);
				AdditionalBenefit.addContent(LegalLiabilityForNonFarePayingPassengers);
				AdditionalBenefit.addContent(UseForHandicap);
				AdditionalBenefit.addContent(AntiThiefDevice);
				AdditionalBenefit.addContent(NCB);
				AdditionalBenefit.addContent(RestrictedTPPD);
				AdditionalBenefit.addContent(PrivateCommercialUsage);
				AdditionalBenefit.addContent(IMT23);
				AdditionalBenefit.addContent(CPAReq);
				AdditionalBenefit.addContent(CPA);
				AdditionalBenefit.addContent(NPAReq);
				AdditionalBenefit.addContent(NPA);

				org.jdom2.Element AddonReq = new org.jdom2.Element("AddonReq");
				AddonReq.setText(jsonResult.getString("AddonReq").trim());

				org.jdom2.Element Addon = new org.jdom2.Element("Addon");

				org.jdom2.Element CoverCode = new org.jdom2.Element("CoverCode");
				CoverCode.setText(jsonResult.getString("CoverCode").trim());

				Addon.addContent(CoverCode);

				org.jdom2.Element PreviousInsDtls = new org.jdom2.Element("PreviousInsDtls");

				org.jdom2.Element UsedCar = new org.jdom2.Element("UsedCar");
				UsedCar.setText(jsonResult.getString("UsedCar").trim());

				org.jdom2.Element UsedCarList = new org.jdom2.Element("UsedCarList");

				org.jdom2.Element PurchaseDate = new org.jdom2.Element("PurchaseDate");
				PurchaseDate.setText(jsonResult.getString("PurchaseDate").trim());

				org.jdom2.Element InspectionRptNo = new org.jdom2.Element("InspectionRptNo");
				InspectionRptNo.setText(jsonResult.getString("InspectionRptNo").trim());

				org.jdom2.Element InspectionDt = new org.jdom2.Element("InspectionDt");
				InspectionDt.setText(jsonResult.getString("InspectionDt").trim());

				UsedCarList.addContent(PurchaseDate);
				UsedCarList.addContent(InspectionRptNo);
				UsedCarList.addContent(InspectionDt);

				org.jdom2.Element RollOver = new org.jdom2.Element("RollOver");
				RollOver.setText(jsonResult.getString("RollOver").trim());

				org.jdom2.Element RollOverList = new org.jdom2.Element("RollOverList");

				org.jdom2.Element RollOverPolicyNo = new org.jdom2.Element("PolicyNo");
				RollOverPolicyNo.setText(jsonResult.getString("RollOverPolicyNo").trim());

				org.jdom2.Element InsuredName = new org.jdom2.Element("InsuredName");
				InsuredName.setText(jsonResult.getString("InsuredName").trim());

				org.jdom2.Element PreviousPolExpDt = new org.jdom2.Element("PreviousPolExpDt");
				PreviousPolExpDt.setText(jsonResult.getString("PreviousPolExpDt").trim());

				org.jdom2.Element ClientCode = new org.jdom2.Element("ClientCode");
				ClientCode.setText(jsonResult.getString("ClientCode").trim());

				org.jdom2.Element rollOverAddress1 = new org.jdom2.Element("Address1");
				rollOverAddress1.setText(jsonResult.getString("rollOverAddress1").trim());

				org.jdom2.Element rollOverAddress2 = new org.jdom2.Element("Address2");
				rollOverAddress2.setText(jsonResult.getString("rollOverAddress2").trim());

				org.jdom2.Element rollOverAddress3 = new org.jdom2.Element("Address3");
				rollOverAddress3.setText(jsonResult.getString("rollOverAddress3").trim());

				org.jdom2.Element rollOverAddress4 = new org.jdom2.Element("Address4");
				rollOverAddress4.setText(jsonResult.getString("rollOverAddress4").trim());

				org.jdom2.Element rollOverAddress5 = new org.jdom2.Element("Address5");
				rollOverAddress5.setText(jsonResult.getString("rollOverAddress5").trim());

				org.jdom2.Element rollOverPinCode = new org.jdom2.Element("PinCode");
				rollOverPinCode.setText(jsonResult.getString("rollOverPinCode").trim());

				org.jdom2.Element rollOverInspectionRptNo = new org.jdom2.Element("InspectionRptNo");
				rollOverInspectionRptNo.setText(jsonResult.getString("rollOverInspectionRptNo").trim());

				org.jdom2.Element rollOverInspectionDt = new org.jdom2.Element("InspectionDt");
				rollOverInspectionDt.setText(jsonResult.getString("InspectionDt").trim());

				org.jdom2.Element NCBDeclartion = new org.jdom2.Element("NCBDeclartion");
				NCBDeclartion.setText(jsonResult.getString("NCBDeclartion").trim());

				org.jdom2.Element ClaimInExpiringPolicy = new org.jdom2.Element("ClaimInExpiringPolicy");
				ClaimInExpiringPolicy.setText(jsonResult.getString("ClaimInExpiringPolicy").trim());

				org.jdom2.Element NCBInExpiringPolicy = new org.jdom2.Element("NCBInExpiringPolicy");
				NCBInExpiringPolicy.setText(jsonResult.getString("NCBInExpiringPolicy").trim());

				RollOverList.addContent(RollOverPolicyNo);
				RollOverList.addContent(InsuredName);
				RollOverList.addContent(PreviousPolExpDt);
				RollOverList.addContent(ClientCode);
				RollOverList.addContent(rollOverAddress1);
				RollOverList.addContent(rollOverAddress2);
				RollOverList.addContent(rollOverAddress3);
				RollOverList.addContent(rollOverAddress4);
				RollOverList.addContent(rollOverAddress5);
				RollOverList.addContent(rollOverPinCode);
				RollOverList.addContent(rollOverInspectionRptNo);
				RollOverList.addContent(rollOverInspectionDt);
				RollOverList.addContent(NCBDeclartion);
				RollOverList.addContent(ClaimInExpiringPolicy);
				RollOverList.addContent(NCBInExpiringPolicy);

				org.jdom2.Element NewVehicle = new org.jdom2.Element("NewVehicle");
				NewVehicle.setText(jsonResult.getString("NewVehicle").trim());

				org.jdom2.Element NewVehicleList = new org.jdom2.Element("NewVehicleList");

				org.jdom2.Element newVehicleInspectionRptNo = new org.jdom2.Element("InspectionRptNo");
				newVehicleInspectionRptNo.setText(jsonResult.getString("newVehicleInspectionRptNo").trim());

				org.jdom2.Element newVehicleInspectionDt = new org.jdom2.Element("InspectionDt");
				newVehicleInspectionDt.setText(jsonResult.getString("newVehicleInspectionDt").trim());

				NewVehicleList.addContent(newVehicleInspectionRptNo);
				NewVehicleList.addContent(newVehicleInspectionDt);

				PreviousInsDtls.addContent(UsedCar);
				PreviousInsDtls.addContent(UsedCarList);
				PreviousInsDtls.addContent(RollOver);
				PreviousInsDtls.addContent(RollOverList);
				PreviousInsDtls.addContent(NewVehicle);
				PreviousInsDtls.addContent(NewVehicleList);

				Risk.addContent(RiskType);
				Risk.addContent(Zone);
				Risk.addContent(Cover);
				Risk.addContent(Vehicle);
				Risk.addContent(InterestParty);
				Risk.addContent(AdditionalBenefit);
				Risk.addContent(AddonReq);
				Risk.addContent(Addon);
				Risk.addContent(PreviousInsDtls);

				rootelement.addContent(uid);
				rootelement.addContent(vendorCode);
				rootelement.addContent(vendorUserId);
				rootelement.addContent(policyHeader);
				rootelement.addContent(pos_MISP);
				rootelement.addContent(client);
				rootelement.addContent(Receipt);
				rootelement.addContent(Risk);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xx = xmlOutput.outputString(document2);

				xmlOutput.output(document2, new FileWriter(path + "\\futureGenerali.xml"));
				//
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("//////////////////////////" + e);
		}
		return xx;

	}

	
	@RequestMapping(value = "/futureGPayment", method = RequestMethod.GET)
	public org.springframework.web.servlet.ModelAndView kotakPayment(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String paymentResponse = null;

		String transactionId= request.getParameter("transactionId");
		String paymentOption= request.getParameter("paymentOption");
		String responseUrl= request.getParameter("responseUrl");
		futureGInteBean.setProposalNo(request.getParameter("proposalNo"));
		String premiumAmount= request.getParameter("premiumAmount");
		String userIdentifier= request.getParameter("userIdentifier");
		String userIdFG= request.getParameter("userIdFG");	
		futureGInteBean.setFirstName(request.getParameter("FirstName"));
		futureGInteBean.setLastName(request.getParameter("lastName"));
		futureGInteBean.setEmail(request.getParameter("Email"));
		futureGInteBean.setPhone(request.getParameter("Phone"));
		futureGInteBean.setCustomerID(request.getParameter("customerID"));
		futureGInteBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
		futureGInteBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
		futureGInteBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
		futureGInteBean.setUserId(request.getParameter("userId"));
		futureGInteBean.setUserDesc(request.getParameter("userDesc"));
		futureGInteBean.setBranchId(request.getParameter("branchId"));
		futureGInteBean.setIPAddress(request.getParameter("IPAddress"));
		
		System.out.println("transactionId::" +transactionId);
		System.out.println("paymentOption::" +paymentOption);
		System.out.println("responseUrl::" +responseUrl);
		System.out.println("premiumAmount::" +premiumAmount);
		System.out.println("userIdentifier::" +userIdentifier);
		System.out.println("userIdFG::" +userIdFG);
		
		/*String checksum =transactionId+"|"+paymentOption+"|"+responseUrl+"|"+futureGInteBean.getProposalNo()+"|"+premiumAmount+"|"+userIdentifier+"|"+
		 userIdFG+"|"+futureGInteBean.getFirstName()+"|"+futureGInteBean.getLastName()+"|"+futureGInteBean.getPhone()+"|"+futureGInteBean.getEmail()+"|" ;
		
		System.out.println("passToHash ===============> " + checksum);

		 
		 BufferedWriter output = null;
	        try {
	        	Properties props = null;
				try {
					props = Utils.readProperties("datasource.properties");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				String sourceFileName = props.getProperty("server.path")+"/hash/shaString.txt";
				
				File file = new File(sourceFileName);
				if(file.exists()){
					file.delete();
				}
				file.createNewFile();
	            output = new BufferedWriter(new FileWriter(file));
	            output.write(checksum);
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	          if ( output != null ) {
	            try {
					output.close();
				} catch (IOException e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	          } 
	        } 
		 
		boolean hash = futureGDao.getShah256(checksum);*/
		 
		HashMap<String,String> payRequest=new HashMap<>();
		
		payRequest.put("transactionId", transactionId);
		payRequest.put("paymentOption", paymentOption);
		payRequest.put("responseUrl", responseUrl);
		payRequest.put("proposalNo", futureGInteBean.getProposalNo());
		payRequest.put("premiumAmount", premiumAmount);
		payRequest.put("userIdentifier", userIdentifier);
		payRequest.put("userIdFG", userIdFG);
		payRequest.put("FirstName", futureGInteBean.getFirstName());
		payRequest.put("lastName", futureGInteBean.getLastName());
		payRequest.put("Email", futureGInteBean.getEmail());
		payRequest.put("Phone", futureGInteBean.getPhone());
		payRequest.put("actionUrl", "http://fglpg001.futuregenerali.in/Ecom_NL/WEBAPPLN/UI/Common/WebAggPay.aspx");
			
		System.out.println("payRequest" +payRequest);
        org.springframework.web.servlet.ModelAndView andView=new org.springframework.web.servlet.ModelAndView("common/FutureGeneraliPayment");
        andView.addObject("data", payRequest);
        return andView;
		 
	}
	
	@RequestMapping(value = "/FutureGPaymentStatus", method = RequestMethod.POST)
	public org.springframework.web.servlet.ModelAndView TransactionStatusPost(
			HttpServletRequest request, HttpServletResponse response)
			throws JSONException {
		String jsonResponse = null;

		String WSPId= request.getParameter("WS_P_ID");
		String transactionId= request.getParameter("TID");
		String PGID= request.getParameter("PGID");
		String Premium= request.getParameter("Premium");
		String Response= request.getParameter("Response");
		
     	
		
	/*System.out.println("mihpayid-" + mihpayid + "mode-" + mode + "key-" + key + "amount-" + amount + "email- " + email + "firstName- " + firstName 
				+ "PG_TYPE" + PG_TYPE + "error_code" + error_code);
		System.out.println("txnid-" + txnid + "status-" + status + "hashResp- " + hashResp + "unmappedstatus- " + unmappedstatus 
				+ "bank_ref_num" + bank_ref_num);

		 String passToHash = salt+"|"+status+"|||||||||||"+"|"+email+"|"+firstName+"|"+productinfo+"|"+amount+"|"+txnid+"|"+key;
		 System.out.println("passToHashResp ===============> " + passToHash);
		
		 BufferedWriter output = null;
	        try {
	        	
	        	Properties props = null;
				try {
					props = Utils.readProperties("datasource.properties");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				String sourceFileName = props.getProperty("server.path")+"/hash/shaString.txt";
	        	
	            File file = new File(sourceFileName);
	            output = new BufferedWriter(new FileWriter(file));
	            output.write(passToHash);
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        } finally {
	          if ( output != null ) {
	            try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	        }
		 
		 
		String hashRes = kotakDao.getSha512(passToHash);*/
		
		HashMap<String, String> payResponse = new HashMap<>();

		payResponse.put("WS_P_ID", WSPId);
		payResponse.put("transactionId", transactionId);
		payResponse.put("paymentGatewayID", PGID);
		payResponse.put("premiumAmount", Premium);
		payResponse.put("responseStatus", Response);
			
		
		System.out.println("payResponse---" + payResponse);
		
//		Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(futureGInteBean.getMotorGroupResponseGroupId()), Integer.parseInt(futureGInteBean.getMotorGroupResponseGicId()), futureGInteBean.getMotorGroupResponseSessionId(),
//					2, preProResponse, futureGInteBean.getIPAddress(), futureGInteBean.getUserId(), futureGInteBean.getBranchId(),
//					futureGInteBean.getUserDesc());
		
		System.out.println("In transaction status");
		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView("common/FutureGTransactionStatus");
		andView.addObject("data", payResponse);
		return andView;

	}
	
	
	
	
}
