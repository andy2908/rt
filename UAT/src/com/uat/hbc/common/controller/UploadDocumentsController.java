package com.uat.hbc.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.model.User;

@Controller
public class UploadDocumentsController {

	@RequestMapping("user/uploadDocuments")
	public ModelAndView loginPage(HttpServletRequest request,
			HttpServletResponse res) {
		
		HttpSession session = request.getSession();
		
		ModelAndView model = new ModelAndView("common/uploadDocuments");
		
		User user = (User)session.getAttribute("user");
		
		model.addObject("userId", user.getUserId());
		
		return model;

	}

	
}
