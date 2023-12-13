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
                    <li><a href="controller?command=trips_passanger" class="trips">Trips</a></li>
                    <li><a href="controller?command=profile" class="profile">Profile</a></li>
                    <li><a href="controller?command=history" class="info">Trip history</a></li>
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
							<th>Driver</th>
                            <th>Action</th>
						</tr>
					</thead>
					<tbody>
                    <c:forEach var="tripHistory" items="${tripHistory}">
                        <tr>
                            <td>${tripHistory.startLocation}</td>
                            <td>${tripHistory.endLocation}</td>
                            <td>${tripHistory.tripDate}</td>
                            <td>${tripHistory.tripPrice}</td>
                            <td><a href="controller?command=user_profile&nickName=${tripHistory.driverID}">${tripHistory.driverID}</a></td>
                            <td class="action_cell">
                                <button onclick="openModalReview('${tripHistory.driverID}', '${tripHistory.tripID}')">Review ${tripHistory.driverID}</button>
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
    function openModalReview(driverID, tripID) {
        document.getElementById('reviewModal').style.display = 'flex';
        document.getElementById('targetID').value = driverID;
        document.getElementById('tripID').value = tripID;
        document.getElementById('h2').innerText = 'Review ' + driverID;
    }

    function closeModalReview() {
        document.getElementById('reviewModal').style.display = 'none';
    }

</script>

</html>
