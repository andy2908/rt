package com.uat.hbc.common.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.model.Result;
import com.google.gson.Gson;

@Repository
public class ViewMotorDao {
	
	private com.uat.hbc.commonFactory.DbProcess dbProcessImpl;

	public com.uat.hbc.commonFactory.DbProcess getDbProcessImpl() {
		return dbProcessImpl;
	}

	public void setDbProcessImpl(com.uat.hbc.commonFactory.DbProcess dbProcessImpl) {
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
	com.uat.hbc.commonFactory.DbProcess dbProcess;
	
	public String searchData(String find,int appId,int policytype,String prodId,String gicId,String prpslId,String applicantID,String frDt,String toDt,String startDate,String endDate,String hbbId,String appStatus,String paymode,int branchId,int userId,String usreDesc) {
		
		Result bean=new Result();

		String errorMsg = "";
		String detailMsg = "";
		String jsonValues = "";
		
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("pi_find", find);
		inputParaList.put("pi_app_id", appId);
		inputParaList.put("pi_policytype", policytype);
		inputParaList.put("pi_prod_id", prodId);
		inputParaList.put("pi_gic_id", gicId);
		inputParaList.put("pi_prpsl_id", prpslId);
		inputParaList.put("pi_applicant_id", applicantID);
		inputParaList.put("pi_fr_dt", frDt);
		inputParaList.put("pi_to_dt", toDt);
		inputParaList.put("pi_start_dt", startDate);
		inputParaList.put("pi_end_dt", endDate);
		inputParaList.put("pi_hbbid", hbbId);
		inputParaList.put("pi_app_status", appStatus);
		inputParaList.put("pi_paymode", paymode);
		inputParaList.put("pi_op_branch_mst_id", branchId);
		inputParaList.put("pi_userid", userId);
		inputParaList.put("pi_userdesc", usreDesc);
		
		System.out.println("Dao find------------>>"+find);
		System.out.println("Dao appId------------>>"+appId);
		System.out.println("Dao policyType------------>>"+policytype);
		System.out.println("Dao prodId------------>>"+prodId);
		System.out.println("Dao gicId------------>>"+gicId);
		System.out.println("Dao prpslId------------>>"+prpslId);
		System.out.println("Dao applicantID------------>>"+applicantID);
		System.out.println("Dao frDt------------>>"+frDt);
		System.out.println("Dao toDt------------>>"+toDt);
		System.out.println("Dao startDate------------>>"+startDate);
		System.out.println("Dao endDate------------>>"+endDate);
		System.out.println("Dao hbbId------------>>"+hbbId);
		System.out.println("Dao appStatus------------>>"+appStatus);
		System.out.println("Dao paymode------------>>"+paymode);
		System.out.println("Dao branchId------------>>"+branchId);
		System.out.println("Dao userId------------>>"+userId);
		System.out.println("Dao usreDesc------------>>"+usreDesc);
		
		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_MOTOR_ENTRY","PR_MOTOR_REPORTS", inputParaList);
		
		Set outResult = cst.entrySet();
		
		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			 if (getKey.equalsIgnoreCase("po_detail")) {
				 List list = (List) me.getValue();
					Gson gson = new Gson();
					jsonValues = gson.toJson(list);
					System.out.println("list=====>>"+list);
					System.out.println("gson=====>>"+gson);
					System.out.println("jsonValues=====>>"+jsonValues);
			} 
			 else if (getKey.equalsIgnoreCase("po_error")) {
					errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				} 
		}
		if (errorMsg.equals("")) {
             bean.setJsonValues(jsonValues);
             System.out.println("");
             System.out.println("Status="+bean.getStatus()+" JSON "+jsonValues);
		} else {
			bean.setStatus("error");
			bean.setErrorMsg(errorMsg);
			System.out.println("Error "+ errorMsg);            
		}

		return jsonValues;
	}
		

	
	
}
