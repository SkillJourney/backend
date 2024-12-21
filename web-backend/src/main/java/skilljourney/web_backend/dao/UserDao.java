package skilljourney.web_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import skilljourney.web_backend.models.User;

public interface UserDao extends JpaRepository<User, Long> {
}
