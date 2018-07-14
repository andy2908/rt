package com.uat.hbc.common;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.uat.hbc.common.model.Result;
import com.uat.hbc.common.service.RegistrationService;

/*import com.common.model.Result;
import com.hb.insurance.service.CalculationDetailsArrayService;
import com.hb.insurance.service.ModGrpDiscPacRegOtherThanDetArrayService;
*/
@Controller
public class RegistrationController {
	@Autowired
	RegistrationService registrationService;

	@RequestMapping("/registrationModel")
	public ModelAndView callJSP(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model = new ModelAndView();
		String appId = req.getParameter("appId");
		model.addObject("appId", appId);
		model.setViewName("common/Registration");
		return model;
	}
	
//	@RequestMapping(value = "/saveData", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
//	public @ResponseBody Result controlPerm(HttpServletRequest request, HttpServletResponse res) {
//		System.out.println("REGISTRATION CONTROLLER");
//		HttpSession session = request.getSession();
//		
//		String procName = "PR_POS_MST";
//		String posMstID = request.getParameter("posMstID");
//		System.out.println("posMstID-->>> "+posMstID);
//		
//		String opflag = "N";
//		System.out.println("opflag-->>> "+opflag);
//		
//		String titleId = request.getParameter("titleId");
//		System.out.println("titleId-->>> "+titleId);
//		
////		String srno = request.getParameter("srno");
//		String firstName = request.getParameter("firstName");
//		System.out.println("firstName-->>> "+firstName);
//		
//		String middleName = request.getParameter("middleName");
//		System.out.println("middleName-->>> "+middleName);
//		
//		String lastName = request.getParameter("lastName");
//		System.out.println("lastName-->>> "+lastName);
//		
//		String firstNameOl = request.getParameter("firstNameOl");
//		System.out.println("firstNameOl-->>> "+firstNameOl);
//		
//		String middleNameOl = request.getParameter("middleNameOl");
//		System.out.println("middleNameOl-->>> "+middleNameOl);
//		
//		String lastNameOl = request.getParameter("lastNameOl");
//		System.out.println("lastNameOl-->>> "+lastNameOl);
//		
//		String fatherName = request.getParameter("fatherName");
//		System.out.println("fatherName-->>> "+fatherName);
//		
//		String motherName = request.getParameter("motherName");
//		System.out.println("motherName-->>> "+motherName);
//		
//		String gender = request.getParameter("gender");
//		System.out.println("gender-->>> "+gender);
//		
//		String maritalStatus = request.getParameter("maritalStatus");
//		System.out.println("maritalStatus-->>> "+maritalStatus);
//		
//		String birthDate = request.getParameter("birthDate");
//		System.out.println("birthDate-->>> "+birthDate);
//		
//		String religionId = request.getParameter("religionId");
//		System.out.println("religionId-->>> "+religionId);
//		
//		String categoryId = request.getParameter("categoryId");
//		System.out.println("categoryId-->>> "+categoryId);
//		
//		String educationId = request.getParameter("educationId");
//		System.out.println("educationId-->>> "+educationId);
//		
//		String mobNo = request.getParameter("mobNo");
//		System.out.println("mobNo-->>> "+mobNo);
//		
//		String phoneNo = request.getParameter("phoneNo");
//		System.out.println("phoneNo-->>> "+phoneNo);
//		
//		String emailId = request.getParameter("emailId");
//		System.out.println("emailId-->>> "+emailId);
//		
//		String adrType = request.getParameter("adrType");
//		System.out.println("adrType-->>> "+adrType);
//		
//		String areaId = request.getParameter("areaId");
//		System.out.println("areaId-->>> "+areaId);
//		
//		String house = request.getParameter("house");
//		System.out.println("house-->>> "+house);
//		
//		String houseId = request.getParameter("houseId");
//		System.out.println("houseId-->>> "+houseId);
//		
//		String street = request.getParameter("street");
//		System.out.println("street-->>> "+street);
//		
//		String streetId = request.getParameter("streetId");
//		System.out.println("streetId-->>> "+streetId);
//		
//		String landmark = request.getParameter("landmark");
//		System.out.println("landmark-->>> "+landmark);
//		
//		String landmarkId = request.getParameter("landmarkId");
//		System.out.println("landmarkId-->>> "+landmarkId);
//		
//		String uploadName = request.getParameter("uploadName");
//		System.out.println("uploadName-->>> "+uploadName);
//		
//		String docPath = request.getParameter("docPath");
//		System.out.println("docPath-->>> "+docPath);
//		
//		String docType = request.getParameter("docType");
//		System.out.println("docType-->>> "+docType);
//		
//		String ftpId = request.getParameter("ftpId");
//		System.out.println("ftpId-->>> "+ftpId);
//		
//		String strid = request.getParameter("strid");
//		System.out.println("strid-->>> "+strid);
//		
//		String branchid = request.getParameter("branchid");
//		
//		String userid = request.getParameter("userid");
//		System.out.println("userid-->>> "+userid);
////		User user = (User)session.getAttribute("user");
////		System.out.println("user:  "+user);
//		String userdesc = request.getParameter("userdesc");
//		
//		String status = request.getParameter("status");
//		System.out.println("status-->>> "+status);
//		
//		Result resObj = registrationService.insertUpdateRecord(procName, opflag, posMstID, titleId, firstName, middleName, lastName, firstNameOl, middleNameOl, lastNameOl, fatherName, motherName, gender, maritalStatus, birthDate, religionId, categoryId, educationId, mobNo, phoneNo, emailId, adrType, areaId, house, houseId, street, streetId, landmark, landmarkId, uploadName, docPath, docType, ftpId, strid, branchid, userid, userdesc);
//		String msg = "";
//		if (resObj.getStatus().equals("success")) {
//			if (opflag.equals("D")) {
//				msg = "Record deleted successfully.";
//			} else if (opflag.equals("M")) {
//				msg = "Record updated successfully.";
//			} else {
//				msg = "Registered Successfully";
//			}
//		} else {
//			msg = resObj.getMsg();
//		}
//		resObj.setMsg(msg);
//		return resObj;
//	}
	
