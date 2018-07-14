package com.uat.hbc.insurance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;






import com.google.gson.Gson;


import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.insurance.model.PAEntryModel;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

@Repository
public class PAEntryDao {
	private DbProcess dbProcessImpl;

	public DbProcess getDbProcessImpl() {
		return dbProcessImpl;
	}

	public void setDbProcessImpl(DbProcess dbProcessImpl) {
		this.dbProcessImpl = dbProcessImpl;
	}

	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public Result insertUpdatePARecordPKGwithArray(PAEntryModel paEntryModel, String userId, String branchId,
			String userDesc, String statusVal) {
		Result resObj = new Result();

		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";

		ArrayDescriptor initialIdDesc = null;
		ArrayDescriptor firstNameDesc = null;
		ArrayDescriptor middleNameDesc = null;
		ArrayDescriptor lastNameDesc = null;
		ArrayDescriptor dobDesc = null;
		ArrayDescriptor genderDesc = null;
		ArrayDescriptor aadharNoDesc = null;
		ArrayDescriptor panNoDesc = null;
		ArrayDescriptor contactNoDesc = null;
		ArrayDescriptor emailIdDesc = null;
		ArrayDescriptor diseaseDesc = null;
		ArrayDescriptor occupationIdDesc = null;
		ArrayDescriptor relIdDesc = null;
		ArrayDescriptor annIncomeDesc = null;
		ArrayDescriptor sumInsADesc = null;
		ArrayDescriptor premADesc = null;
		ArrayDescriptor sumInsBDesc = null;
		ArrayDescriptor premBDesc = null;
		ArrayDescriptor sumInsCDesc = null;
		ArrayDescriptor premCDesc = null;
		ArrayDescriptor sumInsDDesc = null;
		ArrayDescriptor premDDesc = null;
		ArrayDescriptor sumInsABCDDesc = null;
		ArrayDescriptor prevMemberNoDesc = null;
		ArrayDescriptor prevGicIdDesc = null;
		ArrayDescriptor prevProdIdDesc = null;
		ArrayDescriptor prevPolicyTypeDesc = null;
		ArrayDescriptor prevPeriodDesc = null;
		ArrayDescriptor prevStartDateDesc = null;
		ArrayDescriptor prevEndDateDesc = null;
		ArrayDescriptor prevTotalSumInsDesc = null;
		ArrayDescriptor prevInsTypeDesc = null;
		ArrayDescriptor prevPolicyNoDesc = null;
		ArrayDescriptor prevNcbDesc = null;
		ArrayDescriptor memberNoDesc = null;
		ArrayDescriptor addOnDesc = null;
		ArrayDescriptor addOnSumInsDesc = null;
		ArrayDescriptor addOnPremDesc = null;

		Connection conn = null;
		Connection connWrapped = null;

		try {
			System.out.println("conn 111" + conn);
			connWrapped = jdbcTemplate.getDataSource().getConnection();
			System.out.println("conn 222" + conn);
			conn = connWrapped.unwrap(OracleConnection.class);
			System.out.println("conn 333" + conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			initialIdDesc = new ArrayDescriptor("TY_NUMBER", conn);
			firstNameDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			middleNameDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			lastNameDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			dobDesc = new ArrayDescriptor("TY_DATE", conn);
			genderDesc = new ArrayDescriptor("TY_NUMBER", conn);
			aadharNoDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			panNoDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			contactNoDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			emailIdDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			diseaseDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			occupationIdDesc = new ArrayDescriptor("TY_NUMBER", conn);
			relIdDesc = new ArrayDescriptor("TY_NUMBER", conn);
			annIncomeDesc = new ArrayDescriptor("TY_NUMBER", conn);
			sumInsADesc = new ArrayDescriptor("TY_NUMBER", conn);
			premADesc = new ArrayDescriptor("TY_NUMBER", conn);
			sumInsBDesc = new ArrayDescriptor("TY_NUMBER", conn);
			premBDesc = new ArrayDescriptor("TY_NUMBER", conn);
			sumInsCDesc = new ArrayDescriptor("TY_NUMBER", conn);
			premCDesc = new ArrayDescriptor("TY_NUMBER", conn);
			sumInsDDesc = new ArrayDescriptor("TY_NUMBER", conn);
			premDDesc = new ArrayDescriptor("TY_NUMBER", conn);
			sumInsABCDDesc = new ArrayDescriptor("TY_NUMBER", conn);
			prevMemberNoDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			prevGicIdDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			prevProdIdDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			prevPolicyTypeDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			prevPeriodDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			prevStartDateDesc = new ArrayDescriptor("TY_DATE", conn);
			prevEndDateDesc = new ArrayDescriptor("TY_DATE", conn);
			prevTotalSumInsDesc = new ArrayDescriptor("TY_NUMBER", conn);
			prevInsTypeDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			prevPolicyNoDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			prevNcbDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			memberNoDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			addOnDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			addOnSumInsDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			addOnPremDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ARRAY initialIdDbArr = null;
		ARRAY firstNameDbArr = null;
		ARRAY middleNameDbArr = null;
		ARRAY lastNameDbArr = null;
		ARRAY dobDbArr = null;
		ARRAY genderDbArr = null;
		ARRAY aadharNoDbArr = null;
		ARRAY panNoDbArr = null;
		ARRAY contactNoDbArr = null;
		ARRAY emailIdDbArr = null;
		ARRAY diseaseDbArr = null;
		ARRAY occupIdDbArr = null;
		ARRAY relDbArr = null;
		ARRAY annIncomeDbArr = null;
		ARRAY sumInsADbArr = null;
		ARRAY premADbArr = null;
		ARRAY sumInsBDbArr = null;
		ARRAY premBDbArr = null;
		ARRAY sumInsCDbArr = null;
		ARRAY premCDbArr = null;
		ARRAY sumInsdDbArr = null;
		ARRAY premDDbArr = null;
		ARRAY sumInsABCDDbArr = null;
		ARRAY prevMemberNoDbArr = null;
		ARRAY prevgicidDbArr = null;
		ARRAY prevProdIdDbArr = null;
		ARRAY prevPolicyTypeDbArr = null;
		ARRAY prevPeriodDbArr = null;
		ARRAY prevStartDateDbArr = null;
		ARRAY prevEndDateDbArr = null;
		ARRAY prevTotalSumInsDbArr = null;
		ARRAY prevInsTypeArr = null;
		ARRAY prevPolicyNoDbArr = null;
		ARRAY prevNcbNoDbArr = null;
		ARRAY memberNoDbArr = null;
		ARRAY addOnDbArr = null;
		ARRAY addOnSumInsDbArr = null;
		ARRAY addOnPremDbArr = null;
		System.out.println("conn 444" + conn);
		try {
			initialIdDbArr = new ARRAY(initialIdDesc, conn, paEntryModel.getInitialId());
			firstNameDbArr = new ARRAY(firstNameDesc, conn, paEntryModel.getFirstName());
			middleNameDbArr = new ARRAY(middleNameDesc, conn, paEntryModel.getMiddleName());
			lastNameDbArr = new ARRAY(lastNameDesc, conn, paEntryModel.getLastName());
			dobDbArr = new ARRAY(dobDesc, conn, paEntryModel.getDob());
			genderDbArr = new ARRAY(genderDesc, conn, paEntryModel.getGender());
			aadharNoDbArr = new ARRAY(aadharNoDesc, conn, paEntryModel.getAadharNo());
			panNoDbArr = new ARRAY(panNoDesc, conn, paEntryModel.getPanNo());
			contactNoDbArr = new ARRAY(contactNoDesc, conn, paEntryModel.getContactNo());
			emailIdDbArr = new ARRAY(emailIdDesc, conn, paEntryModel.getEmailId());
			diseaseDbArr = new ARRAY(diseaseDesc, conn, paEntryModel.getDisease());
			occupIdDbArr = new ARRAY(occupationIdDesc, conn, paEntryModel.getOccupationId());
			relDbArr = new ARRAY(relIdDesc, conn, paEntryModel.getRelationId());
			annIncomeDbArr = new ARRAY(annIncomeDesc, conn, paEntryModel.getAnnualIncome());
			sumInsADbArr = new ARRAY(sumInsADesc, conn, paEntryModel.getSumInsA());
			premADbArr = new ARRAY(premADesc, conn, paEntryModel.getPremA());
			sumInsBDbArr = new ARRAY(sumInsBDesc, conn, paEntryModel.getSumInsB());
			premBDbArr = new ARRAY(premBDesc, conn, paEntryModel.getPremB());
			sumInsCDbArr = new ARRAY(sumInsCDesc, conn, paEntryModel.getSumInsC());
			premCDbArr = new ARRAY(premCDesc, conn, paEntryModel.getPremC());
			sumInsdDbArr = new ARRAY(sumInsDDesc, conn, paEntryModel.getSumInsD());
			premDDbArr = new ARRAY(premDDesc, conn, paEntryModel.getPremD());
			sumInsABCDDbArr = new ARRAY(sumInsABCDDesc, conn, paEntryModel.getSumInsABCD());
			prevMemberNoDbArr = new ARRAY(prevMemberNoDesc, conn, paEntryModel.getPrevMemberNo());
			prevgicidDbArr = new ARRAY(prevGicIdDesc, conn, paEntryModel.getPrevGicId());
			prevProdIdDbArr = new ARRAY(prevProdIdDesc, conn, paEntryModel.getPrevProdId());
			prevPolicyTypeDbArr = new ARRAY(prevPolicyTypeDesc, conn, paEntryModel.getPrevPolicyType());
			prevPeriodDbArr = new ARRAY(prevPeriodDesc, conn, paEntryModel.getPrevPeriod());
			prevStartDateDbArr = new ARRAY(prevStartDateDesc, conn, paEntryModel.getPrevStartDtae());
			prevEndDateDbArr = new ARRAY(prevEndDateDesc, conn, paEntryModel.getPrevEndDate());
			prevTotalSumInsDbArr = new ARRAY(prevTotalSumInsDesc, conn, paEntryModel.getPrevTotalSumIns());
			prevInsTypeArr = new ARRAY(prevInsTypeDesc, conn, paEntryModel.getPrevInsType());
			prevPolicyNoDbArr = new ARRAY(prevPolicyNoDesc, conn, paEntryModel.getPrevPolicyNo());
			prevNcbNoDbArr = new ARRAY(prevNcbDesc, conn, paEntryModel.getPrevNcb());
			memberNoDbArr = new ARRAY(memberNoDesc, conn, paEntryModel.getMemberNo());
			addOnDbArr = new ARRAY(addOnDesc, conn, paEntryModel.getAddOn());
			addOnSumInsDbArr = new ARRAY(addOnSumInsDesc, conn, paEntryModel.getAddOnSumIns());
			addOnPremDbArr = new ARRAY(addOnPremDesc, conn, paEntryModel.getAddOnPrem());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			// jdbcTemplate.getDataSource().getConnection().close();
			connWrapped.close();
			System.out.println("conn 555" + conn);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("conn 666" + conn);
		HashMap inputParaList = new HashMap<>();
		// http://10.0.2.2:8085/ServerforSql/paEntryController?appId=0&applicantId=0&gicId=0&proposalId=0&productId=16&period=1&startDate=10/01/2018&endDate=09/01/2019&policyNo=&cityId=0&zoneId=0&planId=0&totalSI=0&totalPrem=0&cgst=0&sgst=0&igst=0&ugst=0&tax=0&netPrem=0&serviceCharge=0&totalPayment=0&payMode=0&payId=0&initialId=0~1&firstName=fdg&middleName=dfgf&lastName=dfgf&dob=10/01/1997&gender=120&aadharNo=&panNo=tgff&contactNo=9678453453&emailId=df@g.com&disease=&occupationId=62&relationId=62&annualIncome=500000&sumInsA=500000&premA=&sumInsB=500000&premB=&sumInsC=500000&premC=&sumInsD=500000&premD=&sumInsABCD=&addrId=0&areaId=333&house=rt&houseId=0&street=yrtyr&streetId=0&landmark=gh&landmarkId=0&prevMemberNo=&prevGicId=0&prevProdId=0&prevPolicyType=&prevPeriod=&prevStartDate=&prevEndDate=&prevTotalSumIns=&prevInsType=&prevPolicyNo=&prevNcb=&nomId=0&nomInitialId=3&nomFirstName=dfg&nomMiddleName=dfg&nomLastName=fgdg&nomDob=10/01/1998&nomAadharNo=&nomGender=121&nomRelId=4&nomContactNo=7457565463&nomEmailId=fd@g.com&nomAddrId=0&nomAreaId=345&nomHouse=fgh&nomHouseId=0&nomStreet=fghfg&nomStreetId=0&nomLandmark=fghh&nomLandmarkId=0&addOn=&addOnSumIns=&addOnPrem=&userId=1354&branchId=0&userDesc=&memberNo=1&statusVal=
		inputParaList.put("pio_app_id",
				(paEntryModel.getAppId().equals("") ? null : Integer.parseInt(paEntryModel.getAppId())));
		inputParaList.put("PI_APPLICANT_ID",
				(paEntryModel.getApplicantId().equals("") ? null : Integer.parseInt(paEntryModel.getApplicantId())));
		inputParaList.put("PI_GIC_ID",
				(paEntryModel.getGicId().equals("") ? null : Integer.parseInt(paEntryModel.getGicId())));
		inputParaList.put("PI_PRPSL_ID",
				(paEntryModel.getProposalId().equals("") ? null : Integer.parseInt(paEntryModel.getProposalId())));
		inputParaList.put("PI_PRODUCT_ID",
				(paEntryModel.getProductId().equals("") ? null : Integer.parseInt(paEntryModel.getProductId())));
		inputParaList.put("PI_PERIOD",
				(paEntryModel.getPeriod().equals("") ? null : Integer.parseInt(paEntryModel.getPeriod())));
		inputParaList.put("PI_START_DT", AllUtils.getFormattedDateOracle(paEntryModel.getStartDate()));
		inputParaList.put("PI_END_DT", AllUtils.getFormattedDateOracle(paEntryModel.getEndDate()));
		inputParaList.put("PI_POLICY_NO", paEntryModel.getPolicyNo());
		inputParaList.put("PI_CITY_ID",
				(paEntryModel.getCityId().equals("") ? null : Integer.parseInt(paEntryModel.getCityId())));
		inputParaList.put("PI_ZONE_ID",
				(paEntryModel.getZoneId().equals("") ? null : Integer.parseInt(paEntryModel.getZoneId())));
		inputParaList.put("PI_PLAN_ID",
				(paEntryModel.getPlanId().equals("") ? null : Integer.parseInt(paEntryModel.getPlanId())));
		inputParaList.put("PI_TOTAL_SI",
				(paEntryModel.getTotalSI().equals("") ? null : Integer.parseInt(paEntryModel.getTotalSI())));
		inputParaList.put("PI_TOTAL_PREM",
				(paEntryModel.getTotalPrem().equals("") ? null : Integer.parseInt(paEntryModel.getTotalPrem())));
		inputParaList.put("PI_CGST",
				(paEntryModel.getCgst().equals("") ? null : Integer.parseInt(paEntryModel.getCgst())));
		inputParaList.put("PI_SGST",
				(paEntryModel.getSgst().equals("") ? null : Integer.parseInt(paEntryModel.getSgst())));
		inputParaList.put("PI_IGST",
				(paEntryModel.getIgst().equals("") ? null : Integer.parseInt(paEntryModel.getIgst())));
		inputParaList.put("PI_UGST",
				(paEntryModel.getUgst().equals("") ? null : Integer.parseInt(paEntryModel.getUgst())));
		inputParaList.put("PI_TAX",
				(paEntryModel.getTax().equals("") ? null : Integer.parseInt(paEntryModel.getTax())));
		inputParaList.put("PI_NET_PREM",
				(paEntryModel.getNetPrem().equals("") ? null : Integer.parseInt(paEntryModel.getNetPrem())));
		inputParaList.put("PI_SERVICE_CHARGE", (paEntryModel.getServiceCharge().equals("") ? null
				: Integer.parseInt(paEntryModel.getServiceCharge())));
		inputParaList.put("PI_TOTAL_PAYMENT",
				(paEntryModel.getTotalPayment().equals("") ? null : Integer.parseInt(paEntryModel.getTotalPayment())));
		inputParaList.put("PI_PAYMODE",
				(paEntryModel.getPayMode().equals("") ? null : Integer.parseInt(paEntryModel.getPayMode())));
		inputParaList.put("PI_PAY_ID",
				(paEntryModel.getPayId().equals("") ? null : Integer.parseInt(paEntryModel.getPayId())));

		inputParaList.put("PI_INITIAL_ID", initialIdDbArr);
		inputParaList.put("PI_FIRST_NAME", firstNameDbArr);
		inputParaList.put("PI_MIDDLE_NAME", middleNameDbArr);
		inputParaList.put("PI_LAST_NAME", lastNameDbArr);
		inputParaList.put("PI_DOB", dobDbArr);
		inputParaList.put("PI_GENDER", genderDbArr);
		inputParaList.put("PI_ADHAAR_NO", aadharNoDbArr);
		inputParaList.put("PI_PAN_NO", panNoDbArr);
		inputParaList.put("PI_CONTACT_NO", contactNoDbArr);
		inputParaList.put("PI_EMAIL_ID", emailIdDbArr);
		inputParaList.put("PI_DISEASE", diseaseDbArr);
		inputParaList.put("PI_OCCUPATION_ID", occupIdDbArr);
		inputParaList.put("PI_RELATION_ID", relDbArr);
		inputParaList.put("PI_ANNUAL_INCOME", annIncomeDbArr);
		inputParaList.put("PI_SI_A", sumInsADbArr);
		inputParaList.put("PI_PREM_A", premADbArr);
		inputParaList.put("PI_SI_B", sumInsBDbArr);
		inputParaList.put("PI_PREM_B", premBDbArr);
		inputParaList.put("PI_SI_C", sumInsCDbArr);
		inputParaList.put("PI_PREM_C", premCDbArr);
		inputParaList.put("PI_SI_D", sumInsdDbArr);
		inputParaList.put("PI_PREM_D", premDDbArr);
		inputParaList.put("PI_SI_ABCD", sumInsABCDDbArr);
		inputParaList.put("PI_ADR_ID",
				(paEntryModel.getAddrId().equals("") ? null : Integer.parseInt(paEntryModel.getAddrId())));
		inputParaList.put("PI_AREA_ID",
				(paEntryModel.getAreaId().equals("") ? null : Integer.parseInt(paEntryModel.getAreaId())));
		inputParaList.put("PI_HOUSE", paEntryModel.getHouse());
		inputParaList.put("PI_HOUSE_ID",
				(paEntryModel.getHouseId().equals("") ? null : Integer.parseInt(paEntryModel.getHouseId())));
		inputParaList.put("PI_STREET", paEntryModel.getStreet());
		inputParaList.put("PI_STREET_ID",
				(paEntryModel.getStreetId().equals("") ? null : Integer.parseInt(paEntryModel.getStreetId())));
		inputParaList.put("PI_LANDMARK", paEntryModel.getLandmark());
		inputParaList.put("PI_LANDMARK_ID",
				(paEntryModel.getLandmarkId().equals("") ? null : Integer.parseInt(paEntryModel.getLandmarkId())));
		inputParaList.put("PI_PREV_MEMBER_NO", prevMemberNoDbArr);
		inputParaList.put("PI_PREV_GIC_ID", prevgicidDbArr);
		inputParaList.put("PI_PREV_PRODUCT_ID", prevProdIdDbArr);
		inputParaList.put("PI_PREV_POLICY_TYPE", prevPolicyTypeDbArr);
		inputParaList.put("PI_PREV_PERIOD", prevPeriodDbArr);
		inputParaList.put("PI_PREV_START_DT", prevStartDateDbArr);
		inputParaList.put("PI_PREV_END_DATE", prevEndDateDbArr);
		inputParaList.put("PI_PREV_TOTAL_SI", prevTotalSumInsDbArr);
		inputParaList.put("PI_PREV_INS_TYPE", prevInsTypeArr);
		inputParaList.put("PI_PREV_POLICY_NO", prevPolicyNoDbArr);
		inputParaList.put("PI_PREV_NCB", prevNcbNoDbArr);
		inputParaList.put("PI_NOM_ID",
				(paEntryModel.getNomId().equals("") ? null : Integer.parseInt(paEntryModel.getNomId())));
		inputParaList.put("PI_NOM_INITIAL_ID",
				(paEntryModel.getNomInitialId().equals("") ? null : Integer.parseInt(paEntryModel.getNomInitialId())));
		inputParaList.put("PI_NOM_FIRST_NAME", paEntryModel.getNomFirstName());
		inputParaList.put("PI_NOM_MIDDLE_NAME", paEntryModel.getNomMiddleName());
		inputParaList.put("PI_NOM_LAST_NAME", paEntryModel.getNomLastName());
		inputParaList.put("PI_NOM_DOB", AllUtils.getFormattedDateOracle(paEntryModel.getNomDob()));
		inputParaList.put("PI_NOM_AADHAR_NO", paEntryModel.getNomAadharNo());
		inputParaList.put("PI_NOM_GENDER", paEntryModel.getNomGender());
		inputParaList.put("PI_NOM_REL_ID",
				(paEntryModel.getNomRelId().equals("") ? null : Integer.parseInt(paEntryModel.getNomRelId())));
		inputParaList.put("PI_NOM_CONTACT_NO", paEntryModel.getNomContactNo());
		inputParaList.put("PI_NOM_EMAIL_ID", paEntryModel.getNomEmailId());
		inputParaList.put("PI_NOM_ADR_ID",
				(paEntryModel.getNomAddrId().equals("") ? null : Integer.parseInt(paEntryModel.getNomAddrId())));
		inputParaList.put("PI_NOM_AREA_ID",
				(paEntryModel.getNomAreaId().equals("") ? null : Integer.parseInt(paEntryModel.getNomAreaId())));
		inputParaList.put("PI_NOM_HOUSE", paEntryModel.getNomHouse());
		inputParaList.put("PI_NOM_HOUSE_ID",
				(paEntryModel.getNomHouseId().equals("") ? null : Integer.parseInt(paEntryModel.getNomHouseId())));
		inputParaList.put("PI_NOM_STREET", paEntryModel.getNomStreet());
		inputParaList.put("PI_NOM_STREET_ID",
				(paEntryModel.getNomStreetId().equals("") ? null : Integer.parseInt(paEntryModel.getNomStreetId())));
		inputParaList.put("PI_NOM_LANDMARK", paEntryModel.getNomLandmark());
		inputParaList.put("PI_NOM_LANDMARK_ID", (paEntryModel.getNomLandmarkId().equals("") ? null
				: Integer.parseInt(paEntryModel.getNomLandmarkId())));
		inputParaList.put("PI_MEMBER_NO", memberNoDbArr);
		inputParaList.put("PI_ADDON", addOnDbArr);
		inputParaList.put("PI_ADDON_SI", addOnSumInsDbArr);
		inputParaList.put("PI_ADDON_PREM", addOnPremDbArr);
		inputParaList.put("PI_BRANCH_ID", (branchId.equals("") ? null : Integer.parseInt(branchId)));
		inputParaList.put("PI_USER_ID", (userId.equals("") ? null : Integer.parseInt(userId)));
		inputParaList.put("PI_USER_DESC", userDesc);
		inputParaList.put("PI_STATUS", statusVal);

		System.out.println("inputParaList*****************" + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_PA", "PR_PA_ENTRY", inputParaList);
		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("PO_OP_ID")) {
				opId = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase("PO_ERROR")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("errorMsg" + errorMsg);
			} else if (getKey.equalsIgnoreCase("POC_USER_DTL")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonValues = gson.toJson(list);
				System.out.println("MY JSON DATA===>" + jsonValues);
			}
		}
		if (errorMsg.equals("")) {
			resObj.setStatus("success");
			resObj.setOpId(opId);
			resObj.setGenMstId(mstId);
			resObj.setDocNo(docNo);
			resObj.setMsg("Record Saved Successfully.");
			resObj.setJsonValues(jsonValues);
		} else {
			resObj.setStatus("error");
			resObj.setOpId(opId);
			resObj.setGenMstId("");
			resObj.setMsg(errorMsg);
		}

		return resObj;
	}

}
