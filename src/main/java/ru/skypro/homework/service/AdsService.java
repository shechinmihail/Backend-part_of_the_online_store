package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;

import java.util.Collection;

public interface AdsService {
    Collection<Ads> getAllAds(String title);
    Ads createAds(CreateAds createAds, MultipartFile image, Authentication authentication);
    FullAds getAds(Integer adsId);
    void deleteAds(Integer adsId, Authentication authentication);
    Ads updateAds(CreateAds createAds, Integer adsId, Authentication authentication);
    Collection<Ads> getAdsMe(Integer userId, Authentication authentication);
    String updateImage(Integer adsId,MultipartFile image, Authentication authentication);
}
