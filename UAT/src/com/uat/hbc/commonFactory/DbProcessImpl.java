package com.uat.hbc.commonFactory;

import java.math.BigDecimal;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;

import oracle.jdbc.OracleTypes;

public class DbProcessImpl implements DbProcess{


	private JdbcTemplate jdbcTemplate;  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	private NamedParameterJdbcTemplate jTemplate;
	public void setjTemplate(NamedParameterJdbcTemplate jTemplate) {
		this.jTemplate = jTemplate;
	}

	/**
	 * @return the jdbcTemplate
	 */

	/*public HashMap<String, Object> executeSpCstmt(String vProcedureName, HashMap vInputParaList,HashMap vOutputParaList, List paramList){

		final Set vInputParaSet = vInputParaList.entrySet();
		final Set vOutputParaSet = vOutputParaList.entrySet();
		int paraSize = vInputParaList.size() + vOutputParaList.size();
		String inOutPara = "(?";
		for (int i = 0; i < paraSize-1; i++) {
			inOutPara =inOutPara+"," +"?";
		}
		inOutPara =inOutPara+")";
		final String sp = "{call "+ vProcedureName+inOutPara+"}";
		////System.out.println("sp "+sp);			
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection)
			{
				CallableStatement cstmt =null;

				try{

					cstmt = connection.prepareCall(sp);
					Iterator inputParaItr = vInputParaSet.iterator();
					while (inputParaItr.hasNext()) {
						Map.Entry me = (Map.Entry)inputParaItr.next();
						//String getKey = me.getKey().toString();
						int getKey = (int) me.getKey();
						InOutFieldDetails getObj = (InOutFieldDetails) me.getValue();

						if(getObj.getInputFieldDataType().equals("Integer")){
							Integer value = ObjectConverter.convert(getObj.getInputFieldValue(), Integer.class);
							cstmt.setInt(getKey, Integer.parseInt(getObj.getInputFieldValue()));
						}
						else if(getObj.getInputFieldDataType().equals("String")){
							String str = ObjectConverter.convert(getObj.getInputFieldValue(), String.class);
							cstmt.setString(getKey, getObj.getInputFieldValue().toString());
						}
						else if(getObj.getInputFieldDataType().equals("Float")){
							Float flt = ObjectConverter.convert(getObj.getInputFieldValue(), Float.class);
							cstmt.setFloat(getKey, Float.parseFloat(getObj.getInputFieldValue()));
						}
						else if(getObj.getInputFieldDataType().equals("Double")){
							Double dbl = ObjectConverter.convert(getObj.getInputFieldValue(), Double.class);
							cstmt.setDouble(getKey, Double.parseDouble(getObj.getInputFieldValue()));
						}
						else if(getObj.getInputFieldDataType().equals("Null")){
							cstmt.setNull(getKey, java.sql.Types.NULL);
						}
					}
					//writer.write(strBld.toString());
					//writer.close();
					Iterator outputParaItr = vOutputParaSet.iterator();
					while (outputParaItr.hasNext()) {
						Map.Entry outMe = (Map.Entry)outputParaItr.next();
						//String getKey = outMe.getKey().toString();
						int getKey = (int) outMe.getKey();
						InOutFieldDetails getOutputParaObj = (InOutFieldDetails) outMe.getValue();

						if(getOutputParaObj.getInputFieldDataType().equals("Integer")){
							cstmt.registerOutParameter(getKey, java.sql.Types.INTEGER); 

						}
						else if(getOutputParaObj.getInputFieldDataType().equals("String")){
							cstmt.registerOutParameter(getKey, java.sql.Types.VARCHAR); 
							SqlOutParameter strOutParam = new SqlOutParameter(getOutputParaObj.getInputFieldId(), Types.VARCHAR);
							//paramList.add(strOutParam);

						}
						else if(getOutputParaObj.getInputFieldDataType().equals("Float")){
							cstmt.registerOutParameter(getKey, java.sql.Types.FLOAT); 

						}
						else if(getOutputParaObj.getInputFieldDataType().equals("Double")){
							cstmt.registerOutParameter(getKey, java.sql.Types.DOUBLE); 

						}
						else if(getOutputParaObj.getInputFieldDataType().equals("Cursor")){
							cstmt.registerOutParameter(getKey, OracleTypes.CURSOR); 

						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				return cstmt;
			}
		}
		, paramList);
		////System.out.println("resultMap "+(HashMap<String, Object>) resultMap);
		return (HashMap<String, Object>) resultMap;

		HashMap getresult = new HashMap();
		return (HashMap<String, Object>) getresult;

	}*/
	public HashMap executeSP (String spName, HashMap inParamMap,HashMap outParamMap)
	{
		Map cst = null;
		try{
			class MyStoredProcedure extends StoredProcedure {

				public MyStoredProcedure(JdbcTemplate jdbcTemplate, String name) {

					super(jdbcTemplate, name);
					setFunction(false);

				}

			}
			MyStoredProcedure myStoredProcedure = new MyStoredProcedure(jdbcTemplate, spName);
			int paraSize = inParamMap.size() + outParamMap.size();
			////System.out.println("paraSize "+paraSize);
			Set vInputParaSet = inParamMap.entrySet();
			Set vOutputParaSet = outParamMap.entrySet();
			SqlParameter[] paramArray =new SqlParameter[paraSize];
			Iterator inputParaItr = vInputParaSet.iterator();
			HashMap inParaToSp=new HashMap();
			//int inCount=0;
			while (inputParaItr.hasNext()) {

				Map.Entry me = (Map.Entry)inputParaItr.next();
				String getKey = me.getKey().toString();
				InOutFieldDetails getObj = (InOutFieldDetails) me.getValue();
				if(getObj.getInputFieldDataType().equals("Integer")){
					SqlParameter intParam = new SqlParameter(getKey, Types.INTEGER);
					paramArray[getObj.getOrder()] = intParam;
					inParaToSp.put(getKey, getObj.getInputFieldValue());
				}
				else if(getObj.getInputFieldDataType().equals("String")){
					SqlParameter strParam = new SqlParameter(getKey, Types.VARCHAR);
					paramArray[getObj.getOrder()] = strParam;
					inParaToSp.put(getKey, getObj.getInputFieldValue());
				}
				else if(getObj.getInputFieldDataType().equals("Float")){
					SqlParameter fltParam = new SqlParameter(getKey, Types.FLOAT);
					paramArray[getObj.getOrder()] = fltParam;
					inParaToSp.put(getKey, getObj.getInputFieldValue());
				}
				else if(getObj.getInputFieldDataType().equals("Double")){
					SqlParameter dblParam = new SqlParameter(getKey, Types.DOUBLE);
					paramArray[getObj.getOrder()] = dblParam;
					inParaToSp.put(getKey, getObj.getInputFieldValue());
				}
				else if(getObj.getInputFieldDataType().equals("Null")){
					SqlParameter nullParam = new SqlParameter(getKey, Types.NULL);
					paramArray[getObj.getOrder()] = nullParam;
					inParaToSp.put(getKey, getObj.getInputFieldValue());
				}
				//inCount++;

			}
			Iterator outputParaItr = vOutputParaSet.iterator();
			while (outputParaItr.hasNext()) {
				Map.Entry outMe = (Map.Entry)outputParaItr.next();
				String getKey = outMe.getKey().toString();
				InOutFieldDetails getOutputParaObj = (InOutFieldDetails) outMe.getValue();

				if(getOutputParaObj.getInputFieldDataType().equals("Integer")){
					SqlOutParameter outInt = new SqlOutParameter(getKey, Types.INTEGER); 
					paramArray[getOutputParaObj.getOrder()] = outInt;

				}
				else if(getOutputParaObj.getInputFieldDataType().equals("String")){
					SqlOutParameter outStr = new SqlOutParameter(getKey, Types.VARCHAR);
					paramArray[getOutputParaObj.getOrder()] = outStr;

				}
				else if(getOutputParaObj.getInputFieldDataType().equals("Float")){
					SqlOutParameter outflt = new SqlOutParameter(getKey, Types.FLOAT);
					paramArray[getOutputParaObj.getOrder()] = outflt;

				}
				else if(getOutputParaObj.getInputFieldDataType().equals("Double")){
					SqlOutParameter outDbl = new SqlOutParameter(getKey, Types.DOUBLE);
					paramArray[getOutputParaObj.getOrder()] = outDbl;

				}
				else if(getOutputParaObj.getInputFieldDataType().equals("Cursor")){
					SqlOutParameter outCur = new SqlOutParameter(getKey,OracleTypes.CURSOR); 
					paramArray[getOutputParaObj.getOrder()] = outCur;

				}
				//inCount++;
			}
			////System.out.println("paramArray "+paramArray.length);
			myStoredProcedure.setParameters(paramArray);
			myStoredProcedure.compile();
			//Call stored procedure
			cst = myStoredProcedure.execute(inParaToSp);
		}
		finally{
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (HashMap) cst;
	}

	public HashMap executeProcedure (String packName,String spname, HashMap inParamMap)
	{
		Map simpleJdbcCallResult=null;
		try{
			//jdbcTemplate.getDataSource().getConnection().setAutoCommit(false);
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);	
			// simpleJdbcCall.withProcedureName(procedureName)
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
			.withCatalogName(packName)
			.withProcedureName(spname);
			SqlParameterSource inputPara = new MapSqlParameterSource(inParamMap);
			simpleJdbcCallResult = simpleJdbcCall.execute(inputPara);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (HashMap) simpleJdbcCallResult;

	}
	
	
	public String callFunction(String pkgname, String fnname, HashMap inParamMap)
	{
		String result = "";
		try{
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
			.withCatalogName(pkgname)
			.withFunctionName(fnname);			
			SqlParameterSource inputPara = new MapSqlParameterSource(inParamMap);
			result = simpleJdbcCall.executeFunction(String.class, inputPara);
		}
		finally{
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public BigDecimal callFunctionCheck(String pkgname, String fnname, HashMap inParamMap)
	{
		BigDecimal result;
		try{
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
			.withCatalogName(pkgname)
			.withFunctionName(fnname);			
			SqlParameterSource inputPara = new MapSqlParameterSource(inParamMap);
			result = simpleJdbcCall.executeFunction(BigDecimal.class, inputPara);
		}
		finally{
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public int executeDmlQueryNP(String vQuery,HashMap vInputParaList){	 
		int returnVal= (int) jTemplate.execute(vQuery,vInputParaList,new PreparedStatementCallback() {  
			@Override  
			public Object doInPreparedStatement(PreparedStatement ps)  
					throws SQLException, DataAccessException {  
				return ps.executeUpdate();  
			}  
		});
		
		return returnVal;
	}
	public int executeDmlQueryJT(String query, HashMap vInputParaList){
		int row =0;
		try{
			Set vInputParaSet = vInputParaList.entrySet();
			Iterator inputParaItr = vInputParaSet.iterator();
			Object[] params = new Object[vInputParaList.size()];
			int[] types = new int[vInputParaList.size()];
			while (inputParaItr.hasNext()) {
				Map.Entry me = (Map.Entry)inputParaItr.next();
				String getKey = me.getKey().toString();
				InOutFieldDetails getObj = (InOutFieldDetails) me.getValue();
				if(getObj.getInputFieldDataType().equals("Integer")){
					params[getObj.getOrder()] = getObj.getInputFieldValue();//Integer.parseInt(getObj.getInputFieldValue());
					types[getObj.getOrder()] = Types.INTEGER;
				}
				else if(getObj.getInputFieldDataType().equals("String")){
					////System.out.println("----"+getObj.getOrder() + getObj.getInputFieldValue());
					params[getObj.getOrder()] =  getObj.getInputFieldValue();
					types[getObj.getOrder()] = Types.VARCHAR;
				}
				else if(getObj.getInputFieldDataType().equals("Char")){
					params[getObj.getOrder()] =  getObj.getInputFieldValue();//.charAt(0);
					types[getObj.getOrder()] =  Types.CHAR;
				}
				else if(getObj.getInputFieldDataType().equals("Float")){
					params[getObj.getOrder()] =  getObj.getInputFieldValue();
					types[getObj.getOrder()] = Types.FLOAT;
				}
				else if(getObj.getInputFieldDataType().equals("Double")){
					params[getObj.getOrder()] =  getObj.getInputFieldValue();
					types[getObj.getOrder()] = Types.DOUBLE;
				}
				else if(getObj.getInputFieldDataType().equals("Null")){
					params[getObj.getOrder()] =  getObj.getInputFieldValue();
					types[getObj.getOrder()] = Types.NULL;
				}
			}
			// ////System.out.println("params "+params[0]+" "+params[1]+" "+params[2]+" "+params[3]+" "+params[4]+" "+params[5]+" "+params[6]+" "+params[7]+" "+params[8]+" "+params[9]+" "+params[10]);
			// ////System.out.println("types "+types[0]+" "+types[1]+" "+types[2]+" "+types[3]+" "+types[4]+" "+types[5]+" "+types[6]+" "+types[7]+" "+types[8]+" "+types[9]+" "+types[10]);
			row = jdbcTemplate.update(query, params, types);
		}
		finally{
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return row;

	}

	public List executeSingleSelectQueries(String columnName, String defaultWhere, String tableName, String dbName)
	{		
		String query="";		
		List arr = null;
		try 
		{	
			if (defaultWhere==null)
				query = "select " + columnName + " from " + tableName;
			else
				query = "select " + columnName + " from " + tableName + " where " + defaultWhere;

			String rs_value = jdbcTemplate.queryForObject(query,String.class);
			String str[] = rs_value.split("~");
			ArrayList list = new ArrayList();
			for(int i=0;i<str.length;i++)
			{			
				arr.add(str[i]);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		finally{
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arr;
	}

	@SuppressWarnings("unchecked")
	public List executeSelect(String vQuery,Map vInputParaList){	 
		List retList =jTemplate.query(vQuery, vInputParaList, new ColumnMapRowMapper());  
		return retList;
	}

	public HashMap executeSelectQuery(String queryName){  
		//////System.out.println("connection "+connection);
		HashMap resultOut = new HashMap<>();

		////System.out.println("jdbcTemplate"+jdbcTemplate);
		try{
			resultOut =  (HashMap) jdbcTemplate.query(queryName,new ResultSetExtractor<HashMap>(){  
				@Override  
				public HashMap extractData(ResultSet rs) throws SQLException,  DataAccessException { 
					HashMap rsToMap = resultSetToHash(rs);
					return rsToMap;
				}  
			});
		}
		finally{
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultOut;
	} 


	public HashMap resultSetToHash(ResultSet rs) throws SQLException{
		ArrayList<InOutFieldDetails> rsOutList = null;
		InOutFieldDetails objInOutPara = null;
		HashMap outList = new HashMap<>();
		int j=1;
		while (rs.next()){
			rsOutList = new ArrayList<InOutFieldDetails>();
			ResultSetMetaData rsmd=rs.getMetaData();
			for(int i=1; i<=rsmd.getColumnCount(); i++){
				objInOutPara = new InOutFieldDetails();
				objInOutPara.setInputFieldId(rsmd.getColumnName(i));
				if (rsmd.getColumnTypeName(i).equalsIgnoreCase("VARCHAR2") || rsmd.getColumnTypeName(i).equalsIgnoreCase("NVARCHAR2")){
					objInOutPara.setInputFieldValue(rs.getString(i));
				}
				else if (rsmd.getColumnTypeName(i).equalsIgnoreCase("NUMBER")){
					objInOutPara.setInputFieldValue(rs.getInt(i)+"");
				}
				else if (rsmd.getColumnTypeName(i).equalsIgnoreCase("FLOAT")){
					objInOutPara.setInputFieldValue(rs.getFloat(i)+"");
				}
				else if (rsmd.getColumnTypeName(i).equalsIgnoreCase("DOUBLE")){
					objInOutPara.setInputFieldValue(rs.getDouble(i)+"");
				}
				rsOutList.add(objInOutPara);
			}	
			outList.put("row"+j, rsOutList);
			j++;
		}

		return outList;
	}

	public HashMap selectQueryGettingColType (String query)
	{
		HashMap resultOut = new HashMap<>();
		////System.out.println("jdbcTemplate"+jdbcTemplate);
		resultOut =  (HashMap) jdbcTemplate.query(query,new ResultSetExtractor<Map>(){  
			@Override  
			public Map extractData(ResultSet rs) throws SQLException,  DataAccessException { 
				ResultSetMetaData rsmd = rs.getMetaData();
				Map<String, Object> rowMap = new HashMap<String, Object>();
				int columnCount = rsmd.getColumnCount();
				for(int i = 1 ; i <= columnCount ; i++){
					rowMap.put(rsmd.getColumnName(i),rsmd.getColumnTypeName(i));

				}
				return rowMap;
			}  
		});

		return resultOut;
	}

	@Override
	public ArrayList<String> executeProcedureGettingColType(String packName, String spname, HashMap inParamMap,
			HashMap outParamMap) {
		return null;
	}

}
