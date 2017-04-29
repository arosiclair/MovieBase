/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author arosi
 */
public class Account {
    private int id;
    private String username;
    private String password;
    private int customerId;
    private int employeeId;
    
    public Account (int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account(int id, String username, String password, int customerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
