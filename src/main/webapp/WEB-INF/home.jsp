<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
<%--    <script type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/js/angular/script.js"></script>--%>
<%--    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">--%>
    <style type="text/css">
        body{
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            align-items: center;
        }
        form{
            padding: 1em;
            border-radius: 30px;
            background: darkorange;
        }
    </style>
</head>
<body>

<a>
    Hello ${loggedUser.userDetails.name} ${loggedUser.userDetails.surname}
</a>
<form:form method="post" modelAttribute="exchange" action="count">
    <form:input type="text" path="initAmount"/>
    <form:errors path="initAmount"/>
    <a>
        From:
    </a>
    <form:select path="initCurrency">
        <c:forEach items="${rates}" var="item">
            <form:option value="${item}"/>
        </c:forEach>
    </form:select>

    <a>
        to:
    </a>
    <form:select path="targetCurrency">
        <c:forEach items="${rates}" var="item">
            <form:option value="${item}"/>
        </c:forEach>
    </form:select>
    <form:button type="submit">count</form:button>
</form:form>

${result}
${message}

<a href="logout">
    Logout
</a>
</body>
</html>