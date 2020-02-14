<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


      <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <h2>Connecté en tant que: ${pageContext.request.userPrincipal.name} </h2>
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
    					<li class="nav-item">
    						<a class="nav-link" href="pageRealTimeValues.jsp">
    							Valeurs Actuelles
    						</a>
    					</li>

    					<li class="nav-item">
    						<a class="nav-link" href="pageGraphics.jsp">Historique
    						</a>
    					</li>
    				</ul>
    				<form class="form-inline my-2 my-lg-0">
    				  <button class="btn btn-outline-success my-2 my-sm-0" type="submit" onclick="document.forms['logoutForm'].submit()">Deconnexion</button>
    				</form>
    			</div>
      		</nav>






</head>
</body>
</html>