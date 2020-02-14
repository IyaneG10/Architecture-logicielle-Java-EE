<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Graphics Data</title>
<%@ include file="head.jsp"%>


</head>
	<body>


<%@ include file="entete.jsp"%>


		<script>
			function scanSensorOnNetwork(fileContent){

				var listeCapteurs = [];
				listeCapteurs = (fileContent.split('\r\n'));				//récupere la liste des capteurs


				var dropdownList = document.getElementById("listeCapteurs");
				dropdownList.innerHTML='';
				listeCapteurs.forEach(function(item, index) {

					dropdownList.innerHTML += '<button class="dropdown-item" type="button" onclick=\'getFilesOnServer("./FilesSensors/'+item+".txt"+'",showGraphic)\'>'+item+'</button>';
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
			<br>
			<p>
				Vous pouvez selectionner un capteur et la plage temporelle directement sur le graphique
			</p>
			<br>
			<div class="dropdown"  align="right"  onclick="getFilesOnServer('./FilesSensors/listeCapteurs.txt',scanSensorOnNetwork)">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Capteurs
				</button>
				<div id="listeCapteurs" class="dropdown-menu" aria-labelledby="dropdownMenu2">

				</div>
			</div>


			<br>
			<div id="chart-container" style="height: 370px; max-width: 100%;">
			  <canvas id="mycanvas"></canvas>
			</div>
		</div>
		<!-- END MAIN -->

<%@ include file="pied.jsp"%>


		<!-- Optional JavaScript -->

		<!-- jQuery first, then Popper.js, then Bootstrap JS -->

		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
		</script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous">
		</script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
		</script>

		 <script type="text/javascript">
			function ChangePage(arg1){
			window.location.replace(arg1);
			}
		</script>
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	</body>
</html>
