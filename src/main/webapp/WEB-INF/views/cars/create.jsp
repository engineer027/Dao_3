<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create car</title>
</head>
<body>
<h1>Please provide your car details</h1>

<h4 style="color: green">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/cars/create">
    Please provide model name<br>
    <input type="text" name="model" required><br>
    Please provide manufacturer id<br>
    <input type="number" name="manufacturerId" required><br>
    <input type="reset">
    <button type="submit">Register</button>
</form>

<a href="${pageContext.request.contextPath}/">Go to the main page</a>

</body>
</html>
