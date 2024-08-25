package kz.edu.astanait.dashboard.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.dashboard.dto.chat.CreateMessageDto;
import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.model.MessageEntity;
import kz.edu.astanait.dashboard.repository.MessageRepository;
import kz.edu.astanait.dashboard.repository.UserRepository;
import kz.edu.astanait.dashboard.service.MessageService;
import kz.edu.astanait.dashboard.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public MessageEntity createMessage(CreateMessageDto messageDto) {
        var sender = userRepository.findById(SecurityUtils.getCurrentId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        var message = new MessageEntity();
        message.setChat(messageDto.chat());
        message.setContent(messageDto.content());
        message.setSender(sender);

        return messageRepository.save(message);
    }

    @Override
    public List<MessageEntity> getLastMessages(ChatEntity chat, int page, int size) {
        var pageable = PageRequest.of(0, 150, Sort.by(Sort.Direction.DESC, "createdDate"));
        var lastMessages = messageRepository.findLastMessages(chat, pageable);
        return lastMessages.getContent();
    }
}
