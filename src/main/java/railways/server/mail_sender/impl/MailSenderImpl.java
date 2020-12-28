package railways.server.mail_sender.impl;

import org.springframework.stereotype.Service;
import railways.database.model.Journey;
import railways.database.model.User;
import railways.database.model.UserJourney;
import railways.server.ApplicationProperties;
import railways.server.mail_sender.MailSender;
import railways.server.mail_sender.PdfGenerator;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Properties;

@Service
public class MailSenderImpl implements MailSender {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    private String username;
    private String password;
    private Properties properties;

    {
        this.properties = new Properties();
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.starttls.enable", "true");
        this.properties.put("mail.smtp.host", "smtp.gmail.com");
        this.properties.put("mail.smtp.port", "587");
    }

    public MailSenderImpl() {
        this.username = ApplicationProperties.getMailUser();
        this.password = ApplicationProperties.getMailPassword();

    }

    public MailSenderImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private Message defaultMessage(String email) throws MessagingException {
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setSubject("Railway");
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

        return message;
    }

    private String generateMessageText(UserJourney userJourney) {
        User user = userJourney.getUser();
        Journey journey = userJourney.getJourney();
        long place = userJourney.getPlace();

        return "Dear " + user.getName() + " " + user.getSurname() + "\n\n" +
                "Thank you for receiving ticket for journey : \n" +
                "From " + journey.getFrom() + " ,to " + journey.getTo() +
                " ,place " + place + " at " + dateFormat.format(journey.getDate()) + "\n\n" +
                "Please print a ticket from a file";
    }

    public void sendMessage(UserJourney userJourney) {
        try {
            User user = userJourney.getUser();
            Message message = defaultMessage(user.getEmail());

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(generateMessageText(userJourney));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();

            PdfGenerator pdfGenerator = new PdfGenerator(userJourney);

            String filename = pdfGenerator.generate();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Railway ticket.pdf");
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            pdfGenerator.delete();
        } catch (MessagingException ignore) {
            ignore.printStackTrace();
        }
    }

}
