<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>

<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    System.out.println("username = " + username);
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);

    memberRepository.save(member);

%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
성공
<ul>
    <Li>id=<%=member.getId() %>
    </Li>
    <Li>username=<%=member.getUsername() %>
    </Li>
    <Li>age=<%=member.getAge() %>
    </Li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
