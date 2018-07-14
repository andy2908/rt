package com.uat.hbc.insurance.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.commonFactory.DbProcessImpl;

@Repository
public class IntegrationShreeRamGICDao {
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

	ArrayList<String> resultList = new ArrayList<String>();
	JSONArray array = new JSONArray();
	String retMsg = "";
	String jsonNames1 = "";
	String jsonNames = "";
	
	public String getShreeRamGICGData(String pkg_name, String proc_name,HashMap inputParaList) {
		
		HashMap cst = dbProcess.executeProcedure(pkg_name, proc_name,
				inputParaList);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
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
	
	
}
