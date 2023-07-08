package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;

import java.util.Collection;

public interface AdsService {
    Collection<Ads> getAllAds(String title);
    Ads createAds(CreateAds createAds, MultipartFile image);
    FullAds getAds(Integer adId);
    void deleteAds(Integer adId);
    Ads updateAds(CreateAds createAds, Integer adId);
    Collection<Ads> getAdsMe();
    String updateImage(Integer adId, MultipartFile image);
    byte[] getAdsImage(Integer adId);
}
