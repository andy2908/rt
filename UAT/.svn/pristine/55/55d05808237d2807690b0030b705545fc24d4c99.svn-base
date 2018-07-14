package com.uat.hbc.insurance.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.Holder;

import org.apache.commons.lang3.SerializationUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.bajajAllianz.*;
import com.bajajAllianz.pdfService.ClientInfo;
import com.bajajAllianz.pdfService.WebServiceImplService;
import com.bajajAllianz.pdfService.WebServiceInterface;


import com.uat.hbc.insurance.dao.BajajAllianzDaoIntegration;
import com.uat.hbc.insurance.model.BajajPayBean;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;

import ch.qos.logback.core.net.SyslogOutputStream;


@Controller
public class BajajAllianzIntegrationController {
		
	@Autowired
	BajajAllianzDaoIntegration bajajDao;
	
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;
	
	String premium = "";
	HashMap inputParaList;
	WebServicePolicy serv;
	BajajPayBean payBn = new BajajPayBean();
	BigDecimal tid;
	WeoTyacPayRowWsUserArray wtprwua;
	WeoBjazMotQuestionaryUserArray wbmqua;
	WeoSigMotExtraCoversUser motExtraCvrUser;
	WeoMotAccessoriesUserArray motAccess;
	WeoMotGenParamUserArray genParUsr;
	WeoMotPlanDetailsUser plnDetUser;
	Holder<WeoTyacPayRowWsUserArray> pRcptListInout;
	Holder<WeoB2CCustDetailsUser> pCustDetailsInout;
	Holder<WeoSigMotExtraCoversUser> wsmecuh;
	Holder<WeoMotPremiumDetailsUser> premiumDetailsOutOut;
	Holder<WeoMotPremiumSummaryUserArray> premiumSummeryListOut;
	Holder<WeoMotPlanDetailsUser> motPol;
	Holder<WeoTygeErrorMessageUserArray> pErrorOut;
	Holder<WeoRecStrings40User> pPaymentTransObjInout;
	Holder<String> ppolicyrefOut;
	Holder<String> ppolicyissuedateOut;
	Holder<String> ppartIdOut;
	Holder<BigDecimal> pErrorCodeOut;
	BigDecimal ppremiumpayerid;
	String paymentmode;
	String polRef;
	String policyref;
	Holder<String> policyNo;
	Holder<BigDecimal> pTransactionIdInout;
	Holder<String> pErrorMsgOut;
	org.json.JSONObject obj;
	String paySts;
	
	@RequestMapping(value = "user/BajajAllianzIntegrationController", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public@ResponseBody String savedetails(HttpServletRequest request, HttpServletResponse reponse) throws ParserConfigurationException, SAXException, IOException, JSONException, ParseException {
		HashMap finalHashMap=null;
		plnDetUser = new WeoMotPlanDetailsUser();
		motAccess = new WeoMotAccessoriesUserArray();
		genParUsr = new WeoMotGenParamUserArray();
		WeoMotPremiumSummaryUserArray wmpsua = new WeoMotPremiumSummaryUserArray();
		WeoTygeErrorMessageUserArray wtemua = new WeoTygeErrorMessageUserArray();
		wtprwua = new WeoTyacPayRowWsUserArray();
		wbmqua = new WeoBjazMotQuestionaryUserArray();
		motExtraCvrUser = new WeoSigMotExtraCoversUser();
		
		tid = null;
		
		String request_for = request.getParameter("request_for");
//	    userId = request.getParameter("userId");
//	    System.out.println("userId = " + userId);
//		password = request.getParameter("password");
//		System.out.println("password = " + password);
//		String vehicleCode = request.getParameter("vehicleCode");
		/*int vehicleCode = 38162441;
		System.out.println("vehicleCode = " + vehicleCode);*/
		String city = request.getParameter("city");
		String procName = request.getParameter("procName");
		String tableName = request.getParameter("tableName");
		String zoneID = request.getParameter("zoneID");
		String policyType = request.getParameter("policyType");
		String varid = request.getParameter("varid");
		String businessType = request.getParameter("businessType");
		String productId = request.getParameter("productId");
		String vehId = request.getParameter("vehId");
		String modelID = request.getParameter("modelID");
		String customerType = request.getParameter("customerType");
		System.out.println("customerType = " + customerType);
		String prevGicId = request.getParameter("prevGicId");
		String financeId = request.getParameter("financeId");
		String nomRel = request.getParameter("nomRel");
		String prevProduct = request.getParameter("prevProduct");
		String prevPolicyType = request.getParameter("prevPolicyType");
		String finAgreementType = request.getParameter("finAgreementType");
		String bodyType = request.getParameter("bodyType");
		String presentAreaId = request.getParameter("presentAreaId");
		String permanentCarId = request.getParameter("permanentCarId");
		String PosPolicyNo = request.getParameter("PosPolicyNo");
		String salutationId = request.getParameter("salutationId");
		String covers = request.getParameter("covers");
		String covVal = request.getParameter("covVal");
		String covNo = request.getParameter("covNo");
		String motorGroupResponseGroupId = request.getParameter("motorGroupResponseGroupId");
		String motorGroupResponseSessionId = request.getParameter("motorGroupResponseSessionId");
		String motorGroupResponseGicId = request.getParameter("motorGroupResponseGicId");
		String motorGroupResponseGicName = request.getParameter("motorGroupResponseGicName");
		String userId = request.getParameter("userId");
		String userDesc = request.getParameter("userDesc");
		String branchId = request.getParameter("branchId");
		String IPAddress = request.getParameter("IPAddress");
		String engineNo = request.getParameter("engineNo");
		String chasisNo = request.getParameter("chasisNo");
		
		payBn.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
		payBn.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
		payBn.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
		payBn.setUserId(request.getParameter("userId"));
		payBn.setUserDesc(request.getParameter("userDesc"));
		payBn.setBranchId(request.getParameter("branchId"));
		payBn.setIPAddress(request.getParameter("IPAddress"));
		
		
		String regCode = request.getParameter("regCode");
		System.out.println("regCode = " + regCode);
		
		if(obj.getString("POLTYPE").equalsIgnoreCase("1"))
		{
			regCode = "";
			
		}
		
		String regDt = request.getParameter("regDt");
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
        Date regdate = format1.parse(regDt);
        String regDate = format2.format(regdate);
        System.out.println(format2.format(regdate));
		inputParaList = new HashMap<>();
		
		String idv = request.getParameter("vehIdv");
//		String idv = "";
		System.out.println("idv = " + idv);
		String ncb = request.getParameter("NCBPercentage");
		System.out.println("ncb = " + ncb);
		String mfrYear = request.getParameter("mfrYear");
		String prevPolExpDt = request.getParameter("prevPolExpDt");
		
//		String prevPolExpDt = "3-Apr-2018";
		
//		String prevInsCmpny = request.getParameter("prevInsCmpny");
		System.out.println("prevPolExpDt = " + prevPolExpDt);
		String prevNcb = request.getParameter("prevNcb");
		System.out.println("prevNcb = " + prevNcb);
		if (prevNcb == "") {
			prevNcb = "0";
		}
		System.out.println("prevNcb = " + prevNcb);
		String prvClaimSts = request.getParameter("prvClaimSts");
		System.out.println("prvClaimSts = " + prvClaimSts);
		
		inputParaList.put("PI_TABLE_NAME", tableName);
		System.out.println("tableName--->>> " + tableName);

		System.out.println("ProcName--->>> " + procName);

		System.out.println("rtoCity-->>>" + city);
		inputParaList.put("PI_Rto_City", Integer.parseInt(city));
		System.out.println("rtoCity-->>>" + city);

		inputParaList.put("PI_ZONE_ID", Integer.parseInt(zoneID));
		System.out.println("ZoneId-->>>" + zoneID);

		inputParaList.put("PI_PolicyType", Integer.parseInt(policyType));
		System.out.println("policyType--->>>> " + policyType);

		inputParaList.put("PI_VarID", Integer.parseInt(varid));
		System.out.println("variance--->>>> " + varid);

		inputParaList.put("PI_P_BusinessType", Integer.parseInt(businessType));
		System.out.println("businessType--->>>> " + businessType);

		inputParaList.put("PI_ProductID", Integer.parseInt(productId));
		System.out.println("PI_ProductID--->>>> " + productId);

		inputParaList.put("PI_VehID", Integer.parseInt(vehId));
		System.out.println("PI_VehID--->>>> " + vehId);

		inputParaList.put("PI_ModelID", Integer.parseInt(modelID));
		System.out.println("modelID--->>>> " + modelID);

		inputParaList.put("PI_CUST_TYPE", Integer.parseInt(customerType));
		System.out.println("customerType--->>>> " + customerType);

		inputParaList.put("PI_FIN_ID", financeId);
		System.out.println("financeId--->>>> " + financeId);

		inputParaList.put("PI_PREV_GIC_ID", prevGicId);
		System.out.println("prevGicId--->>>> " + prevGicId);
		
		inputParaList.put("PI_Nom_Rel", nomRel);
		System.out.println("PI_Nom_Rel--->>>> " + nomRel);

		inputParaList.put("PI_Prev_Product", prevProduct);
		System.out.println("PI_Prev_Product--->>>> " + prevProduct);
		
		inputParaList.put("PI_Prev_PolicyType", prevPolicyType);
		System.out.println("PI_Prev_PolicyType--->>>> " + prevPolicyType);
		
		inputParaList.put("PI_FIN_AgreementType", finAgreementType);
		System.out.println("PI_FIN_AgreementType--->>>> " + finAgreementType);
		
		inputParaList.put("PI_Bodytype", bodyType);
		System.out.println("PI_Bodytype--->>>> " + bodyType);
		
		inputParaList.put("PI_Present_AreaID", presentAreaId);
		System.out.println("PI_Present_AreaID--->>>> " + presentAreaId);
		
		inputParaList.put("PI_Permanent_AreaID", permanentCarId);
		System.out.println("PI_Permanent_AreaID--->>>> " + permanentCarId);
		
		inputParaList.put("PI_posPolicyNo", PosPolicyNo);
		System.out.println("PI_posPolicyNo--->>>> " + PosPolicyNo);
		
		inputParaList.put("PI_SalutaionID", salutationId);
		System.out.println("PI_SalutaionID--->>>> " + salutationId);
		
		inputParaList.put("PI_Covers", covers);
		System.out.println("PI_Covers--->>>> " + covers);

		inputParaList.put("PI_COV_VAL", covVal);
		System.out.println("PI_COV_VAL--->>>> " + covVal);
		
		inputParaList.put("PI_COV_NO", covNo);
		System.out.println("PI_COV_NO--->>>> " + covNo);
		System.out.println("inputParaList>>" + inputParaList);
		
		String procData = bajajDao.getBajajData("PKG_MOTOR_CALC", procName, inputParaList);
		
		System.out.println("procData = " + procData);
		
		String jsonNamesArr[] = procData.split("\\}\\]\\[\\{");

		procData = jsonNamesArr[0];
		String coverList = "";
		if (jsonNamesArr.length > 1) {
			coverList = jsonNamesArr[1];
			coverList = "[{" + coverList;
			procData = procData + "}]";
		}
		
		String depriciationCover = "No", liabilityToPaidDriver = "No", NoPassToLLPaiddriver = "0", paUnnamed = "No",
				noOfPerunnamed = "0", unnamedPaSi = "0", tppdDiscount = "No", antiTheftdevice = "No",
				legalliabilityToEmployee = "No", NoOfPassengerForLLToEmployee = "0", roadSideAsstCover = "No",
				fiberGlassTankCover = "No", fiberGlassSI = "", drivingtution = "No", AAIMembship = "No",
				limitedToOwnPremise = "No", vehicleForHandicap = "No", elec = "0", nonElec = "0", geoExt = "", sideCar = "0", volExc = "0";

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

				if (coverId.equals("9")) {
					 System.out.println("cover 9------------>>"+coverId);
					// depriciationCover="Yes";
//					IsPACoverForUnnamedPersons = coverStatus;
//					NumberofPersonsUnnamed = coverNo;
					elec = coverVal;
//					zeroDepreciation = coverStatus;
				}
				//
				if (coverId.equals("2")) {
					 System.out.println("cover 2------------>>"+coverId);
					// liabilityToPaidDriver="Yes";
					// NoPassToLLPaiddriver=coverNo;
//					IsPACoverForUnnamedPersons = coverStatus;
//					NumberofPersonsUnnamed = coverNo;
					nonElec = coverVal;
				}
				//
				if (coverId.equals("23")) {
					System.out.println("cover 23------------>>" + coverId);
//					IsPACoverForUnnamedPersons = coverStatus;
//					NumberofPersonsUnnamed = coverNo;
					geoExt = coverVal;
				}
				//
				if (coverId.equals("30")) {
					 System.out.println("cover 30------------>>"+coverId);
					// tppdDiscount="Yes";
//					IsPACoverForUnnamedPersons = coverStatus;
//					NumberofPersonsUnnamed = coverNo;
					sideCar = coverVal;
//					pojo.setTrailer("0");
				}
//				pojo.setTrailer("0");
				//
				if (coverId.equals("104")) {
					System.out.println("cover 104------------>>" + coverId);
					volExc = coverVal;
//					antiTheftdevice = "Yes";
//					 str_IsAntiTheftDeviceCertifiedByARAI="True";
//					pojo.setCngLpgKit("0");
				}
//				pojo.setCngLpgKit("0");
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
//				if (coverId.equals("9")) {
//					// drivingtution="Yes";
//					EAR = coverStatus;
//					EARSumInsured = coverVal;
//					pojo.setElecAccsrFitd(coverVal);
//				}
//				if (coverId.equals("104")) {
//					// AAIMembship="Yes";
//					VoluntaryDeductibleAmount = coverVal;
//				}
//				if (coverId.equals("18")) {
//					// limitedToOwnPremise="Yes";
//					IsPACoverForUnnamedPersons = coverStatus;
//					NumberofPersonsUnnamed = coverNo;
//					CapitalSumInsuredPerPersonUnnamed = coverVal;
//				}
//				if (coverId.equals("2")) {
//					// vehicleForHandicap="Yes";
//					near = coverStatus;
//					NEARSumInsured = coverVal;
//					pojo.setNeAccsrsFitd(coverVal);
//				}
			}
		}
		
