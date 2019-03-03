<%--
  Created by IntelliJ IDEA.
  User: Mehrdad
  Date: 2019-03-03
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<ul>
    <li>id: <c:out value="${u.id}"/></li>
    <li>first name: <c:out value="${u.firstName}"/></li>
    <li>last name: <c:out value="${u.lastName}"/></li>
    <li>jobTitle: <c:out value="${u.jobTitle}"/></li>
    <li>bio: <c:out value="${u.bio}"/></li>
    <li>
        skills:
        <ul>
            <c:forEach var="s" items="${requestScope.uskills}">
            <li>
                <c:out value="${s.name}"/> : <c:out value="${s.point}"/>
                <form action="/joboonja/endorsskills" method="POST" style="display: ${requestScope.display}">
                    <input hidden name="userId" value="${requestScope.u.id}"/>
                    <input hidden name="sName" value="${s.name}"/>
                    <button>Endorse</button>
                </form>
            </li>
            </c:forEach>
        </ul>
    </li>
</ul>
</body>
</html>