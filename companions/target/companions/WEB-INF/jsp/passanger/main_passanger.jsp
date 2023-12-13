<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Companions</title>
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
        <div class="main_info">
            <article>
                <section>
                    <p>Welcome to "Companions"!</p>
               </section>
                <section>
                    <p> "Companions" is an online platform designed for 
                    anyone seeking company during their travels. 
                    No matter where you're headed, when, or with whom - here, 
                    you'll find the perfect travel companion to make your journey 
                    unforgettable. 
                    </p>
                </section>
                <section>
                    <p>Our main goals:</p>
                    <ol>
                        <li>Make your travels incredibly interesting: "Travel Buddies" helps you connect with like-minded travel companions, turning every trip into an exciting adventure.</li>
                        <li>Save on your trips: We split expenses for gas, tickets, or car rentals, making it possible for everyone to save money and make travel more affordable.</li>
                        <li>Support in any situation: We ensure the safety and comfort of your travels by providing information about trustworthy travel companions and the ability to track routes.</li>
                    </ol>
                </section>
                <section>
                    <p>"Travel Buddies" is not just a convenient tool for finding travel companions but also a community of like-minded individuals ready to share your adventures. 
                    Join us today and embark on new journeys with a community of open and friendly travel companions! Get started by creating your profile and begin traveling with pleasure!</p>
                </section>
            </article>
        </div>
    </body>
</html>
