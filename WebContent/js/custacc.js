$(document).ready(function() {
    $('#acc_num').keydown(maskInput);
});

$(document).ready(function() {
    $('<span style="color:red;">*</span>').insertAfter('.required');
});

$('#acc_num').live("keyup",function(){
    if( ($(this).val()!='')) {
        
        $('#accno_msg').html(''); 
         }
      else 
    	  {
      $('#accno_msg').html('Please provide Account no').attr("value",1);
     }
      
  });
  


$('#acc_type').live("keyup",function(){
    if( ($(this).val()!='')) {
        
        $('#acctype_msg').html(''); 
         }
      else 
    	  {
      $('#acctype_msg').html('Please provide Account type code').attr("value",1);
     }
      
  });

$('#branch_code').live("keyup",function(){
    if( ($(this).val()!='')) {
        
        $('#brcode_msg').html(''); 
         }
      else 
    	  {
      $('#brcode_msg').html('Please provide Branch code').attr("value",1);
     }
      
  });

$('#amount').live("keyup",function(){
    if( ($(this).val()!='')) {
        var reg=/^[0-9]$/;
        if(pattern.test($(this).val())){  
        $('#amnt_msg').html(''); 
         }
      else 
    	  {
      $('#amnt_msg').html('Please enter the valid amount').attr("value",1);
     }
    }
        else
        	
        {
        	$('#amnt_msg').html('Please enter the amount').attr("value",1);
        	}
        	
      
  });




$('#adminid').live("keyup",function(){
    if( ($(this).val()!='')) {
        
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
		  $('#cpwd_msg').html('');
		  }
	  else
		  {
		  $('#cpwd_msg').html('Password limit is min 8 & max 15').attr("value",1);
		  }
	  
	  
});

$('#newpassword').live("keyup",function(){
	  if(($(this).val()!='')&& ($(this).val().length>7)&& ($(this).val().length<15)) 
		  {
		  $('#npwd_msg').html('');
		  }
	  else
		  {
		  $('#npwd_msg').html('Password limit is min 8 & max 15').attr("value",1);
		  }
	  
	  
});

$('#retypepassword').live("keyup",function(){
	  if(($(this).val()!='')&& ($(this).val().length>7)&& ($(this).val().length<15)&&($(this).val()==$('#newpassword').val())) 
		  {
		  $('#rpwd_msg').html('');
		  }
	  else
		  {
		  $('#rpwd_msg').html('Password mismatch').attr("value",1);
		  }
	  
	  
});




$('#branchcode').live("keyup",function(){
    if( ($(this).val()!='')) {
        
        $('#brcode1_msg').html(''); 
         }
      else 
    	  {
      $('#brcode1_msg').html('Please provide Branch code').attr("value",1);
     }
      
  });

$('#branchaddr1').live("keyup",function(){
    if( ($(this).val()!='')) {
        
        $('#bradd1_msg').html(''); 
         }
      else 
    	  {
      $('#bradd1_msg').html('Please provide Branch address').attr("value",1);
     }
      
  });

$('#branchaddr2').live("keyup",function(){
    if( ($(this).val()!='')) {
        
        $('#bradd2_msg').html(''); 
         }
      else 
    	  {
      $('#bradd2_msg').html('Please provide Branch address').attr("value",1);
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
	var x=$('#accno_msg').attr("value");
	var y=$('#acctype_msg').attr("value");
	var a=$('#brcode_msg').attr("value");
	var b=$('#amnt_msg').attr("value");
	var c=$('#adminid_msg').attr("value");
	var d=$('#cpwd_msg').attr("value");
	var e=$('#npwd_msg').attr("value");
	var f=$('#rpwd_msg').attr("value");
	if(x*y*a*b*c*d*e*f!=1)
		{
		alert("something went wrong");
		}
}