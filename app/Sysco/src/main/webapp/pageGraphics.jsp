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

			function setRoomList(fileContent){

			    alert(fileContent);
				var listeSalles = [];
				listeSalles = (fileContent.split('\n'));				//récupere la liste des capteurs


				var dropdownList = document.getElementById("listeSalles");
				dropdownList.innerHTML='';
				listeSalles.forEach(function(item) {
                    dropdownList.innerHTML += '<option value=\"'+item+ ">" + item + "</option>"
				});
			}


			function getDataFromServer(callbackFunction){
				var xhttp = new XMLHttpRequest();

				xhttp.onreadystatechange = function(){
					if (this.readyState == 4 && this.status == 200){
						callbackFunction(this.responseText);
					}
				};

				var listRoom =  document.getElementById("listeSalles");
				var listMeasure = document.getElementById("listMeasures");

				var room =listRoom.options[listRoom.selectedIndex].value;                                               //  récupérer la sélection
                var measureName = listMeasure.options[listMeasure.selectedIndex].value; ;                               //  récupérer la sélection

                if(room != "Choose Room..." && measureName != "Choose Sensor..."){
                    xhttp.open('GET', '/api-datas/historic/'+room+'/'+measureName, true);
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

            <div class="input-group">
                <select class="custom-select" id="listeSalles" onclick="getRoomListFromServer(setRoomList)">
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
                    <button class="btn btn-outline-secondary" type="button" onclick="getDataFromServer(showGraphic)">Button</button>
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
