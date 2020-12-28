package railways.server.request_handlers.manager_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import railways.server.request_handlers.RequestHandler;
import railways.server.request_handlers.RequestManager;
import railways.server.request_handlers.handlers_impl.*;
import railways.network.OperationType;

@Component
public class RequestManagerImpl implements RequestManager {

    @Autowired
    private DeleteReceivedJourneyRequestHandler deleteReceivedJourneyRequestHandler;
    @Autowired
    private GetReceivedPlacesRequestHandler getReceivedPlacesRequestHandler;
    @Autowired
    private ReceiveJourneyRequestHandler receiveJourneyHandlerController;
    @Autowired
    private GetUserJourneysRequestHandler getUserJourneysRequestHandler;
    @Autowired
    private GetAllJourneysRequestHandler getAllJourneysRequestHandler;
    @Autowired
    private RegisterUserRequestHandler registerUserRequestHandler;
    @Autowired
    private DeleteUserRequestHandler deleteUserRequestHandler;
    @Autowired
    private LoginRequestHandler loginRequestHandler;
    @Autowired
    private DeleteJourneyRequestHandler deleteJourneyRequestHandler;
    @Autowired
    private AddJourneyRequestHandler addJourneyRequestHandler;
    @Autowired
    private JourneysCountRequestHandler journeysCountRequestHandler;
    @Autowired
    private GetJourneysForPeriodRequestHandler getJourneysForPeriodRequestHandler;

    @Override
    public RequestHandler handleRequest(OperationType operationType) {

        switch (operationType) {
            case LOGIN:
                return loginRequestHandler;
            case DELETE:
                return deleteUserRequestHandler;
            case GET_JOURNEYS:
                return getAllJourneysRequestHandler;
            case RECEIVE_JOURNEY:
                return receiveJourneyHandlerController;
            case GET_RECEIVED_PLACES:
                return getReceivedPlacesRequestHandler;
            case GET_USER_JOURNEYS:
                return getUserJourneysRequestHandler;
            case DELETE_USER_JOURNEY:
                return deleteReceivedJourneyRequestHandler;
            case REGISTER_USER:
                return registerUserRequestHandler;
            case DELETE_JOURNEY:
                return deleteJourneyRequestHandler;
            case ADD_JOURNEY:
                return addJourneyRequestHandler;
            case GET_JOURNEYS_COUNT:
                return journeysCountRequestHandler;
            case GET_JOURNEYS_FOR_PERIOD:
                return getJourneysForPeriodRequestHandler;
        }

        return null;
    }
}
