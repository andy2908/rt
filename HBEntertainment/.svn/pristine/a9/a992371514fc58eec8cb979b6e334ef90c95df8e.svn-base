<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<link href="/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/jquery.seat-charts.css">
<!-- <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>  -->
<script src="js/jquery.seat-charts.js"></script>
<script>
	var firstSeatLabel = 1;
	var firstSeatLabelRow = 1;

	$(document)
			.ready(
					function() {
						var $cart = $('#selected-seats'), $counter = $('#counter'), $total = $('#total'), sc = $(
								'#seat-map')
								.seatCharts(
										{
											map : [
													'______vvvvvvvvv__vvvvvvvvvvvvvvv__vvvvvvvvv______',
													'_vvvvvvvvvvvvvv__vvvvvvvvvvvvvvv__vvvvvvvvvvvvvv_',
													'vvvvvvvvvvvvvvv__vvvvvvvvvvvvvvv__vvvvvvvvvvvvvvv',
													'_________________________________________________',
													'ppppppppppppppp__ppppppppppppppp__ppppppppppppppp',
													'ppppppppppppppp__ppppppppppppppp__ppppppppppppppp',
													'ppppppppppppppp__ppppppppppppppp__ppppppppppppppp',
													'_________________________________________________',
													'ggggggggggggggg__ggggggggggggggg__ggggggggggggggg',
													'ggggggggggggggg__ggggggggggggggg__ggggggggggggggg',
													'ggggggggggggggg__ggggggggggggggg__ggggggggggggggg',
													'_________________________________________________',
													'sssssssssssssss__sssssssssssssss__sssssssssssssss',
													'sssssssssssssss__sssssssssssssss__sssssssssssssss',
													'sssssssssssssss__sssssssssssssss__sssssssssssssss',

											],
											
											seats : {
												s : {
													price : 1000,
													classes : 'silver-class', //your custom CSS class
													category : 'Silver Class'
												},
												g : {
													price :500,
													classes : 'gold-class', //your custom CSS class
													category : 'Gold Class'
												},
												p : {
													price : 300,
													classes : 'platinum-class', //your custom CSS class
													category : 'Platinum Class'
												},
												v : {
													price : 200,
													classes : 'vip-class', //your custom CSS class
													category : 'VIP Class'
												}
											},
											naming : {
												rows : [ "AA", "AB", "AC",
															
														"","AD", "AE", "AF", " ",
														"AG", "AH", "AI", "",
														"AJ", "AK", "AL", "AM",
														"AN", "AO", "AP" ],
												top : false,
												getLabel : function(character,
														row, column) {
													if (firstSeatLabelRow == row) {
														return firstSeatLabel++;
													} else {
														firstSeatLabelRow = row;
														firstSeatLabel = 1;
														return firstSeatLabel++;
													}
												}
											},
											legend : {
												node : $('#legend'),
												items : [
														[ 'f', 'available',
																'available' ],
														[ 'e', 'selected',
																'selected' ],
														[ 'f', 'unavailable',
																'unavailable' ] ]
											},
											click : function() {
												if (this.status() == 'available') {
													//let's create a new <li> which we'll add to the cart items
													$(
															'<li>'
																	+ this
																			.data().category
																	+ ' Seat # '
																	+ this.settings.label
																	+ ': <b>$'
																	+ this
																			.data().price
																	+ '</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
															.attr(
																	'id',
																	'cart-item-'
																			+ this.settings.id)
															.data(
																	'seatId',
																	this.settings.id)
															.appendTo($cart);

													/*
													 * Lets update the counter and total
													 *
													 * .find function will not find the current seat, because it will change its stauts only after return
													 * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.
													 */
													$counter
															.text(sc
																	.find('selected').length + 1);
													$total
															.text(recalculateTotal(sc)
																	+ this
																			.data().price);

													return 'selected';
												} else if (this.status() == 'selected') {
													//update the counter
													$counter
															.text(sc
																	.find('selected').length - 1);
													//and total
													$total
															.text(recalculateTotal(sc)
																	- this
																			.data().price);

													//remove the item from our cart
													$(
															'#cart-item-'
																	+ this.settings.id)
															.remove();

													//seat has been vacated
													return 'available';
												} else if (this.status() == 'unavailable') {
													//seat has been already booked
													return 'unavailable';
												} else {
													return this.style();
												}
											}
										});

						//this will handle "[cancel]" link clicks
						$('#selected-seats').on(
								'click',
								'.cancel-cart-item',
								function() {
									//let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
									sc.get(
											$(this).parents('li:first').data(
													'seatId')).click();
								});

						//let's pretend some seats have already been booked
						// 	sc.get(['1_2', '4_1', '7_1', '7_2']).status('unavailable');

					});

	function recalculateTotal(sc) {
		var total = 0;
		//basically find every selected seat and sum its price
		sc.find('selected').each(function() {
			total += this.data().price;
		});

		return total;
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
</script>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-36251023-1' ]);
	_gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	});
