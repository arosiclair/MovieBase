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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Employee;

/**
 *
 * @author Stanley
 */
public class EmployeeManager {
    public static Employee createEmployee(String SSN, String firstName, String lastName, int hourlyRate,
                                            String phoneNumber, String address, String city, String state, int zipCode,
                                            boolean isManager, String username, String password){
        Connection connection = DBConnectionManager.getConnection();
        try{
            
            String insertSQL = "INSERT INTO Employee(SSN, FirstName, LastName, PhoneNumber, StartDate, hourlyRate, Address, City, State, ZipCode, Manager) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, SSN);
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
            Employee newEmployee = new Employee(SSN, firstName, lastName, phoneNumber, today, hourlyRate, address, city, state, zipCode, isManager);
            
            // Insert correspnding Account
            stmt = connection.prepareStatement("INSERT INTO Account(Username, Password, EmployeeId) " +
                                                "VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, SSN);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
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
            String query = "SELECT * FROM Account " +
                            "WHERE Username = ? AND Password = ?;";
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
            // Lookup the Employee object
            String query = "SELECT * FROM Employee " +
                            "WHERE SSN = ?;";
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
    
    // Pass in a result set with the cursor at the employee row you want to parse
    private static Employee parseEmployee(ResultSet rs){
        try {
            String SSN = rs.getString("SSN");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String phoneNumber = rs.getString("PhoneNumber");
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
    
    public static List<Employee> getAllEmployees() {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT * FROM Employee";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<Employee> employees = new ArrayList();
            while(rs.next())
                employees.add(parseEmployee(rs));
            return employees;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Employee editEmployee(String ssn, String firstName, String lastName, int hourlyRate, String phoneNumber, String address, String city, String state, int zipCode, boolean isManager) {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "UPDATE Employee SET FirstName = ?, LastName = ?, HourlyRate = ?, PhoneNumber = ?, Address = ?, City = ?, State = ?, ZipCode = ?, Manager = ? WHERE SSN = ?;";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, hourlyRate);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, address);
            stmt.setString(6, city);
            stmt.setString(7, state);
            stmt.setInt(8, zipCode);
            stmt.setBoolean(9, isManager);
            stmt.setString(10, ssn);
            stmt.executeUpdate();
            
            Employee employee = new Employee(ssn, firstName, lastName, phoneNumber, hourlyRate, address, city ,state, zipCode, isManager);
            return employee;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean deleteEmployee(String ssn) {
      try {
            Connection connection = DBConnectionManager.getConnection();
            String query1 = "DELETE FROM Account WHERE employeeId = ?;";
            PreparedStatement stmt1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, ssn);
            stmt1.executeUpdate();
              
            String query2 = "DELETE FROM Employee WHERE SSN = ?;";
            PreparedStatement stmt2 = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
            stmt2.setString(1, ssn);
            stmt2.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  
}
