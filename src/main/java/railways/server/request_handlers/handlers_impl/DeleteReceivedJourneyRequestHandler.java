package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import railways.database.model.UserJourneyId;
import railways.network.model.NetworkModelsUtil;
import railways.network.model.UserJourney;
import railways.server.request_handlers.RequestHandler;
import railways.database.service.UserJourneyService;
import railways.database.service.UserService;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
@Transactional
public class DeleteReceivedJourneyRequestHandler implements RequestHandler {

    @Autowired
    private UserJourneyService userJourneyService;

    @Autowired
    private UserService userService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            UserJourney userJourney = (UserJourney) in.readObject();

            railways.database.model.UserJourneyId userJourneyId = new UserJourneyId();
            userJourneyId.setJourney(NetworkModelsUtil.convertToJourney(
                    userJourney.getJourney())
            );
            userJourneyId.setUser(NetworkModelsUtil.convertToUser(
                    userJourney.getUser())
            );
            userJourneyId.setPlace(userJourney.getPlace());

            userJourneyService.delete(userJourneyService.findById(userJourneyId));

            out.writeObject(new Response(RequestCode.OK));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
