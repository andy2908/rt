package com.uat.hbc.common.dao;

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
public class IntegrationHDFCDao {

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
	String jsonNames = "";
	String coverjsonnames = "";

	public String getHDFCData(String pkg_name, String proc_name,
			HashMap inputParaList) {

		HashMap cst = dbProcess.executeProcedure(pkg_name, proc_name,
				inputParaList);
		System.out.println("cst---->>>>>" + cst);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("Po_Error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
			}
			if (getKey.equalsIgnoreCase("Po_Msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
			}
			if (getKey.equalsIgnoreCase("POC_SQL_RESULT")) {
				List list = (List) me.getValue();
				System.out.println("list result -->>>" + list);
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
				System.out.println("jsonNames HDFC Dao------>>>>" + jsonNames);
			}
			if (getKey.equalsIgnoreCase("POC_COVERS")) {
				List list = (List) me.getValue();
				System.out.println("list cover-->>>" + list);
				Gson gson = new Gson();
				coverjsonnames = gson.toJson(list);
				System.out.println("coverjsonnames HDFC Dao------>>>>" + coverjsonnames);
			}
		}
		String s=coverjsonnames.replace("[{", "").replace("}]", "");
		System.out.println("s.length()---->>"+s.length());
		if (s.length()>0) {
			jsonNames = jsonNames + coverjsonnames;
//			if (jsonNames.contains("}][{")) {
//				jsonNames = jsonNames.replace("}][{", " ,");
//			}
			System.out.println("New JSON Response With Covers In Dao ::: " +jsonNames);
			
		}
		return jsonNames;
	}

}
