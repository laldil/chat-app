package kz.edu.astanait.dashboard.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.dashboard.dto.chat.MessageDto;
import kz.edu.astanait.dashboard.dto.chat.SendMessageRequest;
import kz.edu.astanait.dashboard.dto.chat.SendMessageResponse;
import kz.edu.astanait.dashboard.enums.ChatMemberRole;
import kz.edu.astanait.dashboard.mapper.MessageMapper;
import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.repository.ChatRepository;
import kz.edu.astanait.dashboard.service.ChatMemberService;
import kz.edu.astanait.dashboard.service.ChatService;
import kz.edu.astanait.dashboard.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final ChatMemberService chatMemberService;
    private final MessageServiceImpl messageService;

    @Override
    public SendMessageResponse sendDirectMessage(SendMessageRequest request) {
        var chat = getDirectChat(SecurityUtils.getCurrentId(), request.recipientId(), true);
        var message = messageService.createMessage(new MessageDto(request.content(), request.recipientId(), chat));

        var response = MessageMapper.MAPPER.mapToResponse(message);
        response.setChatId(chat.getId());
        return response;
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

        chatMemberService.createMember(senderId, savedChat, ChatMemberRole.CREATOR);
        chatMemberService.createMember(recipientId, savedChat, ChatMemberRole.CREATOR);

        return savedChat;
    }
}
