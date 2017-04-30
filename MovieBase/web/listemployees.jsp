<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tlds/custom-functions.tld" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <style type="text/css">
            td, th{
                font-size: 15px;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <header class="header">
            <div class="container-fluid">
                <div class="page-header">
                    <h1>MovieBase</h1>
                </div>
            </div>
        </header>

        <!-- Main -->
        <div class="main">
            <div class="container-fluid">
                <h2>All Employees</h2>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createEmployee">Add Employee</button>
                <br>
                <br>
                <table>
                    <thead>
                        <th>SSN</th>
                        <th>Name</th>
                        <th>Start Date</th>
                        <th>Hourly Rate</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Role</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${employees}" var="employee">
                            <tr>
                                <td>${employee.SSN}</td>
                                <td>${employee.firstName} ${employee.lastName}</td>
                                <td>${employee.startDate}</td>
                                <td>$${employee.hourlyRate}</td>
                                <td>${employee.phoneNumber}</td>
                                <td>${employee.fullAddress}</td>
                                <td>
                                  <c:choose>
                                    <c:when test="${employee.manager == true}">
                                      Manager
                                    </c:when>
                                    <c:otherwise>
                                      Customer Representative
                                    </c:otherwise>
                                  </c:choose>
                                </td>
                                <td>
                                  <form method="POST" action="EditEmployee">
                                      <button class="btn btn-default" name="ssn" value="${employee.SSN}">Edit</button>
                                  </form>
                                </td>
                                <td>
                                  <form method="POST" action="DeleteEmployee">
                                    <button class="btn btn-danger" name="ssn" value="${employee.SSN}">Delete</button>
                                  </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- Create Customer Representative Modal -->
    <div id="createEmployee" class="modal fade" role="dialog">
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

