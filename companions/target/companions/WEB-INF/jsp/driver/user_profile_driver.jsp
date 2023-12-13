<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.User" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.Review" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>My profile</title>
    <style>
        <jsp:include page ="../../../css/styles.css"/>
    </style>
</head>
    <body>
        <header>
            <nav class="main_bar">
                <label class="logo"><a href="controller?command=home" class="logoLink">Companions</a></label>
                <ul>
                    <li><a href="controller?command=trips_driver" class="trips">Trips</a></li>
                    <li><a href="controller?command=profile" class="profile">Profile</a></li>
                    <li><a href="controller?command=history" class="info">Trip history</a></li>
                    <li class="logout"><a href="controller?command=logout">Logout</a></li>
                </ul>
            </nav>
        </header>
        <div class="profile_info">
            <div class="profile_list">
                <img class="profile_img" src="/companions/img/user.png">
                <ul class="profile_ul">
                    <li><p>Nick Name: ${user.userNickname}</p></li>
                    <li><p>First name: ${user.firstName}</p></li>
                    <li><p>Last name: ${user.lastName}</p></li>
                    <li><p>Rating: ${user.rating}</p></li>
                </ul>
            </div>
        </div>
        <div class="profile_review">
            <table class="trip_review">
                <caption>My Reviews</caption>
                <thead>
                    <tr>
                        <th>Reviewer</th>
                        <th>Review</th>
                        <th>Rating</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="reviews" items="${reviews}">
                    <tr>
                        <td><a href="controller?command=user_profile&nickName=${reviews.reviewerID}">${reviews.reviewerID}</a></td>
                        <td>${reviews.reviewText}</td>
                        <td>${reviews.rating}</td>
                    </tr>      
                </c:forEach>
                </tbody>	
            </table>
        </div>
    </body>

</html>
