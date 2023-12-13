package ru.rsreu.companions.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.TripsDAO;
import ru.rsreu.companions.DataBase.Data.Trip;
import ru.rsreu.companions.resource.ConfigurationManager;

public class ModerTripsCommand implements ActionCommand {

    private TripsDAO tripsDAO;

    public ModerTripsCommand(DAOFactory daoFactory) {
        this.tripsDAO = daoFactory.getTripsDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        
        String page = null;

        List<Trip> trips = tripsDAO.getAllTrips();
        request.setAttribute("trips", trips);

        page = ConfigurationManager.getProperty("path.page.moder.trips");
        return page;
    }
}
