package sayner.sandbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sayner.sandbox.models.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
