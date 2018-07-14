package com.uat.hbc.insurance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HealthController {
	@RequestMapping("/health")  
    public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse res) {  
    	ModelAndView model = new ModelAndView();
    	model.setViewName("common/health");
    	
    	return model;
    }
}
