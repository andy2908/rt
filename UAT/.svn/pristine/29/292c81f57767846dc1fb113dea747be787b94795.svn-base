package com.uat.hbc.insurance.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
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
import org.springframework.web.portlet.ModelAndView;
import org.xml.sax.InputSource;

import com.google.gson.Gson;
import com.uat.hbc.common.model.MotorResponseBean;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.insurance.dao.IntegrationRelianceDao;
import com.uat.hbc.insurance.model.RelianceIntegrationBean;
import com.uat.hbc.insurance.service.IntegrationSaveResponseService;

@Controller
public class MotorCalculatorRelianceController {

	DOMSource domSource;
	@Autowired
	IntegrationRelianceDao relianceDao;
	@Autowired
	IntegrationSaveResponseService integrationSaveResponseService;
	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;
	HashMap data;
	
	HashMap<String, String> hashmap;
	HashMap<String, String> hashmapProposal;
	HashMap<String, String> hashmapinputs  ;
	HashMap<String, String> hashmapPremium ;
	MotorResponseBean bean;
	RelianceIntegrationBean relianceIntBean;
	String path;
	
	   HashMap<String,String> payResponse;
	 
		public MotorCalculatorRelianceController() {
			 hashmap = new HashMap<>();
	         hashmapProposal = new HashMap<>();
			 hashmapinputs = new HashMap<>();
			 hashmapPremium = new HashMap<>();
			 bean=new MotorResponseBean();
			 path=System.getProperty("user.home");
//			 relianceIntBean= new RelianceIntegrationBean();
			
		}
		

