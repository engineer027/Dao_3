<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: red">${errorMsg}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    Please provide your login:<br>
    <input type="text" name="login" required><br>
    Please provide your password:<br>
    <input type="password" name="pwd" required><br>
    <input type="reset">
    <button type="submit">Login</button><br>
    <a href="${pageContext.request.contextPath}/drivers/create">Create new driver</a>
</form>
</body>
</html>
