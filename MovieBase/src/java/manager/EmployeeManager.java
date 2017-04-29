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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Employee;

/**
 *
 * @author Stanley
 */
public class EmployeeManager {
    public static Employee createEmployee(long SSN, String firstName, String lastName, int hourlyRate,
                                            String phoneNumber, String address, String city, String state, int zipCode,
                                            boolean isManager, String username, String password){
        Connection connection = DBConnectionManager.getConnection();
        try{
            
            String insertSQL = "INSERT INTO Employee(SSN, FirstName, LastName, PhoneNumber, StartDate, hourlyRate, Address, City, State, ZipCode, isManager) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, SSN);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, phoneNumber);
            Date today = new Date(new java.util.Date().getTime());
            stmt.setDate(5, today);
            stmt.setInt(6, hourlyRate);
            stmt.setString(7, address);
            stmt.setString(8, city);
            stmt.setString(9, state);
            stmt.setInt(10, zipCode);
            stmt.setBoolean(11, isManager);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int employeeId;
            if(rs.next())
               employeeId = rs.getInt(1);
            else
                return null;
            Employee newEmployee = new Employee(SSN, firstName, lastName, phoneNumber, today, hourlyRate, address, city, state, zipCode, isManager);
            
            // Insert correspnding Account
            stmt = connection.prepareStatement("INSERT INTO Account(Username, Password, EmployeeId) " +
                                                "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, employeeId);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            int accountId;
            if(rs.next())
               accountId = rs.getInt(1);
            else
                return null;
            Account newAccount = new Account(accountId, username, password);
            newEmployee.setAccount(newAccount);
            
            return newEmployee;
        }catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
  public static Employee getEmployee(String username, String password){
        Connection connection = DBConnectionManager.getConnection();
        try{
            // Lookup the account object
            String query = "SELECT * FROM Account" +
                            "WHERE Username = ? AND Password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            // If we have a match, return the employee object
            if (rs.next()){
                int empId = rs.getInt("employeeId");
                return findEmployee(empId);
            }
            else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Employee findEmployee(int employeeId){
        Connection connection = DBConnectionManager.getConnection();
        try {
            // Lookup the Customer object
            String query = "SELECT * FROM Employee" +
                            "WHERE Id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return parseEmployee(rs);
            }else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // Pass in a result set with the cursor at the customer row you want to parse
    private static Employee parseEmployee(ResultSet rs){
        try {
            long SSN = rs.getLong("SSN");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String phoneNumber = rs.getString("Telephone");
            Date startDate = rs.getDate("StartDate");
            int hourlyRate = rs.getInt("HourlyRate");
            String address = rs.getString("Address");
            String city = rs.getString("City");
            String state = rs.getString("State");
            int zipCode = rs.getInt("ZipCode");
            boolean isManager = rs.getBoolean("Manager");
            return new Employee(SSN, firstName, lastName, phoneNumber, startDate, hourlyRate, address, city, state, zipCode, isManager);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
  
}
