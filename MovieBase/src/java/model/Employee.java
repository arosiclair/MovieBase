/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author jofrench
 */
public class Employee {

    private Account account;
    private String phoneNumber;
    private String SSN;
    private Date startDate;
    private int hourlyRate;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private boolean isManager;

    public Employee() {
    }
    
    public Employee(String SSN, String firstName, String lastName, 
            String phoneNumber, int hourlyRate, String address, String city ,
            String state, int zipCode, boolean isManager) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.hourlyRate = hourlyRate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.isManager = isManager;
      
    }

    public Employee(String SSN, String firstName, String lastName, String phoneNumber,
            Date startDate, int hourlyRate, String address, String city,
            String state, int zipCode, boolean isManager) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.hourlyRate = hourlyRate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.isManager = isManager;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullAddress() {
        return address + ", " + city + ", " + state + " " + zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager() {
        this.isManager = true;
    }
}
