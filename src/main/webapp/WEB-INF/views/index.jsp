<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Taxi</title>
</head>
<body>
<h1>Taxi service</h1>
<a href="${pageContext.request.contextPath}/manufacturers/create">Create manufacturer</a><br>
<a href="${pageContext.request.contextPath}/cars/create">Create car</a><br>
<a href="${pageContext.request.contextPath}/drivers/">Get all drivers</a><br>
<a href="${pageContext.request.contextPath}/manufacturers/">Get all manufacturers</a><br>
<a href="${pageContext.request.contextPath}/cars/">Get all cars</a><br>
<a href="${pageContext.request.contextPath}/cars/my">Get all my cars</a><br>
<a href="${pageContext.request.contextPath}/cars/drivers/add">Add drivers to car</a>
</body>
</html>
