package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import railways.database.model.UserJourney;
import railways.network.model.Journey;
import railways.server.request_handlers.RequestHandler;
import railways.database.service.JourneyService;
import railways.database.service.UserJourneyService;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetReceivedPlacesRequestHandler implements RequestHandler {

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private UserJourneyService userJourneyService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            Journey journey = (Journey) in.readObject();
            railways.database.model.Journey foundedJourney = journeyService.findById(journey.getId());

            List<Long> receivedPlaces = userJourneyService.findByPkJourney(foundedJourney)
                    .stream()
                    .map(UserJourney::getPlace)
                    .collect(Collectors.toList());

            out.writeObject(new Response(RequestCode.OK, receivedPlaces));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
