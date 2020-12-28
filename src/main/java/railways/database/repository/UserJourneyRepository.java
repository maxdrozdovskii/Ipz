package railways.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import railways.database.model.Journey;
import railways.database.model.User;
import railways.database.model.UserJourney;
import railways.database.model.UserJourneyId;

import java.util.List;


@Repository
public interface UserJourneyRepository extends JpaRepository<UserJourney, UserJourneyId> {

    List<UserJourney> findByPkJourney(Journey journey);

    List<UserJourney> findByPkUser(User user);
}
