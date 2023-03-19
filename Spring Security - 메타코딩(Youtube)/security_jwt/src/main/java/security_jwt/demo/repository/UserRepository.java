package security_jwt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security_jwt.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
