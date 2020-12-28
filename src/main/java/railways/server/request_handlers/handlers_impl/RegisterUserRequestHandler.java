package railways.server.request_handlers.handlers_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import railways.database.model.User;
import railways.network.model.NetworkModelsUtil;
import railways.server.request_handlers.RequestHandler;
import railways.database.service.UserService;
import railways.network.RequestCode;
import railways.network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class RegisterUserRequestHandler implements RequestHandler {
    @Autowired
    private UserService userService;

    @Override
    public void execute(ObjectInputStream in, ObjectOutputStream out) {
        try {

            User receivedUser = NetworkModelsUtil.convertToUser(
                    (railways.network.model.User) in.readObject()
            );

            User user = userService.findUserByUsername(receivedUser.getUsername());

            if (user != null) {
                out.writeObject(new Response(
                        RequestCode.ERROR, "User with such username already exist"
                ));
            } else {
                user = userService.save(receivedUser);

                out.writeObject(
                        new Response(RequestCode.OK, NetworkModelsUtil.convertToNetworkUser(user))
                );
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
