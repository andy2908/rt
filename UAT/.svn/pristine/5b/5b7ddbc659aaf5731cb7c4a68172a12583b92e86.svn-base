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
<title>Policy Number</title>
</head>
<body>
	<%
		if (request.getAttribute("data") != null) {

			HashMap<String, String> jsonResp = (HashMap<String, String>) request.getAttribute("data");
			Iterator entries = jsonResp.entrySet().iterator();

			String policyno = "", sts = "";

			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String mapKey = (String) entry.getKey();

				if (mapKey.equalsIgnoreCase("policyNO")) {
					policyno = (null == entry.getValue()) ? "" : entry.getValue().toString();
				}
				if (mapKey.equalsIgnoreCase("paySts")) {
					sts = (null == entry.getValue()) ? "" : entry.getValue().toString();
				}
			}
	%>
	<center>
		<table>
			<tr>
				<th>Policy No.</th>
				<td><%=policyno%></td>
			</tr>
			<tr>
				<th>Pay Status</th>
				<td><%=sts%></td>
			</tr>
		</table>
	</center>
	<%}else{
		PrintWriter printWriter= response.getWriter();
		printWriter.print("Invalid");
	}
	%>
</body>
</html>