		System.out.println("jsonName controller::: " + procData);
		System.out.println("coverList Controller::: " + coverList);
		
//		if (procData.contains("}][{")) {
//			procData = procData.replace("}][{", ",");
//		}
//		if (procData.contains("[") || procData.contains("]")) {
//			procData = procData.replace("[", "");
//			procData = procData.replace("]", "");
//		}

		System.out.println("uuuuuuuuuuuuuu procData uuuuuuuuuuuuu-->>>>" + procData);
		
		try {
			JSONArray json = new JSONArray(procData);
			obj = (org.json.JSONObject)json.get(0);
			System.out.println("obj = " + obj.toString());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		premiumSummeryListOut = new Holder<WeoMotPremiumSummaryUserArray>();
		premiumDetailsOutOut = new Holder<WeoMotPremiumDetailsUser>();
		wsmecuh = new Holder<WeoSigMotExtraCoversUser>();
		pCustDetailsInout = new Holder<WeoB2CCustDetailsUser>();
		pRcptListInout = new Holder<WeoTyacPayRowWsUserArray>();
		motPol = new Holder<WeoMotPlanDetailsUser>();
		Holder<WeoMotAccessoriesUserArray> accesrs = new Holder<WeoMotAccessoriesUserArray>();
		Holder<WeoMotGenParamUserArray> genprm = new Holder<WeoMotGenParamUserArray>();
		Holder<WeoBjazMotQuestionaryUserArray> pQuestListInout = new Holder<WeoBjazMotQuestionaryUserArray>();
		Holder<WeoMotDetariffObjUser> pDetariffObjInout = new Holder<WeoMotDetariffObjUser>();
		pErrorCodeOut = new Holder<BigDecimal>();
		pErrorCodeOut.value = new BigDecimal(0);
		pErrorOut = new Holder<WeoTygeErrorMessageUserArray>();
		pTransactionIdInout = new Holder<BigDecimal>();
		ppolicyrefOut = new Holder<String>("");
		ppolicyissuedateOut = new Holder<String>("");
		ppartIdOut = new Holder<String>("");
		pTransactionIdInout.value = new BigDecimal(0);
		String pTransactionType = "MOTOR_WEBSERVICE";
		String pContactNo = "9874563210";
		ppremiumpayerid = new BigDecimal(0);
		paymentmode = "CC";
		
//		payBn = new BajajPayBean();
		
		String pass = obj.getString("PASSWORD");
		System.out.println("pass = " + pass);
		payBn.setPassword(pass);
		System.out.println("Bnpass= " + payBn.getPassword());
		payBn.setUserId( obj.getString("PUSERID"));
		System.out.println("Bnpass= " + payBn.getUserId());
		String prevInsCmpny = obj.getString("PRVINSCOMPANY");
		
//		if (prevInsCmpny == null) {
			prevInsCmpny = "0";
//			System.out.println("prevInsCmpny1 =  "  + prevInsCmpny);
//		}
		System.out.println("prevInsCmpny2 =  "  + prevInsCmpny);
		
		int cntrctId = obj.getInt("CONTRACTID");
		String polTyp = obj.getString("POLTYPE");
		System.out.println("polTyp = " + polTyp);
		int dgtCode = obj.getInt("PRODUCT4DIGITCODE");
		int deptCode = obj.getInt("DEPTCODE");
		int brnchCode = obj.getInt("BRANCHCODE");
		String strtDt = obj.getString("TERMSTARTDATE");
		String endDt = obj.getString("TERMENDDATE");
		String tpFinTyp = obj.getString("TPFINTYPE");
		int vhTypCod = obj.getInt("VEHICLETYPECODE");
		String vhType = obj.getString("VEHICLETYPE");
		int mscVhCod = obj.getInt("MISCVEHTYPE");
		int vhMkCd = obj.getInt("VEHICLEMAKECODE"); 
		String vhMk = obj.getString("VEHICLEMAKE");
		int vhMdlCd = obj.getInt("VEHICLEMODELCODE");
		String vhMdl = obj.getString("VEHICLEMODEL");
		int vhSbTYpCd = obj.getInt("VEHICLESUBTYPECODE");
		String vhSbTYp = obj.getString("VEHICLESUBTYPE");
		String fuel = obj.getString("FUEL");
		String rtoZone = obj.getString("RTOZONE");
		String regLoc = obj.getString("REGISTRATIONLOCATION");
		String regLocO = obj.getString("REGILOCOTHER");
		int carCap = obj.getInt("CARRYINGCAPACITY");
		int cubCap = obj.getInt("CUBICCAPACITY");
		int vehicleCode = obj.getInt("PVEHICLECODE");
		String pcity = obj.getString("PCITY");
		
		BigDecimal ctrctID = new BigDecimal(cntrctId);
		System.out.println("ctrctID = " + ctrctID);
		BigDecimal didgitCd = new BigDecimal(dgtCode);
		System.out.println("didgitCd = " + didgitCd);
		BigDecimal dptCd = new BigDecimal(deptCode);
		System.out.println("dptCd = " + dptCd);
		BigDecimal brCd = new BigDecimal(brnchCode);
		System.out.println("brCd = " + brCd);
		BigDecimal vhCd = new BigDecimal(vhTypCod);
		System.out.println("vhCd = " + vhCd);
		BigDecimal mscCd = new BigDecimal(mscVhCod);
		System.out.println("mscCd = " + mscCd);
		BigDecimal vhmkcd = new BigDecimal(vhMkCd);
		System.out.println("vhmkcd = " + vhmkcd);
		BigDecimal vhmdcd = new BigDecimal(vhMdlCd);
		System.out.println("vhmdcd = " + vhmdcd);
		BigDecimal vhstcd = new BigDecimal(vhSbTYpCd);
		System.out.println("vhstcd = " + vhstcd);
		BigDecimal carC = new BigDecimal(carCap);
		System.out.println("carC = " + carC);
		BigDecimal cubC = new BigDecimal(cubCap);
		System.out.println("cubC = " + cubC);
		BigDecimal vidv = new BigDecimal(idv);
		System.out.println("vidv = " + vidv);
		BigDecimal ncbp = new BigDecimal(ncb);
		System.out.println("ncbp = " + ncbp);
		BigDecimal preInsCm = new BigDecimal(prevInsCmpny);
		System.out.println("preInsCm = " + preInsCm);
//		BigDecimal prevN = new BigDecimal(prevNcb);
//		System.out.println("prevN = " + prevN);
		
		
		plnDetUser.setContractId(ctrctID);
//		plnDetUser.setPolType(polTyp);
		plnDetUser.setProduct4DigitCode(didgitCd);
		plnDetUser.setDeptCode(dptCd);
		plnDetUser.setBranchCode(brCd);
		plnDetUser.setTermStartDate(strtDt);
		plnDetUser.setTermEndDate(endDt);
//		plnDetUser.setTpFinType(new BigDecimal(tpFinTyp));
//		plnDetUser.setHypo("State Bank of India");
		plnDetUser.setVehicleTypeCode(vhCd);
		plnDetUser.setVehicleType(vhType);
		plnDetUser.setMiscVehType(mscCd);
		plnDetUser.setVehicleMakeCode(vhmkcd);
		plnDetUser.setVehicleMake(vhMk);
		plnDetUser.setVehicleModelCode(vhmdcd);
		plnDetUser.setVehicleModel(vhMdl);
		plnDetUser.setVehicleSubtypeCode(vhstcd);
		plnDetUser.setVehicleSubtype(vhSbTYp);
		plnDetUser.setFuel(fuel);
		plnDetUser.setZone(rtoZone);
		plnDetUser.setEngineNo("FGDFG4654");
		plnDetUser.setChassisNo("FGDFG4654");
		plnDetUser.setRegistrationNo(regCode);
		plnDetUser.setRegistrationDate(regDate);
		plnDetUser.setRegistrationLocation(regLoc);
		plnDetUser.setRegiLocOther(regLocO);
		plnDetUser.setCarryingCapacity(carC);
		plnDetUser.setCubicCapacity(cubC);
		plnDetUser.setYearManf(mfrYear);
		plnDetUser.setVehicleIdv(vidv);
		plnDetUser.setNcb(ncbp);
//		plnDetUser.setAddLoading(new BigDecimal(0));
//		plnDetUser.setAddLoadingOn("0");
//		plnDetUser.setSpDiscRate(new BigDecimal(0));
		plnDetUser.setElecAccTotal(new BigDecimal(elec)); 
		plnDetUser.setNonElecAccTotal(new BigDecimal(nonElec));
//		plnDetUser.setPrvPolicyRef("123123123");
//		plnDetUser.setPrvExpiryDate("3-Apr-2018");
		plnDetUser.setPrvInsCompany(preInsCm);
//		plnDetUser.setPrvNcb(prevN);
		plnDetUser.setPrvClaimStatus(prvClaimSts);
//		plnDetUser.setAutoMembership("");
//		plnDetUser.setPartnerType("P");
//		plnDetUser.setColor("");
		motPol.value = plnDetUser;

		WeoMotAccessoriesUser wmau = new WeoMotAccessoriesUser();
		wmau.setAccCategoryCode(new BigDecimal(1));
		wmau.setAccCount(new BigDecimal(1));
		wmau.setAccIev(new BigDecimal(1));
		wmau.setAccMake("1");
		wmau.setAccModel("1");
		wmau.setAccTypeCode(new BigDecimal(1));
		wmau.setContractId(new BigDecimal(0));

		ArrayList<WeoMotAccessoriesUser> wmaul = new ArrayList<WeoMotAccessoriesUser>();
		wmaul.add(wmau);
		motAccess.setWeoMotAccessoriesUser(wmaul);
		accesrs.value = motAccess;
		
		WeoMotGenParamUser wmgp = new WeoMotGenParamUser();
		wmgp.setParamDesc("PA");
		wmgp.setParamRef("PA");

		ArrayList<WeoMotGenParamUser> wmgpl = new ArrayList<WeoMotGenParamUser>();
		wmgpl.add(wmgp);
		genParUsr.setWeoMotGenParamUser(wmgpl);
		genprm.value = genParUsr;
		
		motExtraCvrUser.setCngValue(new BigDecimal(0));
		motExtraCvrUser.setCovernoteDate("");
		motExtraCvrUser.setCovernoteNo("");
		motExtraCvrUser.setExtraField1("");
		motExtraCvrUser.setExtraField2("");
		motExtraCvrUser.setExtraField3("");
		motExtraCvrUser.setFibreGlassValue(new BigDecimal(0));
		motExtraCvrUser.setGeogExtn(geoExt);
		motExtraCvrUser.setNoOfEmployeesLle(new BigDecimal(0));
		motExtraCvrUser.setNoOfPersonsLlo(new BigDecimal(0));
		motExtraCvrUser.setNoOfPersonsPa(new BigDecimal(5));
		motExtraCvrUser.setNoOfTrailers(new BigDecimal(0));
		motExtraCvrUser.setSideCarValue(new BigDecimal(sideCar));
		motExtraCvrUser.setSubImdcode("");
		motExtraCvrUser.setSumInsuredPa(new BigDecimal(0));
		motExtraCvrUser.setSumInsuredTotalNamedPa(new BigDecimal(100000));
		motExtraCvrUser.setTotalTrailerValue(new BigDecimal(0));
		motExtraCvrUser.setVoluntaryExcess(new BigDecimal(volExc));
		
		WeoBjazMotQuestionaryUser wbmqu = new WeoBjazMotQuestionaryUser();
		wbmqu.setContractId("");
		wbmqu.setQuestionRef("0");
		wbmqu.setQuestionVal("0");
		
		ArrayList<WeoBjazMotQuestionaryUser> wbmqul = new ArrayList<WeoBjazMotQuestionaryUser>();
		wbmqul.add(wbmqu);
		wbmqua.setWeoBjazMotQuestionaryUser(wbmqul);
		pQuestListInout.value = wbmqua;
		
		WeoMotDetariffObjUser wmdou = new WeoMotDetariffObjUser();
			
		pDetariffObjInout.value = wmdou;
		
		WeoMotPremiumDetailsUser wmpdu = new WeoMotPremiumDetailsUser();
		wmpdu.setAddLoadPrem(new BigDecimal(0));
		wmpdu.setCollPremium(new BigDecimal(0));
		wmpdu.setFinalPremium(new BigDecimal(0));
		wmpdu.setImtOut("");
		wmpdu.setNcbAmt(new BigDecimal(0));
		wmpdu.setNetPremium(new BigDecimal(0));
		wmpdu.setServiceTax(new BigDecimal(0));
		wmpdu.setSpDisc(new BigDecimal(0));
		wmpdu.setStampDuty(new BigDecimal(0));
		wmpdu.setTotalActPremium(new BigDecimal(0));
		wmpdu.setTotalIev(new BigDecimal(0));
		wmpdu.setTotalNetPremium(new BigDecimal(0));
		wmpdu.setTotalOdPremium(new BigDecimal(0));
		wmpdu.setTotalPremium(new BigDecimal(0));
		
		premiumDetailsOutOut.value = wmpdu;
		
		WeoMotPremiumSummaryUser wmpsu = new WeoMotPremiumSummaryUser();
		wmpsu.setAct(new BigDecimal(0));
		wmpsu.setNet(new BigDecimal(0));
		wmpsu.setOd(new BigDecimal(0));
		wmpsu.setParamDesc("PA");
		wmpsu.setParamRef("PA");
		wmpsu.setParamType(new BigDecimal(1));
		
		ArrayList<WeoMotPremiumSummaryUser> wmpsual = new ArrayList<WeoMotPremiumSummaryUser>();
		wmpsual.add(wmpsu);
		wmpsua.setWeoMotPremiumSummaryUser(wmpsual);
		premiumSummeryListOut.value = wmpsua;
		
		WeoTygeErrorMessageUser wtemu = new WeoTygeErrorMessageUser();
		wtemu.setErrLevel(new BigDecimal(0));
		wtemu.setErrNumber(new BigDecimal(0));
		wtemu.setErrText("");
		wtemu.setParIndex(new BigDecimal(0));
		wtemu.setParName("");
		wtemu.setProperty("");
		
		ArrayList<WeoTygeErrorMessageUser> wtemul = new ArrayList<WeoTygeErrorMessageUser>();
		wtemul.add(wtemu);
		wtemua.setWeoTygeErrorMessageUser(wtemul);
		pErrorOut.value = wtemua;
 		
		WebServicePolicy_Service service = new WebServicePolicy_Service();
	    serv = service.getWebServicePolicyPort();
		
		switch (request_for) {
		case "NEW":
			
			System.out.println("NEW");
			
		serv.calculateMotorPremiumSig(payBn.getUserId(), payBn.getPassword(), new BigDecimal(vehicleCode), pcity, motPol, accesrs, genprm, motExtraCvrUser, pQuestListInout, pDetariffObjInout, premiumDetailsOutOut, premiumSummeryListOut, pErrorOut, pErrorCodeOut, pTransactionIdInout, pTransactionType, pContactNo);
		
		List<WeoTygeErrorMessageUser> i = pErrorOut.value.getWeoTygeErrorMessageUser();
		
		try{
			System.out.println("err Text = " + i.get(0).getErrText());
		}catch (IndexOutOfBoundsException e) {
//			e.printStackTrace();
			System.out.println("No Error");
		}

		BigDecimal bds = pErrorCodeOut.value;
		System.out.println("err Code = " + bds);
		
	    tid = pTransactionIdInout.value;
		System.out.println("transcId = " + tid);  
				
		if (procData.contains("}][{")) {
			procData = procData.replace("}][{", ",");
		}
		if (procData.contains("[{") || procData.contains("}]")) {
			procData = procData.replace("[", "");
			procData = procData.replace("]", "");
		} 
		
		JSONObject json = new JSONObject();
		json.put("netprem", premiumDetailsOutOut.value.getTotalPremium());
		json.put("totalnetprem", premiumDetailsOutOut.value.getFinalPremium());
		json.put("serviceTax", premiumDetailsOutOut.value.getServiceTax());
		json.put("ErrorText", pErrorCodeOut.value);
		
		premium =  procData + json;
		
		if (premium.contains("}{")) {
			premium = premium.replace("}{", ",");
		}
		
		System.out.println("premium = " + premium);
		
		String collPrem = String.valueOf(premiumDetailsOutOut.value.getCollPremium());
		String fPrem = String.valueOf(premiumDetailsOutOut.value.getFinalPremium());
		String ncbAmt = String.valueOf(premiumDetailsOutOut.value.getNcbAmt());
		String netPrem = String.valueOf(premiumDetailsOutOut.value.getNetPremium());
		String servTx = String.valueOf(premiumDetailsOutOut.value.getServiceTax());
		String spDisc = String.valueOf(premiumDetailsOutOut.value.getSpDisc());
		String stDuty = String.valueOf(premiumDetailsOutOut.value.getStampDuty());
		String ttlActPrem = String.valueOf(premiumDetailsOutOut.value.getTotalActPremium());
		String ttlIev = String.valueOf(premiumDetailsOutOut.value.getTotalIev());
		String ttlNetPrem = String.valueOf(premiumDetailsOutOut.value.getTotalNetPremium());
		String ttlOdPrem = String.valueOf(premiumDetailsOutOut.value.getTotalOdPremium());
		String ttlPrem = String.valueOf(premiumDetailsOutOut.value.getTotalPremium());
		String imtOut = String.valueOf(premiumDetailsOutOut.value.getImtOut());
		String addLdPrem = String.valueOf(premiumDetailsOutOut.value.getAddLoadPrem());
		
		List<WeoMotPremiumSummaryUser> premSumry = premiumSummeryListOut.value.getWeoMotPremiumSummaryUser();
		
		WeoMotPremiumSummaryUser premA = premSumry.get(0);
		WeoMotPremiumSummaryUser premB = premSumry.get(1);
		WeoMotPremiumSummaryUser premC = premSumry.get(2);
		WeoMotPremiumSummaryUser premD = premSumry.get(3);
		
		String bod = premA.getParamDesc();
		String bodRef = premA.getParamRef();
		String bodAct = String.valueOf(premA.getAct());
		String bodNet = String.valueOf(premA.getNet());
		String bodOd = String.valueOf(premA.getOd());
		String bodType = String.valueOf(premA.getParamType());
		
		String btpl = premB.getParamDesc();
		String btplRef = premB.getParamRef();
		String btplAct = String.valueOf(premB.getAct());
		String btplNet = String.valueOf(premB.getNet());
		String btplOd = String.valueOf(premB.getOd());
		String btplType = String.valueOf(premB.getParamType());
		
		String PaCvrOwrDrvr = premC.getParamDesc();
		String PaRef = premC.getParamRef();
		String PaAct = String.valueOf(premC.getAct());
		String PaNet = String.valueOf(premC.getNet());
		String PaOd = String.valueOf(premC.getOd());
		String PaType = String.valueOf(premC.getParamType());
		
		String PaUnmdPsngr = premD.getParamDesc();
		String PaUnmdRef = premD.getParamRef();
		String PaUnmdAct = String.valueOf(premD.getAct());
		String PaUnmdNet = String.valueOf(premD.getNet());
		String PaUnmdOd = String.valueOf(premD.getOd());
		String PaUnmdType = String.valueOf(premD.getParamType());
		
//		for (WeoMotPremiumSummaryUser premSm : premSumry) {
////			if (premSm.getParamDesc() == "Basic Own Damage") {
////				bod = String.valueOf(premSm.getParamDesc());
////				System.out.println("bod = " + bod);
////			}
////			System.out.println("for each paramDesc = " + premSm.getParamDesc());
////			System.out.println("for each paramRef = " + premSm.getParamRef());
////			System.out.println("for each paramAct = " + premSm.getAct());
////			System.out.println("for each Net = " + premSm.getNet());
////			System.out.println("for each OD = " + premSm.getOd());
////			System.out.println("for each paramType = " + premSm.getParamType());
//		}
		
		HashMap<String, String> hashmapBajaj = new HashMap<String, String>();
		hashmapBajaj.put("Premium", collPrem);
		hashmapBajaj.put("FinalPremium", fPrem);
		hashmapBajaj.put("NcbAmt", ncbAmt);
		hashmapBajaj.put("NetPrem", netPrem);
		hashmapBajaj.put("ServiceTax", servTx);
		hashmapBajaj.put("SpDisc", spDisc);
		hashmapBajaj.put("StampDuty", stDuty);
		hashmapBajaj.put("TtlActPrem", ttlActPrem);
		hashmapBajaj.put("TtlIev", ttlIev);
		hashmapBajaj.put("TtlNetPrem", ttlNetPrem);
		hashmapBajaj.put("TtlOdPrem", ttlOdPrem);
		hashmapBajaj.put("TtlPrem", ttlPrem);
		hashmapBajaj.put("ImtOut", imtOut);
		hashmapBajaj.put("AddLdPrem", addLdPrem);
		hashmapBajaj.put("BasicOwnDmg", bod);
		hashmapBajaj.put("BodRef", bodRef);
		hashmapBajaj.put("BodAct", bodAct);
		hashmapBajaj.put("BodNet", bodNet);
		hashmapBajaj.put("BodOD", bodOd);
		hashmapBajaj.put("BodType", bodType);
		hashmapBajaj.put("BscTPLib", btpl);
		hashmapBajaj.put("BscTPRef", btplRef);
		hashmapBajaj.put("BscTPAct", btplAct);
		hashmapBajaj.put("BscTPNet", btplNet);
		hashmapBajaj.put("BscTPOD", btplOd);
		hashmapBajaj.put("BscTPType", btplType);
		hashmapBajaj.put("PaCvrOwrDrvr", PaCvrOwrDrvr);
		hashmapBajaj.put("PaRef", PaRef);
		hashmapBajaj.put("PaAct", PaAct);
		hashmapBajaj.put("PaNet", PaNet);
		hashmapBajaj.put("PaOd", PaOd);
		hashmapBajaj.put("PaType", PaType);
		hashmapBajaj.put("PaUnmdPsngr", PaUnmdPsngr);
		hashmapBajaj.put("PaUnmdRef", PaUnmdRef);
		hashmapBajaj.put("PaUnmdAct", PaUnmdAct);
		hashmapBajaj.put("PaUnmdNet", PaUnmdNet);
		hashmapBajaj.put("PaUnmdOd", PaUnmdOd);
		hashmapBajaj.put("PaUnmdType", PaUnmdType);
		hashmapBajaj.put("TranscId", String.valueOf(tid));
		
		 String procedureName = "PR_PREMIUM";
		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
		 System.out.println("mtrGrpRespGrpId = " + mtrGrpRespGrpId);
		 Integer mtrGrpRespGicId = new Integer(Integer.parseInt(motorGroupResponseGicId));
		 System.out.println("mtrGrpRespGicId = " + mtrGrpRespGicId);
		 System.out.println("motorGroupResponseSessionId = " + motorGroupResponseSessionId);
		 System.out.println("IPAddress = " + IPAddress);
		 System.out.println("userId = " + userId);
		 System.out.println("branchId = " + branchId);
		 System.out.println("userDesc = " + userDesc);
		 Integer value = new Integer(1);
		 String er = String.valueOf(bds);
		 int err = Integer.parseInt(er);
		 if (err == 0) {
			 System.out.println("Saving to db");
			 integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(mtrGrpRespGrpId, mtrGrpRespGicId, motorGroupResponseSessionId, value, hashmapBajaj, IPAddress, userId, branchId, userDesc, procedureName);
			 System.out.println("Saved");
		 }
		
		
			premium = premium.substring(1, premium.length()-1);           //remove curly brackets
			System.out.println("jsonNames:FDF" + premium);
			String[] keyValuePairs1 = premium.split(",");              //split the string to creat key-value pairs
			System.out.println("keyValuePairs31::" + keyValuePairs1);
			System.out.println("keyValuePairs1:::" + keyValuePairs1.length);
			finalHashMap = new HashMap();
			for(String pair : keyValuePairs1)                        //iterate over the pairs
			{
			    String[] entry = pair.split(":");                   //split the pairs to get key and value 
			    finalHashMap.put((entry[0].trim()).replaceAll("\"", ""), (entry[1].trim()).replaceAll("\"", ""));          //add them to the hashmap and trim whitespaces
			}
			
			finalHashMap.put("ErrorMessages", bds);
			finalHashMap.put("ErrorText", err);
			
			premium = "[" +premium+ "]";
			System.out.println("premium = " + premium);
		 
		break; 
		
		case "RENEWAL": 
		
			System.out.println("RENEWAL");
			
			wsmecuh.value = motExtraCvrUser;

			WeoB2CCustDetailsUser wb2cdu = new WeoB2CCustDetailsUser();

			pCustDetailsInout.value = wb2cdu;

			plnDetUser.setPrvPolicyRef(request.getParameter("prevPolNo"));
			
			motPol.value = plnDetUser;
			
			serv.getRnwData(payBn.getUserId(), payBn.getPassword(), motPol, wsmecuh, pCustDetailsInout, pErrorOut, pErrorCodeOut);
			
			List<WeoTygeErrorMessageUser> i2 = pErrorOut.value.getWeoTygeErrorMessageUser();
			
			try{
				System.out.println("i2 = " + i2.get(0).getErrText());
			}catch (IndexOutOfBoundsException e) {
				System.out.println("No Error (RENEWAL)");
			}

			BigDecimal bds2 = pErrorCodeOut.value;
			System.out.println("bds2 = " + bds2);
			
			JSONObject jsonR = new JSONObject();
			jsonR.put("TERMSTARTDATE", motPol.value.getTermStartDate());
			jsonR.put("TERMENDDATE", motPol.value.getTermEndDate());
			jsonR.put("ENGNO", motPol.value.getEngineNo());
			jsonR.put("CHSNO", motPol.value.getChassisNo());
			jsonR.put("ErrorText", pErrorCodeOut.value);
			premium = jsonR.toString();
			
			System.out.println("premium = " + premium);
			String p4dc = String.valueOf(motPol.value.getProduct4DigitCode());
			String mvt = String.valueOf(motPol.value.getMiscVehType());
			String vehIdv = String.valueOf(motPol.value.getVehicleIdv());
			String vehmkcd = String.valueOf(motPol.value.getVehicleMakeCode());
			String vehMdCd = String.valueOf(motPol.value.getVehicleModelCode());
			String vstc = String.valueOf(motPol.value.getVehicleSubtypeCode());
			String vtc = String.valueOf(motPol.value.getVehicleTypeCode());
			String addl = String.valueOf(motPol.value.getAddLoading());
			String brnchCd = String.valueOf(motPol.value.getBranchCode());
			String crCap = String.valueOf(motPol.value.getCarryingCapacity());
			String dptcd = String.valueOf(motPol.value.getDeptCode());
			String eattl = String.valueOf(motPol.value.getElecAccTotal());
			String nc = String.valueOf(motPol.value.getNcb());
			String neattl = String.valueOf(motPol.value.getNonElecAccTotal());
			String pic = String.valueOf(motPol.value.getPrvInsCompany());
			String pncb = String.valueOf(motPol.value.getPrvNcb());
			String sdr = String.valueOf(motPol.value.getSpDiscRate());
			String tfn = String.valueOf(motPol.value.getTpFinType());
			
			HashMap<String, String> hashmapBajajRnw = new HashMap<String, String>();
			hashmapBajajRnw.put("VehicleType", motPol.value.getVehicleType());
			hashmapBajajRnw.put("Prdct4DigitCode", p4dc);
			hashmapBajajRnw.put("TermStrtDt", motPol.value.getTermStartDate());
			hashmapBajajRnw.put("TermEndDt", motPol.value.getTermEndDate());
			hashmapBajajRnw.put("EngnNo", motPol.value.getEngineNo());
			hashmapBajajRnw.put("ChassisNo", motPol.value.getChassisNo());
			hashmapBajajRnw.put("RegNo", motPol.value.getRegistrationNo());
			hashmapBajajRnw.put("PrvPolicyRef", motPol.value.getPrvPolicyRef());
			hashmapBajajRnw.put("AddLoadinOn", motPol.value.getAddLoadingOn());
			hashmapBajajRnw.put("AutoMembership", motPol.value.getAutoMembership());
			hashmapBajajRnw.put("Color", motPol.value.getColor());
			hashmapBajajRnw.put("Fuel", motPol.value.getFuel());
			hashmapBajajRnw.put("Hypo", motPol.value.getHypo());
			hashmapBajajRnw.put("PartnerType", motPol.value.getPartnerType());
			hashmapBajajRnw.put("PolicyType", motPol.value.getPolType());
			hashmapBajajRnw.put("PrvClaimStatus", motPol.value.getPrvClaimStatus());
			hashmapBajajRnw.put("PrvExpDt", motPol.value.getPrvExpiryDate());
			hashmapBajajRnw.put("RegiLocOther", motPol.value.getRegiLocOther());
			hashmapBajajRnw.put("RegDt", motPol.value.getRegistrationDate());
			hashmapBajajRnw.put("RegLoc", motPol.value.getRegistrationLocation());
			hashmapBajajRnw.put("VehMk", motPol.value.getVehicleMake());
			hashmapBajajRnw.put("VehMdl", motPol.value.getVehicleModel());
			hashmapBajajRnw.put("VehSubType", motPol.value.getVehicleSubtype());
			hashmapBajajRnw.put("MiscVehType", mvt);
			hashmapBajajRnw.put("VehIdv", vehIdv);
			hashmapBajajRnw.put("VehMkCode", vehmkcd);
			hashmapBajajRnw.put("VehModlCode", vehMdCd);
			hashmapBajajRnw.put("VehSbTypeCode", vstc);
			hashmapBajajRnw.put("VehTypeCode", vtc);
			hashmapBajajRnw.put("YrMnf", motPol.value.getYearManf());
			hashmapBajajRnw.put("Zone", motPol.value.getZone());
			hashmapBajajRnw.put("AddLoading", addl);
			hashmapBajajRnw.put("BrnchCode", brnchCd);
			hashmapBajajRnw.put("CarrringCap", crCap);
			hashmapBajajRnw.put("DeptCode", dptcd);
			hashmapBajajRnw.put("ElecAccTtl", eattl);
			hashmapBajajRnw.put("Ncb", nc);
			hashmapBajajRnw.put("NonElecAccTtl", neattl);
			hashmapBajajRnw.put("PrvInsCmpny", pic);
			hashmapBajajRnw.put("PrvNcb", pncb);
			hashmapBajajRnw.put("SpDiscRate", sdr);
			hashmapBajajRnw.put("TpFinType", tfn);

			hashmapBajajRnw.put("BajajVehCode", wsmecuh.value.getExtraField1());
			hashmapBajajRnw.put("BajajTrnscId", wsmecuh.value.getExtraField2());
			
			hashmapBajajRnw.put("FirstName", pCustDetailsInout.value.getFirstName());
			hashmapBajajRnw.put("Surname", pCustDetailsInout.value.getSurname());
			hashmapBajajRnw.put("Addr3", pCustDetailsInout.value.getAddLine3());
			hashmapBajajRnw.put("Telephone2", pCustDetailsInout.value.getTelephone2());
			hashmapBajajRnw.put("Status1", pCustDetailsInout.value.getStatus1());
			hashmapBajajRnw.put("Status2", pCustDetailsInout.value.getStatus2());
			hashmapBajajRnw.put("Status3", pCustDetailsInout.value.getStatus3());
	//
			procedureName = "PR_PREMIUM";
			mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
			mtrGrpRespGicId = new Integer(Integer.parseInt(motorGroupResponseGicId));
			value = new Integer(1);
			// System.out.println("valu ''''' " + value);
			// System.out.println("hashmap //////// " +
//			 hashmapKotak.toString());
//			 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
			 integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(mtrGrpRespGrpId, mtrGrpRespGicId, motorGroupResponseSessionId, value, hashmapBajajRnw, IPAddress, userId, branchId, userDesc, procedureName);
			 break;
			 
		default:
			break;
		}     
		
	
		 
		 finalHashMap.put("Company", motorGroupResponseGicName);
		 finalHashMap.put("GICID", motorGroupResponseGicId);
			
			ObjectMapper mapperObj = new ObjectMapper();
			String jsonFinalResp = mapperObj.writeValueAsString(finalHashMap);
			jsonFinalResp= "[" + jsonFinalResp + "]";
	       
			System.out.println("jsonFinalResp==>>"+jsonFinalResp);
			
	        return jsonFinalResp;	
		
		
		
	} 
	
