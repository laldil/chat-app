package kz.edu.astanait.dashboard.service;

import kz.edu.astanait.dashboard.enums.ChatMemberRole;
import kz.edu.astanait.dashboard.model.ChatEntity;
import kz.edu.astanait.dashboard.model.ChatMemberEntity;

public interface ChatMemberService {
    ChatMemberEntity createMember(Long userId, ChatEntity chat, ChatMemberRole role);
}
