package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDto(UserEntity userEntity);

    @Mapping(target = "role", defaultValue = "USER")
    UserEntity toEntity(User userDto);

    @Mapping(target = "email", source = "username")
    UserEntity toEntityFromReq(RegisterReq registerReqDto);
}
