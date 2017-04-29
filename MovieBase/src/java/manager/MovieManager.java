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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movie;

/**
 *
 * @author arosi
 */
public class MovieManager {
    
    public static List<Movie> getMovies(List<Integer> movieIds) {
        List<Movie> resultList = new ArrayList();
        for (Integer movieId : movieIds)
            resultList.add(getMovie(movieId));

        return resultList;
    }

    private static Movie getMovie(Integer movieId) {
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
    
}
