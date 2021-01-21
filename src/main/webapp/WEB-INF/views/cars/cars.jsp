<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All cars</title>
</head>
<body>
<h1>All cars page</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Model</th>
        <th>Manufacturer</th>
        <th>Drivers</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <c:out value="${car.id}"/>
            </td>
            <td>
                <c:out value="${car.model}"/>
            </td>
            <td>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>name</th>
                        <th>Country</th>
                    </tr>
                    <tr>
                        <td>
                            <c:out value="${car.manufacturer.id}"/>
                        </td>
                        <td>
                            <c:out value="${car.manufacturer.name}"/>
                        </td>
                        <td>
                            <c:out value="${car.manufacturer.country}"/>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>name</th>
                        <th>Licence number</th>
                    </tr>
                    <c:forEach var="driver" items="${car.drivers}">
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
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/cars/delete?id=${car.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
