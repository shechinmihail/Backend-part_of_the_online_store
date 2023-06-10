package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAds {
    private int count;
    private List<Ads> results;

    public ResponseWrapperAds(List<Ads> results) {
        this.count = results.size();
        this.results = results;
    }
}
