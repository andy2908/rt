<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload Documents</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="css/datepicker3.css">
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/productDetails.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<script type="text/javascript">
      $(document).ready(function(){
    	  
    	  $('input[type="file"]').change(function(e) {
  			var fileName = e.target.files[0].name;
  			alert('The file "' + fileName + '" has been selected.');
  		});
    	  
    	  function openfileDialog() {
    			$("#fileLoader").click();
    		} 
      });
      
      function showname (id,s) {
          var name = document.getElementById(s); 
          document.getElementById(id).value=name.files.item(0).name; 
        };

      function enableDisable(){
    	  var checked=$("#checkboxEducation").prop("checked");
    	  var checked1=$("#checkboxAadhar").prop("checked");
    	  var checked2=$("#checkboxPanCard").prop("checked");
    	  var checked3=$("#checkboxPassport").prop("checked");
    	  var checked4=$("#checkboxDriving").prop("checked"); 
    	  
    	  if(checked){
    		  document.getElementById("browseTf").disabled = false;
    		  document.getElementById("btnEcucation").disabled = false;
    		  document.getElementById("btnEcucationClr").disabled = false;
    	  }
    	  else{
    		  document.getElementById("browseTf").disabled = true;
    		  document.getElementById("btnEcucation").disabled = true;
    		  document.getElementById("btnEcucationClr").disabled = true;
    	  }
    	  if(checked1){
    		  document.getElementById("aadharTf").disabled = false;
    		  document.getElementById("btnAadhar").disabled = false;
    		  document.getElementById("btnAadharClr").disabled = false;
    	  }
    	  else{
    		  document.getElementById("aadharTf").disabled = true;
    		  document.getElementById("btnAadhar").disabled = true;
    		  document.getElementById("btnAadharClr").disabled = true;
    	  }
    	  if(checked2){
    		  document.getElementById("panCardTf").disabled = false;
    		  document.getElementById("btnPanCard").disabled = false;
    		  document.getElementById("btnPanCardClr").disabled = false;
    	  }
    	  else{
    		  document.getElementById("panCardTf").disabled = true;
    		  document.getElementById("btnPanCard").disabled = true;
    		  document.getElementById("btnPanCardClr").disabled = true;
    	  }
    	   if(checked3){
    		  document.getElementById("passportTf").disabled = false;
     		  document.getElementById("btnPassport").disabled = false;
     		  document.getElementById("btnPassportClr").disabled = false;
    	  }
    	  else{
    		  document.getElementById("passportTf").disabled = true;
     		  document.getElementById("btnPassport").disabled = true;
     		  document.getElementById("btnPassportClr").disabled = true;
    	  }
    	  if(checked4){
    		  document.getElementById("drivingLicenseTf").disabled = false;
    		  document.getElementById("btnDrivingLicense").disabled = false;
    		  document.getElementById("btnDrivingLicenseClr").disabled = false;
    	  }
    	  else{
    		  document.getElementById("drivingLicenseTf").disabled = true;
    		  document.getElementById("btnDrivingLicense").disabled = true;
    		  document.getElementById("btnDrivingLicenseClr").disabled = true;
    	  } 
      }
      
      function searchData() {
			$.ajax({
						url : "${pageContext.request.contextPath}/user/getRecordLst",
						type : 'post',
						dataType : 'json',
						async : false,
						data : {
							sqlMstId : 2002,
							param : null,
						},
						success : function(data) {
							volTable = $("#example").DataTable({
								/* $.extend(true, $.fn.dataTable.defaults, {
								    "lengthMenu": [[5, 10, 15, 20, 25], [5, 10, 15, 20, 25]],
								    "pageLength": 5

								});		 */	
								                      'data' : data,
								                      "iDisplayLength": 5,
												'columnDefs' : [
														{
															'targets' : 4,
															'searchable' : false,
															'orderable' : false,
															'className' : 'dt-body-center',
															'render' : function(
																	data, type,
																	full, meta) {
																return '<img src="images/edit.png", class="img1" id="edit" style="width:100" "height:100" onclick="gettext()" >';
															}
														},
														{
															'targets' : 5,
															'searchable' : false,
															'orderable' : false,
															'className' : 'dt-body-center',
															'render' : function(
																	data, type,
																	full, meta) {
																return '<img src="images/delete.png", class="img2" style="width:100" "height:100" onclick="deleteData()">';
															}
														}
														/* {
															"targets" : 0,
															"visible" : false
														} */
														],

												"columns" : [

												{
													"data" : "SEAT_TYPE_ID"
												}, {
													"data" : "SEAT_TYPE_CODE"
												}, {
													"data" : "SEAT_TYPE_NAME"
												}, {
													"data" : "SEAT_TYPE_NAME_OL"
												}, {
													"data" : null
												}, {
													"data" : null
												} ],

												'destroy' : true,
											});
						},
					});
		}

      </script>
</style>

