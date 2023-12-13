<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Moderator Panel (Users)</title>
    <style>
        <jsp:include page ="../../../css/moder/moder_style.css"/>
    </style>
</head>
<body>
    <h1>Moderator Panel (Users)</h1>
    
    <!-- Add navigation panel -->
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
			<label for="status-filter">Status filter:</label>
            <select id="status-filter">
				<option value="all">all</option>
				<option value="Online">Online</option>
				<option value="Offline">Offline</option>
				<option value="Blocked">Blocked</option>
			</select>
		</div>
        <div>
			<label for="role-filter">Role filter:</label>
            <select id="role-filter">
				<option value="all">all</option>
				<option value="Moderator">Moderator</option>
				<option value="Admin">Admin</option>
				<option value="Passenger">Passenger</option>
				<option value="Driver">Driver</option>
			</select>
		</div>
		<div>
			<label for="nickname-filter"`>Nickname filter:</label>
			<input type="text" id="nickname-filter">
		</div>
        <div>
			<label for="firstname-filter"`>First name filter:</label>
			<input type="text" id="firstname-filter">
		</div>
        <div>
			<label for="lastname-filter"`>Last name filter:</label>
			<input type="text" id="lastname-filter">
		</div>
		<div>
			<label for="rating-filter">Rating filter:</label>
			<input type="number" id="rating-filter">
		</div>
		<button class="apply-button" onclick="filter()">Apply filters</button>
    </div>

    <div class="table-container">
        <table id="users-table">
            <tr>
                <th>Nickname</th>
                <th>Role</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Rating</th>
                <th>Status</th>
                <th class="action-cell">Actions</th>
                <th></th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr class="user-row" data-status="${user.status}" data-role="${user.roleName}" data-nickname="${user.userNickname}" data-firstname="${user.firstName}" data-lastname="${user.lastName}" data-rating="${user.rating}">
                    <td>${user.userNickname}</td>
                    <td>${user.roleName}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.rating}</td>
                    <td>${user.status}</td>
					<form name="blockForm" action="controller" method="POST">
                        <td class="action-cell">
                            <input type="hidden" name="command" value="moder_block_request">
                            <input type="hidden" name="userNickname" value="<c:out value='${user.userNickname}'/>">
                                <button type="submit">Block</button>
                        </td>
                    </form>
					<form name="unblockForm" action="controller" method="POST">
                        <td class="action-cell">
                            <input type="hidden" name="command" value="moder_unblock_request">
                            <input type="hidden" name="userNickname" value="<c:out value='${user.userNickname}'/>">
                                <button type="submit">Unblock</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>

<script type"text/javascript">
    function filter() {
        var statusSelector = document.getElementById('status-filter');
        var roleSelector = document.getElementById('role-filter');
        var nicknameSelector = document.getElementById('nickname-filter');
        var firstNameSelector = document.getElementById('firstname-filter');
        var lastNameSelector = document.getElementById('lastname-filter');
        var ratingSelector = document.getElementById('rating-filter');
        var selectedStatus = statusSelector.value;
        var selectedRole = roleSelector.value;
        var selectedNickName = nicknameSelector.value;
        var selectedFirstName = firstNameSelector.value;
        var selectedLastName = lastNameSelector.value;
        var selectedRating = ratingSelector.value;
        var rows = document.querySelectorAll('.user-row');

        rows.forEach(function (row) {
            var rowStatus = row.getAttribute('data-status');
            var rowRole = row.getAttribute('data-role');
            var rowNickName = row.getAttribute('data-nickname');
            var rowFirstName = row.getAttribute('data-firstname');
            var rowLastName = row.getAttribute('data-lastname');
            var rowRating = row.getAttribute('data-rating');

            var roleCondition = selectedRole === 'all' || rowRole === selectedRole;
            var statusCondition = selectedStatus === 'all' || rowStatus === selectedStatus;
            var nicknameCondition = selectedNickName === '' || rowNickName === selectedNickName;
            var firstNameCondition = selectedFirstName === '' || rowFirstName === selectedFirstName;
            var lastnameCondition = selectedLastName === '' || rowLastName === selectedLastName;
            var ratingCondition = selectedRating === '' || rowRating === selectedRating;

            if (roleCondition && statusCondition && nicknameCondition && firstNameCondition && lastnameCondition && ratingCondition) {
                row.style.display = 'table-row';
            } else {
                row.style.display = 'none';
            }
        });
    }

</script>
</html>
