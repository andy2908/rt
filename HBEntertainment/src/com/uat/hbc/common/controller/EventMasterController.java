package com.uat.hbc.common.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.User;
import com.uat.hbc.common.service.EventMasterService;
import com.uat.hbc.common.*;

@Controller
public class EventMasterController {
	
	private Properties props;
	
	@Autowired
	EventMasterService eventMasterService;

	@RequestMapping("user/eventMaster")
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/eventMaster");
		return model;
	}

	/*@RequestMapping(value = "user/saveEventMaster", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result UploadAttLog(HttpServletRequest request, HttpServletResponse res) {
		String opflag = request.getParameter("opflag");
		String common_tron = request.getParameter("common_tron");
		String event_head_id = request.getParameter("event_head_id");
		String event_name = request.getParameter("event_name");
		String phone_no = request.getParameter("phone_no");
		String mobile_no_1 = request.getParameter("mobile_no_1");
		String mobile_no_2 = request.getParameter("mobile_no_2");
		String email_id = request.getParameter("email_id");
		String website = request.getParameter("website");
		String venue_head_id = request.getParameter("venue_head_id");
		String venue_structure = request.getParameter("venue_structure");
		String total_seats = request.getParameter("total_seats");
		String venue_map = request.getParameter("venue_map");
		String navigation_img = request.getParameter("navigation_img");
		String adr_name = request.getParameter("adr_name");
		String area_id = request.getParameter("area_id");
		String house = request.getParameter("house");
		String house_id = request.getParameter("house_id");
		String street = request.getParameter("street");
		String street_id = request.getParameter("street_id");
		String landmark = request.getParameter("landmark");
		String landmark_id = request.getParameter("landmark_id");
		String srNo = request.getParameter("srNo");
		String event_line_id = request.getParameter("event_line_id");
		String row_no = request.getParameter("row_no");
		String from_seat = request.getParameter("from_seat");
		String to_seat = request.getParameter("to_seat");
		String seat_no = request.getParameter("seat_no");
		String seat_status = request.getParameter("seat_status");
		String seat_type_id = request.getParameter("seat_type_id");
		String row_seat_str = request.getParameter("row_seat_str");
		String online_tran_id = request.getParameter("online_tran_id");
		String pg_train_status = request.getParameter("pg_train_status");
		String pg_amount = request.getParameter("pg_amount");
		String pg_tran_no = request.getParameter("pg_tran_no");
		String pg_tran_date = request.getParameter("pg_tran_date");
		String pg_tran_time = request.getParameter("pg_tran_time");
		String book_by_user_id = request.getParameter("book_by_user_id");
		String book_by_name = request.getParameter("book_by_name");
		String book_person_mobileNo = request.getParameter("book_person_mobileNo");
		String agent_id = request.getParameter("agent_id");
		String branch_id = request.getParameter("branch_id");
		String user_id = request.getParameter("user_id");
		String user_desc = request.getParameter("user_desc");

		System.out.println(" opflag controll===========" + opflag);
		System.out.println(" common_tron controll===========" + common_tron);
		System.out.println(" event_head_id controll===========" + event_head_id);
		System.out.println(" event_name controll===========" + event_name);
		System.out.println(" phone_no controll===========" + phone_no);
		System.out.println(" mobile_no_1 controll===========" + mobile_no_1);
		System.out.println(" mobile_no_2 controll===========" + mobile_no_2);
		System.out.println(" email_id controll===========" + email_id);
		System.out.println(" website controll===========" + website);
		System.out.println(" venue_head_id controll===========" + venue_head_id);
		System.out.println(" venue_structure controll===========" + venue_structure);
		System.out.println(" total_seats controll===========" + total_seats);
		System.out.println(" venue_map controll===========" + venue_map);
		System.out.println(" navigation_img controll===========" + navigation_img);
		System.out.println(" adr_name controll===========" + adr_name);
		System.out.println(" area_id controll===========" + area_id);
		System.out.println(" house controll===========" + house);
		System.out.println(" house_id controll===========" + house_id);
		System.out.println(" street controll===========" + street);
		System.out.println(" street_id controll===========" + street_id);
		System.out.println(" landmark controll===========" + landmark);
		System.out.println(" landmark_id controll===========" + landmark_id);
		System.out.println(" srNo controll===========" + srNo);
		System.out.println(" event_line_id controll===========" + event_line_id);
		System.out.println(" pg_train_status controll===========" + pg_train_status);
		System.out.println(" pg_amount controll===========" + pg_amount);
		System.out.println(" pg_tran_no controll===========" + pg_tran_no);
		System.out.println(" pg_tran_date controll===========" + pg_tran_date);
		System.out.println(" pg_tran_time controll===========" + pg_tran_time);
		System.out.println(" book_by_user_id controll===========" + book_by_user_id);
		System.out.println(" book_by_name controll===========" + book_by_name);
		System.out.println(" book_person_mobileNo controll===========" + book_person_mobileNo);
		System.out.println(" agent_id controll===========" + agent_id);
		System.out.println(" user_id controll===========" + user_id);
		System.out.println(" user_desc controll===========" + user_desc);

	

		Result resObj = eventMasterService.insertUpdatePARecordPKGwithArray(opflag, common_tron, event_head_id,
				event_name, phone_no, mobile_no_1, mobile_no_2, email_id, website, venue_head_id, venue_structure,
				total_seats, eventMapByteArr, navigation_img, adr_name, area_id, house, house_id, street, street_id,
				landmark, landmark_id, srNo, event_line_id, row_no, from_seat, to_seat, seat_no, seat_status,
				seat_type_id, row_seat_str, online_tran_id, pg_train_status, pg_amount, pg_tran_no, pg_tran_date,
				pg_tran_time, book_by_user_id, book_by_name, book_person_mobileNo, agent_id, branch_id, user_id,
				user_desc);

		return resObj;
	}*/

	@RequestMapping(value = "/user/saveEventMaster", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String saveEventMaster(HttpServletRequest request, HttpServletResponse res) throws JSONException {
         HttpSession session = request.getSession();
         
         try {
 			props = Utils.readProperties("datasource.properties");
 		} catch (IOException e1) {
 			e1.printStackTrace();
 		}
		
		User user = (User)session.getAttribute("user");
		String user_id = user.getUserId();
		String branch_id = user.getBranchId();
		String agent_id =user.getUserId();
		String book_by_user_id =user.getUserId();
		String user_desc = user.getUserDesc();
		System.out.println("user_id======="+user_id+"======user_desc=="+user_desc+"======branch_id=="+branch_id+"===agent_id=="+agent_id+"====book_by_user_id=="+book_by_user_id);
		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
		JSONArray json = new JSONArray();
		
		   
		String opflag="", common_tron="", event_head_id="", event_name="", phone_no="", mobile_no_1 = "", mobile_no_2="", email_id="", website="",
				venue_head_id="", venue_structure="", total_seats="",eventMapByteArr="",adr_name="",area_id="",house="",house_id="",street="",
				street_id="", landmark="",landmark_id="",str_id="",srNo="",event_line_id="",row_no="", from_seat="",to_seat="",seat_no="",seat_status="",
				seat_type_id="", row_seat_str="",online_tran_id="",pg_train_status="",pg_amount="",pg_tran_no="",pg_tran_date="",pg_tran_time="",
				 book_by_name="", book_person_mobileNo="", str_row_no="" ;
		byte[] eventMapByteArr1 = null;
		byte[] eventMapByteArr2 = null;
		 String navigation_img = "";
		try {
			String filename = "";
			List<FileItem> items = uploadHandler.parseRequest(request);

			for (FileItem item : items) {
				if (item.isFormField()) {
					if (item.getFieldName().equals("opflag")) {
						opflag = item.getString();
						System.out.println("opFlag : " + opflag);
					} else if (item.getFieldName().equals("common_tron")) {
						common_tron = item.getString();
						System.out.println("common_tron : " + common_tron);
					} else if (item.getFieldName().equals("event_head_id")) {
						event_head_id = item.getString();
						System.out.println("event_head_id : " + event_head_id);
					} else if (item.getFieldName().equals("event_name")) {
						event_name = item.getString();
						System.out.println("event_name : " + event_name);
					} else if (item.getFieldName().equals("phone_no")) {
						phone_no = item.getString();
						System.out.println("phone_no : " + phone_no);
					} else if (item.getFieldName().equals("mobile_no_1")) {
						mobile_no_1 = item.getString();
						System.out.println("mobile_no_1 : " + mobile_no_1);
					} else if (item.getFieldName().equals("mobile_no_2")) {
						mobile_no_2 = item.getString();
						System.out.println("mobile_no_2 : " + mobile_no_2);
					} else if (item.getFieldName().equals("email_id")) {
						email_id = item.getString();
						System.out.println("email_id : " + email_id);
					} else if (item.getFieldName().equals("website")) {
						website = item.getString();
						System.out.println("website : " + website);
					} else if (item.getFieldName().equals("venue_head_id")) {
						venue_head_id = item.getString();
						System.out.println("venue_head_id : " + venue_head_id);
					} else if (item.getFieldName().equals("venue_structure")) {
						venue_structure = item.getString();
						System.out.println("venue_structure : " + venue_structure);
					} else if (item.getFieldName().equals("total_seats")) {
						total_seats = item.getString();
						System.out.println("total_seats : " + total_seats);
					} else if (item.getFieldName().equals("navigation_img")) {
						navigation_img = item.getString();
						System.out.println("navigation_img : " + navigation_img);
					} else if (item.getFieldName().equals("adr_name")) {
						adr_name = item.getString();
						System.out.println("adr_name : " + adr_name);
					} else if (item.getFieldName().equals("area_id")) {
						area_id = item.getString();
						System.out.println("area_id : " + area_id);
					} else if (item.getFieldName().equals("house")) {
						house = item.getString();
						System.out.println("house : " + house);
					} else if (item.getFieldName().equals("house_id")) {
						house_id = item.getString();
						System.out.println("house_id : " + house_id);
					}else if (item.getFieldName().equals("street")) {
						street = item.getString();
						System.out.println("street : " + street);				
					}else if (item.getFieldName().equals("street_id")) {
						street_id = item.getString();
						System.out.println("street_id : " + street_id);
					}else if (item.getFieldName().equals("landmark")) {
						landmark = item.getString();
						System.out.println("landmark : " + landmark);
					}else if (item.getFieldName().equals("landmark_id")) {
						landmark_id = item.getString();
						System.out.println("landmark_id : " + landmark_id);
					}else if(item.getFieldName().equals("str_id")){
						str_id = item.getString();
						System.out.println("str_id : " + str_id);
					}else if (item.getFieldName().equals("srNo")) {
						srNo = item.getString();
						System.out.println("srNo : " + srNo);
					}else if (item.getFieldName().equals("event_line_id")) {
						event_line_id = item.getString();
						System.out.println("event_line_id : " + event_line_id);
					}else if (item.getFieldName().equals("row_no")) {
						row_no = item.getString();
						System.out.println("row_no : " + row_no);
					}else if (item.getFieldName().equals("from_seat")) {
						from_seat = item.getString();
						System.out.println("from_seat : " + from_seat);
					}else if (item.getFieldName().equals("to_seat")) {
						to_seat = item.getString();
						System.out.println("to_seat : " + to_seat);
					}	/*else if (item.getFieldName().equals("seat_no")) {
						seat_no = item.getString();
							System.out.println("seat_no : " + seat_no);
						
					}*/else if (item.getFieldName().equals("seat_status")) {
						seat_status = item.getString();
						System.out.println("seat_status : " + seat_status);
					}else if (item.getFieldName().equals("seat_type_id")) {
						seat_type_id = item.getString();
						System.out.println("seat_type_id : " + seat_type_id);
					}else if (item.getFieldName().equals("row_seat_str")) {
						row_seat_str = item.getString();
						System.out.println("row_seat_str : " + row_seat_str);
					}else if (item.getFieldName().equals("online_tran_id")) {
						online_tran_id = item.getString();
						System.out.println("online_tran_id : " + online_tran_id);
					}else if (item.getFieldName().equals("pg_train_status")) {
						pg_train_status = item.getString();
						System.out.println("pg_train_status : " + pg_train_status);
					}else if (item.getFieldName().equals("pg_amount")) {
						pg_amount = item.getString();
						System.out.println("pg_amount : " + pg_amount);
					}else if (item.getFieldName().equals("pg_tran_no")) {
						pg_tran_no = item.getString();
						System.out.println("pg_train_status : " + pg_tran_no);
					}
					else if (item.getFieldName().equals("pg_tran_date")) {
						pg_tran_date = item.getString();
						System.out.println("pg_tran_date : " + pg_tran_date);
					}else if (item.getFieldName().equals("pg_tran_time")) {
						pg_tran_time = item.getString();
						System.out.println("pg_tran_time : " + pg_tran_time);
					}/*else if (item.getFieldName().equals("book_by_user_id")) {
						book_by_user_id = item.getString();
						System.out.println("book_by_user_id : " + book_by_user_id);
					}*/else if (item.getFieldName().equals("book_by_name")) {
						book_by_name = item.getString();
						System.out.println("book_by_name : " + book_by_name);
					}else if (item.getFieldName().equals("book_person_mobileNo")) {
						book_person_mobileNo = item.getString();
						System.out.println("book_person_mobileNo : " + book_person_mobileNo);
					}/*else if (item.getFieldName().equals("agent_id")) {
						agent_id = item.getString();
						System.out.println("agent_id : " + agent_id);
					}*//*else if (item.getFieldName().equals("branch_id")) {
						branch_id = item.getString();
						System.out.println("branch_id : " + branch_id);
					}*/else if (item.getFieldName().equals("user_id")) {
						user_id = item.getString();
						System.out.println("user_id : " + user_id);
					}else if (item.getFieldName().equals("user_desc")) {
						user_desc = item.getString();
						System.out.println("user_desc : " + user_desc);
					}else if (item.getFieldName().equals("str_row_no")) {
						str_row_no = item.getString();
						System.out.println("str_row_no : " + str_row_no);
					}

					filename = item.getString();
				} 
				else if (!item.isFormField()) {
					System.out.println("file name" + item.getName());
					
					if(item.getFieldName().equals("selectedFile")){
						InputStream is = item.getInputStream();
						eventMapByteArr1 = new byte[is.available()];
						is.read(eventMapByteArr1);
					}else if(item.getFieldName().equals("selectedFile1")){
						InputStream is = item.getInputStream();
						eventMapByteArr2 = new byte[is.available()];
						is.read(eventMapByteArr2);
						File file = new File(props.getProperty("server.path")+"/images/"+item.getName());
						file.createNewFile();
						
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						
						fileOutputStream.write(eventMapByteArr2);
						fileOutputStream.flush();
						fileOutputStream.close();
					}
					
					
					/*byte[] eventMapByteArr = venue_map.getBytes();*/
					System.out.println("size::" + eventMapByteArr1.length);
					JSONObject jsono = new JSONObject();
					jsono.put("status", "success");
					jsono.put("size", item.getSize());
					json.put(jsono);
				}
				System.out.println();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("before ");
		
		Result resObj = eventMasterService.insertUpdatePARecordPKGwithArray(opflag, common_tron, event_head_id,
				event_name, phone_no, mobile_no_1, mobile_no_2, email_id, website, venue_head_id, venue_structure,
				total_seats, eventMapByteArr1, navigation_img, adr_name, area_id, house, house_id, street, street_id,
				landmark, landmark_id, str_id,srNo, event_line_id, row_no, from_seat, to_seat, seat_status,
				seat_type_id, row_seat_str, online_tran_id, pg_train_status, pg_amount, pg_tran_no, pg_tran_date,
				pg_tran_time, book_by_user_id, book_by_name, book_person_mobileNo, agent_id, branch_id, user_id,
				user_desc,str_row_no);
	
		 System.out.println("resObj:::"+resObj);

/* String jsonn = json+""+resObj;*/
 JSONObject jsono1 = new JSONObject();
	jsono1.put("OPId",  resObj.getOpId());
	jsono1.put("status",  resObj.getStatus());
	jsono1.put("msg",  resObj.getMsg());
	jsono1.put("errorMsg",  resObj.getErrorMsg());
	json.put(jsono1);
	
 System.out.println("jsonn===============jsonn======="+json);
		return json.toString();
	}
}
