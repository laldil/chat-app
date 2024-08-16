package kz.edu.astanait.dashboard.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.dashboard.dto.chat.MessageDto;
import kz.edu.astanait.dashboard.model.MessageEntity;
import kz.edu.astanait.dashboard.model.UserEntity;
import kz.edu.astanait.dashboard.repository.MessageRepository;
import kz.edu.astanait.dashboard.repository.UserRepository;
import kz.edu.astanait.dashboard.service.MessageService;
import kz.edu.astanait.dashboard.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public MessageEntity createMessage(MessageDto messageDto) {
        var sender = userRepository.findById(SecurityUtils.getCurrentId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        var message = new MessageEntity();
        message.setChat(messageDto.chat());
        message.setContent(messageDto.content());
        message.setSender(sender);

        return messageRepository.save(message);
    }
}
