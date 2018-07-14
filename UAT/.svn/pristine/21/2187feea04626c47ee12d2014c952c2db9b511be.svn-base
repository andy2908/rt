package com.uat.hbc.fin.controller;

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
import com.uat.hbc.common.model.Result;
import com.uat.hbc.fin.dao.ViewLedgerDao;



@Controller
public class ViewLedgerController {
	@Autowired
	private ViewLedgerDao viewledgerDao;
	
	@RequestMapping("user/viewledger")
	public ModelAndView loginPage(HttpServletRequest request,
			HttpServletResponse res) {
		System.out.println("travel");
		return new ModelAndView("common/viewledger");
	}
	
	@RequestMapping(value = "user/fetchViewLedgerData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String saveDetails(HttpServletRequest request,HttpServletResponse res) {
		
		HttpSession session = request.getSession();
	    
                 
		String procName = request.getParameter("procName");
		String lType = request.getParameter("lType");
		String find = request.getParameter("find");
		String glId = request.getParameter("glId");
		String status = request.getParameter("status");
		String vType = request.getParameter("vType");
		String strLid = request.getParameter("strLid");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		
		System.out.println("procName-->>"+procName);
		System.out.println("lType-->>"+lType);
		System.out.println("find-->>"+find);
		System.out.println("glId-->>"+glId);
		System.out.println("status-->>"+status);
		System.out.println("vType-->>"+vType);
		System.out.println("strLid-->>"+strLid);
		System.out.println("fromDate-->>"+fromDate);
		System.out.println("toDate-->>"+toDate);
		
		String searchResult = viewledgerDao.searchData(procName,lType,find,glId,status,vType,strLid,AllUtils.getFormattedDateOracle(fromDate),AllUtils.getFormattedDateOracle(toDate));
		System.out.println("SEARCHRESULT-->>" + searchResult);
		return searchResult;
	}
}
