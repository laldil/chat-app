package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.dto.MessageDto;

public interface ChatService {
    void sendDirectMessage(MessageDto message);
}
