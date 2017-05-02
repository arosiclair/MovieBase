<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker3.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
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
        <c:if test="${param.createRentalSuccess}">
            <div class="alert alert-success" role="alert">Rental order was successfully made. Rental ID: ${param.rentalId}</div>
        </c:if>
        <c:if test="${param.createRentalFailed}">
            <div class="alert alert-danger" role="alert">Error: there was an issue creating the rental, try again.</div>
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
        
        <a class="btn btn-info" href="ViewAllCustomers">View All Customers</a>
        
        <c:if test="${employee.manager}">
          
            <a class="btn btn-info" href="ListAllMovies">View All Movies</a>
            <a class="btn btn-info" href="ViewAllEmployees">View All Employees</a>
            <br>
            <br>
            <form method="GET" action="RentalSearch">
              <div class="form-group">
                <label>Search Rentals By: </label><br>
                  <select class="btn btn-default" name="searchBy" style="margin-bottom: 10px;">
                    <option value="movie">Movie Name</option>
                    <option value="genre">Genre</option>
                    <option value="firstName">Customer First Name</option>
                    <option value="lastName">Customer Last Name</option>
                  </select>
                  <input class="form-control" name="query" style="margin-bottom: 10px;">
                  <input class="btn btn-primary" type="submit">
              </div>
            </form>
            
            <form method="GET" action="SalesReport">
              <div class="form-group">
                <label>Obtain Sales Report For: </label>
                  <div class="form-group">
                    <div class="input-group date" id="datepicker">
                      <input type="text" class="form-control" name="date" />
                      <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                  </div>
                <script type="text/javascript">
                    $(function () {
                        $('#datepicker').datepicker( {
                          format: "yyyy-mm-dd",
                          viewMode: "months",
                          minViewMode: "months"
                        });
                    });
                </script>
                <input class="btn btn-primary" type="submit">
              </div>
            </form>
            <c:if test="${param.salesReportError}">
              <div class="alert alert-danger alert-dismissable fade in" role="alert">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                Error: there was an issue obtaining the sales report, try again.
              </div>
            </c:if>
            <c:if test="${not empty salesTotal}">
              <div class="alert alert-info alert-dismissable fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                Overall Income Since <fmt:formatDate value="${selectedDate}" type="date" dateStyle="medium" />
              : <strong>$${salesTotal}</strong>
              </div>
            </c:if>
            
          
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
