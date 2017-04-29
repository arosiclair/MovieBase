/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arosi
 */
public class DBConnectionManager {
    
    private static String DB_URL = "jdbc:mysql://localhost:3306/moviebase";
    private static String DB_USER = "root";
    private static String DB_PASS = "password";
    
    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
}
