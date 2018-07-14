package com.uat.hbc.common.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uat.hbc.common.dao.VenueMasterDao;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.VenueMasterModel;

@Service
public class VenueMasterService {
	@Autowired
	VenueMasterDao venueMasterDao;
	VenueMasterModel venueMasterModel = new VenueMasterModel();

	public Result insertUpdatePARecordPKGwithArray(String opFlag, String commonTrno, String venueHeadId,
			String venueName, String phoneNo, String mobileNo1, String mobileNo2, String emailId, String website,
			String venueTypeId, String venueStructure, String totalSeats, byte[] venueMap, String adrName,
			String areaId, String house, String houseId, String street, String streetId, String landmark,
			String landmarkId, String srno, String venueLineId, String rowNo, String seatNo, String seatStatus,
			String seatTypeId, String rowSeatStr,String strId, String branchId, String userId, String userDesc) {
		
		venueMasterModel.setVenueHeadId(venueHeadId);
		venueMasterModel.setVenueName(venueName);
		venueMasterModel.setPhoneNo(phoneNo);
		venueMasterModel.setMobileNo1(mobileNo1);
		venueMasterModel.setMobileNo2(mobileNo2);
		venueMasterModel.setEmailId(emailId);
		venueMasterModel.setWebsite(website);
		venueMasterModel.setVenueTypeId(venueTypeId);
		venueMasterModel.setVenueStructure(venueStructure);
		venueMasterModel.setTotalSeats(totalSeats);
		venueMasterModel.setVenueMap(venueMap);
		venueMasterModel.setAdrName(adrName);
		venueMasterModel.setAreaId(areaId);
		venueMasterModel.setHouse(house);
		venueMasterModel.setHouseId(houseId);
		venueMasterModel.setStreet(street);
		venueMasterModel.setStreetId(streetId);
		venueMasterModel.setLandmark(landmark);
		venueMasterModel.setLandmarkId(landmarkId);
		venueMasterModel.setStrId(strId);

		venueMasterModel.setSrNo(Arrays.stream(srno.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));

		venueMasterModel.setVenueLineId(Arrays.stream(venueLineId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));

		venueMasterModel.setRowNo(rowNo.split("~"));
		venueMasterModel.setSeatNo(seatNo.split("~"));
		venueMasterModel.setSeatStatus(seatStatus.split("~"));

		venueMasterModel.setSeatTypeId(Arrays.stream(seatTypeId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));

		venueMasterModel.setRowSeatStr(rowSeatStr.split("~"));
		Result resObj = new Result();

		resObj = venueMasterDao.insertUpdateVenueRecordPKGwithArray(venueMasterModel, opFlag, commonTrno, userId,
				branchId, userDesc);
		return resObj;

	}
	//////////
}