	@RequestMapping(value = "/saveRegistration", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody Result saveRegistration(HttpServletRequest request,HttpServletResponse res){
		
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());       
        JSONArray json = new JSONArray();
        String procName = "PR_POS_MST";
        String opFlag = null;
        String posMstId = null;
        String titleId = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String firstNameOl = null;
        String middleNameOl = null;
        String lastNameOl = null;
        String fatherName = null;
        String motherName = null;
        String gender = null;
        String maritalStatus = null;
        String religionId = null;
        String categoryId = null;
        String educationId = null;
        String mobNo = null;
        String phoneNo = null;
        String emailId = null;
        String adrType = null;
        String areaId = null;
        String house = null;
        String houseId = null;
        String street = null;
        String streetId = null;
        String landMark = null;
        String landMarkId = null;
        String userId = null;
        String branchId = null;
        String status = null;
        String strId = null;
        String ftpId = null;
        String birthDate = null;
        String docPath = null;
        String docType = null;
        String uploadName = null;
        String userDesc = null;
        String aadharEnroll = null;
        String aadharNo = null;
        String panNo = null;
        try {
        	String filename ="";
            List<FileItem> items = uploadHandler.parseRequest(request);
            
            System.out.println("items"+items.size());
            for (FileItem item : items) {           	
                if (item.isFormField()) {
                	if(item.getFieldName().equals("opflag")){
                		opFlag = item.getString();
                		System.out.println("opFlag : "+opFlag);
                	}else if(item.getFieldName().equals("posMstID")){
                		posMstId = item.getString();
                		System.out.println("posMstID : "+item.getString());
                	}else if(item.getFieldName().equals("titleId")){
                		titleId = item.getString();
                		System.out.println("titleId : "+item.getString());
                	}else if(item.getFieldName().equals("firstName")){
                		firstName = item.getString();
                		System.out.println("firstName : "+item.getString());
                	}else if(item.getFieldName().equals("middleName")){
                		middleName = item.getString();
                		System.out.println("middleName : "+item.getString());
                	}else if(item.getFieldName().equals("lastName")){
                		lastName = item.getString();
                		System.out.println("lastName : "+item.getString());
                	}else if(item.getFieldName().equals("firstNameOl")){
                		firstNameOl = item.getString();
                		System.out.println("firstNameOl : "+item.getString());
                	}else if(item.getFieldName().equals("middleNameOl")){
                		middleNameOl = item.getString();
                		System.out.println("middleNameOl : "+item.getString());
                	}else if(item.getFieldName().equals("lastNameOl")){
                		lastNameOl = item.getString();
                		System.out.println("lastNameOl : "+item.getString());
                	}else if(item.getFieldName().equals("fatherName")){
                		fatherName = item.getString();
                		System.out.println("fatherName : "+item.getString());
                	}else if(item.getFieldName().equals("motherName")){
                		motherName = item.getString();
                		System.out.println("motherName : "+item.getString());
                	}else if(item.getFieldName().equals("gender")){
                		gender = item.getString();
                		System.out.println("gender : "+item.getString());
                	}else if(item.getFieldName().equals("maritalStatus")){
                		maritalStatus = item.getString();
                		System.out.println("maritalStatus : "+item.getString());
                	}else if(item.getFieldName().equals("religionId")){
                		religionId = item.getString();
                		System.out.println("religionId : "+item.getString());
                	}else if(item.getFieldName().equals("aadharEnroll")){
                		aadharEnroll = item.getString();
                		System.out.println("aadharEnroll : "+item.getString());
                	}else if(item.getFieldName().equals("aadharNo")){
                		aadharNo = item.getString();
                		System.out.println("aadharNo : "+item.getString());
                	}else if(item.getFieldName().equals("panNo")){
                		panNo = item.getString();
                		System.out.println("panNo : "+item.getString());
                	}else if(item.getFieldName().equals("adrType")){
                		adrType = item.getString();
                		System.out.println("adrType : "+item.getString());
                	}else if(item.getFieldName().equals("categoryId")){
                		categoryId = item.getString();
                		System.out.println("categoryId : "+item.getString());
                	}else if(item.getFieldName().equals("educationId")){
                		educationId = item.getString();
                		System.out.println("educationId : "+item.getString());
                	}else if(item.getFieldName().equals("mobNo")){
                		mobNo = item.getString();
                		System.out.println("mobNo : "+item.getString());
                	}else if(item.getFieldName().equals("phoneNo")){
                		phoneNo = item.getString();
                		System.out.println("phoneNo : "+item.getString());
                	}else if(item.getFieldName().equals("emailId")){
                		emailId = item.getString();
                		System.out.println("emailId : "+item.getString());
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
                		streetId = item.getString();
                		System.out.println("streetId : "+item.getString());
                	}else if(item.getFieldName().equals("landmark")){
                		landMark = item.getString();
                		System.out.println("landmark : "+item.getString());
                	}else if(item.getFieldName().equals("landmarkId")){
                		landMarkId = item.getString();
                		System.out.println("landmarkId : "+item.getString());
                	}else if(item.getFieldName().equals("userid")){
                		userId = item.getString();
                		System.out.println("userid : "+item.getString());
                	}else if(item.getFieldName().equals("branchid")){
                		branchId = item.getString();
                		System.out.println("branchid : "+item.getString());
                	}else if(item.getFieldName().equals("status")){
                		status = item.getString();
                		System.out.println("status : "+item.getString());
                	}else if(item.getFieldName().equals("strid")){
                		strId = item.getString();
                		System.out.println("strid : "+item.getString());
                	}else if(item.getFieldName().equals("ftpId")){
                		ftpId = item.getString();
                		System.out.println("ftpId : "+item.getString());
                	}else if(item.getFieldName().equals("birthDate")){
                		birthDate = item.getString();
                		System.out.println("birthDate : "+item.getString());
                	}else if(item.getFieldName().equals("docPath")){
                		docPath = item.getString();
                		System.out.println("docPath : "+item.getString());
                	}else if(item.getFieldName().equals("docType")){
                		docType = item.getString();
                		System.out.println("docType : "+item.getString());
                	}else if(item.getFieldName().equals("uploadName")){
                		uploadName = item.getString();
                		System.out.println("uploadName : "+item.getString());
                	}else if(item.getFieldName().equals("userDesc")){
                		userDesc = item.getString();
                		System.out.println("userDesc : "+item.getString());
                	}
                	
                	filename = item.getString();
                }else if(!item.isFormField()){
                	System.out.println("file name"+item.getName());
                	InputStream is = item.getInputStream();
                    byte [] userImage = new byte[is.available()];
                    is.read(userImage);
                    
                    System.out.println("size::"+userImage.length);
                	JSONObject jsono = new JSONObject();
                    jsono.put("status", "success");
                    jsono.put("size", item.getSize());                                              
                    json.put(jsono);
                }
            }
        } catch (FileUploadException e) {
                e.printStackTrace();
        } catch (Exception e) {
        		e.printStackTrace();
        } 
       
        Result resObj = registrationService.insertUpdateRecord(procName, opFlag, posMstId, titleId, firstName, middleName, lastName, firstNameOl, middleNameOl, lastNameOl, fatherName, motherName, gender, maritalStatus, birthDate, aadharEnroll, aadharNo, panNo, religionId, categoryId, educationId, mobNo, phoneNo, emailId, adrType, areaId, house, houseId, street, streetId, landMark, landMarkId, uploadName, docPath, docType, ftpId, strId, branchId, userId, userDesc,status);
        String msg = "";
		if (resObj.getStatus().equals("success")) {
			if (opFlag.equals("D")) {
				msg = "Record deleted successfully.";
			} else if (opFlag.equals("M")) {
				msg = "Record updated successfully.";
			} else {
				msg = "Registered Successfully";
			}
		} else {
			msg = resObj.getMsg();
		}
		resObj.setMsg(msg);
		
		System.out.println("msg"+resObj.getMsg());
        return resObj;
	}
}