		@RequestMapping(value = "user/motorCalculator", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		public @ResponseBody String getProposal(HttpServletRequest request, HttpServletResponse response)
				throws JDOMException, IOException {
			JSONObject fetchPolicy = null;
			relianceIntBean= new RelianceIntegrationBean();
			try {
				String pkg_name = request.getParameter("pkg_name");

				//System.out.println("pkg_name==>" + pkg_name);

				String proc_name = request.getParameter("proc_name");

				//System.out.println("proc_name==>" + proc_name);
				
				String table_name = request.getParameter("table_name");
				
				relianceIntBean.setRequest_for(request.getParameter("request_for"));
//				System.out.println("000000000-->"+pkg_name+","+proc_name+","+table_name);
				
				String clienttype = request.getParameter("clienttype");
				String lastname = request.getParameter("last_name");
				String midname = request.getParameter("midname");
				String forename = request.getParameter("forename");
				String corporatename = request.getParameter("corporatename");
				String occupationid = request.getParameter("occupationid");
				
//				System.out.println("11111111==>" + clienttype +","+lastname+","+midname +","+ forename +","+corporatename+","+occupationid);
				
				String dob = request.getParameter("dob");
				String gender = request.getParameter("gender");
				String phoneno = request.getParameter("phoneno");
				String mobileno = request.getParameter("mobileno");
				String clientaddress = request.getParameter("clientaddress");
				String cld_ca_addresstype = request.getParameter("cld_ca_addresstype");
				
//				System.out.println("p222222222==>" + dob +","+gender+","+phoneno +","+  mobileno +","+clientaddress+","+cld_ca_addresstype);
				
				
				String cld_ca_address1 = request.getParameter("cld_ca_address1");
				String cld_ca_address2 = request.getParameter("cld_ca_address2");
				String cld_ca_address3 = request.getParameter("cld_ca_address3");
				String cld_ca_cityid = request.getParameter("cld_ca_cityid");
				String cld_ca_districtid = request.getParameter("cld_ca_districtid");
				String cld_ca_stateid = request.getParameter("cld_ca_stateid");
				
//				System.out.println("3333333333==>" + cld_ca_address1 +","+cld_ca_address2+","+cld_ca_address3 +","+  cld_ca_cityid +","+cld_ca_districtid+","+cld_ca_stateid);
				
				String cld_ca_pincode = request.getParameter("cld_ca_pincode");
				String cld_ca_country = request.getParameter("cld_ca_country");
				String cld_ca_nearestlandmark = request.getParameter("cld_ca_nearestlandmark");
				String cld_pa_addresstype = request.getParameter("cld_pa_addresstype");
				String cld_pa_address1 = request.getParameter("cld_pa_address1");
				String cld_pa_address2 = request.getParameter("cld_pa_address2");
				
//				System.out.println("4444444444==>" + cld_ca_pincode +","+cld_ca_country+","+cld_ca_nearestlandmark +","+  cld_pa_addresstype +","+cld_pa_address1+","+cld_pa_address2);
				
				String cld_pa_address3 = request.getParameter("cld_pa_address3");
				String cld_pa_cityid = request.getParameter("cld_pa_cityid");
				String cld_pa_districtid = request.getParameter("cld_pa_districtid");
				String cld_pa_stateid = request.getParameter("cld_pa_stateid");
				String cld_pa_pincode = request.getParameter("cld_pa_pincode");
				String cld_pa_country = request.getParameter("cld_pa_country");
				
				
//				System.out.println("55555555==>" + cld_pa_address3 +","+cld_pa_cityid+","+cld_pa_districtid +","+ cld_pa_stateid +","+cld_pa_pincode+","+cld_pa_country);
				
				String cld_pa_nearestlandmark = request.getParameter("cld_pa_nearestlandmark");
				String cld_ra_addresstype = request.getParameter("cld_ra_addresstype");
				String cld_ra_address1 = request.getParameter("cld_ra_address1");
				String cld_ra_address2 = request.getParameter("cld_ra_address2");
				String cld_ra_address3 = request.getParameter("cld_ra_address3");
				String cld_ra_cityid = request.getParameter("cld_ra_cityid");
				
//				System.out.println("6666666==>" + cld_pa_nearestlandmark +","+cld_ra_addresstype+","+cld_ra_address1 +","+ cld_ra_address2 +","+cld_ra_address3+","+cld_ra_cityid);
				
				String cld_ra_districtid = request.getParameter("cld_ra_districtid");
				String cld_ra_stateid = request.getParameter("cld_ra_stateid");
				String cld_ra_pincode = request.getParameter("cld_ra_pincode");
				String cld_ra_country = request.getParameter("cld_ra_country");
				String cld_ra_nearestlandmark = request.getParameter("cld_ra_nearestlandmark");
				String emailid = request.getParameter("emailid");
				
//				System.out.println("777777==>" + cld_ra_districtid +","+cld_ra_stateid+","+cld_ra_pincode +","+ cld_ra_country +","+cld_ra_nearestlandmark+","+emailid);
				
				String salutation = request.getParameter("salutation");
				String maritalstatus = request.getParameter("maritalstatus");
				String nationality = request.getParameter("nationality");
				String p_businesstype = request.getParameter("p_businesstype");
				String productid = request.getParameter("str_prod");
				String vehid = request.getParameter("str_veh");
				String str_businessType = request.getParameter("str_proposalType");
				
//				System.out.println("8888888==>" + salutation +","+maritalstatus+","+nationality +","+ p_businesstype +","+productid+","+vehid);
				
				String modelid = request.getParameter("str_mod");
				String varid = request.getParameter("str_var");
				String idv = request.getParameter("idv");
				String dateofpurchase = request.getParameter("dateOfPurchase");
				String manufacturemonth = request.getParameter("manufacturemonth");
				String manufactureyear = request.getParameter("manufactureyear");
				
//				System.out.println("9999999==>" + modelid +","+varid+","+idv +","+ dateofpurchase +","+manufacturemonth+","+manufactureyear);
				
				String engineno = request.getParameter("engineno");
				String chassis = request.getParameter("chassis");
				String isvehiclehypothicated = request.getParameter("isvehiclehypothicated");
				String financetypeid = request.getParameter("financetypeid");
				String financiername = request.getParameter("financiername");
				String financieraddress = request.getParameter("financieraddress");
				
//				System.out.println("10101010==>" + engineno +","+chassis+","+isvehiclehypothicated +","+ financetypeid +","+financiername+","+financieraddress);
				
				String financiercity = request.getParameter("financiercity");
				String isregaddsameascommadd = request.getParameter("isregaddsameascommadd");
				String isperaddsameascommadd = request.getParameter("isperaddsameascommadd");
				String isregaddsameasperadd = request.getParameter("isregaddsameasperadd");
				String stateofregistrationid = request.getParameter("str_state");
				String rto_city_ID = request.getParameter("str_city");
				
//				System.out.println("11/11/11==>" + financiercity +","+isregaddsameascommadd+","+isperaddsameascommadd +","+ isregaddsameasperadd +","+stateofregistrationid+","+rto_city_ID);
				
//				System.out.println("rto_city_ID-" + rto_city_ID);
				String registration_number = request.getParameter("registration_number");
				String isnewvehicle = request.getParameter("isnewvehicle");
				String registration_date = request.getParameter("vehicleRegDate");
				String covers = request.getParameter("covers");
//				System.out.println("covers-" + covers);
				String coversValue = request.getParameter("coversValue");
//				String isbifuelkit = request.getParameter("isbifuelkit");
				String islpgcng = request.getParameter("islpgcng");
//				System.out.println("coversValue-" + coversValue);
				String pao_nomineename = request.getParameter("pao_nomineename");
//				System.out.println("pao_nomineename-" + pao_nomineename);
				String pao_nomineedob = request.getParameter("pao_nomineedob");
//				System.out.println("pao_nomineedob-" + pao_nomineedob);
				String pao_nomineerel = request.getParameter("pao_nomineerel");
//				System.out.println("pao_nomineerel-" + pao_nomineerel);
				String previnsname = request.getParameter("previnsname");
//				System.out.println("previnsname-" + previnsname);
				String prevpolicyno = request.getParameter("prevpolicyno");
//				System.out.println("prevpolicyno-" + prevpolicyno);
				String prevstartdt = request.getParameter("prevstartdt");
//				System.out.println("prevstartdt-" + prevstartdt);
				String prevenddt = request.getParameter("prevenddt");
//				System.out.println("prevenddt-" + prevenddt);
				String ncbisapp = request.getParameter("ncbisapp");
//				System.out.println("ncbisapp-" + ncbisapp);
				String ncbeligicrit = request.getParameter("ncbeligicrit");
//				System.out.println("ncbeligicrit-" + ncbeligicrit);
				String ncbprevncb = request.getParameter("ncbprevncb");
//				System.out.println("ncbprevncb-" + ncbprevncb);
				String ncbcurncb = request.getParameter("ncbcurncb");
//				System.out.println("ncbcurncb-" + ncbcurncb);
				String voldedamt = request.getParameter("voldedamt");
				String str_productname = request.getParameter("str_productname");
//				System.out.println("voldedamt-" + voldedamt);
				relianceIntBean.setEleamt(request.getParameter("eleamt"));
//			    System.out.println("eleamt-" + relianceIntBean.getEleamt());
			 
			    relianceIntBean.setNoneleamt(request.getParameter("noneleamt"));
//				System.out.println("noneleamt-" + relianceIntBean.getNoneleamt());
				 
//				 System.out.print("noneleamt---->"+noneleamt);
				 
				relianceIntBean.setUnpasno(request.getParameter("unpasno"));
//				 System.out.println("unpasno-" + relianceIntBean.getUnpasno());
				 relianceIntBean.setUnpasamt(request.getParameter("unpasamt"));
//				 System.out.println("unpasamt-" + relianceIntBean.getUnpasamt());
				 relianceIntBean.setAntitheftno(request.getParameter("antitheftno"));
//				 System.out.println("antitheftno-" + relianceIntBean.getAntitheftno());
				 relianceIntBean.setAntitheftname(request.getParameter("antitheftname"));
//				 System.out.println("antitheftname-" + relianceIntBean.getAntitheftname());
				 relianceIntBean.setAutomemno(request.getParameter("automemno"));
//				 System.out.println("coversValue-" + coversValue);
				 relianceIntBean.setAutomemname(request.getParameter("automemname"));
//				 System.out.println("coversValue-" + coversValue);
				 relianceIntBean.setVdpolcovid(request.getParameter("vdpolcovid"));
//				 System.out.println("coversValue-" + coversValue);
//				 System.out.println("111111111111111111111111111");
				 relianceIntBean.setVdamt(request.getParameter("vdamt"));
				 relianceIntBean.setVdno(request.getParameter("vdno"));
//				 System.out.println("222222222222222222222222222");
				 relianceIntBean.setVdname(request.getParameter("vdname"));
				 relianceIntBean.setPatounpasno(request.getParameter("patounpasno"));
//				 System.out.println("333333333333333333333333333333333");
				 relianceIntBean.setPatounpasname(request.getParameter("patounpasname"));
				 relianceIntBean.setBfk_amt(request.getParameter("bfk_amt"));
//				 System.out.println("444444444444444444444444444444");
				 relianceIntBean.setElectricalitemlist(request.getParameter("ElectricalItem"));
				 relianceIntBean.setNonelectricalitemlist(request.getParameter("NonElectricalItem"));
//				 System.out.println("55555555555555555555555555555");
			   	
			    String patounpaspolcovid = request.getParameter("patounpaspolcovid");
				String patounpasamt = request.getParameter("patounpasamt");
				String zone_id=request.getParameter("zone_id");
				String PI_CLD_RA_AreaID=request.getParameter("PI_CLD_RA_AreaID");
				String PI_CLD_PA_AreaID=request.getParameter("PI_CLD_PA_AreaID");
				String PI_CLD_CA_AreaID=request.getParameter("PI_CLD_CA_AreaID");
				
				relianceIntBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
				relianceIntBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
				relianceIntBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
				
				relianceIntBean.setUserId(request.getParameter("userId"));
				relianceIntBean.setUserDesc(request.getParameter("userDesc"));
				relianceIntBean.setBranchId(request.getParameter("branchId"));
				String policyType=request.getParameter("policyType");
				String prevInsId=request.getParameter("prevInsId");
				String coverNo = request.getParameter("coverNo");
				relianceIntBean.setIPAddress(request.getParameter("IPAddress"));
				
				if(relianceIntBean.getUserId()!=null){
					bean.setUserId((relianceIntBean.getUserId()));
				}
				bean.setUserDesc(relianceIntBean.getUserDesc());
				bean.setBranchId(relianceIntBean.getBranchId());
				bean.setGroupId(relianceIntBean.getMotorGroupResponseGroupId());
				bean.setGicId(relianceIntBean.getMotorGroupResponseGicId());
				bean.setSessionId(relianceIntBean.getMotorGroupResponseSessionId());
				bean.setIpAddress(relianceIntBean.getIPAddress());
				bean.setResponseType("1");
				
				ArrayList<String> resultList = new ArrayList<String>();
				JSONArray array = new JSONArray();
				String retMsg = "";
				String jsonNames = "";

				String xx = "";
				HashMap inputParaList = new HashMap<>();
				
				inputParaList.put("PI_TABLE_NAME", table_name);
				inputParaList.put("PI_Rto_City", rto_city_ID);
				inputParaList.put("PI_ZONE_ID", zone_id);
				inputParaList.put("PI_PolicyType", policyType);
				inputParaList.put("PI_VarID", varid);
				inputParaList.put("PI_P_BusinessType", p_businesstype);
				inputParaList.put("PI_ProductID", productid);
				inputParaList.put("PI_VehID", vehid);
				inputParaList.put("PI_ModelID", modelid);
				inputParaList.put("PI_CUST_TYPE", clienttype);
				inputParaList.put("PI_OccupationID", occupationid);
				inputParaList.put("PI_FINTYPE_ID", financetypeid);
				inputParaList.put("PI_PREV_GIC_ID", prevInsId);
				inputParaList.put("PI_CLD_CA_AreaID", PI_CLD_CA_AreaID);
				inputParaList.put("PI_CLD_PA_AreaID", PI_CLD_PA_AreaID);
				inputParaList.put("PI_CLD_RA_AreaID", PI_CLD_RA_AreaID);
				inputParaList.put("PI_Nom_Rel", pao_nomineerel);
				inputParaList.put("PI_Nationality", nationality);
				inputParaList.put("PI_MaritalStatus", maritalstatus);
				inputParaList.put("PI_Covers", covers);
				inputParaList.put("PI_COV_VAL", coversValue);
				inputParaList.put("PI_COV_NO", coverNo);
				inputParaList.put("PI_PREV_NCB", ncbprevncb);
				inputParaList.put("PI_IM_GROUP_ID", relianceIntBean.getMotorGroupResponseGroupId());
				
				System.out.println("inputParaList--->>"+inputParaList);
				
				jsonNames = relianceDao.getRelianceData(pkg_name, proc_name, inputParaList);
				
				System.out.println("jsonNames:: " +jsonNames);
				
				System.out.println("jsonNames Combine first Response======>>"+jsonNames);
				String jsonNamesArr[]= jsonNames.split("\\}\\]\\[\\{");
				
				jsonNames=jsonNamesArr[0];
				String coverList="";
				if(jsonNamesArr.length>1){
					 coverList =jsonNamesArr[1];
					 coverList="[{"+coverList;
				}
				
				jsonNames=jsonNames+"}]";
				System.out.println("jsonName controller::: "+jsonNames);
				System.out.println("coverList Controller::: "+coverList);
			
				if(!coverList.equals("")){
						String coverId="",coverNoOFItems="",coverVal="", isMandatory="", coverStatus="", coverIdentity="", coverRate="";
						JSONArray coverJson = new JSONArray(coverList);
						for (int table = 0; table < coverJson.length(); ++table) {
					            org.json.JSONObject obj = (org.json.JSONObject) coverJson.get(table);
					            String gicId = "", gicCompany = "";
					            if (!obj.isNull("HB_COVER_ID")) {
					                coverId = "" + obj.getInt("HB_COVER_ID");
					                System.out.println("1111111111 coverId==>>"+coverId);
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
									 if(coverStatus.equalsIgnoreCase("yes"))
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
					            	coverVal = "" + obj.getString("COVER_VAL");
					                System.out.println("1111111111 coverVal==>>"+coverVal);
					            }
					            if (!obj.isNull("COV_RATE")) {
					            	coverRate = "" + obj.getString("COV_RATE");
					            	System.out.println("1111111111 coverVal==>>"+coverVal);
					            }
					            
					            
					            
					            if(coverId.equals("52"))
					            {
					            	relianceIntBean.setIsTPPDCover("true");
					            	relianceIntBean.setTPPDCoverPolicyCoverID("");
					            	relianceIntBean.setTPPDCoverSumInsured(coverVal);
					            	relianceIntBean.setTPPDCoverPackageName("");
					            }
					            if(policyType.equals("1"))
					            {
					            	relianceIntBean.setIsBasicODCoverage("true");
					            	relianceIntBean.setBasicODCoverageIsChecked("true");
					            	relianceIntBean.setBasicODCoverageNoOfItems("");
					            	relianceIntBean.setBasicODCoveragePackageName("");
					            	relianceIntBean.setIsBasicLiability("true");
					            	relianceIntBean.setBasicLiabilityIsChecked("true");
					            	relianceIntBean.setBasicLiabilityNoOfItems("");
					            	relianceIntBean.setBasicLiabilityPackageName("");
					            }
					            
					            if(policyType.equals("2"))
					            {
					            	relianceIntBean.setIsBasicLiability("true");
					            	relianceIntBean.setBasicLiabilityIsChecked(coverStatus);
					            	relianceIntBean.setBasicLiabilityNoOfItems(coverNoOFItems);
					            	relianceIntBean.setBasicLiabilityPackageName("");
					            }
					            if(coverId.equals("25"))
					            {
					            	relianceIntBean.setIsRoadTaxcover("true");
					            	relianceIntBean.setRoadTaxIsChecked("true");
					            	relianceIntBean.setRoadTaxNoOfItems("0");
					            	relianceIntBean.setRoadTaxPackageName("");
					            	relianceIntBean.setRoadTaxSumInsured("2000");
					            	relianceIntBean.setRoadTaxPolicyCoverID("") ;
					            }
					            if(coverId.equals("8"))
					            {
					            	relianceIntBean.setIsNilDepreciation("true");
					            	relianceIntBean.setIsNilDepreciationIsChecked("true");
					            	relianceIntBean.setIsNilDepreciationNoOfItems("0");
					            	relianceIntBean.setIsNilDepreciationPackageName("");
					            	relianceIntBean.setIsNilDepreciationPolicyCoverID("") ;
					            	relianceIntBean.setIsNilDepreciationApplicableRate("0.50") ;
					            }
					            if(coverId.equals("54"))
					            {
					            	relianceIntBean.setIsPAToUnnamedPassengerCovered("true");
					            	relianceIntBean.setPAToUnNamedPassengerIsMandatory(isMandatory);
					            	relianceIntBean.setPAToUnNamedPassengerIsChecked(coverStatus);
					            	relianceIntBean.setPAToUnNamedPassengerNoOfItems(coverNoOFItems);
					            	relianceIntBean.setPAToUnNamedPassengerPackageName("");
					            	relianceIntBean.setPAToUnNamedPassengerPolicyCoverID("") ;
					            	relianceIntBean.setPAToUnNamedPassengerSumInsured(coverVal) ;
					            }
					            if(coverId.equals("22"))
					            {
					            	relianceIntBean.setIsFibreGlassFuelTankFitted("true");
					            	relianceIntBean.setFibreGlassFuelTankIsChecked(coverStatus);
					            	relianceIntBean.setFibreGlassFuelTankNoOfItems(coverNoOFItems);
					            	relianceIntBean.setFibreGlassFuelTankPackageName("");
					            }
					            if(coverId.equals("15"))
					            {
					            	relianceIntBean.setIsNCBRetention("true");
					            	
					            }
					            if(coverId.equals("23"))
					            {
					            	relianceIntBean.setGeoCountries(coverVal);
					            	relianceIntBean.setGeoIsChecked(coverStatus);
					            	relianceIntBean.setIsGeographicalAreaExtended(coverStatus);
					            	relianceIntBean.setIsGeoMandatary(isMandatory);
					            } 
					            if(coverId.equals("56"))
					            {
					            	relianceIntBean.setPaToPaidDriverisSelected(coverStatus);
					            	relianceIntBean.setPaToPaidDriverNoOfItems(coverNoOFItems);
					            	relianceIntBean.setPaToPaidDriverSumInsured(coverVal);
					            	relianceIntBean.setPaToPaidDriverIsChecked(coverStatus);
					            	relianceIntBean.setPaToPaidDriverPackageName("");
					            	relianceIntBean.setPaToPaidDriverPolicyCoverID("");
					            }
					            	
					            if(coverId.equals("20"))
					            {
					            	relianceIntBean.setIsUsedForDrivingTuition("true");
					            	relianceIntBean.setDrivingTuitionCoverageIsChecked(coverStatus);
					            	relianceIntBean.setDrivingTuitionCoverageNoOfItems(coverNoOFItems);
					            	relianceIntBean.setDrivingTuitionCoveragePackageName("");
					            }
					            if(coverId.equals("109"))
					            {
					            	relianceIntBean.setIsAutomobileAssociationMembershipDiscount("true");
					            	relianceIntBean.setIsAutomobileAssociationMember("true");
					            	relianceIntBean.setAaiMemberIsChecked(coverStatus);
					            	relianceIntBean.setAaiMemberNoOfItems(coverNoOFItems);
					            	relianceIntBean.setAaiMemberPackageName("");
					            	relianceIntBean.setAaiMemberIsMandatory(isMandatory);
					            }
					            
					            if(coverId.equals("62"))
					            {
					            System.out.println("Liability to Employees");
					            
						            relianceIntBean.setIsLiabilityToEmployeeCovered("true");
						            relianceIntBean.setLiabilityToEmployeeIsChecked(coverStatus);
						            relianceIntBean.setLiabilityToEmployeeNoOfItems(coverNoOFItems);
						            relianceIntBean.setLiabilityToEmployeePackageName("");
						            relianceIntBean.setLiabilityToEmployeePolicyCoverID("");
					            }
					            if(coverId.equals("53"))
					            {
					            	relianceIntBean.setIsPAToOwnerDriverCoverd("true");
					            	relianceIntBean.setPACoverToOwnerDriverNo(coverNoOFItems) ;
					            }
					            if(coverId.equals("24"))
					            {
					            	relianceIntBean.setIsSpeciallyDesignedForHandicapped(coverStatus);
					            	relianceIntBean.setDesignedForHandiCappedIsChecked(coverStatus );
					            	relianceIntBean.setDesignedForHandiCappedNoOfItems(coverNoOFItems) ;
					            	relianceIntBean.setDesignedForHandiCappedPackageName("");
					            }
					            if(coverId.equals("104"))
					            {
					            	relianceIntBean.setIsvoluntaryDeductible("true");
					            	relianceIntBean.setVoluntaryDeductibleSumInsured(coverVal);
					            	relianceIntBean.setVoluntaryDeductibleIsChecked(coverStatus);
					            	relianceIntBean.setVoluntaryDeductibleNoOfItems(coverNoOFItems);
					            	relianceIntBean.setVoluntaryDeductibleIsManadtory(isMandatory);
					            	relianceIntBean.setVoluntaryDeductiblepolicyCoverId("");
					            	relianceIntBean.setVoluntaryDeductiblePackageName("");
					           }
					            if(coverId.equals("21"))
					            {
					            	System.out.println("anti theft");
					            	relianceIntBean.setIsAntiTheftDeviceFitted("true");
					            	relianceIntBean.setAntiTheftSumInsured(coverVal);
					            	relianceIntBean.setAntiTheftIsChecked(coverStatus);
					            	relianceIntBean.setAntiTheftNoOfItems(coverNoOFItems);
					            	relianceIntBean.setAntiTheftIsManadtory(isMandatory);
					            	relianceIntBean.setAntiTheftpolicyCoverId("");
					            	relianceIntBean.setAntiTheftPackageName("");
					            }
					          if(coverId.equals("108"))
					            {
					        	  	relianceIntBean.setIsbifuelkit("true");
					            	relianceIntBean.setBifuelkitISLpgCng("");
					            	relianceIntBean.setBifuelkitSumInsured(coverVal);
					            	relianceIntBean.setBifuelKitIsChecked("true");
					            }
	 				          if(coverId.equals("9"))
					          {
	 				        	 relianceIntBean.setIsElectricalItem("true");
	 				        	relianceIntBean.setIsElectricalItemSI(coverVal);
					          }
					          if(coverId.equals("2"))
					          {
					        	  relianceIntBean.setIsnonElectricalItem("true");
					        	  relianceIntBean.setIsNonElectricalItemSI(coverVal);
					          }
							}
						}
					
				//*******************************************************************
				/////////////////////////////////////////////////////////
				if(lastname.equals("") && midname.equals("") && forename.equals(""))
				{
					lastname ="Test";
					midname ="Test";
					forename ="Test";
				}
				HashMap prePro = new HashMap<>();
				prePro.put("C_ISNONELECTRICALITEMFITTED", relianceIntBean.getIsnonElectricalItem());
				prePro.put("C_ISELECTRICALITEMFITTED",relianceIntBean.getIsElectricalItem());
				prePro.put("C_ELECTRICALITEMSTOTALSI",relianceIntBean.getIsElectricalItemSI());
				prePro.put("C_NONELECTRICALITEMSTOTALSI",relianceIntBean.getIsNonElectricalItemSI());
			
				prePro.put("R_IDV", idv);
				prePro.put("C_NOOFUNPASCOVERED", relianceIntBean.getPAToUnNamedPassengerNoOfItems());
				prePro.put("C_VOLUNTARYDEDUCTABLEAMOUNT", relianceIntBean.getVoluntaryDeductibleSumInsured());
				prePro.put("C_ISVOLUNTARYDEDUCTABLEOPTED", relianceIntBean.getIsvoluntaryDeductible());
				prePro.put("R_MANUFACTUREYEAR", manufactureyear);
				prePro.put("CLD_CLIENTTYPE", clienttype);
				prePro.put("CLD_LASTNAME", lastname);
				prePro.put("CLD_MIDNAME", midname);
				prePro.put("CLD_FORENAME", forename);
				prePro.put("CLD_CORPORATENAME", corporatename);
				prePro.put("CLD_DOB",dob);
				prePro.put("CLD_GENDER", gender);
				prePro.put("CLD_PHONENO", phoneno);
				prePro.put("CLD_MOBILENO", mobileno);
				prePro.put("CLD_CA_ADDRESSTYPE", cld_ca_addresstype);
				prePro.put("CLD_CA_ADDRESS1", cld_ca_address1);
				prePro.put("CLD_CA_ADDRESS2", cld_ca_address2);
				prePro.put("CLD_CA_ADDRESS3", cld_ca_address3);
				prePro.put("CLD_CA_NEARESTLANDMARK",cld_ca_nearestlandmark);
				prePro.put("CLD_PA_ADDRESSTYPE", cld_pa_addresstype);
				prePro.put("CLD_PA_ADDRESS1", cld_pa_address1);
				prePro.put("CLD_PA_ADDRESS2", cld_pa_address2);
				prePro.put("CLD_PA_ADDRESS3", cld_pa_address3);
				prePro.put("CLD_PA_NEARESTLANDMARK",cld_pa_nearestlandmark);
				prePro.put("CLD_RA_ADDRESSTYPE", cld_ra_addresstype);
				prePro.put("CLD_RA_ADDRESS1", cld_ra_address1);
				prePro.put("CLD_RA_ADDRESS2", cld_ra_address2);
				prePro.put("CLD_RA_ADDRESS3", cld_ra_address3);
				prePro.put("CLD_RA_NEARESTLANDMARK",cld_ra_nearestlandmark);
				prePro.put("CLD_EMAILID", emailid);
				prePro.put("CLD_SALUTATION", salutation);
				prePro.put("R_IDV", idv);
				prePro.put("R_DATEOFPURCHASE",dateofpurchase);
				prePro.put("R_MANUFACTUREMONTH", manufacturemonth);
				prePro.put("pi_manufactureyear", manufactureyear);
				prePro.put("R_ENGINENO", engineno);
				prePro.put("R_CHASSIS", chassis);
				prePro.put("R_ISVEHICLEHYPOTHICATED", isvehiclehypothicated);
				prePro.put("R_FINANCIERNAME", financiername);
				prePro.put("R_FINANCIERADDRESS", financieraddress);
				prePro.put("R_FINANCIERCITY", financiercity);
				prePro.put("R_ISREGADDSAMEASCOMMADD", isregaddsameascommadd);
				prePro.put("R_ISPERADDSAMEASCOMMADD", isperaddsameascommadd);
				prePro.put("R_ISREGADDSAMEASPERADD", isregaddsameasperadd);
				prePro.put("pi_stateofregistrationid", stateofregistrationid);
				prePro.put("V_REGISTRATION_NUMBER", registration_number);
				prePro.put("V_ISNEWVEHICLE", isnewvehicle);
				prePro.put("V_REGISTRATION_DATE",registration_date);
				prePro.put("C_ISBIFUELKIT", relianceIntBean.getIsbifuelkit());
				prePro.put("C_BFK_ISLPGCNG", islpgcng);
				prePro.put("C_PAO_NOMINEENAME",pao_nomineename);
				prePro.put("C_PAO_NOMINEEDOB",pao_nomineedob);
				prePro.put("C_BFK_ISCHECKED", relianceIntBean.getBifuelKitIsChecked());
				prePro.put("PI_PREVYEARPOLICYNO", prevpolicyno);
				prePro.put("PI_PREVYEARPOLICYSTARTDATE",prevstartdt);
				prePro.put("PIPREVYEARPOLICYENDDATE",prevenddt);
				prePro.put("NCB_ISNCBAPPLICABLE", ncbisapp);
				prePro.put("NCB_NCBELIGIBILITYCRITERIA", ncbeligicrit);
				prePro.put("pi_voldedamt", voldedamt);
				prePro.put("pi_eleamt", relianceIntBean.getEleamt());
				prePro.put("pi_noneleamt", relianceIntBean.getNoneleamt());
				prePro.put("pi_unpasno", relianceIntBean.getUnpasno());
				prePro.put("pi_unpasamt", relianceIntBean.getUnpasamt());
				prePro.put("C_ISANTITHEFTDEVICEFITTED", relianceIntBean.getIsAntiTheftDeviceFitted());
				prePro.put("C_ISAUTOAMEM", relianceIntBean.getIsAutomobileAssociationMember());
				prePro.put("C_ISTPPDCOVER", relianceIntBean.getIsTPPDCover());
				
				prePro.put("C_ISPATOUNPASCOVERED", relianceIntBean.getIsPAToUnnamedPassengerCovered());
				prePro.put("C_PAU_POLICYCOVERID", relianceIntBean.getPAToUnNamedPassengerPolicyCoverID());
				prePro.put("C_PAU_NOOFITEMS", relianceIntBean.getPAToUnNamedPassengerNoOfItems());
				prePro.put("C_PAU_SUMINSURED", relianceIntBean.getPAToUnNamedPassengerSumInsured());
				prePro.put("C_PAU_ISMANDATORY", relianceIntBean.getPAToUnNamedPassengerIsMandatory());
				prePro.put("C_PAU_ISCHECKED", relianceIntBean.getPAToUnNamedPassengerIsChecked());
				prePro.put("C_PAU_PACKAGENAME", relianceIntBean.getPAToUnNamedPassengerPackageName());
				prePro.put("C_UNPASSI", relianceIntBean.getPAToUnNamedPassengerSumInsured());
				prePro.put("pi_antitheftno", relianceIntBean.getAntitheftno());
				prePro.put("pi_antitheftname", relianceIntBean.getAntitheftname());
				prePro.put("pi_automemname", relianceIntBean.getAutomemname());
				prePro.put("pi_vdpolcovid", relianceIntBean.getVdpolcovid());
				prePro.put("pi_vdamt", relianceIntBean.getVdamt());
				prePro.put("pi_vdno", relianceIntBean.getVdno());
				prePro.put("pi_vdname", relianceIntBean.getVdname());
				prePro.put("pi_patounpasname", relianceIntBean.getPatounpasname());
				prePro.put("C_NEL_ITEMSID", "");
				prePro.put("C_NEL_POLICYID", "");
				prePro.put("C_NEL_SERIALNO", "");
				prePro.put("C_NEL_MAKEMODEL", "");
				prePro.put("C_NEL_PREMIUM", "");
				prePro.put("C_NEL_DESCRIPTION", "");
				prePro.put("C_NEL_CATEGORY", "");
				prePro.put("C_NEL_ACCESSORYSLNO", "");
				prePro.put("C_NEL_SUMINSURED", "");
				prePro.put("C_EL", "0");
				prePro.put("C_NEL", "0");
				prePro.put("C_EL_ITEMSID", "0");
				prePro.put("C_EL_POLICYID", "0");
				prePro.put("C_EL_SERIALNO", "0");
				prePro.put("C_EL_MAKEMODEL", "0");
				prePro.put("C_EL_PREMIUM", "0");
				prePro.put("C_EL_DESCRIPTION", "0");
				prePro.put("C_EL_ACCESSORYSLNO", "0");
				prePro.put("C_EL_SUMINSURED", "0");
				prePro.put("C_ISBASICODCOVERAGE", relianceIntBean.getIsBasicODCoverage());
				prePro.put("C_ISBASICLIABILITY", relianceIntBean.getIsBasicLiability());
				prePro.put("C_ISPATOOWNERDRIVERCOVERD", relianceIntBean.getIsPAToOwnerDriverCoverd());
				prePro.put("C_VD_POLICYCOVERID", relianceIntBean.getVoluntaryDeductiblepolicyCoverId());
				prePro.put("C_VD_SumInsured", relianceIntBean.getVoluntaryDeductibleSumInsured());
				prePro.put("C_VD_ISMANDATORY", relianceIntBean.getVoluntaryDeductibleIsManadtory());
				prePro.put("C_VD_ISCHECKED", relianceIntBean.getVoluntaryDeductibleIsChecked());
				prePro.put("C_VD_PACKAGENAME", relianceIntBean.getVoluntaryDeductiblePackageName());
				prePro.put("C_ATDD_ISMANDATORY", relianceIntBean.getAntiTheftIsManadtory());
				prePro.put("C_ATDD_ISCHECKED", relianceIntBean.getAntiTheftIsChecked());
				prePro.put("C_ATDD_NOOFITEMS", relianceIntBean.getAntiTheftNoOfItems());
				prePro.put("C_ATDD_PACKAGENAME", relianceIntBean.getAntiTheftPackageName());
				prePro.put("C_AAMD_ISMANDATORY", relianceIntBean.getAaiMemberIsMandatory());
				prePro.put("C_AAMD_ISCHECKED", relianceIntBean.getAaiMemberIsChecked());
				prePro.put("C_AAMD_PACKAGENAME", relianceIntBean.getAaiMemberPackageName());
				prePro.put("geoExtension", relianceIntBean.getIsGeographicalAreaExtended());
				prePro.put("geoCountries", relianceIntBean.getGeoCountries());
				prePro.put("CountryMandatory", relianceIntBean.getIsGeoMandatary());
				prePro.put("countryIsChecked", relianceIntBean.getGeoIsChecked());
				prePro.put("Productname", str_productname);
				
				Gson gson = new Gson();
				String json = gson.toJson(prePro);
				jsonNames = json + "" + jsonNames ;
				jsonNames= "[" + jsonNames + "]"; 
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
				System.out.println("jsonNames:::" +jsonNames);
				String xmlFile = xmlFile(jsonNames);
				
			
				switch (relianceIntBean.getRequest_for()) {
				case "premium":
					hashmap.clear();
					relianceIntBean.setMethod("PremiumCalulationForMotor");
					System.out.println("in premium case");
					String s = PostInfoToAPI(xmlFile, relianceIntBean.getMethod());
					System.out.println("Case1 premium--------->>" + s);
					hashmapPremium = new HashMap<>();
					if(!s.isEmpty()){
						hashmapPremium = readResponse(s);
						relianceIntBean.setProcedureName("PR_PREMIUM");
						savePremiumPropsalData(hashmapPremium,relianceIntBean);
						
//						hashmapPremium.remove("geoCountries");
						
						String jsonNew = removeGeoExtension(jsonNames);
						
						
						if(jsonNew.contains("[")){
							jsonNew = jsonNew.replace("[", "");
						}
						if(jsonNew.contains("]")){
							jsonNew = jsonNew.replace("]", "");
					 	}
						

							
							
						jsonNew = jsonNew.substring(1, jsonNew.length()-1);           //remove curly brackets
						System.out.println("jsonNames:FDF" + jsonNew);
						String[] keyValuePairs1 = jsonNew.split(",");              //split the string to creat key-value pairs
						System.out.println("keyValuePairs31::" + keyValuePairs1);
						System.out.println("keyValuePairs1:::" + keyValuePairs1.length);
						for(String pair : keyValuePairs1)                        //iterate over the pairs
						{
						    String[] entry = pair.split(":");                   //split the pairs to get key and value 
						    hashmapinputs.put((entry[0].trim()).replaceAll("\"", ""), (entry[1].trim()).replaceAll("\"", ""));          //add them to the hashmap and trim whitespaces
						}
						hashmapinputs.put("BUSINESSTYPE", str_businessType);
						hashmapinputs.put("GICID", relianceIntBean.getMotorGroupResponseGicId());
						System.out.println("hashmapinputs jsonResponse From DB=================>>"+hashmapinputs);
						System.out.println("hashmapPremium  Server Response:::" +hashmapPremium);
						hashmap.putAll(hashmapPremium);
						hashmap.putAll(hashmapinputs);
						System.out.println("hashmap complete::: "+hashmap);
					}
				
					else{
						hashmap.put("XMLError", "Error");
					}
					
					break;
				case "proposal":
					hashmap.clear();
					relianceIntBean.setMethod("ProposalCreationForMotor");
					System.out.println("in proposal case");
					String s1 = PostInfoToAPI(xmlFile, relianceIntBean.getMethod());
					System.out.println("Case2 proposal--------->>" + s1);
					hashmapProposal= new HashMap<>();
					if(!s1.isEmpty()){
					hashmapProposal = readResponse(s1);
					System.out.println("Case2 hashmap--------->>" + hashmapProposal);
					System.out.println("motorGroupResponseGroupId===>>"+relianceIntBean.getMotorGroupResponseGroupId());
					System.out.println("motorGroupResponseSessionId===>>"+relianceIntBean.getMotorGroupResponseSessionId());
					System.out.println("motorGroupResponseGicId===>>"+relianceIntBean.getMotorGroupResponseGicId());
					//--------------------------------------------
					relianceIntBean.setProcedureName("PR_PROPOSAL");
					
					
					savePremiumPropsalData(hashmapProposal, relianceIntBean);
					
					Iterator entries = hashmapProposal.entrySet().iterator();
					while (entries.hasNext()) {
					Map.Entry entrypro = (Map.Entry) entries.next();
					String mapKeyPro = (String)entrypro.getKey();
						if (mapKeyPro.equalsIgnoreCase("proposalNo")) {
							relianceIntBean.setProposalNo((null == entrypro.getValue()) ? "" : entrypro.getValue().toString());
						}
						if (mapKeyPro.equalsIgnoreCase("FinalPremium")) {
							String ProposalAmount = (null == entrypro.getValue()) ? "" : entrypro.getValue().toString();
							relianceIntBean.setProposalAmount(ProposalAmount);
						}
					}
					if(jsonNames.contains("[")){
						jsonNames = jsonNames.replace("[", "");
					}
					if(jsonNames.contains("]")){
						jsonNames = jsonNames.replace("]", "");
				 	}
					
					jsonNames = jsonNames.substring(1, jsonNames.length()-1);           //remove curly brackets
					String[] keyValuePairs3 = jsonNames.split(",");              //split the string to creat key-value pairs
					
					for(String pair : keyValuePairs3)                        //iterate over the pairs
					{
					    String[] entry1 = pair.split(":");                   //split the pairs to get key and value 
					    hashmapinputs.put((entry1[0].trim()).replaceAll("\"", ""), (entry1[1].trim()).replaceAll("\"", ""));          //add them to the hashmap and trim whitespaces
					}
					hashmapinputs.put("BUSINESSTYPE", str_businessType);
					hashmapinputs.put("GICID", relianceIntBean.getMotorGroupResponseGicId());
					System.out.println("hashmapinputs jsonResponse From DB=================>>"+hashmapinputs);
					System.out.println("hashmapProposal  Server Response:::" +hashmapProposal);
					
					hashmap.putAll(hashmapProposal);
					hashmap.putAll(hashmapinputs);
					System.out.println("hashmap Response:::" +hashmap);
					}
					else{
						hashmap.put("XMLError", "Error");
					}
					break;
				
				
				
				case "FetchAccountDetails":	
					hashmap.clear();
					System.out.println("proposal no is:  " +relianceIntBean.getProposalNo());
					relianceIntBean.setMethod("FetchAccountDetails");
					fetchPolicy = new JSONObject(); 
					
					// System.out.println("jsonNames"+jsonNames);
					JSONArray jsonarray1 = new JSONArray(jsonNames);
					// System.out.println("jsonarray"+jsonarray);
					for (int i = 0; i < jsonarray1.length(); i++) {
						JSONObject jsonResult = jsonarray1.getJSONObject(i);
						relianceIntBean.setUsername(jsonResult.getString("SOURCESYSTEMID").trim());
						relianceIntBean.setPassword(jsonResult.getString("AUTHTOKEN").trim());
						relianceIntBean.setProductCode(jsonResult.getString("P_PRODUCTCODE").trim());
					}
				
					fetchPolicy.put("UserID", relianceIntBean.getUsername());
					fetchPolicy.put("Password", relianceIntBean.getPassword());
					
					String s4 = PostInfoToAPIJSON(fetchPolicy, relianceIntBean.getMethod());
					System.out.println("case4 FetchAccountDetails---->>" + s4);
					
					s4 = s4.substring(1, s4.length()-1);           //remove curly brackets
					String[] keyValuePairs2 = s4.split(",");              //split the string to creat key-value pairs

					for(String pair : keyValuePairs2)                        //iterate over the pairs
					{
					    String[] entry = pair.split(":");                   //split the pairs to get key and value 
					    hashmap.put((entry[0].trim()).replaceAll("\"", ""), (entry[1].trim()).replaceAll("\"", ""));          //add them to the hashmap and trim whitespaces
					}
					System.out.println("Case4 Hashmap=================>>"+hashmap);
					
					Iterator iterator = hashmap.entrySet().iterator();
					System.out.println("iterator============>>"+iterator);
					while (iterator.hasNext()) {
						System.out.println("11111111111111111111111111111111111111");
					    Map.Entry mapEntry = (Map.Entry) iterator.next();
					    String mapKeyStr = (String)mapEntry.getKey();
					   	if (mapKeyStr.equalsIgnoreCase("PaymentMode")) {
					   		String str = (null == mapEntry.getValue()) ? "" : mapEntry.getValue()
										.toString();
					   		if(str.equalsIgnoreCase("CD")){
					   			relianceIntBean.setPaymentMode("2");
					   		}else if(str.equalsIgnoreCase("CDT")){
					   			relianceIntBean.setPaymentMode("3");
					   		}else{
					   			relianceIntBean.setPaymentMode("1");
					   		}
					   		System.out.println("**************************************get Payment Mode Variable value==>>"+relianceIntBean.getPaymentMode());
						}
					}
					
				break;

				default:
					break;
				}
			System.out.println("hasmap:"+ hashmap );
			
			} catch (Exception e) {
				System.out.println("//////////////////////////" + e);
			}
			
			
			ObjectMapper mapperObj = new ObjectMapper();
			String jsonFinalResp = mapperObj.writeValueAsString(hashmap);
			jsonFinalResp= "[" + jsonFinalResp + "]" ;
	        System.out.println("jsonFinalResp==>>"+jsonFinalResp);
	        
			return jsonFinalResp;

		}


		
		
		@RequestMapping(value = "user/ReliancePayment" ,method = RequestMethod.GET)
	public org.springframework.web.servlet.ModelAndView callJsp(HttpServletRequest request,
			HttpServletResponse response) {
		
	     	String paymentMode= request.getParameter("paymentMode");
			String Responseurl= request.getParameter("Responseurl");
			String username= request.getParameter("username");
			String password= request.getParameter("password");
			String proposalNo= request.getParameter("proposalNo");
			System.out.println("proposalNo " +proposalNo);
			String ProposalAmount= request.getParameter("ProposalAmount");
			relianceIntBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
			relianceIntBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
			relianceIntBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
			relianceIntBean.setQuoteNo(request.getParameter("quoteNo"));
			relianceIntBean.setUserId(request.getParameter("userId"));
			relianceIntBean.setUserDesc(request.getParameter("userDesc"));
			relianceIntBean.setBranchId(request.getParameter("branchId"));
			relianceIntBean.setIPAddress(request.getParameter("IPAddress"));
			
			bean.setBranchId(relianceIntBean.getBranchId());
			bean.setUserId(relianceIntBean.getUserId());
			bean.setUserDesc(relianceIntBean.getUserDesc());
			bean.setGroupId(relianceIntBean.getMotorGroupResponseGroupId());
			bean.setSessionId(relianceIntBean.getMotorGroupResponseSessionId());
			bean.setGicId(relianceIntBean.getMotorGroupResponseGicId());
			bean.setResponseType("2");

			
			HashMap<String,String> payRequest=new HashMap<>();
			payRequest.put("UserID",username);
			payRequest.put("ProposalNo", proposalNo);
			payRequest.put("PaymentType", paymentMode);
			payRequest.put("Responseurl",Responseurl);
			payRequest.put("ProposalAmount", ProposalAmount);
			payRequest.put("actionUrl", "http://rzonews.reliancegeneral.co.in:91/PaymentIntegration/PaymentIntegration");
			System.out.println("payRequest" +payRequest);
	        org.springframework.web.servlet.ModelAndView andView=new org.springframework.web.servlet.ModelAndView("common/ReliancePayment");
	        andView.addObject("data", payRequest);
	        return andView;
	}
	
	
	@RequestMapping(value ="/ReliancePaymentStatus", method = RequestMethod.GET)  
	public org.springframework.web.servlet.ModelAndView TransactionStatusPost(HttpServletRequest request,
			HttpServletResponse response) {
		String jsonResponse = null;
		
		String output= request.getParameter("Output");
		System.out.println("output" +output);
	
		//When string is split by | then it will return characters so it is split by "\\|"
		String[] outputArr = output.split("\\|");
		String statusId="", PolicyNumber= "",TransactionNumber="",Optionalvalue="",GatewayName= "", ProposalNo="",TransactionStatus="";
		System.out.println(outputArr);
		if(outputArr[0].equals("0"))
		{
			System.out.println(outputArr.length);
			System.out.println("in if");
			 statusId= outputArr[0];
			 PolicyNumber= outputArr[1];
			 TransactionNumber= outputArr[2];
			 Optionalvalue=outputArr[3];
			 GatewayName= outputArr[4];
			 ProposalNo= outputArr[5];
			 TransactionStatus= outputArr[7];
		}else{
			System.out.println(outputArr.length);
			System.out.println("in else");
			 statusId= outputArr[0];
			 PolicyNumber= outputArr[1];
			 TransactionNumber= outputArr[2];
			 Optionalvalue=outputArr[3];
			 GatewayName= outputArr[4];
			 ProposalNo= outputArr[5];
			 TransactionStatus= outputArr[6];
		}
		
		System.out.println("statusId"+statusId+"PolicyNumber-"+PolicyNumber+"TransactionNumber"+TransactionNumber+"Optionalvalue"+Optionalvalue);
		System.out.println("GatewayName-"+GatewayName+"ProposalNo-"+ProposalNo+"TransactionStatus"+TransactionStatus);

		payResponse=new HashMap<>();
		payResponse.put("statusId", statusId);
		payResponse.put("PolicyNumber", PolicyNumber);
		payResponse.put("TransactionNumber", TransactionNumber);
		payResponse.put("Optionalvalue", Optionalvalue);
		payResponse.put("GatewayName", GatewayName);
		payResponse.put("ProposalNo", ProposalNo);
		payResponse.put("TransactionStatus", TransactionStatus);
		System.out.println("payResponse---"+payResponse);
		
		String procedureName = "PR_PROPOSAL";
		Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(relianceIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(relianceIntBean.getMotorGroupResponseGicId()), relianceIntBean.getMotorGroupResponseSessionId(),
				2, payResponse, relianceIntBean.getIPAddress(), relianceIntBean.getUserId(), relianceIntBean.getBranchId(),relianceIntBean.getUserDesc(), procedureName );
	
		System.out.println("In transaction status for Rel");
		org.springframework.web.servlet.ModelAndView andView=new org.springframework.web.servlet.ModelAndView("common/ReliancePaymentStatus");
		andView.addObject("data", payResponse);
		return andView;
	
	}  
	
private ModelAndView handleException(){
     return new ModelAndView("/WEB-INF/view/common/error.jsp");
}

	private String removeGeoExtension(String jsonNames) throws JSONException {

//		jsonNames= "{" + jsonNames +"}" ;
		System.out.println("jsonNames:: " +jsonNames);
		JSONArray jsonObj = new JSONArray(jsonNames);
		for(int i=0;i<jsonObj.length(); i++)
		{
			  JSONObject obj = (JSONObject) jsonObj.get(i);
		
			if(!obj.isNull("geoCountries")){
					
				obj.remove("geoCountries");
			}

		}
		String jsonName= jsonObj.toString();
		return jsonName;
	
	}

	private HashMap savePremiumPropsalData(HashMap<String, String> hashmapPremium2,
			RelianceIntegrationBean relBean) {
		
		System.out.println("hashmapPremium2:: " +hashmapPremium2);
		HashMap tax= new HashMap();
//			HashMap tax1Map= new HashMap<>();
			/////////////Remove Tax////////////////////
			String rate1="",taxName1="",amount1="";
			for(int index=1;index<=4 ;index++)
			{
				Iterator itr = hashmapPremium2.entrySet().iterator();
//				System.out.println("In for loop for " + index+ " times");
//				System.out.println("itr.hasNext() " + itr.hasNext());
				
				while (itr.hasNext()) {
//					System.out.println("in while loop");
					Map.Entry entrytax = (Map.Entry) itr.next();
					String mapKey = (String)entrytax.getKey();
					if (mapKey.equalsIgnoreCase("tax"+index)) {
//						System.out.println("tax"+index);
						tax = (HashMap) ((null == entrytax.getValue()) ? "" : entrytax.getValue());
					System.out.println("tax mapping:::" +tax);
					Iterator tax1_1 = tax.entrySet().iterator();
					while (tax1_1.hasNext()) {
						Map.Entry<String, String> entrytax1 = (Entry<String, String>) tax1_1.next();
						String mapKeytax1 = (String)entrytax1.getKey();
							if (mapKeytax1.equalsIgnoreCase("Rate")) {
								rate1 = ((null == entrytax1.getValue()) ? "" : entrytax1.getValue()).toString();
							
							}
							if (mapKeytax1.equalsIgnoreCase("TaxName")) {
								taxName1 = ((null == entrytax1.getValue()) ? "" : entrytax1.getValue()).toString();
							}
							if (mapKeytax1.equalsIgnoreCase("Amount")) {
								amount1 = ((null == entrytax1.getValue()) ? "" : entrytax1.getValue()).toString();
							}
							
						}
					
					}
					
				}
				HashMap tax1Map= new HashMap<>();
				tax1Map.put(taxName1+"_rate", rate1);
				tax1Map.put(taxName1+"_taxName", taxName1);
				tax1Map.put(taxName1+"_amount", amount1);
				hashmapPremium2.putAll(tax1Map);
//				System.out.println("tax map:::"+hashmapPremium);
				}
			
			////////////////Remove covers//////////////////////
			List coverlist = new ArrayList<>();
			System.out.println("cover List");
			Iterator coverListItr = hashmapPremium2.entrySet().iterator();
			while (coverListItr.hasNext()) {
				Map.Entry entry = (Map.Entry) coverListItr.next();
				String mapKey = (String)entry.getKey();
					if (mapKey.equalsIgnoreCase("coverlist")) {
						coverlist = (List) ((null == entry.getValue()) ? "" : entry.getValue());
						System.out.println("coverList if>>>"+ coverlist);
					}
				}
			
			for(int index=1;index<=coverlist.size();index++)
			{
				HashMap cover_Map = new HashMap<>();
			
				String 	OriginalPremium="",SumInsured="", EndorsementPremium="",CoverageName="",Premium="",
				PremiumDifference="",CoverID="",Premium2Year="",Premium3Year="";
				Iterator coverListItr2 = hashmapPremium2.entrySet().iterator();
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
							if (mapKeycover.equalsIgnoreCase("OriginalPremium")) {
								OriginalPremium = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"_OriginalPremium"+ OriginalPremium);
							}
//							System.out.println("coversMap.getKey()"+ coversMap.getKey());
							
							if (coversMap.getKey().equalsIgnoreCase("SumInsured")) {
								SumInsured = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"_SumInsured"+ SumInsured);
							}
							if (mapKeycover.equalsIgnoreCase("EndorsementPremium")) {
								EndorsementPremium = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"EndorsementPremium"+ EndorsementPremium);
							}
							if (mapKeycover.equalsIgnoreCase("CoverageName")) {
								CoverageName = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"CoverageName"+ CoverageName);
							}
							if (mapKeycover.equalsIgnoreCase("Premium")) {
								Premium = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"Premium"+ Premium);
							}
							if (mapKeycover.equalsIgnoreCase("PremiumDifference")) {
								PremiumDifference = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"PremiumDifference"+ PremiumDifference);
							}
							if (mapKeycover.equalsIgnoreCase("CoverID")) {
								CoverID = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"CoverID"+ CoverID);
							}
							if (mapKeycover.equalsIgnoreCase("Premium3Year")) {
								Premium3Year = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"Premium3Year"+ Premium3Year);
							}
							if (mapKeycover.equalsIgnoreCase("Premium2Year")) {
								Premium2Year = ((null == coversMap.getValue()) ? "" : coversMap.getValue()).toString();
//								System.out.println("cover"+index+"Premium2Year"+ Premium2Year);
							}
					 }
					 }	
						}
				 HashMap coverMap= new HashMap<>();
					
					coverMap.put("OriginalPremium"+CoverageName.replace(" ", ""), OriginalPremium);
					coverMap.put("SumInsured"+CoverageName.replace(" ", ""), SumInsured);
					coverMap.put("EndorsementPremium"+CoverageName.replace(" ", ""), EndorsementPremium);
					coverMap.put("CoverageName"+CoverageName.replace(" ", ""), CoverageName);
					coverMap.put("Premium"+CoverageName.replace(" ", ""), Premium);
					coverMap.put("PremiumDifference"+CoverageName.replace(" ", ""), PremiumDifference);
					coverMap.put("CoverID"+CoverageName.replace(" ", ""), CoverID);
					coverMap.put("Premium2Year"+CoverageName.replace(" ", ""), Premium2Year);
					coverMap.put("Premium3Year"+CoverageName.replace(" ", ""), Premium3Year);
					
