<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>LogIn</title>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'></link>
    </head>
    <body>
	
	<div class="col-md-offset-2">
		<form:errors path="notandi.*"/>
	
	    <h1>Please enter your username and password</h1>
	    
	    <form action="/login" method="post">
	    <h3>${skilabod}</h3>
			<p>User name: <input type="text" name="UserName" /></p>
			
			<p>Password: <input type="password" name="PW" /></p>
			
			<p><input type="submit" value="Login" /></p>
			
		</form>
		
		
		<form action="/newRegestry" method="post">
			
			<p><input type="submit" value="New User" /></p>
			
		</form>
    </div>
    </body>
    <footer>Class HBV501G, University of Iceland, Fall 2015</footer>
</html>
