<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <title>Real Time Data</title>
        <%@ include file="head.jsp"%>
		<link href="./CSS/pageRealTimeValues.css" rel="stylesheet" type="text/css"/>
    </head>

	<body onload ="getRoomListFromServer(setRoomList)">

        <%@ include file="entete.jsp"%>

		<div class="main" align="center">
            <br>
            <h2>Valeurs instantanées :</h2>
			<br>

            <div class="input-group">
                <select class="custom-select" id="listeSalles">
                    <option selected>Choose Room...</option>
                </select>
            </div>

			<br>


            <div class="row" align="center">
                <div class="column3">
                    <p>Oxygene</p>
                    <div>
                        <canvas id="display1" width="200" height="60"></canvas>
                    </div>
                </div>
                <div class="column3">
                    <p>Monoxyde de carbone</p>
                    <div>
                        <canvas id="display2" width="200" height="60"></canvas>
                    </div>
                </div>
                <div class="column3">
                    <p>Dioxyde de carbone</p>
                    <div>
                        <canvas id="display33" width="200" height="60"></canvas>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column4">
                    <p>Temperature</p>
                    <div>
                        <canvas id="display4" width="200" height="60"></canvas>
                    </div>
                </div>
                <div class="column4">
                    <p>Humidite</p>
                    <div>
                        <canvas id="display5" width="200" height="60"></canvas>
                    </div>
                </div>
                <div class="column4">
                    <p>Pression atmospherique</p>
                    <div>
                        <canvas id="display6" width="200" height="60"></canvas>
                    </div>
                </div>
                <div class="column4">
                    <p>Particules fines</p>
                    <div>
                        <canvas id="display3" width="200" height="60"></canvas>
                    </div>
                </div>
            </div>

	    	<!-- END MAIN -->
        </div>

        <br>
        <br>
        <br>
        <br>
        <%@ include file="pied.jsp"%>

        <!-- 16 Segment Display -->
        <!--[if IE]>
            <script type="text/javascript" src="./JS/excanvas.js"></script>
        <![endif]-->
        <script type="text/javascript" src="./JS/segment-display.js"></script>

        <script type="text/javascript">


			function getRoomListFromServer (callbackFunction){
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function(){
                    if (this.readyState == 4 && this.status == 200){
                        callbackFunction(this.responseText);
                     }
                };

                xhttp.open('GET', '/api-datas/historic/roomlist', true);
                xhttp.overrideMimeType("text/javascript")
                xhttp.send();
            }


			function setRoomList(response){

                var jsonData = JSON.parse(response);
				var dropdownList = document.getElementById("listeSalles");
				var content ='';

				for (var i in jsonData){

                    //alert( jsonData[i].rpi_id);

				    content += '<option value="' + jsonData[i].rpi_id.toString() + '">' + jsonData[i].rpi_id.toString() + '</option>'
				}
				dropdownList.innerHTML = '<option selected>Choose Room...</option>' + content;
			}



			function getDataFromServer(callbackFunction){

				var listRoom =  document.getElementById("listeSalles");
				var room =listRoom.options[listRoom.selectedIndex].text ;                                               //  récupérer la sélection

                if(room != "Choose Room..."){

                    var xhttp = new XMLHttpRequest();

                    xhttp.onreadystatechange = function(){
                        if (this.readyState == 4 && this.status == 200){
                            callbackFunction(this.responseText, measureName);
                        }
                    };

                    xhttp.open('GET', '/api-datas/realtime/room'+room, true);
                    xhttp.overrideMimeType("text/javascript")
                    xhttp.send();
                }
                else{
                    alert("Chosose a room and a type of sensor");
                }
			}

            var dispValue1=0;
            var dispValue2=-10;
            var dispValue3=5;

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
            display1.draw();

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

            var display3 = new SegmentDisplay("display3");
            display3.pattern         = "###";
            display3.displayAngle    = 7.5;
            display3.digitHeight     = 47.5;
            display3.digitWidth      = 25;
            display3.digitDistance   = 4.2;
            display3.segmentWidth    = 2.9;
            display3.segmentDistance = 1.5;
            display3.segmentCount    = 16;
            display3.cornerType      = 3;
            display3.colorOn         = "rgba(0, 0, 0, 1)";
            display3.colorOff        = "rgba(0, 0, 0, 0)";
            display3.draw();

            display1.setValue("'"+dispValue1+"'");
            display2.setValue("'"+dispValue2+"'");
            display3.setValue("'"+dispValue3+"'");

            //window.setInterval('updateDisplay()',10000);
            window.setInterval('animateDisplay1()', 500);
            window.setInterval('animateDisplay2()', 1000);
            window.setInterval('animateDisplay3()', 2000);


            function updateDisplay(){

                getDataFromServer(popValues);
            }


            function popValues(){


            }

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


            function animateDisplay3() {
                display3.setValue("'"+dispValue3+"'");
                dispValue3++;
                if(dispValue3>49)dispValue3=0;
            }


            function ChangePage(arg1){
                window.location.replace(arg1);
            }
        </script>
	</body>
</html>
