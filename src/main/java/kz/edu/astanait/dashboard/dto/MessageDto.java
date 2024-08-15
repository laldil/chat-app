package kz.edu.astanait.dashboard.dto;

public record MessageDto(
        String content,
        Long recipientId
) {
}