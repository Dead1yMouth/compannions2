package ru.rsreu.companions.filter;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.command.client.CommandEnum;
import ru.rsreu.companions.resource.ConfigurationManager;

public class ServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("----FILTERING----");
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        if (!httpRequest.getParameter("command").equals("login")){
            try {
                Boolean loged = (Boolean) session.getAttribute("loged");
                if (loged == null) {
                    System.out.print("USER IS NOT LOGGED");
                    String page = ConfigurationManager.getProperty("path.page.login");
                    RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(page);
                    dispatcher.forward(request, response);
                    return;
                } else if (loged != null) {
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        chain.doFilter(httpRequest, response);

        System.out.println("----FILTERING-END----");
    }

    @Override
    public void destroy() {
    }

}
