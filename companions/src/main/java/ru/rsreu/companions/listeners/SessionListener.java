package ru.rsreu.companions.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
         System.out.println("SESSION CREATED");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
            System.out.println("SESSION DESTROED");       
    }
    
}
