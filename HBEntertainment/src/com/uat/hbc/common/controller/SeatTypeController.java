package com.uat.hbc.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.dao.MasterDao;
import com.uat.hbc.common.AllUtils;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.User;

@Controller
public class SeatTypeController {
	@Autowired
	MasterDao masterDao;

	@RequestMapping("user/seatType")  
    public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse res) {  
    	ModelAndView model = new ModelAndView();
    	model.setViewName("common/seatType");
    	return model;
	}
	@RequestMapping(value = "user/seatTypeEntry", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String saveDetails(HttpServletRequest request,HttpServletResponse res) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String user_id = user.getUserId();
		String branch_id = user.getBranchId();
		//System.out.println("111111111111111111111111111111111");
		String seattype_id = request.getParameter("pio_seat_type_id");
		System.out.println("seattype_id=="+seattype_id);
		String opflag = request.getParameter("pi_opflag");
		System.out.println("opflag=="+opflag);
		String seat_type_code=request.getParameter("pi_seat_type_code");
		System.out.println("seat_type_code=="+seat_type_code);
		String seat_type_name=request.getParameter("pi_seat_type_name");
		String seat_type_name_ol =request.getParameter("pi_seat_type_name_ol");
		String strId=request.getParameter("pi_str_id");
		/*int branch_id =Integer.parseInt(request.getParameter("pi_branch_id"));*/
		String entry_date=request.getParameter("pi_entry_date");
		String op_date =request.getParameter("pi_op_date");
		/*int user_id =Integer.parseInt(request.getParameter("pi_user_id"));*/
		String user_desc=request.getParameter("pi_user_desc");
		String status =request.getParameter("pi_status");
		
		
		
		
		
		System.out.println("seat_type_name=="+seat_type_name);
		System.out.println("seat_type_name_ol=="+seat_type_name_ol);
		System.out.println("strId=="+strId);
		System.out.println("branch_id=="+branch_id);
		System.out.println("entry_date=="+entry_date);
		System.out.println("op_date=="+op_date);
		System.out.println("user_id=="+user_id);
		System.out.println("user_desc=="+user_desc);
		System.out.println("status=="+status);
		
		
		String procName ="PR_ENT_SEAT_TYPE";

        String colName="PIO_SEAT_TYPE_ID~PI_OpFlag~PI_SEAT_TYPE_CODE~PI_SEAT_TYPE_NAME~PI_SEAT_TYPE_NAME_OL~PI_STR_ID~PI_BRANCH_ID~PI_entry_date~PI_op_date~PI_user_id~PI_user_desc~PI_status";
        
        String colValue = Integer.parseInt(seattype_id)+"~"+opflag+"~"+seat_type_code+"~"+seat_type_name+"~"+seat_type_name_ol+"~"+strId+"~"+Integer.parseInt(branch_id)+"~"+AllUtils.getFormattedDateOracle(entry_date)+"~"+AllUtils.getFormattedDateOracle(op_date)+"~"+Integer.parseInt(user_id)+"~"+user_desc+"~"+status;

		System.out.println(colValue);
//			Result objResult=new Result();
		Result objResult = masterDao.insertUpdateRecord(procName, colName, colValue);
		
		String msg = "";
		if( objResult.getStatus().equals("success")){
			if(opflag.equals("D")){
				msg = "Record deleted successfully.";
			}else if(opflag.equals("M")){
				msg = "Record updated successfully.";
			}else{
				msg = "Record saved successfully.";
			}
		}else{
			msg = objResult.getMsg();
		}
		objResult.setMsg(msg);
		System.out.println("Message"+msg);
		return msg;
	}
	}


