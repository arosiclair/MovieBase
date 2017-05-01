/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.EmployeeManager;
import model.Employee;

/**
 *
 * @author Stanley
 */
public class SaveEditEmployee extends HttpServlet {

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
    
    String ssn = request.getParameter("ssn");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    int hourlyRate = Integer.parseInt(request.getParameter("hourlyRate"));
    String phoneNumber = request.getParameter("phoneNumber");
    String address = request.getParameter("address");
    String city = request.getParameter("city");
    String state = request.getParameter("state");
    int zipCode = Integer.parseInt(request.getParameter("zipCode"));
    String isManagerString = request.getParameter("isManager");
    boolean isManager = false;
    if(isManagerString != null)
      isManager = true;

    Employee employee = EmployeeManager.editEmployee(ssn, firstName, lastName, hourlyRate, phoneNumber, address, city, state, zipCode, isManager);
    if (employee != null) {
      response.sendRedirect("ViewAllEmployees?editEmployeeSuccess=true");
    }
    else {
      response.sendRedirect("ViewAllEmployees?editEmployeeFailed=true");
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
