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
      <h2>Record an Order</h2>
      <form method="POST" action="CreateRental" style="padding-bottom: 100px">
        <div class="form-group">
          <label>Movie</label><br>
          <select class="btn btn-default" name="movieId">
              <c:forEach items="${movies}" var="movie">
                  <option value="${movie.id}">${movie.name}</option>
              </c:forEach>
          </select>
        </div>
        <div class="form-group">
          <label>Customer ID</label>
          <input type="number" class="form-control" name="customerid" value="${param.customerId}">
        </div>
        <div class="form-group">
          <label>Customer Representative ID</label>
          <input type="number" class="form-control" name="customerrepid" value="${employee.SSN}">
        </div>
        <input class="btn btn-primary pull-right" type="submit">
      </form>
      <a class="btn btn-default" href="ViewAllCustomers">Back To Customers</a>  
    </div>
  </div>
    </body>
</html>
