$('#answer1').live("keyup",function(){
    if( ($(this).val()!=''))
    {
    	$('#ans1_msg').html('');
    	$('#ans1_msg').attr("value",1);
    }
    	else
    		{
    		$('#ans1_msg').html('Please give your answer');
    		}
     });

$('#answer2').live("keyup",function(){
    if( ($(this).val()!=''))
    {
    	$('#ans2_msg').html('');
    	$('#ans2_msg').attr("value",1);
     }
    else
	{
	$('#ans2_msg').html('Please give your answer');
	}
      
  });

function validate()
{
	var a=$('#ans1_msg').attr("value");
    var b=$('#ans2_msg').attr("value");
    if(a*b==1)
    	{
    	return true;
    	}
    else
    	{
    	alert("Please provide all the details");
    	return false;
    	}
	
	}