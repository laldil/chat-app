package kz.edu.astanait.dashboard.dto.chat;

import kz.edu.astanait.dashboard.model.ChatEntity;

public record CreateMessageDto(
        String content,
        Long recipientId,
        ChatEntity chat
) {
}