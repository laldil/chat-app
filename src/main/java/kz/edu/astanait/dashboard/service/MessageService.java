package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.dto.chat.CreateMessageDto;
import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.model.MessageEntity;

import java.util.List;

public interface MessageService {
    MessageEntity createMessage(CreateMessageDto messageDto);

    List<MessageEntity> getLastMessages(ChatEntity chat, int page, int size);
}
