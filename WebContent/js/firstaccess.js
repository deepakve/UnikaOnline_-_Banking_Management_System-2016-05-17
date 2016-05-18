$('#newpassword').live("keyup",function() {
	 if(($(this).val()!='')) 
	  {
	  var pattern=/^(?=.*\d)(?=.*[A-Z])[0-9a-zA-Z]{8,15}$/;
	  if(pattern.test($(this).val()))
		  {
		  	$('#pwd_msg').html('');
		  	$('#pwd_msg').attr("value",1);
	  		}
	  else
		  
		  $('#pwd_msg').html('Password should contain atleast one digit and atleast one capital letter');
		  
		  }
		  
		  else
			  
			  $('#pwd_msg').html('Please enter the existing password');
		});

$('#answer1').live("keyup", function() {
	if (($(this).val() != '')) {
		$('#ans1_msg').html('');
		$('#ans1_msg').attr("value", 1);
	} else {
		$('#ans1_msg').html('Please give the answer');
	}

});

$('#repassword').live("keyup",function() {
	if(($(this).val()!='')&&($(this).val()==$('#newpassword').val())) 
	{
		  var pattern=/^(?=.*\d)(?=.*[A-Z])[0-9a-zA-Z]{8,15}$/;
		  if(pattern.test($(this).val()))
			  {
			  	$('#pwd2_msg').html('');
			  	$('#pwd2_msg').attr("value",1);
		  		}
		  else
			  
			  $('#pwd2_msg').html('Password mismatch');
			  
			  }
			  
			  else
				  
				  $('#pwd2_msg').html('Please confirm the password');
		});

$('#question2').live("keyup", function() {
	if (($(this).text() != "default")) {
		$('#sec2_msg').html('');
		$('#sec2_msg').attr("value", 1);
	} else {
		$('#sec2_msg').html('Please select the secret question');
	}
});

$('#answer2').live("keyup", function() {
	if (($(this).val() != '')) {
		$('#ans2_msg').html('');
		$('#ans2_msg').attr("value", 1);
	} else {
		$('#ans2_msg').html('Please give the answer');
	}

});
function validate() {
	var b = $('#pwd_msg').attr("value");
	var d = $('#ans1_msg').attr("value");
	var e = $('#pwd2_msg').attr("value");
	var g = $('#ans2_msg').attr("value");

	if (b * d * e * g == 1) {
		return true;
	} else {
		alert('Please provide all the details');
		return false;
	}
}
