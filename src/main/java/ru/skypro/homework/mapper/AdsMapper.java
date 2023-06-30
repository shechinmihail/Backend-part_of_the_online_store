package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdsEntity;

import java.util.Collection;
@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper {

    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", expression = "java(imageMapper(adsEntity))")
    Ads toAdsDto(AdsEntity adsEntity);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(target = "image", expression = "java(imageMapper(adsEntity))")
    FullAds toFullAdsDto(AdsEntity adsEntity);

    void createAdsDtoToAdsEntity(CreateAds createAdsDTO, @MappingTarget AdsEntity adsEntity);

    Collection<Ads> adsEntityToCollectionDto(Collection<AdsEntity> adsCollection);

    AdsEntity toEntity(CreateAds createAdsDto);

    default String imageMapper(AdsEntity adsEntity){
        return "/ads/" + adsEntity.getId() + "/image";
    }
}
