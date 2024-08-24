package kz.edu.astanait.dashboard.controller;

import kz.edu.astanait.dashboard.dto.chat.SendMessageRequest;
import kz.edu.astanait.dashboard.dto.chat.SendMessageResponse;
import kz.edu.astanait.dashboard.service.ChatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTest {
    @InjectMocks
    private ChatController chatController;

    @Mock
    private ChatService chatService;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Test
    public void sendDirectMessage_validRequest_shouldSendMessageToQueue() {
        // ARRANGE
        var request = new SendMessageRequest("test", 1L);

        var expectedMessage = new SendMessageResponse();
        expectedMessage.setChatId(1L);
        expectedMessage.setContent("test");

        Mockito.when(chatService.sendDirectMessage(Mockito.any(SendMessageRequest.class)))
                .thenReturn(expectedMessage);

        // ACT
        chatController.sendDirectMessage(request);

        // ASSERT
        var destinationCaptor = ArgumentCaptor.forClass(String.class);
        var messageCaptor = ArgumentCaptor.forClass(SendMessageResponse.class);
        Mockito.verify(simpMessagingTemplate).convertAndSend(destinationCaptor.capture(), messageCaptor.capture());

        Assertions.assertEquals("/queue/chat/1", destinationCaptor.getValue());
        Assertions.assertEquals(expectedMessage, messageCaptor.getValue());
    }
}