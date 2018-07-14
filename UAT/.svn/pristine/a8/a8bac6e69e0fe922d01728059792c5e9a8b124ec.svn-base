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
<script src="js/jquery.min.js"></script>
<script src="js/dataTables.checkboxes.min.js"></script>
<link rel="stylesheet" href="css/datepicker3.css">
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/productDetails.js"></script>

<title>Transaction Status</title>
</head>
<body onLoad="loaded()">

<jsp:include page="header.jsp"/>

  <%  if(request.getAttribute("data")!=null)
	{ 
	   HashMap<String,String> jsonResp=(HashMap<String,String>) request.getAttribute("data");
	  	Iterator entries = jsonResp.entrySet().iterator();
		String statusId="",PolicyNumber="",TransactionNumber="",Optionalvalue="",GatewayName="",ProposalNo="",TransactionStatus="";
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    String mapKey = (String)entry.getKey();
		   	if (mapKey.equalsIgnoreCase("statusId")) {
		   		statusId = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("PolicyNumber")) {
				PolicyNumber = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("TransactionNumber")) {
				TransactionNumber = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
				
			}
			if (mapKey.equalsIgnoreCase("Optionalvalue")) {
				Optionalvalue = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("GatewayName")) {
				GatewayName = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("ProposalNo")) {
				ProposalNo = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
			if (mapKey.equalsIgnoreCase("TransactionStatus")) {
				TransactionStatus = (null == entry.getValue()) ? "" : entry.getValue()
							.toString();
			}
		}
			
      
//          
	%><center>
	<form name='form' action="" method="post">
	 <table border="1">
          		<tr>
                    <th>status Id</th>
                    <td><%=statusId%></td>
                </tr>
           
      			<tr>
                    <th>Policy Number</th>
                    <td><%=PolicyNumber%></td>
                </tr>
                <tr>
                    <th>Transaction Number</th>
                    <td><%=TransactionNumber%></td>
                </tr>
                <tr>
                    <th>Optional value</th>
                    <td><%=Optionalvalue%></td>
                </tr>
                <tr>
                    <th>Gateway Name </th>
                    <td><%=GatewayName%></td>
                </tr>
                <tr>
                    <th>Proposal No</th>
                    <td><%=ProposalNo%></td>
                </tr>
                <tr>
                    <th>Transaction Status</th>
                    <td id="transStatus"><%=TransactionStatus%></td>
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

<jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">


function loaded(){
	setTimeout(closeForm, 1000);
}
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

/* function redirectToApp()
{
//	var appid = $('#appId').val;
	
	alert("Hello");
 $.ajax({
	url: 'http://localhost:8085/MySpringApp/RelOnlinePayment',
     data: "",
     processData: false,
     contentType: false,
     type: 'POST',
     success: function(data){
       alert(data);
       alert("File uploaded successfully");
     },error: function (data) {
   	  alert("error")
         console.log('An error occurred.');
         console.log(data);
     }
 });
} */
</script>
</html>