<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/validation.js"></script>
<script type="text/javascript">
function validateFields() {
	
	var userid = $("#uid").val();
	var password = $("#pwd").val();

	if (userid == "") {
		alert("Please Enter User id!!");
		setTimeout(function() {
			$("#uid").focus();
		}, 1);
		return;
	} else if (password == "") {
		alert("Please Enter Password!!");
		setTimeout(function() {
			$("#pwd").focus();
		}, 1);
		return;
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/loginValidation",
		type : "post",
		async : true,
		data : {
			userid : userid,
			password : password
		},
		beforeSend: function () {
			showWait();
       },
       complete: function () {
    	    hideWait();
      },
		success : function(resp) {
// 			alert(resp.msg);
			if (resp.status == "success") {
				location.href = "welcome";
			} else {
				$("#pwd").val("");
				alert(resp.msg);
				setTimeout(function() {
					$("#pwd").focus();
				}, 1);
				return;
			}
		},
		error : function() {
// 			alert(resp.msg);
			location.href = "${pageContext.request.contextPath}";
		}
	});
}

</script>
</head>
<body>
	<div class="container"
		style="width: 350px; height: 500px;margin: 0;">

		<form role="form" id="myform">
			<fieldset style="margin-top: 20px;height: 100%;">
				<button type="button" class="close" data-dismiss="modal" >&times;</button>
				<div class="form-group">
					<input id="uid" class="form-control" placeholder="UserId"
						name="userid" type="text" tabIndex="1" required style="width: 80%;margin-left: 20px;">
				</div>
				<div class="form-group">
					<input id="pwd" class="form-control" placeholder="Password"
						name="password" type="password" tabIndex="2" required style="width: 80%;margin-left: 20px;">
				</div>
				<input type="button" class="btn-lg btn-block"
					onclick="validateFields();" value="Login" style="margin-right: 45px;width: 60px;"
					tabIndex="3">
			</fieldset>
		</form>
	</div>
</body>
</html>