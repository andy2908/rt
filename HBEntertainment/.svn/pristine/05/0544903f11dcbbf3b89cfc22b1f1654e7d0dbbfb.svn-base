<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script> -->
<script type="text/javascript" src="js/select2.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script> -->
<script src="js/validation.js"></script>
<script>
/* 
a {
    color: blue;
} */
function validate() {
if (!$('#firstNameTf').val()) {
	alert("Please Enter Name!");
	$('#firstNameTf').focus();
	return false;
}
if (!$('#middleNameTf').val()) {
	alert("Please Enter Middle Name!");
	$('#middleNameTf').focus();
	return false;
}
if (!$('#lastNameTf').val()) {
	alert("Please Enter Last Name!");
	$('#lastNameTf').focus();
	return false;
}
if (!$('#emailTf').val()) {
	alert("Please Enter Email!");
	$('#emailTf').focus();
	return false;
}
if (!$('#mobileNoTf').val()) {
	alert("Please Enter Mobile Number!");
	$('#mobileNoTf').focus();
	return false;
}

var email = document.getElementById('emailTf');
var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
if (!filter.test(email.value)) {
alert('Please provide a valid email address');
$('#emailTf').focus();
$('#emailTf').val("");
return false;
}

var mbl=document.getElementById("mobileNoTf")
var mbFilter=/^[7-9][0-9]{9}$/;
 if (mbl.value.length < 10 || mbl.value.length > 10) {
        alert("Mobile No. is not valid, Please Enter 10 Digit Mobile No.");
        $('#mobileNoTf').val("");
        $('#mobileNoTf').focus();
        return false;
   }
 if (!mbFilter.test(mbTextfiels.value)) {
	 alert('Please provide a valid Mobile no');
	    $('#mobileNoTf').focus();
	    $('#mobileNoTf').val("");
	    return false;
 }

}

function getList() {
	
	var mobNo = $("#mobileNoTf").val();
	var emailId = $("#emailTf").val();
	$
			.ajax({
				url : "${pageContext.request.contextPath}/getRecordLst",
				type : "post",
				async : false,
				data : {
					sqlMstId : '2003',
					param : "'" + mobNo + "'" + "~" + "'" + emailId + "'"
				},
				success : function(resp) {
					if (resp[0].CNT == 0) {
						saveData();
					} else{
						alert("Email or Mobile Already Registered\nPlease try again!!");
					}
				},
				error : function() {
					alert(resp);
				}
			});
}

function saveData() {
	var userId = "0";
	var opFlag = "N";
	var mobNo = $("#mobileNoTf").val();
	var userName = $("#firstNameTf").val();
	var userNameOl = $("#firstNameTf").val();
	var middleName = $("#middleNameTf").val();
	var middleNameOl = $("#middleNameTf").val();
	var lastName = $("#lastNameTf").val();
	var lastNameOl = $("#lastNameTf").val();
	var emailId = $("#emailTf").val();

	$.ajax({
		url : "${pageContext.request.contextPath}/register",
		type : "post",
		async : false,
		data : {
			user_id : userId,
			opflag : opFlag,
			mob_no : mobNo,
			user_name : userName,
			user_name_ol : userNameOl,
			middle_name : middleName,
			middle_name_ol : middleNameOl,
			last_name : lastName,
			last_name_ol : lastNameOl,
			email_id : emailId,
			branch_id : "1",
			user_desc : "null",
			status : "E"
		},
		success : function(resp) {
			alert(resp.msg);
		},
		error : function() {
			alert(resp.msg);
		}
	});
}
 </script>
 <style type="text/css">
 	#forminput input {
	  display: inline; 
	  width: 100%;
	  height: 35px;
	  padding: 6px 12px; 
	  font-size: 14px;
	  line-height: 1.42857143;
	  color: #201a1ae6;
	}
 
 </style>
</head>
<body>
	<div class="container"
		style="background-color: #fff; width: 500px; height: 250px; padding: 0px; margin: 0px;">
		<form class="form-horizontal" id="forminput">
			<div class="form-group" style="margin-right: 40px;">
				<label for="firstName" class="col-sm-3"
					style="width: 15%; margin-left: 60px;">Name</label>
				<div class="col-sm-9" style="display: inline; width: 24%;">
					<input type="text" id="firstNameTf" placeholder="First Name"
						class="form-control" tabIndex="1">
				</div>
				<div class="col-sm-9" style="display: inline; width: 24%;">
					<input type="text" id="middleNameTf" placeholder="Middle Name"
						style="width: 100%;" class="form-control" tabIndex="2">
				</div>
				<div class="col-sm-9" style="display: inline; width: 24%;">
					<input type="text" id="lastNameTf" placeholder="Last Name"
						style="width: 100%;" class="form-control" tabIndex="3">
				</div>
			</div>

			<!--  <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">Email</label>
                        <input type="email" id="emailTf" style="width: 200px" placeholder="Enter Email" class="form-control">
                    	<a href=""><font size="3" color="white"><i>Verify</i></font></a>
                </div> -->

			<div class="form-group">
				<div class="col-md-8" style="width: 100%;">
					<label for="email" style="margin-left: 60px;">Email</label> <input
						type="email" id="emailTf"
						style="width: 230px; margin-top: -30px; margin: 0; margin-left: 30px;height: 35px;"
						class="form-control validateEmail" placeholder="Email"
						tabIndex="4">
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-8" style="width: 100%;">
					<label for="mobile" style="margin-left: 60px;">Mobile</label> <input
						type="text" id="mobileNoTf" class="form-control validateMobile"
						style="width: 230px; margin-top: -30px; margin-left: 20px;height: 35px;"
						 placeholder="Mobile Number"
						maxlength="10" tabIndex="5">
				</div>
			</div>

			<!-- <div class="form-group">
                    <label for="mobile" class="col-sm-3 control-label">Mobile</label>
                    <div class="col-sm-9">
                        <input type="text" id="mobileNoTf" style="width: 200px" placeholder="Enter Mobile No" class="form-control">
                    	<a href=""><font size="3" color="white"><i>Verify</i></font></a>
                    </div>
                </div> -->
                </form>
			<div class="form-row" >
				<div class="col-md-8" style="float: right;" >
					<input type="button" class="btn btn-primary"
						style="float: right; margin: 0px 20px 10px 0px;" value="Register" onclick="getList();">
				</div>
			</div>
		
		<!-- /form -->
	</div>
	<!-- ./container -->

	<br>
</body>
</html>