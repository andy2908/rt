package com.uat.hbc.common.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import groovyjarjarantlr.collections.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.uat.hbc.common.dao.IntegrationSaveResponseDao;
import com.uat.hbc.common.dao.IntegrationUniversalDao;
import com.uat.hbc.common.model.MotorResponseBean;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.UniversalIntgBean;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;
import com.universal.IService1;
import com.universal.Service1;

import groovy.xml.Namespace;

@Controller
public class MotorCalculatorUniversalController {
	@Autowired
	IntegrationUniversalDao universalDao;
	@Autowired
    IntegrationSaveResponseDao integrationSaveResponseDao;
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;
	   String path=System.getProperty("user.home");
		HashMap<String, String> hashmapResult = new HashMap<>();
		HashMap data;
		String request_for, jsonNames;
		String documentType;
		Result objResult;
		HashMap mapFinalResponseUni ;
		MotorResponseBean bean;
		UniversalIntgBean universalIntgBean;
		
		
		
		public MotorCalculatorUniversalController() {
			 mapFinalResponseUni = new HashMap();
			 bean=new MotorResponseBean();
			 universalIntgBean = new UniversalIntgBean();
		}

		@RequestMapping("user/ADUniversal")
		public ModelAndView callJsp(HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("user/ADUniversal");
			return modelAndView;
		}
		
		@RequestMapping(value = "/USGImotorcalculator", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public @ResponseBody String getParameters(HttpServletRequest request,
				HttpServletResponse response) throws JDOMException, IOException, JSONException {
			 
			universalIntgBean = new UniversalIntgBean();
			String pkg_name = request.getParameter("pkg_name");
			String proc_name = request.getParameter("proc_name");
			String request_for = request.getParameter("request_for");
			String tableName = request.getParameter("tableName");
			String rtoCity = request.getParameter("rtoCity");
			String zoneId = request.getParameter("zoneId");
			String policyType = request.getParameter("policyType");
			String varId = request.getParameter("varId");
			String businessType = request.getParameter("businessType");
			String productId = request.getParameter("productId");
			String vehId = request.getParameter("vehId");
			String modelId = request.getParameter("modelId");
			String customerType = request.getParameter("customerType");
			String finId = request.getParameter("finId");
			String prevGICId = request.getParameter("prevGICId");
			String nomineeRel = request.getParameter("nomineeRel");
			String prevProduct = request.getParameter("prevProduct");
			String prevPolicyType = request.getParameter("prevPolicyType");
			String finAgreementType = request.getParameter("finAgreementType");
			String bodyType = request.getParameter("bodyType");
			String presentAreaId = request.getParameter("presentAreaId");
			String permanentAreaId = request.getParameter("permanentAreaId");
			String coverVal = request.getParameter("coverVal");
			String coverNo = request.getParameter("coverNo");
			String posPolicyNo = request.getParameter("PosPolicyNo");
		
			
			String covers = request.getParameter("covers");
			
			HashMap inputParaList = new HashMap<>();
			inputParaList.put("PI_TABLE_NAME", tableName);
			inputParaList.put("PI_Rto_City", Integer.parseInt(rtoCity));
			inputParaList.put("PI_ZONE_ID", Integer.parseInt(zoneId));
			inputParaList.put("PI_PolicyType", Integer.parseInt(policyType));
			inputParaList.put("PI_VarID", Integer.parseInt(varId));
			inputParaList.put("PI_P_BusinessType", Integer.parseInt(businessType));
			inputParaList.put("PI_ProductID", Integer.parseInt(productId));
			inputParaList.put("PI_VehID", Integer.parseInt(vehId));
			inputParaList.put("PI_MODELID", Integer.parseInt(modelId));
			inputParaList.put("PI_CUST_TYPE", Integer.parseInt(customerType));
			inputParaList.put("PI_FIN_ID", (finId));
			inputParaList.put("PI_PREV_GIC_ID", (prevGICId));
			inputParaList.put("PI_Nom_Rel", (nomineeRel));
			inputParaList.put("PI_Prev_Product", (prevProduct));
			inputParaList.put("PI_Prev_PolicyType", (prevPolicyType));
			inputParaList.put("PI_FIN_AgreementType", (finAgreementType));
			inputParaList.put("PI_Bodytype", (bodyType));
			inputParaList.put("PI_Present_AreaID", (presentAreaId));
			inputParaList.put("PI_Permanent_AreaID", (permanentAreaId));
			inputParaList.put("PI_posPolicyNo", (posPolicyNo));
			
			inputParaList.put("PI_Covers", covers);
			inputParaList.put("PI_COV_VAL", coverVal);
			inputParaList.put("PI_COV_NO", coverNo);
			
			System.out.println("tableName--------->>"+tableName);
			System.out.println("rtoCity--------->>"+rtoCity);
			System.out.println("zoneId--------->>"+zoneId);
			System.out.println("policyType--------->>"+policyType);
			System.out.println("varId--------->>"+varId);
			System.out.println("businessType--------->>"+businessType);
			System.out.println("productId--------->>"+productId);
			System.out.println("vehId--------->>"+vehId);
			System.out.println("customerType--------->>"+customerType);
			System.out.println("finId--------->>"+finId);
			System.out.println("prevGICId--------->>"+prevGICId);
			System.out.println("nomineeRel--------->>"+nomineeRel);
			System.out.println("prevProduct--------->>"+prevProduct);
			System.out.println("prevPolicyType--------->>"+prevPolicyType);
			System.out.println("finAgreementType--------->>"+finAgreementType);
			System.out.println("bodyType--------->>"+bodyType);
			System.out.println("presentAreaId--------->>"+presentAreaId);
			System.out.println("permanentAreaId--------->>"+permanentAreaId);
			System.out.println("modelId--------->>"+modelId);
	
			System.out.println("covers--------->>"+covers);
			System.out.println("coverVal--------->>"+coverVal);
			System.out.println("coverNo--------->>"+coverNo);
			 
			System.out.println("inputParalist :: " +inputParaList);
			jsonNames = universalDao.getUniversalData(pkg_name, proc_name, inputParaList);
			
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
			
			//***************************************************************************
			String jsonnm=jsonNames.replace("[", "").replace("]", "");
			System.out.println("jsonnm==>>"+jsonnm);
			System.out.println("jsonNames==>>"+jsonNames);
			if(!jsonnm.equals("")){
				System.out.println("##############################################");
			
			
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
			            	coverNo = "" + obj.getInt("COV_NO");
			                System.out.println("1111111111 coverNo==>>"+coverNo);
			            }
			            if (!obj.isNull("COVER_VAL")) {
			            	coverVal = "" + obj.getString("COVER_VAL");
			                System.out.println("1111111111 coverVal==>>"+coverVal);
			            }
			            
			            //Note = After Getting all covers Id Change Remaining Covers Comparison (Compare with Id Instead Of Name)
			            
			            if (coverId.equalsIgnoreCase("1")) {
			            	universalIntgBean.setSumInsBasicOd(coverVal);
			            	universalIntgBean.setAppBasicOd("True");
						}
			            
			            if (coverId.equalsIgnoreCase("51")) {
			            	universalIntgBean.setSumInsBasicTp(coverVal);
			            	universalIntgBean.setAppBasicTp("True");
						}
			            if (coverName.equalsIgnoreCase("PA COVER TO OWNER DRIVER")) {
			            	universalIntgBean.setSumInsPaCovToOwnDriver(coverVal);
			            	universalIntgBean.setAppPaCovToOwnDriver("True");
						}
			            if (coverId.equalsIgnoreCase("9")) {
			            	universalIntgBean.setSumInsEleAccOd(coverVal);
			            	universalIntgBean.setAppEleAccOd("True");
						}
			            if (coverId.equalsIgnoreCase("2")) {
			            	universalIntgBean.setSumInsNonEleAccOd(coverVal);
			            	universalIntgBean.setAppNonEleAccOd("True");
						}
			            if (coverId.equalsIgnoreCase("108")) {
			            	universalIntgBean.setSumInsCnglpgKitOd(coverVal);
			            	universalIntgBean.setAppCnglpgKitOd("True");
						}
			            if (coverId.equalsIgnoreCase("108")) {
			            	universalIntgBean.setSumInsCnglpgKitTp(coverVal);
			            	universalIntgBean.setAppCnglpgKitTp("True");
						}
			            if (coverId.equalsIgnoreCase("105")) {
			            	universalIntgBean.setSumInsBulCnglpgKitOd(coverVal);
			            	universalIntgBean.setAppBulCnglpgKitOd("True");
						}
			            if (coverId.equalsIgnoreCase("105")) {
			            	universalIntgBean.setSumInsBulCnglpgKitTp(coverVal);
			            	universalIntgBean.setAppBulCnglpgKitTp("True");
						}
			            if (coverId.equalsIgnoreCase("22")) {
			            	universalIntgBean.setSumInsfiberTankOd(coverVal);
			            	universalIntgBean.setAppfiberTankOd("True");
						}
			            if (coverName.equalsIgnoreCase("LEGAL LIABILITY TO PAID DRIVER")) {
			            	universalIntgBean.setSumInsLigLiaToPaidDriver(coverVal);
			            	universalIntgBean.setAppLigLiaToPaidDriver("True");
						}
			            if (coverId.equalsIgnoreCase("54")) {
			            	universalIntgBean.setSumInsUnamPaCovToPassang(coverVal);
			            	universalIntgBean.setAppUnamPaCovToPassang("True");
						}
			            if (coverName.equalsIgnoreCase("PA COVER TO EMPLOYEES OF INSURED")) {
			            	universalIntgBean.setSumInsPaCovToEmpOfInsured(coverVal);
			            	universalIntgBean.setAppPaCovToEmpOfInsured("True");
						}
			            if (coverName.equalsIgnoreCase("PA COVER TO PASSENGERS")) {
			            	universalIntgBean.setSumInsPaCovToPassanger(coverVal);
			            	universalIntgBean.setAppPaCovToPassanger("True");
						}
			            if (coverName.equalsIgnoreCase("PA COVER TO PAID DRIVER")) {
			            	universalIntgBean.setSumInsPaCovToPaidDriver(coverVal);
			            	universalIntgBean.setAppPaCovToPaidDriver("True");
						}
			            if (coverName.equalsIgnoreCase("Other OD")) {
			            	universalIntgBean.setSumInsOtherOd(coverVal);
			            	universalIntgBean.setAppOtherOd("True");
						}
			            if (coverName.equalsIgnoreCase("Other TP")) {
			            	universalIntgBean.setSumInsOtherTp(coverVal);
			            	universalIntgBean.setAppOtherTp("True");
						}
			            
			            if (coverId.equalsIgnoreCase("8")) {
			            	universalIntgBean.setSumInsNilDepWaiCover(coverVal);
			            	universalIntgBean.setAppNilDepWaiCover("True");
						}
			            if (coverName.equalsIgnoreCase("DAILY CASH ALLOWANCE")) {
			            	universalIntgBean.setSumInsDailyCashAllow(coverVal);
			            	universalIntgBean.setAppDailyCashAllow("True");
						}
			            if (coverName.equalsIgnoreCase("KEY REPLACEMENT")) {
			            	universalIntgBean.setSumInsKeyReplacement(coverVal);
			            	universalIntgBean.setAppKeyReplacement("True");
						}
			          
			            if (coverName.equalsIgnoreCase("RETURN TO INVOICE")) {
			            	universalIntgBean.setSumInsReturnToInvoice(coverVal);
			            	universalIntgBean.setAppReturnToInvoice("True");
						}
			            if (coverName.equalsIgnoreCase("ACCIDENTAL HOSPITALIZATION")) {
			            	universalIntgBean.setSumInsAccHospit(coverVal);
			            	universalIntgBean.setAppAccHospit("True");
						}
			            if (coverName.equalsIgnoreCase("Road side Assistance")) {
			            	universalIntgBean.setSumInsRoadSideAssis(coverVal);
			            	universalIntgBean.setAppRoadSideAssis("True");
						}
			            if (coverName.equalsIgnoreCase("HYDROSTATIC LOCK COVER")) {
			            	universalIntgBean.setSumInsHydrostaticLockCover(coverVal);
			            	universalIntgBean.setAppHydrostaticLockCover("True");
						}
			            if (coverName.equalsIgnoreCase("COST OF CONSUMABLES")) {
			            	universalIntgBean.setSumInsCostOfConsumable(coverVal);
			            	universalIntgBean.setAppCostOfConsumable("True");
						}
			            if (coverName.equalsIgnoreCase("SECURE TOWIN")) {
			            	universalIntgBean.setSumInsSecureTowing(coverVal);
			            	universalIntgBean.setAppSecureTowing("True");
						}
			           
			            if (coverId.equalsIgnoreCase("21")) {
			            	universalIntgBean.setSumInsAntiDeviceDisc(coverVal);
			            	universalIntgBean.setAppAntiDeviceDisc("True");
						}
			            if (coverId.equalsIgnoreCase("104")) {
			            	universalIntgBean.setSumInsVolDeductible(coverVal);
			            	universalIntgBean.setAppVolDeductible("True");
						}
			            if (coverName.equalsIgnoreCase("No claim bonus")) {
			            	universalIntgBean.setSumInsNCB(coverVal);
			            	universalIntgBean.setAppNCB("True");
						}
			            if (coverId.equalsIgnoreCase("52")) {
			            	universalIntgBean.setSumInsTppdDiscount(coverVal);
			            	universalIntgBean.setAppTppdDiscount("True");
						}
			            if (coverId.equalsIgnoreCase("109")) {
			            	universalIntgBean.setSumInsAutoAssoDiscount(coverVal);
			            	universalIntgBean.setAppAutoAssoDiscount("True");
						}
			            if (coverId.equalsIgnoreCase("24")) {
			            	universalIntgBean.setSumInsHandicapDiscount(coverVal);
			            	universalIntgBean.setAppHandicapDiscount("True");
						}
			            
					}
				}
			
			//***************************************************************************

			universalIntgBean.setAppBasicOd("True");
			universalIntgBean.setAppBasicTp("True");
		
