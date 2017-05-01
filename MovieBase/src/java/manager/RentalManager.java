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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Rental;
/**
 *
 * @author jofrench
 */
public class RentalManager {
    public static Rental createRental(String employeeId, int movieId, int customerId){
        Connection connection = DBConnectionManager.getConnection();
        try{
            // Insert new Customer
            String insertSQL = "INSERT INTO Rental(EmployeeId, MovieId, CustomerId) " +
                                "VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, employeeId);
            stmt.setInt(2, movieId);
            stmt.setInt(3, customerId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int rentalId;
            if(rs.next())
               rentalId = rs.getInt(1);
            else
                return null;
            Rental newRental = new Rental(rentalId, employeeId, movieId, customerId);
            return newRental;
        }catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Rental findRental(int rentalId){
        Connection connection = DBConnectionManager.getConnection();
        try {
            // Lookup the Rental object
            String query = "SELECT * FROM Rental " +
                            "WHERE Id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, rentalId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return parseRental(rs);
            }else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // Pass in a result set with the cursor at the Rental row you want to parse
    private static Rental parseRental(ResultSet rs){
        try {
            int rentalId = rs.getInt("Id");
            String employeeId = rs.getString("EmployeeId");
            int movieId = rs.getInt("MovieId");
            int customerId = rs.getInt("CustomerId");
            Date dateTime = new Date(rs.getTimestamp("Timestamp").getTime());
            return new Rental(rentalId, employeeId, movieId, customerId, dateTime);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<HashMap<String,String>> getRentalByMovie(String movieName) {
      Connection connection = DBConnectionManager.getConnection();
      try {
          String query = "SELECT R.Id AS RentalId, M.Id AS MovieId, M.Type AS Genre, R.CustomerId AS CustomerId " + 
                          "FROM Rental R, Movie M " +
                          "WHERE M.Name = ? AND M.Id = R.MovieId;";
          PreparedStatement stmt = connection.prepareStatement(query);
          stmt.setString(1, movieName);
          ResultSet rs = stmt.executeQuery();

          List<HashMap<String,String>> rentalList = new ArrayList<HashMap<String,String>>();
          while(rs.next()) {
            rentalList.add( parseRentalSearchAsMap(rs) );
          }

          return rentalList;

      } catch (SQLException ex) {
          Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
    }
    
    public static List<HashMap<String,String>> getRentalByMovieType(String movieType) {
      Connection connection = DBConnectionManager.getConnection();
      try {
          String query = "SELECT R.Id AS RentalId, M.Id AS MovieId, M.Type AS Genre, R.CustomerId AS CustomerId " + 
                          "FROM Rental R, Movie M " +
                          "WHERE M.Type = ? AND M.Id = R.MovieId;";
          PreparedStatement stmt = connection.prepareStatement(query);
          stmt.setString(1, movieType);
          ResultSet rs = stmt.executeQuery();

          List<HashMap<String,String>> rentalList = new ArrayList<HashMap<String,String>>();
          while(rs.next()) {
            rentalList.add( parseRentalSearchAsMap(rs) );
          }

          return rentalList;

      } catch (SQLException ex) {
          Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
    }
    
    public static List<HashMap<String,String>> getRentalByCustFirstName(String firstName) {
      Connection connection = DBConnectionManager.getConnection();
      try {
          String query = "SELECT R.Id AS RentalId, M.Id AS MovieId, M.Type AS Genre, R.CustomerId AS CustomerId " + 
                          "FROM Rental R, Movie M, Customer C " +
                          "WHERE C.FirstName = ? AND M.Id = R.MovieId AND C.Id = R.CustomerId;";
          PreparedStatement stmt = connection.prepareStatement(query);
          stmt.setString(1, firstName);
          ResultSet rs = stmt.executeQuery();

          List<HashMap<String,String>> rentalList = new ArrayList<HashMap<String,String>>();
          while(rs.next()) {
            rentalList.add( parseRentalSearchAsMap(rs) );
          }

          return rentalList;

      } catch (SQLException ex) {
          Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
    }
    
    public static List<HashMap<String,String>> getRentalByCustLastName(String lastName) {
      Connection connection = DBConnectionManager.getConnection();
      try {
          String query = "SELECT R.Id AS RentalId, M.Id AS MovieId, M.Type AS Genre, R.CustomerId AS CustomerId " + 
                          "FROM Rental R, Movie M, Customer C " +
                          "WHERE C.LastName = ? AND M.Id = R.MovieId AND C.Id = R.CustomerId;";
          PreparedStatement stmt = connection.prepareStatement(query);
          stmt.setString(1, lastName);
          ResultSet rs = stmt.executeQuery();

          List<HashMap<String,String>> rentalList = new ArrayList<HashMap<String,String>>();
          while(rs.next()) {
            rentalList.add( parseRentalSearchAsMap(rs) );
          }

          return rentalList;

      } catch (SQLException ex) {
          Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
    }
    
    // This method is for parsing a joined table, different from a Rental table
    private static HashMap<String, String> parseRentalSearchAsMap(ResultSet rs) {
      try {
        // LinkedHashMap ensures order
        HashMap<String, String> searchResult = new LinkedHashMap<String, String>();
        
        searchResult.put("Rental ID", rs.getString("RentalId"));
        searchResult.put("Movie ID", rs.getString("MovieId"));
        searchResult.put("Genre", rs.getString("Genre"));
        searchResult.put("Customer ID", rs.getString("CustomerId"));
        
        return searchResult;
      } catch (SQLException ex) {
          Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
      
    }
}
