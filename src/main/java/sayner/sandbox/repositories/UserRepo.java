package sayner.sandbox.repositories;

import sayner.sandbox.models.User;

import java.util.List;

public interface UserRepo extends RootRepoHibernate {
    List<User> getAllUsers() throws NullPointerException;
}
