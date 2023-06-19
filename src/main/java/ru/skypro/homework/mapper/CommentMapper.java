package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "pk")
    Comment toDto(CommentEntity commentEntity);

    @Mapping(source = "pk", target = "id")
    CommentEntity toEntity(Comment commentDto);

    CommentEntity toEntity(CreateComment createComment);

    List<Comment> commentToCollectionDto(Collection<CommentEntity> commentCollection);
}