	@RequestMapping(value = "user/BajajPolicyNo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public@ResponseBody String PolicyNoIssue(HttpServletRequest request, HttpServletResponse response) throws JSONException, ParseException, JsonGenerationException, JsonMappingException, IOException {
		
		System.out.println("inside bajaj policy");
		
		String request_for = request.getParameter("request_for");
		String motorGroupResponseGroupId = request.getParameter("motorGroupResponseGroupId");
		String motorGroupResponseSessionId = request.getParameter("motorGroupResponseSessionId");
		String motorGroupResponseGicId = request.getParameter("motorGroupResponseGicId");
		String userId = request.getParameter("userId");
		String userDesc = request.getParameter("userDesc");
		String branchId = request.getParameter("branchId");
		String IPAddress = request.getParameter("IPAddress");
		
		String pincode = request.getParameter("pincode");
		String Addr1 = request.getParameter("Addr1");
		String Addr2 = request.getParameter("Addr2");
		String Addr3 = request.getParameter("Addr3");
		String Addr5 = request.getParameter("Addr5");
		String adharNo = request.getParameter("adharNo");
		String dob = request.getParameter("dob");
		
		 SimpleDateFormat format1 = new SimpleDateFormat("dd/mm/yyyy");
		 SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
		 Date date = format1.parse(dob);
		 dob = format2.format(date);
		
		String email = request.getParameter("email");
		String frstnm = request.getParameter("frstnm");
		String midlName = request.getParameter("midlName");
		String mobNo = request.getParameter("mobNo");
		String panNo = request.getParameter("panNo");
		String lstnm = request.getParameter("lstnm");
		String phone = request.getParameter("phone");
		String title = request.getParameter("title");
		System.out.println("pincode::" +pincode);
		System.out.println("Addr1::" +Addr1);
		System.out.println("Addr2::" +Addr2);
		System.out.println("Addr3::" +Addr3);
		System.out.println("Addr5::" +Addr3);
		System.out.println("adharNo::" +adharNo);
		System.out.println("dob::" +dob);
		System.out.println("email::" +email);
		System.out.println("frstnm::" +frstnm);
		System.out.println("midlName::" +midlName);
		System.out.println("mobNo::" +mobNo);
		System.out.println("panNo::" +panNo);
		System.out.println("lstnm::" +lstnm);
		System.out.println("phone::" +phone);
		System.out.println("title::" +title);
		
		WeoTyacPayRowWsUser wtprwu = new WeoTyacPayRowWsUser();
		wtprwu.setCollectionAmt(new BigDecimal(0));
		wtprwu.setPayMode("CC");
		wtprwu.setCollectionNo("");
		wtprwu.setPayAmt(new BigDecimal(0));
		wtprwu.setReceiptNo("");
		
		ArrayList<WeoTyacPayRowWsUser> wtprwul = new ArrayList<WeoTyacPayRowWsUser>();
		wtprwul.add(wtprwu);
		wtprwua.setWeoTyacPayRowWsUser(wtprwul);
		pRcptListInout.value = wtprwua;
		
		WeoB2CCustDetailsUser wb2cdu = new WeoB2CCustDetailsUser();
		wb2cdu.setAddLine1(Addr1);
		wb2cdu.setAddLine2(Addr2);
		wb2cdu.setAddLine3(Addr3);
		wb2cdu.setAddLine5(Addr5);
		wb2cdu.setAvailableTime(adharNo);
		wb2cdu.setCpType("P");
		wb2cdu.setDateOfBirth(dob);
		wb2cdu.setDelivaryOption("");
		wb2cdu.setEmail(email);
		wb2cdu.setEmailAlerts("");
		wb2cdu.setFirstName(frstnm);
		wb2cdu.setExistingYn("N");
		wb2cdu.setInstitutionName("");
		wb2cdu.setLoggedIn("");
		wb2cdu.setMiddleName(midlName);
		wb2cdu.setMobile(mobNo);
		wb2cdu.setMobileAlerts(panNo);
		wb2cdu.setPartId("");
		wb2cdu.setPassword("");
		wb2cdu.setPincode(pincode);
		System.out.println("pincode:::>>" + wb2cdu.getPincode());
		wb2cdu.setProfession("");
		wb2cdu.setStatus1("");
		wb2cdu.setStatus2("");
		wb2cdu.setStatus3(""); 
		wb2cdu.setSurname(lstnm);
		wb2cdu.setTelephone1(phone);
		wb2cdu.setTelephone2("");
		wb2cdu.setTitle(title);
		
		pCustDetailsInout.value = wb2cdu;  
		
		WeoBjazMotQuestionaryUser wbmqueU = new WeoBjazMotQuestionaryUser();
		
		ArrayList<WeoBjazMotQuestionaryUser> wbmqueUl = new ArrayList<WeoBjazMotQuestionaryUser>();
		wbmqueUl.add(wbmqueU);
		wbmqua.setWeoBjazMotQuestionaryUser(wbmqueUl);
		
		WeoSigOtherDetailsUser potherdetails = new WeoSigOtherDetailsUser();
		potherdetails.setExtra1("NEWPG");
		
		WeoMotDetariffObjUser pMotDetariff = new WeoMotDetariffObjUser();
		pMotDetariff.setExtCol20("https://hopebox.co.in/UAT/BajajPaymentStatus?");
		
		wsmecuh.value = motExtraCvrUser;
		
		WeoMotPremiumDetailsUser premDetOut = premiumDetailsOutOut.value;
		WeoMotPremiumSummaryUserArray premSumUA = premiumSummeryListOut.value;


		serv.issuePolicy(payBn.getUserId(), payBn.getPassword(), tid, pRcptListInout, pCustDetailsInout, motPol, motAccess, genParUsr, wsmecuh, premDetOut, premSumUA, wbmqua, ppolicyrefOut, ppolicyissuedateOut, ppartIdOut, pErrorOut, pErrorCodeOut, ppremiumpayerid, paymentmode, potherdetails, pMotDetariff);
		System.out.println("after new hit ");

		String errL = "";
		try{
			 errL = pErrorOut.value.getWeoTygeErrorMessageUser().get(0).getErrText();
		}catch (IndexOutOfBoundsException e) {
//			e.printStackTrace();
			System.out.println("No Error (PolIs)");
		}
		
		BigDecimal errCdOut = pErrorCodeOut.value;
		
		System.out.println("err Txt = " + errL.toString());
		System.out.println("err Code = " + errCdOut.toString());
		
		polRef = ppolicyrefOut.value;
		System.out.println("polRef = " + polRef);
		
		String dt = ppolicyissuedateOut.value;
		System.out.println("issueDate = " + dt);
		
		HashMap<String, String> policyNo = new HashMap<>();
		policyNo.put("policyNO", polRef);
		
		String idout = ppartIdOut.value;
		System.out.println("idout = " + idout);
		
		policyNo.put("errorOut", errL.toString()); 
		
//		System.out.println("before view");  	
//		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView("common/BajajPolicyNo");
//		andView.addObject("data", policyNo);
		System.out.println("premium = " + premium); 
		
		String fibreGlassVal = String.valueOf(wsmecuh.value.getFibreGlassValue());
		String empLle = String.valueOf(wsmecuh.value.getNoOfEmployeesLle());
		String noPrsnLlo = String.valueOf(wsmecuh.value.getNoOfPersonsLlo());
		String noPrsnPa = String.valueOf(wsmecuh.value.getNoOfPersonsPa());
		String noTrailrs = String.valueOf(wsmecuh.value.getNoOfTrailers());
		String sideCarVal = String.valueOf(wsmecuh.value.getSideCarValue());
		String subImdCode = String.valueOf(wsmecuh.value.getSubImdcode());
		String sumInsPa = String.valueOf(wsmecuh.value.getSumInsuredPa());
		String sumInsTtlNmPa = String.valueOf(wsmecuh.value.getSumInsuredTotalNamedPa());
		String ttlTrlrVal = String.valueOf(wsmecuh.value.getTotalTrailerValue());
		String voluntryExcs = String.valueOf(wsmecuh.value.getVoluntaryExcess());
		
		String AddLdPrm = String.valueOf(premDetOut.getAddLoadPrem());
		String collPrem = String.valueOf(premDetOut.getCollPremium());
		String finalPrem = String.valueOf(premDetOut.getFinalPremium());
		String imtOut = premDetOut.getImtOut();
		String ncbAmt = String.valueOf(premDetOut.getNcbAmt());
		String netPrem = String.valueOf(premDetOut.getNetPremium());
		String srvcTx = String.valueOf(premDetOut.getServiceTax());
		String spDisc = String.valueOf(premDetOut.getSpDisc());
		String stmpDuty = String.valueOf(premDetOut.getStampDuty());
		String ttlActPrem = String.valueOf(premDetOut.getTotalActPremium());
		String ttlIev = String.valueOf(premDetOut.getTotalIev());
		String ttlNetPrem = String.valueOf(premDetOut.getTotalNetPremium());
		String ttlOdPrem = String.valueOf(premDetOut.getTotalOdPremium());
		String ttlPrem = String.valueOf(premDetOut.getTotalPremium());
		
		HashMap<String, String> hashmapBajajPolIssue = new HashMap<String, String>();
		hashmapBajajPolIssue.put("AddrLine1", pCustDetailsInout.value.getAddLine1());
		hashmapBajajPolIssue.put("AddrLine2", pCustDetailsInout.value.getAddLine2());
		hashmapBajajPolIssue.put("AddrLine3", pCustDetailsInout.value.getAddLine3());
		hashmapBajajPolIssue.put("AddrLine5", pCustDetailsInout.value.getAddLine5());
		hashmapBajajPolIssue.put("AvailTime", pCustDetailsInout.value.getAvailableTime());
		hashmapBajajPolIssue.put("CpType", pCustDetailsInout.value.getCpType());
		hashmapBajajPolIssue.put("Dob", pCustDetailsInout.value.getDateOfBirth());
		hashmapBajajPolIssue.put("DeliveryOption", pCustDetailsInout.value.getDelivaryOption());
		hashmapBajajPolIssue.put("Email", pCustDetailsInout.value.getEmail());
		hashmapBajajPolIssue.put("EmailAlerts", pCustDetailsInout.value.getEmailAlerts());
		hashmapBajajPolIssue.put("ExstnYn", pCustDetailsInout.value.getExistingYn());
		hashmapBajajPolIssue.put("FrstNm", pCustDetailsInout.value.getFirstName());
		hashmapBajajPolIssue.put("InstNm", pCustDetailsInout.value.getInstitutionName());
		hashmapBajajPolIssue.put("LoggedIn", pCustDetailsInout.value.getLoggedIn());
		hashmapBajajPolIssue.put("MidlNm", pCustDetailsInout.value.getMiddleName());
		hashmapBajajPolIssue.put("Mob", pCustDetailsInout.value.getMobile());
		hashmapBajajPolIssue.put("MobAlrts", pCustDetailsInout.value.getMobileAlerts());
		hashmapBajajPolIssue.put("PartId", pCustDetailsInout.value.getPartId());
		hashmapBajajPolIssue.put("PartTempId", pCustDetailsInout.value.getPartTempId());
		hashmapBajajPolIssue.put("Pass", pCustDetailsInout.value.getPassword());
		hashmapBajajPolIssue.put("Pincode", pCustDetailsInout.value.getPincode());
		hashmapBajajPolIssue.put("PolAddrLine1", pCustDetailsInout.value.getPolAddLine1());
		hashmapBajajPolIssue.put("PolAddrLine2", pCustDetailsInout.value.getPolAddLine2());
		hashmapBajajPolIssue.put("PolAddrLine3", pCustDetailsInout.value.getPolAddLine3());
		hashmapBajajPolIssue.put("PolAddrLine5", pCustDetailsInout.value.getPolAddLine5());
		hashmapBajajPolIssue.put("Status1", pCustDetailsInout.value.getStatus1());
		hashmapBajajPolIssue.put("Status2", pCustDetailsInout.value.getStatus2());
		hashmapBajajPolIssue.put("Status3", pCustDetailsInout.value.getStatus3());
		hashmapBajajPolIssue.put("Surname", pCustDetailsInout.value.getSurname());
		hashmapBajajPolIssue.put("Telephone1", pCustDetailsInout.value.getTelephone1());
		hashmapBajajPolIssue.put("Telephone2", pCustDetailsInout.value.getTelephone2());
		hashmapBajajPolIssue.put("Title", pCustDetailsInout.value.getTitle());
		
		String p4dc = String.valueOf(motPol.value.getProduct4DigitCode());
		String mvt = String.valueOf(motPol.value.getMiscVehType());
		String vehIdv = String.valueOf(motPol.value.getVehicleIdv());
		String vehmkcd = String.valueOf(motPol.value.getVehicleMakeCode());
		String vehMdCd = String.valueOf(motPol.value.getVehicleModelCode());
		String vstc = String.valueOf(motPol.value.getVehicleSubtypeCode());
		String vtc = String.valueOf(motPol.value.getVehicleTypeCode());
		String addl = String.valueOf(motPol.value.getAddLoading());
		String brnchCd = String.valueOf(motPol.value.getBranchCode());
		String crCap = String.valueOf(motPol.value.getCarryingCapacity());
		String dptcd = String.valueOf(motPol.value.getDeptCode());
		String eattl = String.valueOf(motPol.value.getElecAccTotal());
		String nc = String.valueOf(motPol.value.getNcb());
		String neattl = String.valueOf(motPol.value.getNonElecAccTotal());
		String pic = String.valueOf(motPol.value.getPrvInsCompany());
		String pncb = String.valueOf(motPol.value.getPrvNcb());
		String sdr = String.valueOf(motPol.value.getSpDiscRate());
		String tfn = String.valueOf(motPol.value.getTpFinType());
		
	
		hashmapBajajPolIssue.put("VehicleType", motPol.value.getVehicleType());
		hashmapBajajPolIssue.put("Prdct4DigitCode", p4dc);
		hashmapBajajPolIssue.put("TermStrtDt", motPol.value.getTermStartDate());
		hashmapBajajPolIssue.put("TermEndDt", motPol.value.getTermEndDate());
		hashmapBajajPolIssue.put("EngnNo", motPol.value.getEngineNo());
		hashmapBajajPolIssue.put("ChassisNo", motPol.value.getChassisNo());
		hashmapBajajPolIssue.put("RegNo", motPol.value.getRegistrationNo());
		hashmapBajajPolIssue.put("PrvPolicyRef", motPol.value.getPrvPolicyRef());
		hashmapBajajPolIssue.put("AddLoadinOn", motPol.value.getAddLoadingOn());
		hashmapBajajPolIssue.put("AutoMembership", motPol.value.getAutoMembership());
		hashmapBajajPolIssue.put("Color", motPol.value.getColor());
		hashmapBajajPolIssue.put("Fuel", motPol.value.getFuel());
		hashmapBajajPolIssue.put("Hypo", motPol.value.getHypo());
		hashmapBajajPolIssue.put("PartnerType", motPol.value.getPartnerType());
		hashmapBajajPolIssue.put("PolicyType", motPol.value.getPolType());
		hashmapBajajPolIssue.put("PrvClaimStatus", motPol.value.getPrvClaimStatus());
		hashmapBajajPolIssue.put("PrvExpDt", motPol.value.getPrvExpiryDate());
		hashmapBajajPolIssue.put("RegiLocOther", motPol.value.getRegiLocOther());
		hashmapBajajPolIssue.put("RegDt", motPol.value.getRegistrationDate());
		hashmapBajajPolIssue.put("RegLoc", motPol.value.getRegistrationLocation());
		hashmapBajajPolIssue.put("VehMk", motPol.value.getVehicleMake());
		hashmapBajajPolIssue.put("VehMdl", motPol.value.getVehicleModel());
		hashmapBajajPolIssue.put("VehSubType", motPol.value.getVehicleSubtype());
		hashmapBajajPolIssue.put("MiscVehType", mvt);
		hashmapBajajPolIssue.put("VehIdv", vehIdv);
		hashmapBajajPolIssue.put("VehMkCode", vehmkcd);
		hashmapBajajPolIssue.put("VehModlCode", vehMdCd);
		hashmapBajajPolIssue.put("VehSbTypeCode", vstc);
		hashmapBajajPolIssue.put("VehTypeCode", vtc);
		hashmapBajajPolIssue.put("YrMnf", motPol.value.getYearManf());
		hashmapBajajPolIssue.put("Zone", motPol.value.getZone());
		hashmapBajajPolIssue.put("AddLoading", addl);
		hashmapBajajPolIssue.put("BrnchCode", brnchCd);
		hashmapBajajPolIssue.put("CarrringCap", crCap);
		hashmapBajajPolIssue.put("DeptCode", dptcd);
		hashmapBajajPolIssue.put("ElecAccTtl", eattl);
		hashmapBajajPolIssue.put("Ncb", nc);
		hashmapBajajPolIssue.put("NonElecAccTtl", neattl);
		hashmapBajajPolIssue.put("PrvInsCmpny", pic);
		hashmapBajajPolIssue.put("PrvNcb", pncb);
		hashmapBajajPolIssue.put("SpDiscRate", sdr);
		hashmapBajajPolIssue.put("TpFinType", tfn);
		
		hashmapBajajPolIssue.put("AddLdPrm", AddLdPrm);
		hashmapBajajPolIssue.put("collPrem", collPrem);
		hashmapBajajPolIssue.put("finalPrem", finalPrem);
		hashmapBajajPolIssue.put("imtOut", imtOut);
		hashmapBajajPolIssue.put("ncbAmt", ncbAmt);
		hashmapBajajPolIssue.put("netPrem", netPrem);
		hashmapBajajPolIssue.put("srvcTx", srvcTx);
		hashmapBajajPolIssue.put("spDisc", spDisc);
		hashmapBajajPolIssue.put("stmpDuty", stmpDuty);
		hashmapBajajPolIssue.put("ttlActPrem", ttlActPrem);
		hashmapBajajPolIssue.put("ttlIev", ttlIev);
		hashmapBajajPolIssue.put("totalnetprem", ttlNetPrem);
		hashmapBajajPolIssue.put("ttlOdPrem", ttlOdPrem);
		hashmapBajajPolIssue.put("ttlPrem", ttlPrem);
		
		hashmapBajajPolIssue.put("CvrNoteData", wsmecuh.value.getCovernoteDate());
		hashmapBajajPolIssue.put("CvrNoteNO", wsmecuh.value.getCovernoteNo());
		hashmapBajajPolIssue.put("ExtraField1", wsmecuh.value.getExtraField1());
		hashmapBajajPolIssue.put("ExtraField2", wsmecuh.value.getExtraField2());
		hashmapBajajPolIssue.put("ExtraField3", wsmecuh.value.getExtraField3());
		hashmapBajajPolIssue.put("GeoExt", wsmecuh.value.getGeogExtn());
		hashmapBajajPolIssue.put("SubImdCode", wsmecuh.value.getSubImdcode());
		hashmapBajajPolIssue.put("fibreGlassVal", fibreGlassVal);
		hashmapBajajPolIssue.put("empLle", empLle);
		hashmapBajajPolIssue.put("noPrsnLlo", noPrsnLlo);
		hashmapBajajPolIssue.put("noPrsnPa", noPrsnPa);
		hashmapBajajPolIssue.put("noTrailrs", noTrailrs);
		hashmapBajajPolIssue.put("sideCarVal", sideCarVal);
		hashmapBajajPolIssue.put("subImdCode", subImdCode);
		hashmapBajajPolIssue.put("sumInsPa", sumInsPa);
		hashmapBajajPolIssue.put("sumInsTtlNmPa", sumInsTtlNmPa);
		hashmapBajajPolIssue.put("ttlTrlrVal", ttlTrlrVal);
		hashmapBajajPolIssue.put("voluntryExcs", voluntryExcs);
		
		hashmapBajajPolIssue.put("PolicyRef", polRef);
		hashmapBajajPolIssue.put("PolicyIssueDt", dt);
		hashmapBajajPolIssue.put("PpartIdOut", idout);
		hashmapBajajPolIssue.put("PolicyRef", polRef);
		hashmapBajajPolIssue.put("ErrorText", errL.toString());
		
		
		String procedureName = "PR_PROPOSAL";
		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
		 Integer mtrGrpRespGicId = new Integer(Integer.parseInt(motorGroupResponseGicId));
		 Integer value = new Integer(1);
		// System.out.println("valu ''''' " + value);
		// System.out.println("hashmap //////// " +
//		 hashmapKotak.toString());
//		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
		 integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(mtrGrpRespGrpId, mtrGrpRespGicId, motorGroupResponseSessionId, value, hashmapBajajPolIssue, IPAddress, userId, branchId, userDesc, procedureName);
		
		 ObjectMapper mapperObj = new ObjectMapper();
			String jsonFinalResp = mapperObj.writeValueAsString(hashmapBajajPolIssue);
			jsonFinalResp= "[" + jsonFinalResp + "]";
	       
			System.out.println("jsonFinalResp==>>"+jsonFinalResp);
			
	        return jsonFinalResp;	
	        
		
		
		
	}
	
