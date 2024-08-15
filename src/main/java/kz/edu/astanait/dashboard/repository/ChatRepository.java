package kz.edu.astanait.dashboard.repository;

import kz.edu.astanait.dashboard.model.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @Query(value = """
            select c.* from chats c
            join chat_members cm1 on c.id = cm1.chat_id
            join chat_members cm2 on c.id = cm2.chat_id
            where cm1.user_id = :userId1
            and cm2.user_id = :userId2
            and c.is_group_chat = false
            """, nativeQuery = true)
    Optional<ChatEntity> findDirectChatBetweenUsers(Long userId1, Long userId2);

}
