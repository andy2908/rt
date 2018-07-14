package com.uat.hbc.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.uat.hbc.common.model.MotorResponseBean;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

@Repository
public class IntegrationSaveResponseDao {
	private DbProcess dbProcessImpl;

	public DbProcess getDbProcessImpl() {
		return dbProcessImpl;
	}

	public void setDbProcessImpl(DbProcess dbProcessImpl) {
		this.dbProcessImpl = dbProcessImpl;
	}

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;

	public String motorGroupData(String groupId, String sessionId, String proposalType, String policyType,
			String product, String rtoCity, String mfrYear, String variance, String fuelType, String userId,
			String branchId, String userDesc) {

		MotorResponseBean resObj = new MotorResponseBean();

		String errorMsg = "";
		String jsonValues = "";
		String resGroupId = "";
		String resSessId = "";

		HashMap inputParaList = new HashMap<>();
		inputParaList.put("PIO_IM_GROUP_ID", groupId);
		inputParaList.put("PIO_SESSION_ID", sessionId);
		inputParaList.put("PI_proposal_type", proposalType);
		inputParaList.put("PI_policy_type", policyType);
		inputParaList.put("PI_product", product);
		inputParaList.put("PI_RTO_CITY", rtoCity);
		inputParaList.put("PI_mfr_year", mfrYear);
		inputParaList.put("PI_variance", variance);
		inputParaList.put("PI_fueltype", fuelType);
		inputParaList.put("PI_USERID", userId);
		inputParaList.put("pi_branch_id", branchId);
		inputParaList.put("PI_USERDESC", userDesc);
		//
		System.out.println("groupId----------->>" + groupId);
		System.out.println("sessionId----------->>" + sessionId);
		System.out.println("proposalType----------->>" + proposalType);
		System.out.println("policyType----------->>" + policyType);
		System.out.println("product----------->>" + product);
		System.out.println("rtoCity----------->>" + rtoCity);
		System.out.println("mfrYear----------->>" + mfrYear);
		System.out.println("variance----------->>" + variance);
		System.out.println("fuelType----------->>" + fuelType);
		System.out.println("userId----------->>" + userId);
		System.out.println("branchId----------->>" + branchId);
		System.out.println("userDesc----------->>" + userDesc);

		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_INTIG_MOTOR", "PR_MOTOR_GROUP", inputParaList);
		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("PIO_IM_GROUP_ID")) {
				resGroupId = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("resGroupId==>>" + resGroupId);
			}
			if (getKey.equalsIgnoreCase("PIO_SESSION_ID")) {
				resSessId = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("resSessId==>>" + resSessId);
			}
			if (getKey.equalsIgnoreCase("PO_ERROR")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("PO_DETAIL")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonValues = gson.toJson(list);
				System.out.println("**************************************************");
				System.out.println("motorGroupData jsonValues===>" + jsonValues);
			}
		}
		if (errorMsg.equals("") || errorMsg.equals(null) || errorMsg == null) {
			resObj.setStatus("success");
			resObj.setJsonValues(jsonValues);
			resObj.setGroupId(resGroupId);
			resObj.setSessionId(resSessId);
			;
		} else {
			resObj.setStatus("error");
			resObj.setErrorMsg(errorMsg);
		}
		System.out.println("resObj==>>" + resObj);
		
		HashMap input = new HashMap<>();
		input.put("groupId", resGroupId);
		input.put("sessionId", resSessId);
		
		Gson gson = new Gson();
        String jsonNames = gson.toJson(input);
        jsonNames ="[" +jsonNames +"]";
		return jsonNames;
	}

	public String getPaymentData(MotorResponseBean bean, String quoteNo, String proposalNo) {
		HashMap inputParams = new HashMap();
		inputParams.put("PI_GROUP_ID", bean.getGroupId());
		inputParams.put("PI_GIC_ID", bean.getGicId());
		inputParams.put("PI_SESSION_ID", bean.getSessionId());
		inputParams.put("PI_PROPOSAL_NO", proposalNo);
		inputParams.put("PI_QUOTE_NO", quoteNo);

		inputParams.put("PI_USERID", Integer.parseInt(bean.getUserId()));
		inputParams.put("PI_BRANCH_ID", Integer.parseInt(bean.getBranchId()));
		inputParams.put("PI_USERDESC", bean.getUserDesc());

		System.out.println("inputParams:::  " + inputParams);
		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_INTIG_MOTOR", "PR_FETCH_PAYMENT", inputParams);
		Set outResult = cst.entrySet();
		String details = "";
		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("PO_DETAIL")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				details = gson.toJson(list);
				System.out.println("jsonNames Rel Dao------>>>>" + details);
			}
			if (getKey.equalsIgnoreCase("PO_ERROR")) {
				String error = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("resSessId==>>" + error);
			}
		}

		return details;
	}

	
	public String getCovers(String tableName, String rtoCity, String zoneId, String policyType, String varId,
			String bussType, String productId, String custType, String gicId) {

		Result bean = new Result();

		String errorMsg = "";
		String detailMsg = "";
		String jsonValues = "";

		System.out.println("tableName=====>>" + tableName);
		System.out.println("rtoCity=====>>" + rtoCity);
		System.out.println("zoneId=====>>" + zoneId);
		System.out.println("policyType=====>>" + policyType);
		System.out.println("varId=====>>" + varId);
		System.out.println("bussType=====>>" + bussType);
		System.out.println("productId=====>>" + productId);
		System.out.println("custType=====>>" + custType);
		System.out.println("gicId=====>>" + gicId);

		HashMap inputParaList = new HashMap<>();
		inputParaList.put("PI_TABLE_NAME", tableName);
		inputParaList.put("PI_Rto_City", rtoCity);
		inputParaList.put("PI_ZONE_ID", zoneId);
		inputParaList.put("PI_PolicyType", policyType);
		inputParaList.put("PI_VarID", varId);
		inputParaList.put("PI_P_BusinessType", bussType);
		inputParaList.put("PI_ProductID", productId);
		inputParaList.put("PI_CUST_TYPE", custType);
		inputParaList.put("PI_GIC_ID", gicId);

		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_MOTOR_CALC", "EXTRA_COVERS", inputParaList);

		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			// System.out.println();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("Poc_Sql_Result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonValues = gson.toJson(list);
			} else if (getKey.equalsIgnoreCase("Po_Error")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase("Po_Msg")) {
				detailMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
		}
		if (errorMsg.equals("")) {
			bean.setJsonValues(jsonValues);
			bean.setStatus("success");

		} else {
			bean.setStatus("error");
			bean.setErrorMsg(errorMsg);
		}
		System.out.println("response of get Covers=======>>" + jsonValues);
		return jsonValues;

	}

	public Result insertUpdateIntgRecordPKGwithArray(Integer imGrpId, Integer gicId, String sessionId,
			Integer responseType, String[] kerArr, String[] valArr, String tcpIpAdd, String userId, String branchId,
			String userDesc, String procedureName) {
		Result resObj = new Result();

		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";

		ArrayDescriptor keyDesc = null;
		ArrayDescriptor valDesc = null;

		Connection conn = null;
		Connection connWrapped = null;

		try {
//			System.out.println("conn 111" + conn);
			connWrapped = jdbcTemplate.getDataSource().getConnection();
//			System.out.println("conn 222" + conn);
			conn = connWrapped.unwrap(OracleConnection.class);
//			System.out.println("conn 333" + conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			keyDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			valDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ARRAY keyDbArr = null;
		ARRAY valDbArr = null;
//		System.out.println("conn 444" + conn);
		try {
			keyDbArr = new ARRAY(keyDesc, conn, kerArr);
			valDbArr = new ARRAY(valDesc, conn, valArr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			// jdbcTemplate.getDataSource().getConnection().close();
			connWrapped.close();
//			System.out.println("conn 555" + conn);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		System.out.println("conn 666" + conn);
		HashMap inputParaList = new HashMap<>();

		inputParaList.put("PIO_IM_GROUP_ID", imGrpId);
		inputParaList.put("PI_GIC_ID", gicId);
		inputParaList.put("PIO_SESSION_ID", sessionId);
		inputParaList.put("PI_RESPONSE_TYPE", responseType);
		inputParaList.put("PI_KEY", keyDbArr);
		inputParaList.put("PI_VAL", valDbArr);
		inputParaList.put("PI_TCP_IP_ADDRESS", tcpIpAdd);
		inputParaList.put("PI_USERID", Integer.parseInt(userId));
		inputParaList.put("PI_BRANCH_ID", Integer.parseInt(branchId));
		inputParaList.put("PI_USERDESC", userDesc);

		System.out.println("inputParaList*****************" + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_INTIG_MOTOR", procedureName, inputParaList);
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
			} else if (getKey.equalsIgnoreCase("PO_DETAIL")) {
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

	public String motorIntgCalculationDetails(String groupId, String gicId, String sessionId, String responseType,
			String covType, String ipAddress, String userId, String branchId, String userDesc) {


		Result bean = new Result();

		String errorMsg = "";
		String detailMsg = "";
		String jsonValues = "";

		System.out.println("groupId=====>>" + groupId);
		System.out.println("gicId=====>>" + gicId);
		System.out.println("sessionId=====>>" + sessionId);
		System.out.println("responseType=====>>" + responseType);
		System.out.println("covType=====>>" + covType);
		System.out.println("ipAddress=====>>" + ipAddress);
		System.out.println("userId=====>>" + userId);
		System.out.println("branchId=====>>" + branchId);
		System.out.println("userDesc=====>>" + userDesc);

		
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("PIO_IM_GROUP_ID", groupId);
		inputParaList.put("PI_GIC_ID", gicId);
		inputParaList.put("PIO_SESSION_ID", sessionId);
		inputParaList.put("PI_RESPONSE_TYPE", responseType);
		inputParaList.put("PI_COV_TYPE", covType);
		inputParaList.put("PI_TCP_IP_ADDRESS", ipAddress);
		inputParaList.put("PI_USERID", userId);
		inputParaList.put("PI_BRANCH_ID", branchId);
		inputParaList.put("PI_USERDESC", userDesc);

		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_INTIG_MOTOR", "PR_FETCH_PREMIUM", inputParaList);

		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			// System.out.println();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("PO_DETAIL")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonValues = gson.toJson(list);
			} else if (getKey.equalsIgnoreCase("PO_ERROR")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			} 
		}
		if (errorMsg.equals("")) {
			bean.setJsonValues(jsonValues);
			bean.setStatus("success");

		} else {
			bean.setStatus("error");
			bean.setErrorMsg(errorMsg);
		}
		System.out.println("response of get Covers=======>>" + jsonValues);
		return jsonValues;
	}

}

// for(Map.Entry<String,String> map : hashmap.entrySet()){
// System.out.println("2222222222222222222222222222");
// if
// (map.getKey().equalsIgnoreCase("tax1")||map.getKey().equalsIgnoreCase("tax2")||map.getKey().equalsIgnoreCase("tax3"))
// {
// hashmap.remove(map.getKey());
// }else if
// (map.getKey().equalsIgnoreCase("coverlist")||map.getKey().equalsIgnoreCase("cover1")||map.getKey().equalsIgnoreCase("cover2")||map.getKey().equalsIgnoreCase("cover3")||map.getKey().equalsIgnoreCase("cover4")||map.getKey().equalsIgnoreCase("cover5"))
// {
// hashmap.remove(map.getKey());
// }else if
// (map.getKey().equalsIgnoreCase("cover6")||map.getKey().equalsIgnoreCase("cover7")||map.getKey().equalsIgnoreCase("cover8"))
// {
// hashmap.remove(map.getKey());
// }else{
// arList.add(map.getValue());
// }
// }

// System.out.println("arList.size==>>"+arList.size());
// System.out.println("arList.size==>>"+arList.get(0));
