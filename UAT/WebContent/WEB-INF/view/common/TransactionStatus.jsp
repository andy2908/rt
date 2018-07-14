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

	  HashMap<String,String> jsonResp=(HashMap<String,String>) request.getAttribute("data");
	  Iterator entries = jsonResp.entrySet().iterator();
		String mihpayid="",amount="",PG_TYPE="",error_code="",txnid="",status="",bank_ref_num="";
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String mapKey = (String)entry.getKey();
		   	if (mapKey.equalsIgnoreCase("mihpayid")) {
		   		mihpayid = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("amount")) {
				amount = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("PG_TYPE")) {
				PG_TYPE = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
				
			}
			if (mapKey.equalsIgnoreCase("error_code")) {
				error_code = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("txnid")) {
				txnid = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("status")) {
				status = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("bank_ref_num")) {
				bank_ref_num = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		}
			
      
//          
	%><center>
	<form name='form' action="" method="post">
	 <table border="1">
          		<tr>
                    <th>PayU ID</th>
                    <td name="PayUID" id="PayID"><%=mihpayid%></td>
                </tr>
           
      			<tr>
                    <th>Amount</th>
                    <td name="amount" id="amount"><%=amount%></td>
                </tr>
                <tr>
                    <th>Payment GateWay Type</th>
                    <td name="gatewayType" id="gatewayType"><%=PG_TYPE%></td>
                </tr>
                <tr>
                    <th>Error code</th>
                    <td name="errorCode" id="errorCode"><%=error_code%></td>
                </tr>
                <tr>
                    <th>Transaction ID</th>
                    <td name="transId" id="transId"><%=txnid%></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td name="status" id="status"><%=status%></td>
                </tr>
                <tr>
                    <th>Bank Reference Number</th>
                    <td name="bankRefNo" id="bankRefNo"><%=bank_ref_num%></td>
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
       <input type="button" id="close" value="Close" onclick="closeForm()">
</body>
<script type="text/javascript">



function closeForm()
{
	var transactionStatus =  $("#status").html();

	 $.ajax({
			url: 'user/updatePaymentStatus?status='+transactionStatus,
		     data: "",
		    
		     'method' : 'post',
				'dataType' : 'json',
				'async' : false,
		     success: function(data){
		      	window.top.close();
		       
		     },error: function (data) 
		     {
		    	 
		     }

});
}


/* function saveData() {
   
    	var	PayUID = $("#PayUID").val();
    	var	amount = $("#amount").val(); 
    	var	gatewayType = $("#gatewayType").val() ;
    	var	errorCode = $("#errorCode").val() ;
    	var	transId = $("#transId").val();
    	var	status = $("#status").val();
    	var	bankRefNo = $("#bankRefNo").val();
     var paymentResult= "&PayUID=" +PayUID + "&amount=" +amount +"&gatewayType=" +gatewayType +
     "&errorCode="+ errorCode + "&transId=" +transId + "&status=" + status + "&bankRefNo=" +bankRefNo;
     $.ajax({
          url : './VidpaymentResult', // Your Servlet mapping or JSP(not suggested)
          data :paymentResult, 
          type : 'POST',
          success : function() {
				alert("Hello");

          },
          error : function() {
             
          }
      });
}
 */

</script>
</html>