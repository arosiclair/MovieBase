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
import manager.CustomerManager;
import model.Customer;
import model.Employee;

/**
 *
 * @author arosi
 */
public class EditCustomer extends HttpServlet {

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
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("employee") == null && session.getAttribute("customer") == null){
            response.sendRedirect("index.jsp?notLoggedIn=true");
            return;
        }
        
        // If this is a customer, ensure that they are editing their own account
        Customer customer = (Customer) session.getAttribute("customer");
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        if(customer != null && customerId != customer.getId()){
            response.sendRedirect("index.jsp?notLoggedIn=true");
            return;
        }else if (customer != null && customerId == customer.getId())
            request.setAttribute("isCustomer", true);
        
        request.setAttribute("customer", customer != null ? customer : CustomerManager.findCustomer(customerId));
        request.getRequestDispatcher("editcustomer.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("employee") == null && session.getAttribute("customer") == null){
            response.sendRedirect("index.jsp?notLoggedIn=true");
            return;
        }
        
        // If this is a customer, ensure that they are editing their own account
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer != null && customerId != customer.getId()){
            response.sendRedirect("index.jsp?notLoggedIn=true");
            return;
        }
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String type = request.getParameter("type");
        String creditCardNumber = request.getParameter("creditCardNumber");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        int zipCode = Integer.parseInt(request.getParameter("zipCode"));
        Customer result = CustomerManager.editCustomer(customerId, firstName, lastName, email, type, creditCardNumber, rating, phoneNumber, address, city, state, zipCode);
        if(result != null){
            if(customer != null){
                session.setAttribute("customer", result);
                response.sendRedirect("Customer?editSuccess=true");
            }else
                response.sendRedirect("ViewAllCustomers?editCustomerSuccess=true");
        }else{
            if(customer != null)
                response.sendRedirect("Customer?editFailed=true");
            else
                response.sendRedirect("ViewAllCustomers?editCustomerFailed=true");
        }
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
