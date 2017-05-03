<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <link rel="stylesheet" href="css/results.css">
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
                <c:if test="${param.returnSuccess}">
                    <div class="alert alert-success" role="alert">Rental successfully returned.</div>
                </c:if>
                <c:if test="${param.returnFailed}">
                    <div class="alert alert-danger" role="alert">Error: there was an issue returning your rental.</div>
                </c:if>
                
                    <c:choose>
                    <c:when test="${not empty activeRentals}">
                        <h2>${customer.firstName}'s Active Rentals</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>${customer.firstName}'s Rental History</h2>
                    </c:otherwise>
                </c:choose>
                <table>
                    <thead>
                        <th>Movie</th>
                        <th>Date and Time</th>
                        <th>Return Date</th>
                    </thead>
                    <tbody>
                        <c:forEach begin="0" end="${fn:length(rentals) - 1}" varStatus="loop">
                            <tr>
                                <td>${movies[loop.index].name}</td>
                                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${rentals[loop.index].dateTime}"/></td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${empty rentals[loop.index].returnDate}">
                                            <a class="btn btn-default" href="ReturnRental?rentalId=${rentals[loop.index].id}&movieId=${movies[loop.index].id}">Return</a>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatDate type="date" dateStyle="short" value="${rentals[loop.index].returnDate}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a class="btn btn-default" href="Customer">Back To Main Menu</a>
            </div>
        </div>
    </body>
</html>
