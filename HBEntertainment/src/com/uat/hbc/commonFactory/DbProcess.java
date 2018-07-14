package com.uat.hbc.commonFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DbProcess {
		
	public HashMap executeProcedure (String packName,String spname, HashMap inParamMap);
	public String callFunction(String pkgname, String fnname, HashMap inParamMap);
	public BigDecimal callFunctionCheck(String pkgname, String fnname, HashMap inParamMap);
	public int executeDmlQueryJT(String query, HashMap vInputParaList);
	public List executeSingleSelectQueries(String columnName, String defaultWhere, String tableName, String dbName);
	public HashMap executeSelectQuery(String queryName);
	//public HashMap<String, Object> executeSpCstmt(String vProcedureName, HashMap vInputParaList,HashMap vOutputParaList,List paramList);
	public HashMap executeSP (String spName, HashMap inParamMap,HashMap outParamMap);
	public int executeDmlQueryNP(String vQuery,HashMap vInputParaList);
	public List executeSelect(String vQuery,Map vInputParaList);
	public ArrayList<String> executeProcedureGettingColType (String packName,String spname, HashMap inParamMap,HashMap outParamMap);
	public HashMap selectQueryGettingColType (String query);
}
