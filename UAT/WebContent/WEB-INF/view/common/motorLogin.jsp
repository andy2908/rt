<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function validateFields() {
	
	var userid = $("#uidMotor").val();
	var password = $("#pwdMotor").val();

	if (userid == "") {
		old_alert("Please Enter User id!!");
		setTimeout(function() {
			$("#uidMotor").focus();
		}, 1);
		return;
	} else if (password == "") {
		old_alert("Please Enter Password!!");
		setTimeout(function() {
			$("#pwdMotor").focus();
		}, 1);
		return;
	}
	showWait();
	$.ajax({
		url : "${pageContext.request.contextPath}/loginValidation",
		type : "post",
		async : false,
		data : {
			userid : userid,
			password : password
		},
		success : function(resp) {
			hideWait();
			// old_alert(resp.html);
			if (resp.status == "success") {
				// location.href = "welcome";
			//	alert("Success");
				$('#loginbtnMotor').attr("data-dismiss","modal"); 
				$('#getProposal').focus(); 
				 setTimeout(function(){
					 //refreshTable();
					 coverDetails();
				  },1000);
				
				
			} else {
				old_alert(resp.msg);
				$("#pwdMotor").val("");
				setTimeout(function() {
					$("#pwdMotor").focus();
				}, 1);
			
				return;
			}
		},
		error : function(resp) {
			hideWait();
			old_alert(resp.msg);
		//	location.href = "${pageContext.request.contextPath}";
		}
	});
}

function enableButton()
{
	$('#showCompanyBtn').prop('disabled', false);
	}
</script>
</head>
<body>
		<div class="container "
		style="width: 350px; height: 500px;margin: 0;">

		<form role="form" id="myform" >
			<fieldset style="height: 100%;">
				<button type="button" class="close" data-dismiss="modal" onclick="enableButton();">&times;</button>
				<div class="form-group" style="margin-top: 20px;">
					<label><font class="fontColor"> Please Login to continue </font></label>
				</div>
				<div class="form-group" style="margin-top: 20px;">
					<input id="uidMotor" class="form-control" placeholder="UserId" tabIndex="1001"
						name="userid" type="text" required style="width: 80%;margin-left: 20px;">
				</div>
				<div class="form-group" style="margin-top: 20px;">
					<input id="pwdMotor" class="form-control" placeholder="Password" tabIndex="1002"
						name="password" type="password"  required style="width: 80%;margin-left: 20px;">
				</div>
				<input type="button" class="btn-lg btn-block" tabIndex="1003"
					onclick="validateFields();" value="Login" style="margin-right: 45px;width: 70px;" id="loginbtnMotor"
					>
			</fieldset>
		</form>
	</div>
</body>
</html>