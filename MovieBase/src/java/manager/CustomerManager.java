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
import model.AccountType;
import model.Customer;

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
            // Insert new Customer
            String insertSQL = "INSERT INTO Customer(FirstName, LastName, Email, Type, CreditCardNumber, PhoneNumber, RegDate, Address, City, State, ZipCode) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertSQL);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setInt(4, type.ordinal());
            stmt.setString(5, ccNum);
            stmt.setString(6, phoneNumber);
            Date today = new Date(new java.util.Date().getTime());
            stmt.setDate(7, today);
            stmt.setString(8, address);
            stmt.setString(9, city);
            stmt.setString(10, state);
            stmt.setInt(10, zipCode);
            stmt.executeUpdate();
            int userId = stmt.getGeneratedKeys().getInt(1);
            Customer newCustomer = new Customer(firstName, lastName, email, type, ccNum, phoneNumber, address, city, state, zipCode);
            
            // Insert correspnding Account
            stmt = connection.prepareStatement("INSERT INTO Account(Username, Password, CustomerId) " +
                                                "VALUES(?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, userId);
            stmt.executeUpdate();
            int accountId = stmt.getGeneratedKeys().getInt(1);
            Account newAccount = new Account(accountId, username, password);
            newCustomer.setAccount(newAccount);
            
            return newCustomer;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
