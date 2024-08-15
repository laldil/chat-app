package kz.edu.astanait.dashboard.mapper;

import kz.edu.astanait.dashboard.dto.user.UserDto;
import kz.edu.astanait.dashboard.dto.auth.RegistrationRequest;
import kz.edu.astanait.dashboard.model.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity mapToEntity(RegistrationRequest request);

    UserDto mapToDto(UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserDto dto, @MappingTarget UserEntity entity);
}
