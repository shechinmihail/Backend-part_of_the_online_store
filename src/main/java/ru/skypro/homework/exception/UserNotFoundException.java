package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение UserNotFoundException
 * Выпадает, когда в базе данных не найден пользователь
 * Исключение унаследовано от {@link RuntimeException}.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Пользователь не авторизован");
    }
}
