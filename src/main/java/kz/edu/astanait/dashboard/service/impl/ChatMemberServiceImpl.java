package kz.edu.astanait.dashboard.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.dashboard.enums.ChatMemberRole;
import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.model.ChatMemberEntity;
import kz.edu.astanait.dashboard.repository.ChatMemberRepository;
import kz.edu.astanait.dashboard.repository.UserRepository;
import kz.edu.astanait.dashboard.service.ChatMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatMemberServiceImpl implements ChatMemberService {

    private final ChatMemberRepository chatMemberRepository;
    private final UserRepository userRepository;

    @Override
    public ChatMemberEntity createMember(Long userId, ChatEntity chat, ChatMemberRole role) {
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        var chatMember = ChatMemberEntity.builder()
                .user(user)
                .chat(chat)
                .role(role)
                .build();
        return chatMemberRepository.save(chatMember);
    }
}
