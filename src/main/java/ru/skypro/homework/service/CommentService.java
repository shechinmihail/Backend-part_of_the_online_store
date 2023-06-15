package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.util.Collection;

public interface CommentService {
    Collection<Comment> getComments(Integer adsId);
    Comment addComment(Integer adsId, CreateComment createComment, Authentication authentication);

    void deleteComment(Integer commentId, Authentication authentication);

    Comment updateComment(Integer commentId, CreateComment createComment, Authentication authentication);
}
