<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <style>
        body{
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            align-items: center;
            background: #9932CC;
            color: white;
            height: 100vh;
        }
        form{
            padding: 1em;
            border-radius: 30px;
            background: darkorange;
            display: flex;
            flex-direction: column;
            justify-content: space-around;
        }
        form input{
            background: transparent;
            border: none;
            border-bottom: 1px solid #9932CC;
            margin-bottom: 1em;
        }
        a{
            padding: 1em;
            border-radius: 30px;
            background: darkorange;
            color: black;
            text-decoration: none;
        }
    </style>
</head>
<body>

<form:form method="post" modelAttribute="user" action="authorizeUser">

    <%--        <label>Username</label>--%>
    <%--        <form:input path="username" placeholder="Username" type="text"/>--%>
    <%--        <form:errors path="username"/>--%>

    <label>email</label>
    <form:input path="email" placeholder="Email" type="text"/>
    <form:errors path="email"/>
    <label>Password</label>
    <form:input path="password" placeholder="Password" type="password"/>
    <form:errors path="password"/>
    <label>Confirm password</label>
    <form:input path="confirmedPassword" placeholder="confirm password" type="password"/>
    <form:errors path="confirmedPassword"/>
    <label>Username</label>
    <form:input path="userDetails.name" placeholder="username" type="text"/>
    <%--        <form:errors path="userDetails.username"/>--%>
    <label>Surname</label>
    <form:input path="userDetails.surname" placeholder="surname" type="text"/>
    <form:errors path="userDetails.surname"/>

    <label>Currency</label>
    <form:select path="userAccount.currency">
        <c:forEach items="${rates}" var="item">
            <form:option value="${item}"/>
        </c:forEach>
    </form:select>
    <form:errors path="userAccount.currency"/>
    <form:button type="submit">
        Register
    </form:button>
</form:form>
<a href="login">
    I already have account
</a>
</body>
</html>