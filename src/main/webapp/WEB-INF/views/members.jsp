<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- View -->

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>"
</head>
<body>
    <a href="/index.html">메인</a>
<table>
    <thead>
        <th>id</th>
        <th>username</th>
        <th>age</th>
    </thead>
    <tbody>

    <c:forEach var="item" items="${members}">  <!-- members 안에 들어있는 걸 하나씩 꺼내서 item에 담음-->
        <tr>
            <td>${item.id}</td>
            <td>${item.username}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>