package kz.edu.astanait.dashboard.repository;

import kz.edu.astanait.dashboard.model.ChatMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<ChatMemberEntity, Long> {
}
