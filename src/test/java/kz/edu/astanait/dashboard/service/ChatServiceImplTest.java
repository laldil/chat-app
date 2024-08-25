package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.dto.chat.CreateMessageDto;
import kz.edu.astanait.dashboard.dto.chat.SendMessageRequest;
import kz.edu.astanait.dashboard.dto.chat.SendMessageResponse;
import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.model.MessageEntity;
import kz.edu.astanait.dashboard.repository.ChatRepository;
import kz.edu.astanait.dashboard.service.impl.ChatServiceImpl;
import kz.edu.astanait.dashboard.service.impl.MessageServiceImpl;
import kz.edu.astanait.dashboard.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatServiceImplTest {
    @Mock
    private ChatRepository chatRepository;

    @Mock
    private MessageServiceImpl messageService;

    @InjectMocks
    private ChatServiceImpl chatService;

    @Test
    void sendDirectMessage_validRequest_returnsSendMessageResponse() {
        // ARRANGE
        var senderId = 1L;
        var recipientId = 2L;
        var chatId = 3L;
        var content = "Hello!";

        var request = new SendMessageRequest(content, recipientId);
        var chat = getChatEntity(chatId);
        var message = getMessageEntity(chat, content);
        var expectedResponse = new SendMessageResponse(chatId, content);

        try (var mockedSecurityUtils = mockStatic(SecurityUtils.class)) {
            mockedSecurityUtils.when(SecurityUtils::getCurrentId).thenReturn(senderId);

            when(chatRepository.findDirectChatBetweenUsers(senderId, recipientId)).thenReturn(Optional.of(chat));
            when(messageService.createMessage(any(CreateMessageDto.class))).thenReturn(message);

            // ACT
            var result = chatService.sendDirectMessage(request);

            // ASSERT
            assertNotNull(result);
            assertEquals(expectedResponse, result);
            assertEquals(chatId, result.getChatId());
        }
        // VERIFY
        verify(chatRepository).findDirectChatBetweenUsers(senderId, recipientId);
        verify(messageService).createMessage(any(CreateMessageDto.class));
    }

    private ChatEntity getChatEntity(Long chatId) {
        var chat = new ChatEntity();
        chat.setId(chatId);
        return chat;
    }

    private MessageEntity getMessageEntity(ChatEntity chat, String content) {
        var message = new MessageEntity();
        message.setContent(content);
        message.setChat(chat);
        return message;
    }
}
