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
<script	src="js/jquery-1.12.4.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery.min.js"></script>
<title>Payment Status</title>
</head>
<body>
<%  if(request.getAttribute("data")!=null)
	{ 

	  HashMap<String,String> jsonResp=(HashMap<String,String>) request.getAttribute("data");
	  Iterator entries = jsonResp.entrySet().iterator();
		String mihpayid="",amount="",PG_TYPE="",error_code="",txnid="",status="",bank_ref_num="",unmappedstatus="",hashResp="",mode="",key="";
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
			if (mapKey.equalsIgnoreCase("mode")) {
				mode = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("key")) {
				key = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("hashResp")) {
				hashResp = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("unmappedstatus")) {
				unmappedstatus = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		}
			
      
//          
	%><center>
	<form name='form' action="" method="get">
	 <table border="1">
          		<tr>
                    <th>PayU ID</th>
                    <td><%=mihpayid%></td>
                </tr>
           
      			<tr>
                    <th>Amount</th>
                    <td><%=amount%></td>
                </tr>
                <tr>
                    <th>Mode</th>
                    <td><%=mode%></td>
                </tr>
                <tr>
                    <th>Key</th>
                    <td><%=key%></td>
                </tr>
                <tr>
                    <th>HashResponse</th>
                    <td><%=hashResp%></td>
                </tr>
                <tr>
                    <th>UnmappedStatus</th>
                    <td><%=unmappedstatus%></td>
                </tr>
                <tr>
                    <th>Payment GateWay Type</th>
                    <td><%=PG_TYPE%></td>
                </tr>
                <tr>
                    <th>Error code</th>
                    <td><%=error_code%></td>
                </tr>
                <tr>
                    <th>Transaction ID</th>
                    <td><%=txnid%></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td id="transStatus"><%=status%></td>
                </tr>
                <tr>
                    <th>Bank Reference Number</th>
                    <td><%=bank_ref_num%></td>
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
	var transactionStatus =  $("#transStatus").html();

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

</script>
</html>