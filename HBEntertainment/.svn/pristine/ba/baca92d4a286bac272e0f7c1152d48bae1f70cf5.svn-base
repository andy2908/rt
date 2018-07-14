package com.uat.hbc.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.model.User;

public class SessionInterceptor implements HandlerInterceptor  {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>"+request.getServletPath());
		 if(request.getServletPath().equals("/")||request.getServletPath().equals("/login")||request.getServletPath().equals("/sendEmailOtp")||request.getServletPath().equals("/verifyEmail")||request.getServletPath().equals("/verifyEmailOtp")
				 ||request.getServletPath().equals("/updateForgetPassword")||request.getServletPath().equals("/getMobileNoForOTP")||request.getServletPath().equals("/getStateList")||request.getServletPath().equals("/loginRegistration")
				 ||request.getServletPath().equals("/getDistrictList")||request.getServletPath().equals("/loginCodeCheck")||request.getServletPath().equals("/getPINDetailsLst")||request.getServletPath().equals("/json")){
			 System.out.println("in");
	            return true;
	        }else{
		System.out.println("pre-request");
		boolean status = true;
		HttpSession session = request.getSession();
		User userObj = (User) session.getAttribute("user");
		System.out.println(userObj);
		if(userObj == null){
			status = false;
			request.setAttribute("sess", "sess");
			request.getRequestDispatcher("/logout");
//			request.getRequestDispatcher("/").forward(request, response);
			//response.sendRedirect("/MSAMBERP/");
			
			return false;
		}
		
		return status;
	}
	}
	//override postHandle() and afterCompletion() 

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
