package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, когда доступ запрещён
 */

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessException extends RuntimeException {

    public AccessException(String message) {
        super(message);
    }
}
