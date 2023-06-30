package ru.skypro.homework.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResponseWrapperAds<T> {
    private int count;
    private List<Ads> results;
}
