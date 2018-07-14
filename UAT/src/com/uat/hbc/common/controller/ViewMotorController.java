package com.uat.hbc.common.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.dao.ViewMotorDao;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.User;
import com.uat.hbc.common.model.ViewMotorModel;

@Controller
public class ViewMotorController {
	
	@Autowired
	ViewMotorDao viewMotorApplicationDao;
	
	@RequestMapping("user/viewMotor")  
    public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse res) {
		
		HttpSession httpSession = request.getSession();
		
		User user = (User) httpSession.getAttribute("user");
		
    	ModelAndView model = new ModelAndView();
    	model.addObject("masterType", user.getMasterType());
    	model.addObject("userId", user.getUserId());
    	model.setViewName("common/viewMotor");
    	
    	return model;
    }

	@RequestMapping(value = "user/ViewMotorApplicationFetch", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String PaymentStatusFetch(HttpServletRequest request,
			HttpServletResponse res) {
		String objResult = null;
		HttpSession session = request.getSession();
		String find = request.getParameter("find");
		int appID = Integer.parseInt(request.getParameter("appID"));
//		int policytype = Integer.parseInt(request.getParameter("policytype"));
		String prodId = request.getParameter("prodId");
		String gicId = request.getParameter("gicId");
		String prpslID = request.getParameter("prpslID");
		String applicantID = request.getParameter("applicantID");
		String frmDt = request.getParameter("frmDt");
		String toDt = request.getParameter("toDt");
		String startDt = request.getParameter("startDt");
		String endDt = request.getParameter("endDt");
		String hbbID = request.getParameter("hbbID");
		String appStatus = request.getParameter("appStatus");
		String payMode = request.getParameter("payMode");
		int branch_id = Integer.parseInt(request.getParameter("branch_mst_id"));
		int user_id = Integer.parseInt(request.getParameter("userid"));
		
		User user = (User)session.getAttribute("user");
		String userdesc = user.getUserDesc();

		System.out.println("find==" + find);
		System.out.println("appID==" + appID);
//		System.out.println("policytype==" + policytype);
		System.out.println("prodId==" + prodId);
		System.out.println("gicId==" + gicId);
		System.out.println("prpslID==" + prpslID);
		System.out.println("applicantID==" + applicantID);
		System.out.println("frmDt==" + frmDt);
		System.out.println("toDt==" + toDt);
		System.out.println("startDt==" + startDt);
		System.out.println("endDt==" + endDt);
		System.out.println("hbbID==" + hbbID);
//		System.out.println("appStatus==" + appStatus);
		System.out.println("payMode==" + payMode);
		System.out.println("branch_id==" + branch_id);
		System.out.println("user_id==" + user_id);
		System.out.println("userdesc==" + userdesc);
		System.out.println("You Are In Fetch Controller....");
		// ///////////////////////////////////////////////////////////

		int SrNo = 1;

		if (gicId != null) {
			String gicIdArr[] = gicId.split(",");
			String applicantIDArr[] = applicantID.split(",");
			String prodIdArr[] = prodId.split(",");
//			String appStatusArr[] = appStatus.split(",");
			ViewMotorModel viewMotorApplicationModel = new ViewMotorModel();
			viewMotorApplicationModel.setFind(find);
			viewMotorApplicationModel.setAppId(appID);
//			viewMotorApplicationModel.setPolType(policytype);
			viewMotorApplicationModel.setPrpsl(prpslID);
			viewMotorApplicationModel.setFrDt(frmDt);
			viewMotorApplicationModel.setToDt(toDt);
			viewMotorApplicationModel.setStartDt(startDt);
			viewMotorApplicationModel.setEndDt(endDt);
			viewMotorApplicationModel.setHbbId(hbbID);
//			viewMotorApplicationModel.setAppStatus(appStatus);
			viewMotorApplicationModel.setPayMode(payMode);

			ArrayList<ViewMotorModel> viewMotorApplicationList = new ArrayList<ViewMotorModel>();
			for (int i = 0; i < gicIdArr.length; i++) {
				viewMotorApplicationModel.setGicId(gicIdArr[i]);
				System.out.println("gicIdArr[i]--->>" + gicIdArr[i]);

				for (int j = 0; j < applicantIDArr.length; j++) {
					viewMotorApplicationModel.setApplicantId(applicantIDArr[j]);
					System.out.println("applicantIDArr[j]--->>"
							+ applicantIDArr[j]);

					for (int k = 0; k < prodIdArr.length; k++) {
						viewMotorApplicationModel.setProdId(prodIdArr[k]);
						System.out.println("prodIdArr[k]--->>" + prodIdArr[k]);

//						for (int l = 0; l < appStatusArr.length; l++) {
//							viewMotorApplicationModel
//									.setAppStatus(appStatusArr[l]);
//							System.out.println("appStatusArr[l]--->>"
//									+ appStatusArr[l]);

							String fromDate = frmDt;
							if (fromDate.equals("")) {
								fromDate = "";
								System.out.println("fromDate if empty--"
										+ fromDate);
							} else {
								fromDate = viewMotorApplicationModel.getFrDt();
								System.out.println("fromDate if selected--"
										+ fromDate);
							}

							String toDate = toDt;
							if (toDate.equals("")) {
								toDate = "";
								System.out
										.println("toDate if empty--" + toDate);
							} else {
								toDate = viewMotorApplicationModel.getToDt();
								System.out.println("toDate if selected--"
										+ toDate);
							}

							String startDate = startDt;
							if (startDate.equals("")) {
								startDate = "";
								System.out.println("startDate if empty--"
										+ startDate);
							} else {
								startDate = viewMotorApplicationModel
										.getStartDt();
								System.out.println("startDate if selected--"
										+ startDate);
							}

							objResult = viewMotorApplicationDao.searchData(
									viewMotorApplicationModel.getFind(),
									viewMotorApplicationModel.getAppId(),
									viewMotorApplicationModel.getPolType(),
									viewMotorApplicationModel.getProdId(),
									viewMotorApplicationModel.getGicId(),
									viewMotorApplicationModel.getPrpsl(),
									viewMotorApplicationModel.getApplicantId(),
									fromDate, toDate, startDate,
									viewMotorApplicationModel.getEndDt(),
									viewMotorApplicationModel.getHbbId(),
									viewMotorApplicationModel.getAppStatus(),
									viewMotorApplicationModel.getPayMode(),
									branch_id, user_id, userdesc);
							System.out.println("searchResult--->>" + objResult);

						}
					}
				}
			
		}
		// ///////////////////////////////////////////////////////////
		// String searchResult = new String();
		//
		// searchResult=viewMotorApplicationDao.searchData(find, appID,
		// policytype, prodId, gicId, prpslID,applicantID, frmDt, toDt, startDt,
		// endDt, hbbID, appStatus, payMode, branch_id, user_id, userdesc);
		//
		// return searchResult;
		return objResult;
	}
	

}
