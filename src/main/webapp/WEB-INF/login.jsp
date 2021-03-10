<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<style type="text/css">
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
<body>

    <form:form method="post" modelAttribute="user">
        <label>email</label>
        <form:input type="text" placeholder="email" path="email"/>
        <label>password</label>
        <form:input type="password" placeholder="password" path="password"/>
        <form:button type="submit">Login</form:button>
    </form:form>
    <br/>
    <a href="register">
        register
    </a>
    ${message}
</body>
</html>