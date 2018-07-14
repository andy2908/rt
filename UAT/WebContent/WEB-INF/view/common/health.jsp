<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="css/datepicker3.css">
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/productDetails.js"></script>
<script>
	$(document).ready(function() {
		
		$("#bankNameTf,#branchNameTf,#stateSelect,#citySelect,#occupationSelect,#coverTypeSelect,#noOfMembersSelect,#SumInsuredSelect,#initialsSelect,#nationalitySelect,#proposerCity,#countrySelect,#districtSelect,#proposerState,#pincodeSelect,#nomDtlInitial,#nomDtlRelation,#nomDtlCountry,#nomDtlState,#nomDtlDistrict,#nomDtlCity, #nomDtlPincode").select2({
			placeholder : "--Select--",
			allowClear : true,
			maximumSelectionSize : 1,
		});
		
		  $('.datePicker').datepicker({
			   placeholder:"dd/mm/yyyy",
		       autoclose: true,
		       todayHighlight: true,
		       format:'dd/mm/yyyy'
		    }); 
		 
		   $('.main').on('scroll', function() {
				$('.leftscroll').scrollTop($(this).scrollTop());
				$('#topmenu').scrollLeft($(this).scrollLeft());
			});

			$('.leftscroll').scroll(function() {
				$('.main').scrollTop($(this).scrollTop());
			});
			
	      $("#proposerDtlTab").hide();   
		  $("#diseaseDetails").hide();
		   $("#insuredDetails").hide();  
	      $("#previousPolicy").hide(); 
	      $("#previousPolicyDetails").hide(); 
		  $("#nomineeDetailsTab").hide();  
		  $("#medicalDetails").hide();    
		  $("#showCompanies").hide();    
		  $("#bankDtlTab").hide();    
		  
		populateState();
		populateOccupation();
		populateCoverType();
		populateNoOfMembers();
		populateSumInsured();
		populateCurrentDate();
		getInitial();
		populateNationality();
		populateCountry();
		AddRow();
		nomDtlInitial();
		nomDtlRelation();
		nomDtlCountry();
		populateBankNameDtl();
		
		 $('input:text:first').focus();
         var $inp = $('input:text');
        
         $inp.bind('keydown', function(e) {
		    if (e.which == 39) {
		          nxtIdx = $inp.index(this)+1; 
		        alert("nxtIdx-->>>> "+nxtIdx);
                $(":input:text:eq(" + nxtIdx + ")").focus();
                
		    }
		});
	   
		
	});
	$(function() {
		$(".wrapper1").scroll(function() {
			$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
		});
		$(".wrapper2").scroll(function() {
			$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
		});
	});
	var k=1;
	function populateCurrentDate(){
		var date = new Date();
		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();
		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;
		 currentDate = day + "/" + month + "/" + year;
		$("#policyStartDate").attr("value", currentDate);

		var day1 = date.getDate() - 1;
		var month1 = date.getMonth() + 1;
		var year1 = date.getFullYear() + 1;

		if (day1 < 10)
			day1 = "0" + day1;
		if (month1 < 10)
			month1 = "0" + month1;
		enddate=day1 + "/" + month1 + "/" + year1;
		$("#policyEndDate").attr("value", enddate);
	}
	function getPolicyEndDate()
	{
	     var policyStartDate = $("#policyStartDate").val();
	    
	    var date= policyStartDate.split("/")[0];
	    var month= policyStartDate.split("/")[1];
	    var year= policyStartDate.split("/")[2];
	     var startDate=month+"/"+date+"/"+year;
		 if(startDate == null || startDate == ''){
			$("#policyEndDate").attr("value", null);
		}else{ 
			var endDate = new Date(startDate);
			var day1=endDate.getDate()-1;
			var month1=endDate.getMonth()+1;
	    	var year1=endDate.getFullYear()+1;
			 if (day1 < 10)
				day1 = "0" + day1;
			if (month1 < 10)
				month1 = "0" + month1;
			enddate=day1 + "/" + month1 + "/" + year1;  
			$("#policyEndDate").attr("value", enddate);
			 }  
		
	}
	
	function populateState() {
		$.ajax({
					url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=find_rtostate&table_name=AMCP&str_company_type=&str_gic=&str_gicbid=&str_prod=&str_discnm=&str_rgrp=&str_state=&str_city=&str_prpsl=&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype=&str_veh=&str_mod=&str_var=&str_policy_age=&str_hbbid=&str_user_level=&str_user_id=&str_login_type=HB+EMPLOYEE&str_gap=&str_ageto=",
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(resp) {
						$("#stateSelect").empty();
						$("#stateSelect").append('<option value="">--Select--');
						$.each(resp, function(key, value) {
							nKey = parseInt(key) + 1;
							$("#stateSelect").append('<option value="'+resp[key].STATE_ID+'"> '+ resp[key].STATE_NAME + '');
							
						});
					},
				});
	}
	function populateCity() {
		var stateSelect = $("#stateSelect").val();
		$.ajax({
					url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=find_rtocity&table_name=AMCP&str_company_type=&str_gic=&str_gicbid=&str_prod=&str_discnm=&str_rgrp=&str_state='"
							+ stateSelect
							+ "'&str_city=&str_prpsl=&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype=&str_veh=&str_mod=&str_var=&str_policy_age=&str_hbbid=&str_user_level=&str_user_id=&str_login_type=HB+EMPLOYEE&str_gap=&str_ageto=",
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(resp) {
						$("#citySelect").empty();
						$("#citySelect").append('<option value="">--Select--');
						$.each(resp, function(key, value) {
							nKey = parseInt(key) + 1;
							$("#citySelect").append(
									'<option value="'+resp[key].RTOCITYID+'"> '
											+ resp[key].RTOCITYNAME + '');
						});
					},
				});

	}
	function populateOccupation() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '1',
			},
			success : function(resp) {
				$("#occupationSelect").empty();
				$("#occupationSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#occupationSelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	
	function populateCoverType() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '8',
			},
			success : function(resp) {
				$("#coverTypeSelect").empty();
				$("#coverTypeSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#coverTypeSelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	function populateNoOfMembers() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : 133,
			},
			success : function(resp) {
				$("#noOfMembersSelect").empty();
				$("#noOfMembersSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#noOfMembersSelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	function populateSumInsured() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : 132,
			},
			success : function(resp) {
				$("#SumInsuredSelect").empty();
				$("#SumInsuredSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#SumInsuredSelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	function getInitial() {
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '4',
			},
			success : function(resp) {
				$("#initialsSelect").empty();
				$("#initialsSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#initialsSelect").append('<option value="'+resp[key].DET_ID+'"> '+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	function populateNationality(){
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '26',
			},
			success : function(resp) {
				$("#nationalitySelect").empty();
				$("#nationalitySelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#nationalitySelect").append(
							'<option value="'+resp[key].DET_ID+'"> '
									+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	function populateCountry(){
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 95,
				param : null,
			},
			success : function(resp) {
				$("#countrySelect").empty();
				$("#countrySelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#countrySelect").append('<option value="'+resp[key].COUNTRY_ID+'"> '+ resp[key].COUNTRY_NAME + '');
				});
			},
		});
	}
	function populateNomRelation(){
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '5',
			},
			success : function(resp) {
				$("#nomDtlRelation").empty();
				$("#nomDtlRelation").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#nomDtlRelation").append('<option value="'+resp[key].DET_ID+'"> '+ resp[key].DET_NAME + '');
				});
			},
		});
	}
	function populateProposerState(){
		var country = $("#countrySelect").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 96,
				param : country,
			},
			success : function(resp) {
				$("#proposerState").empty();
				$("#proposerState").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#proposerState").append('<option value="'+resp[key].STATE_ID+'"> '+ resp[key].STATE_NAME + '');
				});
			},
		});
	}
	
	function populateDistrict(){
		var country = $("#countrySelect").val();
		var proposerState = $("#proposerState").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/getRecordLst",
		    type:'post',
		    datatype:'json',
		    async:'false',
		    data:{
		    	sqlMstId : 97,
				param : country + "~" + proposerState,
		},
		success : function(resp) {
			$("#districtSelect").empty();
			$("#districtSelect").append('<option value="">--Select--');
			$.each(resp, function(key, value) {
				nKey = parseInt(key) + 1;
				$("#districtSelect").append('<option value="'+resp[key].DISTRICT_ID+'"> '+ resp[key].DISTRICT_NAME + '');
			});
		},
	});
	}
	function populateProposerCity(){
		var country = $("#countrySelect").val();
		var state = $("#proposerState").val();
		var district = $("#districtSelect").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 98,
				param : country + "~" + state + "~" + district,
			},
			success : function(resp) {
				$("#proposerCity").empty();
				$("#proposerCity").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#proposerCity").append('<option value="'+resp[key].CITY_ID+'"> '+ resp[key].CITY_NAME + '');
				});
			},
		});
	}
	function populatePincode(){
		var country = $("#countrySelect").val();
		var state = $("#proposerState").val();
		var district = $("#districtSelect").val();
		var city = $("#proposerCity").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 100,
				param : country + "~" + state + "~" + district
						+ "~" + "null" + "~" + city,
			},
			success : function(resp) {
				$("#pincodeSelect").empty();
				$("#pincodeSelect").append('<option value="">--Select--');
				$.each(resp, function(key, value) {
					nKey = parseInt(key) + 1;
					$("#pincodeSelect").append('<option value="'+resp[key].AREA_ID+'"> '+ resp[key].AREA_NAME + '   '+ resp[key].PINCODE + '');
				});
				
			},
		});
	}
	function populateLandmark(){
		var areaId = $("#pincodeSelect").val();
		$.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 109,
						param : 1 + "~" + areaId,
					},
					success : function(resp) {
						$("#landMarkList").empty();
						$("#landMarkList").append('<option rel="" value="">--Select--');
						$.each(resp,function(key, value) {
						 nKey = parseInt(key) + 1;
						 $("#landMarkList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
						});
					},
				});
	}
	function populateStreet(){
		landmarkId =($("#landMarkList option[value='" + $('#landMark').val() + "']").attr('rel'));
		$.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 109,
						param : 2 + "~" + landmarkId,
					},
					success : function(resp) {
						$("#streetList").empty();
						$("#streetList").append('<option rel="" value="">--Select--');
						$.each(resp,function(key, value) {
						nKey = parseInt(key) + 1;
						$("#streetList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
						
					});
						
					},
				});
	}
	function populateHouseNo(){
		streetId =($("#streetList option[value='" + $('#street').val() + "']").attr('rel'));
		$.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 109,
						param : 3 + "~" + streetId,
					},
					success : function(resp) {
						$("#houseList").empty();
						$("#houseList").append('<option rel="" value="">--Select--');
						$.each(resp,function(key, value) {
						nKey = parseInt(key) + 1;
						$("#houseList").append('<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
						streetId=resp[key].ADD_MST_ID;
					});
					
					},
				});
	}
	function populateBankNameDtl(){
		$.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 79,
						param : 'Null',
					},
					success : function(resp) {
						$("#bankNameTf").empty();
						$("#bankNameTf").append('<option value="">--Select--');
						$.each(resp, function(key, value) {
							nKey = parseInt(key) + 1;
							$("#bankNameTf").append('<option value="'+resp[key].FIN_MASTER_ID+'"> '+ resp[key].FIN_NAME+'');
						});
					
					},
				});
	}
	function populateBranchName(){
		var bankNameId=$("#bankNameTf").val();
		$.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 164,
						param : "2" + "~" + "1" + "~" + bankNameId + "~" + "null" + "~" + "null" + "~" + "null" + "",
					},
					success : function(resp) {
						$("#branchNameTf").empty();
						$("#branchNameTf").append('<option value="">--Select--');
						$.each(resp, function(key, value) {
							nKey = parseInt(key) + 1;
							$("#branchNameTf").append('<option value="'+resp[key].FIN_MASTER_ID+'"> '+ resp[key].FINANCE_NAME+'');
						});
					
					},
				});
	}
	
	function getProposerData()
	{
		if (validateProposerDetails()) {
			var firstname = $('#firstNameTextfiels').val();
			var lastName = $('#lastnameTextfiels').val();
			var middleName = $('#middleNameTextfiels').val();
			var dob = $('#proposerDOB').val();
			$('#insuredDob1').val(dob);
			$('#firstname1').val(firstname);
			$('#middleName1').val(middleName);
			$('#lastName1').val(lastName);
			$("#insuredDetails").show();
			alert("Success");
			$("#insuredDetails").show();
		}
	}
	function validateProposerDetails(){
	
		if (!$('#initialsSelect').val()) {
			alert("Please Select Initial!!");
			$('#initialsSelect').select2('focus');
			return false;
		}
		if (!$('#firstNameTextfiels').val()) {
			alert("Please Enter First Name!!");
			$('#firstNameTextfiels').focus();
			return false;
		}
		if (!$('#middleNameTextfiels').val()) {
			alert("Please Enter Middle Name!!");
			$('#middleNameTextfiels').focus();
			return false;
		}
		if (!$('#lastnameTextfiels').val()) {
			alert("Please Enter Last Name!!");
			$('#lastnameTextfiels').focus();
			return false;
		}
		if (!$('#proposerDOB').val()) {
			alert("Please Select Date Of Birth!!");
			$('#proposerDOB').empty();
			$('#proposerDOB').focus();
			return false;
		}
		if (!$("input[name='propGender']:checked").val()) {
			alert("Please Select Gender!!");
			return false;
		}
		
		if (!$('#adharTextfiels').val()) {
			alert("Please Enter Aadhar No!!");
			$('#adharTextfiels').focus();
			return false;
		}
		var adharTextfiels=document.getElementById("adharTextfiels")
		 if (adharTextfiels.value.length < 12 || adharTextfiels.value.length > 12) {
	            alert("Aadhar No. is not valid, Please Enter 12 Digit Aadhar No.");
	            $('#adharTextfiels').val("");
	            $('#adharTextfiels').focus();
	            return false;
	       }
		if (!$('#EmailTextfiels').val()) {
			alert("Please Enter Email!!");
			$('#EmailTextfiels').focus();
			return false;
		}
		 var email = document.getElementById('EmailTextfiels');
		    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		    if (!filter.test(email.value)) {
		    alert('Please provide a valid email address');
		    $('#EmailTextfiels').focus();
		    $('#EmailTextfiels').val("");
		    return false;
		 }
		if (!$('#mbTextfiels').val()) {
			alert("Please Enter Mobile No!!");
			$('#mbTextfiels').focus();
			return false;
		}
		var mbTextfiels=document.getElementById("mbTextfiels")
		var mbFilter=/^[7-9][0-9]{9}$/;
		 if (mbTextfiels.value.length < 10 || mbTextfiels.value.length > 10) {
	            alert("Mobile No. is not valid, Please Enter 10 Digit Mobile No.");
	            $('#mbTextfiels').val("");
	            $('#mbTextfiels').focus();
	            return false;
	       }
		 if (!mbFilter.test(mbTextfiels.value)) {
			 alert('Please provide a valid Mobile no');
			    $('#mbTextfiels').focus();
			    $('#mbTextfiels').val("");
			    return false;
		 }
		if (!$('#phTextfiels').val()) {
			alert("Please Enter Phone No!!");
			$('#phTextfiels').focus();
			return false;
		}
		var phoneNo=document.getElementById("phTextfiels")
		 if (phoneNo.value.length < 11 || phoneNo.value.length > 11) {
	            alert("Phone No. is not valid, Please Enter 11 Digit Phone No.");
	            $('#phTextfiels').val("");
	            $('#phTextfiels').focus();
	            return false;
	       }
		if (!$('#nationalitySelect').val()) {
			alert("Please Select Nationality!!");
			$('#nationalitySelect').select2('focus');
			return false;
		}

		if (!$('#countrySelect').val()) {
			alert("Please Select Country!!");
			$('#countrySelect').select2('focus');
			return false;
		}

		if (!$('#proposerState').val()) {
			alert("Please Select State!!");
			$('#proposerState').select2('focus');
			return false;
		}
		if (!$('#districtSelect').val()) {
			alert("Please Select District!!");
			$('#districtSelect').select2('focus');
			return false;
		}
		if (!$('#proposerCity').val()) {
			alert("Please Select City!!");
			$('#proposerCity').select2('focus');
			return false;
		}
		if (!$('#pincodeSelect').val()) {
			alert("Please Select Pincode!!");
			$('#pincodeSelect').select2('focus');
			return false;
		}
		if (!$('#landMark').val()) {
			alert("Please Enter Or Select LandMark!!");
			$('#landMark').focus();
			return false;
		}
		if (!$('#street').val()) {
			alert("Please Enter Or Select Street!!");
			$('#street').focus();
			return false;
		}

		if (!$('#House').val()) {
			alert("Please Enter Or Select House!!");
			$('#House').focus();
			return false;
		}

		$('#proposerDOB').empty();
		var dob = $('#proposerDOB').val();
		var date = dob.split('/')[2];
		var d = new Date();
		var lipday = (d.getFullYear() - date);
		if (lipday < 18.000 || (!lipday == 18.000)) {
			alert('Your age should be 18 or greater than 18 yrs!!!');
			$('#proposerDOB').empty();
			$('#proposerDOB').focus();
			
			return false;
		}
		
		return true;
	}
	
	function clearProposerData(){
		document.getElementById("firstNameTextfiels").value="";
		document.getElementById("middleNameTextfiels").value="";
		document.getElementById("lastnameTextfiels").value="";
		document.getElementById("proposerDOB").value="";
		 $('input[name=propGender]').prop('checked', false);
		 document.getElementById("adharTextfiels").value="";
		 document.getElementById("EmailTextfiels").value="";
		 document.getElementById("mbTextfiels").value="";
		 document.getElementById("phTextfiels").value="";
		 $("#initialsSelect").select2('val',null);
		 $("#nationalitySelect").select2('val',null);
		 $("#countrySelect").select2('val',null);
		 $("#proposerState").select2('val','--Select--');
		 $("#districtSelect").select2('val','--Select--');
		 $("#proposerCity").select2('val','--Select--');
		 $("#pincodeSelect").select2('val','--Select--');
		 $("#landMark").val("");
		 $("#landMarkList").val("");
		 $("#street").val("");
		 $("#streetList").val("");
		 $("#House").val("");
		 $("#houseList").val("");
	}
	
	function isNumber(evt) {
	     evt = (evt) ? evt : window.event;
	     var charCode = (evt.which) ? evt.which : evt.keyCode;
	     if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	        
	         return false;
	     }
	     return true;
	 }
	function getNoOfRows(){
		var members=$("#noOfMembersSelect :selected").text();
			   for(var j=2;j<=members;++j){ 
				 AddRow();
				 addDiseaseTable();
			}    
			 members=0;  
	}
	
	function AddRow(){
		row = '<tr id="tableRow'+k+'">'+
		'<td>'+k+'</td>'+
		'<td><input type="text" id="firstname'+k+'"required="required"></td>'+
		'<td><input type="text" id="middleName'+k+'" required="required"></td>'+
		'<td><input type="text" id="lastName'+k+'"required="required">'+
		'</td><td><select id="relation'+k+'">'+
		'<option></option>'+
		'</td><td><select id="gender'+k+'">'+
		'<option>--Select--</option></td>'+
		'<td ><input class="datePicker" type="text"id="insuredDob'+k+'"></td>'+
		'<td><select id="currentSickness'+k+'">'+
		'<option>--Select--</option>'+
		'<option>Yes</option><option>No</option></td>'+
		'<td><select id="Occupation'+k+'">'+
		'<option>--Select--</option></td>'+
		'<td><input type="text" id="grossAnnualIncome'+k+'" required="required" onkeypress="return isNumber(event)"></td>'+
		'<td><input type="text" id="SIA'+k+'" onkeypress="return isNumber(event)"></td>'+
		'<td><input type="text" id="SIB'+k+'" onkeypress="return isNumber(event)"></td>'+
		'<td><input type="text"  id="SIC'+k+'" onkeypress="return isNumber(event)"></td>'+
		'<td><input type="text" id="SID'+k+'" onkeypress="return isNumber(event)"></td>'+
		'<td><input type="text"  id="sumInsured'+k+'" disabled="disabled"></td></tr>';
		 $('#insuredDetailsTable').append(row);
		
		 $.ajax({
 			url :"${pageContext.request.contextPath}/getRecordLst",
 			type : 'post',
 			dataType : 'json',
 			async : false,
 			data : {
 				sqlMstId : 175,
 				param : null,
 			},
 				  success : function(resp) {
 					$.each(resp, function(key, value) {
 						nKey = parseInt(key) + 1;
 						 checkBox='<input type="checkbox" '+
 						'id="check'+nKey+'" '+
 						' name="checkbox"> <input type="text" class="datepicker"> ';
 						var diseaseRow="<tr><td>" + resp[key].DISEASE_NAME  + "</td>"+
 						"<td>" + checkBox +"</td><td id=spouse style=display:none>"+checkBox+"</td><td id=son style=display:none>"+checkBox+"</td><td id=daughter style=display:none>"+checkBox+"</td></tr>";
 						 $("#diseaseDetailsTable tbody").append(diseaseRow);
 						
 					});
 				},
 		});
		 
		$.ajax({
			url :"${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 239,
				param : k,
			},
				  success : function(resp) {
					$.each(resp, function(key, value) {
						nKey = parseInt(key) + 1;
						$('#relation'+k+'').append(
								'<option value="'+resp[key].DET_ID+'"> '
								+ resp[key].DET_NAME + '');
					});
				},
		});
		$.ajax({
			url :"${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '27',
			},
				  success : function(resp) {
					$.each(resp, function(key, value) {
						nKey = parseInt(key) + 1;
						$('#gender'+k+'').append(
								'<option value="'+resp[key].DET_ID+'"> '
								+ resp[key].DET_NAME + '');
					});
				},
		});
		$.ajax({
			url :"${pageContext.request.contextPath}/getRecordLst",
			type : 'post',
			dataType : 'json',
			async : false,
			data : {
				sqlMstId : 70,
				param : '1',
			},
				  success : function(resp) {
					$.each(resp, function(key, value) {
						nKey = parseInt(key) + 1;
						$('#Occupation'+k+'').append(
								'<option value="'+resp[key].DET_ID+'"> '
								+ resp[key].DET_NAME + '');
					});
				},
		});
		
		/* var members=$("#noOfMembersSelect :selected").text();
			 if(members==2){
			 alert(members); 
				$("#spouse").show();
			}  */
			
	  var relation= $('#relation'+k+'').text();
		  var members='<th>'+relation+'</th>';
		 $("#diseaseTableHead").append(members);
		 
		
		 
		$("#relation"+k+",#gender"+k+",#Occupation"+k+",#currentSickness"+k+"").select2({
			allowClear : true,
			maximumSelectionSize : 1,
		});
		
		 	k++;
		 	 $('.datePicker').datepicker({
			       autoclose: true,
			       format:'dd/mm/yyyy'
			    });
	}
	
	
	function getDiseaseDetails()
	{
		 $("#diseaseDetails").show();	
	}
	function hideDiseaseDetails(){
		$("#diseaseDetails").hide();	
		$("#nomineeDetailsTab").show(); 
		$('#collapseThree').collapse('show');
	}
	
	function validateInsuredDetail()
	{
		if(validateSelf()){
			alert("Success");
		  $("#medicalDetails").show();
		}
	}
	
	
	function validateSelf() {
		var members=$("#noOfMembersSelect :selected").text();
		for(var j=1;j<k;j++)
		{
		 if (!$('#firstname'+j+'').val()) {
				alert("Please Enter First Name!!");
				$('#firstname'+j+'').focus();
				return false;
			}
			if (!$('#middleName'+j+'').val()) {
				alert("Please Enter Middle Name!!");
				$('#middleName'+j+'').focus();
				return false;
			}
			if (!$('#lastName'+j+'').val()) {
				alert("Please Enter Last Name!!");
				$('#lastName'+j+'').focus();
				return false;
			}
			if ($('#relation'+j+'').val()=="--Select--") {
				alert("Please Select Relation!!");
				$('#relation'+j+'').select2('focus');
				return false;
			}
			if ($('#gender'+j+'').val()=="--Select--") {
				alert("Please Select Gender!!");
				$('#gender'+j+'').select2('focus');
				return false;
			}
			if (!$('#insuredDob'+j+'').val()) {
				alert("Please Select Date Of Birth!!");
				$('#insuredDob'+j+'').focus();
				return false;
			}
			if ($('#currentSickness'+j+'').val()=="--Select--") {
				alert("Please Select Date Of Birth!!");
				$('#insuredDob'+j+'').focus();
				return false;
			}
			if ($('#Occupation'+j+'').val()=="--Select--") {
				alert("Please Select Occupation!!");
				$('#Occupation'+j+'').select2('focus');
				return false;
			}
			
			if (!$('#grossAnnualIncome'+j+'').val()) {
				alert("Please Enter Gross Annual Income!!");
				$('#grossAnnualIncome'+j+'').focus();
				return false;
			} 
		
		var selfGrossAnnualIncome = $('#grossAnnualIncome1').val();
		if (selfGrossAnnualIncome > 10000000 || (!selfGrossAnnualIncome == 10000000)) {
			alert("Gross Annual Income of Self Should not be greater than 10000000")
			$('#grossAnnualIncome1').val("");
			$('#grossAnnualIncome1').focus();
			return false;
		}
		
		if((!$('#SIA1').val())&&(!$('#SIB1').val())&&(!$('#SIC1').val())&&(!$('#SID1').val())){
			$('#sumInsured1').val("0");
		}else{
			var sia = parseInt($('#SIA1').val(), 10);
			var sib = parseInt($('#SIB1').val(), 10);
			var sic = parseInt($('#SIC1').val(), 10);
			var sid = parseInt($('#SID1').val(), 10);
			var sum = sia + sib + sic + sid;
			var grossAnnualIncome=selfGrossAnnualIncome / 12;
			var selfSi= 200 *grossAnnualIncome ;
			 var SI;
            if (selfSi <= 2000000) {
                SI = selfSi;
            } else {
                SI = 2000000;
            }
			if (sum > SI) {
               alert("SmInsured should be less than " + SI);
           }else{
			$('#sumInsured1').val(sum);
           }
		}
		$('#insuredDob1').empty();
		var insuredDob1 = $('#insuredDob1').val();
		var date = insuredDob1.split('/')[2];
		var d = new Date();
		var lipday = (d.getFullYear() - date);
		if (lipday < 18.000 || (!lipday == 18.000)) {
			alert('Your age should be 18 or greater than 18 years!!!');
			$('#insuredDob1').val();
			$('#insuredDob1').focus();
			return false;
		}
		
		}
		 var $rows = $('#insuredDetailsTable tr'),
		    visibleCount = $rows.filter(':visible').length; 
		if(members!=1){
			
			if((members==2||members==3||members==4)&&visibleCount==2)
				{
				/* alert("Please Add Row"); */
				return true;
				}
			else{
		var insuredDob2 = $('#insuredDob2').val();
		var date = insuredDob2.split('/')[2];
		var d = new Date();
		var lipday = (d.getFullYear() - date);
		if (lipday < 18.000 || (!lipday == 18.000)) {
			alert('Your age should be 18 or greater than 18 years!!!');
			return false;
		}
		var spouseGrossAnnualIncome = $('#grossAnnualIncome2').val();
		var spouseWorking=$("input[name='optradio1']:checked"). val();
		if((!$("input[name='optradio1']:checked"). val())||spouseWorking=="No"){
			var selfSI= $('#sumInsured1').val();
			var spouseSi=selfSI * (0.50);
			var si2;
			if (spouseSi <= 100000) {
                si2 = spouseSi + "";
                $('#sumInsured2').val(si2);
		}else {
            si2 = 100000 + "";
            $('#sumInsured2').val(si2);
        }
		}
		if (spouseGrossAnnualIncome == "" && spouseWorking == "Yes") {
			alert('Please Enter Gross Annual Income Of Spouse!!!');
			return false;
		}
		}
		}
		if(members!=1&&members!=2){
			if((members==3||members==4)&&(visibleCount==2||visibleCount==3))
			{
			/* alert("Please Add Row"); */
			return true;
			}
		var selfSI= $('#sumInsured1').val();
		var childrenSi=selfSI * (0.25);
		var si2;
		if (childrenSi <= 50000) {
            si2 = childrenSi + "";
            $('#sumInsured3').val(si2);
		}
		else {
            si2 = 50000 + "";
            $('#sumInsured3').val(si2);
        }
		var insuredDob3 = $('#insuredDob3').val();
		var date = insuredDob3.split('/')[2];
		var d = new Date();
		var lipday = (d.getFullYear() - date);
		if ((lipday < 5 || (!lipday == 5)) || (lipday > 25 || (!lipday == 25))) {
			alert(' Age should be between 5 years to 25 years !!!');
			return false;
		}
		}
		if(members!=1&&members!=2&&members!=3){
			if(members==4&&(visibleCount==2||visibleCount==3||visibleCount==4))
			{
			/* alert("Please Add Row"); */
			return true;
			}
		var insuredDob4 = $('#insuredDob4').val();
		var dobYear = insuredDob4.split('/')[2];
		var d = new Date();
		var lipday = (d.getFullYear() - dobYear);
		if ((lipday < 5 || (!lipday == 5)) || (lipday > 25 || (!lipday == 25))) {
			alert('Your age should be between 5 to 25 !!!');
			return false;
		}
		var selfSI= $('#sumInsured1').val();
		var childrenSi=selfSI * (0.25);
		var si2;
		if (childrenSi <= 50000) {
            si2 = childrenSi + "";
            $('#sumInsured4').val(si2);
		}
		else {
            si2 = 50000 + "";
            $('#sumInsured4').val(si2);
        }
		}
		return true;
	}
	function showPreviousDetails(){
		$("#previousPolicy").show();
	}
	function hidePreviousDetails(){
		$("#previousPolicy").hide();
	}
	var i=1;
	function previousPolicyAddNewMember(){
		if(validatePreviousPolicyDetails()){
			var row = '<tr>'+
			'<td>'+i+'</td>'+
			'<td><input type="text" name="member" id="member'+i+'"></td>'+
			'<td><select id="gic'+i+'">'+
			'<option>--Select--</option></select></td>'+
			'<td><input type="text" name="policyNo" id="policyNo'+i+'" onkeypress="return isNumber(event)"> </td>'+
			'<td><input type="text" name="insuredSince" id="insuredSince'+i+'" maxlength="4" onkeypress="return isNumber(event)"> </td>'+
			'<td ><input class="datePicker" type="text" id="policyStartDate'+i+'"></td>'+
			'<td><input class="datePicker" type="text" id="policyEndDate'+i+'"></td>'+
			'<td><input type="text" name="prevSumInsured" id="prevSumInsured'+i+'" onkeypress="return isNumber(event)"></td>'+
			'<td><select id="claim'+i+'">'+
			'<option>--Select--</option><option>Yes</option><option>No</option></td></tr>';
			$('#previousTable').append(row);
			$.ajax({
				url : "${pageContext.request.contextPath}/getInsFindFormData?"+
						"pkg_name=PKG_INS_FIND&proc_name=find_gic"+
						"&table_name=RGCP&str_company_type="+
						"&str_gic=&str_gicbid=&str_prod="+
						"&str_discnm=&str_rgrp=&str_state="+
						"&str_city=&str_prpsl=&str_spnm="+
						"&str_mgnm=&str_productcode=0&str_type=0"+
						"&str_type_1=0&str_kg_from=0&str_kg_to=0"+
						"&str_fueltype=&str_veh=&str_mod=&str_var="+
						"&str_policy_age=&str_hbbid=&str_user_level=0"+
						"&str_user_id=0&str_login_type=0&str_gap=0&str_ageto=0",
				type : 'post',
				dataType : 'json',
				async : false,
					  success : function(resp) {
						  $('#gic'+i+'').empty();
							$('#gic'+i+'').append('<option value="">--Select--');
						$.each(resp, function(key, value) {
							nKey = parseInt(key) + 1;
							$('#gic'+i+'').append(
									'<option value="'+resp[key].GIC_ID+'"> '
									+ resp[key].GIC_NAME + '');
						});
					},
					error:function(resp){
						alert("Gic Error");
					},
			});
			$("#gic"+i+",#claim"+i+"").select2({
				allowClear : true,
				maximumSelectionSize : 1,
			});
			i++;
			 $('.datePicker').datepicker({
			       autoclose: true,
			       format:'dd/mm/yyyy'
			    });
	}
	}
	function validatePreviousPolicyDetails(){
		var newDate=new Date();
		var currentYear=newDate.getFullYear();
		var startYear=currentYear-100;
		for(var j=1;j<i;j++)
		{
			if(!$('#member'+j+'').val()){
				alert("Please Enter Member Name");
				$('#member'+j+'').val("");
	        	 $('#member'+j+'').focus();
	        	 return false;
			}
			if($('#gic'+j+'').val()=="--Select--"){
				alert("Please Select Gic");
				$('#gic'+j+'').val("");
	        	 $('#gic'+j+'').focus();
	        	 return false;
			}
			if(!$('#policyNo'+j+'').val()){
				alert("Please Enter Policy No");
				$('#policyNo'+j+'').val("");
	        	 $('#policyNo'+j+'').focus();
	        	 return false;
			}
			if(!$('#insuredSince'+j+'').val()){
				alert("Please Enter Insured Since");
				$('#insuredSince'+j+'').val("");
	        	 $('#insuredSince'+j+'').focus();
	        	 return false;
			}
			if(!$('#policyStartDate'+j+'').val()){
				alert("Please Select Policy Start date");
				$('#policyStartDate'+j+'').val("");
	        	 $('#policyStartDate'+j+'').focus();
	        	 return false;
			}
			if(!$('#policyEndDate'+j+'').val()){
				alert("Please Select Policy End date");
				$('#policyEndDate'+j+'').val("");
	        	 $('#policyEndDate'+j+'').focus();
	        	 return false;
			}
			if(!$('#prevSumInsured'+j+'').val()){
				alert("Please Enter Sum Insured");
				$('#prevSumInsured'+j+'').val("");
	        	 $('#prevSumInsured'+j+'').focus();
	        	 return false;
			}
			if(!$('#claim'+j+'').val()){
				alert("Please Select claim");
				$('#claim'+j+'').val("");
	        	 $('#claim'+j+'').focus();
	        	 return false;
			}
		var insuredSince=$('#insuredSince'+j+'').val();
		if (insuredSince >= startYear && insuredSince <= currentYear) {
			$('#insuredSince'+j+'').val(insuredSince);
			return true;
        } else {
        	 alert("Entered Year Should be between" + startYear + "-" + currentYear);
        	 $('#insuredSince'+j+'').val("");
        	 $('#insuredSince'+j+'').focus();
        	 return false;
        }
		}
		
		return true;
	}
	function showPreviousPolicyTab(){
		if(validateNominee()){
		   $("#previousPolicyDetails").show();
		}
	}
	function validateNominee() {
		if (!$('#nomDtlInitial').val()) {
			alert("Please Select Initial!!");
			$('#nomDtlInitial').focus();
			return false;
		}
		if (!$('#nomDtlFname').val()) {
			alert("Please Enter First Name!!");
			$('#nomDtlFname').focus();
			return false;
		}
		if (!$('#nomDtlMname').val()) {
			alert("Please Enter Middle Name!!");
			$('#nomDtlMname').focus();
			return false;
		}
		if (!$('#nomDtlLname').val()) {
			alert("Please Enter Last Name!!");
			$('#nomDtlLname').focus();
			return false;
		}
		if (!$('#nomDtlRelation').val()) {
			alert("Please Select Relation!!");
			$('#nomDtlRelation').focus();
			return false;
		}
		if (!$('#nomDtlAdharNo').val()) {
			alert("Please Enter Adhar No!!");
			$('#nomDtlAdharNo').focus();
			return false;
		}
		var adharTextfiels=document.getElementById("nomDtlAdharNo")
		 if (adharTextfiels.value.length < 12 || adharTextfiels.value.length > 12) {
	            alert("Aadhar No. is not valid, Please Enter 12 Digit Aadhar No.");
	            $('#nomDtlAdharNo').val("");
	            $('#nomDtlAdharNo').focus();
	            return false;
	       }
		if (!$('#nomDtlEmail').val()) {
			alert("Please Enter Email!!");
			$('#nomDtlEmail').focus();
			return false;
		}
		 var email = document.getElementById('nomDtlEmail');
		    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		    if (!filter.test(email.value)) {
		    alert('Please provide a valid email address');
		    $('#nomDtlEmail').focus();
		    $('#nomDtlEmail').val("");
		    return false;
		 }
		if (!$('#nomDtlMobileNo').val()) {
			alert("Please Enter Mobile No!!");
			$('#nomDtlMobileNo').focus();
			return false;
		}
		var mbTextfiels=document.getElementById("nomDtlMobileNo")
		var mbFilter=/^[7-9][0-9]{9}$/;
		 if (mbTextfiels.value.length < 10 || mbTextfiels.value.length > 10) {
	            alert("Mobile No. is not valid, Please Enter 10 Digit Mobile No.");
	            $('#nomDtlMobileNo').val("");
	            $('#nomDtlMobileNo').focus();
	            return false;
	       }
		 if (!mbFilter.test(nomDtlMobileNo.value)) {
			 alert('Please provide a valid Mobile no');
			    $('#mbTextfiels').focus();
			    $('#mbTextfiels').val("");
			    return false;
		 }
		
		var phoneNo=document.getElementById("nomDtlphoneNo")
		 if (phoneNo.value.length < 11 || phoneNo.value.length > 11) {
	            alert("Phone No. is not valid, Please Enter 11 Digit Phone No.");
	            $('#nomDtlphoneNo').val("");
	            $('#nomDtlphoneNo').focus();
	            return false;
	       }
		
		return true;
	}
	function showNomineeDetails()
	{
		$("#nomineeDetailsTab").show();
		$('#collapseThree').collapse('show');
	}
	function showCompanies()
	{
		$.ajax({
			url : "${pageContext.request.contextPath}/healthCalculator?",
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(resp) {
				$.each(resp,function(key, value) {
				nKey = parseInt(key) + 1;
				alert(resp.msg); 
			});
			
			},
			error : function(resp) {
				alert(resp.msg); 
			},
		});
	}
	function showProposerDeatils()
	{
		if(validateProposerDtl()){
		$("#proposerDtlTab").show();
		 $('#collapseOne').collapse('show');
		}
	}
	function validateProposerDtl()
	{
		if (!$('#stateSelect').val()) {
			alert("Please Select State!!");
			$('#stateSelect').select2('focus');
			return false;
		}
		if (!$('#citySelect').val()) {
			alert("Please Select City!!");
			$('#citySelect').select2('focus');
			return false;
		}
		if (!$('#occupationSelect').val()) {
			alert("Please Select Occupation!!");
			$('#occupationSelect').select2('focus');
			return false;
		}
		if (!$('#coverTypeSelect').val()) {
			alert("Please Select Cover Type!!");
			$('#coverTypeSelect').select2('focus');
			return false;
		}
		if (!$('#noOfMembersSelect').val()) {
			alert("Please Select No Of Members!!");
			$('#noOfMembersSelect').select2('focus');
			return false;
		}
		if (!$('#SumInsuredSelect').val()) {
			alert("Please Select Sum insured!!");
			$('#SumInsuredSelect').select2('focus');
			return false;
		}
		if (!$('#policyStartDate').val()) {
			alert("Please Select Policy Start Date!!");
			$('#policyStartDate').focus()
			return false;
		}
		if (!$('#policyEndDate').val()) {
			alert("Please Select Policy Start Date!!");
			$('#policyStartDate').focus()
			return false;
		}
		return true;
	}
	function showCompanyDtlTab(){
		 $("#showCompanies").show();    
	}
	
	function fillAddressDetails()
	{
		        var country = $("#countrySelect").select2("val");
				var propState = $("#proposerState").select2("val");
				var district = $("#districtSelect").select2("val");
				var city = $("#proposerCity").select2("val");
				var pincode = $("#pincodeSelect").select2("val");
				var landmark = $("#landMark").val();
				var street = $("#street").val();
				var houseNo = $("#House").val();
		      
		        var checked=$("#nomCheckBox").prop("checked");
		      
		      if(checked){
		    	  nomDtlCountry();
		  		$("#nomDtlCountry").select2("val",country);
		  		nomDtlState();
		  		$("#nomDtlState").select2("val",propState);
		  		nomDtlDistrict();
		  		$("#nomDtlDistrict").select2("val",district);
		  		nomDtlCity();
		  		$("#nomDtlCity").select2("val",city);
		  		nomDtlPincode();
		  		$("#nomDtlPincode").select2("val",pincode);
		  		nomDtlLandmark();
		  		$("#nomDtlLandmark").val(landmark);
		  		nomDtlStreet();
		  		$("#nomDtlStreet").val(street);
		  		 nomDtlHouseNo();
		  		$("#nomDtlHouseNo").val(houseNo);
		      }else{
			  	$("#nomDtlCountry").select2("val","");
		  		$("#nomDtlState").select2("val","");
		  		$("#nomDtlDistrict").select2("val","");
		  		$("#nomDtlCity").select2("val","");
		  		$("#nomDtlPincode").select2("val","");
		  		$("#nomDtlLandmark").val("");
		  		$("#nomDtlStreet").val("");
			  	$("#nomDtlHouseNo").val("");
		      }
		     
		 } 
	function clearNomineeData(){
		document.getElementById("nomFirstNameTextField").value="";
		document.getElementById("nomMiddleNameTextfiels").value="";
		document.getElementById("nomLastnameTextfiels").value="";
		document.getElementById("nomAdharTextfiels").value="";
		document.getElementById("nomDate").value="";
		 $('input[name=nomSameAddress]').prop('checked', false);
		 document.getElementById("emailTextfiels").value="";
		 document.getElementById("phTextfiels").value="";
		 document.getElementById("mbTextfiels").value="";
		 $("#nomInitialsSelect").select2('val',null);
		 $("#nomRelation").select2('val',null);
		 $("#nomCountrySelect").select2('val',null);
		 $("#nomState").select2('val','--Select--');
		 $("#nomDistrict").select2('val','--Select--');
		 $("#nomCity").select2('val','--Select--');
		 $("#nomPincode").select2('val','--Select--');
		 $("#nomLandMark").val("");
		 $("#nomLandMarkList").val("");
		 $("#nomStreet").val("");
		 $("#nomStreetList").val("");
		 $("#nomHouse").val("");
		 $("#nomHouseNoList").val("");
	}
	</script>
	<style type="text/css">
	
