package com.uat.hbc.insurance.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uat.hbc.common.model.Result;

@Controller
public class PaPremiumCalculationController {
	@Autowired
com.uat.hbc.insurance.service.PaPremiumCalculationService PaPremiumCalculationService;
 @RequestMapping(value= "/paPremiumCalculation", method=RequestMethod.POST , produces = "application/json; charset=UTF-8")
 public @ResponseBody String  paPremiumCalculation(HttpServletRequest request,HttpServletResponse responce)
 { 
	  
	 String prpsl_type=request.getParameter("prpsl_type");
	String rtoCity=request.getParameter("rtoCity");
	String sumA=request.getParameter("sumA");
	String sumB=request.getParameter("sumB");
	String sumC=request.getParameter("sumC");
	String sumD=request.getParameter("sumD");
	String total_si=request.getParameter("total_si");
	String risk_class=request.getParameter("risk_class");
	String coverId=request.getParameter("coverId");
	String cover_val=request.getParameter("cover_val");
	String preNCB=request.getParameter("preNCB");
	String  pre_policyType=request.getParameter("pre_policyType");
	String preComp_id=request.getParameter("preComp_id");
	String  userId=request.getParameter("userId");
	String opBranchMstId=request.getParameter("opBranchMstId");
	


	String objResult = PaPremiumCalculationService.getRecordWithArray(prpsl_type,rtoCity,sumA,sumB,sumC,sumD,total_si,risk_class,coverId,cover_val,preNCB,pre_policyType,preComp_id,userId,opBranchMstId);

			System.out.println("resObj="+objResult);
			return objResult;
	
 }
	
}
