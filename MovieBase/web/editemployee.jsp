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
        <h2>Edit Employee</h2>
          <form method="POST" action="SaveEditEmployee">
            <div class="form-group">
              <label>First name</label>
              <input type="text" class="form-control" name="firstName" value="${empToEdit.firstName}">
            </div>
            <div class="form-group">
              <label>Last name</label>
              <input type="text" class="form-control" name="lastName" value="${empToEdit.lastName}">
            </div>
            <div class="form-group">
              <label>Hourly Rate</label>
              <input type="number" class="form-control" name="hourlyRate" value="${empToEdit.hourlyRate}">
            </div>
            <div class="form-group">
              <label>Phone Number</label>
              <input type="number" class="form-control" name="phoneNumber" value="${empToEdit.phoneNumber}">
            </div>
            <div class="form-group">
              <label>Address</label>
              <input type="text" class="form-control" name="address" value="${empToEdit.address}">
            </div>
            <div class="form-group">
              <label>City</label>
              <input type="text" class="form-control" name="city" value="${empToEdit.city}">
            </div>
            <div class="form-group">
              <label>State</label>
              <input type="text" class="form-control" name="state" value="${empToEdit.state}">
            </div>
            <div class="form-group">
              <label>Zip Code</label>
              <input type="number" class="form-control" name="zipCode" value="${empToEdit.zipCode}">
            </div>
            <div class="checkbox">
              <c:choose>
                <c:when test="${empToEdit.manager == true}">
                  <c:choose>
                    <c:when test="${empToEdit.SSN == employee.SSN}">
                      <label class="hidden"><input class="hidden" type="checkbox" name="isManager" checked>Manager?</label>
                    </c:when>
                    <c:otherwise>
                      <label><input type="checkbox" name="isManager" checked>Manager?</label>
                    </c:otherwise>
                  </c:choose>
                </c:when>
                <c:otherwise>
                  <label><input type="checkbox" name="isManager">Manager?</label>
                </c:otherwise>
              </c:choose>
                
            </div>
            <a class="btn btn-default" href="ViewAllEmployees">Back To Employees</a>
            <button class="btn btn-primary pull-right" type="submit" name="ssn" value="${empToEdit.SSN}">Finish Edit</button>
          </form>
      </div>
    </div>
    
  </body>
</html>