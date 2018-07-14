package com.uat.hbc.insurance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TravelController {
	@RequestMapping("/travel")
	public ModelAndView loginPage(HttpServletRequest request,
			HttpServletResponse res) {
		System.out.println("travel");
		return new ModelAndView("common/travel");

	}
}