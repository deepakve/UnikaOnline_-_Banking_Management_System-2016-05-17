document.onmousedown=disableclick;
function disableclick(e)
{
  if(event.button==2)
   {
     alert("This function is disabled here");
     return false;    
   }
}
