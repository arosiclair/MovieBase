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
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat:400,700' />
        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Material Design Lite
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script> -->
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
                <c:if test="${param.notLoggedIn}">
                    <div class="alert alert-danger" role="alert">You need to be logged in to do that. Please Log in.</div>
                </c:if>
                <c:if test="${param.loginFailed}">
                    <div class="alert alert-danger" role="alert">Incorrect log in credentials. Try Again.</div>
                </c:if>
                <h2>Log In</h2>
                <form method="POST" action="Login" style="margin-bottom: 20px;">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" class="form-control" name="username">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary" name="action" value="customer">Login</button>
                    <button type="submit" class="btn btn-info" name="action" value="employee">Employee Login</button>
                </form>
                <p>Need an account?</p>
                <a href="register.html"><button class="btn btn-default">Register</button></a>
            </div>
        </div>

    </body>
</html>
