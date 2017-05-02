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
                <c:if test="${param.createMovieSuccess}">
                    <div class="alert alert-success" role="alert">Movie was successfully added.</div>
                </c:if>
                <c:if test="${param.createMovieFailed}">
                    <div class="alert alert-danger" role="alert">Error: there was an issue adding the movie, try again.</div>
                </c:if>
                <c:if test="${param.editMovieSuccess}">
                    <div class="alert alert-success" role="alert">Movie was successfully edited.</div>
                </c:if>
                <c:if test="${param.editMovieFailed}">
                    <div class="alert alert-danger" role="alert">Error: there was an issue editing the movie, try again.</div>
                </c:if>
                <c:if test="${param.deleteMovieSuccess}">
                    <div class="alert alert-success" role="alert">Movie was successfully deleted.</div>
                </c:if>
                <c:if test="${param.deleteMovieFailed}">
                    <div class="alert alert-danger" role="alert">Error: there was an issue deleting the movie, try again.</div>
                </c:if>
                    
                <h2>Comprehensive List of Movies</h2>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addMovie">Add Movie</button>
                <br>
                <br>
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
                                <td>${movie.distFee}</td>
                                <td>${movie.rating}</td>
                                <td>${movie.numCopies}</td>
                                
                                <td>
                                  <form method="POST" action="EditMovie">
                                      <button class="btn btn-default" name="movieId" value="${movie.id}">Edit</button>
                                  </form>
                                </td>
                                <td>
                                  <form method="POST" action="DeleteMovie">
                                    <button class="btn btn-danger" name="movieId" value="${movie.id}">Delete</button>
                                  </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a class="btn btn-default" href="Employee">Back To Main Menu</a>  
            </div>
        </div>
        
        <!-- Add Movie Modal -->
    <div id="addMovie" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Add Movie</h4>
          </div>
          <div class="modal-body">
            <form method="POST" action="AddMovie" style="padding-bottom: 40px">
              <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" name="name">
              </div>
              <div class="form-group">
                <label>Genre</label>
                <input type="text" class="form-control" name="genre">
              </div>
              <div class="form-group">
                <label>Rating</label>
                <input type="number" class="form-control" name="rating">
              </div>
              <div class="form-group">
                <label>Distribution Fee</label>
                <input type="number" class="form-control" name="distFee">
              </div>
              <div class="form-group">
                <label>Number of Copies</label>
                <input type="number" class="form-control" name="numCopies">
              </div>
              <input class="btn btn-primary pull-right" type="submit">
            </form>
          </div>
        </div>
      </div>
    </div>
        
    </body>
</html>
