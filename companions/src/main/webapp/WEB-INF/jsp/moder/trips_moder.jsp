<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.Trip" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Moderator (Trips)</title>
    <style>
        <jsp:include page ="../../../css/moder/moder_style.css"/>
    </style>
</head>
<body>
    <h1>Moderator-panle (Trips)</h1>
	<!-- Добавляем панель навигации -->
    <div class="navigation-panel">
        <ul>
            <li><a href="controller?command=moder_users" id="users-tab">Users</a></li>
            <li><a href="controller?command=moder_trips" id="trips-tab">Trips</a></li>
            <li><a href="controller?command=moder_reviews" id="rating-tab">Ratings</a></li>
        </ul>
    </div>
	
	<div class="filter-container">
        <form name="logout" action="controller" method="POST">
            <input type="hidden" name="command" value="logout">
            <%-- <input type="hidden" id="name_delete" name="name" value="1"> --%>
            <button type="submit">Logout</button>
        </form>
        <div>
            <label for="startLocation-filter">Filter by departion point:</label>
            <input type="text" id="startLocation-filter">
        </div>
        
        <div>
            <label for="endLocation-filter">Filter by arrival point:</label>
            <input type="text" id="endLocation-filter">
        </div>
        
        <div>
            <label for="date-filter">Filter by date:</label>
            <input type="date" id="date-filter">
        </div>
        
        <div>
            <label for="price-filter">Filter by price:</label>
            <input type="number" step="0.01" id="price-filter">
        </div>
        
        <div>
            <label for="driver-filter">Filter by Driver:</label>
            <input type="text" id="driver-filter">
        </div>
        
        <div>
            <label for="seats-filter">Filter by Seats:</label>
            <input type="number" id="seats-filter">
        </div>


        
        <button class="apply-button" onclick="filter()">Apply filters</button>
    </div>
	
    <div class="table-container">
        <table id="trips-table">
            <tr>
                <th>ID</th>
                <th>Departure</th>
                <th>Arrival point</th>
                <th>Date</th>
                <th>Price</th>
                <th>Driver</th>
                <th>Avaliable Seats</th>
                <th class="action-cell">Actions</th>
            </tr>
            <c:forEach var="trips" items="${trips}">
                <tr class="user-row" data-tripid="${trips.tripID}" data-startlocation="${trips.startLocation}" data-endlocation="${trips.endLocation}" data-tripdate="${trips.tripDate}" data-tripprice="${trips.tripPrice}" data-driverid="${trips.driverID}" data-avaliableseats="${trips.avaliableSeats}">
                    <td>${trips.tripID}</td>
                    <td>${trips.startLocation}</td>
                    <td>${trips.endLocation}</td>
                    <td>${trips.tripDate}</td>
                    <td>${trips.tripPrice}</td>
                    <td>${trips.driverID}</td>
                    <td>${trips.avaliableSeats}</td>
                    <td class="action-cell">
                        <button onclick="openModalRedact('${trips.tripID}', '${trips.startLocation}', '${trips.endLocation}', '${trips.tripDate}', '${trips.tripPrice}', '${trips.avaliableSeats}')">Redact</button>
                    </td>
                </tr>
            </c:forEach>
            <!-- Добавьте здесь другие поездки из базы данных или другого источника -->
        </table>
    </div>

    <div id="redactModal" class="overlay">
        <div class="modal">
            <span class="close-btn" onclick="closeModalRedact()" style="cursor:pointer;">&times;</span>
            <h2>Redact</h2>
            <form name="redactTrip" action="controller" method="POST">
                <input type="hidden" name="command" value="redact_trip">
                <input type="hidden" name="tripID" id="tripID">
                <input type="text" name="startLocation" id="startLocation">
                <input type="text" name="endLocation" id="endLocation">
                <input type="date" name="tripDate" id="tripDate">
                <input type="number" name="tripPrice" id="tripPrice">
                <input type="number" name="avaliableSeats" id="avaliableSeats">
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
</body>

<script type="text/javascript">
// Function to open the modal
    function openModalRedact(tripID, startLocation, endLocation, tripDate, tripPrice, avaliableSeats) {
        document.getElementById('redactModal').style.display = 'flex';
        document.getElementById('tripID').value = tripID;
        document.getElementById('startLocation').value = startLocation;
        document.getElementById('endLocation').value = endLocation;
        document.getElementById('tripDate').value = tripDate;
        document.getElementById('tripPrice').value = tripPrice;
        document.getElementById('avaliableSeats').value = avaliableSeats;
    }

    // Function to close the modal
    function closeModalRedact() {
        document.getElementById('redactModal').style.display = 'none';
    }

    function filter() {
        var startLocationSelector = document.getElementById('startLocation-filter');
        var endLocationSelector = document.getElementById('endLocation-filter');
        var dateSelector = document.getElementById('date-filter');
        var priceSelector = document.getElementById('price-filter');
        var driverSelector = document.getElementById('driver-filter');
        var seatsSelector = document.getElementById('seats-filter');
        var selectedStartLocation = startLocationSelector.value;
        var selectedEndLocation = endLocationSelector.value;
        var selectedDate = dateSelector.value;
        var selectedPrice = priceSelector.value;
        var selectedDriver = driverSelector.value;
        var selectedSeats = seatsSelector.value;
        var rows = document.querySelectorAll('.user-row');

        console.log(rows);

        rows.forEach(function (row) {
            var rowStartLocation = row.getAttribute('data-startlocation');
            var rowEndLocation = row.getAttribute('data-endlocation');
            var rowDate = row.getAttribute('data-tripdate');
            var rowPrice = row.getAttribute('data-tripprice');
            var rowDriver = row.getAttribute('data-driverid');
            var rowSeats = row.getAttribute('data-avaliableseats');

            var startLocationCondition = selectedStartLocation === '' || rowStartLocation === selectedStartLocation;
            var endLocationCondition = selectedEndLocation === '' || rowEndLocation === selectedEndLocation;
            var dateCondition = selectedDate === '' || rowDate === selectedDate;
            var priceCondition = selectedPrice === '' || rowPrice === selectedPrice;
            var driverCondition = selectedDriver === '' || rowDriver === selectedDriver;
            var seatsCondition = selectedSeats === '' || rowSeats === selectedSeats;

            if (startLocationCondition && endLocationCondition && dateCondition && priceCondition && driverCondition && seatsCondition) {
                row.style.display = 'table-row';
            } else {
                row.style.display = 'none';
            }
        });
    }
</script>
</html>
