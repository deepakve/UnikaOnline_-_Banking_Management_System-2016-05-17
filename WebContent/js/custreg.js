$(document).ready(function() {
    $('#customerid').keydown(maskInput);
});

$(document).ready(function() {
    $('<span style="color:red;">*</span>').insertAfter('.required');
});


$('#contact').live("keyup",function(){
    if( ($(this).val()!='')) {
      var pattern=/^\d{10}$/ ;
      if(pattern.test($(this).val())){   
        $('#cntct_msg').html(''); 
         }
      else 
      $('#cntct_msg').html('Please provide valid phone no');
     
      
      }
      else {$('#cntct_msg').html('Contact No please ');$('#cntct_msg').attr("value",1);}
  });

$('#email').live("keyup",function(){
    if( ($(this).val()!='')) {
      var pattern=/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/ ;
      if(pattern.test($(this).val())){   
        $('#email_msg').html(''); 
         }
      else 
      $('#email_msg').html('Please provide valid emailid');
      
      
      }
      else {$('#email_msg').html('emailid ');$('#email_msg').attr("value",1);}
  });


$('#adminid').live("keyup",function(){
	  if(($(this).val()!='')) 
		  {
		  $('#adminid_msg').html('');
		  }
	  else
		  {
		  $('#adminid_msg').html('Please provide your id').attr("value",1);
		  }
	  
	  
});

$('#currentpassword').live("keyup",function(){
	  if(($(this).val()!='')&& ($(this).val().length>7)&& ($(this).val().length<15)) 
		  {
		  $('#curpwd_msg').html('');
		  }
	  else
		  {
		  $('#curpwd_msg').html('Password limit is min 8 & max 15').attr("value",1);
		  }
	  
	  
});

$('#newpassword').live("keyup",function(){
	  if(($(this).val()!='')&& ($(this).val().length>7)&& ($(this).val().length<15)) 
		  {
		  $('#newpwd_msg').html('');
		  }
	  else
		  {
		  $('#newpwd_msg').html('Password limit is min 8 & max 15').attr("value",1);
		  }
	  
	  
});

$('#retypepassword').live("keyup",function(){
	  if(($(this).val()!='')&& ($(this).val().length>7)&& ($(this).val().length<15)&&($(this).val()==$('#newpassword').val())) 
		  {
		  $('#repwd_msg').html('');
		  }
	  else
		  {
		  $('#repwd_msg').html('Password mismatch').attr("value",1);
		  }
	  
	  
});


function maskInput() {
    var key_code = window.event.keyCode;
    var oElement = window.event.srcElement;
    if (!window.event.shiftKey && !window.event.ctrlKey && !window.event.altKey) {
        if ((key_code > 47 && key_code < 58) ||
            (key_code > 95 && key_code < 106)) {

            if (key_code > 95)
                 key_code -= (95-47);
            oElement.value = oElement.value;
        } else if(key_code == 8) {
            oElement.value = oElement.value;
        } else if(key_code != 9) {
            event.returnValue = false;
        }
    }
}

function validate()
{
	var x=$('#cntct_msg').attr("value");
	var y=$('#email_msg').attr("value");
	var a=$('#curpwd_msg').attr("value");
	var b=$('#newpwd_msg').attr("value");
	var c=$('#repwd_msg').attr("value");
	if(x*y*a*b*c!=1)
		{
		alert("Something went wrong");
		}
}