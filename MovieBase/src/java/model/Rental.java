/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author jofrench
 */
public class Rental {

    private int id;
    private Date dateTime;
    private String employeeId;
    private int customerId;
    private int movieId;
    private Date returnDate;
    
    public Rental(){}
    
    public Rental(int id, String employeeId, int movieId, int customerId){
        this.id = id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.movieId = movieId;
    }
    
    public Rental(int id, String employeeId, int movieId, int customerId, Date dateTime, Date returnDate){
        this.id = id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.movieId = movieId;
        this.dateTime = dateTime;
        this.returnDate = returnDate;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
