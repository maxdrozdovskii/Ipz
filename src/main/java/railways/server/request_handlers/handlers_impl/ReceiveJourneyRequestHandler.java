package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import railways.server.mail_sender.MessageQueue;
import railways.network.model.NetworkModelsUtil;
import railways.network.model.UserJourney;
import railways.server.request_handlers.RequestHandler;
import railways.database.service.UserJourneyService;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class ReceiveJourneyRequestHandler implements RequestHandler {

    @Autowired
    private UserJourneyService userJourneyService;

    @Autowired
    private MessageQueue messageQueue;

    @Override
    synchronized public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            UserJourney userJourney = (UserJourney) in.readObject();

            railways.database.model.UserJourneyId journeyId = NetworkModelsUtil
                    .convertToUserJourneyId(userJourney);

            if (userJourneyService.findById(journeyId) != null) {
                out.writeObject(new Response(RequestCode.ERROR));
                return;
            }

            railways.database.model.UserJourney savedUser = userJourneyService.save(
                    NetworkModelsUtil.convertToUserJourney(userJourney)
            );

            messageQueue.add(savedUser);

            out.writeObject(new Response(RequestCode.OK));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
