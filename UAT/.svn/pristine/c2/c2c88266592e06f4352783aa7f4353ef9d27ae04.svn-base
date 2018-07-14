<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONArray"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Payment</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="Microsoft FrontPage 4.0" name="GENERATOR">
</head>
<body>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
    <!-- saved from url=(0058)http://testpb.policybazaar.com/HDFCErgoPaymentRequest.aspx -->

	<%
		if( request.getAttribute("data")!=null)
			  {
		HashMap<String,String> payRequest=(HashMap<String,String>) request.getAttribute("data");
		Iterator entries = payRequest.entrySet().iterator();
		String responseUrl="",UserName="",UserMailId="",ProductCd="",ProducerCd="", CustomerId="",TxnAmount="",AdditionalInfo1="",AdditionalInfo2="",AdditionalInfo3="",hdnPayMode="";
		while (entries.hasNext()) {
			
			Map.Entry entry = (Map.Entry) entries.next();
		    String mapKey = (String)entry.getKey();
		    if (mapKey.equalsIgnoreCase("hdnPayMode")) {
		hdnPayMode = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("CustomerId")) {
		   		CustomerId = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		   	if (mapKey.equalsIgnoreCase("ProducerCd")) {
		ProducerCd = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("UserMailId")) {
		UserMailId = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("AdditionalInfo3")) {
		AdditionalInfo3 = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("UserName")) {
		UserName = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("AdditionalInfo2")) {
		AdditionalInfo2 = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("AdditionalInfo1")) {
		AdditionalInfo1 = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			
			}
			if (mapKey.equalsIgnoreCase("TxnAmount")) {
		TxnAmount = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("ProductCd")) {
		ProductCd = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("responseUrl")) {
				responseUrl = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		}
	%>
	<form id="form1" name="form1" action="" method="post">
		<div>
			<input id="__VIEWSTATE" type="hidden"
				value="/wEPDwUKMTgyOTc0NDQ4NGRk" name="__VIEWSTATE">
		</div>
		<div>
			<input id="__EVENTVALIDATION" type="hidden"
				value="/wEWCwKu9u1aArWAsjgC2N/j/ggCu8Wf9QYC1q69igEC8ZfbnwsCz5v5iAgCr67qtggCj+334AIC1N752ggC7OGafg=="
				name="__EVENTVALIDATION">
		</div>
		CustomerID <input id="CustomerId" name="CustomerId"
			value=<%=CustomerId%> /><br /> TxnAmount <input id="TxnAmount"
			name="TxnAmount" value=<%=TxnAmount%> /><br /> AdditionalInfo1 <input
			id="AdditionalInfo1" name="AdditionalInfo1"
			value=<%=AdditionalInfo1%> /><br /> AdditionalInfo2 <input
			id="AdditionalInfo2" name="AdditionalInfo2"
			value=<%=AdditionalInfo2%> /><br /> AdditionalInfo3 <input
			id="AdditionalInfo3" name="AdditionalInfo3"
			value=<%=AdditionalInfo3%> /><br /> hdnPayMode <input
			id="hdnPayMode" name="hdnPayMode" value=<%=hdnPayMode%> /><br />
		UserName <input id="UserName" name="UserName" value=<%=UserName%> /><br />
		UserMailId <input id="UserMailId" name="UserMailId"
			value=<%=UserMailId%> /><br /> ProductCd <input id="ProductCd"
			name="ProductCd" value=<%=ProductCd%> /><br /> 
			ProducerCd <input id="ProducerCd" name="ProducerCd" value=<%=ProducerCd%> /> 
			<!-- responseUrl <input id="responseUrl" name="responseUrl" value=<%=responseUrl%> /><br /> --> 
			<input onclick="submitForm()" type="button" value="CALL HDFC GATEWAY">
	</form>

	<script language="javascript" type="text/javascript">
		function submitForm() {
			var elm = document.form1.elements[0];
			elm.parentNode.removeChild(elm);
			document.form1.method = "POST";

			document.form1.action = "http://202.191.196.210/UAT/OnlineProducts/MotorOnlineBreakin/TIM.aspx";

			document.form1.submit();
		}
	</script>
	<%
		}
	%>
</body>
</html>
