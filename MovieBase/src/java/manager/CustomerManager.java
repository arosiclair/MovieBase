/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.AccountType;
import model.Customer;
import model.Movie;

/**
 *
 * @author arosi
 */
public class CustomerManager {
    
    public static Customer CreateCustomer(String firstName, String lastName, String email,
                                    AccountType type, String ccNum, String phoneNumber,
                                    String address, String city, String state, int zipCode,
                                    String username, String password){
        Connection connection = DBConnectionManager.getConnection();
        try{
            connection.setAutoCommit(false);
            // Insert new Customer
            String insertSQL = "INSERT INTO Customer(FirstName, LastName, Email, Type, CreditCardNumber, PhoneNumber, RegDate, Address, City, State, ZipCode) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, type.toString());
            stmt.setString(5, ccNum);
            stmt.setString(6, phoneNumber);
            Date today = new Date(new java.util.Date().getTime());
            stmt.setDate(7, today);
            stmt.setString(8, address);
            stmt.setString(9, city);
            stmt.setString(10, state);
            stmt.setInt(11, zipCode);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int customerId;
            if(rs.next())
               customerId = rs.getInt(1);
            else
                return null;
            Customer newCustomer = new Customer(firstName, lastName, email, type, ccNum, phoneNumber, address, city, state, zipCode);
            newCustomer.setId(customerId);
            
            // Insert correspnding Account
            stmt = connection.prepareStatement("INSERT INTO Account(Username, Password, CustomerId) " +
                                                "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, customerId);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            int accountId;
            if(rs.next())
               accountId = rs.getInt(1);
            else
                return null;
            Account newAccount = new Account(accountId, username, password);
            newCustomer.setAccount(newAccount);
            
            connection.commit();
            connection.setAutoCommit(true);
            return newCustomer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            try {
              if (connection != null) {
                connection.rollback();
              }
            } catch (SQLException ex2) {
              ex2.printStackTrace();
            }
            return null;
        }
    }
    
    public static Customer getCustomer(String username, String password){
        Connection connection = DBConnectionManager.getConnection();
        try{
            // Lookup the account object
            String query = "SELECT * FROM Account " +
                            "WHERE Username = ? AND Password = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            // If we have a match, return the customer object
            if (rs.next()){
                Account account = parseAccount(rs);
                if (account != null){
                    Customer customer = findCustomer(account.getCustomerId());
                    if(customer != null)
                        customer.setAccount(account);
                    return customer;
                }else
                    return null;
            }
            else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Customer findCustomer(int customerId){
        Connection connection = DBConnectionManager.getConnection();
        try {
            // Lookup the Customer object
            String query = "SELECT * FROM Customer " +
                            "WHERE Id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return parseCustomer(rs);
            }else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // Pass in a result set with the cursor at the customer row you want to parse
    private static Customer parseCustomer(ResultSet rs){
        try {
            int id = rs.getInt("Id");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String email = rs.getString("Email");
            AccountType type = AccountType.valueOf(rs.getString("Type"));
            String ccNum = rs.getString("CreditCardNumber");
            int rating = rs.getInt("Rating");
            String phoneNumber = rs.getString("PhoneNumber");
            Date regDate = rs.getDate("RegDate");
            String address = rs.getString("Address");
            String city = rs.getString("City");
            String state = rs.getString("State");
            int zipCode = rs.getInt("ZipCode");
            return new Customer(id, firstName, lastName, email, type, ccNum, rating, phoneNumber, regDate, address, city, state, zipCode);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<Movie> getWatchList(int customerId) {
        Connection connection = DBConnectionManager.getConnection();
        List<Integer> movieIds = getWatchListMovieIds(customerId);
        return MovieManager.getMovies(movieIds);
    }

    public static List<Integer> getWatchListMovieIds(int customerId) {
        Connection connection = DBConnectionManager.getConnection();
        String query = "SELECT MovieId FROM Queue WHERE CustomerId = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            List<Integer> movieIds = new ArrayList();
            while(rs.next())
                movieIds.add(rs.getInt("MovieId"));
            return movieIds;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static Account parseAccount(ResultSet rs) {
        try {
            int id = rs.getInt("Id");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            int customerId = rs.getInt("CustomerId");
            return new Account(id, username, password, customerId);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean addToWatchList(int customerId, int movieId) {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "INSERT INTO Queue VALUES(?, ?);";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customerId);
            stmt.setInt(2, movieId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }   

    public static List<Customer> getAllCustomers() {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT * FROM Customer";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<Customer> customers = new ArrayList();
            while(rs.next())
                customers.add(parseCustomer(rs));
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean editCustomer(int customerId, String firstName, String lastName, 
            String email, String type, String creditCardNumber, int rating, 
            String phoneNumber, String address, String city, String state, int zipCode) {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "UPDATE Customer SET FirstName = ?, LastName = ?, Email = ?, Type = ?, CreditCardNumber = ?, Rating = ?, PhoneNumber = ?, address = ?, City = ?, State = ?, ZipCode = ? WHERE Id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, type);
            stmt.setString(5, creditCardNumber);
            stmt.setInt(6, rating);
            stmt.setString(7, phoneNumber);
            stmt.setString(8, address);
            stmt.setString(9, city);
            stmt.setString(10, state);
            stmt.setInt(11, zipCode);
            stmt.setInt(12, customerId);
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean deleteCustomer(int customerId) {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "DELETE FROM Customer WHERE Id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static int getSalesReport(String selectDate) {
      try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT SUM(C.MonthlyFee) AS Total " +
                            "FROM Customer Cust, Cost C " +
                            "WHERE Cust.RegDate > ? AND Cust.Type = C.AccType;";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date regDate =  df.parse(selectDate);
            java.sql.Date sqlRegDate = new java.sql.Date(regDate.getTime());
            stmt.setDate(1, sqlRegDate);
            ResultSet rs = stmt.executeQuery();
            int salesTotal = -1;
            if (rs.next()) {
              salesTotal = rs.getInt("Total");
            }
            return salesTotal;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        } catch(ParseException ex) {
            ex.printStackTrace();
            return -3;
        }
    }
    
    public static List<HashMap<String,String>> getMostActiveCustomers() {
      // First create view of all customers and number of rentals made
      try {
            Connection connection = DBConnectionManager.getConnection();
            String query1 = "CREATE VIEW CountOrders(CustId, NumOrders) AS " +
                            "SELECT R.CustomerId, COUNT(*) " +
                            "FROM Rental R " +
                            "GROUP BY R.CustomerId;";
            PreparedStatement stmt1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            // View already exists
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
      // Query for the cusomer rep with the most rentals handled
      try {
        Connection connection = DBConnectionManager.getConnection();
        String query2 = "SELECT O.CustId, C.FirstName, C.LastName, C.Rating, O.NumOrders " + 
                        "FROM CountOrders O, Customer C " + 
                        "WHERE O.CustId = C.Id AND O.NumOrders >= (SELECT MAX(D.NumOrders) FROM CountOrders D);";
        PreparedStatement stmt2 = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt2.executeQuery();
        
        List<HashMap<String,String>> custRepList = new ArrayList<HashMap<String,String>>();
        while(rs.next()) {
          custRepList.add( parseCountOrdersAsMap(rs) );
        }

        return custRepList;
      }
      catch (SQLException ex) {
        Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        return null;
      }
    }
    
    private static HashMap<String,String> parseCountOrdersAsMap(ResultSet rs) {
      try {
        // LinkedHashMap ensures order
        HashMap<String,String> result = new LinkedHashMap<String, String>();
        result.put("CustRepId", rs.getString("CustId"));
        result.put("CustRepFirstName", rs.getString("FirstName"));
        result.put("CustRepLastName", rs.getString("LastName"));
        result.put("Rating", Integer.toString(rs.getInt("Rating")));
        result.put("NumOrders", Integer.toString(rs.getInt("NumOrders")));
        
        return result;
      } catch (SQLException ex) {
        Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        return null;
      }
    }
}
