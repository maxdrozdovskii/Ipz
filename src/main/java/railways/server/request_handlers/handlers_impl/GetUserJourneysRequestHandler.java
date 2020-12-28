package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import railways.network.model.NetworkModelsUtil;
import railways.network.model.User;
import railways.network.model.UserJourney;
import railways.server.request_handlers.RequestHandler;
import railways.database.service.UserJourneyService;
import railways.database.service.UserService;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class GetUserJourneysRequestHandler implements RequestHandler {

    @Autowired
    private UserJourneyService userJourneyService;

    @Autowired
    private UserService userService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            User user = (User) in.readObject();
            railways.database.model.User foundedUser = userService.findById(user.getId());

            List<UserJourney> receivedJourneys = userJourneyService.findByPkUser(foundedUser)
                    .stream()
                    .map(NetworkModelsUtil::convertToNetworkUserJourney)
                    .collect(Collectors.toList());

            out.writeObject(new Response(RequestCode.OK, receivedJourneys));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
