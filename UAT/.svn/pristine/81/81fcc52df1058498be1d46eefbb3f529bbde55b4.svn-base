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
String status="",policyNumber="",transactionNumber="",proposalNumber="",premiumAmount="",policyLink="";

%>
<% 
  if( request.getAttribute("data")!=null)
  {
	HashMap<String,String> payRequest=(HashMap<String,String>) request.getAttribute("data");
	Iterator entries = payRequest.entrySet().iterator();
	String txnid="",amount="",actionUrl="",productinfo="",SURL="",FURL="",key="",FirstName="",Email="",Phone="",quotationNumber="",customerID="";
	while (entries.hasNext()) {
	    Map.Entry entry = (Map.Entry) entries.next();
	    String mapKey = (String)entry.getKey();
	   	if (mapKey.equalsIgnoreCase("txnid")) {
			txnid = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("amount")) {
			amount = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("actionUrl")) {
			actionUrl = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
			System.out.println("actionUrl" +actionUrl);
		}
		if (mapKey.equalsIgnoreCase("productinfo")) {
			productinfo = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("SURL")) {
			SURL = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("FURL")) {
			FURL = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("key")) {
			key = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("FirstName")) {
			FirstName = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("Email")) {
			Email = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("Phone")) {
			Phone = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("quotationNumber")) {
			quotationNumber = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		if (mapKey.equalsIgnoreCase("customerID")) {
			customerID = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	}
%>

<form name='form' action=<%=actionUrl %> method="post">
<input type='hidden' name='txnid' value='<%=txnid%>' /> 
<input type='hidden' name='amount' value='<%=amount%>' /> 
<input type='hidden' name='productinfo' value='<%=productinfo%>' /> 
<input type='hidden' name='SURL' value='<%=SURL%>' /> 
<input type='hidden' name='FURL' value='<%=FURL%>' /> 
<input type='hidden' name='key' value='<%=key%>' /> 
<input type='hidden' name='FirstName' value='<%=FirstName%>' />
 <input type='hidden' name='Email' value='<%=Email%>'/> 
<input type='hidden' name='Phone' value='<%=Phone%>' /> 
<input type='hidden' name='quotationNumber' value='<%=quotationNumber%>' />
 <input type='hidden' name='customerID' value='<%=customerID%>' /> 

</form>

<%

}
%>
</body> 
</html>