<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

		<!-- My CSS -->
		<link href="./CSS/page1.css" rel="stylesheet" type="text/css"/>

		<title> Green World | Page d'accueil'</title>
		<link rel="icon" type="image/png" href="./images/logo2.png">
</head>

	<body>
		<div cla
  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Connecté en tant que: ${pageContext.request.userPrincipal.name}</h2>
    </c:if>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

  	<div class="jumbotron text-center" style="margin-bottom:0;padding-top: 2rem;padding-bottom: 1rem;" onclick="ChangePage('welcome.jsp');">
  			<h1>GREEN WORLD</h1>
  			<p>Weather Station: Pollution</p>
  		</div>

  		<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
  			<a class="navbar-brand" href="welcome.jsp">
  				<img src="./images/H.png" width="30" height="25" alt="">
  			</a>

  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
  				<span class="navbar-toggler-icon"></span>
  			</button>

			  <div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
						<a class="nav-link" href="pageRealTimeValues.jsp">
							Valeurs Actuelles
						</a>
					</li>

					<li class="nav-item ">
						<a class="nav-link" href="pageGraphics.jsp">Historique
						</a>
					</li>
				</ul>
				<form class="form-inline my-2 my-lg-0">
				  <button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="document.forms['logoutForm'].submit()">Deconnexion</button>
				</form>
			</div>
  		</nav>

		<div class="main">
            <br>
			<h2>Valeurs instantanées :</h2>
			<p>Valeur Capteur 1 :</p>
			<div>
				<canvas id="display1" width="200" height="60"></canvas>
			</div>

			<p>Valeur Capteur 2 :</p>
			<div>
				<canvas id="display2" width="200" height="60"></canvas>
			</div>

		<!-- END MAIN -->
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div class="jumbotron text-center" style="margin-bottom:0;padding-bottom: 1rem;padding-top: 1rem;">
			<img src="./images/logoPolytech.png" alt="">
			<img src="./images/logoUnivLille.png" alt="">
		</div>


		<!-- Optional JavaScript -->

		<!-- jQuery first, then Popper.js, then Bootstrap JS -->

		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
		</script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous">
		</script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
		</script>

		<!-- 16 Segment Display -->
		<!--[if IE]>
			<script type="text/javascript" src="./JS/excanvas.js"></script>
		<![endif]-->
		<script type="text/javascript" src="./JS/segment-display.js"></script>

		<script type="text/javascript">

			function ChangePage(arg1){
				window.location.replace(arg1);
			}
			var dispValue1=0;
			var dispValue2=-10;

			var display1 = new SegmentDisplay("display1");
			display1.pattern         = "###";
			display1.displayAngle    = 7.5;
			display1.digitHeight     = 47.5;
			display1.digitWidth      = 25;
			display1.digitDistance   = 4.2;
			display1.segmentWidth    = 2.9;
			display1.segmentDistance = 1.5;
			display1.segmentCount    = 16;
			display1.cornerType      = 3;
			display1.colorOn         = "rgba(0, 0, 0, 1)";
			display1.colorOff        = "rgba(0, 0, 0, 0)";
			//display1.draw();

			var display2 = new SegmentDisplay("display2");
			display2.pattern         = "####";
			display2.displayAngle    = 7.5;
			display2.digitHeight     = 47.5;
			display2.digitWidth      = 25;
			display2.digitDistance   = 4.2;
			display2.segmentWidth    = 2.9;
			display2.segmentDistance = 1.5;
			display2.segmentCount    = 16;
			display2.cornerType      = 3;
			display2.colorOn         = "rgba(100, 100, 0, 1)";
			display2.colorOff        = "rgba(0, 0, 0, 0)";
			display2.draw();

			display1.setValue("'"+dispValue1+"'");
			display2.setValue("'"+dispValue2+"'");

			window.setInterval('animateDisplay1()', 500);
			window.setInterval('animateDisplay2()', 1000);

			function animateDisplay1() {
				dispValue1+=1;
				if(dispValue1>=9)dispValue1=0;
				display1.setValue("'"+dispValue1+"'");


			}

			function animateDisplay2() {
				display2.setValue("'"+dispValue2+"'");
				dispValue2++;
				if(dispValue2>99)dispValue2=0;
			}
		</script>
	</body>
</html>
