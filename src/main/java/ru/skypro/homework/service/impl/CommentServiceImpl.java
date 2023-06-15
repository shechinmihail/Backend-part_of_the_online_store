package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.exception.ObjectAbsenceException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Сервис для методов работы с комментариями
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(Comment.class);

    /**
     * Поле репозитория комментариев
     */
    private final CommentRepository commentRepository;

    /**
     * Поле маппинга комментариев
     */
     private CommentMapper commentMapper;

    /**
     * Конструктор - создание нового объекта репозитория
     * @param commentRepository репозиторий комментариев
     * @see CommentRepository (CommentRepository)
     */
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Позволяет получить все комментарии к определенному объявлению
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#getByAdsId(Integer)}
     *
     * @param adsId идентификатор объявления, не может быть null
     * @return возвращает все комментарии к определенному объявлению
     */
    @Override
    public Collection<Comment> getComments(Integer adsId) {
        logger.info("Вызван метод получения всех комментариев к определенному объявлению");
        if (commentRepository.getByAdsId(adsId) == null) {
            throw new ObjectAbsenceException("Объявление с таким Id не найдено в БД");
        }
        return CommentMapper.INSTANCE.commentToCollectionDto(commentRepository.getByAdsId(adsId));
        //TODO добавила в  маппер Collection<Comment> commentToCollectionDto(Collection<CommentEntity> CommentCollection);
    }
    /**
     * Позволяет добавить комментарий к определенному объявлению
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#save(Object)}
     *
     * @param adsId идентификатор объявления, не может быть null
     * @param createComment данные комментария
     * @param authentication авторизованный пользователь
     * @return возвращает добавленный комментарий
     */
    @Override
    public Comment addComment(@NotNull Integer adsId, CreateComment createComment, Authentication authentication) {
     // TODO здесь еще нужно добавить код который привяжет комментарий к конкретному объявлению
        logger.info("Вызван метод добавления комментария");
        if (createComment == null) {
            throw new RuntimeException("");
        }
        CommentEntity commentEntity = CommentMapper.INSTANCE.toEntity(createComment);
        commentRepository.save(commentEntity);

        return CommentMapper.INSTANCE.toDto(commentEntity);
    }
    /**
     * Позволяет удалить комментарий
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#getById(Integer)}
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#deleteById(Integer)}
     * @param commentId идентификатор комментария, не может быть null
     * @param authentication авторизованный пользователь
     */
    @Override
    public void deleteComment(Integer commentId, Authentication authentication) {
        logger.info("Вызван метод удаления комментария по идентификатору (id)");
        if (commentRepository.getById(commentId) == null) {
            throw new ObjectAbsenceException("Комментарий с таким Id не найдено в БД");
        }
        commentRepository.deleteById(commentId);
    }

    /**
     * Позволяет изменить комментарий
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#getById(Integer)}
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#save(Object)}
     * @param commentId идентификатор комментария, не может быть null
     * @param createComment измененный комментарий
     * @param authentication авторизованный пользователь
     * @return возвращает измененный комментарий
     */
    @Override
    public Comment updateComment(@NotNull Integer commentId, CreateComment createComment, Authentication authentication) {
        logger.info("Вызван метод обновления комментария по идентификатору (id)");
        if (commentRepository.getById(commentId) == null) {
            throw new ObjectAbsenceException("Комментарий с таким Id не найден в БД");
        }
        // TODO меняется только текст? Дата создания не меняется на дату изменения
        CommentEntity updateCommentEntity = CommentMapper.INSTANCE.toEntity(createComment);
        commentRepository.save(updateCommentEntity);
        return commentMapper.INSTANCE.toDto(updateCommentEntity);
    }
}
