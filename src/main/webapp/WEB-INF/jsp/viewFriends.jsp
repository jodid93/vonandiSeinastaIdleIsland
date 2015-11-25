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
	    <h1>View Friends</h1>
	    
	    <br>
	    <p>${skilabod}</p>
	    
	    
		<hr>
		<form action="/menu" method="get">
			<p><input type="submit" value="Back" /></p>
		</form>
	</div>
    </body>

</html>