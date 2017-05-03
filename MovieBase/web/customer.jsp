<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Movie Base</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700" />
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Material Design Lite
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script> -->

        <style type="text/css">
            a {
                margin-bottom: 10px!important;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <header class="header">
            <div class="container">
                <div class="page-header">
                    <h1>MovieBase</h1>
                    <form method="POST" action="Logout" style="margin-bottom: 20px;">
                      <button type="submit" class="btn btn-primary">Logout</button>
                    </form>
                </div>
            </div>
        </header>

        <!-- Main -->
        <div class="main">
            <div class="container">
                <c:if test="${param.editSuccess}">
                    <div class="alert alert-success" role="alert">Your settings were successfully edited.</div>
                </c:if>
                <c:if test="${param.editFailed}">
                    <div class="alert alert-danger" role="alert">Error: there was an issue updating your settings.</div>
                </c:if>
                <h2>Welcome, ${customer.firstName}</h2>
                <a class="btn btn-default" href="CustomerWatchList">My Watch List</a><br>
                <a class="btn btn-default" href="ActiveRentals">Active Rentals</a><br>
                <a class="btn btn-default" href="RentalHistory">Rental History</a><br>
                <a class="btn btn-default" href="BestSellingMovies">View Best Sellers</a><br>
                <a class="btn btn-default" href="SearchMovies">Search Movies</a><br>
                <a class="btn btn-default" href="EditCustomer?customerId=${customer.id}">Edit Your Settings</a><br>
            </div>
        </div>
    </body>
</html>
