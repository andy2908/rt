<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONArray"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		if( request.getAttribute("data")!=null)
			  {
		HashMap<String,String> payRequest=(HashMap<String,String>) request.getAttribute("data");
		Iterator entries = payRequest.entrySet().iterator();
		String key="",txnid="",amount="",productInfo="",prdctInfo="",firstname="",email="",phone="",
				surl="",furl="",userDefine1="",	hash="",userDefine2="",userDefine3="",userDefine4="",userDefine5="";
		while (entries.hasNext()) {
			
			Map.Entry entry = (Map.Entry) entries.next();
		    String mapKey = (String)entry.getKey();
		    if (mapKey.equalsIgnoreCase("key")) {
		    	key = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("txnid")) {
		   		txnid = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		   	if (mapKey.equalsIgnoreCase("amount")) {
		   		amount = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("productInfo")) {
		   		productInfo = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("firstname")) {
		   		firstname = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("email")) {
		   		email = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("phone")) {
		   		phone = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
		   	if (mapKey.equalsIgnoreCase("surl")) {
		   		surl = (null == entry.getValue()) ? "" : entry.getValue()
					.toString();
			}
			if (mapKey.equalsIgnoreCase("furl")) {
				furl = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("hash")) {
				hash = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("userDefine1")) {
				userDefine1 = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("userDefine2")) {
				userDefine2 = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("userDefine3")) {
				userDefine3 = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("userDefine4")) {
				userDefine4 = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
			if (mapKey.equalsIgnoreCase("userDefine5")) {
				userDefine5 = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		}
	%>
	<form id="form1" name="form1" action="" method="get">
		<div>
			<input id="__VIEWSTATE" type="hidden"
				value="/wEPDwUKMTgyOTc0NDQ4NGRk" name="__VIEWSTATE">
		</div>
		<div>
			<input id="__EVENTVALIDATION" type="hidden"
				value="/wEWCwKu9u1aArWAsjgC2N/j/ggCu8Wf9QYC1q69igEC8ZfbnwsCz5v5iAgCr67qtggCj+334AIC1N752ggC7OGafg=="
				name="__EVENTVALIDATION">
		</div>
		Key <input id="key" name="key" value=<%=key%> /><br />
			 TxnId <input id="txnid"name="txnid" value=<%=txnid%> /><br /> 
			 Amount <input id="amount" name="amount" value=<%=amount%> /><br />
             ProductInfo <input id="productInfo" name="productInfo" value=<%=productInfo%> /><br /> 
             First Name <input id="firstname" name="firstname" value=<%=firstname%> /><br />
			 Email <input id="email" name="email" value=<%=email%> /><br />
		     Phone <input id="phone" name="phone" value=<%=phone%> /><br />
		     Surl <input id="surl" name="surl" value=<%=surl%> /><br /> 
		     Furl <input id="furl" name="furl" value=<%=furl%> /><br />
		      <input id="hash" name="hash" type="hidden" value=<%=hash%> /><br />
		     Udf1 <input id="userDefine1" name="userDefine1" value=<%=userDefine1%> /><br />
		     Udf2 <input id="userDefine2" name="userDefine2" value=<%=userDefine2%> /><br />
		     Udf3 <input id="userDefine3" name="userDefine3" value=<%=userDefine3%> /><br />
		     Udf4 <input id="userDefine4" name="userDefine4" value=<%=userDefine4%> /><br />
		     Udf5 <input id="userDefine5" name="userDefine5" value=<%=userDefine5%> /><br />
		  <input
			onclick="submitForm()" type="button" value="CALL KOTAK GATEWAY">
	</form>

	<script language="javascript" type="text/javascript">
		function submitForm() {
			var elm = document.form1.elements[0];
			elm.parentNode.removeChild(elm);
			document.form1.method = "POST";

			document.form1.action = "https://test.payu.in/_payment";

			document.form1.submit();
		}
	</script>
	<%
		}
	%>
</body>
</html>