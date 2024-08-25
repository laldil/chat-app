package kz.edu.astanait.dashboard.repository;

import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.model.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query("SELECT m FROM MessageEntity m WHERE m.chat = :chat ORDER BY m.createdDate DESC")
    Page<MessageEntity> findLastMessages(@Param("chat") ChatEntity chat, Pageable pageable);
}