#header-fixed {
	position: fixed;
	top: 0px;
	display: none;
	background-color: white;
}

div.dataTables_wrapper {
	width: 800px;
	margin: 0 auto;
}

#abcIS {
	width: 100%;
	height: 100%;
	opacity: 95;
	top: 0;
	left: 0;
	display: none;
	position: fixed;
	background-color: #313131;
	overflow: auto
}

img#close {
	position: absolute;
	right: -14px;
	top: -14px;
	cursor: pointer
}

div#popupContact {
	position: absolute;
	left: 40%;
	top: 17%;
	margin-left: -202px;
	font-family: 'Raleway', sans-serif;
}

.main {
	height: 200px;
	overflow-x: scroll;
}

.div2 {
	height: 210px;
}

#abcIS {
	width: 100%;
	height: 100%;
	opacity: 95;
	top: 0;
	left: 0;
	display: none;
	position: fixed;
	background-color: #313131;
	overflow: auto
}

.leftscroll {
	position: absolute;
	top: 95px;
	left: 247px;
	width: 17px;
	height: 265px;
	overflow-y: scroll;
}

@media only screen and (max-width:768px) {
	.leftscroll {
		position: absolute;
		top: 99px;
		left: 32px;
		width: 17px;
		height: 265px;
		overflow-y: scroll;
	}
}

