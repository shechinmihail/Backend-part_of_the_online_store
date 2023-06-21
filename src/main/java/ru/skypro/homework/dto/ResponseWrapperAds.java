package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class ResponseWrapperAds<T> {
    private int count;
    private Collection<T> results;

    public ResponseWrapperAds(Collection<T> results) {
        this.count = results.size();
        this.results = results;
    }
}
