/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Date;
import java.util.ArrayList;
/**
 *
 * @author jofrench
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private int ID;
    private String type;
    private int CCNum;
    private int rating;
    private long phoneNumber;
    private Date regDate;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private ArrayList<Movie> movieQueue;
    
    public Customer(){}
    
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID=ID;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
    public int getCCNum(){
        return CCNum;
    }
    public void setCCNum(int CCNum){
        this.CCNum=CCNum;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    public long getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public Date getRegDate(){
        return regDate;
    }
    public void setRegDate(Date regDate){
        this.regDate=regDate;
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
    public ArrayList<Movie> getMovieQueue(){
        return movieQueue;
    }
}
