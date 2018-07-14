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
public class BajajAllianzDaoIntegration {

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
	
	public String getBajajData(String pkg_name, String proc_name, HashMap inputParaList) {

		HashMap cst = dbProcess.executeProcedure(pkg_name, proc_name, inputParaList);
		System.out.println("cst---->>>>>" + cst);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("Po_Error")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("Po_Msg")) {
				retMsg = (null == me.getValue()) ? "" : me.getValue().toString();
			}
			if (getKey.equalsIgnoreCase("POC_SQL_RESULT")) {
				List list = (List) me.getValue();
				System.out.println("POC_SQL_RESULT list-->>>" + list);
				Gson gson = new Gson();
				jsonNames = gson.toJson(list);
				System.out.println("jsonNames Bajaj Dao------>>>>" + jsonNames);
			}
			if (getKey.equalsIgnoreCase("POC_COVERS")) {
				List list = (List) me.getValue();
				System.out.println("list-->>>" + list);
				Gson gson = new Gson();
				coverjsonnames = gson.toJson(list);
				System.out.println("coverjsonnames Bajaj Dao------>>>>" + coverjsonnames);
			}
			jsonNames = jsonNames + coverjsonnames;
			// jsonNames = jsonNames;

			// System.out.println("jsonNames +
			// coverjsonnames;-->>>>"+jsonNames);
		}
		System.out.println("jsonNames + coverjsonnames;-->>>>" + jsonNames);
		// if (jsonNames.contains("}][{")) {
		// jsonNames = jsonNames.replace("}][{", ",");
		// }
		// if (jsonNames.contains("[") || jsonNames.contains("]") ) {
		// jsonNames = jsonNames.replace("[", "");
		// jsonNames = jsonNames.replace("]", "");
		// }
		if (jsonNames.contains("PYP_TYPE")) {
			jsonNames = jsonNames.replace("PYP_TYPE", "PreviousPolicyTypeOrTypeofCover");
		}

		System.out.println("New JSON-->>>>" + jsonNames);

		return jsonNames;
		
	}
	
}
