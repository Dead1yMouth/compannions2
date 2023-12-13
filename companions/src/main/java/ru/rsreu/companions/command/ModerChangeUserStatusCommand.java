package ru.rsreu.companions.command;

// import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.UsersDAO;
// import ru.rsreu.companions.DataBase.Data.User;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class ModerChangeUserStatusCommand implements ActionCommand{

    private UsersDAO usersDAO;
    private DAOFactory daoFactory;
    private String status;

    public ModerChangeUserStatusCommand(DAOFactory daoFactory, String status) {
        this.usersDAO = daoFactory.getUsersDAO();
        this.daoFactory = daoFactory;
        this.status = status;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        // String login = (String) session.getAttribute("login");

        if (!role.equals("Moderator")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }

        String nick = request.getParameter("userNickname");
        usersDAO.changeUserStatus(nick, status);

        ModerUsersCommand command = new ModerUsersCommand(daoFactory);
        page = command.execute(request);

        return page;
    }

}
