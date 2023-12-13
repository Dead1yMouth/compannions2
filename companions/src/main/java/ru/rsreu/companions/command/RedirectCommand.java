package ru.rsreu.companions.command;

import ru.rsreu.companions.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RedirectCommand implements ActionCommand{

    private static final String PARAM_NAME_PAGE = "page";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String redirectPage = request.getParameter(PARAM_NAME_PAGE);
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 

        if(role.equals("Passenger")) {
            String redirectString = ConfigurationManager.getProperty("path.page.passanger.redirect");
            page = String.format(redirectString, redirectPage);
            return page;
        }

        if(role.equals("Driver")) {
            String redirectString = ConfigurationManager.getProperty("path.page.driver.redirect");
            page = String.format(redirectString, redirectPage);
            return page;
        }

        if(role.equals("Moderator")) {
            String redirectString = ConfigurationManager.getProperty("path.page.moder.redirect");
            page = String.format(redirectString, redirectPage);
            return page;
        }

        if(role.equals("Admin")) {
            String redirectString = ConfigurationManager.getProperty("path.page.admin.redirect");
            page = String.format(redirectString, redirectPage);
            return page;
        }

        page = ConfigurationManager.getProperty("path.page") + redirectPage + ".jsp";
        return page;
    }
}
