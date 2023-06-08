package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdsEntity;

/**
 * Интерфейс AdsRepository
 * для работы с БД (для объявлений)
 */
@Repository
public interface AdsRepository extends JpaRepository<AdsEntity, Integer> {

}
