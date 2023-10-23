package edu.cmart.service.impl;

import edu.cmart.entity.City;
import edu.cmart.model.dto.CityDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.mapper.CityMapper;
import edu.cmart.repository.CityRepository;
import edu.cmart.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static edu.cmart.util.method.Search.getPageable;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Page<CityDto> findAll(SearchCriteria searchCriteria) {
        Page<City> cities = cityRepository.findAll(getPageable(searchCriteria));
        return cities.map(cityMapper::apply);
    }

    @Override
    public Page<CityDto> findAllByName(String name, SearchCriteria searchCriteria) {
        Page<City> cities = cityRepository.findAllByNameContaining(name, getPageable(searchCriteria));
        return cities.map(cityMapper::apply);
    }

    @Override
    public CityDto findById(Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);
        return city.map(cityMapper::apply).orElse(null);
    }

    @Override
    public CityDto save(CityDto dto) {
        return cityMapper.apply(
                cityRepository.save(
                        cityMapper.applyToCity(dto)));
    }

    @Override
    public void delete(Long cityId) {
        cityRepository.deleteById(cityId);
    }
}
