package com.uat.hbc.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.dao.MasterDao;
import com.uat.hbc.common.model.Result;

@Controller
public class VenueTypeController {
	@Autowired
	MasterDao masterDao;
	
	@RequestMapping("user/venueType")
	public ModelAndView callJSP(HttpServletRequest req,HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/venuetype");
		return model;
	}
	@RequestMapping(value = "/venueEntry", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result saveDetails(HttpServletRequest request,HttpServletResponse res) {
		HttpSession session = request.getSession();
		String venueType = request.getParameter("venueType");
		String opFlag = request.getParameter("opFlag");
		String venueTypeCode=request.getParameter("venueTypeCode");
		String venueTypeName=request.getParameter("venueTypeName");
		String venueTypeNameOl=request.getParameter("venueTypeNameOl");
		String strId=request.getParameter("strId");
		String branchId=request.getParameter("branchId");
		String entryDate = request.getParameter("entryDate");
		String opDate=request.getParameter("opDate");
		String userId = request.getParameter("userId");
		String userDesc = request.getParameter("userDesc");
		String status = request.getParameter("status");
		
		
		System.out.println("venueType---->>"+venueType);
		System.out.println("opFlag---->>"+opFlag);
		System.out.println("venueTypeCode---->>"+venueTypeCode);
		System.out.println("venueTypeName---->>"+venueTypeName);
		System.out.println("venueTypeNameOl---->>"+venueTypeNameOl);
		System.out.println("strId--->>"+strId);
		System.out.println("branchId---->>"+branchId);
		System.out.println("entryDate---->>"+entryDate);
		System.out.println("opDate---->>"+opDate);
		System.out.println("userId---->>"+userId);
		System.out.println("userDesc---->>"+userDesc);
		System.out.println("status---->>"+status);
		
		String procName = "PR_ENT_VENUE_TYPE";
		                   

		String colName = "PIO_VENUE_TYPE_ID~PI_OPFLAG~PI_VENUE_TYPE_CODE~PI_VENUE_TYPE_NAME~PI_VENUE_TYPE_NAME_OL~PI_STR_ID~PI_BRANCH_ID~PI_ENTRY_DATE~PI_OP_DATE~PI_USER_ID~PI_USER_DESC~PI_STATUS";

		String colValue =venueType+"~"+opFlag+"~"+venueTypeCode+"~"+venueTypeName+"~"+venueTypeNameOl+"~"+strId+"~"+branchId+"~"+AllUtils.getFormattedDateOracle(entryDate)+"~"+AllUtils.getFormattedDateOracle(opDate)+"~"+userId+"~"+userDesc+"~"+status;

		Result objResult = masterDao.insertUpdateRecord(procName, colName, colValue);
		
		String msg = "";
		if(objResult.getStatus().equals("success")){
			if(opFlag.equals("D")){
				msg = "Record deleted successfully.";
			}else if(opFlag.equals("M")){
				msg = "Record updated successfully.";
			}else{
				msg = "Record saved successfully.";
			}
		}else{
			msg = objResult.getMsg();
		}
		objResult.setMsg(msg);
		return objResult;
	}
}

