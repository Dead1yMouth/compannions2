package ru.rsreu.companions.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.DBType;
import ru.rsreu.companions.DataBase.UsersDAO;
import ru.rsreu.companions.DataBase.Data.User;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

public class BlockCommand implements ActionCommand {
    private static final String BLOCKED = "BLOCKED"; 

    private UsersDAO usersDAO;
    private DAOFactory daoFactory;

    public BlockCommand() {
        this.daoFactory = DAOFactory.getInstance(DBType.ORACLE);
        this.usersDAO = daoFactory.getUsersDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); 
        // String login = (String) session.getAttribute("login");

        if(!role.equals("Moderator")) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            return page;
        }
        String login = (String) request.getAttribute("login");
        
        List<User> userList = usersDAO.getUserByNickName(login);

        String userStatus = userList.get(0).getStatus();

        if (userStatus.toUpperCase().equals(BlockCommand.BLOCKED)) {
            page = ConfigurationManager.getProperty("path.page.block");
            return page;
        }
        return null;
    }

}
