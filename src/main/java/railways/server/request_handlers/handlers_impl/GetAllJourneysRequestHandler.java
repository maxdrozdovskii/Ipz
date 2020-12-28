package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import railways.network.model.NetworkModelsUtil;
import railways.server.request_handlers.RequestHandler;
import railways.database.service.JourneyService;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.stream.Collectors;

@Component
public class GetAllJourneysRequestHandler implements RequestHandler {

    @Autowired
    private JourneyService journeyService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            out.writeObject(new Response(
                            RequestCode.OK,
                            journeyService.findAll()
                                    .stream()
                                    .map(
                                            NetworkModelsUtil::convertToNetworkJourney
                                    ).collect(Collectors.toList())
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
