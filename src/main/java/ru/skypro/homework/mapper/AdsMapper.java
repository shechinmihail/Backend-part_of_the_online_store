package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdsEntity;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);


    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "image", defaultValue = "null")
    Ads toAdsDto(AdsEntity adsEntity);

    @Mapping(source = "id",target = "pk")
    @Mapping(source = "author.firstName",target = "authorFirstName")
    @Mapping(source = "author.lastName",target = "authorLastName")
    @Mapping(source = "author.email",target = "email")
    @Mapping(source = "author.phone",target = "phone")
    @Mapping(target = "image", defaultValue = "null")
    FullAds toFullAdsDto(AdsEntity adsEntity);

    AdsEntity createAdsDtoToAdsEntity(CreateAds createAdsDTO);

    Collection<Ads> adsEntityToCollectionDto(Collection<AdsEntity> adsCollection);

    AdsEntity toEntity(CreateAds createAdsDto);

}
