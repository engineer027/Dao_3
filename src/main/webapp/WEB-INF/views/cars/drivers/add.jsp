<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver to car</title>
</head>
<body>
<h1>Please provide your car details</h1>
<h4 style="color: green">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
    Please provide driver id<br>
    <input type="number" name="driver_id" required><br>
    Please provide car id<br>
    <input type="number" name="car_id" required><br>
    <input type="reset">
    <button type="submit">Register</button>
</form>
<a href="${pageContext.request.contextPath}/">Go to the main page</a>
</body>
</html>
