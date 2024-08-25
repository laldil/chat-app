package kz.edu.astanait.dashboard.dto.chat;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private Long senderId;
    private String content;
    private Date createdDate;
    private Date modifiedDate;
}
