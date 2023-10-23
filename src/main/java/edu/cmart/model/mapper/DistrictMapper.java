package edu.cmart.model.mapper;

import edu.cmart.entity.City;
import edu.cmart.entity.District;
import edu.cmart.model.dto.CityDto;
import edu.cmart.model.dto.DistrictDto;
import edu.cmart.repository.CityRepository;
import edu.cmart.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DistrictMapper implements Function<District, DistrictDto> {

    private final CityRepository cityRepository;


    @Override
    public DistrictDto apply(District district) {
        return DistrictDto
                .builder()
                .id(district.getId())
                .name(district.getName())
                .cityId(district.getCity().getId())
                .build();
    }

    public District applyToDistrict(DistrictDto districtDto){
        City city = cityRepository.findById(districtDto.getCityId()).get();
        return District
                .builder()
                .id(districtDto.getId())
                .name(districtDto.getName())
                .city(city)
                .build();
    }
}
