/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Employee;
import model.Movie;
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
}
