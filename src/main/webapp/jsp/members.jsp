<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>

<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = MemberRepository.findAll();
%>

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

        <%
        for (Member member : members) {  //html에서 동적으로 for문 돌려서 member 객체 꺼냄
            out.write("    <tr>");
            out.write("        <td>"+member.getId()+"</td>");
            out.write("        <td>"+member.getUsername()+"</td>");
            out.write("        <td>"+member.getAge()+"</td>");
            out.write("    </tr>");
        }
        %>

    </tbody>
</table>
</body>
</html>

