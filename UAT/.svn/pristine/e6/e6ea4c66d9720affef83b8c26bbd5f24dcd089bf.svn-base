package com.uat.hbc.insurance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
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
import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.commonFactory.DbProcess;
import com.uat.hbc.insurance.model.MotorEntryModel;

import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

@Repository
public class MotorEntryDao {
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

	public Result insertUpdatePARecordPKGwithArray(MotorEntryModel motorEntryModel, String opFlag, String userId,
			String branchId, String userDesc) {
		Result resObj = new Result();

		String opId = "";
		String mstId = "";
		String errorMsg = "";
		String docNo = "";
		String jsonValues = "";

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

		ARRAY addTypeDbArr = null;
		ARRAY addNameDbArr = null;
		ARRAY addIdDbArr = null;
		ARRAY houseDbArr = null;
		ARRAY houseIdDbArr = null;
		ARRAY streetDbArr = null;
		ARRAY streetIdDbArr = null;
		ARRAY landmarkDbArr = null;
		ARRAY landmarkIdDbArr = null;
		ARRAY ratingTypeDbArr = null;
		ARRAY ratingIdDbArr = null;
		ARRAY ratingValDbArr = null;
		ARRAY valIdDbArr = null;
		ARRAY valDateDbArr = null;
		ARRAY rateDbArr = null;
		ARRAY amountDbArr = null;
		ARRAY ratingDetailsDbArr = null;
		ARRAY hbUserIdDbArr = null;
		ARRAY commRateDbArr = null;
		ARRAY commIncentiveDbArr = null;
		ARRAY cridDbArr = null;
		ARRAY commAmountDbArr = null;
		ARRAY nomTypeDbArr = null;
		ARRAY nomInitialIdDbArr = null;
		ARRAY nomFirstNameDbArr = null;
		ARRAY nomMiddleNameDbArr = null;
		ARRAY nomLastNameDbArr = null;
		ARRAY nomDobDbArr = null;
		ARRAY nomAadharNoDbArr = null;
		ARRAY nomGenderDbArr = null;
		ARRAY nomRelIdDbArr = null;
		ARRAY nomContactNoDbArr = null;
		ARRAY nomEmailIdDbArr = null;
		ARRAY nomAreaIdDbArr = null;
		ARRAY nomHouseDbArr = null;
		ARRAY nomHouseIdDbArr = null;
		ARRAY nomStreetDbArr = null;
		ARRAY nomStreetIdDbArr = null;
		ARRAY nomLandmarkDbArr = null;
		ARRAY nomLandmarkIdDbArr = null;
		ARRAY prevClaimNoDbArr = null;
		ARRAY prevClaimDateDbArr = null;
		ARRAY prevClaimAmountDbArr = null;

		try {
			addTypeDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getAddType());
			addNameDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getAddName());
			addIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getAddId());
			houseDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getHouse());
			houseIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getHouseId());
			streetDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getStreet());
			streetIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getStreetId());
			landmarkDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getLandmark());
			landmarkIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getLandmarkId());
			ratingTypeDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getRatingType());
			ratingIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getRatingId());
			ratingValDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getRatingVal());
			valIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getValId());
			valDateDbArr = new ARRAY(dateArrayDesc, conn, motorEntryModel.getValDate());
			rateDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getRate());
			amountDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getAmount());
			ratingDetailsDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getRatingDetails());
			hbUserIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getHbUserId());
			commRateDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getCommRate());
			commIncentiveDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getCommIncentive());
			cridDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getCrid());
			commAmountDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getCommAmount());
			nomTypeDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getNomType());
			nomInitialIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getNomInitialId());
			nomFirstNameDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomFirstName());
			nomMiddleNameDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomMiddleName());
			nomLastNameDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomLastName());
			nomDobDbArr = new ARRAY(dateArrayDesc, conn, motorEntryModel.getNomDob());
			nomAadharNoDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomAadharNo());
			nomGenderDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomGender());
			nomRelIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getNomRelId());
			nomContactNoDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomContactNo());
			nomEmailIdDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomEmailId());
			nomAreaIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getNomAreaId());
			nomHouseDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomHouse());
			nomHouseIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getHouseId());
			nomStreetDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomStreet());
			nomStreetIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getNomStreetId());
			nomLandmarkDbArr = new ARRAY(varcharArrayDesc, conn, motorEntryModel.getNomLandmark());
			nomLandmarkIdDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getNomLandmarkId());
			prevClaimNoDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getPrevClaimNo());
			prevClaimDateDbArr = new ARRAY(dateArrayDesc, conn, motorEntryModel.getPrevClaimDate());
			prevClaimAmountDbArr = new ARRAY(numberArrayDesc, conn, motorEntryModel.getPrevClaimAmount());
		} catch (Exception e) {
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

		inputParaList.put("pio_app_id",
				(motorEntryModel.getAppId().equals("") ? null : new BigDecimal(motorEntryModel.getAppId())));
		inputParaList.put("pi_opflag", opFlag);
		inputParaList.put("pi_applicant_id", (motorEntryModel.getApplicantId().equals("") ? null
				: new BigDecimal(motorEntryModel.getApplicantId())));
		inputParaList.put("pi_gic_id",
				(motorEntryModel.getGicId().equals("") ? null : new BigDecimal(motorEntryModel.getGicId())));
		inputParaList.put("pi_prpsl_id",
				(motorEntryModel.getProposalId().equals("") ? null : new BigDecimal(motorEntryModel.getProposalId())));
		inputParaList.put("pi_product_id",
				(motorEntryModel.getProductId().equals("") ? null : new BigDecimal(motorEntryModel.getProductId())));
		inputParaList.put("pi_pol_period",
				(motorEntryModel.getPolPeriod().equals("") ? null : new BigDecimal(motorEntryModel.getPolPeriod())));
		inputParaList.put("pi_start_dt", (motorEntryModel.getStartDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getStartDate())));
		inputParaList.put("pi_end_dt", (motorEntryModel.getEndDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getEndDate())));
		inputParaList.put("pi_idv",
				(motorEntryModel.getIdv().equals("") ? null : new BigDecimal(motorEntryModel.getIdv())));
		inputParaList.put("pi_od_total",
				(motorEntryModel.getOdTotal().equals("") ? null : new BigDecimal(motorEntryModel.getOdTotal())));
		inputParaList.put("pi_tp_total",
				(motorEntryModel.getTpTotal().equals("") ? null : new BigDecimal(motorEntryModel.getTpTotal())));
		inputParaList.put("pi_total_prem",
				(motorEntryModel.getTotalPrem().equals("") ? null : new BigDecimal(motorEntryModel.getTotalPrem())));
		inputParaList.put("pi_cgst",
				(motorEntryModel.getCgst().equals("") ? null : new BigDecimal(motorEntryModel.getCgst())));
		inputParaList.put("pi_sgst",
				(motorEntryModel.getSgst().equals("") ? null : new BigDecimal(motorEntryModel.getSgst())));
		inputParaList.put("pi_igst",
				(motorEntryModel.getIgst().equals("") ? null : new BigDecimal(motorEntryModel.getIgst())));
		inputParaList.put("pi_ugst",
				(motorEntryModel.getUgst().equals("") ? null : new BigDecimal(motorEntryModel.getUgst())));
		inputParaList.put("pi_total_tax",
				(motorEntryModel.getTotalTax().equals("") ? null : new BigDecimal(motorEntryModel.getTotalTax())));
		inputParaList.put("pi_net_prem",
				(motorEntryModel.getNetPrem().equals("") ? null : new BigDecimal(motorEntryModel.getNetPrem())));
		inputParaList.put("pi_service_charge", (motorEntryModel.getServiceCharge().equals("") ? null
				: new BigDecimal(motorEntryModel.getServiceCharge())));
		inputParaList.put("pi_total_pay",
				(motorEntryModel.getTotalPay().equals("") ? null : new BigDecimal(motorEntryModel.getTotalPay())));
		inputParaList.put("pi_pay_mode",
				(motorEntryModel.getPayMode().equals("") ? null : new BigDecimal(motorEntryModel.getPayMode())));
		inputParaList.put("pi_rto_id",
				(motorEntryModel.getRtoId().equals("") ? null : new BigDecimal(motorEntryModel.getRtoId())));
		inputParaList.put("pi_zone_id",
				(motorEntryModel.getZoneId().equals("") ? null : new BigDecimal(motorEntryModel.getZoneId())));
		inputParaList.put("pi_var_id",
				(motorEntryModel.getVarId().equals("") ? null : new BigDecimal(motorEntryModel.getVarId())));
		inputParaList.put("pi_mfr_year", motorEntryModel.getMfrYear());
		inputParaList.put("pi_mfr_month", motorEntryModel.getMfrMonth());
		inputParaList.put("pi_reg_dt", (motorEntryModel.getRegDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getRegDate())));
		inputParaList.put("pi_fueltype",
				(motorEntryModel.getFuelType().equals("") ? null : new BigDecimal(motorEntryModel.getFuelType())));
		inputParaList.put("pi_reg_no", motorEntryModel.getRegNo());
		inputParaList.put("pi_chasis_no", motorEntryModel.getChassisNo());
		inputParaList.put("pi_engine_no", motorEntryModel.getEngineNo());
		inputParaList.put("pi_passengers",
				(motorEntryModel.getPassengers().equals("") ? null : new BigDecimal(motorEntryModel.getPassengers())));
		inputParaList.put("pi_cc",
				(motorEntryModel.getCc().equals("") ? null : new BigDecimal(motorEntryModel.getCc())));
		inputParaList.put("pi_body_type",
				(motorEntryModel.getBodyType().equals("") ? null : new BigDecimal(motorEntryModel.getBodyType())));
		inputParaList.put("pi_cust_type",
				(motorEntryModel.getCustType().equals("") ? null : new BigDecimal(motorEntryModel.getCustType())));
		inputParaList.put("pi_ins_type", motorEntryModel.getInsType());
		inputParaList.put("pi_policy_no", motorEntryModel.getPolicyNo());
		inputParaList.put("pi_covernote_no", motorEntryModel.getCoverNoteNo());
		inputParaList.put("pi_salutation_id", (motorEntryModel.getSalutationId().equals("") ? null
				: new BigDecimal(motorEntryModel.getSalutationId())));
		inputParaList.put("pi_first_name", motorEntryModel.getFirstName());
		inputParaList.put("pi_middle_name", motorEntryModel.getMiddleName());
		inputParaList.put("pi_last_name", motorEntryModel.getLastName());
		inputParaList.put("pi_dob", AllUtils.getFormattedDateOracle(motorEntryModel.getDob()));
		inputParaList.put("pi_adhaar_no", motorEntryModel.getAadharNo());
		inputParaList.put("pi_pan_no", motorEntryModel.getPanNo());
		inputParaList.put("pi_contact_no_1", motorEntryModel.getContactNo1());
		inputParaList.put("pi_contact_no_2", motorEntryModel.getContactNo2());
		inputParaList.put("pi_email_id", motorEntryModel.getEmailId());
		inputParaList.put("pi_gender", motorEntryModel.getGender());
		inputParaList.put("pi_bank_id",
				(motorEntryModel.getBankId().equals("") ? null : new BigDecimal(motorEntryModel.getBankId())));
		inputParaList.put("pi_account_no", motorEntryModel.getAccountNo());
		inputParaList.put("pi_other_det", motorEntryModel.getOtherDet());
		inputParaList.put("pi_add_type", addTypeDbArr);
		inputParaList.put("pi_add_name", addNameDbArr);
		inputParaList.put("pi_add_id", addIdDbArr);
		inputParaList.put("pi_house", houseDbArr);
		inputParaList.put("pi_house_id", houseIdDbArr);
		inputParaList.put("pi_street", streetDbArr);
		inputParaList.put("pi_street_id", streetIdDbArr);
		inputParaList.put("pi_landmark", landmarkDbArr);
		inputParaList.put("pi_landmark_id", landmarkIdDbArr);
		inputParaList.put("pi_rating_type", ratingTypeDbArr);
		inputParaList.put("pi_rating_id", ratingIdDbArr);
		inputParaList.put("pi_rating_val", ratingValDbArr);
		inputParaList.put("pi_val_id", valIdDbArr);
		inputParaList.put("pi_val_date", valDateDbArr);
		inputParaList.put("pi_rate", rateDbArr);
		inputParaList.put("pi_amount", amountDbArr);
		inputParaList.put("pi_rating_details", ratingDetailsDbArr);
		inputParaList.put("pi_hb_user_id", hbUserIdDbArr);
		inputParaList.put("pi_comm_rate", commRateDbArr);
		inputParaList.put("pi_comm_incentive", commIncentiveDbArr);
		inputParaList.put("pi_crid", cridDbArr);
		inputParaList.put("pi_comm_amount", commAmountDbArr);
		inputParaList.put("pi_nom_type", nomTypeDbArr);
		inputParaList.put("pi_nom_initial_id", nomInitialIdDbArr);
		inputParaList.put("pi_nom_first_name", nomFirstNameDbArr);
		inputParaList.put("pi_nom_middle_name", nomMiddleNameDbArr);
		inputParaList.put("pi_nom_last_name", nomLastNameDbArr);
		inputParaList.put("pi_nom_dob", nomDobDbArr);
		inputParaList.put("pi_nom_aadhar_no", nomAadharNoDbArr);
		inputParaList.put("pi_nom_gender", nomGenderDbArr);
		inputParaList.put("pi_nom_rel_id", nomRelIdDbArr);
		inputParaList.put("pi_nom_contact_no", nomContactNoDbArr);
		inputParaList.put("pi_nom_email_id", nomEmailIdDbArr);
		inputParaList.put("pi_nom_area_id", nomAreaIdDbArr);
		inputParaList.put("pi_nom_house", nomHouseDbArr);
		inputParaList.put("pi_nom_house_id", nomHouseIdDbArr);
		inputParaList.put("pi_nom_street", nomStreetDbArr);
		inputParaList.put("pi_nom_street_id", nomStreetIdDbArr);
		inputParaList.put("pi_nom_landmark", nomLandmarkDbArr);
		inputParaList.put("pi_nom_landmark_id", nomLandmarkIdDbArr);
		inputParaList.put("pi_is_prev_ins", motorEntryModel.getIsPrevIns());
		inputParaList.put("pi_prev_gic_id",
				(motorEntryModel.getPrevGicId().equals("") ? null : new BigDecimal(motorEntryModel.getPrevGicId())));
		inputParaList.put("pi_prev_product_id",
				(motorEntryModel.getPrevProdId().equals("") ? null : new BigDecimal(motorEntryModel.getPrevProdId())));
		inputParaList.put("pi_prev_policy_type", (motorEntryModel.getPrevPolicyType().equals("") ? null
				: new BigDecimal(motorEntryModel.getPrevPolicyType())));
		inputParaList.put("pi_prev_period",
				(motorEntryModel.getPrevPeriod().equals("") ? null : new BigDecimal(motorEntryModel.getPrevPeriod())));
		inputParaList.put("pi_prev_start_dt", (motorEntryModel.getPrevStartDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getPrevStartDate())));
		inputParaList.put("pi_prev_end_date", (motorEntryModel.getPrevEndDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getPrevEndDate())));
		inputParaList.put("pi_prev_idv",
				(motorEntryModel.getPrevIdv().equals("") ? null : new BigDecimal(motorEntryModel.getPrevIdv())));
		inputParaList.put("pi_prev_ins_type", (motorEntryModel.getPrevInsType().equals("") ? null
				: new BigDecimal(motorEntryModel.getPrevInsType())));
		inputParaList.put("pi_prev_policy_no", motorEntryModel.getPrevPolicyNo());
		inputParaList.put("pi_prev_covernote_no", motorEntryModel.getPrevCovernoteNo());
		inputParaList.put("pi_prev_owner_type", motorEntryModel.getPrevOwnerType());
		inputParaList.put("pi_rc_book_name_transfer", motorEntryModel.getRcBookNameTransfer());
		inputParaList.put("pi_dt_rc_book_name_transfer", (motorEntryModel.getRcBookNameTransferDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getRcBookNameTransferDate())));
		inputParaList.put("pi_ins_name_transfer", motorEntryModel.getInsNameTransfer());
		inputParaList.put("pi_dt_ins_name_transfer", (motorEntryModel.getInsNameTransferDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getInsNameTransferDate())));
		inputParaList.put("pi_prev_ncb", motorEntryModel.getPrevNcb());
		inputParaList.put("pi_prev_is_claim", motorEntryModel.getPrevIsClaim());
		inputParaList.put("pi_prev_claim_no", prevClaimNoDbArr);
		inputParaList.put("pi_prev_claim_date", prevClaimDateDbArr);
		inputParaList.put("pi_prev_claim_amount", prevClaimAmountDbArr);
		inputParaList.put("pi_fin_id",
				(motorEntryModel.getFinId().equals("") ? null : new BigDecimal(motorEntryModel.getFinId())));
		inputParaList.put("pi_fin_type", motorEntryModel.getFinType());
		inputParaList.put("pi_fin_city_id",
				(motorEntryModel.getFinCityId().equals("") ? null : new BigDecimal(motorEntryModel.getFinCityId())));
		inputParaList.put("pi_is_insp", motorEntryModel.getIsInsp());
		inputParaList.put("pi_contact_person_name", motorEntryModel.getContactPersonName());
		inputParaList.put("pi_insp_contact_no", motorEntryModel.getInspContactNo());
		inputParaList.put("pi_insp_add_id",
				(motorEntryModel.getInspAddId().equals("") ? null : new BigDecimal(motorEntryModel.getInspAddId())));
		inputParaList.put("pi_insp_house", motorEntryModel.getInspHouse());
		inputParaList.put("pi_insp_house_id", (motorEntryModel.getInspHouseId().equals("") ? null
				: new BigDecimal(motorEntryModel.getInspHouseId())));
		inputParaList.put("pi_insp_street", motorEntryModel.getStreet());
		inputParaList.put("pi_insp_street_id",
				(motorEntryModel.getStreetId().equals("") ? null : new BigDecimal(motorEntryModel.getInspStreetId())));
		inputParaList.put("pi_insp_landmark", motorEntryModel.getLandmark());
		inputParaList.put("pi_insp_landmark_id", (motorEntryModel.getStreetId().equals("") ? null
				: new BigDecimal(motorEntryModel.getInspLandmarkId())));
		inputParaList.put("pi_pay_bank_id", motorEntryModel.getPayBankId());
		inputParaList.put("pi_pay_chq_utr_no", motorEntryModel.getPayChqUtrNo());
		inputParaList.put("pi_pay_chq_tran_date", (motorEntryModel.getPayChqTranDate().equals("") ? null
				: AllUtils.getFormattedDateOracle(motorEntryModel.getPayChqTranDate())));
		inputParaList.put("pi_remarks", motorEntryModel.getRemarks());
		inputParaList.put("pi_op_branch_mst_id", branchId);
		inputParaList.put("pi_userid", userId);
		inputParaList.put("pi_userdesc", userDesc);

		System.out.println("inputParaList = " + inputParaList);
		HashMap<String, Object> cst = dbProcess.executeProcedure("PKG_MOTOR_ENTRY", "PR_SAVE_LOCAL", inputParaList);
		Set outResult = cst.entrySet();

		Iterator resItr = outResult.iterator();
		while (resItr.hasNext()) {
			Map.Entry me = (Map.Entry) resItr.next();
			String getKey = me.getKey().toString();
			if (getKey.equalsIgnoreCase("PO_OP_ID")) {
				opId = (null == me.getValue()) ? "" : me.getValue().toString();
			} else if (getKey.equalsIgnoreCase("po_error")) {
				errorMsg = (null == me.getValue()) ? "" : me.getValue().toString();
				System.out.println("errorMsg" + errorMsg);
			} else if (getKey.equalsIgnoreCase("POC_USER_DTL")) {
				List list = (List) me.getValue();
				Gson gson = new Gson();
				jsonValues = gson.toJson(list);
				System.out.println("MY JSON DATA===>" + jsonValues);
			}
		}
		if (errorMsg.equals("")) {
			if (opFlag.equals("N")) {
				resObj.setStatus("success");
				resObj.setOpId(opId);
				resObj.setGenMstId(mstId);
				resObj.setDocNo(docNo);
				resObj.setMsg("Record Saved Successfully.");
				resObj.setJsonValues(jsonValues);
			}
			if (opFlag.equals("M")) {
				resObj.setStatus("success");
				resObj.setOpId(opId);
				resObj.setGenMstId(mstId);
				resObj.setDocNo(docNo);
				resObj.setMsg("Record Updated Successfully.");
				resObj.setJsonValues(jsonValues);
			}
		} else {
			resObj.setStatus("error");
			resObj.setOpId(opId);
			resObj.setGenMstId("");
			resObj.setMsg(errorMsg);
		}

		return resObj;
	}
}
