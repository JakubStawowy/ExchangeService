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
<body>

<form:form method="post" modelAttribute="user">

    <%--        <label>Username</label>--%>
    <%--        <form:input path="username" placeholder="Username" type="text"/>--%>
    <%--        <form:errors path="username"/>--%>

    <label>email</label>
    <form:input path="email" placeholder="Email" type="text"/>
<%--    <form:errors path="email"/>--%>
    <label>Password</label>
    <form:input path="password" placeholder="Password" type="password"/>
    <form:errors path="password"/>
    <label>Username</label>
    <form:input path="userDetails.name" placeholder="username" type="text"/>
    <%--        <form:errors path="userDetails.username"/>--%>
    <label>Surname</label>
    <form:input path="userDetails.surname" placeholder="surname" type="text"/>
    <form:errors path="userDetails.surname"/>
    <form:button type="submit">
        Register
    </form:button>


</form:form>
</body>
</html>