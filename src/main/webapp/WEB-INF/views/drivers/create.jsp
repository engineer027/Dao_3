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
    <input type="text" name="licence_number" required><br>
    Please provide login<br>
    <input type="text" name="login" required><br>
    Please provide password<br>
    <input type="password" name="pwd" required><br>
    <input type="reset">
    <button type="submit">Register</button>
</form>
<a href="${pageContext.request.contextPath}/login">Go to the login page</a>
</body>
</html>
