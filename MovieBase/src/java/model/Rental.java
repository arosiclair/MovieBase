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
    private Date rentDate;
    private Time rentTime;
    private int ID;
    private Employee employee;
    private Movie movie;
    private Customer customer;
    
    public Rental(){}
    
    public Rental(Date rentDate, Time rentTime, int ID, int employeeId, int movieId, int customerId){
        this.rentDate=rentDate;
        this.rentTime=rentTime;
        this.ID = ID;
        this.employee.setSSN(employeeId);
        this.movie.setID(movieId);
        this.customer.setID(customerId);
    }
    
    public Date getRentDate(){
        return rentDate;
    }
    public void setRentDate(Date rentDate){
        this.rentDate=rentDate;
    }
    public Time getRentTime(){
        return rentTime;
    }
    public void setRentTime(Time rentTime){
        this.rentTime=rentTime;
    }
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID=ID;
    }
    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee=employee;
    }
    public Movie getMovie(){
        return movie;
    }
    public void setMovie(Movie movie){
        this.movie=movie;
    }
    public Customer getCustomer(){
        return customer;
    }
    public void setCustomer(Customer customer){
        this.customer=customer;
    }
}
