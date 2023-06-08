package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.UserEntity;

/**
 * Интерфейс UserRepository
 * для работы с БД (для пользователя)
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
