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
            <div class="find_bar">
                <input type="text" name="start__location" placeholder="From" required>
                <input type="text" name="end__location" placeholder="To" required>
                <input type="text" name="start__date" placeholder="Seats" required>
                <input type="date" name="end__date" placeholder="Depart" required>
				<button name="search__trip" value="" type="submit">SEARCH</button>
            </div>
		<div class="tables">
			<div class="column_my_trips">
				<table class="my_trips">
					<caption>My trips</caption>
					<thead>
						<tr>
							<th>From</th>
							<th>To</th>
							<th>Date</th>
							<th>Price, ₽</th>
							<th>Driver</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="myTrips" items="${myTrips}">
							<tr>
								<td>${myTrips.startLocation}</td>
								<td>${myTrips.endLocation}</td>
								<td>${myTrips.tripDate}</td>
								<td>${myTrips.tripPrice}</td>
								<td><a href="controller?command=user_profile&nickName=${myTrips.driverID}">${myTrips.driverID}</a></td>
								<td>${myTrips.status}</td>
								<form name="refuseForm" action="controller" method="POST">
									<td class="action_cell">
										<input type="hidden" name="command" value="refuse_request">
										<input type="hidden" name="requestID" value="<c:out value='${myTrips.requestID}'/>">
										<button type="submit">Отказаться</button>
									</td>
								</form>
							</tr>
						</c:forEach>
					</tbody>	
				</table>
			</div>	
			<div class="column_avaliable_trips">
				<table class="available_trips">
					<caption>Available trips</caption>
					<tr>
						<th>From</th>
						<th>To</th>
						<th>Date</th>
						<th>Price, ₽</th>
						<th>Driver</th>
						<th>Seats</th>
						<th>Action</th>
					</tr>
					<c:forEach var="trip" items="${trips}">
						<tr>
							<td>${trip.startLocation}</td>
							<td>${trip.endLocation}</td>
							<td>${trip.tripDate}</td>
							<td>${trip.tripPrice}</td>
							<td><a href="controller?command=user_profile&nickName=${trip.driverID}">${trip.driverID}</a></td>
							<td>${trip.avaliableSeats}</td>
							<form name="makeForm" action="controller" method="POST">
								<input type="hidden" name="command" value="make_request">
								<input type="hidden" name="tripID" value="<c:out value='${trip.tripID}'/>">
								<td><button>Подать заявку</button></td>
							</form>
						</tr>
					</c:forEach>
				</table>
			</div>	
		</div>
    </body>
</html>
