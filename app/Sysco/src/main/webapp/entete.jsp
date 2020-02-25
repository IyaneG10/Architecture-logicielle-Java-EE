<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="./JS/jquery-1.11.1.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

      	<div class="jumbotron text-center" style="margin-bottom:0;padding-top: 2rem;padding-bottom: 1rem;" onclick="ChangePage('welcome.jsp');">
            <h1>GREEN WORLD</h1>
            <p>Projet Architecture Logicielle JEE: Smart Eco Rooms</p>
        </div>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">

            <a class="navbar-brand-left" href="welcome.jsp">
                <img src="./images/H.png" width="30" height="25" alt="">
            </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="pageRealTimeValues.jsp">
                            Valeurs instantannées
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="pageGraphics.jsp">Historique des valeurs
                        </a>
                    </li>
                </ul>
                <span class="navbar-text">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <form class="form-inline my-2 my-lg-0" id="logoutForm" method="POST" action="${contextPath}/logout">
                            <button class="btn btn-outline-success my-2 my-sm-0"  name="${_csrf.parameterName}" value="${_csrf.token}">
                                    Déconnecter, ${pageContext.request.userPrincipal.name}
                            </button>
                        </form>
                    </c:if>
                </span>
            </div>
        </nav>
    </head>
    </body>
</html>