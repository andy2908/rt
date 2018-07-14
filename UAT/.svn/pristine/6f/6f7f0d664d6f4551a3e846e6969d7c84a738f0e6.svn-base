package com.uat.hbc.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.PasswordManager;
import com.uat.hbc.common.dao.MasterDao;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.User;

@Controller
public class LoginController {
	
	@Autowired
	MasterDao masterDao;

	@RequestMapping(value = "/loginValidation", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result saveDetails(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String userId = request.getParameter("userid");
		
		userId = userId.trim();
		
		String passWord = request.getParameter("password");

		passWord = passWord.replaceAll(" ", "+");

		if (userId != null) {

			session.setAttribute("userid", userId);

			session.setAttribute("password", passWord);
		}

		PasswordManager pwm = new PasswordManager();

		String encryptedPassword = pwm.encrypt(passWord);

		String procName = "PR_VALIDATE_USER";

		String colName = "PI_USER_CODE~PI_PASSWORD";

		String colValue = userId + "~" + encryptedPassword;

		Result objResult = masterDao.insertUpdateRecord(procName, colName, colValue);

		String msg = "";
		JSONArray array;
		User user = new User();
		
		if (objResult.getStatus().equals("success")) {
			msg = "Logged In Successfully!!";
			try {
				array = new JSONArray(objResult.getJsonValues());
				JSONObject jsonObject = array.getJSONObject(0);
				user.setUserId(jsonObject.getString("AD_USER_ID"));
				user.setUserCode(jsonObject.getString("USER_CODE"));
				user.setUserName(jsonObject.getString("USER_NAME"));
				user.setUserNameOL(jsonObject.getString("USER_NAME_OL"));
//				user.setDeptId(jsonObject.getString("DEPARTMENT_ID"));
//				user.setMobileNo_P(jsonObject.getString("P_MOBILE_NO"));
//				user.setMobileNo_O(jsonObject.getString("O_MOBILE_NO"));
//				user.setEmailId_P(jsonObject.getString("P_EMAIL_ID"));
//				user.setEmailId_O(jsonObject.getString("O_EMAIL_ID"));
				user.setMasterType(jsonObject.getString("HB_MASTER_TYPE"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			session.setAttribute("user", user);
		} else {
			System.out.println("status msg:::" + objResult.getMsg());
			msg = "Please Check Credentials and Login Again.";
		}
		objResult.setMsg(msg);

		return objResult;
	}

	@RequestMapping("/")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/index");

		return model;
	}

	@RequestMapping("/welcome")
	public ModelAndView admin(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/admin");

		return model;
	}

	@RequestMapping("/loginModel")
	public ModelAndView loadLogin(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/login");

		return model;
	}
	
	@RequestMapping("/agentLoginModel")
	public ModelAndView loadAgentLogin(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/agentLogin");

		return model;
	}

	@RequestMapping("/page404")
	public ModelAndView page404(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/errorpage");

		return model;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse res) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
	}

	@RequestMapping(value = "/checkUserLogin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String checkLoginStatus(HttpServletRequest request, HttpServletResponse res) {
		String loginStatus="";
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if(user == null){
			loginStatus="[{\"loginStatus\":"+ "\"no\"}]";
		}else{
			loginStatus="[{\"loginStatus\":"+ "\"yes\"}]";
		}
		return loginStatus;
	}
	
}
