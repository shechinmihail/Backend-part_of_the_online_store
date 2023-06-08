package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.exception.ObjectAbsenceException;
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

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Позволяет получить все комментарии к определенному объявлению
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#getByAdsId(Integer)}
     * @param adsId (не может быть null)
     * @return возвращает все комментарии к определенному объявлению
     */
    @Override
    public Collection<CommentEntity> getComments(Integer adsId) {
        if (commentRepository.getByAdsId(adsId) == null) {
            throw new ObjectAbsenceException("Объявление с таким Id не найдено в БД");
        }
        return commentRepository.getByAdsId(adsId);
    }
    /**
     * Позволяет добавить комментарий к определенному объявлению
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#save(Object)}
     * @param adsId (не может быть null)
     * @param newCommentEntity
     * @return возвращает добавленный комментарий
     */
    @Override
    public CommentEntity addComment(@NotNull Integer adsId, CommentEntity newCommentEntity) {
     //здесь еще нужно добавить код который привяжет комментарий к конкретному объявлению

        return commentRepository.save(newCommentEntity);
    }
    /**
     * Позволяет удалить комментарий
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#getById(Integer)}
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#deleteById(Integer)}
     * @param commentId (не может быть null)
     */
    @Override
    public void deleteComment(Integer commentId) {
        if (commentRepository.getById(commentId) == null) {
            throw new ObjectAbsenceException("Комментарий с таким Id не найдено в БД");
        }
        commentRepository.deleteById(commentId);
    }

    /**
     * Позволяет изменить комментарий
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#getById(Integer)}
     * <br> Использован метод репозитория {@link ru.skypro.homework.repository.CommentRepository#save(Object)}
     * @param commentId (не может быть null)
     * @param newText
     * @return возвращает измененный комментарий
     */
    @Override
    public CommentEntity updateComment(@NotNull Integer commentId, String newText) {
        if (commentRepository.getById(commentId) == null) {
            throw new ObjectAbsenceException("Комментарий с таким Id не найден в БД");
        }
        CommentEntity updateCommentEntity = commentRepository.getById(commentId);
        updateCommentEntity.setText(newText);
        // меняется только текст? Дата создания не меняется на дату изменения

        return commentRepository.save(updateCommentEntity);
    }
}
