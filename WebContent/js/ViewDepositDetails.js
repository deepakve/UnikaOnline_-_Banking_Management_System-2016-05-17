function getSelectedOneDetails() {
	var radios = document.getElementsByName('account');
	var data = null;
	for ( var i = 0, length = radios.length; i < length; i++) {
		if (radios[i].checked) {
			data = radios[i].value;
		}
	}

	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("GET", "ViewFixedDepositDetailsServlet?account="+escape(data), true);
	xmlhttp.send(null);
	
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("details").innerHTML = xmlhttp.responseText;
			}
	};
}