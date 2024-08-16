package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.dto.chat.MessageDto;
import kz.edu.astanait.dashboard.model.MessageEntity;

public interface MessageService {
    MessageEntity createMessage(MessageDto messageDto);
}
