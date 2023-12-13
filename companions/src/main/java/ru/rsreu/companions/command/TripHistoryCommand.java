package ru.rsreu.companions.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.TripsDAO;
import ru.rsreu.companions.DataBase.Data.Trip;
import ru.rsreu.companions.DataBase.Data.TripInformation;
import ru.rsreu.companions.resource.ConfigurationManager;

public class TripHistoryCommand implements ActionCommand {

    TripsDAO tripsDAO;

    public TripHistoryCommand(DAOFactory daoFactory) {
        this.tripsDAO = daoFactory.getTripsDAO();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String login = (String) session.getAttribute("login");

        

        if (role.equals("Passenger")) {
            List<Trip> trips = tripsDAO.getTripHistory(login);
            request.setAttribute("tripHistory", trips);
            page = ConfigurationManager.getProperty("path.page.passanger.trips.history");
        }
        if (role.equals("Driver")) {
            List<TripInformation> trips = tripsDAO.getTripsInformation(login);
            request.setAttribute("tripHistory", trips);
            page = ConfigurationManager.getProperty("path.page.driver.trips.history");
        }
        return page;
    }

}
