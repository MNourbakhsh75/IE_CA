<%--
  Created by IntelliJ IDEA.
  User: Mehrdad
  Date: 2019-03-02
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Projects</title>
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
<fmt:requestEncoding value = "ISO-8859-1" />
<table>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>budget</th>
    </tr>
    <c:forEach var="p" items="${requestScope.showProjects}">
        <tr>
            <td><c:out value="${p.id}"/></td>
            <td><c:out value="${p.title}"/></td>
            <td><c:out value="${p.budget}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
