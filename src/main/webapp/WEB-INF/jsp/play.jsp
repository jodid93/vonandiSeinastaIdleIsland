<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="is">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/../../resources/static/css/game.css"/>
    <!--script(type='text/javascript' src='/../../resources/static/javascript/htmlButtons.js')-->
  </head>
  <body>
    <p hidden="hidden" id="user">${user}</p>
    <p hidden="hidden" id="userData">${userData}</p>
    <p hidden="hidden" id="isFriend">${isFriend}</p>
    <!--h1 IDLE ISLAND-->
    <canvas id="myCanvas" width="auto" height="auto"></canvas>
    <div class="buttons">
      <div class="circle-buttons">
        <div class="upg"></div>
        <form method="post" action="/gameSettings" class="form-settings">
          <div class="sett"></div>
        </form>
      </div>
      <div class="arrows-buttons"></div>
      <!--div.quit-->
    </div>
    <form method="post" action="/exit" id="exit" hidden="hidden">
      <input type="text" id="submitString" name="submitString"/>
      <input type="text" id="score" name="score"/>
      <input type="hidden" id="checkFriend" name="checkFriend"/>
      <div>
        <button class="game-exit"></button>
      </div>
    </form>
    <script src="/../../resources/static/javascript/jquery.js"></script>
    <script src="/../../resources/static/javascript/imagesPreload2.js"></script>
    <script src="/../../resources/static/javascript/soundsPreload2.js"></script>
    <script src="/../../resources/static/javascript/Coconut.js"></script>
    <script src="/../../resources/static/javascript/Sprite.js"></script>
    <script src="/../../resources/static/javascript/htmlButtons.js"></script>
    <script src="/../../resources/static/javascript/Button2.js"></script>
    <script src="/../../resources/static/javascript/Display2.js"></script>
    <script src="/../../resources/static/javascript/UserData2.js"></script>
    <script src="/../../resources/static/javascript/Calculator2.js"></script>
    <script src="/../../resources/static/javascript/gameEngine2.js"></script>
    <script src="/../../resources/static/javascript/Initialize2.js"></script>
	
	  <div class="backgrounds">
	    <div class="sky"></div>
	    <div class="sea"></div>
	    <div class="mine-wall"></div>
	    <div class="mine-floor"></div>
	  </div>
  </body>



</html>