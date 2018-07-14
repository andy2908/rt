package com.uat.hbc.fin.dao;

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
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;

@Repository
public class ViewLedgerDao {
	private DbProcess dbProcessImpl;

	public DbProcess getDbProcessImpl() {
		return dbProcessImpl;
	}

	public void setDbProcessImpl(DbProcess dbProcessImpl) {
		this.dbProcessImpl = dbProcessImpl;
	}

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
	
	
	public String searchData(String procName, String lType, String find,
			String glId, String status, String vType, String strLid,
			String fromDate, String toDate) {
	
		Result bean=new Result();

		String errorMsg = "";
		String detailMsg = "";
		String jsonValues = "";
		
		HashMap inputParaList = new HashMap<>();
		
		
		
		inputParaList.put("pi_l_type", lType);
		inputParaList.put("pi_find", find);
		inputParaList.put("pi_gl_id", glId);
		inputParaList.put("pi_status", status);
		inputParaList.put("pi_vtype", vType);
		inputParaList.put("pi_str_lid", strLid);
		inputParaList.put("pi_from_dt", fromDate);
		inputParaList.put("pi_to_dt", toDate);
		
		System.out.println("---IN DAO---");
        System.out.println("lType-->>"+lType);	
        System.out.println("find-->>"+find);	
        System.out.println("glId-->>"+glId);	
        System.out.println("status-->>"+status);	
        System.out.println("vType-->>"+vType);	
        System.out.println("strLid-->>"+strLid);	
        System.out.println("fromDate-->>"+fromDate);	
        System.out.println("toDate-->>"+toDate);
        
		HashMap<String, Object> cst = dbProcess.executeProcedure("ACCOUNTS",procName, inputParaList);

		Set outResult = cst.entrySet();
        System.out.println("RESULT-->>"+outResult);
        
		Iterator resItr = outResult.iterator();

		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			//System.out.println();
			String getKey = me.getKey().toString();
			 if (getKey.equalsIgnoreCase("p_refcursor")) {
				 List list = (List) me.getValue();
					Gson gson = new Gson();
					jsonValues = gson.toJson(list);
			} 
			 else if (getKey.equalsIgnoreCase("po_error")) {
					errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				} 
		}
		if (errorMsg.equals("")) {
	       bean.setJsonValues(jsonValues);
	       bean.setStatus("success");
	       //System.out.println("");
	       System.out.println("Status="+bean.getStatus()+" JSON "+jsonValues);
		} else {
			bean.setStatus("error");
			bean.setErrorMsg(errorMsg);
			System.out.println("Error "+ errorMsg);            
		}

		return jsonValues;
	}
}