</script>
<style>
body {
	font-family: 'Roboto', sans-serif;
	background-color: #fafafa;
}

a {
	color: #b71a4c;
}

.front-indicator {
	width: 100%;
	margin: 5px 32px 15px 0px;
	background-color: #f6f6f6;
	color: #adadad;
	text-align: center;
	padding: 3px;
	border-radius: 5px;
}

.main {
	height: 200px;
	overflow-x: scroll;
}

.wrapper {
	width: 100%;
	text-align: center;
	margin-top: 45px;
}
/* .container {
	margin: 0 auto;
	width: 500px;
	text-align: left;
} */
/* .booking-details {
	float: left;
	text-align: left;
	margin-left: 35px;
	font-size: 12px;
	position: relative;
	height: 401px;
}

.booking-details h2 {
	margin: 25px 0 20px 0;
	font-size: 17px;
}

.booking-details h3 {
	margin: 5px 5px 0 0;
	font-size: 14px;
} */
div.seatCharts-cell {
	color: #182C4E;
	height: 20px;
	width: 20px;
	line-height: 20px;
}

div.seatCharts-seat {
	color: #FFFFFF;
	cursor: pointer;
}

div.seatCharts-row {
	height: 25px;
}

div.seatCharts-seat.available {
	background-color: #B9DEA0;
}

div.seatCharts-seat.available.vip-class {
	/*  	background: url(vip.png); */
	background-color: #839282b3;
}

div.seatCharts-seat.available.platinum-class {
	/*  	background: url(vip.png); */
	background-color: #f3b624b3;
}

div.seatCharts-seat.available.gold-class {
	/*  	background: url(vip.png); */
	background-color: #a392bc;
}

div.seatCharts-seat.available.silver-class {
	/*  	background: url(vip.png); */
	background-color: #6b7fd8e6;
}

div.seatCharts-seat.focused {
	background-color: #76B474;
}

div.seatCharts-seat.selected {
	background-color: #00b9f5;
}

div.seatCharts-seat.unavailable {
	background-color: #F01414;
}

div.seatCharts-container {
	/* 	border-right: 1px dotted #adadad; */
	padding: 20px;
	float: left;
}

/* div.seatCharts-legend {
	padding-left: 0px;
	position: absolute;
	bottom: 16px;
}

ul.seatCharts-legendList {
	padding-left: 0px;
}

span.seatCharts-legendDescription {
	margin-left: 5px;
	line-height: 30px;
} */
.checkout-button {
	display: block;
	margin: 10px 0;
	font-size: 14px;
}

#selected-seats {
	max-height: 90px;
	overflow-y: scroll;
	overflow-x: none;
	width: 170px;
}
</style>
</head>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-4" style="margin-bottom: -45px;">
				<div id="legend"></div>

			</div>
			
			<div class="col-md-6 " style="margin-top: 12px;">  
			
				<div class="form-check form-check-inline">
 <div class="seatCharts-seat seatCharts-cell " style="background-color: #6b7fd8e6;"></div>
  <label class="form-check-label" for="inlineCheckbox1">VIP Class</label>
</div>

<div class="form-check form-check-inline">
 <div class="seatCharts-seat seatCharts-cell "style="background-color: #a392bc;"></div>
  <label class="form-check-label" for="inlineCheckbox3">Platinum Class</label>
</div>
<div class="form-check form-check-inline">
  <div class="seatCharts-seat seatCharts-cell "style="background-color: #f3b624b3;"></div>
  <label class="form-check-label" for="inlineCheckbox2">Gold Class</label>
</div>
<div class="form-check form-check-inline">
   <div class="seatCharts-seat seatCharts-cell "style="background-color: #839282b3;"></div>
  <label class="form-check-label" for="inlineCheckbox3">Silver Class</label>
</div>

			</div> 	
		</div>
	



		<div class="row">
			<div class="col-md-12">
				<div class="main wrapper2" style="height: 350px;">

					<div id="seat-map" style="width: 2045px;">

						<div class="front-indicator">Stage</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="booking-details">
					<h2>Booking Details</h2>
					<h3>
						Selected Seats (<span id="counter">0</span>):
					</h3>
					<ul id="selected-seats" style="width: 500px;">

					</ul>
					Total: <b>$<span id="total">0</span></b>
					<button class="checkout-button">Checkout &raquo;</button>
				</div>
			</div>
		</div>

		<br> <br> <br> <br> <br>
	</div>

</body>
</html>
