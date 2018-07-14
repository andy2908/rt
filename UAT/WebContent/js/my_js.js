// Validating Empty Field
function check_empty() {
if (document.getElementById('name').value == "" || document.getElementById('email').value == "" || document.getElementById('msg').value == "") {
alert("Fill All Fields !");
} else {
document.getElementById('form').submit();
alert("Form Submitted Successfully...");
}
}
//Function To Display Popup Finance
function div_show() {
document.getElementById('abc').style.display = "block";
}
//Function to Hide Popup
function div_hide(){
document.getElementById('abc').style.display = "none";
}



//Function To Display Popup Bank Details
function div_show1() {
document.getElementById('abcB').style.display = "block";
}
//Function to Hide Popup
function div_hide1(){
document.getElementById('abcB').style.display = "none";
}

//Function To Display Popup Inspection Details
function div_show2() {
	document.getElementById('abcI').style.display = "block";
	}
	//Function to Hide Popup
	function div_hide2(){
	document.getElementById('abcI').style.display = "none";
	}

	
	//Function To Display Popup Nominee Details
	function div_show3() {
		document.getElementById('abcN').style.display = "block";
		}
		//Function to Hide Popup
		function div_hide3(){
		document.getElementById('abcN').style.display = "none";
		}
		
		//Function To Display Popup insured Details
		function div_show4() {
			document.getElementById('abcIS').style.display = "block";
			}
			//Function to Hide Popup
			function div_hide4(){
			document.getElementById('abcIS').style.display = "none";
			}

