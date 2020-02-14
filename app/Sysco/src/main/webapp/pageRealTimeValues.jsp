<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Real Time Data</title>
<%@ include file="head.jsp"%>
</head>

	<body>
<%@ include file="entete.jsp"%>

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
<%@ include file="pied.jsp"%>


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
