package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.dto.chat.SendMessageRequest;
import kz.edu.astanait.dashboard.dto.chat.SendMessageResponse;

public interface ChatService {
    SendMessageResponse sendDirectMessage(SendMessageRequest message);
}
