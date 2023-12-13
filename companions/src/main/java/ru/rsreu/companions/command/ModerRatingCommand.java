package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.companions.DataBase.DAOFactory;

public class ModerRatingCommand implements ActionCommand {

    public ModerRatingCommand(DAOFactory daoFactory) {
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }

}
