package ru.rsreu.companions.command.client;

import ru.rsreu.companions.DataBase.DAOFactory;
import ru.rsreu.companions.DataBase.DBType;
import ru.rsreu.companions.command.AcceptTripRequestCommand;
import ru.rsreu.companions.command.ActionCommand;
import ru.rsreu.companions.command.AddUserCommand;
import ru.rsreu.companions.command.AdminUsersCommand;
import ru.rsreu.companions.command.DeclineRequestCommand;
import ru.rsreu.companions.command.DeleteReviewCommand;
import ru.rsreu.companions.command.DeleteUserCommand;
import ru.rsreu.companions.command.DriverTripsCommand;
import ru.rsreu.companions.command.LoginCommand;
import ru.rsreu.companions.command.LogoutCommand;
import ru.rsreu.companions.command.MakeReviewCommand;
import ru.rsreu.companions.command.MakeTripCommand;
import ru.rsreu.companions.command.MakeTripsRequestCommand;
import ru.rsreu.companions.command.MenuCommand;
import ru.rsreu.companions.command.ModerChangeUserStatusCommand;
import ru.rsreu.companions.command.ModerRatingCommand;
import ru.rsreu.companions.command.ModerReviewsCommand;
import ru.rsreu.companions.command.ModerTripsCommand;
import ru.rsreu.companions.command.ModerUsersCommand;
import ru.rsreu.companions.command.RedirectCommand;
import ru.rsreu.companions.command.RefuseRequestCommand;
import ru.rsreu.companions.command.RemoveTripCommand;
import ru.rsreu.companions.command.TripHistoryCommand;
import ru.rsreu.companions.command.UserProfileCommand;
import ru.rsreu.companions.logic.DAOLogic;
import ru.rsreu.companions.command.PassangerTripsCommand;
import ru.rsreu.companions.command.ProfileCommand;
import ru.rsreu.companions.command.RedactProfileCommand;
import ru.rsreu.companions.command.RedactReviewCommand;
import ru.rsreu.companions.command.RedactTripCommand;
import ru.rsreu.companions.command.RedactUserCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand(factory);
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand(factory);
        }
    },

    REDIRECT {
        {
            this.command = new RedirectCommand();
        }
    },

    HOME {
        {
            this.command = new MenuCommand();
        }
    },

    HISTORY {
        {
            this.command = new TripHistoryCommand(factory);
        }
    },

    TRIPS_PASSANGER {
        {
            this.command = new PassangerTripsCommand(factory);
        }
    },

    REFUSE_REQUEST {
        {
            this.command = new RefuseRequestCommand(factory);
        }
    },

    MAKE_REQUEST {
        {
            this.command = new MakeTripsRequestCommand(factory);   
        }
    },

    ADMIN_USERS {
        {
            this.command = new AdminUsersCommand(factory);
        }
    },
    
    TRIPS_DRIVER {
        {
            this.command = new DriverTripsCommand(factory);
        }
    },
    
    MAKE_TRIP {
        {
            this.command = new MakeTripCommand(factory);
        }
    },
    
    ACCEPT_REQUEST {
        {
            this.command = new AcceptTripRequestCommand(factory);
        }
    },
    
    DECLINE_REQUEST {
        {
            this.command = new DeclineRequestCommand(factory);
        }
    },
    
    REMOVE_TRIP {
        {
            this.command = new RemoveTripCommand(factory); 
        }
    },
    
    REDACT_TRIP {
        {
            this.command = new RedactTripCommand(factory);
        }
    },
    
    PROFILE {
        {
            this.command = new ProfileCommand(factory);
        }
    },
    
    USER_PROFILE {
        {
            this.command = new UserProfileCommand(factory);
        }  
    },

    REDACT_PROFILE {
        {
            this.command = new RedactProfileCommand(factory);
        }
    },

    MODER_USERS {
        {
            this.command = new ModerUsersCommand(factory);
        }
    },

    MODER_RATING {
        {
            this.command = new ModerRatingCommand(factory);
        }
    },

    MODER_BLOCK_REQUEST {
        {
            this.command = new ModerChangeUserStatusCommand(factory, "Blocked");
        }
    },

    MODER_UNBLOCK_REQUEST {
        {
            this.command = new ModerChangeUserStatusCommand(factory, "Offline");
        }
    },

    MODER_TRIPS {
        {
            this.command = new ModerTripsCommand(factory);
        }
    },

    MODER_REVIEWS {
        {
            this.command = new ModerReviewsCommand(factory);
        }  
    },

    REDACT_REVIEW {
        {
            this.command = new RedactReviewCommand(factory);
        }
    },

    MAKE_REVIEW {
        {
            this.command = new MakeReviewCommand(factory);
        }
    },

    DELETE_REVIEW {
        {
            this.command = new DeleteReviewCommand(factory);
        }
    },

    ADD_USER {
        {
            this.command = new AddUserCommand(factory);
        }
    },
    
    REDACT_USER {
        {
            this.command = new RedactUserCommand(factory);
        }
    },

    DELETE_USER {
        {
            this.command = new DeleteUserCommand(factory);
        }
    };

    DAOFactory factory; 
    {
        factory = DAOLogic.getDaoFactory();
    };

    ActionCommand command;
    public  ActionCommand getCurrentCommand() {
        return  command;
    }
}
