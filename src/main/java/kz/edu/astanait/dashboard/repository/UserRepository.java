package kz.edu.astanait.dashboard.repository;

import kz.edu.astanait.dashboard.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author aldi
 * @since 13.08.2024
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
