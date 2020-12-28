package railways.server.mail_sender;


import railways.database.model.UserJourney;

public interface MailSender {
    void sendMessage(UserJourney userJourney);
}
