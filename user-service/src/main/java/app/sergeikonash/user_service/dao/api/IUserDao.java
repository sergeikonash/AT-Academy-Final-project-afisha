package app.sergeikonash.user_service.dao.api;

import app.sergeikonash.user_service.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserDao extends JpaRepository<User, UUID> {
}
