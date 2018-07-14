$(document).ready(function (){
	reLoadValidation();
	applyMandatory();
});

function disableFields(selector){
	$(selector).prop("disabled",true);
	$(selector).attr("disabled",true);
	$(selector).addClass("disabledbutton");
}

function hideFields(selector){
	$(selector).hide();
	$(selector).css("display","none");
	reLoadValidation();
}

function showFields(selector){
	$(selector).show();
	$(selector).css("display","block");
	reLoadValidation();
}

function enableFields(selector){
	$(selector).prop("disabled",false);
	$(selector).attr("disabled",false);
	$(selector).removeClass("disabledbutton");
}

function clearFields(selector){
	$(selector).val("");
	$(selector).select2("val","");
}

function validateFormArray(selector){
	for(var i=0;i<selector.length;i++){
		var tagName = $(selector[i]).prop("tagName");
		if(tagName == "input" || tagName == "INPUT"){
			var value = $(selector[i]).val();
			var isVisible = $(selector[i]).is(":visible");
			if(value=="" && isVisible){
				old_alert($(selector[i-1]).html() + " cannot be empty!!");
				setTimeout(function(){$(selector[i]).focus()},1);
				return false;
			}
		}
	}
	return true;
}

function validateForm(element){
	var selector = element.getElementsByClassName("mandatory");
	for(var i=0;i<selector.length;i++){
		var tagName = $(selector[i]).prop("tagName");
		if(tagName == "input" || tagName == "INPUT"){
			var value = $(selector[i]).val();
			var isVisible = $(selector[i]).is(":visible");
			if(value=="" && isVisible){
				old_alert($(selector[i-1]).html() + " cannot be empty!!");
				setTimeout(function(){$(selector[i]).focus()},1);
				return false;
			}
		}
	}
	return true;
}
function applyMandatory(){
	var selector = document.getElementsByClassName("mandatory");
	
	for(var i=0;i<selector.length;i++){
		var tagName = $(selector[i]).prop("tagName");
		if(tagName == "label" || tagName == "LABEL"){
			var value = $(selector[i]).html();
			if(!value.includes("*")){
				$(selector[i]).html("<font size=\"4\" color=\"#337ab7\">*</font>"+value);
			}
		}
	}
}

