package edu.cmart.model.mapper;

import edu.cmart.entity.District;
import edu.cmart.entity.Ward;
import edu.cmart.model.dto.WardDto;
import edu.cmart.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class WardMapper implements Function<Ward, WardDto> {

    private final DistrictRepository districtRepository;


    @Override
    public WardDto apply(Ward ward) {
        return WardDto
                .builder()
                .id(ward.getId())
                .name(ward.getName())
                .districtId(ward.getDistrict().getId())
                .build();
    }

    public Ward applyToWard(WardDto wardDto){
        District district = districtRepository.findById(wardDto.getDistrictId()).get();
        return Ward
                .builder()
                .id(wardDto.getId())
                .name(wardDto.getName())
                .district(district)
                .build();
    }
}
