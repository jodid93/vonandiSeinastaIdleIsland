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
		  
		  <tr>
		  	<td>${user1}</td>
		  	<td>${score1}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user2}</td>
		  	<td>${score2}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user3}</td>
		  	<td>${score3}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user4}</td>
		  	<td>${score4}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user5}</td>
		  	<td>${score5}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user6}</td>
		  	<td>${score6}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user7}</td>
		  	<td>${score7}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user8}</td>
		  	<td>${score8}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user9}</td>
		  	<td>${score9}</td>
		  </tr>
		  
		  <tr>
		  	<td>${user10}</td>
		  	<td>${score10}</td>
		  </tr>
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