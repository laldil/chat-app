package kz.edu.astanait.dashboard.mapper;


import kz.edu.astanait.dashboard.dto.chat.SendMessageResponse;
import kz.edu.astanait.dashboard.model.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper MAPPER = Mappers.getMapper(MessageMapper.class);

    SendMessageResponse mapToResponse(MessageEntity entity);
}
