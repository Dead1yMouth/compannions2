<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.Review" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Moder-panel (Reviews)</title>
    <style>
        <jsp:include page ="../../../css/moder/moder_style.css"/>
        td {
            max-width: 150px; /* Set your desired maximum width */
            overflow: auto;
            white-space: nowrap; /* Prevent text from wrapping to the next line */
        }   
    </style>
</head>
<body>
    <h1>Moder-panel (Reviews)</h1>
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
			<label for="id-filter">ID filter:</label>
			<input type="number" id="id-filter">
		</div>
        <div>
			<label for="reviewer-filter">Reviewer filter:</label>
			<input type="text" id="reviewer-filter">
		</div>
		<div>
			<label for="target-filter"`>Target filter:</label>
			<input type="text" id="target-filter">
		</div>
        <div>
			<label for="review-filter"`>Review filter:</label>
			<input type="text" id="review-filter">
		</div>
        <div>
			<label for="rating-filter"`>Rating filter:</label>
			<input type="text" id="rating-filter">
		</div>
		<div>
			<label for="trip-id-filter">Trip ID filter:</label>
			<input type="number" id="trip-id-filter">
		</div>
		<button class="apply-button" onclick="filter()">Apply filters</button>
    </div>
	
    <div class="table-container">
        <table id="ratings-table">
            <tr>
                <th>ID</th>
                <th>Reviewer</th>
                <th>Target</th>
                <th>Review</th>
                <th>Rating</th>
                <th>Trip ID</th>
                <th class="action-cell">Actions</th>
            </tr>
            <c:forEach var="reviews" items="${reviews}">
                <tr class="user-row" review-id="${reviews.reviewID}" review-revID="${reviews.reviewerID}" review-target="${reviews.targetID}" review-text="${reviews.reviewText}" review-rating="${reviews.rating}" review-tripID="${reviews.tripID}">
                    <td>${reviews.reviewID}</td>
                    <td>${reviews.reviewerID}</td>
                    <td>${reviews.targetID}</td>
                    <td>${reviews.reviewText}</td>
                    <td>${reviews.rating}</td>
                    <td>${reviews.tripID}</td>
                    <td class="action-cell">
                        <button onclick="openModalRedact('${reviews.reviewID}', '${reviews.reviewText}', ${reviews.rating})">Redact</button>
                        <button onclick="openModalDelete('${reviews.reviewID}')">Delete</button>
                    </td>
                </tr>
            </c:forEach>
            <!-- Добавьте здесь другие оценки из базы данных или другого источника -->
        </table>
    </div>

    <div id="redactModal" class="overlay">
        <div class="modal">
            <span class="close-btn" onclick="closeModalRedact()" style="cursor:pointer;">&times;</span>
            <h2>Redact</h2>
            <form name="redactReview" action="controller" method="POST">
                <input type="hidden" name="command" value="redact_review">
                <input type="hidden" name="reviewID" id="reviewID">
                <textarea id="reviewText" name="reviewText" rows="4" cols="50"></textarea>           
                <input type="number" min="1" max="5" name="rating" id="rating">           
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>

    <div id="deleteModal" class="overlay">
        <div class="modal">            
            <span class="close-btn" onclick="closeModalDelete()" style="cursor:pointer;">&times;</span>
            <h2>Are you sure?</h2>
            <form name="deleteReview" action="controller" method="POST">
                <input type="hidden" name="command" value="delete_review">
                <input type="hidden" id="reviewID-delete" name="reviewID-delete" value="1">
                <button type="submit">Yes</button>
            </form>
        </div>
    </div>

</body>

<script type="text/javascript">
// Function to open the modal
    function openModalRedact(reviewID, reviewText, rating) {
        document.getElementById('redactModal').style.display = 'flex';
        document.getElementById('reviewID').value = reviewID;
        document.getElementById('reviewText').value = reviewText;
        document.getElementById('rating').value = rating;   
    }

    function openModalDelete(reviewID) {
        document.getElementById('deleteModal').style.display = 'flex';
        document.getElementById('reviewID-delete').value = revieweID;
    }

    function closeModalDelete() {
        document.getElementById('deleteModal').style.display = 'none';
    }

    // Function to close the modal
    function closeModalRedact() {
        document.getElementById('redactModal').style.display = 'none';
    }

    function filter() {
        var idSelector = document.getElementById('id-filter');
        var reviewerSelector = document.getElementById('reviewer-filter');
        var targetSelector = document.getElementById('target-filter');
        var reviewSelector = document.getElementById('review-filter');
        var ratingSelector = document.getElementById('rating-filter');
        var tripIdSelector = document.getElementById('trip-id-filter');
        var selectedID = idSelector.value;
        var selectedReviewer = reviewerSelector.value;
        var selectedTarget = targetSelector.value;
        var selectedReview = reviewSelector.value;
        var selectedRating = ratingSelector.value;
        var selectedTripID = tripIdSelector.value;
        var rows = document.querySelectorAll('.user-row');

        rows.forEach(function (row) {
            var rowID = row.getAttribute('review-id');
            var rowRevID = row.getAttribute('review-revID');
            var rowTarget = row.getAttribute('review-target');
            var rowText = row.getAttribute('review-text');
            var rowRating = row.getAttribute('review-rating');
            var rowTripID = row.getAttribute('review-tripID');

            var idCondition = selectedID === '' || rowID === selectedID;
            var revIdCondition = selectedReviewer === '' || rowRevID === selectedReviewer;
            var targetCondition = selectedTarget === '' || rowTarget === selectedTarget;
            var textCondition = selectedReview === '' || rowText === selectedReview;
            var ratingCondition = selectedRating === '' || rowRating === selectedRating;
            var tripIdCondition = selectedTripID === '' || rowTripID === selectedTripID;

            if (idCondition && revIdCondition && targetCondition && textCondition && ratingCondition && ratingCondition && tripIdCondition) {
                row.style.display = 'table-row';
            } else {
                row.style.display = 'none';
            }
        });
    }
</script>

</html>
