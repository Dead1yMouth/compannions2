package ru.rsreu.companions.command;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.TripsDAO;

public class RefuseRequestCommand implements ActionCommand {

    private TripsDAO tripsDAO; 
    private DAOFactory daoFactory;

    public RefuseRequestCommand(DAOFactory daoFactory) {
        this.tripsDAO = daoFactory.getTripsDAO();
        this.daoFactory = daoFactory;
    }

    @Override
    public String execute(HttpServletRequest request) {
    
        int requestID = Integer.parseInt(request.getParameter("requestID"));

        tripsDAO.refuseTripRequest(requestID);

        PassangerTripsCommand command = new PassangerTripsCommand(daoFactory);
        String page = command.execute(request);

        return page;
    }

    
    
}
