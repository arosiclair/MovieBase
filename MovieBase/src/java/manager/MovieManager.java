/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;

/**
 *
 * @author arosi
 */
public class MovieManager {
  
    public static Movie createMovie(String name, String type, int rating, float distFee, int numCopies) {
      Connection connection = DBConnectionManager.getConnection();
        try{
            String insertSQL = "INSERT INTO Movie(Name, Type, Rating, DistrFee, NumCopies) " +
                                "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setInt(3, rating);
            stmt.setFloat(4, distFee);
            stmt.setInt(5, numCopies);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int movieId;
            if(rs.next())
               movieId = rs.getInt(1);
            else
                return null;
            Movie newMovie = new Movie(movieId, name, type, rating, distFee, numCopies);
            
            return newMovie;
        }catch (SQLException ex) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Movie> getMovies(List<Integer> movieIds) {
        List<Movie> resultList = new ArrayList();
        for (Integer movieId : movieIds)
            resultList.add(getMovie(movieId));

        return resultList;
    }

    public static Movie getMovie(Integer movieId) {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT * FROM Movie WHERE Id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return parseMovie(rs);
            else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Movie editMovie(Integer movieId, String name, String type, int rating, float distFee, int numCopies) {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "UPDATE Movie SET Name = ?, Type = ?, Rating = ?, DistrFee = ?, NumCopies = ? WHERE Id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setInt(3, rating);
            stmt.setFloat(4, distFee);
            stmt.setInt(5, numCopies);
            stmt.setInt(6, movieId);
            stmt.executeUpdate();
            
            Movie newMovie = new Movie(movieId, name, type, rating, distFee, numCopies);
            return newMovie;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean deleteMovie(int movieId) {
      try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "DELETE FROM Movie WHERE Id = ?;";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, movieId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // Pass in the result set with the cursor at the Movie row that you want parsed
    private static Movie parseMovie(ResultSet rs) {
        try {
            int id = rs.getInt("Id");
            String name = rs.getString("Name");
            String genre = rs.getString("Type");
            int rating = rs.getInt("Rating");
            int distFee = rs.getInt("DistrFee");
            int numCopies = rs.getInt("NumCopies");
            return new Movie(id, name, genre, rating, distFee, numCopies);
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Movie> getAllMovies() {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT * FROM Movie;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            List<Movie> movieList = new ArrayList<Movie>();
            while(rs.next()) {
              movieList.add(parseMovie(rs));
            }
            return movieList;  
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Movie> getBestSellingMovies() {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT M.Id, M.Name, M.Type, M.Rating, COUNT(*) AS NumRentals " +
                    "FROM Movie M, Rental R " +
                    "WHERE M.Id = R.MovieId " +
                    "GROUP BY M.Id " +
                    "ORDER BY NumRentals DESC;";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            List<Movie> bestSellers = new ArrayList();
            while(rs.next())
                bestSellers.add(parseBestSellingMovie(rs));
            return bestSellers;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static Movie parseBestSellingMovie(ResultSet rs) {
        try {
            Movie newMovie = new Movie();
            newMovie.setID(rs.getInt("Id"));
            newMovie.setName(rs.getString("Name"));
            newMovie.setGenre(rs.getString("Type"));
            newMovie.setRating(rs.getInt("Rating"));
            newMovie.setNumRentals(rs.getInt("NumRentals"));
            return newMovie;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Movie> searchMovies(String query){
        try {
            Connection connection = DBConnectionManager.getConnection();
            String sqlQuery = "SELECT * FROM Movie WHERE Name LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            List<Movie> searchResults = new ArrayList();
            while(rs.next())
                searchResults.add(parseMovie(rs));
            return searchResults;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<Movie> searchMoviesByGenre(String genre) {
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT * FROM Movie WHERE Type = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, genre);
            ResultSet rs = stmt.executeQuery();
            List<Movie> searchResults = new ArrayList();
            while(rs.next())
                searchResults.add(parseMovie(rs));
            return searchResults;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<String> getMovieGenres(){
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT DISTINCT Type FROM Movie";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<String> genres = new ArrayList();
            while(rs.next())
                genres.add(rs.getString(1));
            return genres;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<HashMap<String,String>> getMostRentedMovies() {
      // First create view of all rented movies and number of rentals made
      try {
            Connection connection = DBConnectionManager.getConnection();
            String query1 = "CREATE VIEW MovieOrders(MovieId, NumOrders) AS " +
                            "SELECT R.MovieId, COUNT(R.MovieId) " +
                            "FROM Rental R " +
                            "GROUP BY R.MovieId;";
            PreparedStatement stmt1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            // View already exists
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
      // Query for the cusomer rep with the most rentals handled
      try {
        Connection connection = DBConnectionManager.getConnection();
        String query2 = "SELECT O.MovieId, M.Name, M.Rating, O.NumOrders " + 
                        "FROM MovieOrders O, Movie M " + 
                        "WHERE O.MovieId = M.Id AND O.NumOrders >= (SELECT MAX(D.NumOrders) FROM MovieOrders D);";
        PreparedStatement stmt2 = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt2.executeQuery();
        
        List<HashMap<String,String>> custRepList = new ArrayList<HashMap<String,String>>();
        while(rs.next()) {
          custRepList.add( parseCountOrdersAsMap(rs) );
        }

        return custRepList;
      }
      catch (SQLException ex) {
        Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        return null;
      }
    }
    
    private static HashMap<String,String> parseCountOrdersAsMap(ResultSet rs) {
      try {
        // LinkedHashMap ensures order
        HashMap<String,String> result = new LinkedHashMap<String, String>();
        result.put("MovieId", rs.getString("MovieId"));
        result.put("MovieName", rs.getString("Name"));
        result.put("Rating", Integer.toString(rs.getInt("Rating")));
        result.put("NumOrders", Integer.toString(rs.getInt("NumOrders")));
        
        return result;
      } catch (SQLException ex) {
        Logger.getLogger(CustomerManager.class.getName()).log(Level.SEVERE, null, ex);
        return null;
      }
    }
    
    // decrements the number of copies for a movie, returns false if no copies are available
    public static boolean decrNumCopies(int movieId){
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "UPDATE Movie SET NumCopies = NumCopies - 1 WHERE Id = ? AND NumCopies > 0";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, movieId);
            int numAffected = stmt.executeUpdate();
            return numAffected == 1;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean incrNumCopies(int movieId){
        try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "UPDATE Movie SET NumCopies = NumCopies + 1 WHERE Id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, movieId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static List<Movie> getMoviesByActor(String name) {
      try {
            Connection connection = DBConnectionManager.getConnection();
            String query = "SELECT M.Id, M.Name, M.Type, M.Rating, M.DistrFee, M.NumCopies " + 
                    "FROM Movie M, Actor A, ActsIn Ac " + 
                    "WHERE A.Name = ? AND Ac.ActorId = A.Id AND Ac.MovieId = M.Id;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            List<Movie> searchResults = new ArrayList();
            while(rs.next())
                searchResults.add(parseMovie(rs));
            return searchResults;
        } catch (SQLException ex) {
            Logger.getLogger(MovieManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
