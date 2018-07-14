package com.uat.hbc.insurance.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.dom4j.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uat.hbc.common.model.Result;
import com.uat.hbc.insurance.dao.PAPremiumDao;
import com.uat.hbc.insurance.model.PAPremiumModel;

@Service
public class PaPremiumCalculationService {

	@Autowired
	private PAPremiumDao paPremiumDao;
	PAPremiumModel paPremiumModel = new PAPremiumModel();
	
	
	 public String  getRecordWithArray(String prpsl_type,String rtoCity,String sumA,String sumB,String sumC,String sumD,String total_si,String risk_class,String coverId,String cover_val,String preNCB,String  pre_policyType,String preComp_id,String userId,String opBranchMstId)
	 {
		System.out.println("service runs");
		paPremiumModel.setPrpsl_type(prpsl_type);
		paPremiumModel.setRtoCity(rtoCity);
		paPremiumModel.setSumA(Arrays.stream(sumA.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paPremiumModel.setSumB(Arrays.stream(sumB.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paPremiumModel.setSumC(Arrays.stream(sumC.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paPremiumModel.setSumD(Arrays.stream(sumD.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paPremiumModel.setTotal_si(Arrays.stream(total_si.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paPremiumModel.setRisk_class(Arrays.stream(risk_class.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paPremiumModel.setCoverId(Arrays.stream(coverId.split("~")).map(s -> {
			try {
				return new BigDecimal(s);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}).toArray(BigDecimal[]::new));
		paPremiumModel.setCover_val(cover_val.equals("") ? null : cover_val.split("~"));
		paPremiumModel.setPreNCB(preNCB);
		paPremiumModel.setPre_policyType(pre_policyType);
		paPremiumModel.setPreComp_id(preComp_id);
		
		
		String obj=paPremiumDao.getPAPRemiumRecordPKGwithArray(paPremiumModel,userId,opBranchMstId);
		
		return obj;
	 }


	
}
