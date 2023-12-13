package ru.rsreu.companions.command;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.UsersDAO;
import ru.rsreu.companions.DataBase.Data.User;
import ru.rsreu.companions.logic.LoginLogic;
import ru.rsreu.companions.resource.ConfigurationManager;
import ru.rsreu.companions.resource.MessageManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    private UsersDAO usersDAO;
    private DAOFactory daoFactory;

    public LoginCommand(DAOFactory daoFactory) {
        this.usersDAO = daoFactory.getUsersDAO();
        this.daoFactory = daoFactory;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        System.out.println("LOGIN COMMAND LOGIN: " + login);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        HttpSession session = request.getSession();

        List<User> userList = usersDAO.getUserByNickName(login); 


        if (LoginLogic.checkLogin(login, password, userList)) {
            session.setAttribute("loged", true);
            String role = userList.get(0).getRoleName();
            String Login = userList.get(0).getUserNickname();
            String userStatus = userList.get(0).getStatus();
            session.setAttribute("role", role);
            session.setAttribute("login", Login);
            session.setAttribute("status", userStatus);

            usersDAO.changeUserStatus(login, "Online");
            
            switch (role) {
                case "Admin":
                    AdminUsersCommand adminCommand = new AdminUsersCommand(daoFactory);
                    page = adminCommand.execute(request);
                    break;
                case "Moderator":
                    ModerUsersCommand moderUsersCommand = new ModerUsersCommand(daoFactory);
                    page = moderUsersCommand.execute(request);
                    break;
                case "Passenger":
                    page = ConfigurationManager.getProperty("path.page.passanger.main");
                    break;
                case "Driver":
                    page = ConfigurationManager.getProperty("path.page.driver.main");
                    break;
                default:
                    page = ConfigurationManager.getProperty("path.page.login");
                break;
            }
            return page;
        } 

        request.setAttribute("errorLoginPassMessage",
                MessageManager.getProperty("message.loginerror"));
        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
