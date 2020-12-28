package railways.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import railways.database.model.Journey;

import java.util.List;
import java.util.Date;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {

    List<Journey> findJourneyByFrom(String from);

    List<Journey> findJourneyByTo(String to);

    List<Journey> findJourneyByDate(Date date);

    List<Journey> findJourneyByDateLessThan(Date date);
}
