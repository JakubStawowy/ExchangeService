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

<a>
    Hello ${loggedUser.userDetails.name}
</a>
<%--<form:form method="post" modelAttribute="exchange">--%>
<%--    <form:input type="text" path="value"/>--%>
<%--    <form:select path="b">--%>
<%--        <form:option value="1"/>--%>
<%--        <form:option value="2"/>--%>
<%--        <form:option value="3"/>--%>
<%--    </form:select>--%>
<%--</form:form>--%>

<a>
    ${exchange.value}
</a>

<a href="logout">
    Logout
</a>
</body>
</html>