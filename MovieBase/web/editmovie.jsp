<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <h2>Edit Movie</h2>
        <form method="POST" action="SaveEditMovie" style="padding-bottom: 40px">
          <div class="form-group">
            <label>Name</label>
            <input type="text" class="form-control" name="name" value="${movie.name}">
          </div>
          <div class="form-group">
            <label>Genre</label>
            <input type="text" class="form-control" name="genre" value="${movie.genre}">
          </div>
          <div class="form-group">
            <label>Rating</label>
            <input type="number" class="form-control" name="rating" value="${movie.rating}">
          </div>
          <div class="form-group">
            <label>Distribution Fee</label>
            <input type="number" class="form-control" name="distFee" value="${movie.distFee}">
          </div>
          <div class="form-group">
            <label>Number of Copies</label>
            <input type="number" class="form-control" name="numCopies" value="${movie.numCopies}">
          </div>
            <button class="btn btn-primary pull-right" type="submit" name="id" value="${movie.id}">Finish Edit</button>
        </form>
      </div>
    </div>
    
  </body>
</html>
