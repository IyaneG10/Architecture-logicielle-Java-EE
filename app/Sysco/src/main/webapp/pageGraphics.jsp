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
    </head>

	<body>

        <%@ include file="entete.jsp"%>

		<script>
			function scanSensorOnNetwork(fileContent){

				var listeSalles = [];
				listeSalles = (fileContent.split('\n'));				//récupere la liste des capteurs


				var dropdownList = document.getElementById("listeSalles");
				dropdownList.innerHTML='';
				listeSalles.forEach(function(item, index) {

					dropdownList.innerHTML += '<button class="dropdown-item" type="button" onclick=\'getFilesOnServer("./FilesSensors/'+item+".json"+'",showGraphic)\'>'+item+'</button>';
				});
			}


			function getFilesOnServer(url, callbackFunction){
				var xhttp = new XMLHttpRequest();

				xhttp.onreadystatechange = function(){
					if (this.readyState == 4 && this.status == 200){
						callbackFunction(this.responseText);
					}
				};

				xhttp.open("GET", url, true);
				xhttp.overrideMimeType("text/plain")
				xhttp.send();
			}


			function  showGraphic(jsonTexte) {

				var jsonData = JSON.parse(jsonTexte+"]}");
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
			<div class="dropdown"  align="right"  onclick="getFilesOnServer('./FilesSensors/listeSalles.txt',scanSensorOnNetwork)">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Salles
				</button>
				<div id="listeSalles" class="dropdown-menu" aria-labelledby="dropdownMenu2">

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
