package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdsEntity;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс AdsRepository
 * для работы с БД (для объявлений)
 */
@Repository
public interface AdsRepository extends JpaRepository<AdsEntity, Integer> {
    @Query(value = "SELECT * FROM ads WHERE title LIKE %title%", nativeQuery = true)
    List<AdsEntity> findByTitleLikeIgnoreCase(@Param("title") String title);

    Collection<AdsEntity> findByAuthorId(Integer authorId);
}