//					System.out.println("coverMap::"+ coverMap);
					hashmapPremium2.putAll(coverMap);
//					System.out.println("CoverMap" + hashmapProposal);
//					if (mapKeytax1.equalsIgnoreCase("cover"+index)) {
//						itr.remove();
//					}
				 		
					}
		
			//////////////////////////////////////////////////////
			Iterator coverListItr1 = hashmapPremium2.entrySet().iterator();
		
			while (coverListItr1.hasNext()) {
			Map.Entry entry = (Map.Entry) coverListItr1.next();
			String mapKey = (String)entry.getKey();
			
				for(int index1=1;index1<=coverlist.size()+1;index1++)
				{
					if (mapKey.equalsIgnoreCase("cover"+index1)) {
						coverListItr1.remove();
//						System.out.println("coverListItr1 in 1");
					} 
					
				}
				if (mapKey.equalsIgnoreCase("coverlist")) {
					coverListItr1.remove();
				}
				if (mapKey.equalsIgnoreCase("tax1")) {
					coverListItr1.remove();
				}
				if (mapKey.equalsIgnoreCase("tax2")) {
					coverListItr1.remove();
				}
				if (mapKey.equalsIgnoreCase("tax3")) {
					coverListItr1.remove();
				}
				if (mapKey.equalsIgnoreCase("tax4")) {
					coverListItr1.remove();
				}
			}
			String errorMsg="";
			Iterator iterateorMap = hashmapPremium2.entrySet().iterator();
			while (iterateorMap.hasNext()) {
			Map.Entry entry = (Map.Entry) iterateorMap.next();
			String mapKey = (String)entry.getKey();
				if (mapKey.equalsIgnoreCase("ErrorMessages")) {
					errorMsg = (null == entry.getValue()) ? "" : entry.getValue().toString();
				}
			}
			System.out.println("hashmapPremium2 final map:::"+ hashmapPremium2);
			if(errorMsg.equals("")){
			if(relianceIntBean.getMotorGroupResponseGicId().equals("2")){
//				
//				MotorResponseBean objResult = integrationSaveResponseDao.saveRelianceProposalDataDump(hashmapProposal, bean);
	////
//				MotorResponseBean objResult1 = integrationSaveResponseDao.saveRelianceProposalDataProcess(bean);
				
				Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(relianceIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(relianceIntBean.getMotorGroupResponseGicId()), relianceIntBean.getMotorGroupResponseSessionId(),
						1, hashmapPremium2, relianceIntBean.getIPAddress(), relianceIntBean.getUserId(), relianceIntBean.getBranchId(),
						relianceIntBean.getUserDesc(), relBean.getProcedureName());
				
			}
			}
			return hashmapPremium2;
		
	}

	@RequestMapping(value = "/motorCalculatorPolicyPDF", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody  String getPolicyPDF(HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException, JSONException {
		JSONObject paymentExternal = null;
		String PolicyNo= request.getParameter("PolicyNo");
		String ProductCode= request.getParameter("ProductCode");
		String policyUrl = GenerateSchedule(PolicyNo, ProductCode);
		HashMap json = new HashMap<>();
		json.put("policyUrl", policyUrl);
		Gson gson = new Gson();
	    String url = gson.toJson(json);
	    System.out.println(url);
		return url;
		
	}
	
	public static void convertByteArrayToDoc(byte[] b) {

        OutputStream out;
        try {
            out = new FileOutputStream("D:/1.pdf");
            out.write(b);
            
//            out.close();
            System.out.println("write success");
        } catch (Exception e) {
            System.out.println(e);
        }
	}

	@RequestMapping(value = "/motorCalculatorRelPayment", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody void makeRelPayment(HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException, JSONException {
		JSONObject paymentExternal = null;
		
				String username= request.getParameter("username");
				String password= request.getParameter("password");
				String proposalNo= request.getParameter("proposalNo");
				String paymentMode= request.getParameter("paymentMode");
				String request_for= request.getParameter("request_for");
				relianceIntBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
				relianceIntBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
				relianceIntBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
				String quoteNo=request.getParameter("quoteNo");
				relianceIntBean.setUserId(request.getParameter("userId"));
				relianceIntBean.setUserDesc(request.getParameter("userDesc"));
				relianceIntBean.setBranchId(request.getParameter("branchId"));
				String IPAddress=request.getParameter("IPAddress");
				System.out.println("motorGroupResponseGicId:::" +relianceIntBean.getMotorGroupResponseGicId());
				hashmap.clear();
				if(request_for.equalsIgnoreCase("paymentExternal")){
					relianceIntBean.setMethod("PolicyMakeLiveExternal");
				}
				paymentExternal = new JSONObject(); 
				
				paymentExternal.put("UserID", username);
				paymentExternal.put("Password", password);
				paymentExternal.put("ProposalNo", proposalNo);
				paymentExternal.put("PaymentType", paymentMode);
					
				System.out.println("paymentExternal" +paymentExternal);
				String s3 = PostInfoToAPIJSON(paymentExternal, relianceIntBean.getMethod());
				System.out.println("case3 payment External-------->>" + s3);
				bean.setBranchId(relianceIntBean.getBranchId());
				bean.setUserId(relianceIntBean.getUserId());
				bean.setUserDesc(relianceIntBean.getUserDesc());
				bean.setGroupId(relianceIntBean.getMotorGroupResponseGroupId());
				bean.setSessionId(relianceIntBean.getMotorGroupResponseSessionId());
				bean.setGicId(relianceIntBean.getMotorGroupResponseGicId());
				bean.setResponseType("2");
				s3 = s3.substring(1, s3.length()-1);           //remove curly brackets
				String[] keyValuePairs = s3.split(",");              //split the string to creat key-value pairs

				for(String pair : keyValuePairs)                        //iterate over the pairs
				{
				    String[] entry = pair.split(":");                   //split the pairs to get key and value 
				    hashmap.put((entry[0].trim()).replaceAll("\"", ""), (entry[1].trim()).replaceAll("\"", ""));   
				}
				System.out.println("Case3 Hashmap=================>>"+hashmap);

				String procedureName = "PR_PROPOSAL";
				Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(relianceIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(relianceIntBean.getMotorGroupResponseGicId()), relianceIntBean.getMotorGroupResponseSessionId(),
						2, hashmap, IPAddress, relianceIntBean.getUserId(), relianceIntBean.getBranchId(),relianceIntBean.getUserDesc(), procedureName );
		
	}
	
	
	
	public String GenerateSchedule(String policyNumber,String productCode){
	Boolean boolFlag = false;
	String str="http://rzonews.reliancegeneral.co.in:91/API/Service/GeneratePolicyschedule?"+"PolicyNo="+policyNumber +"&ProductCode="+ productCode;
		return str;
	
	}

	@RequestMapping(value = "user/motorCalculatorCoverage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getcoverDetails(HttpServletRequest request,
			HttpServletResponse response) throws JDOMException, IOException {
		String coverageDetails = null;
		relianceIntBean= new RelianceIntegrationBean();
		try {
			String pkg_name = request.getParameter("pkg_name");

			System.out.println("pkg_name==>" + pkg_name);

			String proc_name = request.getParameter("proc_name");

			System.out.println("proc_name==>" + proc_name);

			String table_name = request.getParameter("table_name");

			 relianceIntBean.setRequest_for(request.getParameter("request_for"));
			 System.out.println("request_for" +relianceIntBean.getRequest_for());
			String clienttype = request.getParameter("clienttype");
			String lastname = request.getParameter("last_name");
			String midname = request.getParameter("midname");
			String forename = request.getParameter("forename");
			String corporatename = request.getParameter("corporatename");
			String occupationid = request.getParameter("occupationid");
			
			String gender = request.getParameter("gender");
			String phoneno = request.getParameter("phoneno");
			String mobileno = request.getParameter("mobileno");
			String clientaddress = request.getParameter("clientaddress");
			String cld_ca_addresstype = request
					.getParameter("cld_ca_addresstype");
			String cld_ca_address1 = request.getParameter("cld_ca_address1");
			String cld_ca_address2 = request.getParameter("cld_ca_address2");
			String cld_ca_address3 = request.getParameter("cld_ca_address3");
			String cld_ca_cityid = request.getParameter("cld_ca_cityid");
			String cld_ca_districtid = request
					.getParameter("cld_ca_districtid");
			String cld_ca_stateid = request.getParameter("cld_ca_stateid");
			String cld_ca_pincode = request.getParameter("cld_ca_pincode");
			String cld_ca_country = request.getParameter("cld_ca_country");
			String cld_ca_nearestlandmark = request
					.getParameter("cld_ca_nearestlandmark");
			String cld_pa_addresstype = request
					.getParameter("cld_pa_addresstype");
			String cld_pa_address1 = request.getParameter("cld_pa_address1");
			String cld_pa_address2 = request.getParameter("cld_pa_address2");
			String cld_pa_address3 = request.getParameter("cld_pa_address3");
			String cld_pa_cityid = request.getParameter("cld_pa_cityid");
			String cld_pa_districtid = request
					.getParameter("cld_pa_districtid");
			String cld_pa_stateid = request.getParameter("cld_pa_stateid");
			String cld_pa_pincode = request.getParameter("cld_pa_pincode");
			String cld_pa_country = request.getParameter("cld_pa_country");
			String cld_pa_nearestlandmark = request
					.getParameter("cld_pa_nearestlandmark");
			String cld_ra_addresstype = request
					.getParameter("cld_ra_addresstype");
			String cld_ra_address1 = request.getParameter("cld_ra_address1");
			String cld_ra_address2 = request.getParameter("cld_ra_address2");
			String cld_ra_address3 = request.getParameter("cld_ra_address3");
			String cld_ra_cityid = request.getParameter("cld_ra_cityid");
			String cld_ra_districtid = request
					.getParameter("cld_ra_districtid");
			String cld_ra_stateid = request.getParameter("cld_ra_stateid");
			String cld_ra_pincode = request.getParameter("cld_ra_pincode");
			String cld_ra_country = request.getParameter("cld_ra_country");
			String cld_ra_nearestlandmark = request
					.getParameter("cld_ra_nearestlandmark");
			String emailid = request.getParameter("emailid");
			String salutation = request.getParameter("salutation");
			String maritalstatus = request.getParameter("maritalstatus");
			String nationality = request.getParameter("nationality");
			String p_businesstype = request.getParameter("p_businesstype");
			String productid = request.getParameter("str_prod");
			String vehid = request.getParameter("str_veh");
			String modelid = request.getParameter("str_mod");
			String varid = request.getParameter("str_var");
			String idv = request.getParameter("idv");
			String dateofpurchase = request.getParameter("dateOfPurchase");
			String manufacturemonth = request.getParameter("manufacturemonth");
			String manufactureyear = request.getParameter("manufactureyear");
			String engineno = request.getParameter("engineno");
			String chassis = request.getParameter("chassis");
			String isvehiclehypothicated = request
					.getParameter("isvehiclehypothicated");
			String financetypeid = request.getParameter("financetypeid");
			String financiername = request.getParameter("financiername");
			String financieraddress = request.getParameter("financieraddress");
			String financiercity = request.getParameter("financiercity");
			String isregaddsameascommadd = request
					.getParameter("isregaddsameascommadd");
			String isperaddsameascommadd = request
					.getParameter("isperaddsameascommadd");
			String isregaddsameasperadd = request
					.getParameter("isregaddsameasperadd");
			String stateofregistrationid = request.getParameter("str_state");
			String rto_city_ID = request.getParameter("str_city");
			String registration_number = request
					.getParameter("registration_number");
			String isnewvehicle = request.getParameter("isnewvehicle");
			String registration_date = request
					.getParameter("vehicleRegDate");
			String covers = request.getParameter("covers");
			String isbifuelkit = request.getParameter("isbifuelkit");
			String islpgcng = request.getParameter("islpgcng");
			String bfk_amt = request.getParameter("bfk_amt");
			String pao_nomineename = request.getParameter("pao_nomineename");
			String pao_nomineedob = request.getParameter("pao_nomineedob");
			String pao_nomineerel = request.getParameter("pao_nomineerel");
			String previnsname = request.getParameter("previnsname");
			String prevpolicyno = request.getParameter("prevpolicyno");
			System.out.println("prevpolicyno ::" +prevpolicyno);
			String prevstartdt = request.getParameter("prevstartdt");
			String prevenddt = request.getParameter("prevenddt");
			String ncbisapp = request.getParameter("ncbisapp");
			String ncbeligicrit = request.getParameter("ncbeligicrit");
			String ncbprevncb = request.getParameter("ncbprevncb");
			String ncbcurncb = request.getParameter("ncbcurncb");
			String voldedamt = request.getParameter("voldedamt");
			String eleamt = request.getParameter("eleamt");
			String noneleamt = request.getParameter("noneleamt");
			String unpasno = request.getParameter("unpasno");
			String unpasamt = request.getParameter("unpasamt");
			String antitheftno = request.getParameter("antitheftno");
			String antitheftname = request.getParameter("antitheftname");
			String automemno = request.getParameter("automemno");
			String automemname = request.getParameter("automemname");
			String vdpolcovid = request.getParameter("vdpolcovid");
			String vdamt = request.getParameter("vdamt");
			String vdno = request.getParameter("vdno");
			String vdname = request.getParameter("vdname");
			String patounpasno = request.getParameter("patounpasno");
			String patounpasname = request.getParameter("patounpasname");
			String patounpaspolcovid = request
					.getParameter("patounpaspolcovid");
			String patounpasamt = request.getParameter("patounpasamt");
			String zone_id=request.getParameter("zone_id");
			String dob = request.getParameter("dob");
			String PI_CLD_RA_AreaID=request.getParameter("PI_CLD_RA_AreaID");
			String PI_CLD_PA_AreaID=request.getParameter("PI_CLD_PA_AreaID");
			String PI_CLD_CA_AreaID=request.getParameter("PI_CLD_CA_AreaID");
			String policyType=request.getParameter("policyType");
			String prevInsId =request.getParameter("prevInsId");
			System.out.println("prevInsId::: "+prevInsId);
			String coversValue =request.getParameter("coversValue");
			String coverNo =request.getParameter("coverNo");
			relianceIntBean.setMotorGroupResponseGroupId(request.getParameter("motorGroupResponseGroupId"));
			relianceIntBean.setMotorGroupResponseSessionId(request.getParameter("motorGroupResponseSessionId"));
			relianceIntBean.setMotorGroupResponseGicId(request.getParameter("motorGroupResponseGicId"));
			
			relianceIntBean.setUserId(request.getParameter("userId"));
			relianceIntBean.setUserDesc(request.getParameter("userDesc"));
			relianceIntBean.setBranchId(request.getParameter("branchId"));
			relianceIntBean.setIPAddress(request.getParameter("IPAddress"));

			ArrayList<String> resultList = new ArrayList<String>();
			JSONArray array = new JSONArray();
			String retMsg = "";
			String jsonNames = ""; 

			String xx = "";
			// File inputFile = new File("D:\\input.txt");
			// SAXBuilder saxBuilder = new SAXBuilder();

			Document document2;// =saxBuilder.build(inputFile);
			org.w3c.dom.Document document = null;
			
				
			//System.out.println("Request_type--==" + request_for);
//			System.out.println("inputParaListCover--==" + inputParaList);
			HashMap inputParaList = new HashMap<>();
			
			inputParaList.put("PI_TABLE_NAME", table_name);
			inputParaList.put("PI_Rto_City", rto_city_ID);
			inputParaList.put("PI_ZONE_ID", zone_id);
			inputParaList.put("PI_POLICYTYPE", policyType);
			inputParaList.put("PI_VarID", varid);
			inputParaList.put("PI_P_BusinessType", p_businesstype);
			inputParaList.put("PI_ProductID", productid);
			inputParaList.put("PI_VehID", vehid);
			inputParaList.put("PI_ModelID", modelid);
			inputParaList.put("PI_CUST_TYPE", clienttype);
			inputParaList.put("PI_OccupationID", occupationid);
			inputParaList.put("PI_FINTYPE_ID", financetypeid);
			inputParaList.put("PI_PREV_GIC_ID", prevInsId);
			inputParaList.put("PI_CLD_CA_AreaID", PI_CLD_CA_AreaID);
			inputParaList.put("PI_CLD_PA_AreaID", PI_CLD_PA_AreaID);
			inputParaList.put("PI_CLD_RA_AreaID", PI_CLD_RA_AreaID);
			inputParaList.put("PI_Nom_Rel", pao_nomineerel);
			inputParaList.put("PI_Nationality", nationality);
			inputParaList.put("PI_MaritalStatus", maritalstatus);
			inputParaList.put("PI_Covers", covers);
			inputParaList.put("PI_COV_VAL", coversValue);
			inputParaList.put("PI_COV_NO", coverNo);
			inputParaList.put("PI_PREV_NCB", ncbprevncb);
			inputParaList.put("PI_IM_GROUP_ID", relianceIntBean.getMotorGroupResponseGroupId());
			System.out.println("inputParaList<>" +inputParaList);
			
			jsonNames = relianceDao.getRelianceData(pkg_name, proc_name, inputParaList);
		
			HashMap coverage = new HashMap<>();
			coverage.put("C_ISNONELECTRICALITEMFITTED", noneleamt);
			coverage.put("C_ISELECTRICALITEMFITTED", eleamt);
			coverage.put("C_ELECTRICALITEMSTOTALSI",relianceIntBean.getIsElectricalItemSI() );
			coverage.put("C_NONELECTRICALITEMSTOTALSI",relianceIntBean.getIsNonElectricalItemSI() );
			coverage.put("C_UNPASSI", relianceIntBean.getPAToUnNamedPassengerSumInsured());
			coverage.put("R_IDV", idv);
			coverage.put("C_NOOFUNPASCOVERED", relianceIntBean.getPAToUnNamedPassengerNoOfItems());
			coverage.put("C_VOLUNTARYDEDUCTABLEAMOUNT", relianceIntBean.getVoluntaryDeductibleSumInsured());
			coverage.put("C_ISVOLUNTARYDEDUCTABLEOPTED", relianceIntBean.getIsvoluntaryDeductible());
			coverage.put("R_DATEOFPURCHASE",dateofpurchase);
			coverage.put("R_MANUFACTUREYEAR", manufactureyear);
			coverage.put("CLD_CLIENTTYPE", clienttype);
			coverage.put("CLD_LASTNAME", lastname);
			coverage.put("CLD_MIDNAME", midname);
			coverage.put("CLD_FORENAME", forename);
			coverage.put("CLD_CORPORATENAME", corporatename);
//			coverage.put("pi_occupationid", occupationid);
			coverage.put("CLD_DOB",dob);
			coverage.put("CLD_GENDER", gender);
			coverage.put("CLD_PHONENO", phoneno);
			coverage.put("CLD_MOBILENO", mobileno);
//			coverage.put("pi_clientaddress", clientaddress);
			coverage.put("CLD_CA_ADDRESSTYPE", cld_ca_addresstype);
			coverage.put("CLD_CA_ADDRESS1", cld_ca_address1);
			coverage.put("CLD_CA_ADDRESS2", cld_ca_address2);
			coverage.put("CLD_CA_ADDRESS3", cld_ca_address3);
//			coverage.put("CLD_CA_CITYID", cld_ca_cityid);
//			coverage.put("CLD_CA_DISTRICTID", cld_ca_districtid);
//			coverage.put("CLD_CA_STATEID", cld_ca_stateid);
//			coverage.put("CLD_CA_PINCODE", cld_ca_pincode);
			//inputParaList.put("CLD_CA_COUNTRY", cld_ca_country);
			coverage.put("CLD_CA_NEARESTLANDMARK",
					cld_ca_nearestlandmark);
			coverage.put("CLD_PA_ADDRESSTYPE", cld_pa_addresstype);
			coverage.put("CLD_PA_ADDRESS1", cld_pa_address1);
			coverage.put("CLD_PA_ADDRESS2", cld_pa_address2);
			coverage.put("CLD_PA_ADDRESS3", cld_pa_address3);
			//inputParaList.put("CLD_PA_CITYID", cld_pa_cityid);
			//inputParaList.put("CLD_PA_DISTRICTID", cld_pa_districtid);
			//inputParaList.put("CLD_PA_STATEID", cld_pa_stateid);
//			coverage.put("CLD_PA_PINCODE", cld_pa_pincode);
			//inputParaList.put("CLD_PA_COUNTRY", cld_pa_country);
			coverage.put("CLD_PA_NEARESTLANDMARK",
					cld_pa_nearestlandmark);
			coverage.put("CLD_RA_ADDRESSTYPE", cld_ra_addresstype);
			coverage.put("CLD_RA_ADDRESS1", cld_ra_address1);
			coverage.put("CLD_RA_ADDRESS2", cld_ra_address2);
			coverage.put("CLD_RA_ADDRESS3", cld_ra_address3);
//			coverage.put("CLD_RA_CITYID", cld_ra_cityid);
//			coverage.put("CLD_RA_DISTRICTID", cld_ra_districtid);
//			coverage.put("CLD_RA_STATEID", cld_ra_stateid);
//			coverage.put("CLD_RA_PINCODE", cld_ra_pincode);
//			coverage.put("CLD_RA_COUNTRY", cld_ra_country);
			coverage.put("CLD_RA_NEARESTLANDMARK",cld_ra_nearestlandmark);
			coverage.put("CLD_EMAILID", emailid);
			coverage.put("CLD_SALUTATION", salutation);
			//inputParaList.put("CLD_MARITALSTATUS", maritalstatus);
			//inputParaList.put("CLD_NATIONALITY", nationality);
			//inputParaList.put("pi_p_businesstype", p_businesstype);
			//inputParaList.put("pi_productid", productid);
			//inputParaList.put("pi_vehid", vehid);
			//inputParaList.put("pi_modelid", modelid);
			//inputParaList.put("pi_varid", varid);
			coverage.put("pi_idv", idv);
//			coverage.put("pi_dateofpurchase",dateofpurchase);
//			System.out.println("R_DATEOFPURCHASE"+ AllUtils.getFormattedDateOracle(dateofpurchase));
			coverage.put("R_MANUFACTUREMONTH", manufacturemonth);
			coverage.put("pi_manufactureyear", manufactureyear);
			coverage.put("R_ENGINENO", engineno);
			coverage.put("R_CHASSIS", chassis);
			coverage
					.put("R_ISVEHICLEHYPOTHICATED", isvehiclehypothicated);
			//inputParaList.put("pi_financetypeid", financetypeid);
			coverage.put("R_FINANCIERNAME", financiername);
			coverage.put("R_FINANCIERADDRESS", financieraddress);
			coverage.put("R_FINANCIERCITY", financiercity);
			coverage.put("R_ISREGADDSAMEASCOMMADD", isregaddsameascommadd);
			coverage.put("R_ISPERADDSAMEASCOMMADD", isperaddsameascommadd);
			coverage.put("R_ISREGADDSAMEASPERADD", isregaddsameasperadd);
			coverage.put("pi_stateofregistrationid", stateofregistrationid);
			//inputParaList.put("pi_rto_city", rto_city_ID);
			coverage.put("V_REGISTRATION_NUMBER", registration_number);
			coverage.put("V_ISNEWVEHICLE", isnewvehicle);
			coverage.put("V_REGISTRATION_DATE",registration_date);
			//inputParaList.put("pi_covers", covers);
			//inputParaList.put("PI_COV_VAL", coversValue);
			coverage.put("C_ISBIFUELKIT", isbifuelkit);
			coverage.put("C_BFK_ISLPGCNG", islpgcng);
			//coverage.put("pi_bfk_amt", bfk_amt);
			coverage.put("C_PAO_NOMINEENAME",pao_nomineename);
			coverage.put("C_PAO_NOMINEEDOB",pao_nomineedob);
			coverage.put("C_BFK_ISCHECKED", relianceIntBean.getBifuelKitIsChecked());
//			coverage.put("C_PAO_NOMINEERELATIONSHIP", pao_nomineerel);
			////////////////////////////////////////
//			coverage.put("PI_PREVYEARINSURER", previnsname);
			coverage.put("PI_PREVYEARPOLICYNO", prevpolicyno);
			coverage.put("PI_PREVYEARPOLICYSTARTDATE",prevstartdt);
			coverage.put("PIPREVYEARPOLICYENDDATE",prevenddt);
			coverage.put("NCB_ISNCBAPPLICABLE", ncbisapp);
			coverage.put("NCB_NCBELIGIBILITYCRITERIA", ncbeligicrit);
//			coverage.put("NCB_PREVIOUSNCB", ncbprevncb);
//			coverage.put("NCB_CURRENTNCB", ncbcurncb);
			coverage.put("pi_voldedamt", voldedamt);
			coverage.put("pi_eleamt", eleamt);
			coverage.put("pi_noneleamt", noneleamt);
			coverage.put("pi_unpasno", unpasno);
			coverage.put("pi_unpasamt", unpasamt);
			coverage.put("C_ISANTITHEFTDEVICEFITTED", relianceIntBean.getIsAntiTheftDeviceFitted());
			coverage.put("C_ISAUTOAMEM", relianceIntBean.getIsAutomobileAssociationMember());
			coverage.put("C_ISTPPDCOVER", relianceIntBean.getIsTPPDCover());
			
			coverage.put("C_ISPATOUNPASCOVERED", relianceIntBean.getIsPAToUnnamedPassengerCovered());
			coverage.put("pi_antitheftno", antitheftno);
			coverage.put("pi_antitheftname", antitheftname);
			coverage.put("C_PAU_NOOFITEMS", relianceIntBean.getPAToUnNamedPassengerNoOfItems());
			coverage.put("pi_automemname", automemname);
			coverage.put("pi_vdpolcovid", vdpolcovid);
			coverage.put("pi_vdamt", vdamt);
			coverage.put("pi_vdno", vdno);
			coverage.put("pi_vdname", vdname);
			coverage.put("C_PAU_SUMINSURED", patounpasno);
			coverage.put("pi_patounpasname", patounpasname);
			coverage.put("C_PAU_POLICYCOVERID", patounpaspolcovid);
//			coverage.put("pi_patounpasamt", patounpasamt);
						
			
			coverage.put("C_NEL_ITEMSID", "");
			coverage.put("C_NEL_POLICYID", "");
			coverage.put("C_NEL_SERIALNO", "");
			coverage.put("C_NEL_MAKEMODEL", "");
			coverage.put("C_NEL_PREMIUM", "");
			coverage.put("C_NEL_DESCRIPTION", "");
			coverage.put("C_NEL_CATEGORY", "");
			coverage.put("C_NEL_ACCESSORYSLNO", "");
			coverage.put("C_NEL_SUMINSURED", "");
			coverage.put("C_EL", "0");
			coverage.put("C_NEL", "0");
			coverage.put("C_EL_ITEMSID", "0");
			coverage.put("C_EL_POLICYID", "0");
			coverage.put("C_EL_SERIALNO", "0");
			coverage.put("C_EL_MAKEMODEL", "0");
			coverage.put("C_EL_PREMIUM", "0");
			coverage.put("C_EL_DESCRIPTION", "0");
			coverage.put("C_EL_ACCESSORYSLNO", "0");
			coverage.put("C_EL_SUMINSURED", "0");
			coverage.put("C_ISBASICODCOVERAGE", relianceIntBean.getIsBasicODCoverage());
			coverage.put("C_ISBASICLIABILITY", relianceIntBean.getIsBasicLiability());
			coverage.put("C_ISPATOOWNERDRIVERCOVERD", relianceIntBean.getIsPAToOwnerDriverCoverd());
			coverage.put("C_VD_POLICYCOVERID", relianceIntBean.getVoluntaryDeductiblepolicyCoverId());
			coverage.put("C_VD_SumInsured", relianceIntBean.getVoluntaryDeductibleSumInsured());
			coverage.put("C_VD_ISMANDATORY", relianceIntBean.getVoluntaryDeductibleIsManadtory());
			coverage.put("C_VD_ISCHECKED", relianceIntBean.getVoluntaryDeductibleIsChecked());
			coverage.put("C_VD_PACKAGENAME", relianceIntBean.getVoluntaryDeductiblePackageName());
			coverage.put("C_ATDD_ISMANDATORY", relianceIntBean.getAntiTheftIsManadtory());
			coverage.put("C_ATDD_ISCHECKED", relianceIntBean.getAntiTheftIsChecked());
			coverage.put("C_ATDD_NOOFITEMS", relianceIntBean.getAntiTheftNoOfItems());
			coverage.put("C_ATDD_PACKAGENAME", relianceIntBean.getAntiTheftPackageName());
			coverage.put("C_AAMD_ISMANDATORY", relianceIntBean.getAaiMemberIsMandatory());
			coverage.put("C_AAMD_ISCHECKED", relianceIntBean.getAaiMemberIsChecked());
			coverage.put("C_AAMD_PACKAGENAME", relianceIntBean.getAaiMemberPackageName());
			coverage.put("C_PAU_ISMANDATORY", relianceIntBean.getPAToUnNamedPassengerIsMandatory());
			coverage.put("C_PAU_ISCHECKED", relianceIntBean.getPAToUnNamedPassengerIsChecked());
			coverage.put("C_PAU_PACKAGENAME", relianceIntBean.getPAToUnNamedPassengerPackageName());
			coverage.put("C_PAU_PACKAGENAME", relianceIntBean.getPAToUnNamedPassengerPackageName());
			coverage.put("geoExtension", relianceIntBean.getIsGeographicalAreaExtended());
			coverage.put("geoCountries", relianceIntBean.getGeoCountries());
			coverage.put("CountryMandatory", relianceIntBean.getIsGeoMandatary());
			coverage.put("countryIsChecked", relianceIntBean.getGeoIsChecked());
			
			System.out.println("Coverage :: " +coverage);
			Gson gson = new Gson();
			String json = gson.toJson(coverage);
			System.out.println("json additional parameters = " + json);
			jsonNames = json + "" + jsonNames ;
			System.out.println("result Befor Removing brackets = " + jsonNames);
		
			jsonNames= "[" + jsonNames + "]"; 
			System.out.println("final jsonNames = " + jsonNames);
			System.out.println("Request_type--==" + relianceIntBean.getRequest_for());
			
			if(jsonNames.contains("}]]"))
			{
				jsonNames = jsonNames.replace("}]]", "}]");
			}
			if(jsonNames.contains("}[{"))
			{
				jsonNames = jsonNames.replace("}[{", ",");
			}
			System.out.println("After jsonNames = " + jsonNames);
			if(jsonNames.length()!= 0){
				String xmlFile = xmlFile(jsonNames);
				System.out.println("xmlFile" + xmlFile);
				String s = PostInfoToAPI(xmlFile, "CoverageDetailsForMotor");
				System.out.println("s---------" + s);
				
				coverageDetails = readCoverageResponce(s);
				System.out.println("coverageDetails>>" + coverageDetails);
			}
		} catch (Exception e) {
			System.out.println("//////////////ee////////////" + e);
		}
		return coverageDetails;
	}

	private String readCoverageResponce(String str) {
		HashMap<String, String> input =null;
		HashMap coverMap= new HashMap<>();
		HashMap cst = null;
		String jsonNames = "";
		String retMsg = "";
		String coverageName= "";
		String finalRespo="";
		try {

			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(new ByteArrayInputStream(str.getBytes()));
			//-------------------------------------------------------------------	
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			String xt = xmlOutput.outputString(document);
			  
            
			xmlOutput.output(document, new FileWriter(path+"\\respcov.xml"));
		//	-----------------------------------------------------------
			
			// System.out.println("document---"+document);
			org.jdom2.Element root = document.getRootElement();
			// System.out.println("root element---"+root);
			List list = root.getChildren();
			// System.out.println("lIST="+list);
			Iterator itr = list.iterator();
			input = new HashMap<String, String>();
			input.put("TABLE_NAME", "AMCP");
			org.jdom2.Element TraceID = (org.jdom2.Element) root
					.getChild("TraceID");
			TraceID.getValue();
			while (itr.hasNext()) {
				org.jdom2.Element s = (org.jdom2.Element) itr.next();
				// System.out.println(s.getName());
				if (s.getName().equalsIgnoreCase("CoverageList")) {
				//	input.put("coverType", "1");
					//input.put("rate", "");
				}
				if (s.getName().equalsIgnoreCase("LstAddonCovers")) {
					//input.put("coverType", "2");
					//input.put("ismandatory", "");

				}
				if (s.getName().equalsIgnoreCase("ErrorMessages")) {
					input.put("ErrorMessages", s.getValue());
				}/*else{
					input.put("ErrorMessages", "");
				}*/
				//
				// //System.out.println("node"+s);
				List ls = s.getChildren();
				// System.out.println(ls);
				Iterator it = ls.iterator();
				// System.out.println("traceid-------"+ TraceID.getValue());
				input.put("TRACE_ID", TraceID.getValue());

				if (!ls.isEmpty()) {
					while (it.hasNext()) {
						String[] values = new String[50];
						ArrayList a1 = new ArrayList<>();
						for (int index = 0; index < values.length; index++) {
							values[index] = "";
						}
						org.jdom2.Element rn = (org.jdom2.Element) it.next();
						// System.out.println("node----"+rn);
						List rnc = rn.getChildren();
						// System.out.println("childL------"+rnc);
						Iterator itrnc = rnc.iterator();

						while (itrnc.hasNext()) {
							org.jdom2.Element rncv = (org.jdom2.Element) itrnc
									.next();
							 System.out.println("nodename----"+rncv.getName());
							// System.out.println("nodecv----"+rncv.getValue());
							List valList = rncv.getChildren();
							if (!valList.isEmpty()) {
								int i = 0;
								Iterator itrval = valList.iterator();
								org.jdom2.Element val=null;
								while (itrval.hasNext()) {
								
									 val = (org.jdom2.Element) itrval.next();
									 System.out.println("val----"+val.getName());
									values[i] = val.getValue();
								 System.out.println(i+"--"+values[i]+"--------------------"+values[i]);
								 a1.add(values[i]);
								 i++;
									
								}
								String amount = a1.toString();
								amount = amount.replace("[", "");
								amount = amount.replace("]", "");
								
								if(rncv.getName() == "EMIProtection"){
									 input.put(coverageName.replace(" ", "")+"_rate", amount);
								}else{
								 input.put(coverageName.replace(" ", "")+"_value", amount);
								}
								 System.out.println(i+"--"+values[i]+"--------------------"+values[i]);

							} else {
								
								// System.out.println( "****"+rncv.getName());
								if (rncv.getName().equalsIgnoreCase(
										"CoverageName")) {
									input.put("coverName", rncv.getValue());
									coverageName= rncv.getValue();
									System.out.println("coverageName::" + coverageName);
									// /System.out.println("----CoverageName-----"+rncv.getValue());
								}
								if (rncv.getName().equalsIgnoreCase(
										"CoverageID")) {
									input.put(coverageName.replace(" ", "")+"_coverId", rncv.getValue());
									 System.out.println(coverageName.trim()+"_coverId"+":::"+rncv.getValue());
								}
								if (rncv.getName().equalsIgnoreCase(
										"TypeofCover")) {
									input.put(coverageName.replace(" ", "")+"_cover_type", "2");
									// System.out.println("-----TypeofCover----"+rncv.getValue());
								}
								if (rncv.getName().equalsIgnoreCase(
										"Ismandatory")) {
									// System.out.println("---------"+rncv.getValue());
									if (rncv.getValue()
											.equalsIgnoreCase("true")) {
										input.put(coverageName.replace(" ", "")+"_ismandatory", "Y");
									} else {
										input.put(coverageName.replace(" ", "")+"_ismandatory", "N");
									}
									// System.out.println("---------"+rncv.getValue());
								}
								if (rncv.getName().equalsIgnoreCase("rate")) {
									input.put(coverageName.replace(" ", "")+"_rate", rncv.getValue());
									// System.out.println("---------"+rncv.getValue());
								}
							}
						}
						 //coverMap.putAll(input);
					}
					System.out.println("jsonNames==" + coverMap);
				}
				 coverMap.putAll(input);
			}
			String procedureName = "PR_COVERS";
			Result resObj = integrationSaveResponseService.insertUpdateIntgRecordPKGwithArray(Integer.parseInt(relianceIntBean.getMotorGroupResponseGroupId()), Integer.parseInt(relianceIntBean.getMotorGroupResponseGicId()), relianceIntBean.getMotorGroupResponseSessionId(),
					0, input, relianceIntBean.getIPAddress(), relianceIntBean.getUserId(), relianceIntBean.getBranchId(),relianceIntBean.getUserDesc(), procedureName );
		
			/*jsonNames = resObj.getJsonValues();
			System.out.println("resObj==" + resObj);*/
			ObjectMapper mapperObj = new ObjectMapper();
			String jsonFinalResp = mapperObj.writeValueAsString(coverMap);
	        System.out.println("jsonFinalResp==>>"+jsonFinalResp);
			
	         finalRespo= "["+	jsonFinalResp +"]";
	        System.out.println("jsonFinalResp==>>"+finalRespo);
		
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalRespo;
	}


	private HashMap<String, String> readResponse(String str) {
		data = new HashMap();
		HashMap tax;
        ArrayList coverlist= new ArrayList<>();
		try {
			data.clear();
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(new ByteArrayInputStream(str
					.getBytes()));
		//-------------------------------------------------------------------	
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			String xt = xmlOutput.outputString(document);

			xmlOutput.output(document, new FileWriter(path+"\\respfile.xml"));
		//	-----------------------------------------------------------
			org.jdom2.Element root = document.getRootElement();
			List list = root.getChildren();
			System.out.println("LIST=" + list);
			Iterator itr = list.iterator();
			data.put("Company", "Reliance");
			int i=0,x=1;
			while (itr.hasNext()) {
				org.jdom2.Element s = (org.jdom2.Element) itr.next();
				List lst = s.getChildren();
				Iterator iterator = lst.iterator();
				HashMap cover = new HashMap();
				cover.clear();
				if (!lst.isEmpty()) {
					int j = 0;
					
					while (iterator.hasNext()) {						
						
						org.jdom2.Element rncv = (org.jdom2.Element) iterator.next();
//						System.out.println("rncv--"+rncv);
						List ls=rncv.getChildren();						
						Iterator itra = ls.iterator();						
						if (!(ls.isEmpty())) {
//						System.out.println("ls-->"+ls);
						tax=new HashMap();
//							System.out.println("tax is empty----" + tax +"----"+tax.isEmpty());
						while(itra.hasNext()){
							
							 org.jdom2.Element tx = (org.jdom2.Element) itra.next();
//							System.out.println("nodename----" + tx.getName());
//							System.out.println("nodecv------" + tx.getValue());
							 tax.put(tx.getName(), tx.getValue());
						  }
						j++;
//						System.out.println(j+"---"+tax);
						data.put("tax"+j,tax);
//						System.out.println("data----" + tax +"----"+data);
						}
						else{
							x=0;
//							System.out.println("rncvname----" + rncv.getName());
//							System.out.println("rncvvalue------" + rncv.getValue());
							if (!(rncv.getValue() == null)) {
//								System.out.println("nodename----" + rncv.getName());
//								System.out
//										.println("nodecv------" + rncv.getValue());
	                            
								// List valList=rncv.getChildren();
								cover.put(rncv.getName(), rncv.getValue());
								// System.out.println("Cover----"+cover);
								// data.put("cover"+i, cover);
								// i++;
							} else {
								
//								System.out.println("getName----" + rncv.getName());
//								System.out
//										.println("getValue----" + rncv.getValue());
								cover.put(s.getName(), "");
								// System.out.println("Cover----"+cover);
								// data.put("cover"+i, cover);
								// i++;
							}
							
						}
						
						
					}
					if(x==0){
					i++;
//					System.out.println("Cover---->"+i+cover);
					data.put("cover"+i, cover);
					coverlist.add("cover"+i);
					}

           } else {

					if (!(s.getValue() == null)) {
//						System.out.println("getName----" + s.getName());
//						System.out.println("getValue----" + s.getValue());
						data.put(s.getName(), s.getValue());
					} else {
//						System.out.println("getName----" + s.getName());
//						System.out.println("getValue----" + s.getValue());
						data.put(s.getName(), "");
					}
				}
			}
			data.put("coverlist", coverlist);
//			System.out.println("data--" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private String PostInfoToAPI(Object obj, String MethodName) {
		// //System.out.println("Object=====>"+obj);
		org.w3c.dom.Document doc = null;
		String str = null;
		DOMSource domSource = null;
		try {
		
			URL url = new URL("http://rzonews.reliancegeneral.co.in:91/API/Service/"
					+ MethodName);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");
			String request = obj.toString();
			// System.out.println(request);
			byte[] data = request.getBytes("UTF-8");
			// System.out.println(" byte[] "+data);
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(data, 0, data.length);
			// System.out.println(" outputStream "+outputStream);
			outputStream.close();
			// System.out.println("connection-----"+connection);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			// System.out.println("in==>" + in);
			String inputLine;
			StringBuffer response = new StringBuffer();
			System.out.println("RESPONSE==>" + response);
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			str = response.toString();
//			System.out.println("String=====>" + str);
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();
				doc = builder.parse(new InputSource(new StringReader(str)));
				// System.out.println(" doc===>" + doc);

				domSource = new DOMSource(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println(str);
			// System.out.println("domSource===>" + domSource);

		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}
//
	
	private String PostInfoToAPIJSON(Object obj, String MethodName) {
		// //System.out.println("Object=====>"+obj);
		org.w3c.dom.Document doc = null;
		String str = null;
		DOMSource domSource = null;
		try {
		
			URL url = new URL("http://220.226.197.206:91/API/Service/"
					+ MethodName);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			String request = obj.toString();
			// System.out.println(request);
			byte[] data = request.getBytes("UTF-8");
			// System.out.println(" byte[] "+data);
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(data, 0, data.length);
			// System.out.println(" outputStream "+outputStream);
			outputStream.close();
			// System.out.println("connection-----"+connection);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			// System.out.println("in==>" + in);
			String inputLine;
			StringBuffer response = new StringBuffer();
//			System.out.println("RESPONSE==>" + response);
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
			 System.out.println("jsonarray"+jsonarray);
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonResult = jsonarray.getJSONObject(i);
				 System.out.println("<<<<<<:" + jsonResult);
				// String coverDetails=jsonResult.getString("coverDetails");

				org.jdom2.Element rootelement = new org.jdom2.Element(
						"PolicyDetails");
				document2 = new Document(rootelement);

				org.jdom2.Element coverDetail = new org.jdom2.Element(
						"CoverDetails");

				org.jdom2.Element ClientDetails = new org.jdom2.Element(
						"ClientDetails");

				org.jdom2.Element ClientType = new org.jdom2.Element(
						"ClientType");
				ClientType.setText(jsonResult.getString("CLD_CLIENTTYPE")
						.trim());

				org.jdom2.Element LastName = new org.jdom2.Element("LastName");
//				System.out.println(jsonResult.getString("prePro"));
				LastName.setText(jsonResult.getString("CLD_LASTNAME").trim());

				org.jdom2.Element MidName = new org.jdom2.Element("MidName");
				System.out.println(jsonResult.getString("CLD_MIDNAME"));
				MidName.setText(jsonResult.getString("CLD_MIDNAME").trim());

				org.jdom2.Element ForeName = new org.jdom2.Element("ForeName");
				ForeName.setText(jsonResult.getString("CLD_FORENAME").trim());

				org.jdom2.Element CorporateName = new org.jdom2.Element(
						"CorporateName");
				CorporateName.setText(jsonResult.getString("CLD_CORPORATENAME")
						.trim());

				org.jdom2.Element OccupationID = new org.jdom2.Element(
						"OccupationID");
				OccupationID.setText(jsonResult.getString("CLD_OCCUPATIONID")
						.trim());

				org.jdom2.Element DOB = new org.jdom2.Element("DOB");
				DOB.setText(jsonResult.getString("CLD_DOB").trim());

				org.jdom2.Element Gender = new org.jdom2.Element("Gender");
				Gender.setText(jsonResult.getString("CLD_GENDER").trim());

				org.jdom2.Element PhoneNo = new org.jdom2.Element("PhoneNo");
				PhoneNo.setText(jsonResult.getString("CLD_PHONENO").trim());

				org.jdom2.Element MobileNo = new org.jdom2.Element("MobileNo");
				MobileNo.setText(jsonResult.getString("CLD_MOBILENO").trim());

				org.jdom2.Element ClientAddress = new org.jdom2.Element(
						"ClientAddress");

				org.jdom2.Element CommunicationAddress = new org.jdom2.Element(
						"CommunicationAddress");

				org.jdom2.Element AddressType = new org.jdom2.Element(
						"AddressType");
				AddressType.setText(jsonResult.getString("CLD_CA_ADDRESSTYPE")
						.trim());

				org.jdom2.Element Address1 = new org.jdom2.Element("Address1");
				Address1.setText(jsonResult.getString("CLD_CA_ADDRESS1").trim());

				org.jdom2.Element Address2 = new org.jdom2.Element("Address2");
				Address2.setText(jsonResult.getString("CLD_CA_ADDRESS2").trim());

				org.jdom2.Element Address3 = new org.jdom2.Element("Address3");
				Address3.setText(jsonResult.getString("CLD_CA_ADDRESS3").trim());

				org.jdom2.Element DistrictID = new org.jdom2.Element(
						"DistrictID");
				DistrictID.setText(jsonResult.getString("CLD_CA_DISTRICTID")
						.trim());

				org.jdom2.Element CityID = new org.jdom2.Element("CityID");
				CityID.setText(jsonResult.getString("CLD_CA_CITYID").trim());

				org.jdom2.Element StateID = new org.jdom2.Element("StateID");
				StateID.setText(jsonResult.getString("CLD_CA_STATEID").trim());

				org.jdom2.Element Pincode = new org.jdom2.Element("Pincode");

				Pincode.setText(jsonResult.getString("CLD_CA_PINCODE").trim());

				org.jdom2.Element Country = new org.jdom2.Element("Country");
				Country.setText(jsonResult.getString("CLD_CA_COUNTRY").trim());

				org.jdom2.Element NearestLandmark = new org.jdom2.Element(
						"NearestLandmark");
				NearestLandmark.setText(jsonResult.getString(
						"CLD_CA_NEARESTLANDMARK").trim());

				org.jdom2.Element PermanentAddress = new org.jdom2.Element(
						"PermanentAddress");

				org.jdom2.Element PA_AddressType = new org.jdom2.Element(
						"AddressType");
				PA_AddressType.setText(jsonResult.getString(
						"CLD_PA_ADDRESSTYPE").trim());

				org.jdom2.Element PA_Address1 = new org.jdom2.Element(
						"Address1");
				PA_Address1.setText(jsonResult.getString("CLD_PA_ADDRESS1")
						.trim());

				org.jdom2.Element PA_Address2 = new org.jdom2.Element(
						"Address2");
				PA_Address2.setText(jsonResult.getString("CLD_PA_ADDRESS2")
						.trim());

				org.jdom2.Element PA_Address3 = new org.jdom2.Element(
						"Address3");
				PA_Address3.setText(jsonResult.getString("CLD_PA_ADDRESS3")
						.trim());

				org.jdom2.Element PA_CityID = new org.jdom2.Element("CityID");
				PA_CityID.setText(jsonResult.getString("CLD_PA_CITYID").trim());

				org.jdom2.Element PA_DistrictID = new org.jdom2.Element(
						"DistrictID");
				PA_DistrictID.setText(jsonResult.getString("CLD_PA_DISTRICTID")
						.trim());

				org.jdom2.Element PA_StateID = new org.jdom2.Element("StateID");
				PA_StateID.setText(jsonResult.getString("CLD_PA_STATEID")
						.trim());

				org.jdom2.Element PA_Pincode = new org.jdom2.Element("Pincode");
				PA_Pincode.setText(jsonResult.getString("CLD_PA_PINCODE")
						.trim());

				org.jdom2.Element PA_Country = new org.jdom2.Element("Country");
				PA_Country.setText(jsonResult.getString("CLD_PA_COUNTRY")
						.trim());

				org.jdom2.Element PA_NearestLandmark = new org.jdom2.Element(
						"NearestLandmark");
				PA_NearestLandmark.setText(jsonResult.getString(
						"CLD_PA_NEARESTLANDMARK").trim());

				org.jdom2.Element RegistrationAddress = new org.jdom2.Element(
						"RegistrationAddress");

				org.jdom2.Element RA_AddressType = new org.jdom2.Element(
						"AddressType");
				RA_AddressType.setText(jsonResult.getString(
						"CLD_RA_ADDRESSTYPE").trim());

				org.jdom2.Element RA_Address1 = new org.jdom2.Element(
						"Address1");
				RA_Address1.setText(jsonResult.getString("CLD_RA_ADDRESS1")
						.trim());

				org.jdom2.Element RA_Address2 = new org.jdom2.Element(
						"Address2");
				RA_Address2.setText(jsonResult.getString("CLD_RA_ADDRESS2")
						.trim());

				org.jdom2.Element RA_Address3 = new org.jdom2.Element(
						"Address3");
				RA_Address3.setText(jsonResult.getString("CLD_RA_ADDRESS3")
						.trim());

				org.jdom2.Element RA_CityID = new org.jdom2.Element("CityID");
				RA_CityID.setText(jsonResult.getString("CLD_RA_CITYID").trim());

				org.jdom2.Element RA_StateID = new org.jdom2.Element("StateID");
				RA_StateID.setText(jsonResult.getString("CLD_RA_STATEID")
						.trim());

				org.jdom2.Element RA_DistrictID = new org.jdom2.Element(
						"DistrictID");
				RA_DistrictID.setText(jsonResult.getString("CLD_RA_DISTRICTID")
						.trim());

				org.jdom2.Element RA_Pincode = new org.jdom2.Element("Pincode");
				RA_Pincode.setText(jsonResult.getString("CLD_RA_PINCODE")
						.trim());

				org.jdom2.Element RA_Country = new org.jdom2.Element("Country");
				RA_Country.setText(jsonResult.getString("CLD_RA_COUNTRY")
						.trim());

				org.jdom2.Element RA_NearestLandmark = new org.jdom2.Element(
						"NearestLandmark");
				RA_NearestLandmark.setText(jsonResult.getString(
						"CLD_RA_NEARESTLANDMARK").trim());

				org.jdom2.Element EmailID = new org.jdom2.Element("EmailID");
				EmailID.setText(jsonResult.getString("CLD_EMAILID").trim());

				org.jdom2.Element Salutation = new org.jdom2.Element(
						"Salutation");
				Salutation.setText(jsonResult.getString("CLD_SALUTATION")
						.trim());

				org.jdom2.Element MaritalStatus = new org.jdom2.Element(
						"MaritalStatus");
				MaritalStatus.setText(jsonResult.getString("CLD_MARITALSTATUS")
						.trim());

				org.jdom2.Element Nationality = new org.jdom2.Element(
						"Nationality");
				Nationality.setText(jsonResult.getString("CLD_NATIONALITY")
						.trim());

				org.jdom2.Element Policy = new org.jdom2.Element("Policy");

				org.jdom2.Element BusinessType = new org.jdom2.Element(
						"BusinessType");
				BusinessType.setText(jsonResult.getString("P_BUSINESSTYPE")
						.trim());

				org.jdom2.Element Cover_From = new org.jdom2.Element(
						"Cover_From");
				Cover_From.setText(jsonResult.getString("P_COVER_FROM").trim());
				// Cover_From.setText("24/06/2017".trim());

				org.jdom2.Element Branch_Code = new org.jdom2.Element(
						"Branch_Code");
				Branch_Code.setText(jsonResult.getString("P_BRANCH_CODE")
						.trim());
				// Branch_Code.setText("1101");

				org.jdom2.Element AgentName = new org.jdom2.Element("AgentName");
				AgentName.setText(jsonResult.getString("P_AGENTNAME").trim());

				org.jdom2.Element ProductCode = new org.jdom2.Element(
						"productcode");
				ProductCode.setText(jsonResult.getString("P_PRODUCTCODE")
						.trim());

				org.jdom2.Element OtherSystemName = new org.jdom2.Element(
						"OtherSystemName");
				OtherSystemName.setText(jsonResult.getString(
						"P_OTHERSYSTEMNAME").trim());

				org.jdom2.Element isMotorQuote = new org.jdom2.Element(
						"isMotorQuote");
				isMotorQuote.setText(jsonResult.getString("P_ISMOTORQUOTE")
						.trim());

				org.jdom2.Element isMotorQuoteFlow = new org.jdom2.Element(
						"isMotorQuoteFlow");
				isMotorQuoteFlow.setText(jsonResult.getString(
						"P_ISMOTORQUOTEFLOW").trim());

				org.jdom2.Element Risk1 = new org.jdom2.Element("Risk");

				org.jdom2.Element VehicleMakeID = new org.jdom2.Element(
						"VehicleMakeID");
				VehicleMakeID.setText(jsonResult.getString("R_VEHICLEMAKEID")
						.toString().trim());

				org.jdom2.Element VehicleModelID = new org.jdom2.Element(
						"VehicleModelID");
				VehicleModelID.setText(jsonResult.getString("R_VEHICLEMODELID")
						.trim());

				org.jdom2.Element CubicCapacity = new org.jdom2.Element(
						"CubicCapacity");
				CubicCapacity.setText(jsonResult.getString("R_CUBICCAPACITY")
						.trim());

				org.jdom2.Element Zone = new org.jdom2.Element("Zone");
				Zone.setText(jsonResult.getString("R_ZONE").trim());

				org.jdom2.Element RTOLocationID = new org.jdom2.Element(
						"RTOLocationID");
				RTOLocationID.setText(jsonResult.getString("R_RTOLOCATIONID")
						.trim());

				org.jdom2.Element ExShowroomPrice = new org.jdom2.Element(
						"ExShowroomPrice");
				ExShowroomPrice.setText(jsonResult.getString(
						"R_EXSHOWROOMPRICE").trim());

				org.jdom2.Element IDV = new org.jdom2.Element("IDV");
				IDV.setText(jsonResult.getString("R_IDV").trim());

				org.jdom2.Element DateOfPurchase = new org.jdom2.Element(
						"DateOfPurchase");
				DateOfPurchase.setText(jsonResult.getString("R_DATEOFPURCHASE")
						.trim());
				// DateOfPurchase.setText("24/06/2017".trim());

				org.jdom2.Element ManufactureMonth = new org.jdom2.Element(
						"ManufactureMonth");
				 ManufactureMonth.setText(jsonResult.getString("R_MANUFACTUREMONTH").trim());
				//ManufactureMonth.setText("06".trim());

				org.jdom2.Element ManufactureYear = new org.jdom2.Element(
						"ManufactureYear");
				ManufactureYear.setText(jsonResult.getString(
						"R_MANUFACTUREYEAR").trim());
				// ManufactureYear.setText("2017".trim());

				org.jdom2.Element EngineNo = new org.jdom2.Element("EngineNo");
				EngineNo.setText(jsonResult.getString("R_ENGINENO").trim());

				org.jdom2.Element Chassis = new org.jdom2.Element("Chassis");
				Chassis.setText(jsonResult.getString("R_CHASSIS").trim());

				org.jdom2.Element IsVehicleHypothicated = new org.jdom2.Element(
						"IsVehicleHypothicated");
				IsVehicleHypothicated.setText(jsonResult.getString(
						"R_ISVEHICLEHYPOTHICATED").trim());

				org.jdom2.Element FinanceType = new org.jdom2.Element(
						"FinanceType");
				FinanceType.setText(jsonResult.getString("R_FINANCETYPE")
						.trim());

				org.jdom2.Element FinancierName = new org.jdom2.Element(
						"FinancierName");
				FinancierName.setText(jsonResult.getString("R_FINANCIERNAME")
						.trim());

				org.jdom2.Element FinancierAddress = new org.jdom2.Element(
						"FinancierAddress");
				FinancierAddress.setText(jsonResult.getString(
						"R_FINANCIERADDRESS").trim());

				org.jdom2.Element FinancierCity = new org.jdom2.Element(
						"FinancierCity");
				FinancierCity.setText(jsonResult.getString("R_FINANCIERCITY")
						.trim());

				org.jdom2.Element IsRegAddressSameasCommAddress = new org.jdom2.Element(
						"IsRegAddressSameasCommAddress");
				IsRegAddressSameasCommAddress.setText(jsonResult.getString(
						"R_ISREGADDSAMEASCOMMADD").trim());

				org.jdom2.Element IsPermanentAddressSameasCommAddress = new org.jdom2.Element(
						"IsPermanentAddressSameasCommAddress");
				IsPermanentAddressSameasCommAddress.setText(jsonResult
						.getString("R_ISPERADDSAMEASCOMMADD").trim());

				org.jdom2.Element IsRegAddressSameasPermanentAddress = new org.jdom2.Element(
						"IsRegAddressSameasPermanentAddress");
				IsRegAddressSameasPermanentAddress.setText(jsonResult
						.getString("R_ISREGADDSAMEASPERADD").trim());

				org.jdom2.Element VehicleVariant = new org.jdom2.Element(
						"VehicleVariant");
				VehicleVariant.setText(jsonResult.getString("R_VEHICLEVARIANT")
						.trim());

				org.jdom2.Element StateOfRegistrationID = new org.jdom2.Element(
						"StateOfRegistrationID");
				StateOfRegistrationID.setText(jsonResult.getString(
						"R_STATEOFREGISTRATIONID").trim());

				org.jdom2.Element Rto_RegionCode = new org.jdom2.Element(
						"Rto_RegionCode");
				Rto_RegionCode.setText(jsonResult.getString("R_RTO_REGIONCODE")
						.trim());

				org.jdom2.Element Vehicle = new org.jdom2.Element("Vehicle");

				org.jdom2.Element Registration_Number = new org.jdom2.Element(
						"Registration_Number");
				Registration_Number.setText(jsonResult.getString(
						"V_REGISTRATION_NUMBER").trim());

				org.jdom2.Element RegistrationNumber_New = new org.jdom2.Element(
						"RegistrationNumber_New");
				RegistrationNumber_New.setText(jsonResult.getString(
						"V_ISNEWVEHICLE").trim());

				org.jdom2.Element Registration_date = new org.jdom2.Element(
						"Registration_date");
				Registration_date.setText(jsonResult.getString(
						"V_REGISTRATION_DATE").trim());
				// Registration_date.setText("24/06/2017".trim());

				org.jdom2.Element SeatingCapacity = new org.jdom2.Element(
						"SeatingCapacity");
				SeatingCapacity.setText(jsonResult.getString(
						"V_SEATINGCAPACITY").trim());

				org.jdom2.Element TypeOfFuel = new org.jdom2.Element(
						"TypeOfFuel");
				TypeOfFuel.setText(jsonResult.getString("V_TYPEOFFUEL").trim());

				org.jdom2.Element MiscTypeOfVehicleID = new org.jdom2.Element(
						"MiscTypeOfVehicleID");
				// MiscTypeOfVehicleID.setText(jsonResult.getString("MiscTypeOfVehicleID").trim());

				org.jdom2.Element Cover = new org.jdom2.Element("Cover");

				org.jdom2.Element C_IsVoluntaryDeductableOpted = new org.jdom2.Element(
						"IsVoluntaryDeductableOpted");
				C_IsVoluntaryDeductableOpted.setText(jsonResult.getString(
						"C_ISVOLUNTARYDEDUCTABLEOPTED").trim());

				org.jdom2.Element C_VoluntaryDeductableAmount = new org.jdom2.Element(
						"VoluntaryDeductableAmount");
				C_VoluntaryDeductableAmount.setText(jsonResult.getString(
						"C_VOLUNTARYDEDUCTABLEAMOUNT").trim());
				
				org.jdom2.Element IsFibreGlassFuelTankFitted = new org.jdom2.Element(
						"IsFibreGlassFuelTankFitted");
				IsFibreGlassFuelTankFitted.setText(relianceIntBean.getIsFibreGlassFuelTankFitted());
				
				org.jdom2.Element IsUsedForDrivingTuition = new org.jdom2.Element(
						"IsUsedForDrivingTuition");
				IsUsedForDrivingTuition.setText(relianceIntBean.getIsUsedForDrivingTuition());
				
				org.jdom2.Element IsNilDepreciation = new org.jdom2.Element(
						"IsNilDepreciation");
				IsNilDepreciation.setText(relianceIntBean.getIsNilDepreciation());

				
				org.jdom2.Element IsSpeciallyDesignedForHandicapped = new org.jdom2.Element(
						"IsSpeciallyDesignedForHandicapped");
				IsSpeciallyDesignedForHandicapped.setText(relianceIntBean.getIsSpeciallyDesignedForHandicapped());
				
				
				
				org.jdom2.Element IsLiabilityToPaidDriverCovered = new org.jdom2.Element(
						"IsLiabilityToPaidDriverCovered");
				IsLiabilityToPaidDriverCovered.setText(relianceIntBean.getPaToPaidDriverisSelected());
				
				
				org.jdom2.Element C_IsAntiTheftDeviceFitted = new org.jdom2.Element(
						"IsAntiTheftDeviceFitted");
				C_IsAntiTheftDeviceFitted.setText(jsonResult.getString(
						"C_ISANTITHEFTDEVICEFITTED").trim());

				org.jdom2.Element C_IsAutomobileAssociationMember = new org.jdom2.Element(
						"IsAutomobileAssociationMember");
				C_IsAutomobileAssociationMember.setText(jsonResult.getString(
						"C_ISAUTOAMEM").trim());

				org.jdom2.Element C_IsPAToUnnamedPassengerCovered = new org.jdom2.Element(
						"IsPAToUnnamedPassengerCovered");
				C_IsPAToUnnamedPassengerCovered.setText(jsonResult.getString(
						"C_ISPATOUNPASCOVERED").trim());

				org.jdom2.Element C_NoOfUnnamedPassenegersCovered = new org.jdom2.Element("NoOfUnnamedPassenegersCovered");
				C_NoOfUnnamedPassenegersCovered.setText(jsonResult.getString("C_NOOFUNPASCOVERED").trim());

				org.jdom2.Element C_UnnamedPassengersSI = new org.jdom2.Element("UnnamedPassengersSI");
				C_UnnamedPassengersSI.setText(jsonResult.getString("C_UNPASSI").trim());

				org.jdom2.Element C_IsElectricalItemFitted = new org.jdom2.Element("IsElectricalItemFitted");
				C_IsElectricalItemFitted.setText(jsonResult.getString("C_ISELECTRICALITEMFITTED").trim());

				org.jdom2.Element C_ElectricalItemsTotalSI = new org.jdom2.Element("ElectricalItemsTotalSI");
				C_ElectricalItemsTotalSI.setText(jsonResult.getString(
						"C_ELECTRICALITEMSTOTALSI").trim());

//				C_ElectricalItemsTotalSI.setText(eleamt);

				org.jdom2.Element C_IsNonElectricalItemFitted = new org.jdom2.Element(
						"IsNonElectricalItemFitted");
				C_IsNonElectricalItemFitted.setText(jsonResult.getString("C_ISNONELECTRICALITEMFITTED").trim());
				
				org.jdom2.Element C_NonElectricalItemsTotalSI = new org.jdom2.Element(
						"NonElectricalItemsTotalSI");
				
				C_NonElectricalItemsTotalSI.setText(jsonResult.getString(
						"C_NONELECTRICALITEMSTOTALSI").trim());
				
//				C_NonElectricalItemsTotalSI.setText(noneleamt);


				
				
				org.jdom2.Element C_IsBiFuelKit = new org.jdom2.Element(
						"IsBiFuelKit");
				C_IsBiFuelKit.setText(relianceIntBean.getIsbifuelkit());

				org.jdom2.Element C_BiFuelKitSi = new org.jdom2.Element(
						"BiFuelKitSi");
				C_BiFuelKitSi.setText(relianceIntBean.getBifuelkitSumInsured());
//				C_BiFuelKitSi.setText(relianceIntBean.getBifuelkitSumInsured());
				
//				org.jdom2.Element c_IsChecked = new org.jdom2.Element(
//						"BiFuelKitSi");
//				//C_BiFuelKitSi.setText(jsonResult.getString("C_BIFUELKITSI").trim());
//				c_IsChecked.setText(relianceIntBean.getIsbifuelkit());
//				
				
				org.jdom2.Element FibreGlassFuelTank = new org.jdom2.Element(
						"FibreGlassFuelTank");

				org.jdom2.Element FibreGlassFuelTank1 = new org.jdom2.Element(
						"FibreGlassFuelTank");
				
				org.jdom2.Element FibreIsChecked = new org.jdom2.Element(
						"IsChecked");
				FibreIsChecked.setText(relianceIntBean.getFibreGlassFuelTankIsChecked());
				
				org.jdom2.Element FibreNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				FibreNoOfItems.setText(relianceIntBean.getFibreGlassFuelTankNoOfItems());
			
				org.jdom2.Element FibrePackageName = new org.jdom2.Element(
						"PackageName");
				FibrePackageName.setText(relianceIntBean.getFibreGlassFuelTankPackageName());
				
				FibreGlassFuelTank1.addContent(FibreIsChecked);
				FibreGlassFuelTank1.addContent(FibreNoOfItems);
				FibreGlassFuelTank1.addContent(FibrePackageName);
				
				
				org.jdom2.Element IsRoadTaxcover = new org.jdom2.Element(
						"IsRoadTaxcover");
				IsRoadTaxcover.setText(relianceIntBean.getIsRoadTaxcover());
				
				
				org.jdom2.Element RoadTax = new org.jdom2.Element(
						"RoadTax");
				
				org.jdom2.Element RoadTax1 = new org.jdom2.Element(
						"RoadTax");
				org.jdom2.Element RoadTaxIsChecked = new org.jdom2.Element(
						"IsChecked");
				RoadTaxIsChecked.setText(relianceIntBean.getRoadTaxIsChecked());
				
				org.jdom2.Element RoadTaxNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				RoadTaxNoOfItems.setText(relianceIntBean.getRoadTaxNoOfItems());
				
				org.jdom2.Element RoadTaxPackageName = new org.jdom2.Element(
						"PackageName");
				RoadTaxPackageName.setText(relianceIntBean.getRoadTaxPackageName());
				
				org.jdom2.Element RoadTaxSumInsured = new org.jdom2.Element(
						"SumInsured");
				RoadTaxSumInsured.setText(relianceIntBean.getRoadTaxSumInsured());
				
				org.jdom2.Element RoadTaxPolicyCoverID = new org.jdom2.Element(
						"PolicyCoverID");
				RoadTaxPolicyCoverID.setText(relianceIntBean.getRoadTaxPolicyCoverID());
				
				RoadTax1.addContent(RoadTaxIsChecked);
				RoadTax1.addContent(RoadTaxNoOfItems);
				RoadTax1.addContent(RoadTaxPackageName);
				RoadTax1.addContent(RoadTaxSumInsured);
				RoadTax1.addContent(RoadTaxPolicyCoverID);
				RoadTax.addContent(RoadTax1);
			
				Cover.addContent(RoadTax);
				Cover.addContent(IsRoadTaxcover);
				
				org.jdom2.Element DrivingTuitionCoverage = new org.jdom2.Element(
						"DrivingTuitionCoverage");

				org.jdom2.Element DrivingTuitionCoverage1 = new org.jdom2.Element(
						"DrivingTuitionCoverage");
				
				org.jdom2.Element drivingTIsChecked = new org.jdom2.Element(
						"IsChecked");
				drivingTIsChecked.setText(relianceIntBean.getDrivingTuitionCoverageIsChecked());
				
				org.jdom2.Element drivingTNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				drivingTNoOfItems.setText(relianceIntBean.getDrivingTuitionCoverageNoOfItems());
			
				org.jdom2.Element drivingTPackageName = new org.jdom2.Element(
						"PackageName");
				drivingTPackageName.setText(relianceIntBean.getDrivingTuitionCoveragePackageName());
				
				DrivingTuitionCoverage1.addContent(drivingTIsChecked);
				DrivingTuitionCoverage1.addContent(drivingTNoOfItems);
				DrivingTuitionCoverage1.addContent(drivingTPackageName);
				
				DrivingTuitionCoverage.addContent(DrivingTuitionCoverage1);
				
				
				org.jdom2.Element NilDepreciationCoverage = new org.jdom2.Element(
						"NilDepreciationCoverage");

				org.jdom2.Element NilDepreciationCoverage1 = new org.jdom2.Element(
						"NilDepreciationCoverage");
				
				org.jdom2.Element nilDepIsChecked = new org.jdom2.Element(
						"IsChecked");
				nilDepIsChecked.setText(relianceIntBean.getIsNilDepreciationIsChecked());
				
				org.jdom2.Element nilDepNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				nilDepNoOfItems.setText(relianceIntBean.getIsNilDepreciationNoOfItems());
			
				org.jdom2.Element nilDepPackageName = new org.jdom2.Element(
						"PackageName");
				nilDepPackageName.setText(relianceIntBean.getIsNilDepreciationPackageName());
				
				org.jdom2.Element nilDepPolicyCoverID = new org.jdom2.Element(
						"PolicyCoverID");
				nilDepPolicyCoverID.setText(relianceIntBean.getIsNilDepreciationPolicyCoverID());
				
				org.jdom2.Element nilApplicableRate = new org.jdom2.Element(
						"ApplicableRate");
				nilApplicableRate.setText(relianceIntBean.getIsNilDepreciationApplicableRate());
				
				NilDepreciationCoverage1.addContent(nilDepIsChecked);
				NilDepreciationCoverage1.addContent(nilDepNoOfItems);
				NilDepreciationCoverage1.addContent(nilDepPackageName);
				NilDepreciationCoverage1.addContent(nilDepPolicyCoverID);
				NilDepreciationCoverage1.addContent(nilApplicableRate);
				
				NilDepreciationCoverage.addContent(NilDepreciationCoverage1);
				
				
				
				org.jdom2.Element SpeciallyDesignedforChallengedPerson = new org.jdom2.Element(
						"SpeciallyDesignedforChallengedPerson");

				org.jdom2.Element SpeciallyDesignedforChallengedPerson1 = new org.jdom2.Element(
						"SpeciallyDesignedforChallengedPerson");
				
				org.jdom2.Element handicapIsChecked = new org.jdom2.Element(
						"IsChecked");
				handicapIsChecked.setText(relianceIntBean.getDesignedForHandiCappedIsChecked());
				
				org.jdom2.Element handicapNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				handicapNoOfItems.setText(relianceIntBean.getDesignedForHandiCappedNoOfItems());
			
				org.jdom2.Element handicapPackageName = new org.jdom2.Element(
						"PackageName");
				handicapPackageName.setText(relianceIntBean.getDesignedForHandiCappedPackageName());
				
			
				SpeciallyDesignedforChallengedPerson1.addContent(handicapIsChecked);
				SpeciallyDesignedforChallengedPerson1.addContent(handicapNoOfItems);
				SpeciallyDesignedforChallengedPerson1.addContent(handicapPackageName);
				
				SpeciallyDesignedforChallengedPerson.addContent(SpeciallyDesignedforChallengedPerson1);
				
				Cover.addContent(SpeciallyDesignedforChallengedPerson);
				
				
				org.jdom2.Element LiabilityToPaidDriver = new org.jdom2.Element(
						"LiabilityToPaidDriver");

				org.jdom2.Element LiabilityToPaidDriver1 = new org.jdom2.Element(
						"LiabilityToPaidDriver");
				
				org.jdom2.Element paTopaidDriverIsChecked = new org.jdom2.Element(
						"IsChecked");
				paTopaidDriverIsChecked.setText(relianceIntBean.getPaToPaidDriverIsChecked());
				
				org.jdom2.Element paTopaidDriverNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				paTopaidDriverNoOfItems.setText(relianceIntBean.getPaToPaidDriverNoOfItems());
			
				org.jdom2.Element paTopaidDriverPackageName =new  org.jdom2.Element(
						"PackageName");
				paTopaidDriverPackageName.setText(relianceIntBean.getPaToPaidDriverPackageName());
				
				org.jdom2.Element paTopaidDriverPolicyCoverID = new org.jdom2.Element(
						"PolicyCoverID");
				paTopaidDriverPolicyCoverID.setText(relianceIntBean.getPaToPaidDriverPolicyCoverID());
				
			
				LiabilityToPaidDriver1.addContent(paTopaidDriverIsChecked);
				LiabilityToPaidDriver1.addContent(paTopaidDriverNoOfItems);
				LiabilityToPaidDriver1.addContent(paTopaidDriverPackageName);
				LiabilityToPaidDriver1.addContent(paTopaidDriverPolicyCoverID);
				
				LiabilityToPaidDriver.addContent(LiabilityToPaidDriver1);
				
				Cover.addContent(IsLiabilityToPaidDriverCovered);
				Cover.addContent(LiabilityToPaidDriver);
				
				org.jdom2.Element IsLiabilityToEmployeeCovered = new org.jdom2.Element(
						"PolicyCoverID");
				IsLiabilityToEmployeeCovered.setText(relianceIntBean.getIsLiabilityToEmployeeCovered());
				
								
				org.jdom2.Element LiabilityToEmployee = new org.jdom2.Element(
						"LiabilityToEmployee");

				org.jdom2.Element LiabilityToEmployee1 = new org.jdom2.Element(
						"LiabilityToEmployee");
				
				org.jdom2.Element liabilityToEmployeeIsChecked = new org.jdom2.Element(
						"IsChecked");
				liabilityToEmployeeIsChecked.setText(relianceIntBean.getLiabilityToEmployeeIsChecked());
				
				org.jdom2.Element liabilityToEmployeeNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				liabilityToEmployeeNoOfItems.setText(relianceIntBean.getLiabilityToEmployeeNoOfItems());
			
				org.jdom2.Element liabilityToEmployeePackageName =new  org.jdom2.Element(
						"PackageName");
				liabilityToEmployeePackageName.setText(relianceIntBean.getLiabilityToEmployeePackageName());
				
				org.jdom2.Element liabilityToEmployeePolicyCoverID = new org.jdom2.Element(
						"PolicyCoverID");
				liabilityToEmployeePolicyCoverID.setText(relianceIntBean.getLiabilityToEmployeePolicyCoverID());
				
			
				LiabilityToEmployee1.addContent(liabilityToEmployeeIsChecked);
				LiabilityToEmployee1.addContent(liabilityToEmployeeNoOfItems);
				LiabilityToEmployee1.addContent(liabilityToEmployeePackageName);
				LiabilityToEmployee1.addContent(liabilityToEmployeePolicyCoverID);
				
				LiabilityToEmployee.addContent(LiabilityToEmployee1);
				
				Cover.addContent(IsLiabilityToEmployeeCovered);
				Cover.addContent(LiabilityToEmployee);
				
				org.jdom2.Element geoExtn = new org.jdom2.Element(
						"IsGeographicalAreaExtended");
				geoExtn.setText(jsonResult.getString("geoExtension").trim());
//				C_BiFuelKitSi.setText(relianceIntBean.getBfk_amt());
				
				
				org.jdom2.Element C_IsTPPDCover = new org.jdom2.Element(
						"IsTPPDCover");
				C_IsTPPDCover.setText(jsonResult.getString("C_ISTPPDCOVER")
						.trim());
				
				
				org.jdom2.Element TPPDCover = new org.jdom2.Element(
						"TPPDCover");
				
				org.jdom2.Element TPPDCover1 = new org.jdom2.Element(
						"TPPDCover");
				
				org.jdom2.Element TPPDPolicyCoverID = new org.jdom2.Element(
						"PolicyCoverID");
				TPPDPolicyCoverID.setText(relianceIntBean.getTPPDCoverPolicyCoverID());
				
				org.jdom2.Element TPPDSumInsured = new org.jdom2.Element(
						"SumInsured");
				TPPDSumInsured.setText(relianceIntBean.getTPPDCoverSumInsured());
				
				org.jdom2.Element TPPDIsChecked = new org.jdom2.Element(
						"IsChecked");
				TPPDIsChecked.setText(relianceIntBean.getIsTPPDCover());
				
				org.jdom2.Element TPPDNoOfItems = new org.jdom2.Element(
						"NoOfItems");
				TPPDNoOfItems.setText(relianceIntBean.getTPPDCoverNoOfItems());
				
				org.jdom2.Element TPPDPackageName = new org.jdom2.Element(
						"PackageName");
				TPPDPackageName.setText(relianceIntBean.getTPPDCoverPackageName());
				
				
				
				TPPDCover1.addContent(TPPDPolicyCoverID);
				TPPDCover1.addContent(TPPDSumInsured);
				TPPDCover1.addContent(TPPDIsChecked);
				TPPDCover1.addContent(TPPDNoOfItems);
				TPPDCover1.addContent(TPPDPackageName);
				TPPDCover.addContent(TPPDCover1);
				Cover.addContent(TPPDCover);
				
				org.jdom2.Element C_IsBasicODCoverage = new org.jdom2.Element(
						"IsBasicODCoverage");
				C_IsBasicODCoverage.setText(jsonResult.getString(
						"C_ISBASICODCOVERAGE").trim());

				org.jdom2.Element C_IsBasicLiability = new org.jdom2.Element(
						"IsBasicLiability");
				C_IsBasicLiability.setText(jsonResult.getString(
						"C_ISBASICLIABILITY").trim());

				org.jdom2.Element C_IsPAToOwnerDriverCoverd = new org.jdom2.Element(
						"IsPAToOwnerDriverCoverd");
				C_IsPAToOwnerDriverCoverd.setText(jsonResult.getString(
						"C_ISPATOOWNERDRIVERCOVERD").trim());

				org.jdom2.Element C_PACoverToOwner = new org.jdom2.Element(
						"PACoverToOwner");

				org.jdom2.Element CPA_PACoverToOwner1 = new org.jdom2.Element(
						"PACoverToOwner");

				org.jdom2.Element CPA_NomineeName = new org.jdom2.Element(
						"NomineeName");
				CPA_NomineeName.setText(jsonResult.getString(
						"C_PAO_NOMINEENAME").trim());

				org.jdom2.Element CPA_NomineeDOB = new org.jdom2.Element(
						"NomineeDOB");
				CPA_NomineeDOB.setText(jsonResult.getString("C_PAO_NOMINEEDOB")
						.trim());

				org.jdom2.Element CPA_NomineeRelationship = new org.jdom2.Element(
						"NomineeRelationship");
				CPA_NomineeRelationship.setText(jsonResult.getString(
						"C_PAO_NOMINEERELATIONSHIP").trim());
				
				
				org.jdom2.Element geoExtnCountry = new org.jdom2.Element(
						"GeographicalExtension");
				
				org.jdom2.Element geoExtn1 = new org.jdom2.Element(
						"GeographicalExtension");
				
				org.jdom2.Element GeoCountries = new org.jdom2.Element(
						"Countries");
				GeoCountries.setText(jsonResult.getString(
						"geoCountries").trim());
				
				org.jdom2.Element CountryMandatory = new org.jdom2.Element(
						"IsMandatory");
				CountryMandatory.setText(jsonResult.getString("CountryMandatory")
						.trim());
				
				org.jdom2.Element IsChecked = new org.jdom2.Element(
						"IsChecked");
				IsChecked.setText(jsonResult.getString(
						"countryIsChecked").trim());

				
				
				org.jdom2.Element C_BifuelKit = new org.jdom2.Element(
						"BifuelKit");

				org.jdom2.Element CB_BifuelKit1 = new org.jdom2.Element(
						"BifuelKit");

				org.jdom2.Element CB_IsChecked = new org.jdom2.Element(
						"IsChecked");
				CB_IsChecked.setText(jsonResult.getString("C_BFK_ISCHECKED")
						.trim());

				org.jdom2.Element CB_ISLpgCng = new org.jdom2.Element(
						"ISLpgCng");
				CB_ISLpgCng.setText(jsonResult.getString("C_BFK_ISLPGCNG")
						.trim());

				org.jdom2.Element CB_SumInsured = new org.jdom2.Element(
						"SumInsured");
//				CB_SumInsured.setText(jsonResult.getString("C_BFK_SUMINSURED")
//						.trim());
				CB_SumInsured.setText(relianceIntBean.getBfk_amt());

				org.jdom2.Element C_VoluntaryDeductible = new org.jdom2.Element(
						"VoluntaryDeductible");

				org.jdom2.Element CV_VoluntaryDeductible = new org.jdom2.Element(
						"VoluntaryDeductible");

				org.jdom2.Element CV_SumInsured = new org.jdom2.Element(
						"SumInsured");
				CV_SumInsured.setText(jsonResult.getString("C_VD_SumInsured")
						.trim());

				org.jdom2.Element CV_IsMandatory = new org.jdom2.Element(
						"IsMandatory");
				CV_IsMandatory.setText(jsonResult.getString("C_VD_ISMANDATORY")
						.trim());

				org.jdom2.Element CV_PolicyCoverID = new org.jdom2.Element(
						"PolicyCoverID");
				CV_PolicyCoverID.setText(jsonResult.getString(
						"C_VD_POLICYCOVERID").trim());
			
				org.jdom2.Element CV_IsChecked = new org.jdom2.Element(
						"IsChecked");
				CV_IsChecked.setText(jsonResult.getString("C_VD_ISCHECKED")
						.trim());

				org.jdom2.Element CV_NoOfItems = new org.jdom2.Element(
						"NoOfItems");
				CV_NoOfItems.setText(relianceIntBean.getVdno());

				org.jdom2.Element CV_PackageName = new org.jdom2.Element(
						"PackageName");
				CV_PackageName.setText(jsonResult.getString("C_VD_PACKAGENAME")
						.trim());

				org.jdom2.Element C_AntiTheftDeviceDiscount = new org.jdom2.Element(
						"AntiTheftDeviceDiscount");

				org.jdom2.Element CA_AntiTheftDeviceDiscount = new org.jdom2.Element(
						"AntiTheftDeviceDiscount");

				org.jdom2.Element CA_IsMandatory = new org.jdom2.Element(
						"IsMandatory");
				CA_IsMandatory.setText(jsonResult.getString(
						"C_ATDD_ISMANDATORY").trim());

				org.jdom2.Element CA_IsChecked = new org.jdom2.Element(
						"IsChecked");
				CA_IsChecked.setText(jsonResult.getString("C_ATDD_ISCHECKED")
						.trim());

				org.jdom2.Element CA_NoOfItems = new org.jdom2.Element(
						"NoOfItems");
				CA_NoOfItems.setText(jsonResult.getString("C_ATDD_NOOFITEMS").trim());
//						CA_NoOfItems.setText(antitheftno);
				org.jdom2.Element CA_PackageName = new org.jdom2.Element(
						"PackageName");
				CA_PackageName.setText(jsonResult.getString(
						"C_ATDD_PACKAGENAME").trim());

				org.jdom2.Element C_AutomobileAssociationMembershipDiscount = new org.jdom2.Element(
						"AutomobileAssociationMembershipDiscount");

				org.jdom2.Element CAMD_AutomobileAssociationMembershipDiscount = new org.jdom2.Element(
						"AutomobileAssociationMembershipDiscount");

				org.jdom2.Element CAMD_IsMandatory = new org.jdom2.Element(
						"IsMandatory");
				CAMD_IsMandatory.setText(jsonResult.getString(
						"C_AAMD_ISMANDATORY").trim());

				org.jdom2.Element CAMD_IsChecked = new org.jdom2.Element(
						"IsChecked");
				CAMD_IsChecked.setText(jsonResult.getString("C_AAMD_ISCHECKED")
						.trim());

				org.jdom2.Element CAMD_NoOfItems = new org.jdom2.Element(
						"NoOfItems");
				//CAMD_NoOfItems.setText(jsonResult.getString("C_AAMD_NOOFITEMS").trim());
				CAMD_NoOfItems.setText(relianceIntBean.getAutomemno());
				org.jdom2.Element CAMD_PackageName = new org.jdom2.Element(
						"PackageName");
				CAMD_PackageName.setText(jsonResult.getString(
						"C_AAMD_PACKAGENAME").trim());

				org.jdom2.Element C_PAToUnNamedPassenger = new org.jdom2.Element(
						"PAToUnNamedPassenger");

				org.jdom2.Element CPU_PAToUnNamedPassenger = new org.jdom2.Element(
						"PAToUnNamedPassenger");

				org.jdom2.Element CPU_IsMandatory = new org.jdom2.Element(
						"IsMandatory");
				CPU_IsMandatory.setText(jsonResult.getString(
						"C_PAU_ISMANDATORY").trim());

				org.jdom2.Element CPU_IsChecked = new org.jdom2.Element(
						"IsChecked");
				CPU_IsChecked.setText(jsonResult.getString("C_PAU_ISCHECKED")
						.trim());

				org.jdom2.Element CPU_NoOfItems = new org.jdom2.Element(
						"NoOfItems");
				CPU_NoOfItems.setText(jsonResult.getString("C_PAU_NOOFITEMS").trim());
//				CPU_NoOfItems.setText(automemno);
				org.jdom2.Element CPU_PackageName = new org.jdom2.Element(
						"PackageName");
				CPU_PackageName.setText(jsonResult.getString(
						"C_PAU_PACKAGENAME").trim());

				org.jdom2.Element CPU_PolicyCoverID = new org.jdom2.Element(
						"PolicyCoverID");
				CPU_PolicyCoverID.setText(jsonResult.getString(
						"C_PAU_POLICYCOVERID").trim());

				org.jdom2.Element CPU_SumInsured = new org.jdom2.Element(
						"SumInsured");
				CPU_SumInsured.setText(jsonResult.getString("C_PAU_SUMINSURED").trim());
//				CPU_SumInsured.setText(patounpasno);

				org.jdom2.Element C_ElectricItems = new org.jdom2.Element(
						"ElectricItems") ;

				int count = Integer.parseInt(jsonResult.getString("C_EL"));
				 
				
				
				org.jdom2.Element CE_ElectricalItems = null, CE_electricalItemsID = null, CE_policyId = null, CE_serialNo = null, CE_makeModel = null, CE_ElectricPremium = null, CE_description = null, CE_electricalAccessorySlNo = null, CE_sumInsured = null;
				
				 System.out.println("electricalitemlist////////////////"+relianceIntBean.getElectricalitemlist());
				 if((!relianceIntBean.getElectricalitemlist().isEmpty())&& (!relianceIntBean.getElectricalitemlist().equalsIgnoreCase("null")) && (!relianceIntBean.getElectricalitemlist().equals(null)))
				    {
					 relianceIntBean.setElectricalItem(relianceIntBean.getElectricalitemlist().split("~"));
				      int c=relianceIntBean.getElectricalItem().length;
//				      System.out.println("c---------------"+ c);
				      if(c>0)
				      {
				    	
				    
				    	  for (int index=0;index<c;index++) {
				            String ele=relianceIntBean.getElectricalItem()[index];
				        	String electriaclItem[]=ele.split(",");
				  
					     CE_ElectricalItems = new org.jdom2.Element(
							"ElectricalItems");

//					     System.out.println("CE_ElectricalItems---------------");
					// ElectricalItems elements

					CE_electricalItemsID = new org.jdom2.Element(
							"ElectricalItemsID");
				
					CE_electricalItemsID.setText(jsonResult.getString(
							"C_EL_ITEMSID").trim());
					
					 System.out.println("CE_electricalItemsID ---------------");

					CE_policyId = new org.jdom2.Element("PolicyId");
					CE_policyId.setText(jsonResult.getString("C_EL_POLICYID")
							.trim());
//					 System.out.println("CE_policyId ---------------");
					 
					CE_serialNo = new org.jdom2.Element("SerialNo");
					CE_serialNo.setText(jsonResult.getString("C_EL_SERIALNO")
							.trim());

					CE_makeModel = new org.jdom2.Element("MakeModel");
//					CE_makeModel.setText(jsonResult.getString("C_EL_MAKEMODEL")
//							.trim());
					 System.out.println("CE_makeModel---------------");
					CE_makeModel.setText(electriaclItem[1]);
//
//
					CE_ElectricPremium = new org.jdom2.Element(
							"ElectricPremium");
					CE_ElectricPremium.setText(jsonResult.getString(
							"C_EL_PREMIUM").trim());
					
					CE_description = new org.jdom2.Element("Description");
//					CE_description.setText(jsonResult.getString(
//							"C_EL_DESCRIPTION").trim());
					 
					CE_description.setText(electriaclItem[0]);


					CE_electricalAccessorySlNo = new org.jdom2.Element(
							"ElectricalAccessorySlNo");
					CE_electricalAccessorySlNo.setText(jsonResult.getString(
							"C_EL_ACCESSORYSLNO").trim());
					
					CE_sumInsured = new org.jdom2.Element("SumInsured");
					CE_sumInsured.setText(jsonResult.getString(
							"C_EL_SUMINSURED").trim());
					
					CE_sumInsured.setText(electriaclItem[3]);

					C_ElectricItems.addContent(CE_ElectricalItems);

					CE_ElectricalItems.addContent(CE_electricalItemsID);
					CE_ElectricalItems.addContent(CE_policyId);
					CE_ElectricalItems.addContent(CE_serialNo);
					CE_ElectricalItems.addContent(CE_makeModel);
					CE_ElectricalItems.addContent(CE_ElectricPremium);
					CE_ElectricalItems.addContent(CE_description);
					CE_ElectricalItems.addContent(CE_electricalAccessorySlNo);
					CE_ElectricalItems.addContent(CE_sumInsured);

				        }
				      }
				      
				     } 
             else{
                	  System.out.println("ElectricalItems/////////////////////");
                    for (; count >= 0; count--) {
							     CE_ElectricalItems = new org.jdom2.Element(
									"ElectricalItems");

							// ElectricalItems elements

							CE_electricalItemsID = new org.jdom2.Element(
									"ElectricalItemsID");
							CE_electricalItemsID.setText(jsonResult.getString(
									"C_EL_ITEMSID").trim());

							CE_policyId = new org.jdom2.Element("PolicyId");
							CE_policyId.setText(jsonResult.getString("C_EL_POLICYID")
									.trim());

							CE_serialNo = new org.jdom2.Element("SerialNo");
							CE_serialNo.setText(jsonResult.getString("C_EL_SERIALNO")
									.trim());

							CE_makeModel = new org.jdom2.Element("MakeModel");
							CE_makeModel.setText(jsonResult.getString("C_EL_MAKEMODEL")
									.trim());

							CE_ElectricPremium = new org.jdom2.Element(
									"ElectricPremium");
							CE_ElectricPremium.setText(jsonResult.getString(
									"C_EL_PREMIUM").trim());

							CE_description = new org.jdom2.Element("Description");
							CE_description.setText(jsonResult.getString(
									"C_EL_DESCRIPTION").trim());

							CE_electricalAccessorySlNo = new org.jdom2.Element(
									"ElectricalAccessorySlNo");
							CE_electricalAccessorySlNo.setText(jsonResult.getString(
									"C_EL_ACCESSORYSLNO").trim());

							CE_sumInsured = new org.jdom2.Element("SumInsured");
							CE_sumInsured.setText(jsonResult.getString(
									"C_EL_SUMINSURED").trim());
							
							C_ElectricItems.addContent(CE_ElectricalItems);

							CE_ElectricalItems.addContent(CE_electricalItemsID);
							CE_ElectricalItems.addContent(CE_policyId);
							CE_ElectricalItems.addContent(CE_serialNo);
							CE_ElectricalItems.addContent(CE_makeModel);
							CE_ElectricalItems.addContent(CE_ElectricPremium);
							CE_ElectricalItems.addContent(CE_description);
							CE_ElectricalItems.addContent(CE_electricalAccessorySlNo);
							CE_ElectricalItems.addContent(CE_sumInsured);
				    	  
                 }
             }
//				    	  
//				
//
				org.jdom2.Element C_nonElectricItems = new org.jdom2.Element("NONELECTRICITEMS");
				
				

				int count1 = Integer.parseInt(jsonResult.getString("C_NEL"));
				org.jdom2.Element CNE_NonElectricalItems = null, CNE_NonElectricalItemsID = null, CNE_policyId = null, CNE_serialNo = null, CNE_makeModel = null, CNE_NonElectricPremium = null, CNE_Description = null, CNE_Category = null, CNE_NonElectricalAccessorySlNo = null, CNE_sumInsured = null;
				if((!relianceIntBean.getNonelectricalitemlist().isEmpty())&& (relianceIntBean.getNonelectricalitemlist()!=null))
				{
					relianceIntBean.setNonelectricalItem(relianceIntBean.getNonelectricalitemlist().split("~"));
					 int c=relianceIntBean.getNonelectricalItem().length;
//					 System.out.println("c/////////////////////"+c);
					 if(c>0)
					 {
				 for (int index=0;index<c;index++) {
					 
					 String nonele=relianceIntBean.getNonelectricalItem()[index];
					 String electriaclItem[]= nonele.split(",");
					 
					CNE_NonElectricalItems = new org.jdom2.Element(
							"NonElectricalItems");

					// ElectricalItems elements

					CNE_NonElectricalItemsID = new org.jdom2.Element(
							"NonElectricalItemsID");
					CNE_NonElectricalItemsID.setText(jsonResult.getString(
							"C_NEL_ITEMSID").trim());

					CNE_policyId = new org.jdom2.Element("PolicyId");
					CNE_policyId.setText(jsonResult.getString("C_NEL_POLICYID")
							.trim());

					CNE_serialNo = new org.jdom2.Element("SerialNo");
					CNE_serialNo.setText(jsonResult.getString("C_NEL_SERIALNO")
							.trim());

					CNE_makeModel = new org.jdom2.Element("MakeModel");
//					CNE_makeModel.setText(jsonResult.getString(
//							"C_NEL_MAKEMODEL").trim());

					CNE_makeModel.setText(electriaclItem[1]);
					
					CNE_NonElectricPremium = new org.jdom2.Element(
							"NonElectricPremium");
					CNE_NonElectricPremium.setText(jsonResult.getString(
							"C_NEL_PREMIUM").trim());

					CNE_Description = new org.jdom2.Element("Description");
//					CNE_Description.setText(jsonResult.getString(
//							"C_NEL_DESCRIPTION").trim());
					
					CNE_Description.setText(electriaclItem[0]);

					CNE_Category = new org.jdom2.Element("Category");
					CNE_Category.setText(jsonResult.getString("C_NEL_CATEGORY")
							.trim());

					CNE_NonElectricalAccessorySlNo = new org.jdom2.Element(
							"ElectricalAccessorySlNo");
					CNE_NonElectricalAccessorySlNo.setText(jsonResult
							.getString("C_NEL_ACCESSORYSLNO").trim());

					CNE_sumInsured = new org.jdom2.Element("SumInsured");
//					CNE_sumInsured.setText(jsonResult.getString(
//							"C_NEL_SUMINSURED").trim());
					CNE_sumInsured.setText(electriaclItem[3]);
					
					C_nonElectricItems.addContent(CNE_NonElectricalItems);
					CNE_NonElectricalItems.addContent(CNE_NonElectricalItemsID);
					CNE_NonElectricalItems.addContent(CNE_policyId);
					CNE_NonElectricalItems.addContent(CNE_serialNo);
					CNE_NonElectricalItems.addContent(CNE_makeModel);
					CNE_NonElectricalItems.addContent(CNE_NonElectricPremium);
					CNE_NonElectricalItems.addContent(CNE_Description);
					CNE_NonElectricalItems.addContent(CNE_Category);
					CNE_NonElectricalItems
							.addContent(CNE_NonElectricalAccessorySlNo);
					CNE_NonElectricalItems.addContent(CNE_sumInsured);
				 }

				}
				}
				else{
					for (; count1 >= 0; count1--) {
						 
						 
						CNE_NonElectricalItems = new org.jdom2.Element(
								"NonElectricalItems");

						// ElectricalItems elements

						CNE_NonElectricalItemsID = new org.jdom2.Element(
								"NonElectricalItemsID");
						CNE_NonElectricalItemsID.setText(jsonResult.getString(
								"C_NEL_ITEMSID").trim());

						CNE_policyId = new org.jdom2.Element("PolicyId");
						CNE_policyId.setText(jsonResult.getString("C_NEL_POLICYID")
								.trim());

						CNE_serialNo = new org.jdom2.Element("SerialNo");
						CNE_serialNo.setText(jsonResult.getString("C_NEL_SERIALNO")
								.trim());

						CNE_makeModel = new org.jdom2.Element("MakeModel");
						CNE_makeModel.setText(jsonResult.getString(
								"C_NEL_MAKEMODEL").trim());

					
						
						CNE_NonElectricPremium = new org.jdom2.Element(
								"NonElectricPremium");
						CNE_NonElectricPremium.setText(jsonResult.getString(
								"C_NEL_PREMIUM").trim());

						CNE_Description = new org.jdom2.Element("Description");
						CNE_Description.setText(jsonResult.getString(
								"C_NEL_DESCRIPTION").trim());
						
					

						CNE_Category = new org.jdom2.Element("Category");
						CNE_Category.setText(jsonResult.getString("C_NEL_CATEGORY")
								.trim());

						CNE_NonElectricalAccessorySlNo = new org.jdom2.Element(
								"ElectricalAccessorySlNo");
						CNE_NonElectricalAccessorySlNo.setText(jsonResult
								.getString("C_NEL_ACCESSORYSLNO").trim());

						CNE_sumInsured = new org.jdom2.Element("SumInsured");
						CNE_sumInsured.setText(jsonResult.getString(
								"C_NEL_SUMINSURED").trim());
						
						C_nonElectricItems.addContent(CNE_NonElectricalItems);
						CNE_NonElectricalItems.addContent(CNE_NonElectricalItemsID);
						CNE_NonElectricalItems.addContent(CNE_policyId);
						CNE_NonElectricalItems.addContent(CNE_serialNo);
						CNE_NonElectricalItems.addContent(CNE_makeModel);
						CNE_NonElectricalItems.addContent(CNE_NonElectricPremium);
						CNE_NonElectricalItems.addContent(CNE_Description);
						CNE_NonElectricalItems.addContent(CNE_Category);
						CNE_NonElectricalItems
								.addContent(CNE_NonElectricalAccessorySlNo);
						CNE_NonElectricalItems.addContent(CNE_sumInsured);
						
					 }

				}
				org.jdom2.Element PreviousInsuranceDetails = new org.jdom2.Element(
						"PreviousInsuranceDetails");

				org.jdom2.Element IsVehicleOfPreviousPolicySold = new org.jdom2.Element(
						"IsVehicleOfPreviousPolicySold");
				IsVehicleOfPreviousPolicySold.setText(jsonResult.getString(
						"PI_PREVYEARINSURER").trim());

				org.jdom2.Element PrevYearPolicyStartDate = new org.jdom2.Element(
						"PrevYearPolicyStartDate");
				PrevYearPolicyStartDate.setText(jsonResult.getString(
						"PI_PREVYEARPOLICYSTARTDATE").trim());

				org.jdom2.Element PrevYearPolicyEndDate = new org.jdom2.Element(
						"PrevYearPolicyEndDate");
				PrevYearPolicyEndDate.setText(jsonResult.getString(
						"PIPREVYEARPOLICYENDDATE").trim());

				org.jdom2.Element PrevYearNCB = new org.jdom2.Element(
						"PrevYearPolicyNo");
				PrevYearNCB.setText(jsonResult.getString("PI_PREVYEARPOLICYNO")
						.trim());

				org.jdom2.Element NCBEligibility = new org.jdom2.Element(
						"NCBEligibility");

				org.jdom2.Element IsNCBApplicable = new org.jdom2.Element(
						"IsNCBApplicable");
				IsNCBApplicable.setText(jsonResult.getString(
						"NCB_ISNCBAPPLICABLE").trim());

				org.jdom2.Element NCBEligibilityCriteria = new org.jdom2.Element(
						"NCBEligibilityCriteria");
				NCBEligibilityCriteria.setText(jsonResult.getString(
						"NCB_NCBELIGIBILITYCRITERIA").trim());

				org.jdom2.Element PreviousNCB = new org.jdom2.Element(
						"PreviousNCB");
				PreviousNCB.setText(jsonResult.getString("NCB_PREVIOUSNCB")
						.toString().trim());
				org.jdom2.Element CurrentNCB = new org.jdom2.Element(
						"CurrentNCB");
				CurrentNCB.setText(jsonResult.getString("NCB_CURRENTNCB").toString().trim());
				
				org.jdom2.Element UserID = new org.jdom2.Element("UserID");
				UserID.setText(jsonResult.getString("USERID").toString().trim());

				org.jdom2.Element ProductCode1 = new org.jdom2.Element(
						"ProductCode");
				ProductCode1
						.setText(jsonResult.getString("PRODUCTCODE").trim());

				org.jdom2.Element SourceSystemID = new org.jdom2.Element(
						"SourceSystemID");
				SourceSystemID.setText(jsonResult.getString("SOURCESYSTEMID")
						.trim());

				org.jdom2.Element AuthToken = new org.jdom2.Element("AuthToken");
				AuthToken.setText(jsonResult.getString("AUTHTOKEN").trim());
            if(relianceIntBean.getRequest_for().equals("coverage")){
				ClientDetails.addContent(ClientType);
			}else{			
				ClientDetails.addContent(ClientType);
				ClientDetails.addContent(LastName);
				ClientDetails.addContent(MidName);
				ClientDetails.addContent(ForeName);
				ClientDetails.addContent(CorporateName);
				ClientDetails.addContent(OccupationID);
				ClientDetails.addContent(DOB);
				ClientDetails.addContent(Gender);
				ClientDetails.addContent(PhoneNo);
				ClientDetails.addContent(MobileNo);
				ClientDetails.addContent(ClientAddress);
			}

				ClientAddress.addContent(CommunicationAddress);
				CommunicationAddress.addContent(AddressType);
				CommunicationAddress.addContent(Address1);
				CommunicationAddress.addContent(Address2);
				CommunicationAddress.addContent(Address3);
				CommunicationAddress.addContent(CityID);
				CommunicationAddress.addContent(DistrictID);
				CommunicationAddress.addContent(StateID);

				CommunicationAddress.addContent(Pincode);
				CommunicationAddress.addContent(Country);
				CommunicationAddress.addContent(NearestLandmark);

				ClientAddress.addContent(PermanentAddress);
				PermanentAddress.addContent(PA_AddressType);
				PermanentAddress.addContent(PA_Address1);
				PermanentAddress.addContent(PA_Address2);
				PermanentAddress.addContent(PA_Address3);
				PermanentAddress.addContent(PA_CityID);
				PermanentAddress.addContent(PA_DistrictID);
				PermanentAddress.addContent(PA_StateID);
				PermanentAddress.addContent(PA_Pincode);
				PermanentAddress.addContent(PA_Country);
				PermanentAddress.addContent(PA_NearestLandmark);

				ClientAddress.addContent(RegistrationAddress);
				RegistrationAddress.addContent(RA_AddressType);
				RegistrationAddress.addContent(RA_Address1);
				RegistrationAddress.addContent(RA_Address2);
				RegistrationAddress.addContent(RA_Address3);
				RegistrationAddress.addContent(RA_CityID);
				RegistrationAddress.addContent(RA_StateID);
				RegistrationAddress.addContent(RA_DistrictID);
				RegistrationAddress.addContent(RA_Pincode);
				RegistrationAddress.addContent(RA_Country);
				RegistrationAddress.addContent(RA_NearestLandmark);

				ClientDetails.addContent(EmailID);
				ClientDetails.addContent(Salutation);
				ClientDetails.addContent(MaritalStatus);
				ClientDetails.addContent(Nationality);

				Policy.addContent(BusinessType);
				Policy.addContent(Cover_From);
				Policy.addContent(AgentName);
				Policy.addContent(ProductCode);
				Policy.addContent(OtherSystemName);
				Policy.addContent(isMotorQuote);
				Policy.addContent(isMotorQuoteFlow);
				Policy.addContent(Branch_Code);

				Risk1.addContent(VehicleMakeID);
				Risk1.addContent(VehicleModelID);
				Risk1.addContent(CubicCapacity);
				Risk1.addContent(Zone);
				Risk1.addContent(RTOLocationID);
				Risk1.addContent(ExShowroomPrice);
				Risk1.addContent(IDV);
				Risk1.addContent(DateOfPurchase);
				Risk1.addContent(ManufactureMonth);
				Risk1.addContent(ManufactureYear);
				Risk1.addContent(EngineNo);
				Risk1.addContent(Chassis);
				Risk1.addContent(IsVehicleHypothicated);
				Risk1.addContent(FinanceType);
				Risk1.addContent(FinancierName);
				Risk1.addContent(FinancierAddress);
				Risk1.addContent(FinancierCity);
				Risk1.addContent(IsRegAddressSameasCommAddress);
				Risk1.addContent(IsPermanentAddressSameasCommAddress);
				Risk1.addContent(IsRegAddressSameasPermanentAddress);
				Risk1.addContent(VehicleVariant);
				Risk1.addContent(StateOfRegistrationID);
				Risk1.addContent(Rto_RegionCode);

				geoExtn1.addContent(GeoCountries);
				geoExtn1.addContent(CountryMandatory);
				geoExtn1.addContent(IsChecked);
				
				geoExtnCountry.addContent(geoExtn1);

				FibreGlassFuelTank.addContent(FibreGlassFuelTank1);
				Cover.addContent(IsSpeciallyDesignedForHandicapped);
				Cover.addContent(NilDepreciationCoverage);
				Cover.addContent(IsNilDepreciation);
				Cover.addContent(DrivingTuitionCoverage);
				Cover.addContent(IsUsedForDrivingTuition);
				Cover.addContent(IsFibreGlassFuelTankFitted);
				Cover.addContent(FibreGlassFuelTank);
				Cover.addContent(C_IsVoluntaryDeductableOpted);
				Cover.addContent(C_VoluntaryDeductableAmount);
				Cover.addContent(C_IsAntiTheftDeviceFitted);
				Cover.addContent(C_IsAutomobileAssociationMember);
				Cover.addContent(C_IsPAToUnnamedPassengerCovered);
				Cover.addContent(C_NoOfUnnamedPassenegersCovered);
				Cover.addContent(C_UnnamedPassengersSI);
				Cover.addContent(C_IsElectricalItemFitted);
				Cover.addContent(C_ElectricalItemsTotalSI);
				Cover.addContent(geoExtn);
				Cover.addContent(geoExtnCountry);

				Cover.addContent(C_IsNonElectricalItemFitted);
				Cover.addContent(C_NonElectricalItemsTotalSI);
				Cover.addContent(C_IsBiFuelKit);
				Cover.addContent(C_BiFuelKitSi);
				Cover.addContent(C_IsTPPDCover);
				Cover.addContent(C_IsBasicODCoverage);
				Cover.addContent(C_IsBasicLiability);
				Cover.addContent(C_IsPAToOwnerDriverCoverd);
				Cover.addContent(C_PACoverToOwner);
				Cover.addContent(C_BifuelKit);
				Cover.addContent(C_VoluntaryDeductible);
				Cover.addContent(C_AntiTheftDeviceDiscount);
				Cover.addContent(C_PAToUnNamedPassenger);
				Cover.addContent(C_ElectricItems);
				Cover.addContent(C_nonElectricItems);
				Cover.addContent(C_AutomobileAssociationMembershipDiscount);

				C_PACoverToOwner.addContent(CPA_PACoverToOwner1);
				CPA_PACoverToOwner1.addContent(CPA_NomineeName);
				CPA_PACoverToOwner1.addContent(CPA_NomineeDOB);
				CPA_PACoverToOwner1.addContent(CPA_NomineeRelationship);

				C_BifuelKit.addContent(CB_BifuelKit1);
				CB_BifuelKit1.addContent(CB_IsChecked);
				CB_BifuelKit1.addContent(CB_ISLpgCng);
				CB_BifuelKit1.addContent(CB_SumInsured);

				C_VoluntaryDeductible.addContent(CV_VoluntaryDeductible);
				CV_VoluntaryDeductible.addContent(CV_SumInsured);
				CV_VoluntaryDeductible.addContent(CV_IsMandatory);
				CV_VoluntaryDeductible.addContent(CV_PolicyCoverID);
				CV_VoluntaryDeductible.addContent(CV_IsChecked);
				CV_VoluntaryDeductible.addContent(CV_NoOfItems);
				CV_VoluntaryDeductible.addContent(CV_PackageName);

				C_AntiTheftDeviceDiscount
						.addContent(CA_AntiTheftDeviceDiscount);
				CA_AntiTheftDeviceDiscount.addContent(CA_IsMandatory);
				CA_AntiTheftDeviceDiscount.addContent(CA_IsChecked);
				CA_AntiTheftDeviceDiscount.addContent(CA_NoOfItems);
				CA_AntiTheftDeviceDiscount.addContent(CA_PackageName);

				C_AutomobileAssociationMembershipDiscount
						.addContent(CAMD_AutomobileAssociationMembershipDiscount);
				CAMD_AutomobileAssociationMembershipDiscount
						.addContent(CAMD_IsMandatory);
				CAMD_AutomobileAssociationMembershipDiscount
						.addContent(CAMD_IsChecked);
				CAMD_AutomobileAssociationMembershipDiscount
						.addContent(CAMD_NoOfItems);
				CAMD_AutomobileAssociationMembershipDiscount
						.addContent(CAMD_PackageName);

				C_PAToUnNamedPassenger.addContent(CPU_PAToUnNamedPassenger);
				CPU_PAToUnNamedPassenger.addContent(CPU_IsMandatory);
				CPU_PAToUnNamedPassenger.addContent(CPU_IsChecked);
				CPU_PAToUnNamedPassenger.addContent(CPU_NoOfItems);
				CPU_PAToUnNamedPassenger.addContent(CPU_PackageName);
				CPU_PAToUnNamedPassenger.addContent(CPU_PolicyCoverID);
				CPU_PAToUnNamedPassenger.addContent(CPU_SumInsured);

				

				

				Vehicle.addContent(Registration_Number);
				Vehicle.addContent(RegistrationNumber_New);
				Vehicle.addContent(Registration_date);
				Vehicle.addContent(SeatingCapacity);
				Vehicle.addContent(TypeOfFuel);
				Vehicle.addContent(MiscTypeOfVehicleID);

				document2.getRootElement().addContent(ClientDetails);
				document2.getRootElement().addContent(Policy);
				document2.getRootElement().addContent(Risk1);
				document2.getRootElement().addContent(Vehicle);
				System.out.println("Coverage====" + relianceIntBean.getRequest_for());
				if (relianceIntBean.getRequest_for().equals("coverage")) {
					System.out.println("Coverage request");
				} else {
					document2.getRootElement().addContent(Cover);
				}
				document2.getRootElement().addContent(PreviousInsuranceDetails);
				document2.getRootElement().addContent(NCBEligibility);
				document2.getRootElement().addContent(UserID);
				document2.getRootElement().addContent(ProductCode1);
				document2.getRootElement().addContent(SourceSystemID);
				document2.getRootElement().addContent(AuthToken);

				PreviousInsuranceDetails
						.addContent(IsVehicleOfPreviousPolicySold);
				PreviousInsuranceDetails.addContent(PrevYearPolicyStartDate);
				PreviousInsuranceDetails.addContent(PrevYearPolicyEndDate);
				PreviousInsuranceDetails.addContent(PrevYearNCB);

				NCBEligibility.addContent(IsNCBApplicable);
				NCBEligibility.addContent(NCBEligibilityCriteria);
				NCBEligibility.addContent(PreviousNCB);
				NCBEligibility.addContent(CurrentNCB);

				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xx = xmlOutput.outputString(document2);

				xmlOutput.output(document2, new FileWriter(path+"\\f.xml"));
//				 System.out.println("xx="+xx);
			
			// TransformerFactory factory=TransformerFactory.newInstance();
			// Transformer transformer=factory.newTransformer();
			// domSource= new DOMSource(document);

			// String xmlstr =
			// "<PolicyDetails><CoverDetails/><TrailerDetails/><ClientDetails><ClientType>0</ClientType></ClientDetails><Policy><BusinessType>1</BusinessType><Cover_From>08/03/2017</Cover_From><Branch_Code>1101</Branch_Code><AgentName>HB</AgentName><productcode>2311</productcode><OtherSystemName>1</OtherSystemName><isMotorQuote>true</isMotorQuote></Policy><Risk><VehicleMakeID>192</VehicleMakeID><VehicleModelID>8143</VehicleModelID><Zone>A</Zone><RTOLocationID>584</RTOLocationID><ExShowroomPrice>392838</ExShowroomPrice><IDV>335877</IDV><DateOfPurchase>08/03/2017</DateOfPurchase><ManufactureMonth>03</ManufactureMonth><ManufactureYear>2017</ManufactureYear><IsVehicleHypothicated>false</IsVehicleHypothicated><VehicleVariant>LXI</VehicleVariant><StateOfRegistrationID>21</StateOfRegistrationID><Rto_RegionCode>MH-12</Rto_RegionCode></Risk><Vehicle><Registration_Number>MH-12-W-2642</Registration_Number><RegistrationNumber_New/><Registration_date>08/03/2017</Registration_date><SeatingCapacity>5</SeatingCapacity><TypeOfFuel>1</TypeOfFuel><MiscTypeOfVehicleID/></Vehicle><Cover><IsVoluntaryDeductableOpted>true</IsVoluntaryDeductableOpted><VoluntaryDeductableAmount>2500</VoluntaryDeductableAmount><IsAntiTheftDeviceFitted>true</IsAntiTheftDeviceFitted><IsAutomobileAssociationMember>true</IsAutomobileAssociationMember><IsPAToUnnamedPassengerCovered>true</IsPAToUnnamedPassengerCovered><NoOfUnnamedPassenegersCovered>5</NoOfUnnamedPassenegersCovered><UnnamedPassengersSI>100000</UnnamedPassengersSI><IsElectricalItemFitted>true</IsElectricalItemFitted><ElectricalItemsTotalSI>10000</ElectricalItemsTotalSI><IsNonElectricalItemFitted>true</IsNonElectricalItemFitted><NonElectricalItemsTotalSI>10000</NonElectricalItemsTotalSI><IsBiFuelKit>false</IsBiFuelKit><BiFuelKitSi>0</BiFuelKitSi><IsTPPDCover>true</IsTPPDCover><IsBasicODCoverage>true</IsBasicODCoverage><IsBasicLiability>true</IsBasicLiability><IsPAToOwnerDriverCoverd>true</IsPAToOwnerDriverCoverd><PACoverToOwner><PACoverToOwner><NomineeName></NomineeName><NomineeDOB></NomineeDOB><NomineeRelationship></NomineeRelationship></PACoverToOwner></PACoverToOwner><BifuelKit><BifuelKit><IsChecked>false</IsChecked><ISLpgCng>false</ISLpgCng><SumInsured>0</SumInsured></BifuelKit></BifuelKit><VoluntaryDeductible><VoluntaryDeductible><IsMandatory>false</IsMandatory><PolicyCoverID/><SumInsured>2500</SumInsured><IsChecked>false</IsChecked><NoOfItems/><PackageName/></VoluntaryDeductible></VoluntaryDeductible><AntiTheftDeviceDiscount><AntiTheftDeviceDiscount><IsMandatory>false</IsMandatory><IsChecked>true</IsChecked><NoOfItems/><PackageName/></AntiTheftDeviceDiscount></AntiTheftDeviceDiscount><AutomobileAssociationMembershipDiscount><AutomobileAssociationMembershipDiscount><IsMandatory>false</IsMandatory><IsChecked>true</IsChecked><NoOfItems/><PackageName/></AutomobileAssociationMembershipDiscount></AutomobileAssociationMembershipDiscount><PAToUnNamedPassenger><PAToUnNamedPassenger><IsMandatory>false</IsMandatory><IsChecked>true</IsChecked><NoOfItems>5</NoOfItems><PackageName/><PolicyCoverID/><SumInsured>100000</SumInsured></PAToUnNamedPassenger></PAToUnNamedPassenger><ElectricItems><ElectricalItems><ElectricalItemsID/><PolicyId/><SerialNo/><MakeModel/><ElectricPremium/><Description/><ElectricalAccessorySlNo/><SumInsured>0</SumInsured></ElectricalItems></ElectricItems><NonElectricItems><NonElectricalItems><NonElectricalItemsID/><PolicyID/><SerialNo/><MakeModel/><NonElectricPremium/><Description/><Category/><NonElectricalAccessorySlNo/><SumInsured/></NonElectricalItems></NonElectricItems></Cover><UserID>100002</UserID><ProductCode>2311</ProductCode><SourceSystemID>100002</SourceSystemID><AuthToken>Pass@123</AuthToken></PolicyDetails>";

			// File file=new File("D:\\file.xml");
			// StreamResult result = new StreamResult(file);
			// transformer.transform(domSource, result);

			// File fXmlFile = new File("D:\\file.xml");
			// DocumentBuilderFactory dbFactory =
			// DocumentBuilderFactory.newInstance();
			// DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

			// doc.getDocumentElement().normalize();
			// org.w3c.dom.NodeList doc.getElementsByTagName("ClientDetails");

			// String dom= domSource.toString();
			//
			//
			// System.out.println("----->"+dom.toString());
			//
			// System.out.println("----->"+domSource.getNode());
			//

			// String s =PostInfoToAPI(xx,"CoverageDetailsForMotor");
			// hashmap= readResponce(s);
			// File file2=new File("D:\\f.xml");
			// StreamResult result1 = new StreamResult(file2);
			//
			// DocumentBuilderFactory
			// builderFactory1=DocumentBuilderFactory.newInstance();
			// DocumentBuilder builder1=builderFactory1.newDocumentBuilder();
			// org.w3c.dom.Document document1= builder1.parse(file2);
			//
			// document1.getDocumentElement().normalize();
			// org.w3c.dom.NodeList flowList =
			// document1.getElementsByTagName("MotorPolicy");
			// for (int i = 0; i < flowList.getLength(); i++) {
			// org.w3c.dom.NodeList childList =
			// flowList.item(i).getChildNodes();
			// for (int j = 0; j < childList.getLength(); j++) {
			// Node childNode = childList.item(j);
			// if ("status".equals(childNode.getNodeName())) {
			// System.out.println(childList.item(j).getTextContent()
			// .trim());
			// }
			// }
			// }
			//
			//}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("//////////////////////////" + e);
		}
		return xx;

	}
	
	
}
