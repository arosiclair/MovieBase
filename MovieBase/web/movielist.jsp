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
            table {
                border-collapse: collapse;
            }
            th {
                font-style: bold;
            }
            th, td , table{
                border: 1px solid black;
                font-size: 20px;
                padding: 20px;
            }
        </style>
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
                <h2>Comprehensive List of Movies</h2>
                <table>
                    <thead>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Genre</th>
                        <th>Distribution Fee</th>
                        <th>Rating</th>
                        <th>Copies Left</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${movieList}" var="movie">
                            <tr>
                                <td>${movie.id}</td>
                                <td>${movie.name}</td>
                                <td>${movie.genre}</td>
                                <td>${movie.rating}</td>
                                <td>${movie.distFee}</td>
                                <td>${movie.numCopies}</td>
                                
                                <td>
                                  <form method="POST" action="EditMovie">
                                    <div class="form-group">
                                      <button class="btn btn-default" name="movieId" value="${movie.id}">Edit</button>
                                    </div>
                                  </form>
                                </td>
                                <td>
                                  <button class="btn btn-default">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
