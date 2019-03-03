<%--
  Created by IntelliJ IDEA.
  User: Mehrdad
  Date: 2019-03-02
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<html>
<head>
    <title>Response</title>
</head>
<body>
<h2>
    <c:out value="${requestScope.res}"/>
</h2>
</body>
</html>
