function activateTextbox1()
{
	var text_box = document.getElementById("fname");
	text_box.removeAttribute("readonly");
	document.getElementById("fname").focus();
}

function activateTextbox2()
{
	var text_box = document.getElementById("lname");
	text_box.removeAttribute("readonly");
	document.getElementById("lname").focus();
}

function activateTextbox3()
{
	var text_box = document.getElementById("email");
	text_box.removeAttribute("readonly");
	document.getElementById("email").focus();
}

function activateTextbox4()
{
	var text_box = document.getElementById("mobile");
	text_box.removeAttribute("readonly");
	document.getElementById("mobile").focus();
}