package edu.cmart.model.mapper;

import edu.cmart.entity.City;
import edu.cmart.model.dto.CityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CityMapper implements Function<City, CityDto> {


    @Override
    public CityDto apply(City city) {
        return CityDto
                .builder()
                .id(city.getId())
                .name(city.getName())
                .description(city.getDescription())
                .build();
    }

    public City applyToCity(CityDto cityDto){
        return City
                .builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .description(cityDto.getDescription())
                .build();
    }
}
