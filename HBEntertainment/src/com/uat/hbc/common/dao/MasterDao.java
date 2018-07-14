package com.uat.hbc.common.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.commonFactory.DbProcessImpl;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

@Repository
public class MasterDao {

	private DbProcessImpl dbProcessImpl;

	public DbProcessImpl getDbProcessImpl() {
		return dbProcessImpl;
	}

	public void setDbProcessImpl(DbProcessImpl dbProcessImpl) {
		this.dbProcessImpl = dbProcessImpl;
	}

	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public String getRecordList(String sqlMstId, String param, String flag, String workingDate, String branchMstId,
			String deptMstId, String entityId, String userId, String primaryKey) {
		ArrayList<String> resultList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		String retMsg = "";
		String jsonNames = "";
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("Pi_Sql_Mst_Id", sqlMstId);
		inputParaList.put("Pi_Sql_Param", param);
		inputParaList.put("Pi_OpFlag", flag);
		inputParaList.put("Pi_OP_DATE", workingDate);
		inputParaList.put("Pi_BRANCH_ID", branchMstId);
		inputParaList.put("Pi_USER_ID", userId);
		inputParaList.put("Pi_ENTER_DESC", null);
		inputParaList.put("Pio_PK", primaryKey);
		inputParaList.put("Pi_USER_DESC", null);

		HashMap<String, Object> cst = dbProcess.executeProcedure("", "PR_CALL_SQL", inputParaList);
		System.out.println("Head cst==> " + cst);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("Po_OpId")) {

			}
			if (getKey.equalsIgnoreCase("Pio_PK")) {
			}
			if (getKey.equalsIgnoreCase("po_error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("retMsg" + retMsg);
			}
			if (getKey.equalsIgnoreCase("Po_Msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("retMsg" + retMsg);
			}
			if (getKey.equalsIgnoreCase("poc_sql_result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
			}
		}
		return jsonNames;

	}

	public Result insertUpdateRecord(String procName, String colName, String colValue) {
		Result resObj = new Result();

		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";

		String[] colNameArr = colName.split("~");
		String[] colValueArr = colValue.split("~");
		System.out.println("length of columns*****************" + colNameArr.length);
		System.out.println("no of parameters*****************" + colValueArr.length);
		HashMap inputParaList = new HashMap<>();
		for (int i = 0; i < colNameArr.length; i++) {
			if (colValueArr[i].equalsIgnoreCase("NULL")) {
				inputParaList.put(colNameArr[i], null);
			} else {
				inputParaList.put(colNameArr[i], colValueArr[i]);
				System.out.println(i + 1 + "):-" + colNameArr[i] + ":" + colValueArr[i]);
			}
		}
		HashMap<String, Object> cst = dbProcess.executeProcedure("", procName, inputParaList);
		
		System.out.println("inputParaList*****************" + inputParaList);
		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("PO_OP_ID")) {
				opId = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase(colNameArr[0])) {
				mstId = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase("PO_ERROR")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("errorMsg" + errorMsg);

			} else if (getKey.equalsIgnoreCase("POC_USER_DTL")) {
				jsonValues = jsonValues + "[";
				List list = (List) me.getValue();
				for (Object object : list) {
					Map hashMap = new HashMap<>();
					hashMap = (Map) object;
					Iterator iterator = hashMap.entrySet().iterator();
					jsonValues = jsonValues + "{";
					while(iterator.hasNext()){
						Map.Entry meInt = (Map.Entry) iterator.next();
						jsonValues = jsonValues +"\""+ meInt.getKey().toString()+"\":\""+(meInt.getValue()!=null?meInt.getValue().toString():"")+"\","; 
						
					}
					jsonValues = jsonValues.substring(0, jsonValues.length()-1) + "},";
				}
				
				jsonValues = jsonValues.substring(0, jsonValues.length()-1) + "]";
				System.out.println("resultStr::" +jsonValues);
//				Gson gson = new Gson();
//				jsonValues = gson.toJson(list);
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

	public Result insertUpdateRecordPKG(String pkgName, String procName, String colName, String colValue) {
		Result resObj = new Result();

		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";

		String[] colNameArr = colName.split("~");
		String[] colValueArr = colValue.split("~");
		System.out.println("length of columns*****************" + colNameArr.length);
		System.out.println("no of parameters*****************" + colValueArr.length);
		HashMap inputParaList = new HashMap<>();
		for (int i = 0; i < colNameArr.length; i++) {
			if (colValueArr[i].equalsIgnoreCase("NULL")) {
				inputParaList.put(colNameArr[i], null);
			} else {
				inputParaList.put(colNameArr[i], colValueArr[i]);
				System.out.println(i + 1 + "):-" + colNameArr[i] + ":" + colValueArr[i]);
			}
		}
		HashMap<String, Object> cst = dbProcess.executeProcedure(pkgName, procName, inputParaList);
		System.out.println("inputParaList*****************" + inputParaList);
		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("PO_OP_ID")) {
				opId = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase(colNameArr[0])) {
				mstId = (null == me.getValue()) ? "" : me.getValue().toString();
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

	public void getOptionalParameter(String... str) {
		final String defaultValue = "";
		if (str.length != 0) {
			for (int i = 0; i < str.length; i++) {
				HashMap inputParaList = new HashMap<>();
				inputParaList.put("str[i]", str[i]);
			}
		}

	}

	public String getInsFindFormList(String pkgName, String procName, String tableName, String strGic, String strGicbid,
			String strProd, String strDisc, String strRgrp, String strPrpsl, String strSpnm, String strMgnm,
			String strVeh, String strMod, String strVar, String strState, String strCity, String strPolAge,
			String strHbbid, String userLevel, String companyType, String loginType, String strGap, String strFuelType,
			String strProductCode, String strType, String strType1, String strKgFrom, String strKgTo, String userId,
			String ageto, String strPolicyType, String strCalcType, String strFuelKit, String strCustType,
			String valId) {

		ArrayList<String> resultList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		String retMsg = "";
		String jsonNames = "";
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("pi_table_name", tableName);
		inputParaList.put("pi_company_type", companyType);
		inputParaList.put("pi_str_gic", strGic);
		inputParaList.put("pi_str_gicbid", strGicbid);
		inputParaList.put("pi_str_prod", strProd);
		inputParaList.put("pi_str_discnm", strDisc);
		inputParaList.put("pi_str_rgrp", strRgrp);
		inputParaList.put("pi_str_state", strState);
		inputParaList.put("pi_str_city", strCity);
		inputParaList.put("pi_str_prpsl", strPrpsl);
		inputParaList.put("pi_str_spnm", strSpnm);
		inputParaList.put("pi_str_mgnm", strMgnm);
		inputParaList.put("pi_productcode", strProductCode);
		inputParaList.put("pi_type", strType);
		inputParaList.put("pi_type_1", strType1);
		inputParaList.put("pi_kg_from", strKgFrom);
		inputParaList.put("pi_kg_to", strKgTo);
		inputParaList.put("pi_str_fueltype", strFuelType);
		inputParaList.put("pi_str_veh", strVeh);
		inputParaList.put("pi_str_mod", strMod);
		inputParaList.put("pi_str_var", strVar);
		inputParaList.put("pi_policy_age", strPolAge);
		inputParaList.put("pi_str_hbbid", strHbbid);
		inputParaList.put("pi_user_level", userLevel);
		inputParaList.put("pi_user_id", userId);
		inputParaList.put("pi_login_type", loginType);
		inputParaList.put("pi_str_gap", strGap);
		inputParaList.put("pi_str_ageto", ageto);
		inputParaList.put("pi_str_policytype", strPolicyType);
		inputParaList.put("pi_str_calctype", strCalcType);
		inputParaList.put("pi_str_fuelkit", strFuelKit);
		inputParaList.put("pi_str_custtype", strCustType);
		inputParaList.put("pi_val_id", valId);
		System.out.println("Input Parameter List=" + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure(pkgName, procName, inputParaList);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("po_error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("po_msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("poc_sql_result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
			}
		}
		return jsonNames;

	}

	public String getAccFindFormList(String pkgName, String procName, String strLType, String strFind, String strGtid,
			String strGLId, String strStatus, String strVtype, String strLid) {

		ArrayList<String> resultList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		String retMsg = "";
		String jsonNames = "";
		HashMap inputParaList = new HashMap<>();

		inputParaList.put("pi_l_type", strLType);
		inputParaList.put("PI_GT_ID", strGtid);
		inputParaList.put("pi_find", strFind);
		inputParaList.put("pi_gl_id", strGLId);
		inputParaList.put("pi_status", strStatus);
		inputParaList.put("pi_vtype", strVtype);
		inputParaList.put("PI_STR_LID", strLid);

		System.out.println("Account Parameter List=" + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure(pkgName, procName, inputParaList);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("po_error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("po_msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("p_refcursor")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
			}
		}
		return jsonNames;

	}

	public String getRecordFromFunction(String pkgName, String funcName, String colName, String colValue) {
		String result = "";

		String[] colNameArr = colName.split("~");
		String[] colValueArr = colValue.split("~");
		System.out.println("length of columns*****************" + colNameArr.length);
		System.out.println("no of parameters*****************" + colValueArr.length);
		HashMap inputParaList = new HashMap<>();
		for (int i = 0; i < colNameArr.length; i++) {
			if (colValueArr[i].equalsIgnoreCase("NULL")) {
				inputParaList.put(colNameArr[i], null);
			} else {
				inputParaList.put(colNameArr[i], colValueArr[i]);
				System.out.println(i + 1 + "):-" + colNameArr[i] + ":" + colValueArr[i]);
			}
		}
		System.out.println("inputParaList*****************" + inputParaList);
		result = dbProcess.callFunction(pkgName, funcName, inputParaList);

		System.out.println("res::" + result);
		/*
		 * Set outResult = cst.entrySet();
		 * 
		 * Iterator resItr = outResult.iterator(); while (resItr.hasNext()) {
		 * Map.Entry me = (Map.Entry) resItr.next(); String getKey =
		 * me.getKey().toString(); if (getKey.equalsIgnoreCase("PO_OP_ID")) {
		 * opId = (null == me.getValue()) ? "" : me.getValue().toString(); }
		 * else if (getKey.equalsIgnoreCase(colNameArr[0])) { mstId = (null ==
		 * me.getValue()) ? "" : me.getValue().toString(); } else if
		 * (getKey.equalsIgnoreCase("PO_ERROR")) { errorMsg = (null ==
		 * me.getValue()) ? "" : me.getValue() .toString();
		 * System.out.println("errorMsg" + errorMsg);
		 * 
		 * } else if (getKey.equalsIgnoreCase("POC_USER_DTL")) { List list =
		 * (List) me.getValue(); Gson gson = new Gson(); jsonValues =
		 * gson.toJson(list); System.out.println("MY JSON DATA===>" +
		 * jsonValues); } } if (errorMsg.equals("")) {
		 * resObj.setStatus("success"); resObj.setOpId(opId);
		 * resObj.setGenMstId(mstId); resObj.setDocNo(docNo);
		 * resObj.setMsg("Record Saved Successfully.");
		 * resObj.setJsonValues(jsonValues); } else { resObj.setStatus("error");
		 * resObj.setOpId(opId); resObj.setGenMstId("");
		 * resObj.setMsg(errorMsg); }
		 */

		return "{[\"result\":\"" + result + "\"]}";
	}

	public Result insertUpdateRecordPKGwithArray(BigDecimal[] rudIdArr, BigDecimal commonTrNo, BigDecimal[] srNo,
			String[] userType, BigDecimal[] reqDetailsId, String compulsory, String userId, String branchId,
			String userDesc) {
		Result resObj = new Result();

		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";

		ArrayDescriptor rudIdDesc = null;
		ArrayDescriptor srNoDesc = null;
		ArrayDescriptor userTypeDesc = null;
		ArrayDescriptor reqDetailsIdDesc = null;
		Connection conn =null;

		DataSource ds = jdbcTemplate.getDataSource();
		conn = DataSourceUtils.getConnection(ds);
		try {
			rudIdDesc = new ArrayDescriptor("TY_NUMBER", conn);
			srNoDesc = new ArrayDescriptor("TY_NUMBER", conn);
			userTypeDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			reqDetailsIdDesc = new ArrayDescriptor("TY_NUMBER", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ARRAY rudIdDbArr = null;
		ARRAY srNoDbArr = null;
		ARRAY userTypeDbArr = null;
		ARRAY reqDetailsIdDbArr = null;
		
		try {
			rudIdDbArr = new ARRAY(rudIdDesc, conn, rudIdArr);
			srNoDbArr = new ARRAY(srNoDesc, conn, srNo);
			userTypeDbArr = new ARRAY(userTypeDesc, conn, userType);
			reqDetailsIdDbArr = new ARRAY(reqDetailsIdDesc, conn, reqDetailsId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		HashMap inputParaList = new HashMap<>();

		inputParaList.put("PI_RUD_ID", rudIdDbArr);
		inputParaList.put("PI_COMMON_TRNO", commonTrNo);
		inputParaList.put("PI_SRNO", srNoDbArr);
		inputParaList.put("PI_USER_TYPE", userTypeDbArr);
		inputParaList.put("PI_REQ_DETAILS_ID", reqDetailsIdDbArr);
		inputParaList.put("PI_COMPULSARY", compulsory);
		inputParaList.put("PI_USERID", userId);
		inputParaList.put("pi_branch_id", branchId);
		inputParaList.put("PI_USERDESC", userDesc);

		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_USER_REQ_DET_ENTRY_ARRAY", "PR_USER_REQ_DET_DUMP",
				inputParaList);
		System.out.println("inputParaList*****************" + inputParaList);
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
