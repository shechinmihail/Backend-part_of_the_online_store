package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "pk")
    Comment toDto(CommentEntity commentEntity);

    @Mapping(target = "pk", source = "id")
    CommentEntity toEntity(Comment commentDto);

    CommentEntity toEntity(CreateComment createComment);
}
