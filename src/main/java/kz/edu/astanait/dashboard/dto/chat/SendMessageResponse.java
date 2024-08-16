package kz.edu.astanait.dashboard.dto.chat;

import lombok.Data;

@Data
public class SendMessageResponse {
    private Long chatId;
    private String content;
}
