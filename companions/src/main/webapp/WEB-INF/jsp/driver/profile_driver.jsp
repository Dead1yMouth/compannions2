<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.User" %>
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
                <button onclick="toggleEdit()" id="editButton">Edit</button>
                    <form name="makeReview" action="controller" method="POST">
                        <input type="hidden" name="command" value="redact_profile">
                        <input type="hidden" name="name" value="${user.userNickname}">
                        <input type="hidden" name="role" value="${user.roleName}">
                        <input type="hidden" name="password" value="${user.userPassword}">
                        <li><p>Nick Name: ${user.userNickname}</p></li>
                        <li><p>First name: <input class="info-input" name="firstName" type="text" value='${user.firstName}' data='${user.firstName}' id="edit2" readOnly></p></li>
                        <li><p>Last name: <input class="info-input" name="lastName" type="text" value='${user.lastName}' data='${user.lastName}' id="edit3" readOnly></p></li>
                        <li><p>Rating: ${user.rating}</p></li>
                        <li><button id="submitButton" style="display:none" type="submit">Submit</button></li>
                    </form>
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

<script type="text/javascript">
    function toggleEdit() {
        var edit2 = document.getElementById("edit2");
        var edit3 = document.getElementById("edit3");
        var submitButton = document.getElementById("submitButton");
        var editButton = document.getElementById("editButton");

        edit2.readOnly = !edit2.readOnly;
        edit3.readOnly = !edit3.readOnly;

        if (edit2.readOnly) {
            submitButton.style.display = 'none';
        } else {
            submitButton.style.display = 'inline-block';
        }

        var inputs = document.querySelectorAll('.info-input');
        console.log(inputs);
        inputs.forEach(function (input) {
            if (input.readOnly) {
                input.style.backgroundColor = "#f0f0f0";
                input.value = input.getAttribute('data');
                editButton.innerText = "Edit";
            } else {
                input.style.backgroundColor = "#fff";
                editButton.innerText = "Cancel";  
            }
        });
    }
</script>

</html>
