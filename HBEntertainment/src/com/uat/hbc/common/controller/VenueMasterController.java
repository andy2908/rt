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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uat.hbc.common.Utils;
import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.model.User;
import com.uat.hbc.common.service.VenueMasterService;

@Controller
public class VenueMasterController {
	@Autowired
	VenueMasterService venueMasterService;
	private Properties props;
	@RequestMapping("user/venueMaster")  
    public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse res) {  
    	ModelAndView model = new ModelAndView();
    	model.setViewName("common/venueMaster");
    	return model;
	    }
	
	@RequestMapping(value = "user/venueMasterController", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result UploadAttLog(HttpServletRequest request, HttpServletResponse res) {
		HttpSession session = request.getSession();
		   try {
	 			props = Utils.readProperties("datasource.properties");
	 		} catch (IOException e1) {
	 			e1.printStackTrace();
	 		}
			
		User user = (User)session.getAttribute("user");
		byte[] eventMapByteArr1 = null;
		String opFlag = "";
		String commonTrno = null;
		String venueHeadId = null;
		String venueName = null;
		String phoneNo = null;
		String mobileNo1 = null;
		String mobileNo2 = null;
		String emailId = null;
		String website = null;
		String venueTypeId = null;
		String venueStructure = null;
		String totalSeats = null;
		String adrName = null;
		String areaId = null;
		String house = null;
		String houseId = null;
		String street = null;
		String streetId = null;
		String landmark = null;
		String landmarkId = null;
		String srno = null;
		String venueLineId = null;
		String rowNo = null;
		String seatNo = null;
		String seatStatus = null;
		String seatTypeId = null;
		String rowSeatStr = null;
		String strId = null;
		String branchId = user.getBranchId();
		String userId = user.getUserId();
		String userDesc = user.getUserDesc();
		
		 ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());       
	     JSONArray json = new JSONArray();
	     try {
	        	String filename ="";
	            List<FileItem> items = uploadHandler.parseRequest(request);
	            
	            System.out.println("items"+items.size());
	            for (FileItem item : items) {           	
	                if (item.isFormField()) {
	                	if(item.getFieldName().equals("commonTrno")){
	                		commonTrno  = item.getString();
	                		System.out.println("commonTrno  : "+item.getString());
	                	}else if(item.getFieldName().equals("venueHeadId")){
	                		venueHeadId  = item.getString();
	                		System.out.println("venueHeadId  : "+item.getString());
	                	}else if(item.getFieldName().equals("venueName")){
	                		venueName  = item.getString();
	                		System.out.println("venueName  : "+item.getString());
	                	}else if(item.getFieldName().equals("phoneNo")){
	                		phoneNo  = item.getString();
	                		System.out.println("phoneNo  : "+item.getString());
	                	}else if(item.getFieldName().equals("mobileNo1")){
	                		mobileNo1  = item.getString();
	                		System.out.println("mobileNo1  : "+item.getString());
	                	}else if(item.getFieldName().equals("mobileNo2")){
	                		mobileNo2  = item.getString();
	                		System.out.println("mobileNo2  : "+item.getString());
	                	}else if(item.getFieldName().equals("emailId")){
	                		emailId  = item.getString();
	                		System.out.println("emailId  : "+item.getString());
	                	}else if(item.getFieldName().equals("website")){
	                		website  = item.getString();
	                		System.out.println("website : "+item.getString());
	                	}else if(item.getFieldName().equals("venueTypeId")){
	                		venueTypeId  = item.getString();
	                		System.out.println("venueTypeId  : "+item.getString());
	                	}else if(item.getFieldName().equals("venueStructure")){
	                		venueStructure  = item.getString();
	                		System.out.println("venueStructure  : "+item.getString());
	                	}else if(item.getFieldName().equals("totalSeats")){
	                		totalSeats  = item.getString();
	                		System.out.println("totalSeats  : "+item.getString());
	                	}else if(item.getFieldName().equals("adrName")){
	                		adrName = item.getString();
	                		System.out.println("adrName : "+item.getString());
	                	}else if(item.getFieldName().equals("areaId")){
	                		areaId = item.getString();
	                		System.out.println("areaId : "+item.getString());
	                	}else if(item.getFieldName().equals("house")){
	                		house = item.getString();
	                		System.out.println("house : "+item.getString());
	                	}else if(item.getFieldName().equals("houseId")){
	                		houseId = item.getString();
	                		System.out.println("houseId : "+item.getString());
	                	}else if(item.getFieldName().equals("street")){
	                		street = item.getString();
	                		System.out.println("street : "+item.getString());
	                	}else if(item.getFieldName().equals("streetId")){
	                		streetId  = item.getString();
	                		System.out.println("streetId : "+item.getString());
	                	}else if(item.getFieldName().equals("landmark")){
	                		landmark = item.getString();
	                		System.out.println("landmark : "+item.getString());
	                	}else if(item.getFieldName().equals("landmarkId")){
	                		landmarkId = item.getString();
	                		System.out.println("landmarkId : "+item.getString());
	                	}else if(item.getFieldName().equals("srno")){
                		srno = item.getString();
	                		
	                		System.out.println("srno : "+item.getString());
	                	}else if(item.getFieldName().equals("venueLineId")){
                		venueLineId = item.getString();
	                		
	                		System.out.println("venueLineId : "+item.getString());
	                	}else if(item.getFieldName().equals("rowNo")){
	                		rowNo  = item.getString();
	                		System.out.println("rowNo  : "+item.getString());
	                	}else if(item.getFieldName().equals("seatNo")){
	                		seatNo = item.getString();
	                		System.out.println("seatNo : "+item.getString());
	                	}else if(item.getFieldName().equals("seatStatus")){
	                		seatStatus = item.getString();
	                		System.out.println("seatStatus : "+item.getString());
	                	}else if(item.getFieldName().equals("seatTypeId")){
	                		seatTypeId = item.getString();
	                		System.out.println("seatTypeId : "+item.getString());
	                	}else if(item.getFieldName().equals("rowSeatStr")){
	                		rowSeatStr = item.getString();
	                		System.out.println("rowSeatStr : "+item.getString());
	                	}else if(item.getFieldName().equals("strId")){
	                		strId = item.getString();
	                		System.out.println("strId : "+item.getString());
	                	}else if(item.getFieldName().equals("opFlag")){
	                		opFlag = item.getString();
	                		System.out.println("opFlag========== : "+item.getString());
	                	}
	                	
	                	filename = item.getString();
	                }else if(!item.isFormField()){
	                	System.out.println("file name" + item.getName());
						
						 if(item.getFieldName().equals("fileLoader")){
							InputStream is = item.getInputStream();
							eventMapByteArr1 = new byte[is.available()];
							is.read(eventMapByteArr1);
							File file = new File(props.getProperty("server.path")+"/images/"+item.getName());
							file.createNewFile();
							System.out.println("file:  "+file);
							FileOutputStream fileOutputStream = new FileOutputStream(file);
							
							fileOutputStream.write(eventMapByteArr1);
							fileOutputStream.flush();
							fileOutputStream.close();
						}
						System.out.println("size::" + eventMapByteArr1.length);
						JSONObject jsono = new JSONObject();
						jsono.put("status", "success");
						jsono.put("size", item.getSize());
						json.put(jsono);
	                	///////////////////////////
	                }
	            }
	        } catch (FileUploadException e) {
	                e.printStackTrace();
	        } catch (Exception e) {
	        		e.printStackTrace();
	        } 
		
		Result resObj =venueMasterService.insertUpdatePARecordPKGwithArray(opFlag, commonTrno,
				venueHeadId, venueName, phoneNo, mobileNo1, mobileNo2, emailId, website,
				venueTypeId, venueStructure, totalSeats, eventMapByteArr1, adrName, areaId, 
				house, houseId, street, streetId, landmark, landmarkId, srno, 
				venueLineId, rowNo, seatNo, seatStatus, seatTypeId, rowSeatStr,strId, 
				branchId, userId, userDesc);
		
		return resObj;
	}
}
