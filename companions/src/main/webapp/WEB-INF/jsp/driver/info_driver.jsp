<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>About us</title>
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
		<div class="cite_info">
			<p style="background-color: rgb(218, 218, 218);">we make cool things</p>
		</div>
	</body>
</html>
