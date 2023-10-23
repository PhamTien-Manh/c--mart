package edu.cmart.service.impl;

import edu.cmart.entity.District;
import edu.cmart.model.dto.DistrictDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.mapper.DistrictMapper;
import edu.cmart.repository.DistrictRepository;
import edu.cmart.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Paper;
import java.util.Optional;

import static edu.cmart.util.method.Search.getPageable;

@RequiredArgsConstructor
@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    @Override
    public Page<DistrictDto> findAll(SearchCriteria searchCriteria) {
        Page<District> districts = districtRepository.findAll(getPageable(searchCriteria));
        return districts.map(districtMapper::apply);
    }

    @Override
    public Page<DistrictDto> findAllByCityId(Long cityId, SearchCriteria searchCriteria) {
        Page<District> districts = districtRepository.findAllByCityId(cityId, getPageable(searchCriteria));
        return districts.map(districtMapper::apply);
    }

    @Override
    public Page<DistrictDto> findAllByName(String name, SearchCriteria searchCriteria) {
        Page<District> districts = districtRepository.findAllByNameContaining(name, getPageable(searchCriteria));
        return districts.map(districtMapper::apply);
    }

    @Override
    public DistrictDto findById(Long districtId) {
        Optional<District> district = districtRepository.findById(districtId);
        return district.map(districtMapper::apply).orElse(null);
    }

    @Override
    public DistrictDto save(DistrictDto dto) {
        return districtMapper.apply(
                districtRepository.save(
                        districtMapper.applyToDistrict(dto)));
    }

    @Override
    public void delete(Long districtId) {
        districtRepository.deleteById(districtId);
    }
}
