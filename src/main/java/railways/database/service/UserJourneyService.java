package railways.database.service;

import railways.database.model.Journey;
import railways.database.model.User;
import railways.database.model.UserJourney;
import railways.database.model.UserJourneyId;

import java.util.List;

public interface UserJourneyService {

    List<UserJourney> findAll();

    UserJourney findById(UserJourneyId id);

    UserJourney save(UserJourney userJourney);

    void delete(UserJourney userJourney);

    List<UserJourney> findByPkJourney(Journey journey);

    List<UserJourney> findByPkUser(User user);
}
