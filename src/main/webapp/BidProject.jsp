<%--
  Created by IntelliJ IDEA.
  User: Mehrdad
  Date: 2019-03-02
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html lang="fa">
<head>
    <meta charset="UTF-8">
    <title>Project</title>
</head>
<body>
<ul>
    <li><c:out value="id : ${requestScope.project.id}"/></li>
    <li><c:out value="title : ${requestScope.project.title}"/></li>
    <li><c:out value="description : ${requestScope.project.description}"/></li>
    <li>imageUrl: <img src="${requestScope.project.imageUrl}" style="width: 50px; height: 50px;"></li>
    <li><c:out value="budget : ${requestScope.project.budget}"/></li>
</ul>
<!-- display form if user has not bidded before -->
<form action="/joboonja/bidcheckamount" method="POST" style="display: ${requestScope.display}">
    <label for="bidAmount">Bid Amount:</label>
    <input type="number" name="bidAmount">
    <input hidden name="projectId" value="${requestScope.project.id}"/>
    <button>Submit</button>
</form>
</body>
</html>
