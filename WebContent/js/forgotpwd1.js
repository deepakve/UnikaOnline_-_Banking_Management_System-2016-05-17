$('#customerid').live("keyup",function(){
	  if(($(this).val()!='')) 
		  {
		  $('#custid_msg').html('');
		  $('#custid_msg').attr("value",1);
		  }
	  else
		  {
		  $('#custid_msg').html('please provide your customer id');
		  }
	  
	  
});


$('#emailid').live("keyup",function(){
	
    if( ($(this).val()!='')) {
      var pattern=/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/ ;
      if(pattern.test($(this).val())){   
        $('#mail_msg').html(''); 
        $('#mail_msg').attr("value",1);
         }
      else 
      $('#mail_msg').html('Please provide valid emailid');
     
      
      }
      else {$('#mail_msg').html('emailid ');}
  });
  
  
$('#contactNo').live("keyup",function(){
    if( ($(this).val()!='')) {
      var pattern=/^\d{10}$/ ;
      if(pattern.test($(this).val())){   
        $('#ctct_msg').html(''); 
        $('#ctct_msg').attr("value",1);
         }
      else 
      $('#ctct_msg').html('Please provide valid phone no');
     
      
      }
      else {$('#ctct_msg').html('Contact No please ');}
  });
  
$(document).ready(function() {
    $('#customerid').keydown(maskInput);
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
	var a=$('#mail_msg').attr("value");
    var b=$('#ctct_msg').attr("value");
    var c=$('#custid_msg').attr("value");
    if(a*b*c==1)
    	{
    	
    	return true;
    	
    	}
    else
    	{
    	alert("provide all the details");
    	return false;
    	
    	}
	
	}