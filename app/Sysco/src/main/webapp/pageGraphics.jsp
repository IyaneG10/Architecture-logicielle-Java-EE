<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Graphics Data</title>
        <%@ include file="head.jsp"%>
        <script src="./JS/canvasjs.min.js"></script>
    </head>

	<body onload ="getRoomListFromServer(setRoomList)">

        <%@ include file="entete.jsp"%>

		<script>

			function setRoomList(response){

                var jsonData = JSON.parse(response);


				var dropdownList = document.getElementById("listeSalles");

				var content ='';

				for (var i in jsonData){

                    //alert( jsonData[i].rpi_id);

				    content += '<option value="' + jsonData[i].rpi_id.toString() + '">' + jsonData[i].rpi_id.toString() + '</option>'
				}


				dropdownList.innerHTML = '<option selected>Choose Room...</option>' + content;

				//alert(dropdownList.innerHTML);
			}


			function getDataFromServer(callbackFunction){
				var listRoom =  document.getElementById("listeSalles");
				var listMeasure = document.getElementById("listMeasures");

				var room =listRoom.options[listRoom.selectedIndex].text ;                                               //  récupérer la sélection
                var measureName = listMeasure.options[listMeasure.selectedIndex].text; ;                               //  récupérer la sélection

                if(room != "Choose Room..." && measureName != "Choose Sensor..."){

                    var xhttp = new XMLHttpRequest();

                    xhttp.onreadystatechange = function(){
                        if (this.readyState == 4 && this.status == 200){
                            callbackFunction(this.responseText, measureName);
                        }
                    };

                    xhttp.open('GET', '/api-datas/historic/room'+room+'/'+measureName, true);
                    xhttp.overrideMimeType("text/javascript")
                    xhttp.send();
                }
                else{
                    alert("Chosose a room and a type of sensor");
                }
			}


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


			function  showGraphic(jsonTexte, measureName) {

				var jsonData = JSON.parse(jsonTexte);
				var dps = [];
				var titleList = [
				    "Oxygene",
                    "Monoxyde de carbone",
                    "Dioxyde de carbone",
                    "Temperature",
                    "Humidite",
                    "Pression atmospherique",
                    "Particules fines"];
				var unityList = [
                    "%",
                    "%",
                    "%",
                    "°C",
                    "%",
                    "hpa",
                    "kg/m3"];

                var listMeasure = document.getElementById("listMeasures");
                var index = listMeasure.options[listMeasure.selectedIndex].value -1;

				for (var i in jsonData){

                   //alert( jsonData[i].rpi_id);

					dps.push({
						x: new Date(jsonData[i].date), y: jsonData[i].measure_value
					});
                }

				console.log(dps);


				//Better to construct options first and then pass it as a parameter
				var options = {
					title: {
						text: "Historique " + titleList[index]
					},
					animationEnabled: true,
					exportEnabled: true,
					zoomEnabled: true,
					axisY: {
						title: titleList[index],
						//valueFormatString: "#.0#",
						suffix: unityList[index]
					},
					data: [
					{
						yValueFormatString: '#,##0.##" ' + unityList[index] + '"',
						xValueFormatString: "YYYY/MM/DD à HH:mm:ss",
						type: "spline",
						dataPoints: dps
					}
					]
				};

				console.log(options);

				var chart = new CanvasJS.Chart("chart-container",options);

				chart.render();

			}
		</script>


		<div class="main">
			<p>
				Vous pouvez selectionner une salle et la plage temporelle directement sur le graphique
			</p>
			<br>

            <div class="input-group">
                <select class="custom-select" id="listeSalles">
                    <option selected>Choose Room...</option>
                </select>

                <select class="custom-select" id="listMeasures">
                    <option selected>Choose Sensor...</option>
                    <option value="1">  Oxygene                 </a>
                    <option value="2">  Monoxyde de carbone     </a>
                    <option value="3">  Dioxyde de carbone      </a>
                    <option value="4">  Temperature             </a>
                    <option value="5">  Humidite                </a>
                    <option value="6">  Pression atmospherique  </a>
                    <option value="7">  Particules fines        </a>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" onclick="getDataFromServer(showGraphic)">Valider</button>
                </div>
            </div>

			<br>
			<br>
			<br>
			<div id="chart-container" style="height: 370px; max-width: 100%;">
			  <canvas id="mycanvas"></canvas>
			</div>
		</div>
        <br>
        <br>
		<!-- END MAIN -->

        <%@ include file="pied.jsp"%>
	</body>
</html>
