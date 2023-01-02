<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 15.12.2022
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="theme/css/sb-admin-2.min.css" rel="stylesheet">
    <title>Informacje o użytkowniku</title>
</head>
<body>
<jsp:include page="index.html"></jsp:include>
<jsp:include page="footerList.html"></jsp:include>
<h5>Informacje o użytkowniku</h5>

<c:forEach var="user" items="${users}">
    Id: ${user.id} Username: ${user.userName} Email: ${user.email}
    <a href="/remove?id=${user.id}">
        <button class="btn">
            Usuń
        </button>
    </a>
    <a href="/edit?id=${user.id}">
        <button class="btn">
            Edytuj
        </button>
    </a>
    <br/>
</c:forEach>

<br/>
</body>
</html>
