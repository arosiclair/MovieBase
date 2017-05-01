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
                <h2>All Customers</h2>
                <table>
                    <thead>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Account Type</th>
                        <th>Rating</th>
                        <th>Phone Number</th>
                        <th>Register Date</th>
                        <th>Address</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${customers}" var="customer">
                            <tr>
                                <td>${customer.id}</td>
                                <td>${customer.firstName} ${customer.lastName}</td>
                                <td>${customer.email}</td>
                                <td>${customer.type}</td>
                                <td>${customer.rating}</td>
                                <td>${customer.phoneNumber}</td>
                                <td>${customer.regDate}</td>
                                <td>${customer.fullAddress}</td>
                                <td><a class="btn btn-default" href="CreateRentalForm?customerId=${customer.id}">Create Order</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>