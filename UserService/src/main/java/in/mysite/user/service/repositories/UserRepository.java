package in.mysite.user.service.repositories;

import in.mysite.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
