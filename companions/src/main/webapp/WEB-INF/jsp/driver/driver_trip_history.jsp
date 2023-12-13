<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.Trip" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>User Profile</title>
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
                    <li><a href="controller?command=trips_history" class="info">Trip history</a></li>
					<li class="logout"><a href="controller?command=logout">Logout</a></li>
                </ul>
            </nav>
        </header>
		<div class="tables_history">
			<div class="column_my_trips_history">
				<table class="my_trips">
					<caption>History</caption>
					<thead>
						<tr>
							<th>From</th>
							<th>To</th>
							<th>Date</th>
							<th>Price, ₽</th>
							<th>Passangers</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
                    <c:forEach var="tripHistory" items="${tripHistory}">
                        <tr>
                            <td>${tripHistory.trip.startLocation}</td>
                            <td>${tripHistory.trip.endLocation}</td>
                            <td>${tripHistory.trip.tripDate}</td>
                            <td>${tripHistory.trip.tripPrice}</td>
                            <td>
                                <select class="selector" id="${tripHistory.trip.tripID}">
                                    <c:forEach var="user" items="${tripHistory.usersID}">
                                        <option value="${user}">${user}</option>
                                    </c:forEach>
                                </select>
                                <a id="${tripHistory.trip.tripID}_user" href="controller?command=user_profile&nickName=${user}">Ссылка</a>
                            </td>
                            <td class="action_cell">
                                <button id="${tripHistory.trip.tripID}_button" onclick="openModalReview('${tripHistory.trip.tripID}')">Review</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
				</table>
			</div>
		</div>

        <div id="reviewModal" class="overlay">
        <div class="modal">
            <span class="close-btn" onclick="closeModalReview()" style="cursor:pointer;">&times;</span>
            <h2 id="h2">Comment</h2>
            <form name="makeReview" action="controller" method="POST">
                <input type="hidden" name="command" value="make_review">
                <input type="hidden" name="targetID" id="targetID">
                <input type="hidden" name="tripID" id="tripID">
                <textarea id="reviewText" name="reviewText" rows="4" cols="50"></textarea>           
                <input type="number" min="1" max="5" name="rating" id="rating" value="5">           
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>

    </body>
<script type="text/javascript">
    var  selectors = document.querySelectorAll('.selector');
    console.log(selectors);
    selectors.forEach(function (selector){
        console.log("HAHAHAH");
        selector.addEventListener('change', function() {
            var selectedValue = selector.value;
            var link = document.getElementById(selector.id + '_user');
            var button = document.getElementById(selector.id + '_button');

            console.log(link);
            link.href = "controller?command=user_profile&nickName=" + selectedValue;
        });

        if (selector.length == 0) {
            selector.style.visibility = 'hidden';
            var link = document.getElementById(selector.id + '_user');
            var button = document.getElementById(selector.id + '_button');

            button.style.visibility = 'hidden';
            link.style.visibility = 'hidden';
        }
    });

    function openModalReview(tripID) {
        document.getElementById('reviewModal').style.display = 'flex';
        selector = document.getElementById(tripID)
        document.getElementById('targetID').value = selector.value;
        document.getElementById('tripID').value = tripID;
        document.getElementById('h2').innerText = 'Review ' + selector.value;
    }

    function closeModalReview() {
        document.getElementById('reviewModal').style.display = 'none';
    }
</script>

</html>
