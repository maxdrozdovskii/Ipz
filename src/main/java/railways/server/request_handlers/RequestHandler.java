package railways.server.request_handlers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@FunctionalInterface
public interface RequestHandler {
    void execute(ObjectInputStream in, ObjectOutputStream out);
}
