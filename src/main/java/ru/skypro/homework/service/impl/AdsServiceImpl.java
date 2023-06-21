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
import ru.skypro.homework.service.UserService;

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
     * Поле маппинга объявлении
     */
    private AdsMapper adsMapper;

    /**
     * Поле репозитория пользователя
     */
    private final UserRepository userRepository;

    /**
     * Поле сервиса пользователя
     */
    private final UserService userService;

    /**
     * Конструктор - создание нового объекта репозитория
     *
     * @param adsRepository  репозиторий объявления
     * @param userRepository репозиторий пользователя
     * @param userService сервис пользователя
     * @see AdsRepository(AdsRepository)
     */
    public AdsServiceImpl(AdsRepository adsRepository, UserRepository userRepository, UserService userService) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.userService = userService;
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
        logger.info("Вызван метод добавления объявления");
        AdsEntity adsEntity = adsMapper.toEntity(createAds);
        adsRepository.save(adsEntity);

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
        return adsMapper.toFullAdsDto(adsRepository.findById(adsId).orElseThrow());
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
        AdsEntity adsEntity = adsMapper.toEntity(createAds);
        adsRepository.save(adsEntity);

        return adsMapper.toAdsDto(adsEntity);
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
