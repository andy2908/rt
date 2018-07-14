package com.uat.hbc.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
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
import com.uat.hbc.common.model.VenueMasterModel;
import com.uat.hbc.commonFactory.DbProcess;

@Repository
public class VenueMasterDao {
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
	public Result insertUpdateVenueRecordPKGwithArray(VenueMasterModel venueMasterModel,String opFlag, String commonTrNo, String userId, String branchId,
			String userDesc){
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
			varcharArrayDesc = new ArrayDescriptor("TY_VARCHAR2", conn);
			numberArrayDesc = new ArrayDescriptor("TY_NUMBER", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ARRAY srNoDbArr = null;
		ARRAY venueLineIdDbArr = null;
		ARRAY rowNoDbArr = null;
		ARRAY seatNoDbArr = null;
		ARRAY seatStatusDbArr = null;
		ARRAY seatTypeIdDbArr = null;
		ARRAY rowSeatStrDbArr = null;
		
		System.out.println("conn 444" + conn);
		try {
			srNoDbArr = new ARRAY(numberArrayDesc, conn, venueMasterModel.getSrNo());
			venueLineIdDbArr = new ARRAY(numberArrayDesc, conn, venueMasterModel.getVenueLineId());
			rowNoDbArr = new ARRAY(varcharArrayDesc, conn, venueMasterModel.getRowNo());
			seatNoDbArr = new ARRAY(varcharArrayDesc, conn, venueMasterModel.getSeatNo());
			seatStatusDbArr = new ARRAY(varcharArrayDesc, conn, venueMasterModel.getSeatStatus());
			seatTypeIdDbArr = new ARRAY(numberArrayDesc, conn, venueMasterModel.getSeatTypeId());
			rowSeatStrDbArr = new ARRAY(varcharArrayDesc, conn, venueMasterModel.getRowSeatStr());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connWrapped.close();
			System.out.println("conn 555" + conn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("conn 666" + conn);
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("PI_OPFLAG",opFlag);
		inputParaList.put("PI_COMMON_TRNO",Integer.parseInt(commonTrNo));
		inputParaList.put("PIO_VENUE_HEAD_ID",
				(venueMasterModel.getVenueHeadId().equals("") ? null : Integer.parseInt(venueMasterModel.getVenueHeadId())));		
		inputParaList.put("PI_VENUE_NAME",venueMasterModel.getVenueName());
		inputParaList.put("PI_PHONE_NO",venueMasterModel.getPhoneNo());
		inputParaList.put("PI_MOBILE_NO_1",venueMasterModel.getMobileNo1());
		inputParaList.put("PI_MOBILE_NO_2",venueMasterModel.getMobileNo2());
		inputParaList.put("PI_EMAIL_ID",venueMasterModel.getEmailId());
		inputParaList.put("PI_WEBSITE",venueMasterModel.getWebsite());
		inputParaList.put("PI_VENUE_TYPE_ID",
				(venueMasterModel.getVenueTypeId().equals("") ? null : Integer.parseInt(venueMasterModel.getVenueTypeId())));
		inputParaList.put("PI_VENUE_STRUCTURE",venueMasterModel.getVenueStructure());
		inputParaList.put("PI_TOTAL_SEATS",
				(venueMasterModel.getTotalSeats().equals("") ? null : Integer.parseInt(venueMasterModel.getTotalSeats())));
		inputParaList.put("PI_VENUE_MAP",venueMasterModel.getVenueMap());
		inputParaList.put("PI_ADR_NAME",venueMasterModel.getAdrName());
		inputParaList.put("PI_AREA_ID",
				(venueMasterModel.getAreaId().equals("") ? null : Integer.parseInt(venueMasterModel.getAreaId())));
		inputParaList.put("PI_HOUSE",venueMasterModel.getHouse());
		inputParaList.put("PI_HOUSE_ID",
				(venueMasterModel.getHouseId().equals("") ? null : Integer.parseInt(venueMasterModel.getHouseId())));
		inputParaList.put("PI_STREET",venueMasterModel.getStreet());
		inputParaList.put("PI_STREET_ID",
				(venueMasterModel.getStreetId().equals("") ? null : Integer.parseInt(venueMasterModel.getStreetId())));
		inputParaList.put("PI_LANDMARK",venueMasterModel.getLandmark());
		inputParaList.put("PI_LANDMARK_ID",
				(venueMasterModel.getLandmarkId().equals("") ? null : Integer.parseInt(venueMasterModel.getLandmarkId())));
		inputParaList.put("PI_STR_ID",
				(venueMasterModel.getStrId().equals("") ? null : Integer.parseInt(venueMasterModel.getStrId())));
		
		inputParaList.put("PI_SRNO", srNoDbArr);
		inputParaList.put("PI_VENUE_LINE_ID", venueLineIdDbArr);
		inputParaList.put("PI_ROW_NO", rowNoDbArr);
		inputParaList.put("PI_SEAT_NO", seatNoDbArr);
		inputParaList.put("PI_SEAT_STATUS", seatStatusDbArr);
		inputParaList.put("PI_SEAT_TYPE_ID", seatTypeIdDbArr);
		inputParaList.put("PI_ROW_SEAT_STR", rowSeatStrDbArr);
		inputParaList.put("pi_branch_id",
				(branchId.equals("") ? null : Integer.parseInt(branchId)));
		inputParaList.put("PI_USERID",
				(userId.equals("") ? null : Integer.parseInt(userId)));
		inputParaList.put("PI_USERDESC", userDesc);
		
		System.out.println("inputParaList*****************" + inputParaList);
		
		HashMap<String, Object> cst = dbProcess.executeProcedure(
				"PKG_ENT_MST", "PR_VENUE_MST", inputParaList);
		
		Set outResult = cst.entrySet();
		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
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
			if(opFlag.equals("N")){
				resObj.setMsg("Record Saved Successfully.");
			}
			if(opFlag.equals("M")){
				resObj.setMsg("Record Updated Successfully.");
			}
			if(opFlag.equals("D")){
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
