$('#name').live("keyup",function(){
    if( ($(this).val()!=''))
    {    	
      $('#name_msg').html('');
      $('#name_msg').attr("value",1);
     }
    else
    	{
    	$('#name_msg').html('Please provide your name');
    	}
      
  });

$('#accno').live("keyup",function(){
    if( ($(this).val()!=''))
    {
    	var reg=/^[0-9]+$/;
    	if(reg.test($(this).val()))
    	{
    		$('#acc_msg').html('');
    		$('#acc_msg').attr("value",1);
    	}
    	else
    		{
    		$('#acc_msg').html('Please provide valid account number');
    		}
    }
    else
      $('#acc_msg').html('Please provide your account number');
     
      
  });

$('#confirmacc').live("keyup",function(){
    if( ($(this).val()==''))
    {      
      $('#cacc_msg').html('Please confirm your account number');
      
     }
    else if(($(this).val()!=$('#accno').val()))
	{
    
	$('#cacc_msg').html('Account Number miss match');
	}
    else
    	{
    	$('#cacc_msg').html('');
    	$('#cacc_msg').attr("value",1);
    	}
      
  });

$('#limit').live("keyup",function(){
    if( ($(this).val()!='')) {
      var pattern=/^[0-9]+$/ ;
      if(pattern.test($(this).val())){   
        $('#limit_msg').html(''); 
        $('#limit_msg').attr("value",1);
         }
      else 
      $('#limit_msg').html('Please provide valid limit');
     
      
      }
      else {$('#limit_msg').html('Please give the limit ');}
  });


function validate()
{
	var x=$('#name_msg').attr("value");	
	var y=$('#acc_msg').attr("value");
	var z=$('#cacc_msg').attr("value");
	var k=$('#limit_msg').attr("value");
	if(x*y*z*k==1)
		{
			return true;
		}	
	else
		{
			alert("Please provide all details");
			return false;
		}
}



