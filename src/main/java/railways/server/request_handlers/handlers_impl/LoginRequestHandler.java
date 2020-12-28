package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import railways.database.model.User;
import railways.network.model.NetworkModelsUtil;
import railways.server.request_handlers.RequestHandler;;
import railways.database.service.UserService;
import railways.server.ApplicationProperties;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class LoginRequestHandler implements RequestHandler {

    @Autowired
    private UserService userService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {
            String username = (String) in.readObject();
            String password = (String) in.readObject();

            if (ApplicationProperties.isAdminCredentials(username, password)) {
                out.writeObject(new Response(RequestCode.OK, NetworkModelsUtil.convertToAdmin()));
                return;
            }

            User user = userService.findUserByUsername(username);

            if (user == null) {
                out.writeObject(new Response(RequestCode.ERROR, "No such user in database"));
                return;
            } else if (!user.getPassword().equals(password)) {
                out.writeObject(new Response(RequestCode.ERROR, "Incorrect password"));
                return;
            }

            out.writeObject(new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkUser(user)));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
