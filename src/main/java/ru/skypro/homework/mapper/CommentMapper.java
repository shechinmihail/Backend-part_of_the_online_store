package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Component
public interface CommentMapper {

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "timeMapping")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "authorImage", defaultValue = "null")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    Comment toDto(CommentEntity commentEntity);

    CommentEntity toEntity(CreateComment createComment);

    List<Comment> commentsEntityToCommentsDtoCollection(Collection<CommentEntity> commentEntityCollection);

    @Named("timeMapping")
    default Long time(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0L;
        }
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
