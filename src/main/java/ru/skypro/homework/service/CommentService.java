package ru.skypro.homework.service;

import ru.skypro.homework.entity.CommentEntity;

import java.util.Collection;

public interface CommentService {
    Collection<CommentEntity> getComments(Integer adsId);
    CommentEntity addComment(Integer adsId, CommentEntity newCommentEntity);

    void deleteComment(Integer commentId);

    CommentEntity updateComment(Integer commentId, String newText);
}