			String customerName = request.getParameter("CustomerName");
			String dob = request.getParameter("DOB");
			String gender = request.getParameter("Gender");
			String canBeParent = request.getParameter("CanBeParent");
			String contactTelephoneSTD = request.getParameter("ContactTelephoneSTD");
			String mobileNo = request.getParameter("MobileNo");
			String emailid = request.getParameter("Emailid");
			String presentAddressLine1 = request.getParameter("PresentAddressLine1");
			String presentAddressLine2 = request.getParameter("PresentAddressLine2");
			String permanentAddressLine1 = request.getParameter("PermanentAddressLine1");
			String permanentAddressLine2 = request.getParameter("PermanentAddressLine2");
			String custGSTNo = request.getParameter("CustGSTNo");
			String instrumentNo = request.getParameter("InstrumentNo");
			String instrumentDate = request.getParameter("InstrumentDate");
			String bankID = request.getParameter("BankID");
			String nomineeName = request.getParameter("NomineeName");
			String nomineeRelation = request.getParameter("NomineeRelation");
			String iMDBranchName = request.getParameter("IMDBranchName");
			String iMDBranchCode = request.getParameter("IMDBranchCode");
			String sPName = request.getParameter("SPName");
			String sPCode = request.getParameter("SPCode");
			String proposalDate = request.getParameter("ProposalDate");
			String policyNumberChar = request.getParameter("PolicyNumberChar");
			String vehicleLaidUpFrom = request.getParameter("VehicleLaidUpFrom");
			String vehicleLaidUpTo = request.getParameter("VehicleLaidUpTo");
			String fromdate = request.getParameter("Fromdate");
			String todate = request.getParameter("Todate");
			String fromhour = request.getParameter("Fromhour");
			String tohour = request.getParameter("Tohour");
			String financierCode = request.getParameter("FinancierCode");
			String agreementType = request.getParameter("AgreementType");
			String branchName = request.getParameter("BranchName");
			String financierName = request.getParameter("FinancierName");
			String srNo = request.getParameter("SrNo");
			String productCode = request.getParameter("ProductCode");
			String claimSettled = request.getParameter("ClaimSettled");
			String claimPremium = request.getParameter("ClaimPremium");
			String claimAmount = request.getParameter("ClaimAmount");
			String dateofLoss = request.getParameter("DateofLoss");
			String natureofLoss = request.getParameter("NatureofLoss");
			String claimNo = request.getParameter("ClaimNo");
			String policyEffectiveTo = request.getParameter("PolicyEffectiveTo");
			String policyEffectiveFrom = request.getParameter("PolicyEffectiveFrom");
			String dateOfInspection = request.getParameter("DateOfInspection");
			String policyPremium = request.getParameter("PolicyPremium");
			String policyNo = request.getParameter("PolicyNo");
			String policyYear = request.getParameter("PolicyYear");
			String officeCode = request.getParameter("OfficeCode");
			String policyStatus = request.getParameter("PolicyStatus");
			String corporateCustomerId = request.getParameter("CorporateCustomerId");
			String insurerName = request.getParameter("InsurerName");
			String insurerAddress = request.getParameter("InsurerAddress");
			String previousPolicyType = request.getParameter("PreviousPolicyType");
			String officeAddress = request.getParameter("OfficeAddress");
			String netPremium = request.getParameter("NetPremium");
			String serviceTax = request.getParameter("ServiceTax");
			String stampDuty2 = request.getParameter("StampDuty2");
			String cGST = request.getParameter("CGST");
			String sGST = request.getParameter("SGST");
			String uGST = request.getParameter("UGST");
			String iGST = request.getParameter("IGST");
			String totalPremium = request.getParameter("TotalPremium");
			String loadingAmount = request.getParameter("LoadingAmount");
			String loadingRate = request.getParameter("LoadingRate");
			String sumInsured = request.getParameter("SumInsured");
			String rate = request.getParameter("Rate");
			String premium = request.getParameter("Premium");
			String applicable = request.getParameter("Applicable");
			String description = request.getParameter("Description");
			String discountAmount = request.getParameter("DiscountAmount");
			String discountRate = request.getParameter("DiscountRate");
			String discountSumInsured = request.getParameter("DiscountSumInsured");
			String discountRate1 = request.getParameter("DiscountRate1");
			String discountPremium = request.getParameter("DiscountPremium");
			String discountApplicable = request.getParameter("DiscountApplicable");
			String discountDescription = request.getParameter("DiscountDescription");
			String rTOLocationCode = request.getParameter("RTOLocationCode");
			String noOfClaimsOnPreviousPolicy = request.getParameter("NoOfClaimsOnPreviousPolicy");
			String registrationNumber = request.getParameter("RegistrationNumber");
			String bodyTypeCode = request.getParameter("BodyTypeCode");
			String modelStatus = request.getParameter("ModelStatus");
			String vehicleExShowroomPrice = request.getParameter("VehicleExShowroomPrice");
			String dateOfDeliveryOrRegistration = request.getParameter("DateOfDeliveryOrRegistration");
			String yearOfManufacture = request.getParameter("YearOfManufacture");
			String dateOfFirstRegistration = request.getParameter("DateOfFirstRegistration");
			String registrationNumberSection1 = request.getParameter("RegistrationNumberSection1");
			String registrationNumberSection2 = request.getParameter("RegistrationNumberSection2");
			String registrationNumberSection3 = request.getParameter("RegistrationNumberSection3");
			String registrationNumberSection4 = request.getParameter("RegistrationNumberSection4");
			String engineNumber = request.getParameter("EngineNumber");
			String chassisNumber = request.getParameter("ChassisNumber");
			String extensionCountryName = request.getParameter("ExtensionCountryName");
			String automobileAssocnFlag = request.getParameter("AutomobileAssocnFlag");
			String automobileAssociationNumber = request.getParameter("AutomobileAssociationNumber");
			String voluntaryExcess = request.getParameter("VoluntaryExcess");
			String tPPDLimit = request.getParameter("TPPDLimit");
			String antiTheftDiscFlag = request.getParameter("AntiTheftDiscFlag");
			String handicapDiscFlag = request.getParameter("HandicapDiscFlag");
			String numberOfDrivers = request.getParameter("NumberOfDrivers");
			String numberOfEmployees = request.getParameter("NumberOfEmployees");
			String transferOfNCB = request.getParameter("TransferOfNCB");
			String transferOfNCBPercent = request.getParameter("TransferOfNCBPercent");
			String nCBDeclaration = request.getParameter("NCBDeclaration");
			String previousVehicleSaleDate = request.getParameter("PreviousVehicleSaleDate");
			String bonusOnPreviousPolicy = request.getParameter("BonusOnPreviousPolicy");
			String vehicleClass = request.getParameter("VehicleClass");
			String vehicleMake = request.getParameter("VehicleMake");
			String bodyTypeDescription = request.getParameter("BodyTypeDescription");
//			String numberOfWheels = request.getParameter("NumberOfWheels");
//			String cubicCapacity = request.getParameter("CubicCapacity");
//			String seatingCapacity = request.getParameter("SeatingCapacity");
//			String registrationZone = request.getParameter("RegistrationZone");
			String vehiclesDrivenBy = request.getParameter("VehiclesDrivenBy");
			String driversAge = request.getParameter("DriversAge");
			String driversExperience = request.getParameter("DriversExperience");
			String driversQualification = request.getParameter("DriversQualification");
			String vehicleModelCluster = request.getParameter("VehicleModelCluster");
			String openCoverNoteFlag = request.getParameter("OpenCoverNoteFlag");
			String legalLiability = request.getParameter("LegalLiability");
			String paidDriver = request.getParameter("PaidDriver");
			String nCBConfirmation = request.getParameter("NCBConfirmation");
			String registrationDate = request.getParameter("RegistrationDate");
			String tPLoadingRate = request.getParameter("TPLoadingRate");
			String extensionCountry = request.getParameter("ExtensionCountry");
			String vehicleAge = request.getParameter("VehicleAge");
			String locationCode = request.getParameter("LocationCode");
			String registrationZoneDescription = request.getParameter("RegistrationZoneDescription");
			String numberOfWorkmen = request.getParameter("NumberOfWorkmen");
			String vehicCd = request.getParameter("VehicCd");
			String salesTax = request.getParameter("SalesTax");
			String modelOfVehicle = request.getParameter("ModelOfVehicle");
			String populateDetails = request.getParameter("PopulateDetails");
			String vehicleIDV = request.getParameter("VehicleIDV");
			String showroomPriceDeviation = request.getParameter("ShowroomPriceDeviation");
			String newVehicle = request.getParameter("NewVehicle");
			
			String paymentId = request.getParameter("PaymentId");
			String mICRCheque = request.getParameter("MICRCheque");
			String instrumentDatePayment = request.getParameter("InstrumentDatePayment");
			String draweeBankName = request.getParameter("DraweeBankName");
			String hOUSEBANKNAME = request.getParameter("HOUSEBANKNAME");
			String amountPaid = request.getParameter("AmountPaid");
			String paymentType = request.getParameter("PaymentType");
			String paymentMode = request.getParameter("PaymentMode");
			String instrumentNoPayment = request.getParameter("InstrumentNoPayment");
			String status = request.getParameter("Status");
			String depositSlipNo = request.getParameter("DepositSlipNo");
			String payerType = request.getParameter("PayerType");
			String presentPinCode = request.getParameter("PresentPinCode");
			
			
			universalIntgBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
			universalIntgBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
			universalIntgBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
			universalIntgBean.setUserId(request.getParameter("userId"));
			universalIntgBean.setUserDesc(request.getParameter("userDesc"));
			universalIntgBean.setBranchId(request.getParameter("branchId"));
			universalIntgBean.setIPAddress(request.getParameter("IpAddress"));
			
			
			if(request_for.equalsIgnoreCase("Premium"))
			{
				documentType = "Quotation"; 
			}
			else if(request_for.equalsIgnoreCase("proposal"))
			{
				documentType = "Proposal"; 
			}
			if(gender.equalsIgnoreCase("male")){
				gender="M";
			}else if(gender.equalsIgnoreCase("female")){
				gender="F";
			}
			
			
			HashMap inputMap = new HashMap<>();
//			inputMap.put("CustomerType",customerType );
			inputMap.put("DOCUMENTTYPE",documentType);
			inputMap.put("CustomerName",customerName);
			inputMap.put("DOB",dob);
			inputMap.put("Gender",gender);
			inputMap.put("CanBeParent",canBeParent );
			inputMap.put("ContactTelephoneSTD",contactTelephoneSTD );
			inputMap.put("MobileNo",mobileNo );
			inputMap.put("Emailid",emailid );
			inputMap.put("PresentAddressLine1",presentAddressLine1 );
			inputMap.put("PresentAddressLine2",presentAddressLine2 );
			inputMap.put("PermanentAddressLine1",permanentAddressLine1 );
			inputMap.put("PermanentAddressLine2",permanentAddressLine2 );
			inputMap.put("CustGSTNo",custGSTNo );
			inputMap.put("InstrumentNo",instrumentNo );
			inputMap.put("InstrumentDate",instrumentDate );
			inputMap.put("BankID",bankID );
			inputMap.put("NomineeName",nomineeName );
			inputMap.put("NomineeRelation",nomineeRelation );
			inputMap.put("IMDBranchName",iMDBranchName );
			inputMap.put("IMDBranchCode",iMDBranchCode );
			inputMap.put("SPName",sPName );
			inputMap.put("SPCode",sPCode );
			inputMap.put("ProposalDate" ,proposalDate);
			inputMap.put("PolicyNumberChar" ,policyNumberChar);
			inputMap.put("VehicleLaidUpFrom" ,vehicleLaidUpFrom);
			inputMap.put("VehicleLaidUpTo" ,vehicleLaidUpTo);
//			inputMap.put("Fromdate" ,fromdate);
			
			inputMap.put("Todate" ,todate);
			inputMap.put("Fromhour" ,fromhour);
			inputMap.put("Tohour" ,tohour);
			inputMap.put("FinancierCode",financierCode);
			inputMap.put("AgreementType",agreementType);
			inputMap.put("BranchName",branchName);
			inputMap.put("FinancierName",financierName);
			inputMap.put("SrNo",srNo);
			inputMap.put("ProductCode",productCode);
			inputMap.put("ClaimSettled",claimSettled);
			inputMap.put("ClaimPremium",claimPremium);
			inputMap.put("ClaimAmount",claimAmount);
			inputMap.put("DateofLoss",dateofLoss);
			inputMap.put("NatureofLoss",natureofLoss);
			inputMap.put("ClaimNo",claimNo);
			inputMap.put("PolicyEffectiveTo",policyEffectiveTo);
			inputMap.put("PolicyEffectiveFrom",policyEffectiveFrom);
			inputMap.put("DateOfInspection",dateOfInspection);
			inputMap.put("PolicyPremium",policyPremium);
			inputMap.put("PolicyNo",policyNo);
			inputMap.put("PolicyYear",policyYear);
			inputMap.put("OfficeCode",officeCode);
			inputMap.put("PolicyStatus",policyStatus);
			inputMap.put("CorporateCustomerId",corporateCustomerId);
			inputMap.put("InsurerName",insurerName);
			inputMap.put("InsurerAddress",insurerAddress);
			inputMap.put("PreviousPolicyType",previousPolicyType );
			inputMap.put("OfficeAddress",officeAddress );
			inputMap.put("NetPremium",netPremium );
			inputMap.put("ServiceTax",serviceTax );
			inputMap.put("StampDuty2",stampDuty2 );
			inputMap.put("CGST",cGST );
			inputMap.put("SGST",sGST );
			inputMap.put("UGST",uGST );
			inputMap.put("IGST",iGST );
			inputMap.put("TotalPremium",totalPremium);
			inputMap.put("LoadingAmount",loadingAmount );
			inputMap.put("LoadingRate",loadingRate );
			inputMap.put("SumInsured",sumInsured );
			inputMap.put("Rate",rate );
			inputMap.put("Premium",premium );
			inputMap.put("Applicable",applicable );
			inputMap.put("Description",description );
			inputMap.put("DiscountAmount",discountAmount );
			inputMap.put("DiscountRate",discountRate );
			inputMap.put("DiscountSumInsured",discountSumInsured );
			inputMap.put("DiscountRate1",discountRate1 );
			inputMap.put("DiscountPremium",discountPremium );
			inputMap.put("DiscountApplicable",discountApplicable );
			inputMap.put("DiscountDescription",discountDescription );
			
			inputMap.put("RTOLocationCode",rTOLocationCode );
			inputMap.put("NoOfClaimsOnPreviousPolicy",noOfClaimsOnPreviousPolicy );
			inputMap.put("RegistrationNumber",registrationNumber );
			inputMap.put("BodyTypeCode",bodyTypeCode );
			inputMap.put("ModelStatus",modelStatus );
			inputMap.put("VehicleExShowroomPrice",vehicleExShowroomPrice );
			inputMap.put("DateOfDeliveryOrRegistration",dateOfDeliveryOrRegistration );
			inputMap.put("YearOfManufacture",yearOfManufacture );
			inputMap.put("DateOfFirstRegistration",dateOfFirstRegistration );
			inputMap.put("RegistrationNumberSection1",registrationNumberSection1 );
			inputMap.put("RegistrationNumberSection2",registrationNumberSection2 );
			inputMap.put("RegistrationNumberSection3",registrationNumberSection3 );
			inputMap.put("RegistrationNumberSection4",registrationNumberSection4);
			inputMap.put("EngineNumber",engineNumber);
			inputMap.put("ChassisNumber",chassisNumber);
			inputMap.put("ExtensionCountryName",extensionCountryName);
			inputMap.put("AutomobileAssocnFlag",automobileAssocnFlag);
			inputMap.put("AutomobileAssociationNumber",automobileAssociationNumber);
			inputMap.put("VoluntaryExcess",voluntaryExcess);
			inputMap.put("TPPDLimit",tPPDLimit);
			inputMap.put("AntiTheftDiscFlag",antiTheftDiscFlag);
			inputMap.put("HandicapDiscFlag",handicapDiscFlag);
			inputMap.put("NumberOfDrivers",numberOfDrivers);
			inputMap.put("NumberOfEmployees",numberOfEmployees);
			inputMap.put("TransferOfNCB",transferOfNCB);
			inputMap.put("TransferOfNCBPercent",transferOfNCBPercent);
			inputMap.put("NCBDeclaration",nCBDeclaration);
			inputMap.put("PreviousVehicleSaleDate",previousVehicleSaleDate);
			inputMap.put("BonusOnPreviousPolicy",bonusOnPreviousPolicy);
			inputMap.put("VehicleClass",vehicleClass );
			inputMap.put("VehicleMake",vehicleMake );
			inputMap.put("BodyTypeDescription",bodyTypeDescription );
//			inputMap.put("NumberOfWheels",numberOfWheels );
//			inputMap.put("CubicCapacity",cubicCapacity );
//			inputMap.put("SeatingCapacity",seatingCapacity );
//			inputMap.put("RegistrationZone",registrationZone );
			inputMap.put("VehiclesDrivenBy",vehiclesDrivenBy );
			inputMap.put("DriversAge",driversAge );
			inputMap.put("DriversExperience",driversExperience );
			inputMap.put("DriversQualification",driversQualification );
			inputMap.put("VehicleModelCluster",vehicleModelCluster );
			inputMap.put("OpenCoverNoteFlag",openCoverNoteFlag );
			inputMap.put("LegalLiability",legalLiability );
			inputMap.put("PaidDriver",paidDriver );
			inputMap.put("NCBConfirmation",nCBConfirmation );
			inputMap.put("RegistrationDate",registrationDate );
			inputMap.put("TPLoadingRate",tPLoadingRate );
			inputMap.put("ExtensionCountry",extensionCountry );
			inputMap.put("VehicleAge",vehicleAge );
			inputMap.put("LocationCode",locationCode );
			inputMap.put("RegistrationZoneDescription",registrationZoneDescription );
			inputMap.put("NumberOfWorkmen",numberOfWorkmen );
			inputMap.put("VehicCd",vehicCd );
			inputMap.put("SalesTax",salesTax );
			inputMap.put("ModelOfVehicle",modelOfVehicle );
			inputMap.put("PopulateDetails",populateDetails );
			inputMap.put("VehicleIDV", vehicleIDV );
			inputMap.put("ShowroomPriceDeviation", showroomPriceDeviation );
			inputMap.put("NewVehicle", newVehicle );
			inputMap.put("PaymentId",paymentId );
			inputMap.put("MICRCheque",mICRCheque );
			inputMap.put("InstrumentDatePayment",instrumentDatePayment );
			inputMap.put("DraweeBankName",draweeBankName );
			inputMap.put("HOUSEBANKNAME",hOUSEBANKNAME );
			inputMap.put("AmountPaid",amountPaid );
			inputMap.put("PaymentType",paymentType );
			inputMap.put("PaymentMode",paymentMode );
			inputMap.put("payerType",payerType );
			inputMap.put("InstrumentNoPayment",instrumentNoPayment );
			inputMap.put("Status",status );
			inputMap.put("DepositSlipNo",depositSlipNo );
			inputMap.put("PresentPinCode",presentPinCode );
		
