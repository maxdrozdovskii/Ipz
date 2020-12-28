package railways.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import railways.database.model.Journey;
import railways.database.model.User;
import railways.database.model.UserJourney;
import railways.database.model.UserJourneyId;
import railways.database.repository.UserJourneyRepository;
import railways.database.service.UserJourneyService;

import java.util.List;

@Service
public class UserJourneyServiceImpl implements UserJourneyService {

    @Autowired
    private UserJourneyRepository userJourneyRepository;

    @Override
    public List<UserJourney> findAll() {
        return userJourneyRepository.findAll();
    }

    @Override
    public UserJourney findById(UserJourneyId id) {
        return userJourneyRepository.findOne(id);
    }

    @Override
    public UserJourney save(UserJourney userJourney) {
        return userJourneyRepository.save(userJourney);
    }

    @Override
    public void delete(UserJourney userJourney) {
        userJourneyRepository.delete(userJourney);
    }

    @Override
    public List<UserJourney> findByPkJourney(Journey journey) {
        return userJourneyRepository.findByPkJourney(journey);
    }

    @Override
    public List<UserJourney> findByPkUser(User user) {
        return userJourneyRepository.findByPkUser(user);
    }
}
