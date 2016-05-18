$('#newPassword').live("keyup",function(){
	if(($(this).val()!='')) 
	  {
	  var pattern=/^(?=.*\d)(?=.*[A-Z])[0-9a-zA-Z]{8,15}$/;
	  if(pattern.test($(this).val()))
		  {
		  	$('#pwd1_msg').html('');
		  	$('#pwd1_msg').attr("value",1);
	  		}
	  else
		  
		  $('#pwd1_msg').html('Password should contain atleast one digit and atleast one capital letter');
		  
		  }
		  
		  else
			  
			  $('#pwd1_msg').html('Please enter the new password');
	  
	  
});

$('#cfmPassword').live("keyup",function(){
	if(($(this).val()!='')&&($(this).val()==$('#newPassword').val())) 
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


function validate()
{
	var b=$('#pwd1_msg').attr("value");
	var c=$('#pwd2_msg').attr("value");
	if(b*c==1)
		{
		return true;
		}
	else
		{
		alert("Please provide all the details");
		return false;
		}
		}