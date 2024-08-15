package kz.edu.astanait.dashboard.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.dashboard.dto.MessageDto;
import kz.edu.astanait.dashboard.enums.ChatMemberRole;
import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.repository.ChatRepository;
import kz.edu.astanait.dashboard.service.ChatMemberService;
import kz.edu.astanait.dashboard.service.ChatService;
import kz.edu.astanait.dashboard.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatMemberService chatMemberService;

    @Override
    public void sendDirectMessage(MessageDto message) {
        var chat = getDirectChat(SecurityUtils.getCurrentId(), message.recipientId(), true);

    }

    private ChatEntity getDirectChat(Long senderId, Long recipientId, boolean createIfNotExists) {
        return chatRepository.findDirectChatBetweenUsers(senderId, recipientId)
                .orElseGet(() -> {
                    if (!createIfNotExists) {
                        throw new EntityNotFoundException("Chat not found");
                    }
                    return createAndSaveDirectChat(senderId, recipientId);
                });
    }

    private ChatEntity createAndSaveDirectChat(Long senderId, Long recipientId) {
        var chat = new ChatEntity();
        chat.setGroupChat(false);

        var savedChat = chatRepository.save(chat);

        var sender = chatMemberService.createMember(senderId, savedChat, ChatMemberRole.CREATOR);
        var recipient = chatMemberService.createMember(recipientId, savedChat, ChatMemberRole.CREATOR);

        savedChat.setMembers(List.of(sender, recipient));
        return chatRepository.save(savedChat);
    }
}
