package com.uat.hbc.insurance;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.model.User;

@Controller
public class MotorController {

	@RequestMapping("/motor")  
    public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse res) {  
    	ModelAndView model = new ModelAndView();
    	model.setViewName("common/integrationMotorCalulation");
    	
    	return model;
    }
	

	
	@RequestMapping("/loginMotorModel")
	public ModelAndView loadMotorLogin(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/motorLogin");

		return model;
	}
	
	@RequestMapping(value = "user/updatePaymentStatus", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getParameters(
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
		String paymentStatus="";
	String payStatus = request.getParameter("status");
//	System.out.println("payStatus:::"+ payStatus);
	HttpSession httpSession = request.getSession();
	
	User user = (User) httpSession.getAttribute("user");
	
	user.setPaymentStatus(payStatus);
//	System.out.println("user::"+ user.getPaymentStatus());
	
			paymentStatus ="[{\"status\":\"" +user.getPaymentStatus()+"\"}]"; 
	return paymentStatus;
	}
	
	
	@RequestMapping(value = "user/getPaymentStatus", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String getPaymentStatus(
			HttpServletRequest request, HttpServletResponse response)
			throws JDOMException, IOException {
	String payResponse="";
	
	HttpSession httpSession = request.getSession();
	
	User user = (User) httpSession.getAttribute("user");
//	System.out.println("user::"+ user);
	String status = user.getPaymentStatus();
	
	payResponse ="[{\"status\":\"" +status+"\"}]"; 
		return payResponse;
	}
	
}
