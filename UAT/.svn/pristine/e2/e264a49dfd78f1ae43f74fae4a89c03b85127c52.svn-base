package com.uat.hbc.insurance.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import com.google.gson.Gson;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.commonFactory.DbProcessImpl;

@Repository
public class IntegrationVideoconDao {

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

	
	public String getVideoconData(String packageName, String procedurName, HashMap inputParaList ) {
		ArrayList<String> resultList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		String retMsg = "";
		String jsonNames = "";
		String jsonNames1 = "";
		List coversList ;
		
		HashMap<String, Object> cst= dbProcess.executeProcedure(packageName, procedurName, inputParaList);
		Set<Entry<String, Object>> outResult= cst.entrySet();
		//Set outResult = cst.entrySet();
		Iterator<Entry<String, Object>> resItr = outResult.iterator();
		
		while (resItr.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object> ) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("po_error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
				
			}
			if (getKey.equalsIgnoreCase("po_msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
				
			}
			if (getKey.equalsIgnoreCase("poc_sql_result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
				System.out.println("jsonNamesPremiumrequest----" + jsonNames);
			}
			if (getKey.equalsIgnoreCase("Poc_Covers")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames1 = gson.toJson(list);
				System.out.println("covers----" + jsonNames1);
			}
		}
		System.out.println("jsonNames1.isEmpty()-->>"+jsonNames1.isEmpty());
		System.out.println("jsonNames1.length()-->>"+jsonNames1.length());
		String s=jsonNames1.replace("[{", "").replace("}]", "");
		System.out.println("s.length()---->>"+s.length());
		if (s.length()>0) {
			jsonNames = jsonNames + jsonNames1;
//			if (jsonNames.contains("}][{")) {
//				jsonNames = jsonNames.replace("}][{", " ,");
//			}
			System.out.println("New JSON Response With Covers In Dao ::: " +jsonNames);
			
		}
		
		
		return jsonNames;

	}

	
	public String getRecordList(String sqlMstId, String param, String flag,
			String workingDate, String branchMstId, String deptMstId,
			String entityId, String userId, String primaryKey) {
		ArrayList<String> resultList = new ArrayList<String>();
		JSONArray array = new JSONArray();
		String retMsg = "";
		String jsonNames = "";
		String jsonNames1 = "";
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

		HashMap<String, Object> cst = dbProcess.executeProcedure("",
				"PR_CALL_SQL", inputParaList);
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
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
				System.out.println("retMsg" +retMsg);
			}
			if (getKey.equalsIgnoreCase("Po_Msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
				System.out.println("retMsg" +retMsg);
			}
			if (getKey.equalsIgnoreCase("poc_sql_result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
			}
			System.out.println(jsonNames);
		}
		return jsonNames;

	}

}
