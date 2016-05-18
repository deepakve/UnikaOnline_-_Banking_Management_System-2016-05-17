function getSelectedOneDetails()
{
	var radios = document.getElementsByName('account');
	var data = null;
	for (var i = 0, length = radios.length; i < length; i++) {
	    if (radios[i].checked) {
	    	data = radios[i].value;
	    }
	}
	
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.open("GET", "ModifyAccountDetailsServlet?account="+escape(data), true);
	xmlhttp.send(null);
	
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
			{
				document.getElementById("Selected").innerHTML = xmlhttp.responseText;
			}
	};
}

function isOneChecked() 
{
	  var chx = document.getElementsByName('account');
	  for (var i=0; i<chx.length; i++) {
	    if (chx[i].checked) {
	      return true;
	    } 
	  }
	  alert("Select atleast one account");
	  return false;
}

function validate()
{
	if(isOneCheked())
		{
			return true;
		}
	return false;
}


