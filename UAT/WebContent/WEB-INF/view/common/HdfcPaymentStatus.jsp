<%@page import="java.util.HashMap"%>
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
		String PolicyNo="",Msg="",ProposalNo="",Amt="";
		while (entries.hasNext()) {
			
			Map.Entry entry = (Map.Entry) entries.next();
		    String mapKey = (String)entry.getKey();
		    if (mapKey.equalsIgnoreCase("PolicyNo")) {
		    	PolicyNo = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		   	if (mapKey.equalsIgnoreCase("Msg")) {
		   		Msg = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		   	if (mapKey.equalsIgnoreCase("ProposalNo")) {
		   		ProposalNo = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		   	if (mapKey.equalsIgnoreCase("Amt")) {
		   		Amt = (null == entry.getValue()) ? "" : entry.getValue()
			.toString();
			}
		   
		}
	%>
	<form id="form1" name="form1" action="" method="POST">
		<div>
			<input id="__VIEWSTATE" type="hidden"
				value="/wEPDwUKMTgyOTc0NDQ4NGRk" name="__VIEWSTATE">
		</div>
		<div>
			<input id="__EVENTVALIDATION" type="hidden"
				value="/wEWCwKu9u1aArWAsjgC2N/j/ggCu8Wf9QYC1q69igEC8ZfbnwsCz5v5iAgCr67qtggCj+334AIC1N752ggC7OGafg=="
				name="__EVENTVALIDATION">
		</div>
		<center>
			<table border="1">
				<tr>
					<td>PolicyNo</td>
					<td><input id="PolicyNo" name="PolicyNo" value=<%=PolicyNo%> /></td>
				</tr>
				<tr>
					<td>Msg</td>
					<td><input id="Msg" name="Msg" value=<%=Msg%> /></td>
				</tr>
				<tr>
					<td>ProposalNo</td>
					<td><input id="ProposalNo" name="ProposalNo"
						value=<%=ProposalNo%> /></td>
				</tr>
				
				<tr>
					<td>Amt</td>
					<td><input id="Amt" name="Amt"
						value=<%=Amt%> /></td>
				</tr>
				
				<tr>
					<td colspan="2"><input onclick="submitForm()" type="button"
						value="Submit"></td>
				</tr>
			</table>
		</center>
	</form>

	<script language="javascript" type="text/javascript">
		function submitForm() {
			var elm = document.form1.elements[0];
			elm.parentNode.removeChild(elm);
			document.form1.method = "POST";
			document.form1.action = "http://202.191.196.210/uat/onlineproducts/NewDesignMotorOnline/ThankYou.aspx";
			document.form1.submit();
		}
	</script>
	<%
		}
	%>
</body>
</html>