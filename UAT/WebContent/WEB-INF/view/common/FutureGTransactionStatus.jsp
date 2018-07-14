<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.json.JSONException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Status</title>
</head>
<body>

  <%  if(request.getAttribute("data")!=null)
	{ 
	  String WS_P_ID="",transactionId="",paymentGatewayID="",premiumAmount="",responseStatus="";
	  HashMap<String,String> jsonResp=(HashMap<String,String>) request.getAttribute("data");
	  Iterator entries = jsonResp.entrySet().iterator();
	 
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String mapKey = (String)entry.getKey();
		    
		   	if (mapKey.equalsIgnoreCase("WS_P_ID")) {
		   		WS_P_ID = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		   	if (mapKey.equalsIgnoreCase("transactionId")) {
		   		transactionId = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		   	if (mapKey.equalsIgnoreCase("paymentGatewayID")) {
		   		paymentGatewayID = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		   	if (mapKey.equalsIgnoreCase("premiumAmount")) {
		   		premiumAmount = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		   	if (mapKey.equalsIgnoreCase("responseStatus")) {
		   		responseStatus = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		}
			
      
//          
	%><center>
	<form name='form' action="" method="post">
	 <table border="1">
          		<tr>
					<th>FG Transaction ID</th>
                    <td name="FGTransID" id="FGTransID"><%=WS_P_ID%></td>
                </tr>
           
      			<tr>
                    <th>Transaction ID</th>
                    <td name="transId" id="transId"><%=transactionId%></td>
                </tr> 
                <tr>
                    <th>Payment Gateway ID</th>
                    <td name="gatewayType" id="gatewayType"><%=paymentGatewayID%></td>
                </tr>
                <tr>
                    <th>Premium Amount</th>
                    <td name="gatewayType" id="gatewayType"><%=premiumAmount%></td>
                </tr>
                <tr>
                    <th>Payment Response</th>
                    <td name="gatewayType" id="gatewayType"><%=responseStatus%></td>
                </tr>
              
              
           
               
         </table>
         </form>
         </center>
     <%
		 }else{

    		PrintWriter printWriter= response.getWriter();
    		printWriter.print("Invalid");
     }
  %> 
  </body>
</html>