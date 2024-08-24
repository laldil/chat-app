package kz.edu.astanait.dashboard.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SendMessageResponse {
    private Long chatId;
    private String content;
}
