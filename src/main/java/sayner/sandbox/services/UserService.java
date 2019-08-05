package sayner.sandbox.services;

import sayner.sandbox.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws NullPointerException;
}
