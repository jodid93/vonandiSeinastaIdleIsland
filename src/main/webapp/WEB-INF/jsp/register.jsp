<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>User Page</title>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'></link>
    </head>
    <body>

	<div class="col-md-offset-2">
	    <h1>New user</h1>
	    
	    <h1>Please enter a username and password</h1>
	    
	    <h3>${skilabod}</h3>
	    <form action="/registered" method="post">
			<p>User name: <input type="text" name="UserName" /></p>
			
			<p>Password: <input type="text" name="PW" /></p>
			
			<p><input type="submit" value="Submit" /></p>
			
		</form>
	    
		<hr>
		<form action="/login" method="get">
			<p><input type="submit" value="Back" /></p>
		</form>
	</div>
    </body>

</html>