</head>
<body><b>
<div style="border:2px solid black;background-color: #ff9e66" class="container-fluid">
	
	<!-- <div align="center" style="background-color: #d1bebe;margin: auto;"> -->
	<font size="4"><div align="center" style="background-color: #d1bebe">Upload Documents</div></font>
	<!-- </div> -->
      
      <table class="table" style="margin: auto;border=1">
                 
                <tr> 
                         <td style="width: 40px">
                         <input type="checkbox" class="form-check-input" id="checkboxEducation"
                         onclick="enableDisable()">
                         </td>
					     <td style="width: 700px">
					     Education Certificate &nbsp;
					     (Minimum Qualification:Senior Secondary School Certificate or its equivalent)
					     </td>
					    <td style="width: 380px">
					    <form id="myForm">
					    <input type="text" id="browseTf" disabled="disabled">
						<input type="file" id="selectedFile" style="display: none;"  multiple onchange="showname('browseTf','selectedFile')" />
						<input type="button" id="btnEcucation" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile').click();"/>
					    <input type="button" id="btnEcucationClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary" 
					    onclick="document.getElementById('browseTf').value = ''">
					    </form>
						</td>
                 </tr>
                
                <tr>
                     <td>
                     	<input type="checkbox" class="form-check-input" id="checkboxAadhar" 
                     	onclick="enableDisable()">
                     </td>
					 <td>Aadhar No</td>
					  <td style="width: 380px">
						<form id="myForm1">
					    <input type="text" id="aadharTf" disabled="disabled">
						<input type="file" id="selectedFile1" style="display: none;"  onchange="showname('aadharTf','selectedFile1')" >
						<input type="button" id="btnAadhar" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile1').click();"/>
					    <input type="button" id="btnAadharClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"  
						onclick="document.getElementById('aadharTf').value = ''">
					    </form>
					 </td>
               </tr>
               <tr>
                     <td><input type="checkbox" class="form-check-input" id="checkboxPanCard" 
                     onclick="enableDisable()">
                     </td>
					 <td>Pan Card No</td>
					  <td style="width: 380px">
					<form id="myForm2">
					    <input type="text" id="panCardTf" disabled="disabled">
						<input type="file" id="selectedFile2" style="display: none;"  onchange="showname('panCardTf','selectedFile2')" >
						<input type="button" id="btnPanCard" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile2').click();"/>
					    <input type="button" id="btnPanCardClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"
						onclick="document.getElementById('panCardTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td><input type="checkbox" class="form-check-input" id="checkboxPassport"
                     onclick="enableDisable()">
                     </td>
					 <td>Passport</td>
					  <td  style="width: 380px">
					 <form id="myForm3">
					    <input type="text" id="passportTf" disabled="disabled">
						<input type="file" id="selectedFile3" style="display: none;"  onchange="showname('passportTf','selectedFile3')" >
						<input type="button" id="btnPassport" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile3').click();"/>
					    <input type="button" id="btnPassportClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"  
						onclick="document.getElementById('passportTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td><input type="checkbox" class="form-check-input" id="checkboxDriving"
                     onclick="enableDisable()">
                     </td>
					 <td>Driving License</td>
					  <td  style="width: 380px">
					  <form id="myForm4">
					    <input type="text" id="drivingLicenseTf" disabled="disabled">
						<input type="file" id="selectedFile4" style="display: none;" onchange="showname('drivingLicenseTf','selectedFile4')" >
						<input type="button" id="btnDrivingLicense" disabled="disabled" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile4').click();"/>
					    <input type="button" id="btnDrivingLicenseClr" disabled="disabled" style="height: 30px" value="Clear" class="btn btn-primary"
						onclick="document.getElementById('drivingLicenseTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td></td>
					 <td>Photo</td>
					  <td  style="width: 380px">
					<form id="myForm5">
					    <input type="text" id="photoTf">
						<input type="file" id="selectedFile5" style="display: none;"  onchange="showname('photoTf','selectedFile5')" >
						<input type="button" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile5').click();"/>
					    <input type="button" style="height: 30px" value="Clear" class="btn btn-primary"  
					    onclick="document.getElementById('photoTf').value = ''">
					    </form>
					 </td>
              </tr>
              <tr>
                     <td></td>
					 <td>Sign</td>
					  <td  style="width: 380px">
					 <form id="myForm6">
					    <input type="text" id="signTf">
						<input type="file" id="selectedFile6" style="display: none;"  onchange="showname('signTf','selectedFile6')" >
						<input type="button" style="height: 30px" value="Browse" class="btn btn-primary" onclick="document.getElementById('selectedFile6').click();"/>
					    <input type="button" style="height: 30px" value="Clear" class="btn btn-primary" 
						onclick="document.getElementById('signTf').value = ''">
					    </form>
					 </td>
              </tr>
      </table>
    </div>
   </b>
</body>
</html>