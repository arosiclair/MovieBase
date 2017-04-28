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
    private String name;
    private String genre;
    private float distFee;
    private int numCopies;
    private int rating;
    private int ID;
    private ArrayList<Actor> actors;
    
    public Movie(){}
    
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
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID=ID;
    }
    public ArrayList<Actor> getActors(){
        return actors;
    }
    public boolean hasActor(Actor a){
        return actors.contains(a);
    }
}
