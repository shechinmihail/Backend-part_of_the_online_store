package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение ObjectAbsenceException
 * Исключение, когда нужно обработать иные исключения
 * Исключение унаследовано от {@link RuntimeException}.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectException extends RuntimeException {
    public ObjectException(String message)
    {super(message);}
}
