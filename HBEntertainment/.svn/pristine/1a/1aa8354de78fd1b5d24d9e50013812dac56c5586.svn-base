package com.uat.hbc.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.uat.hbc.common.ExportReport;
import com.uat.hbc.common.PasswordManager;
import com.uat.hbc.common.Utils;
import com.uat.hbc.common.dao.MasterDao;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.User;

@Controller
public class MasterController {
	
	private Properties props;

	@Autowired
	MasterDao masterDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	@RequestMapping(value = "/getRecordLst", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getList(HttpServletRequest request,
			HttpServletResponse res) {
		// ////System.out.println("------------in Record List-------------");
		HttpSession session = request.getSession();
		
		String sqlMstId = request.getParameter("sqlMstId");
		String param = request.getParameter("param");
		
		System.out.println("param:" + param);
		String result = masterDao.getRecordList(sqlMstId, param, null, null,
				null, null, "1", "11", null);
		/*
		 * try { PrintWriter writer = res.getWriter(); writer.write(result);
		 * writer.flush(); writer.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		System.out.println("JSON DATA:" + result);
		return result;
	}
	
	@RequestMapping(value = "/user/getRecordLst", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getListUser(HttpServletRequest request,
			HttpServletResponse res) {
		// ////System.out.println("------------in Record List-------------");
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		String sqlMstId = request.getParameter("sqlMstId");
		String param = request.getParameter("param");
		String deptMstId = user.getDeptId();
		String entityId = "1";
		String userId = user.getUserId();
		
		System.out.println("param:" + param);
		String result = masterDao.getRecordList(sqlMstId, param, null, null,
				null, deptMstId, entityId, userId, null);
		/*
		 * try { PrintWriter writer = res.getWriter(); writer.write(result);
		 * writer.flush(); writer.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		System.out.println("JSON DATA:" + result);
		return result;
	}

	@RequestMapping(value = "/getInsFindFormData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getFindFormData(HttpServletRequest request,
			HttpServletResponse res) {
		// ////System.out.println("------------in Record List-------------");
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();

		String pkg_name = request.getParameter("pkg_name");
		String proc_name = request.getParameter("proc_name");
		String table_name = request.getParameter("table_name");
		String str_company_type = request.getParameter("str_company_type");
		String str_gic = request.getParameter("str_gic");
		String str_gicbid = request.getParameter("str_gicbid");
		String str_prod = request.getParameter("str_prod");
		String str_discnm = request.getParameter("str_discnm");
		String str_rgrp = request.getParameter("str_rgrp");
		String str_state = request.getParameter("str_state");
		String str_city = request.getParameter("str_city");
		String str_prpsl = request.getParameter("str_prpsl");
		String str_spnm = request.getParameter("str_spnm");
		String str_mgnm = request.getParameter("str_mgnm");
		String str_productcode = request.getParameter("str_productcode");
		String str_type = request.getParameter("str_type");
		String str_type_1 = request.getParameter("str_type_1");
		String str_kg_from = request.getParameter("str_kg_from");
		String str_kg_to = request.getParameter("str_kg_to");
		String str_fueltype = request.getParameter("str_fueltype");
		String str_veh = request.getParameter("str_veh");
		String str_mod = request.getParameter("str_mod");
		String str_var = request.getParameter("str_var");
		String str_policy_age = request.getParameter("str_policy_age");
		String str_hbbid = request.getParameter("str_hbbid");
		String str_user_level = request.getParameter("str_user_level");
		String str_user_id = request.getParameter("str_user_id");
		String str_login_type = request.getParameter("str_login_type");
		String str_gap = request.getParameter("str_gap");
		String str_ageto = request.getParameter("str_ageto");
		String str_poltype = request.getParameter("str_poltype");
		String str_calctype = request.getParameter("str_calctype");
		String str_fuelkit = request.getParameter("str_fuelkit");
		String str_custType = request.getParameter("str_custType");
		String valID = request.getParameter("valID");

		System.out.println("str_poltype-->>>" + str_poltype);
		System.out.println("str_fueltype-->>>" + str_fueltype);
		System.out.println("str_fuelkit-->>>" + str_fuelkit);
		System.out.println("str_prod-->>>" + str_prod);
		System.out.println("str_user_id-->>>" + str_user_id);
		System.out.println("str_login_type-->>>" + str_login_type);

		String result = masterDao.getInsFindFormList(pkg_name, proc_name,
				table_name, str_gic, str_gicbid, str_prod, str_discnm,
				str_rgrp, str_prpsl, str_spnm, str_mgnm, str_veh, str_mod,
				str_var, str_state, str_city, str_policy_age, str_hbbid,
				str_user_level, str_company_type, str_login_type, str_gap,
				str_fueltype, str_productcode, str_type, str_type_1,
				str_kg_from, str_kg_to, str_user_id, str_ageto, str_poltype,
				str_calctype, str_fuelkit, str_custType, valID);
		/*
		 * try { PrintWriter writer = res.getWriter(); writer.write(result);
		 * writer.flush(); writer.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		System.out.println("JSON DATA:" + result);
		return result;

	}

	@RequestMapping(value = "/getAccFindFormData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getAccFindFormData(HttpServletRequest request,
			HttpServletResponse res) {
		// ////System.out.println("------------in Record List-------------");
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();

//		LEDGER(PI_FIND     VARCHAR2, --*
//                PI_GT_ID    VARCHAR2, --*
//                PI_L_TYPE   VARCHAR2, --*
//                PI_GL_ID    VARCHAR2, --*
//                PI_STATUS   VARCHAR2,
//                PI_VTYPE    VARCHAR2,
//                PI_STR_LID  VARCHAR2,
//                P_REFCURSOR Out Sys_Refcursor,
//                PO_ERROR    Out Varchar2)
		
		String pkg_name = request.getParameter("pkg_name");
		String proc_name = request.getParameter("proc_name");
		// String table_name = request.getParameter("table_name");
		String find = request.getParameter("find");
		String gtId = request.getParameter("gtId");
		String lType = request.getParameter("lType");
		String glId = request.getParameter("glId");
		String status = request.getParameter("status");
		String vType = request.getParameter("vType");
		String strLid = request.getParameter("strLid");

		System.out.println("pkg_name-->>>" + pkg_name);
		System.out.println("proc_name-->>>" + proc_name);
		System.out.println("find-->>>" + find);
		System.out.println("gtId-->>>" + gtId);
		System.out.println("lType-->>>" + lType);
		System.out.println("glId-->>>" + glId);
		System.out.println("status-->>>" + status);
		System.out.println("vType-->>>" + vType);
		System.out.println("strLid-->>>" + strLid);

		String result = masterDao.getAccFindFormList(pkg_name, proc_name,
				lType, find,gtId, glId, status, vType, strLid);
		/*
		 * try { PrintWriter writer = res.getWriter(); writer.write(result);
		 * writer.flush(); writer.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		System.out.println("JSON DATA:" + result);
		return result;

	}

	@RequestMapping(value = "/getDataFromFunction", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getDataFromFunction(HttpServletRequest request,
			HttpServletResponse res) {
		
		String colName=request.getParameter("colName");
		String colValue=request.getParameter("colValue");
		String pkgName = request.getParameter("pkgName");
		pkgName = pkgName.equalsIgnoreCase("null")?"":pkgName;
		String funcName = request.getParameter("funcName");
		
		String result = masterDao.getRecordFromFunction(pkgName, funcName, colName, colValue);
		
		return result;
	}
	
	@RequestMapping(value = "/printReport", method = RequestMethod.GET)
	public void printPRCReport(HttpServletRequest request, HttpServletResponse response) {
		// //System.out.println("in print report controller...");

		HttpSession session = request.getSession();
		String reportName = request.getParameter("p_report_name");

		Map<String, Object> parameters = new HashMap<String, Object>();

		Connection conn = null;

		String paramName = request.getParameter("paramname");
		String paramValue = request.getParameter("paramvalue");
		String paramNameArr[] = paramName.split("~");
		String paramValueArr[] = paramValue.split("~");
		for (int i = 0; i < paramNameArr.length; i++) {
			parameters.put(paramNameArr[i], paramValueArr[i]);
		}

		try {
			conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());

			new ExportReport().generatePRCReport(reportName, parameters, response, conn);
			conn.close();
		} catch (Exception e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/getEncodedString", method = RequestMethod.GET)
	public @ResponseBody String getEncodedString(HttpServletRequest request, HttpServletResponse res) {
		
		PasswordManager manager = new PasswordManager();
		String inputStr = request.getParameter("inputString");
		
		inputStr = manager.encrypt(inputStr);
		
		return inputStr;
	}
	
	@RequestMapping(value = "/getDecodedString", method = RequestMethod.GET)
	public @ResponseBody String getDecodedString(HttpServletRequest request, HttpServletResponse res) {
		
		PasswordManager manager = new PasswordManager();
		String inputStr = request.getParameter("inputString");
		
		inputStr = manager.decrypt(inputStr);
		
		return inputStr;
	}
}