			System.out.println("inputMap.size()----->>"+inputMap.size());
			Gson gson = new Gson();
		        String json = gson.toJson(inputMap);
		        System.out.println("json = " + json);
		        String result=  json + "" + jsonNames ;
		        System.out.println("result with brackets = " + result);
		       
		        if(result.contains("}[{")){
			 	       result = result.replace("}[{", ",");
			 	      }
		        if(result.contains("}]")){
		 	       result = result.replace("}]", "}");
		 	      }
		    System.out.println("result removing brackets = " + result);
			String xmlData = xmlFile("["+result+"]");
//			System.out.println("xmlData is =============>>" + xmlData);
			Service1 service1 = new Service1();
			IService1 api= service1.getBasicHttpBindingIService1();
			
			String responseXml = api.commBRIDGEFusionMOTOR(xmlData);
			System.out.println("ResponseXml is need to convert =============>>" + responseXml);
		
			try {
				mapFinalResponseUni = readResponse(responseXml);		//to Convert Xml to Json format
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("mapFinalResponseUni=>>"+mapFinalResponseUni);
			

			
			bean.setUserId(universalIntgBean.getUserId());
			bean.setUserDesc(universalIntgBean.getUserDesc());
			bean.setBranchId(universalIntgBean.getBranchId());
			bean.setGroupId(universalIntgBean.getMotorGroupResponseGroupId());
			bean.setSessionId(universalIntgBean.getMotorGroupResponseSessionId());
			bean.setGicId(universalIntgBean.getMotorGroupResponseGicId());
			bean.setResponseType("1");
			bean.setIpAddress(universalIntgBean.getIPAddress());
			
			

		if (request_for.equalsIgnoreCase("proposal")) {
			String procedureName = "PR_PROPOSAL";
			saveDataProposal(mapFinalResponseUni,procedureName );
		}
		else if (request_for.equalsIgnoreCase("premium")) {
			String procedureName = "PR_PREMIUM";
			saveDataProposal(mapFinalResponseUni,procedureName );
		}
		HashMap jsonMap= jsonToMap(jsonNames);
		mapFinalResponseUni.put("JsonResponse", "success");
		mapFinalResponseUni.putAll(jsonMap);
		//-----------------------------------
		}else{
			mapFinalResponseUni.clear();
			mapFinalResponseUni.put("JsonResponse", "error");
		}
			mapFinalResponseUni.put("GICID",universalIntgBean.getMotorGroupResponseGicId());
			ObjectMapper mapperObj = new ObjectMapper();
			String jsonFinalResp = mapperObj.writeValueAsString(mapFinalResponseUni);
	        System.out.println("jsonFinalResp==>>"+jsonFinalResp);
			
