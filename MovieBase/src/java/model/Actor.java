/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
/**
 *
 * @author jofrench
 */
public class Actor {
    private String name;
    private int age;
    private String gender;
    private int ID;
    private int rating;
    private ArrayList<Movie> moviesAppearedIn;
    
    public Actor(){}
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID=ID;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    public ArrayList<Movie> getMoviesAppearedIn(){
        return moviesAppearedIn;
    }
    public boolean actsIn(Movie m){
        return moviesAppearedIn.contains(m);
    }
}
