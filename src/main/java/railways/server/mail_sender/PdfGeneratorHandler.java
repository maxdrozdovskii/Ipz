package railways.server.mail_sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import railways.database.model.UserJourney;

@Service
public class PdfGeneratorHandler {

    @Autowired
    private MessageQueue messageQueue;

    @Autowired
    private MailSender mailSender;

    @Scheduled(fixedRate = 2000)
    public void event() {
        while (true) {
            UserJourney userJourney = messageQueue.get();
            mailSender.sendMessage(userJourney);
        }
    }
}
