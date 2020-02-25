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

	<body>

        <%@ include file="entete.jsp"%>

		<script>

		    var roomSelected = null;
		    
			function setRoomList(fileContent){

				var listeSalles = [];
				listeSalles = (fileContent.split('\n'));				//récupere la liste des capteurs


				var dropdownList = document.getElementById("listeSalles");
				dropdownList.innerHTML='';
				listeSalles.forEach(function(item, index) {

					dropdownList.innerHTML += '<button class="dropdown-item" type="button" onclick=\'getFilesOnServer("./FilesSensors/'+item+".json"+'",showGraphic)\'>'+item+'</button>';
				});
			}


			function getDataFromServer(callbackFunction){
				var xhttp = new XMLHttpRequest();

				xhttp.onreadystatechange = function(){
					if (this.readyState == 4 && this.status == 200){
						callbackFunction(this.responseText);
					}
				};
				var room = "";              //  récupérer la sélection du dropdownMenu2
				var measureName = "";       //  récupérer la sélection du dropdownMenu3

                xhttp.open('GET', '/api-datas/historic/'+room+'/'+measureName, true);
                xhttp.overrideMimeType("text/javascript")
                xhttp.send(data);
			}


			function getRoomListFromServer (callbackFunction){
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function(){
                    if (this.readyState == 4 && this.status == 200){
                        callbackFunction(this.responseText);
                    }
                };

                './api-datas/historic/

                xhttp.open('GET', '/api-datas/historic/roomlist', true);
                xhttp.overrideMimeType("text/javascript")
                xhttp.send();
            }


			function  showGraphic(jsonTexte) {

				var jsonData = JSON.parse(jsonTexte);
				var dps = [];
				var unity = " kg/m²";

				for (i in jsonData.DATA) {
					dps.push({
						x:  new Date(jsonData.DATA[i].TIME), y: jsonData.DATA[i].Y
					});
				}

				//Better to construct options first and then pass it as a parameter
				var options = {
					title: {
						text: "Historique Journalier"
					},
					animationEnabled: true,
					exportEnabled: true,
					zoomEnabled: true,
					axisY: {
						title: "Taux CO2",
						valueFormatString: "#0.",
						suffix: unity,
						/*stripLines: [{
							value: 3366500,
							label: "Average"
						}]*/
					},
					data: [
					{
						yValueFormatString: "###0.###"+unity,
						xValueFormatString: "YYYY/MM/DD à HH:mm",
						type: "spline",
						dataPoints: dps
					}
					]
				};


				var chart = new CanvasJS.Chart("chart-container",options);

				chart.render();

			}
		</script>


		<div class="main">
			<p>
				Vous pouvez selectionner une salle et la plage temporelle directement sur le graphique
			</p>
			<br>

			<div class="btn-group dropleft" >
				<a class="btn btn-secondary dropdown-toggle"href="#"  type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
				 onclick="showGraphic('{}')" onload="getRoomListFromServer(setRoomList)">
					Salles
				</a>
				<div id="listeSalles" class="dropdown-menu"  aria-labelledby="dropdownMenu2">
				</div>
			</div>
			<br>
			<br>

            <div class="btn-group dropleft">
                <a class="btn btn-secondary dropdown-toggle" onclick="getDataFromServer(showGraphic)" role="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Mesures
                </a>
                <div id="listMeasures" class="dropdown-menu" aria-labelledby="dropdownMenu3">
                    <a class="dropdown-item" href="#">  Oxygene                 </a>
                    <a class="dropdown-item" href="#">  Monoxyde de carbone     </a>
                    <a class="dropdown-item" href="#">  Dioxyde de carbone      </a>
                    <a class="dropdown-item" href="#">  Temperature             </a>
                    <a class="dropdown-item" href="#">  Humidite                </a>
                    <a class="dropdown-item" href="#">  Pression atmospherique  </a>
                    <a class="dropdown-item" href="#">  Particules fines        </a>
                </div>
            </div>

			<br>
			<div id="chart-container" style="height: 370px; max-width: 100%;">
			  <canvas id="mycanvas"></canvas>
			</div>
		</div>
		<!-- END MAIN -->

        <%@ include file="pied.jsp"%>
	</body>
</html>
