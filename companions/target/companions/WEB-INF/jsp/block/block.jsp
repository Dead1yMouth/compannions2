<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page import="ru.rsreu.companions.DataBase.Data.User" %> --%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: rgb(218, 218, 218);
        }

        #blocked_container {
            width: 50%;
            height: 40%;
            text-align: center;
            padding: 20px;
            border-radius: 40px;
            border: 5px solid black;
            background-color: #67768d;
            font-family: 'Montserrat', sans-serif;
        }

        h1 {
            font-size: 70px;
            color: white;
        }

        p {
          font-size: 50px;
          color: rgba(255, 255, 255, 0.5);
        }
    </style>
    <title>Blocked Page</title>
</head>
<body>
    <div id="blocked_container">
        <h1>Sorry, you have been blocked</h1>
        <p>Try another time</p>
    </div>
</body>
</html>
