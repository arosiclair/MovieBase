<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="container">
            <h2>Search Movies</h2>
            <form method="GET" action="TextSearch">
                <div class="form-group">
                    <label>Search by Text</label>
                    <input class="form-control" name="query" style="margin-bottom: 10px;">
                    <input class="btn btn-primary" type="submit">
                </div>
            </form>
            <form method="GET" action="GenreSearch">
                <div class="form-group">
                    <label>Search by Genre</label><br>
                    <select class="btn btn-default" name="genre" style="margin-bottom: 10px;">
                        <c:forEach items="${genres}" var="genre">
                            <option value="${genre}">${genre}</option>
                        </c:forEach>
                    </select><br>
                    <input class="btn btn-primary" type="submit">
                </div>
            </form>
            <a class="btn btn-default" href="Customer">Back To Main Menu</a>
        </div>
    </body>
</html>
