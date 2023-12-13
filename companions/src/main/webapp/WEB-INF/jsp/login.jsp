<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
    <style>
        <jsp:include page ="../../css/loginStyle.css"/>
    </style>
</head>
<body>
    <div class="container">
        <h2>Companions</h2>
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login" />
            <div class="input-box">
                <input type="text" id="login" name="login" placeholder="Login" value="" />
            </div>
            <div class="input-box">
                <input type="password" id="password" name="password" placeholder="Password" value="" />
            </div>
            <p class="error-message">${errorLoginPassMessage}</p>
            <p class="error-message">${wrongAction}</p>
            <p class="error-message">${nullPage}</p>
            <input class="btn" type="submit" value="Log in" />
        </form>
    </div>
</body>
</html>
