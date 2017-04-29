/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import manager.DBConnectionManager;


/**
 *
 * @author Rustam
 */
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //App Initialized Here
        System.out.println("Servlet Context Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionManager.closeConnection();
    }
    
}
