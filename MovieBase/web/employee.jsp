<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        
    <div class="main">
      <div class="container">
        <c:if test="${param.createEmployeeSuccess}">
            <div class="alert alert-success" role="alert">Employee successfully created.</div>
        </c:if>
        <c:if test="${param.createEmployeeFailed}">
            <div class="alert alert-danger" role="alert">Error: there was an issue creating the employee, try again.</div>
        </c:if>
        <h2>Welcome, ${employee.firstName} ${employee.lastName}</h2>
        <c:choose>
          <c:when test="${employee.manager == true}">
            <h4>Status: Manager</h4>
          </c:when>
          <c:otherwise>
            <h4>Status: Customer Representative</h4>
          </c:otherwise>
        </c:choose>
            
        <a class="btn btn-default" href="ListAllMovies">List All Movies</a>
        <c:if test="${employee.manager}">
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addMovie">Add Movie</button>
          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createCustRep">Add Customer Representative</button>
        </c:if> 
      </div>
    </div>
    
    <!-- Modals -->
    
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
    
    <!-- Create Customer Representative Modal -->
    <div id="createCustRep" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Add Customer Representative</h4>
          </div>
          <div class="modal-body">
            <form method="POST" action="CreateCustomerRep">
              <div class="form-group">
                <label>Username</label>
                <input type="text" class="form-control" name="username">
              </div>
              <div class="form-group">
                <label>Password</label>
                <input type="text" class="form-control" name="password">
              </div>
              <div class="form-group">
                <label for="SSN">Social Security Number</label>
                <input type="number" class="form-control" name="SSN">
              </div>
              <div class="form-group">
                <label>Hourly Rate</label>
                <input type="number" class="form-control" name="hourlyRate">
              </div>
              <div class="form-group">
                <label>First name</label>
                <input type="text" class="form-control" name="firstname">
              </div>
              <div class="form-group">
                <label>Last name</label>
                <input type="text" class="form-control" name="lastname">
              </div>
              <div class="form-group">
                <label>Phone Number</label>
                <input type="number" class="form-control" name="phonenumber">
              </div>
              <div class="form-group">
                <label>Address</label>
                <input type="text" class="form-control" name="address">
              </div>
              <div class="form-group">
                <label>City</label>
                <input type="text" class="form-control" name="city">
              </div>
              <div class="form-group">
                <label>State</label>
                <input type="text" class="form-control" name="state">
              </div>
              <div class="form-group">
                <label>ZipCode</label>
                <input type="number" class="form-control" name="zipcode">
              </div>
              <div class="checkbox">
                  <label><input type="checkbox" name="isManager">Manager?</label>
              </div>
              <input class="btn btn-primary pull-right" type="submit">
            </form>
          </div>
        </div>
        
      </div>
    </div>
    
  </body>
</html>
