<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All drivers</title>
</head>
<body>
<h1>All drivers page</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Licence number</th>
    </tr>
    <c:forEach var="driver" items="${drivers}">
        <tr>
            <td>
                <c:out value="${driver.id}"/>
            </td>
            <td>
                <c:out value="${driver.name}"/>
            </td>
            <td>
                <c:out value="${driver.licenceNumber}"/>
            </td>
        </tr>
    </c:forEach>

</table>
<a href="${pageContext.request.contextPath}/">Go to the main page</a>
</body>
</html>
