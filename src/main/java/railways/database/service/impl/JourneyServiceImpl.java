package railways.database.service.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import railways.database.model.Journey;
import railways.database.repository.JourneyRepository;
import railways.database.service.JourneyService;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {

    private static final int PERIOD_LIMIT = 10;

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Journey> findAll() {
        return journeyRepository.findAll();
    }

    @Override
    public Journey findById(Long id) {
        return journeyRepository.findOne(id);
    }

    @Override
    public List<Journey> findJourneyByFrom(String from) {
        return journeyRepository.findJourneyByFrom(from);
    }

    @Override
    public List<Journey> findJourneyByTo(String to) {
        return journeyRepository.findJourneyByTo(to);
    }

    @Override
    public List<Journey> findJourneyByDate(Date date) {
        return journeyRepository.findJourneyByDate(date);
    }

    @Override
    public Journey save(Journey journey) {
        return journeyRepository.save(journey);
    }

    @Override
    public void delete(Journey journey) {
        journeyRepository.delete(journey);
    }

    @Override
    public List<Journey> findJourneyByDateLessThan(Date date) {
        return journeyRepository.findJourneyByDateLessThan(date);
    }

    @Override
    public long count() {
        return journeyRepository.count();
    }

    @Override
    @Transactional
    public List<Journey> findJourneysForPeriod(int offset) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Journey.class);
        criteria.setFirstResult(offset * PERIOD_LIMIT);
        criteria.setMaxResults(PERIOD_LIMIT);

        return (List<Journey>) criteria.list();
    }

}
