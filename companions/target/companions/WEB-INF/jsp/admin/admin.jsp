<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.rsreu.companions.DataBase.Data.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin-panel</title>
    <style>
        <jsp:include page ="../../../css/admin/admin_style.css"/>
    </style>
</head>
<body>
    <h1>Admin-panel</h1>
    
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
	
		<table id="users-table">
            <tr>
                <th>User ID</th>
                <th>Role</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Rating</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr class="user-row" data-status="${user.status}" data-role="${user.roleName}" data-nickname="${user.userNickname}" data-firstname="${user.firstName}" data-lastname="${user.lastName}" data-rating="${user.rating}">
                    <td>${user.userNickname}</td>
                    <td>${user.roleName}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.rating}</td>
                    <td>${user.status}</td>
                    <td class="action-cell">
                        <button onclick="openModalDelete('${user.userNickname}')">Delete</button>
                        <button onclick="openModalRedact('${user.userNickname}', '${user.roleName}', '${user.firstName}', '${user.lastName}', '${user.userPassword}')">Redact</button>
                    </td>
                </tr>
            </c:forEach>
		</table>

    <div id="redactModal" class="overlay">
        <div class="modal">
            <span class="close-btn" onclick="closeModalRedact()" style="cursor:pointer;">&times;</span>
            <h2>Redact</h2>
            <form name="redactUser" action="controller" method="POST">
                <input type="hidden" name="command" value="redact_user">
                <input type="text" id="name" name="name">
                <select id="role" name="role">
                    <option value="Admin">Admin</option>
                    <option value="Moderator">Moderator</option>
                    <option value="Driver">Driver</option>
                    <option value="Passenger">Passenger</option>
			    </select>
                <input type="text" id="firstName" name="firstName">
                <input type="text" id="lastName" name="lastName">
                <input type="text" id="password" name="password">
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>

    <div id="deleteModal" class="overlay">
        <div class="modal">            
            <span class="close-btn" onclick="closeModalDelete()" style="cursor:pointer;">&times;</span>
            <h2>Are you sure?</h2>
            <form name="deleteUser" action="controller" method="POST">
                <input type="hidden" name="command" value="delete_user">
                <input type="hidden" id="name_delete" name="name" value="1">
                <button type="submit">Yes</button>
            </form>
        </div>
    </div>

    <div id="addModal" class="overlay">
        <div class="modal">
            <span class="close-btn" onclick="closeModalAdd()" style="cursor:pointer;">&times;</span>
            <h2>Add</h2>
            <form name="addUser" action="controller" method="POST">
                <input type="hidden" name="command" value="add_user">
                <input type="text" id="name" name="name">
                <select id="role" name="role">
                    <option value="Admin">Admin</option>
                    <option value="Moderator">Moderator</option>
                    <option value="Driver">Driver</option>
                    <option value="Passenger">Passenger</option>
			    </select>
                <input type="text" id="firstName" name="firstName">
                <input type="text" id="lastName" name="lastName">
                <input type="text" id="password" name="password">
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>


    <a href="#" class="add-button" onclick="openModalAdd()">Add user</a>
</body>

<script type="text/javascript">
    // Function to open the modal
    function openModalRedact(name, role, firstName, lastName, password) {
        document.getElementById('redactModal').style.display = 'flex';
        document.getElementById('name').value = name;
        document.getElementById('role').value = role;
        document.getElementById('firstName').value = firstName;
        document.getElementById('lastName').value = lastName;
        document.getElementById('password').value = password;
    }

    // Function to close the modal
    function closeModalRedact() {
        document.getElementById('redactModal').style.display = 'none';
    }

    function openModalDelete(name) {
        document.getElementById('deleteModal').style.display = 'flex';
        document.getElementById('name_delete').value = name;
    }

    function closeModalDelete() {
        document.getElementById('deleteModal').style.display = 'none';
    }

    // Function to open the modal
    function openModalAdd() {
        document.getElementById('addModal').style.display = 'flex';
    }

    // Function to close the modal
    function closeModalAdd() {
        document.getElementById('addModal').style.display = 'none';
    }

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
