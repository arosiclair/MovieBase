/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.RentalManager;

/**
 *
 * @author Stanley
 */
public class RentalSearch extends HttpServlet {

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
    
    String query = request.getParameter("query");
    String searchBy = request.getParameter("searchBy");
    List<HashMap<String,String>> results = null;
    
    switch (searchBy) {
      case "movie":
        results = RentalManager.getRentalByMovie(query);
        break;
      case "genre":
        results = RentalManager.getRentalByMovieType(query);
        break;
      case "firstName":
        results = RentalManager.getRentalByCustFirstName(query);
        break;
      case "lastName":
        results = RentalManager.getRentalByCustLastName(query);
        break;
      default:
    }
    
    request.setAttribute("query", query);
    request.setAttribute("searchResults", results);
    request.getRequestDispatcher("rentalsearchresults.jsp").forward(request, response);
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