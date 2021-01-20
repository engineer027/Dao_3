<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create driver</title>
</head>
<body>
<h1>Please provide your driver details</h1>

<h4 style="color: green">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/drivers/create">
    Please provide driver name<br>
    <input type="text" name="name" required><br>
    Please provide licence number<br>
    <input type="text" name="licenceNumber" required><br>
    <input type="reset">
    <button type="submit">Register</button>
</form>

<a href="${pageContext.request.contextPath}/">Go to the main page</a>

</body>
</html>
