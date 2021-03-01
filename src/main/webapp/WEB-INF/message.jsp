<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
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
    <a>
        ${message}
    </a>
    <a href="home">
        Go back to home page
    </a>
</body>
</html>