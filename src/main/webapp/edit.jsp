<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 16.12.2022
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="index.html"></jsp:include>
<jsp:include page="footerList.html"></jsp:include>
<h5>Edytuj użytkownika</h5>
<form method="get" action="/edit">
  Id użytkownika<br/>
  <input type="text" name="id"><br/>
  Nazwa użytkownika <br/>
  <input type="text" name="username"><br/>
  E-mail <br/>
  <input type="text" name="email"><br/>
  <button type="submit">Zapisz</button>

</form>
</body>
</html>
