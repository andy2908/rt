<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html> 
<body onload='document.forms[0].submit()'>

<%!
String status="",policyNumber="",transactionNumber="",proposalNumber="",proposalAmount="",policyLink="";

%>
<% 
  if( request.getAttribute("data")!=null)
  {
	HashMap<String,String> payRequest=(HashMap<String,String>) request.getAttribute("data");
	Iterator entries = payRequest.entrySet().iterator();

	String proposalNo="",userID="",password="",paymentType="",responseurl="",actionUrl="";
	while (entries.hasNext()) {
			
		Map.Entry entry = (Map.Entry) entries.next();
	    String mapKey = (String)entry.getKey();
	    if (mapKey.equalsIgnoreCase("UserID")) {
			userID = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("ProposalNo")) {
	   		proposalNo = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("PaymentType")) {
			paymentType = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("ProposalAmount")) {
			proposalAmount = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		
		}
		
		if (mapKey.equalsIgnoreCase("Responseurl")) {
			responseurl = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("actionUrl")) {
			actionUrl = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	
	}
%>

<form name='form' action=<%=actionUrl%> method="post">
	
<input type='hidden' name='userId' value=<%=userID%> /> 
<input type='hidden' name='proposalno' value=<%=proposalNo%> /> 
<input type='hidden' name='PaymentType' value=<%=paymentType%> /> 
<input type='hidden' name='ProposalAmount' value=<%=proposalAmount%> /> 
<input type='hidden' name='Responseurl' value=<%=responseurl%> /> 

</form>

<%

}
%>
</body> 
</html>