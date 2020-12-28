package railways.network;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import railways.database.service.JourneyService;

import java.util.Date;

@Service
@EnableAsync
public class JourneysClear {

    @Autowired
    private JourneyService journeyService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void clear() {
        Date date = new Date();

        journeyService.findJourneyByDateLessThan(date)
                .forEach(journeyService::delete);
    }
}
