function validateLogin()
{
	$.ajax({
		 type: "GET",
		 url: "LoginValidateServlet",
		 data: {}
		 }).done(function( result ) {
		 });
}