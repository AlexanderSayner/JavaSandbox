package sayner.sandbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sayner.sandbox.modelle.User;

public interface UserDetailRepo extends JpaRepository<User, String> {
}