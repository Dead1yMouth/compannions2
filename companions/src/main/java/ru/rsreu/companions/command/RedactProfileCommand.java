package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.UsersDAO;

public class RedactProfileCommand implements ActionCommand {

    private UsersDAO usersDAO;
    private DAOFactory daoFactory;
    
    public RedactProfileCommand(DAOFactory daoFactory) {
        this.usersDAO = daoFactory.getUsersDAO();
        this.daoFactory = daoFactory;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        String login = (String) session.getAttribute("login");

        String roleName = request.getParameter("role");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        usersDAO.redactUser(login, roleName, firstName, lastName, password);

        if (role.equals("Passenger") || role.equals("Driver")) {
            ProfileCommand command = new ProfileCommand(daoFactory);
            page = command.execute(request);
            return page;
        }
        return null;
    }

}
