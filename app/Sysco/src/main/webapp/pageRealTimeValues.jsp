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
                <select class="custom-select" id="listeSalles" onchange="updateDisplay()">
                    <option selected>Choose Room...</option>
                </select>
            </div>
			<br>
            <div class="row" align="center">
                <div class="column3">
                    <p>Oxygene</p>
                    <div class="divMeasure" id ="Oxygene">
                    </div>
                </div>
                <div class="column3">
                    <p>Monoxyde de carbone</p>
                    <div class="divMeasure" id ="Monoxyde de carbone">
                    </div>
                </div>
                <div class="column3">
                    <p>Dioxyde de carbone</p>
                    <div class="divMeasure" id ="Dioxyde de carbone">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="column4">
                    <p>Temperature</p>
                    <div class="divMeasure" class="divMeasure" id ="Temperature">
                    </div>
                </div>
                <div class="column4">
                    <p>Humidite</p>
                    <div class="divMeasure" id ="Humidite">
                    </div>
                </div>
                <div class="column4">
                    <p>Pression atmospherique</p>
                    <div class="divMeasure" id ="Pression atmospherique">
                    </div>
                </div>
                <div    class="column4">
                    <p>Particules fines</p>
                    <div class="divMeasure" id ="Particules fines">
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
                            callbackFunction(this.responseText);
                        }
                    };

                    xhttp.open('GET', '/api-datas/realtime/room'+room, true);
                    xhttp.overrideMimeType("text/javascript")
                    xhttp.send();
                }
			}

            window.setInterval('updateDisplay()',10000);

            function updateDisplay(){

                getDataFromServer(popValues);
            }


            function popValues(jsonArrayString){



                var jsonArray = JSON.parse(jsonArrayString);
                var dropdownList = document.getElementById("listeSalles");
                var content ='';

                for (var i in jsonArray){

                    var divDisplayer = document.getElementById(jsonArray[i].measure_name);

                    //console.log(divDisplayer);
                    //content += '<option value="' + jsonArray[i].rpi_id.toString() + '">' + jsonArray[i].rpi_id.toString() + '</option>'

				    divDisplayer.innerHTML = jsonArray[i].measure_value;//'<option selected>Choose Room...</option>' + content;
                }
            }

            function ChangePage(arg1){
                window.location.replace(arg1);
            }
        </script>
	</body>
</html>
