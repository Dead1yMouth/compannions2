package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.UsersDAO;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class DeleteUserCommand implements ActionCommand {

    private UsersDAO usersDAO;
    private DAOFactory daoFactory;

    public DeleteUserCommand(DAOFactory daoFactory) {
        this.usersDAO = daoFactory.getUsersDAO();
        this.daoFactory = daoFactory;
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

        String name = request.getParameter("name");

        usersDAO.deleteUser(name);
        AdminUsersCommand command = new AdminUsersCommand(daoFactory);
        page = command.execute(request);

        return page;
    }
}
