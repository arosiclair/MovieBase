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
    private int id;
    private Account account;
    private String firstName;
    private String lastName;
    private String email;
    private AccountType type;
    private String CreditCardNumber;
    private int rating;
    private String phoneNumber;
    private Date regDate;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private ArrayList<Movie> movieQueue;
    
    public Customer(){}
    
    public Customer(String firstName, String lastName, String email,
                    AccountType type, String ccNum, String phoneNumber,
                    String address, String city, String state, int zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
        this.CreditCardNumber = ccNum;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.movieQueue = new ArrayList();
    }
    
    public Customer(String firstName, String lastName, String email,
                    AccountType type, String ccNum, int rating, String phoneNumber,
                    String address, String city, String state, int zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
        this.CreditCardNumber = ccNum;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.movieQueue = new ArrayList();
    }

    public Customer(int id, String firstName, String lastName, String email, AccountType type, String ccNum, int rating, String phoneNumber, String address, String city, String state, int zipCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
        this.CreditCardNumber = ccNum;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.movieQueue = new ArrayList();
    }
    
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
    public int getId(){
        return id;
    }
    public void setId(int Id){
        this.id=Id;
    }
    public AccountType getType(){
        return type;
    }
    public void setType(AccountType type){
        this.type=type;
    }
    public String getCCNum(){
        return CreditCardNumber;
    }
    public void setCCNum(String CCNum){
        this.CreditCardNumber=CCNum;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public Date getRegDate(){
        return regDate;
    }
    public void setRegDate(Date regDate){
        this.regDate=regDate;
    }
    public String getAddress(){
        return address;
    }
    public String getFullAddress(){
        return address + ", " + city + ", " + state + " " + zipCode;
    }
    public void setAddress(String address){
        this.address=address;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
