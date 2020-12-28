package railways.server.request_handlers;

import railways.network.OperationType;

public interface RequestManager {
    RequestHandler handleRequest(OperationType operationType);
}
