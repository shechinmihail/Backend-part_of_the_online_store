package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UserSecurity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "image", expression = "java(imageMap(userEntity))")
    User toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(ru.skypro.homework.dto.Role.USER)")
//    @Mapping(target = "role", defaultValue = "USER")
    //@Mapping(target = "image", source = "image")
    UserEntity toEntity(User userDto);

    ImageEntity map(String value);

    @Mapping(target = "email", source = "username")
    UserEntity toEntityFromReq(RegisterReq registerReqDto);

    default String imageMap(UserEntity userEntity) {
        return "/users/"+ userEntity.getId() + "/image";
    }

    UserSecurity toSecurityDTO(UserEntity userEntity);
}