	@RequestMapping(value = "user/BajajPayment", method = RequestMethod.GET)
	public org.springframework.web.servlet.ModelAndView Payment(HttpServletRequest request, HttpServletResponse response) throws JSONException {
				
		
		String motorGroupResponseGroupId = request.getParameter("motorGroupResponseGroupId");
		String motorGroupResponseSessionId = request.getParameter("motorGroupResponseSessionId");
		String motorGroupResponseGicId = request.getParameter("motorGroupResponseGicId");
		String userId = request.getParameter("userId");
		String userDesc = request.getParameter("userDesc");
		String branchId = request.getParameter("branchId");
		String IPAddress = request.getParameter("IPAddress");
		
		WeoRecStrings40User wrsu = new WeoRecStrings40User();
		
		pPaymentTransObjInout = new Holder<WeoRecStrings40User>();
		pPaymentTransObjInout.value = wrsu;
	
		System.out.println("ppolicyrefOut.value " + ppolicyrefOut.value);
		
//		serv.getPgTransStatus(userId, password, pTransactionIdInout, pPaymentTransObjInout, ppolicyrefOut, pErrorMsgOut, pErrorCodeOut);
		
//		System.out.println("ppolicyrefOut.value " + polRef);
//		
//		String ereMsgOut = pErrorMsgOut.value;
////		String ereMsgOut = "no err";
//		System.out.println("pErrorMsgOut " + ereMsgOut);
		System.out.println("userId " + payBn.getUserId());
		
		BigDecimal trnscId = pTransactionIdInout.value;
		System.out.println("pTransactionIdInout " + trnscId);
		
		HashMap<String, Object> payResponse = new HashMap<String, Object>();
		
		payResponse.put("trnscId", trnscId);
		payResponse.put("polRef", polRef);
//		payResponse.put("errMSg", ereMsgOut);
		payResponse.put("userId", payBn.getUserId());
		
		System.out.println("In transaction status");
		System.out.println("payBn.getUserId() = " + payBn.getUserId());
		
//		String procedureName = "PR_PREMIUM";
//		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
//		 Integer mtrGrpRespGicId = new Integer(Integer.parseInt(motorGroupResponseGicId));
//		 Integer value = new Integer(2);
//		// System.out.println("valu ''''' " + value);
//		// System.out.println("hashmap //////// " +
////		 hashmapKotak.toString());
////		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
//		 integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(mtrGrpRespGrpId, mtrGrpRespGicId, motorGroupResponseSessionId, value, payRequest, IPAddress, userId, branchId, userDesc, procedureName);

		
		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView("common/BajajPayment");
		andView.addObject("data", payResponse);
		return andView;
	}
	
