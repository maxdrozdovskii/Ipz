package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import railways.database.service.JourneyService;
import railways.network.RequestCode;
import railways.network.Response;
import railways.server.request_handlers.RequestHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class JourneysCountRequestHandler implements RequestHandler {

    @Autowired
    private JourneyService journeyService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            out.writeObject(new Response(RequestCode.OK, journeyService.count()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
