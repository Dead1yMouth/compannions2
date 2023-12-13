package ru.rsreu.companions.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.UsersDAO;
import ru.rsreu.companions.DataBase.Data.User;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class AdminUsersCommand implements ActionCommand {


    private UsersDAO usersDAO; 

    public AdminUsersCommand(DAOFactory daoFactory) {
        this.usersDAO = daoFactory.getUsersDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        // String login = (String) session.getAttribute("login");

        if(!role.equals("Admin")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }

        List<User> users = usersDAO.getUsers();
        request.setAttribute("users", users);

        
        page = ConfigurationManager.getProperty("path.page.admin"); 
        return page;
    }
}
