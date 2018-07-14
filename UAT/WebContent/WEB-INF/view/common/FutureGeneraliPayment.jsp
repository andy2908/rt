<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html> 
<body onload='document.forms[0].submit()'>

<% 
  if( request.getAttribute("data")!=null)
  {
	HashMap<String,String> payRequest=(HashMap<String,String>) request.getAttribute("data");
	Iterator entries = payRequest.entrySet().iterator();
	String txnid="",paymentOption="", responseUrl="" , proposalNo="",premiumAmount="" ,actionUrl="", userIdentifier="" ,Email="",Phone="", lastName="", userIdFG="" , FirstName="";
	while (entries.hasNext()) {
	    Map.Entry entry = (Map.Entry) entries.next();
	    String mapKey = (String)entry.getKey();
	    
	   	if (mapKey.equalsIgnoreCase("transactionId")) {
			txnid = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("paymentOption")) {
			paymentOption = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("responseUrl")) {
	   		responseUrl = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("proposalNo")) {
	   		proposalNo = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("premiumAmount")) {
	   		premiumAmount = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("userIdentifier")) {
	   		userIdentifier = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("userIdFG")) {
	   		userIdFG = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("FirstName")) {
	   		FirstName = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
	   	if (mapKey.equalsIgnoreCase("lastName")) {
	   		lastName = (null == entry.getValue()) ? "" : entry.getValue()
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
	   	if (mapKey.equalsIgnoreCase("actionUrl")) {
	   		actionUrl = (null == entry.getValue()) ? "" : entry.getValue()
						.toString();
		}
		
	}
%>

<form name="form1" method="post" action="http://fglpg001.futuregenerali.in/Ecom_NL/WEBAPPLN/UI/Common/RedirectWebAggPay.aspx" id="form1">
TransactionID:<input type="text" name='TransactionID' value=<%=txnid%>/><br>
PaymentOption:<input type="text" name='PaymentOption' value=<%=paymentOption%>/><br>
ResponseURL:<input type="text" name='ResponseURL' value=<%=responseUrl%>/><br>
ProposalNumber:<input type="text" name='ProposalNumber' value=<%=proposalNo%>/><br>
PremiumAmount:<input type="text" name='PremiumAmount' value=<%=premiumAmount%>/><br>
UserIdentifier:<input type="text" name='UserIdentifier' value=<%=userIdentifier%>/><br>
UserId:<input type="text" name='UserId' value=<%=userIdFG%>/><br>
FirstName: <input type="text" name='FirstName' value=<%=FirstName%>/><br>
LastName:<input type="text" name='LastName' value=<%=lastName%>/><br>
Mobile:<input type="text" name='Mobile' value=<%=Phone%>/><br>
Email:<input type="text" name='Email' value=<%=Email%>/><br>
</form> 

<form name="form1" method="post" action=<%=actionUrl%> id="form1">
TransactionID:<input name="Transaction_ID" type="text" value="AJ123456789" id="Transaction_ID" /><br />
PaymentOption:<input name="Payment_Option" type="text" value="3" id="Payment_Option" /><br />
<!--ResponseURL:<input name="ctl02" type="text" value="http://fglpg001.futuregenerali.in/ecom_nl/WEBAPPLN/UI/Common/WebAggData.htm" name="ResponseURL" /><br />-->
ResponseURL:<input name="Response_URL" type="text" value="http://fglpg001.futuregenerali.in/ECOM_NL/WEBAPPLN/UI/Common/WebAggData.aspx" id="Response_URL" /><br />
ProposalNumber:<input name="Proposal_Number" type="text" value="A321456987" id="Proposal_Number" /><br />
PremiumAmount:<input name="Premium_Amount" type="text" value="1000" id="Premium_Amount" /><br />
UserIdentifier:<input name="User_Identifier" type="text" value="TestAgg" id="User_Identifier" /><br />
UserId:<input name="User_Id" type="text" value="456" id="User_Id" /><br />
FirstName:<input name="First_Name" type="text" value="tester" id="First_Name" /><br />
LastName:<input name="Last_Name" type="text" value="tester" id="Last_Name" /><br />
Mobile:<input name="Mobile_No" type="text" value="987654321" id="Mobile_No" /><br />
Email:<input name="Email_ID" type="text" value="test@test.com" id="Email_ID" /><br />
</form>


<%

}
%>
</body> 
</html>