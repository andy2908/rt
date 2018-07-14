package com.uat.hbc.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uat.hbc.common.dao.IntegrationSaveResponseDao;
import com.uat.hbc.common.model.MotorResponseBean;
import com.uat.hbc.commonFactory.DbProcess;




@Controller
public class PaymentFetchController {

	@Autowired
    IntegrationSaveResponseDao integrationSaveResponseDao;
	@Autowired
	@Qualifier("dbProcessImpl")
	DbProcess dbProcess;
	MotorResponseBean bean=new MotorResponseBean();

@RequestMapping(value = "user/getPaymentDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
public @ResponseBody String getPayRelData(HttpServletRequest request, HttpServletResponse response)
		 {
	String str="";
	String motorGroupResponseGroupId=request.getParameter("motorGroupResponseGroupId");
	String motorGroupResponseSessionId=request.getParameter("motorGroupResponseSessionId");
	String motorGroupResponseGicId=request.getParameter("motorGroupResponseGicId");
	String userId=request.getParameter("userId");
	String userDesc=request.getParameter("userDesc");
	String branchId=request.getParameter("branchId");
	String quotationNo=request.getParameter("quotationNo");
	String proposalNo=request.getParameter("proposalNo");
	System.out.println("proposalNo:::" +proposalNo);
	System.out.println("quotationNo:::" +quotationNo);
	System.out.println("branchId:::" +branchId);
	System.out.println("userDesc:::" +userDesc);
	System.out.println("userId:::" +userId);
	System.out.println("motorGroupResponseGicId:::" +motorGroupResponseGicId);
	System.out.println("motorGroupResponseSessionId:::" +motorGroupResponseSessionId);
	System.out.println("motorGroupResponseGroupId:::" +motorGroupResponseGroupId);
	
	bean.setGroupId(motorGroupResponseGroupId);
	bean.setSessionId(motorGroupResponseSessionId);
	bean.setGicId(motorGroupResponseGicId);
	bean.setUserId(userId);
	bean.setUserDesc(userDesc);
	bean.setBranchId(branchId);
	
	String payResponse = integrationSaveResponseDao.getPaymentData(bean, quotationNo, proposalNo);
	System.out.println("payResponse::" +payResponse);
	return payResponse;
}
}

			