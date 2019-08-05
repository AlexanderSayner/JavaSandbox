package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sayner.sandbox.models.User;
import sayner.sandbox.repositories.UserRepo;
import sayner.sandbox.services.UserService;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<User> getAllUsers() throws NullPointerException {
        return userRepo.getAllUsers();
    }
}
