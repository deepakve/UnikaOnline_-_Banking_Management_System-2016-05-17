function getSelectedAccountDetails()
{
	var data = document.getElementById('AccountSelector').value;
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.open("GET", "SelectedAccountDetailsServlet?option="+escape(data), true);
	xmlhttp.send(null);
	
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("accounts").innerHTML = xmlhttp.responseText;
			}
	};
}