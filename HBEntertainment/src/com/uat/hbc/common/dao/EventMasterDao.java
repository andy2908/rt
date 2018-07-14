package com.uat.hbc.common.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uat.hbc.common.model.EventMasterModel;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
@Repository
public class EventMasterDao {
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

	public Result insertUpdateVenueRecordPKGwithArray(EventMasterModel eventMasterModel, String opflag,
			String common_tron, String user_id, String branch_id, String user_desc) {
System.out.println("eventMasterModel============"+eventMasterModel.toString());
System.out.println("opflag  dao============"+opflag);
		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";
		Result resObj = new Result();
		ArrayDescriptor varcharArrayDesc = null;
		ArrayDescriptor numberArrayDesc = null;
		ArrayDescriptor dateArrayDesc = null;
		
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
			dateArrayDesc = new ArrayDescriptor("TY_DATE", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ARRAY srNoDbArr = null;
		ARRAY event_line_iddDbArr = null;
		ARRAY row_noDbArr = null;
		ARRAY from_seatDbArr = null;
		ARRAY to_seatDbArr = null;
		ARRAY seat_noDbArr = null;
		ARRAY seat_statusDbArr = null;
		ARRAY seat_type_idDbArr = null;
		ARRAY row_seat_strDbArr = null;
		ARRAY online_tran_idDbArr = null;
		ARRAY pg_train_statusDbArr = null;
		ARRAY pg_amountDbArr = null;
		ARRAY pg_tran_noDbArr = null;
		ARRAY pg_tran_dateDbArr = null;
		ARRAY pg_tran_timeDbArr = null;
		ARRAY book_by_user_idDbArr = null;
		ARRAY book_by_nameDbArr = null;
		ARRAY book_person_mobileNoDbArr = null;
		ARRAY agent_idDbArr = null;
		
		String[] rowNo= eventMasterModel.getRow_no();
		BigDecimal[] froseatDbArr= eventMasterModel.getFrom_seat();
		BigDecimal[] to_seatDbArr1= eventMasterModel.getTo_seat();
		for(int i=0;i<rowNo.length;i++){
			/*System.out.println("rowNo========"+rowNo[i]);
			System.out.println("froseatDbArr========"+froseatDbArr[i]);
			System.out.println("to_seatDbArr1========"+to_seatDbArr1[i]);*/
		}
		
		try {
			srNoDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getSrNo());
			event_line_iddDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getEvent_line_id());
			row_noDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getRow_no());
			from_seatDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getFrom_seat());
			to_seatDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getTo_seat());
			/*seat_noDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getSeat_no());*/
			seat_statusDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getSeat_status());
			seat_type_idDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getSeat_type_id());
			row_seat_strDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getRow_seat_str());
			online_tran_idDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getOnline_tran_id());
			pg_train_statusDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getPg_train_status());
			pg_amountDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getPg_amount());
			pg_tran_noDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getPg_tran_no());
			pg_tran_dateDbArr = new ARRAY(dateArrayDesc, conn, eventMasterModel.getPg_tran_date());
			pg_tran_timeDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getPg_tran_time());
			book_by_user_idDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getBook_by_user_id());
			book_by_nameDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getBook_by_name());
			book_person_mobileNoDbArr = new ARRAY(varcharArrayDesc, conn, eventMasterModel.getBook_person_mobileNo());
			agent_idDbArr = new ARRAY(numberArrayDesc, conn, eventMasterModel.getAgent_id());
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
		
		HashMap inputParaList = new HashMap<>();
		inputParaList.put("PI_OPFLAG",opflag);
		inputParaList.put("PI_COMMON_TRNO",(eventMasterModel.getCommon_tron()==null ? null : Integer.parseInt(eventMasterModel.getCommon_tron())));
		inputParaList.put("PIO_EVENT_HEAD_ID",
				(eventMasterModel.getEvent_head_id()==null ? null : Integer.parseInt(eventMasterModel.getEvent_head_id())));		
		inputParaList.put("PI_EVENT_NAME",eventMasterModel.getEvent_name());
		inputParaList.put("PI_PHONE_NO",eventMasterModel.getPhone_no());
		inputParaList.put("PI_MOBILE_NO_1",eventMasterModel.getMobile_no_1());
		inputParaList.put("PI_MOBILE_NO_2",eventMasterModel.getMobile_no_2());
		inputParaList.put("PI_EMAIL_ID",eventMasterModel.getEmail_id());
		inputParaList.put("PI_WEBSITE",eventMasterModel.getWebsite());
		inputParaList.put("PI_VENUE_HEAD_ID",
				(eventMasterModel.getVenue_head_id()==null ? null : Integer.parseInt(eventMasterModel.getVenue_head_id())));
		inputParaList.put("PI_VENUE_STRUCTURE",eventMasterModel.getVenue_structure());
		inputParaList.put("PI_TOTAL_SEATS",
				(eventMasterModel.getTotal_seats()==null ? null : Integer.parseInt(eventMasterModel.getTotal_seats())));
		inputParaList.put("PI_VENUE_MAP",eventMasterModel.getVenue_map());
		inputParaList.put("PI_NAVIGATOR_IMG",eventMasterModel.getNavigation_img());
		inputParaList.put("PI_ADR_NAME",eventMasterModel.getAdr_name());
	
		inputParaList.put("PI_AREA_ID",
				(eventMasterModel.getArea_id()==null ? null : Integer.parseInt(eventMasterModel.getArea_id())));
		inputParaList.put("PI_HOUSE",eventMasterModel.getHouse());
		inputParaList.put("PI_HOUSE_ID",eventMasterModel.getHouse_id());
		inputParaList.put("PI_STREET",eventMasterModel.getStreet());
		inputParaList.put("PI_STREET_ID",eventMasterModel.getStreet_id());
		inputParaList.put("PI_LANDMARK",eventMasterModel.getLandmark());
		inputParaList.put("PI_LANDMARK_ID",eventMasterModel.getLandmark_id());
		inputParaList.put("PI_STR_ID", eventMasterModel.getStr_id());
		System.out.println("PI_STR_ID -->> " +eventMasterModel.getStr_id() );
		inputParaList.put("PI_STR_ROW_NO", eventMasterModel.getStr_row_no());
		System.out.println("PI_STR_ROW_NO -->> " +eventMasterModel.getStr_row_no() );
		inputParaList.put("PI_SRNO", srNoDbArr);
		
		inputParaList.put("PI_EVENT_LINE_ID", event_line_iddDbArr );
		inputParaList.put("PI_ROW_NO", row_noDbArr );
		inputParaList.put("PI_FROM_SEAT", from_seatDbArr);
		inputParaList.put("PI_TO_SEAT", to_seatDbArr);
		/*inputParaList.put("PI_SEAT_NO", seat_noDbArr );*/
		inputParaList.put("PI_SEAT_STATUS",seat_statusDbArr );
		inputParaList.put("PI_SEAT_TYPE_ID",seat_type_idDbArr);
		inputParaList.put("PI_ROW_SEAT_STR",row_seat_strDbArr );
		inputParaList.put("PI_ONLINE_TRAN_ID",online_tran_idDbArr );
		inputParaList.put("PI_PG_TRAN_STATUS",pg_train_statusDbArr);
		inputParaList.put("PI_PG_AMOUNT",pg_amountDbArr);
		inputParaList.put("PI_PG_TRAN_NO",pg_tran_noDbArr);
		inputParaList.put("PI_PG_TRAN_DATE",pg_tran_dateDbArr);
		inputParaList.put("PI_PG_TRAN_TIME",pg_tran_timeDbArr);
		inputParaList.put("PI_BOOKED_BY_USER_ID",book_by_user_idDbArr);
		inputParaList.put("PI_BOOKED_BY_NAME",book_by_nameDbArr);
		
		inputParaList.put("PI_BOOKED_PERSON_MOBILENO",book_person_mobileNoDbArr);
		inputParaList.put("PI_AGENT_ID", agent_idDbArr);
		System.out.println("baranchId========="+branch_id);
		inputParaList.put("pi_branch_id",
				branch_id);
		inputParaList.put("PI_USERID",
				user_id);
		inputParaList.put("PI_USERDESC", user_desc);
		
		System.out.println("inputParaList*****************" + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure(
				"pkg_ent_mst","PR_EVENT_MST", inputParaList);
		
		Set outResult = inputParaList.entrySet();
		Iterator resItr = outResult.iterator();
		
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
		/*	System.out.println("me=============="+me);
			String getVal= me.getValue()+"";*/
		/*	System.out.println("getVal=============="+getVal);*/
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
			} else if (opflag.equals("M")) {
					resObj.setMsg("Record Updated Successfully.");
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
