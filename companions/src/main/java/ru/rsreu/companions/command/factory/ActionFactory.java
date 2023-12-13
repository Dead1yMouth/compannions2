package ru.rsreu.companions.command.factory;

import ru.rsreu.companions.command.ActionCommand;
import ru.rsreu.companions.command.EmptyCommand;
import ru.rsreu.companions.command.client.CommandEnum;
import ru.rsreu.companions.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum =
                    CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
