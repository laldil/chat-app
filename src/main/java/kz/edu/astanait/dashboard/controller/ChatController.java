package kz.edu.astanait.dashboard.controller;

import jakarta.validation.Valid;
import kz.edu.astanait.dashboard.dto.chat.SendMessageRequest;
import kz.edu.astanait.dashboard.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendDirectMessage")
    public void sendDirectMessage(@Valid @RequestBody SendMessageRequest request) {
        var message = chatService.sendDirectMessage(request);

        var destination = "/queue/chat/%s".formatted(message.getChatId());
        messagingTemplate.convertAndSend(destination, message);
    }
}
