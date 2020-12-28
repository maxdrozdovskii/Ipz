package railways.database.service;

import railways.database.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User findUserByUsername(String username);

    User save(User user);

    void delete(User user);
}
