<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create manufacturer</title>
</head>
<body>
<h1>Please provide your manufacturer details</h1>

<h4 style="color: green">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/manufacturers/create">
    Please provide manufacturer name<br>
    <input type="text" name="name" required><br>
    Please provide manufacturer country<br>
    <input type="text" name="country" required><br>
    <input type="reset">
    <button type="submit">Register</button>
</form>

<a href="${pageContext.request.contextPath}/">Go to the main page</a>

</body>
</html>
