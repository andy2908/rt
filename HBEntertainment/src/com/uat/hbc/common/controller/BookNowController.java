package com.uat.hbc.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookNowController {

	@RequestMapping("/bookNow")  
    public ModelAndView page404(HttpServletRequest request,HttpServletResponse res) {  
    	ModelAndView model = new ModelAndView();
    	model.setViewName("common/bookNow");
    	
    	return model;
    }
}
