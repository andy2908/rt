package com.uat.hbc.common.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.model.RegistrationModel;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;

import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;


@Repository
public class RegistrationDao {

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
	
	public Result insertUpdateRecord(
			RegistrationModel registrationModel,
			String procName, String opflag, String userid, String branch_id,String userdesc) {
		
		System.out.println("***********In DAO************");
		
		Result resObj = new Result();
		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";
		ArrayDescriptor varcharArrayDesc = null;
		ArrayDescriptor numberArrayDesc = null;
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
			numberArrayDesc = new ArrayDescriptor("TY_NUMBER", conn);
			varcharArrayDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ARRAY adrType = null;
		ARRAY areaId = null;
		ARRAY house = null;
		ARRAY houseID = null;
		ARRAY street = null;
		ARRAY streetID = null;
		ARRAY landmark = null;
		ARRAY landmarkId = null;
		ARRAY uploadName = null;
		ARRAY docType = null;
		ARRAY docPath = null;
		ARRAY ftpId = null;
		
		System.out.println("conn 444" + conn);
		try {
			
			adrType = new ARRAY(varcharArrayDesc, conn,
					registrationModel.getAdrType());
			areaId = new ARRAY(numberArrayDesc, conn,
					registrationModel.getAreaId());
			house = new ARRAY(varcharArrayDesc, conn,
					registrationModel.getHouse());
			houseID = new ARRAY(numberArrayDesc, conn,
					registrationModel.getHouseId());
			street = new ARRAY(varcharArrayDesc, conn,
					registrationModel.getStreet());
			streetID = new ARRAY(numberArrayDesc, conn,
					registrationModel.getStreetId());
			landmark = new ARRAY(varcharArrayDesc, conn,
					registrationModel.getLandmark());
			landmarkId = new ARRAY(numberArrayDesc, conn,
					registrationModel.getLandmarkId());
			uploadName = new ARRAY(varcharArrayDesc, conn,
					registrationModel.getUploadName());		
			docType = new ARRAY(varcharArrayDesc, conn,
					registrationModel.getDocType());		
			docPath = new ARRAY(varcharArrayDesc, conn,
					registrationModel.getDocPath());		
			ftpId = new ARRAY(numberArrayDesc, conn,
					registrationModel.getFtpId());		
			
			String[] s = registrationModel.getUploadName();
			System.err.println("size"+s.length);
			for(String s1: s){
				System.out.println(s1);
			}
			
			String[] s1 = registrationModel.getDocPath();
			System.err.println("size of docPath"+s1.length);
			for(String s2: s1){
				System.out.println(s2);
			}
			
			String[] s3 = registrationModel.getDocType();
			System.err.println("size of docType"+s3.length);
			for(String s2: s3){
				System.out.println(s2);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connWrapped.close();
			System.out.println("conn 555" + conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("conn 666" + conn);
		System.out.println("FTP id:" +ftpId);
		System.err.println("Status"+registrationModel.getStatus());
		System.out.println("Birth date:" + AllUtils.getFormattedDateOracle(registrationModel.getBirthDate()));
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("pio_pos_mst_id", registrationModel.getPosMstID() == null? 0:Integer.parseInt(registrationModel.getPosMstID()));
		inputParaList.put("pi_opflag", opflag);
		inputParaList.put("pi_title_id", registrationModel.getTitleId().equals("") ? 0: Integer.parseInt(registrationModel.getTitleId()));
		inputParaList.put("pi_first_name", registrationModel.getFirstName());
		inputParaList.put("pi_middle_name", registrationModel.getMiddleName());
		inputParaList.put("pi_last_name", registrationModel.getLastName());
		inputParaList.put("pi_first_name_ol", registrationModel.getFirstNameOl());
		inputParaList.put("pi_middle_name_ol", registrationModel.getMiddleNameOl());
		inputParaList.put("pi_last_name_ol", registrationModel.getLastNameOl());
		inputParaList.put("pi_father_name", registrationModel.getFatherName());
		inputParaList.put("pi_mother_name", registrationModel.getMotherName());
		inputParaList.put("pi_gender", registrationModel.getGender());
		inputParaList.put("pi_marital_status", registrationModel.getMaritalStatis() == null? 0: Integer.parseInt(registrationModel.getMaritalStatis()));
		inputParaList.put("pi_birth_date", AllUtils.getFormattedDateOracle(registrationModel.getBirthDate()));
		inputParaList.put("pi_religion_id", registrationModel.getReligionId().equals("") ? 0: Integer.parseInt(registrationModel.getReligionId()));
		inputParaList.put("PI_AADHAR_ENROLL_NO", registrationModel.getAadharEnroll());
		inputParaList.put("PI_AADHAR_NO", registrationModel.getAadharNo());
		inputParaList.put("PI_PAN_NO", registrationModel.getPanNo());
		inputParaList.put("pi_category_id", registrationModel.getCategoryId().equals("") ? 0: Integer.parseInt(registrationModel.getCategoryId()));
		inputParaList.put("pi_education_id", registrationModel.getEducationId().equals("") ? 0: Integer.parseInt(registrationModel.getEducationId()));
		inputParaList.put("pi_mob_no", registrationModel.getMobNo());
		inputParaList.put("pi_phone_no", registrationModel.getPhoneNo());
		inputParaList.put("pi_email_id", registrationModel.getEmailId());
		inputParaList.put("pi_adr_type", adrType);
		inputParaList.put("pi_area_id", areaId);
		inputParaList.put("pi_house", house);
		inputParaList.put("pi_house_id", houseID);
		inputParaList.put("pi_street", street);
		inputParaList.put("pi_street_id", streetID);
		inputParaList.put("pi_landmark", landmark);
		inputParaList.put("pi_landmark_id", landmarkId);
		inputParaList.put("pi_upload_name", uploadName);
		inputParaList.put("pi_doc_path", docPath);
		inputParaList.put("pi_doc_type", docType);
		inputParaList.put("pi_ftp_id", ftpId);
		inputParaList.put("pi_str_id",registrationModel.getStrID());
		inputParaList.put("pi_branch_id",(branch_id == null ? null : Integer.parseInt(branch_id)));
		inputParaList.put("pi_entry_date", AllUtils.getFormattedDateOracle(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
		inputParaList.put("pi_op_date", AllUtils.getFormattedDateOracle(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
		inputParaList.put("pi_user_id",(userid.equals("") ? null : Integer.parseInt(userid)));
		inputParaList.put("pi_user_desc", userdesc);
	    inputParaList.put("pi_status",registrationModel.getStatus());
		System.out.println("******inputParaList*******" + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_POS_APP", procName, inputParaList);
		System.out.println("cst"+cst);
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			System.out.println("resItr"+resItr);
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("po_opid")) {
				opId = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase("po_error")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue()
						.toString();
				System.out.println("errorMsg" + errorMsg);
			}
		}
		if (errorMsg.equals("")) {
			resObj.setStatus("success");
			resObj.setOpId(opId);
			resObj.setGenMstId(mstId);
			resObj.setDocNo(docNo);
			if (opflag.equals("N")) {
				resObj.setMsg("Record Saved Successfully.");
			} else if (opflag.equals("D")) {
				resObj.setMsg("Record Deleted Successfully.");
			}
			resObj.setJsonValues(jsonValues);
		} else {
			resObj.setStatus("error");
			resObj.setOpId(opId);
			resObj.setGenMstId("");
			resObj.setMsg(errorMsg);
		}
		return resObj;
	}

}
