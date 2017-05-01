/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.MovieManager;
import model.Movie;

/**
 *
 * @author Stanley
 */
public class SaveEditMovie extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    if(session == null || session.getAttribute("employee") == null){
        response.sendRedirect("index.jsp?notLoggedIn=true");
        return;
    }
    
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String type = request.getParameter("genre");
    int rating = Integer.parseInt(request.getParameter("rating"));
    float distFee = Float.parseFloat(request.getParameter("distFee"));
    int numCopies = Integer.parseInt(request.getParameter("numCopies"));

    Movie newMovie = MovieManager.editMovie(id, name, type, rating, distFee, numCopies);
    if (newMovie != null) {
      response.sendRedirect("ListAllMovies?editMovieSuccess=true");
    }
    else {
      response.sendRedirect("ListAllMovies?editMovieFailed=true");
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
