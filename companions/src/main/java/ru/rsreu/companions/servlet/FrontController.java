package ru.rsreu.companions.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ru.rsreu.companions.command.ActionCommand;
import ru.rsreu.companions.command.factory.ActionFactory;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        
        // HttpSession session = request.getSession(true);
        
        System.out.println("ATTENTION!!!! REQUEST IN PROCESS");
        
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        
        if (page != null) {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request,response);
        } else {
            
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }

    }

}
