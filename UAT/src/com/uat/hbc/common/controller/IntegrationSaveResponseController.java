package com.uat.hbc.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.dao.IntegrationSaveResponseDao;
import com.uat.hbc.common.dao.MasterDao;
import com.uat.hbc.common.model.MotorResponseBean;


@Controller
public class IntegrationSaveResponseController {


	@Autowired
	MasterDao masterDao;
	@org.springframework.beans.factory.annotation.Autowired
	private IntegrationSaveResponseDao integrationSaveResponseDao;

	@RequestMapping("user/IntegrationSaveResponse")
	public ModelAndView callJSP(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/IntegrationSaveResponse");
		return model;
	}
	
	@RequestMapping(value = "/IntegrationSaveResponse", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String RtoGroupComPack(HttpServletRequest request,HttpServletResponse res) {
		System.out.println("In IntegrationSaveResponse" );
		String groupId = request.getParameter("groupId");
		String sessionId = request.getParameter("sessionId");
		String proposalType = request.getParameter("proposalType");
		String policyType = request.getParameter("policyType");
		String product = request.getParameter("product");
		String rtoCity = request.getParameter("rtoCity");
		String mfrYear = request.getParameter("mfrYear");
		String variance = request.getParameter("variance");
		String fuelType = request.getParameter("fuelType");
		String userId = request.getParameter("userId");
		String branchId = request.getParameter("branchId");
		String userDesc = request.getParameter("userDesc");
		
		String result=integrationSaveResponseDao.motorGroupData(groupId,sessionId,proposalType,policyType,product,rtoCity,mfrYear,variance,fuelType,userId,branchId,userDesc);
		System.out.println("result::: " +result);
		return result;
		
	}
	
	
	@RequestMapping(value = "/IntegrationGetCovers", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String IntGetCovers(HttpServletRequest request,HttpServletResponse res) {
		
		String tableName = request.getParameter("TABLE_NAME");
		String rtoCity = request.getParameter("Rto_City");
		String zoneId = request.getParameter("ZONE_ID");
		String policyType = request.getParameter("PolicyType");
		String varId = request.getParameter("VarID");
		String bussType = request.getParameter("BusinessType");
		String productId = request.getParameter("ProductID");
		String custType = request.getParameter("CUST_TYPE");
		String gicId = request.getParameter("GIC_ID");
		
		String result=integrationSaveResponseDao.getCovers(tableName,rtoCity,zoneId,policyType,varId,bussType,productId,custType,gicId);
		return result;
		
	}
	
	
	@RequestMapping(value = "/motorIntgCalculationDetails", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String motorIntgCalculationDetails(HttpServletRequest request,HttpServletResponse res) {
		

		
		String groupId = request.getParameter("GROUP_ID");
		String gicId = request.getParameter("GIC_ID");
		String sessionId = request.getParameter("SESSION_ID");
		String responseType = request.getParameter("RESPONSE_TYPE");
		String covType = request.getParameter("COV_TYPE");
		String ipAddress = request.getParameter("IP_ADDRESS");
		String userId = request.getParameter("USERID");
		String branchId = request.getParameter("BRANCH_ID");
		String userDesc = request.getParameter("USERDESC");
		
		String result=integrationSaveResponseDao.motorIntgCalculationDetails(groupId,gicId,sessionId,responseType,covType,ipAddress,userId,branchId,userDesc);
		return result;
		
	}
}
