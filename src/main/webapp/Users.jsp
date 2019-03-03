<%--
  Created by IntelliJ IDEA.
  User: Mehrdad
  Date: 2019-03-03
  Time: 6:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <style>
        table {
            text-align: center;
            margin: 0 auto;
        }
        td {
            min-width: 300px;
            margin: 5px 5px 5px 5px;
            text-align: center;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>jobTitle</th>
    </tr>
    <c:forEach var="u" items="${requestScope.users}">
        <tr>
            <td><c:out value="${u.id}"/></td>
            <td><c:out value="${u.firstName} ${u.lastName}"/></td>
            <td><c:out value="${u.jobTitle}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
