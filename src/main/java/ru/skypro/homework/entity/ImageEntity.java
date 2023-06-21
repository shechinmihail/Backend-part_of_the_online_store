package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Класс ImageEntity, представляет сущность изображении
 */
@Entity
@Data
@Table(name = "image")
public class ImageEntity {

    /**
     * Идентификационный номер (id) изображения
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * Тип изображения
     */
    private String mediaType;

    /**
     * Данные изображения
     */
    private byte[] data;
}
