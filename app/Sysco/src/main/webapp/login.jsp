<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>

        <title>Log in with your account</title>

        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>

    <body>

        <div class="jumbotron text-center" style="margin-bottom:0;padding-top: 2rem;padding-bottom: 1rem;" onclick="ChangePage('welcome.jsp');">
            <h1>GREEN WORLD</h1>
            <p>Projet Architecture Logicielle JEE: Smart Eco Rooms</p>
        </div>

        <div class="container">
            <form method="POST" action="${contextPath}/login" class="form-signin">
                <h2 class="form-heading">Connexion</h2>

                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <span>${message}</span>
                    <input name="username" type="text" class="form-control" placeholder="Login"
                           autofocus="true"/>
                    <input name="password" type="password" class="form-control" placeholder="Mot de passe"/>
                    <span>${error}</span>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
                    <h4 class="text-center"><a href="${contextPath}/registration">Créer un compte</a></h4>
                </div>
            </form>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </body>
</html>
