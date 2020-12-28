package railways.database.service;

import railways.database.model.Journey;

import java.util.Date;
import java.util.List;

public interface JourneyService {
    List<Journey> findAll();

    Journey findById(Long id);

    List<Journey> findJourneyByFrom(String from);

    List<Journey> findJourneyByTo(String to);

    List<Journey> findJourneyByDate(Date date);

    Journey save(Journey journey);

    void delete(Journey journey);

    List<Journey> findJourneyByDateLessThan(Date date);

    long count();

    List<Journey> findJourneysForPeriod(int offset);
}
