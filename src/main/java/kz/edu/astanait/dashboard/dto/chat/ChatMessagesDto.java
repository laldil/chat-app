package kz.edu.astanait.dashboard.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessagesDto {
    private Long chatId;
    private String name;
    private List<MessageDto> messages;
}
