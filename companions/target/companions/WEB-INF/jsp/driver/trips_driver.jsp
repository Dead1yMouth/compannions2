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
							<th>Seats</th>
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
								<td>${myTrips.avaliableSeats}</td>
								<form name="removeTrip" action="controller" method="POST">
									<td class="action_cell">
									<input type="hidden" name="command" value="remove_trip">
									<input type="hidden" name="tripID" value="<c:out value='${myTrips.tripID}'/>">
										<button type="submit">Отменить</button>
									</td>
								</form>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
			<div class="column_create_trips">
				<table class="create_trip">
					<caption>Trip form</caption>	
					<tbody>
						<form name="makeTrip" action="controller" method="POST">
							<input type="hidden" name="command" value="make_trip">
							<tr>
								<td><input type="text" id="departure" name="departure" value="" placeholder="Departure"  required></td>
								<td style="background:#505f75"></td>
							</tr>
							<tr>
								<td><input type="text" id="arrival" name="arrival" value="" placeholder="Arrival" required></td>
								<td style="background:#505f75"></td>
							</tr>
							<tr>
								<td><input type="date" id="date" name="date" value="" placeholder="dd-mm-yyyy" required></td>
								<td class="action_cell" style="background:#505f75"><button type="submit">Создать поездку</button></td>
							</tr>
							<tr>
								<td><input type="number" id="price" name="price" value="" placeholder="Price, ₽" required></td>
								<td style="background:#505f75"></td>
							</tr>
							<tr>
								<td><input type="number" id="seats" name="seats" value="" placeholder="Seats" required></td>
								<td style="background:#505f75"></td>
							</tr>
						</form>
					</tbody>		
				</table>
			</div>
		</div>
		<div class="tables">
			<div class="column_trip_request">
				<table class="trip_request">
					<caption>Requests</caption>
					<thead>
						<tr>
							<th>Passanger</th>
							<th>From</th>
							<th>To</th>
							<th>Date</th>
							<th>Price, ₽</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="tripRequest" items="${tripRequest}">
							<%-- <a href="controller?command=trips_driver" class="trips">Trips</a> --%>
							<tr> 
								<td> <a href="controller?command=user_profile&nickName=${tripRequest.passengerID}">${tripRequest.passengerID}</a></td>
								<td>${tripRequest.startLocation}</td>
								<td>${tripRequest.endLocation}</td>
								<td>${tripRequest.tripDate}</td>
								<td>${tripRequest.tripPrice}</td>
								<td>${tripRequest.status}</td>
								<td class="action_cell">
									<form name="acceptTrip" action="controller" method="POST" style="background:transparent">
										<input type="hidden" name="command" value="accept_request">
										<input type="hidden" name="tripID" value="<c:out value='${tripRequest.tripID}'/>">
										<button  type="submit">Принять</button>
									</form>
									<form name="declineTrip" action="controller" method="POST" style="background:transparent">
										<input type="hidden" name="command" value="decline_request">
										<input type="hidden" name="tripID" value="<c:out value='${tripRequest.tripID}'/>">
										<button type="submit">Отклонить</button>
									</form>
								</td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
    </body>
</html>
