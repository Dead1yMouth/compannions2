package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.resource.ConfigurationManager;

public class MenuCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role.equals("Passenger")) {
            page = ConfigurationManager.getProperty("path.page.passanger.main");

        }
        if (role.equals("Driver")) {
            page = ConfigurationManager.getProperty("path.page.driver.main");
        }
        return page;
    }

}
