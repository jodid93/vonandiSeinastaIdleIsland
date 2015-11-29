<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>User Page</title>
       	<link rel='stylesheet' href='../../resources/static/css/base.css'></link>
        <link rel='stylesheet' href='../../resources/static/css/viewFriends.css'></link>
    </head>
    <body>
    	<p hidden="hidden" id="gamestate">${gamestate}</p> 
	    <script type="text/javascript" src="../../../resources/static/javascript/jquery.js"></script>
	    <script type="text/javascript" src="../../../resources/static/javascript/soundsPreload2.js"></script>
		<script type="text/javascript" src="../../../resources/static/javascript/playTheme.js"></script>
	
	<h1>View Friends</h1>

	<div class="middle">

		<hr>
	   	
	   	 <c:forEach items="${users}" var="user">
	   	 	<form action="/viewFriends" method="post">
				<input type="submit" name="who" value="View ${user}'s Island" />
			</form>
	    </c:forEach>
	   	
		<hr>
		<form action="/menu" method="get">
			<p><input type="submit" value="Back" /></p>
		</form>
	</div>
    </body>

</html>