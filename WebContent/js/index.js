$(document).ready(function() {
    $('#customerid').keydown(maskInput);
});



$('#customerid').live("keyup",function(){
	  if(($(this).val()!='')) 
		  {
		  $('#custid_msg').html('');
		  $('#custid_msg').attr("value",1);
		  }
	  else
		  {
		  $('#custid_msg').html('');
		  }
	  
	  
});

function validate()
{
	var y=$('#custid_msg').attr("value");
    if(x*y==1)
    	{
    	return true;
    	}
    else
    	{
    	alert("Please provide all details");
    	return false;
    	}
}
function maskInput() {
	
    var key_code = window.event.keyCode;
    var oElement = window.event.srcElement;
    if (!window.event.shiftKey && !window.event.ctrlKey && !window.event.altKey) {
        if ((key_code > 47 && key_code < 58) ||
            (key_code > 95 && key_code < 106)) {

            if (key_code > 95)
                 key_code -= (95-47);
            oElement.value = oElement.value;
        } 
        else if(key_code == 8) 
        {
            oElement.value = oElement.value;
        } 
        else if(key_code != 9) 
        {
            event.returnValue = false;
        }
    }
    
   
}