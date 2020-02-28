<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
		<!-- Required meta tags -->
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

		<!-- My CSS -->
		<link href="./CSS/page1.css" rel="stylesheet" type="text/css"/>

		<title> Green World | Home</title>
		<link rel="icon" type="image/png" href="./images/logo2.png">
    </head>

	<body>
        <%@ include file="entete.jsp"%>
		<div class="main">
			<br>
			<h2>Page Not Found:</h2>
			<h2> Error 404 </h2>
			<br>
			<br>
			<!-- <div class="fakeimg">Fake Image</div> -->
			<p>
			</p>
		<!-- END MAIN -->
		</div>

		<!-- END MAIN -->

        <%@ include file="pied.jsp"%>
    </body>
</html>
