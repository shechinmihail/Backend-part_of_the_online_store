package ru.skypro.homework.exception;

/**
 * Исключение, когда что-то не найдено в БД
 */
public class ObjectAbsenceException extends RuntimeException {
    public ObjectAbsenceException(String message) {super(message);}

    public ObjectAbsenceException() {
        super("User is not found in database");
    }
}
