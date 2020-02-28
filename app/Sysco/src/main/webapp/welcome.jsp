<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>

        <title>Page d''accueil</title>
        <%@ include file="head.jsp"%>
    </head>

    <body>
        <%@ include file="entete.jsp"%>
        <div class="main">
            <br>
            <h2>Membres de projet</h2>
            <ul>
                <li>Jorge CABRAL</li>
                <li>Hamza FAHIM</li>
                <li>Loïck MOLLET</li>
                <li>Malick SECK</li>
            </ul>
            <h2>Référent pédagogique: Thomas VANTROYS</h2>

            <br>

            <!-- <div class="fakeimg">Fake Image</div> -->
            <p>
            </p>
            <!-- END MAIN -->
        </div>

        <%@ include file="pied.jsp"%>
    </body>
</html>
