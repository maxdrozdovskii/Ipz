package railways.network.model;

public class NetworkModelsUtil {

    public static Journey convertToNetworkJourney(railways.database.model.Journey journey) {
        return new Journey(
                journey.getId(),
                journey.getFrom(),
                journey.getTo(),
                journey.getPlaces(),
                journey.getPrice(),
                journey.getDate()
        );
    }

    public static User convertToNetworkUser(railways.database.model.User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }

    public static User convertToNetworkUser(railways.database.model.User user, boolean isAdmin) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                isAdmin
        );
    }

    public static User convertToAdmin() {
        String admin = "Admin";
        return new User(0L, admin, admin, admin, admin, admin, true);
    }

    public static UserJourney convertToNetworkUserJourney(railways.database.model.UserJourney userJourney) {
        return new UserJourney(
                userJourney.getPlace(),
                convertToNetworkUser(userJourney.getUser()),
                convertToNetworkJourney(userJourney.getJourney())
        );
    }

    public static railways.database.model.Journey convertToJourney(Journey networkJourney) {
        railways.database.model.Journey journey = new railways.database.model.Journey();

        journey.setDate(networkJourney.getDate());
        journey.setFrom(networkJourney.getFrom());
        journey.setTo(networkJourney.getTo());
        journey.setId(networkJourney.getId());
        journey.setPrice(networkJourney.getPrice());
        journey.setPlaces(networkJourney.getPlaces());

        return journey;
    }

    public static railways.database.model.User convertToUser(User user) {
        railways.database.model.User newUser = new railways.database.model.User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setSurname(user.getSurname());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setId(user.getId());

        return newUser;
    }

    public static railways.database.model.UserJourney convertToUserJourney(UserJourney userJourney) {
        User networkUser = userJourney.getUser();
        Journey networkJourney = userJourney.getJourney();

        railways.database.model.User user = new railways.database.model.User();
        user.setId(networkUser.getId());
        user.setPassword(networkUser.getPassword());
        user.setUsername(networkUser.getUsername());
        user.setSurname(networkUser.getSurname());
        user.setName(networkUser.getName());
        user.setEmail(networkUser.getEmail());

        railways.database.model.Journey journey = new railways.database.model.Journey();
        journey.setDate(networkJourney.getDate());
        journey.setFrom(networkJourney.getFrom());
        journey.setTo(networkJourney.getTo());
        journey.setId(networkJourney.getId());
        journey.setPlaces(networkJourney.getPlaces());
        journey.setPrice(networkJourney.getPrice());

        railways.database.model.UserJourney newUserJourney = new railways.database.model.UserJourney();

        newUserJourney.setPlace(userJourney.getPlace());
        newUserJourney.setJourney(journey);
        newUserJourney.setUser(user);

        return newUserJourney;
    }

    public static railways.database.model.UserJourneyId convertToUserJourneyId(UserJourney userJourney) {
        User networkUser = userJourney.getUser();
        Journey networkJourney = userJourney.getJourney();

        railways.database.model.User user = new railways.database.model.User();
        user.setId(networkUser.getId());
        user.setPassword(networkUser.getPassword());
        user.setUsername(networkUser.getUsername());
        user.setSurname(networkUser.getSurname());
        user.setName(networkUser.getName());
        user.setEmail(networkUser.getEmail());

        railways.database.model.Journey journey = new railways.database.model.Journey();
        journey.setDate(networkJourney.getDate());
        journey.setFrom(networkJourney.getFrom());
        journey.setTo(networkJourney.getTo());
        journey.setId(networkJourney.getId());
        journey.setPlaces(networkJourney.getPlaces());
        journey.setPrice(networkJourney.getPrice());

        railways.database.model.UserJourneyId newUserJourney = new railways.database.model.UserJourneyId();

        newUserJourney.setPlace(userJourney.getPlace());
        newUserJourney.setJourney(journey);
        newUserJourney.setUser(user);

        return newUserJourney;
    }
}
