package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, когда что-то не найдено в БД
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectAbsenceException extends RuntimeException {
    public ObjectAbsenceException(String message) {super(message);}
}
