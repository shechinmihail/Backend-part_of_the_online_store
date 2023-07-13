package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение AdsNotFoundException
 * Выпадает, когда в базе данных не найден объявление
 * Исключение унаследовано от {@link RuntimeException}.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdsNotFoundException extends RuntimeException{
    public AdsNotFoundException(){
        super("Объявление не найдено");
    }
}
