<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <%@ include file="head.jsp"%>
        <title>Real Time Data</title>
    </head>

	<body>
        <%@ include file="entete.jsp"%>

		<div class="main">
            <br>
			<h2>Valeurs instantanées :</h2>
			<div>
			    <p>Oxygene</p>
				<canvas id="display1" width="200" height="60"></canvas>

			    <p>Monoxyde de carbone</p>
				<canvas id="display2" width="200" height="60"></canvas>

			    <p>Dioxyde de carbone</p>
				<canvas id="display3" width="200" height="60"></canvas>
			</div>

			<div>
			    <p>Temperature</p>
				<canvas id="display4" width="200" height="60"></canvas>

			    <p>Humidite</p>
				<canvas id="display5" width="200" height="60"></canvas>

			    <p>Pression atmospherique</p>
				<canvas id="display6" width="200" height="60"></canvas>

			    <p>Particules fines</p>
				<canvas id="display7" width="200" height="60"></canvas>
			</div>
		</div>

        <!-- END MAIN -->
        <%@ include file="pied.jsp"%>

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
