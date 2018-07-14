package com.uat.hbc.insurance.dao;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.insurance.model.PAPremiumModel;
import com.google.gson.Gson;


@Repository
public class PAPremiumDao {
	private DbProcess dbProcessImpl;

	public DbProcess getDbProcessImpl() {
		return dbProcessImpl;
	}

	public void setDbProcessImpl(DbProcess dbProcessImpl) {
		this.dbProcessImpl = dbProcessImpl;
	}

	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public String getPAPRemiumRecordPKGwithArray(PAPremiumModel paPremiumModel, String userId, String branchId)
		 {
		String errorMsg = "";
		String po_msg = "";
		String jsonValues = "";

		Result resObj = new Result();
		
		ArrayDescriptor sumADesc= null;
		ArrayDescriptor sumBDesc= null;
		ArrayDescriptor sumCDesc= null;
		ArrayDescriptor sumDDesc= null;
		ArrayDescriptor totalsiDesc= null;
		ArrayDescriptor riskclassDesc= null;
		ArrayDescriptor coverIdDesc= null;
		ArrayDescriptor covervalDesc= null;
		
		Connection conn = null;
		Connection connWrapped = null;

		try {
			System.out.println("conn 111" + conn);
			connWrapped = jdbcTemplate.getDataSource().getConnection();
			System.out.println("conn 222" + conn);
			conn = connWrapped.unwrap(OracleConnection.class);
			System.out.println("conn 333" + conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			
			
			 sumADesc=  new ArrayDescriptor("TY_NUMBER", conn);
			 sumBDesc=  new ArrayDescriptor("TY_NUMBER", conn);
			 sumCDesc=  new ArrayDescriptor("TY_NUMBER", conn);
			 sumDDesc=  new ArrayDescriptor("TY_NUMBER", conn);
			 totalsiDesc=  new ArrayDescriptor("TY_NUMBER", conn);
			 riskclassDesc= new ArrayDescriptor("TY_NUMBER", conn);
			 coverIdDesc= new ArrayDescriptor("TY_NUMBER", conn);
			 covervalDesc= new ArrayDescriptor("TY_VARCHAR2", conn);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ARRAY sumAArr= null;
		ARRAY sumBArr= null;
		ARRAY sumCArr= null;
		ARRAY sumDArr= null;
		ARRAY totalsiArr= null;
		ARRAY riskclassArr= null;
		ARRAY coverIdArr= null;
		ARRAY covervalArr= null;
		System.out.println("conn 444" + conn);
		try {
			 sumAArr= new ARRAY(sumADesc, conn, paPremiumModel.getSumA());
			 sumBArr=  new ARRAY(sumBDesc, conn, paPremiumModel.getSumB());
			 sumCArr=  new ARRAY(sumCDesc, conn, paPremiumModel.getSumB());
			 sumDArr=  new ARRAY(sumDDesc, conn, paPremiumModel.getSumD());
			 totalsiArr=  new ARRAY(totalsiDesc, conn, paPremiumModel.getTotal_si());
			 riskclassArr=  new ARRAY(riskclassDesc, conn, paPremiumModel.getRisk_class());
			 coverIdArr= new ARRAY(coverIdDesc, conn, paPremiumModel.getCoverId());
			 covervalArr= new ARRAY(covervalDesc, conn, paPremiumModel.getCover_val());;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			// jdbcTemplate.getDataSource().getConnection().close();
			connWrapped.close();
			System.out.println("conn 555" + conn);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("conn 666" + conn);
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("PI_PRPSL_TYPE" ,(paPremiumModel.getPrpsl_type().equals("")? null:Integer.parseInt(paPremiumModel.getPrpsl_type())));
		inputParaList.put("PI_RTOCITY_ID" ,(paPremiumModel.getRtoCity().equals("")? null:Integer.parseInt(paPremiumModel.getRtoCity())));
		inputParaList.put("PI_SUMA" ,sumAArr);
		inputParaList.put("PI_SUMB" ,sumBArr);
		inputParaList.put("PI_SUMC" ,sumCArr);
		inputParaList.put("PI_SUMD" ,sumDArr);
		inputParaList.put("PI_TOTAL_SI" ,totalsiArr);
		inputParaList.put("PI_RISK_CLASS" ,riskclassArr);
		inputParaList.put("PI_COVER_ID" ,coverIdArr);
		inputParaList.put("PI_COVER_VAL" ,covervalArr);
		inputParaList.put("PI_PRE_NCB" , paPremiumModel.getPreNCB());
		inputParaList.put("PI_PRE_POLTYPE" ,paPremiumModel.getPre_policyType());
		inputParaList.put("PI_PRE_COMP_ID" ,paPremiumModel.getPreComp_id());
		inputParaList.put("PI_USERID" ,(userId.equals("")? null:Integer.parseInt(userId)));
		inputParaList.put("PI_OP_BRANCH_MST_ID" ,(branchId.equals("")? null:Integer.parseInt(branchId)));
			System.out.println("inputParaList*****************" + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_PA", "PR_GET_PREMIUM", inputParaList);
		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("Po_Msg")) {
				po_msg = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase("Po_Error")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("errorMsg" + errorMsg);
			} else if (getKey.equalsIgnoreCase("Poc_Sql_Result")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonValues = gson.toJson(list);
//				jsonValues="[" +jsonValues+"]";
				System.out.println("MY JSON DATA===>" + jsonValues);
			}
		}
		if (errorMsg.equals("")) {
			resObj.setStatus("success");			
			resObj.setDocNo(po_msg);
			resObj.setMsg("Record Saved Successfully.");
			resObj.setJsonValues(jsonValues);
		} else {
			resObj.setStatus("error");
			resObj.setOpId(po_msg);
			resObj.setGenMstId("");
			resObj.setMsg(errorMsg);
		}

		return jsonValues;
	}
	
	
}
