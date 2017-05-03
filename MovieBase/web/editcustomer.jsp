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
        <h2>Edit Customer</h2>
        <form method="POST" action="EditCustomer" style="padding-bottom: 50px;">
            <div class="form-group">
              <label>First name</label>
              <input type="text" class="form-control" name="firstName" value="${customer.firstName}">
            </div>
            <div class="form-group">
              <label>Last name</label>
              <input type="text" class="form-control" name="lastName" value="${customer.lastName}">
            </div>
            <div class="form-group">
              <label>Email</label>
              <input type="text" class="form-control" name="email" value="${customer.email}">
            </div>
            <div class="form-group">
              <label>Account Type</label><br/>
              <select class="btn btn-default" name="type">
                <option value="LIMITED" ${customer.type == "LIMITED" ? "selected" : ""}>Limited</option>
                <option value="UNLIMITED_1" ${customer.type == "UNLIMITED_1" ? "selected" : ""}>Unlimited-1</option>
                <option value="UNLIMITED_2" ${customer.type == "UNLIMITED_2" ? "selected" : ""}>Unlimited-2</option>
                <option value="UNLIMITED_3" ${customer.type == "UNLIMITED_3" ? "selected" : ""}>Unlimited-3</option>
              </select>
            </div>
            <div class="form-group">
              <label>Credit Card Number</label>
              <input type="number" class="form-control" name="creditCardNumber" value="${customer.creditCardNumber}">
            </div>
            <div class="form-group">
              <label>Rating</label>
              <input type="number" class="form-control" name="rating" value="${customer.rating}">
            </div>
            <div class="form-group">
              <label>Phone Number</label>
              <input type="text" class="form-control" name="phoneNumber" value="${customer.phoneNumber}">
            </div>
            <div class="form-group">
              <label>Address</label>
              <input type="text" class="form-control" name="address" value="${customer.address}">
            </div>
            <div class="form-group">
              <label>City</label>
              <input type="text" class="form-control" name="city" value="${customer.city}">
            </div>
            <div class="form-group">
              <label>State</label>
              <input type="text" class="form-control" name="state" value="${customer.state}">
            </div>
            <div class="form-group">
              <label>Zip Code</label>
              <input type="number" class="form-control" name="zipCode" value="${customer.zipCode}">
            </div>
              <a class="btn btn-default" href="${isCustomer == true ? "Customer" : "ViewAllCustomers"}">${isCustomer == true ? "Back to Main Menu" : "Back to Customers"}</a>  
              <button class="btn btn-primary pull-right" type="submit" name="customerId" value="${customer.id}">Submit</button>
          </form>
      </div>
    </div>
  </body>
</html>