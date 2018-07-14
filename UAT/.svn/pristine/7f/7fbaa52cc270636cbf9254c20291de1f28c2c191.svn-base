<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script	src="js/jquery.dataTables.min.js"></script>

<script src="js/dataTables.checkboxes.min.js"></script>
<link rel="stylesheet" href="css/jquery.dataTables.min.css">

<script src="js/dataTables.checkboxes.min.js"></script>
<script src="js/dataTables.select.min.js"></script>
<script src="js/productDetails.js"></script>
<script src="js/validation.js"></script>

<style>
.pointer {cursor: pointer;}
.css-serial {
  counter-reset: serial-number;  /* Set the serial number counter to 0 */
}

.css-serial td:first-child + td:before {
  counter-increment: serial-number;  /* Increment the serial number counter */
  content: counter(serial-number);  /* Display the counter */
}

.dataTables_wrapper {
   width: 500px;
}

div.dataTables_wrapper {
/* 	width: 800px; */
	/* margin: 0 auto; */
}
.selected {
    background-color: brown;
    color: #FFF;
}

.dataTables_wrapper .dataTables_paginate {
    float: left;
    text-align: left;
}
.dataTables_wrapper .dataTables_filter {
    float: left;
    text-align: left;
    margin-left: 60px;
}
.accordianColor{
background-color : #337ab7;
color : white;
}
.accordianColor:hover{
background-color : #286090;
}

/* .fontColor{
color : #e55f05;
}
 */
  .fontColor{
color : #f46707;
}

.dataTables_scroll
{
    overflow:auto;
}
.hide_column {
    display : none;
}

</style> 
<script>


