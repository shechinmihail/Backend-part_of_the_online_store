package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * UserServiceImpl
 * Сервис для обновления пароля, информации, аватара и поиска авторизованного пользователя в базе данных
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    /**
     * Поле репозитория пользователя
     */
    private final UserRepository userRepository;

    /**
     * Поле маппинга пользователя
     */
    private UserMapper userMapper;

    private final ImageServiceImpl imageService;

    /**
     * Конструктор - создание нового объекта репозитория
     *
     * @param userRepository
     * @param userMapper
     * @param imageService
     * @see UserRepository (UserRepository)
     */
//    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ImageServiceImpl imageService) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//        this.imageService = imageService;
//    }

    /**
     * Обновление пароля пользователя
     *
     * @param newPassword    новый пароль
     * @param authentication авторизованный пользователь
     * @return новый пароль для авторизованного пользователя
     */
    @Override
    public void setNewPassword(NewPassword newPassword, Authentication authentication) {
        logger.info("Вызван метод обновления пароля пользователя");
        UserEntity userEntity = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow();
        userEntity.setPassword(newPassword.getNewPassword());
        userRepository.save(userEntity);
        userMapper.toDto(userEntity);
    }

    /**
     * Получение информации об авторизованном пользователе
     *
     * @param authentication авторизованный пользователь
     * @return информацию об авторизованном пользователе
     */
    @Override
    public User getUser(Authentication authentication) {
        logger.info("Вызван метод получения информации об авторизованном пользователе");
        return userMapper.toDto(userRepository.getUserEntitiesByEmail(authentication.getName()));
    }

    /**
     * Обновить информацию об авторизованном пользователе
     *
     * @param user           пользователь
     * @param authentication авторизованный пользователь
     * @return обновленную информацию об авторизованном пользователе
     */
    @Override
    public User updateUser(User user, Authentication authentication) {
        logger.info("Вызван метод обновления информации об авторизованном пользователе");
        UserEntity userEntity = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(); // TODO надо сделать исключение
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    /**
     * Обновить аватар авторизованного пользователя
     *
     * @param image          аватар авторизованного пользователя
     * @param authentication авторизованный пользователь
     */
    @Override
    public void updateUserImage(MultipartFile image, Authentication authentication) throws IOException {
        logger.info("Вызван метод обновления аватара авторизованного пользователя");
        UserEntity userEntity = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(); // TODO сделать исключение
        ImageEntity imageEntity = imageService.downloadImage(image);
        userEntity.setImageEntity(imageEntity);
        userRepository.save(userEntity);
    }

    @Override
    public byte[] getUserImage(Integer userId) throws IOException {
        logger.info("Вызван метод получения аватара пользователя");
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(); // TODO надо сделать исключение
        if (userEntity.getImageEntity() != null){
            return userEntity.getImageEntity().getData();
        } else {
            File avatar = new File("src/main/resources/imageAvatar/*");
            return Files.readAllBytes(avatar.toPath());
        }
    }
}
