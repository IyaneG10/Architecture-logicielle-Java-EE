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
  			<h2>SysCo</h2>
  			<h2> Architechture Logicielle </h2>
  			<h5>Smart Eco Rooms</h5>
  			<br>
  			<br>
  			<!-- <div class="fakeimg">Fake Image</div> -->
  			<p>
  			</p>
  		<!-- END MAIN -->
  		</div>

<%@ include file="pied.jsp"%>




  		<script type="text/javascript">

  			function ChangePage(arg1){
  			window.location.replace(arg1);
  			}

  		</script>
    </body>
</html>
