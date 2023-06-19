package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.util.Collection;

/**
 * Сервис AdsServiceImpl
 * Сервис для добавления, удаления, редактирования и поиска объявлений в базе данных
 */
@Service
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
     * Конструктор - создание нового объекта репозитория
     *
     * @param adsRepository  репозиторий объявления
     * @param userRepository репозиторий пользователя
     * @see AdsRepository(AdsRepository)
     */
    public AdsServiceImpl(AdsRepository adsRepository, UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Получение списка всех объявлений из базы данных
     *
     * @return список(коллекцию) объявлений
     */
    @Override
    public Collection<Ads> getAllAds(String title) {
        logger.info("Вызван метод получения всех объявлений");
        if (title == null) {
            return AdsMapper.INSTANCE.adsToCollectionDto(adsRepository.findAll());
        }
        return AdsMapper.INSTANCE.adsToCollectionDto(adsRepository.findByTitleLikeIgnoreCase(title));
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
        logger.info("Вызван метод добавления объявления");
        if (createAds == null) {
            throw new RuntimeException("");
        }
        AdsEntity adsEntity = AdsMapper.INSTANCE.toEntity(createAds);
        adsRepository.save(adsEntity);

        return AdsMapper.INSTANCE.toDto(adsEntity);
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
        return AdsMapper.INSTANCE.adToFullAdsDto(adsRepository.findById(adsId).orElseThrow());
    }

    /**
     * Удаление объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId          идентификатор объявления, не может быть null
     * @param authentication авторизованный пользователь
     */
    @Override
    public void deleteAds(Integer adsId, Authentication authentication) {
        logger.info("Вызван метод удаления объявления по идентификатору (id)");
        adsRepository.deleteById(adsId);
    }

    /**
     * Обновление объявления по идентификатору (id), хранящихся в базе данных
     *
     * @param adsId          идентификатор объявления, не может быть null
     * @param createAds      данные объявления
     * @param authentication авторизованный пользователь
     * @return возвращает обновленное объявление по идентификатору (id)
     */
    @Override
    public Ads updateAds(CreateAds createAds, Integer adsId, Authentication authentication) {
        logger.info("Вызван метод обновления объявления по идентификатору (id)");
        if (adsId == null || !adsRepository.findById(adsId).isPresent()) {
            return null;
        }
        AdsEntity adsEntity = AdsMapper.INSTANCE.toEntity(createAds);
        adsRepository.save(adsEntity);

        return AdsMapper.INSTANCE.toDto(adsEntity);
    }

    /**
     * Получение объявлений авторизованного пользователя, хранящихся в базе данных
     *
     * @param authorId       идентификатор пользователя, не может быть null
     * @param authentication авторизованный пользователь
     * @return возвращает все объявления авторизованного пользователя
     */
    @Override
    public Collection<Ads> getAdsMe(Integer authorId, Authentication authentication) {
        logger.info("Вызван метод получения объявлений авторизованного пользователя");
        Collection<AdsEntity> AdsEntity = adsRepository.findByAuthorId(authorId);
        return AdsMapper.INSTANCE.adsToCollectionDto(AdsEntity);
    }

    /**
     * Обновление картинки объявления
     *
     * @param adsId          идентификатор объявления, не может быть null
     * @param image          картинка объявления
     * @param authentication авторизованный пользователь
     * @return объявление с новой картинкой
     */
    @Override
    public String updateImage(Integer adsId, MultipartFile image, Authentication authentication) {
        logger.info("Вызван метод обновления картинки объявления");
        return null;
    }
}
