package com.uat.hbc.common.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uat.hbc.common.dao.EventMasterDao;
import com.uat.hbc.common.model.EventMasterModel;
import com.uat.hbc.common.model.Result;

@Service
public class EventMasterService {
    @Autowired
	EventMasterDao eventMasterDao;

	EventMasterModel eventMasterModel=new EventMasterModel();
	public Result insertUpdatePARecordPKGwithArray(String opflag, String common_tron, String event_head_id, String event_name,
			String phone_no, String mobile_no_1, String mobile_no_2, String email_id, String website, String venue_head_id,
			String venue_structure, String total_seats, byte[] venue_map, String navigation_img, String adr_name,
			String area_id, String house, String house_id, String street, String street_id, String landmark,
			String landmark_id,String str_id ,String srNo, String event_line_id,String row_no, String from_seat, String to_seat,
			String seat_status, String seat_type_id, String row_seat_str, String online_tran_id, String pg_train_status,
			String pg_amount, String pg_tran_no, String pg_tran_date, String pg_tran_time, String book_by_user_id,
			String book_by_name, String book_person_mobileNo, String agent_id, String branch_id, String user_id, String user_desc,String str_row_no) {
		
	
		eventMasterModel.setEvent_head_id(event_head_id);
		eventMasterModel.setEvent_name(event_name);
		eventMasterModel.setPhone_no(phone_no);
		eventMasterModel.setMobile_no_1(mobile_no_1);
		eventMasterModel.setMobile_no_2(mobile_no_2);
		eventMasterModel.setEmail_id(email_id);
		eventMasterModel.setWebsite(website);
		eventMasterModel.setVenue_head_id(venue_head_id);
		eventMasterModel.setVenue_structure(venue_structure);
		eventMasterModel.setTotal_seats(total_seats);
		eventMasterModel.setVenue_map(venue_map);
		eventMasterModel.setNavigation_img(navigation_img);
		eventMasterModel.setAdr_name(adr_name);
		eventMasterModel.setArea_id(area_id);
		eventMasterModel.setHouse(house);
		eventMasterModel.setHouse_id(house_id);
		eventMasterModel.setStreet(street);
		eventMasterModel.setStreet_id(street_id);
		eventMasterModel.setLandmark(landmark);
		eventMasterModel.setLandmark_id(landmark_id);
		eventMasterModel.setStr_id(str_id);
		eventMasterModel.setStr_row_no(str_row_no);
		eventMasterModel.setSrNo(Arrays.stream(srNo.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		
		
		
		eventMasterModel.setEvent_line_id(Arrays.stream(event_line_id.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		
		eventMasterModel.setRow_no(row_no.split("~"));
		
		eventMasterModel.setFrom_seat(Arrays.stream(from_seat.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		
		eventMasterModel.setTo_seat(Arrays.stream(to_seat.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		
		/*eventMasterModel.setSeat_no(seat_no.split("~"));*/
		eventMasterModel.setSeat_status(seat_status.split("~"));
		
		eventMasterModel.setSeat_type_id(Arrays.stream(seat_type_id.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		eventMasterModel.setRow_seat_str(row_seat_str.split("~"));
		eventMasterModel.setOnline_tran_id(Arrays.stream(online_tran_id.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		eventMasterModel.setPg_train_status(pg_train_status.split("~"));
		eventMasterModel.setPg_amount(Arrays.stream(pg_amount.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		eventMasterModel.setPg_tran_no(pg_tran_no.split("~"));
		/*eventMasterModel.setPg_tran_date(pg_tran_date.split("~"));*/
		eventMasterModel.setPg_tran_date(Arrays.stream(pg_tran_date.split("~")).map(s -> {
			return com.uat.hbc.common.AllUtils.getFormattedDateOracle(s);
		}).toArray(String[]::new));
		eventMasterModel.setPg_tran_time(pg_tran_time.split("~"));
		eventMasterModel.setBook_by_user_id(Arrays.stream(book_by_user_id.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		
		eventMasterModel.setBook_by_name(book_by_name.split("~"));
		eventMasterModel.setBook_person_mobileNo(book_person_mobileNo.split("~"));
		
		eventMasterModel.setAgent_id(Arrays.stream(agent_id.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		
		  Result resObj = new Result();
		resObj = eventMasterDao.insertUpdateVenueRecordPKGwithArray(eventMasterModel, opflag, common_tron, user_id,
				branch_id, user_desc);
		return resObj;
		
	}
	

}