$(document).ready(function() {
	startXHR();
	setTimeout(function(){stopXHR();},10000);

		var today = new Date();
		var yr = today.getFullYear();
		var mm = today.getMonth();
		var dd = today.getDate();
		var year= parseInt(yr)-18;
		console.log("year::>>"+year);
		var day = parseInt(dd)-21;
		var getDate="";
		
		
	 $("#dateofReg").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy" });
	 $("#dateofReg").val(null);
	 $('#dateofReg').on('click', function () {
	     $('#dateofReg').jqxDateTimeInput('open');
	 });
	 $('#dateofReg').on('change', function (event) {
		 getDate= $('#dateofReg').val();  
	     console.log("getDate = " + getDate);
	     $('#prevDtlRegDate ').jqxDateTimeInput('setDate', getDate);
	     /* $("#prevDtlRegDate").jqxDateTimeInput({ min: new Date(year1, month1, 1), max: new Date(yr, mm, dd) }); */
	 });
	 
	 $("#insDtlDob").jqxDateTimeInput({ width: '120px', height: '25px',allowNullDate: true, placeHolder: "dd/mm/yyyy"});
	 $("#insDtlDob").val(null);
	 $('#insDtlDob').on('click', function () {
	     $('#insDtlDob').jqxDateTimeInput('open');
	     var yearIns= parseInt(yr)-18;
	     var mmIns= parseInt(mm)+1;
		 $("#insDtlDob").val(dd+"/"+mmIns+"/"+yearIns);
		
			console.log("year::>>"+yearIns+" "+dd+"/"+mmIns+"/"+yearIns);
			$("#insDtlDob").jqxDateTimeInput({max: new Date(yearIns, mm, dd)}); 
	 });
//	 $('#insDtlDob').val(new Date(year, mm, dd));
	 
	 $("#nomDtlDOB").jqxDateTimeInput({ width: '170px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", max: new Date(yr, mm, dd) });
	 $("#nomDtlDOB").val(null);
	 $('#nomDtlDOB').on('click', function () {
	     $('#nomDtlDOB').jqxDateTimeInput('open');
	 });
	 
	 $("#inspDtlInspAllotedDate").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy" });
	 $("#inspDtlInspAllotedDate").val(null);
	 $('#inspDtlInspAllotedDate').on('click', function () {
	     $('#inspDtlInspAllotedDate').jqxDateTimeInput('open');
	 });
	 
	 $("#inspectionDate").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy" });
	 $("#inspectionDate").val(null);
	 $('#inspectionDate').on('click', function () {
	     $('#inspectionDate').jqxDateTimeInput('open');
	 }); 

	  $("#dob1").jqxDateTimeInput({ width: '135px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", max: new Date(yr, mm, dd) });
	 $("#dob1").val(null);
	 $('#dob1').on('click', function () {
		 $('#dob1').jqxDateTimeInput('open');
	 });
		 	 
	$("#dob2").jqxDateTimeInput({ width: '135px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", max: new Date(yr, mm, dd) });
	$("#dob2").val(null);
	$('#dob2').on('click', function () {
		 $('#dob2').jqxDateTimeInput('open');
	});
		 	 
	$("#dob3").jqxDateTimeInput({ width: '135px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", max: new Date(yr, mm, dd)  });
 	$("#dob3").val(null);
	$('#dob3').on('click', function () {
		$('#dob3').jqxDateTimeInput('open');
	});
		 	 
	$("#dob4").jqxDateTimeInput({ width: '135px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy" , max: new Date(yr, mm, dd) });
	$("#dob4").val(null);
	$('#dob4').on('click', function () {
		 $('#dob4').jqxDateTimeInput('open');
	}); 
	
	$("#riskStartDt").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy" });
	 $("#riskStartDt").val(null);
	 $('#riskStartDt').on('click', function () {
	     $('#riskStartDt').jqxDateTimeInput('open');
	 }); 
	 
	 $("#prevFromDate").jqxDateTimeInput({ width: '160px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", max: new Date(yr, mm, dd) });
	 $("#prevFromDate").val(null);
	 $('#prevFromDate').on('click', function () {
	     $('#prevFromDate').jqxDateTimeInput('open');
	 });
	 
	 $("#prevToDate").jqxDateTimeInput({ width: '160px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", min: new Date(yr, mm, day), max: new Date(yr, mm, dd) });
	 $("#prevToDate").val(null);
	 $('#prevToDate').on('click', function () {
	     $('#prevToDate').jqxDateTimeInput('open');
	 }); 
	 
	 $("#prevDtNmTransInRcBk").jqxDateTimeInput({ width: '160px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", min: new Date(yr, mm, day), max: new Date(yr, mm, dd) });
	 $("#prevDtNmTransInRcBk").val(null);
	 $('#prevDtNmTransInRcBk').on('click', function () {
	     $('#prevDtNmTransInRcBk').jqxDateTimeInput('open');
	 }); 
	 
	 $("#prevDtNmTransInInsPolicy").jqxDateTimeInput({ width: '160px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy", min: new Date(yr, mm, day), max: new Date(yr, mm, dd) });
	 $("#prevDtNmTransInInsPolicy").val(null);
	 $('#prevDtNmTransInInsPolicy').on('click', function () {
	     $('#prevDtNmTransInInsPolicy').jqxDateTimeInput('open');
	 }); 
	 
	 $("#prevDtlRegDate").jqxDateTimeInput({ width: '220px', height: '25px', allowNullDate: true, placeHolder: "dd/mm/yyyy" });
	 $('#prevDtlRegDate').on('click', function () {
	     $('#prevDtlRegDate').jqxDateTimeInput('open');
	     /* console.log("mnth = " + month1 + "yr = " + year1); */
	 });  
	 $("#aaiExpiryDate").jqxDateTimeInput({allowNullDate: true, placeHolder: "dd/mm/yyyy" });
	 $("#aaiExpiryDate").val(null);
	 $('#aaiExpiryDate').on('click', function () {
	     $('#aaiExpiryDate').jqxDateTimeInput('open');
	 });
	 $("#prevDtNmTransInRcBk").jqxDateTimeInput({ disabled: true ,showCalendarButton: false});
	 $("#prevDtNmTransInInsPolicy").jqxDateTimeInput({ disabled: true ,showCalendarButton: false});

	$("#inspectionStateTF,#inspectionCityTF,#inspectionPincodeTF,#inspectionSurveyorNameTF,#inspectionAgencyTF,#inspectionSurveyorStatus").select2({
		allowClear : true,
		maximumSelectionSize : 1,
	});
	
		$(function () {
		  
	//	var compDtlActive = true;
		$('#getPremiumBtn').click(function () {
		//	old_alert("compDtlActive:::" + compDtlActive);
	       $('.compDtl').collapse('show');
	       $('.compDtl1').attr('data-toggle', '');
	       
	    });
	    $('#accordion').on('show.bs.collapse', function () {
	        if (compDtlActive) $('#accordion .in').collapse('hide');
	    });
	});
		
	$("input#engineNo,#chasisNo").on({
		keydown: function(e) {
			if (e.which === 32)
			   return false;
			},
	});
		
	
	$('#dependant1').change(function () {
			  
            if ($(this).val() == "Yes1" ||$(this).val() == "No1" ) {
                $('#ACoverCheck2').each(function () {
                    $(this).prop('disabled', false);
                });
            }
            else {
                $('#ACoverCheck2').each(function () {
                    $(this).prop('disabled', true);
                    $(this).prop('checked', false);
                });
            }
        });
	  $('#dependant2').change(function () {
		  
            if ($(this).val() == "Yes2" ||$(this).val() == "No2" ) {
                $('#ACoverCheck3').each(function () {
                    $(this).prop('disabled', false);
                });
            }
            else {
                $('#ACoverCheck3').each(function () {
                    $(this).prop('disabled', true);
                    $(this).prop('checked', false);
                });
            }
        });
	  $('#dependant3').change(function () {
		  
            if ($(this).val() == "Yes3" ||$(this).val() == "No3" ) {
                $('#ACoverCheck4').each(function () {
                    $(this).prop('disabled', false);
                });
            }
            else {
                $('#ACoverCheck4').each(function () {
                    $(this).prop('disabled', true);
                    $(this).prop('checked', false);
                });
            }
        });
	  
	  $("#insDtlInitial,#insDtlMaritialStatus,#insDtlNationality,#insDtlCACountry, #insDtlCAState, #insDtlCADistrict, #insDtlCACity,#insDtlCAPincode, #insDtlPACountry,#insDtlPADistrict,#insDtlPAState,#insDtlPACity,#insDtlPAPincode,#insDtlOACountry,#insDtlOAState,#insDtlOADistrict, #insDtlOACity,#insDtlOAPincode,#nomDtlInitial,#nomDtlRelation,#nomDtlCountry,#nomDtlState,#nomDtlDistrict,#nomDtlCity, #nomDtlPincode,#prevProduct,#prevMfrYear,#prevRtoState, #prevRtoCity,#prevVehicleType,#prevModel,#prevVariance,#prevPolicyType,#prevInsuranceType,#prevInsuranceCompany,#prevFuelKit,#prevCustomerType,#prevPolicyPeriod,#prevPolicyMonth").select2({
				allowClear : true,
				maximumSelectionSize : 1,
			});
	  
});
$('#packageDetails tbody').on( 'click', 'td', function (e) {
		$("#selectedIndex").val($(this).index());
		//old_alert( $("#selectedIndex").val())
	 	$("#row_index").val($(this).closest("tr").index());
	         //old_alert( $("#row_index").val())
	      	//old_alert("packageDetails-->>>>> ");
	     if($("#selectedIndex").val() == "3"){
	        $("#nomineeDtlDiv").hide();
	     	$("#insuredDtlDiv").hide();   
	     	$("#companyDtlDiv").hide();
	     	$("#paymentOptionsDiv").hide();  
	     	$("#proposalDetailsDiv").hide();  
	     	$("#paymentDetailsDiv").hide();  
	     	$("#cardPay").hide(); 
	     	$("#onlinePay").hide(); 
	     	$("#relExtPay").hide(); 
	     	$("#cdtBal").hide();
	     	$("#remainingBal").hide();   
	     	$("#creditcardPay").hide();
	     	$("#VehicleFinDtlDiv").hide();  
	     }
});
	

$('#geoExtensionCover tbody tr').on( 'click',function (e) {
		$(this).addClass("selected");
});
	 $('#liabilityDetails tbody').on( 'click', 'td', function (e) {
	    	
		 $("#selectedIndex").val($(this).index());
			//old_alert( $("#selectedIndex").val())
	         $("#row_index").val($(this).closest("tr").index());
	       	//old_alert("show div liabilityDetails -->>>>> ");
	       if($("#selectedIndex").val()=="7"){
	         $("#nomineeDtlDiv").hide();
		     	$("#insuredDtlDiv").hide();   
		     	$("#companyDtlDiv").hide();
		     	$("#paymentOptionsDiv").hide();  
		     	$("#cardPay").hide(); 
		     	$("#onlinePay").hide(); 
		     	$("#relExtPay").hide(); 
		     	$("#cdtBal").hide();
		     	$("#remainingBal").hide();   
		     	$("#creditcardPay").hide();
		     	$("#VehicleFinDtlDiv").hide();  
		     	$("#proposalDetailsDiv").hide();  
		     	$("#paymentDetailsDiv").hide();  
	       }
	 });
	 $('#additionalcover tbody').on( 'click', 'td', function (e) {
		 $("#row_index").val($(this).closest("tr").index());
		 $("#selectedIndex").val($(this).index());
			//old_alert( $("#selectedIndex").val())
			var v1 = $('#additionalcover tbody tr').eq( $("#row_index").val()).find('td').eq($("#selectedIndex").val()).html();
			
			if($("#selectedIndex").val()=="0" || ($("#selectedIndex").val()=="1" && v1 != ""))
			{
		 	$("#nomineeDtlDiv").hide();
	     	$("#insuredDtlDiv").hide();   
	     	$("#companyDtlDiv").hide();
	     	$("#paymentOptionsDiv").hide();  
	     	$("#cardPay").hide(); 
	     	$("#onlinePay").hide(); 
	     	$("#relExtPay").hide(); 
	     	$("#cdtBal").hide();
	     	$("#remainingBal").hide();   
	     	$("#creditcardPay").hide(); 
	     	$("#VehicleFinDtlDiv").hide();  
	     	$("#proposalDetailsDiv").hide();  
	     	$("#paymentDetailsDiv").hide();  
			 }
	 });
	
	 $('#extraCovers tbody').on( 'click', 'td', function (e) {
		 $("#row_index").val($(this).closest("tr").index());
		 $("#selectedIndex").val($(this).index());
		
	 });
	 $('#companyDetails tbody').on( 'click', 'td', function (e) {
		 $("#row_indexCmpTbl").val($(this).closest("tr").index());
			//old_alert("row_indexCmpTbl:::" +$("#row_indexCmpTbl").val());
	 });

	 
	 
 function restrictSpace(e) {
	    if (e.which === 32)
	      return false;
	  }
 function checkVehNoLength(el) {
	  if (el.value.length != 4) {
	    old_alert("Length Must Be Exactly 4 Numbers");
	    //$('#vehRegNo4').focus();
	    setTimeout(function(){$("#vehRegNo4").val("").focus();},1);
		return false;
	  }else{
		  return false;
	  }
	}
 
function openPolicyTypeModal(formName) {
	
	$.ajax({
		url : formName,
		type : "post",
		async : "false",
		context : document.body,
		success : function(result) {
			//old_alert("success"+result);
			$("#contentBodyMotor").html(result);
			$("#showmodelMotor").modal('show');
			$("#logModelMotor").css({
				'width' : '55%'
			
			});
			$("#logModelMotor").css({
				'height' : '50%'
			});
			$("#logModelMotor").css({
				'margin-left' : '120px'
			});
			$("#logModelMotor").css({
				'margin-top' : '20px'
			});
			$("#logHeaderMotor").hide();
		}
	});

}


	$('#b1').on('click', function (ev) {
		var comId = $('#paymentCompany').val();

		$.gic = '48';
		//old_alert("compId---------->>"+comId);
        ev.preventDefault();
        if (comId=='48') {
            old_alert('Please select any one item in grid');
            $("#shreeRamPay").modal("show");
            shriRamPolicy();
        }
        else {
        	//old_alert('Else  block');
        	 $("#shreeRamPay").modal("hide");
        	 getPaymentDetails();
        	 var active = true;
        	 if (active) {
		            active = false;
		            $('.paymentDtl').collapse('show');
		            $('.paymentDtl1').attr('data-toggle', '');
		           
		        } else {
		            active = true;
		            $('.paymentDtl').collapse('hide');
		            $('.paymentDtl1').attr('data-toggle', 'collapse');
		           
		        }
        	 
        		}
    		});
			 $('#accordion').on('show.bs.collapse', function () {
	       		 if (active) $('#accordion .in').collapse('hide');
	    	});
	
	
			 function  getGeoCountries() {
					var id=$('#geoSelectedIds').val()
					
					if(id != ""){
						$('#additionalcover tbody tr').eq( $("#row_index").val()).find('td').eq(11).html(id);
						console.log("SETiDINCOLUMN---->>"+id);
					    $('#geoOK').attr("data-dismiss","modal"); 
						}
					else{
						old_alert("Please select Geographical extension data ");
						$('#geoOK').attr("data-dismiss",""); 
					} 
			 }
	
	function prevDtlPolicyMonth(){
		var policyPeriod = $("#prevPolicyPeriod option:selected").text();
		// THE JSON ARRAY.
		// old_alert("change........");
		if(policyPeriod == "Long Term")
		{
			$('#selectMonth').html("<font class='fontColor' style='width: 160px';>Year");
		}else{
			$('#selectMonth').html("<font class='fontColor' style='width: 160px';>Month");
		}
				 $('#prevPolicyMonth').val('').trigger("change");
				 $("#prevPolicyMonth").empty().end().append('<option value="">policy Month');
				 
				 var policyArray=new Array();
				 if(policyPeriod=="One Year"){
				 policyArray =[{ id: '1', text: '12 Month'}]; 

				 }else if(policyPeriod=="Short Term"){
				      policyArray =
				                [{ id: '1', text: '1 Month' },
				                    { id: '2', text: '2 Month' },
				                    { id: '3', text: '3 Month' },
				                    { id: '4', text: '4 Month' },
				                    { id: '5', text: '5 Month' },
				                    { id: '6', text: '6 Month' },
				                    { id: '7', text: '7 Month' },
				                    { id: '8', text: '8 Month' },
				                    { id: '9', text: '9 Month' },
				                    { id: '10', text: '10 Month' },
				                    { id: '11', text: '11 Month'}]; 

				}else if(policyPeriod=="Long Term"){
				     policyArray =
				        		[{ id: '1', text: '2 Year' },
				                 { id: '2', text: '3 Year' },
				                 { id: '3', text: '4 Year' },
				                 { id: '4', text: '5 Year' },
				                 { id: '5', text: '6 Year' },
				                 { id: '6', text: '7 Year' },
				                 { id: '7', text: '8 Year' },
				                 { id: '8', text: '9 Year' },
				                 { id: '9', text: '10 Year'}];

				}  
				     
				 $("#prevPolicyMonth").empty();
				$("#prevPolicyMonth").append('<option value="">');		//for set empty text on period change
				$.each(policyArray, function (key, value) {
							nKey = parseInt(key) + 1;
							$("#prevPolicyMonth")
								.append(
									'<option value="' + policyArray[key].id + '"> ' +
									policyArray[key].text +
									'');
				});
	}
	 
	
	function prevDtlOwnerType(){
		var ownTyp = $("#prevOwnerType option:selected").text();
		 if((ownTyp).toUpperCase()==("second").toUpperCase()){
			 $('#prevChkNmTransInRcBk').removeAttr('disabled');
		 }else{
			 $("#prevChkNmTransInRcBk").prop("checked", false);
			 $("#prevChkNmTransInInsPolicy").prop("checked", false);
			/*  document.getElementById("prevDtNmTransInRcBk").value = "dd-mm-yyyy";
			 document.getElementById("prevDtNmTransInInsPolicy").value = "dd-mm-yyyy"; */
			 
			 $('#prevChkNmTransInRcBk').attr("disabled","disabled"); 
			 $('#prevChkNmTransInInsPolicy').attr("disabled","disabled"); 

			 /* $('#prevDtNmTransInRcBk').attr("disabled","disabled"); 
			
			 $('#prevDtNmTransInInsPolicy').attr("disabled","disabled");  */
			 $("#prevDtNmTransInRcBk").val(null);
			 $("#prevDtNmTransInInsPolicy").val(null);
			 
			 $("#prevDtNmTransInRcBk").jqxDateTimeInput({ disabled: true , showCalendarButton: false, placeHolder: "dd/mm/yyyy"});
			 $("#prevDtNmTransInInsPolicy").jqxDateTimeInput({ disabled: true , showCalendarButton: false, placeHolder: "dd/mm/yyyy"});
			 
		 }
		 
		 /* prevChkNmTransInRcBk prevDtNmTransInRcBk prevChkNmTransInInsPolicy prevDtNmTransInInsPolicy */
	}
	
	function prevDtlNmTransInRcBk(){
		 var chk = document.getElementById("prevChkNmTransInRcBk").checked
		 if(chk==true){
			 $('#prevChkNmTransInInsPolicy').removeAttr('disabled');
			 /* $('#prevDtNmTransInRcBk').removeAttr('disabled');
		
			 $('#prevDtNmTransInInsPolicy').removeAttr('disabled'); */
			 $("#prevDtNmTransInRcBk").jqxDateTimeInput({ disabled: false , showCalendarButton: true, placeHolder: "dd/mm/yyyy"});
			 $("#prevDtNmTransInInsPolicy").jqxDateTimeInput({ disabled: false , showCalendarButton: true, placeHolder: "dd/mm/yyyy"});
			 
			 
		 }else if(chk==false){
			 $("#prevChkNmTransInInsPolicy").prop("checked", false);
			/*  document.getElementById("prevDtNmTransInRcBk").value = "dd-mm-yyyy";
			 document.getElementById("prevDtNmTransInInsPolicy").value = "dd-mm-yyyy"; */
			 $('#prevChkNmTransInInsPolicy').attr("disabled","disabled"); 
			/*  $('#prevDtNmTransInRcBk').attr("disabled","disabled"); 
			
			 $('#prevDtNmTransInInsPolicy').attr("disabled","disabled");  */
			 $("#prevDtNmTransInRcBk").val(null);
			 $("#prevDtNmTransInInsPolicy").val(null);
			 
			 $("#prevDtNmTransInRcBk").jqxDateTimeInput({ disabled: true , showCalendarButton: false, placeHolder: "dd/mm/yyyy"});
			 $("#prevDtNmTransInInsPolicy").jqxDateTimeInput({ disabled: true , showCalendarButton: false, placeHolder: "dd/mm/yyyy"});
			 
		 }
	}
	
		function validateElec() {
		if (!$('#eleAccessoryTextfiels').val()) {
			old_alert("Please Select Accessory!!");
			$('#eleAccessoryTextfiels').focus();
			return false;
		}
		
		
		if (!$('#eleMakeTextfiels').val()) { 
			old_alert("Please Select Make!!");
			$('#eleMakeTextfiels').focus();
			return false;
		}
		
		if (!$('#eleModelTextfiels').val()) {
			old_alert("Please Select Model!!");
			$('#eleModelTextfiels').focus();
			return false;
		}
		
		if (!$('#eleYearTextfiels').val()) {
			old_alert("Please Enter Year!!");
			$('#eleYearTextfiels').focus();
			return false;
		}
		
		if (!$('#elePriceTextfiels').val()) {
			old_alert("Please Enter Price!!");
			$('#elePriceTextfiels').focus();
			return false;
		}
		
		var badic_idv = parseFloat($("#basicIdv").val());
        var validation = badic_idv * 20 / 100;
         
        var tot = parseFloat($("#eleTotalTextfiels").val()) + parseFloat($("#elePriceTextfiels").val());
		if (tot>validation) {
			old_alert("Total Must Not Be More Than 20% Of Basic IDV");
			$('#elePriceTextfiels').focus();
			return false;
		}
		return true;
		
	}
		function validateNonElec() {
			if (!$('#nonEleAccessoryTextfiels').val()) { 
				old_alert("Please Select Accessory!!");
				$('#nonEleAccessoryTextfiels').focus();
				return false;
			} 
			if (!$('#nonEleMakeTextfiels').val()) {
				old_alert("Please Select Make!!");
				$('#nonEleMakeTextfiels').focus();
				return false;
			}
			if (!$('#nonEleModelTextfiels').val()) {
				old_alert("Please Select Model!!");
				$('#nonEleModelTextfiels').focus();
				return false;
			}	
			
			if (!$('#nonEleYearTextfiels').val()) {
				old_alert("Please enter Year!!");
				
				return false;
			}	
			
			if (!$('#nonElePriceTextfiels').val()) {
				old_alert("Please enter Price!!");
				
				return false;
			}	
			
			var badic_idv = parseFloat($("#basicIdv").val());
	        var validation = badic_idv * 20 / 100;
	         
	        var tot = parseFloat($("#nonEleTotalTextfiels").val()) + parseFloat($("#nonElePriceTextfiels").val());
			if (tot>validation) {
				old_alert("Total Must Not Be More Than 20% Of Basic IDV");
				$('#nonElePriceTextfiels').focus();
				return false;
			}
			return true;
		}
		
	function isNumber(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	   /*      old_alert("Please enter only Numbers."); */
	        return false;
	    }

	   
	}
	function ischar(evt) {
	    evt = (evt) ? evt : window.event;
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode < 65 && (charCode > 90 || charCode < 8)) {
	   /*      old_alert("Please enter only Numbers."); */
	        return false;
	    }

	   
	}
	
	
	function SetFocus() {
		// safety check, make sure its a post 1999 browser
		if (!document.getElementById) {
			return;
		}

		var txtMyInputBoxElement = document.getElementById("setFocus");

		if (txtMyInputBoxElement != null) {
			txtMyInputBoxElement.focus();
		}
	}

	function checkEmail(emailIdTf) {

	    var email = document.getElementById(emailIdTf);
	    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	    if (!filter.test(email.value)) {
	    old_alert('Please provide a valid email address');
	   /*  email.focus; */
	    $('#'+emailIdTf).focus();
	    return false;
	 }
	}
	
	function checkPanNo(panNoTf) {
		var panNo = document.getElementById(panNoTf).value;
		var regex1=/^[A-Z]{5}\d{4}[A-Z]{1}$/;  
	      if(regex1.test(panNo)== false)
	      {
	        old_alert('Please enter valid Pan number');
	        $('#'+panNoTf).focus();
	        return false;
	      }

	}
	
	
	  function ValidateAlpha(e) {
          try {
              if (window.event) {
                  var charCode = window.event.keyCode;
              }
              else if (e) {
                  var charCode = e.which;
              }
              else { return true; }
              if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
                  return true;
              else
                  return false;
          }
          catch (err) {
              old_alert(err.Description);
          }
      }

/* insured Details method starts Here */
	
	function insDtlInitial(){
		$("#insDtlInitial").empty();
		$("#insDtlInitial").append('<option value="">--Select--');
		var resp = getRecordList("70","4");
		$.each(resp, function (key, value) {
			nKey = parseInt(key) + 1;
			$("#insDtlInitial").append(
			/* 	'<option value="'+resp[key].DET_ID+'">'+resp[key].DET_NAME+''
			); */
			
			'<option insDtlInitialDtl="'+resp[key].OTHER_DETAILS+'" value="'+resp[key].DET_ID+'"> '
			+ resp[key].DET_NAME+ '');
		});
}
	
		function insDtlMaritialStatus(){
			$("#insDtlMaritialStatus").empty();
			$("#insDtlMaritialStatus").append('<option value="">--Select--');
			var resp = getRecordList("70","25");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlMaritialStatus").append('<option value="' + resp[key].DET_ID + '">'+resp[key].DET_NAME+'');
			});
		}
		
		function insDtlNationality(){
			$("#insDtlNationality").empty();
			$("#insDtlNationality").append('<option value="">--Select--');
			var resp = getRecordList("70","26");
			$.each(resp, function (key, value) {
				nKey = parseInt(key) + 1;
				$("#insDtlNationality").append('<option value="' + resp[key].DET_ID + '"> ' +resp[key].DET_NAME +'');
			});
		}
	
	/* CA insDtlCAState  insDtlCADistrict  insDtlCACity  insDtlCAPincode */
	
	function insDtlCADistrict(){
		var insDtlCACountryId = $("#insDtlCACountry").val();
		var insDtlCAStateId = $("#insDtlCAState").val();
		var param= insDtlCACountryId+ "~" +insDtlCAStateId ; 
		var resp = getRecordList("97",param);
		$.each(resp, function (key, value) {
					nKey = parseInt(key) + 1;
					$("#insDtlCADistrict")
						.append(
							'<option value="' + resp[key].DISTRICT_ID + '"> ' +
							resp[key].DISTRICT_NAME +
							'');
				});
	}

	

	function searchData()
	{
		 var input, filter, table, tr, td1,td2, i;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("voluntary");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
		    td1 = tr[i].getElementsByTagName("td")[2];
		    td2 = tr[i].getElementsByTagName("td")[3];
		    if (td1|| td2) {
		      if (td1.innerHTML.toUpperCase().indexOf(filter) > -1 || td2.innerHTML.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }       
		  }
	}
	$('.searchFromTablePack').keyup(function() {
		var $rows = $('.searchCoverTablePack tr');
	    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
	    
	    $rows.show().filter(function() {
	        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
	        return !~text.indexOf(val);
	    }).hide();
	});
	$('.searchFromTableLib').keyup(function() {
		var $rows = $('.searchCoverTableLib tr');
	    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
	    
	    $rows.show().filter(function() {
	        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
	        return !~text.indexOf(val);
	    }).hide();
	});
	$('.searchFromTableAddon').keyup(function() {
		var $rows = $('.searchCoverTableAddon tr');
	    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
	    
	    $rows.show().filter(function() {
	        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
	        return !~text.indexOf(val);
	    }).hide();
	});
	$('.searchFromTableOd').keyup(function() {
		var $rows = $('.searchCoverTableOd tr');
	    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
	    
	    $rows.show().filter(function() {
	        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
	        return !~text.indexOf(val);
	    }).hide();
	});
	$('.searchFromTableTp').keyup(function() {
		var $rows = $('.searchCoverTableTp tr');
	    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
	    
	    $rows.show().filter(function() {
	        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
	        return !~text.indexOf(val);
	    }).hide();
	});
	
	function showPackDetails() {
		showWait();
		/* if ($.fn.DataTable.isDataTable('#packageDetails')) {
			$('#packageDetails').DataTable().destroy();
			
			} */
			var productnameid = $("#productname").val();
			var RTOStateid = $("#RTOState").val();
			var RTOSCityid = $("#RTOSCity").val();
			var proposalid = $("#proposal").val();
			var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
			var applicantid = $("#applicantname").val();
			var fuelKitId = $("#fuelKit").val();
			var varienceId = $("#varience").val();

			//var productnameid = $("#productname").val();
		//	var RTOStateid = $("#RTOState").val();
		//	var RTOSCityid = $("#RTOSCity").val();
			//var proposalid = $("#proposal").val();
			//var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
			//var applicantid = $("#applicantname").val();
			//var fuelKitId = $("#fuelKit").val();
			//var varienceId = $("#varience").val();
				var policyType = $("#policyTypeId").val() ;
			$.ajax({
				url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=FIND_COVERS&table_name=IMCP&str_company_type=&str_gic=&str_gicbid=&str_prod="
						+ productnameid
						+ "&str_discnm=&str_rgrp=&str_state="
						+ RTOStateid
						+ "&str_city="
						+ RTOSCityid
						+ "&str_prpsl="
						+ proposalid
						+ "&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype="
						+ fuelTypeId
						+ "&str_fuelkit="
						+ fuelKitId
						+ "&str_veh=&str_mod=&str_var="
						+ varienceId
						+ "&str_policy_age=&str_hbbid=&str_user_level=&str_user_id="
						+ applicantid
						+ "&str_login_type=HB+EMPLOYEE&str_gap=&str_ageto=&str_poltype=1&str_calctype=",
				type : 'post',
				dataType : 'json',
				async : false,
				/* beforeSend: function () {
					showWait();
					},
					complete: function () {
					//hideWait();
					}, */
				beforeSend: function () {
					showWait();
				},
				success : function(resp) {
					hideWait();
					// var row=1;
					$.each(resp, function(key, value) {
												nKey = parseInt(key) + 1;
												var status = resp[key].COVER_STATUS;
												var coverIdentity = resp[key].COVER_IDENTITY;
											//	old_alert(status);
												var formID = resp[key].FORM_ID;
												var formCaption = resp[key].FORM_CAPTION;
												var coverId=  resp[key].COVER_ID;
												var numField = resp[key].NUM_FIELD;
												var row2 = "<tr><td style='width: 40px'>"
														+ nKey
														+ '</td><td style="display:none;">'
														+ resp[key].COVER_IDENTITY
														+ '</td><td style="width: 200px">'
														+ resp[key].COVER_DETAILS
														+ "</td>"
														+ (formID == "262" && formCaption == "ELECTRICAL ACCESSORIES"? '<td style="width: 95px" data-backdrop="static" id="eleform262" data-toggle="modal" data-target="#eleAcc" onclick="getCoverIdentity('+coverIdentity+');">'
																+ "<u style='color:#283FE6;'>"+ resp[key].COVER_STATUS +"</u>"
																+ '</td>'
																:(formID == "262" && formCaption == "NON ELECTRICAL ACCESSORIES"? '<td style="width: 95px" data-backdrop="static" id="nonEleform262" data-toggle="modal" data-target="#nonEleAcc" onclick="getCoverIdentity('+coverIdentity+');">'
																		+ "<u style='color:#283FE6;'>"+resp[key].COVER_STATUS +"</u>"
																		+ '</td>'
																		: (formID == "263" ?
																			'<td style="width: 95px" id="volAccform263" data-toggle="modal" data-backdrop="static" data-target="#voluntaryAccess" onclick="getVoluntaryData('+coverIdentity+','+coverId+');getCoverIdentity('+coverIdentity+');">'
																		+ "<u style='color:#283FE6;'>"+  resp[key].COVER_STATUS +"</u>"
																		+ '</td>'
																		: '<td style="width: 95px"><input type="text" style="width: 80px;" id="tblBiofuelKitPack" class="numberOnlyWithDecimal tblBiofuelKit" placeholder="0.00" onclick="getCoverIdentity('+coverIdentity+');">'
																			//	+ resp[key].COVER_STATUS
																				+ '</td>')))
														+ '<td style=display:none;">'
														+ resp[key].COVER_VAL
														+ '</td><td style="display:none;">'
														+ resp[key].COVER_ID
														+ '</td><td style=display:none;">'
														+ resp[key].POLICYTYPE
														+ '</td><td style="display:none;">'
														+ resp[key].P_ID
														+ '</td><td style="display:none;">'
														+ resp[key].FORM_ID
														+ '</td><td style="display:none;">'
														+ resp[key].FORM_CAPTION
														+ '</td><td style="display:none;">'
														+ resp[key].NUM_FIELD
														+ '</td >'
														+ (numField == "N" ? '<td style="display:none;">' +
																0 + '</td >'
																: '<td style="display:none;"></td>')
														+ "</tr>";
												$("#packageDetails").append(row2);
												
												 
								});
							
						},
					});
			reLoadValidation();
	}
	
	function showExtraCompanyCovers(companyId) {
		showWait();
			
			var seletedId="",id="";
			$("#extraCovers td").remove();
			
			if(companyId=="2"){
				seletedId=$('#RelianceExtraCovers').val();
			}
			else if(companyId=="4"){
				seletedId=$('#HdfcExtraCovers').val();
			}
			console.log("seletedId==============>>"+seletedId); 
			if(seletedId!=""){
				var selcov=seletedId.split("~");
				var allSelId=selcov[0];
				 id=allSelId.split(",");
				console.log()
				console.log("selcov==============>>"+selcov); 
				console.log("allSelId==============>>"+allSelId); 
			}
			
			console.log("id==============>>"+id); 
			
			
			var productnameid = $("#productname").val();
			var RTOStateid = $("#RTOState").val();
			var RTOSCityid = $("#RTOSCity").val();
			var proposalid = $("#proposal").val();
			var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
			var applicantid = $("#applicantname").val();
			var fuelKitId = $("#fuelKit").val();
			var varienceId = $("#varience").val();

			console.log("Inside showExtraCompanyCovers");
				var policyType = $("#policyTypeId").val() ;
			$.ajax({
				url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=FIND_COVERS&table_name=IMCP&str_company_type=&str_gic="+companyId+"&str_gicbid=&str_prod="
						+ productnameid
						+ "&str_discnm=&str_rgrp=&str_state="
						+ RTOStateid
						+ "&str_city="
						+ RTOSCityid
						+ "&str_prpsl="
						+ proposalid
						+ "&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype="
						+ fuelTypeId
						+ "&str_fuelkit="
						+ fuelKitId
						+ "&str_veh=&str_mod=&str_var="
						+ varienceId
						+ "&str_policy_age=&str_hbbid=&str_user_level=&str_user_id="
						+ applicantid
						+ "&str_login_type=HB+EMPLOYEE&str_gap=&str_ageto=&str_poltype=1&str_calctype=",
				type : 'post',
				dataType : 'json',
				async : false,
				
				beforeSend: function () {
					showWait();
				},
				success : function(resp) {
					hideWait();
					$.each(resp, function(key, value) {

						nKey = parseInt(key) + 1;
						var status = resp[key].COVER_STATUS;
						var coverIdentity = resp[key].COVER_IDENTITY;
					//	old_alert(status);
						var formID = resp[key].FORM_ID;
						var formCaption = resp[key].FORM_CAPTION;
						var coverId=  resp[key].COVER_ID;
						var numField = resp[key].NUM_FIELD;
						
						/* ----------------------------------Trail Code-------------------------------------------------- */
						 var chk=false,chkRow="";
						if(id.length>0){
						for(i=0;i<id.length;i++){
							console.log("inside if id.length"+id.length); 
							if(coverIdentity==id[i]){
								chk=true;
							}
						
						if(chk==true){
							console.log("2222222222222222222222222222222222"); 
							chkRow = '<input type="checkbox" name="checkbox" id="extraCheck'+nKey+'" onclick = "openExtraCovers('+coverIdentity+','+coverId+','+nKey+');selectedExtraCovers('+companyId+');" checked>';
						}else{
							console.log("333333333333333333333333333333333333"); 
							chkRow = '<input type="checkbox" name="checkbox" id="extraCheck'+nKey+'" onclick = "openExtraCovers('+coverIdentity+','+coverId+','+nKey+');selectedExtraCovers('+companyId+');">';
						}

						}
						}else{
							chkRow = '<input type="checkbox" name="checkbox" id="extraCheck'+nKey+'" onclick = "openExtraCovers('+coverIdentity+','+coverId+','+nKey+');selectedExtraCovers('+companyId+');">';
						} 
						
						/* ----------------------------------Trail Code-------------------------------------------------- */
						//var row = '<input type="checkbox" name="checkbox" id="extraCheck'+nKey+'" onclick = "openExtraCovers('+coverIdentity+','+coverId+','+nKey+');selectedExtraCovers('+companyId+');">';
						
						var row2 = "<tr><td style='width: 40px' >"
								+ chkRow
								+ '</td><td style="display:none;">'
								+ resp[key].COVER_IDENTITY
								+ '</td><td style="width: 200px">'
								+ resp[key].COVER_DETAILS
								+ "</td>"
								+ '<td style="width: 95px" id="volAccform263" data-toggle="modal" data-backdrop="static" data-target="#voluntaryAccess" onclick="checkCheckBox(\'extraCheck'+nKey+'\');getVoluntaryData('+coverIdentity+','+coverId+');getCoverIdentity('+coverIdentity+');selectedExtraCovers('+companyId+');">'
									+ "<u style='color:#283FE6;'>"+  resp[key].COVER_STATUS +"</u></td>"
								+ '<td style=display:none;">'
								+ resp[key].COVER_VAL
								+ '</td><td style="display:none;">'
								+ resp[key].COVER_ID
								+ '</td><td style=display:none;">'
								+ resp[key].POLICYTYPE
								+ '</td><td style="display:none;">'
								+ resp[key].P_ID
								+ '</td><td style="display:none;">'
								+ resp[key].FORM_ID
								+ '</td><td style="display:none;">'
								+ resp[key].FORM_CAPTION
								+ '</td><td style="display:none;">'
								+ resp[key].NUM_FIELD
								+ '</td >'
								+ (numField == "N" ? '<td style="display:none;">' +
										0 + '</td >'
										: '<td style="display:none;"></td>')
								+ "</tr>";
						$("#extraCovers").append(row2);
					});
					if(!$.fn.DataTable.isDataTable('#extraCovers')){
						$('#extraCovers').DataTable({
							destroy: true,	
							"columnDefs": [
							                {  "targets":[-1] ,"searchable": false, "sortable": false}
							              ] 
						});
					}
				},
			});
	}
	
	function selectedExtraCovers(companyId){
	    var additionalcoverArr = [];
		var k=1;	
		var id="",value="";
		
		$("table#extraCovers tr").each(function() { 
			var additionalcoverArrayThisRow = [];
			var tableData = $(this).find('td');
			 if (tableData.length > 0) {
				 tableData.each(function() { additionalcoverArrayThisRow.push($(this).text()); });
				 additionalcoverArr.push(additionalcoverArrayThisRow);
			}
			 console.log("additionalcoverArrayThisRow for extra covers=="+additionalcoverArrayThisRow);
			 var a=additionalcoverArrayThisRow[1];
			 var b=additionalcoverArrayThisRow[3];
			 // var c=additionalcoverArrayThisRow[2];
			 console.log("a b  c----------->>"+a+" "+b);
			  if(a!=undefined && b!=undefined){
				 if($("#extraCheck"+k).prop("checked")){
						if(id==""){
							id=a;
							value=b;
							console.log("id----->>"+id+"  value----->>"+value);
						}else{
							id=id+","+a;
							value=value+","+b;
							console.log("id----->>"+id+"  value----->>"+value);
						}
						$('#RelianceExtraCovers').val("");
						$('#HdfcExtraCovers').val("");
					}
			 k++;
			} 
			
		});
		
		if(companyId=="2"){
			$('#RelianceExtraCovers').val(id+"~"+value);
		}
		else if(companyId=="4"){
			$('#HdfcExtraCovers').val(id+"~"+value); 	
		}
		
		console.log("companyCov----------->>"+id+"~"+value) 
	}

function openExtraCovers(coverIdentity,coverId,nKey)
{
	
	var ch1= $("#extraCheck"+nKey).prop("checked");
	console.log("ch1:::" +ch1);
	if(!ch1)
	{
				//clearAccidentalData();
				$("#voluntaryAccess").modal("hide");
				$('#extraCompanyCovers').modal('show');
				$('#extraCovers tbody tr').eq($("#row_index").val()).find('td').eq(3).html("<u style='color:#283FE6;'>"+"SELECT"+"</u>");
				$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).html("");
				$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(24).html("");
				$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(25).html("");
				
	}else{
		getCoverIdentity(coverIdentity);
		getVoluntaryData(coverIdentity,coverId);
			$('#extraCompanyCovers').modal('hide');
			$('#voluntaryAccess').modal({
                backdrop: 'static',
                keyboard: false, 
                show: true
        	}); 
	}
			$('#voluntaryCancel').click(function () {
		   	 	$("#extraCheck"+nKey).prop("checked", false);
		   		$('#extraCompanyCovers').modal('show');
		   	 	$('#extraCovers tbody tr').eq($("#row_index").val()).find('td').eq(3).html("<u style='color:#283FE6;'>"+"SELECT"+"</u>");
		   	 	$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).html("");
				$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(24).html("");
				$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(25).html("");
			});
			
		
	}
					
	


	
function showLibilityDetails() {
		
		/* if ($.fn.DataTable.isDataTable('#liabilityDetails')) {
			$('#liabilityDetails').DataTable().destroy();
			
			} */
		var productnameid = $("#productname").val();
		var RTOStateid = $("#RTOState").val();
		var RTOSCityid = $("#RTOSCity").val();
		var proposalid = $("#proposal").val();
		var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
		var applicantid = $("#applicantname").val();
		var fuelKitId = $("#fuelKit").val();
		var varienceId = $("#varience").val();
		$
		.ajax({
			url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=FIND_COVERS&table_name=IMCP&str_company_type=&str_gic=&str_gicbid=&str_prod="
					+ productnameid
					+ "&str_discnm=&str_rgrp=&str_state="
					+ RTOStateid
					+ "&str_city="
					+ RTOSCityid
					+ "&str_prpsl="
					+ proposalid
					+ "&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype="
					+ fuelTypeId
					+ "&str_fuelkit="
					+ fuelKitId
					+ "&str_veh=&str_mod=&str_var="
					+ varienceId
					+ "&str_policy_age=&str_hbbid=&str_user_level=&str_user_id="
					+ applicantid
					+ "&str_login_type=HB+EMPLOYEE&str_gap=&str_ageto=&str_poltype=2&str_calctype=",
			type : 'post',
			dataType : 'json',
			async : false,
			beforeSend: function () {
				showWait();
			},
			success : function(resp) {
				hideWait();
				// var row=1;
				$.each(resp, function(key, value) {
							var status = resp[key].COVER_STATUS;
							var coverIdentity = resp[key].COVER_IDENTITY;
						//	old_alert(status);
							var numField= resp[key].NUM_FIELD ;
							//old_alert(numField);
							var formID = resp[key].FORM_ID;
							var formCaption = resp[key].FORM_CAPTION;
							var coverId=  resp[key].COVER_ID;
							var	nKey = parseInt(key) + 1;
							var row2 = "<tr><td style='width: 40px'>" + nKey + "</td>"+'</td><td style="display:none;">'
									+ resp[key].COVER_IDENTITY + "</td>"+'</td><td style="display:none;">'
									+ resp[key].COVER_VAL + "</td>"+'</td><td style="display:none;">'
									+ resp[key].FORM_CAPTION + "</td>"+'</td><td style="display:none;">'
									+ resp[key].NUM_FIELD + "</td>"+'</td><td style="display:none;">'
									+ resp[key].POLICYTYPE + "</td>"
									+"<td style='width: 200px'>"
									+ resp[key].PACKAGE_COVERS + "</td>"+
									(status == "SELECT" ?
											 (formID == "263" ? '<td style="width: 95px" id="tppd263" data-backdrop="static" data-toggle="modal" data-target="#Tppd" onclick="getTPPDData('+coverIdentity+','+coverId+',this);getCoverIdentity('+coverIdentity+');hideNumField(\''+numField+'\');">'
											+ "<u style='color:#283FE6;'>"+ resp[key].COVER_STATUS +"</u>"
											+ '</td>'
											:(formID == "" ? "<td style='width: 95px'>"+" "+"</td>":"<td style='width: 95px'><u style='color:#283FE6;'>"+ resp[key].COVER_STATUS +"</u></td>")):(status != "DATE" || status != "SELECT"?
													'<td style="width: 95px">'
													+ '<input type="text" class="numberOnlyWithDecimal tblBiofuelKit" style="width: 80px;" id="tblBiofuelKitLib" placeholder="0.00">'
													+ '</td>'
													: '<td style="width: 95px">'+ "<u style='color:#283FE6;'>"+ resp[key].COVER_STATUS +"</u>"
															+ '</td>'))
									+'</td><td style="display:none;">'
									+ +resp[key].COVER_ID + "</td>"+'</td><td style="display:none;">'
									+ resp[key].FORM_ID + "</td>"+'</td><td style="display:none;">'
									+ resp[key].P_ID +  "</td>"
									+ (numField == "N" ? '</td><td style="display:none;">'+
											0 + '</td>'
											:'</td><td style="display:none;">'+ 0 +'</td>')+
									+ "</tr>";
							$("#liabilityDetails").append(row2);

				});
				 				
			},
		});
		reLoadValidation();
	}

	function hideNumField(numField){
	//	 old_alert("noOfPersons::: " +numField);
		 if(numField == "N")
			{
				 document.getElementById("noOfPersonsDiv").style.visibility="hidden";
			}else{
				 document.getElementById("noOfPersonsDiv").style.visibility="visible";
			}  
	}

	
	function addEleAccTotable() {
	if(validateElec()){
		var accessory = $("#eleAccessoryTextfiels").val();
		var make = $("#eleMakeTextfiels").val();
		var model = $("#eleModelTextfiels").val();
		var manfYr = $("#eleYearTextfiels").val();
		var price = parseFloat($("#elePriceTextfiels").val());
		var remove = '<button id="removeItem" onclick="removeRowEle(this);" >Remove</button>';
	
		var rows = "";
		rows += "<tr><td>" + accessory + "</td><td>" + make + "</td><td>"
				+ model + "</td><td>" + manfYr + "</td><td>" + price
				+ "</td><td>" + remove + "</td></tr>";
		$(rows).appendTo("#eleAccesory tbody");
	
		
		document.getElementById("eleAccessoryTextfiels").value="";
		document.getElementById("eleMakeTextfiels").value="";
		document.getElementById("eleModelTextfiels").value="";
		document.getElementById("eleYearTextfiels").value="";
		document.getElementById("elePriceTextfiels").value="";
		updateEleTotalSI();
	
	}
		
	}
	function updateEleTotalSI()
	{
		var total = 0;
		$("table#eleAccesory td:nth-child(5)").each(function() {
		    total += parseInt($(this).text());
		});
	//	old_alert(total);
	if(total !=0){
		$("#eleTotalTextfiels").val(total);
	}
	else{
		$("#eleTotalTextfiels").val("");
		}
	}

	function removeRowEle(r) {
		 var i = r.parentNode.parentNode.rowIndex;
		 document.getElementById("eleAccesory").deleteRow(i);
		 updateEleTotalSI();
	}
	
	function saveEleData(){
		
			
		if(!$("#eleTotalTextfiels").val()){
			old_alert("Please Add Electrical Accesories");
			$('#eleOk').attr("data-dismiss",""); 
	}else{
	var electricalAcc="";
		var eleAccArray = [];
		$("table#eleAccesory tr").each(function() { 
			var eleAccArrayThisRow = [];
			var tableData = $(this).find('td');
			 if (tableData.length > 0) {
				 tableData.each(function() { eleAccArrayThisRow.push($(this).text()); });
				 eleAccArray.push(eleAccArrayThisRow);
			}
		});
		for(var i=0; i<eleAccArray.length ; i++)
		{
			for(var j=0; j<eleAccArray[i].length ; j++)
			{
				/* electricalAcc +=myTableArray[i][j]+"~" ; */
				/* old_alert(" in first if  "+electricalAcc); */
				if(j==0 || j==1||j==2 || j==3){
					if(j==1){
						electricalAcc +=eleAccArray[i][j]+" ";
					}else{
						if(electricalAcc=="")
						{
							electricalAcc =eleAccArray[i][j]+",";
						}else{
							electricalAcc +=eleAccArray[i][j]+",";
						}
					}
				}
				if(j==4){
					electricalAcc +=eleAccArray[i][j]+"~" ;
				}
			}
		}
			$("#electricalAcc").val(electricalAcc);	
		 if(coverId == 9){
	      // 	old_alert("eleTotalTextfiels" + $("#eleTotalTextfiels").val());
			 $('#packageDetails tbody tr').eq( $("#row_index").val()).find('td').eq($("#selectedIndex").val()).html($("#eleTotalTextfiels").val() +" "+ '<u style="color:#283FE6;">EDIT</u>');
			
 			var v=$("#eleTotalTextfiels").val();
			 
			 $("#totalEleAmount").val(v);
			 console.log("v---->>"+v);
			 var eleAmount=parseInt($("#totalEleAmount").val());
			 var nonEleAmount=parseInt($("#totalNonEleAmount").val());
			 var basicIdv=parseInt($("#basicIdv").val());
			
			 console.log(isNaN(eleAmount)+" "+isNaN(nonEleAmount));
			 if(isNaN(eleAmount)){
				 console.log("eleAmount=="+eleAmount);
				 eleAmount=0;
			 }
			 if(isNaN(nonEleAmount)){
				 console.log("nonEleAmount=="+nonEleAmount);
				 nonEleAmount=0;
			 }
			 console.log(eleAmount+" "+nonEleAmount+" "+basicIdv);
			 var tot=basicIdv+eleAmount+nonEleAmount;
			 console.log(tot);
			 document.getElementById("totalIDV").value = tot;
		 }
		 $('#eleOk').attr("data-dismiss","modal"); 
	   }
	}
	
	
	function clearEleData()
	{
		document.getElementById("eleAccessoryTextfiels").value="";
		document.getElementById("eleMakeTextfiels").value="";
		document.getElementById("eleModelTextfiels").value="";
		document.getElementById("eleYearTextfiels").value="";
		document.getElementById("elePriceTextfiels").value="";
		$('#eleAccesory td').remove();
		$('#eleTotalTextfiels').val("");
		$('#packageDetails tbody tr').eq( $("#row_index").val()).find('td').eq(3).html("<u style='color:#283FE6;'>"+"SELECT"+"</u>");
		$('#packageDetails tbody tr').eq( $("#row_index").val()).find('td').eq(4).html("");
	}
	function clearNonEleData()
	{
		document.getElementById("nonEleAccessoryTextfiels").value="";
		document.getElementById("nonEleMakeTextfiels").value="";
		document.getElementById("nonEleModelTextfiels").value="";
		document.getElementById("nonEleYearTextfiels").value="";
		document.getElementById("nonElePriceTextfiels").value="";
		$('#nonEleAccesory td').remove();
		$('#nonEleTotalTextfiels').val("");
		$('#packageDetails tbody tr').eq( $("#row_index").val()).find('td').eq($("#selectedIndex").val()).html("<u style='color:#283FE6;'>"+"SELECT"+"</u>");
	}

	
	 function clearVoluntaryData() {
		$('#extraCompanyCovers').modal('show');
		 $('#extraCovers tbody tr').eq($("#row_index").val()).find('td').eq(3).html("<u style='color:#283FE6;'>"+"SELECT"+"</u>");
	    	
	 }
	 function clearTppdData() {
		 $('#liabilityDetails tbody tr').eq($("#row_index").val()).find('td').eq($("#selectedIndex").val()).html("<u style='color:#283FE6;'>"+"SELECT"+"</u>");
		 $("#noOfPerson").val("");
	}

	
	function addNonEleAccTotable() {
		
		if(validateNonElec()){
			var accessory = $("#nonEleAccessoryTextfiels").val();
			var make = $("#nonEleMakeTextfiels").val();
			var model = $("#nonEleModelTextfiels").val();
			var manfYr = $("#nonEleYearTextfiels").val();
			var price = parseFloat($("#nonElePriceTextfiels").val());
			var remove = '<button id="removenonEleItem" onclick="removeRowNonEle(this);" >Remove</button>';
		
			var rows = "";
			rows += "<tr><td>" + accessory + "</td><td>" + make + "</td><td>"
					+ model + "</td><td>" + manfYr + "</td><td>" + price
					+ "</td><td>" + remove + "</td></tr>";
			$(rows).appendTo("#nonEleAccesory tbody");
		
			
			document.getElementById("nonEleAccessoryTextfiels").value="";
			document.getElementById("nonEleMakeTextfiels").value="";
			document.getElementById("nonEleModelTextfiels").value="";
			document.getElementById("nonEleYearTextfiels").value="";
			document.getElementById("nonElePriceTextfiels").value="";
			updateNonEleTotalSI();
			
		}
		
	}

	function updateNonEleTotalSI()
	{
		var total = 0;
		$("table#nonEleAccesory td:nth-child(5)").each(function() {
		    total += parseInt($(this).text());
		});
		//old_alert(total);
		if(total !=0){
		$("#nonEleTotalTextfiels").val(total);
		}
		else{
			$("#nonEleTotalTextfiels").val("");
			}
	}

	function removeRowNonEle(r) {
		 var i = r.parentNode.parentNode.rowIndex;
		 document.getElementById("nonEleAccesory").deleteRow(i);
		 updateNonEleTotalSI();
	}
	
	function saveNonEleData(){
	
		if(!$("#nonEleTotalTextfiels").val()){
			old_alert("Please select Non-Electrical Accesories");
			$('#nonEleOk').attr("data-dismiss",""); 
	}else{
		var nonElectricalAcc="";
		var nonEleAccArray = [];
		$("table#nonEleAccesory tr").each(function() { 
			var nonEleAccArrayThisRow = [];
			var tableData = $(this).find('td');
			 if (tableData.length > 0) {
				 tableData.each(function() { nonEleAccArrayThisRow.push($(this).text()); });
				 nonEleAccArray.push(nonEleAccArrayThisRow);
			}
		});
		for(var i=0; i<nonEleAccArray.length ; i++)
		{
			for(var j=0; j<nonEleAccArray[i].length ; j++)
			{
				/* electricalAcc +=myTableArray[i][j]+"~" ; */
			//	old_alert(" in first if  "+nonElectricalAcc);
				if(j==0 || j==1||j==2 || j==3){
					if(j==1){
						nonElectricalAcc +=nonEleAccArray[i][j]+" ";
					}else{
						if(nonElectricalAcc=="")
						{
							nonElectricalAcc =nonEleAccArray[i][j]+",";
						}else{
							nonElectricalAcc +=nonEleAccArray[i][j]+",";
						}
					}
				}
				if(j==4){
					nonElectricalAcc +=nonEleAccArray[i][j]+"~" ;
				}
			}
		}
		$("#nonElectricalAcc").val(nonElectricalAcc);
		 if(coverId == 2){
		      // 	old_alert("eleTotalTextfiels" + $("#eleTotalTextfiels").val());
			 $('#packageDetails tbody tr').eq($("#row_index").val()).find('td').eq($("#selectedIndex").val()).html($("#nonEleTotalTextfiels").val()+" "+ '<u style="color:#283FE6;">EDIT</u>');
			 
			 
			// $('#packageDetails tbody tr').eq($("#row_index").val()).find('td').eq($("#selectedIndex").val()).html($("#nonEleTotalTextfiels").val());
	
			 var v=$("#nonEleTotalTextfiels").val();
			 
			 $("#totalNonEleAmount").val(v);
			 console.log("v---->>"+v);
			 var eleAmount=parseInt($("#totalEleAmount").val());
			 var nonEleAmount=parseInt($("#totalNonEleAmount").val());
			 var basicIdv=parseInt($("#basicIdv").val());
			
			 console.log(isNaN(eleAmount)+" "+isNaN(nonEleAmount));
			 if(isNaN(eleAmount)){
				 console.log("eleAmount=="+eleAmount);
				 eleAmount=0;
			 }
			 if(isNaN(nonEleAmount)){
				 console.log("nonEleAmount=="+nonEleAmount);
				 nonEleAmount=0;
			 }
			 console.log(eleAmount+" "+nonEleAmount+" "+basicIdv);
			 var tot=basicIdv+eleAmount+nonEleAmount;
			 console.log(tot);
			 document.getElementById("totalIDV").value = tot;
		 }
		$('#nonEleOk').attr("data-dismiss","modal"); 
	}
	 }

	
	var elementIndex="";
	function getTPPDData(coverIdentity,coverId,element) {
		//old_alert("coverId::" +coverId);
		elementIndex = element;
	//	old_alert("hgsfj::: "+$("#elementIndex").val());
		var tb = document.getElementById("liabilityDetails");
		var str = $(tb.rows[element.parentNode.rowIndex].cells[6]).html();
	 //	old_alert("str:::"+str);
		
		var productnameid = $("#productname").val();
		var RTOSCityid = $("#RTOSCity").val();
		var proposalid = $("#proposal").val();
		var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
		var applicantid = $("#applicantname").val();
		var fuelKitId = $("#fuelKit").val();
		var varienceId = $("#varience").val();
		//old_alert(coverIdentity + "" + coverId);
		$("#modalTitle").empty();
		$("#modalTitle").html("&nbsp;&nbsp;"+str);
			
		$.ajax({
					url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=FIND_COVERS&table_name=IMCP&str_company_type=&str_gic=&str_gicbid=&str_prod="
						+ productnameid
						+ "&str_discnm="+ coverIdentity+ "&str_rgrp=&str_state="
						+ "&str_city="
						+ RTOSCityid
						+ "&str_prpsl="
						+ proposalid
						+ "&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype="
						+ fuelTypeId
						+ "&str_fuelkit="
						+ fuelKitId
						+ "&str_veh=&str_mod=&str_var="
						+ varienceId
						+ "&str_policy_age=&str_hbbid=&str_user_level=&str_user_id="
						+"&str_login_type=&str_gap=&str_ageto=&str_poltype=1&str_calctype=&valID="+coverId,
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(data) {
						 $("#tppdRestrict").DataTable({
				   			'data' : data,
				   			 "columns": [
				   		          { "data": "ID" },
				   		          { "data": "RATE"},
				   		         { "data": "AMOUNT" }],
				   		      select: {
						            style: 'single'
						        },
				   		    'destroy': true,
				   		    "lengthMenu": [5, 10, 20, 50],
					        "pageLength": 5,
					        "bInfo" : false
			   				});
						},
				});
		

	}

/* function  refreshVoluntaryTable() {
	$('#voluntary td').remove();
	getVoluntaryData();
	
}
function  refreshTable() {
	$('#tppdRestrict td').remove();
	getTPPDData();
	
} */

	var allCovers="";
	function getVoluntaryData(coverIdentity,coverId) {

		$('#extraCompanyCovers').modal('hide');
		var productnameid = $("#productname").val();
		var RTOSCityid = $("#RTOSCity").val();
		var proposalid = $("#proposal").val();
		var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
		var applicantid = $("#applicantname").val();
		var fuelKitId = $("#fuelKit").val();
		var varienceId = $("#varience").val();
		
			
		$.ajax({
					url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=FIND_COVERS&table_name=IMCP&str_company_type=&str_gic=&str_gicbid=&str_prod="
						+ productnameid
						+ "&str_discnm="+ coverIdentity+ "&str_rgrp=&str_state="
						+ "&str_city="
						+ RTOSCityid
						+ "&str_prpsl="
						+ proposalid
						+ "&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype="
						+ fuelTypeId
						+ "&str_fuelkit="
						+ fuelKitId
						+ "&str_veh=&str_mod=&str_var="
						+ varienceId
						+ "&str_policy_age=&str_hbbid=&str_user_level=&str_user_id="
						+"&str_login_type=&str_gap=&str_ageto=&str_poltype=1&str_calctype=&valID="+coverId,
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(data) {
						volTable= $("#voluntary").DataTable({
				   			'data' : data,
				   			"columnDefs": [
				   			            {
				   			                "targets": [ 0 ],
				   			                "visible": false,
				   			                "searchable": false
				   			            }],
				   			"columns": [
								{ "data": "ID"},
					   		    { "data": "RATE" },
					   		    { "data": "AMOUNT" }
					   		    ],
					   		    'destroy': true,
					   		 select: {
						            style: 'single'
						        },
						 	"lengthMenu": [5, 10, 20, 50],
					        "pageLength": 5,
					        "bInfo" : false
			   				});
						},
					});
		
	}

	
	function getVoluntaryExcessData() {
		
		 var cellData="";
		 var table = $('#voluntary').DataTable();
		 table.rows({selected:  true}).every( function (rowIdx, tableLoop, rowLoop ) {
		 	var rowNode = this.node();
		   	$(rowNode).find("td:visible").each(function (){
		       cellData = $(this).text();
		       old_alert(cellData);
		    });
		   // old_alert("cellData::: " + cellData);
		});
		if(cellData !=""){
		$('#extraCompanyCovers').modal('show');
		var	coverId = $("#coverId").val();
		var personCount="";
		console.log("coverId:::"+coverId);
	    if(coverId == 104){
	    	 personCount="0";
	    	 console.log("row_index:::"+$("#row_index").val());
	       	 console.log("row_indexCmpTbl:::"+$("#row_indexCmpTbl").val());
	    	 $('#extraCovers tbody tr').eq($("#row_index").val()).find('td').eq(3).html(cellData +" "+ '<u style="color:#283FE6;">EDIT</u>');
	    	 updateCoversInCompanyGrid(coverId,cellData,personCount);
	    }
	    $('#voluntaryOk').attr("data-dismiss","modal"); 
	  }else{
			old_alert("Please select voluntary excess data ");
			$('#voluntaryOk').attr("data-dismiss",""); 
			$('#extraCompanyCovers').modal('hide');
			}
	 }

	function updateCoversInCompanyGrid(coverId,cellData,personCount){
	var coverIdentity = $('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).text();
	 
	/* if(companyId=="2"){
		seletedId=$('#RelianceExtraCovers').val();
	}
	else if(companyId=="4"){
		seletedId=$('#HdfcExtraCovers').val();
	} */
	
	console.log("coverIdentitycoverIdentity  IN updateCoversInCompanyGrid:::"+coverIdentity);
	console.log("coverIdentity IN updateCoversInCompanyGrid:::"+coverId);
	 console.log("coverIdentity IN updateCoversInCompanyGrid:::"+cellData);
	 console.log("coverIdentity IN updateCoversInCompanyGrid:::"+personCount);
   	 if(coverIdentity == "")
	   {
   	   	var coveridVal = $('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).text();
   		console.log("coveridVal IN updateCoversInCompanyGrid:::"+coveridVal);
		$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).html(coverId);
	   }
	   else{
		   var coveridVal = $('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).text();
		   coverId = coverId + "," +coveridVal ; 
		   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).html(coverId);
	   }
	   
	   if($('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').eq(24).text() == "")
	   {
		   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(24).html(cellData);
	   }
	   else{
		   var sumInsured = $('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').eq(24).text();
		   cellData = cellData + "," +sumInsured ; 
		   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(24).html(cellData);
	   }
	   
	   if($('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').eq(25).text() == "" )
	   {
		   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(25).html("0");
	   }
	   else{
		   var noOfpersons = $('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').eq(25).text();
		   personCount = personCount + "," +noOfpersons ; 
		   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(25).html(personCount);
	   }
	   
	}
	function getExraCovers(){
		  console.log("row_indexCmpTbl:::"+row_indexCmpTbl);
		 // $('#companyDetails tbody tr').eq( $("#row_indexCmpTbl").val()).remove();
		  $('#extraCoverOk').attr("data-dismiss","modal"); 
		/*   $('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).text();
		  console.log("value:::"+$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).text()); */
		  companyPremium();
			
		//  hitCompanyPremiumRequest();    
		
		}
	
	function companyPremium()
	{
		  var companyDtlArr = [];
		  $('#companyDetails tbody').find('tr').eq($("#row_indexCmpTbl").val()).find('td').each(function() { 

		         var textval = $(this).text(); // this will be the text of each <td>
		         companyDtlArr.push(textval);
		 });
			var comapanyDtlRow = companyDtlArr.join (",");
			 console.log("value:::"+comapanyDtlRow);
			 $("#comapanyDtlRow").val(comapanyDtlRow);
				if(comapanyDtlRow !=""){
					var comapanyDtlRowData = comapanyDtlRow.split(",");
					var cmpId = comapanyDtlRowData[17];
					if(cmpId == "2")
					{
						getReliancePremiumResponse();
					}
				}
	}
	
	function cancelExtraCover(){
		var count = $('table#extraCovers tr:last').index() + 1;
		console.log("count :" + count);
		for(var i=1;i<=count;i++){
			$("#extraCheck"+i).prop("checked", false);
		}
		$('#RelianceExtraCovers').val("");
		$('#HdfcExtraCovers').val("");
		$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(23).html("");
		$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(24).html("");
		$('#companyDetails tbody tr').eq($("#row_indexCmpTbl").val()).find('td').eq(25).html("");
		
		companyPremium();
	}
	 
	function getLibilityCoversData() {
		 var cellData="";
		 var table = $('#tppdRestrict').DataTable();
		 table.rows({selected:  true}).every( function ( rowIdx, tableLoop, rowLoop ) {
		 	var rowNode = this.node();
		  	 $(rowNode).find("td:visible").each(function (){
		       cellData = $(this).text();
		     //  old_alert(cellData);
		    });
		 //   old_alert("cellData::: " + cellData);
		});
		/* 	var ele = $("#elementIndex").val();
			old_alert("ele::;" +ele); */
		//	old_alert("cellData::: " + cellData);
			
			$('#liabilityDetails tbody tr').eq($("#row_index").val()).find('td').eq($("#selectedIndex").val()).html(cellData+" "+ '<u style="color:#283FE6;">EDIT</u>' );
			var tb = document.getElementById("liabilityDetails");
			var noPerson = $(tb.rows[elementIndex.parentNode.rowIndex].cells[4]).html();

			if(cellData== "")
			{
				old_alert("Please select Amount");
				$('#libCoverOk').attr("data-dismiss",""); 
			}
			else if((noPerson == "Y" && ($("#noOfPerson").val()=="" || $("#noOfPerson").val()=="0")))
				{
				old_alert("Please select no of persons");
				$("#noOfPerson").focus();
				$('#libCoverOk').attr("data-dismiss",""); 
				}
			else{
			//	old_alert("go ahead");
				$('#libCoverOk').attr("data-dismiss","modal"); 
				if(noPerson == "Y")
				{
			 		$('#liabilityDetails tbody tr').eq($("#row_index").val()).find('td').eq(11).html($("#noOfPerson").val());
			 		$("#noOfPerson").val("");
				}
				else
				{
					$('#liabilityDetails tbody tr').eq($("#row_index").val()).find('td').eq(11).html("0");
				}
			}
			
		}

	
	function additionalCoverage() {
		/* if ($.fn.DataTable.isDataTable('#additionalcover')) {
			$('#additionalcover').DataTable().destroy();
			
			} */
		 var productnameid = $("#productname").val();
			var RTOStateid = $("#RTOState").val();
			var RTOSCityid = $("#RTOSCity").val();
			var proposalid = $("#proposal").val();
			var fuelTypeId = $('#varience option:selected').attr('relfuelTypeId');
			var applicantid = $("#applicantname").val();
			var fuelKitId = $("#fuelKit").val();
			var varienceId = $("#varience").val();
			var age = $("#age").val();
			
					var resp = getInsFindFormData("PKG_INS_FIND","FIND_ADDON","AMCP","","","",productnameid,"","",RTOStateid,RTOSCityid,proposalid,"","","","","","","",fuelTypeId,"","",varienceId,age,"","",applicantid,"HB EMPLOYEE","","","","");	
						$.each(resp,function(key, value) {
											nKey = parseInt(key) + 1;
											var coverIdentity = resp[key].COVER_IDENTITY;
											var formID = resp[key].FORM_ID;
											if(formID == "")
											{
												formID = parseInt("0");
											}
											//console.log("formId:::>>"+nKey + "  "  +formID);
											var row = '<input type="checkbox" name="checkbox" id="addonCheck'+nKey+'" onclick = "openModel('+formID+','+coverIdentity+','+nKey+');">';
										
											//var Edit = '<a href="#">Edit</a>';
											var row2 = '<tr><td style="text-align: center;width:37px;" >' + row + "</td>"+
											(formID > "0" ? 
													(formID == "261" ? 
															'<td style="width:45px;" id="accident261"  data-target="#accidentalCover" onclick="checkCheckBox(\'addonCheck'+nKey+'\');openModel('+formID+','+coverIdentity+','+nKey+'); getAdditionalCover('+nKey+'); getCoverIdentity('+coverIdentity+');">'
															+ "<u style='color:#283FE6;'>"+ "Edit"+ '</u></td>' 
															:(formID == "265" ? 
																	'<td style="width:45px;" id="geo265"   data-target="#geoExtension" onclick="checkCheckBox(\'addonCheck'+nKey+'\');openModel('+formID+','+coverIdentity+','+nKey+'); getAdditionalCover('+nKey+'); ">'
																	+"<u style='color:#283FE6;'>"+ "Edit"+ '</u></td>' 
																	: (formID == "266" ?
																			'<td style="width:45px;" id="aai266"  data-target="#aaiMembership"  onclick="checkCheckBox(\'addonCheck'+nKey+'\');openModel('+formID+','+coverIdentity+','+nKey+'); getAdditionalCover('+nKey+'); getCoverIdentity('+coverIdentity+');">'
																			+"<u style='color:#283FE6;'>"+  'Edit' +'</u>'
																			+ '</td>':'<td style="width:45px;"></td>' )))
															:'<td style="width:45px;"></td>')
													
													+'<td style="display:none;">' + nKey
													+ '</td><td style="display:none;">'
													+ resp[key].P_ID
													+ '</td><td style="display:none;">'
													+ resp[key].COVER_IDENTITY
													+ "</td><td style='width:250px;''>"
													+ resp[key].ADDON_COVERS
													+ '</td><td style="display:none;">'
													+ resp[key].POLICYTYPE
													+ '</td><td style="display:none;">'
													+ resp[key].FORM_ID
													+'</td><td style="display:none;">'
													+ resp[key].FORM_CAPTION
													+ '</td><td style="display:none;">'
													+ resp[key].NUM_FIELD
													+ '</td><td style="display:none;">'
													+ "0"
													+ '</td><td style="display:none;">'
													+ "0"
													+ "</td></tr>";
											$("#additionalcover").append(row2);
											
										});
								   
						
	}
	
 var coverId;
function getCoverIdentity(coverIdentity){
	
	coverId = coverIdentity;
	$("#coverId").val(coverId);
}

$('#eleAcc').on('shown.bs.modal', function() {
	  $('#eleAccessoryTextfiels').focus();
	})
$('#nonEleAcc').on('shown.bs.modal', function() {
	  $('#nonEleAccessoryTextfiels').focus();
	})
$('#Tppd').on('shown.bs.modal', function() {
	  $('#noOfPerson').focus();
	})
$('#accidentalCover').on('shown.bs.modal', function() {
	  $('#ACoverCheck1').focus();
	})
$('#aaiMembership').on('shown.bs.modal', function() {
	  $('#aaiName').focus();
	})


function openModel(formID,coverIdentity,nKey){
	//old_alert("FormId is:::" + formID );	


	//getAdditionalCover(nKey);
	getCoverIdentity(coverIdentity);

	var ch1= $("#addonCheck"+nKey).prop("checked");
	if(formID == "261")
	{
			if(!ch1)
			{
				//clearAccidentalData();
				$("#accidentalCover").modal("hide");
				
				
			}else{
			disableAccidentalCovers();	
			$('#accidentalCover').modal({
                backdrop: 'static',
                keyboard: false, 
                show: true
        }); 
			}
			$('#accidentalCancel').click(function () {
				disableAccidentalCovers();	
				clearAccidentalData();
		   	 	$("#addonCheck"+nKey).prop("checked", false);
		   	 	
			});
			$('#AccCovOkBtn').click(function () {
				var checkBox1 = document.getElementById("ACoverCheck1");
				 if(checkBox1.checked == false ){
					 $("#addonCheck"+nKey).prop("checked", false);
				 }
			});
		
	}
	if(formID == "266")
	{
		if(!ch1)
		{
			//clearAAI();
			$("#aaiMembership").modal("hide");
			
		}else{
			$('#aaiMembership').modal({
                backdrop: 'static',
                keyboard: false, 
                show: true
        }); 
			
		}
		$('#aaiCancel').click(function () {
			clearAAI();
			 $("#addonCheck"+nKey).prop("checked", false);
					
		});
	}
	if(formID == "265")
	{
		if(!ch1)
		{
			$("#geoExtension").modal("hide");
			$('#additionalcover tbody tr').eq($("#row_index").val()).find('td').eq(11).html("0");
		}else{
			openGeoExtension();
			$('#geoExtension').modal({
                backdrop: 'static',
                keyboard: false, 
                show: true
        }); 
		}
		$('#geoCancel').click(function () {
			$('#geoSelectedIds').val("");
			 $("#addonCheck"+nKey).prop("checked", false);
			
		});
		
	}

}

function checkCheckBox(checkBoxId) {
	console.log("checkBoxId::" +checkBoxId);
	 $("#"+checkBoxId).prop("checked", true);
}


function getAge() {
var dateString= $('#dob1').val();
	
	//old_alert("dateString::" +dateString);
	 var regDate = dateString.split("/");
	 
     var regDay = regDate[0];
		var regMonth = regDate[1];
		var regYr = regDate[2];
	var dateOfReg=  regMonth + "/" + regDay + "/" + regYr;
	
	
	 var today = new Date();
	    var birthDate = new Date(dateOfReg);
	    var age = today.getFullYear() - birthDate.getFullYear();
	    var m = today.getMonth() - birthDate.getMonth();
	    if (m < 0 || (m === 0 && today.getDate() < 

birthDate.getDate())) {
	        age--;
	    }
	   // old_alert("Age is::" + age);
	
}
function getAdditionalCover(k)
{
	 var values = new Array();
       $.each($("#check"+k+":checked").closest("td").siblings("td"),
              function () {
                   values.push($(this).text());
              });
    	var coverValue = values.join (",");
    //   old_alert("val---" + coverValue);
       console.log(coverValue);
      
       var covers = coverValue.split(",");
        var coverNo = covers[3];
		var coverValue = covers[9];
		var coverStatus = covers[10];
	//	old_alert(coverNo +" " +coverValue + " "+ coverStatus);
		$('#additionalcover tbody tr').eq($("#row_index").val()).find('td').eq(11).html("0");
}
function calcAccAge1(dobId,ageId){
	console.log("dobId-->>"+dobId+"ageId-->>"+ageId);
	var birthDate=$('#'+dobId+'').val();
	console.log("birthDate-->>"+birthDate);

	var BDate = birthDate.split("/");
	 
        var birthDay = BDate[0];
	var birthMonth = BDate[1];
	var birthYr = BDate[2];
	
	var dob = 	birthYr+""+birthMonth+""+birthDay	//'19800810';
	console.log("dob-->>"+dob);
	
	var year = Number(dob.substr(0, 4));
	var month = Number(dob.substr(4, 2)) - 1;
	var day = Number(dob.substr(6, 2));
	var today = new Date();
	var age = today.getFullYear() - year;
	if (today.getMonth() < month || (today.getMonth() == month && today.getDate() < day)) {
	  age--;
	}
	$('#'+ageId+'').val(age);
	/* old_alert(age); */
}
/*Accidental covers Starts Here */
function validAccidentalCover() {
		 var checkBox2 = document.getElementById("ACoverCheck2");
			if (!$('#name1').val()) {
				old_alert("Please Select Name!!");
				$('#name1').focus();
				 $('#ACoverCheck2').prop('checked', false);
				return false;
			}
			if (!$('#dob1').val()) { 
				old_alert("Please Select DOB!!");
				$('#dob1').focus();
				 $('#ACoverCheck2').prop('checked', false);
				return false;
			}
			if (!$('#age1').val()) {
				old_alert("Please DOB Again!!");
				$('#age2').focus();
				 $('#ACoverCheck2').prop('checked', false);
				return false;
			}
			if (!$('#age1').val()) {
				old_alert("Please Select Age!!");
				$('#age1').focus();
				 $('#ACoverCheck2').prop('checked', false);
				return false;
			}
			if ($('#gender1 :selected').text() == 'Select') {
				old_alert("Please Select Gender!!");
				$('#gender1').focus();
				 $('#ACoverCheck2').prop('checked', false);
				return false;
			}
			if ($('#relation1 :selected').text() == 'Select') {
				old_alert("Please Select Relation!!");
				$('#relation1').focus();
				 $('#ACoverCheck2').prop('checked', false);
				return false;
			}
			if ($('#dependant1 :selected').text() == 'Select') {
				old_alert("Please Select Dependent!!");
				$('#dependant1').focus();
				 $('#ACoverCheck2').prop('checked', false);
				return false;
			}
			/* else{
				 $('#ACoverCheck2').prop('checked', true);
			} */
			return true;
			
	}
	 function validAccidentalCover2() {
		 var checkBox3 = document.getElementById("ACoverCheck3");
			if (!$('#name2').val()) {
				old_alert("Please Select Name!!");
				$('#name2').focus();
				 $('#ACoverCheck3').prop('checked', false);
				return false;
			}
			else if (!$('#dob2').val()) { 
				old_alert("Please Select DOB Again!!");
				$('#dob2').focus();
				$('#ACoverCheck3').prop('checked', false);
				return false;
			}
			else if (!$('#age2').val()) {
				old_alert("Please Select Age!!");
				$('#age2').focus();
				$('#ACoverCheck3').prop('checked', false);
				return false;
			}
			else if ($('#gender2 :selected').text() == 'Select') {
				old_alert("Please Select Gender!!");
				$('#gender2').focus();
				$('#ACoverCheck3').prop('checked', false);
				return false;
			}
			else if ($('#relation2 :selected').text() == 'Select') {
				old_alert("Please Select Relation!!");
				$('#relation2').focus();
				$('#ACoverCheck3').prop('checked', false);
				return false;
			}
			else if ($('#dependant2 :selected').text() == 'Select') {
				old_alert("Please Select Dependent!!");
				$('#dependant2').focus();
				$('#ACoverCheck3').prop('checked', false);
				return false;
			}
			/* else{
				$('#ACoverCheck3').prop('checked', true);
			} */
			return true;
	}
	 function validAccidentalCover3() {
		 var checkBox4 = document.getElementById("ACoverCheck4");
			if (!$('#name3').val()) {
				old_alert("Please Select Name!!");
				$('#name3').focus();
				$('#ACoverCheck4').prop('checked', false);
				return false;
			}
			else if (!$('#dob3').val()) { 
				old_alert("Please Select DOB Again!!");
				$('#dob3').focus();
				$('#ACoverCheck4').prop('checked', false);
				return false;
			}
			else if (!$('#age3').val()) {
				old_alert("Please Select Age!!");
				$('#age3').focus();
				$('#ACoverCheck4').prop('checked', false);
				return false;
			}
			else if ($('#gender3 :selected').text() == 'Select') {
				old_alert("Please Select Gender!!");
				$('#gender3').focus();
				$('#ACoverCheck4').prop('checked', false);
				return false;
			}
			else if ($('#relation3 :selected').text() == 'Select') {
				old_alert("Please Select Relation!!");
				$('#relation3').focus();
				$('#ACoverCheck4').prop('checked', false);
				return false;
			}
			else if ($('#dependant3 :selected').text() == 'Select') {
				old_alert("Please Select Dependent!!");
				$('#dependant3').focus();
				$('#ACoverCheck4').prop('checked', false);
				return false;
			}
			/* else{
				$('#ACoverCheck4').prop('checked', true);
			} */
			return true;
	}
	 function validAccidentalCover4() {
			if (!$('#name4').val()) {
				old_alert("Please Select Name!!");
				$('#name4').focus();
				return false;
			}
			else if (!$('#dob4').val()) { 
				old_alert("Please Select DOB Again!!");
				$('#dob4').focus();
				return false;
			}
			else if (!$('#age4').val()) {
				old_alert("Please Select Age!!");
				$('#age4').focus();
				return false;
			}
			else if ($('#gender4 :selected').text() == 'Select') {
				old_alert("Please Select Gender!!");
				$('#gender4').focus();
				return false;
			}
			else if ($('#relation4 :selected').text() == 'Select') {
				old_alert("Please Select Relation!!");
				$('#relation4').focus();
				return false;
			}
			else if ($('#dependant4 :selected').text() == 'Select') {
				old_alert("Please Select Dependent!!");
				$('#dependant4').focus();
				return false;
			}else{
				return true;
			}
			return true;
	}
	 
		/* Disable Accidental cover textfields:: */
		function disableAccidentalCovers()
		{
			 var checkBox1 = document.getElementById("ACoverCheck1");
			 var checkBox2 = document.getElementById("ACoverCheck2");
			 var checkBox3 = document.getElementById("ACoverCheck3");
			 var checkBox4 = document.getElementById("ACoverCheck4");
			 if(checkBox1.checked == false)
			{
			$('#name1').attr("disabled","disabled");  
			$('#dob1').attr("disabled","disabled");  
			$('#age1').attr("disabled","disabled");  
			$('#gender1').attr("disabled","disabled");  
			$('#relation1').attr("disabled","disabled");  
			$('#dependant1').attr("disabled","disabled");  
			}
			 if(checkBox2.checked == false)
				{
			$('#name2').attr("disabled","disabled");  
			$('#dob2').attr("disabled","disabled");  
			$('#age2').attr("disabled","disabled");  
			$('#gender2').attr("disabled","disabled");  
			$('#relation2').attr("disabled","disabled");  
			$('#dependant2').attr("disabled","disabled");  
				}
			 if(checkBox3.checked == false)
				{
			$('#name3').attr("disabled","disabled");  
			$('#dob3').attr("disabled","disabled");  
			$('#age3').attr("disabled","disabled");  
			$('#gender3').attr("disabled","disabled");  
			$('#relation3').attr("disabled","disabled");  
			$('#dependant3').attr("disabled","disabled");  
				}
			 if(checkBox4.checked == false)
				{
			$('#name4').attr("disabled","disabled");  
			$('#dob4').attr("disabled","disabled");  
			$('#age4').attr("disabled","disabled");  
			$('#gender4').attr("disabled","disabled");  
			$('#relation4').attr("disabled","disabled");  
			$('#dependant4').attr("disabled","disabled");  
				}
		}
function enableAccidental1TF()
		{
			 var checkBox1 = document.getElementById("ACoverCheck1");
			 var checkBox2 = document.getElementById("ACoverCheck2");
			 var checkBox3 = document.getElementById("ACoverCheck3");
			 var checkBox4 = document.getElementById("ACoverCheck4");
			// old_alert("checkBox1.checked--->>"+checkBox1.checked);
			if(checkBox1.checked == true )
			{
				
				 $('#name1').removeAttr('disabled');
				 $('#dob1').removeAttr('disabled');
				 $('#age1').removeAttr('disabled');
				 $('#gender1').removeAttr('disabled');
				 $('#relation1').removeAttr('disabled');
				 $('#dependant1').removeAttr('disabled');
				 
				 $('#name1').removeClass("disabledbutton"); 
				 $('#dob1').removeClass("disabledbutton"); 
				 $('#age1').removeClass("disabledbutton"); 
				 $('#gender1').removeClass("disabledbutton"); 
				 $('#relation1').removeClass("disabledbutton"); 
				 $('#dependant1').removeClass("disabledbutton"); 
			}if(checkBox1.checked == false )
			{
				clearAccRow1();
				$('#ACoverCheck2').prop('disabled', true);
				$('#ACoverCheck3').prop('disabled', true);
				$('#ACoverCheck4').prop('disabled', true);
			}
			/* else{
				clearAccRow1();
			} */
			if( checkBox2.checked == true && validAccidentalCover()){
				$('#ACoverCheck2').prop('checked', true);
				 $('#name2').removeAttr('disabled');
				 $('#dob2').removeAttr('disabled');
				 $('#age2').removeAttr('disabled');
				 $('#gender2').removeAttr('disabled');
				 $('#relation2').removeAttr('disabled');
				 $('#dependant2').removeAttr('disabled');
				 
				 $('#name2').removeClass("disabledbutton"); 
				 $('#dob2').removeClass("disabledbutton"); 
				 $('#age2').removeClass("disabledbutton"); 
				 $('#gender2').removeClass("disabledbutton"); 
				 $('#relation2').removeClass("disabledbutton"); 
				 $('#dependant2').removeClass("disabledbutton"); 
			}if(checkBox2.checked == false )
			{
				clearAccRow2();
				$('#ACoverCheck3').prop('disabled', true);
				$('#ACoverCheck4').prop('disabled', true);
			}
			/* else{
				clearAccRow2();
			} */
			 if(checkBox3.checked == true && validAccidentalCover2()){
				 $('#ACoverCheck3').prop('checked', true);
				 $('#name3').removeAttr('disabled');			
				 $('#dob3').removeAttr('disabled');
				 $('#age3').removeAttr('disabled');
				 $('#gender3').removeAttr('disabled');
				 $('#relation3').removeAttr('disabled');
				 $('#dependant3').removeAttr('disabled');
				 
				 $('#name3').removeClass("disabledbutton"); 
				 $('#dob3').removeClass("disabledbutton"); 
				 $('#age3').removeClass("disabledbutton"); 
				 $('#gender3').removeClass("disabledbutton"); 
				 $('#relation3').removeClass("disabledbutton"); 
				 $('#dependant3').removeClass("disabledbutton"); 
			 }if(checkBox3.checked == false )
				{
					clearAccRow3();
					$('#ACoverCheck4').prop('disabled', true);
				}
			/*  else{
				clearAccRow3();
			} */
			if(checkBox4.checked == true  && validAccidentalCover3()){
				$('#ACoverCheck4').prop('checked', true);
				 $('#name4').removeAttr('disabled');
				 $('#dob4').removeAttr('disabled');
				 $('#age4').removeAttr('disabled');
				 $('#gender4').removeAttr('disabled');
				 $('#relation4').removeAttr('disabled');
				 $('#dependant4').removeAttr('disabled');
				 
				 $('#name4').removeClass("disabledbutton"); 
				 $('#dob4').removeClass("disabledbutton"); 
				 $('#age4').removeClass("disabledbutton"); 
				 $('#gender4').removeClass("disabledbutton"); 
				 $('#relation4').removeClass("disabledbutton"); 
				 $('#dependant4').removeClass("disabledbutton"); 
			}if(checkBox4.checked == false )
			{
				clearAccRow4();
			}
			/* else{
					clearAccRow1();
					disableAccidentalCovers();
			}  */
		}
	/* 	 ==================================================================== */
	/*Accidental covers ends Here */
	function validateAAI()
	{
		    
		if (!$('#aaiName').val()) {
			old_alert("Please Enter AAI Membership Name");
			$('#aaiName').focus();
			$('#aaiOkBtn').attr("data-dismiss",""); 
			return false;
		}
		if (!$('#aaiMembershipNo').val()) {
			old_alert("Please Enter AAI Membership Number");
			$('#aaiMembershipNo').focus();
			$('#aaiOkBtn').attr("data-dismiss",""); 
			return false;
		}
		if (!$('#aaiCode').val()) {
			old_alert("Please Enter AAI Membership Code");
			$('#aaiCode').focus();
			$('#aaiOkBtn').attr("data-dismiss",""); 
			return false;
		}
		if (!$('#aaiAssociationName').val()) {
			old_alert("Please Enter AAI Membership Association Name");
			$('#aaiAssociationName').focus();
			$('#aaiOkBtn').attr("data-dismiss",""); 
			return false;
		}
		if (!$('#aaiExpiryDate').val()) {
			old_alert("Please Enter AAI Membership Expiry Date");
			$('#aaiExpiryDate').focus();
			$('#aaiOkBtn').attr("data-dismiss",""); 
			return false;
		}
		$('#aaiOkBtn').attr("data-dismiss","modal"); 
		return true;
	}
	
	function clearAAI(){
	    
		$("#aaiName").val("");
		$("#aaiMembershipNo").val("");
		$("#aaiCode").val("");
		$("#aaiAssociationName").val("");
		$("#aaiExpiryDate").val("");
	}
	
	function selectGeoCountry(){
	    var additionalcoverArr = [];
		var k=0;	
		var id="";
		
		$("table#geoExtensionCover tr").each(function() { 
			var additionalcoverArrayThisRow = [];
			var tableData = $(this).find('td');
			 if (tableData.length > 0) {
				 tableData.each(function() { additionalcoverArrayThisRow.push($(this).text()); });
				 additionalcoverArr.push(additionalcoverArrayThisRow);
			}
			 
			 var a=additionalcoverArrayThisRow[0];
			 var b=additionalcoverArrayThisRow[1];
			 var c=additionalcoverArrayThisRow[2];
			// console.log("a b  c----------->>"+a+" "+b+" "+c);
			// console.log("k value----------->>"+k +"val of b----------->>"+b);
			 if(a!=undefined && b!=undefined && c!=undefined){
				 if($("#geoCheck"+b).prop("checked")){
						if(id==""){
							id=b;
							//console.log("id--111111111111111--------->>"+id)
						}else{
							id=id+"~"+b;
							//console.log("id----2222222222222------->>"+id)
						}
						$('#geoSelectedIds').val("");
					}
			 k++;
			}
			
		});
		$('#geoSelectedIds').val(id); 
		console.log("final id----------->>"+id) 
	}

		function openGeoExtension()
		{		
				var seletedId=$('#geoSelectedIds').val(id);
				var id=seletedId.split("~");
				var sr=1 ;
			$.ajax({
						url : "${pageContext.request.contextPath}/getRecordLst?sqlMstId=" + "70" + "&param=" + "28",
						type : 'post',
						dataType : 'json',
						async : true,
						success : function(resp) {
							$('#geoExtensionCover td').remove();

							//$('#geoExtensionCover').empty();
							$.each(resp, function (key, value) {
							//console.log("resp = " + resp);
							var recId=resp[key].DET_ID ;	
							var chk=false,row;
							for(i=0;i<id.length;i++){
								//console.log("recId------->>"+recId+"id[i]=======>>"+id[i]); 
								if(recId==id[i]){
									chk=true;
								}
							
							if(chk==true){
								row = '<input type="checkbox" class="countryCheck" name="checkbox" id="geoCheck'+recId+'" onclick="selectGeoCountry();" checked>';
							}else{
								row = '<input type="checkbox" class="countryCheck" name="checkbox" id="geoCheck'+recId+'" onclick="selectGeoCountry();">';
							}

							}
							var row2="<tr><td>" + row + "</td>" +
							"<td style='display:none;'>" + resp[key].DET_ID + "</td>" +
							"<td>" + resp[key].DET_NAME + "</td>" +
							"</tr>";
					// old_alert(row2);
					sr++;
					
					$("#geoExtensionCover").append(row2);
				
					
					
					});
					if(!$.fn.DataTable.isDataTable('#geoExtensionCover')){
								$('#geoExtensionCover').DataTable({
									order: [[1, 'asc']],
									   columnDefs: [{
									      targets: "_all",
									      orderable: false
									   }],
								 	/* 'destroy': true,
							   		    "lengthMenu": [5, 10, 20, 50],
								        "pageLength": 5,
								      //  "bInfo" : false
									 */
								});
							}
					},
					error:function(data) {
						hideWait();
					
					},
					
					}); 
			
			}
	
	function getVehicleNo() {
		var relRtoCode = $('#RTOSCity option:selected').attr('relRtoCode');
		var vehicle = relRtoCode.split("-");
		var vehRegNO1 = vehicle[0];
		var vehRegNO2 = vehicle[1];

		$("#vehRegNo1").val(vehRegNO1);
		$("#vehRegNo2").val(vehRegNO2);
		$("#prevVehId1").val(vehRegNO1);
		$("#prevVehId2").val(vehRegNO2);
		if(($("#prevVehId1").val()) || ($("#prevVehId2").val()) || ($("#prevVehId3").val()) || ($("#prevVehId4").val()))
		{
			$("#vehRegNo1").val($("#prevVehId1").val());
			$("#vehRegNo2").val($("#prevVehId2").val());
			$("#vehRegNo3").val($("#prevVehId3").val());
			$("#vehRegNo4").val($("#prevVehId4").val());
		}
	}
	
	function validateManfYr(id)
	{
		//old_alert(id);
		var manfYr =  parseInt(document.getElementById(id).value);
		var vehManfYr =  parseInt($("#year option:selected").text());
		var date = new Date();
		var year =  parseInt(date.getFullYear());
		console.log("vehManfYr-->>"+vehManfYr+" manfYr==>"+manfYr+" year==>>"+year);
	
		if((manfYr >= vehManfYr || manfYr == vehManfYr) && (manfYr == year || manfYr <= year)){
			// $('#elePriceTextfiels').focus();
			return true
		}else{
			old_alert("Please enter valid manufacture year");
			document.getElementById(id).value="";
			setTimeout(function(){$('#'+id).focus();},1);
			return false;
		}
	}


	function getPolicyTerm() {
		var vehicleid = $("#vehicle").val();
		$
				.ajax({
					url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=find_policy_term&table_name=AMCP&str_company_type=&str_gic=&str_gicbid=&str_prod=&str_discnm=&str_rgrp=&str_state=&str_city="
							+ "&str_prpsl=&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype=&str_veh="
							+ vehicleid
							+ "&str_mod=&str_var=&str_policy_age=&str_hbbid=&str_user_level=&str_user_id=&str_login_type=&str_gap=&str_ageto=",
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(resp) {
						$("#policyTerm").empty();
						$("#vehicle").append('');
						$.each(resp, function(key, value) {
						//	old_alert(value);
							nKey = parseInt(key) + 1;
							$("#policyTerm").append(resp[key].MODEL_NAME + '');

						});
					},
				});
	}

	$(function() {
		$('.main').on('scroll', function() {
			$('.leftscroll').scrollTop($(this).scrollTop());
			$('#topmenu').scrollLeft($(this).scrollLeft());
		});

		$('.leftscroll').scroll(function() {
			$('.main').scrollTop($(this).scrollTop());

		});
	});

	// function getValuesFromProduct(){ 
//	var reltype = $('#productname option:selected').attr('reltype');
//	var reltype1 = $('#productname option:selected').attr('reltype1');
//	var relproductcode = $('#productname option:selected').attr('relproductcode');
	
//	$("#reltype").val(reltype);
//	$("#reltype1").val(reltype1);
//$("#relproductcode").val(relproductcode);
//	
	
//	old_alert("reltype::::"+reltype+"reltype1::::::"+reltype1+"relproductcode:::"+relproductcode);
	//}  

	
/* function getDataTableData(){
	 	var table = $('#tppdRestrict').DataTable();
        var ids = $.map(table.rows('.selected').data(), function (item) {
        old_alert(item);
            return item[0]
        });
        console.log(ids)
        old_alert(table.rows('.selected').data().length + ' row(s) selected');
   
} */
function saveVoluntaryData()
{
	var table = $('#voluntary').DataTable();
    var ids = $.map(table.rows('.selected').data(), function (item) {
   // old_alert(item);
        return item[0]
    });
    console.log(ids)
   // old_alert(table.rows('.selected').data().length + ' row(s) selected');
}
	
function saveAccidentalData()
{
	var checkBox1 = document.getElementById("ACoverCheck1");
	var checkBox2 = document.getElementById("ACoverCheck2");
	var checkBox3 = document.getElementById("ACoverCheck3");
	var checkBox4 = document.getElementById("ACoverCheck4");
	//old_alert(checkBox1.checked+" "+checkBox2.checked+" "+checkBox3.checked+" "+checkBox4.checked);
	var s1,s2,s3,s4;
	if(checkBox1.checked == true){
		s1=validAccidentalCover();
		/* if(checkBox2.checked == false){
			old_alert("in condition==>>"+checkBox2.checked);
			$('#ACoverCheck2').prop('checked', false);
		} */
	}
	 if(checkBox2.checked == true){
		 s2=validAccidentalCover2();
		/* if(checkBox3.checked == false){
			$('#ACoverCheck3').prop('checked', false);
		} */
	}
	 if(checkBox3.checked == true){
		 s3=validAccidentalCover3();
		/* if(checkBox4.checked == false){
			$('#ACoverCheck4').prop('checked', false);
		} */
	}
	 if(checkBox4.checked == true){
		 s4=validAccidentalCover4();
	}
	 if(s1==false||s2==false||s3==false||s4==false){
		 $('#AccCovOkBtn').attr("data-dismiss",""); 
	 }else{
		 $('#AccCovOkBtn').attr("data-dismiss","modal"); 
	 }
}

/* ---------------------------------------------------------------------------------------- */
 function clearAccRow1(){
	 $('#ACoverCheck1').prop('disabled', false);
	 $('#ACoverCheck1').prop('checked', false);
	 disableFields("#name1,#dob1,#age1,#gender1,#relation1,#dependant1");
	 clearFields("#name1,#age1");	 
	 $('#dob1').val("");
	 $('#gender1').val("Select");
	 $('#relation1').val("Select");
	 $('#dependant1').val("Select");
}
function clearAccRow2(){
	/* $('#ACoverCheck2').prop('disabled', true); */
	 $('#ACoverCheck2').prop('checked', false);
	 disableFields("#name2,#dob2,#age2,#gender2,#relation2,#dependant2");
	 clearFields("#name2,#age2");
	 $('#dob2').val("");
	 $('#gender2').val("Select");
	 $('#relation2').val("Select");
	 $('#dependant2').val("Select");
}
function clearAccRow3(){
	/* $('#ACoverCheck3').prop('disabled', true); */
	 $('#ACoverCheck3').prop('checked', false);
	 disableFields("#name3,#dob3,#age3,#gender3,#relation3,#dependant3");
	 clearFields("#name3,#age3");
	 $('#dob3').val("");
	 $('#gender3').val("Select");
	 $('#relation3').val("Select");
	 $('#dependant3').val("Select");
}
function clearAccRow4(){
	/* $('#ACoverCheck4').prop('disabled', true); */
	 $('#ACoverCheck4').prop('checked', false);
	 disableFields("#name4,#dob4,#age4,#gender4,#relation4,#dependant4");
	 clearFields("#name4,#age4,#gender4,#relation4,#dependant4");	 
	 $('#dob4').val("");
	 $('#gender4').val("Select");
	 $('#relation4').val("Select");
	 $('#dependant4').val("Select");
}

 
/* ---------------------------------------------------------------------------------------- */

function clearAccidentalData()
{
	clearAccRow1();
	clearAccRow2();
	clearAccRow3();
	clearAccRow4();
	$('#ACoverCheck2').prop('disabled', true);
	$('#ACoverCheck3').prop('disabled', true);
	$('#ACoverCheck4').prop('disabled', true);
	 
}
	
function openPolicyType(id){
//	old_alert(id);
	if(id == "package"){
	$("#policyTypeId").val("1");
	}
	else if(id == "liability")
	{
		$("#policyTypeId").val("2");
	}
	proposal();
}

function proposal() {

	var mainDiv="";
	$("#policyTypeDiv").hide();
	$("#proposalTypeDiv").show();
	$('#proposalTypetable td').remove();
	var resp = getInsFindFormData("PKG_INS_FIND","find_proposal_type","AMCP","","","","","","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	var proposalType= "";
	$.each(resp, function(key, value) {
		nKey = parseInt(key) + 1;
		var key1= parseInt(key) + 1;
		proposalType=  resp[key].PROPOSAL_TYPE;
		/* mainDiv = '<div ><a href=javascript:openPolicyTypeModal(+"policyTypeIns"+)> Go Back' +"</a></div>" */
		if(proposalType == "NEW"){
		mainDiv =mainDiv + '<tr><td><img src="images/new1.png" style="width:300px;height: 100px;margin-left: 2px;" id='+resp[key].PID+' onclick="openProductDetails(this.id,\''+proposalType+'\');"></td></tr>';
		}
		else if(proposalType=="USED"){
			mainDiv = mainDiv + '<tr><td><img src="images/used1.png" style="width:300px;height: 100px;margin-left: 2px;" id='+resp[key].PID+' onclick="openProductDetails(this.id,\''+proposalType+'\');"></td></tr>';
		}
		console.log(resp);
	});
	console.log(proposalType);
	$("#proposalTypetable").append(mainDiv);
/* 	$("#contentBodyMotor").html(mainDiv);
	$("#showmodelMotor").modal('show');
	$("#logModelMotor").css({
		//'width' : '350px',
		'height' : 'auto',
		'margin-left' : '120px',
		'margin-top' : '100px',
		'position':'relative'
	});
	$("#logHeaderMotor").hide(); */
}

function openProductDetails(id,proposalType){
	//old_alert("4444444444444" + proposalType);
	var proposalTypeId = id;
	/* if(id==1){ */
		//old_alert("55555555555555");
	$("#proposal").val(proposalTypeId);
	$("#proposalTf").val(proposalType);
	//old_alert("proposal type:::>>>"+ $("#proposal").val());
	product();
 
}

function product() {
	//$("#productContainer").remove();
	$("#policyTypeDiv").hide();
	$("#proposalTypeDiv").hide();
	$("#productTypeDiv").show();
	$("#openProductsBtnDiv").hide();
	
	var mainDiv="";
	$('#productTypeTable td').remove();
	var resp = getInsFindFormData("PKG_INS_FIND","find_product","AMCP","","","","","","","","","","","","","","","","","","","","","","","","0","HB EMPLOYEE","","");
	var key2=0;
	var keySize =0;
	
	for(var key = 0;key<resp.length;key++){
		console.log("dadada"+key);
		var tr = "<tr>";
		var productName = resp[key].PRODUCTNAME;

		var td1 = '<td id='+resp[key].PRODUCTID+'  style=\"font-weight: bold;font-size: 12px;border: 1px solid #286090;background-color: white;text-align: left; padding: 8px; width:300px; height:50px\" onclick="getProductName(this.id,\''+productName+'\');"><font class="fontColor pointer">'+resp[key].PRODUCTNAME +"</font></td>";

		//var td1 = '<td id='+resp[key].PRODUCTID+' style=\"font-weight: bold;font-size: 12px;border: 1px solid #286090;background-color: #F08000;text-align: left; padding: 8px; width:300px; height:50px\" onclick="getProductName(this.id,\''+productName+'\');"><font color="white" class="pointer">'+resp[key].PRODUCTNAME +"</font></td>";

		key = parseInt(key) + 1; 
		var td2 = "";
		if(key<resp.length){
			productName = resp[key].PRODUCTNAME;

			td2 = '<td id='+resp[key].PRODUCTID +'  style=\"font-weight: bold;font-size: 12px; border: 1px solid #286090;background-color: #white;text-align: left;padding: 8px; width:300px ; height:50px\" onclick="getProductName(this.id,\''+productName+'\');"><font class="fontColor pointer">'+resp[key].PRODUCTNAME+"<font></td>";

			//td2 = '<td id='+resp[key].PRODUCTID +' style=\"font-weight: bold;font-size: 12px; border: 1px solid #286090;background-color: #F08000;text-align: left;padding: 8px; width:300px ; height:50px\" onclick="getProductName(this.id,\''+productName+'\');"><font color="white" class="pointer">'+resp[key].PRODUCTNAME+"<font></td>";

		}
		tr = tr + td1 + td2 + "</tr>";
		
		mainDiv = mainDiv + tr;
	}
	$("#productTypeTable").append(mainDiv);
	
/* 	$("#contentBodyMotor").html(mainDiv);
	$("#showmodelMotor").modal('show');
	$("#logModelMotor").css({
		'width' : '630px',
		'height' : 'auto',
		'margin-left' : '10px',
		'margin-top' : '100px',
		'position':'relative'
	});
	$("#logHeaderMotor").hide(); */
	
	//console.log(resp);
}

function getProductName(id, productName) {
	//old_alert("1111111111111111111" + productName);
	console.log("Product Id:::" +id);
	//old_alert("Product Id:::" +id);
	var productId = id;
	console.log("proposal Id:::"+ $("#proposal").val());
	var proposalType =  $("#proposal").val();
	$("#productname").val(productId);
	$("#productnameTf").val(productName);
	
		/* $("#showmodel").modal('hide');
		$("#wrapperMain").show();
		$("#contentBodyMotor").hide();
		$("#showmodelMotor").modal('hide'); */
		$("#productDetails").show();
		$("#openProductsBtnDiv").show();
		$("#productTypeDiv").hide();
		$("#showCompanyBtn").prop('disabled', false);
		
		
		year();
		 var policyType = $("#policyTypeId").val();
		 if(policyType == "1")
		 {
			//old_alert("in show ::>> "+policyType);
			 $(".liability").show();
			 
		 }else if(policyType == "2")
		 {
			// old_alert("in hide ::>> "+policyType);
			 $(".liability").hide();
			 $(".formatDiv").css({
					'width':'1170px'});
		}
			
		console.log("you are in ");
	/* }  */
}
	
	/*  function pkgCovers() {
		var applicantid = $("#applicantname").val();
		var productnameid = $("#productname").val();
		var proposalid = $("#proposal").val();
		var RTOStateid = $("#RTOState").val();
		var RTOSCityid = $("#RTOSCity").val();
		var Zoneid = $("#Zone").val();
		var vehicleid = $("#vehicle").val();
		var Modelid = $("#Model").val();
		$
				.ajax({
					url : "${pageContext.request.contextPath}/getInsFindFormData?pkg_name=PKG_INS_FIND&proc_name=FIND_COVERS&table_name=IMCP&str_company_type=&str_gic=&str_gicbid=&str_prod=1&str_discnm=&str_rgrp=&str_state=&str_city=553&str_prpsl=1&str_spnm=&str_mgnm=&str_productcode=&str_type=&str_type_1=&str_kg_from=&str_kg_to=&str_fueltype=1&str_fuelkit=4&str_veh=&str_mod=&str_var=67&str_policy_age=&str_hbbid=&str_user_level=&str_user_id=&str_login_type=&str_gap=&str_ageto=&str_poltype=1&str_calctype=",
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(resp) {
						$("#cc").empty();
						$("#cc").append('<option value="">--Select--');
						$.each(resp, function(key, value) {
							nKey = parseInt(key) + 1;
							$("#fuelKit").append(
									'<option value="'+resp[key].VAR_ID+'"> '
											+ resp[key].VAR_NAME + '');
						});
					},
				});

	} 
	
	 */
	/* 
	 function isNumber(evt) {
	     evt = (evt) ? evt : window.event;
	     var charCode = (evt.which) ? evt.which : evt.keyCode;
	     if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	    //      old_alert("Please enter only Numbers."); 
	         return false;
	     }

	     return true;
	 }
	

	$(function() {
		$(".wrapper1").scroll(function() {
			$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
		});
		$(".wrapper2").scroll(function() {
			$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
		});
	});*/
	function gotoPolicyType(){
		
		$("#policyTypeDiv").show();
		$("#proposalTypeDiv").hide();
		$("#productDetails").hide();
		$("#openProductsBtnDiv").hide();
		}
	function gotoPropsalType(){
		$("#proposalTypeDiv").show();
		$("#productTypeDiv").hide();
		$("#productDetails").hide();
		$("#openProductsBtnDiv").hide();
		}
	function openProducts(){
		
		$("#productTypeDiv").show();
		$("#showCompanyDiv").show();
		$("#productDetails").hide();
		$("#openProductsBtnDiv").hide();
		clearProductDetails();
		clearPreviousDetails();
		clearAllFields();
		setTimeout(function(){$('#year').select2('focus');},1);
		//populateApplicantName();
		/* nomDtlInitial();
		nomDtlRelation(); */
		/* insDtlInitial();
		inspectionState();
		inspectionAgency();
		insDtlCACountry(); */
		}

	function clearProductDetails(){
		 $('#year,#month,#RTOState,#RTOSCity,#vehicle,#Model,#varience,#fuelType,#fuelKit,#customer').select2('data', {
			 allowClear : true,
			 id: null,
			 text: '--Select--'
		 });
		 $('#dateofReg').val("");
		 $('#passengers,#Zone,#hp,#wheels,#cc,#kg,#exshowroomprice,#mobileNo,#emailId,#fuelType').val("");
	}
</script>

</head>
<body>
 <div class="container"> 
 <input type="hidden" id="proposal">
 <input type="hidden" id="proposalTf">
<input type="hidden" id="productname">
<input type="hidden" id="productnameTf">
<input type="hidden" id="policyTypeId">
<input type="hidden" id="relIdv">
<input type="hidden" id="row_indexCmpTbl">
<input type="hidden" id="coverId">
<input type="hidden" id="comapanyDtlRow">
<input type="hidden" id="RelianceExtraCovers">
<input type="hidden" id="HdfcExtraCovers">
 	
	<div class="row" id="policyTypeDiv">
	<center>
	<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" ></div>
			<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" >
				<div style="width: 300px;"><a href=""  class="fontColor pointer"> Click here to Go Back </a></div><br>
				<table>
				<tr>
				<td>
					<img src="images/package1.png" style="width:300px;height: 100px;margin-left: 2px;" id="package" onclick="openPolicyType(this.id);">
				</td>
				</tr>
				
				<tr>
				<td>
					<img src="images/liability1.png" style="width:300px;height: 100px;margin-left: 2px;" id="liability" onclick="openPolicyType(this.id);"> 
				</td>
				</tr>
				</table>
			
	<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" ></div>
	</div>
	</center>
	</div>
	
	<div class="row" id="proposalTypeDiv" style="display:none">
	<center>
	<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" ></div>
	<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" >
	<div style="width: 300px;"><a onclick="gotoPolicyType();"  class="fontColor pointer">Click here to Go Back</a></div><br>
	<table id="proposalTypetable">
	
	</table>
	</div>
	 <div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" ></div>
	 </center>
	</div>
 	
 	
 	 
 	<div class="row" id="productTypeDiv" style="display:none">
 	 <div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" style="width: 250px;"></div>
 	  <div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" >
	<div style="width: 650px;"><center><a onclick="gotoPropsalType();"  class="fontColor pointer"> Click here to Go Back</a></center></div><br>
	<table  id="productTypeTable" style="width: 650px;box-shadow: 0 5px 15px rgba(0, 0, 0, .5); background-color: white">
	
	</table>
	</div>
	 <div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" ></div>
	</div>
 	
 <!-- ---------------Start------code for shreeramPayment -------------------------- -->
	<div class="modal fade modalClose"  role="dialog" id="shreeRamPay" >
   <div  class="modal-dialog">
      <div class="modal-content" style="width: 650px;height: 800px">
         <div class="modal-header">

            <h4>Policy Approve</h4>
         </div>
         <div class="modal-body">
            <div style="background-color: #ff7d33; width: 600px;height: 680px">
               <br />
               <div><label for="proposalNo" style="margin-left:5px"; class="fontColor"> *Proposal Number</label> <input id="shrProposalNo" size="40x" type="text" style="margin-left:50px";/></div>
               <br />
               <div><label for="transactionNo" style="margin-left:5px"; class="fontColor"> *Transaction Number</label> <input id="shrTransactionNo" size="40x" type="text" style="margin-left:30px";/></div>
               <br />
               <div><label for="empCardNo" style="margin-left:5px"; class="fontColor"> Employee Card Number</label> <input id="shrEmpCardNo" size="40x" type="text" style="margin-left:15px";/></div>
               <br />
               <div><label for="cardHolderName" style="margin-left:5px"; class="fontColor"> Card Holder Name</label> <input  id="shrCardHolderName" size="40x" type="text" style="margin-left:53px";/></div>
               <br />
               <div><label for="cardType" style="margin-left:5px"; class="fontColor"> Card Type</label> <input id="shrCardType"  size="40x" type="text" style="margin-left:110px";/></div>
               <br />
               <div><label for="cardValidUpto" style="margin-left:5px"; class="fontColor"> Card Valid upto</label> <input   id="shrCardValidUpto" size="40x" type="text" style="margin-left:72px";/></div>
               <br />
               <div><label for="bankName" style="margin-left:5px"; class="fontColor"> Bank Name</label> <input   id="shrBankName" size="40x" type="text" style="margin-left:100px";/></div>
               <br />
               <div><label for="branchName" style="margin-left:5px"; class="fontColor"> Branch Name</label> <input id="shrBranchName" size="40x" type="text" style="margin-left:85px";/></div>
               <br />
               <div><label for="paymentType" style="margin-left:5px"; class="fontColor"> payment Type</label> <input id="shrPaymentType"  size="40x" type="text" style="margin-left:80px";/></div>
               <br />
               <div><label for="transactionDate" style="margin-left:5px"; class="fontColor"> Transaction date</label> <input class="datepicker" id="shrTransactionDate"  type="text" placeholder="Select Date" style="width: 310px;margin-left:65px";/></div>
               <br />
               <div><label for="chequeType" style="margin-left:5px"; class="fontColor"> Cheque Type</label> <input  id="shrChequeType" size="40x" type="text" style="margin-left:90px";/></div>
               <br />
               <div><label for="chequeClear" style="margin-left:5px"; class="fontColor"> Cheque Clear</label> <input  id="shrChequeClear" size="40x" type="text" style="margin-left:87px";/></div>
               <br />
               <div><label for="cashType" style="margin-left:5px"; class="fontColor"> Cash Type</label> <input id="shrCashType"  size="40x" type="text" style="margin-left:107px";/></div>
               <br />
               <div>
                  <div style="margin-left:5px";><input name="Payment" type="Button" value="Payment" data-dismiss="modal" onclick="getPolicyApproveForShreeRam();"/> <input name="Cancel" type="Button" value="Cancel" data-dismiss="modal" /></div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>


<!-- ---------------Start------PreviewCompany -------------------------- -->
	<div class="modal fade modalClose" id="previewCompany" role="dialog" >
   <div class="modal-dialog" style="position:fixed;left: 17%;">
      <div class="modal-content" style="width: 950px;height: 480px">
         <div class="modal-body">
            <div style="background-color: white; width: 920px;height: 450px">
           <button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="background-color: #d1bebe">
						Motor Calculation Details</h4>
						
						<div id="prevCompDiv">
		
				
			<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" >
				<br>
				<div class="main wrapper2" style="margin: auto;height:320px;width:450px;overflow:hidden;">
					<div class="div2 div4" >
						<table id="previewCompOD" class="table" 
							style="margin: 10px;width:400px;display:block;border-style: hidden;" >
							<thead style="display: inline-block;width:400px;">
							<tr style="background-color: #dac8b6;">
										<th>S/N</th>
										<th>Own Damage</th> 
										<th>Rate</th>
										<th>Val</th>
										<th>Amount</th>
						    </tr>
						    </thead>
						    <tbody style="display: inline-block;overflow-y:scroll;width:100%;"></tbody>
						</table>
					</div>
				</div>

			</div>
			
			<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" style="height:320px;width:400px;margin-left:130px;">
				<br>

				<div class="main wrapper2" style="margin: auto;height:320px;width:450px;overflow:hidden;">
					<div class="div2 div4">
						<table id="previewCompTP" class="table" 
							style="margin: 5px;width:400px;display:block;border-style: hidden;">		<!-- //style="padding-right:550px;" -->
							<thead style="display:table;width:400px;">
							<tr style="background-color: #dac8b6;">
										<th>S/N</th>
										<th>Liability</th> 
										<th>Rate</th>
										<th>Val</th>
										<th>Amount</th>
						    </tr>
						    </thead>
						    <tbody style="display: inline-block;overflow-y:scroll;width:100%;"></tbody>
						</table>
					</div>
				</div>
			</div>
			
		<!-- </div> -->
	</div>
		<!-- 	------------for input text------------------------------- -->
		
		<div class="row" style="padding: 5px;margin-top:350px;">
      <div class="col-md-2" style="width: 120px;margin-right: -25px;">
      <label for="netPremium"><font class="fontColor" style="width: 50px";>Net Premium</font></label>
       <input id="prevNetPremium" type="text" style="width: 90px";>
      </div>
      
       <div class="col-md-2" style="width: 80px;margin-right: -15px;">
      <label for="cGst"><font class="fontColor" style="width: 50px";>CGst</font></label>
       <input id="prevCGst" type="text" style="width: 60px";>
      </div>
      
       <div class="col-md-2" style="width: 80px;margin-right: -15px;">
      <label for="sGst"><font class="fontColor" style="width: 50px";>SGst</font></label>
       <input id="prevSGst" type="text" size="10" style="width: 60px";>	</input>	
      </div>
      
       <div class="col-md-2" style="width: 80px;margin-right: -15px;">
      <label for="uGst"><font class="fontColor" style="width: 50px";>UGst</font></label>
       <input id="prevUGst" type="text" size="10" style="width: 60px";>	
      </div>
      
       <div class="col-md-2" style="width: 80px;margin-right: -15px;">
      <label for="iGst"><font class="fontColor" style="width: 50px";>IGst</font></label>
       <input id="prevIGst" type="text" size="10" style="width: 60px";>
      </div>
      
       <div class="col-md-2" style="width: 100px;margin-right: -15px;">
      <label for="tax"><font class="fontColor" style="width: 80px";>Tax</font></label>
       <input id="prevTax" type="text" size="10" style="width: 80px";>
      </div>
      
        <div class="col-md-2" style="width: 140px;margin-right: -25px;">
      <label for="totPremi"><font class="fontColor" style="width: 50px";>Total Premium</font></label>
       <input id="prevTotalPremium" type="text" size="10" style="width: 90px";>
      </div>
      
        <div class="col-md-2" style="width: 150px;margin-right: -25px;">
      <label for="serviceCharge"><font class="fontColor" style="width: 50px";>Service Charge</font></label>
       <input id="prevServiceCharge" type="text" size="10" style="width: 100px";>
      </div>
      
        <div class="col-md-2" style="width: 150px;margin-right: -25px;">
      <label for="totalPay"><font class="fontColor" style="width: 50px";>Total Payment</font></label>
       <input id="prevTotalPayment" type="text" size="10" style="width: 100px";>
      </div>
   </div> 
		<!-- 	------------for input text------------------------------- -->
			
					
            </div>
         </div>
      </div>
   </div>
</div>
 
<div class="modal fade modalClose" id="eleAcc" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width:630px">
				<div class="modal-header">
				
					<h4 class="modal-title" style="background-color : #337ab7;color : white;">
						Electrical Accessory</h4>
				</div>
				<div class="modal-body">
					<div style="background-color: white; width: 600px; border-color: #337ab7;border-weight: 1px;border-style:solid;">
					<br>
						<div class="gap" style="height: 10px; width: 475px"></div>
						<div class="row">
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor"
									style="margin-left: 10px">Accessory</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="eleAccessoryTextfiels"
									tabIndex="2001" type="text" style="margin-left: 10px"/>
							</div>
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor">Make</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="eleMakeTextfiels" type="text" tabIndex="2002"/>
							</div>
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor">Model</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="eleModelTextfiels" type="text" tabIndex="2003"/>
							</div>
						</div>
						<div class="row" style="padding: 5px">
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor"
									style="margin-left: 10px">Manf.Year</font></label> &nbsp;&nbsp;&nbsp; <input maxlength="4"
									id="eleYearTextfiels" tabIndex="2004" type="text" style="margin-left: 10px"  onchange="validateManfYr(this.id);"
									 class="numberOnly"/>
							</div>
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor">Price</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="elePriceTextfiels" type="text" class="numberOnlyWithDecimal" tabIndex="2005" />
							</div>
							<div class=" col-md-4">
							<br>
								<input type="Button" name="ADD" value="ADD" class="btn btn-primary" onclick="addEleAccTotable();" style="width:80px" tabIndex="2006">
							</div>
						</div>
					
						<div class="row" style="padding: 5px">
							<table id="eleAccesory" class="table" border="1"
								style="width: 590px; margin-left: 14px">
								<tr style="background-color: #dac8b6;">
								
									<th>Accessory</th>
									<th>Make</th>
									<th>Model</th>
									<th>Manf.Year</th>
									<th>Price</th>
									<th>Remove</th>
								</tr>
							</table>
						</div>
						<div class="row" style="padding: 5px">
							<div class=" col-md-6"></div>
							<div class=" col-md-6"style="float: right;margin-right: -90px;/*! margin-top: -1px; */margin-bottom: -40px;">
							<label for="Total:"><font class="fontColor">Total:</font></label>
								<input id="eleTotalTextfiels" style="width: inherit" type="text" readonly/>
							</div>
						</div>
						<div class="row" style="padding: 5px">
							<div class=" col-md-4"></div>
							<div class=" col-md-1" style="width:15%">
								<input class="btn btn-primary"  type="Button" name="OK" value="OK" id="eleOk" onclick="saveEleData();" style="width:80px" tabIndex="2007">
							</div>
							&nbsp; <input type="Button" class="btn btn-primary" name="Cancel" onclick="clearEleData()"
								value="Cancel" data-dismiss="modal" style="width:80px" tabIndex="2008"/>
								
						</div>

					</div>
				</div>


			</div>
			
		</div>
	</div>

	

<div class="modal fade modalClose" id="nonEleAcc" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width:630px">
				<div class="modal-header">
				
					<h4 class="modal-title" style="background-color : #337ab7;color : white;">Non
						Electrical Accessory</h4>
				</div>
				<div class="modal-body">
					<div style="background-color: white; width: 600px; border-color: #337ab7;border-weight: 1px;border-style:solid;">
					<br>
						<div class="gap" style="height: 10px; width: 475px"></div>
						<div class="row">
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor"
									style="margin-left: 10px">Accessory</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="nonEleAccessoryTextfiels"
									tabIndex="3001" type="text" style="margin-left: 10px">
							</div>
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor">Make</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="nonEleMakeTextfiels" type="text" tabIndex="3002">
							</div>
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor">Model</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="nonEleModelTextfiels" type="text" tabIndex="3003">
							</div>
						</div>
						<div class="row" style="padding: 5px">
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor"
									style="margin-left: 10px">Manf.Year</font></label> &nbsp;&nbsp;&nbsp; 
									<input maxlength="4"
									id="nonEleYearTextfiels" type="text" style="margin-left: 10px"  tabIndex="3004" onchange="validateManfYr(this.id);"
									 class="numberOnly"/>
							</div>
							<div class=" col-md-4">
								<label for="contact"><font class="fontColor">Price</font></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="nonElePriceTextfiels" type="text"  class="numberOnlyWithDecimal" tabIndex="3005"/>
							</div>
							<div class=" col-md-4">
							<br>
								<input type="Button" name="ADD" value="ADD" onclick="addNonEleAccTotable();" class="btn btn-primary" style="width:80px" tabIndex="3006">
							</div>
						</div>
						
						<div class="row" style="padding: 5px">
							<table id="nonEleAccesory" class="table" border="1"
								style="width: 590px; margin-left: 14px">
								<tr style="background-color: #dac8b6;">
								
									<th>Accessory</th>
									<th>Make</th>
									<th>Model</th>
									<th>Manf.Year</th>
									<th>Price</th>
									<th>Remove</th>
								</tr>
							</table>
						</div>
						<div class="row" style="padding: 5px">
							<div class=" col-md-6"></div>
							<div class=" col-md-6"style="float: right;margin-right: -90px;/*! margin-top: -1px; */margin-bottom: -40px;">
							<label for="Total:"><font class="fontColor">Total:</font></label>
								<input id="nonEleTotalTextfiels" style="width: inherit" type="text"  readonly >
							</div>
						</div>
						<div class="row" style="padding: 5px">
							<div class=" col-md-4"></div>
							<div class=" col-md-1" style="width:15%">
								<input type="Button" name="OK" value="OK" id="nonEleOk" tabIndex="3007" onclick="saveNonEleData();" class="btn btn-primary" style="width:80px" >
						</div>
							&nbsp; <input type="Button" name="Cancel" onclick="clearNonEleData();"
								value="Cancel" data-dismiss="modal" class="btn btn-primary" style="width:80px" tabIndex="3008">
								
						</div>
					</div>
				</div>


			</div>
			
		</div>
	</div>
	
	<!-- //////vol new //////// -->
	
	<div class="modal fade modalClose" id="voluntaryAccess" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 650px;">
				<div class="modal-header">
				
					<h4 class="modal-title"  style="background-color : #337ab7;color : white;">&nbsp;&nbsp;Voluntary Access</h4>
				</div>
				<div class="modal-body">
					<div style=" background-color: white;width: 600px; border-color: #337ab7;border-weight: 1px;border-style:solid;">
					<br>
						<div class="gap" style="height: 10px; width: 475px"></div>
						<div class="row">
							<div class=" col-md-4">	</div>
							<div class=" col-md-6 col-sm-6 col-lg-6 col-xs-12">	
								
							</div>
							<div class=" col-md-2"></div> 
							<div class=" col-md-4">	</div>
							
							<div class="row" style="padding-left:50px;padding-right:200px;height:220px;width:100%;padding-top:5px">
							<table id="voluntary" class="table" border="1"
								style="width: 590px; margin-left: 14px;width:100%;">
								  <thead>
								<tr style="background-color: #dac8b6;">
									
									<th>ID</th>
									<th>Rate</th>
									<th>Amount</th>
								</tr>
								  </thead>
							</table>
						</div>
							<div class=" col-md-2"></div>
						</div>
						<div class="row" style="padding: 5px">
						<div class=" col-md-4"></div>
							<div class=" col-md-1" style="width:15%">
								<input type="Button" name="OK" value="OK" onclick="getVoluntaryExcessData();" id="voluntaryOk" style="width:85px" class="btn btn-primary" >
							</div>
							 &nbsp; &nbsp;<input type="Button" name="Cancel" value="Cancel" id="voluntaryCancel" onclick="clearVoluntaryData();" data-dismiss="modal" class="btn btn-primary" style="width:85px">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //////vol new //////// -->
	
	<!--Extra covers Modal   -->
	
	<div class="modal fade modalClose" id="extraCompanyCovers" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 650px;">
				<div class="modal-header">
				
					<h4 class="modal-title"  style="background-color : #337ab7;color : white;">&nbsp;&nbsp;Extra Covers</h4>
				</div>
				<div class="modal-body">
					<div style=" background-color: white;width: 600px; border-color: #337ab7;border-weight: 1px;border-style:solid;">
					<br>
						<div class="gap" style="height: 10px; width: 475px"></div>
						<div class="row">
							<div class=" col-md-4">	</div>
							<div class=" col-md-6 col-sm-6 col-lg-6 col-xs-12">	
								
							</div>
							<div class=" col-md-2"></div>
							<div class=" col-md-4">	</div>
							
							<div class="row" style="padding-left:50px;padding-right:200px;height:220px;width:100%;padding-top:5px">
							<table id="extraCovers" class="table" border="1"
								style="width: 590px; margin-left: 14px;width:100%;">
								  <thead>
								<tr style="background-color: #dac8b6;">
								<th style="width: 30px"><img src="images/check.png" style="width:20px;height: 20px;"></img></th>
								<th style="display:none;">Cover Identity</th>
								<th style="width: 200px">Package Covers</th>
								<th>Status</th>
								<th style="display:none;">Cover Value</th>
								<th style="display:none;">Cover Id</th>
								<th style="display:none;">Policy Type</th>
								<th style="display:none;">PID</th>
								<th style="display:none;">Form ID</th>
								<th style="display:none;">Form Caption</th>
								<th style="display:none;">Num Field</th>
								<!-- style="display:none;" -->
								<th style="display:none;">Cover Number</th>
								</tr>
								  </thead>
								<tbody>
								</tbody>
							</table>
						</div>
							<div class=" col-md-2"></div>
						</div>
						<div class="row" style="padding: 5px">
						<div class=" col-md-4"></div>
							<div class=" col-md-1" style="width:15%">
								<input type="Button" name="OK" value="OK" onclick="getExraCovers();" id="extraCoverOk"  style="width:85px" class="btn btn-primary" >
							</div>
							 &nbsp; &nbsp;<input type="Button" name="Cancel" value="Cancel" onclick="cancelExtraCover()" data-dismiss="modal" class="btn btn-primary" style="width:85px">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Extra covers Modal   -->

	
	
	<!-- //////TPPD Popup////// -->
	<div class="modal fade modalClose" id="Tppd" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 650px;">
				<div class="modal-header">
				
					<h4 class="modal-title" id="modalTitle" style="background-color : #337ab7;color : white;">&nbsp;&nbsp;Tppd Ristricted</h4>
				</div>
				<div class="modal-body">
					<div style="background-color: white; width: 600px; border-color: #337ab7;border-weight: 1px;border-style:solid;">
					<br>
						<div class="gap" style="height: 10px; width: 475px"></div>
						<div class="row">
							<div class=" col-md-4">	</div>
							<div class=" col-md-6 col-sm-6 col-lg-6 col-xs-12">	
								
							</div>
							<div class=" col-md-2"></div>
							<div class=" col-md-4">	</div>
							<div class=" col-md-6 col-sm-6 col-lg-6 col-xs-12" id="noOfPersonsDiv">	
								<label for="contact"><font  style="margin-left: -38px;">No Of Person</font></label>
								&nbsp;&nbsp; 
								<input id="noOfPerson" style="width: 190px; margin-left: -5px;margin-bottom: 5px;" type="text" class="numberOnly" tabIndex="4001" maxlength="2">
							</div>
							<div class="row" style="padding-left:50px;padding-right:200px;height:220px;width:100%;padding-top:5px">
							<table id="tppdRestrict" class="table" border="1"
								style="width: 590px; margin-left: 14px;width:100%;">
								  <thead>
								<tr style="background-color: #dac8b6;">
									
									<th>ID</th>
									<th>Rate</th>
									<th>Amount</th>
								</tr>
								  <thead>
							</table>
						</div>
							<div class=" col-md-2"></div>
						</div>
						<div class="row" style="padding: 5px">
						<div class=" col-md-4"></div>
							<div class=" col-md-1" style="width:15%">
								<input type="Button" name="OK" value="OK" tabIndex="4002" onclick="getLibilityCoversData();" id="libCoverOk" style="width:85px" class="btn btn-primary" >
							</div>
							 &nbsp; &nbsp;<input type="Button" name="Cancel" value="Cancel" tabIndex="4003" onclick="clearTppdData();" data-dismiss="modal" class="btn btn-primary" style="width:85px">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- TPPD popup ends -->
	
	<!--Accidental Covers POPUP  -->
	
	<div class="modal fade modalClose" id="accidentalCover" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 920px;margin-left: -75px;">
				<div class="modal-header">
			
					<h4 class="modal-title" style="background-color : #337ab7;color : white;">&nbsp;&nbsp;Accidental Cover</h4>
				</div>
				<div class="modal-body">
				
						<div class="row"  style="background-color: white;width: 900px; border-color: #337ab7;border-weight: 1px;border-style:solid;margin-left:-5px;">
						<div class="row" style="margin-top:5px;">
						<div class="col-md-1">
						</div>
						<div class="col-md-2">
						<label for="Membership"><font class="fontColor">Name</font></label>
						</div>
						<div class="col-md-2">
						<label for="Membership"><font class="fontColor">DOB</font></label>
						</div>
						<div class="col-md-1">
						<label for="Membership"><font class="fontColor">Age</font></label>
						</div>
						<div class="col-md-2">
						<label for="Membership"><font class="fontColor">Gender</font></label>
						</div>
						<div class="col-md-2">
						<label for="Membership"><font class="fontColor">Relation</font></label>
						</div>
						<div class="col-md-2">
						<label for="Membership"><font class="fontColor">Dependant</font></label>
						</div>
						</div>
						<div class="row">
						<div class="col-md-1">
						<div class="checkbox">
						<label st> <input type="checkbox" tabIndex="1" style="margin-left: 1px" id="ACoverCheck1" onclick="enableAccidental1TF();"><font class="fontColor">&nbsp;&nbsp;&nbsp;&nbsp;1</font></label>
							</div>
							</div>
				<div class="col-md-2">
							<input id="name1" class="textOnly"  type="text"style="width:140px;" tabIndex="5001" >
						</div>
							<div class="col-md-2">
							<!-- <input id="dob1" class="datepicker" placeholder="Select Date" onchange="calcAccAge1('dob1','age1')"   type="text" style="width: 135px"> -->
							<div id="dob1"  onchange="calcAccAge1('dob1','age1')"  tabIndex="5002" style="width: 135px"></div>
						</div>
						<div class="col-md-1">
							<input id="age1"   type="text"size="2" tabIndex="5003" readonly>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="gender1" tabIndex="5004">
									<option value="Select" >Select</option>
									<option value="Male" >Male</option>
									<option value="Female">Female</option>			
								</select>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="relation1" tabIndex="5005">
									<option value="Select" >Select</option>
									<option value="Male" >Self</option>
									<option value="Female">Spouse</option>			
									<option value="Female">Son</option>			
									<option value="Female">Daughter</option>			
									<option value="Female">Mother</option>			
									<option value="Female">Father</option>			
									<option value="Female">Sister</option>			
									<option value="Female">Brother</option>			
									<option value="Female">Other</option>			
								</select>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="dependant1" tabIndex="5006">
									<option value="Select" >Select</option>
									<option value="Yes1" >Yes</option>
									<option value="No1">No</option>			
								</select>
						</div>
						</div>
						<div class="row" style="padding: 1px">
						<div class="col-md-1">
						<div class="checkbox">
						<label st> <input type="checkbox" style="margin-left: 1px" tabIndex="5007" id="ACoverCheck2" onclick="enableAccidental1TF();" disabled><font class="fontColor" >&nbsp;&nbsp;&nbsp;&nbsp;2</font></label>
							</div>
							</div>
							<div class="col-md-2">
							<input  id="name2" class="textOnly"  type="text" style="width:140px;" tabIndex="5008">
						</div>
							<div class="col-md-2">
							<!-- <input id="dob2" onclick="aaiDobValidation()" class="datepicker" placeholder="Select Date" onchange="calcAccAge1('dob2','age2')"  type="text" style="width: 135px"> -->
							<div id="dob2"  tabIndex="5009" onchange="calcAccAge1('dob2','age2')"  type="text" style="width: 135px"></div>
						</div>
						<div class="col-md-1">
							<input id="age2"  type="text"size="2" onfocus="getAge();" tabIndex="5010" readonly>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="gender2" tabIndex="5011">
									<option value="Select" >Select</option>
									<option value="Male" >Male</option>
									<option value="Female">Female</option>			
								</select>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="relation2" tabIndex="5012">
									<option value="Select" >Select</option>
									<option value="Male" >Self</option>
									<option value="Female">Spouse</option>			
									<option value="Female">Son</option>			
									<option value="Female">Daughter</option>			
									<option value="Female">Mother</option>			
									<option value="Female">Father</option>			
									<option value="Female">Sister</option>			
									<option value="Female">Brother</option>			
									<option value="Female">Other</option>			
								</select>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="dependant2" tabIndex="5013">
									<option value="Select" >Select</option>
									<option value="Yes2" >Yes</option>
									<option value="No2">No</option>			
								</select>
						</div>
						</div>
						<div class="row" style="padding: 1px">
						<div class="col-md-1">
						<div class="checkbox">
						<label> <input type="checkbox" tabIndex="5014" style="margin-left: 1px" id="ACoverCheck3" onclick="enableAccidental1TF();"disabled><font class="fontColor">&nbsp;&nbsp;&nbsp;&nbsp;3</font></label>
							</div>
							</div>
							<div class="col-md-2">
							<input id="name3" class="textOnly"  tabIndex="5015" type="text" style="width:140px;">
						</div>
							<div class="col-md-2">
							<!-- <input id="dob3" onclick="aaiDobValidation()" class="datepicker" placeholder="Select Date" onchange="calcAccAge1('dob3','age3')"   type="text" style="width: 135px"> -->
								<div id="dob3"  tabIndex="5016" onchange="calcAccAge1('dob3','age3')"  style="width: 135px"></div>
						</div>
						<div class="col-md-1">
							<input id="age3"  type="text"size="2" tabIndex="5017" readonly>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="gender3" tabIndex="5018">
									<option value="Select" >Select</option>
									<option value="Male" >Male</option>
									<option value="Female">Female</option>			
								</select>
						</div>
						<div class="col-md-2">
						<select style="width: 115px"  id="relation3" tabIndex="5019">
									<option value="Select" >Select</option>
									<option value="Male" >Self</option>
									<option value="Female">Spouse</option>			
									<option value="Female">Son</option>			
									<option value="Female">Daughter</option>			
									<option value="Female">Mother</option>			
									<option value="Female">Father</option>			
									<option value="Female">Sister</option>			
									<option value="Female">Brother</option>			
									<option value="Female">Other</option>			
								</select>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="dependant3" tabIndex="5020">
									<option value="Select" >Select</option>
									<option value="Yes3" >Yes</option>
									<option value="No3">No</option>			
								</select>
						</div>
						</div>
						<div class="row" style="padding: 1px">
						<div class="col-md-1">
						<div class="checkbox">
						<label> <input type="checkbox" style="margin-left: 1px" tabIndex="5021" id="ACoverCheck4" onclick="enableAccidental1TF();"disabled><font class="fontColor">&nbsp;&nbsp;&nbsp;&nbsp;4</font></label>
							</div>
							</div>
							<div class="col-md-2">
							<input id="name4" class="textOnly"  type="text" style="width:140px;" tabIndex="5022">
						</div>
							<div class="col-md-2">
							<!-- <input id="dob4" onclick="aaiDobValidation()" class="datepicker" placeholder="Select Date"  onchange="calcAccAge1('dob4','age4')"   type="text" style="width: 135px"> -->
								<div id="dob4"  tabIndex="5023" onchange="calcAccAge1('dob4','age4')"  type="text" style="width: 135px"></div>
						</div>
						<div class="col-md-1">
							<input id="age4"  type="text"size="2" tabIndex="5024" readonly>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="gender4" tabIndex="5025">
									<option value="Select" >Select</option>
									<option value="Male" >Male</option>
									<option value="Female">Female</option>			
								</select>
						</div>
						<div class="col-md-2">
						<select style="width: 115px" id="relation4" tabIndex="5026">
									<option value="Select" >Select</option>
									<option value="Male" >Self</option>
									<option value="Female">Spouse</option>			
									<option value="Female">Son</option>			
									<option value="Female">Daughter</option>			
									<option value="Female">Mother</option>			
									<option value="Female">Father</option>			
									<option value="Female">Sister</option>			
									<option value="Female">Brother</option>			
									<option value="Female">Other</option>			
								</select>
						</div>
						<div class="col-md-2">
							<select style="width: 115px"id="dependant4" tabIndex="5027">
										<option value="Select" >Select</option>
										<option value="Yes4" >Yes</option>
										<option value="No4">No</option>			
									</select>
							</div>
							
							</div>
							<div class="modal-footer">
							<div class="col-md-7">
							</div>
							<div class="col-md-5">
								<button type="button" class="btn btn-primary" onclick="saveAccidentalData();" tabIndex="5028" id="AccCovOkBtn">OK</button>
								<button type="button" class="btn btn-primary" onclick="clearAccidentalData();" tabIndex="5029">Clear</button>
								<button type="button" class="btn btn-primary" onclick="clearAccidentalData();" tabIndex="5030" data-dismiss="modal" id="accidentalCancel">Cancel</button>
							</div>
							</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	

	
	
	<!--Accidental Cover ends  -->
	
	
	<!-- AAI Membership Cover Starts Here -->
	
	<div class="modal fade modalClose" id="aaiMembership" role="dialog">
   <div class="modal-dialog">
      <div class="modal-content" style="width: 710px;">
         <div class="modal-header">
            <h4 class="modal-title" style="background-color : #337ab7;color : white;">&nbsp;&nbsp;AAI Membership Cover</h4>
         </div>
         <div class="modal-body">
            <div class="row"  style="background-color: white;width:680px; border-color: #337ab7;border-weight: 1px;border-style:solid;margin-left:1px;">
               <br>
               <div class="row">
                  <div class="col-sm-8 col-md-8 col-lg-8 col-xl-12">
                     <label for="Membership"><font class="fontColor"  style="margin-left:18px;" >AAI Membership</font></label>
                     <input id="aaiName"  type="text"  style="margin-left:90px;width:205px;" tabIndex="5201" >
                  </div>
               </div>
               <div class="row">
                  <div class="col-sm-8 col-md-8 col-lg-8 col-xl-12">
                     <label for="Membership"><font class="fontColor"  style="margin-left:18px">AAI Membership  No</font></label>
                     <input id="aaiMembershipNo"  type="text"   style="margin-left:67px;width:205px;" tabIndex="5202" >
                  </div>
               </div>
               <div class="row">
                  <div class="col-sm-8 col-md-8 col-lg-8 col-xl-12">
                     <label for="Membership"><font class="fontColor" style="margin-left:18px">AAI Association Code</font></label>
                     <input id="aaiCode"  type="text"  style="margin-left:55px;width:205px;"tabIndex="5203" >
                  </div>
               </div>
               <div class="row">
                  <div class="col-sm-8 col-md-8 col-lg-8 col-xl-12">
                     <label for="Membership"><font class="fontColor"  style="margin-left:18px">AAI Association Name</font></label>
                     <input id="aaiAssociationName"  type="text"  style="margin-left:53px;width:205px;" tabIndex="5204" class="textOnly">
                  </div>
               </div>
               <div class="row">
                  <div class="col-sm-10 col-md-10 col-lg-10 col-xl-12">
                     <label for="Membership"><font class="fontColor"  style="margin-left:18px">AAI Membership Expiry Date</font></label>
                     <div id="aaiExpiryDate" tabIndex="5205"  style="width: 210px; height: 26px; border: 2px solid #ccc;margin-top:-20px;margin-left:225px;" class="numberOnly"></div>
                  </div>
               </div>
               <br>
               <div class="modal-footer">
                  <div class="col-md-7">
                  </div>
                  <div class="col-md-5">
                     <button type="button" class="btn btn-primary" onclick="validateAAI();" tabIndex="5206" id="aaiOkBtn">OK</button>
                     <button type="button" class="btn btn-primary" onclick="clearAAI();" tabIndex="5207">Clear</button>
                     <button type="button" class="btn btn-primary"
                        data-dismiss="modal" id="aaiCancel" tabIndex="5208">Cancel</button>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>

	
	
	<!-- AAI Membership Cover Ends Here -->
	
	<!-- GeoExtension Cover Starts Here  -->
	<!-- GeoExtension Cover Starts Here  -->
	
	<div class="modal fade modalClose" id="geoExtension" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 650px;">
				<div class="modal-header">
					
					<h4 class="modal-title" style="background-color : #337ab7;color : white;">&nbsp;&nbsp;Geographical Extensions</h4>
				</div>
				<div class="modal-body">
					<div style="background-color: white; width: 600px; border-color: #337ab7;border-weight: 1px;border-style:solid;">
					<br>
						<div class="gap" style="height: 10px; width: 475px"></div>
						<div class="row">
							<div class="row" style="padding-left:50px;padding-right:200px;height:220px;padding-top:5px">
							<!-- <table id="geoExtensionCover" class="table" border="1"
								style="width: 590px; margin-left: 14px">
								  <thead>
								<tr style="background-color: #4cbcce;">
									
									<th>ID</th>
									<th>Name</th>
									
								</tr>
								  <thead>
							</table> -->
                <table id="geoExtensionCover" border="1" style="margin-left: 14px; width: 525px" class="datatable table striped hovered cell-hovered border bordered"cellspacing="0">
		                   <thead>
			                 <tr style="background-color: #dac8b6;">
									<th style="padding-left: 11px;"> Select</th>
									<th style="display:none;">ID</th>
									<th>Name</th>
									
		                        </tr>
		                </thead>
		               <tbody>
			
		           </tbody>
	            </table>
							
						</div>
							<div class=" col-md-2"></div>
						</div>
						<div class="row" style="padding: 5px">
							<div class=" col-md-4"></div>
							<div class=" col-md-1" style="width:15%">
								<input type="Button" name="OK" value="OK" id="geoOK" class="btn btn-primary" onclick="getGeoCountries()" style="width:85px;" >
							</div>
							&nbsp;&nbsp; <input type="Button" name="Cancel" value="Cancel" class="btn btn-primary" id="geoCancel" data-dismiss="modal" style="width:85px;">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- GeoExtension Cover Ends Here  -->
	<!-- -----------------------------Start Product Details ---------------------------------------------- -->
	<div id="openProductsBtnDiv" style="display:none" >
		<center>
			<label id="openProducts" onclick="openProducts();">
						<a   class="fontColor pointer">Click here to Go Back</a></label> </center>
		
	</div> 

<div  id="productDetails" style="display:none">

		<div class="row" style="background-color: white ; box-shadow: 0 5px 15px rgba(0, 0, 0, .5);"
			style="padding: 10px">
			<div style="background-color: #337ab7; color:white;">&nbsp;&nbsp;<b>Product
				Details</b></div>
		<!-- 	<div class="row" style="padding: 10px">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label class="mandatory" for="DoR"><font class="fontColor">Product</font></label>
				</div>
				<div class="col-xs-12 col-md-7 col-sm-7 col-lg-7">
					<select id="productname" onchange="proposal();" tabIndex="1"
						style="width: 613px;">
						<option>--Select--</option>
					</select>

				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">

					<label for="DoR" class="mandatory"><font class="fontColor">Proposal Type</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="proposal" onchange="year()" tabIndex="2" style="width: 220px;">
						<option>--Select--</option>
					</select>
				</div>
			</div> -->
			<div class="row" style="padding: 10px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory "><font class="fontColor">Mfr Year</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="year" onchange="month();" tabIndex="1" style="width: 220px;">
						<option>--Select--</option>
					</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory "><font class="fontColor" style="width: 100px;">Mfr Month</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="month" onchange="RTOState()" tabIndex="2" style="width: 220px;" class="manfMonth">
						<option>--Select--</option>
					</select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory"><font class="fontColor">Date Of
							Registration</font></label>
				</div>
				<div class="col-xs-12 col-md-6 col-sm-3 col-lg-3">
				<div style="width: 220px;" data-date-format="mm-dd-yyyy">
				
				<div id="dateofReg" placeholder="dd/mm/yyyy" tabIndex="3" type="text" style="width: 220px; height: 26px;" class="numberOnly">
        		</div>
				
					 <!-- <input id="jqxTf"  placeholder="dd/mm/yyyy" tabIndex="5" type="text" style="width: 220px; height: 26px;" class="numberOnly">  -->
					<!-- <input type="date" class="numberOnly" style="width: 220px; height: 26px;"> -->
					<!-- <span id="dateofReg" class="input-group-addon" style="width: 20%; float: right; height: 26px;"> <i	class="fa fa-calendar" ></i></span>
					 -->
					</div>
				</div>
			</div>
			<div class="row" style="padding: 10px; margin-top: -18px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">

					<label for="DoR" class="mandatory"><font class="fontColor">RTOState</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="RTOState" onchange="RTOCity()" tabIndex="4" style="width: 220px;">
						<option>--Select--</option>
					</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory"><font class="fontColor">RTOCity</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="RTOSCity" onchange="zonePopup();vehicleType(); " tabIndex="5" style="width: 220px;">
						<option>--Select--</option>
					</select>
					
				</div>


				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory"><font class="fontColor">Zone</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input type="text" id="Zone" class="mandatory" tabIndex="6" style="width: 220px;" readonly>
				</div>
			</div>
			<div class="row" style="padding: 10px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">

					<label for="DoR" class="mandatory"><font class="fontColor">Vehicle Type</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="vehicle" onchange="model()" tabIndex="7" style="width: 220px;">
						<option>--Select--</option>
					</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">

					<label for="DoR" class="mandatory"><font class="fontColor">Model</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="Model" onchange="variance();" tabIndex="8" style="width: 220px;">
						<option>--Select--</option>
					</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory"><font class="fontColor">Variance</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="varience" onchange="getValuesFromVarience();populateCustomer();fuelKit()"tabIndex="9"
						style="width: 220px;">
						<option myTag="123">--Select--</option>
					</select>
				</div>
			</div>
			<!--2 trial div end -->
			<div class="row" style="padding: 10px; margin-top: -15px;">
				<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4">
					<label for="DoR" class="mandatory"><font class="fontColor">Passengers</font></label>
					 <input id="passengers" name="applicant name" type="text" style="width: 50px;margin-left: 16px;" readonly>
					 <label for="DoR" class="mandatory"><font class="fontColor">HP</font></label>
					 <input id="hp" name="HP" type="text" style="width: 50px;" readonly> 
						<label for="DoR" class="mandatory"><font class="fontColor">KG</font></label> 
						<input id="kg" name="applicant name"
						 type="text" style="width: 45px;" readonly>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label class="mandatory" for="DoR"><font class="fontColor">Wheel</font></label></div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					 <input
						id="wheels" name="applicant name"  type="text"
						style="width: 93px;" readonly> <label for="DoR"><font class="fontColor">CC</font></label> <input
						id="cc" name="applicant name"  type="text"
						style="width: 100px;" readonly>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory" ><font class="fontColor">Fuel Type</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="fuelType" name="Proposal Type" 
						style="width: 220px;" type="text" readonly>
				</div>
			</div>
			<div class="row" style="padding: 10px">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR"><font class="fontColor">Fuel Kit</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="fuelKit" style="width: 220px;" tabIndex="10">
						<option>--Select--</option>
					</select>
				</div>
				<div class="liability">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1 ">

					<label for="DoR" class="mandatory"><font class="fontColor">Ex-showroom
							Price</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="exshowroomprice"  type="text" 
						style="width: 220px;" readonly />
				</div>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR" class="mandatory"><font class="fontColor">Customer
							Type </font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="customer" onchange="onCustomerTypeAction()" tabIndex="11" style="width: 220px;">
						<option>--Select--</option>
					</select>
				</div>
			</div>
			<div class="row" id="contactDetailsDiv">
			<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12">
				<div style="background-color:#337ab7; color:white;">&nbsp;&nbsp; Contact
						Details</div>
				<div class="row" style="padding: 10px">
					<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
						<label for="DoR" class="mandatory"><font class="fontColor">Mobile No</font></label>
					</div>
					<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="mobileNo"  type="text" 
						style="width: 220px;" tabIndex="12" class="validateMobile"/>
					
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">

					<label for="DoR" class="mandatory"><font class="fontColor">
					Email Id</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="emailId"  type="text" 
						style="width: 220px;" tabIndex="13" class="validateEmail" placeholder="abc@abc.com"/>
				</div>
				
			</div>
			</div>
			</div>
			
			
			<div class="row" id="isPrevPolicyDiv" style="display:none">
			<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12">
				<div style="background-color:#337ab7 ; color:white">&nbsp;&nbsp; Does Previous Policy Exists?</div>
				<div class="row" style="padding: 10px">
				  &nbsp;&nbsp;&nbsp;&nbsp;<label><input id="prevPolYes" type="radio" onclick="showPrevDtl();" name="prevPolicy"style="width: 12px"  value="prevYes"><font class="fontColor">&nbsp;&nbsp;Yes</font></label>
		 &nbsp;&nbsp;&nbsp;&nbsp;<label><input id="prevPolNo" type="radio"  onclick="showPrevDtl();"   name="prevPolicy"><font class="fontColor"  value="prevno">&nbsp;&nbsp;No</font></label>
		
			</div>
			</div>
			</div>
			<!-- -----------------------------Start Previous Policy Details ---------------------------------------------- -->
		
<div  style="background-color:white;">
<div  id="prevPolicyDetailsDiv" style="display:none">
<div class="panel-title prevpanelCls accordianColor" >
   &nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
      data-parent="#accordion" 
      href="#collapsePreviousPolicy" aria-expanded="true"
      aria- controls="collapseOne"> Previous Policy 
   Details </a>
</div>
<div id="collapsePreviousPolicy" class="panel-collapse collapse prevpanel"
   role="tabpanel" aria-labelledby="headingOne" class="main 
   wrapper2"
   style="margin: auto;">
   <br>
   <div style="background-color: 
    #337ab7 ; color:white">&nbsp;&nbsp;Product
      Details
   </div>
  
   <!-- ---------------temp Code------------------------------------------------ -->
   <!-- 			1 -->
			<div class="row" style="padding: 10px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label ><font class="fontColor">Product</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevProduct" onchange="prevDtlYear()" style="width: 220px;" tabIndex="21">
        			 </select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label><font class="fontColor">Mfr Year</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="prevMfrYear" 
            		onchange="prevDtlRtoState()"  style="width: 220px;" tabIndex="22">
         			</select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="reg"><font class="fontColor">Reg 
         			Date</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="prevDtlRegDate"  placeholder="Select Date" class="datepicker" type="text" style="width: 
           				 220px ;height:26px"; tabIndex="23"/>
				</div>
			</div>
			&nbsp;
	<!-- 		2 -->
			<div class="row" style="padding: 10px; margin-top: -18px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label><font class="fontColor">RTO State</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevRtoState" 
            			onchange="prevDtlRtoCity()" style="width: 220px;" tabIndex="24">
         			</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label><font class="fontColor">RTO City</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="prevRtoCity" 
           			 onchange="prevDtlVehicleType()" style="width: 220px;" tabIndex="25">
        			 </select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label><font class="fontColor">Vehicle Type</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevVehicleType" 
           			 onchange="prevDtlModel()"  style="width: 220px;" tabIndex="26">
         			</select>
				</div>
			</div>
			
<!-- 			3 -->
			<div class="row" style="padding: 10px; margin-top: -18px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label><font class="fontColor">Model</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevModel" onchange="prevDtlVariance()"  
           			 style="width: 220px;" tabIndex="27">
         			</select>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label><font class="fontColor">Variance</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevVariance" 
            			onchange="getPrevDtlValuesFromVarience();prevDtlPolicyType();" tabIndex="28"  style="width: 220px;">
         			</select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="pass"><font 
            			class="fontColor">Passanger</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="prevPassanger" type="text"size="1" style="width: 
           			 95px" readonly><label for="cc"><font class="fontColor">CC</font></label>
       				  <input id="prevCc" type="text"size="1" style="width: 95px" readonly>
				</div>
			</div>
			
<!-- 			4 -->
			<div class="row" style="padding: 10px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label><font class="fontColor">Policy Type</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevPolicyType" 
           			 onchange="prevDtlInsuranceCompany();prevDtlFuelKit();" tabIndex="29" style="width: 220px;">
         			</select>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label><font class="fontColor">Prev Insurance Type</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevInsuranceType" onchange=""  style="width: 
           				 220px ;height:26px;" tabIndex="30">
           				 <option></option>
           				 <option>Covernote</option>
           				 <option>Policy</option>
         			</select>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label><font class="fontColor">Prev Insurance Company</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevInsuranceCompany" onchange=""  tabIndex="31"
            			style="width: 220px;">
         			</select>
				</div>
			</div>
<!-- 	5   ----------->
			<div class="row" style="padding: 10px; margin-top: -15px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR"><font class="fontColor">Policy/ CoverNote No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="prevPolicyCoverNo" name="applicant name" class="alphaNumericOnly"
            			type="text" size="28" style="width: 220px;" tabIndex="32">
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label><font class="fontColor">FuelKit</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="prevFuelKit" 
            			style="width: 220px"; tabIndex="33">
         			</select>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="DoR"><font class="fontColor">FuelType</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="prevInsFuelType" name="PrevInsFuelType"  tabIndex="34"
           			 type="text" style="width: 220px;" readonly>
				</div>
			</div>
			
	
	<!-- ---------------temp Code------------------------------------------------ -->
   
   <br>
   <div class="col-xs-12" style="background-color: #337ab7 ;color : white;">&nbsp;&nbsp;Policy
      Period
   </div>
   &nbsp;
   
   <div class="row" style="padding: 5px">
      <div class="col-md-2" style="width: 190px;margin-left:10px";>
      <label for="SelectPeriod"><font class="fontColor" style="width: 160px";>Period</font></label>
       <select id="prevPolicyPeriod" onchange="prevDtlPolicyMonth()" style="width: 160px;" tabIndex="35">
            <option></option>
            <option>One Year</option>
            <option>Long Term</option>
            <option>Short Term</option>
         </select>
      </div>
      
       <div class="col-md-2" style="width: 190px";>
      <label for="SelectMonth" id="selectMonth"><font class="fontColor" style="width: 160px";>Month</font></label>
         <select id="prevPolicyMonth" onchange=""  style="width: 160px"; tabIndex="36">
           <option></option>
         </select>
      </div>
      
       <div class="col-md-2" style="width: 190px";>
      <label for="InsuranceFrom"><font class="fontColor" style="width: 160px";>Insurance From</font></label>
          <div id="prevFromDate"  placeholder="Select Date" class="datepicker" tabIndex="37" type="text"  style="width: 160px;height:26px";>	</div>	
      </div>
      
       <div class="col-md-2" style="width: 190px";>
      <label for="InsuranceFrom"><font class="fontColor" style="width: 160px";>Insurance To</font></label>
         <div id="prevToDate" placeholder="Select Date" tabIndex="38" onchange="prevToDateValidation()" tabIndex="38" class="datepicker"  type="text"  style="width: 160px;height:26px;">	</div>		
      </div>
      
       <div class="col-md-2" style="width: 190px";>
       <label for="IDV"><font class="fontColor" style="width: 160px";>IDV</font></label>
         <input id="prevIdv" type="text" size="10" style="width: 160px;" tabIndex="39" class="numberOnlyWithDecimal">
      </div>
      
       <div class="col-md-2" style="width: 190px";>
      <label for="CustomerType"><font class="fontColor" style="width: 160px";>Customer Type</font></label>
         <select id="prevCustomerType" onchange=""  style="width: 160px;" tabIndex="40">
            <option> </option>
         </select>
      </div>
   </div> 
   
   &nbsp;
   <div class="row" style="padding :10px;border: solid thin;width: 1140px;margin-left: 
      10px">
      <div class="row">
         <div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
            <label for="NCB"><font 
               class="fontColor">No Claim Bonus</font></label>
         </div>
         <div class="col-xs-12 col-md-1 col-sm-1 
            col-lg-1">
            <select id="prevNcb" name="No Claim 
               Bonus" onchange="" style="width: 70px;" tabIndex="41">
               <option 
                  value="Select"></option>
               <option 
                  value="0">25</option>
               <option 
                  value="1">35</option>
               <option 
                  value="2">40</option>
               <option 
                  value="3">45</option>
               <option 
                  value="3">50</option>
               <option 
                  value="3">55</option>
               <option 
                  value="3">65</option>
            </select>
         </div>
         <div class="col-xs-12 col-md-1 col-sm-1 
            col-lg-1">
            <label for="owner"><font 
               class="fontColor">Owner
            Type</font></label>
         </div>
         <div class="col-xs-12 col-md-2 col-sm-2 
            col-lg-2">
            <select id="prevOwnerType"  onchange="prevDtlOwnerType()"  tabIndex="42" name="ownerType" style="width: 140px;">
               <option 
                  value="Select"></option>
               <option 
                  value="First">First</option>
               <option 
                  value="Second">Second</option>
            </select>
         </div>
         <div class="col-xs-12 col-md-3 col-sm-3 
            col-lg-3">
            <input id="prevChkNmTransInRcBk" onchange="prevDtlNmTransInRcBk()" tabIndex="43"  type="checkbox" 
               name="xfer" value="xfer" ><font class="fontColor"> <b>Name
            Transfer in RC book If 
            Yes</b></font><br>
         </div>
         <div class="col-xs-12 col-md-2 col-sm-2 
            col-lg-2">
            <label for="date"><font 
               class="fontColor">Transfer
            Date</font></label>
         </div>
         <div class="col-xs-12 col-md-2 col-sm-2 
            col-lg-2">
            <input id="prevDtNmTransInRcBk" placeholder="Select Date" class="datepicker" tabIndex="44" type="text"  style="width: 160px;height:26px;">
         </div>
      </div>
      <div class="row">
         <div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
            <label for="NCB"><font class="fontColor">No Of
            Claims</font></label>
         </div>
         <div class="col-xs-12 col-md-1 col-sm-1 
            col-lg-1">
            <select id="prevNoOfClaims" name="No of 
               Claims" tabIndex="45" style="width: 70px;">
               <option 
                  value="Select"></option>
               <option 
                  value="0">0</option>
               <option 
                  value="1">1</option>
               <option 
                  value="2">2</option>
               <option 
                  value="3">3</option>
            </select>
         </div>
         
         
         <div class="col-xs-12 col-md-1 col-sm-1 
            col-lg-1">
            <label for="veh"><font 
               class="fontColor">Vehicle Reg
            No</font></label>
         </div>
         <div class="col-xs-12 col-md-2 col-sm-2 
            col-lg-2">
               <input id="prevVehId1" 
                  type="text" size="1"  style="width: 30px;" tabIndex="46" readonly="readonly">
               <input id="prevVehId2" 
                  type="text" size="1" style="width: 30px;" tabIndex="47" readonly="readonly">
               <input id="prevVehId3"
                  type="text" size="1" maxlength="2" style="width: 30px;"  tabIndex="48" class="textOnly">
               <input id="prevVehId4" tabIndex="49" maxlength="4"
                  type="text" size="1" class="numberOnly">
         </div>
         <div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
            <input id="prevChkNmTransInInsPolicy" type="checkbox" 
               name="xfer" value="xfer" ><font class="fontColor"> <b>Name
            Transfer in Insurance Policy If 
            Yes</b></font><br>
         </div>
         <div class="col-xs-12 col-md-2 col-sm-2 
            col-lg-2">
            <label for="date"><font 
               class="fontColor">Transfer
            Date</font></label>
         </div>
         <div class="col-xs-12 col-md-2 col-sm-2 
            col-lg-2">
            <input id="prevDtNmTransInInsPolicy"  class="datepicker"  type="text" tabIndex="50"
               size="20"  style="width: 160px;height:26px;" placeholder="Select date">
         </div>
      </div>
   </div>
  
</div>
</div>
<!-- -----------------------------End Previous Policy Details ---------------------------------------------- -->
<br>
		</div>		<!-- closing div of previous Details 	---------------------->
			
			
			
			
			<div id="showCompanyDiv"><button type="button" class="btn btn-primary"
				style="float: right; margin-right: 52px;margin-bottom: 10px;" id="showCompanyBtn" tabIndex="51"
				onclick="showCompanies();">Get Premium</button></div> 
			<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3"></div>
			<input type="hidden" id="zoneId">
			 <input type="hidden" id="depr"> 
			 <input type="hidden" id="deprLimit"> 
			 <input type="hidden" id="age">
			 <input type="hidden" id="gap">
			 <input type="hidden" id="proposalNo">
			 <input type="hidden" id="quotationNo">
			 <input type="hidden" id="premiumAmount">
			 <input type="hidden" id="customerId">
			 <input type="hidden" id="proposalDetailsTable">
			 <input type="hidden" id="allCovers">
			 <input type="hidden" id="allCoversValue">
			 <input type="hidden" id="allCoversNumber">
			 <input type="hidden" id="regNo">
			 <input type="hidden" id="regNo1">
			 <input type="hidden" id="regNo2">
			 <input type="hidden" id="regNo3">
			 <input type="hidden" id="regNo4">
			 <input type="hidden" id="newVehicle">
			 <input type="hidden" id="finalRegNo">
			 <input type="hidden" id="customerType">
			 <input type="hidden" id="custTypeIdRel">
			 <input type="hidden" id="custType">
			 <input type="hidden" id="groupID">
			 <input type="hidden" id="sessionId">
		<input type="hidden" id="paymentCompany">
		<input type="hidden" id="userName">
		<input type="hidden" id="password">
		<input type="hidden" id="paymentMode">
		<input type="hidden" id="CDBalanceAmount">
		<input type="hidden" id="CDTBalanceAmount">
		<input type="hidden" id="productCode">
		<input type="hidden" id="companyDtl_companyId">
		<input type="hidden" id="companyName">
		<input type="hidden" id="entryDate">
		<input type="hidden" id="hdfcFinalProposalNo">
		<input type="hidden" id="insName">
		<input type="hidden" id="hdfcNetPremium">
		<input type="hidden" id="hdfcTotalTax">
		<input type="hidden" id="hdfcTotalPremium">
		<input type="hidden" id="hdfcVehicleIDV">
		<input type="hidden" id="hdfcExShowroomPrize">
		<input type="hidden" id="electricalAcc">
		<input type="hidden" id="posPolicyNoUniversal">
		<input type="hidden" id="PolNoFuture">
		<input type="hidden" id="agentCodeFG">
		<input type="hidden" id="fuID">
	<input type="hidden" id="shreeRamApprovePolSysIdForPdf">
	<input type="hidden" id="nonElectricalAcc">
	<input type="hidden" id="elementIndex">
	<input type="hidden" id="row_index">
	<input type="hidden" id="selectedIndex">
	<input type="hidden" id="payer">
	<input type="hidden" id="transactionDate">
	<input type="hidden" id="ModeOfPayment">
	<input type="hidden" id="proposalDetailTable">
	<input type="hidden" id="PolicyNumber">
	<input type="hidden" id="prevCompDataCompList">
	<input type="hidden" id="paymentDataAll">
	<input type="hidden" id="saveRatingType">
	<input type="hidden" id="saveRatingId">
	<input type="hidden" id="saveRatingVal">
	<input type="hidden" id="saveRateCalc">
	<input type="hidden" id="saveAmount">
	<input type="hidden" id="compSerial">
	<input type="hidden" id="totalEleAmount">
	<input type="hidden" id="totalNonEleAmount">
	<input type="hidden" id="insDtlAgeTf">
	<input type="hidden" id="geoSelectedIds">
	<input type="hidden" id="bajajQuouteNo">


			<!--3 trial div end -->
			
		<!-- -----------------------------Start Other Details ---------------------------------------------- -->
			<div class="row" id="otherDetailsDiv" style="display:none">

				<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12">
					<div style="background-color: #337ab7 ;color : white;">&nbsp;&nbsp; Other
						Details</div>
<!-- 	1--------- -->
			<div class="row" style="padding: 10px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="date" class="mandatory"><font class="fontColor"
						>Risk Start Date</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<!-- <input id="riskStartDt" class="datepicker" placeholder="Select Date" type="text" style="width: 
           				 220px ;height:26px"  tabIndex="65" /> -->
           			<input id="riskStartDt" type="text" style="width: 
           				 220px ;height:26px"  tabIndex="65" readonly/>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label for="date"><font class="fontColor">Body Type</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="bodyType" style="width: 
           				 220px;"  tabIndex="66">
								<option>Body Type</option>
					 </select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="date"><font class="fontColor">Vehicle No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="vehRegNo1" type="text" size="1"  tabIndex="67" readonly/>
					<input id="vehRegNo2" type="text" size="1"  tabIndex="68" readonly/> 
					<input id="vehRegNo3" maxlength="2" class="textOnly" type="text" size="1" style="width:80px"  tabIndex="69"/> 
					<input id="vehRegNo4" maxlength="4" onchange="checkVehNoLength(this)" type="text" size="1" class="numberOnly" style="width: 80px"  tabIndex="70"/>
				</div>
			</div>
			
			<div class="row" style="padding: 10px; margin-top: -18px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="date" class="mandatory"><font class="fontColor"
								>Engine no</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="engineNo" type="text" size="17" tabIndex="71" style="width: 
           				 220px;" class="fiveDigitValidation alphaNumericOnly" />
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="date" class="mandatory"><font class="fontColor"">Chassis
									no</font></label> 
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<input id="chasisNo" type="text" size="25" style="width: 
           				 220px;" tabIndex="72" class="fiveDigitValidation alphaNumericOnly" />
				</div>
				<div class="liability">			
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="date"><font class="fontColor"
								>Basic IDV</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="basicIdv" type="text" size="17" class="numberOnlyWithDecimal" style="width:  95px "/>
							<label for="date"><font class="fontColor" style="width:  80px " >Total IDV</font></label><input id="totalIDV"
								class="numberOnlyWithDecimal" type="text" size="25" style="width: 90px;margin-left: 5px "/>

				</div>
				</div>
			</div>
					
			</div>
		</div>
<!-- -----------------------------Ends Other Details ---------------------------------------------- -->	
			
			
		</div>
	
	</div>
<!-- -----------------------------End Product Details ---------------------------------------------- -->

			

	<div class="row rowColor" style="background-color: white; box-shadow: 0 5px 15px rgba(0, 0, 0, .5);" >
		<div id="coversDiv" style="display:none">
			<div class="panel-title run1 accordianColor">
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapsepack" aria-expanded="true"
					aria-controls="collapseOne"> Covers </a>
			</div>
			<div id="collapsepack" class="panel-collapse collapse run"
				role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				</br></br>
			<div class="liability">
			<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4" style="height: 40%;">
				<div style="background-color: #337ab7; color :white">&nbsp;&nbsp;Package
					Details</div>
				<br>
				<div class="main wrapper2" style="margin: auto; overflow-x:hidden;overflow-y:hidden;height: 250px;">
					<div class="div2 div4">
					<b>Search: </b><input type="text"  style="width: 295px; "class="searchFromTablePack"  placeholder="Search Here.." title="Type in a name">
					</br></br>
						
						<table id="packageDetails" class="table" border="1" 
							style="width:98%;lengthChange: false;border-style: hidden;">
							 <thead data-sorter="false" style="display: table;width:100%;orderable:false;" >
							<tr style="background-color: #dac8b6;">

								<th style="width: 40px">S/N</th>
								<th style="display:none;">Cover Identity</th>
								<th style="width: 200px">Package Covers</th>
								<th>Status</th>
								<th style="display:none;">Cover Value</th>
								<th style="display:none;">Cover Id</th>
								<th style="display:none;">Policy Type</th>
								<th style="display:none;">PID</th>
								<th style="display:none;">Form ID</th>
								<th style="display:none;">Form Caption</th>
								<th style="display:none;">Num Field</th>
								<!-- style="display:none;" -->
								<th style="display:none;">Cover Number</th>

							</tr>
							</thead>
							<tbody class="searchCoverTablePack" style=" display: block; max-height: 150px;overflow-y: scroll;">
						 
						 </tbody>
						</table>
					</div>
				</div>

			</div>
			</div>
			<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4 formatDiv" >
				<div style="background-color:  #337ab7; color :white">&nbsp;&nbsp;Liability
					Details</div>
				<br>

				<div class="main wrapper2" style="margin: auto; overflow-x:hidden;overflow-y:hidden;height: 250px;">
					<div class="div2 div4">
					<b>Search: </b><input type="text"  id="searchLiability" style="width: 295px; "class="searchFromTableLib"  placeholder="Search Here..." title="Type in a name">
						</br></br>
						<table id="liabilityDetails" class="table" border="1" 
							style="width:98%;lengthChange: false;border-style: hidden;">
							 <thead style="display: table;width:100%;orderable:false;">
							<tr style="background-color: #dac8b6;">

								<th style="width: 40px">S/N</th>
								<th style="display:none;">Cover Identity</th>
								<th style="display:none;">Cover Value</th>
								<th style="display:none;">Form Caption</th>
								<th style="display:none;">Num Field</th>
								<th style="display:none;">Policy Type</th>
								<th style="width: 200px">Liability Covers</th>
								<th>Status</th>
								<th style="display:none;">Cover Id</th>
								<th style="display:none;">Form Id</th>
								<th style="display:none;">PID</th>
								<th style="display:none;">Cover Number</th>

							</tr>
							</thead>
							<tbody class="searchCoverTableLib" style=" display: block; max-height: 150px;overflow-y: scroll;">
						 
						 </tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="liability">
			<div class="col-xs-12 col-md-4 col-sm-4 col-lg-4">

				<div style="background-color:  #337ab7; color :white">&nbsp;&nbsp;Additional
					Coverage</div>
				<br>

				<div class="main wrapper2" style="margin: auto; overflow-x:hidden;overflow-y:hidden;height: 250px;">
					<div class="div2 div4">
					<b>Search: </b><input type="text"  style="width: 295px; "class="searchFromTableAddon"  placeholder="Search Here.." title="Type in a name">
					</br></br>	
					<table id="additionalcover" class="table" border="1" 
							style="width:98%;lengthChange: false;border-style: hidden;">
							 <thead style="display: table;width:100%;orderable:false;">
							<tr style="background-color: #dac8b6;">
								<th style="width:30px;"><img src="images/check.png" style="width:20px;height: 20px;"></img></th>
								<th style="width:30px;">Edit</th>
								<th style="display:none;">Sr No</th>
								<th style="display:none;">PID</th>
								<th style="display:none;">Cover Identity</th>
								<th style="width:270px;">Additional Covers</th>
								<th style="display:none;">POLICYTYPE</th>
								<th style="display:none;">Form ID</th>
								<th style="display:none;">Form Caption</th>
								<th style="display:none;">Num Fields</th>
								<th style="display:none;">Total Person</th>
								<th style="display:none;">Status</th>	<!-- //style="display:none;" -->
								
							</tr>
							</thead>
							<tbody class="searchCoverTableAddon" style=" display: block; max-height: 145px;overflow-y: scroll;">
						 
						 </tbody>
						</table>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	
	<div id="getPremiumBtnDiv" style="display:none">
		<button  type="button" class="btn btn-primary" style="margin-left: 1040px;width: 120px;margin-top: 15px;" tabIndex="73" id="getPremiumBtn" onclick=" hitCompanyPremiumRequest();">Get Premium</button>
	<div class="gap" style="height: 30px; width: 475px"></div>
	</div>
<!-- </div> -->

		

<div id="companyDtlDiv" style="display:none; height: 455px;">
			<div class="panel-title compDtl1 accordianColor" >
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseCompanyDetails" aria-expanded="true"
					aria-controls="collapseOne"> Company Details </a>
			</div>

			<br>

			<!-- <div id="collapseCompanyDetails" class="panel-collapse collapse compDtl" role="tabpanel"
				aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				<div class="div2 div4"> -->
		<div id="collapseCompanyDetails" class="panel-collapse collapse compDtl" 
				role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				<div class="main wrapper2" style="margin: auto;  overflow-y: hidden; height: 400px;">
					<div class="div2 div4">
					<table id="companyDetails" class="table css-serial" border="1" width="100" style="margin: auto; width: 100px;">
						 <thead>
						<tr style="background-color: #dac8b6;">
							<th>Select</th>
							<th>Sr No</th> 
							<th>Addon</th> 
							<th>Company</th>
							<th>Risk Start Date</th>
							<th>Age</th>
							<th>Expiry Date</th>
							<th>Disc OD</th>
							<th>Disc TP</th>
							<th>OD</th>
							<th>TP</th>
							<th>Net</th>
							<th>Tax</th>
							<th>Total</th>
							<th>proposal</th>
							<th>Max Disc OD</th>
							<th>Max Disc TP</th>
							<th >Company Id</th>
							<th style="display:none;">CGST</th>
							<th style="display:none;">SGST</th>
							<th style="display:none;">IGST</th>
							<th style="display:none;">UGST</th>
							<th style="display:none;">Service Charges</th>
							<th >coverId</th>
							<th >coverVal</th>
							<th >CoverNo</th>
						</tr>
						 </thead>
						 <tbody>
						 
						 </tbody>
					</table>
				</div>
			</div>
</div>
<br>
</div>



<!-- ---------------	Insured Details Code Starts Here -------------------------	 -->
		
			<div class="row" style="width: 1165px; margin-left: 2px;display:none" id="insuredDtlDiv">
				<div class="panel-title insDtl1 accordianColor" >
   &nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
      data-parent="#accordion" href="#collapseInsuredDetails" aria-expanded="true"
      aria-controls="collapseOne"> Insured Details </a>
		</div>
		<br>
<div id="collapseInsuredDetails" class="panel-collapse collapse insDtl"
   role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
   style="margin: auto;">
   
    <div class="row" style="border: solid thin;width: 1140px;margin-left: 
      10px">
    <div class="row" style="padding: 15px">
   			<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 80px";>
   			<br>
					<label for="contact"><font class="fontColor">Insured Name</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width: 150px";>
				<label for="contact" class="mandatory"><font class="fontColor"style="margin-left: 8px;" >
        			 Initial</font></label>	
					<select id="insDtlInitial" onclick="insDtlRadioVal()" style="width: 120px"; tabIndex="90">
           				 <option>--Select--</option>
         			</select>
				</div>
    
     <!--  <div class="col-md-2" style="width: 230px;margin-left:10px";>
      <label for="contact"><font class="fontColor"style="margin-left: 8px;">
         Initial</font></label>	
       <select id="insDtlInitial" style="width: 200px";>
            <option>--Select--</option>
         </select>
      </div> -->
     
       <div class="col-md-2" style="width: 305px";>
      <label for="contact" class="mandatory"><font class="fontColor" style="margin-left: 8px;">First Name</font></label>
         <input id="insDtlFname" type="text" class="textOnly" style="width: 250px" tabIndex="91">
      </div>
      
       <div class="col-md-2" style="width: 305px";>
      <label for="contact" class="mandatory"><font class="fontColor" style="margin-left: 8px;">Middle Name</font></label>		
          <input id="insDtlMname" type="text" class="textOnly" size="15" style="width: 250px" tabIndex="92">		
      </div>
      
       <div class="col-md-2" style="width: 275px";>
      <label for="contact" class="mandatory"><font class="fontColor" style="margin-left: 8px;">Last Name</font></label>	
         <input id="insDtlLname" type="text" class="textOnly" size="15" style="width: 270px" tabIndex="93">	
      </div>
	</div> 
	
	<div class="row" style="padding: 15px; margin-top: -18px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 80px";>
					<label for="contact" class="mandatory"><font class="fontColor" >Date Of Birth</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width: 150px"; >
				<!-- 	<input id="insDtlDob"   placeholder="dd/mm/yyyy" tabIndex="94" type="text"  style="width: 120px;height:26px";> -->
					<div id="insDtlDob" placeholder="dd/mm/yyyy" tabIndex="94" type="text" style="width: 120px; height: 26px;" class="numberOnly"></div>
				</div>
				
				
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 90px";>
					 <label for="Gender" class="mandatory"><font class="fontColor" >Gender</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3" style="width: 225px";>
					<label><input id="insDtlGenMale" type="radio" tabIndex="95" name="gender"style="width: 12px"  value="male"><font class="fontColor">Male</font></label>
					<label><input id="insDtlGenFemale" type="radio"  tabIndex="96" name="gender"><font class="fontColor"  value="female">Female</font></label>
					<label><input id="insDtlGenOther" type="radio" tabIndex="97" name="gender"><font class="fontColor"  value="other">Other</font></label>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor">Marital Status</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width: 200px";>
					<select id="insDtlMaritialStatus" style="width: 150px"; tabIndex="98">
           				 <option>Maritial Status</option>
         			</select>
				</div>
				
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width:130px;">
					<label for="adhar" class="mandatory"><font class="fontColor" >
         			Nationality</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width: 155px;">
					<select id="insDtlNationality" style="width: 140px;" tabIndex="99">
            			<option>--Select--</option>
         			</select>
			</div>
	</div>
	  </div>
	<div class="row" style="padding: 10px; margin-top: -18px;">
	<br>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
         			Mobile</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="insDtlMobileNo" type="text" class="validateMobile" tabIndex="100" 
            			 size="30" style="width: 250px">
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
        				 Phone No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<input id="insDtlPhoneNo" type="text"  class="validatePhone"  tabIndex="101"  size="30" style="width: 250px;">
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor">
         			Email Id</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="insDtlEmail"  placeholder="abc@abc.com" type="text" class="validateEmail"
            			 size="34" style="width: 250px;" tabIndex="102" >
				</div>
	</div>
	
	<div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor">
        				 PAN Card No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input  id="insDtlPanNo"  type="text"
            			 size="30" onkeypress="" style="width: 250px;" tabIndex="103" class="validatePan">
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adharEnrollNo"><font class="fontColor" >
         			Aadhar Enroll No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<input  id="insDtlAadharEnrollNo"  type="text"
            			 size="30"  style="width: 250px;"  tabIndex="104"  class="validateAadharEnrol">
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor">
         				Aadhar No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input  id="insDtlAadharNo"  type="text"
            		 size="30"  style=" width: 250px;"  tabIndex="105" class="validateAadhar">
					</div>
	</div>
	
	
	
	
   <div  class="row">
      <label for="corr"><font class="fontColor" style="margin-left: 25px">
      Correspondence Address</font></label>	   
   </div>
   
  <!--  Address changes CA -->
   <div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label class="mandatory"><font class="fontColor">
        			 Name</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="insDtlCAName"  class="textOnly" type="text" 
            		 style="width: 250px;" tabIndex="106">
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label class="mandatory"><font class="fontColor" >
        			 Country</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="insDtlCACountry" onchange="insDtlCAState()" style="width: 250px;" tabIndex="107">
           			 <option>--Select--</option>
         			</select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label class="mandatory"><font class="fontColor" >
        				 State</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					 <select id="insDtlCAState" onchange="insDtlCADistrict()" style="width: 250px;" tabIndex="108">
           			 <option>--Select--</option>
         				</select>
				</div>
	</div>
	
	<div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor">
        			 District</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="insDtlCADistrict" onchange="insDtlCACity()" style="width: 250px;" tabIndex="109">
           			 <option>--Select--</option>
      				   </select>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
        			 City</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="insDtlCACity"  onchange="insDtlCAPincode()"  style="width: 250px;" tabIndex="110">
           			 <option>--Select--</option>
         			</select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 98px;">
					<label for="adhar" class="mandatory"><font class="fontColor">
        			 Pincode</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width: 250px;">
					 <select id="insDtlCAPincode" onchange="insDtlCALandmark()" style="width: 250px;" tabIndex="111">
            		<option>--Select--</option>
        			</select>
				</div>
	</div>
	
	<div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 120px;">
					<label for="adhar" class="mandatory"><font class="fontColor" >
         			Landmark</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="margin-left:-23px;">
					<input id="insDtlCALandmark" onchange="insDtlCAStreet()" list="insDtlCALandmarkList" type="text"
           			  style="width: 250px;" tabIndex="112">
					<datalist id="insDtlCALandmarkList"></datalist>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" ><font class="fontColor">
         			Street</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<input id="insDtlCAStreet" onchange="insDtlCAHouse()"  list="insDtlCAStreetList" type="text"
           			 style="width: 250px;" tabIndex="113">
            		<datalist id="insDtlCAStreetList"></datalist>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar"><font class="fontColor">
        			 House No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					 <input id="insDtlCAHouse"  list="insDtlCAHouseList"  type="text"
           			   style="width: 250px;" tabIndex="114">
           			 <datalist id="insDtlCAHouseList"></datalist>
				</div>
	</div>
	
	
   <!--  Address changes CA -->
   
      <!--  Address changes PA -->
       <div  class="row" style="padding: 25px; ">
         <label for="corr"><font class="fontColor" >
         Permanent Address</font></label>	
            <label> <input style="margin-left: 20px;" type="checkbox" id="insDtlChk1" tabIndex="115" onclick="copyPermanentAddress()"><font size="2"  style="font-weight:normal;" class="fontColor">&nbsp;&nbsp;&nbsp;&nbsp;Same As Correspondance Address
         </font></label>   
      </div>
      
      <div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar"><font class="fontColor">
           			 Name</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="insDtlPAName" class="textOnly" type="text"
               		 style="width: 250px;" tabIndex="116">
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor">
            		Country</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="insDtlPACountry"  onchange="insDtlPAState();"  style="width: 250px;" tabIndex="117">
         			   <option>--Select--</option>
         			</select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
           			 State</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					 <select id="insDtlPAState" onchange="insDtlPADistrict();"  style="width: 250px;" tabIndex="118">
            		<option>--Select--</option>
         			</select>
				</div>
	</div>
	
	<div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label for="adhar" class="mandatory"><font class="fontColor" >
           			 District</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="insDtlPADistrict" onchange="insDtlPACity()" style="width: 250px;" tabIndex="119">
            		<option>--Select--</option>
             		</select>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
          			  City</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					 <select id="insDtlPACity" onchange="insDtlPAPincode()"  style="width: 250px;" tabIndex="120">
         			   <option>--Select--</option>
            		 </select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 98px;">
					<label for="adhar" class="mandatory"><font class="fontColor">
           			 Pincode</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width: 250px;">
					 <select id="insDtlPAPincode" onchange="insDtlPALandmark()" style="width: 250px;" tabIndex="121">
            		<option>--Select--</option>
         			</select>
				</div>
	</div>
	
	<div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1"  style="width: 120px;">
					<label for="adhar" class="mandatory"><font class="fontColor" >
           			 Landmark</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="margin-left:-23px;">
					<input id="insDtlPALandmark" onchange="insDtlPAStreet()" list="insDtlPALandmarkList" type="text"
            		 style="width: 250px;" tabIndex="122">
					<datalist id="insDtlPALandmarkList"></datalist>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar"><font class="fontColor">
            		Street</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<input id="insDtlPAStreet" onchange="insDtlPAHouse()"  list="insDtlPAStreetList" type="text" tabIndex="123"
            		  style="width: 250px;">
            		<datalist id="insDtlPAStreetList"></datalist>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label for="adhar"><font class="fontColor">
           			 House No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="insDtlPAHouse"  list="insDtlPAHouseList"  type="text" tabIndex="124"
            		  style="width: 250px;">
            		<datalist id="insDtlPAHouseList"></datalist>
				</div>
	</div>
      
      <!--  Address changes PA -->
     
       <!--  Address changes OA -->
       <div  class="row" style="padding: 25px; ">
         <label for="corr"><font class="fontColor">
         Office Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></label>	  	
            <label> <input style="margin-left: 20px;" type="checkbox"  id="insDtlChk2" tabIndex="125"  onclick="copyOfficeAddress()"><font size="2" style="font-weight:normal;" class="fontColor">&nbsp;&nbsp;&nbsp;&nbsp;Same As Permanent Address
         </font></label>   
      </div>
      
      <div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					 <label for="adhar"><font class="fontColor">
            		Name</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="insDtlOAOfcName" type="text"
              		 style="width: 250px;" tabIndex="126">
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
           			 Country</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="insDtlOACountry" onchange="insDtlOAState()" style="width: 250px;" tabIndex="127">
           			 <option>--Select--</option>
            		 </select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor">
           			 State</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="insDtlOAState"   onchange="insDtlOADistrict()" style="width: 250px;" tabIndex="128">
            		<option>--Select--</option>
            		 </select>
				</div>
	</div>
	
	<div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
           			 District</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="insDtlOADistrict" onchange="insDtlOACity()" style="width: 250px;" tabIndex="129">
           			 <option>--Select--</option>
           		  </select>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar" class="mandatory"><font class="fontColor" >
          			  City</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					 <select id="insDtlOACity" onchange="insDtlOAPincode()" style="width: 250px;" tabIndex="130">
           			 <option>--Select--</option>
           			  </select>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 98px;">
					 <label for="adhar" class="mandatory"><font class="fontColor" >
            		Pincode</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width: 250px;">
					 <select id="insDtlOAPincode" onchange="insDtlOALandmark()" style="width: 250px;" tabIndex="131">
          			  <option>--Select--</option>
         			</select>
				</div>
	</div>
	
	<div class="row" style="padding: 10px; ">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 120px;">
					 <label for="adhar" class="mandatory"><font class="fontColor">
           			 Landmark</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3"  style="margin-left:-23px;">
					  <input id="insDtlOALandmark" onchange="insDtlOAStreet()" list="insDtlOALandmarkList" type="text" tabIndex="132"
            			 style="width: 250px;" >
					 <datalist id="insDtlOALandmarkList"></datalist>
				</div>
				
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="adhar"><font class="fontColor">
          			  Street</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<input id="insDtlOAStreet" onchange="insDtlOAHouse()"  list="insDtlOAStreetList" type="text" tabIndex="133"
            		 style="width: 250px;" >
           			 <datalist id="insDtlOAStreetList"></datalist>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					  <label for="adhar"><font class="fontColor">
           			 House No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="insDtlOAHouse"  list="insDtlOAHouseList"  type="text" tabIndex="134"
            		 style="width: 250px;" >
            		<datalist id="insDtlOAHouseList"></datalist>
				</div>
	</div>
       
       <!--  Address changes OA -->
    
      
      <div class="modal-footer">
      <div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
       <button type="button" class="btn btn-primary" onclick="clearInsuredDetails();" id="clearInsuredDtl" tabIndex="135"
            >Clear Insured Details</button>
      </div>
    
   	</div>
   
</div>
	</div>
	
	<!-- ---------------	Insured Details Code Ends Here -------------------------	 -->

		<!-- ---------------	Nominee Details Code Starts Here -------------------------	 -->
			<div id="nomineeDtlDiv" style="display:none">
			<div class="panel-title nominee1 accordianColor" >
   &nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
      data-parent="#accordion" href="#collapseNomineeDetails" aria-expanded="true"
      aria-controls="collapseOne">Nominee Details  </a>
</div>
<br>
<div id="collapseNomineeDetails" class="panel-collapse nominee collapse" role="tabpanel"
   aria-labelledby="headingOne" class="main wrapper2"
   style="margin: auto;">
   <div class="row" style="padding: 5px">
      <div class="col-md-3">
         <div class="col-md-3">
            <label for="contact" class="mandatory"><font class="fontColor">Initial</font></label>	
         </div>
         <div class="col-md-3">
            <select id="nomDtlInitial" style="width: 175px" tabIndex="141">
               <option>--Select--</option>
            </select>
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" class="mandatory"><font class="fontColor" >First Name</font></label>
         </div>
         <div class="col-md-3">
            <input id="nomDtlFname" type="text" class="textOnly" style="width: 173px"  tabIndex="142">
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" ><font class="fontColor" >Middle Name</font></label>
         </div>
         <div class="col-md-3">
            <input id="nomDtlMname" type="text" class="textOnly" style="width: 173px" tabIndex="143">
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-3" >
            <label for="contact" class="mandatory"><font class="fontColor">Last Name</font></label>
         </div>
         <div class="col-md-3" >
            <input id="nomDtlLname" type="text" class="textOnly"  style="width: 173px" tabIndex="144">
         </div>
      </div>
   </div>
   <div class="row" style="padding: 5px">
      <div class="col-md-3">
         <div class="col-md-3">
            <label for="contact" class="mandatory"><font class="fontColor" >Relation</font></label>		
         </div>
         <div class="col-md-5">
            <select id="nomDtlRelation" style="width: 175px" tabIndex="145">
               <option>--Select--</option>
            </select>
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" class="mandatory"><font class="fontColor" >Aadhar No</font></label>	
         </div>
         <div class="col-md-3">
            <input id="nomDtlAdharNo" type="text" style="width: 175px" tabIndex="146"  class="validateAadhar">
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" class="mandatory"><font class="fontColor" >Date Of Birth</font></label>		
         </div>
         <div class="col-md-3">
            <input id="nomDtlDOB"   placeholder="dd/mm/yyyy" tabIndex="147" type="text" style="width: 175px">
         </div>
      </div>
   </div>
   <div class="row" style="padding: 5px">
       <div class="checkbox">
         <label> <input type="checkbox" id="nomDtlCheckBox" style="margin-left: 20px" tabIndex="148" onclick="copyNomineeAddress();"><font class="fontColor">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Address Same As Insured Address
         </font></label>
      </div>
   </div>
   <div class="row" style="padding: 5px">
      <div class="col-md-3">
         <div class="col-md-3">
            <label for="contact" class="mandatory"><font class="fontColor">Country</font></label>		
         </div>
         <div class="col-md-5">
            <select id="nomDtlCountry" style="width: 175px" onchange="nomDtlState()" tabIndex="149">
               <option>--Select--</option>
            </select>
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" class="mandatory"><font class="fontColor">State</font></label>
         </div>
         <div class="col-md-3">
            <select id="nomDtlState" style="width: 175px" onchange="nomDtlDistrict()" tabIndex="150">
               <option>--Select--</option>
            </select>
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" class="mandatory"><font class="fontColor">District</font></label>
         </div>
         <div class="col-md-3">
            <select id="nomDtlDistrict" style="width: 175px"  onchange="nomDtlCity()" tabIndex="151">
               <option>--Select--</option>
            </select>
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-3">
            <label for="contact" class="mandatory"><font class="fontColor">City</font></label>
         </div>
         <div class="col-md-3">
            <select id="nomDtlCity" style="width: 175px" onchange="nomDtlPincode()" tabIndex="152">
               <option>--Select--</option>
            </select>
         </div>
      </div>
   </div>
   <div class="row" style="padding: 5px">
      <div class="col-md-3" style="padding: 10px">
         <div class="col-md-3" style="margin-left: 2px">
            <label for="contact" style="margin-left: 3px" class="mandatory"><font class="fontColor">Pincode</font></label>		
         </div>
         <div class="col-md-5">
            <select id="nomDtlPincode"  onchange="nomDtlLandmark()" style="width: 175px"  tabIndex="153">
               <option>--Select--</option>
            </select>
         </div>
      </div>
      <div class="col-md-3" style="padding: 10px">
         <div class="col-md-4" style="margin-left: 2px">
            <label for="contact" style="margin-left: 3px" class="mandatory"><font class="fontColor">Landmark</font></label>
         </div>
         <div class="col-md-3">
               <input id="nomDtlLandmark" onchange="nomDtlStreet()" list="nomDtlLandmarkList" type="text"
            	 style="width: 173px" tabIndex="154">
			<datalist id="nomDtlLandmarkList"></datalist>
         </div>
      </div>
      <div class="col-md-3" style="padding: 10px">
         <div class="col-md-4" style="margin-left: 2px">
            <label for="contact" style="margin-left: 3px"><font class="fontColor">Street</font></label>
         </div>
         <div class="col-md-3">
                <input id="nomDtlStreet" onchange="nomDtlHouseNo()" list="nomDtlStreetList" type="text"tabIndex="155"
            	 style="width: 173px">
			<datalist id="nomDtlStreetList"></datalist>
         </div>
      </div>
      <div class="col-md-3" style="margin-top: 5px">
         <div class="col-md-3">
            <label for="contact" style="margin-bottom: 4px"><font class="fontColor">House No</font></label>
         </div>
         <div class="col-md-3" style="margin-top: 4px">
                  <input id="nomDtlHouseNo"  list="nomDtlHouseNoList" type="text" tabIndex="156"
            	 style="width: 173px">
			<datalist id="nomDtlHouseNoList"></datalist>
         </div>
      </div>
   </div>
   <div class="row" style="padding: 5px">
      <div class="col-md-3">
         <div class="col-md-3">
            <label for="contact" class="mandatory"><font class="fontColor">Email</font></label>		
         </div>
         <div class="col-md-5">
           <input id="nomDtlEmail"  placeholder="abc@abc.com"  type="text"style="width: 173px"
                class="validateEmail" tabIndex="157">
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" class="mandatory"><font class="fontColor">Phone no</font></label>
         </div>
         <div class="col-md-3">
            <input id="nomDtlphoneNo" class="validatePhone" type="text"style="width: 173px" tabIndex="158">
         </div>
      </div>
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact" class="mandatory"><font class="fontColor">Mobile</font></label>
         </div>
         <div class="col-md-3">
            <input id="nomDtlMobileNo" class="validateMobile" type="text"style="width: 173px"  tabIndex="159" class="validateMobile"
               >
         </div>
      </div>
   </div>
 <div class="modal-footer">
      
        <div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
       
         <!-- <button type="button" class="btn btn-primary"
            >OK</button> -->
         <button type="button" class="btn btn-primary" onclick="clearNomineeDetails();" id="clearNomineeDetails" tabIndex="160" 
            >Clear Nominee Details</button>
         <!-- <button type="button" class="btn btn-primary"
            data-dismiss="modal">Cancel</button> -->
     
      </div>
   </div>
</div>
</div>
	<!-- ---------------	Nominee Details Code Ends Here -------------------------	 -->

<div id="insuredBankDtlDiv" style="display:none">
			<div class="panel-title accordianColor" >
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseInsuredBankDetails" aria-expanded="true"
					aria-controls="collapseOne"> Insured Bank Details </a>
			</div>
			<br>
			<div id="collapseInsuredBankDetails" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">

				<br>

				<div class="row" style="width: 640px">
					<div class="col-md-3">
						<label for="bankName"><font class="fontColor">* Bank
								Name</font></label>

					</div>
					<div class="col-md-9">
					<select id="insBankDtlBankName" onchange="getBankDtlBranchName();"
						 style="width: 400px;">
						<option>--Select--</option>
					</select>
					</div>
				
					<div class="col-md-3">
						<label for="branchName"><font class="fontColor">*
								Branch Name</font></label>

					</div>
					<div class="col-md-9">
						<select id="insBankDtlBranchName" 
						style="width:400px;">
						<option>--Select--</option>
					</select>
					</div>
					
					<div class="col-md-3">
						<label for="accno"><font class="fontColor">* Account
								No</font></label>
					</div>
					<div class="col-md-9">
						<input id="insBankDtlAccNo" type="text" size=40">
					</div>
					<div class="col-md-3">
						<label for="ifsccode"><font class="fontColor">* IFSC
								Code</font></label>
					</div>
					<div class="col-md-9">
						<input id="insBankDtlifscCode" type="text" size=40">
					</div>
					<div class="col-md-3">
						<label for="othrdetails"><font class="fontColor">*
								Other Details</font></label>
					</div>
					<div class="col-md-9">
						<input id="insBankDtlOtherDetails" type="text" size=40">
					</div>
				</div>


				<div class="modal-footer">
					<div class="col-md-1"></div>
					<div class="col-md-1"></div>
					<div class="col-md-1"></div>
					<div class="col-md-1"></div>
					<div class="col-md-1"></div>
					<div class="col-md-1">
						<button type="button" class="btn btn-primary" >Submit</button>
					</div>

					<div class="col-md-1">
						<button type="button" class="btn btn-primary"
							style="margin-left: 4px;" onclick="clearInsuredBankDtls();" >Clear</button>
					</div>
					<div class="col-md-1">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>

</div>


		<!-- inspection Details Starts Here-->
	<div id="inspectionDtlDiv" style="display:none">
			<div class="row" style="width: 1165px; margin-left: 2px" >
				<div class="panel-title accordianColor" >
					&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
						data-parent="#accordion" href="#collapseInspectionDetails" aria-expanded="true"
						aria-controls="collapseOne"> Inspection Details </a>
				</div>
				<br>
				<div id="collapseInspectionDetails" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
					style="margin: auto;">
										
				<div class="row">
				
	
				<div class="row" style="padding: 10px;margin-left: 2px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="contactPersonName"><font class="fontColor">
										Contact Person Name</font></label>
				</div>
				<div class="col-xs-12 col-md-7 col-sm-7 col-lg-7">
					<input id="inspDtlContactPerName" type="text" style="width:613px;"  tabIndex="171" class="textOnly">
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="contctno"><font class="fontColor">
										Contact No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="inspDtlContactNo" type="text" size="30" tabIndex="172" class="validateMobile">
				</div>
			</div>
			
			<div class="col-md-6" style="padding: 0px; margin-top: 15px; margin-left: 30px;">
			<label for="vehlocation"><font class="fontColor" size="3px"><b>Current&nbsp
										Vehicle &nbsp Location : </b></font></label>
			</div>
			
			<br><br><br><br>
			
			 <div class="row" style="padding: 10px; margin-top: -18px;margin-left: 2px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="State"><font class="fontColor"> State</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="inspectionStateTF" onchange="inspectionCity();" tabIndex="173"
						 style="width: 220px;">
						<option>--Select--</option>
						</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="City"><font class="fontColor"> City</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="inspectionCityTF" onchange="inspectionPincode();"tabIndex="174"
						 style="width: 220px;">
						<option>--Select--</option>
						</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="Pincode"><font class="fontColor">
										Pincode</font></label>			
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="inspectionPincodeTF" onchange="inspectionLandmark(); "tabIndex="175"
						 style="width: 220px;">
						<option>--Select--</option>
						</select>
				</div>
			</div>
				
						
			<div class="row" style="padding: 10px; margin-top: 10px; margin-left: 2px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="Landmark"><font class="fontColor">
										Landmark</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="inspectionLandmarkTF" onchange="inspectionStreet()" tabIndex="176" list="inspectionLandmarkList" type="text"
			            		  style="width: 220px;">
							<datalist id="inspectionLandmarkList"></datalist>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="Street"><font class="fontColor">
										Street</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<input id="inspectionStreetTF" onchange="inspectionhouseNo()" tabIndex="177" list="inspectionStreetList" type="text"
			            		  style="width: 220px;">
							<datalist id="inspectionStreetList"></datalist>
				</div>

				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="house"><font class="fontColor"> House
										No/Name</font></label>		
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="inspectionHouseTF" onchange="" list="inspectionHouseList" type="text" tabIndex="178"
			            		  style="width: 220px;">
							<datalist id="inspectionHouseList"></datalist>
				</div>
			</div>
			<div class="row" style="padding: 10px;  margin-left: 2px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="insAgency"><font class="fontColor">
										Inspection Agency</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="inspectionAgencyTF" onchange="inspectionSurveyorName();" tabIndex="179"
						 style="width: 220px;">
						<option>--Select--</option>
						</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="surv"><font class="fontColor">
										Surveyor Name</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
					<select id="inspectionSurveyorNameTF" onchange="inspectionSurveyorStatus();" tabIndex="180"
						 style="width: 220px;">
						<option>--Select--</option>
						</select>
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="survNo"><font class="fontColor">
										Surveyor Contact No</font></label>	
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="inspDtlSurveyorContactNo" type="text" style="width: 220px;" tabIndex="181" class="validateMobile">
				</div>
			</div>
			<div class="row" style="padding: 10px;  margin-left: 2px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="survNo"><font class="fontColor">
										Survey/<br>Inspection No</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<input id="inspDtlSurvInspNo" type="text" style="width: 220px;" tabIndex="182">
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="insdate"><font class="fontColor">
										Inspection Alloted Date</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3  col-lg-3">
				<div id="inspDtlInspAllotedDate" tabIndex="183">
				</div>
					<!-- <input id="inspDtlInspAllotedDate" tabIndex="183" class="datepicker" placeholder="Select Date" type="text"  style="height: 26px;width: 220px;""> -->
				</div>
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="surveyins"><font class="fontColor">
										Survey/ Inspection Status</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3">
					<select id="inspectionSurveyorStatus" onchange="" tabIndex="184"
						 style="width: 220px;">
						<option>--Select--</option>
						</select>
				</div>
		</div>
		<div class="row" style="padding: 10px;  margin-left: 2px;">
				<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
					<label for="narr"><font class="fontColor">
										Narration</font></label>
				</div>
				<div class="col-xs-12 col-md-3 col-sm-3 col-lg-3" style="width:690px;">
					<!-- <input id="narration" type="text" style="width: 220px;"> -->
					<textarea id="inspDtlNarration" rows="1" style="width:615px;height:40px;resize: none" tabIndex="185"></textarea>
				</div><div class="col-xs-12 col-md-1 col-sm-1 col-lg-1" style="width: 115px;">
					<label for="insdate2"><font class="fontColor">Inspection Date</font></label>
				</div>
				<div id="inspectionDate" placeholder="dd/mm/yyyy" tabIndex="186" type="text" style="width: 220px; height: 26px;" class="numberOnly">
        		</div>
				<!-- <div id="inspectionDate" class="col-xs-12 col-md-3 col-sm-3 col-lg-3"> -->
				 <!-- <input  class="datepicker" type="text" style="width: 190px; height: 25px;" tabIndex="186">   -->
				<!-- </div> -->
				
		</div>
					
			     <div class="modal-footer">
      		<div class="col-xs-12 col-md-1 col-sm-1 col-lg-1">
        		 <button type="button" class="btn btn-primary" onclick="clearInspectionDetails()"
									style="margin-right: 30px;" tabIndex="187">Clear Inspection Deatils</button>

      			 </div>
   				 </div>			
			</div>
		</div>
	</div>
</div>

<!-- inspection Details Ends Here-->


		<div id="backPapersDiv" style="display:none">
			<div class="panel-title accordianColor" >
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseBackPaper" aria-expanded="true"
					aria-controls="collapseOne"> Back Papers </a>
			</div>
			<br>
			<div id="collapseBackPaper" class="panel-collapse collapse" role="tabpanel"
				aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				<div class="div2 div4"></div>
			</div>
		</div>
		
		<!--finance changes -->
		<div id="VehicleFinDtlDiv" style="display:none">
			<div class="panel-title accordianColor" >
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseVehicleFinanceDetails" aria-expanded="true"
					aria-controls="collapseOne"> Vehicle Finance Details </a>
			</div>
			<br>
			<div id="collapseVehicleFinanceDetails" class="panel-collapse collapse"
				role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				<div>					
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-1">
							<label for="finType"><font class="fontColor">*
									Finance Type</font></label>

						</div>
						<div class="col-md-3">
							<select id="finDtlFinType" onchange="getFinanceBy();" tabIndex="191"  style="width: 250px;">
						<option>--Select--</option>
					</select>
						</div>
						<div class="col-md-1">
							<label for="finBy"><font class="fontColor">*
									Finance By</font></label>

						</div>
						<div class="col-md-3">
							<select id="finDtlFinBy" onchange="getFinanceName();" tabIndex="192"  style="width: 250px;">
						<option>--Select--</option>
						</select>
						</div>
						<div class="col-md-1">
							<label for="finName"><font class="fontColor">*Finance
									Name</font></label>
						</div>
						<div class="col-md-3">
							<select id="finDtlFinName" onchange="getFinanceState();" tabIndex="193" style="width: 250px;">
						<option>--Select--</option>
						</select>
						</div>
						</div>
						<div class="col-md-12">
						<div class="col-md-1">
							<label for="finState"><font class="fontColor">*Finance
									State</font></label>
						</div>
						<div class="col-md-3">
							<select id="finDtlFinState" onchange="getFinanceCity();"   tabIndex="194" style="width: 250px;">
						<option>--Select--</option>
						</select>
						</div>
						<div class="col-md-1">
							<label for="finCity"><font class="fontColor">*
									Finance City</font></label>
						</div>
						<div class="col-md-3">
							<select id="finDtlFinCity"   style="width: 250px;" tabIndex="195">
						<option>--Select--</option>
						</select>
						</div>
						<br><br>
						<div class="col-md-1">
							<button type="button" class="btn btn-primary" style="margin-left:-490px;" tabIndex="196" onclick="clearVehicleFinDtls();">Clear Finance Details</button>
						</div>
						</div>
					</div>
					

					<!-- <div class="modal-footer">
						<div class="col-md-1"></div>
						<div class="col-md-1"></div>
						<div class="col-md-1"></div>
						<div class="col-md-1"></div>
						<div class="col-md-1"></div>
						<div class="col-md-1">
							<button type="button" class="btn btn-primary">Submit</button>
						</div>

						<div class="col-md-1">
							<button type="button" class="btn btn-primary"
								style="margin-left: 4px;">Clear</button>
						</div>
						<div class="col-md-1">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Close</button>
						</div>
					</div> -->


				</div>
			</div>
			<hr style="color:white;">
			<div class="row" style="padding: 5px">
							<div class=" col-md-3"></div>
							<div class=" col-md-3"></div>
							<div class=" col-md-3"></div>
							<div class=" col-md-3">
							<button type="button" id="previewCompId"  data-target="#previewCompany" tabIndex="197"  onclick="getCompanyPreview('');" class="btn btn-primary"  class="fontColor" >Preview Details</button>
							<button type="button" class="btn btn-primary"  onclick="getProposalResponse();" tabIndex="198" id="getProposal" style="width:120px;">Submit</button>			
					</div>
								
						</div>
						
			<!-- <br> <br> &nbsp;&nbsp;&nbsp;&nbsp; -->
			</div>
		
		<!-- 
		finance changes -->
		
		<!-- proposalDetailsDiv -->
		
		
		<div id="proposalDetailsDiv" style="display:none">
	<div class="panel-title run1 accordianColor" >
				&nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
					data-parent="#accordion" href="#collapseProposalDetails"
					aria-expanded="true" aria-controls="collapseOne"> Proposal Details
					 </a>
			</div>

			<br>
			<div id="collapseProposalDetails" class="panel-collapse collapse run"
				role="tabpanel" aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">
				<div class="main wrapper2" style="margin: auto; overflow-x:hidden;overflow-y:hidden; height:100px;">
					<div class="div2 div4">
						<table id="proposalDetails" class="table" border="1" width="100" style="margin: auto;">
					<tr style="background-color: #dac8b6;">
						<th>Sr No</th>
						<th>Proposal No</th>
						<th>Quotation No</th>
						<th>Product Name</th>
						<th>Risk Start Date</th>
						<th>Insured Name</th>
						<th>Payment Amount</th>
					</tr>
				</table>
					</div>
				</div>
				
				 <div class="modal-footer">
      <div class="col-md-7"></div>
      <div class="col-md-5">
        <label><font class="fontColor"> Amount To Be Paid </font></label>
					 <input id="premAmount" name="Amount" placeholder="" type="text" readonly>
			 <button id="b1" type="button"  data-target="#shreeRamPay" class="btn btn-primary" tabIndex="200" style="margin-top: 0px; height: 30px" class="fontColor" >Pay</button>
				 
      </div>
   </div>
			</div>
		
			</div>
		
			<!-- proposalDetailsDiv -->
		
			<!-- ---------------	payment options Code Starts Here -------------------------	 -->
	<div id="paymentOptionsDiv" style="display:none">
		<div class="panel-title paymentDtl1 accordianColor" >
   &nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
      data-parent="#accordion" href="#collapsePaymentOptions" aria-expanded="true"
      aria-controls="collapseOne">Payment Options  </a>
</div>
<br>
<div id="collapsePaymentOptions" class="panel-collapse collapse paymentDtl" role="tabpanel"
   aria-labelledby="headingOne" class="main wrapper2"
   style="margin: auto;">
   <div class="row" style="padding: 5px">
      <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact"><font class="fontColor">Payment Type</font></label>	
         </div>
       <div class="col-md-3">
            <input id="paymenttype" type="text" style="width: 173px" readonly>
         </div>
      </div>
      <div class="col-md-9">
         <div class="col-md-3">
            <label for="contact"><font class="fontColor">Payment Mode</font></label>
         </div>
         <div class="col-md-3">
          <div id="relExtPay" style="display:none"><label><input id="paymentModeCD" type="radio" name="payMode"style="width: 12px"  value="CD">
          <font class="fontColor">CD</font></label>&nbsp;&nbsp;
         <label><input id="paymentModeCDT" type="radio" name="payMode"><font class="fontColor" value="CDT">CDT</font></label>&nbsp;&nbsp;
         </div>
         <div id="onlinePay" style="display:none">
         <label><input id="paymentModeOnline" type="radio" name="payMode"><font class="fontColor"  value="Online">Online</font></label>&nbsp;&nbsp;
         </div>
         <div id="cardPay" style="display:none">
         <label><input id="paymentModeCreditCard" type="radio" name="payMode"><font class="fontColor"  value="CreditCard">Credit Card</font></label>&nbsp;&nbsp;
         <label><input id="paymentModeDebitCard" type="radio" name="payMode"><font class="fontColor"  value="DebitCard">Debit Card</font></label>
        </div>
        <div id="creditcardPay" style="display:none">
         <label><input id="payModeCreditCard" type="radio" name="payMode"><font class="fontColor"  value="CreditCard">Credit Card</font></label>&nbsp;&nbsp;
        </div>
         </div>
           <div class="col-md-3">
         <div class="col-md-4">
            <label for="contact"><font class="fontColor">Amount Paid</font></label>	
         </div>
         <div class="col-md-3">
         <input id="amountPaid" type="text" style="width: 173px" readonly>
          </div>
      </div>
      </div>
    
   </div>
   <div class="row" style="padding: 5px">
      <div class="col-md-3" id="cdtBal" style="display:none">
         <div class="col-md-3">
            <label><font class="fontColor">Available CDT balance</font></label>		
         </div>
         <div class="col-md-5">
           <input id="availableCDTbalance" type="text" style="width: 173px" >
         </div>
      </div>
    
      <div class="col-md-3" id="remainingBal" style="display:none">
         <div class="col-md-5">
            <label for="contact"><font class="fontColor">Balance After Transaction</font></label>		
         </div>
         <div class="col-md-3">
             <input id="balanceAfterTransaction" type="text" style="width: 173px" readonly>
         </div>
      </div>
   </div>
   <div class="main wrapper2" style="margin: auto;overflow-x:hidden;overflow-y:hidden;height:100px;">
			<div class="div2 div4">
				<table id="paymentDetails" class="table" border="1" width="100" style="margin: auto;">
					<tr style="background-color: #dac8b6;">

						<th>Sr No</th>
						<th>Payer</th>
						<th>Mode of Payment</th>
						<th>Instrument Date</th>
						<th>Instrument Number</th>
						<th>Amount to be Paid</th>
						<th>Account Number</th>
					</tr>
				</table>
			</div>
		</div>
   <div class="modal-footer">
      <div class="col-md-7"></div>
      <div class="col-md-5">
         <button type="button" class="btn btn-primary" onclick="makePayment();" id="submitDtls"
                         >Submit Details</button>
      </div>
   </div>
</div>
</div>
	<!-- ---------------	Payment Opion Code Ends Here -------------------------	 -->
		
		
<div id="paymentDetailsDiv" style="display:none;height:200px;">
<div class="panel-title paymentDtl1 accordianColor" >
   &nbsp;&nbsp; <a class="collapsed" data-toggle="collapse"
      data-parent="#accordion" href="#collapsePaymentDetails" aria-expanded="true"
      aria-controls="collapseOne">Payment
			Details </a>
</div>
<br>
<div id="collapsePaymentDetails" class="panel-collapse collapse paymentDtl" role="tabpanel"
   aria-labelledby="headingOne" class="main wrapper2"
   style="margin: auto;">
   
		<div class="main wrapper2" style="margin: auto; overflow-x:hidden;overflow-y:hidden;height:100px;">
			<div class="div2 div4">
				<table id="paymentStatus" class="table" border="1" width="100" style="margin: auto;">
					<tr style="background-color: #dac8b6;">

						<th>Sr No</th>
						<th>Message</th>
						<th>Proposal No</th>
						<th>Policy Number</th>
						<th>Transaction Number</th>
					</tr>
				</table>
			</div>
		</div>
		<button type="button" class="btn btn-primary" onclick="getInsurancePolicy();"
			style="margin-top: 15px;height: 30px;margin-left:1050px;">Get
			Policy</button>
			<br>
</div>
		
</div>
 </div>
	
</body>

</html>