	@RequestMapping(value = "/BajajPaymentStatus", method = RequestMethod.POST)
	public org.springframework.web.servlet.ModelAndView TransactionStatusPost(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {

		policyref = request.getParameter("policyref");
		
		System.out.println("In Payment status");
		
		policyNo = new Holder<String>(policyref);
		
//		System.out.println("userId = " + payBn.getUserId());
//		System.out.println("password = " + payBn.getPassword());
//		System.out.println("pTransactionIdInout = " + pTransactionIdInout.value);
//		System.out.println("pPaymentTransObjInout = " + pPaymentTransObjInout.value);
//		System.out.println("policyNo = " + policyNo.value);
//		System.out.println("pErrorMsgOut = " + pErrorMsgOut.value);
//		System.out.println("pErrorCodeOut = " + pErrorCodeOut.value);
//		
//		String policyref = ;
		System.out.println("policyref = " + policyNo.value);
//		System.out.println("policyref = " + policyref);
		paySts = request.getParameter("p_pay_status");
		System.out.println("paySts = " + paySts);
		
//		System.out.println("policyNo = " + policyNo.value);
//		System.out.println("policyNo = " + policyref);
		
		HashMap<String, String> payResponse = new HashMap<>();
		
		payResponse.put("policyNo", policyref);
		payResponse.put("paySts", paySts);
		
		System.out.println("userId = " + payBn.getUserId());
		System.out.println("password = " + payBn.getPassword());
//		System.out.println("pTransactionIdInout = " + pTransactionIdInout.value);
//		System.out.println("pPaymentTransObjInout = " + pPaymentTransObjInout.value);
//		System.out.println("policyNo = " + policyNo.value);
//		System.out.println("pErrorMsgOut = " + pErrorMsgOut.value);
//		System.out.println("pErrorCodeOut = " + pErrorCodeOut.value);
//		
//		serv.getPgTransStatus(payBn.getUserId(), payBn.getPassword(), pTransactionIdInout, pPaymentTransObjInout, policyNo, pErrorMsgOut, pErrorCodeOut);

		String procedureName = "PR_PROPOSAL";
		int mtrGrpRespGrpId = Integer.parseInt(payBn.getMotorGroupResponseGroupId());
		int mtrGrpRespGicId = Integer.parseInt(payBn.getMotorGroupResponseGicId());
		Integer value = new Integer(2);
//		// System.out.println("valu ''''' " + value);
//		// System.out.println("hashmap //////// " +
////		 hashmapKotak.toString());
////		 Integer mtrGrpRespGrpId = new Integer(Integer.parseInt(motorGroupResponseGroupId));
		integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(mtrGrpRespGrpId, mtrGrpRespGicId, payBn.getMotorGroupResponseSessionId(), value, payResponse, payBn.getIPAddress(), payBn.getUserId(), payBn.getBranchId(), payBn.getUserDesc(), procedureName);
		
		System.out.println("After Hit PayStatus");
		org.springframework.web.servlet.ModelAndView andView = new org.springframework.web.servlet.ModelAndView("common/BajajPolicyNo");
		andView.addObject("data", payResponse);
		return andView;

	} 
	
	@RequestMapping(value = "user/BajajGetPdf", method = RequestMethod.POST)
	public String BajajPdf(HttpServletRequest request, HttpServletResponse response) {
		
//		String policyNo = request.getParameter("ppolicyref_out");
		
		ClientInfo clntInfo = new ClientInfo();
		/*clntInfo.setUserId(payBn.getUserId());
		clntInfo.setPassword(payBn.getPassword()); 
		clntInfo.setPdfMode("WS_POLICY_PDF");
		clntInfo.setPolicyNum(policyref);*/		
		
		clntInfo.setUserId("10053264@general.bajajallianz.co.in");
		clntInfo.setPassword("newpas12"); 
		clntInfo.setPdfMode("WS_POLICY_PDF");
		clntInfo.setPolicyNum("OG-19-2101-1801-00000030");
		
		WebServiceImplService pdfServ = new WebServiceImplService();
		WebServiceInterface servport = pdfServ.getWebServiceImplPort();
		String errMsg = clntInfo.getErrorMsg();
		System.out.println("PDFerrMsg = " + errMsg);
		String errCd = clntInfo.getErrorCode();
		System.out.println("PDFerrCode = " + errCd);
	
		System.out.println("url = " + clntInfo.getStr6());
		Object bpdf1 =  servport.downloadFile(clntInfo);
		System.out.println("bpdf1::>>" + bpdf1);
		
		byte[] bytes = SerializationUtils.serialize((Serializable) bpdf1);
	
		System.out.println("bytes::>>" + bytes);
		OutputStream out;
        try {
            out = new FileOutputStream(System.getProperty("user.home")+ policyref + ".pdf");
            out.write(bytes);
            out.close();
            System.out.println("write success");
        } catch (Exception e) {
            System.out.println(e);
        }
        return "{\"status:\" \"Policy Downloaded successfully}";
	}
}
