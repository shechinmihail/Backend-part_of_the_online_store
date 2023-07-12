package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;
@Service
public interface UserService {

    void setNewPassword(NewPassword newPassword, Authentication authentication);

    User getUser(Authentication authentication);

    User updateUser(User user, Authentication authentication);

    void updateUserImage(MultipartFile image, Authentication authentication) throws IOException;

    UserEntity getNameUser(String email);

    byte[] getUserImage(Integer userId) throws IOException;
}
