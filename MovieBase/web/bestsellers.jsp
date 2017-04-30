<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tlds/custom-functions.tld" prefix="fn" %>
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
                </div>
            </div>
        </header>

        <!-- Main -->
        <div class="main">
            <div class="container">
                <h2>Best Selling Movies</h2>
                <table>
                    <thead>
                        <th>Name</th>
                        <th>Genre</th>
                        <th>Rating</th>
                        <th>Number of Rentals</th>
                        <th>Add to Watch List</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${bestSellers}" var="movie">
                            <tr>
                                <td>${movie.name}</td>
                                <td>${movie.genre}</td>
                                <td>${movie.rating}</td>
                                <td>${movie.numRentals}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${fn:containsInt(watchList, movie.id)}">Added</c:when>
                                        <c:otherwise>
                                            <form method="POST" action="AddToWatchList">
                                                <input type="hidden" name="movieId" value="${movie.id}">
                                                <button class="btn btn-default" type="submit">Add</button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
