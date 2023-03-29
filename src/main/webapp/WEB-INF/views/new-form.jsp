<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!-- View -->

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<!-- 상대 경로 사용하면 폼 전송 시 [현재 URL이 속한 계층 경로 + /save] 호출 : 경로가 다른 다른 곳에서도 쓰기 위해 사용 -->
<!-- save 가 아닌 /save 를 넣게 되면 url이 http://localhost:8080/save 이렇게 나옴 (절대경로) -->
<!-- http://localhost:8080/servlet-mvc/members/save 이렇게 나오는게 상대경로 -->
<!-- 보통은 절대경로 사용 -->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>

</body>
</html>