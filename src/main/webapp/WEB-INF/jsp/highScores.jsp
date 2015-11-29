<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" >

    <head>
        <title>User Page</title>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'></link>
    </head>
    <body>
	
	<div class="col-md-offset-2">
	    <h1>HIGH SCORES</h1>
	    
	    <hr>
		<form action="/menu" method="get">
			<p><input type="submit" value="Back" /></p>
		</form>
	    
	    <br>
	    <h3> Choose which highscores you want to see<h3>
	    
	    <form action="/highScores" method="post">
			<select name="select">
			  <option value="1">All users (global)</option> 
			  <option value="2">Your friends</option>
			</select>
			
			<p><input type="submit" value="Choose" /></p>
			
		</form>
    </div>
    <div>
    	<table class="table table-striped">
		  <thead>
		  	<tr>
		  		<th>User</th>
		  		<th>Score</th>
		  	</tr>
		  </thead>
		  
		   <c:forEach items="${data}" var="dats">
		   		<tr>
			  		<th>${dats.key}</th>
			  		<th>${dats.value}</th>
			  	</tr>
		    </c:forEach>
		    
		  
		</table>
    </div>

	<hr>
	<div class="col-md-offset-2">
	
		<form action="/menu" method="get">
			<p><input type="submit" value="Back" /></p>
		</form>
	</div>
    </body>

</html>