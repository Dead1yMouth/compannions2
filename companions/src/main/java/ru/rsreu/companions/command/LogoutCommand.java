package ru.rsreu.companions.command;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.UsersDAO;
import ru.rsreu.companions.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {

    private UsersDAO usersDAO;

    public LogoutCommand(DAOFactory daoFactory) {
        this.usersDAO = daoFactory.getUsersDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String page = ConfigurationManager.getProperty("path.page.login");
            HttpSession session = request.getSession(false);

            String login = (String) session.getAttribute("login");

            usersDAO.changeUserStatus(login, "Offline");

            session.invalidate();
            return page;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
