package edu.cmart.service.impl;

import edu.cmart.entity.Ward;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.dto.WardDto;
import edu.cmart.model.mapper.WardMapper;
import edu.cmart.repository.WardRepository;
import edu.cmart.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static edu.cmart.util.method.Search.getPageable;

@RequiredArgsConstructor
@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;
    private final WardMapper wardMapper;

    @Override
    public Page<WardDto> findAll(SearchCriteria searchCriteria) {
        Page<Ward> wards = wardRepository.findAll(getPageable(searchCriteria));
        return wards.map(wardMapper::apply);
    }

    @Override
    public Page<WardDto> findAllByDistrictId(Long districtId, SearchCriteria searchCriteria) {
        Page<Ward> wards = wardRepository.findAllByDistrictId(districtId, getPageable(searchCriteria));
        return wards.map(wardMapper::apply);
    }

    @Override
    public Page<WardDto> findAllByName(String name, SearchCriteria searchCriteria) {
        Page<Ward> wards = wardRepository.findAllByNameContaining(name, getPageable(searchCriteria));
        return wards.map(wardMapper::apply);
    }

    @Override
    public WardDto findById(Long wardId) {
        Optional<Ward> ward = wardRepository.findById(wardId);
        return ward.map(wardMapper::apply).orElse(null);
    }

    @Override
    public WardDto save(WardDto dto) {
        return wardMapper.apply(
                wardRepository.save(
                        wardMapper.applyToWard(dto)));
    }

    @Override
    public void delete(Long districtId) {
        wardRepository.deleteById(districtId);
    }
}
