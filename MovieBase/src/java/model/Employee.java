/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jofrench
 */
public class Employee {
    private long phoneNumber;
    private long SSN;
    private int wage;
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    
    public long getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public long getSSN(){
        return SSN;
    }
    public void setSSN(long SSN){
        this.SSN=SSN;
    }
    public int getWage(){
        return wage;
    }
    public void setWage(int wage){
        this.wage=wage;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAddress(){
        return streetAddress + ", " + city + ", " + state + " " + zipCode;
    }
    public String getStreetAddress(){
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress){
        this.streetAddress=streetAddress;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state=state;
    }
    public int getZipCode(){
        return zipCode;
    }
    public void setZipCode(int zipCode){
        this.zipCode=zipCode;
    }
}
