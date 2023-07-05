package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.dto.ResponseWrapperComment;


public interface CommentService {
    ResponseWrapperComment getComments(Integer adId);

    Comment addComment(Integer adId, CreateComment createComment, Authentication authentication);

    void deleteComment(Integer adId, Integer commentId);

    void deleteAllByAdsId(Integer adId);

    Comment updateComment(Integer adId, Integer commentId, Comment comment);
}
