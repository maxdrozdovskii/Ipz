package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import railways.database.model.Journey;
import railways.network.model.NetworkModelsUtil;
import railways.server.request_handlers.RequestHandler;
import railways.database.service.JourneyService;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class AddJourneyRequestHandler implements RequestHandler {

    @Autowired
    private JourneyService journeyService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            railways.network.model.Journey receivedJourney = (railways.network.model.Journey) in.readObject();

            Journey journey = NetworkModelsUtil.convertToJourney(receivedJourney);
            journeyService.save(journey);

            out.writeObject(new Response(RequestCode.OK));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
