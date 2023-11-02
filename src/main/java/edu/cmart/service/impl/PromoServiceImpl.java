package edu.cmart.service.impl;

import edu.cmart.model.dto.PromoDto;
import edu.cmart.model.dto.SearchCriteria;
import edu.cmart.model.mapper.PromoMapper;
import edu.cmart.repository.PromoRepository;
import edu.cmart.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static edu.cmart.util.method.Search.getPageable;

@RequiredArgsConstructor
@Service
public class PromoServiceImpl implements PromoService {

    private final PromoRepository promoRepository;
    private final PromoMapper promoMapper;

    @Override
    public Page<PromoDto> findAll(SearchCriteria searchCriteria) {
        return promoRepository.findAll(getPageable(searchCriteria)).map(promoMapper::apply);
    }

    @Override
    public Page<PromoDto> findAllByName(String name, SearchCriteria searchCriteria) {
        return promoRepository.findAllByNameContains(name,
                getPageable(searchCriteria)).map(promoMapper::apply);
    }

    @Override
    public PromoDto save(PromoDto promoDto) {
        return promoMapper.apply(promoRepository.save(promoMapper.applyToPromo(promoDto)));
    }

    @Override
    public PromoDto findById(Long id) {
        return promoRepository.findById(id)
                .map(promoMapper::apply).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        promoRepository.deleteById(id);
    }
}
