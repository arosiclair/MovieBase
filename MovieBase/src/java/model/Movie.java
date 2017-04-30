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
public class Movie {
    private int id;
    private String name;
    private String genre;
    private float distFee;
    private int numCopies;
    private int rating;
    private int numRentals;
    private ArrayList<Actor> actors;
    
    public Movie(){}

    public Movie(int id, String name, String genre, int rating, int distFee, int numCopies) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.distFee = distFee;
        this.numCopies = numCopies;
        actors = new ArrayList();
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public float getDistFee(){
        return distFee;
    }
    public void setDistFee(float distFee){
        this.distFee=distFee;
    }
    public int getNumCopies(){
        return numCopies;
    }
    public void setNumCopies(int numCopies){
        this.numCopies=numCopies;
    }
    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    public int getId(){
        return id;
    }
    public void setID(int Id){
        this.id=Id;
    }
    public ArrayList<Actor> getActors(){
        return actors;
    }
    public boolean hasActor(Actor a){
        return actors.contains(a);
    }

    public int getNumRentals() {
        return numRentals;
    }

    public void setNumRentals(int numRentals) {
        this.numRentals = numRentals;
    }
}
