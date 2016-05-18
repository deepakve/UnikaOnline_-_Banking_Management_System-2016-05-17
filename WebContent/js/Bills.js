function getSelectedBillDetails() {
	var radios = document.getElementsByName('Bill');
	var data1 = null;
	for ( var i = 0, length = radios.length; i < length; i++) {
		if (radios[i].checked) {
			data1 = radios[i].value;
		}
	}
	
	$.ajax({
		 type: "GET",
		 url: "BillDetailsServlet",
		 data: {Bill:data1}
		 }).done(function( result ) {
		 $("#Bill").html( result );
		 });
}

function getElectricityBill() {
	var data1 = $("#area").val();
	var data2 = $("#billnumber").val();
	
	if(data1.trim().length > 0 && data2.trim().length > 0){
		$.ajax({
			type: "GET",
			url: "ElectricityBillServlet",
			data: {area:data1, billnumber:data2}
			}).done(function( result ) {
			$("#BillValue").val(result);
			document.getElementById("Submit").disabled=false;
		});		
	}
}

function getTelephoneBill() {
	var data1 = $("#operator").val();
	var data2 = null;
	
	var radios = document.getElementsByName('type');	
	for ( var i = 0, length = radios.length; i < length; i++) {
		if (radios[i].checked) {
			data2 = radios[i].value;
		}
	}
	
	var data3 = $("#number").val();
	
	if(data1.trim().length > 0 && data2.trim().length > 0 && data3.trim().length > 0){
		$.ajax({
			type: "GET",
			url: "TelephoneBillServlet",
			data: {operator:data1, type:data2, number:data3}
			}).done(function( result ) {
			$("#amount").val(result);
			document.getElementById("Submit").disabled=false;
		});		
	}
}


function getNumberDetails() {
	var data1 = $("#operator").val();
	var content;
	$("#number").val("");
	$("#amount").val("");
	
	if(data1 == "---Choose---"){
		$("#type").html("");
	}
	else if(data1 == "bsnl" || data1 == "airtel"){
		content = "<input type=\"radio\" name=\"type\" value=\"gsm\"> GSM" +
		"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
		"<input type=\"radio\" name=\"type\" value=\"landline\"> Land Line";
		
		$("#type").html(content);		
	}	
	else if(data1 == "vodafone" || data1 == "idea" || data1 == "aircel"){
		content = "<input type=\"radio\" name=\"type\" value=\"gsm\" checked> GSM";		
		
		$("#type").html(content);		
	}	
	else{
		content = "<input type=\"radio\" name=\"type\" value=\"gsm\"> GSM" +
		"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
		"<input type=\"radio\" name=\"type\" value=\"cdma\"> CDMA" +
		"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" +
		"<input type=\"radio\" name=\"type\" value=\"landline\"> Land Line";
		
		$("#type").html(content);	
	}
}

