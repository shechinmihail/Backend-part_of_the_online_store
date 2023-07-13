package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.ObjectException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetails;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Collection;

/**
 * Сервис AdsServiceImpl
 * Сервис для добавления, удаления, редактирования и поиска объявлений в базе данных
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdsServiceImpl implements AdsService {

    private static final Logger logger = LoggerFactory.getLogger(Ads.class);

    /**
     * Поле репозитория объявлении
     */
    private final AdsRepository adsRepository;

    /**
     * Поле репозитория пользователя
     */
    private final UserRepository userRepository;

    /**
     * Поле репозитория комментариев
     */
    private final CommentRepository commentRepository;

    /**
     * Поле маппинга объявлении
     */
    private final AdsMapper adsMapper;

    /**
     * Поле сервиса пользователя
     */
    private final UserService userService;

    /**
     * Поле сервиса картинки
     */
    private final ImageService imageService;

    /**
     * Поле проверки авторизации
     */
    private final MyUserDetails userDetails;

    /**
     * Получение списка всех объявлений из базы данных
     *
     * @return список(коллекцию) объявлений
     */
    @Override
    public Collection<Ads> getAllAds(String title) {
        logger.info("Вызван метод получения всех объявлений");
        if (title == null) {

            return adsMapper.adsEntityToCollectionDto(adsRepository.findAll());
        }
        return adsMapper.adsEntityToCollectionDto(adsRepository.findByTitleLikeIgnoreCase(title));
    }

    /**
     * Добавление нового объявления и сохранение его в базе данных
     *
     * @param createAds      данные объявления
     * @param image          картинка объявления
     * @param authentication авторизованный пользователь
     * @return добавленное новое объявление
     */
    @Override
    public Ads createAds(CreateAds createAds, MultipartFile image, Authentication authentication) {
        if (createAds.getPrice() < 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0!");
        }
        logger.info("Вызван метод добавления объявления");

        AdsEntity adsEntity = adsMapper.toEntity(createAds);
        UserEntity author = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        adsEntity.setAuthor(author);

        ImageEntity adImage;
        try {
            adImage = imageService.downloadImage(image);
        } catch (IOException e) {
            throw new ObjectException("Ошибка при загрузке фото");
        }

        adsEntity.setImageEntity(adImage);
        adsRepository.save(adsEntity);
        logger.info("Сохранено новое объявление");

        return adsMapper.toAdsDto(adsEntity);
    }

    /**
     * Получение объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId идентификатор объявления, не может быть null
     * @return возвращает объявление по идентификатору (id)
     */
    @Override
    public FullAds getAds(Integer adsId) {
        logger.info("Вызван метод получения объявления по идентификатору (id)");
        return adsMapper.toFullAdsDto(adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new));
    }

    /**
     * Удаление объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId идентификатор объявления, не может быть null
     */
    @Override
    public void deleteAds(Integer adsId) {
        logger.info("Вызван метод удаления объявления по идентификатору (id)");
        AdsEntity deleteAd = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        if (isOwner(deleteAd, userDetails)) {
            commentRepository.deleteCommentEntitiesByAd_Id(adsId);
            imageService.deleteImage(deleteAd.getImageEntity().getId());
            adsRepository.deleteById(adsId);
        } else {
            throw new ObjectException("Вы не можете удалять чужие объявления");
        }
    }

    /**
     * Обновление объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId     идентификатор объявления, не может быть null
     * @param createAds данные объявления
     * @return возвращает обновленное объявление по идентификатору (id)
     */
    @Override
    public Ads updateAds(CreateAds createAds, Integer adsId) {

        if (createAds.getPrice() < 0) {
            throw new ObjectException("Цена должна быть больше 0!");
        }

        AdsEntity updateAd = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        if (isOwner(updateAd, userDetails)) {
            updateAd.setTitle(createAds.getTitle());
            updateAd.setPrice(createAds.getPrice());
            updateAd.setDescription(createAds.getDescription());

            adsRepository.save(updateAd);

            return adsMapper.toAdsDto(updateAd);
        }
        throw new ObjectException("Вы не можете изменять чужие объявления");
    }

    /**
     * Получение объявлений авторизованного пользователя, хранящихся в базе данных
     *
     * @param authentication авторизованный пользователь
     * @return возвращает все объявления авторизованного пользователя
     */
    @Override
    public Collection<Ads> getAdsMe(Authentication authentication) {
        logger.info("Вызван метод получения объявлений авторизованного пользователя");
        Collection<AdsEntity> AdsEntity = adsRepository.findByAuthorId(userService.getUser(authentication).getId());
        return adsMapper.adsEntityToCollectionDto(AdsEntity);
    }

    /**
     * Обновление картинки объявления
     *
     * @param adsId идентификатор объявления, не может быть null
     * @param image картинка объявления
     * @return объявление с новой картинкой
     */
    @Override
    public String updateImage(Integer adsId, MultipartFile image) throws IOException {
        logger.info("Вызван метод обновления картинки объявления");
        AdsEntity updateAd = adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new);
        if (isOwner(updateAd, userDetails)) {
            Integer idDeleteImage = updateAd.getImageEntity().getId();
            updateAd.setImageEntity(imageService.downloadImage(image));
            imageService.deleteImage(idDeleteImage);
            adsRepository.save(updateAd);
            return adsMapper.toAdsDto(updateAd).getImage();
        }

        throw new ObjectException("Вы не можете изменять чужие объявления");
    }

    /**
     * Получение картинки объявления
     *
     * @param adsId идентификатор объявления, не может быть null
     * @return картинку объявления
     */
    @Override
    public byte[] getAdImage(Integer adsId) {
        logger.info("Вызван метод получения картинки объявления по идентификатору (id) " + adsId);
        return imageService.getImage(adsRepository.findById(adsId).orElseThrow(AdsNotFoundException::new).getImageEntity().getId());
    }

    /**
     * Проверка владельца объявления
     *
     * @param ad      объявление пользователя
     * @param details авторизация пользователя
     * @return true если пользователь действительно собственник объявления, false если нет
     */
    private boolean isOwner(AdsEntity ad, MyUserDetails details) {
        if (ad.getAuthor().getEmail().equals(details.getUserSecurity().getEmail()) || details.getUserSecurity().getRole() == Role.ADMIN) {
            return true;
        } else {
            return false;
        }
    }
}
