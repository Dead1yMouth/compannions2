<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>About us</title>
    <style>
        <jsp:include page ="../../css/styles.css"/>
    </style>
</head>
    <body>
        <header>
            <nav class="main_bar">
                <label class="logo">Companions</label>
                <ul>
                    <li><a href="controller?command=redirect&page=trips" class="trips">Trips</a></li>
                    <li><a href="controller?command=redirect&page=profile" class="profile">Profile</a></li>
                    <li><a href="controller?command=redirect&page=info" class="info">About us</a></li>
                </ul>
            </nav>
        </header>
		<div class="cite_info">
			<p style="background-color: rgb(218, 218, 218);">we make cool things</p>
		</div>
	</body>
</html>
