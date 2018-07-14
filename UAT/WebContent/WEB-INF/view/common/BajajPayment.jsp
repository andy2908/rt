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
		
		String trnscId = "", polRef = "", errMSg = "", userId= "";
		
		while (entries.hasNext()) {
	
			Map.Entry entry = (Map.Entry) entries.next();
  			String mapKey = (String)entry.getKey();
  		if (mapKey.equalsIgnoreCase("trnscId")) {
  			trnscId = (null == entry.getValue()) ? "" : entry.getValue().toString();
		}
 		if (mapKey.equalsIgnoreCase("polRef")) {
 			polRef = (null == entry.getValue()) ? "" : entry.getValue().toString();
		}
 		if (mapKey.equalsIgnoreCase("errMSg")) {
 			errMSg = (null == entry.getValue()) ? "" : entry.getValue().toString();		
 		}
 		if (mapKey.equalsIgnoreCase("userId")) {
 			userId = (null == entry.getValue()) ? "" : entry.getValue().toString();		
 		}
		}
	
	%>
	
	<center>
	
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
			 TxnId <input id="txnid"name="txnid" value='<%=trnscId%>' /><br /> 
			 UserId <input id="amount" name="amount" value='<%=userId%>' /><br />
		  <input
			onclick="submitForm();" type="button" value="CALL  GATEWAY">
	</form>

	<script language="javascript" type="text/javascript">
		function submitForm() {
/* alert("Hello"); */

			 <%-- console.log("submit");
			 
			
			 alert("submit");
			
			var elm = document.form1.elements[0];
			elm.parentNode.removeChild(elm);
			document.form1.method = "POST";

			 document.form1.action = "http://webservicesdev.bajajallianz.com/Insurance/WS/new_cc_payment.jsp?requestId="+<%=trnscId%>+"&Username="+<%=userId%>+"&sourceName=WS_MOTOR";

			document.form1.submit();  --%>
			
			var elm = document.forms[0].elements[0];
            elm.parentNode.removeChild(elm);
            document.forms[0].method = "post";
            document.forms[0].action = "http://webservicesdev.bajajallianz.com/Insurance/WS/new_cc_payment.jsp?requestId="+<%=trnscId%>+"&Username=10053264@general.bajajallianz.co.in&sourceName=WS_MOTOR";

            document.forms[0].submit();
		}
	</script>
	<%
		}
	
	%>
</body>
</html>