package kz.edu.astanait.dashboard.dto.chat;

import jakarta.validation.constraints.NotNull;

public record SendMessageRequest(
        @NotNull(message = "Content must be provided")
        String content,
        @NotNull(message = "Recipient must be provided")
        Long recipientId
) {
}