function reLoadValidation(){
	
	var compo = $('[tabIndex=1]')[0];
	setTimeout(function(){$(compo).focus();},1);
	setTimeout(function(){$(compo).select2("focus");},1);
	
	$('input').on('keypress', function(e) {
		var cnt = 1;
		var typeComp= $('[tabIndex=' + (this.tabIndex) + ']')[0];
		var type=$(typeComp).attr("type");
		var comp = $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0];
		var isVisible = $(comp).is(":visible");
		var firstComp = $('[tabIndex=1]')[0];
		
		if(e.which == 13 && firstComp != undefined){
//			console.log("comp::::"+comp);
//			console.log("comp::::"+(comp == undefined));
			while(comp == undefined || !isVisible){
//				console.log("type::::"+type);
				cnt = cnt + 1;
				comp = $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0];
				isVisible = $(comp).is(":visible");
				
				if(type == "button"){
//					console.log("type::::"+type);
					break;
				}
			}
//			console.log("cnt:::"+cnt);
			e.which !== 13 || $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0].focus();
			e.which !== 13 || $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0].select2("focus");
		}
    });
	
	$('select').on('keypress', function(e) {
		var cnt = 1;
		var comp = $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0];
		var isVisible = $(comp).is(":visible");
		
		var firstComp = $('[tabIndex=1]')[0];
		
		if(e.which == 13 && firstComp != undefined){
			console.log("comp::::"+comp);
			console.log("comp::::"+(comp == undefined));
			while(comp == undefined || !isVisible){
				cnt = cnt + 1;
				comp = $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0];
				isVisible = $(comp).is(":visible");
			}
			console.log("cnt:::"+cnt);
			e.which !== 13 || $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0].focus();
	     //   e.which !== 13 || $('[tabIndex=' + (+this.tabIndex + cnt) + ']')[0].select2("focus");
		}
    });
	
	
	$('.validateEmail').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = $("#"+id).val();
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(!value.match(mailformat) && value!=""){
			old_alert("Please Enter Valid Mail Id!!");
			//setTimeout(function(){$("#"+id).val("").focus();},1);
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}
	});
	
	$('.validateMobile').bind('keypress', function (event) {
    	var s = event.keyCode || event.which;
	    var regex = new RegExp("^[0-9]+$");
	    
	    var id = $(this).attr("id");
		var value = $("#"+id).val();
		
	    if(value.length<1){
	    	regex = new RegExp("^[789]+$");
	    }else{
	    	regex = new RegExp("^[0-9]+$");
	    }
	    
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key) && s != "8" && s != "9"  && s!="37" && s != "38" && s!="40") {
	       event.preventDefault();
	       return false;
	    }else if(s == "8" || s == "9" || s=="37" || s=="38" || s=="40"){
	    	return true;
	    }
	    
	   
		
		if(value.length>=10){
			event.preventDefault();
			return false;
		}else{
			return true;
		}
	});
	
	$('.validateAadhar').bind('keypress', function (event) {
    	var s = event.keyCode || event.which;
	    var regex = new RegExp("^[0-9]+$");
	    
	    var id = $(this).attr("id");
		var value = $("#"+id).val();

		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key) && s != "8" && s != "9"  && s!="37" && s != "38" && s!="40") {
	       event.preventDefault();
	       return false;
	    }else if(s == "8" || s == "9" || s=="37" || s=="38" || s=="40"){
	    	return true;
	    }
		
		if(value.length>=12){
			event.preventDefault();
			return false;
		}else{
			return true;
		}
	});
	
	$('.validatePan').bind('keypress', function (event) {
    	var s = event.keyCode || event.which;
	    var regex = new RegExp("^[0-9]+$");
	    
	    var id = $(this).attr("id");
		var value = $("#"+id).val();
		
		if(value.length<=4){
			regex = new RegExp("^[A-Za-z]+$");
		}else if(value.length<=8){
			regex = new RegExp("^[0-9]+$");
		}else if(value.length>8){
			regex = new RegExp("^[A-Za-z]+$");
		}

		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key) && s != "8" && s != "9"  && s!="37" && s != "38" && s!="40") {
	    	event.preventDefault();
	    	return false;	
	    }else if(s == "8" || s == "9" || s=="37" || s=="38" || s=="40"){
	    	return true;
	    }
		
		if(value.length>=10){
			event.preventDefault();
			return false;
		}else{
			return true;
		}
	});
	
	$('.validatePan').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = $("#"+id).val();
		
		$("#"+id).val(value.toUpperCase());
		
		if(value.length<10 && value.length>0){
			old_alert("Please Enter Valid PAN No.!!");
			//setTimeout(function(){$("#"+id).val("").focus();},1);
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}else{
			return true;
		}
		
	});
	
	$('.validatePhone').bind('keypress', function (event) {
    	var s = event.keyCode || event.which;
	    var regex = new RegExp("^[0-9]+$");
	    
	    var id = $(this).attr("id");
		var value = $("#"+id).val();

		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key) && s != "8" && s != "9"  && s!="37" && s != "38" && s!="40") {
	       event.preventDefault();
	       return false;
	    }else if(s == "8" || s == "9" || s=="37" || s=="38" || s=="40"){
	    	return true;
	    }
		
		if(value.length>=11){
			event.preventDefault();
			return false;
		}else{
			return true;
		}
	});
	
	$('.validatePhone').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = $("#"+id).val();
		
		$("#"+id).val(value.toUpperCase());
		
		if(value.length<11 && value.length>0){
			old_alert("Please Enter Valid Phone No.!!");
			//setTimeout(function(){$("#"+id).val("").focus();},1);
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}else{
			return true;
		}
		
	});
	
	$('.validateAadhar').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = $("#"+id).val();
		
		$("#"+id).val(value.toUpperCase());
		
		if(value.length<12 && value.length>0){
			old_alert("Please Enter Valid Aadhar No.!!");
			//setTimeout(function(){$("#"+id).val("").focus();},1);
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}else{
			return true;
		}
	});
	
	$('.validateAadharEnrol').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = $("#"+id).val();
		
		$("#"+id).val(value.toUpperCase());
		
		if(value.length<28 && value.length>0){
			old_alert("Please Enter Valid Aadhar Enrollment No.!!");
			//setTimeout(function(){$("#"+id).val("").focus();},1);
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}else{
			return true;
		}
	});
	
	$('.validateMobile').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = $("#"+id).val();
		
		$("#"+id).val(value.toUpperCase());
		
		if(value.length<10 && value.length>0){
			old_alert("Please Enter Valid Mobile No.!!");
			//setTimeout(function(){$("#"+id).val("").focus();},1);
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}else{
			return true;
		}
		
	});
	
	$('.validateAadharEnrol').bind('keypress', function (event) {
    	var s = event.keyCode || event.which;
	    var regex = new RegExp("^[0-9]+$");
	    
	    var id = $(this).attr("id");
		var value = $("#"+id).val();

		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key) && s != "8" && s != "9"  && s!="37" && s != "38" && s!="40") {
	    	console.log("keycode:::"+s);
	       event.preventDefault();
	       return false;
	    }else if(s == "8" || s == "9" || s=="37" || s=="38" || s=="40"){
	    	console.log("keycode:::"+s);
	    	return true;
	    }
		
		if(value.length>=28){
			event.preventDefault();
			return false;
		}else{
			return true;
		}
	});
	
	$('.textOnly').bind('keypress', function (event) {
		var s = event.keyCode || event.which;
	    var regex = new RegExp("^[A-Za-z]+$");
	    
		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key) && s != "8" && s != "9") {
	       event.preventDefault();
	       return false;
	    }else if(s == "8" || s == "9"){
	    	return true;
	    }
		
	});
	
	$('.tblBiofuelKit').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = parseFloat($("#"+id).val());
		var exShowroomPrc=parseFloat($("#exshowroomprice").val()); 
		if(value>exShowroomPrc){
			old_alert("Bio Fuel Kit Value Must Not Be Greater Than ExShowRoom Price");
			//setTimeout(function(){$("#"+id).val("").focus();},1);
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}else{
			return true;
		}
	});
	
	$('.fiveDigitValidation').bind('blur', function (event) {
		var id = $(this).attr("id");
		var value = $("#"+id).val();
		console.log("here onchange"+value.length);
		if(value.length < 5 && value.length>0 ){
			old_alert("Minimum 5 digits required");
			$("#"+id).val("");
			setTimeout(function(){$("#"+id).focus();},1);
			return false;
		}else{
			return true;
		}
	});
	
	$('.numberOnly').bind('keypress', function (event) {
		var s = event.keyCode || event.which;
		var regex = new RegExp("^[0-9]+$");
		
		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		if (!regex.test(key) && s != "8" && s != "9") {
			event.preventDefault();
			return false;
		}else if(s == "8" || s == "9"){
			return true;
		}
	});
	
	$('.alphaNumericOnly').bind('keypress', function (event) {
		var s = event.keyCode || event.which;
		var regex = new RegExp("^[a-zA-Z0-9\s]+$");
		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		if (!regex.test(key) && s != "8" && s != "9" && s != "45"&& s != "32") {
			event.preventDefault();
			return false;
		}else if(s == "8" || s == "9" || s == "45"|| s == "32"){
			return true;
		}
	});
	 
	 $('.numberOnlyWithDecimal').bind('keypress', function (event) {
			var s = event.keyCode || event.which;
			var regex = new RegExp("^[0-9]+$");
			
			var id = $(this).attr("id");
			var value = $("#"+id).val();
			var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
			var floatVal = parseFloat(value);
			
			if (s == 46 && value.indexOf(".") !== -1) {
		        return false;
		    } 
			
			if (!regex.test(key) && s != "8" && s != "9" && s != "46") {
				event.preventDefault();
				return false;
			}else if(s == "8" || s == "9" || s == "46"){
				return true;
			}
			
			if (value.indexOf(".") !== -1)
		    {
				 var v = parseFloat(value);
				    if (isNaN(v)) {
				    	 this.value = '';
		        }
		        else
		        {
		        	console.log("value--"+value+" v------"+v);
		            var number =value.split('.');
		            if (number.length == 2 && number[1].length > 1)
		                return false;
		        }
		    }
			
		});
	
	

	$(".minorValidation").bind('blur',function(event){
		var id = $(this).attr("id");
		var regex = new RegExp("^[dmyDMY]+$");
		var value = $("#"+id).val();
		
		if(!regex.test(value)&&value!=""){
			var BDate = value.split("/");
			 
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
			if(age<18){
				old_alert("Insurer Age Must Be 18 Or 18+");
				return false;
			}else{
				return true;
			}
		}
	});
}

function calcInsDtlAge(dobId){
	console.log("dobId-->>"+dobId);
	var birthDate=$('#'+dobId+'').val();
	console.log("birthDate-->>"+birthDate);
	var regex = new RegExp("^[dmyDMY]+$");
	if(!regex.test(birthDate)&&birthDate!=""){
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
	if(age<18){
		old_alert("Insurer Age Must Be 18 Or 18+");
		return false;
	}else{
		return true;
	}
	}
}

