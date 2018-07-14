package com.uat.hbc.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.uat.hbc.common.PasswordGenerator;
import com.uat.hbc.common.PasswordManager;
import com.uat.hbc.common.dao.MasterDao;
import com.uat.hbc.common.model.Result;

@Controller
public class RegisterController {

	@Autowired
	MasterDao masterDao;
	
	@RequestMapping(value = "/registerModel")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse res){
		
		return new ModelAndView("common/register");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result saveDetails(HttpServletRequest request, HttpServletResponse res) {
		
		HttpSession session = request.getSession();
		
		String userId = request.getParameter("user_id");
		String opFlag = request.getParameter("opflag");
		String mobNo = request.getParameter("mob_no");
		String userName = request.getParameter("user_name");
		String userNameOl = request.getParameter("user_name_ol");
		String middleName = request.getParameter("middle_name");
		String middleNameOl = request.getParameter("middle_name_ol");
		String lastName = request.getParameter("last_name");
		String lastNameOl = request.getParameter("last_name_ol");
		String emailId = request.getParameter("email_id");
		String branchId = request.getParameter("branch_id");
		String entryDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String opDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String userDesc = request.getParameter("user_desc");
		String status = request.getParameter("status");

		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true)
				.useLower(true).useUpper(true).usePunctuation(true).build();

		String password = passwordGenerator.generate(8);

		try {
			password = new PasswordManager().encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("password::" + password);

		String procName = "PR_OL_USER_MASTER";

		String colName = "pio_user_id~pi_opflag~pi_user_code~pi_user_name~pi_user_name_ol~pi_middle_name~pi_middle_name_ol~pi_last_name~pi_last_name_ol~pi_email_id~pi_mob_no~pi_password~pi_branch_id~pi_entry_date~pi_op_date~pi_user_id~pi_user_desc~pi_status";

		String colValue = userId + "~" + opFlag + "~" + mobNo + "~" + userName + "~"
				+ userNameOl + "~" + middleName + "~" + middleNameOl + "~" + lastName + "~" + lastNameOl + "~" + emailId + "~" + mobNo
				+ "~" + password + "~" + branchId + "~" + AllUtils.getFormattedDateOracle(entryDate) + "~"
				+ AllUtils.getFormattedDateOracle(opDate) + "~" + userId + "~" + userDesc + "~" + status;

		System.out.println("colValue"+colValue);
		// Result objResult=new Result();
		Result objResult = masterDao.insertUpdateRecord(procName, colName, colValue);

		String msg = "";
		if (objResult.getStatus().equals("success")) {
			if (opFlag.equals("D")) {
				msg = "Record deleted successfully.";
			} else if (opFlag.equals("M")) {
				msg = "Record updated successfully.";
			} else {
				msg = "Registered Successfully";
			}
		} else {
			msg = objResult.getMsg();
		}
		objResult.setMsg(msg);
//		System.out.println("msg : "+msg);
		return objResult;
	}

}