.div1 {
	width: 17px;
	height: 830px;
}
	</style>
</head>
<body>
<div class="container" style="margin-top: 130px;">

		<div class="row" align="right">
			<button type="button" class="btn btn-primary" onclick="closeForm();">&nbsp;Close</button>
		</div>
		<br>
		<div class="row" style="background-color: #4cbcce;">
		<div class="panel-head" style="background-color: #d1bebe; ">&nbsp;&nbsp; Basic Details</div>
		<div class="panel-body" id="collapseTwo" style="background-color: #4cbcce;   class="panel-collapse collapse"role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"	style="margin: auto;">
		<div class="row" style="padding: 5px">
		<div class="col-sm-2">
		<div class="col-md-2">
        <label for="state"><font color="#ffffff">State</font>
        </label></div>
	    <select id="stateSelect" onchange="populateCity()">	
	    <option>--Select--</option>
	    </select>
	    </div>
		
		<div class="col-sm-2">
		<div class="col-md-2">
       <label for="city" ><font color="#ffffff">City</font>
       </label></div>
	   <select id="citySelect" >	
	    <option>--Select--</option>
	    </select>
	    </div>
	    
		<div class="col-sm-2">
		<div class="col-md-2">
       <label for="occupation"><font color="#ffffff">Occupation</font></label></div>
	   <select id="occupationSelect" >	
	    <option>--Select--</option>
	    </select>
	    </div>
		<div class="col-sm-2">
		<div class="col-sm-12">
       <label for="coverType"><font color="#ffffff">Cover Type</font></label></div>
	   <select id="coverTypeSelect" >	
	    <option>--Select--</option>
	    </select>
	    </div>
		
		<div class="col-sm-2">
		<div class="col-sm-12">
       <label for="noOfMembers"><font color="#ffffff">No.Of Members</font></label></div>
	   <select id="noOfMembersSelect" onchange="getNoOfRows()" >	
	    <option>--Select--</option>
	    </select>
	    </div>
		
		<div class="col-sm-2">
		<div class="col-sm-12">
       <label for="sumInsured"><font color="#ffffff">Sum Insured</font></label></div>
	   <select id="SumInsuredSelect" >	
	    <option>--Select--</option>
	    </select>
	    </div>
		</div>
	

		 <div class="row" style="padding: 5px">
		<div class="col-sm-2">
		<div class="col-sm-12">
		 <label for="policyStartDate"><font color="#ffffff">Policy Start Date</font></label></div>
		<input type="text" id="policyStartDate" class="datePicker" onchange="getPolicyEndDate()" style="width: 135px">
		</div>
		
		
		<div class="col-sm-2">
		<div class="col-sm-12">
		 <label for="policyEndDate"><font color="#ffffff">Policy End Date</font></label></div>
		<input type="text" id="policyEndDate" class="datePicker" disabled="disabled" style="width: 135px">
		</div>
		</div>
		<br>
		<div class="row"style="padding: 5px">
				<div class="col-md-1">
				<button type="button" class="btn btn-primary" style="width: 110px" onclick="showProposerDeatils()">Next</button>
				</div>
				</div>		
		
		</div>
		</div>
		
		<br>
	<div class="row" style="background-color: #4cbcce;" id="proposerDtlTab">
		<div class="panel-title" style="background-color: #d1bebe;">
				&nbsp;&nbsp;
				 <a  class="collapsed" data-toggle="collapse" data-parent="#accordion"	href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" >
				  Proposer Details
				 </a>
			</div>
			<br>
			<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" class="main wrapper2" style="margin: auto;">
            <div class="gap" style="height: 10px; width: 475px"></div>
					<div class="row" style="padding: 5px">
					<div class="col-md-3">
					<div class="col-md-6">
					<label for="initial"><font color="#ffffff" style="margin-left:2px" >* Initial</font></label>
					</div>
					<div class="col-md-5">
					<select id="initialsSelect" style="width: 145px">
								<option>--Select--</option>
							</select>
					</div>
					</div>
					
					<div class="col-md-3">
					<div class="col-md-5">
					<label for="firstName"><font color="#ffffff">*First Name</font></label>
					</div>
					<div class="col-md-5">
					<input id="firstNameTextfiels" type="text" >
					</div>
					</div>
					
					<div class="col-md-3">
					<div class="col-md-6">
					<label for="firstName"><font color="#ffffff">*Middle Name</font></label>
					</div>
					<div class="col-md-4">
					<input id="middleNameTextfiels" type="text" style="width: 160px">
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-5">
					<label for="firstName"><font color="#ffffff">*Last Name</font></label>
					</div>
					<div class="col-md-5">
					<input id="lastnameTextfiels" type="text" style="width: 145px">
					</div>
					</div>
					</div>
					
					
					<div class="row" style="padding: 5px">
					<div class="col-md-3">
					<div class="col-md-6">
					<label for="dob"><font color="#ffffff">* Date Of Birth</font></label>
					</div>
					<div class="col-md-4">
					<input id="proposerDOB" type="text" class="datePicker" style="width: 145px">
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-3">
					<label for="Gender"><font color="#ffffff">Gender</font></label>
					</div>
					<div class="col-md-9">
					<input type="radio" name="propGender"style="width: 12px"  value="male"><font color="#ffffff">&nbsp;Male</font>
					<input type="radio" name="propGender"  value="female"><font color="#ffffff">Female</font>
					<input type="radio" name="propGender" value="other"><font color="#ffffff" >&nbsp;Other</font>
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-6">
					<label for="adhar"><font color="#ffffff">Aadhar No</font></label>
					</div>
					<div class="col-md-4">
					<input id="adharTextfiels" type="text" style="width: 160px" onkeypress="return isNumber(event)" maxlength="12">
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-5">
					<label for="firstName"><font color="#ffffff">Email</font></label>
					</div>
					<div class="col-md-5">
					<input id="EmailTextfiels" type="email" style="width: 145px" >
					</div>
					</div>
					</div>
					
					
					<div class="row" style="padding: 5px">
					<div class="col-md-3">
					<div class="col-md-6">
                    <label for="mb"><font color="#ffffff">* Mobile</font></label>					
                    </div>
					<div class="col-md-5">
					<input id="mbTextfiels"type="text"
					 onkeypress="return isNumber(event)" maxlength="10" 
					 style="width: 145px">
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-5">
                    <label for="ph"><font color="#ffffff"> Phone No</font></label>
                    </div>
					<div class="col-md-5">
					<input id="phTextfiels"type="text" 
					onkeypress="return isNumber(event)" maxlength="11"  >
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-6">
                    <label for="Nationality"><font color="#ffffff">
                    *Nationality</font></label>					
                    </div>
					<div class="col-md-4">
					<select id="nationalitySelect" 
					onchange="populateCountry()"style="width: 160px">
					<option>--Select--</option>
				   </select>
					</div>
					</div>
					</div>
					
					
					<div class="row" style="padding: 5px">
					<div class="col-md-3">
					<div class="col-md-6">
                    <label for="Country"><font color="#ffffff">* Country</font></label>		
	                </div>
					<div class="col-md-5">
					<select id="countrySelect" onchange="populateProposerState()"
					style="width: 145px">
					<option>--Select--</option>
					</select>	
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-5">
                    <label for="State" ><font color="#ffffff">* State</font></label>		
          			</div>
					<div class="col-md-5">
					<select id="proposerState" onchange="populateDistrict();"  
					style="width: 145px">
					<option>--Select--</option>
					</select>
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-6">
                    <label for="District"><font color="#ffffff">* District</font></label>					</div>
					<div class="col-md-4">
					<select id="districtSelect" onchange="populateProposerCity()" 
					style="width: 160px">
					<option>--Select--</option>
					</select>
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-5">
                    <label for="City"><font color="#ffffff">* City</font></label>				
                 	</div>
					<div class="col-md-5">
					<select id="proposerCity" onchange="populatePincode()"
					style="width: 145px">
					<option>--Select--</option>
					</select>
					</div>
					</div>
					</div>
					
					
					<div class="row" style="padding: 5px">
					<div class="col-md-3">
					<div class="col-md-6">
                    <label for="Pincode"><font color="#ffffff">* Pincode</font></label>	
                    </div>
					<div class="col-md-5">
					<select id="pincodeSelect" onchange="populateLandmark()" style="width: 145px">
				    <option>--Select--</option>
				    </select>	
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-5">
                    <label for="Landmark"><font color="#ffffff">*Landmark</font></label>			
					</div>
					<div class="col-md-5">
					<input id="landMark" list="landMarkList" onchange="populateStreet();" >
					<datalist id="landMarkList"></datalist>
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-6">
                    <label for="Street"><font color="#ffffff">* Street</font></label>		
                   	</div>
					<div class="col-md-4">
					<input id="street" list="streetList"  onchange="populateHouseNo();"
					style="width: 160px">
							<datalist id="streetList"></datalist>
					</div>
					</div>
					
					
					<div class="col-md-3">
					<div class="col-md-5">
                    <label for="house"><font color="#ffffff">* House No</font></label>	
					</div>
					<div class="col-md-5">
					<input id="House" list="houseList" style="width: 145px">
							<datalist id="houseList"></datalist>
					</div>
					</div>
				<div class="row"style="padding: 5px"></div>
				<div class="row"style="padding: 5px">
				<div class="col-md-10">
				</div>
				<div class="col-md-2">
				<button type="button" class="btn btn-primary"
				 onclick="getProposerData()">Ok</button>
				 <button type="button" class="btn btn-primary" 
				 onclick="clearProposerData()">Clear</button>
				</div>
				</div>
				</div>
				</div>
				
				</div>
				
				<br>
				
				<div class="row" style="background-color: #4cbcce;"
			id="insuredDetails">
			<div style="background-color: #d1bebe;">&nbsp;&nbsp;Insured
				Details</div>
				<button type="button" class="btn btn-primary"  id="addInsuredDetails" onclick="validateInsuredDetail()">Please Click here to Add Insured Details</button>
			<br>
			<div class="main wrapper2" style="margin: auto;">
				<div class="div2 div4">
				
					<table class="table" border="1" width="80%" height="120%" style="margin: auto;" id="insuredDetailsTable">
						<tr style="background-color: #dac8b6;"height="10%">
							<th>Sr No</th>
							<th>First Name</th>
							<th>Middle Name</th>
							<th>Last Name</th>
							<th>RelationShip</th>
							<th>Gender</th>
							<th>DOB</th>
							<th>Current Sickness</th>
							<th>Occupation</th>
							<th>Gross Annual Income</th>
							<th>SI-A(Death)</th>
							<th>SI-B(Death+Permanent Total Disability(PTD))</th>
							<th>SI-C(Death+Permanent Total Disability(PTD)+Permanent
								Partial Disability(PPD))</th>
							<th>SI-D(Death+Permanent Total Disability(PTD)+Permanent
								Partial Disability(PPD))+Temporary Total Disability(TTD)</th>
							<th>Sum Insured</th>
							
						</tr>
						
					</table>
				</div>
			</div>
		</div>
		<br>
	
		<br>
		<div class="row" style="background-color: #4cbcce;"
			id="medicalDetails">

			<div class="col-xs-12 col-md-4 col-sm-4 col-xl-4">
				<div class="radio">
					<label for="">Has any person to be insured been diagnosed/hospitalized/under any treatment 
					for any illness/disease or injury during any time in past? If Yes please select
					the disease/injury as mentioned below. If others please specify.</label>
				</div>
			</div>
			<div class="col-xs-12 col-md-2 col-sm-2 col-xl-2"
				style="margin-top: 10px;">
				<label class="radio-inline">
				<input type="radio" id="diseaseRadioYes" name="diseaseRadio" value="yes" onclick="getDiseaseDetails()">Yes</label>
				 <label class="radio-inline">
				 <input type="radio" name="diseaseRadio" value="no" onclick="hideDiseaseDetails()">No</label>
			</div>
			<div class="col-xs-12 col-md-4 col-sm-4 col-xl-4">
			</div>
			<br>
		</div>
		<br>
		<div class="row" style="background-color: #4cbcce;"
			id="diseaseDetails">
			<div style="background-color: #d1bebe;">&nbsp;&nbsp;Disease
				Details</div>
				
			<br>
			<div class="main wrapper2" style="margin: auto;">
				<div class="div2 div4">
					<table class="table" border="1" width="1%" height="10%" style="margin: auto;" id="diseaseDetailsTable">
					<tbody>
					<tr id="diseaseTableHead">
					<th style="width: 10px;">Disease Name</th>
					</tr> 
					</tbody>
					</table>
					<div class="modal-footer">
           <div class="col-md-7"></div>
          <div class="col-md-5">
          <button type="button" class="btn btn-primary" onclick="showNomineeDetails()"
            >OK</button>
          </div>
          </div>
		  </div>
			</div>
		</div>
		
		<br>
		
	<div class="row" style="background-color: #4cbcce" id="nomineeDetailsTab">
		<div class="panel-title" style="background-color: #d1bebe;">
				&nbsp;&nbsp;
				 <a  class="collapsed" data-toggle="collapse" data-parent="#accordion"	href="#collapseThree" aria-expanded="true" aria-controls="collapseOne">
				  Nominee Details
				 </a>
			</div>
			<br>
         <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" class="main wrapper2" style="margin: auto;">
				 <div class="row" style="padding: 5px">
           
             <div class="col-md-3">
             <div class="col-md-3">
             <label for="contact"><font color="#ffffff">Initial</font></label>	
             </div>
             <div class="col-md-3">
             <select id="nomDtlInitial" style="width: 175px" >
             <option>--Select--</option>
             </select>
             </div>
             </div>
          
             <div class="col-md-3">
             <div class="col-md-4">
             <label for="contact"><font color="#ffffff">First Name</font></label>
             </div>
             <div class="col-md-3">
             <input id="nomDtlFname" type="text" style="width: 173px" >
             </div>
             </div>
             
             
             <div class="col-md-3">
             <div class="col-md-5">
             <label for="contact"><font color="#ffffff">Middle Name</font></label>
             </div>
             <div class="col-md-3">
             <input id="nomDtlMname" type="text"style="width: 173px" >
             </div>
             </div>
             
             
             <div class="col-md-3">
             <div class="col-md-4" >
             <label for="contact"><font color="#ffffff">Last Name</font></label>
             </div>
             <div class="col-md-3" >
             <input id="nomDtlLname" type="text" style="width: 145px" >
             </div>
             </div>
             
             
             </div>
             <div class="row" style="padding: 5px">
             <div class="col-md-3">
             <div class="col-md-3">
             <label for="contact"><font color="#ffffff">Relation</font></label>		
             </div>
             <div class="col-md-5">
             <select id="nomDtlRelation" style="width: 175px">
             <option>--Select--</option>
             </select>
             </div>
             </div>
             
             
            <div class="col-md-3">
            <div class="col-md-4">
            <label for="contact"><font color="#ffffff">Aadhar No</font></label>	
            </div>
            <div class="col-md-3">
            <input id="nomDtlAdharNo" type="text" style="width: 175px" onkeypress="return isNumber(event)" maxlength="12">
            </div>
            </div>
            
            
            <div class="col-md-3">
            <div class="col-md-5">
            <label for="contact"><font color="#ffffff">Date Of Birth</font></label>		
            </div>
            <div class="col-md-3">
            <input id="nomDtlDOB" type="text" style="width: 175px" class="datePicker">
            </div>
            </div>
            </div>
            
            
            <div class="row" style="padding: 5px">
            <div class="checkbox">
            <label> <input type="checkbox" style="margin-left: 20px" id="nomCheckBox" onchange="fillAddressDetails()">
            <font color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Address Same As Insured Address
            </font></label>
            </div>
            </div>
            
            
            <div class="row" style="padding: 5px">
            <div class="col-md-3">
            <div class="col-md-3">
            <label for="contact"><font color="#ffffff">Country</font></label>		
            </div>
            <div class="col-md-5">
            <select id="nomDtlCountry" style="width: 175px" onchange="nomDtlState()">
            <option>--Select--</option>
            </select>
            </div>
            </div>
            
            
           <div class="col-md-3">
           <div class="col-md-4">
           <label for="contact"><font color="#ffffff">State</font></label>
           </div>
           <div class="col-md-3">
           <select id="nomDtlState" style="width: 175px" onchange="nomDtlDistrict()" >
           <option>--Select--</option>
           </select>
           </div>
           </div>
           
           
          <div class="col-md-3">
          <div class="col-md-5">
          <label for="contact"><font color="#ffffff">District</font></label>
          </div>
          <div class="col-md-3">
          <select id="nomDtlDistrict" style="width: 175px"  onchange="nomDtlCity()" >
          <option>--Select--</option>
          </select>
          </div>
          </div>
          
          
          <div class="col-md-3">
          <div class="col-md-4">
          <label for="contact"><font color="#ffffff">City</font></label>
          </div>
          <div class="col-md-3">
          <select id="nomDtlCity" style="width: 145px" onchange="nomDtlPincode()">
          <option>--Select--</option>
          </select>
          </div>
          </div>
          </div>
          
          
          <div class="row" style="padding: 5px">
          <div class="col-md-3">
          <div class="col-md-3">
          <label for="contact"><font color="#ffffff">Pincode</font></label>		
          </div>
          <div class="col-md-5">
          <select id="nomDtlPincode"  onchange="nomDtlLandmark()" style="width: 175px"  >
          <option>--Select--</option>
          </select>
          </div>
          </div>
          
          
         <div class="col-md-3">
         <div class="col-md-4">
         <label for="contact"><font color="#ffffff">Landmark</font></label>
         </div>
         <div class="col-md-3">
         <input id="nomDtlLandmark" onchange="nomDtlStreet()"
          list="nomDtlLandmarkList" type="text"
           style="width: 173px">
	      <datalist id="nomDtlLandmarkList"></datalist>
         </div>
         </div>
         
         
         <div class="col-md-3">
         <div class="col-md-5">
         <label for="contact"><font color="#ffffff">Street</font></label>
         </div>
         <div class="col-md-3">
         <input id="nomDtlStreet" onchange="nomDtlHouseNo()" list="nomDtlStreetList" type="text"
           style="width: 173px">
	     <datalist id="nomDtlStreetList"></datalist>
         </div>
         </div>
      
      
         <div class="col-md-3">
         <div class="col-md-4">
         <label for="contact"><font color="#ffffff">House No</font></label>
         </div>
         <div class="col-md-3">
         <input id="nomDtlHouseNo"  list="nomDtlHouseNoList" type="text"
          style="width: 145px">
		 <datalist id="nomDtlHouseNoList"></datalist>
         </div>
         </div>
         </div>
         
         
         <div class="row" style="padding: 5px">
         <div class="col-md-3">
         <div class="col-md-3">
         <label for="contact"><font color="#ffffff">Email</font></label>		
         </div>
         <div class="col-md-5">
         <input id="nomDtlEmail" type="text"style="width: 173px"
               required="required">
         </div>
         </div>
         
         
         <div class="col-md-3">
         <div class="col-md-4">
         <label for="contact"><font color="#ffffff">Phone no</font></label>
         </div>
         <div class="col-md-3">
         <input id="nomDtlphoneNo" type="text"style="width: 173px"
               required="required"  onkeypress="return isNumber(event)" maxlength="11">
         </div>
         </div>
         
         
         <div class="col-md-3">
         <div class="col-md-5">
         <label for="contact"><font color="#ffffff">Mobile</font></label>
         </div>
         <div class="col-md-3">
         <input id="nomDtlMobileNo" type="text"style="width: 173px" onkeypress="return isNumber(event)" maxlength="10"
               required="required">
         </div>
         </div>
         </div>
         
         
         <div class="modal-footer">
         <div class="col-md-7"></div>
         <div class="col-md-5">
         <button type="button" class="btn btn-primary" onclick="showPreviousPolicyTab()"
            >OK</button>
         <button type="button" class="btn btn-primary" onclick="clearNomineeData()"
            >Clear</button>
         <button type="button" class="btn btn-primary"
            data-dismiss="modal">Cancel</button>
          </div>
          </div>
		  </div>
			
		</div>
		
	<br>
		<div class="row" style="background-color: #4cbcce;"
			id="previousPolicyDetails">
			<div class="col-xs-12 col-md-4 col-sm-4 col-xl-4">
				<div class="radio">
					<label for="">Do you/other Insured Member hold any
						other Health Insurance Cover?</label>
				</div>
			</div>
			<div class="col-xs-12 col-md-2 col-sm-2 col-xl-2"
				style="margin-top: 5px;">
				<label class="radio-inline"><input type="radio" id="prevDet"
					name="optradio" value="yes" onclick="showPreviousDetails();previousPolicyAddNewMember()">Yes</label> <label class="radio-inline"><input
					 type="radio" name="optradio" value="no" onclick="hidePreviousDetails();showCompanyDtlTab()">No</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div class="col-xs-12 col-md-4 col-sm-4 col-xl-4">
			</div>
			<br>
		</div>	
		
		<div class="row" id="previousPolicy"
			style="background-color: #4cbcce;">
			<div style="background-color: #d1bebe;">&nbsp;&nbsp;Previous
				Policy</div>
			<br>
			
			<div class="main wrapper2" style="margin: auto;">
				<button type="button" id="addRow" name="addRow" class="btn btn-primary" onclick="previousPolicyAddNewMember()"style=right;margin-top: 15px;">Add More Previous Policy Details</button>
				<div class="div2 div4">
					<table class="table " border="1" width="80%" style="margin: auto;" id="previousTable">
						<tr style="background-color: #dac8b6;">
							<th>Sr No</th>
							<th>Member</th>
							<th>GIC</th>
							<th>Policy No</th>
							<th>Insured Since</th>
							<th>Policy Start Date</th>
							<th>Policy End Date</th>
							<th>Sum Insured</th>
							<th>Claim ,If Any</th>
							</tr>
					</table>
					<br>
				<button type="button" id="nextPrv" name="nextPrv" class="btn btn-primary" onclick="showCompanyDtlTab()"style=right;margin-top: 15px;">Next</button>
				</div>
			</div>
			</div>
			<br>
			<div class="row" id="showCompanies"
			style="background-color: #4cbcce;">
				<button type="button" class="btn btn-primary" style="text-align: center;" onclick="showCompanies();">Show Companies</button>
				<div style="background-color: #d1bebe;">&nbsp;&nbsp;Company
				Details</div>
				<div class="main wrapper2" style="margin: auto;">
				<div class="div2 div4">
					<table class="table " border="1" width="80%" style="margin: auto;" id="tableShowCompanies">
						<tr style="background-color: #dac8b6;">
							<th>Select</th>
							<th>Gic Id</th>
							<th>Gic</th>
							<th>Premium A</th>
							<th>Premium B</th>
							<th>Premium C</th>
							<th>Premium D</th>
							<!-- <th>PA Product Based Premium</th> -->
							<th>Medical Expenses Extension</th>
							<th>Total Premium</th>
							<th>CGST</th>
							<th>SGST</th>
							<th>Total Tax</th>
							
					</table>
					
				</div>
			</div>
			</div>
		<br> 
		<div class="row" style="background-color: #4cbcce;" id="bankDtlTab">
		<div class="panel-title" style="background-color: #d1bebe;">
				&nbsp;&nbsp;
				 <a  class="collapsed" data-toggle="collapse" data-parent="#accordion"	href="#collapseBnkDtl" aria-expanded="true" aria-controls="collapseOne" >
				 Bank Details
				 </a>
			</div>
			<br>
			<div id="collapseBnkDtl" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" class="main wrapper2" style="margin: auto;">
			<div class="row" style="padding: 5px">
					 <div class="col-md-4">
                     <div class="col-md-5">
                     <label for="bankName"><font color="#ffffff">Bank Name</font></label>
                     </div>
                     <div class="col-md-3">
                     <select id="bankNameTf" style="width: 250px" onchange="populateBranchName()">
                     <option>--Select--</option>
                     </select>
                     </div>
                     </div>
                    </div>
                    <br>
				<div class="row" style="padding: 5px">
					 <div class="col-md-4">
                     <div class="col-md-5">
                     <label for="branchName"><font color="#ffffff">Branch Name</font></label>
                     </div>
                     <div class="col-md-3">
                     <select id="branchNameTf" style="width: 250px">
                     <option>--Select--</option>
                     </select>
                     </div>
                     </div>
                    </div>
                    <br>
				<div class="row" style="padding: 5px">
					 <div class="col-md-4">
                     <div class="col-md-5">
                     <label for="accountNo"><font color="#ffffff">Account No</font></label>
                     </div>
                     <div class="col-md-3">
                     <input id="accountNoTf" type="text"style="width: 250px"
                       required="required"  >
                     </div>
                     </div>
                    </div>
                    <br>
				<div class="row" style="padding: 5px">
					 <div class="col-md-4">
                     <div class="col-md-5">
                     <label for="ifscCode"><font color="#ffffff">IFSC Code</font></label>
                     </div>
                     <div class="col-md-3">
                     <input id="ifscCodeTf" type="text"style="width: 250px"
                       required="required"  >
                     </div>
                     </div>
                    </div>
                    <br>
			<div class="row" style="padding: 5px">
					 <div class="col-md-4">
                     <div class="col-md-5">
                     <label for="otherDetails"><font color="#ffffff">Other Details</font></label>
                     </div>
                     <div class="col-md-3">
                     <input id="otherDetailsTf" type="text"style="width: 250px"
                       required="required"  >
                     </div>
                     </div>
                    </div>
                    
                <div class="row"style="padding: 5px"></div>
				<div class="row"style="padding: 5px">
				<div class="col-md-2">
				</div>
				<div class="col-md-3">
				<button type="button" class="btn btn-primary">Ok</button>
				 <button type="button" class="btn btn-primary">Clear</button>
				</div>
				</div>
				
				</div>
			</div>
			<br>
			
	</div>
		
	

</body>
</html>