		String finalRespo= "["+	jsonFinalResp +"]";
		System.out.println("finalRespoWith Brackets=====>>"+finalRespo);
		return finalRespo;
			
		}
		public HashMap jsonToMap(String jsonNames) throws JSONException {

	        HashMap<String, String> map = new HashMap<String, String>();
	        JSONArray array = new JSONArray();
	    	try {
				// System.out.println("jsonNames"+jsonNames);
				JSONArray jsonarray = new JSONArray(jsonNames);
//				 System.out.println("jsonarray"+jsonarray);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject jsonResult = jsonarray.getJSONObject(i);
			       
			        Iterator<?> keys = jsonResult.keys();

	        while( keys.hasNext() ){
	            String key = (String)keys.next();
	            String value = jsonResult.getString(key); 
	            map.put(key, value);
	          //  System.out.println("json : "+jsonResult);
	        }
				}
		}catch (Exception e) {
					// TODO: handle exception
		}
	    
	        System.out.println("map : "+map);
	        return map;
	    }
		
		private void saveDataProposal(HashMap mapFinalResponseUniSave, String procedureName) {
			ArrayList coverlist =  new ArrayList<>();
			System.out.println("cover List");
			Iterator<Map.Entry<String, String>> coverListItr = mapFinalResponseUniSave.entrySet().iterator();
			while (coverListItr.hasNext()) {
				Map.Entry entry = (Map.Entry) coverListItr.next();
				String mapKey = (String)entry.getKey();
					if (mapKey.equalsIgnoreCase("coverlist")) {
						coverlist = (ArrayList) ((null == entry.getValue()) ? "" : entry.getValue());
						System.out.println("coverList if>>>"+ coverlist);
					}
				}
			System.out.println("coverlist.size---------------->>"+coverlist.size());
			
			HashMap coverMap= new HashMap<>();
			for(int index=1;index<=coverlist.size();index++)
			{
				HashMap cover_Map = new HashMap<>();
				String 	sumInsured="",applicable="", premium="",rate="",coverGroups="",
						addonCoverGroups="",description="",discountRate="",discountAmount="";
				Iterator coverListItr2 = mapFinalResponseUniSave.entrySet().iterator();
				 while (coverListItr2.hasNext()) {
					 Map.Entry entryCover = (Map.Entry) coverListItr2.next();
					 String mapKey = (String)entryCover.getKey();
					 if (mapKey.equalsIgnoreCase("cover"+index)) {
						 cover_Map = (HashMap) ((null == entryCover.getValue()) ? "" : entryCover.getValue());
//						 System.out.println("cover mapping:::" +cover_Map);
						
						Iterator cover1_1 = cover_Map.entrySet().iterator();
						
						while (cover1_1.hasNext()) {
						Map.Entry<String , String> coversMap = (Map.Entry<String , String>) cover1_1.next();
						String mapKeycover = (String)coversMap.getKey();
							
							if (mapKeycover.equalsIgnoreCase("SumInsured")) {
								sumInsured = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
							}
							if (mapKeycover.equalsIgnoreCase("Applicable")) {
								applicable = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
							}
							if (mapKeycover.equalsIgnoreCase("Premium")) {
								premium = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
							}
							if (mapKeycover.equalsIgnoreCase("Rate")) {
								rate = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
							}
							
							if(index<=17){
								if (mapKeycover.equalsIgnoreCase("CoverGroups")) {
									coverGroups = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
								}
							}			
							
							if(index>17 && index<=26){
								if (mapKeycover.equalsIgnoreCase("AddonCoverGroups")) {
									addonCoverGroups = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
								}
							}
							
							if(index>26){
								if (mapKeycover.equalsIgnoreCase("Description")) {
									description = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
								}
								if (mapKeycover.equalsIgnoreCase("DiscountRate")) {
									discountRate = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
								}
								if (mapKeycover.equalsIgnoreCase("DiscountAmount")) {
									discountAmount = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
								}
							}
							
						}
					 }
				}
				 	
				 		if(index<=17){
				 			
				 			String covName=coverGroups.replace(" ", "_");
				 			coverMap.put("cover_SumInsured_"+covName, sumInsured);
					 		coverMap.put("cover_Applicable_"+covName, applicable);
					 		coverMap.put("cover_Premium_"+covName, premium);
					 		coverMap.put("cover_Rate_"+covName, rate);
				 			
				 			coverMap.put("cover_CoverGroups", coverGroups);
				 		}
				 		if(index>17 && index<=26){
				 			
				 			String covName=addonCoverGroups.replace(" ", "_");
				 			coverMap.put("cover_SumInsured_"+covName, sumInsured);
					 		coverMap.put("cover_Applicable_"+covName, applicable);
					 		coverMap.put("cover_Premium_"+covName, premium);
					 		coverMap.put("cover_Rate_"+covName, rate);
				 			
				 			coverMap.put("cover_AddonCoverGroups", addonCoverGroups);
				 		}
				 		if(index>26){
				 			
				 			String covName=description.replace(" ", "_");
				 			coverMap.put("cover_SumInsured_"+covName, sumInsured);
					 		coverMap.put("cover_Applicable_"+covName, applicable);
					 		coverMap.put("cover_Premium_"+covName, premium);
					 		coverMap.put("cover_Rate_"+covName, rate);
				 			
				 			coverMap.put("cover_Description", description);
				 			coverMap.put("cover_DiscountRate", discountRate);
				 			coverMap.put("cover_DiscountAmount", discountAmount);
				 		}
				 	
				 		mapFinalResponseUniSave.putAll(coverMap);
				 	
				 		
			}
			System.out.println("CoverMap" + coverMap);
	 		
			


	 		
	 			Iterator coverListItr1 = mapFinalResponseUniSave.entrySet().iterator();

	 			while (coverListItr1.hasNext()) {
	 				Map.Entry entry = (Map.Entry) coverListItr1.next();
	 				String mapKey = (String) entry.getKey();

	 				for (int index1 = 1; index1 <= coverlist.size() + 1; index1++) {
	 					if (mapKey.equalsIgnoreCase("cover" + index1)) {
	 						coverListItr1.remove();
	 					}

	 				}
	 				if (mapKey.equalsIgnoreCase("coverlist")) {
	 					coverListItr1.remove();
	 				}
	 			}
	 			System.out.println("mapFinalResponseUniSave" + mapFinalResponseUniSave);
//				MotorResponseBean objResult = integrationSaveResponseDao
//				.saveRelianceProposalDataDump(mapFinalResponseUniSave, bean);
//
//				MotorResponseBean objResult1 = integrationSaveResponseDao
//				.saveRelianceProposalDataProcess(bean);
	 			
//	 			String motorGroupResponseGroupId=request.getParameter("motorGroupResponseGroupId");
//				String motorGroupResponseSessionId=request.getParameter("motorGroupResponseSessionId");
//				String motorGroupResponseGicId=request.getParameter("motorGroupResponseGicId");
//				String userId=request.getParameter("userId");
//				String userDesc=request.getParameter("userDesc");
//				String branchId=request.getParameter("branchId");
//				 IPAddress=request.getParameter("IpAddress");
	 			
	 			System.out.println("imGrpId----Integer------------>>"+universalIntgBean.getMotorGroupResponseGroupId());
	 			System.out.println("gicId------Integer---------->>"+universalIntgBean.getMotorGroupResponseGicId());
	 			System.out.println("sessionId---------------->>"+universalIntgBean.getMotorGroupResponseSessionId());
	 			System.out.println("responseType--Integer-------------->>"+1);
	 			System.out.println("tcpIpAdd---------------->>"+universalIntgBean.getIPAddress());
	 			System.out.println("userId---------------->>"+universalIntgBean.getUserId());
	 			System.out.println("branchId---------------->>"+universalIntgBean.getBranchId());
	 			System.out.println("userDesc---------------->>"+universalIntgBean.getUserDesc());
	 			
	 			
	 			Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(universalIntgBean.getMotorGroupResponseGroupId()), Integer.parseInt(universalIntgBean.getMotorGroupResponseGicId()), universalIntgBean.getMotorGroupResponseSessionId(),
	 					1, mapFinalResponseUniSave, universalIntgBean.getIPAddress(), universalIntgBean.getUserId(), universalIntgBean.getBranchId(),
	 					universalIntgBean.getUserDesc(), procedureName);
		}
		
		//*******************************************************************************
		private HashMap<String, String> readResponse(String str) {
			data = new HashMap();
			HashMap tax;
	        ArrayList coverlist= new ArrayList<>();
			try {
				data.clear();
				SAXBuilder saxBuilder = new SAXBuilder();
				Document document = saxBuilder.build(new ByteArrayInputStream(str.getBytes()));
			//-------------------------------------------------------------------	
				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				String xt = xmlOutput.outputString(document);

				xmlOutput.output(document, new FileWriter(path+"\\UniversalResponse.xml"));
			//	-----------------------------------------------------------
				org.jdom2.Element root = document.getRootElement();
				java.util.List<Element> list = root.getChildren();
				System.out.println("LIST=" + list);
				Iterator itr = list.iterator();
				data.put("Company", "Universal Sompo General Insurance CO Ltd");
				int i=0,x=1,temp=0;
				while (itr.hasNext()) {
				org.jdom2.Element s = (org.jdom2.Element) itr.next();
				java.util.List<Element> lst = s.getChildren();

				Iterator iterator = lst.iterator();
				HashMap cover = new HashMap();
				cover.clear();
				if (!lst.isEmpty()) {
					int j = 0;
					while (iterator.hasNext()) {
						org.jdom2.Element rncv = (org.jdom2.Element) iterator
								.next();
//						System.out.println("rncv--" + rncv);
						java.util.List<Element> ls = rncv.getChildren();
						Iterator itra = ls.iterator();
						if (!(ls.isEmpty())) {
//							System.out.println("ls-->" + ls);
							tax = new HashMap();
							while (itra.hasNext()) {
								org.jdom2.Element tx1 = (org.jdom2.Element) itra
										.next();
								
//								System.out.println("rncv--" + tx1);
								java.util.List<Element> ls1 = tx1.getChildren();
								Iterator itra1 = ls1.iterator();
								
								if (!(ls1.isEmpty())) {
									while (itra1.hasNext()) {
										org.jdom2.Element tx2 = (org.jdom2.Element) itra1.next();
										
										//*******************************************
//										System.out.println("rncv--" + tx2);
										java.util.List<Element> ls2 = tx2.getChildren();
										Iterator itra2 = ls2.iterator();
										
										if (!(ls2.isEmpty())) {
											while (itra2.hasNext()) {
												org.jdom2.Element tx3 = (org.jdom2.Element) itra2.next();
												
												
												//*******************************************
//												System.out.println("rncv--" + tx3);
												java.util.List<Element> ls3 = tx3.getChildren();
												Iterator itra3 = ls3.iterator();
												
												if (!(ls3.isEmpty())) {
													while (itra3.hasNext()) {
														org.jdom2.Element tx4 = (org.jdom2.Element) itra3.next();
														
														//*******************************************
//														System.out.println("rncv--" + tx4);
														java.util.List<Element> ls4 = tx4.getChildren();
														Iterator itra4 = ls4.iterator();
														
														if (!(ls4.isEmpty())) {
															while (itra4.hasNext()) {
																org.jdom2.Element tx5 = (org.jdom2.Element) itra4.next();
																
																
																//*******************************************
//																System.out.println("rncv--" + tx5);
																java.util.List<Element> ls5 = tx5.getChildren();
																Iterator itra5 = ls5.iterator();
																
																if (!(ls5.isEmpty())) {
																	
																	String node1Type=tx4.getAttributeValue("Type");
																	String node1Name=tx4.getAttributeValue("Name");
																	cover = new HashMap<>();
																	while (itra5.hasNext()) {
																		org.jdom2.Element tx6 = (org.jdom2.Element) itra5.next();
																		
																		String name=tx6.getAttributeValue("Name");
//																		System.out.println("node1Type==>>"+node1Type);
//																		System.out.println("node1Name==>>"+node1Name);
//																		System.out.println("name2Name==>>"+name);
																		if((node1Type.equals("Group")&&node1Name.equalsIgnoreCase("Covers"))&&(name.equalsIgnoreCase("Premium")||name.equalsIgnoreCase("Rate")||name.equalsIgnoreCase("SumInsured")
																				||name.equalsIgnoreCase("Applicable")||name.equalsIgnoreCase("CoverGroups")))
																		{
																			x=0;
																			cover.put(tx6.getName(),tx6.getAttributeValue("Value"));
																		}else if((node1Type.equals("Group")&&node1Name.equalsIgnoreCase("AddonCovers"))&&(name.equalsIgnoreCase("Premium")||name.equalsIgnoreCase("Rate")||name.equalsIgnoreCase("SumInsured")
																				||name.equalsIgnoreCase("Applicable")||name.equalsIgnoreCase("AddonCoverGroups")))
																		{
																			x=0;
																			cover.put(tx6.getName(),tx6.getAttributeValue("Value"));
																		}else if((node1Type.equals("Group")&&node1Name.equalsIgnoreCase("Other Discount Group"))&&(name.equalsIgnoreCase("Premium")||name.equalsIgnoreCase("Rate")||name.equalsIgnoreCase("SumInsured")
																				||name.equalsIgnoreCase("Applicable")||name.equalsIgnoreCase("Description")||name.equalsIgnoreCase("Discount Amount")||name.equalsIgnoreCase("Discount Rate")))
																		{
																			x=0;
																			cover.put(tx6.getName(),tx6.getAttributeValue("Value"));
																		}
																		else{
																			x=1;
																			data.put(tx6.getName(),tx6.getAttributeValue("Value"));
																		}
//																		System.out.println("tx6666666666666-------------->>"+tx6.getAttributeValue("Value"));
//																		System.out.println("tx6666666666666-------------->>"+tx6.getName());
																	}
																	
																	if(x==0){
																	i++;
//																	System.out.println("cover"+i+" ***-->>"+cover);
																	data.put("cover"+i, cover);
																	coverlist.add("cover"+i);
																	}
																	
																	
																}
																//**********************************************
																
																data.put(tx5.getName(),tx5.getAttributeValue("Value"));
//																System.out.println("tx5555555555555-------------->>"+tx5.getAttributeValue("Value"));
//																System.out.println("tx5555555555555-------------->>"+tx5.getName());
															}
															
														}
														//**********************************************
														
														data.put(tx4.getName(),tx4.getAttributeValue("Value"));
//														System.out.println("tx4444444444444-------------->>"+tx4.getAttributeValue("Value"));
//														System.out.println("tx4444444444444-------------->>"+tx4.getName());
													}
													
												}
												//**********************************************
												
												data.put(tx3.getName(),tx3.getAttributeValue("Value"));
//												System.out.println("tx3333333333333-------------->>"+tx3.getAttributeValue("Value"));
//												System.out.println("tx3333333333333-------------->>"+tx3.getName());
											}
											
										}
										//**********************************************
										
										data.put(tx2.getName(),tx2.getAttributeValue("Value"));
//										System.out.println("tx222222222222-------------->>"+tx2.getAttributeValue("Value"));
//										System.out.println("tx222222222222-------------->>"+tx2.getName());
									}
									
								}
								data.put(tx1.getName(),tx1.getAttributeValue("Value"));
//								System.out.println("tx111111111111-------------->>"+tx1.getAttributeValue("Value"));
//								System.out.println("tx111111111111-------------->>"+tx1.getName());

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
//					System.out.println("s.getAttributeValue elseeeeee ---------------->>"+s.getAttributeValue("Value"));
//					System.out.println("s.getValue() elseeeeee ---------------->>"+s.getValue());
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
//				System.out.println("data--" + data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
		//********************************************************************************

		public String xmlFile(String jsonNames) {

			ArrayList<String> resultList = new ArrayList<String>();
			JSONArray array = new JSONArray();
			String retMsg = "";
			HashMap<String, String> hashmap = new HashMap<>();
			String xx = "";
			Document document2;
			org.w3c.dom.Document document = null;
			HashMap inputParaList = new HashMap<>();
//			System.out.println(":>>>>>>" + jsonNames);
			try {
				// System.out.println("jsonNames"+jsonNames);
				JSONArray jsonarray = new JSONArray(jsonNames);
//				 System.out.println("jsonarray"+jsonarray);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject jsonResult = jsonarray.getJSONObject(i);
					// System.out.println(jsonResult+"<<<<<<:");
					// String coverDetails=jsonResult.getString("coverDetails");

//				org.jdom2.Element strXdoc = new org.jdom2.Element("strXdoc");
//				strXdoc.setAttribute("xmlns",);
//				strXdoc.addNamespaceDeclaration(new Names)
//				strXdoc.addNamespaceDeclaration(new Namespace("http://tempuri.org/"));
//				strXdoc.removeNamespaceDeclaration("http://tempuri.org/");
				
//				document2 = new Document(strXdoc);
					org.jdom2.Element rootelement = new org.jdom2.Element("Root");
//					rootelement.removeNamespaceDeclaration(null);
					document2 = new Document(rootelement);

					org.jdom2.Element Authentication = new org.jdom2.Element("Authentication");
					
					org.jdom2.Element WACode = new org.jdom2.Element("WACode");
//					WACode.setText("20000025");
					WACode.setText(jsonResult.getString("WACODE").trim());
					org.jdom2.Element WAAppCode = new org.jdom2.Element("WAAppCode");
//					WAAppCode.setText("30000027");
					WAAppCode.setText(jsonResult.getString("WAAPPCODE").trim());
					org.jdom2.Element WAUserID = new org.jdom2.Element("WAUserID");
					WAUserID.setText(jsonResult.getString("WAUSERID").trim());
//					WAUserID.setText("USER01");
					org.jdom2.Element WAUserPwd = new org.jdom2.Element("WAUserPwd");
					WAUserPwd.setText(jsonResult.getString("WAUSERPWD").trim());
//					WAUserPwd.setText("pass@123");
					org.jdom2.Element WAType = new org.jdom2.Element("WAType");
					WAType.setText(jsonResult.getString("WATYPE").trim());
//					WAType.setText("0");
					org.jdom2.Element DocumentType = new org.jdom2.Element("DocumentType");
					DocumentType.setText(jsonResult.getString("DOCUMENTTYPE").trim());
//					DocumentType.setText("Quotation");
					org.jdom2.Element Versionid = new org.jdom2.Element("Versionid");
					Versionid.setText(jsonResult.getString("VERSIONID").trim());
//					Versionid.setText("1.1");
					org.jdom2.Element GUID = new org.jdom2.Element("GUID");
					GUID.setText("");
		             
					Authentication.addContent(WACode);
					Authentication.addContent(WAAppCode);
					Authentication.addContent(WAUserID);
					Authentication.addContent(WAUserPwd);
					Authentication.addContent(WAType);
					Authentication.addContent(DocumentType);
					Authentication.addContent(Versionid);
					Authentication.addContent(GUID);
		              
					
					
					org.jdom2.Element Customer = new org.jdom2.Element("Customer");
					org.jdom2.Element CustomerType = new org.jdom2.Element("CustomerType");
					CustomerType.setText(jsonResult.getString("CUSTOMERTYPE").trim());
//					CustomerType.setText("Individual");
					org.jdom2.Element CustomerName = new org.jdom2.Element("CustomerName");
					CustomerName.setText(jsonResult.getString("CustomerName").trim());
//					CustomerName.setText("MADAN LAL");
					org.jdom2.Element DOB = new org.jdom2.Element("DOB");
					DOB.setText(jsonResult.getString("DOB").trim());
//					DOB.setText("09/10/1990");
					org.jdom2.Element Gender = new org.jdom2.Element("Gender");
					Gender.setText(jsonResult.getString("Gender").trim());
//					Gender.setText("M");
					org.jdom2.Element CanBeParent = new org.jdom2.Element("CanBeParent");
					CanBeParent.setText(jsonResult.getString("CanBeParent").trim());
//					CanBeParent.setText("0");
					org.jdom2.Element ContactTelephoneSTD = new org.jdom2.Element("ContactTelephoneSTD");
					ContactTelephoneSTD.setText(jsonResult.getString("ContactTelephoneSTD").trim());
//					ContactTelephoneSTD.setText("-");
					org.jdom2.Element MobileNo = new org.jdom2.Element("MobileNo");
					MobileNo.setText(jsonResult.getString("MobileNo").trim());
//					MobileNo.setText("9958840536");
					org.jdom2.Element Emailid = new org.jdom2.Element("Emailid");
					Emailid.setText(jsonResult.getString("Emailid").trim());
//					Emailid.setText("paveeraheja@gmail.com");
					org.jdom2.Element PresentAddressLine1 = new org.jdom2.Element("PresentAddressLine1");
					PresentAddressLine1.setText(jsonResult.getString("PresentAddressLine1").trim());
//					PresentAddressLine1.setText("H NO 99AMOHALLA MUKHARJEE");
					org.jdom2.Element PresentAddressLine2 = new org.jdom2.Element("PresentAddressLine2");
					PresentAddressLine2.setText(jsonResult.getString("PresentAddressLine2").trim());
//					PresentAddressLine2.setText("NAGARW NO 10Fatehabad NA");
					org.jdom2.Element PresentStateCode = new org.jdom2.Element("PresentStateCode");
					PresentStateCode.setText(jsonResult.getString("PRESENTSTATECODE").trim());
//					PresentStateCode.setText("127");
					org.jdom2.Element PresentCityDistCode = new org.jdom2.Element("PresentCityDistCode");
					PresentCityDistCode.setText(jsonResult.getString("PRESENTCITYDISTCODE").trim());
//					PresentCityDistCode.setText("6386");
					org.jdom2.Element PresentPinCode = new org.jdom2.Element("PresentPinCode");
					PresentPinCode.setText(jsonResult.getString("PRESENTPINCODE").trim());
//					PresentPinCode.setText("125050");
					org.jdom2.Element PermanentAddressLine1 = new org.jdom2.Element("PermanentAddressLine1");
					PermanentAddressLine1.setText(jsonResult.getString("PermanentAddressLine1").trim());
//					PermanentAddressLine1.setText("H NO 99AMOHALLA MUKHARJEE");
					org.jdom2.Element PermanentAddressLine2 = new org.jdom2.Element("PermanentAddressLine2");
					PermanentAddressLine2.setText(jsonResult.getString("PermanentAddressLine2").trim());
//					PermanentAddressLine2.setText("NAGARW NO 10Fatehabad NA");
					org.jdom2.Element PermanentStateCode = new org.jdom2.Element("PermanentStateCode");
					PermanentStateCode.setText(jsonResult.getString("PERMANENTSTATECODE").trim());
//					PermanentStateCode.setText("127");
					org.jdom2.Element PermanentCityDistCode = new org.jdom2.Element("PermanentCityDistCode");
					PermanentCityDistCode.setText(jsonResult.getString("PERMANENTCITYDISTCODE").trim());
//					PermanentCityDistCode.setText("6386");
					org.jdom2.Element PermanentPinCode = new org.jdom2.Element("PermanentPinCode");
					PermanentPinCode.setText(jsonResult.getString("PERMANENTPINCODE").trim());
//					PermanentPinCode.setText("125050");
					org.jdom2.Element CustGSTNo = new org.jdom2.Element("CustGSTNo");
					CustGSTNo.setText(jsonResult.getString("CustGSTNo").trim());
//					CustGSTNo.setText("00000000000");
					org.jdom2.Element ProductName = new org.jdom2.Element("ProductName");
					ProductName.setText(jsonResult.getString("PRODUCTNAME").trim());
//					ProductName.setText(("Two Wheeler Package Policy").trim());
					org.jdom2.Element ProductCode = new org.jdom2.Element("ProductCode");
					ProductCode.setText(jsonResult.getString("PRODUCTCODE").trim());
//					ProductCode.setText("2311");
					org.jdom2.Element InstrumentNo = new org.jdom2.Element("InstrumentNo");
					InstrumentNo.setText(jsonResult.getString("InstrumentNo").trim());
//					InstrumentNo.setText("NULL");
					org.jdom2.Element InstrumentDate = new org.jdom2.Element("InstrumentDate");
					InstrumentDate.setText(jsonResult.getString("InstrumentDate").trim());
//					InstrumentDate.setText("NULL");
					org.jdom2.Element BankID = new org.jdom2.Element("BankID");
					BankID.setText(jsonResult.getString("BankID").trim());
//					BankID.setText("NULL");
					org.jdom2.Element PosPolicyNo = new org.jdom2.Element("PosPolicyNo");
					PosPolicyNo.setText(jsonResult.getString("POSPOLICYNO").trim());
//					PosPolicyNo.setText("50033118");
					org.jdom2.Element WAURN = new org.jdom2.Element("WAURN");
					WAURN.setText(jsonResult.getString("WAURN").trim());
//					WAURN.setText("6c60e675-3d9b-4497-ac22-7db6c643a72a");
					org.jdom2.Element NomineeName = new org.jdom2.Element("NomineeName");
					NomineeName.setText(jsonResult.getString("NomineeName").trim());
//					NomineeName.setText("Tulsi Das");
					org.jdom2.Element NomineeRelation = new org.jdom2.Element("NomineeRelation");
					NomineeRelation.setText(jsonResult.getString("NomineeRelation").trim());
//					NomineeRelation.setText("Brother");
		              
					Customer.addContent(CustomerType);
					Customer.addContent(CustomerName);
					Customer.addContent(DOB);
					Customer.addContent(Gender);
					Customer.addContent(CanBeParent);
					Customer.addContent(ContactTelephoneSTD);
					Customer.addContent(MobileNo);
					Customer.addContent(Emailid);
					Customer.addContent(PresentAddressLine1);
					Customer.addContent(PresentAddressLine2);
					Customer.addContent(PresentStateCode);
					Customer.addContent(PresentCityDistCode);
					Customer.addContent(PresentPinCode);
					Customer.addContent(PermanentAddressLine1);
					Customer.addContent(PermanentAddressLine2);
					Customer.addContent(PermanentStateCode);
					Customer.addContent(PermanentCityDistCode);
					Customer.addContent(PermanentPinCode);
					Customer.addContent(CustGSTNo);
					Customer.addContent(ProductName);
					Customer.addContent(ProductCode);
					Customer.addContent(InstrumentNo);
					Customer.addContent(InstrumentDate);
					Customer.addContent(BankID);
					Customer.addContent(PosPolicyNo);
					Customer.addContent(WAURN);
					Customer.addContent(NomineeName);
					Customer.addContent(NomineeRelation);
					
					
//					PRODUCTNAME
					String productName=jsonResult.getString("PRODUCTNAME").toString().trim();
//					System.out.println("productname======"+productName);
					
		              
					org.jdom2.Element Product = new org.jdom2.Element("Product");	
//					Product.setAttribute("Name", "Private Car Package Policy");  
					Product.setAttribute("Name", productName);   
					org.jdom2.Element GeneralProposal = new org.jdom2.Element("GeneralProposal");	
					GeneralProposal.setAttribute("Name", "General Proposal");     
					org.jdom2.Element GeneralProposalGroup = new org.jdom2.Element("GeneralProposalGroup");	
					GeneralProposalGroup.setAttribute("Name", "General Proposal Group");     
					org.jdom2.Element DistributionChannel= new org.jdom2.Element("DistributionChannel");	
					DistributionChannel.setAttribute("Name", "Distribution Channel");     
					org.jdom2.Element BranchDetails= new org.jdom2.Element("BranchDetails");	
					BranchDetails.setAttribute("Name", "Branch Details");     
					org.jdom2.Element IMDBranchName= new org.jdom2.Element("IMDBranchName");	
					IMDBranchName.setAttribute("Name", "IMDBranchName");     
					IMDBranchName.setAttribute("Value", jsonResult.getString("IMDBranchName").trim());     
					org.jdom2.Element IMDBranchCode= new org.jdom2.Element("IMDBranchCode");	
					IMDBranchCode.setAttribute("Name", "IMDBranch Code");     
					IMDBranchCode.setAttribute("Value", jsonResult.getString("IMDBranchCode").trim());     
					BranchDetails.addContent(IMDBranchName);
					BranchDetails.addContent(IMDBranchCode);
					
					org.jdom2.Element SPDetails= new org.jdom2.Element("SPDetails");	
					SPDetails.setAttribute("Name", "SP Details");     
					org.jdom2.Element SPName= new org.jdom2.Element("SPName");	
					SPName.setAttribute("Name", "SP Name");     
					SPName.setAttribute("Value", jsonResult.getString("SPName").trim());     
					org.jdom2.Element SPCode= new org.jdom2.Element("SPCode");	
					SPCode.setAttribute("Name", "SP Code");     
					SPCode.setAttribute("Value", jsonResult.getString("SPCode").trim());     
					
					SPDetails.addContent(SPName);
					SPDetails.addContent(SPCode);
					
					DistributionChannel.addContent(BranchDetails);
					DistributionChannel.addContent(SPDetails);
			

				
				
					org.jdom2.Element GeneralProposalInformation= new org.jdom2.Element("GeneralProposalInformation");	
					GeneralProposalInformation.setAttribute("Name", "General Proposal Information");     
					org.jdom2.Element TypeOfBusiness= new org.jdom2.Element("TypeOfBusiness");	
					TypeOfBusiness.setAttribute("Name", "Type Of Business");     
					TypeOfBusiness.setAttribute("Value", jsonResult.getString("TYPEOFBUSINESS").trim());  
					
					org.jdom2.Element Sector = new org.jdom2.Element("Sector");	
					Sector.setAttribute("Name", "Sector");
					Sector.setAttribute("Value",jsonResult.getString("SECTOR").trim());
					
					org.jdom2.Element ServiceTaxExemptionCategory= new org.jdom2.Element("ServiceTaxExemptionCategory");	
					ServiceTaxExemptionCategory.setAttribute("Name", "Service Tax Exemption Category");     
					ServiceTaxExemptionCategory.setAttribute("Value", jsonResult.getString("SERVICETAXEXEMPTIONCATEGORY").trim());     
					
					org.jdom2.Element BusinessType= new org.jdom2.Element("BusinessType");	
					BusinessType.setAttribute("Name", "Transaction Type");     
					BusinessType.setAttribute("Value", jsonResult.getString("BUSINESSTYPE").trim());     
					
					org.jdom2.Element ProposalDate= new org.jdom2.Element("ProposalDate");	
					ProposalDate.setAttribute("Name", "Proposal Date");     
					ProposalDate.setAttribute("Value", jsonResult.getString("ProposalDate").trim());     
					
					org.jdom2.Element DealId= new org.jdom2.Element("DealId");	
					DealId.setAttribute("Name", "Deal Id");     
//					DealId.setAttribute("Value",jsonResult.getString("DEALID").trim());
					DealId.setAttribute("Value","");
					
					org.jdom2.Element PolicyNumberChar= new org.jdom2.Element("PolicyNumberChar");	
					PolicyNumberChar.setAttribute("Name", "PolicyNumberChar");     
					PolicyNumberChar.setAttribute("Value",jsonResult.getString("PolicyNumberChar").trim());  
					
					org.jdom2.Element VehicleLaidUpFrom= new org.jdom2.Element("VehicleLaidUpFrom");	
					VehicleLaidUpFrom.setAttribute("Name", "VehicleLaidUpFrom");     
					VehicleLaidUpFrom.setAttribute("Value",jsonResult.getString("VehicleLaidUpFrom").trim());  
					
					org.jdom2.Element VehicleLaidUpTo= new org.jdom2.Element("VehicleLaidUpTo");	
					VehicleLaidUpTo.setAttribute("Name", "VehicleLaidUpTo");     
					VehicleLaidUpTo.setAttribute("Value", jsonResult.getString("VehicleLaidUpTo").trim());  
					
					org.jdom2.Element PolicyEffectiveDate= new org.jdom2.Element("PolicyEffectiveDate");	
					PolicyEffectiveDate.setAttribute("Name", "Policy Effective Date");     
//					PolicyEffectiveDate.setAttribute("Value", "");     
					org.jdom2.Element Fromdate= new org.jdom2.Element("Fromdate");	
					Fromdate.setAttribute("Name", "From Date");     
					Fromdate.setAttribute("Value",jsonResult.getString("POLICYSTARTDATE").trim()); 
					
					org.jdom2.Element Todate= new org.jdom2.Element("Todate");	
					Todate.setAttribute("Name", "To Date");     
					Todate.setAttribute("Value", jsonResult.getString("Todate").trim());     
					
					org.jdom2.Element Fromhour= new org.jdom2.Element("Fromhour");	
					Fromhour.setAttribute("Name", "From Hour");     
					Fromhour.setAttribute("Value", jsonResult.getString("Fromhour").trim());   
					
					org.jdom2.Element Tohour= new org.jdom2.Element("Tohour");	
					Tohour.setAttribute("Name", "To Hour");     
					Tohour.setAttribute("Value", jsonResult.getString("Tohour").trim());     
					
					PolicyEffectiveDate.addContent(Fromdate);
					PolicyEffectiveDate.addContent(Todate);
					PolicyEffectiveDate.addContent(Fromhour);
					PolicyEffectiveDate.addContent(Tohour);
					
					GeneralProposalInformation.addContent(TypeOfBusiness);
					GeneralProposalInformation.addContent(Sector);
					GeneralProposalInformation.addContent(ServiceTaxExemptionCategory);
					GeneralProposalInformation.addContent(BusinessType);
					GeneralProposalInformation.addContent(ProposalDate);
					GeneralProposalInformation.addContent(DealId);
					GeneralProposalInformation.addContent(PolicyNumberChar);
					GeneralProposalInformation.addContent(VehicleLaidUpFrom);
					GeneralProposalInformation.addContent(VehicleLaidUpTo);
					GeneralProposalInformation.addContent(PolicyEffectiveDate);
					
					GeneralProposalGroup.addContent(DistributionChannel);
					GeneralProposalGroup.addContent(GeneralProposalInformation);
					
					GeneralProposal.addContent(GeneralProposalGroup);
					
					org.jdom2.Element FinancierDetails= new org.jdom2.Element("FinancierDetails");	
					FinancierDetails.setAttribute("Name", "Financier Details");    
					org.jdom2.Element FinancierDtlGrp= new org.jdom2.Element("FinancierDtlGrp");
					FinancierDtlGrp.setAttribute("Type", "Group");    
					FinancierDtlGrp.setAttribute("Name", "Financier Dtl Group");    
					org.jdom2.Element FinancierDtlGrpData= new org.jdom2.Element("FinancierDtlGrpData");
					FinancierDtlGrpData.setAttribute("Type", "GroupData");    
					org.jdom2.Element FinancierCode= new org.jdom2.Element("FinancierCode");
					FinancierCode.setAttribute("Name", "Financier Code");    
					FinancierCode.setAttribute("Value", jsonResult.getString("FinancierCode").trim());    
					org.jdom2.Element AgreementType= new org.jdom2.Element("AgreementType");
					AgreementType.setAttribute("Name", "Agreement Type");    
					AgreementType.setAttribute("Value", jsonResult.getString("AgreementType").trim());    
					org.jdom2.Element BranchName= new org.jdom2.Element("BranchName");
					BranchName.setAttribute("Name", "Branch Name");    
//					BranchName.setAttribute("Value", jsonResult.getString("BranchName").trim());    
//					BranchName.setAttribute("Value", "");    
					
					org.jdom2.Element FinancierName= new org.jdom2.Element("FinancierName");
					FinancierName.setAttribute("Name", "Financier Name");    
					FinancierName.setAttribute("Value",jsonResult.getString("FinancierName").trim());    
					org.jdom2.Element SrNo= new org.jdom2.Element("SrNo");
					SrNo.setAttribute("Name", "Sr No");    
					SrNo.setAttribute("Value", jsonResult.getString("SrNo").trim());    
 
					
					FinancierDtlGrpData.addContent(FinancierCode);
					FinancierDtlGrpData.addContent(AgreementType);
					FinancierDtlGrpData.addContent(BranchName);
					FinancierDtlGrpData.addContent(FinancierName);
					FinancierDtlGrpData.addContent(SrNo);
					
					FinancierDtlGrp.addContent(FinancierDtlGrpData);
					FinancierDetails.addContent(FinancierDtlGrp);

					GeneralProposal.addContent(FinancierDetails);
					
					
					
					org.jdom2.Element PreviousPolicyDetails= new org.jdom2.Element("PreviousPolicyDetails");
					PreviousPolicyDetails.setAttribute("Name", "Previous Policy Details");    
					org.jdom2.Element PreviousPolDtlGroup= new org.jdom2.Element("PreviousPolDtlGroup");
					PreviousPolDtlGroup.setAttribute("Type", "Group");    
					PreviousPolDtlGroup.setAttribute("Name", "Previous Pol Dtl Group");    
					org.jdom2.Element PreviousPolDtlGroupData= new org.jdom2.Element("PreviousPolDtlGroupData");
					PreviousPolDtlGroupData.setAttribute("Type", "GroupData");    
					org.jdom2.Element ProductCodePrev= new org.jdom2.Element("ProductCode");
					ProductCodePrev.setAttribute("Name", "Product Code");    
					ProductCodePrev.setAttribute("Value", jsonResult.getString("ProductCode").trim());  
					
					org.jdom2.Element ClaimSettled= new org.jdom2.Element("ClaimSettled");
					ClaimSettled.setAttribute("Name", "Claim Settled");    
					ClaimSettled.setAttribute("Value", jsonResult.getString("ClaimSettled").trim());   
					
					org.jdom2.Element ClaimPremium= new org.jdom2.Element("ClaimPremium");
					ClaimPremium.setAttribute("Name", "Claim Premium");    
					ClaimPremium.setAttribute("Value", jsonResult.getString("ClaimPremium").trim());   
					
					org.jdom2.Element ClaimAmount= new org.jdom2.Element("ClaimAmount");
					ClaimAmount.setAttribute("Name", "Claim Amount");    
					ClaimAmount.setAttribute("Value", jsonResult.getString("ClaimAmount").trim());  
					
					org.jdom2.Element DateofLoss= new org.jdom2.Element("DateofLoss");
					DateofLoss.setAttribute("Name", "Date Of Loss");    
					DateofLoss.setAttribute("Value", jsonResult.getString("DateofLoss").trim());  
					
					org.jdom2.Element NatureofLoss= new org.jdom2.Element("NatureofLoss");
					NatureofLoss.setAttribute("Name", "Nature Of Loss");    
					NatureofLoss.setAttribute("Value",jsonResult.getString("NatureofLoss").trim()); 
					
					org.jdom2.Element ClaimNo= new org.jdom2.Element("ClaimNo");
					ClaimNo.setAttribute("Name", "Claim No");    
					ClaimNo.setAttribute("Value", jsonResult.getString("ClaimNo").trim()); 
					
					org.jdom2.Element PolicyEffectiveTo= new org.jdom2.Element("PolicyEffectiveTo");
					PolicyEffectiveTo.setAttribute("Name", "Policy Effective To");    
					PolicyEffectiveTo.setAttribute("Value", jsonResult.getString("PolicyEffectiveTo").trim());   
										
					org.jdom2.Element PolicyEffectiveFrom= new org.jdom2.Element("PolicyEffectiveFrom");
					PolicyEffectiveFrom.setAttribute("Name", "Policy Effective From");    
					PolicyEffectiveFrom.setAttribute("Value",jsonResult.getString("PolicyEffectiveFrom").trim());    
					
					org.jdom2.Element DateOfInspection= new org.jdom2.Element("DateOfInspection");
					DateOfInspection.setAttribute("Name", "DateOfInspection");    
					DateOfInspection.setAttribute("Value", jsonResult.getString("DateOfInspection").trim());   
					
					org.jdom2.Element PolicyPremium= new org.jdom2.Element("PolicyPremium");
					PolicyPremium.setAttribute("Name", "PolicyPremium");    
					PolicyPremium.setAttribute("Value", jsonResult.getString("PolicyPremium").trim());    
					
					org.jdom2.Element PolicyNo= new org.jdom2.Element("PolicyNo");
					PolicyNo.setAttribute("Name", "Policy No");    
					PolicyNo.setAttribute("Value", jsonResult.getString("PolicyNo").trim());    
					
					org.jdom2.Element PolicyYear= new org.jdom2.Element("PolicyYear");
					PolicyYear.setAttribute("Name", "Policy Year");    
					PolicyYear.setAttribute("Value", jsonResult.getString("PolicyYear").trim());    
					
					org.jdom2.Element OfficeCode= new org.jdom2.Element("OfficeCode");
					OfficeCode.setAttribute("Name", "Office Name");    
					OfficeCode.setAttribute("Value", jsonResult.getString("OfficeCode").trim());   
					
					org.jdom2.Element PolicyStatus= new org.jdom2.Element("PolicyStatus");
					PolicyStatus.setAttribute("Name", "Policy Status");    
					PolicyStatus.setAttribute("Value", jsonResult.getString("PolicyStatus").trim());    
					
					org.jdom2.Element CorporateCustomerId= new org.jdom2.Element("CorporateCustomerId");
					CorporateCustomerId.setAttribute("Name", "Corporate Customer Id");    
					CorporateCustomerId.setAttribute("Value", jsonResult.getString("CorporateCustomerId").trim());  
					
					org.jdom2.Element InsurerName= new org.jdom2.Element("InsurerName");
					InsurerName.setAttribute("Name", "InsurerName");    
					InsurerName.setAttribute("Value", jsonResult.getString("InsurerName").trim());    
					
					org.jdom2.Element InsurerAddress= new org.jdom2.Element("InsurerAddress");
					InsurerAddress.setAttribute("Name", "InsurerAddress");    
					InsurerAddress.setAttribute("Value", jsonResult.getString("InsurerAddress").trim());    
					
					
					PreviousPolDtlGroupData.addContent(ProductCodePrev);
					PreviousPolDtlGroupData.addContent(ClaimSettled);
					PreviousPolDtlGroupData.addContent(ClaimPremium);
					PreviousPolDtlGroupData.addContent(ClaimAmount);
					PreviousPolDtlGroupData.addContent(DateofLoss);
					PreviousPolDtlGroupData.addContent(NatureofLoss);
					PreviousPolDtlGroupData.addContent(ClaimNo);
					PreviousPolDtlGroupData.addContent(PolicyEffectiveTo);
					PreviousPolDtlGroupData.addContent(PolicyEffectiveFrom);
					PreviousPolDtlGroupData.addContent(DateOfInspection);
					PreviousPolDtlGroupData.addContent(PolicyPremium);
					PreviousPolDtlGroupData.addContent(PolicyNo);
					PreviousPolDtlGroupData.addContent(PolicyYear);
					PreviousPolDtlGroupData.addContent(OfficeCode);
					PreviousPolDtlGroupData.addContent(PolicyStatus);
					PreviousPolDtlGroupData.addContent(CorporateCustomerId);
					PreviousPolDtlGroupData.addContent(InsurerName);
					PreviousPolDtlGroupData.addContent(InsurerAddress);
					
					PreviousPolDtlGroup.addContent(PreviousPolDtlGroupData);
					PreviousPolicyDetails.addContent(PreviousPolDtlGroup);		//added
					GeneralProposal.addContent(PreviousPolicyDetails);
					
				
					
					org.jdom2.Element PreviousPolicyType= new org.jdom2.Element("PreviousPolicyType");
					PreviousPolicyType.setAttribute("Name", "Previous Policy Type");    
					PreviousPolicyType.setAttribute("Value", jsonResult.getString("PreviousPolicyType").trim());    
					org.jdom2.Element OfficeAddress= new org.jdom2.Element("OfficeAddress");
					OfficeAddress.setAttribute("Name", "Office Address");    
					OfficeAddress.setAttribute("Value", jsonResult.getString("OfficeAddress").trim());    
					
					PreviousPolicyDetails.addContent(PreviousPolicyType);
					PreviousPolicyDetails.addContent(OfficeAddress);

//					PreviousPolicyDetails.addContent(PreviousPolDtlGroup);
					
					
					
					org.jdom2.Element PremiumCalculation= new org.jdom2.Element("PremiumCalculation");
					PremiumCalculation.setAttribute("Name", "Premium Calculation");    
					org.jdom2.Element NetPremium= new org.jdom2.Element("NetPremium");
					NetPremium.setAttribute("Name", "Net Premium");    
					NetPremium.setAttribute("Value", jsonResult.getString("NetPremium").trim());  
					
					org.jdom2.Element ServiceTax= new org.jdom2.Element("ServiceTax");
					ServiceTax.setAttribute("Name", "Service Tax");    
					ServiceTax.setAttribute("Value", jsonResult.getString("ServiceTax").trim()); 
					
					org.jdom2.Element StampDuty2= new org.jdom2.Element("StampDuty2");
					StampDuty2.setAttribute("Name", "Stamp Duty");    
					StampDuty2.setAttribute("Value", jsonResult.getString("StampDuty2").trim());  
					
					org.jdom2.Element CGST= new org.jdom2.Element("CGST");
					CGST.setAttribute("Name", "CGST");    
					CGST.setAttribute("Value", jsonResult.getString("CGST").trim());   
					
					org.jdom2.Element SGST= new org.jdom2.Element("SGST");
					SGST.setAttribute("Name", "SGST");    
					SGST.setAttribute("Value", jsonResult.getString("SGST").trim());  
					
					org.jdom2.Element UGST= new org.jdom2.Element("UGST");
					UGST.setAttribute("Name", "UGST");    
					UGST.setAttribute("Value", jsonResult.getString("UGST").trim());  
					
					org.jdom2.Element IGST= new org.jdom2.Element("IGST");
					IGST.setAttribute("Name", "IGST");    
					IGST.setAttribute("Value", jsonResult.getString("IGST").trim());   
					
					org.jdom2.Element TotalPremium= new org.jdom2.Element("TotalPremium");
					TotalPremium.setAttribute("Name", "Total Premium");    
					TotalPremium.setAttribute("Value", jsonResult.getString("TotalPremium").trim());    
					
					PremiumCalculation.addContent(NetPremium);
					PremiumCalculation.addContent(ServiceTax);
					PremiumCalculation.addContent(StampDuty2);
					PremiumCalculation.addContent(CGST);
					PremiumCalculation.addContent(SGST);
					PremiumCalculation.addContent(UGST);
					PremiumCalculation.addContent(IGST);
					PremiumCalculation.addContent(TotalPremium);
					
					
					org.jdom2.Element Risks= new org.jdom2.Element("Risks");
					Risks.setAttribute("Name", "Risks");    
					org.jdom2.Element Risk= new org.jdom2.Element("Risk");
					Risk.setAttribute("Type", "Group");    
					Risk.setAttribute("Name", "Risks");    
					org.jdom2.Element RisksData= new org.jdom2.Element("RisksData");
					RisksData.setAttribute("Type", "GroupData");    
					org.jdom2.Element DetariffLoadings= new org.jdom2.Element("De-tariffLoadings");
					DetariffLoadings.setAttribute("Name", "De-tariffLoadings");    
					org.jdom2.Element DetariffLoadingGroup= new org.jdom2.Element("De-tariffLoadingGroup");
					DetariffLoadingGroup.setAttribute("Type", "Group");    
					DetariffLoadingGroup.setAttribute("Name", "De-tariff Loading Group");
					org.jdom2.Element DetariffLoadingGroupData= new org.jdom2.Element("De-tariffLoadingGroupData");
					DetariffLoadingGroupData.setAttribute("Type", "GroupData");    
					
				
					
					org.jdom2.Element LoadingAmount= new org.jdom2.Element("LoadingAmount");
					LoadingAmount.setAttribute("Name", "Loading Amount");    
					LoadingAmount.setAttribute("Value", jsonResult.getString("LoadingAmount").trim());
					
					org.jdom2.Element LoadingRate= new org.jdom2.Element("LoadingRate");
					LoadingRate.setAttribute("Name", "Loading Rate");    
					LoadingRate.setAttribute("Value", jsonResult.getString("LoadingRate").trim()); 
					
					org.jdom2.Element SumInsured= new org.jdom2.Element("SumInsured");
					SumInsured.setAttribute("Name", "SumInsured");    
					SumInsured.setAttribute("Value", jsonResult.getString("SumInsured").trim()); 
					
					org.jdom2.Element Rate= new org.jdom2.Element("Rate");
					Rate.setAttribute("Name", "Rate");    
					Rate.setAttribute("Value", jsonResult.getString("Rate").trim()); 
					
					org.jdom2.Element Premium= new org.jdom2.Element("Premium");
					Premium.setAttribute("Name", "Premium");    
					Premium.setAttribute("Value", jsonResult.getString("Premium").trim()); 
					
					org.jdom2.Element Applicable= new org.jdom2.Element("Applicable");
					Applicable.setAttribute("Name", "Applicable");    
//					Applicable.setAttribute("Value", jsonResult.getString("Applicable").trim());    
					Applicable.setAttribute("Value", "True");    
					
					org.jdom2.Element Description= new org.jdom2.Element("Description");
					Description.setAttribute("Name", "Description");    
					Description.setAttribute("Value", jsonResult.getString("Description").trim());    
					
					
					DetariffLoadingGroupData.addContent(LoadingAmount);
					DetariffLoadingGroupData.addContent(LoadingRate);
					DetariffLoadingGroupData.addContent(SumInsured);
					DetariffLoadingGroupData.addContent(Rate);
					DetariffLoadingGroupData.addContent(Premium);
					DetariffLoadingGroupData.addContent(Applicable);
					DetariffLoadingGroupData.addContent(Description);
					
					DetariffLoadingGroup.addContent(DetariffLoadingGroupData);
					DetariffLoadings.addContent(DetariffLoadingGroup);
					RisksData.addContent(DetariffLoadings);
					
					org.jdom2.Element DetariffDiscounts= new org.jdom2.Element("De-tariffDiscounts");
					DetariffDiscounts.setAttribute("Name", "De-tariffDiscounts");    
					org.jdom2.Element DetariffDiscountGroup= new org.jdom2.Element("De-tariffDiscountGroup");
					DetariffDiscountGroup.setAttribute("Type", "Group");    
					DetariffDiscountGroup.setAttribute("Name", "De-tariff Discount Group");    
					org.jdom2.Element DetariffDiscountGroupData= new org.jdom2.Element("De-tariffDiscountGroupData");
					DetariffDiscountGroupData.setAttribute("Type", "GroupData");    
					
					
					org.jdom2.Element DiscountAmount= new org.jdom2.Element("DiscountAmount");
					DiscountAmount.setAttribute("Name", "Discount Amount");    
					DiscountAmount.setAttribute("Value", jsonResult.getString("DiscountAmount").trim());
					
					org.jdom2.Element DiscountRate= new org.jdom2.Element("DiscountRate");
					DiscountRate.setAttribute("Name", "Discount Rate");    
					DiscountRate.setAttribute("Value",  jsonResult.getString("DiscountRate").trim());  
					
					org.jdom2.Element DiscountSumInsured= new org.jdom2.Element("SumInsured");
					DiscountSumInsured.setAttribute("Name", "SumInsured");    
					DiscountSumInsured.setAttribute("Value",  jsonResult.getString("DiscountSumInsured").trim());  
					
					org.jdom2.Element DiscountRate1= new org.jdom2.Element("Rate");
					DiscountRate1.setAttribute("Name", "Rate");    
					DiscountRate1.setAttribute("Value", jsonResult.getString("DiscountRate1").trim());   
					
					org.jdom2.Element DiscountPremium= new org.jdom2.Element("Premium");
					DiscountPremium.setAttribute("Name", "Premium");    
					DiscountPremium.setAttribute("Value",  jsonResult.getString("DiscountPremium").trim());  
					
					org.jdom2.Element DiscountApplicable= new org.jdom2.Element("Applicable");
					DiscountApplicable.setAttribute("Name", "Applicable");    
//					DiscountApplicable.setAttribute("Value", jsonResult.getString("DiscountApplicable").trim());   
					DiscountApplicable.setAttribute("Value", "True");   
					
					org.jdom2.Element DiscountDescription= new org.jdom2.Element("Description");
					DiscountDescription.setAttribute("Name", "Description");    
					DiscountDescription.setAttribute("Value", jsonResult.getString("DiscountDescription").trim());    
					
					
					
					DetariffDiscountGroupData.addContent(DiscountAmount);
					DetariffDiscountGroupData.addContent(DiscountRate);
					DetariffDiscountGroupData.addContent(DiscountSumInsured);
					DetariffDiscountGroupData.addContent(DiscountRate1);
					DetariffDiscountGroupData.addContent(DiscountPremium);
					DetariffDiscountGroupData.addContent(DiscountApplicable);
					DetariffDiscountGroupData.addContent(DiscountDescription);
					
					DetariffDiscountGroup.addContent(DetariffDiscountGroupData);
					DetariffDiscounts.addContent(DetariffDiscountGroup);
					
					RisksData.addContent(DetariffDiscounts);
					
					//****Start***********Code For Adding Package & Liability Covers
					
					org.jdom2.Element CoverDetails= new org.jdom2.Element("CoverDetails");
					CoverDetails.setAttribute("Name", "CoverDetails");    
					org.jdom2.Element Covers= new org.jdom2.Element("Covers");
					Covers.setAttribute("Type", "Group");    
					Covers.setAttribute("Name", "Covers");    
					
					addCovers(Covers,universalIntgBean.getSumInsBasicOd(),universalIntgBean.getAppBasicOd(),"Basic OD");
					addCovers(Covers,universalIntgBean.getSumInsBasicTp(),universalIntgBean.getAppBasicTp(),"Basic TP");
					addCovers(Covers,universalIntgBean.getSumInsPaCovToOwnDriver(),universalIntgBean.getAppPaCovToOwnDriver(),"PA COVER TO OWNER DRIVER");
					addCovers(Covers,universalIntgBean.getSumInsEleAccOd(),universalIntgBean.getAppEleAccOd(),"ELECTRICAL ACCESSORY OD");
					addCovers(Covers,universalIntgBean.getSumInsNonEleAccOd(),universalIntgBean.getAppNonEleAccOd(),"NON ELECTRICAL ACCESSORY OD");
					addCovers(Covers,universalIntgBean.getSumInsCnglpgKitOd(),universalIntgBean.getAppCnglpgKitOd(),"CNGLPG KIT OD");
					addCovers(Covers,universalIntgBean.getSumInsCnglpgKitTp(),universalIntgBean.getAppCnglpgKitTp(),"CNGLPG KIT TP");
					addCovers(Covers,universalIntgBean.getSumInsBulCnglpgKitOd(),universalIntgBean.getAppBulCnglpgKitOd(),"BUILTIN CNGLPG KIT OD");
					addCovers(Covers,universalIntgBean.getSumInsBulCnglpgKitTp(),universalIntgBean.getAppBulCnglpgKitTp(),"BUILTIN CNGLPG KIT TP");
					addCovers(Covers,universalIntgBean.getSumInsfiberTankOd(),universalIntgBean.getAppfiberTankOd(),"FIBERTANK OD");
					addCovers(Covers,universalIntgBean.getSumInsLigLiaToPaidDriver(),universalIntgBean.getAppLigLiaToPaidDriver(),"LEGAL LIABILITY TO PAID DRIVER");
					addCovers(Covers,universalIntgBean.getSumInsUnamPaCovToPassang(),universalIntgBean.getAppUnamPaCovToPassang(),"UNNAMED PA COVER TO PASSENGERS");
					addCovers(Covers,universalIntgBean.getSumInsPaCovToEmpOfInsured(),universalIntgBean.getAppPaCovToEmpOfInsured(),"PA COVER TO EMPLOYEES OF INSURED");
					addCovers(Covers,universalIntgBean.getSumInsPaCovToPassanger(),universalIntgBean.getAppPaCovToPassanger(),"PA COVER TO PASSENGERS");
					addCovers(Covers,universalIntgBean.getSumInsPaCovToPaidDriver(),universalIntgBean.getAppPaCovToPaidDriver(),"PA COVER TO PAID DRIVER");
					addCovers(Covers,universalIntgBean.getSumInsOtherOd(),universalIntgBean.getAppOtherOd(),"Other OD");
					addCovers(Covers,universalIntgBean.getSumInsOtherTp(),universalIntgBean.getAppOtherTp(),"Other TP");

					CoverDetails.addContent(Covers);
					RisksData.addContent(CoverDetails);
					
					//****End***********Code For Adding Package & Liability Covers
					
					
					
					//****Start***********Code For Adding Addon Covers
					
					org.jdom2.Element AddonCoverDetails = new org.jdom2.Element("AddonCoverDetails");	
					AddonCoverDetails.setAttribute("Name", "AddonCoverDetails");
					org.jdom2.Element AddonCovers = new org.jdom2.Element("AddonCovers");	
					AddonCovers.setAttribute("Type", "Group");
					AddonCovers.setAttribute("Name", "AddonCovers");
					
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsNilDepWaiCover(),universalIntgBean.getAppNilDepWaiCover(),"Nil Depreciation Waiver cover");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsDailyCashAllow(),universalIntgBean.getAppDailyCashAllow(),"DAILY CASH ALLOWANCE");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsKeyReplacement(),universalIntgBean.getAppKeyReplacement(),"KEY REPLACEMENT");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsReturnToInvoice(),universalIntgBean.getAppReturnToInvoice(),"RETURN TO INVOICE");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsAccHospit(),universalIntgBean.getAppAccHospit(),"ACCIDENTAL HOSPITALIZATION");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsRoadSideAssis(),universalIntgBean.getAppRoadSideAssis(),"Road side Assistance");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsHydrostaticLockCover(),universalIntgBean.getAppHydrostaticLockCover(),"HYDROSTATIC LOCK COVER");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsCostOfConsumable(),universalIntgBean.getAppCostOfConsumable(),"COST OF CONSUMABLES");
					addAddonCovers(AddonCovers,universalIntgBean.getSumInsSecureTowing(), universalIntgBean.getAppSecureTowing(),"SECURE TOWING");
					
					AddonCoverDetails.addContent(AddonCovers);
					RisksData.addContent(AddonCoverDetails);
					
					//****End***********Code For Adding Addon Covers
					
					org.jdom2.Element OtherLoadings = new org.jdom2.Element("OtherLoadings");	
					OtherLoadings.setAttribute("Name", "OtherLoadings");
					org.jdom2.Element OtherLoadingGroup = new org.jdom2.Element("OtherLoadingGroup");	
					OtherLoadingGroup.setAttribute("Type", "Group");
					OtherLoadingGroup.setAttribute("Name", "Other Loading Group");

					OtherLoadings.addContent(OtherLoadingGroup);
					RisksData.addContent(OtherLoadings);
					
					//****Start***********Code For Adding Discount Covers
					
					org.jdom2.Element OtherDiscounts = new org.jdom2.Element("OtherDiscounts");	
					OtherDiscounts.setAttribute("Name", "OtherDiscounts");
					org.jdom2.Element OtherDiscountGroup = new org.jdom2.Element("OtherDiscountGroup");	
					OtherDiscountGroup.setAttribute("Type", "Group");
					OtherDiscountGroup.setAttribute("Name", "Other Discount Group");
				
					addDiscountCovers(OtherDiscountGroup,universalIntgBean.getSumInsAntiDeviceDisc(),universalIntgBean.getAppAntiDeviceDisc(),"Antitheft device discount");
//					addDiscountCovers(OtherDiscountGroup,sumInsVolDeductible,appVolDeductible,"Voluntary deductable");
					addDiscountCovers(OtherDiscountGroup,universalIntgBean.getSumInsVolDeductible(),"True","Voluntary deductable");
//					addDiscountCovers(OtherDiscountGroup,sumInsNCB,appNCB,"No claim bonus");
					addDiscountCovers(OtherDiscountGroup,universalIntgBean.getSumInsNCB(),"True","No claim bonus");
					addDiscountCovers(OtherDiscountGroup,universalIntgBean.getSumInsTppdDiscount(),universalIntgBean.getAppTppdDiscount(),"TPPD Discount");
					addDiscountCovers(OtherDiscountGroup,universalIntgBean.getSumInsAutoAssoDiscount(),universalIntgBean.getAppAutoAssoDiscount(),"Automobile Association discount");
					addDiscountCovers(OtherDiscountGroup,universalIntgBean.getSumInsHandicapDiscount(),universalIntgBean.getAppHandicapDiscount(),"Handicap discount");
					
					
					OtherDiscounts.addContent(OtherDiscountGroup);
					RisksData.addContent(OtherDiscounts);
					
					//****End***********Code For Adding Discount Covers
					
					Risk.addContent(RisksData);
					
					org.jdom2.Element VehicleClassCode = new org.jdom2.Element("VehicleClassCode");	
					VehicleClassCode.setAttribute("Name", "VehicleClassCode");
					VehicleClassCode.setAttribute("Value", jsonResult.getString("VEHICLECLASSCODE").trim());    
					
					org.jdom2.Element VehicleMakeCode  = new org.jdom2.Element("VehicleMakeCode");	
					VehicleMakeCode.setAttribute("Name", "VehicleMakeCode");
					VehicleMakeCode.setAttribute("Value", jsonResult.getString("VEHICLEMAKECODE").trim());
					
					org.jdom2.Element VehicleModelCode   = new org.jdom2.Element("VehicleModelCode");	
					VehicleModelCode.setAttribute("Name", "VehicleModelCode");
					VehicleModelCode.setAttribute("Value", jsonResult.getString("VEHICLEMODELCODE").trim()); 
					
					org.jdom2.Element RTOLocationCode   = new org.jdom2.Element("RTOLocationCode");	
					RTOLocationCode.setAttribute("Name", "RTOLocationCode");
					RTOLocationCode.setAttribute("Value", jsonResult.getString("RTOLocationCode").trim());
					
					org.jdom2.Element NoOfClaimsOnPreviousPolicy   = new org.jdom2.Element("NoOfClaimsOnPreviousPolicy");	
					NoOfClaimsOnPreviousPolicy.setAttribute("Name", "No Of Claims On Previous Policy");
					NoOfClaimsOnPreviousPolicy.setAttribute("Value", jsonResult.getString("NoOfClaimsOnPreviousPolicy").trim());   
					
					org.jdom2.Element RegistrationNumber   = new org.jdom2.Element("RegistrationNumber");	
					RegistrationNumber.setAttribute("Name", "Registration Number");
					RegistrationNumber.setAttribute("Value", jsonResult.getString("RegistrationNumber").trim()); 
					
					org.jdom2.Element BodyTypeCode   = new org.jdom2.Element("BodyTypeCode");	
					BodyTypeCode.setAttribute("Name", "BodyTypeCode");
					BodyTypeCode.setAttribute("Value", jsonResult.getString("BodyTypeCode").trim());   
					
					org.jdom2.Element ModelStatus   = new org.jdom2.Element("ModelStatus");	
					ModelStatus.setAttribute("Name", "ModelStatus");
					ModelStatus.setAttribute("Value", jsonResult.getString("ModelStatus").trim());   
					
					org.jdom2.Element GrossVehicleWeight   = new org.jdom2.Element("GrossVehicleWeight");	
					GrossVehicleWeight.setAttribute("Name", "GrossVehicleWeight");
					GrossVehicleWeight.setAttribute("Value", jsonResult.getString("GROSSVEHICLEWEIGHT").trim());  
					
					org.jdom2.Element CarryingCapacity   = new org.jdom2.Element("CarryingCapacity");	
					CarryingCapacity.setAttribute("Name", "CarryingCapacity");
					CarryingCapacity.setAttribute("Value", jsonResult.getString("CARRYINGCAPACITY").trim());  
					
					org.jdom2.Element VehicleType   = new org.jdom2.Element("VehicleType");	
					VehicleType.setAttribute("Name", "VehicleType");
					VehicleType.setAttribute("Value", jsonResult.getString("VEHICLETYPE").trim());    
					
					org.jdom2.Element PlaceOfRegistration   = new org.jdom2.Element("PlaceOfRegistration");	
					PlaceOfRegistration.setAttribute("Name", "Place Of Registration");
					PlaceOfRegistration.setAttribute("Value", jsonResult.getString("PLACEOFREGISTRATION").trim());  
					
					org.jdom2.Element VehicleModel   = new org.jdom2.Element("VehicleModel");	
					VehicleModel.setAttribute("Name", "VehicleModel");
					VehicleModel.setAttribute("Value", jsonResult.getString("VEHICLEMODEL").trim());    
					
					org.jdom2.Element VehicleExShowroomPrice   = new org.jdom2.Element("VehicleExShowroomPrice");	
					VehicleExShowroomPrice.setAttribute("Name", "VehicleExShowroomPrice");
					VehicleExShowroomPrice.setAttribute("Value", jsonResult.getString("VehicleExShowroomPrice").trim());   
					
					org.jdom2.Element DateOfDeliveryOrRegistration   = new org.jdom2.Element("DateOfDeliveryOrRegistration");	
					DateOfDeliveryOrRegistration.setAttribute("Name", "DateOfDeliveryOrRegistration");
					DateOfDeliveryOrRegistration.setAttribute("Value", jsonResult.getString("DateOfDeliveryOrRegistration").trim());
					
				
					
					org.jdom2.Element YearOfManufacture   = new org.jdom2.Element("YearOfManufacture");	
					YearOfManufacture.setAttribute("Name", "Year Of Manufacture");
					YearOfManufacture.setAttribute("Value", jsonResult.getString("YearOfManufacture").trim());  
					
					org.jdom2.Element DateOfFirstRegistration   = new org.jdom2.Element("DateOfFirstRegistration");	
					DateOfFirstRegistration.setAttribute("Name", "DateOfFirstRegistration");
					DateOfFirstRegistration.setAttribute("Value", jsonResult.getString("DateOfFirstRegistration").trim());
					
					org.jdom2.Element RegistrationNumberSection1   = new org.jdom2.Element("RegistrationNumberSection1");	
					RegistrationNumberSection1.setAttribute("Name", "Regn No. Section 1");
					RegistrationNumberSection1.setAttribute("Value", jsonResult.getString("RegistrationNumberSection1").trim());    
					
					org.jdom2.Element RegistrationNumberSection2   = new org.jdom2.Element("RegistrationNumberSection2");	
					RegistrationNumberSection2.setAttribute("Name", "Regn No. Section 2");
					RegistrationNumberSection2.setAttribute("Value", jsonResult.getString("RegistrationNumberSection2").trim());   
					
					org.jdom2.Element RegistrationNumberSection3   = new org.jdom2.Element("RegistrationNumberSection3");	
					RegistrationNumberSection3.setAttribute("Name", "Regn No. Section 3");
					RegistrationNumberSection3.setAttribute("Value", jsonResult.getString("RegistrationNumberSection3").trim());    
					org.jdom2.Element RegistrationNumberSection4   = new org.jdom2.Element("RegistrationNumberSection4");	
					RegistrationNumberSection4.setAttribute("Name", "Regn No. Section 4");
					RegistrationNumberSection4.setAttribute("Value", jsonResult.getString("RegistrationNumberSection4").trim());    
					org.jdom2.Element EngineNumber   = new org.jdom2.Element("EngineNumber");	
					EngineNumber.setAttribute("Name", "Engine Number");
					EngineNumber.setAttribute("Value", jsonResult.getString("EngineNumber").trim()); 
//					EngineNumber.setAttribute("Value", "ZTCR56789"); 
					
					org.jdom2.Element ChassisNumber   = new org.jdom2.Element("ChassisNumber");	
					ChassisNumber.setAttribute("Name", "Chassis Number");
					ChassisNumber.setAttribute("Value", jsonResult.getString("ChassisNumber").trim());    
//					ChassisNumber.setAttribute("Value", "ZTCR56789");    
					
					org.jdom2.Element BodyColour   = new org.jdom2.Element("BodyColour");	
					BodyColour.setAttribute("Name", "Body Colour");
					BodyColour.setAttribute("Value", jsonResult.getString("BODYCOLOUR").trim());    
					org.jdom2.Element FuelType   = new org.jdom2.Element("FuelType");	
					FuelType.setAttribute("Name", "Fuel Type");
					FuelType.setAttribute("Value", jsonResult.getString("FUELTYPE").trim());    
					
					org.jdom2.Element ExtensionCountryName   = new org.jdom2.Element("ExtensionCountryName");	
					ExtensionCountryName.setAttribute("Name", "Extension Country Name");
					ExtensionCountryName.setAttribute("Value", jsonResult.getString("ExtensionCountryName").trim());  
					
					org.jdom2.Element RegistrationAuthorityName   = new org.jdom2.Element("RegistrationAuthorityName");	
					RegistrationAuthorityName.setAttribute("Name", "Registration Authority Name");
					RegistrationAuthorityName.setAttribute("Value", jsonResult.getString("REGISTRATIONAUTHORITYNAME").trim());    
					
					org.jdom2.Element AutomobileAssocnFlag   = new org.jdom2.Element("AutomobileAssocnFlag");	
					AutomobileAssocnFlag.setAttribute("Name", "AutomobileAssocnFlag");
					AutomobileAssocnFlag.setAttribute("Value", jsonResult.getString("AutomobileAssocnFlag").trim()); 
					
					org.jdom2.Element AutomobileAssociationNumber   = new org.jdom2.Element("AutomobileAssociationNumber");	
					AutomobileAssociationNumber.setAttribute("Name", "Automobile Association Number");
					AutomobileAssociationNumber.setAttribute("Value", jsonResult.getString("AutomobileAssociationNumber").trim());   
					
					org.jdom2.Element VoluntaryExcess   = new org.jdom2.Element("VoluntaryExcess");	
					VoluntaryExcess.setAttribute("Name", "Voluntary Access");
					VoluntaryExcess.setAttribute("Value", jsonResult.getString("VoluntaryExcess").trim());  
					
					org.jdom2.Element TPPDLimit   = new org.jdom2.Element("TPPDLimit");	
					TPPDLimit.setAttribute("Name", "TPPDLimit");
					TPPDLimit.setAttribute("Value", jsonResult.getString("TPPDLimit").trim());  
					
					org.jdom2.Element AntiTheftDiscFlag   = new org.jdom2.Element("AntiTheftDiscFlag");	
					AntiTheftDiscFlag.setAttribute("Name", "AntiTheftDiscFlag");
					AntiTheftDiscFlag.setAttribute("Value", jsonResult.getString("AntiTheftDiscFlag").trim()); 
					
					org.jdom2.Element HandicapDiscFlag   = new org.jdom2.Element("HandicapDiscFlag");	
					HandicapDiscFlag.setAttribute("Name", "HandicapDiscFlag");
					HandicapDiscFlag.setAttribute("Value", jsonResult.getString("HandicapDiscFlag").trim());   
					
					org.jdom2.Element NumberOfDrivers   = new org.jdom2.Element("NumberOfDrivers");	
					NumberOfDrivers.setAttribute("Name", "NumberOfDrivers");
					NumberOfDrivers.setAttribute("Value", jsonResult.getString("NumberOfDrivers").trim());
					
					org.jdom2.Element NumberOfEmployees   = new org.jdom2.Element("NumberOfEmployees");	
					NumberOfEmployees.setAttribute("Name", "NumberOfEmployees");
					NumberOfEmployees.setAttribute("Value", jsonResult.getString("NumberOfEmployees").trim());    
					org.jdom2.Element TransferOfNCB   = new org.jdom2.Element("TransferOfNCB");	
					TransferOfNCB.setAttribute("Name", "TransferOfNCB");
					TransferOfNCB.setAttribute("Value", jsonResult.getString("TransferOfNCB").trim()); 
					
					org.jdom2.Element TransferOfNCBPercent   = new org.jdom2.Element("TransferOfNCBPercent");	
					TransferOfNCBPercent.setAttribute("Name", "TransferOfNCBPercent");
					TransferOfNCBPercent.setAttribute("Value", jsonResult.getString("TransferOfNCBPercent").trim());    
					org.jdom2.Element NCBDeclaration   = new org.jdom2.Element("NCBDeclaration");	
					NCBDeclaration.setAttribute("Name", "NCBDeclaration");
					NCBDeclaration.setAttribute("Value", jsonResult.getString("NCBDeclaration").trim());    
					org.jdom2.Element PreviousVehicleSaleDate   = new org.jdom2.Element("PreviousVehicleSaleDate");	
					PreviousVehicleSaleDate.setAttribute("Name", "PreviousVehicleSaleDate");
					PreviousVehicleSaleDate.setAttribute("Value", jsonResult.getString("PreviousVehicleSaleDate").trim());
					
					org.jdom2.Element BonusOnPreviousPolicy   = new org.jdom2.Element("BonusOnPreviousPolicy");	
					BonusOnPreviousPolicy.setAttribute("Name", "BonusOnPreviousPolicy");
					BonusOnPreviousPolicy.setAttribute("Value", jsonResult.getString("BonusOnPreviousPolicy").trim()); 
					
				
					org.jdom2.Element VehicleClass   = new org.jdom2.Element("VehicleClass");	
					VehicleClass.setAttribute("Name", "VehicleClass");
					VehicleClass.setAttribute("Value", jsonResult.getString("VehicleClass").trim());    
					org.jdom2.Element VehicleMake   = new org.jdom2.Element("VehicleMake");	
					VehicleMake.setAttribute("Name", "VehicleMake");
					VehicleMake.setAttribute("Value", jsonResult.getString("VehicleMake").trim());  
					
					org.jdom2.Element BodyTypeDescription   = new org.jdom2.Element("BodyTypeDescription");	
					BodyTypeDescription.setAttribute("Name", "BodyTypeDescription");
					BodyTypeDescription.setAttribute("Value", jsonResult.getString("BodyTypeDescription").trim());
					
					org.jdom2.Element NumberOfWheels   = new org.jdom2.Element("NumberOfWheels");	
					NumberOfWheels.setAttribute("Name", "NumberOfWheels");
//					NumberOfWheels.setAttribute("Value", jsonResult.getString("NumberOfWheels").trim());  
					NumberOfWheels.setAttribute("Value", jsonResult.getString("NUMBEROFWHEELS").trim());  
					
					org.jdom2.Element CubicCapacity   = new org.jdom2.Element("CubicCapacity");	
					CubicCapacity.setAttribute("Name", "CubicCapacity");
//					CubicCapacity.setAttribute("Value", jsonResult.getString("CubicCapacity").trim()); 
					CubicCapacity.setAttribute("Value", jsonResult.getString("CUBICCAPACITY").trim()); 
					
					org.jdom2.Element SeatingCapacity   = new org.jdom2.Element("SeatingCapacity");	
					SeatingCapacity.setAttribute("Name", "SeatingCapacity");
//					SeatingCapacity.setAttribute("Value", jsonResult.getString("SeatingCapacity").trim());   
					SeatingCapacity.setAttribute("Value", jsonResult.getString("SEATINGCAPACITY").trim());   
					
					org.jdom2.Element RegistrationZone   = new org.jdom2.Element("RegistrationZone");	
					RegistrationZone.setAttribute("Name", "RegistrationZone");
//					RegistrationZone.setAttribute("Value", jsonResult.getString("RegistrationZone").trim());
					RegistrationZone.setAttribute("Value", jsonResult.getString("REGISTRATIONZONE").trim());
					
					org.jdom2.Element VehiclesDrivenBy   = new org.jdom2.Element("VehiclesDrivenBy");	
					VehiclesDrivenBy.setAttribute("Name", "Vehicles Driven By");
					VehiclesDrivenBy.setAttribute("Value", jsonResult.getString("VehiclesDrivenBy").trim());    
					org.jdom2.Element DriversAge   = new org.jdom2.Element("DriversAge");	
					DriversAge.setAttribute("Name", "Drivers Age");
					DriversAge.setAttribute("Value", jsonResult.getString("DriversAge").trim());    
					
					org.jdom2.Element DriversExperience   = new org.jdom2.Element("DriversExperience");	
					DriversExperience.setAttribute("Name", "Drivers Experience");
					DriversExperience.setAttribute("Value", jsonResult.getString("DriversExperience").trim());
					
					org.jdom2.Element DriversQualification   = new org.jdom2.Element("DriversQualification");	
					DriversQualification.setAttribute("Name", "Drivers Qualification");
					DriversQualification.setAttribute("Value", jsonResult.getString("DriversQualification").trim());  
					
					org.jdom2.Element VehicleModelCluster   = new org.jdom2.Element("VehicleModelCluster");	
					VehicleModelCluster.setAttribute("Name", "VehicleModelCluster");
					VehicleModelCluster.setAttribute("Value", jsonResult.getString("VehicleModelCluster").trim());    
					
					org.jdom2.Element OpenCoverNoteFlag   = new org.jdom2.Element("OpenCoverNoteFlag");	
					OpenCoverNoteFlag.setAttribute("Name", "OpenCoverNote");
					OpenCoverNoteFlag.setAttribute("Value", jsonResult.getString("OpenCoverNoteFlag").trim());    
					
					org.jdom2.Element LegalLiability   = new org.jdom2.Element("LegalLiability");	
					LegalLiability.setAttribute("Name", "LegalLiability");
					LegalLiability.setAttribute("Value", jsonResult.getString("LegalLiability").trim());  
					
					org.jdom2.Element PaidDriver   = new org.jdom2.Element("PaidDriver");	
					PaidDriver.setAttribute("Name", "PaidDriver");
					PaidDriver.setAttribute("Value", jsonResult.getString("PaidDriver").trim()); 
					
					org.jdom2.Element NCBConfirmation   = new org.jdom2.Element("NCBConfirmation");	
					NCBConfirmation.setAttribute("Name", "NCBConfirmation");
					NCBConfirmation.setAttribute("Value", jsonResult.getString("NCBConfirmation").trim());   
					
					
					
					org.jdom2.Element RegistrationDate   = new org.jdom2.Element("RegistrationDate");	
					RegistrationDate.setAttribute("Name", "RegistrationDate");
					RegistrationDate.setAttribute("Value", jsonResult.getString("RegistrationDate").trim());    
					org.jdom2.Element TPLoadingRate   = new org.jdom2.Element("TPLoadingRate");	
					TPLoadingRate.setAttribute("Name", "TPLoadingRate");
					TPLoadingRate.setAttribute("Value", jsonResult.getString("TPLoadingRate").trim());    
					org.jdom2.Element ExtensionCountry   = new org.jdom2.Element("ExtensionCountry");	
					ExtensionCountry.setAttribute("Name", "ExtensionCountry");
					ExtensionCountry.setAttribute("Value", jsonResult.getString("ExtensionCountry").trim());    
					org.jdom2.Element VehicleAge   = new org.jdom2.Element("VehicleAge");	
					VehicleAge.setAttribute("Name", "VehicleAge");
					VehicleAge.setAttribute("Value", jsonResult.getString("VehicleAge").trim());   
					
					org.jdom2.Element LocationCode   = new org.jdom2.Element("LocationCode");	
					LocationCode.setAttribute("Name", "LocationCode");
					LocationCode.setAttribute("Value", jsonResult.getString("LocationCode").trim());  
					
					org.jdom2.Element RegistrationZoneDescription   = new org.jdom2.Element("RegistrationZoneDescription");	
					RegistrationZoneDescription.setAttribute("Name", "RegistrationZoneDescription");
					RegistrationZoneDescription.setAttribute("Value", jsonResult.getString("RegistrationZoneDescription").trim());    
					
					org.jdom2.Element NumberOfWorkmen   = new org.jdom2.Element("NumberOfWorkmen");	
					NumberOfWorkmen.setAttribute("Name", "NumberOfWorkmen");
					NumberOfWorkmen.setAttribute("Value", jsonResult.getString("NumberOfWorkmen").trim());    
					org.jdom2.Element VehicCd   = new org.jdom2.Element("VehicCd");	
					VehicCd.setAttribute("Name", "VehicCd");
					VehicCd.setAttribute("Value", jsonResult.getString("VehicCd").trim());    
					org.jdom2.Element SalesTax   = new org.jdom2.Element("SalesTax");	
					SalesTax.setAttribute("Name", "Sales Tax");
					SalesTax.setAttribute("Value", jsonResult.getString("SalesTax").trim());    
					org.jdom2.Element ModelOfVehicle= new org.jdom2.Element("ModelOfVehicle");	
					ModelOfVehicle.setAttribute("Name", "Model Of Vehicle");
					ModelOfVehicle.setAttribute("Value", jsonResult.getString("ModelOfVehicle").trim()); 
					
					org.jdom2.Element PopulateDetails= new org.jdom2.Element("PopulateDetails");	
					PopulateDetails.setAttribute("Name", "Populate details");
					PopulateDetails.setAttribute("Value", jsonResult.getString("PopulateDetails").trim());    
					org.jdom2.Element VehicleIDV= new org.jdom2.Element("VehicleIDV");	
					VehicleIDV.setAttribute("Name", "VehicleInsuredDeclaredValue");
					VehicleIDV.setAttribute("Value", jsonResult.getString("VehicleIDV").trim());    
					
					org.jdom2.Element ShowroomPriceDeviation= new org.jdom2.Element("ShowroomPriceDeviation");	
					ShowroomPriceDeviation.setAttribute("Name", "ShowroomPriceDeviation");
					ShowroomPriceDeviation.setAttribute("Value", jsonResult.getString("ShowroomPriceDeviation").trim());    
					org.jdom2.Element NewVehicle= new org.jdom2.Element("NewVehicle");	
					NewVehicle.setAttribute("Name", "New Vehicle");
					NewVehicle.setAttribute("Value", jsonResult.getString("NewVehicle").trim());    
					
					Risks.addContent(Risk);
					Risks.addContent(VehicleClassCode) ;
					Risks.addContent(VehicleMakeCode) ;
					Risks.addContent(VehicleModelCode) ;
					Risks.addContent(RTOLocationCode) ;
					Risks.addContent(NoOfClaimsOnPreviousPolicy) ;
					Risks.addContent(RegistrationNumber) ;
					Risks.addContent(BodyTypeCode) ;
					Risks.addContent(ModelStatus) ;
					Risks.addContent(GrossVehicleWeight) ;
					Risks.addContent(CarryingCapacity) ;
					Risks.addContent(VehicleType) ;
					Risks.addContent(PlaceOfRegistration) ;
					Risks.addContent(VehicleModel) ;
					Risks.addContent(VehicleExShowroomPrice) ;
					Risks.addContent(DateOfDeliveryOrRegistration) ;
					Risks.addContent(YearOfManufacture) ;
					Risks.addContent(DateOfFirstRegistration) ;
					Risks.addContent(RegistrationNumberSection1) ;
					Risks.addContent(RegistrationNumberSection2) ;
					Risks.addContent(RegistrationNumberSection3) ;
					Risks.addContent(RegistrationNumberSection4) ;
					Risks.addContent(EngineNumber) ;
					Risks.addContent(ChassisNumber) ;
					Risks.addContent(BodyColour) ;
					Risks.addContent(FuelType) ;
					Risks.addContent(ExtensionCountryName) ;
					Risks.addContent(RegistrationAuthorityName) ;
					Risks.addContent(AutomobileAssocnFlag) ;
					Risks.addContent(AutomobileAssociationNumber) ;
					Risks.addContent(VoluntaryExcess) ;
					Risks.addContent(TPPDLimit) ;
					Risks.addContent(AntiTheftDiscFlag) ;
					Risks.addContent(HandicapDiscFlag) ;
					Risks.addContent(NumberOfDrivers) ;
					Risks.addContent(NumberOfEmployees) ;
					Risks.addContent(TransferOfNCB) ;
					Risks.addContent(TransferOfNCBPercent) ;
					Risks.addContent(NCBDeclaration) ;
					Risks.addContent(PreviousVehicleSaleDate) ;
					Risks.addContent(BonusOnPreviousPolicy) ;
					Risks.addContent(VehicleClass) ;
					Risks.addContent(VehicleMake) ;
					Risks.addContent(BodyTypeDescription) ;
					Risks.addContent(NumberOfWheels) ;
					Risks.addContent(CubicCapacity) ;
					Risks.addContent(SeatingCapacity) ;
					Risks.addContent(RegistrationZone) ;
					Risks.addContent(VehiclesDrivenBy) ;
					Risks.addContent(DriversAge) ;
					Risks.addContent(DriversExperience) ;
					Risks.addContent(DriversQualification) ;
					Risks.addContent(VehicleModelCluster) ;
					Risks.addContent(OpenCoverNoteFlag) ;
					Risks.addContent(LegalLiability) ;
					Risks.addContent(PaidDriver) ;
					Risks.addContent(NCBConfirmation) ;
					Risks.addContent(RegistrationDate) ;
					Risks.addContent(TPLoadingRate) ;
					Risks.addContent(ExtensionCountry) ;
					Risks.addContent(VehicleAge) ;
					Risks.addContent(LocationCode) ;
					Risks.addContent(RegistrationZoneDescription) ;
					Risks.addContent(NumberOfWorkmen) ;
					Risks.addContent(VehicCd) ;
					Risks.addContent(SalesTax) ;
					Risks.addContent(ModelOfVehicle) ;
					Risks.addContent(PopulateDetails) ;
					Risks.addContent(VehicleIDV) ;
					Risks.addContent(ShowroomPriceDeviation) ;
					Risks.addContent(NewVehicle) ;
					
			
				
			
				Product.addContent(GeneralProposal);
				Product.addContent(PremiumCalculation);
				Product.addContent(Risks);
				
					
				org.jdom2.Element PaymentDetails= new org.jdom2.Element("PaymentDetails");	
				
				org.jdom2.Element PaymentEntry= new org.jdom2.Element("PaymentEntry");	
			
				
				org.jdom2.Element PaymentId= new org.jdom2.Element("PaymentId");
				PaymentId.setText( jsonResult.getString("PaymentId").trim()); 
				
				org.jdom2.Element MICRCheque= new org.jdom2.Element("MICRCheque");	
				MICRCheque.setText( jsonResult.getString("MICRCheque").trim());
				
				org.jdom2.Element InstrumentDatePayment= new org.jdom2.Element("InstrumentDate");
				InstrumentDatePayment.setText( jsonResult.getString("InstrumentDatePayment").trim()); 
				
				org.jdom2.Element DraweeBankName= new org.jdom2.Element("DraweeBankName");
				DraweeBankName.setText( jsonResult.getString("DraweeBankName").trim());  
				
				org.jdom2.Element HOUSEBANKNAME= new org.jdom2.Element("HOUSEBANKNAME");
				HOUSEBANKNAME.setText( jsonResult.getString("HOUSEBANKNAME").trim());  
				
				org.jdom2.Element AmountPaid= new org.jdom2.Element("AmountPaid");	
				AmountPaid.setText( jsonResult.getString("AmountPaid").trim()); 
				
				org.jdom2.Element PaymentType= new org.jdom2.Element("PaymentType");
				PaymentType.setText( jsonResult.getString("PaymentType").trim());  
				
				org.jdom2.Element PaymentMode= new org.jdom2.Element("PaymentMode");
				PaymentMode.setText( jsonResult.getString("PaymentMode").trim());
				
				org.jdom2.Element InstrumentNoPayment= new org.jdom2.Element("InstrumentNo");
				InstrumentNoPayment.setText( jsonResult.getString("InstrumentNoPayment").trim());
				
				org.jdom2.Element Status= new org.jdom2.Element("Status");	
				Status.setText( jsonResult.getString("Status").trim());    
				
				org.jdom2.Element DepositSlipNo=new org.jdom2.Element("DepositSlipNo");
				DepositSlipNo.setText( jsonResult.getString("DepositSlipNo").trim()); 
				
				org.jdom2.Element PayerType=new org.jdom2.Element("PayerType");	
				PayerType.setText("");    
				
				
				PaymentEntry.addContent(PaymentId);
				PaymentEntry.addContent(MICRCheque);
				PaymentEntry.addContent(InstrumentDatePayment);
				PaymentEntry.addContent(DraweeBankName);
				PaymentEntry.addContent(HOUSEBANKNAME);
				PaymentEntry.addContent(AmountPaid);
				PaymentEntry.addContent(PaymentType);
				PaymentEntry.addContent(PaymentMode);
				PaymentEntry.addContent(InstrumentNoPayment);
				PaymentEntry.addContent(Status);
				PaymentEntry.addContent(DepositSlipNo);
				PaymentEntry.addContent(PayerType);
				PaymentDetails.addContent(PaymentEntry);
			
			
				org.jdom2.Element Errors=new org.jdom2.Element("Errors");	
				org.jdom2.Element ErrorCode=new org.jdom2.Element("ErrorCode");	
				org.jdom2.Element ErrDescription=new org.jdom2.Element("ErrDescription");	
			
				Errors.addContent(ErrorCode);
				Errors.addContent(ErrDescription);
				rootelement.addContent(Authentication) ;
				rootelement.addContent(Customer) ;
				rootelement.addContent(Product) ;
				rootelement.addContent(PaymentDetails);
				rootelement.addContent(Errors);
					
				
					
				XMLOutputter xmlOutput = new XMLOutputter();
					xmlOutput.setFormat(Format.getPrettyFormat());
					xx = xmlOutput.outputString(document2);

					xmlOutput.output(document2, new FileWriter(path+"\\UniversalRequest.xml"));
//				System.out.println("xx="+xx);
				System.err.println("sumInsNCB------------------------>>"+universalIntgBean.getSumInsNCB());
				System.err.println("appNCB--------------------------->>"+universalIntgBean.getAppNCB());
			
				}	
			} catch (Exception e) {
				System.out.println("//////////////////////////" + e);
			}
			return xx;

		}
		
		private void addCovers(org.jdom2.Element Covers,String sumInsured,String applicable,String coverGroups){
				org.jdom2.Element CoversData=null;
				CoversData = new org.jdom2.Element("CoversData");	
				CoversData.setAttribute("Type", "GroupData");
				org.jdom2.Element PremiumCovers = new org.jdom2.Element("Premium");	
				PremiumCovers.setAttribute("Name", "Premium");
				PremiumCovers.setAttribute("Value",  "");    
				org.jdom2.Element RateCovers = new org.jdom2.Element("Rate");	
				RateCovers.setAttribute("Name", "Rate");
				RateCovers.setAttribute("Value",  "");    
				org.jdom2.Element SumInsuredCovers = new org.jdom2.Element("SumInsured");	
				SumInsuredCovers.setAttribute("Name", "SumInsured");
				SumInsuredCovers.setAttribute("Value", sumInsured);    
				org.jdom2.Element ApplicableCovers = new org.jdom2.Element("Applicable");	
				ApplicableCovers.setAttribute("Name", "Applicable");
				ApplicableCovers.setAttribute("Value", applicable);    
				org.jdom2.Element CoverGroupsCovers = new org.jdom2.Element("CoverGroups");	
				CoverGroupsCovers.setAttribute("Name", "CoverGroups");
				CoverGroupsCovers.setAttribute("Value", coverGroups);
				CoversData.addContent(PremiumCovers);
				CoversData.addContent(RateCovers);
				CoversData.addContent(SumInsuredCovers);
				CoversData.addContent(ApplicableCovers);
				CoversData.addContent(CoverGroupsCovers);
				Covers.addContent(CoversData);
			
		}
		
		private void addAddonCovers(org.jdom2.Element AddonCovers,String sumInsured,String applicable,String addonCoverGroups){
			
			org.jdom2.Element AddonCoversData=null;
			AddonCoversData = new org.jdom2.Element("AddonCoversData");	
			AddonCoversData.setAttribute("Type", "GroupData");
			org.jdom2.Element PremiumAddOn = new org.jdom2.Element("Premium");	
			PremiumAddOn.setAttribute("Name", "Premium");
			PremiumAddOn.setAttribute("Value", "");    
			org.jdom2.Element RateAddOn = new org.jdom2.Element("Rate");	
			RateAddOn.setAttribute("Name", "Rate");
			RateAddOn.setAttribute("Value", "");    
			org.jdom2.Element SumInsuredAddOn = new org.jdom2.Element("SumInsured");	
			SumInsuredAddOn.setAttribute("Name", "SumInsured");
			SumInsuredAddOn.setAttribute("Value", sumInsured);    
			org.jdom2.Element ApplicableAddOn = new org.jdom2.Element("Applicable");	
			ApplicableAddOn.setAttribute("Name", "Applicable");
			ApplicableAddOn.setAttribute("Value",applicable);    
			org.jdom2.Element AddonCoverGroupsAddOn = new org.jdom2.Element("AddonCoverGroups");	
			AddonCoverGroupsAddOn.setAttribute("Name", "AddonCoverGroups");
			AddonCoverGroupsAddOn.setAttribute("Value",  addonCoverGroups);
			
			AddonCoversData.addContent(PremiumAddOn);
			AddonCoversData.addContent(RateAddOn);
			AddonCoversData.addContent(SumInsuredAddOn);
			AddonCoversData.addContent(ApplicableAddOn);
			AddonCoversData.addContent(AddonCoverGroupsAddOn);
			AddonCovers.addContent(AddonCoversData);
			
		}
		
		private void addDiscountCovers(org.jdom2.Element OtherDiscountGroup,String sumInsured,String applicable,String description){
			System.out.println("Discount sumInsured------------>>"+sumInsured);	
			System.out.println("Discount description------------>>"+description);	
			
			org.jdom2.Element OtherDiscountGroupData=new org.jdom2.Element("OtherDiscountGroupData");	
			OtherDiscountGroupData.setAttribute("Type", "GroupData");
			org.jdom2.Element DiscountAmountotherDisc = new org.jdom2.Element("DiscountAmount");	
			DiscountAmountotherDisc.setAttribute("Name", "Discount Amount");
			DiscountAmountotherDisc.setAttribute("Value", "");    
			org.jdom2.Element DiscountRateotherDisc = new org.jdom2.Element("DiscountRate");	
			DiscountRateotherDisc.setAttribute("Name", "Discount Rate");
			DiscountRateotherDisc.setAttribute("Value",  "");    
			org.jdom2.Element SumInsuredotherDisc = new org.jdom2.Element("SumInsured");	
			SumInsuredotherDisc.setAttribute("Name", "SumInsured");
			SumInsuredotherDisc.setAttribute("Value", sumInsured);    
			org.jdom2.Element RateotherDisc = new org.jdom2.Element("Rate");	
			RateotherDisc.setAttribute("Name", "Rate");
			RateotherDisc.setAttribute("Value",  "");    
			org.jdom2.Element PremiumotherDisc = new org.jdom2.Element("Premium");	
			PremiumotherDisc.setAttribute("Name", "Premium");
			PremiumotherDisc.setAttribute("Value", "");    
			org.jdom2.Element ApplicableotherDisc = new org.jdom2.Element("Applicable");	
			ApplicableotherDisc.setAttribute("Name", "Applicable");
			ApplicableotherDisc.setAttribute("Value", applicable);    
			org.jdom2.Element DescriptionotherDisc = new org.jdom2.Element("Description");	
			DescriptionotherDisc.setAttribute("Name", "Description");
			DescriptionotherDisc.setAttribute("Value",description);
			
			OtherDiscountGroupData.addContent(DiscountAmountotherDisc);
			OtherDiscountGroupData.addContent(DiscountRateotherDisc);
			OtherDiscountGroupData.addContent(SumInsuredotherDisc);
			OtherDiscountGroupData.addContent(RateotherDisc);
			OtherDiscountGroupData.addContent(PremiumotherDisc);
			OtherDiscountGroupData.addContent(ApplicableotherDisc);
			OtherDiscountGroupData.addContent(DescriptionotherDisc);
			OtherDiscountGroup.addContent(OtherDiscountGroupData);
			
		}
		

}
