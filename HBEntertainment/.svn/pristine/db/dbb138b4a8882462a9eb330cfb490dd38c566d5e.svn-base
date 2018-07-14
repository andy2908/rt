<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.main {
	height: 200px;
	overflow-x: scroll;
}
</style>

<link rel="stylesheet" type="text/css" href="css/select2.css">
<script type="text/javascript" src="js/select2.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/productDetails.js"></script>
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<link rel="stylesheet" href="css/datepicker3.css">
<link rel="stylesheet" href="css/sb-admin-2.css">
<script src="js/dataTables.select.min.js"></script>
<script src="js/validation.js"></script>
<style type="text/css">
#tb2 {
	counter-reset: serial-number; /* Set the serial number counter to 0 */
}

.container-fluid {
	padding-right: 0px;
	padding-left: 0px;
	margin-right: auto;
	margin-left: auto;
}

.select2-container .select2-choice {
	border: 1px solid #b3a7a7;
}

#tb2 td:first-child:before {
	counter-increment: serial-number;
	/* Increment the serial number counter */
	content: counter(serial-number); /* Display the counter */
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Master</title>
<script>
	$(document)
			.ready(
					function() {
						$("#editdata").hide();
						$("#deleteData").hide();

						$('#example').DataTable({
							"scrollX" : true
						});
						$(
								"#venueTypeSelect,#venueSelect,#venueStructureSelect,#countrySelect,#stateSelect,#districtSelect,#citySelect,#pincodeSelect")
								.select2({
									placeholder : "--Select--",
									allowClear : true,
									maximumSelectionSize : 1,
								});

						$('input[type="file"]').change(
								function(e) {
									var fileName = e.target.files[0].name;
									alert('The file "' + fileName
											+ '" has been selected.');
								});

						populateVenueTypeSelect();
						populateCountrySelect();
						getSeatType();
						reLoadValidation();

					});
	function showname(id, s) {
		var name = document.getElementById(s);
		document.getElementById(id).value = name.files.item(0).name;
	}

	function openfileDialog() {
		$("#fileLoader").click();
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

	function populateVenueTypeSelect() {
		$("#venueTypeSelect").empty();
		$("#venueTypeSelect").append('<option value="">--Select--');
		var resp = getRecordList("2001", "null");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#venueTypeSelect").append(
					'<option value="'+resp[key].VENUE_TYPE_ID+'"> '
							+ resp[key].VENUE_TYPE_NAME + '');
		});
	}
	function populateVenueSelect() {
		var venueType = $("#venueTypeSelect").val();

		$("#venueSelect").empty();
		$("#venueSelect").append('<option value="">--Select--');
		var resp = getRecordList("2004", venueType);
		$
				.each(
						resp,
						function(key, value) {
							nKey = parseInt(key) + 1;
							$("#venueSelect")
									.append(
											'<option phn="'+resp[key].PHONE_NO+'" mbl1="'+resp[key].MOBILE1_NO + '" mbl2="'+resp[key].MOBILE2_NO  +'" email="'+resp[key].EMAIL_ID+'" website="'+resp[key].WEBSITE+'" country="'+resp[key].COUNTRY_ID+'" state="'+resp[key].STATE_ID+'" district="'+resp[key].DISTRICT_ID+'" cityy="'+resp[key].TEHSIL_ID+'" pincode="'+resp[key].AREA_ID+'" vstr="'+resp[key].VENUE_STRUCTURE+'" totalSeat="'+resp[key].TOTAL_SEATS+'" land="'+resp[key].LANDMARK_NAME+'" lan_id="'+resp[key].LANDMARK_ID+'" street="'+resp[key].STREET_NAME+'" street_id="'+resp[key].STREET_ID+'" house="'+resp[key].HOUSE_NAME+'" house_id="'+resp[key].HOUSE_ID+'"  value="'+resp[key].VENUE_HEAD_ID+'"  value="'+resp[key].VENUE_ID+'" >'
													+ resp[key].VENUE_NAME + '');
						});
	}

	function rowNoType() {
		var seatTypeId = $('.seatTypeSelect option:selected').attr('value');
		var venueSelect = $("#venueSelect").val();
		venueSelect = venueSelect.toLowerCase();
		if (venueSelect.includes("select")) {
			venueSelect = "null";
		}
		$("#rowNo").empty();
		$("#rowNo").append('<option value="">--Select--');
		var resp = getRecordList("2005", venueSelect);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#rowNo").append(
					'<option value="'+resp[key].ROW_NO+'"row_no="'+resp[key].ROW_NO+'"> '
							+ resp[key].ROW_NO + '');
		});

	}

	function getValues() {

		var row_no = $('#rowNo option:selected').attr('row_no');
		var street_id = $('#venueSelect option:selected').attr('street_id');
		var house_id = $('#venueSelect option:selected').attr('house_id');
		var lan_id = $('#venueSelect option:selected').attr('lan_id');
		var venueHeadId = $('#venueSelect option:selected').attr('value');
		var vstr = $('#venueSelect option:selected').attr('vstr');
		var totalSeat = $('#venueSelect option:selected').attr('totalSeat');
		var phn = $('#venueSelect option:selected').attr('phn');
		var mobile1 = $('#venueSelect option:selected').attr('mbl1');
		var mobile2 = $('#venueSelect option:selected').attr('mbl2');
		var email = $('#venueSelect option:selected').attr('email');
		var website = $('#venueSelect option:selected').attr('website');
		var land = $('#venueSelect option:selected').attr('land');
		var street = $('#venueSelect option:selected').attr('street');
		var house = $('#venueSelect option:selected').attr('house');
		var country = $('#venueSelect option:selected').attr('country');
		var state = $('#venueSelect option:selected').attr('state');
		var district = $('#venueSelect option:selected').attr('district');
		var city = $('#venueSelect option:selected').attr('cityy');
		var pincode = $('#venueSelect option:selected').attr('pincode');

		$("#row_no").val(row_no);
		$("#mobileNoTf").val(mobile1);
		$("#mobileNoTf").val(mobile2);
		$("#phn").val(phn);
		$("#venueHeadId").val(venueHeadId);

		$("#totalSeatTf").val(totalSeat);
		$("#venueStructureSelect").select2("val", vstr);
		$("#websiteTf").val(website);

		populateCountrySelect();
		$("#countrySelect").select2("val", country);

		populateStateSelect();
		$("#stateSelect").select2("val", state);

		populateDistrictSelect();
		$("#districtSelect").select2("val", district);

		populateCitySelect();
		$("#citySelect").select2("val", city);

		populatePincodeSelect();
		$("#pincodeSelect").select2("val", pincode);
		populateLandmarkSelect();
		$("#landmarkTf").val(land);
		populateHouseNoSelect();
		$("#houseNoTf").val(house);
		populateStreetSelect();
		$("#streetTf").val(street);
	}

	function getSeatType() {

		$(".seatTypeSelect").empty();
		$(".seatTypeSelect").append('<option value="">--Select--');
		var resp = getRecordList("2002", "null");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$(".seatTypeSelect").append(
					'<option value="'+resp[key].SEAT_TYPE_ID+'"> '
							+ resp[key].SEAT_TYPE_NAME + '');
		});
	}

	function fromSeatType(element) {
		//var venueType = $("#venueTypeSelect").val();
		var venueTypeId = $("#venueSelect").val();
		var tb = document.getElementById("tb2");

		var rowNoSelect = $(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[1]
						.getElementsByTagName('select')).val();
		var total = venueTypeId + "~" + rowNoSelect;

		$(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[2]
						.getElementsByTagName('select')).empty();
		$(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[2]
						.getElementsByTagName('select')).append(
				'<option value="">--Select--');
		var resp = getRecordList("2006", total);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$(
					tb.rows[element.parentNode.parentNode.rowIndex].cells[2]
							.getElementsByTagName('select')).append(
					'<option value="'+resp[key].SEAT_NO+'"> '
							+ resp[key].SEAT_NO + '');
		});
	}

	function toSeatType(element) {
		var venueTypeId = $("#venueSelect").val();
		//var venueType = $("#venueTypeSelect").val();
		var tb = document.getElementById("tb2");
		var rowNoSelect = $(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[1]
						.getElementsByTagName('select')).val();
		var total = venueTypeId + "~" + rowNoSelect;
		$(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[3]
						.getElementsByTagName('select')).empty();
		$(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[3]
						.getElementsByTagName('select')).append(
				'<option value="">--Select--');

		var resp = getRecordList("2006", total);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$(
					tb.rows[element.parentNode.parentNode.rowIndex].cells[3]
							.getElementsByTagName('select')).append(
					'<option value="'+resp[key].SEAT_NO+'"> '
							+ resp[key].SEAT_NO + '');

		});
	}

	function SeatDiffType(element) {
		var tb = document.getElementById("tb2");
		var from = $(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[2]
						.getElementsByTagName('select')).val();
		var to = $(
				tb.rows[element.parentNode.parentNode.rowIndex].cells[3]
						.getElementsByTagName('select')).val();
		if (to < from) {
			alert("To seat shold be greater than From seat.");
		} else {
			var diffST = ((to) - (from)) + 1;
			$(
					tb.rows[element.parentNode.parentNode.rowIndex].cells[6]
							.getElementsByTagName('input')).val(diffST);
		}
	}

	$(function() {
		// 			 rowNoType();
		getSeatType();
		$('#addMore2')
				.on(
						'click',
						function() {

							/* $("#fromS").prop('disabled', false);
							$("#toS").prop('disabled', false); */

						var id = $('#rowNo option:selected')
							.text();
						//	console.log("rowNo======>"+id);
							
							var data = $("#tb2 tr:eq(2)").clone(true).appendTo(
									"#tb2");
							data.find("input").val('');
							var tb = document.getElementById("tb2");
							var ctb = tb.rows.length - 1;

							$(
									tb.rows[ctb].cells[2]
											.getElementsByTagName('select'))
									.prop('disabled', false);
							$(
									tb.rows[ctb].cells[3]
											.getElementsByTagName('select'))
									.prop('disabled', false);

							var venueSelect = $("#venueSelect").val();
							$(
									tb.rows[ctb].cells[1]
											.getElementsByTagName('select'))
									.empty();
							$(
									tb.rows[ctb].cells[1]
											.getElementsByTagName('select'))
									.append('<option value="">--Select--');
							var resp = getRecordList("2005", venueSelect);
							$
									.each(
											resp,
											function(key, value) {
												nKey = parseInt(key) + 1;
												$(
														tb.rows[ctb].cells[1]
																.getElementsByTagName('select'))
														.append(
																'<option value="'+resp[key].ROW_NO+'"> '
																		+ resp[key].ROW_NO
																		+ '');
											});

							$(
									tb.rows[ctb].cells[4]
											.getElementsByTagName('select'))
									.empty();
							$(
									tb.rows[ctb].cells[4]
											.getElementsByTagName('select'))
									.append('<option value="">--Select--');

							resp = getRecordList("2002", "null");
							$
									.each(
											resp,
											function(key, value) {
												nKey = parseInt(key) + 1;
												$(
														tb.rows[ctb].cells[4]
																.getElementsByTagName('select'))
														.append(
																'<option value="'+resp[key].SEAT_TYPE_ID+'"> '
																		+ resp[key].SEAT_TYPE_NAME
																		+ '');
											});

							// $("#tdiff").prop('disabled', false); 
							/*  
							 $('#fromS').disable(false);
							 $('#toS').disable(false);
							 $('#tdiff').disable(false);  */

						});

		$(document).on('click', '.remove', function() {
			var trIndex = $(this).closest("tr").index();
			if (trIndex > 2) {
				$(this).closest("tr").remove();
			} else {

			}
		});

	});

	function reloadTabIndex() {
		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;

		var cnt = 50;

		for (var i = 2; i < ctb; i++) {
			$(tb.rows[i].cells[1].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[2].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[3].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[4].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[5].getElementsByTagName('select')).attr(
					"tabIndex", cnt++);
			$(tb.rows[i].cells[7].getElementsByTagName('input')).attr(
					"tabIndex", cnt++);
		}
		reLoadValidation();
	}

	function populateCountrySelect() {
		$("#countrySelect").empty();
		$("#countrySelect").append('<option value="">--Select--');
		var resp = getRecordList("95", "null");
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#countrySelect").append(
					'<option value="'+resp[key].COUNTRY_ID+'"> '
							+ resp[key].COUNTRY_NAME + '');
		});
	}
	function populateStateSelect() {
		var country = $("#countrySelect").val();
		$("#stateSelect").empty();
		$("#stateSelect").append('<option value="">--Select--');
		var resp = getRecordList("96", country);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#stateSelect").append(
					'<option value="'+resp[key].STATE_ID+'"> '
							+ resp[key].STATE_NAME + '');
		});
	}
	function populateDistrictSelect() {
		var country = $("#countrySelect").val();
		var state = $("#stateSelect").val();
		$("#districtSelect").empty();
		$("#districtSelect").append('<option value="">--Select--');
		var resp = getRecordList("97", country + "~" + state);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#districtSelect").append(
					'<option value="'+resp[key].DISTRICT_ID+'"> '
							+ resp[key].DISTRICT_NAME + '');
		});
	}

	function populateCitySelect() {
		var country = $("#countrySelect").val();
		var state = $("#stateSelect").val();
		var district = $("#districtSelect").val();
		$("#citySelect").empty();
		$("#citySelect").append('<option value="">--Select--');
		var resp = getRecordList("98", country + "~" + state + "~" + district);
		$.each(resp, function(key, value) {
			nKey = parseInt(key) + 1;
			$("#citySelect").append(
					'<option value="'+resp[key].CITY_ID+'"> '
							+ resp[key].CITY_NAME + '');
		});
	}
	function populatePincodeSelect() {
		var country = $("#countrySelect").val();
		var state = $("#stateSelect").val();
		var district = $("#districtSelect").val();
		var city = $("#citySelect").val();
		$("#pincodeSelect").empty();
		$("#pincodeSelect").append('<option value="">--Select--');
		var resp = getRecordList("100", country + "~" + state + "~" + district
				+ "~" + "null" + "~" + city);
		$
				.each(
						resp,
						function(key, value) {
							nKey = parseInt(key) + 1;
							$("#pincodeSelect")
									.append(
											'<option  pincodeCA="' + resp[key].PINCODE + '" value="' + resp[key].AREA_ID + '"> '
													+ resp[key].AREA_NAME + '');

						});

	}

	function populateLandmarkSelect() {
		var areaId = $("#pincodeSelect").val();
		var resp = getRecordList("109", 1 + "~" + areaId);
		$("#landmarkList").empty();
		$("#landmarkList").append('<option rel="" value="">--Select--');
		$
				.each(
						resp,
						function(key, value) {
							nKey = parseInt(key) + 1;
							$("#landmarkList")
									.append(
											'<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
						});
	}

	function populateStreetSelect() {
		landmarkId = ($("#landmarkList option[value='" + $('#landmarkTf').val()
				+ "']").attr('rel'));
		var resp = getRecordList("109", 2 + "~" + landmarkId);
		$
				.each(
						resp,
						function(key, value) {
							nKey = parseInt(key) + 1;
							$("#streetList")
									.append(
											'<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'"> ');
						});
	}

	function populateHouseNoSelect() {
		var streetId = $('#venueSelect option:selected').attr('street_id');
		var resp = getRecordList("109", 3 + "~" + streetId);

		$("#houseNoList").empty();
		$("#houseNoList").append('<option rel="" value="">--Select--');
		$
				.each(
						resp,
						function(key, value) {
							nKey = parseInt(key) + 1;
							$("#houseNoList")
									.append(
											'<option rel="'+resp[key].ADD_MST_ID+'" value="'+resp[key].ADD_NAME+'">');
						});
	}

	function checkFileUpload(element) {
		var $file = element;

		if ($file.files.length > 0) {
			for (var i = 0; i < $file.files.length; i++) {
				var size = $file.files[i].size;
				if (parseInt(size) > 5242880) {
					$("#" + element.getAttribute("id")).val(null);
					alert("File Size Greater Than 5 MB is not allowed!!\nPlease Reduce File Upload Size.");
				}
			}
		}
	}

	function clearData() {
		clearFields("#eventNameTf,#phoneNoTf,#mobileNoTf,#websiteTf,#emailIDTf,#landmarkTf,#streetTf,#navigatorImage,#houseNoTf,#totalSeatTf,#seatStrTf,#rowNo,#fromS,#toS,#seatType,#seatStatuss,#venueMap,#addressTf,#tdiff");
		document.getElementById("eventNameTf").focus();

		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;
		for (var i = ctb; i > 2; i--) {
			$("#tb2 tr:eq(" + i + ")").remove();
		}

		ctb = tb.rows.length - 1;

		$(tb.rows[ctb].cells[1].getElementsByTagName('select')).empty();
		$(tb.rows[ctb].cells[1].getElementsByTagName('select')).append(
				'<option value="">--Select--');
		$(tb.rows[ctb].cells[1].getElementsByTagName('select')).val("");

		$(tb.rows[ctb].cells[2].getElementsByTagName('select')).empty();
		$(tb.rows[ctb].cells[2].getElementsByTagName('select')).append(
				'<option value="">--Select--');
		$(tb.rows[ctb].cells[2].getElementsByTagName('select')).val("");

		$(tb.rows[ctb].cells[3].getElementsByTagName('select')).empty();
		$(tb.rows[ctb].cells[3].getElementsByTagName('select')).append(
				'<option value="">--Select--');
		$(tb.rows[ctb].cells[3].getElementsByTagName('select')).val("");

		$(tb.rows[ctb].cells[4].getElementsByTagName('select')).empty();
		$(tb.rows[ctb].cells[4].getElementsByTagName('select')).append(
				'<option value="">--Select--');
		$(tb.rows[ctb].cells[4].getElementsByTagName('select')).val("");

		$(tb.rows[ctb].cells[5].getElementsByTagName('select')).empty();
		$(tb.rows[ctb].cells[5].getElementsByTagName('select')).append(
				'<option value="">--Select--');
		$(tb.rows[ctb].cells[5].getElementsByTagName('select')).val("");

		$(tb.rows[ctb].cells[6].getElementsByTagName('input')).val("");
		$(tb.rows[ctb].cells[7].getElementsByTagName('input')).val("");

		$("#venueTypeSelect").select2('val', null);
		$("#venueSelect").select2('val', null);
		$("#venueStructureSelect").select2('val', null);
		$("#nationalitySelect").select2('val', null);
		$("#countrySelect").select2('val', null);
		$("#stateSelect").select2('val', null);
		$("#districtSelect").select2('val', null);
		$("#citySelect").select2('val', null);
		$("#pincodeSelect").select2('val', null);
	}

	function venueList2() {
		$(".modal-body").empty();
		$(".modal-body")
				.append(
						"<table id='venueList123' class='table' border='1'style=' width: 100%;'><thead class='th'><tr style='background-color: #dac8b6;'><th >Sr No</th><th>Event Head Id</th><th>Event Name</th><th>Total Seat</th><th>City</th><th>Pin code</th><th>Edit</th></tr></thead><tbody></tbody></table>");

		$("#vnlist").html("Event List");
		$
				.ajax({
					url : "${pageContext.request.contextPath}/getRecordLst",
					type : 'post',
					dataType : 'json',
					async : false,
					data : {
						sqlMstId : 2007,
						param : "null",
					},
					success : function(data) {
						volTable = $("#venueList123")
								.DataTable(
										{
											'data' : data,

											'columnDefs' : [
													{
														'targets' : 6,
														'searchable' : false,
														'orderable' : false,
														'className' : 'dt-body-center',
														'render' : function(
																data, type,
																full, meta) {
															return '<img src="images/edit.png" id="editImage" data-dismiss="modal" style="width:20px" onclick="edittext();edittextTable();topFunction()">';
														}
													}, ],

											"columns" : [ {
												"data" : null
											}, {
												"data" : "EVENT_HEAD_ID"
											}, {
												"data" : "EVENT_NAME"
											}, {
												"data" : "TOTAL_SEATS"
											}, {
												"data" : "TEHSIL"
											}, {
												"data" : "PINCODE"
											}, {
												"data" : null
											}, ],

											select : {
												style : 'single'
											},
											'destroy' : true,
											"lengthMenu" : [ 5, 10, 15, 20 ],
											"pageLength" : 5,
											"bInfo" : false,
											"order" : [ [ 1, 'asc' ] ]
										});

						volTable.on('order.dt search.dt', function() {
							volTable.column(0, {
								search : 'applied',
								order : 'applied'
							}).nodes().each(function(cell, i) {
								cell.innerHTML = i + 1;
							});
						}).draw();

					},
					error : function() {
						alert("error");
					}

				});
	}

	function edittext() {

		var table = $('#venueList123').DataTable();
		$('#venueList123 tbody').on(
				'click',
				'tr',
				function() {
					var tableData = $(this).children('td').map(function() {
						return $(this).text();
					}).get();

					var eventHeadid = $.trim(tableData[1]);
					var eventName = $.trim(tableData[2]);

					var tb = document.getElementById("tb2");
					var ctb = tb.rows.length - 1;

					$(tb.rows[ctb].cells[2].getElementsByTagName('select'))
							.prop('disabled', true);
					$(tb.rows[ctb].cells[3].getElementsByTagName('select'))
							.prop('disabled', true);

					var resp = getRecordList("2010", eventHeadid);
					$.each(resp, function(key, value) {
						nKey = parseInt(key) + 1;

						console
								.log("EDITTEXT====eventHeadid1==>"
										+ eventHeadid);
						console.log("EDITTEXT====eventName==>" + eventName);
						var EVENT_NAME = resp[key].EVENT_NAME
						var PHONE_NO = resp[key].PHONE_NO
						var MOBILE1_NO = resp[key].MOBILE1_NO
						//	var MOBILE2_NO=resp[key].MOBILE2_NO
						var EMAIL_ID = resp[key].EMAIL_ID
						var WEBSITE = resp[key].WEBSITE
						var TOTAL_SEATS = resp[key].TOTAL_SEATS
						var ADDRESS = resp[key].ADDRESS
						var ADDRESS_ID = resp[key].ADDRESS_ID
						var AREA_ID = resp[key].AREA_ID
						var LANDMARK_ID = resp[key].LANDMARK_ID
						var LANDMARK_NAME = resp[key].LANDMARK_NAME
						var STREET_NAME = resp[key].STREET_NAME
						var HOUSE_NAME = resp[key].HOUSE_NAME
						var PINCODE = resp[key].PINCODE
						var TEHSIL_ID = resp[key].TEHSIL_ID
						var TEHSIL = resp[key].TEHSIL
						var DISTRICT_ID = resp[key].DISTRICT_ID
						var DISTRICT = resp[key].DISTRICT
						var STATE_ID = resp[key].STATE_ID
						var STATENAME = resp[key].STATENAME
						var COUNTRY_ID = resp[key].COUNTRY_ID
						var COUNTRY = resp[key].COUNTRY
						var VENUE_STRUCTURE = resp[key].VENUE_STRUCTURE
						var VENUE_TYPE_ID = resp[key].VENUE_TYPE_ID
						var VENUE_ID = resp[key].VENUE_ID
						var VENUE_TYPE_NAME = resp[key].VENUE_TYPE_NAME
						var VENUE_NAME = resp[key].VENUE_NAME

						$("#eventNameTf").val(EVENT_NAME);
						$("#phoneNoTf").val(PHONE_NO);
						$("#mobileNoTf").val(MOBILE1_NO);
						$("#emailIDTf").val(EMAIL_ID);
						$("#websiteTf").val(WEBSITE);

						$("#totalSeatTf").val(TOTAL_SEATS);
						$("#landmarkTf").val(LANDMARK_NAME);
						$("#streetTf").val(STREET_NAME);
						$("#houseNoTf").val(HOUSE_NAME);
						$("#addressTf").val(ADDRESS);
						
						
						$("#venueTypeSelect").select2("val", VENUE_TYPE_ID);
						populateVenueSelect();
						$("#venueSelect").select2("val", VENUE_ID);
						$("#venueStructureSelect").select2("val", VENUE_STRUCTURE);

						populateCountrySelect();
						$('#countrySelect').select2('data', {
							allowClear : true,
							id : COUNTRY_ID,
							text : COUNTRY,
						});

						populateStateSelect();
						$('#stateSelect').select2('data', {
							allowClear : true,
							id : STATE_ID,
							text : STATENAME,
						});

						populateDistrictSelect();
						$('#districtSelect').select2('data', {
							allowClear : true,
							id : DISTRICT_ID,
							text : DISTRICT,
						});

						populateCitySelect();
						$('#citySelect').select2('data', {
							allowClear : true,
							id : TEHSIL_ID,
							text : TEHSIL,
						});

						populatePincodeSelect();
						$('#pincodeSelect').select2('data', {
							allowClear : true,
							id : AREA_ID,
							text : PINCODE,
						});
						
						
					});

				});

	}

	
	function edittextTable() {

		var table = $('#venueList123').DataTable();
		$('#venueList123 tbody')
				.on(
						'click',
						'tr',
						function() {
							var tableData = $(this).children('td').map(function() {
										return $(this).text();
									}).get();

							eventHeadid = $.trim(tableData[1]);
							var eventName = $.trim(tableData[2]);

							$("#eventHeadid").val(eventHeadid);

							var resp = getRecordList("2011", eventHeadid);
							$.each(resp,function(key, value) {
												
								nKey = parseInt(key) + 1;

											
								console.log("EDITTEXTABLE====eventHeadid==>"+ eventHeadid);
								console.log("EDITTEXTABLE====eventName==>"+ eventName);

								var EVENT_LINE_ID = resp[key].EVENT_LINE_ID;
								var ROW_NO = resp[key].ROW_NO;
								var SEAT_NO = resp[key].SEAT_NO;
								var ROW_WISE_SEAT_STRUCTURE = resp[key].ROW_WISE_SEAT_STRUCTURE;
								var SEAT_TYPE_NAME = resp[key].SEAT_TYPE_NAME;
								var SEAT_TYPE_ID = resp[key].SEAT_TYPE_ID;
								var SEAT_STATUS = resp[key].SEAT_STATUS;
								var PI_STR_ROW_NO = resp[key].PI_STR_ROW_NO;

								rowNoType();

								$('#rowNo option:selected').text(ROW_NO);
								console.log("rowNo===>"+ $('#rowNo option:selected').text());

								$("#seatStrTf").val(ROW_WISE_SEAT_STRUCTURE);
								console.log("seatStructure===>"+ $('#seatStrTf').val());

								$("#seatStatuss").val(SEAT_STATUS);
								console.log("seatStatuss===>"+ $('#seatStatuss').val());

								getSeatType();
								$('#seatType option:selected').text(SEAT_TYPE_NAME);
								console.log("seattype===>"+ $('#seatType option:selected').text());
								$('#seatType').val(SEAT_TYPE_ID);
								console.log("seattypeId===>"+ $('#seatType').val());

						});

							$("#editdata").show();
							$("#deleteData").show();
							$("#savedata").hide();

						});

	}

	function saveDataList() {
		
		
		var venueStucture = $("#venueStructureSelect");		
		var stat = validateFormArray(['#eventNameTf']);
		
       
		if(stat){
			
			 if(!$('#venueTypeSelect').val()){
				alert("Please select venue Type");
				$("#venueTypeSelect").select2('focus');
				return false;
			}else if(!$('#venueSelect').val()){
				alert("Please select venue");
				$("#venueSelect").select2('focus');
				$("#venueSelect").focus();
				return false;
			}else if(!$('#emailIDTf').val()){
				alert("Please enter emailId");
				$("#emailIDTf").focus();
			}else if(!$('#websiteTf').val()){
				alert("Please enter website");
				$("#websiteTf").focus();
			}else if(!$('#phoneNoTf').val()){
				alert("Please enter phone_no");
				$("#phoneNoTf").focus();
			}else if(!$('#mobileNoTf').val()){
				alert("Please enter mobile_no");
				$("#mobileNoTf").focus();
			}else if(venueStucture.val() == "") {
	            alert("Please select Venue Stucture!");
	            $('#venueStructureSelect').focus();
	            return false;
	        }else if(!$('#totalSeatTf').val()){
				alert("Please enter total_seat");
				$("#totalSeatTf").focus();
			}else if(!$('#venueMap').val()) {
				alert("Please Select Venue Map");
				$('#venueMap').focus();
				return false;
			}else if(!$('#navigatorImage').val()) {
				alert("Please Select navigator Image");
				$('#navigatorImage').focus();
				return false;
			}else if (!$('#countrySelect').val()) {
				alert("Please Select Country.");
			 	$('#countrySelect').select2('focus'); 
				return false;
			}else if (!$('#stateSelect').val()) {
				alert("Please Select State.");
			 	$('#stateSelect').select2('focus'); 
				return false;
			}else if (!$('#districtSelect').val()) {
				alert("Please Select District.");
			 	$('#districtSelect').select2('focus'); 
				return false;
			}else if (!$('#citySelect').val()) {
				alert("Please Select City.");
			 	$('#citySelect').select2('focus'); 
				return false;
			}else if (!$('#pincodeSelect').val()) {
				alert("Please Select Pincode.");
			 	$('#pincodeSelect').select2('focus'); 
				return false;
			}else if (!$('#landmarkTf').val()) {
				alert("Please Enter Landmark.");
				$('#landmarkTf').focus();
			}else if (!$('#streetTf').val()) {
				alert("Please Enter Street.");
				$('#streetTf').focus(); 
				return false;
			}else if (!$('#houseNoTf').val()) {
				alert("Please Enter HouseNo.");
				$('#houseNoTf').focus(); 
				return false;
			}else if (!$('#addressTf').val()) {
				alert("Please Enter Address.");
				$('#addressTf').focus(); 
				return false;
			}
		}
		
		
		var tb = document.getElementById("tb2");
		var ctb = tb.rows.length - 1;

		var cnt = 50;

		var rowNoStr = "";
		var seatFromStr = "";
		var seatToStr = "";
		var seatTypeStr = "";
		var seatStatusStr = "";
		var noofSeatStr = "";
		var seatStrucStr = "";
		var srNoStr ="";

		for (var i = 2; i <= ctb; i++) {
			var rowNo = $(tb.rows[i].cells[1].getElementsByTagName('select'))
					.val();
			var seatFrom = $(tb.rows[i].cells[2].getElementsByTagName('select'))
					.val();
			var seatTo = $(tb.rows[i].cells[3].getElementsByTagName('select'))
					.val();
			var seatType = $(tb.rows[i].cells[4].getElementsByTagName('select'))
					.val();
			var seatStatus = $(
					tb.rows[i].cells[5].getElementsByTagName('select')).val();
			var noofSeat = $(tb.rows[i].cells[6].getElementsByTagName('input'))
					.val();
			var seatStruc = $(tb.rows[i].cells[7].getElementsByTagName('input'))
					.val();
			
			var srno = "1";

			if (rowNo != "" && seatFrom != "" && seatTo != "" && seatType != ""
					&& seatStatus != "" && noofSeat != "" && seatStruc != "") {
				rowNoStr = rowNoStr + rowNo + "~";
				seatFromStr = seatFromStr + seatFrom + "~";
				seatToStr = seatToStr + seatTo + "~";
				seatTypeStr = seatTypeStr + seatType + "~";
				seatStatusStr = seatStatusStr + seatStatus + "~";
				noofSeatStr = noofSeatStr + noofSeat + "~";
				seatStrucStr = seatStrucStr + seatStruc + "~";
				srNoStr = srNoStr + srno + "~";
			}
		}

		console.log("rowNoStr:::" + rowNoStr);
		if (rowNoStr.split("~").length > 1) {
			var stat = true;
		} else {
			alert("Please Enter Alteast One Row in Seat Layout!");
			var stat = false;
		}

		if (stat) {
			var street_id = $('#venueSelect option:selected').attr('street_id');
			var house_id = $('#venueSelect option:selected').attr('house_id');
			var lan_id = $('#venueSelect option:selected').attr('lan_id');
			var venueHeadId = $('#venueSelect option:selected').attr('value');
			var event_name = $("#eventNameTf").val();
			var phone_no = $("#phoneNoTf").val();
			var mobile_no_1 = $("#mobileNoTf").val();
			var mobile_no_2 = $("#mobileNoTf").val();
			var email_id = $("#emailIDTf").val();
			var website = $("#websiteTf").val();
			var venueType = $("#venueTypeSelect").val();
			var venueTypeId = $("#venueSelect").val();
			var venueStucture = $("#venueStructureSelect").val();

			venueStuctuc = "";
			if (venueStucture == "Fixed") {
				venueStuctuc = "F";
			} else {
				venueStuctuc = "C";
			}

			var totalSeats = $("#totalSeatTf").val();
			var navigatorImage = $('#navigatorImage').val();
			var areaId = $("#pincodeSelect").val();
			var house = $("#houseNoTf").val();
			var houseId = $('#venueSelect option:selected').attr('house_id');
			var street = $("#streetTf").val();
			var streetId = ($("#streetList option[value='"+ $('#streetTf').val() + "']").attr('rel'));
			var landmark = $("#landmarkTf").val();
			var landmarkId = ($("#landmarkList option[value='"+ $('#landmarkTf').val() + "']").attr('rel'));
			var srno = "1";
			var venueHeadeId = $('#venueSelect option:selected').attr('value');
			var address = $("#addressTf").val();

			var pg_tran_no = "";
			var pg_tran_date = "";
			var book_by_user_id = "";
			var book_by_name = "";
			var agent_id = "";
			var pg_amount = "";

			var pg_train_status = "";
			$formData = new FormData();

			var $file6 = document.getElementById('selectedFile');

			if ($file6.files.length > 0) {
				for (var i = 0; i < $file6.files.length; i++) {
					$formData.append("selectedFile", $file6.files[i]);
				}
			}

			var $file7 = document.getElementById('selectedFile1');

			if ($file7.files.length > 0) {
				for (var i = 0; i < $file7.files.length; i++) {
					$formData.append("selectedFile1", $file7.files[i]);
				}
			}

			$formData.append("opflag", "N");
			$formData.append("common_tron", "0");
			$formData.append("event_head_id", "0");
			$formData.append("event_name", event_name);
			$formData.append("phone_no", phone_no);
			$formData.append("mobile_no_1", mobile_no_1);
			$formData.append("mobile_no_2", mobile_no_1);
			$formData.append("email_id", email_id);
			$formData.append("website", website);
			$formData.append("venue_head_id", venueHeadeId);
			$formData.append("venue_structure", venueStucture);
			$formData.append("total_seats", totalSeats);
			/* $formData.append("venue_map",""); */
			$formData.append("navigation_img", navigatorImage);
			$formData.append("adr_name", address);
			$formData.append("area_id", areaId);
			$formData.append("house", house);
			$formData.append("house_id", house_id);
			$formData.append("street", street);
			$formData.append("street_id", street_id);
			$formData.append("landmark", landmark);
			$formData.append("landmark_id", lan_id);
			$formData.append("srNo", srNoStr);
			$formData.append("row_no", rowNoStr);
			$formData.append("from_seat", seatFromStr);
			$formData.append("to_seat", seatToStr);
			$formData.append("seat_status", seatStatusStr);
			$formData.append("seat_type_id", seatTypeStr);
			$formData.append("row_seat_str", seatStrucStr);
			$formData.append("online_tran_id", "");
			$formData.append("pg_train_status", pg_train_status);
			$formData.append("pg_amount", pg_amount);
			$formData.append("pg_train_status", pg_amount);
			$formData.append("pg_tran_date", pg_train_status);
			$formData.append("pg_tran_no", pg_tran_no);
			$formData.append("pg_tran_date", pg_tran_date);
			$formData.append("pg_tran_time", "");
			$formData.append("book_by_user_id", "");
			$formData.append("book_by_name", "");
			$formData.append("book_person_mobileNo", "");
			$formData.append("agent_id", "");
			$formData.append("branch_id", "");
			$formData.append("str_id", "");

			$
					.ajax({
						url : '${pageContext.request.contextPath}/user/saveEventMaster',
						type : 'POST',
						data : $formData,
						dataType : 'json',
						async : false,
						contentType : false,
						processData : false,
						success : function(resp) {

							alert("Record Submit Successfully");
							clearData();
						},
						error : function(resp) {
							alert(resp);

						},
					});
		}

	}

	function updateData() {

		
		if (!$("#venueMap").val()) {
			alert("Please Select Venue Map");
			$("#venueMap").selcet2('focus');
			return false;
		}

		if (!$("#navigatorImage").val()) {
			alert("Please Select Navigator Image")
			$("#navigatorImage").select2('focus');
		}

		var stat = validateFormArray([ '#eventNameTf', '#phoneNoTf',
				'#mobileNoTf', '#websiteTf', '#emailIDTf', '#venueTypeSelect',
				'#totalSeatTf', '#venueMap', '#venueSelect', '#countrySelect',
				'#stateSelect', '#districtSelect', '#citySelect',
				'#pincodeSelect', '#streetTf', '#houseNoTf', '#selectedFile',
				'#nationalitySelect' ]);

		var tb = document.getElementById("tb2");

		console.log("tb====>" + tb);
		var ctb = tb.rows.length - 1;
		console.log("ctb====>" + ctb);
		var cnt = 50;

		var rowNoStr = "";
		var seatFromStr = "";
		var seatToStr = "";
		var seatTypeStr = "";
		var seatStatusStr = "";
		var noofSeatStr = "";
		var seatStrucStr = "";
		var srnoStr = "";
		

		for (var i = 2; i <= ctb; i++) {
			var rowNo = $(tb.rows[i].cells[1].getElementsByTagName('select'))
					.val();
		
			var seatFrom = $(tb.rows[i].cells[2].getElementsByTagName('select'))
					.val();
			var seatTo = $(tb.rows[i].cells[3].getElementsByTagName('select'))
					.val()
			var seatType = $(tb.rows[i].cells[4].getElementsByTagName('select'))
					.val();
			var seatStatus = $(tb.rows[i].cells[5].getElementsByTagName('select'))
			        .val();
			var noofSeat = $(tb.rows[i].cells[6].getElementsByTagName('input'))
					.val();
			var seatStruc = $(tb.rows[i].cells[7].getElementsByTagName('input'))
					.val();
		
			var srno = "1";
					
			
			if (rowNo != "" && seatFrom != "" && seatTo != "" && seatType != ""
					&& seatStatus != "" && noofSeat != "" && seatStruc != "") {
				rowNoStr = rowNoStr + rowNo + "~";
				console.log("Update Row_No========>" + rowNoStr);
				seatFromStr = seatFromStr + seatFrom + "~";
				console.log("Update Seat_From========>" + seatFromStr);
				seatToStr = seatToStr + seatTo + "~";
				console.log("Update Seat_to========>" + seatToStr);
				seatTypeStr = seatTypeStr + seatType + "~";
				console.log("Update Seat_Type===>" + seatTypeStr);
				seatStatusStr = seatStatusStr + seatStatus + "~";
				console.log("Update Seat_Status===>" + seatStatusStr);
				noofSeatStr = noofSeatStr + noofSeat + "~";
				seatStrucStr = seatStrucStr + seatStruc + "~";
				console.log("Update Seat_Structure===>" + seatStrucStr);
				srnoStr = srnoStr + srno + "~";
				
			}
		}

		console.log("rowNoStr:::" + rowNoStr);
		if (rowNoStr.split("~").length > 1) {
			var stat = true;
		} else {
			alert("Please Enter Alteast One Row in Seat Layout!");
			var stat = false;
		}

		if (stat) {

			var eventHeadeId = $("#eventHeadid").val();
			var event_name = $("#eventNameTf").val();
			var phone_no = $("#phoneNoTf").val();
			var mobile_no_1 = $("#mobileNoTf").val();
			var mobile_no_2 = $("#mobileNoTf").val();
			var email_id = $("#emailIDTf").val();
			var website = $("#websiteTf").val();
			var venueHeadeId = $('#venueSelect option:selected').attr('value');
			var venueStucture = $("#venueStructureSelect").val();
			var totalSeats = $("#totalSeatTf").val();
			var address = $("#addressTf").val();
			var areaId = $("#pincodeSelect").val();
			var house = $("#houseNoTf").val();
			var street = $("#streetTf").val();
			var landmark = $("#landmarkTf").val();

			var house_id = $('#venueSelect option:selected').attr('house_id');
			if (house_id == undefined || house_id == "") {
				house_id = "0";
			}

			var street_id = $('#venueSelect option:selected').attr('street_id');
			if (street_id == undefined || street_id == "") {
				street_id = "0";
			}

			var lan_id = $('#venueSelect option:selected').attr('lan_id');
			if (lan_id == undefined || lan_id == "") {
				lan_id = "0";
			}

			
			var row_no = $('#rowNo option:selected').attr('row_no');
			if (row_no == undefined || row_no == "") {
				row_no = "0";
			}

			var row_no = $('#rowNo option:selected').val();

						
			var pg_train_status = "";
			var pg_amount = "";
			var pg_tran_date = "";
			var pg_tran_no = "";
			var book_by_user_id = "";
			var book_by_name = "";
			var agent_id = "";
			var str_id = "";
			
			$formData = new FormData();

			var $file6 = document.getElementById('selectedFile');

			if ($file6.files.length > 0) {
				for (var i = 0; i < $file6.files.length; i++) {
					$formData.append("selectedFile", $file6.files[i]);
				}
			}

			var $file7 = document.getElementById('selectedFile1');

			if ($file7.files.length > 0) {
				for (var i = 0; i < $file7.files.length; i++) {
					$formData.append("selectedFile1", $file7.files[i]);
				}
			}

			$formData.append("opflag", "M");
			$formData.append("common_tron", "0");
			$formData.append("event_head_id", eventHeadeId);
			$formData.append("event_name", event_name);
			$formData.append("phone_no", phone_no);
			$formData.append("mobile_no_1", mobile_no_1);
			$formData.append("mobile_no_2", mobile_no_2);
			$formData.append("email_id", email_id);
			$formData.append("website", website);
			$formData.append("venue_head_id", venueHeadeId);
			$formData.append("venue_structure", venueStucture);
			$formData.append("total_seats", totalSeats);
			$formData.append("adr_name", address);
			$formData.append("area_id", areaId);
			$formData.append("house", house);
			$formData.append("house_id", house_id);
			$formData.append("street", street);
			$formData.append("street_id", street_id);
			$formData.append("landmark", landmark);
			$formData.append("landmark_id", lan_id);
			$formData.append("srNo", srnoStr);
			$formData.append("row_no", rowNoStr);
			$formData.append("from_seat", seatFromStr);
			$formData.append("to_seat", seatToStr);
			$formData.append("seat_status", seatStatusStr);
			$formData.append("seat_type_id", seatTypeStr);
			$formData.append("row_seat_str", seatStrucStr);
			$formData.append("online_tran_id", "");
			$formData.append("pg_train_status", pg_train_status);
			$formData.append("pg_amount", pg_amount);
			$formData.append("pg_tran_date", pg_tran_date);
			$formData.append("pg_tran_no", pg_tran_no);
			$formData.append("pg_tran_date", pg_tran_date);
			$formData.append("pg_tran_time", "");
			$formData.append("book_by_user_id", "");
			$formData.append("book_by_name", "");
			$formData.append("book_person_mobileNo", "");
			$formData.append("agent_id", "");
			$formData.append("branch_id", "");
			$formData.append("str_id", "");
			$formData.append("str_row_no", row_no);

			$
					.ajax({
						url : '${pageContext.request.contextPath}/user/saveEventMaster',
						type : 'POST',
						data : $formData,
						dataType : 'json',
						async : false,
						contentType : false,
						processData : false,
						success : function(resp) {
							alert("Record Update Successfully");
							clearData();
						},
						error : function(resp) {
							alert("error");
							alert(resp.msg);
						},
					});
			
			$("#editdata").hide();
			$("#deleteData").hide();
			$("#savedata").show();
		}

	}
	function deleteData2() {

		if (!$("#venueTypeSelect").val()) {
			alert("Please Select Venue Type");
			$("#venueTypeSelect").select2('focus');
			return false;
		}

		if (!$("#venueSelect").val()) {
			alert("Please Select Venue");
			$("#venueSelect").select2('focus');
			return false;
		}

		if (!$("#venueMap").val()) {
			alert("Please Select Venue Map");
			$("#venueMap").selcet2('focus');
			return false;
		}

		if (!$("#navigatorImage").val()) {
			alert("Please Select Navigator Image")
			$("#navigatorImage").select2('focus');
		}

		var stat = validateFormArray([ '#eventNameTf', '#phoneNoTf',
				'#mobileNoTf', '#websiteTf', '#emailIDTf', '#venueTypeSelect',
				'#totalSeatTf', '#venueMap', '#navigatorImage', '#venueSelect',
				'#countrySelect', '#stateSelect', '#districtSelect',
				'#citySelect', '#pincodeSelect', '#streetTf', '#houseNoTf',
				'#selectedFile', '#selectedFile1' ]);

		var tb = document.getElementById("tb2");

		console.log("tb====>" + tb);
		var ctb = tb.rows.length - 1;
		console.log("ctb====>" + ctb);
		var cnt = 50;

		var rowNoStr = "";
		var seatFromStr = "";
		var seatToStr = "";
		var seatTypeStr = "";
		var seatStatusStr = "";
		var noofSeatStr = "";
		var seatStrucStr = "";
		var srnoStr = "";

		for (var i = 2; i <= ctb; i++) {
			var rowNo = $(tb.rows[i].cells[1].getElementsByTagName('select'))
					.val();
			console.log("rowNo===>" + rowNo);

			var seatFrom = $(tb.rows[i].cells[2].getElementsByTagName('select'))
					.val();
			var seatTo = $(tb.rows[i].cells[3].getElementsByTagName('select'))
					.val()
			var seatType = $(tb.rows[i].cells[4].getElementsByTagName('select'))
					.val();
			console.log("seatType===>" + seatType);

			var seatStatus = $(tb.rows[i].cells[5].getElementsByTagName('select'))
			        .val();
			console.log("seatStatus===>" + seatStatus);

			var noofSeat = $(tb.rows[i].cells[6].getElementsByTagName('input'))
					.val();

			var seatStruc = $(tb.rows[i].cells[7].getElementsByTagName('input'))
					.val();
			console.log("seatStruc===>" + seatStruc);

			var srno = "1";
			var seatStatus = $("#seatStatuss").val();

			if (rowNo != "" && seatFrom != "" && seatTo != "" && seatType != ""
					&& seatStatus != "" && noofSeat != "" && seatStruc != "") {
				rowNoStr = rowNoStr + rowNo + "~";
				console.log("rowNoStr========>" + rowNoStr);
				seatFromStr = seatFromStr + seatFrom + "~";
				seatToStr = seatToStr + seatTo + "~";
				seatTypeStr = seatTypeStr + seatType + "~";
				console.log("SEATTYPE===>" + seatTypeStr);
				seatStatusStr = seatStatusStr + seatStatus + "~";
				console.log("SEATSTATUS===>" + seatStatusStr);
				noofSeatStr = noofSeatStr + noofSeat + "~";
				seatStrucStr = seatStrucStr + seatStruc + "~";
				srnoStr = srnoStr + srno + "~";
			}
		}

		/* console.log("rowNoStr:::" + rowNoStr);
		if (rowNoStr.split("~").length > 1) {
			var stat = true;
		} else {
			alert("Please Enter Alteast One Row in Seat Layout!");
			var stat = false;
		} */

		if (stat) {

			var eventHeadeId = $("#eventHeadid").val();
			var event_name = $("#eventNameTf").val();
			var phone_no = $("#phoneNoTf").val();
			var mobile_no_1 = $("#mobileNoTf").val();
			var mobile_no_2 = $("#mobileNoTf").val();
			var email_id = $("#emailIDTf").val();
			var website = $("#websiteTf").val();
			var venueHeadeId = $('#venueSelect option:selected').attr('value');
			var venueStucture = $("#venueStructureSelect").val();
			var totalSeats = $("#totalSeatTf").val();
			var address = $("#addressTf").val();
			var areaId = $("#pincodeSelect").val();
			var house = $("#houseNoTf").val();
			var street = $("#streetTf").val();
			var landmark = $("#landmarkTf").val();

			var house_id = $('#venueSelect option:selected').attr('house_id');
			if (house_id == undefined || house_id == "") {
				house_id = "0";
			}

			var street_id = $('#venueSelect option:selected').attr('street_id');
			if (street_id == undefined || street_id == "") {
				street_id = "0";
			}

			var lan_id = $('#venueSelect option:selected').attr('lan_id');
			if (lan_id == undefined || lan_id == "") {
				lan_id = "0";
			}

			
			var row_no = $('#rowNo option:selected').val();
			var pg_train_status = "";
			var pg_amount = "";
			var pg_tran_date = "";
			var pg_tran_no = "";
			var book_by_user_id = "";
			var book_by_name = "";
			var agent_id = "";
			var str_id = "";

			$formData = new FormData();

			var $file6 = document.getElementById('selectedFile');

			if ($file6.files.length > 0) {
				for (var i = 0; i < $file6.files.length; i++) {
					$formData.append("selectedFile", $file6.files[i]);
				}
			}

			var $file7 = document.getElementById('selectedFile1');

			if ($file7.files.length > 0) {
				for (var i = 0; i < $file7.files.length; i++) {
					$formData.append("selectedFile1", $file7.files[i]);
				}
			}

			$formData.append("opflag", "D");
			$formData.append("common_tron", "0");
			$formData.append("event_head_id", eventHeadid);
			$formData.append("event_name", event_name);
			$formData.append("phone_no", phone_no);
			$formData.append("mobile_no_1", mobile_no_1);
			$formData.append("mobile_no_2", mobile_no_1);
			$formData.append("email_id", email_id);
			$formData.append("website", website);
			$formData.append("venue_head_id", venueHeadeId);
			$formData.append("venue_structure", venueStucture);
			$formData.append("total_seats", totalSeats);
			// $formData.append("venue_map",""); 
			//$formData.append("navigation_img", navigatorImage);
			$formData.append("adr_name", address);
			$formData.append("area_id", areaId);
			$formData.append("house", house);
			$formData.append("house_id", house_id);
			$formData.append("street", street);
			$formData.append("street_id", street_id);
			$formData.append("landmark", landmark);
			$formData.append("landmark_id", lan_id);
			$formData.append("srNo", srnoStr);
			$formData.append("row_no", rowNoStr);
			$formData.append("seat_status", seatStatusStr);
			$formData.append("seat_type_id", seatTypeStr);
			$formData.append("row_seat_str", seatStrucStr);
			$formData.append("online_tran_id", "");
			$formData.append("pg_train_status", pg_train_status);
			$formData.append("pg_amount", pg_amount);
			$formData.append("pg_tran_date", pg_tran_date);
			$formData.append("pg_tran_no", pg_tran_no);
			$formData.append("pg_tran_time", "");
			$formData.append("book_by_user_id", "");
			$formData.append("book_by_name", "");
			$formData.append("book_person_mobileNo", "");
			$formData.append("agent_id", "");
			$formData.append("branch_id", "");
			$formData.append("str_id", eventHeadid);
			$formData.append("str_row_no", row_no);

			$
					.ajax({
						url : '${pageContext.request.contextPath}/user/saveEventMaster',
						type : 'POST',
						data : $formData,
						dataType : 'json',
						async : false,
						contentType : false,
						processData : false,
						success : function(resp) {
							alert("Record Delete Successfully");
							clearData();
						},
						error : function(resp) {
							alert(resp);

						},
					});

			clearList();
			$("#editdata").hide();
			$("#savedata").Show();
		}
	}

	function clearList() {

		document.getElementById('eventName').value = "";
		document.getElementById('totalSeat').value = "";
		document.getElementById('citytb').value = "";
		document.getElementById('pincodetb').value = "";

	}
	function topFunction() {
		document.body.scrollTop = 0;
		document.documentElement.scrollTop = 0;
	}

	
		function deleteRow() {
			
    		var tb = document.getElementById("tb2");
       		var ctb = tb.rows.length - 1;
       		var cnt = 50;

       		var rowNoStr = "";
       	    var seatFromStr = "";
       		var seatToStr = "";
       		var seatTypeStr = "";
       		var seatStatusStr = "";
       		var noofSeatStr = "";
       		var seatStrucStr = "";
       		var srnoStr = "";

       		for (var i = 2; i <= ctb; i++) {
       			var rowNo = $(tb.rows[i].cells[1].getElementsByTagName('select'))
       					.val();
       			
       			 var seatFrom = $(tb.rows[i].cells[2].getElementsByTagName('select'))
       					.val();
       			var seatTo = $(tb.rows[i].cells[3].getElementsByTagName('select'))
       					.val()
       			var seatType = $(tb.rows[i].cells[4].getElementsByTagName('select'))
       					.val();
       			
       			var seatStatus = $(tb.rows[i].cells[5].getElementsByTagName('select'))
       			        .val();
       			
       			var noofSeat = $(tb.rows[i].cells[6].getElementsByTagName('input'))
       					.val();

       			var seatStruc = $(tb.rows[i].cells[7].getElementsByTagName('input'))
       					.val();
       			

       			/* var srno = "1";
       			var seatStatus = $("#seatStatuss").val(); */

       			if (rowNo != ""  && seatFrom != "" && seatTo != "" && seatType != ""
       					&& seatStatus != "" && noofSeat != "" && seatStruc != "" ) {
       				rowNoStr = rowNoStr + rowNo + "~";
       			}
       		}

       		
            $(document).on("click", ".remove", function(){
            if (rowNoStr.split("~").length > 1) {

                   $(this).parents("tr").remove();
            } else {
            	
           }

       }); 
    } 
		
	
	</script>
</head>
<body>
	<input type="hidden" id="row_index">
	<input type="hidden" id="selectedIndex">
	<input type="hidden" id=row_index>
	<input type="hidden" id=modelName>
	<input type="hidden" id=seatTypeID>
	<input type="hidden" id=eventHeadid>
	<input type="hidden" id=modelName>

	<div class="container-fluid" style="padding: 5px">
		<div id="notificationText" style="display: none;">
			<div id="notificationContent"
				style="z-index: 99999; opacity: 0.9; float: inherit; text-align: center;">
			</div>
		</div>

		<div class="panel-body" id="collapseTwo"
			style="background-color: #fff; box-shadow: 5px 5px 30px 5px #888888;"
			class="panel-collapse collapse" role="tabpanel"
			aria-labelledby="headingOne" class="main wrapper2"
			style="margin: auto;">

			<div class="row" align="right">
				<div class=" col-sm-12 col-md-12 col-lg-12">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#myModal" onclick="venueList2()" tabIndex="23">Event
						list</button>

					<button type="submit" id="savedata" class="btn btn-primary"
						onclick="saveDataList();" tabIndex="57">Save</button>
					<button type="submit" id="editdata" class="btn btn-primary"
						onclick="updateData();" tabIndex="58">Update</button>
					<button type="button" id="deleteData" class="btn btn-primary"
						onclick="deleteData2()" tabIndex="26">Delete</button>
					<button type="button" class="btn btn-primary"
						onclick="clearData();" tabIndex="59">Clear</button>


				</div>

			</div>


			<div class="row">

				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Event" class="mandatory">Event Name:</label> <input
						type="text" id="eventNameTf" tabIndex="1" class="form-control"
						title="Event Name" placeholder="Enter Event Name">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Phone" class="mandatory">Venue Type:</label> <select
						class="form-control" id="venueTypeSelect" title="Venue Type"
						onchange="populateVenueSelect()" tabIndex="2">
						<option>--Select--</option>
					</select>
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Mobile" class="mandatory">Venue:</label> <select
						id="venueSelect" class="form-control" title="Venue "
						onchange="getValues();rowNoType();" tabIndex="3">
						<option>--Select--</option>
					</select>

				</div>
			</div>


			<div class="row">

				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="email" class="mandatory"><font>Email ID</label>
					<input id="emailIDTf" type="email" title="Email ID"
						class="form-control validateEmail" placeholder="Enter Email ID"
						tabIndex="4">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Phone" class="mandatory">Web Site:</label> <input
						title="Web Site" id="websiteTf" type="text"
						placeholder="Enter Web Site Name" class="form-control"
						tabIndex="5">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Mobile" class="mandatory">Phone No :</label> <input
						id="phoneNoTf" type="text" class="form-control validatePhone"
						tabIndex="6" title="Phone No" placeholder="Enter Phone No">
				</div>
			</div>

			<div class="row">

				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Venue" class="mandatory">Mobile No:</label> <input
						id="mobileNoTf" type="text" title="Mobile No." maxlength="10"
						class="form-control validateMobile" tabIndex="7"
						placeholder="Enter Mobile No">
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label class="mandatory ">Venue Structure:</label> <select
						id="venueStructureSelect" class="form-control" tabIndex="8"
						title="Venue Structure">
						<option>--Select--</option>
						<option value="F">Fixed</option>
						<option value="C">Customizable</option>
					</select>
				</div>
				<div class=" col-sm-4 col-md-4 col-lg-4">
					<label for="Mobile" class="mandatory">Total Seat:</label> <input
						id="totalSeatTf" type="text" class="form-control"
						placeholder="Enter Total Seat" tabIndex="9" title="Total Seat">
				</div>

			</div>


			<div class="row">

				<div class=" col-sm-4 col-md-4 col-lg-4">

					<label for="venueMap" class="mandatory"><font>Venue
							Map</font></label>


					<form id="myForm">
						<input type="text" id="venueMap" title="Venue Map"
							class="form-control" disabled="disabled"> <input
							type="file" id="selectedFile" style="display: none;"
							onchange="showname('venueMap','selectedFile');checkFileUpload(this)" />
						<input type="button" id="btnEcucation" value="Browse"
							class="btn btn-primary"
							onclick="document.getElementById('selectedFile').click();"
							tabIndex="10" /> <input type="button" id="btnEcucationClr"
							value="Clear" class="btn btn-primary"
							onclick="document.getElementById('venueMap').value = ''">
					</form>
				</div>

				<div class=" col-sm-4 col-md-4 col-lg-4">

					<label for="venueMap" class="mandatory"><font>Navigator
							Image</font></label>


					<form id="myForm">
						<input type="text" id="navigatorImage" title="navigator Image"
							class="form-control" disabled="disabled"> <input
							type="file" id="selectedFile1" style="display: none;" multiple
							onchange="showname('navigatorImage','selectedFile1');checkFileUpload(this)" />
						<input type="button" id="btnEcucation" value="Browse"
							class="btn btn-primary"
							onclick="document.getElementById('selectedFile1').click();"
							tabIndex="11" /> <input type="button" id="btnEcucationClr"
							value="Clear" class="btn btn-primary"
							onclick="document.getElementById('navigatorImage').value = ''">
					</form>
				</div>
			</div>
		</div>

		<br>

		<div class="panel panel-default">
			<div class="panel-heading" style="background-color: #dac8b6;">Address
				Details</div>
			<div class="panel-body" id="collapseTwo"
				style="background-color: #fff; box-shadow: 5px 5px 30px 5px #888888;"
				class=" panel-collapse
				collapse" role="tabpanel"
				aria-labelledby="headingOne" class="main wrapper2"
				style="margin: auto;">


				<div class="row">
					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Venue" class="mandatory">Country:</label> <select
							id="countrySelect" title="Country " class="form-control"
							onchange="populateStateSelect();" tabIndex="13">
							<option>--Select--</option>
						</select>

					</div>
					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label class="mandatory ">State:</label> <select id="stateSelect"
							title="State " onchange="populateDistrictSelect();"
							class="form-control" tabIndex="14">
							<option>--Select--</option>
						</select>
					</div>

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Mobile" class="mandatory">District:</label> <select
							id="districtSelect" class="form-control" title="District "
							onchange="populateCitySelect();" tabIndex="15">
							<option>--Select--</option>
						</select>
					</div>
				</div>



				<div class="row">
					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Venue" class="mandatory">City:</label> <select
							id="citySelect" title="City " class="form-control"
							onchange="populatePincodeSelect();" tabIndex="16">
							<option>--Select--</option>
						</select>
					</div>

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label class="mandatory ">Pin Code:</label> <select
							id="pincodeSelect" title="Pincode " class="form-control"
							onchange="populateLandmarkSelect();" tabIndex="17">
							<option>--Select--</option>
						</select>
					</div>

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Venue" class="mandatory">Landmark:</label> <input
							id="landmarkTf" list="landmarkList" type="text" title="Landmark "
							class="form-control" onchange="populateStreetSelect();"
							tabIndex="18" placeholder="Enter Landmark:">
						<datalist id="landmarkList"></datalist>
					</div>
				</div>

				<div class="row">


					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label class="mandatory ">Street:</label> <input id="streetTf"
							list="streetList" type="text" title="Street "
							placeholder="Enter Street" class="form-control"
							onchange="populateHouseNoSelect();" tabIndex="19">
						<datalist id="streetList"></datalist>
					</div>

					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Mobile" class="mandatory">House No:</label> <input
							id="houseNoTf" list="houseNoList" type="text"
							class="form-control" title="House No "
							placeholder="Enter Landmark:" tabIndex="20">
						<datalist id="houseNoList"></datalist>
					</div>
					<div class=" col-sm-4 col-md-4 col-lg-4">
						<label for="Mobile" class="mandatory">Address:</label> <input
							id="addressTf" type="text" title="Address" class="form-control"
							placeholder="Enter Address" tabIndex="21">
						<datalist id="addressList"></datalist>
					</div>
				</div>

				<br> <br> <br>
				<div class="panel panel-primary">
					<!-- <div class="panel-heading" style="background-color: #ff9e66;">
			<h4>Table</h4>
		</div> -->
					<div class="bs-example">
						<div class="Container" style="overflow-x: scroll;">
							<table class="table table-hover small-text" id="tb2">
								<tr class="tr-header">
									<th><label class="mandatory">S.No</label></th>
									<th><label class="mandatory">Row No.</label></th>
									<th colspan="2"><label class="mandatory">Seat</label></th>
									<th><label class="mandatory">Seat Type</label></th>
									<th><label class="mandatory">Seat Status</label></th>
									<th><label class="mandatory">No.of Seat</label></th>
									<th><label class="mandatory">Seat Str</label></th>
									<th><a href="javascript:void(0);"
										style="font-size: 18px; width: 33%;" id="addMore2"
										title="Add More Record"><span
											class="glyphicon glyphicon-plus"></span></a></th>
								</tr>

								<tr height="5%" class="tr-header">
									<th></th>
									<th></th>
									<th><label class="mandatory">From</label></th>
									<th><label class="mandatory">To</label></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr id="1A" style="color: #333;">
									<td>&nbsp;</td>
									<td><select class="rowNoSelect form-control" id="rowNo"
										title="Row Select" style="width: 120px; height: 26px;"
										onchange="fromSeatType(this)" tabIndex="51">
											<option>--Select--</option>
									</select></td>
									<td><select class="fromSeatSelect form-control" id="fromS"
										title="From Seat" style="width: 120px; height: 26px;"
										onchange="toSeatType(this)" tabIndex="52">
											<option>--Select--</option>
									</select></td>
									<td><select class="toSeatSelect form-control" id="toS"
										class="mandatory" title="To Seat"
										style="width: 120px; height: 26px;"
										onchange="SeatDiffType(this)" tabIndex="53">
											<option>--Select--</option>
									</select></td>
									<td><select class="seatTypeSelect form-control" id="seatType"
										title="Seat Type" style="width: 120px; height: 26px;"
										onchange="" tabIndex="54">
											<option>--Select--</option>
									</select></td>
									<td><select name="seatStatus" title="Seat Status" class="form-control"
										id="seatStatuss" style="width: 95px; height: 26px;"
										tabIndex="55">
											<option value="F">Free</option>
											<option value="B">Book</option>
											<option value="P">Pass</option>
											<option value="R">Reserve</option>
									</select></td>
									<td><input type="text" name="noOfSeat" id="tdiff" class="form-control"
										title="No.of Seat" style="width: 90px;" disabled></td>
									<td><input type="text" id="seatStrTf" class="form-control"
										style="width: 85px" title="Seat Str" tabIndex="56"></td>
									<td><a href='javascript:void(0);' class='remove'
										onclick="deleteRow()"><span
											class='glyphicon glyphicon-remove'></span></a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<br>
			</div>
		</div>

	</div>
</body>
</html>