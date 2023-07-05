package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapperAds<T> {
    private int count;
    private final Collection<T> results;

    public ResponseWrapperAds(Collection<T> results) {
        this.count = results.size();
        this.results = results;
    }
}
